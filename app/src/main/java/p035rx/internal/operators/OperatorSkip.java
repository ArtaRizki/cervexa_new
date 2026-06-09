package p035rx.internal.operators;

import p035rx.Observable;
import p035rx.Producer;
import p035rx.Subscriber;

/* JADX INFO: loaded from: classes2.dex */
public final class OperatorSkip<T> implements Observable.Operator<T, T> {
    final int toSkip;

    public OperatorSkip(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("n >= 0 required but it was " + i);
        }
        this.toSkip = i;
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        return new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorSkip.1
            int skipped = 0;

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
                if (this.skipped >= OperatorSkip.this.toSkip) {
                    subscriber.onNext(t);
                } else {
                    this.skipped++;
                }
            }

            @Override // p035rx.Subscriber
            public void setProducer(Producer producer) {
                subscriber.setProducer(producer);
                producer.request(OperatorSkip.this.toSkip);
            }
        };
    }
}
