package p035rx.internal.operators;

import java.util.concurrent.Callable;
import p035rx.Observable;
import p035rx.Subscriber;
import p035rx.exceptions.Exceptions;
import p035rx.internal.producers.SingleDelayedProducer;

/* JADX INFO: loaded from: classes2.dex */
public final class OnSubscribeFromCallable<T> implements Observable.OnSubscribe<T> {
    private final Callable<? extends T> resultFactory;

    public OnSubscribeFromCallable(Callable<? extends T> callable) {
        this.resultFactory = callable;
    }

    @Override // p035rx.functions.Action1
    public void call(Subscriber<? super T> subscriber) {
        SingleDelayedProducer singleDelayedProducer = new SingleDelayedProducer(subscriber);
        subscriber.setProducer(singleDelayedProducer);
        try {
            singleDelayedProducer.setValue(this.resultFactory.call());
        } catch (Throwable th) {
            Exceptions.throwOrReport(th, subscriber);
        }
    }
}
