package com.baidu.trace.p012c;

import com.baidu.trace.model.StatusCodes;
import com.baidu.trace.p012c.C0850a;

/* JADX INFO: renamed from: com.baidu.trace.c.b */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0851b implements Runnable {

    /* JADX INFO: renamed from: a */
    private /* synthetic */ C0850a.d f1734a;

    RunnableC0851b(C0850a.d dVar) {
        this.f1734a = dVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C0850a.d.m1215a(this.f1734a.f1727a, this.f1734a.f1732f, StatusCodes.NETWORK_NOT_AVAILABLE, StatusCodes.MSG_NETWORK_NOT_AVAILABLE, this.f1734a.f1728b, this.f1734a.f1733g);
    }
}
