package p035rx.internal.operators;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import kotlin.jvm.internal.LongCompanionObject;
import p035rx.Observable;
import p035rx.Subscriber;
import p035rx.exceptions.Exceptions;
import p035rx.internal.producers.SingleDelayedProducer;

/* JADX INFO: loaded from: classes2.dex */
public final class OperatorToObservableList<T> implements Observable.Operator<List<T>, T> {

    private static final class Holder {
        static final OperatorToObservableList<Object> INSTANCE = new OperatorToObservableList<>();

        private Holder() {
        }
    }

    public static <T> OperatorToObservableList<T> instance() {
        return (OperatorToObservableList<T>) Holder.INSTANCE;
    }

    OperatorToObservableList() {
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super T> call(final Subscriber<? super List<T>> subscriber) {
        final SingleDelayedProducer singleDelayedProducer = new SingleDelayedProducer(subscriber);
        Subscriber<T> subscriber2 = new Subscriber<T>() { // from class: rx.internal.operators.OperatorToObservableList.1
            boolean completed = false;
            List<T> list = new LinkedList();

            @Override // p035rx.Subscriber
            public void onStart() {
                request(LongCompanionObject.MAX_VALUE);
            }

            @Override // p035rx.Observer
            public void onCompleted() {
                if (this.completed) {
                    return;
                }
                this.completed = true;
                try {
                    ArrayList arrayList = new ArrayList(this.list);
                    this.list = null;
                    singleDelayedProducer.setValue(arrayList);
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this);
                }
            }

            @Override // p035rx.Observer
            public void onError(Throwable th) {
                subscriber.onError(th);
            }

            @Override // p035rx.Observer
            public void onNext(T t) {
                if (this.completed) {
                    return;
                }
                this.list.add(t);
            }
        };
        subscriber.add(subscriber2);
        subscriber.setProducer(singleDelayedProducer);
        return subscriber2;
    }
}
