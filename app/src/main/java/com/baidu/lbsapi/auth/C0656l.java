package com.baidu.lbsapi.auth;

import android.os.Handler;
import android.os.Looper;

/* JADX INFO: renamed from: com.baidu.lbsapi.auth.l */
/* JADX INFO: loaded from: classes.dex */
class C0656l extends Thread {

    /* JADX INFO: renamed from: a */
    Handler f145a;

    /* JADX INFO: renamed from: b */
    private Object f146b;

    /* JADX INFO: renamed from: c */
    private boolean f147c;

    C0656l() {
        this.f145a = null;
        this.f146b = new Object();
        this.f147c = false;
    }

    C0656l(String str) {
        super(str);
        this.f145a = null;
        this.f146b = new Object();
        this.f147c = false;
    }

    /* JADX INFO: renamed from: a */
    public void m165a() {
        if (C0645a.f120a) {
            C0645a.m130a("Looper thread quit()");
        }
        this.f145a.getLooper().quit();
    }

    /* JADX INFO: renamed from: b */
    public void m166b() {
        synchronized (this.f146b) {
            try {
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!this.f147c) {
                this.f146b.wait();
            }
        }
    }

    /* JADX INFO: renamed from: c */
    public void m167c() {
        synchronized (this.f146b) {
            this.f147c = true;
            this.f146b.notifyAll();
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Looper.prepare();
        this.f145a = new Handler();
        if (C0645a.f120a) {
            C0645a.m130a("new Handler() finish!!");
        }
        Looper.loop();
        if (C0645a.f120a) {
            C0645a.m130a("LooperThread run() thread id:" + String.valueOf(Thread.currentThread().getId()));
        }
    }
}
