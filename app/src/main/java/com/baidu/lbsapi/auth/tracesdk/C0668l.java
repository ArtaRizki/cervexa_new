package com.baidu.lbsapi.auth.tracesdk;

import android.os.Handler;
import android.os.Looper;

/* JADX INFO: renamed from: com.baidu.lbsapi.auth.tracesdk.l */
/* JADX INFO: loaded from: classes.dex */
class C0668l extends Thread {

    /* JADX INFO: renamed from: a */
    Handler f181a;

    /* JADX INFO: renamed from: b */
    private Object f182b;

    /* JADX INFO: renamed from: c */
    private boolean f183c;

    C0668l() {
        this.f181a = null;
        this.f182b = new Object();
        this.f183c = false;
    }

    C0668l(String str) {
        super(str);
        this.f181a = null;
        this.f182b = new Object();
        this.f183c = false;
    }

    /* JADX INFO: renamed from: a */
    public void m226a() {
        if (C0657a.f156a) {
            C0657a.m189a("Looper thread quit()");
        }
        this.f181a.getLooper().quit();
    }

    /* JADX INFO: renamed from: b */
    public void m227b() {
        synchronized (this.f182b) {
            try {
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!this.f183c) {
                this.f182b.wait();
            }
        }
    }

    /* JADX INFO: renamed from: c */
    public void m228c() {
        synchronized (this.f182b) {
            this.f183c = true;
            this.f182b.notifyAll();
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Looper.prepare();
        this.f181a = new Handler();
        if (C0657a.f156a) {
            C0657a.m189a("new Handler() finish!!");
        }
        Looper.loop();
        if (C0657a.f156a) {
            C0657a.m189a("LooperThread run() thread id:" + String.valueOf(Thread.currentThread().getId()));
        }
    }
}
