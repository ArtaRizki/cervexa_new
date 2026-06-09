package p035rx.android.schedulers;

import android.os.Handler;
import p035rx.Scheduler;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public final class HandlerScheduler extends LooperScheduler {
    @Override // p035rx.android.schedulers.LooperScheduler, p035rx.Scheduler
    public /* bridge */ /* synthetic */ Scheduler.Worker createWorker() {
        return super.createWorker();
    }

    @Deprecated
    public static HandlerScheduler from(Handler handler) {
        if (handler == null) {
            throw new NullPointerException("handler == null");
        }
        return new HandlerScheduler(handler);
    }

    private HandlerScheduler(Handler handler) {
        super(handler);
    }
}
