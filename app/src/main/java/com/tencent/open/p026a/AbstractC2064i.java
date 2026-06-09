package com.tencent.open.p026a;

import com.tencent.open.p026a.C2059d;

/* JADX INFO: renamed from: com.tencent.open.a.i */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC2064i {

    /* JADX INFO: renamed from: a */
    private volatile int f3180a;

    /* JADX INFO: renamed from: b */
    private volatile boolean f3181b;

    /* JADX INFO: renamed from: c */
    private C2063h f3182c;

    /* JADX INFO: renamed from: a */
    protected abstract void mo2092a(int i, Thread thread, long j, String str, String str2, Throwable th);

    public AbstractC2064i() {
        this(C2058c.f3155a, true, C2063h.f3179a);
    }

    public AbstractC2064i(int i, boolean z, C2063h c2063h) {
        this.f3180a = C2058c.f3155a;
        this.f3181b = true;
        this.f3182c = C2063h.f3179a;
        m2144a(i);
        m2146a(z);
        m2145a(c2063h);
    }

    /* JADX INFO: renamed from: b */
    public void m2147b(int i, Thread thread, long j, String str, String str2, Throwable th) {
        if (m2148d() && C2059d.a.m2115a(this.f3180a, i)) {
            mo2092a(i, thread, j, str, str2, th);
        }
    }

    /* JADX INFO: renamed from: a */
    public void m2144a(int i) {
        this.f3180a = i;
    }

    /* JADX INFO: renamed from: d */
    public boolean m2148d() {
        return this.f3181b;
    }

    /* JADX INFO: renamed from: a */
    public void m2146a(boolean z) {
        this.f3181b = z;
    }

    /* JADX INFO: renamed from: e */
    public C2063h m2149e() {
        return this.f3182c;
    }

    /* JADX INFO: renamed from: a */
    public void m2145a(C2063h c2063h) {
        this.f3182c = c2063h;
    }
}
