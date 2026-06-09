package p035rx.internal.operators;

import java.util.concurrent.atomic.AtomicInteger;
import p035rx.Observable;
import p035rx.Producer;
import p035rx.Scheduler;
import p035rx.Subscriber;
import p035rx.functions.Action0;
import p035rx.functions.Func2;
import p035rx.internal.producers.ProducerArbiter;
import p035rx.schedulers.Schedulers;
import p035rx.subscriptions.SerialSubscription;

/* JADX INFO: loaded from: classes2.dex */
public final class OperatorRetryWithPredicate<T> implements Observable.Operator<T, Observable<T>> {
    final Func2<Integer, Throwable, Boolean> predicate;

    public OperatorRetryWithPredicate(Func2<Integer, Throwable, Boolean> func2) {
        this.predicate = func2;
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super Observable<T>> call(Subscriber<? super T> subscriber) {
        Scheduler.Worker workerCreateWorker = Schedulers.trampoline().createWorker();
        subscriber.add(workerCreateWorker);
        SerialSubscription serialSubscription = new SerialSubscription();
        subscriber.add(serialSubscription);
        ProducerArbiter producerArbiter = new ProducerArbiter();
        subscriber.setProducer(producerArbiter);
        return new SourceSubscriber(subscriber, this.predicate, workerCreateWorker, serialSubscription, producerArbiter);
    }

    static final class SourceSubscriber<T> extends Subscriber<Observable<T>> {
        final AtomicInteger attempts = new AtomicInteger();
        final Subscriber<? super T> child;
        final Scheduler.Worker inner;

        /* JADX INFO: renamed from: pa */
        final ProducerArbiter f3503pa;
        final Func2<Integer, Throwable, Boolean> predicate;
        final SerialSubscription serialSubscription;

        @Override // p035rx.Observer
        public void onCompleted() {
        }

        public SourceSubscriber(Subscriber<? super T> subscriber, Func2<Integer, Throwable, Boolean> func2, Scheduler.Worker worker, SerialSubscription serialSubscription, ProducerArbiter producerArbiter) {
            this.child = subscriber;
            this.predicate = func2;
            this.inner = worker;
            this.serialSubscription = serialSubscription;
            this.f3503pa = producerArbiter;
        }

        @Override // p035rx.Observer
        public void onError(Throwable th) {
            this.child.onError(th);
        }

        @Override // p035rx.Observer
        public void onNext(final Observable<T> observable) {
            this.inner.schedule(new Action0() { // from class: rx.internal.operators.OperatorRetryWithPredicate.SourceSubscriber.1
                @Override // p035rx.functions.Action0
                public void call() {
                    SourceSubscriber.this.attempts.incrementAndGet();
                    Subscriber<T> subscriber = new Subscriber<T>() { // from class: rx.internal.operators.OperatorRetryWithPredicate.SourceSubscriber.1.1
                        boolean done;

                        @Override // p035rx.Observer
                        public void onCompleted() {
                            if (this.done) {
                                return;
                            }
                            this.done = true;
                            SourceSubscriber.this.child.onCompleted();
                        }

                        @Override // p035rx.Observer
                        public void onError(Throwable th) {
                            if (this.done) {
                                return;
                            }
                            this.done = true;
                            if (SourceSubscriber.this.predicate.call(Integer.valueOf(SourceSubscriber.this.attempts.get()), th).booleanValue() && !SourceSubscriber.this.inner.isUnsubscribed()) {
                                SourceSubscriber.this.inner.schedule(this);
                            } else {
                                SourceSubscriber.this.child.onError(th);
                            }
                        }

                        @Override // p035rx.Observer
                        public void onNext(T t) {
                            if (this.done) {
                                return;
                            }
                            SourceSubscriber.this.child.onNext(t);
                            SourceSubscriber.this.f3503pa.produced(1L);
                        }

                        @Override // p035rx.Subscriber
                        public void setProducer(Producer producer) {
                            SourceSubscriber.this.f3503pa.setProducer(producer);
                        }
                    };
                    SourceSubscriber.this.serialSubscription.set(subscriber);
                    observable.unsafeSubscribe(subscriber);
                }
            });
        }
    }
}
