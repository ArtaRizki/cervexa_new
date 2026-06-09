package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.tencent.bugly.proguard.an */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public final class C1994an extends AbstractC2008k {

    /* JADX INFO: renamed from: i */
    private static byte[] f2746i = {0};

    /* JADX INFO: renamed from: j */
    private static Map<String, String> f2747j;

    /* JADX INFO: renamed from: a */
    public byte f2748a = 0;

    /* JADX INFO: renamed from: b */
    public int f2749b = 0;

    /* JADX INFO: renamed from: c */
    public byte[] f2750c = null;

    /* JADX INFO: renamed from: f */
    private String f2753f = "";

    /* JADX INFO: renamed from: d */
    public long f2751d = 0;

    /* JADX INFO: renamed from: g */
    private String f2754g = "";

    /* JADX INFO: renamed from: e */
    public String f2752e = "";

    /* JADX INFO: renamed from: h */
    private Map<String, String> f2755h = null;

    @Override // com.tencent.bugly.proguard.AbstractC2008k
    /* JADX INFO: renamed from: a */
    public final void mo1717a(C2007j c2007j) {
        c2007j.m1768a(this.f2748a, 0);
        c2007j.m1769a(this.f2749b, 1);
        byte[] bArr = this.f2750c;
        if (bArr != null) {
            c2007j.m1778a(bArr, 2);
        }
        String str = this.f2753f;
        if (str != null) {
            c2007j.m1773a(str, 3);
        }
        c2007j.m1770a(this.f2751d, 4);
        String str2 = this.f2754g;
        if (str2 != null) {
            c2007j.m1773a(str2, 5);
        }
        String str3 = this.f2752e;
        if (str3 != null) {
            c2007j.m1773a(str3, 6);
        }
        Map<String, String> map = this.f2755h;
        if (map != null) {
            c2007j.m1775a((Map) map, 7);
        }
    }

    static {
        HashMap map = new HashMap();
        f2747j = map;
        map.put("", "");
    }

    @Override // com.tencent.bugly.proguard.AbstractC2008k
    /* JADX INFO: renamed from: a */
    public final void mo1716a(C2006i c2006i) {
        this.f2748a = c2006i.m1752a(this.f2748a, 0, true);
        this.f2749b = c2006i.m1753a(this.f2749b, 1, true);
        this.f2750c = c2006i.m1763c(2, false);
        this.f2753f = c2006i.m1762b(3, false);
        this.f2751d = c2006i.m1755a(this.f2751d, 4, false);
        this.f2754g = c2006i.m1762b(5, false);
        this.f2752e = c2006i.m1762b(6, false);
        this.f2755h = (Map) c2006i.m1757a(f2747j, 7, false);
    }
}
