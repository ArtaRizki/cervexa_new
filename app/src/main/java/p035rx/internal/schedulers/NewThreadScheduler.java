package p035rx.internal.schedulers;

import java.util.concurrent.ThreadFactory;
import p035rx.Scheduler;

/* JADX INFO: loaded from: classes2.dex */
public final class NewThreadScheduler extends Scheduler {
    private final ThreadFactory threadFactory;

    public NewThreadScheduler(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
    }

    @Override // p035rx.Scheduler
    public Scheduler.Worker createWorker() {
        return new NewThreadWorker(this.threadFactory);
    }
}
