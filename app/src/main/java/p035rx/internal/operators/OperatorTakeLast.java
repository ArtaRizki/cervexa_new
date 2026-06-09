package p035rx.internal.operators;

import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicLong;
import p035rx.Observable;
import p035rx.Producer;
import p035rx.Subscriber;
import p035rx.functions.Func1;

/* JADX INFO: loaded from: classes2.dex */
public final class OperatorTakeLast<T> implements Observable.Operator<T, T> {
    final int count;

    public OperatorTakeLast(int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("count cannot be negative");
        }
        this.count = i;
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        final TakeLastSubscriber takeLastSubscriber = new TakeLastSubscriber(subscriber, this.count);
        subscriber.add(takeLastSubscriber);
        subscriber.setProducer(new Producer() { // from class: rx.internal.operators.OperatorTakeLast.1
            @Override // p035rx.Producer
            public void request(long j) {
                takeLastSubscriber.requestMore(j);
            }
        });
        return takeLastSubscriber;
    }

    static final class TakeLastSubscriber<T> extends Subscriber<T> implements Func1<Object, T> {
        final Subscriber<? super T> actual;
        final int count;
        final AtomicLong requested = new AtomicLong();
        final ArrayDeque<Object> queue = new ArrayDeque<>();

        /* JADX INFO: renamed from: nl */
        final NotificationLite<T> f3507nl = NotificationLite.instance();

        public TakeLastSubscriber(Subscriber<? super T> subscriber, int i) {
            this.actual = subscriber;
            this.count = i;
        }

        @Override // p035rx.Observer
        public void onNext(T t) {
            if (this.queue.size() == this.count) {
                this.queue.poll();
            }
            this.queue.offer(this.f3507nl.next(t));
        }

        @Override // p035rx.Observer
        public void onError(Throwable th) {
            this.queue.clear();
            this.actual.onError(th);
        }

        @Override // p035rx.Observer
        public void onCompleted() {
            BackpressureUtils.postCompleteDone(this.requested, this.queue, this.actual, this);
        }

        @Override // p035rx.functions.Func1
        public T call(Object obj) {
            return this.f3507nl.getValue(obj);
        }

        void requestMore(long j) {
            if (j > 0) {
                BackpressureUtils.postCompleteRequest(this.requested, j, this.queue, this.actual, this);
            }
        }
    }
}
