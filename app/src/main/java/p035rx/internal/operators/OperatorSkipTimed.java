package p035rx.internal.operators;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import p035rx.Observable;
import p035rx.Scheduler;
import p035rx.Subscriber;
import p035rx.functions.Action0;

/* JADX INFO: loaded from: classes2.dex */
public final class OperatorSkipTimed<T> implements Observable.Operator<T, T> {
    final Scheduler scheduler;
    final long time;
    final TimeUnit unit;

    public OperatorSkipTimed(long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.time = j;
        this.unit = timeUnit;
        this.scheduler = scheduler;
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        Scheduler.Worker workerCreateWorker = this.scheduler.createWorker();
        subscriber.add(workerCreateWorker);
        final AtomicBoolean atomicBoolean = new AtomicBoolean();
        workerCreateWorker.schedule(new Action0() { // from class: rx.internal.operators.OperatorSkipTimed.1
            @Override // p035rx.functions.Action0
            public void call() {
                atomicBoolean.set(true);
            }
        }, this.time, this.unit);
        return new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorSkipTimed.2
            @Override // p035rx.Observer
            public void onNext(T t) {
                if (atomicBoolean.get()) {
                    subscriber.onNext(t);
                }
            }

            @Override // p035rx.Observer
            public void onError(Throwable th) {
                try {
                    subscriber.onError(th);
                } finally {
                    unsubscribe();
                }
            }

            @Override // p035rx.Observer
            public void onCompleted() {
                try {
                    subscriber.onCompleted();
                } finally {
                    unsubscribe();
                }
            }
        };
    }
}
