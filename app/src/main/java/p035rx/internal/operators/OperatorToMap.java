package p035rx.internal.operators;

import java.util.HashMap;
import java.util.Map;
import kotlin.jvm.internal.LongCompanionObject;
import p035rx.Observable;
import p035rx.Subscriber;
import p035rx.exceptions.Exceptions;
import p035rx.functions.Func0;
import p035rx.functions.Func1;
import p035rx.observers.Subscribers;

/* JADX INFO: loaded from: classes2.dex */
public final class OperatorToMap<T, K, V> implements Observable.Operator<Map<K, V>, T> {
    final Func1<? super T, ? extends K> keySelector;
    private final Func0<? extends Map<K, V>> mapFactory;
    final Func1<? super T, ? extends V> valueSelector;

    public static final class DefaultToMapFactory<K, V> implements Func0<Map<K, V>> {
        @Override // p035rx.functions.Func0, java.util.concurrent.Callable
        public Map<K, V> call() {
            return new HashMap();
        }
    }

    public OperatorToMap(Func1<? super T, ? extends K> func1, Func1<? super T, ? extends V> func12) {
        this(func1, func12, new DefaultToMapFactory());
    }

    public OperatorToMap(Func1<? super T, ? extends K> func1, Func1<? super T, ? extends V> func12, Func0<? extends Map<K, V>> func0) {
        this.keySelector = func1;
        this.valueSelector = func12;
        this.mapFactory = func0;
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super T> call(final Subscriber<? super Map<K, V>> subscriber) {
        try {
            final Map<K, V> mapCall = this.mapFactory.call();
            return new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorToMap.1
                private Map<K, V> map;

                {
                    this.map = mapCall;
                }

                @Override // p035rx.Subscriber
                public void onStart() {
                    request(LongCompanionObject.MAX_VALUE);
                }

                @Override // p035rx.Observer
                public void onNext(T t) {
                    try {
                        this.map.put(OperatorToMap.this.keySelector.call(t), OperatorToMap.this.valueSelector.call(t));
                    } catch (Throwable th) {
                        Exceptions.throwOrReport(th, subscriber);
                    }
                }

                @Override // p035rx.Observer
                public void onError(Throwable th) {
                    this.map = null;
                    subscriber.onError(th);
                }

                @Override // p035rx.Observer
                public void onCompleted() {
                    Map<K, V> map = this.map;
                    this.map = null;
                    subscriber.onNext(map);
                    subscriber.onCompleted();
                }
            };
        } catch (Throwable th) {
            Exceptions.throwOrReport(th, subscriber);
            Subscriber<? super T> subscriberEmpty = Subscribers.empty();
            subscriberEmpty.unsubscribe();
            return subscriberEmpty;
        }
    }
}
