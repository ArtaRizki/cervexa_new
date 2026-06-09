package p035rx.internal.operators;

import p035rx.Observable;
import p035rx.Producer;
import p035rx.Subscriber;
import p035rx.exceptions.Exceptions;
import p035rx.functions.Func1;
import p035rx.internal.producers.ProducerArbiter;
import p035rx.plugins.RxJavaPlugins;
import p035rx.subscriptions.SerialSubscription;

/* JADX INFO: loaded from: classes2.dex */
public final class OperatorOnErrorResumeNextViaFunction<T> implements Observable.Operator<T, T> {
    final Func1<Throwable, ? extends Observable<? extends T>> resumeFunction;

    public static <T> OperatorOnErrorResumeNextViaFunction<T> withSingle(final Func1<Throwable, ? extends T> func1) {
        return new OperatorOnErrorResumeNextViaFunction<>(new Func1<Throwable, Observable<? extends T>>() { // from class: rx.internal.operators.OperatorOnErrorResumeNextViaFunction.1
            @Override // p035rx.functions.Func1
            public Observable<? extends T> call(Throwable th) {
                return Observable.just(func1.call(th));
            }
        });
    }

    public static <T> OperatorOnErrorResumeNextViaFunction<T> withOther(final Observable<? extends T> observable) {
        return new OperatorOnErrorResumeNextViaFunction<>(new Func1<Throwable, Observable<? extends T>>() { // from class: rx.internal.operators.OperatorOnErrorResumeNextViaFunction.2
            @Override // p035rx.functions.Func1
            public Observable<? extends T> call(Throwable th) {
                return observable;
            }
        });
    }

    public static <T> OperatorOnErrorResumeNextViaFunction<T> withException(final Observable<? extends T> observable) {
        return new OperatorOnErrorResumeNextViaFunction<>(new Func1<Throwable, Observable<? extends T>>() { // from class: rx.internal.operators.OperatorOnErrorResumeNextViaFunction.3
            @Override // p035rx.functions.Func1
            public Observable<? extends T> call(Throwable th) {
                if (th instanceof Exception) {
                    return observable;
                }
                return Observable.error(th);
            }
        });
    }

    public OperatorOnErrorResumeNextViaFunction(Func1<Throwable, ? extends Observable<? extends T>> func1) {
        this.resumeFunction = func1;
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        final ProducerArbiter producerArbiter = new ProducerArbiter();
        final SerialSubscription serialSubscription = new SerialSubscription();
        Subscriber<T> subscriber2 = new Subscriber<T>() { // from class: rx.internal.operators.OperatorOnErrorResumeNextViaFunction.4
            private boolean done;
            long produced;

            @Override // p035rx.Observer
            public void onCompleted() {
                if (this.done) {
                    return;
                }
                this.done = true;
                subscriber.onCompleted();
            }

            @Override // p035rx.Observer
            public void onError(Throwable th) {
                if (this.done) {
                    Exceptions.throwIfFatal(th);
                    RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
                    return;
                }
                this.done = true;
                try {
                    unsubscribe();
                    Subscriber<T> subscriber3 = new Subscriber<T>() { // from class: rx.internal.operators.OperatorOnErrorResumeNextViaFunction.4.1
                        @Override // p035rx.Observer
                        public void onNext(T t) {
                            subscriber.onNext(t);
                        }

                        @Override // p035rx.Observer
                        public void onError(Throwable th2) {
                            subscriber.onError(th2);
                        }

                        @Override // p035rx.Observer
                        public void onCompleted() {
                            subscriber.onCompleted();
                        }

                        @Override // p035rx.Subscriber
                        public void setProducer(Producer producer) {
                            producerArbiter.setProducer(producer);
                        }
                    };
                    serialSubscription.set(subscriber3);
                    long j = this.produced;
                    if (j != 0) {
                        producerArbiter.produced(j);
                    }
                    OperatorOnErrorResumeNextViaFunction.this.resumeFunction.call(th).unsafeSubscribe(subscriber3);
                } catch (Throwable th2) {
                    Exceptions.throwOrReport(th2, subscriber);
                }
            }

            @Override // p035rx.Observer
            public void onNext(T t) {
                if (this.done) {
                    return;
                }
                this.produced++;
                subscriber.onNext(t);
            }

            @Override // p035rx.Subscriber
            public void setProducer(Producer producer) {
                producerArbiter.setProducer(producer);
            }
        };
        serialSubscription.set(subscriber2);
        subscriber.add(serialSubscription);
        subscriber.setProducer(producerArbiter);
        return subscriber2;
    }
}
