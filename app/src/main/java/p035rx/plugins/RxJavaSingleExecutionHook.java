package p035rx.plugins;

import p035rx.Observable;
import p035rx.Single;
import p035rx.Subscription;

/* JADX INFO: loaded from: classes2.dex */
public abstract class RxJavaSingleExecutionHook {
    public <T> Single.OnSubscribe<T> onCreate(Single.OnSubscribe<T> onSubscribe) {
        return onSubscribe;
    }

    public <T, R> Observable.Operator<? extends R, ? super T> onLift(Observable.Operator<? extends R, ? super T> operator) {
        return operator;
    }

    public <T> Throwable onSubscribeError(Throwable th) {
        return th;
    }

    public <T> Subscription onSubscribeReturn(Subscription subscription) {
        return subscription;
    }

    public <T> Observable.OnSubscribe<T> onSubscribeStart(Single<? extends T> single, Observable.OnSubscribe<T> onSubscribe) {
        return onSubscribe;
    }
}
