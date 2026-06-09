package p035rx.subjects;

import java.util.ArrayList;
import p035rx.Observable;
import p035rx.exceptions.Exceptions;
import p035rx.functions.Action1;
import p035rx.internal.operators.NotificationLite;
import p035rx.subjects.SubjectSubscriptionManager;

/* JADX INFO: loaded from: classes2.dex */
public final class PublishSubject<T> extends Subject<T, T> {

    /* JADX INFO: renamed from: nl */
    private final NotificationLite<T> f3545nl;
    final SubjectSubscriptionManager<T> state;

    public static <T> PublishSubject<T> create() {
        final SubjectSubscriptionManager subjectSubscriptionManager = new SubjectSubscriptionManager();
        subjectSubscriptionManager.onTerminated = new Action1<SubjectSubscriptionManager.SubjectObserver<T>>() { // from class: rx.subjects.PublishSubject.1
            @Override // p035rx.functions.Action1
            public void call(SubjectSubscriptionManager.SubjectObserver<T> subjectObserver) throws Throwable {
                subjectObserver.emitFirst(subjectSubscriptionManager.getLatest(), subjectSubscriptionManager.f3546nl);
            }
        };
        return new PublishSubject<>(subjectSubscriptionManager, subjectSubscriptionManager);
    }

    protected PublishSubject(Observable.OnSubscribe<T> onSubscribe, SubjectSubscriptionManager<T> subjectSubscriptionManager) {
        super(onSubscribe);
        this.f3545nl = NotificationLite.instance();
        this.state = subjectSubscriptionManager;
    }

    @Override // p035rx.Observer
    public void onCompleted() {
        if (this.state.active) {
            Object objCompleted = this.f3545nl.completed();
            for (SubjectSubscriptionManager.SubjectObserver<T> subjectObserver : this.state.terminate(objCompleted)) {
                subjectObserver.emitNext(objCompleted, this.state.f3546nl);
            }
        }
    }

    @Override // p035rx.Observer
    public void onError(Throwable th) {
        if (this.state.active) {
            Object objError = this.f3545nl.error(th);
            ArrayList arrayList = null;
            for (SubjectSubscriptionManager.SubjectObserver<T> subjectObserver : this.state.terminate(objError)) {
                try {
                    subjectObserver.emitNext(objError, this.state.f3546nl);
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
        for (SubjectSubscriptionManager.SubjectObserver<T> subjectObserver : this.state.observers()) {
            subjectObserver.onNext(t);
        }
    }

    @Override // p035rx.subjects.Subject
    public boolean hasObservers() {
        return this.state.observers().length > 0;
    }

    public boolean hasThrowable() {
        return this.f3545nl.isError(this.state.getLatest());
    }

    public boolean hasCompleted() {
        Object latest = this.state.getLatest();
        return (latest == null || this.f3545nl.isError(latest)) ? false : true;
    }

    public Throwable getThrowable() {
        Object latest = this.state.getLatest();
        if (this.f3545nl.isError(latest)) {
            return this.f3545nl.getError(latest);
        }
        return null;
    }
}
