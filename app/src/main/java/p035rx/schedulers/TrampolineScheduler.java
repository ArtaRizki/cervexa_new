package p035rx.schedulers;

import p035rx.Scheduler;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public final class TrampolineScheduler extends Scheduler {
    @Override // p035rx.Scheduler
    public Scheduler.Worker createWorker() {
        return null;
    }

    private TrampolineScheduler() {
        throw new AssertionError();
    }
}
