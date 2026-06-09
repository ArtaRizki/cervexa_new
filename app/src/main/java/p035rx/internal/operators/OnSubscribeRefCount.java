package p035rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import p035rx.Observable;
import p035rx.Subscriber;
import p035rx.Subscription;
import p035rx.functions.Action0;
import p035rx.functions.Action1;
import p035rx.observables.ConnectableObservable;
import p035rx.subscriptions.CompositeSubscription;
import p035rx.subscriptions.Subscriptions;

/* JADX INFO: loaded from: classes2.dex */
public final class OnSubscribeRefCount<T> implements Observable.OnSubscribe<T> {
    private final ConnectableObservable<? extends T> source;
    volatile CompositeSubscription baseSubscription = new CompositeSubscription();
    final AtomicInteger subscriptionCount = new AtomicInteger(0);
    final ReentrantLock lock = new ReentrantLock();

    public OnSubscribeRefCount(ConnectableObservable<? extends T> connectableObservable) {
        this.source = connectableObservable;
    }

    @Override // p035rx.functions.Action1
    public void call(Subscriber<? super T> subscriber) {
        boolean z;
        this.lock.lock();
        if (this.subscriptionCount.incrementAndGet() == 1) {
            AtomicBoolean atomicBoolean = new AtomicBoolean(true);
            try {
                this.source.connect(onSubscribe(subscriber, atomicBoolean));
                if (z) {
                    return;
                } else {
                    return;
                }
            } finally {
                if (atomicBoolean.get()) {
                }
            }
        }
        try {
            doSubscribe(subscriber, this.baseSubscription);
        } finally {
            this.lock.unlock();
        }
    }

    private Action1<Subscription> onSubscribe(final Subscriber<? super T> subscriber, final AtomicBoolean atomicBoolean) {
        return new Action1<Subscription>() { // from class: rx.internal.operators.OnSubscribeRefCount.1
            @Override // p035rx.functions.Action1
            public void call(Subscription subscription) {
                try {
                    OnSubscribeRefCount.this.baseSubscription.add(subscription);
                    OnSubscribeRefCount.this.doSubscribe(subscriber, OnSubscribeRefCount.this.baseSubscription);
                } finally {
                    OnSubscribeRefCount.this.lock.unlock();
                    atomicBoolean.set(false);
                }
            }
        };
    }

    void doSubscribe(final Subscriber<? super T> subscriber, final CompositeSubscription compositeSubscription) {
        subscriber.add(disconnect(compositeSubscription));
        this.source.unsafeSubscribe((Subscriber<? super Object>) new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OnSubscribeRefCount.2
            @Override // p035rx.Observer
            public void onError(Throwable th) {
                cleanup();
                subscriber.onError(th);
            }

            @Override // p035rx.Observer
            public void onNext(T t) {
                subscriber.onNext(t);
            }

            @Override // p035rx.Observer
            public void onCompleted() {
                cleanup();
                subscriber.onCompleted();
            }

            void cleanup() {
                OnSubscribeRefCount.this.lock.lock();
                try {
                    if (OnSubscribeRefCount.this.baseSubscription == compositeSubscription) {
                        OnSubscribeRefCount.this.baseSubscription.unsubscribe();
                        OnSubscribeRefCount.this.baseSubscription = new CompositeSubscription();
                        OnSubscribeRefCount.this.subscriptionCount.set(0);
                    }
                } finally {
                    OnSubscribeRefCount.this.lock.unlock();
                }
            }
        });
    }

    private Subscription disconnect(final CompositeSubscription compositeSubscription) {
        return Subscriptions.create(new Action0() { // from class: rx.internal.operators.OnSubscribeRefCount.3
            @Override // p035rx.functions.Action0
            public void call() {
                OnSubscribeRefCount.this.lock.lock();
                try {
                    if (OnSubscribeRefCount.this.baseSubscription == compositeSubscription && OnSubscribeRefCount.this.subscriptionCount.decrementAndGet() == 0) {
                        OnSubscribeRefCount.this.baseSubscription.unsubscribe();
                        OnSubscribeRefCount.this.baseSubscription = new CompositeSubscription();
                    }
                } finally {
                    OnSubscribeRefCount.this.lock.unlock();
                }
            }
        });
    }
}
