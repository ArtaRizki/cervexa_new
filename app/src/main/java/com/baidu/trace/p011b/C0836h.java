package com.baidu.trace.p011b;

import android.os.Handler;

/* JADX INFO: renamed from: com.baidu.trace.b.h */
/* JADX INFO: loaded from: classes.dex */
public final class C0836h extends Thread {

    /* JADX INFO: renamed from: a */
    private Handler f1661a;

    /* JADX INFO: renamed from: b */
    private boolean f1662b = true;

    public C0836h(Handler handler) {
        this.f1661a = handler;
    }

    /* JADX INFO: renamed from: a */
    public final void m1160a() {
        this.f1662b = false;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        synchronized (this) {
            try {
                wait(15000L);
            } catch (InterruptedException unused) {
            }
        }
        Handler handler = this.f1661a;
        if (handler == null || !this.f1662b) {
            return;
        }
        handler.obtainMessage(24).sendToTarget();
    }
}
