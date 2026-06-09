package p035rx.internal.operators;

import p035rx.Observable;
import p035rx.Producer;
import p035rx.Subscriber;
import p035rx.exceptions.Exceptions;
import p035rx.exceptions.OnErrorThrowable;
import p035rx.functions.Func1;
import p035rx.internal.util.RxJavaPluginUtils;

/* JADX INFO: loaded from: classes2.dex */
public final class OperatorFilter<T> implements Observable.Operator<T, T> {
    final Func1<? super T, Boolean> predicate;

    public OperatorFilter(Func1<? super T, Boolean> func1) {
        this.predicate = func1;
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        FilterSubscriber filterSubscriber = new FilterSubscriber(subscriber, this.predicate);
        subscriber.add(filterSubscriber);
        return filterSubscriber;
    }

    static final class FilterSubscriber<T> extends Subscriber<T> {
        final Subscriber<? super T> actual;
        boolean done;
        final Func1<? super T, Boolean> predicate;

        public FilterSubscriber(Subscriber<? super T> subscriber, Func1<? super T, Boolean> func1) {
            this.actual = subscriber;
            this.predicate = func1;
            request(0L);
        }

        @Override // p035rx.Observer
        public void onNext(T t) {
            try {
                if (this.predicate.call(t).booleanValue()) {
                    this.actual.onNext(t);
                } else {
                    request(1L);
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                unsubscribe();
                onError(OnErrorThrowable.addValueAsLastCause(th, t));
            }
        }

        @Override // p035rx.Observer
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPluginUtils.handleException(th);
            } else {
                this.done = true;
                this.actual.onError(th);
            }
        }

        @Override // p035rx.Observer
        public void onCompleted() {
            if (this.done) {
                return;
            }
            this.actual.onCompleted();
        }

        @Override // p035rx.Subscriber
        public void setProducer(Producer producer) {
            super.setProducer(producer);
            this.actual.setProducer(producer);
        }
    }
}
