package p035rx.internal.operators;

import java.util.concurrent.atomic.AtomicReference;
import p035rx.Observable;
import p035rx.Subscriber;
import p035rx.Subscription;
import p035rx.observers.SerializedSubscriber;

/* JADX INFO: loaded from: classes2.dex */
public final class OperatorSampleWithObservable<T, U> implements Observable.Operator<T, T> {
    static final Object EMPTY_TOKEN = new Object();
    final Observable<U> sampler;

    public OperatorSampleWithObservable(Observable<U> observable) {
        this.sampler = observable;
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        final SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber);
        final AtomicReference atomicReference = new AtomicReference(EMPTY_TOKEN);
        final AtomicReference atomicReference2 = new AtomicReference();
        final Subscriber<U> subscriber2 = new Subscriber<U>() { // from class: rx.internal.operators.OperatorSampleWithObservable.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // p035rx.Observer
            public void onNext(U u) {
                Object andSet = atomicReference.getAndSet(OperatorSampleWithObservable.EMPTY_TOKEN);
                if (andSet != OperatorSampleWithObservable.EMPTY_TOKEN) {
                    serializedSubscriber.onNext(andSet);
                }
            }

            @Override // p035rx.Observer
            public void onError(Throwable th) {
                serializedSubscriber.onError(th);
                ((Subscription) atomicReference2.get()).unsubscribe();
            }

            @Override // p035rx.Observer
            public void onCompleted() {
                onNext(null);
                serializedSubscriber.onCompleted();
                ((Subscription) atomicReference2.get()).unsubscribe();
            }
        };
        Subscriber<T> subscriber3 = new Subscriber<T>() { // from class: rx.internal.operators.OperatorSampleWithObservable.2
            @Override // p035rx.Observer
            public void onNext(T t) {
                atomicReference.set(t);
            }

            @Override // p035rx.Observer
            public void onError(Throwable th) {
                serializedSubscriber.onError(th);
                subscriber2.unsubscribe();
            }

            @Override // p035rx.Observer
            public void onCompleted() {
                subscriber2.onNext(null);
                serializedSubscriber.onCompleted();
                subscriber2.unsubscribe();
            }
        };
        atomicReference2.lazySet(subscriber3);
        subscriber.add(subscriber3);
        subscriber.add(subscriber2);
        this.sampler.unsafeSubscribe(subscriber2);
        return subscriber3;
    }
}
