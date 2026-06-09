package p035rx.internal.operators;

import p035rx.Observable;
import p035rx.Subscriber;
import p035rx.exceptions.Exceptions;
import p035rx.functions.Func1;
import p035rx.internal.util.UtilityFunctions;

/* JADX INFO: loaded from: classes2.dex */
public final class OperatorDistinctUntilChanged<T, U> implements Observable.Operator<T, T> {
    final Func1<? super T, ? extends U> keySelector;

    private static class Holder {
        static final OperatorDistinctUntilChanged<?, ?> INSTANCE = new OperatorDistinctUntilChanged<>(UtilityFunctions.identity());

        private Holder() {
        }
    }

    public static <T> OperatorDistinctUntilChanged<T, T> instance() {
        return (OperatorDistinctUntilChanged<T, T>) Holder.INSTANCE;
    }

    public OperatorDistinctUntilChanged(Func1<? super T, ? extends U> func1) {
        this.keySelector = func1;
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        return new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorDistinctUntilChanged.1
            boolean hasPrevious;
            U previousKey;

            @Override // p035rx.Observer
            public void onNext(T t) {
                U u = this.previousKey;
                try {
                    U uCall = OperatorDistinctUntilChanged.this.keySelector.call(t);
                    this.previousKey = uCall;
                    if (this.hasPrevious) {
                        if (u != uCall && (uCall == null || !uCall.equals(u))) {
                            subscriber.onNext(t);
                            return;
                        } else {
                            request(1L);
                            return;
                        }
                    }
                    this.hasPrevious = true;
                    subscriber.onNext(t);
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, subscriber, t);
                }
            }

            @Override // p035rx.Observer
            public void onError(Throwable th) {
                subscriber.onError(th);
            }

            @Override // p035rx.Observer
            public void onCompleted() {
                subscriber.onCompleted();
            }
        };
    }
}
