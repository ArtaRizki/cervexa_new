package p035rx;

/* JADX INFO: loaded from: classes2.dex */
public interface Observer<T> {
    void onCompleted();

    void onError(Throwable th);

    void onNext(T t);
}
