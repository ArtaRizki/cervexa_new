package p035rx.observers;

import p035rx.Observer;
import p035rx.Subscriber;

/* JADX INFO: loaded from: classes2.dex */
public class SerializedSubscriber<T> extends Subscriber<T> {

    /* JADX INFO: renamed from: s */
    private final Observer<T> f3541s;

    public SerializedSubscriber(Subscriber<? super T> subscriber) {
        this(subscriber, true);
    }

    public SerializedSubscriber(Subscriber<? super T> subscriber, boolean z) {
        super(subscriber, z);
        this.f3541s = new SerializedObserver(subscriber);
    }

    @Override // p035rx.Observer
    public void onCompleted() {
        this.f3541s.onCompleted();
    }

    @Override // p035rx.Observer
    public void onError(Throwable th) {
        this.f3541s.onError(th);
    }

    @Override // p035rx.Observer
    public void onNext(T t) {
        this.f3541s.onNext(t);
    }
}
