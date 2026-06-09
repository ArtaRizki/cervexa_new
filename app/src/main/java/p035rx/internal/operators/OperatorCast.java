package p035rx.internal.operators;

import p035rx.Observable;
import p035rx.Producer;
import p035rx.Subscriber;
import p035rx.exceptions.Exceptions;
import p035rx.exceptions.OnErrorThrowable;
import p035rx.internal.util.RxJavaPluginUtils;

/* JADX INFO: loaded from: classes2.dex */
public class OperatorCast<T, R> implements Observable.Operator<R, T> {
    final Class<R> castClass;

    public OperatorCast(Class<R> cls) {
        this.castClass = cls;
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super T> call(Subscriber<? super R> subscriber) {
        CastSubscriber castSubscriber = new CastSubscriber(subscriber, this.castClass);
        subscriber.add(castSubscriber);
        return castSubscriber;
    }

    static final class CastSubscriber<T, R> extends Subscriber<T> {
        final Subscriber<? super R> actual;
        final Class<R> castClass;
        boolean done;

        public CastSubscriber(Subscriber<? super R> subscriber, Class<R> cls) {
            this.actual = subscriber;
            this.castClass = cls;
        }

        @Override // p035rx.Observer
        public void onNext(T t) {
            try {
                this.actual.onNext(this.castClass.cast(t));
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
