package p035rx.internal.operators;

import java.util.concurrent.TimeUnit;
import p035rx.Observable;
import p035rx.Scheduler;
import p035rx.Subscriber;
import p035rx.exceptions.Exceptions;
import p035rx.functions.Action0;

/* JADX INFO: loaded from: classes2.dex */
public final class OnSubscribeTimerOnce implements Observable.OnSubscribe<Long> {
    final Scheduler scheduler;
    final long time;
    final TimeUnit unit;

    public OnSubscribeTimerOnce(long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.time = j;
        this.unit = timeUnit;
        this.scheduler = scheduler;
    }

    @Override // p035rx.functions.Action1
    public void call(final Subscriber<? super Long> subscriber) {
        Scheduler.Worker workerCreateWorker = this.scheduler.createWorker();
        subscriber.add(workerCreateWorker);
        workerCreateWorker.schedule(new Action0() { // from class: rx.internal.operators.OnSubscribeTimerOnce.1
            @Override // p035rx.functions.Action0
            public void call() {
                try {
                    subscriber.onNext(0L);
                    subscriber.onCompleted();
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, subscriber);
                }
            }
        }, this.time, this.unit);
    }
}
