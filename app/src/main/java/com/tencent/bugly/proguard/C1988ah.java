package com.tencent.bugly.proguard;

/* JADX INFO: renamed from: com.tencent.bugly.proguard.ah */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public final class C1988ah extends AbstractC2008k implements Cloneable {

    /* JADX INFO: renamed from: a */
    public String f2677a = "";

    /* JADX INFO: renamed from: d */
    private String f2680d = "";

    /* JADX INFO: renamed from: b */
    public String f2678b = "";

    /* JADX INFO: renamed from: e */
    private String f2681e = "";

    /* JADX INFO: renamed from: c */
    public String f2679c = "";

    @Override // com.tencent.bugly.proguard.AbstractC2008k
    /* JADX INFO: renamed from: a */
    public final void mo1718a(StringBuilder sb, int i) {
    }

    @Override // com.tencent.bugly.proguard.AbstractC2008k
    /* JADX INFO: renamed from: a */
    public final void mo1717a(C2007j c2007j) {
        c2007j.m1773a(this.f2677a, 0);
        String str = this.f2680d;
        if (str != null) {
            c2007j.m1773a(str, 1);
        }
        String str2 = this.f2678b;
        if (str2 != null) {
            c2007j.m1773a(str2, 2);
        }
        String str3 = this.f2681e;
        if (str3 != null) {
            c2007j.m1773a(str3, 3);
        }
        String str4 = this.f2679c;
        if (str4 != null) {
            c2007j.m1773a(str4, 4);
        }
    }

    @Override // com.tencent.bugly.proguard.AbstractC2008k
    /* JADX INFO: renamed from: a */
    public final void mo1716a(C2006i c2006i) {
        this.f2677a = c2006i.m1762b(0, true);
        this.f2680d = c2006i.m1762b(1, false);
        this.f2678b = c2006i.m1762b(2, false);
        this.f2681e = c2006i.m1762b(3, false);
        this.f2679c = c2006i.m1762b(4, false);
    }
}
