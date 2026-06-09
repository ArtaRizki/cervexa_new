package p035rx.internal.operators;

import p035rx.Observable;
import p035rx.Subscriber;

/* JADX INFO: loaded from: classes2.dex */
public enum NeverObservableHolder implements Observable.OnSubscribe<Object> {
    INSTANCE;

    static final Observable<Object> NEVER = Observable.create(INSTANCE);

    @Override // p035rx.functions.Action1
    public void call(Subscriber<? super Object> subscriber) {
    }

    public static <T> Observable<T> instance() {
        return (Observable<T>) NEVER;
    }
}
