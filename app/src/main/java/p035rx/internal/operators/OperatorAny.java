package p035rx.internal.operators;

import p035rx.Observable;
import p035rx.Subscriber;
import p035rx.exceptions.Exceptions;
import p035rx.functions.Func1;
import p035rx.internal.producers.SingleDelayedProducer;

/* JADX INFO: loaded from: classes2.dex */
public final class OperatorAny<T> implements Observable.Operator<Boolean, T> {
    final Func1<? super T, Boolean> predicate;
    final boolean returnOnEmpty;

    public OperatorAny(Func1<? super T, Boolean> func1, boolean z) {
        this.predicate = func1;
        this.returnOnEmpty = z;
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super T> call(final Subscriber<? super Boolean> subscriber) {
        final SingleDelayedProducer singleDelayedProducer = new SingleDelayedProducer(subscriber);
        Subscriber<T> subscriber2 = new Subscriber<T>() { // from class: rx.internal.operators.OperatorAny.1
            boolean done;
            boolean hasElements;

            @Override // p035rx.Observer
            public void onNext(T t) {
                this.hasElements = true;
                try {
                    if (!OperatorAny.this.predicate.call(t).booleanValue() || this.done) {
                        return;
                    }
                    this.done = true;
                    singleDelayedProducer.setValue(Boolean.valueOf(true ^ OperatorAny.this.returnOnEmpty));
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
                if (this.hasElements) {
                    singleDelayedProducer.setValue(false);
                } else {
                    singleDelayedProducer.setValue(Boolean.valueOf(OperatorAny.this.returnOnEmpty));
                }
            }
        };
        subscriber.add(subscriber2);
        subscriber.setProducer(singleDelayedProducer);
        return subscriber2;
    }
}
