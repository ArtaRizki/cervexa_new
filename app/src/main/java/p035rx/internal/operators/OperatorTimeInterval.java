package p035rx.internal.operators;

import p035rx.Observable;
import p035rx.Scheduler;
import p035rx.Subscriber;
import p035rx.schedulers.TimeInterval;

/* JADX INFO: loaded from: classes2.dex */
public final class OperatorTimeInterval<T> implements Observable.Operator<TimeInterval<T>, T> {
    final Scheduler scheduler;

    public OperatorTimeInterval(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super T> call(final Subscriber<? super TimeInterval<T>> subscriber) {
        return new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorTimeInterval.1
            private long lastTimestamp;

            {
                this.lastTimestamp = OperatorTimeInterval.this.scheduler.now();
            }

            @Override // p035rx.Observer
            public void onNext(T t) {
                long jNow = OperatorTimeInterval.this.scheduler.now();
                subscriber.onNext(new TimeInterval(jNow - this.lastTimestamp, t));
                this.lastTimestamp = jNow;
            }

            @Override // p035rx.Observer
            public void onCompleted() {
                subscriber.onCompleted();
            }

            @Override // p035rx.Observer
            public void onError(Throwable th) {
                subscriber.onError(th);
            }
        };
    }
}
