package p035rx.internal.operators;

import p035rx.Observable;
import p035rx.Subscriber;
import p035rx.exceptions.Exceptions;
import p035rx.functions.Action0;
import p035rx.plugins.RxJavaPlugins;

/* JADX INFO: loaded from: classes2.dex */
public final class OperatorDoAfterTerminate<T> implements Observable.Operator<T, T> {
    final Action0 action;

    public OperatorDoAfterTerminate(Action0 action0) {
        if (action0 == null) {
            throw new NullPointerException("Action can not be null");
        }
        this.action = action0;
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        return new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorDoAfterTerminate.1
            @Override // p035rx.Observer
            public void onNext(T t) {
                subscriber.onNext(t);
            }

            @Override // p035rx.Observer
            public void onError(Throwable th) {
                try {
                    subscriber.onError(th);
                } finally {
                    callAction();
                }
            }

            @Override // p035rx.Observer
            public void onCompleted() {
                try {
                    subscriber.onCompleted();
                } finally {
                    callAction();
                }
            }

            void callAction() {
                try {
                    OperatorDoAfterTerminate.this.action.call();
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
                }
            }
        };
    }
}
