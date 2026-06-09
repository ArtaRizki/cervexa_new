package p035rx.internal.operators;

import p035rx.Observable;
import p035rx.Subscriber;
import p035rx.exceptions.Exceptions;
import p035rx.functions.Func1;
import p035rx.internal.producers.SingleDelayedProducer;

/* JADX INFO: loaded from: classes2.dex */
public final class OperatorAll<T> implements Observable.Operator<Boolean, T> {
    final Func1<? super T, Boolean> predicate;

    public OperatorAll(Func1<? super T, Boolean> func1) {
        this.predicate = func1;
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super T> call(final Subscriber<? super Boolean> subscriber) {
        final SingleDelayedProducer singleDelayedProducer = new SingleDelayedProducer(subscriber);
        Subscriber<T> subscriber2 = new Subscriber<T>() { // from class: rx.internal.operators.OperatorAll.1
            boolean done;

            @Override // p035rx.Observer
            public void onNext(T t) {
                try {
                    if (OperatorAll.this.predicate.call(t).booleanValue() || this.done) {
                        return;
                    }
                    this.done = true;
                    singleDelayedProducer.setValue(false);
                    unsubscribe();
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this, t);
                }
            }

            @Override // p035rx.Observer
            public void onError(Throwable th) {
                subscriber.onError(th);
            }

            @Override // p035rx.Observer
            public void onCompleted() {
                if (this.done) {
                    return;
                }
                this.done = true;
                singleDelayedProducer.setValue(true);
            }
        };
        subscriber.add(subscriber2);
        subscriber.setProducer(singleDelayedProducer);
        return subscriber2;
    }
}
