package p035rx.internal.operators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import p035rx.Observable;
import p035rx.Subscriber;
import p035rx.exceptions.Exceptions;

/* JADX INFO: loaded from: classes2.dex */
public final class BlockingOperatorMostRecent {
    private BlockingOperatorMostRecent() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Iterable<T> mostRecent(final Observable<? extends T> observable, final T t) {
        return new Iterable<T>() { // from class: rx.internal.operators.BlockingOperatorMostRecent.1
            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                MostRecentObserver mostRecentObserver = new MostRecentObserver(t);
                observable.subscribe((Subscriber) mostRecentObserver);
                return mostRecentObserver.getIterable();
            }
        };
    }

    private static final class MostRecentObserver<T> extends Subscriber<T> {

        /* JADX INFO: renamed from: nl */
        final NotificationLite<T> f3476nl;
        volatile Object value;

        MostRecentObserver(T t) {
            NotificationLite<T> notificationLiteInstance = NotificationLite.instance();
            this.f3476nl = notificationLiteInstance;
            this.value = notificationLiteInstance.next(t);
        }

        @Override // p035rx.Observer
        public void onCompleted() {
            this.value = this.f3476nl.completed();
        }

        @Override // p035rx.Observer
        public void onError(Throwable th) {
            this.value = this.f3476nl.error(th);
        }

        @Override // p035rx.Observer
        public void onNext(T t) {
            this.value = this.f3476nl.next(t);
        }

        public Iterator<T> getIterable() {
            return new Iterator<T>() { // from class: rx.internal.operators.BlockingOperatorMostRecent.MostRecentObserver.1
                private Object buf = null;

                @Override // java.util.Iterator
                public boolean hasNext() {
                    this.buf = MostRecentObserver.this.value;
                    return !MostRecentObserver.this.f3476nl.isCompleted(this.buf);
                }

                @Override // java.util.Iterator
                public T next() {
                    try {
                        if (this.buf == null) {
                            this.buf = MostRecentObserver.this.value;
                        }
                        if (MostRecentObserver.this.f3476nl.isCompleted(this.buf)) {
                            throw new NoSuchElementException();
                        }
                        if (MostRecentObserver.this.f3476nl.isError(this.buf)) {
                            throw Exceptions.propagate(MostRecentObserver.this.f3476nl.getError(this.buf));
                        }
                        return MostRecentObserver.this.f3476nl.getValue(this.buf);
                    } finally {
                        this.buf = null;
                    }
                }

                @Override // java.util.Iterator
                public void remove() {
                    throw new UnsupportedOperationException("Read only iterator");
                }
            };
        }
    }
}
