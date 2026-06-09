package p035rx.internal.schedulers;

import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import p035rx.Subscription;
import p035rx.functions.Action0;
import p035rx.internal.util.SubscriptionList;
import p035rx.subscriptions.CompositeSubscription;

/* JADX INFO: loaded from: classes2.dex */
public final class ScheduledAction extends AtomicReference<Thread> implements Runnable, Subscription {
    private static final long serialVersionUID = -3962399486978279857L;
    final Action0 action;
    final SubscriptionList cancel;

    public ScheduledAction(Action0 action0) {
        this.action = action0;
        this.cancel = new SubscriptionList();
    }

    public ScheduledAction(Action0 action0, CompositeSubscription compositeSubscription) {
        this.action = action0;
        this.cancel = new SubscriptionList(new Remover(this, compositeSubscription));
    }

    public ScheduledAction(Action0 action0, SubscriptionList subscriptionList) {
        this.action = action0;
        this.cancel = new SubscriptionList(new Remover2(this, subscriptionList));
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            lazySet(Thread.currentThread());
            this.action.call();
        } finally {
            try {
            } finally {
            }
        }
    }

    @Override // p035rx.Subscription
    public boolean isUnsubscribed() {
        return this.cancel.isUnsubscribed();
    }

    @Override // p035rx.Subscription
    public void unsubscribe() {
        if (this.cancel.isUnsubscribed()) {
            return;
        }
        this.cancel.unsubscribe();
    }

    public void add(Subscription subscription) {
        this.cancel.add(subscription);
    }

    public void add(Future<?> future) {
        this.cancel.add(new FutureCompleter(future));
    }

    public void addParent(CompositeSubscription compositeSubscription) {
        this.cancel.add(new Remover(this, compositeSubscription));
    }

    public void addParent(SubscriptionList subscriptionList) {
        this.cancel.add(new Remover2(this, subscriptionList));
    }

    private final class FutureCompleter implements Subscription {

        /* JADX INFO: renamed from: f */
        private final Future<?> f3513f;

        FutureCompleter(Future<?> future) {
            this.f3513f = future;
        }

        @Override // p035rx.Subscription
        public void unsubscribe() {
            if (ScheduledAction.this.get() != Thread.currentThread()) {
                this.f3513f.cancel(true);
            } else {
                this.f3513f.cancel(false);
            }
        }

        @Override // p035rx.Subscription
        public boolean isUnsubscribed() {
            return this.f3513f.isCancelled();
        }
    }

    private static final class Remover extends AtomicBoolean implements Subscription {
        private static final long serialVersionUID = 247232374289553518L;
        final CompositeSubscription parent;

        /* JADX INFO: renamed from: s */
        final ScheduledAction f3514s;

        public Remover(ScheduledAction scheduledAction, CompositeSubscription compositeSubscription) {
            this.f3514s = scheduledAction;
            this.parent = compositeSubscription;
        }

        @Override // p035rx.Subscription
        public boolean isUnsubscribed() {
            return this.f3514s.isUnsubscribed();
        }

        @Override // p035rx.Subscription
        public void unsubscribe() {
            if (compareAndSet(false, true)) {
                this.parent.remove(this.f3514s);
            }
        }
    }

    private static final class Remover2 extends AtomicBoolean implements Subscription {
        private static final long serialVersionUID = 247232374289553518L;
        final SubscriptionList parent;

        /* JADX INFO: renamed from: s */
        final ScheduledAction f3515s;

        public Remover2(ScheduledAction scheduledAction, SubscriptionList subscriptionList) {
            this.f3515s = scheduledAction;
            this.parent = subscriptionList;
        }

        @Override // p035rx.Subscription
        public boolean isUnsubscribed() {
            return this.f3515s.isUnsubscribed();
        }

        @Override // p035rx.Subscription
        public void unsubscribe() {
            if (compareAndSet(false, true)) {
                this.parent.remove(this.f3515s);
            }
        }
    }
}
