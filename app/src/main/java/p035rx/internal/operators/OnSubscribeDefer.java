package p035rx.internal.operators;

import p035rx.Observable;
import p035rx.Subscriber;
import p035rx.exceptions.Exceptions;
import p035rx.functions.Func0;
import p035rx.observers.Subscribers;

/* JADX INFO: loaded from: classes2.dex */
public final class OnSubscribeDefer<T> implements Observable.OnSubscribe<T> {
    final Func0<? extends Observable<? extends T>> observableFactory;

    public OnSubscribeDefer(Func0<? extends Observable<? extends T>> func0) {
        this.observableFactory = func0;
    }

    @Override // p035rx.functions.Action1
    public void call(Subscriber<? super T> subscriber) {
        try {
            this.observableFactory.call().unsafeSubscribe(Subscribers.wrap(subscriber));
        } catch (Throwable th) {
            Exceptions.throwOrReport(th, subscriber);
        }
    }
}
