package p035rx.internal.operators;

import kotlin.jvm.internal.LongCompanionObject;
import p035rx.Observable;
import p035rx.Subscriber;
import p035rx.observers.SerializedSubscriber;

/* JADX INFO: loaded from: classes2.dex */
public final class OperatorTakeUntil<T, E> implements Observable.Operator<T, T> {
    private final Observable<? extends E> other;

    public OperatorTakeUntil(Observable<? extends E> observable) {
        this.other = observable;
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        final SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber, false);
        final Subscriber<T> subscriber2 = new Subscriber<T>(serializedSubscriber, false) { // from class: rx.internal.operators.OperatorTakeUntil.1
            @Override // p035rx.Observer
            public void onNext(T t) {
                serializedSubscriber.onNext(t);
            }

            @Override // p035rx.Observer
            public void onError(Throwable th) {
                try {
                    serializedSubscriber.onError(th);
                } finally {
                    serializedSubscriber.unsubscribe();
                }
            }

            @Override // p035rx.Observer
            public void onCompleted() {
                try {
                    serializedSubscriber.onCompleted();
                } finally {
                    serializedSubscriber.unsubscribe();
                }
            }
        };
        Subscriber<E> subscriber3 = new Subscriber<E>() { // from class: rx.internal.operators.OperatorTakeUntil.2
            @Override // p035rx.Subscriber
            public void onStart() {
                request(LongCompanionObject.MAX_VALUE);
            }

            @Override // p035rx.Observer
            public void onCompleted() {
                subscriber2.onCompleted();
            }

            @Override // p035rx.Observer
            public void onError(Throwable th) {
                subscriber2.onError(th);
            }

            @Override // p035rx.Observer
            public void onNext(E e) {
                onCompleted();
            }
        };
        serializedSubscriber.add(subscriber2);
        serializedSubscriber.add(subscriber3);
        subscriber.add(serializedSubscriber);
        this.other.unsafeSubscribe(subscriber3);
        return subscriber2;
    }
}
