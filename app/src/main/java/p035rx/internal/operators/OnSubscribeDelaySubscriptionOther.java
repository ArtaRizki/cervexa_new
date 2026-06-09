package p035rx.internal.operators;

import p035rx.Observable;
import p035rx.Subscriber;
import p035rx.observers.Subscribers;
import p035rx.plugins.RxJavaPlugins;
import p035rx.subscriptions.SerialSubscription;
import p035rx.subscriptions.Subscriptions;

/* JADX INFO: loaded from: classes2.dex */
public final class OnSubscribeDelaySubscriptionOther<T, U> implements Observable.OnSubscribe<T> {
    final Observable<? extends T> main;
    final Observable<U> other;

    public OnSubscribeDelaySubscriptionOther(Observable<? extends T> observable, Observable<U> observable2) {
        this.main = observable;
        this.other = observable2;
    }

    @Override // p035rx.functions.Action1
    public void call(Subscriber<? super T> subscriber) {
        final SerialSubscription serialSubscription = new SerialSubscription();
        subscriber.add(serialSubscription);
        final Subscriber subscriberWrap = Subscribers.wrap(subscriber);
        Subscriber<U> subscriber2 = new Subscriber<U>() { // from class: rx.internal.operators.OnSubscribeDelaySubscriptionOther.1
            boolean done;

            @Override // p035rx.Observer
            public void onNext(U u) {
                onCompleted();
            }

            @Override // p035rx.Observer
            public void onError(Throwable th) {
                if (this.done) {
                    RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
                } else {
                    this.done = true;
                    subscriberWrap.onError(th);
                }
            }

            @Override // p035rx.Observer
            public void onCompleted() {
                if (this.done) {
                    return;
                }
                this.done = true;
                serialSubscription.set(Subscriptions.unsubscribed());
                OnSubscribeDelaySubscriptionOther.this.main.unsafeSubscribe(subscriberWrap);
            }
        };
        serialSubscription.set(subscriber2);
        this.other.unsafeSubscribe(subscriber2);
    }
}
