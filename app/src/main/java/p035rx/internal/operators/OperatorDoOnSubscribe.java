package p035rx.internal.operators;

import p035rx.Observable;
import p035rx.Subscriber;
import p035rx.functions.Action0;
import p035rx.observers.Subscribers;

/* JADX INFO: loaded from: classes2.dex */
public class OperatorDoOnSubscribe<T> implements Observable.Operator<T, T> {
    private final Action0 subscribe;

    public OperatorDoOnSubscribe(Action0 action0) {
        this.subscribe = action0;
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        this.subscribe.call();
        return Subscribers.wrap(subscriber);
    }
}
