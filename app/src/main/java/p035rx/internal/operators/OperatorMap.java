package p035rx.internal.operators;

import p035rx.Observable;
import p035rx.Producer;
import p035rx.Subscriber;
import p035rx.exceptions.Exceptions;
import p035rx.exceptions.OnErrorThrowable;
import p035rx.functions.Func1;
import p035rx.internal.util.RxJavaPluginUtils;

/* JADX INFO: loaded from: classes2.dex */
public final class OperatorMap<T, R> implements Observable.Operator<R, T> {
    final Func1<? super T, ? extends R> transformer;

    public OperatorMap(Func1<? super T, ? extends R> func1) {
        this.transformer = func1;
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super T> call(Subscriber<? super R> subscriber) {
        MapSubscriber mapSubscriber = new MapSubscriber(subscriber, this.transformer);
        subscriber.add(mapSubscriber);
        return mapSubscriber;
    }

    static final class MapSubscriber<T, R> extends Subscriber<T> {
        final Subscriber<? super R> actual;
        boolean done;
        final Func1<? super T, ? extends R> mapper;

        public MapSubscriber(Subscriber<? super R> subscriber, Func1<? super T, ? extends R> func1) {
            this.actual = subscriber;
            this.mapper = func1;
        }

        @Override // p035rx.Observer
        public void onNext(T t) {
            try {
                this.actual.onNext(this.mapper.call(t));
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
            this.actual.setProducer(producer);
        }
    }
}
