package p035rx.internal.operators;

import p035rx.Observable;
import p035rx.Scheduler;
import p035rx.Subscriber;
import p035rx.Subscription;
import p035rx.exceptions.Exceptions;
import p035rx.functions.Func0;
import p035rx.functions.Func1;
import p035rx.internal.operators.OperatorTimeoutBase;
import p035rx.schedulers.Schedulers;
import p035rx.subscriptions.Subscriptions;

/* JADX INFO: loaded from: classes2.dex */
public class OperatorTimeoutWithSelector<T, U, V> extends OperatorTimeoutBase<T> {
    @Override // p035rx.internal.operators.OperatorTimeoutBase
    public /* bridge */ /* synthetic */ Subscriber call(Subscriber subscriber) {
        return super.call(subscriber);
    }

    public OperatorTimeoutWithSelector(final Func0<? extends Observable<U>> func0, final Func1<? super T, ? extends Observable<V>> func1, Observable<? extends T> observable) {
        super(new OperatorTimeoutBase.FirstTimeoutStub<T>() { // from class: rx.internal.operators.OperatorTimeoutWithSelector.1
            @Override // p035rx.functions.Func3
            public Subscription call(final OperatorTimeoutBase.TimeoutSubscriber<T> timeoutSubscriber, final Long l, Scheduler.Worker worker) {
                Func0 func02 = func0;
                if (func02 != null) {
                    try {
                        return ((Observable) func02.call()).unsafeSubscribe(new Subscriber<U>() { // from class: rx.internal.operators.OperatorTimeoutWithSelector.1.1
                            @Override // p035rx.Observer
                            public void onCompleted() {
                                timeoutSubscriber.onTimeout(l.longValue());
                            }

                            @Override // p035rx.Observer
                            public void onError(Throwable th) {
                                timeoutSubscriber.onError(th);
                            }

                            @Override // p035rx.Observer
                            public void onNext(U u) {
                                timeoutSubscriber.onTimeout(l.longValue());
                            }
                        });
                    } catch (Throwable th) {
                        Exceptions.throwOrReport(th, timeoutSubscriber);
                        return Subscriptions.unsubscribed();
                    }
                }
                return Subscriptions.unsubscribed();
            }
        }, new OperatorTimeoutBase.TimeoutStub<T>() { // from class: rx.internal.operators.OperatorTimeoutWithSelector.2
            @Override // p035rx.functions.Func4
            public Subscription call(final OperatorTimeoutBase.TimeoutSubscriber<T> timeoutSubscriber, final Long l, T t, Scheduler.Worker worker) {
                try {
                    return ((Observable) func1.call(t)).unsafeSubscribe(new Subscriber<V>() { // from class: rx.internal.operators.OperatorTimeoutWithSelector.2.1
                        @Override // p035rx.Observer
                        public void onCompleted() {
                            timeoutSubscriber.onTimeout(l.longValue());
                        }

                        @Override // p035rx.Observer
                        public void onError(Throwable th) {
                            timeoutSubscriber.onError(th);
                        }

                        @Override // p035rx.Observer
                        public void onNext(V v) {
                            timeoutSubscriber.onTimeout(l.longValue());
                        }
                    });
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, timeoutSubscriber);
                    return Subscriptions.unsubscribed();
                }
            }
        }, observable, Schedulers.immediate());
    }
}
