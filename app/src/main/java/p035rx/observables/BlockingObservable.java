package p035rx.observables;

import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicReference;
import p035rx.Observable;
import p035rx.Observer;
import p035rx.Producer;
import p035rx.Subscriber;
import p035rx.Subscription;
import p035rx.exceptions.OnErrorNotImplementedException;
import p035rx.functions.Action0;
import p035rx.functions.Action1;
import p035rx.functions.Actions;
import p035rx.functions.Func1;
import p035rx.internal.operators.BlockingOperatorLatest;
import p035rx.internal.operators.BlockingOperatorMostRecent;
import p035rx.internal.operators.BlockingOperatorNext;
import p035rx.internal.operators.BlockingOperatorToFuture;
import p035rx.internal.operators.BlockingOperatorToIterator;
import p035rx.internal.operators.NotificationLite;
import p035rx.internal.util.BlockingUtils;
import p035rx.internal.util.UtilityFunctions;
import p035rx.subscriptions.Subscriptions;

/* JADX INFO: loaded from: classes2.dex */
public final class BlockingObservable<T> {
    static final Object ON_START = new Object();
    static final Object SET_PRODUCER = new Object();
    static final Object UNSUBSCRIBE = new Object();

    /* JADX INFO: renamed from: o */
    private final Observable<? extends T> f3538o;

    private BlockingObservable(Observable<? extends T> observable) {
        this.f3538o = observable;
    }

    public static <T> BlockingObservable<T> from(Observable<? extends T> observable) {
        return new BlockingObservable<>(observable);
    }

    public void forEach(final Action1<? super T> action1) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final AtomicReference atomicReference = new AtomicReference();
        BlockingUtils.awaitForComplete(countDownLatch, this.f3538o.subscribe((Subscriber<? super Object>) new Subscriber<T>() { // from class: rx.observables.BlockingObservable.1
            @Override // p035rx.Observer
            public void onCompleted() {
                countDownLatch.countDown();
            }

            @Override // p035rx.Observer
            public void onError(Throwable th) {
                atomicReference.set(th);
                countDownLatch.countDown();
            }

            @Override // p035rx.Observer
            public void onNext(T t) {
                action1.call(t);
            }
        }));
        if (atomicReference.get() != null) {
            if (atomicReference.get() instanceof RuntimeException) {
                throw ((RuntimeException) atomicReference.get());
            }
            throw new RuntimeException((Throwable) atomicReference.get());
        }
    }

    public Iterator<T> getIterator() {
        return BlockingOperatorToIterator.toIterator(this.f3538o);
    }

    public T first() {
        return blockForSingle(this.f3538o.first());
    }

    public T first(Func1<? super T, Boolean> func1) {
        return blockForSingle(this.f3538o.first(func1));
    }

    public T firstOrDefault(T t) {
        return blockForSingle(this.f3538o.map(UtilityFunctions.identity()).firstOrDefault(t));
    }

    public T firstOrDefault(T t, Func1<? super T, Boolean> func1) {
        return blockForSingle(this.f3538o.filter(func1).map(UtilityFunctions.identity()).firstOrDefault(t));
    }

    public T last() {
        return blockForSingle(this.f3538o.last());
    }

    public T last(Func1<? super T, Boolean> func1) {
        return blockForSingle(this.f3538o.last(func1));
    }

    public T lastOrDefault(T t) {
        return blockForSingle(this.f3538o.map(UtilityFunctions.identity()).lastOrDefault(t));
    }

    public T lastOrDefault(T t, Func1<? super T, Boolean> func1) {
        return blockForSingle(this.f3538o.filter(func1).map(UtilityFunctions.identity()).lastOrDefault(t));
    }

    public Iterable<T> mostRecent(T t) {
        return BlockingOperatorMostRecent.mostRecent(this.f3538o, t);
    }

    public Iterable<T> next() {
        return BlockingOperatorNext.next(this.f3538o);
    }

    public Iterable<T> latest() {
        return BlockingOperatorLatest.latest(this.f3538o);
    }

    public T single() {
        return blockForSingle(this.f3538o.single());
    }

    public T single(Func1<? super T, Boolean> func1) {
        return blockForSingle(this.f3538o.single(func1));
    }

    public T singleOrDefault(T t) {
        return blockForSingle(this.f3538o.map(UtilityFunctions.identity()).singleOrDefault(t));
    }

    public T singleOrDefault(T t, Func1<? super T, Boolean> func1) {
        return blockForSingle(this.f3538o.filter(func1).map(UtilityFunctions.identity()).singleOrDefault(t));
    }

    public Future<T> toFuture() {
        return BlockingOperatorToFuture.toFuture(this.f3538o);
    }

    public Iterable<T> toIterable() {
        return new Iterable<T>() { // from class: rx.observables.BlockingObservable.2
            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                return BlockingObservable.this.getIterator();
            }
        };
    }

    private T blockForSingle(Observable<? extends T> observable) {
        final AtomicReference atomicReference = new AtomicReference();
        final AtomicReference atomicReference2 = new AtomicReference();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        BlockingUtils.awaitForComplete(countDownLatch, observable.subscribe((Subscriber<? super Object>) new Subscriber<T>() { // from class: rx.observables.BlockingObservable.3
            @Override // p035rx.Observer
            public void onCompleted() {
                countDownLatch.countDown();
            }

            @Override // p035rx.Observer
            public void onError(Throwable th) {
                atomicReference2.set(th);
                countDownLatch.countDown();
            }

            @Override // p035rx.Observer
            public void onNext(T t) {
                atomicReference.set(t);
            }
        }));
        if (atomicReference2.get() != null) {
            if (atomicReference2.get() instanceof RuntimeException) {
                throw ((RuntimeException) atomicReference2.get());
            }
            throw new RuntimeException((Throwable) atomicReference2.get());
        }
        return (T) atomicReference.get();
    }

    public void subscribe() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final Throwable[] thArr = {null};
        BlockingUtils.awaitForComplete(countDownLatch, this.f3538o.subscribe((Subscriber<? super Object>) new Subscriber<T>() { // from class: rx.observables.BlockingObservable.4
            @Override // p035rx.Observer
            public void onNext(T t) {
            }

            @Override // p035rx.Observer
            public void onError(Throwable th) {
                thArr[0] = th;
                countDownLatch.countDown();
            }

            @Override // p035rx.Observer
            public void onCompleted() {
                countDownLatch.countDown();
            }
        }));
        Throwable th = thArr[0];
        if (th != null) {
            if (th instanceof RuntimeException) {
                throw ((RuntimeException) th);
            }
            throw new RuntimeException(th);
        }
    }

    public void subscribe(Observer<? super T> observer) {
        Object objPoll;
        final NotificationLite notificationLiteInstance = NotificationLite.instance();
        final LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        Subscription subscriptionSubscribe = this.f3538o.subscribe((Subscriber<? super Object>) new Subscriber<T>() { // from class: rx.observables.BlockingObservable.5
            @Override // p035rx.Observer
            public void onNext(T t) {
                linkedBlockingQueue.offer(notificationLiteInstance.next(t));
            }

            @Override // p035rx.Observer
            public void onError(Throwable th) {
                linkedBlockingQueue.offer(notificationLiteInstance.error(th));
            }

            @Override // p035rx.Observer
            public void onCompleted() {
                linkedBlockingQueue.offer(notificationLiteInstance.completed());
            }
        });
        do {
            try {
                objPoll = linkedBlockingQueue.poll();
                if (objPoll == null) {
                    objPoll = linkedBlockingQueue.take();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                observer.onError(e);
                return;
            } finally {
                subscriptionSubscribe.unsubscribe();
            }
        } while (!notificationLiteInstance.accept(observer, objPoll));
    }

    public void subscribe(Subscriber<? super T> subscriber) {
        final NotificationLite notificationLiteInstance = NotificationLite.instance();
        final LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        final Producer[] producerArr = {null};
        Subscriber<T> subscriber2 = new Subscriber<T>() { // from class: rx.observables.BlockingObservable.6
            @Override // p035rx.Observer
            public void onNext(T t) {
                linkedBlockingQueue.offer(notificationLiteInstance.next(t));
            }

            @Override // p035rx.Observer
            public void onError(Throwable th) {
                linkedBlockingQueue.offer(notificationLiteInstance.error(th));
            }

            @Override // p035rx.Observer
            public void onCompleted() {
                linkedBlockingQueue.offer(notificationLiteInstance.completed());
            }

            @Override // p035rx.Subscriber
            public void setProducer(Producer producer) {
                producerArr[0] = producer;
                linkedBlockingQueue.offer(BlockingObservable.SET_PRODUCER);
            }

            @Override // p035rx.Subscriber
            public void onStart() {
                linkedBlockingQueue.offer(BlockingObservable.ON_START);
            }
        };
        subscriber.add(subscriber2);
        subscriber.add(Subscriptions.create(new Action0() { // from class: rx.observables.BlockingObservable.7
            @Override // p035rx.functions.Action0
            public void call() {
                linkedBlockingQueue.offer(BlockingObservable.UNSUBSCRIBE);
            }
        }));
        this.f3538o.subscribe((Subscriber<? super Object>) subscriber2);
        while (!subscriber.isUnsubscribed()) {
            try {
                try {
                    Object objPoll = linkedBlockingQueue.poll();
                    if (objPoll == null) {
                        objPoll = linkedBlockingQueue.take();
                    }
                    if (subscriber.isUnsubscribed() || objPoll == UNSUBSCRIBE) {
                        break;
                    }
                    if (objPoll == ON_START) {
                        subscriber.onStart();
                    } else if (objPoll == SET_PRODUCER) {
                        subscriber.setProducer(producerArr[0]);
                    } else if (notificationLiteInstance.accept(subscriber, objPoll)) {
                        return;
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    subscriber.onError(e);
                }
            } finally {
                subscriber2.unsubscribe();
            }
        }
    }

    public void subscribe(Action1<? super T> action1) {
        subscribe(action1, new Action1<Throwable>() { // from class: rx.observables.BlockingObservable.8
            @Override // p035rx.functions.Action1
            public void call(Throwable th) {
                throw new OnErrorNotImplementedException(th);
            }
        }, Actions.empty());
    }

    public void subscribe(Action1<? super T> action1, Action1<? super Throwable> action12) {
        subscribe(action1, action12, Actions.empty());
    }

    public void subscribe(final Action1<? super T> action1, final Action1<? super Throwable> action12, final Action0 action0) {
        subscribe(new Observer<T>() { // from class: rx.observables.BlockingObservable.9
            @Override // p035rx.Observer
            public void onNext(T t) {
                action1.call(t);
            }

            @Override // p035rx.Observer
            public void onError(Throwable th) {
                action12.call(th);
            }

            @Override // p035rx.Observer
            public void onCompleted() {
                action0.call();
            }
        });
    }
}
