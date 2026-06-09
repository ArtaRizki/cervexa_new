package p035rx.internal.operators;

import p035rx.Observable;
import p035rx.Subscriber;
import p035rx.observers.SerializedSubscriber;

/* JADX INFO: loaded from: classes2.dex */
public final class OperatorSerialize<T> implements Observable.Operator<T, T> {

    private static final class Holder {
        static final OperatorSerialize<Object> INSTANCE = new OperatorSerialize<>();

        private Holder() {
        }
    }

    public static <T> OperatorSerialize<T> instance() {
        return (OperatorSerialize<T>) Holder.INSTANCE;
    }

    OperatorSerialize() {
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        return new SerializedSubscriber(new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorSerialize.1
            @Override // p035rx.Observer
            public void onCompleted() {
                subscriber.onCompleted();
            }

            @Override // p035rx.Observer
            public void onError(Throwable th) {
                subscriber.onError(th);
            }

            @Override // p035rx.Observer
            public void onNext(T t) {
                subscriber.onNext(t);
            }
        });
    }
}
