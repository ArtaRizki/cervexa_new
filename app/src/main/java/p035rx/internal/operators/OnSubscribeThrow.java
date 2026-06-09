package p035rx.internal.operators;

import p035rx.Observable;
import p035rx.Subscriber;

/* JADX INFO: loaded from: classes2.dex */
public final class OnSubscribeThrow<T> implements Observable.OnSubscribe<T> {
    private final Throwable exception;

    public OnSubscribeThrow(Throwable th) {
        this.exception = th;
    }

    @Override // p035rx.functions.Action1
    public void call(Subscriber<? super T> subscriber) {
        subscriber.onError(this.exception);
    }
}
