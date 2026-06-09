package p035rx.subscriptions;

import java.util.concurrent.Future;
import p035rx.Subscription;
import p035rx.functions.Action0;

/* JADX INFO: loaded from: classes2.dex */
public final class Subscriptions {
    private static final Unsubscribed UNSUBSCRIBED = new Unsubscribed();

    private Subscriptions() {
        throw new IllegalStateException("No instances!");
    }

    public static Subscription empty() {
        return BooleanSubscription.create();
    }

    public static Subscription unsubscribed() {
        return UNSUBSCRIBED;
    }

    public static Subscription create(Action0 action0) {
        return BooleanSubscription.create(action0);
    }

    public static Subscription from(Future<?> future) {
        return new FutureSubscription(future);
    }

    private static final class FutureSubscription implements Subscription {

        /* JADX INFO: renamed from: f */
        final Future<?> f3548f;

        public FutureSubscription(Future<?> future) {
            this.f3548f = future;
        }

        @Override // p035rx.Subscription
        public void unsubscribe() {
            this.f3548f.cancel(true);
        }

        @Override // p035rx.Subscription
        public boolean isUnsubscribed() {
            return this.f3548f.isCancelled();
        }
    }

    public static CompositeSubscription from(Subscription... subscriptionArr) {
        return new CompositeSubscription(subscriptionArr);
    }

    static final class Unsubscribed implements Subscription {
        @Override // p035rx.Subscription
        public boolean isUnsubscribed() {
            return true;
        }

        @Override // p035rx.Subscription
        public void unsubscribe() {
        }

        Unsubscribed() {
        }
    }
}
