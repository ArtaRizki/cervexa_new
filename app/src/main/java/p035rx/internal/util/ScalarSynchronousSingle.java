package p035rx.internal.util;

import p035rx.Scheduler;
import p035rx.Single;
import p035rx.SingleSubscriber;
import p035rx.Subscriber;
import p035rx.functions.Action0;
import p035rx.functions.Func1;
import p035rx.internal.schedulers.EventLoopsScheduler;

/* JADX INFO: loaded from: classes2.dex */
public final class ScalarSynchronousSingle<T> extends Single<T> {
    final T value;

    public static final <T> ScalarSynchronousSingle<T> create(T t) {
        return new ScalarSynchronousSingle<>(t);
    }

    protected ScalarSynchronousSingle(final T t) {
        super(new Single.OnSubscribe<T>() { // from class: rx.internal.util.ScalarSynchronousSingle.1
            @Override // p035rx.functions.Action1
            public void call(SingleSubscriber<? super T> singleSubscriber) {
                singleSubscriber.onSuccess((Object) t);
            }
        });
        this.value = t;
    }

    public T get() {
        return this.value;
    }

    public Single<T> scalarScheduleOn(Scheduler scheduler) {
        if (scheduler instanceof EventLoopsScheduler) {
            return create((Single.OnSubscribe) new DirectScheduledEmission((EventLoopsScheduler) scheduler, this.value));
        }
        return create((Single.OnSubscribe) new NormalScheduledEmission(scheduler, this.value));
    }

    static final class DirectScheduledEmission<T> implements Single.OnSubscribe<T> {

        /* JADX INFO: renamed from: es */
        private final EventLoopsScheduler f3526es;
        private final T value;

        DirectScheduledEmission(EventLoopsScheduler eventLoopsScheduler, T t) {
            this.f3526es = eventLoopsScheduler;
            this.value = t;
        }

        @Override // p035rx.functions.Action1
        public void call(SingleSubscriber<? super T> singleSubscriber) {
            singleSubscriber.add(this.f3526es.scheduleDirect(new ScalarSynchronousSingleAction(singleSubscriber, this.value)));
        }
    }

    static final class NormalScheduledEmission<T> implements Single.OnSubscribe<T> {
        private final Scheduler scheduler;
        private final T value;

        NormalScheduledEmission(Scheduler scheduler, T t) {
            this.scheduler = scheduler;
            this.value = t;
        }

        @Override // p035rx.functions.Action1
        public void call(SingleSubscriber<? super T> singleSubscriber) {
            Scheduler.Worker workerCreateWorker = this.scheduler.createWorker();
            singleSubscriber.add(workerCreateWorker);
            workerCreateWorker.schedule(new ScalarSynchronousSingleAction(singleSubscriber, this.value));
        }
    }

    static final class ScalarSynchronousSingleAction<T> implements Action0 {
        private final SingleSubscriber<? super T> subscriber;
        private final T value;

        ScalarSynchronousSingleAction(SingleSubscriber<? super T> singleSubscriber, T t) {
            this.subscriber = singleSubscriber;
            this.value = t;
        }

        @Override // p035rx.functions.Action0
        public void call() {
            try {
                this.subscriber.onSuccess(this.value);
            } catch (Throwable th) {
                this.subscriber.onError(th);
            }
        }
    }

    public <R> Single<R> scalarFlatMap(final Func1<? super T, ? extends Single<? extends R>> func1) {
        return create((Single.OnSubscribe) new Single.OnSubscribe<R>() { // from class: rx.internal.util.ScalarSynchronousSingle.2
            @Override // p035rx.functions.Action1
            public void call(final SingleSubscriber<? super R> singleSubscriber) {
                Single single = (Single) func1.call(ScalarSynchronousSingle.this.value);
                if (single instanceof ScalarSynchronousSingle) {
                    singleSubscriber.onSuccess(((ScalarSynchronousSingle) single).value);
                    return;
                }
                Subscriber<R> subscriber = new Subscriber<R>() { // from class: rx.internal.util.ScalarSynchronousSingle.2.1
                    @Override // p035rx.Observer
                    public void onCompleted() {
                    }

                    @Override // p035rx.Observer
                    public void onError(Throwable th) {
                        singleSubscriber.onError(th);
                    }

                    @Override // p035rx.Observer
                    public void onNext(R r) {
                        singleSubscriber.onSuccess(r);
                    }
                };
                singleSubscriber.add(subscriber);
                single.unsafeSubscribe(subscriber);
            }
        });
    }
}
