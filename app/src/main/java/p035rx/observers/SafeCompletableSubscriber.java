package p035rx.observers;

import p035rx.Completable;
import p035rx.Subscription;
import p035rx.exceptions.CompositeException;
import p035rx.exceptions.Exceptions;
import p035rx.exceptions.OnCompletedFailedException;
import p035rx.exceptions.OnErrorFailedException;
import p035rx.internal.util.RxJavaPluginUtils;

/* JADX INFO: loaded from: classes2.dex */
public final class SafeCompletableSubscriber implements Completable.CompletableSubscriber, Subscription {
    final Completable.CompletableSubscriber actual;
    boolean done;

    /* JADX INFO: renamed from: s */
    Subscription f3539s;

    public SafeCompletableSubscriber(Completable.CompletableSubscriber completableSubscriber) {
        this.actual = completableSubscriber;
    }

    @Override // rx.Completable.CompletableSubscriber
    public void onCompleted() {
        if (this.done) {
            return;
        }
        this.done = true;
        try {
            this.actual.onCompleted();
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            throw new OnCompletedFailedException(th);
        }
    }

    @Override // rx.Completable.CompletableSubscriber
    public void onError(Throwable th) {
        RxJavaPluginUtils.handleException(th);
        if (this.done) {
            return;
        }
        this.done = true;
        try {
            this.actual.onError(th);
        } catch (Throwable th2) {
            Exceptions.throwIfFatal(th2);
            throw new OnErrorFailedException(new CompositeException(th, th2));
        }
    }

    @Override // rx.Completable.CompletableSubscriber
    public void onSubscribe(Subscription subscription) {
        this.f3539s = subscription;
        try {
            this.actual.onSubscribe(this);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            subscription.unsubscribe();
            onError(th);
        }
    }

    @Override // p035rx.Subscription
    public void unsubscribe() {
        this.f3539s.unsubscribe();
    }

    @Override // p035rx.Subscription
    public boolean isUnsubscribed() {
        return this.done || this.f3539s.isUnsubscribed();
    }
}
