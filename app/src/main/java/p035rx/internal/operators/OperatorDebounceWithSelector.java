package p035rx.internal.operators;

import kotlin.jvm.internal.LongCompanionObject;
import p035rx.Observable;
import p035rx.Subscriber;
import p035rx.exceptions.Exceptions;
import p035rx.functions.Func1;
import p035rx.internal.operators.OperatorDebounceWithTime;
import p035rx.observers.SerializedSubscriber;
import p035rx.subscriptions.SerialSubscription;

/* JADX INFO: loaded from: classes2.dex */
public final class OperatorDebounceWithSelector<T, U> implements Observable.Operator<T, T> {
    final Func1<? super T, ? extends Observable<U>> selector;

    public OperatorDebounceWithSelector(Func1<? super T, ? extends Observable<U>> func1) {
        this.selector = func1;
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber);
        SerialSubscription serialSubscription = new SerialSubscription();
        subscriber.add(serialSubscription);
        return new C27481(subscriber, serializedSubscriber, serialSubscription);
    }

    /* JADX INFO: renamed from: rx.internal.operators.OperatorDebounceWithSelector$1 */
    class C27481 extends Subscriber<T> {
        final Subscriber<?> self;
        final OperatorDebounceWithTime.DebounceState<T> state;
        final /* synthetic */ SerializedSubscriber val$s;
        final /* synthetic */ SerialSubscription val$ssub;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C27481(Subscriber subscriber, SerializedSubscriber serializedSubscriber, SerialSubscription serialSubscription) {
            super(subscriber);
            this.val$s = serializedSubscriber;
            this.val$ssub = serialSubscription;
            this.state = new OperatorDebounceWithTime.DebounceState<>();
            this.self = this;
        }

        @Override // p035rx.Subscriber
        public void onStart() {
            request(LongCompanionObject.MAX_VALUE);
        }

        @Override // p035rx.Observer
        public void onNext(T t) {
            try {
                Observable<U> observableCall = OperatorDebounceWithSelector.this.selector.call(t);
                final int next = this.state.next(t);
                Subscriber<U> subscriber = new Subscriber<U>() { // from class: rx.internal.operators.OperatorDebounceWithSelector.1.1
                    @Override // p035rx.Observer
                    public void onNext(U u) {
                        onCompleted();
                    }

                    @Override // p035rx.Observer
                    public void onError(Throwable th) {
                        C27481.this.self.onError(th);
                    }

                    @Override // p035rx.Observer
                    public void onCompleted() {
                        C27481.this.state.emit(next, C27481.this.val$s, C27481.this.self);
                        unsubscribe();
                    }
                };
                this.val$ssub.set(subscriber);
                observableCall.unsafeSubscribe(subscriber);
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, this);
            }
        }

        @Override // p035rx.Observer
        public void onError(Throwable th) {
            this.val$s.onError(th);
            unsubscribe();
            this.state.clear();
        }

        @Override // p035rx.Observer
        public void onCompleted() {
            this.state.emitAndComplete(this.val$s, this);
        }
    }
}
