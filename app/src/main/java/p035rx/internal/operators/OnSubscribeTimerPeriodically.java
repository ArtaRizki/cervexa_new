package p035rx.internal.operators;

import java.util.concurrent.TimeUnit;
import p035rx.Observable;
import p035rx.Scheduler;
import p035rx.Subscriber;
import p035rx.exceptions.Exceptions;
import p035rx.functions.Action0;

/* JADX INFO: loaded from: classes2.dex */
public final class OnSubscribeTimerPeriodically implements Observable.OnSubscribe<Long> {
    final long initialDelay;
    final long period;
    final Scheduler scheduler;
    final TimeUnit unit;

    public OnSubscribeTimerPeriodically(long j, long j2, TimeUnit timeUnit, Scheduler scheduler) {
        this.initialDelay = j;
        this.period = j2;
        this.unit = timeUnit;
        this.scheduler = scheduler;
    }

    @Override // p035rx.functions.Action1
    public void call(final Subscriber<? super Long> subscriber) {
        final Scheduler.Worker workerCreateWorker = this.scheduler.createWorker();
        subscriber.add(workerCreateWorker);
        workerCreateWorker.schedulePeriodically(new Action0() { // from class: rx.internal.operators.OnSubscribeTimerPeriodically.1
            long counter;

            @Override // p035rx.functions.Action0
            public void call() {
                try {
                    Subscriber subscriber2 = subscriber;
                    long j = this.counter;
                    this.counter = 1 + j;
                    subscriber2.onNext(Long.valueOf(j));
                } catch (Throwable th) {
                    try {
                        workerCreateWorker.unsubscribe();
                    } finally {
                        Exceptions.throwOrReport(th, subscriber);
                    }
                }
            }
        }, this.initialDelay, this.period, this.unit);
    }
}
