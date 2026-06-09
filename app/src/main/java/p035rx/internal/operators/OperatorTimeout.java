package p035rx.internal.operators;

import java.util.concurrent.TimeUnit;
import p035rx.Observable;
import p035rx.Scheduler;
import p035rx.Subscriber;
import p035rx.Subscription;
import p035rx.functions.Action0;
import p035rx.internal.operators.OperatorTimeoutBase;

/* JADX INFO: loaded from: classes2.dex */
public final class OperatorTimeout<T> extends OperatorTimeoutBase<T> {
    @Override // p035rx.internal.operators.OperatorTimeoutBase
    public /* bridge */ /* synthetic */ Subscriber call(Subscriber subscriber) {
        return super.call(subscriber);
    }

    public OperatorTimeout(final long j, final TimeUnit timeUnit, Observable<? extends T> observable, Scheduler scheduler) {
        super(new OperatorTimeoutBase.FirstTimeoutStub<T>() { // from class: rx.internal.operators.OperatorTimeout.1
            @Override // p035rx.functions.Func3
            public Subscription call(final OperatorTimeoutBase.TimeoutSubscriber<T> timeoutSubscriber, final Long l, Scheduler.Worker worker) {
                return worker.schedule(new Action0() { // from class: rx.internal.operators.OperatorTimeout.1.1
                    @Override // p035rx.functions.Action0
                    public void call() {
                        timeoutSubscriber.onTimeout(l.longValue());
                    }
                }, j, timeUnit);
            }
        }, new OperatorTimeoutBase.TimeoutStub<T>() { // from class: rx.internal.operators.OperatorTimeout.2
            @Override // p035rx.functions.Func4
            public Subscription call(final OperatorTimeoutBase.TimeoutSubscriber<T> timeoutSubscriber, final Long l, T t, Scheduler.Worker worker) {
                return worker.schedule(new Action0() { // from class: rx.internal.operators.OperatorTimeout.2.1
                    @Override // p035rx.functions.Action0
                    public void call() {
                        timeoutSubscriber.onTimeout(l.longValue());
                    }
                }, j, timeUnit);
            }
        }, observable, scheduler);
    }
}
