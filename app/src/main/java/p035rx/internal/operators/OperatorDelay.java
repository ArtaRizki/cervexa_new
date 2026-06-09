package p035rx.internal.operators;

import java.util.concurrent.TimeUnit;
import p035rx.Observable;
import p035rx.Scheduler;
import p035rx.Subscriber;
import p035rx.functions.Action0;

/* JADX INFO: loaded from: classes2.dex */
public final class OperatorDelay<T> implements Observable.Operator<T, T> {
    final long delay;
    final Scheduler scheduler;
    final TimeUnit unit;

    public OperatorDelay(long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.delay = j;
        this.unit = timeUnit;
        this.scheduler = scheduler;
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        Scheduler.Worker workerCreateWorker = this.scheduler.createWorker();
        subscriber.add(workerCreateWorker);
        return new C27501(subscriber, workerCreateWorker, subscriber);
    }

    /* JADX INFO: renamed from: rx.internal.operators.OperatorDelay$1 */
    class C27501 extends Subscriber<T> {
        boolean done;
        final /* synthetic */ Subscriber val$child;
        final /* synthetic */ Scheduler.Worker val$worker;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C27501(Subscriber subscriber, Scheduler.Worker worker, Subscriber subscriber2) {
            super(subscriber);
            this.val$worker = worker;
            this.val$child = subscriber2;
        }

        @Override // p035rx.Observer
        public void onCompleted() {
            this.val$worker.schedule(new Action0() { // from class: rx.internal.operators.OperatorDelay.1.1
                @Override // p035rx.functions.Action0
                public void call() {
                    if (C27501.this.done) {
                        return;
                    }
                    C27501.this.done = true;
                    C27501.this.val$child.onCompleted();
                }
            }, OperatorDelay.this.delay, OperatorDelay.this.unit);
        }

        @Override // p035rx.Observer
        public void onError(final Throwable th) {
            this.val$worker.schedule(new Action0() { // from class: rx.internal.operators.OperatorDelay.1.2
                @Override // p035rx.functions.Action0
                public void call() {
                    if (C27501.this.done) {
                        return;
                    }
                    C27501.this.done = true;
                    C27501.this.val$child.onError(th);
                    C27501.this.val$worker.unsubscribe();
                }
            });
        }

        @Override // p035rx.Observer
        public void onNext(final T t) {
            this.val$worker.schedule(new Action0() { // from class: rx.internal.operators.OperatorDelay.1.3
                /* JADX WARN: Multi-variable type inference failed */
                @Override // p035rx.functions.Action0
                public void call() {
                    if (C27501.this.done) {
                        return;
                    }
                    C27501.this.val$child.onNext(t);
                }
            }, OperatorDelay.this.delay, OperatorDelay.this.unit);
        }
    }
}
