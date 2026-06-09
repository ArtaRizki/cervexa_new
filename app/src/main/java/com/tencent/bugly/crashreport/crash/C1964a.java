package com.tencent.bugly.crashreport.crash;

/* JADX INFO: renamed from: com.tencent.bugly.crashreport.crash.a */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public final class C1964a implements Comparable<C1964a> {

    /* JADX INFO: renamed from: a */
    public long f2514a = -1;

    /* JADX INFO: renamed from: b */
    public long f2515b = -1;

    /* JADX INFO: renamed from: c */
    public String f2516c = null;

    /* JADX INFO: renamed from: d */
    public boolean f2517d = false;

    /* JADX INFO: renamed from: e */
    public boolean f2518e = false;

    /* JADX INFO: renamed from: f */
    public int f2519f = 0;

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(C1964a c1964a) {
        C1964a c1964a2 = c1964a;
        if (c1964a2 == null) {
            return 1;
        }
        long j = this.f2515b - c1964a2.f2515b;
        if (j <= 0) {
            return j < 0 ? -1 : 0;
        }
        return 1;
    }
}
