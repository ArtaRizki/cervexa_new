package p035rx.internal.operators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import p035rx.Notification;
import p035rx.Observable;
import p035rx.Subscriber;
import p035rx.exceptions.Exceptions;

/* JADX INFO: loaded from: classes2.dex */
public final class BlockingOperatorNext {
    private BlockingOperatorNext() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Iterable<T> next(final Observable<? extends T> observable) {
        return new Iterable<T>() { // from class: rx.internal.operators.BlockingOperatorNext.1
            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                return new NextIterator(observable, new NextObserver());
            }
        };
    }

    static final class NextIterator<T> implements Iterator<T> {
        private final Observable<? extends T> items;
        private T next;
        private final NextObserver<T> observer;
        private boolean hasNext = true;
        private boolean isNextConsumed = true;
        private Throwable error = null;
        private boolean started = false;

        NextIterator(Observable<? extends T> observable, NextObserver<T> nextObserver) {
            this.items = observable;
            this.observer = nextObserver;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            Throwable th = this.error;
            if (th != null) {
                throw Exceptions.propagate(th);
            }
            if (!this.hasNext) {
                return false;
            }
            if (this.isNextConsumed) {
                return moveToNext();
            }
            return true;
        }

        private boolean moveToNext() {
            try {
                if (!this.started) {
                    this.started = true;
                    this.observer.setWaiting(1);
                    this.items.materialize().subscribe((Subscriber<? super Notification<? extends T>>) this.observer);
                }
                Notification<? extends T> notificationTakeNext = this.observer.takeNext();
                if (notificationTakeNext.isOnNext()) {
                    this.isNextConsumed = false;
                    this.next = notificationTakeNext.getValue();
                    return true;
                }
                this.hasNext = false;
                if (notificationTakeNext.isOnCompleted()) {
                    return false;
                }
                if (notificationTakeNext.isOnError()) {
                    Throwable throwable = notificationTakeNext.getThrowable();
                    this.error = throwable;
                    throw Exceptions.propagate(throwable);
                }
                throw new IllegalStateException("Should not reach here");
            } catch (InterruptedException e) {
                this.observer.unsubscribe();
                Thread.currentThread().interrupt();
                this.error = e;
                throw Exceptions.propagate(e);
            }
        }

        @Override // java.util.Iterator
        public T next() {
            Throwable th = this.error;
            if (th != null) {
                throw Exceptions.propagate(th);
            }
            if (hasNext()) {
                this.isNextConsumed = true;
                return this.next;
            }
            throw new NoSuchElementException("No more elements");
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Read only iterator");
        }
    }

    private static class NextObserver<T> extends Subscriber<Notification<? extends T>> {
        private final BlockingQueue<Notification<? extends T>> buf = new ArrayBlockingQueue(1);
        final AtomicInteger waiting = new AtomicInteger();

        @Override // p035rx.Observer
        public void onCompleted() {
        }

        @Override // p035rx.Observer
        public void onError(Throwable th) {
        }

        NextObserver() {
        }

        @Override // p035rx.Observer
        public void onNext(Notification<? extends T> notification) {
            if (this.waiting.getAndSet(0) == 1 || !notification.isOnNext()) {
                while (!this.buf.offer(notification)) {
                    Notification<? extends T> notificationPoll = this.buf.poll();
                    if (notificationPoll != null && !notificationPoll.isOnNext()) {
                        notification = notificationPoll;
                    }
                }
            }
        }

        public Notification<? extends T> takeNext() throws InterruptedException {
            setWaiting(1);
            return this.buf.take();
        }

        void setWaiting(int i) {
            this.waiting.set(i);
        }
    }
}
