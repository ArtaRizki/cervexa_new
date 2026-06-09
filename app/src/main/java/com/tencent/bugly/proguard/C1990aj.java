package com.tencent.bugly.proguard;

/* JADX INFO: renamed from: com.tencent.bugly.proguard.aj */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public final class C1990aj extends AbstractC2008k implements Cloneable {

    /* JADX INFO: renamed from: d */
    private static byte[] f2685d;

    /* JADX INFO: renamed from: a */
    private byte f2686a;

    /* JADX INFO: renamed from: b */
    private String f2687b;

    /* JADX INFO: renamed from: c */
    private byte[] f2688c;

    @Override // com.tencent.bugly.proguard.AbstractC2008k
    /* JADX INFO: renamed from: a */
    public final void mo1718a(StringBuilder sb, int i) {
    }

    public C1990aj() {
        this.f2686a = (byte) 0;
        this.f2687b = "";
        this.f2688c = null;
    }

    public C1990aj(byte b, String str, byte[] bArr) {
        this.f2686a = (byte) 0;
        this.f2687b = "";
        this.f2688c = null;
        this.f2686a = b;
        this.f2687b = str;
        this.f2688c = bArr;
    }

    @Override // com.tencent.bugly.proguard.AbstractC2008k
    /* JADX INFO: renamed from: a */
    public final void mo1717a(C2007j c2007j) {
        c2007j.m1768a(this.f2686a, 0);
        c2007j.m1773a(this.f2687b, 1);
        byte[] bArr = this.f2688c;
        if (bArr != null) {
            c2007j.m1778a(bArr, 2);
        }
    }

    @Override // com.tencent.bugly.proguard.AbstractC2008k
    /* JADX INFO: renamed from: a */
    public final void mo1716a(C2006i c2006i) {
        this.f2686a = c2006i.m1752a(this.f2686a, 0, true);
        this.f2687b = c2006i.m1762b(1, true);
        if (f2685d == null) {
            f2685d = new byte[]{0};
        }
        this.f2688c = c2006i.m1763c(2, false);
    }
}
