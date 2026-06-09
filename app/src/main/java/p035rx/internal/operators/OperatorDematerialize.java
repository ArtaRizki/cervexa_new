package p035rx.internal.operators;

import p035rx.Notification;
import p035rx.Observable;
import p035rx.Subscriber;

/* JADX INFO: loaded from: classes2.dex */
public final class OperatorDematerialize<T> implements Observable.Operator<T, Notification<T>> {

    private static final class Holder {
        static final OperatorDematerialize<Object> INSTANCE = new OperatorDematerialize<>();

        private Holder() {
        }
    }

    public static OperatorDematerialize instance() {
        return Holder.INSTANCE;
    }

    OperatorDematerialize() {
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super Notification<T>> call(final Subscriber<? super T> subscriber) {
        return new Subscriber<Notification<T>>(subscriber) { // from class: rx.internal.operators.OperatorDematerialize.1
            boolean terminated;

            @Override // p035rx.Observer
            public void onNext(Notification<T> notification) {
                int i = C27532.$SwitchMap$rx$Notification$Kind[notification.getKind().ordinal()];
                if (i == 1) {
                    if (this.terminated) {
                        return;
                    }
                    subscriber.onNext(notification.getValue());
                } else if (i == 2) {
                    onError(notification.getThrowable());
                } else {
                    if (i != 3) {
                        return;
                    }
                    onCompleted();
                }
            }

            @Override // p035rx.Observer
            public void onError(Throwable th) {
                if (this.terminated) {
                    return;
                }
                this.terminated = true;
                subscriber.onError(th);
            }

            @Override // p035rx.Observer
            public void onCompleted() {
                if (this.terminated) {
                    return;
                }
                this.terminated = true;
                subscriber.onCompleted();
            }
        };
    }

    /* JADX INFO: renamed from: rx.internal.operators.OperatorDematerialize$2 */
    static /* synthetic */ class C27532 {
        static final /* synthetic */ int[] $SwitchMap$rx$Notification$Kind;

        static {
            int[] iArr = new int[Notification.Kind.values().length];
            $SwitchMap$rx$Notification$Kind = iArr;
            try {
                iArr[Notification.Kind.OnNext.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$rx$Notification$Kind[Notification.Kind.OnError.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$rx$Notification$Kind[Notification.Kind.OnCompleted.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
