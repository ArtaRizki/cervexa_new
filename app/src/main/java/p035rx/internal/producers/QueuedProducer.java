package p035rx.internal.producers;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.jvm.internal.LongCompanionObject;
import p035rx.Observer;
import p035rx.Producer;
import p035rx.Subscriber;
import p035rx.exceptions.Exceptions;
import p035rx.exceptions.MissingBackpressureException;
import p035rx.internal.operators.BackpressureUtils;
import p035rx.internal.util.atomic.SpscLinkedAtomicQueue;
import p035rx.internal.util.unsafe.SpscLinkedQueue;
import p035rx.internal.util.unsafe.UnsafeAccess;

/* JADX INFO: loaded from: classes2.dex */
public final class QueuedProducer<T> extends AtomicLong implements Producer, Observer<T> {
    static final Object NULL_SENTINEL = new Object();
    private static final long serialVersionUID = 7277121710709137047L;
    final Subscriber<? super T> child;
    volatile boolean done;
    Throwable error;
    final Queue<Object> queue;
    final AtomicInteger wip;

    public QueuedProducer(Subscriber<? super T> subscriber) {
        this(subscriber, UnsafeAccess.isUnsafeAvailable() ? new SpscLinkedQueue() : new SpscLinkedAtomicQueue());
    }

    public QueuedProducer(Subscriber<? super T> subscriber, Queue<Object> queue) {
        this.child = subscriber;
        this.queue = queue;
        this.wip = new AtomicInteger();
    }

    @Override // p035rx.Producer
    public void request(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("n >= 0 required");
        }
        if (j > 0) {
            BackpressureUtils.getAndAddRequest(this, j);
            drain();
        }
    }

    public boolean offer(T t) {
        if (t == null) {
            if (!this.queue.offer(NULL_SENTINEL)) {
                return false;
            }
        } else if (!this.queue.offer(t)) {
            return false;
        }
        drain();
        return true;
    }

    @Override // p035rx.Observer
    public void onNext(T t) {
        if (offer(t)) {
            return;
        }
        onError(new MissingBackpressureException());
    }

    @Override // p035rx.Observer
    public void onError(Throwable th) {
        this.error = th;
        this.done = true;
        drain();
    }

    @Override // p035rx.Observer
    public void onCompleted() {
        this.done = true;
        drain();
    }

    private boolean checkTerminated(boolean z, boolean z2) {
        if (this.child.isUnsubscribed()) {
            return true;
        }
        if (!z) {
            return false;
        }
        Throwable th = this.error;
        if (th != null) {
            this.queue.clear();
            this.child.onError(th);
            return true;
        }
        if (!z2) {
            return false;
        }
        this.child.onCompleted();
        return true;
    }

    private void drain() {
        if (this.wip.getAndIncrement() == 0) {
            Subscriber<? super T> subscriber = this.child;
            Queue<Object> queue = this.queue;
            while (!checkTerminated(this.done, queue.isEmpty())) {
                this.wip.lazySet(1);
                long j = get();
                long j2 = 0;
                while (j != 0) {
                    boolean z = this.done;
                    Object objPoll = queue.poll();
                    if (checkTerminated(z, objPoll == null)) {
                        return;
                    }
                    if (objPoll == null) {
                        break;
                    }
                    try {
                        if (objPoll == NULL_SENTINEL) {
                            subscriber.onNext(null);
                        } else {
                            subscriber.onNext(objPoll);
                        }
                        j--;
                        j2++;
                    } catch (Throwable th) {
                        if (objPoll == NULL_SENTINEL) {
                            objPoll = null;
                        }
                        Exceptions.throwOrReport(th, subscriber, objPoll);
                        return;
                    }
                }
                if (j2 != 0 && get() != LongCompanionObject.MAX_VALUE) {
                    addAndGet(-j2);
                }
                if (this.wip.decrementAndGet() == 0) {
                    return;
                }
            }
        }
    }
}
