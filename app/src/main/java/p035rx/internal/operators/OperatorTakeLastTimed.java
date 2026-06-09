package p035rx.internal.operators;

import java.util.ArrayDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import p035rx.Observable;
import p035rx.Producer;
import p035rx.Scheduler;
import p035rx.Subscriber;
import p035rx.functions.Func1;

/* JADX INFO: loaded from: classes2.dex */
public final class OperatorTakeLastTimed<T> implements Observable.Operator<T, T> {
    final long ageMillis;
    final int count;
    final Scheduler scheduler;

    public OperatorTakeLastTimed(long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.ageMillis = timeUnit.toMillis(j);
        this.scheduler = scheduler;
        this.count = -1;
    }

    public OperatorTakeLastTimed(int i, long j, TimeUnit timeUnit, Scheduler scheduler) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("count could not be negative");
        }
        this.ageMillis = timeUnit.toMillis(j);
        this.scheduler = scheduler;
        this.count = i;
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        final TakeLastTimedSubscriber takeLastTimedSubscriber = new TakeLastTimedSubscriber(subscriber, this.count, this.ageMillis, this.scheduler);
        subscriber.add(takeLastTimedSubscriber);
        subscriber.setProducer(new Producer() { // from class: rx.internal.operators.OperatorTakeLastTimed.1
            @Override // p035rx.Producer
            public void request(long j) {
                takeLastTimedSubscriber.requestMore(j);
            }
        });
        return takeLastTimedSubscriber;
    }

    static final class TakeLastTimedSubscriber<T> extends Subscriber<T> implements Func1<Object, T> {
        final Subscriber<? super T> actual;
        final long ageMillis;
        final int count;
        final Scheduler scheduler;
        final AtomicLong requested = new AtomicLong();
        final ArrayDeque<Object> queue = new ArrayDeque<>();
        final ArrayDeque<Long> queueTimes = new ArrayDeque<>();

        /* JADX INFO: renamed from: nl */
        final NotificationLite<T> f3508nl = NotificationLite.instance();

        public TakeLastTimedSubscriber(Subscriber<? super T> subscriber, int i, long j, Scheduler scheduler) {
            this.actual = subscriber;
            this.count = i;
            this.ageMillis = j;
            this.scheduler = scheduler;
        }

        @Override // p035rx.Observer
        public void onNext(T t) {
            if (this.count != 0) {
                long jNow = this.scheduler.now();
                if (this.queue.size() == this.count) {
                    this.queue.poll();
                    this.queueTimes.poll();
                }
                evictOld(jNow);
                this.queue.offer(this.f3508nl.next(t));
                this.queueTimes.offer(Long.valueOf(jNow));
            }
        }

        protected void evictOld(long j) {
            long j2 = j - this.ageMillis;
            while (true) {
                Long lPeek = this.queueTimes.peek();
                if (lPeek == null || lPeek.longValue() >= j2) {
                    return;
                }
                this.queue.poll();
                this.queueTimes.poll();
            }
        }

        @Override // p035rx.Observer
        public void onError(Throwable th) {
            this.queue.clear();
            this.queueTimes.clear();
            this.actual.onError(th);
        }

        @Override // p035rx.Observer
        public void onCompleted() {
            evictOld(this.scheduler.now());
            this.queueTimes.clear();
            BackpressureUtils.postCompleteDone(this.requested, this.queue, this.actual, this);
        }

        @Override // p035rx.functions.Func1
        public T call(Object obj) {
            return this.f3508nl.getValue(obj);
        }

        void requestMore(long j) {
            BackpressureUtils.postCompleteRequest(this.requested, j, this.queue, this.actual, this);
        }
    }
}
