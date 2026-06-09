package p035rx.internal.util;

import p035rx.Observer;
import p035rx.Subscriber;

/* JADX INFO: loaded from: classes2.dex */
public final class ObserverSubscriber<T> extends Subscriber<T> {
    final Observer<? super T> observer;

    public ObserverSubscriber(Observer<? super T> observer) {
        this.observer = observer;
    }

    @Override // p035rx.Observer
    public void onNext(T t) {
        this.observer.onNext(t);
    }

    @Override // p035rx.Observer
    public void onError(Throwable th) {
        this.observer.onError(th);
    }

    @Override // p035rx.Observer
    public void onCompleted() {
        this.observer.onCompleted();
    }
}
