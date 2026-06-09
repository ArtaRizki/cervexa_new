package p035rx.internal.operators;

import p035rx.Observable;
import p035rx.Producer;
import p035rx.Subscriber;
import p035rx.internal.producers.ProducerArbiter;
import p035rx.subscriptions.SerialSubscription;

/* JADX INFO: loaded from: classes2.dex */
public final class OperatorSwitchIfEmpty<T> implements Observable.Operator<T, T> {
    private final Observable<? extends T> alternate;

    public OperatorSwitchIfEmpty(Observable<? extends T> observable) {
        this.alternate = observable;
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        SerialSubscription serialSubscription = new SerialSubscription();
        ProducerArbiter producerArbiter = new ProducerArbiter();
        ParentSubscriber parentSubscriber = new ParentSubscriber(subscriber, serialSubscription, producerArbiter, this.alternate);
        serialSubscription.set(parentSubscriber);
        subscriber.add(serialSubscription);
        subscriber.setProducer(producerArbiter);
        return parentSubscriber;
    }

    private static final class ParentSubscriber<T> extends Subscriber<T> {
        private final Observable<? extends T> alternate;
        private final ProducerArbiter arbiter;
        private final Subscriber<? super T> child;
        private boolean empty = true;
        private final SerialSubscription ssub;

        ParentSubscriber(Subscriber<? super T> subscriber, SerialSubscription serialSubscription, ProducerArbiter producerArbiter, Observable<? extends T> observable) {
            this.child = subscriber;
            this.ssub = serialSubscription;
            this.arbiter = producerArbiter;
            this.alternate = observable;
        }

        @Override // p035rx.Subscriber
        public void setProducer(Producer producer) {
            this.arbiter.setProducer(producer);
        }

        @Override // p035rx.Observer
        public void onCompleted() {
            if (!this.empty) {
                this.child.onCompleted();
            } else {
                if (this.child.isUnsubscribed()) {
                    return;
                }
                subscribeToAlternate();
            }
        }

        private void subscribeToAlternate() {
            AlternateSubscriber alternateSubscriber = new AlternateSubscriber(this.child, this.arbiter);
            this.ssub.set(alternateSubscriber);
            this.alternate.unsafeSubscribe(alternateSubscriber);
        }

        @Override // p035rx.Observer
        public void onError(Throwable th) {
            this.child.onError(th);
        }

        @Override // p035rx.Observer
        public void onNext(T t) {
            this.empty = false;
            this.child.onNext(t);
            this.arbiter.produced(1L);
        }
    }

    private static final class AlternateSubscriber<T> extends Subscriber<T> {
        private final ProducerArbiter arbiter;
        private final Subscriber<? super T> child;

        AlternateSubscriber(Subscriber<? super T> subscriber, ProducerArbiter producerArbiter) {
            this.child = subscriber;
            this.arbiter = producerArbiter;
        }

        @Override // p035rx.Subscriber
        public void setProducer(Producer producer) {
            this.arbiter.setProducer(producer);
        }

        @Override // p035rx.Observer
        public void onCompleted() {
            this.child.onCompleted();
        }

        @Override // p035rx.Observer
        public void onError(Throwable th) {
            this.child.onError(th);
        }

        @Override // p035rx.Observer
        public void onNext(T t) {
            this.child.onNext(t);
            this.arbiter.produced(1L);
        }
    }
}
