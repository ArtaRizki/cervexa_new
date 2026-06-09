package p035rx.internal.operators;

import p035rx.Observable;
import p035rx.Single;
import p035rx.SingleSubscriber;
import p035rx.Subscriber;
import p035rx.plugins.RxJavaPlugins;
import p035rx.subscriptions.SerialSubscription;

/* JADX INFO: loaded from: classes2.dex */
public final class SingleOnSubscribeDelaySubscriptionOther<T> implements Single.OnSubscribe<T> {
    final Single<? extends T> main;
    final Observable<?> other;

    public SingleOnSubscribeDelaySubscriptionOther(Single<? extends T> single, Observable<?> observable) {
        this.main = single;
        this.other = observable;
    }

    @Override // p035rx.functions.Action1
    public void call(final SingleSubscriber<? super T> singleSubscriber) {
        final SingleSubscriber<T> singleSubscriber2 = new SingleSubscriber<T>() { // from class: rx.internal.operators.SingleOnSubscribeDelaySubscriptionOther.1
            @Override // p035rx.SingleSubscriber
            public void onSuccess(T t) {
                singleSubscriber.onSuccess(t);
            }

            @Override // p035rx.SingleSubscriber
            public void onError(Throwable th) {
                singleSubscriber.onError(th);
            }
        };
        final SerialSubscription serialSubscription = new SerialSubscription();
        singleSubscriber.add(serialSubscription);
        Subscriber<? super Object> subscriber = new Subscriber<Object>() { // from class: rx.internal.operators.SingleOnSubscribeDelaySubscriptionOther.2
            boolean done;

            @Override // p035rx.Observer
            public void onNext(Object obj) {
                onCompleted();
            }

            @Override // p035rx.Observer
            public void onError(Throwable th) {
                if (this.done) {
                    RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
                } else {
                    this.done = true;
                    singleSubscriber2.onError(th);
                }
            }

            @Override // p035rx.Observer
            public void onCompleted() {
                if (this.done) {
                    return;
                }
                this.done = true;
                serialSubscription.set(singleSubscriber2);
                SingleOnSubscribeDelaySubscriptionOther.this.main.subscribe(singleSubscriber2);
            }
        };
        serialSubscription.set(subscriber);
        this.other.subscribe(subscriber);
    }
}
