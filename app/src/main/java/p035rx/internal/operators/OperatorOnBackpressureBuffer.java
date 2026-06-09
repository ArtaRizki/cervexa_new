package p035rx.internal.operators;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.jvm.internal.LongCompanionObject;
import p035rx.BackpressureOverflow;
import p035rx.Observable;
import p035rx.Producer;
import p035rx.Subscriber;
import p035rx.exceptions.Exceptions;
import p035rx.exceptions.MissingBackpressureException;
import p035rx.functions.Action0;
import p035rx.internal.util.BackpressureDrainManager;

/* JADX INFO: loaded from: classes2.dex */
public class OperatorOnBackpressureBuffer<T> implements Observable.Operator<T, T> {
    private final Long capacity;
    private final Action0 onOverflow;
    private final BackpressureOverflow.Strategy overflowStrategy;

    private static class Holder {
        static final OperatorOnBackpressureBuffer<?> INSTANCE = new OperatorOnBackpressureBuffer<>();

        private Holder() {
        }
    }

    public static <T> OperatorOnBackpressureBuffer<T> instance() {
        return (OperatorOnBackpressureBuffer<T>) Holder.INSTANCE;
    }

    OperatorOnBackpressureBuffer() {
        this.capacity = null;
        this.onOverflow = null;
        this.overflowStrategy = BackpressureOverflow.ON_OVERFLOW_DEFAULT;
    }

    public OperatorOnBackpressureBuffer(long j) {
        this(j, null, BackpressureOverflow.ON_OVERFLOW_DEFAULT);
    }

    public OperatorOnBackpressureBuffer(long j, Action0 action0) {
        this(j, action0, BackpressureOverflow.ON_OVERFLOW_DEFAULT);
    }

    public OperatorOnBackpressureBuffer(long j, Action0 action0, BackpressureOverflow.Strategy strategy) {
        if (j <= 0) {
            throw new IllegalArgumentException("Buffer capacity must be > 0");
        }
        if (strategy == null) {
            throw new NullPointerException("The BackpressureOverflow strategy must not be null");
        }
        this.capacity = Long.valueOf(j);
        this.onOverflow = action0;
        this.overflowStrategy = strategy;
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        BufferSubscriber bufferSubscriber = new BufferSubscriber(subscriber, this.capacity, this.onOverflow, this.overflowStrategy);
        subscriber.add(bufferSubscriber);
        subscriber.setProducer(bufferSubscriber.manager());
        return bufferSubscriber;
    }

    private static final class BufferSubscriber<T> extends Subscriber<T> implements BackpressureDrainManager.BackpressureQueueCallback {
        private final AtomicLong capacity;
        private final Subscriber<? super T> child;
        private final BackpressureDrainManager manager;
        private final Action0 onOverflow;
        private final BackpressureOverflow.Strategy overflowStrategy;
        private final ConcurrentLinkedQueue<Object> queue = new ConcurrentLinkedQueue<>();
        private final AtomicBoolean saturated = new AtomicBoolean(false);

        /* JADX INFO: renamed from: on */
        private final NotificationLite<T> f3498on = NotificationLite.instance();

        public BufferSubscriber(Subscriber<? super T> subscriber, Long l, Action0 action0, BackpressureOverflow.Strategy strategy) {
            this.child = subscriber;
            this.capacity = l != null ? new AtomicLong(l.longValue()) : null;
            this.onOverflow = action0;
            this.manager = new BackpressureDrainManager(this);
            this.overflowStrategy = strategy;
        }

        @Override // p035rx.Subscriber
        public void onStart() {
            request(LongCompanionObject.MAX_VALUE);
        }

        @Override // p035rx.Observer
        public void onCompleted() throws Throwable {
            if (this.saturated.get()) {
                return;
            }
            this.manager.terminateAndDrain();
        }

        @Override // p035rx.Observer
        public void onError(Throwable th) throws Throwable {
            if (this.saturated.get()) {
                return;
            }
            this.manager.terminateAndDrain(th);
        }

        @Override // p035rx.Observer
        public void onNext(T t) throws Throwable {
            if (assertCapacity()) {
                this.queue.offer(this.f3498on.next(t));
                this.manager.drain();
            }
        }

        @Override // rx.internal.util.BackpressureDrainManager.BackpressureQueueCallback
        public boolean accept(Object obj) {
            return this.f3498on.accept(this.child, obj);
        }

        @Override // rx.internal.util.BackpressureDrainManager.BackpressureQueueCallback
        public void complete(Throwable th) {
            if (th != null) {
                this.child.onError(th);
            } else {
                this.child.onCompleted();
            }
        }

        @Override // rx.internal.util.BackpressureDrainManager.BackpressureQueueCallback
        public Object peek() {
            return this.queue.peek();
        }

        @Override // rx.internal.util.BackpressureDrainManager.BackpressureQueueCallback
        public Object poll() {
            Object objPoll = this.queue.poll();
            AtomicLong atomicLong = this.capacity;
            if (atomicLong != null && objPoll != null) {
                atomicLong.incrementAndGet();
            }
            return objPoll;
        }

        private boolean assertCapacity() throws Throwable {
            long j;
            if (this.capacity == null) {
                return true;
            }
            do {
                j = this.capacity.get();
                if (j <= 0) {
                    try {
                    } catch (MissingBackpressureException e) {
                        if (this.saturated.compareAndSet(false, true)) {
                            unsubscribe();
                            this.child.onError(e);
                        }
                    }
                    boolean z = this.overflowStrategy.mayAttemptDrop() && poll() != null;
                    Action0 action0 = this.onOverflow;
                    if (action0 != null) {
                        try {
                            action0.call();
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            this.manager.terminateAndDrain(th);
                            return false;
                        }
                    }
                    if (!z) {
                        return false;
                    }
                }
            } while (!this.capacity.compareAndSet(j, j - 1));
            return true;
        }

        protected Producer manager() {
            return this.manager;
        }
    }
}
