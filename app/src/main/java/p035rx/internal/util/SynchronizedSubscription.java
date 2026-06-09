package p035rx.internal.util;

import p035rx.Subscription;

/* JADX INFO: loaded from: classes2.dex */
public class SynchronizedSubscription implements Subscription {

    /* JADX INFO: renamed from: s */
    private final Subscription f3527s;

    public SynchronizedSubscription(Subscription subscription) {
        this.f3527s = subscription;
    }

    @Override // p035rx.Subscription
    public synchronized void unsubscribe() {
        this.f3527s.unsubscribe();
    }

    @Override // p035rx.Subscription
    public synchronized boolean isUnsubscribed() {
        return this.f3527s.isUnsubscribed();
    }
}
