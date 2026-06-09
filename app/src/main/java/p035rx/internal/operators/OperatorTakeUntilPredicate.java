package p035rx.internal.operators;

import p035rx.Observable;
import p035rx.Producer;
import p035rx.Subscriber;
import p035rx.exceptions.Exceptions;
import p035rx.functions.Func1;

/* JADX INFO: loaded from: classes2.dex */
public final class OperatorTakeUntilPredicate<T> implements Observable.Operator<T, T> {
    final Func1<? super T, Boolean> stopPredicate;

    private final class ParentSubscriber extends Subscriber<T> {
        private final Subscriber<? super T> child;
        private boolean done = false;

        ParentSubscriber(Subscriber<? super T> subscriber) {
            this.child = subscriber;
        }

        @Override // p035rx.Observer
        public void onNext(T t) {
            this.child.onNext(t);
            try {
                if (OperatorTakeUntilPredicate.this.stopPredicate.call(t).booleanValue()) {
                    this.done = true;
                    this.child.onCompleted();
                    unsubscribe();
                }
            } catch (Throwable th) {
                this.done = true;
                Exceptions.throwOrReport(th, this.child, t);
                unsubscribe();
            }
        }

        @Override // p035rx.Observer
        public void onCompleted() {
            if (this.done) {
                return;
            }
            this.child.onCompleted();
        }

        @Override // p035rx.Observer
        public void onError(Throwable th) {
            if (this.done) {
                return;
            }
            this.child.onError(th);
        }

        void downstreamRequest(long j) {
            request(j);
        }
    }

    public OperatorTakeUntilPredicate(Func1<? super T, Boolean> func1) {
        this.stopPredicate = func1;
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        final ParentSubscriber parentSubscriber = new ParentSubscriber(subscriber);
        subscriber.add(parentSubscriber);
        subscriber.setProducer(new Producer() { // from class: rx.internal.operators.OperatorTakeUntilPredicate.1
            @Override // p035rx.Producer
            public void request(long j) {
                parentSubscriber.downstreamRequest(j);
            }
        });
        return parentSubscriber;
    }
}
