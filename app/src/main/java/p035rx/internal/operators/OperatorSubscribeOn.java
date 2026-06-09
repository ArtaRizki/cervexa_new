package p035rx.internal.operators;

import p035rx.Observable;
import p035rx.Producer;
import p035rx.Scheduler;
import p035rx.Subscriber;
import p035rx.functions.Action0;

/* JADX INFO: loaded from: classes2.dex */
public final class OperatorSubscribeOn<T> implements Observable.OnSubscribe<T> {
    final Scheduler scheduler;
    final Observable<T> source;

    public OperatorSubscribeOn(Observable<T> observable, Scheduler scheduler) {
        this.scheduler = scheduler;
        this.source = observable;
    }

    @Override // p035rx.functions.Action1
    public void call(Subscriber<? super T> subscriber) {
        Scheduler.Worker workerCreateWorker = this.scheduler.createWorker();
        subscriber.add(workerCreateWorker);
        workerCreateWorker.schedule(new C28061(subscriber, workerCreateWorker));
    }

    /* JADX INFO: renamed from: rx.internal.operators.OperatorSubscribeOn$1 */
    class C28061 implements Action0 {
        final /* synthetic */ Scheduler.Worker val$inner;
        final /* synthetic */ Subscriber val$subscriber;

        C28061(Subscriber subscriber, Scheduler.Worker worker) {
            this.val$subscriber = subscriber;
            this.val$inner = worker;
        }

        @Override // p035rx.functions.Action0
        public void call() {
            final Thread threadCurrentThread = Thread.currentThread();
            OperatorSubscribeOn.this.source.unsafeSubscribe(new Subscriber<T>(this.val$subscriber) { // from class: rx.internal.operators.OperatorSubscribeOn.1.1
                @Override // p035rx.Observer
                public void onNext(T t) {
                    C28061.this.val$subscriber.onNext(t);
                }

                @Override // p035rx.Observer
                public void onError(Throwable th) {
                    try {
                        C28061.this.val$subscriber.onError(th);
                    } finally {
                        C28061.this.val$inner.unsubscribe();
                    }
                }

                @Override // p035rx.Observer
                public void onCompleted() {
                    try {
                        C28061.this.val$subscriber.onCompleted();
                    } finally {
                        C28061.this.val$inner.unsubscribe();
                    }
                }

                @Override // p035rx.Subscriber
                public void setProducer(final Producer producer) {
                    C28061.this.val$subscriber.setProducer(new Producer() { // from class: rx.internal.operators.OperatorSubscribeOn.1.1.1
                        @Override // p035rx.Producer
                        public void request(final long j) {
                            if (threadCurrentThread == Thread.currentThread()) {
                                producer.request(j);
                            } else {
                                C28061.this.val$inner.schedule(new Action0() { // from class: rx.internal.operators.OperatorSubscribeOn.1.1.1.1
                                    @Override // p035rx.functions.Action0
                                    public void call() {
                                        producer.request(j);
                                    }
                                });
                            }
                        }
                    });
                }
            });
        }
    }
}
