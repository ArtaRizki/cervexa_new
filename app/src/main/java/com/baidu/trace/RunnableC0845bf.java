package com.baidu.trace;

/* JADX INFO: renamed from: com.baidu.trace.bf */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0845bf implements Runnable {

    /* JADX INFO: renamed from: a */
    private /* synthetic */ RunnableC0844be f1706a;

    RunnableC0845bf(RunnableC0844be runnableC0844be) {
        this.f1706a = runnableC0844be;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f1706a.f1702b.onQueryCacheTrackCallback(this.f1706a.f1701a);
    }
}
