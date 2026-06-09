package p035rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import p035rx.Completable;
import p035rx.Observable;
import p035rx.Subscriber;
import p035rx.Subscription;
import p035rx.exceptions.MissingBackpressureException;
import p035rx.internal.util.unsafe.SpscArrayQueue;
import p035rx.plugins.RxJavaPlugins;
import p035rx.subscriptions.SerialSubscription;

/* JADX INFO: loaded from: classes2.dex */
public final class CompletableOnSubscribeConcat implements Completable.CompletableOnSubscribe {
    final int prefetch;
    final Observable<Completable> sources;

    /* JADX WARN: Multi-variable type inference failed */
    public CompletableOnSubscribeConcat(Observable<? extends Completable> observable, int i) {
        this.sources = observable;
        this.prefetch = i;
    }

    @Override // p035rx.functions.Action1
    public void call(Completable.CompletableSubscriber completableSubscriber) {
        CompletableConcatSubscriber completableConcatSubscriber = new CompletableConcatSubscriber(completableSubscriber, this.prefetch);
        completableSubscriber.onSubscribe(completableConcatSubscriber);
        this.sources.subscribe((Subscriber<? super Completable>) completableConcatSubscriber);
    }

    static final class CompletableConcatSubscriber extends Subscriber<Completable> {
        final Completable.CompletableSubscriber actual;
        volatile boolean done;
        final int prefetch;
        final SpscArrayQueue<Completable> queue;

        /* JADX INFO: renamed from: sr */
        final SerialSubscription f3479sr = new SerialSubscription();
        final ConcatInnerSubscriber inner = new ConcatInnerSubscriber();
        final AtomicInteger wip = new AtomicInteger();
        final AtomicBoolean once = new AtomicBoolean();

        public CompletableConcatSubscriber(Completable.CompletableSubscriber completableSubscriber, int i) {
            this.actual = completableSubscriber;
            this.prefetch = i;
            this.queue = new SpscArrayQueue<>(i);
            add(this.f3479sr);
            request(i);
        }

        @Override // p035rx.Observer
        public void onNext(Completable completable) {
            if (!this.queue.offer(completable)) {
                onError(new MissingBackpressureException());
            } else if (this.wip.getAndIncrement() == 0) {
                next();
            }
        }

        @Override // p035rx.Observer
        public void onError(Throwable th) {
            if (this.once.compareAndSet(false, true)) {
                this.actual.onError(th);
            } else {
                RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
            }
        }

        @Override // p035rx.Observer
        public void onCompleted() {
            if (this.done) {
                return;
            }
            this.done = true;
            if (this.wip.getAndIncrement() == 0) {
                next();
            }
        }

        void innerError(Throwable th) {
            unsubscribe();
            onError(th);
        }

        void innerComplete() {
            if (this.wip.decrementAndGet() != 0) {
                next();
            }
            if (this.done) {
                return;
            }
            request(1L);
        }

        void next() {
            boolean z = this.done;
            Completable completablePoll = this.queue.poll();
            if (completablePoll != null) {
                completablePoll.unsafeSubscribe(this.inner);
            } else {
                if (z) {
                    if (this.once.compareAndSet(false, true)) {
                        this.actual.onCompleted();
                        return;
                    }
                    return;
                }
                RxJavaPlugins.getInstance().getErrorHandler().handleError(new IllegalStateException("Queue is empty?!"));
            }
        }

        final class ConcatInnerSubscriber implements Completable.CompletableSubscriber {
            ConcatInnerSubscriber() {
            }

            @Override // rx.Completable.CompletableSubscriber
            public void onSubscribe(Subscription subscription) {
                CompletableConcatSubscriber.this.f3479sr.set(subscription);
            }

            @Override // rx.Completable.CompletableSubscriber
            public void onError(Throwable th) {
                CompletableConcatSubscriber.this.innerError(th);
            }

            @Override // rx.Completable.CompletableSubscriber
            public void onCompleted() {
                CompletableConcatSubscriber.this.innerComplete();
            }
        }
    }
}
