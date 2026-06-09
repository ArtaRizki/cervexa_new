package com.tencent.bugly.proguard;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: renamed from: com.tencent.bugly.proguard.aa */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public final class RunnableC1981aa implements Runnable {

    /* JADX INFO: renamed from: a */
    private final Handler f2666a;

    /* JADX INFO: renamed from: b */
    private final String f2667b;

    /* JADX INFO: renamed from: c */
    private long f2668c;

    /* JADX INFO: renamed from: d */
    private final long f2669d;

    /* JADX INFO: renamed from: e */
    private boolean f2670e = true;

    /* JADX INFO: renamed from: f */
    private long f2671f;

    RunnableC1981aa(Handler handler, String str, long j) {
        this.f2666a = handler;
        this.f2667b = str;
        this.f2668c = j;
        this.f2669d = j;
    }

    /* JADX INFO: renamed from: a */
    public final void m1698a() {
        if (this.f2670e) {
            this.f2670e = false;
            this.f2671f = SystemClock.uptimeMillis();
            this.f2666a.post(this);
        }
    }

    /* JADX INFO: renamed from: b */
    public final boolean m1700b() {
        return !this.f2670e && SystemClock.uptimeMillis() > this.f2671f + this.f2668c;
    }

    /* JADX INFO: renamed from: c */
    public final int m1701c() {
        if (this.f2670e) {
            return 0;
        }
        return SystemClock.uptimeMillis() - this.f2671f < this.f2668c ? 1 : 3;
    }

    /* JADX INFO: renamed from: d */
    public final String m1702d() {
        return this.f2667b;
    }

    /* JADX INFO: renamed from: e */
    public final Looper m1703e() {
        return this.f2666a.getLooper();
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f2670e = true;
        this.f2668c = this.f2669d;
    }

    /* JADX INFO: renamed from: a */
    public final void m1699a(long j) {
        this.f2668c = LongCompanionObject.MAX_VALUE;
    }
}
