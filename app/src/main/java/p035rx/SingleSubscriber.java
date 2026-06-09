package p035rx;

import p035rx.internal.util.SubscriptionList;

/* JADX INFO: loaded from: classes2.dex */
public abstract class SingleSubscriber<T> implements Subscription {

    /* JADX INFO: renamed from: cs */
    private final SubscriptionList f3475cs = new SubscriptionList();

    public abstract void onError(Throwable th);

    public abstract void onSuccess(T t);

    public final void add(Subscription subscription) {
        this.f3475cs.add(subscription);
    }

    @Override // p035rx.Subscription
    public final void unsubscribe() {
        this.f3475cs.unsubscribe();
    }

    @Override // p035rx.Subscription
    public final boolean isUnsubscribed() {
        return this.f3475cs.isUnsubscribed();
    }
}
