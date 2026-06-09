package p035rx.internal.operators;

import p035rx.Observable;
import p035rx.Subscriber;
import p035rx.exceptions.Exceptions;
import p035rx.functions.Func0;
import p035rx.observers.Subscribers;

/* JADX INFO: loaded from: classes2.dex */
public final class OnSubscribeDelaySubscriptionWithSelector<T, U> implements Observable.OnSubscribe<T> {
    final Observable<? extends T> source;
    final Func0<? extends Observable<U>> subscriptionDelay;

    public OnSubscribeDelaySubscriptionWithSelector(Observable<? extends T> observable, Func0<? extends Observable<U>> func0) {
        this.source = observable;
        this.subscriptionDelay = func0;
    }

    @Override // p035rx.functions.Action1
    public void call(final Subscriber<? super T> subscriber) {
        try {
            this.subscriptionDelay.call().take(1).unsafeSubscribe(new Subscriber<U>() { // from class: rx.internal.operators.OnSubscribeDelaySubscriptionWithSelector.1
                @Override // p035rx.Observer
                public void onNext(U u) {
                }

                @Override // p035rx.Observer
                public void onCompleted() {
                    OnSubscribeDelaySubscriptionWithSelector.this.source.unsafeSubscribe(Subscribers.wrap(subscriber));
                }

                @Override // p035rx.Observer
                public void onError(Throwable th) {
                    subscriber.onError(th);
                }
            });
        } catch (Throwable th) {
            Exceptions.throwOrReport(th, subscriber);
        }
    }
}
