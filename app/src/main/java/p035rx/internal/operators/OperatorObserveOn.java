package p035rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import p035rx.Observable;
import p035rx.Producer;
import p035rx.Scheduler;
import p035rx.Subscriber;
import p035rx.exceptions.MissingBackpressureException;
import p035rx.functions.Action0;
import p035rx.internal.schedulers.ImmediateScheduler;
import p035rx.internal.schedulers.TrampolineScheduler;
import p035rx.internal.util.RxRingBuffer;
import p035rx.internal.util.atomic.SpscAtomicArrayQueue;
import p035rx.internal.util.unsafe.SpscArrayQueue;
import p035rx.internal.util.unsafe.UnsafeAccess;
import p035rx.plugins.RxJavaPlugins;
import p035rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes2.dex */
public final class OperatorObserveOn<T> implements Observable.Operator<T, T> {
    private final int bufferSize;
    private final boolean delayError;
    private final Scheduler scheduler;

    public OperatorObserveOn(Scheduler scheduler, boolean z) {
        this(scheduler, z, RxRingBuffer.SIZE);
    }

    public OperatorObserveOn(Scheduler scheduler, boolean z, int i) {
        this.scheduler = scheduler;
        this.delayError = z;
        this.bufferSize = i <= 0 ? RxRingBuffer.SIZE : i;
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        Scheduler scheduler = this.scheduler;
        if ((scheduler instanceof ImmediateScheduler) || (scheduler instanceof TrampolineScheduler)) {
            return subscriber;
        }
        ObserveOnSubscriber observeOnSubscriber = new ObserveOnSubscriber(scheduler, subscriber, this.delayError, this.bufferSize);
        observeOnSubscriber.init();
        return observeOnSubscriber;
    }

    public static <T> Observable.Operator<T, T> rebatch(final int i) {
        return new Observable.Operator<T, T>() { // from class: rx.internal.operators.OperatorObserveOn.1
            @Override // p035rx.functions.Func1
            public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
                ObserveOnSubscriber observeOnSubscriber = new ObserveOnSubscriber(Schedulers.immediate(), subscriber, false, i);
                observeOnSubscriber.init();
                return observeOnSubscriber;
            }
        };
    }

    private static final class ObserveOnSubscriber<T> extends Subscriber<T> implements Action0 {
        final Subscriber<? super T> child;
        final boolean delayError;
        long emitted;
        Throwable error;
        volatile boolean finished;
        final int limit;
        final Queue<Object> queue;
        final Scheduler.Worker recursiveScheduler;
        final AtomicLong requested = new AtomicLong();
        final AtomicLong counter = new AtomicLong();

        /* JADX INFO: renamed from: on */
        final NotificationLite<T> f3497on = NotificationLite.instance();

        public ObserveOnSubscriber(Scheduler scheduler, Subscriber<? super T> subscriber, boolean z, int i) {
            this.child = subscriber;
            this.recursiveScheduler = scheduler.createWorker();
            this.delayError = z;
            i = i <= 0 ? RxRingBuffer.SIZE : i;
            this.limit = i - (i >> 2);
            if (UnsafeAccess.isUnsafeAvailable()) {
                this.queue = new SpscArrayQueue(i);
            } else {
                this.queue = new SpscAtomicArrayQueue(i);
            }
            request(i);
        }

        void init() {
            Subscriber<? super T> subscriber = this.child;
            subscriber.setProducer(new Producer() { // from class: rx.internal.operators.OperatorObserveOn.ObserveOnSubscriber.1
                @Override // p035rx.Producer
                public void request(long j) {
                    if (j > 0) {
                        BackpressureUtils.getAndAddRequest(ObserveOnSubscriber.this.requested, j);
                        ObserveOnSubscriber.this.schedule();
                    }
                }
            });
            subscriber.add(this.recursiveScheduler);
            subscriber.add(this);
        }

        @Override // p035rx.Observer
        public void onNext(T t) {
            if (isUnsubscribed() || this.finished) {
                return;
            }
            if (!this.queue.offer(this.f3497on.next(t))) {
                onError(new MissingBackpressureException());
            } else {
                schedule();
            }
        }

        @Override // p035rx.Observer
        public void onCompleted() {
            if (isUnsubscribed() || this.finished) {
                return;
            }
            this.finished = true;
            schedule();
        }

        @Override // p035rx.Observer
        public void onError(Throwable th) {
            if (isUnsubscribed() || this.finished) {
                RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
                return;
            }
            this.error = th;
            this.finished = true;
            schedule();
        }

        protected void schedule() {
            if (this.counter.getAndIncrement() == 0) {
                this.recursiveScheduler.schedule(this);
            }
        }

        @Override // p035rx.functions.Action0
        public void call() {
            long j = this.emitted;
            Queue<Object> queue = this.queue;
            Subscriber<? super T> subscriber = this.child;
            NotificationLite<T> notificationLite = this.f3497on;
            long jAddAndGet = 1;
            do {
                long jProduced = this.requested.get();
                while (jProduced != j) {
                    boolean z = this.finished;
                    Object objPoll = queue.poll();
                    boolean z2 = objPoll == null;
                    if (checkTerminated(z, z2, subscriber, queue)) {
                        return;
                    }
                    if (z2) {
                        break;
                    }
                    subscriber.onNext(notificationLite.getValue(objPoll));
                    j++;
                    if (j == this.limit) {
                        jProduced = BackpressureUtils.produced(this.requested, j);
                        request(j);
                        j = 0;
                    }
                }
                if (jProduced == j && checkTerminated(this.finished, queue.isEmpty(), subscriber, queue)) {
                    return;
                }
                this.emitted = j;
                jAddAndGet = this.counter.addAndGet(-jAddAndGet);
            } while (jAddAndGet != 0);
        }

        boolean checkTerminated(boolean z, boolean z2, Subscriber<? super T> subscriber, Queue<Object> queue) {
            if (subscriber.isUnsubscribed()) {
                queue.clear();
                return true;
            }
            if (!z) {
                return false;
            }
            if (this.delayError) {
                if (!z2) {
                    return false;
                }
                Throwable th = this.error;
                try {
                    if (th != null) {
                        subscriber.onError(th);
                    } else {
                        subscriber.onCompleted();
                    }
                    return false;
                } finally {
                }
            }
            Throwable th2 = this.error;
            if (th2 != null) {
                queue.clear();
                try {
                    subscriber.onError(th2);
                    return true;
                } finally {
                }
            }
            if (!z2) {
                return false;
            }
            try {
                subscriber.onCompleted();
                return true;
            } finally {
            }
        }
    }
}
