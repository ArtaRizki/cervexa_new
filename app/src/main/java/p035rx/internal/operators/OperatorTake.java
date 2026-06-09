package p035rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import p035rx.Observable;
import p035rx.Producer;
import p035rx.Subscriber;

/* JADX INFO: loaded from: classes2.dex */
public final class OperatorTake<T> implements Observable.Operator<T, T> {
    final int limit;

    public OperatorTake(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("limit >= 0 required but it was " + i);
        }
        this.limit = i;
    }

    /* JADX INFO: renamed from: rx.internal.operators.OperatorTake$1 */
    class C28091 extends Subscriber<T> {
        boolean completed;
        int count;
        final /* synthetic */ Subscriber val$child;

        C28091(Subscriber subscriber) {
            this.val$child = subscriber;
        }

        @Override // p035rx.Observer
        public void onCompleted() {
            if (this.completed) {
                return;
            }
            this.completed = true;
            this.val$child.onCompleted();
        }

        @Override // p035rx.Observer
        public void onError(Throwable th) {
            if (this.completed) {
                return;
            }
            this.completed = true;
            try {
                this.val$child.onError(th);
            } finally {
                unsubscribe();
            }
        }

        @Override // p035rx.Observer
        public void onNext(T t) {
            if (isUnsubscribed()) {
                return;
            }
            int i = this.count;
            this.count = i + 1;
            if (i < OperatorTake.this.limit) {
                boolean z = this.count == OperatorTake.this.limit;
                this.val$child.onNext(t);
                if (!z || this.completed) {
                    return;
                }
                this.completed = true;
                try {
                    this.val$child.onCompleted();
                } finally {
                    unsubscribe();
                }
            }
        }

        @Override // p035rx.Subscriber
        public void setProducer(final Producer producer) {
            this.val$child.setProducer(new Producer() { // from class: rx.internal.operators.OperatorTake.1.1
                final AtomicLong requested = new AtomicLong(0);

                @Override // p035rx.Producer
                public void request(long j) {
                    long j2;
                    long jMin;
                    if (j <= 0 || C28091.this.completed) {
                        return;
                    }
                    do {
                        j2 = this.requested.get();
                        jMin = Math.min(j, ((long) OperatorTake.this.limit) - j2);
                        if (jMin == 0) {
                            return;
                        }
                    } while (!this.requested.compareAndSet(j2, j2 + jMin));
                    producer.request(jMin);
                }
            });
        }
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        C28091 c28091 = new C28091(subscriber);
        if (this.limit == 0) {
            subscriber.onCompleted();
            c28091.unsubscribe();
        }
        subscriber.add(c28091);
        return c28091;
    }
}
