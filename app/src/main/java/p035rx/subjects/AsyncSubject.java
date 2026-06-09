package p035rx.subjects;

import java.util.ArrayList;
import p035rx.Observable;
import p035rx.exceptions.Exceptions;
import p035rx.functions.Action1;
import p035rx.internal.operators.NotificationLite;
import p035rx.internal.producers.SingleProducer;
import p035rx.subjects.SubjectSubscriptionManager;

/* JADX INFO: loaded from: classes2.dex */
public final class AsyncSubject<T> extends Subject<T, T> {
    volatile Object lastValue;

    /* JADX INFO: renamed from: nl */
    private final NotificationLite<T> f3543nl;
    final SubjectSubscriptionManager<T> state;

    public static <T> AsyncSubject<T> create() {
        final SubjectSubscriptionManager subjectSubscriptionManager = new SubjectSubscriptionManager();
        subjectSubscriptionManager.onTerminated = new Action1<SubjectSubscriptionManager.SubjectObserver<T>>() { // from class: rx.subjects.AsyncSubject.1
            @Override // p035rx.functions.Action1
            public void call(SubjectSubscriptionManager.SubjectObserver<T> subjectObserver) {
                Object latest = subjectSubscriptionManager.getLatest();
                NotificationLite<T> notificationLite = subjectSubscriptionManager.f3546nl;
                if (latest == null || notificationLite.isCompleted(latest)) {
                    subjectObserver.onCompleted();
                } else if (notificationLite.isError(latest)) {
                    subjectObserver.onError(notificationLite.getError(latest));
                } else {
                    subjectObserver.actual.setProducer(new SingleProducer(subjectObserver.actual, notificationLite.getValue(latest)));
                }
            }
        };
        return new AsyncSubject<>(subjectSubscriptionManager, subjectSubscriptionManager);
    }

    protected AsyncSubject(Observable.OnSubscribe<T> onSubscribe, SubjectSubscriptionManager<T> subjectSubscriptionManager) {
        super(onSubscribe);
        this.f3543nl = NotificationLite.instance();
        this.state = subjectSubscriptionManager;
    }

    @Override // p035rx.Observer
    public void onCompleted() {
        if (this.state.active) {
            Object objCompleted = this.lastValue;
            if (objCompleted == null) {
                objCompleted = this.f3543nl.completed();
            }
            for (SubjectSubscriptionManager.SubjectObserver<T> subjectObserver : this.state.terminate(objCompleted)) {
                if (objCompleted == this.f3543nl.completed()) {
                    subjectObserver.onCompleted();
                } else {
                    subjectObserver.actual.setProducer(new SingleProducer(subjectObserver.actual, this.f3543nl.getValue(objCompleted)));
                }
            }
        }
    }

    @Override // p035rx.Observer
    public void onError(Throwable th) {
        if (this.state.active) {
            ArrayList arrayList = null;
            for (SubjectSubscriptionManager.SubjectObserver<T> subjectObserver : this.state.terminate(this.f3543nl.error(th))) {
                try {
                    subjectObserver.onError(th);
                } catch (Throwable th2) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(th2);
                }
            }
            Exceptions.throwIfAny(arrayList);
        }
    }

    @Override // p035rx.Observer
    public void onNext(T t) {
        this.lastValue = this.f3543nl.next(t);
    }

    @Override // p035rx.subjects.Subject
    public boolean hasObservers() {
        return this.state.observers().length > 0;
    }

    public boolean hasValue() {
        return !this.f3543nl.isError(this.state.getLatest()) && this.f3543nl.isNext(this.lastValue);
    }

    public boolean hasThrowable() {
        return this.f3543nl.isError(this.state.getLatest());
    }

    public boolean hasCompleted() {
        Object latest = this.state.getLatest();
        return (latest == null || this.f3543nl.isError(latest)) ? false : true;
    }

    public T getValue() {
        Object obj = this.lastValue;
        if (this.f3543nl.isError(this.state.getLatest()) || !this.f3543nl.isNext(obj)) {
            return null;
        }
        return this.f3543nl.getValue(obj);
    }

    public Throwable getThrowable() {
        Object latest = this.state.getLatest();
        if (this.f3543nl.isError(latest)) {
            return this.f3543nl.getError(latest);
        }
        return null;
    }
}
