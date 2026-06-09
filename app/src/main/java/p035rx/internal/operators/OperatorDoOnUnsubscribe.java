package p035rx.internal.operators;

import p035rx.Observable;
import p035rx.Subscriber;
import p035rx.functions.Action0;
import p035rx.observers.Subscribers;
import p035rx.subscriptions.Subscriptions;

/* JADX INFO: loaded from: classes2.dex */
public class OperatorDoOnUnsubscribe<T> implements Observable.Operator<T, T> {
    private final Action0 unsubscribe;

    public OperatorDoOnUnsubscribe(Action0 action0) {
        this.unsubscribe = action0;
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        subscriber.add(Subscriptions.create(this.unsubscribe));
        return Subscribers.wrap(subscriber);
    }
}
