package p035rx.internal.operators;

import java.util.concurrent.atomic.AtomicReference;
import p035rx.Observable;
import p035rx.Subscriber;
import p035rx.exceptions.Exceptions;
import p035rx.functions.Func2;
import p035rx.observers.SerializedSubscriber;

/* JADX INFO: loaded from: classes2.dex */
public final class OperatorWithLatestFrom<T, U, R> implements Observable.Operator<R, T> {
    static final Object EMPTY = new Object();
    final Observable<? extends U> other;
    final Func2<? super T, ? super U, ? extends R> resultSelector;

    public OperatorWithLatestFrom(Observable<? extends U> observable, Func2<? super T, ? super U, ? extends R> func2) {
        this.other = observable;
        this.resultSelector = func2;
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super T> call(Subscriber<? super R> subscriber) {
        final SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber, false);
        subscriber.add(serializedSubscriber);
        final AtomicReference atomicReference = new AtomicReference(EMPTY);
        Subscriber<T> subscriber2 = new Subscriber<T>(serializedSubscriber, true) { // from class: rx.internal.operators.OperatorWithLatestFrom.1
            @Override // p035rx.Observer
            public void onNext(T t) {
                Object obj = atomicReference.get();
                if (obj != OperatorWithLatestFrom.EMPTY) {
                    try {
                        serializedSubscriber.onNext(OperatorWithLatestFrom.this.resultSelector.call(t, obj));
                    } catch (Throwable th) {
                        Exceptions.throwOrReport(th, this);
                    }
                }
            }

            @Override // p035rx.Observer
            public void onError(Throwable th) {
                serializedSubscriber.onError(th);
                serializedSubscriber.unsubscribe();
            }

            @Override // p035rx.Observer
            public void onCompleted() {
                serializedSubscriber.onCompleted();
                serializedSubscriber.unsubscribe();
            }
        };
        Subscriber<U> subscriber3 = new Subscriber<U>() { // from class: rx.internal.operators.OperatorWithLatestFrom.2
            @Override // p035rx.Observer
            public void onNext(U u) {
                atomicReference.set(u);
            }

            @Override // p035rx.Observer
            public void onError(Throwable th) {
                serializedSubscriber.onError(th);
                serializedSubscriber.unsubscribe();
            }

            @Override // p035rx.Observer
            public void onCompleted() {
                if (atomicReference.get() == OperatorWithLatestFrom.EMPTY) {
                    serializedSubscriber.onCompleted();
                    serializedSubscriber.unsubscribe();
                }
            }
        };
        serializedSubscriber.add(subscriber2);
        serializedSubscriber.add(subscriber3);
        this.other.unsafeSubscribe(subscriber3);
        return subscriber2;
    }
}
