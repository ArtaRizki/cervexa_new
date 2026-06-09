package p035rx.internal.operators;

import p035rx.Observable;
import p035rx.Scheduler;
import p035rx.Subscriber;
import p035rx.schedulers.Timestamped;

/* JADX INFO: loaded from: classes2.dex */
public final class OperatorTimestamp<T> implements Observable.Operator<Timestamped<T>, T> {
    final Scheduler scheduler;

    public OperatorTimestamp(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super T> call(final Subscriber<? super Timestamped<T>> subscriber) {
        return new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorTimestamp.1
            @Override // p035rx.Observer
            public void onCompleted() {
                subscriber.onCompleted();
            }

            @Override // p035rx.Observer
            public void onError(Throwable th) {
                subscriber.onError(th);
            }

            @Override // p035rx.Observer
            public void onNext(T t) {
                subscriber.onNext(new Timestamped(OperatorTimestamp.this.scheduler.now(), t));
            }
        };
    }
}
