package p035rx.internal.operators;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.internal.LongCompanionObject;
import p035rx.Completable;
import p035rx.Observable;
import p035rx.Subscriber;
import p035rx.Subscription;
import p035rx.exceptions.CompositeException;
import p035rx.plugins.RxJavaPlugins;
import p035rx.subscriptions.CompositeSubscription;

/* JADX INFO: loaded from: classes2.dex */
public final class CompletableOnSubscribeMerge implements Completable.CompletableOnSubscribe {
    final boolean delayErrors;
    final int maxConcurrency;
    final Observable<Completable> source;

    /* JADX WARN: Multi-variable type inference failed */
    public CompletableOnSubscribeMerge(Observable<? extends Completable> observable, int i, boolean z) {
        this.source = observable;
        this.maxConcurrency = i;
        this.delayErrors = z;
    }

    @Override // p035rx.functions.Action1
    public void call(Completable.CompletableSubscriber completableSubscriber) {
        CompletableMergeSubscriber completableMergeSubscriber = new CompletableMergeSubscriber(completableSubscriber, this.maxConcurrency, this.delayErrors);
        completableSubscriber.onSubscribe(completableMergeSubscriber);
        this.source.subscribe((Subscriber<? super Completable>) completableMergeSubscriber);
    }

    static final class CompletableMergeSubscriber extends Subscriber<Completable> {
        final Completable.CompletableSubscriber actual;
        final boolean delayErrors;
        volatile boolean done;
        final int maxConcurrency;
        final CompositeSubscription set = new CompositeSubscription();
        final AtomicInteger wip = new AtomicInteger(1);
        final AtomicBoolean once = new AtomicBoolean();
        final AtomicReference<Queue<Throwable>> errors = new AtomicReference<>();

        public CompletableMergeSubscriber(Completable.CompletableSubscriber completableSubscriber, int i, boolean z) {
            this.actual = completableSubscriber;
            this.maxConcurrency = i;
            this.delayErrors = z;
            if (i == Integer.MAX_VALUE) {
                request(LongCompanionObject.MAX_VALUE);
            } else {
                request(i);
            }
        }

        Queue<Throwable> getOrCreateErrors() {
            Queue<Throwable> queue = this.errors.get();
            if (queue != null) {
                return queue;
            }
            ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
            return this.errors.compareAndSet(null, concurrentLinkedQueue) ? concurrentLinkedQueue : this.errors.get();
        }

        @Override // p035rx.Observer
        public void onNext(Completable completable) {
            if (this.done) {
                return;
            }
            this.wip.getAndIncrement();
            completable.unsafeSubscribe(new Completable.CompletableSubscriber() { // from class: rx.internal.operators.CompletableOnSubscribeMerge.CompletableMergeSubscriber.1

                /* JADX INFO: renamed from: d */
                Subscription f3482d;
                boolean innerDone;

                @Override // rx.Completable.CompletableSubscriber
                public void onSubscribe(Subscription subscription) {
                    this.f3482d = subscription;
                    CompletableMergeSubscriber.this.set.add(subscription);
                }

                @Override // rx.Completable.CompletableSubscriber
                public void onError(Throwable th) {
                    if (this.innerDone) {
                        RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
                        return;
                    }
                    this.innerDone = true;
                    CompletableMergeSubscriber.this.set.remove(this.f3482d);
                    CompletableMergeSubscriber.this.getOrCreateErrors().offer(th);
                    CompletableMergeSubscriber.this.terminate();
                    if (!CompletableMergeSubscriber.this.delayErrors || CompletableMergeSubscriber.this.done) {
                        return;
                    }
                    CompletableMergeSubscriber.this.request(1L);
                }

                @Override // rx.Completable.CompletableSubscriber
                public void onCompleted() {
                    if (this.innerDone) {
                        return;
                    }
                    this.innerDone = true;
                    CompletableMergeSubscriber.this.set.remove(this.f3482d);
                    CompletableMergeSubscriber.this.terminate();
                    if (CompletableMergeSubscriber.this.done) {
                        return;
                    }
                    CompletableMergeSubscriber.this.request(1L);
                }
            });
        }

        @Override // p035rx.Observer
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
                return;
            }
            getOrCreateErrors().offer(th);
            this.done = true;
            terminate();
        }

        @Override // p035rx.Observer
        public void onCompleted() {
            if (this.done) {
                return;
            }
            this.done = true;
            terminate();
        }

        void terminate() {
            Queue<Throwable> queue;
            if (this.wip.decrementAndGet() == 0) {
                Queue<Throwable> queue2 = this.errors.get();
                if (queue2 == null || queue2.isEmpty()) {
                    this.actual.onCompleted();
                    return;
                }
                Throwable thCollectErrors = CompletableOnSubscribeMerge.collectErrors(queue2);
                if (this.once.compareAndSet(false, true)) {
                    this.actual.onError(thCollectErrors);
                    return;
                } else {
                    RxJavaPlugins.getInstance().getErrorHandler().handleError(thCollectErrors);
                    return;
                }
            }
            if (this.delayErrors || (queue = this.errors.get()) == null || queue.isEmpty()) {
                return;
            }
            Throwable thCollectErrors2 = CompletableOnSubscribeMerge.collectErrors(queue);
            if (this.once.compareAndSet(false, true)) {
                this.actual.onError(thCollectErrors2);
            } else {
                RxJavaPlugins.getInstance().getErrorHandler().handleError(thCollectErrors2);
            }
        }
    }

    public static Throwable collectErrors(Queue<Throwable> queue) {
        ArrayList arrayList = new ArrayList();
        while (true) {
            Throwable thPoll = queue.poll();
            if (thPoll == null) {
                break;
            }
            arrayList.add(thPoll);
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        if (arrayList.size() == 1) {
            return (Throwable) arrayList.get(0);
        }
        return new CompositeException(arrayList);
    }
}
