package p035rx.internal.operators;

import p035rx.Observable;
import p035rx.Producer;
import p035rx.Subscriber;
import p035rx.functions.Action1;

/* JADX INFO: loaded from: classes2.dex */
public class OperatorDoOnRequest<T> implements Observable.Operator<T, T> {
    final Action1<Long> request;

    public OperatorDoOnRequest(Action1<Long> action1) {
        this.request = action1;
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        final ParentSubscriber parentSubscriber = new ParentSubscriber(subscriber);
        subscriber.setProducer(new Producer() { // from class: rx.internal.operators.OperatorDoOnRequest.1
            @Override // p035rx.Producer
            public void request(long j) {
                OperatorDoOnRequest.this.request.call(Long.valueOf(j));
                parentSubscriber.requestMore(j);
            }
        });
        subscriber.add(parentSubscriber);
        return parentSubscriber;
    }

    private static final class ParentSubscriber<T> extends Subscriber<T> {
        private final Subscriber<? super T> child;

        ParentSubscriber(Subscriber<? super T> subscriber) {
            this.child = subscriber;
            request(0L);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void requestMore(long j) {
            request(j);
        }

        @Override // p035rx.Observer
        public void onCompleted() {
            this.child.onCompleted();
        }

        @Override // p035rx.Observer
        public void onError(Throwable th) {
            this.child.onError(th);
        }

        @Override // p035rx.Observer
        public void onNext(T t) {
            this.child.onNext(t);
        }
    }
}
