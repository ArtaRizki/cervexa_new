package p035rx.internal.util;

import p035rx.Notification;
import p035rx.Observer;
import p035rx.functions.Action1;

/* JADX INFO: loaded from: classes2.dex */
public final class ActionNotificationObserver<T> implements Observer<T> {
    final Action1<Notification<? super T>> onNotification;

    public ActionNotificationObserver(Action1<Notification<? super T>> action1) {
        this.onNotification = action1;
    }

    @Override // p035rx.Observer
    public void onNext(T t) {
        this.onNotification.call(Notification.createOnNext(t));
    }

    @Override // p035rx.Observer
    public void onError(Throwable th) {
        this.onNotification.call(Notification.createOnError(th));
    }

    @Override // p035rx.Observer
    public void onCompleted() {
        this.onNotification.call(Notification.createOnCompleted());
    }
}
