package p035rx.internal.operators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import kotlin.jvm.internal.LongCompanionObject;
import p035rx.Observable;
import p035rx.Observer;
import p035rx.Subscriber;
import p035rx.functions.Func1;
import p035rx.observers.SerializedObserver;
import p035rx.observers.SerializedSubscriber;
import p035rx.subjects.UnicastSubject;
import p035rx.subscriptions.CompositeSubscription;

/* JADX INFO: loaded from: classes2.dex */
public final class OperatorWindowWithStartEndObservable<T, U, V> implements Observable.Operator<Observable<T>, T> {
    final Func1<? super U, ? extends Observable<? extends V>> windowClosingSelector;
    final Observable<? extends U> windowOpenings;

    public OperatorWindowWithStartEndObservable(Observable<? extends U> observable, Func1<? super U, ? extends Observable<? extends V>> func1) {
        this.windowOpenings = observable;
        this.windowClosingSelector = func1;
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super T> call(Subscriber<? super Observable<T>> subscriber) {
        CompositeSubscription compositeSubscription = new CompositeSubscription();
        subscriber.add(compositeSubscription);
        final SourceSubscriber sourceSubscriber = new SourceSubscriber(subscriber, compositeSubscription);
        Subscriber<U> subscriber2 = new Subscriber<U>() { // from class: rx.internal.operators.OperatorWindowWithStartEndObservable.1
            @Override // p035rx.Subscriber
            public void onStart() {
                request(LongCompanionObject.MAX_VALUE);
            }

            @Override // p035rx.Observer
            public void onNext(U u) {
                sourceSubscriber.beginWindow(u);
            }

            @Override // p035rx.Observer
            public void onError(Throwable th) {
                sourceSubscriber.onError(th);
            }

            @Override // p035rx.Observer
            public void onCompleted() {
                sourceSubscriber.onCompleted();
            }
        };
        compositeSubscription.add(sourceSubscriber);
        compositeSubscription.add(subscriber2);
        this.windowOpenings.unsafeSubscribe(subscriber2);
        return sourceSubscriber;
    }

    static final class SerializedSubject<T> {
        final Observer<T> consumer;
        final Observable<T> producer;

        public SerializedSubject(Observer<T> observer, Observable<T> observable) {
            this.consumer = new SerializedObserver(observer);
            this.producer = observable;
        }
    }

    final class SourceSubscriber extends Subscriber<T> {
        final Subscriber<? super Observable<T>> child;
        final CompositeSubscription csub;
        boolean done;
        final Object guard = new Object();
        final List<SerializedSubject<T>> chunks = new LinkedList();

        public SourceSubscriber(Subscriber<? super Observable<T>> subscriber, CompositeSubscription compositeSubscription) {
            this.child = new SerializedSubscriber(subscriber);
            this.csub = compositeSubscription;
        }

        @Override // p035rx.Subscriber
        public void onStart() {
            request(LongCompanionObject.MAX_VALUE);
        }

        @Override // p035rx.Observer
        public void onNext(T t) {
            synchronized (this.guard) {
                if (this.done) {
                    return;
                }
                Iterator it = new ArrayList(this.chunks).iterator();
                while (it.hasNext()) {
                    ((SerializedSubject) it.next()).consumer.onNext(t);
                }
            }
        }

        @Override // p035rx.Observer
        public void onError(Throwable th) {
            try {
                synchronized (this.guard) {
                    if (this.done) {
                        return;
                    }
                    this.done = true;
                    ArrayList arrayList = new ArrayList(this.chunks);
                    this.chunks.clear();
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        ((SerializedSubject) it.next()).consumer.onError(th);
                    }
                    this.child.onError(th);
                }
            } finally {
                this.csub.unsubscribe();
            }
        }

        @Override // p035rx.Observer
        public void onCompleted() {
            try {
                synchronized (this.guard) {
                    if (this.done) {
                        return;
                    }
                    this.done = true;
                    ArrayList arrayList = new ArrayList(this.chunks);
                    this.chunks.clear();
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        ((SerializedSubject) it.next()).consumer.onCompleted();
                    }
                    this.child.onCompleted();
                }
            } finally {
                this.csub.unsubscribe();
            }
        }

        void beginWindow(U u) {
            final SerializedSubject<T> serializedSubjectCreateSerializedSubject = createSerializedSubject();
            synchronized (this.guard) {
                if (this.done) {
                    return;
                }
                this.chunks.add(serializedSubjectCreateSerializedSubject);
                this.child.onNext(serializedSubjectCreateSerializedSubject.producer);
                try {
                    Observable<? extends V> observableCall = OperatorWindowWithStartEndObservable.this.windowClosingSelector.call(u);
                    Subscriber<V> subscriber = new Subscriber<V>() { // from class: rx.internal.operators.OperatorWindowWithStartEndObservable.SourceSubscriber.1
                        boolean once = true;

                        @Override // p035rx.Observer
                        public void onError(Throwable th) {
                        }

                        @Override // p035rx.Observer
                        public void onNext(V v) {
                            onCompleted();
                        }

                        @Override // p035rx.Observer
                        public void onCompleted() {
                            if (this.once) {
                                this.once = false;
                                SourceSubscriber.this.endWindow(serializedSubjectCreateSerializedSubject);
                                SourceSubscriber.this.csub.remove(this);
                            }
                        }
                    };
                    this.csub.add(subscriber);
                    observableCall.unsafeSubscribe(subscriber);
                } catch (Throwable th) {
                    onError(th);
                }
            }
        }

        void endWindow(SerializedSubject<T> serializedSubject) {
            boolean z;
            synchronized (this.guard) {
                if (this.done) {
                    return;
                }
                Iterator<SerializedSubject<T>> it = this.chunks.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    } else if (it.next() == serializedSubject) {
                        z = true;
                        it.remove();
                        break;
                    }
                }
                if (z) {
                    serializedSubject.consumer.onCompleted();
                }
            }
        }

        SerializedSubject<T> createSerializedSubject() {
            UnicastSubject unicastSubjectCreate = UnicastSubject.create();
            return new SerializedSubject<>(unicastSubjectCreate, unicastSubjectCreate);
        }
    }
}
