package p035rx.subjects;

import java.lang.reflect.Array;
import java.util.ArrayList;
import p035rx.Observable;
import p035rx.exceptions.Exceptions;
import p035rx.functions.Action1;
import p035rx.internal.operators.NotificationLite;
import p035rx.subjects.SubjectSubscriptionManager;

/* JADX INFO: loaded from: classes2.dex */
public final class BehaviorSubject<T> extends Subject<T, T> {
    private static final Object[] EMPTY_ARRAY = new Object[0];

    /* JADX INFO: renamed from: nl */
    private final NotificationLite<T> f3544nl;
    private final SubjectSubscriptionManager<T> state;

    public static <T> BehaviorSubject<T> create() {
        return create(null, false);
    }

    public static <T> BehaviorSubject<T> create(T t) {
        return create(t, true);
    }

    private static <T> BehaviorSubject<T> create(T t, boolean z) {
        final SubjectSubscriptionManager subjectSubscriptionManager = new SubjectSubscriptionManager();
        if (z) {
            subjectSubscriptionManager.setLatest(NotificationLite.instance().next(t));
        }
        subjectSubscriptionManager.onAdded = new Action1<SubjectSubscriptionManager.SubjectObserver<T>>() { // from class: rx.subjects.BehaviorSubject.1
            @Override // p035rx.functions.Action1
            public void call(SubjectSubscriptionManager.SubjectObserver<T> subjectObserver) throws Throwable {
                subjectObserver.emitFirst(subjectSubscriptionManager.getLatest(), subjectSubscriptionManager.f3546nl);
            }
        };
        subjectSubscriptionManager.onTerminated = subjectSubscriptionManager.onAdded;
        return new BehaviorSubject<>(subjectSubscriptionManager, subjectSubscriptionManager);
    }

    protected BehaviorSubject(Observable.OnSubscribe<T> onSubscribe, SubjectSubscriptionManager<T> subjectSubscriptionManager) {
        super(onSubscribe);
        this.f3544nl = NotificationLite.instance();
        this.state = subjectSubscriptionManager;
    }

    @Override // p035rx.Observer
    public void onCompleted() {
        if (this.state.getLatest() == null || this.state.active) {
            Object objCompleted = this.f3544nl.completed();
            for (SubjectSubscriptionManager.SubjectObserver<T> subjectObserver : this.state.terminate(objCompleted)) {
                subjectObserver.emitNext(objCompleted, this.state.f3546nl);
            }
        }
    }

    @Override // p035rx.Observer
    public void onError(Throwable th) {
        if (this.state.getLatest() == null || this.state.active) {
            Object objError = this.f3544nl.error(th);
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
        if (this.state.getLatest() == null || this.state.active) {
            Object next = this.f3544nl.next(t);
            for (SubjectSubscriptionManager.SubjectObserver<T> subjectObserver : this.state.next(next)) {
                subjectObserver.emitNext(next, this.state.f3546nl);
            }
        }
    }

    int subscriberCount() {
        return this.state.observers().length;
    }

    @Override // p035rx.subjects.Subject
    public boolean hasObservers() {
        return this.state.observers().length > 0;
    }

    public boolean hasValue() {
        return this.f3544nl.isNext(this.state.getLatest());
    }

    public boolean hasThrowable() {
        return this.f3544nl.isError(this.state.getLatest());
    }

    public boolean hasCompleted() {
        return this.f3544nl.isCompleted(this.state.getLatest());
    }

    public T getValue() {
        Object latest = this.state.getLatest();
        if (this.f3544nl.isNext(latest)) {
            return this.f3544nl.getValue(latest);
        }
        return null;
    }

    public Throwable getThrowable() {
        Object latest = this.state.getLatest();
        if (this.f3544nl.isError(latest)) {
            return this.f3544nl.getError(latest);
        }
        return null;
    }

    public T[] getValues(T[] tArr) {
        Object latest = this.state.getLatest();
        if (this.f3544nl.isNext(latest)) {
            if (tArr.length == 0) {
                tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), 1));
            }
            tArr[0] = this.f3544nl.getValue(latest);
            if (tArr.length > 1) {
                tArr[1] = null;
            }
        } else if (tArr.length > 0) {
            tArr[0] = null;
        }
        return tArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Object[] getValues() {
        Object[] values = getValues(EMPTY_ARRAY);
        return values == EMPTY_ARRAY ? new Object[0] : values;
    }
}
