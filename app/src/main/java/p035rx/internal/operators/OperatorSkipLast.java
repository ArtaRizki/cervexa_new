package p035rx.internal.operators;

import java.util.ArrayDeque;
import java.util.Deque;
import p035rx.Observable;
import p035rx.Subscriber;

/* JADX INFO: loaded from: classes2.dex */
public class OperatorSkipLast<T> implements Observable.Operator<T, T> {
    final int count;

    public OperatorSkipLast(int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("count could not be negative");
        }
        this.count = i;
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        return new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorSkipLast.1

            /* JADX INFO: renamed from: on */
            private final NotificationLite<T> f3504on = NotificationLite.instance();
            private final Deque<Object> deque = new ArrayDeque();

            @Override // p035rx.Observer
            public void onCompleted() {
                subscriber.onCompleted();
            }

            @Override // p035rx.Observer
            public void onError(Throwable th) {
                subscriber.onError(th);
            }

            @Override // p035rx.Observer
            public void onNext(T t) {
                if (OperatorSkipLast.this.count == 0) {
                    subscriber.onNext(t);
                    return;
                }
                if (this.deque.size() == OperatorSkipLast.this.count) {
                    subscriber.onNext(this.f3504on.getValue(this.deque.removeFirst()));
                } else {
                    request(1L);
                }
                this.deque.offerLast(this.f3504on.next(t));
            }
        };
    }
}
