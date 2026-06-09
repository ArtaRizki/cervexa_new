package p035rx.internal.operators;

import java.util.concurrent.TimeUnit;
import p035rx.Observable;
import p035rx.Scheduler;
import p035rx.Subscriber;
import p035rx.functions.Action0;
import p035rx.observers.SerializedSubscriber;

/* JADX INFO: loaded from: classes2.dex */
public final class OperatorTakeTimed<T> implements Observable.Operator<T, T> {
    final Scheduler scheduler;
    final long time;
    final TimeUnit unit;

    public OperatorTakeTimed(long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.time = j;
        this.unit = timeUnit;
        this.scheduler = scheduler;
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        Scheduler.Worker workerCreateWorker = this.scheduler.createWorker();
        subscriber.add(workerCreateWorker);
        TakeSubscriber takeSubscriber = new TakeSubscriber(new SerializedSubscriber(subscriber));
        workerCreateWorker.schedule(takeSubscriber, this.time, this.unit);
        return takeSubscriber;
    }

    static final class TakeSubscriber<T> extends Subscriber<T> implements Action0 {
        final Subscriber<? super T> child;

        public TakeSubscriber(Subscriber<? super T> subscriber) {
            super(subscriber);
            this.child = subscriber;
        }

        @Override // p035rx.Observer
        public void onNext(T t) {
            this.child.onNext(t);
        }

        @Override // p035rx.Observer
        public void onError(Throwable th) {
            this.child.onError(th);
            unsubscribe();
        }

        @Override // p035rx.Observer
        public void onCompleted() {
            this.child.onCompleted();
            unsubscribe();
        }

        @Override // p035rx.functions.Action0
        public void call() {
            onCompleted();
        }
    }
}
