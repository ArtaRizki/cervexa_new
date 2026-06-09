package p035rx.internal.operators;

import java.util.ArrayList;
import java.util.Collection;
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
public final class OperatorToMultimap<T, K, V> implements Observable.Operator<Map<K, Collection<V>>, T> {
    final Func1<? super K, ? extends Collection<V>> collectionFactory;
    final Func1<? super T, ? extends K> keySelector;
    private final Func0<? extends Map<K, Collection<V>>> mapFactory;
    final Func1<? super T, ? extends V> valueSelector;

    public static final class DefaultToMultimapFactory<K, V> implements Func0<Map<K, Collection<V>>> {
        @Override // p035rx.functions.Func0, java.util.concurrent.Callable
        public Map<K, Collection<V>> call() {
            return new HashMap();
        }
    }

    public static final class DefaultMultimapCollectionFactory<K, V> implements Func1<K, Collection<V>> {
        @Override // p035rx.functions.Func1
        public Collection<V> call(K k) {
            return new ArrayList();
        }
    }

    public OperatorToMultimap(Func1<? super T, ? extends K> func1, Func1<? super T, ? extends V> func12) {
        this(func1, func12, new DefaultToMultimapFactory(), new DefaultMultimapCollectionFactory());
    }

    public OperatorToMultimap(Func1<? super T, ? extends K> func1, Func1<? super T, ? extends V> func12, Func0<? extends Map<K, Collection<V>>> func0) {
        this(func1, func12, func0, new DefaultMultimapCollectionFactory());
    }

    public OperatorToMultimap(Func1<? super T, ? extends K> func1, Func1<? super T, ? extends V> func12, Func0<? extends Map<K, Collection<V>>> func0, Func1<? super K, ? extends Collection<V>> func13) {
        this.keySelector = func1;
        this.valueSelector = func12;
        this.mapFactory = func0;
        this.collectionFactory = func13;
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super T> call(final Subscriber<? super Map<K, Collection<V>>> subscriber) {
        try {
            final Map<K, Collection<V>> mapCall = this.mapFactory.call();
            return new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorToMultimap.1
                private Map<K, Collection<V>> map;

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
                        K kCall = OperatorToMultimap.this.keySelector.call(t);
                        V vCall = OperatorToMultimap.this.valueSelector.call(t);
                        Collection<V> collectionCall = this.map.get(kCall);
                        if (collectionCall == null) {
                            try {
                                collectionCall = OperatorToMultimap.this.collectionFactory.call(kCall);
                                this.map.put(kCall, collectionCall);
                            } catch (Throwable th) {
                                Exceptions.throwOrReport(th, subscriber);
                                return;
                            }
                        }
                        collectionCall.add(vCall);
                    } catch (Throwable th2) {
                        Exceptions.throwOrReport(th2, subscriber);
                    }
                }

                @Override // p035rx.Observer
                public void onError(Throwable th) {
                    this.map = null;
                    subscriber.onError(th);
                }

                @Override // p035rx.Observer
                public void onCompleted() {
                    Map<K, Collection<V>> map = this.map;
                    this.map = null;
                    subscriber.onNext(map);
                    subscriber.onCompleted();
                }
            };
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            subscriber.onError(th);
            Subscriber<? super T> subscriberEmpty = Subscribers.empty();
            subscriberEmpty.unsubscribe();
            return subscriberEmpty;
        }
    }
}
