package p035rx.subjects;

import p035rx.Observable;
import p035rx.Subscriber;
import p035rx.observers.SerializedObserver;

/* JADX INFO: loaded from: classes2.dex */
public class SerializedSubject<T, R> extends Subject<T, R> {
    private final Subject<T, R> actual;
    private final SerializedObserver<T> observer;

    public SerializedSubject(final Subject<T, R> subject) {
        super(new Observable.OnSubscribe<R>() { // from class: rx.subjects.SerializedSubject.1
            @Override // p035rx.functions.Action1
            public void call(Subscriber<? super R> subscriber) {
                subject.unsafeSubscribe(subscriber);
            }
        });
        this.actual = subject;
        this.observer = new SerializedObserver<>(subject);
    }

    @Override // p035rx.Observer
    public void onCompleted() {
        this.observer.onCompleted();
    }

    @Override // p035rx.Observer
    public void onError(Throwable th) {
        this.observer.onError(th);
    }

    @Override // p035rx.Observer
    public void onNext(T t) {
        this.observer.onNext(t);
    }

    @Override // p035rx.subjects.Subject
    public boolean hasObservers() {
        return this.actual.hasObservers();
    }
}
