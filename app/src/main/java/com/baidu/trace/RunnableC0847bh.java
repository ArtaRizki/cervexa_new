package com.baidu.trace;

/* JADX INFO: renamed from: com.baidu.trace.bh */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0847bh implements Runnable {

    /* JADX INFO: renamed from: a */
    private /* synthetic */ RunnableC0846bg f1713a;

    RunnableC0847bh(RunnableC0846bg runnableC0846bg) {
        this.f1713a = runnableC0846bg;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f1713a.f1708b.onClearCacheTrackCallback(this.f1713a.f1707a);
    }
}
