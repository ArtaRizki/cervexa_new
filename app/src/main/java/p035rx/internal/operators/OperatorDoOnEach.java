package p035rx.internal.operators;

import java.util.Arrays;
import p035rx.Observable;
import p035rx.Observer;
import p035rx.Subscriber;
import p035rx.exceptions.CompositeException;
import p035rx.exceptions.Exceptions;

/* JADX INFO: loaded from: classes2.dex */
public class OperatorDoOnEach<T> implements Observable.Operator<T, T> {
    final Observer<? super T> doOnEachObserver;

    public OperatorDoOnEach(Observer<? super T> observer) {
        this.doOnEachObserver = observer;
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        return new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorDoOnEach.1
            private boolean done = false;

            @Override // p035rx.Observer
            public void onCompleted() {
                if (this.done) {
                    return;
                }
                try {
                    OperatorDoOnEach.this.doOnEachObserver.onCompleted();
                    this.done = true;
                    subscriber.onCompleted();
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this);
                }
            }

            @Override // p035rx.Observer
            public void onError(Throwable th) {
                Exceptions.throwIfFatal(th);
                if (this.done) {
                    return;
                }
                this.done = true;
                try {
                    OperatorDoOnEach.this.doOnEachObserver.onError(th);
                    subscriber.onError(th);
                } catch (Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    subscriber.onError(new CompositeException(Arrays.asList(th, th2)));
                }
            }

            @Override // p035rx.Observer
            public void onNext(T t) {
                if (this.done) {
                    return;
                }
                try {
                    OperatorDoOnEach.this.doOnEachObserver.onNext(t);
                    subscriber.onNext(t);
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this, t);
                }
            }
        };
    }
}
