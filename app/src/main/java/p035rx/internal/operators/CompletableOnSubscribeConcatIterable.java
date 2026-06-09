package p035rx.internal.operators;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import p035rx.Completable;
import p035rx.Subscription;
import p035rx.subscriptions.SerialSubscription;
import p035rx.subscriptions.Subscriptions;

/* JADX INFO: loaded from: classes2.dex */
public final class CompletableOnSubscribeConcatIterable implements Completable.CompletableOnSubscribe {
    final Iterable<? extends Completable> sources;

    public CompletableOnSubscribeConcatIterable(Iterable<? extends Completable> iterable) {
        this.sources = iterable;
    }

    @Override // p035rx.functions.Action1
    public void call(Completable.CompletableSubscriber completableSubscriber) {
        try {
            Iterator<? extends Completable> it = this.sources.iterator();
            if (it == null) {
                completableSubscriber.onSubscribe(Subscriptions.unsubscribed());
                completableSubscriber.onError(new NullPointerException("The iterator returned is null"));
            } else {
                ConcatInnerSubscriber concatInnerSubscriber = new ConcatInnerSubscriber(completableSubscriber, it);
                completableSubscriber.onSubscribe(concatInnerSubscriber.f3481sd);
                concatInnerSubscriber.next();
            }
        } catch (Throwable th) {
            completableSubscriber.onSubscribe(Subscriptions.unsubscribed());
            completableSubscriber.onError(th);
        }
    }

    static final class ConcatInnerSubscriber extends AtomicInteger implements Completable.CompletableSubscriber {
        private static final long serialVersionUID = -7965400327305809232L;
        final Completable.CompletableSubscriber actual;
        int index;

        /* JADX INFO: renamed from: sd */
        final SerialSubscription f3481sd = new SerialSubscription();
        final Iterator<? extends Completable> sources;

        public ConcatInnerSubscriber(Completable.CompletableSubscriber completableSubscriber, Iterator<? extends Completable> it) {
            this.actual = completableSubscriber;
            this.sources = it;
        }

        @Override // rx.Completable.CompletableSubscriber
        public void onSubscribe(Subscription subscription) {
            this.f3481sd.set(subscription);
        }

        @Override // rx.Completable.CompletableSubscriber
        public void onError(Throwable th) {
            this.actual.onError(th);
        }

        @Override // rx.Completable.CompletableSubscriber
        public void onCompleted() {
            next();
        }

        void next() {
            if (!this.f3481sd.isUnsubscribed() && getAndIncrement() == 0) {
                Iterator<? extends Completable> it = this.sources;
                while (!this.f3481sd.isUnsubscribed()) {
                    try {
                        if (!it.hasNext()) {
                            this.actual.onCompleted();
                            return;
                        }
                        try {
                            Completable next = it.next();
                            if (next == null) {
                                this.actual.onError(new NullPointerException("The completable returned is null"));
                                return;
                            } else {
                                next.unsafeSubscribe(this);
                                if (decrementAndGet() == 0) {
                                    return;
                                }
                            }
                        } catch (Throwable th) {
                            this.actual.onError(th);
                            return;
                        }
                    } catch (Throwable th2) {
                        this.actual.onError(th2);
                        return;
                    }
                }
            }
        }
    }
}
