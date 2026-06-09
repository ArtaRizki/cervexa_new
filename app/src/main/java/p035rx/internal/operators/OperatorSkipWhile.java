package p035rx.internal.operators;

import p035rx.Observable;
import p035rx.Subscriber;
import p035rx.exceptions.Exceptions;
import p035rx.functions.Func1;
import p035rx.functions.Func2;

/* JADX INFO: loaded from: classes2.dex */
public final class OperatorSkipWhile<T> implements Observable.Operator<T, T> {
    final Func2<? super T, Integer, Boolean> predicate;

    public OperatorSkipWhile(Func2<? super T, Integer, Boolean> func2) {
        this.predicate = func2;
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        return new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorSkipWhile.1
            int index;
            boolean skipping = true;

            @Override // p035rx.Observer
            public void onNext(T t) {
                if (!this.skipping) {
                    subscriber.onNext(t);
                    return;
                }
                try {
                    Func2<? super T, Integer, Boolean> func2 = OperatorSkipWhile.this.predicate;
                    int i = this.index;
                    this.index = i + 1;
                    if (!func2.call(t, Integer.valueOf(i)).booleanValue()) {
                        this.skipping = false;
                        subscriber.onNext(t);
                    } else {
                        request(1L);
                    }
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

    public static <T> Func2<T, Integer, Boolean> toPredicate2(final Func1<? super T, Boolean> func1) {
        return new Func2<T, Integer, Boolean>() { // from class: rx.internal.operators.OperatorSkipWhile.2
            @Override // p035rx.functions.Func2
            public Boolean call(T t, Integer num) {
                return (Boolean) func1.call(t);
            }
        };
    }
}
