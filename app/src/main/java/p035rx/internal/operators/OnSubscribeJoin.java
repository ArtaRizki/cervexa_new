package p035rx.internal.operators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import p035rx.Observable;
import p035rx.Subscriber;
import p035rx.Subscription;
import p035rx.exceptions.Exceptions;
import p035rx.functions.Func1;
import p035rx.functions.Func2;
import p035rx.observers.SerializedSubscriber;
import p035rx.subscriptions.CompositeSubscription;
import p035rx.subscriptions.SerialSubscription;

/* JADX INFO: loaded from: classes2.dex */
public final class OnSubscribeJoin<TLeft, TRight, TLeftDuration, TRightDuration, R> implements Observable.OnSubscribe<R> {
    final Observable<TLeft> left;
    final Func1<TLeft, Observable<TLeftDuration>> leftDurationSelector;
    final Func2<TLeft, TRight, R> resultSelector;
    final Observable<TRight> right;
    final Func1<TRight, Observable<TRightDuration>> rightDurationSelector;

    public OnSubscribeJoin(Observable<TLeft> observable, Observable<TRight> observable2, Func1<TLeft, Observable<TLeftDuration>> func1, Func1<TRight, Observable<TRightDuration>> func12, Func2<TLeft, TRight, R> func2) {
        this.left = observable;
        this.right = observable2;
        this.leftDurationSelector = func1;
        this.rightDurationSelector = func12;
        this.resultSelector = func2;
    }

    @Override // p035rx.functions.Action1
    public void call(Subscriber<? super R> subscriber) {
        new ResultSink(new SerializedSubscriber(subscriber)).run();
    }

    final class ResultSink {
        boolean leftDone;
        int leftId;
        boolean rightDone;
        int rightId;
        final Subscriber<? super R> subscriber;
        final Object guard = new Object();
        final CompositeSubscription group = new CompositeSubscription();
        final Map<Integer, TLeft> leftMap = new HashMap();
        final Map<Integer, TRight> rightMap = new HashMap();

        public ResultSink(Subscriber<? super R> subscriber) {
            this.subscriber = subscriber;
        }

        public void run() {
            this.subscriber.add(this.group);
            LeftSubscriber leftSubscriber = new LeftSubscriber();
            RightSubscriber rightSubscriber = new RightSubscriber();
            this.group.add(leftSubscriber);
            this.group.add(rightSubscriber);
            OnSubscribeJoin.this.left.unsafeSubscribe(leftSubscriber);
            OnSubscribeJoin.this.right.unsafeSubscribe(rightSubscriber);
        }

        final class LeftSubscriber extends Subscriber<TLeft> {
            LeftSubscriber() {
            }

            protected void expire(int i, Subscription subscription) {
                boolean z;
                synchronized (ResultSink.this.guard) {
                    z = ResultSink.this.leftMap.remove(Integer.valueOf(i)) != null && ResultSink.this.leftMap.isEmpty() && ResultSink.this.leftDone;
                }
                if (z) {
                    ResultSink.this.subscriber.onCompleted();
                    ResultSink.this.subscriber.unsubscribe();
                } else {
                    ResultSink.this.group.remove(subscription);
                }
            }

            @Override // p035rx.Observer
            public void onNext(TLeft tleft) {
                int i;
                int i2;
                synchronized (ResultSink.this.guard) {
                    ResultSink resultSink = ResultSink.this;
                    i = resultSink.leftId;
                    resultSink.leftId = i + 1;
                    ResultSink.this.leftMap.put(Integer.valueOf(i), tleft);
                    i2 = ResultSink.this.rightId;
                }
                try {
                    Observable<TLeftDuration> observableCall = OnSubscribeJoin.this.leftDurationSelector.call(tleft);
                    LeftDurationSubscriber leftDurationSubscriber = new LeftDurationSubscriber(i);
                    ResultSink.this.group.add(leftDurationSubscriber);
                    observableCall.unsafeSubscribe(leftDurationSubscriber);
                    ArrayList arrayList = new ArrayList();
                    synchronized (ResultSink.this.guard) {
                        for (Map.Entry<Integer, TRight> entry : ResultSink.this.rightMap.entrySet()) {
                            if (entry.getKey().intValue() < i2) {
                                arrayList.add(entry.getValue());
                            }
                        }
                    }
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        ResultSink.this.subscriber.onNext(OnSubscribeJoin.this.resultSelector.call(tleft, (TRight) it.next()));
                    }
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this);
                }
            }

            @Override // p035rx.Observer
            public void onError(Throwable th) {
                ResultSink.this.subscriber.onError(th);
                ResultSink.this.subscriber.unsubscribe();
            }

            @Override // p035rx.Observer
            public void onCompleted() {
                boolean z;
                synchronized (ResultSink.this.guard) {
                    z = true;
                    ResultSink.this.leftDone = true;
                    if (!ResultSink.this.rightDone && !ResultSink.this.leftMap.isEmpty()) {
                        z = false;
                    }
                }
                if (z) {
                    ResultSink.this.subscriber.onCompleted();
                    ResultSink.this.subscriber.unsubscribe();
                } else {
                    ResultSink.this.group.remove(this);
                }
            }

            final class LeftDurationSubscriber extends Subscriber<TLeftDuration> {

                /* JADX INFO: renamed from: id */
                final int f3491id;
                boolean once = true;

                public LeftDurationSubscriber(int i) {
                    this.f3491id = i;
                }

                @Override // p035rx.Observer
                public void onNext(TLeftDuration tleftduration) {
                    onCompleted();
                }

                @Override // p035rx.Observer
                public void onError(Throwable th) {
                    LeftSubscriber.this.onError(th);
                }

                @Override // p035rx.Observer
                public void onCompleted() {
                    if (this.once) {
                        this.once = false;
                        LeftSubscriber.this.expire(this.f3491id, this);
                    }
                }
            }
        }

        final class RightSubscriber extends Subscriber<TRight> {
            RightSubscriber() {
            }

            void expire(int i, Subscription subscription) {
                boolean z;
                synchronized (ResultSink.this.guard) {
                    z = ResultSink.this.rightMap.remove(Integer.valueOf(i)) != null && ResultSink.this.rightMap.isEmpty() && ResultSink.this.rightDone;
                }
                if (z) {
                    ResultSink.this.subscriber.onCompleted();
                    ResultSink.this.subscriber.unsubscribe();
                } else {
                    ResultSink.this.group.remove(subscription);
                }
            }

            @Override // p035rx.Observer
            public void onNext(TRight tright) {
                int i;
                int i2;
                synchronized (ResultSink.this.guard) {
                    ResultSink resultSink = ResultSink.this;
                    i = resultSink.rightId;
                    resultSink.rightId = i + 1;
                    ResultSink.this.rightMap.put(Integer.valueOf(i), tright);
                    i2 = ResultSink.this.leftId;
                }
                ResultSink.this.group.add(new SerialSubscription());
                try {
                    Observable<TRightDuration> observableCall = OnSubscribeJoin.this.rightDurationSelector.call(tright);
                    RightDurationSubscriber rightDurationSubscriber = new RightDurationSubscriber(i);
                    ResultSink.this.group.add(rightDurationSubscriber);
                    observableCall.unsafeSubscribe(rightDurationSubscriber);
                    ArrayList arrayList = new ArrayList();
                    synchronized (ResultSink.this.guard) {
                        for (Map.Entry<Integer, TLeft> entry : ResultSink.this.leftMap.entrySet()) {
                            if (entry.getKey().intValue() < i2) {
                                arrayList.add(entry.getValue());
                            }
                        }
                    }
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        ResultSink.this.subscriber.onNext(OnSubscribeJoin.this.resultSelector.call((TLeft) it.next(), tright));
                    }
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this);
                }
            }

            @Override // p035rx.Observer
            public void onError(Throwable th) {
                ResultSink.this.subscriber.onError(th);
                ResultSink.this.subscriber.unsubscribe();
            }

            @Override // p035rx.Observer
            public void onCompleted() {
                boolean z;
                synchronized (ResultSink.this.guard) {
                    z = true;
                    ResultSink.this.rightDone = true;
                    if (!ResultSink.this.leftDone && !ResultSink.this.rightMap.isEmpty()) {
                        z = false;
                    }
                }
                if (z) {
                    ResultSink.this.subscriber.onCompleted();
                    ResultSink.this.subscriber.unsubscribe();
                } else {
                    ResultSink.this.group.remove(this);
                }
            }

            final class RightDurationSubscriber extends Subscriber<TRightDuration> {

                /* JADX INFO: renamed from: id */
                final int f3492id;
                boolean once = true;

                public RightDurationSubscriber(int i) {
                    this.f3492id = i;
                }

                @Override // p035rx.Observer
                public void onNext(TRightDuration trightduration) {
                    onCompleted();
                }

                @Override // p035rx.Observer
                public void onError(Throwable th) {
                    RightSubscriber.this.onError(th);
                }

                @Override // p035rx.Observer
                public void onCompleted() {
                    if (this.once) {
                        this.once = false;
                        RightSubscriber.this.expire(this.f3492id, this);
                    }
                }
            }
        }
    }
}
