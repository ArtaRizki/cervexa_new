package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.tencent.bugly.proguard.ap */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public final class C1996ap extends AbstractC2008k implements Cloneable {

    /* JADX INFO: renamed from: n */
    private static Map<String, String> f2759n;

    /* JADX INFO: renamed from: o */
    private static /* synthetic */ boolean f2760o = !C1996ap.class.desiredAssertionStatus();

    /* JADX INFO: renamed from: m */
    private static C1995ao f2758m = new C1995ao();

    /* JADX INFO: renamed from: a */
    public boolean f2761a = true;

    /* JADX INFO: renamed from: b */
    public boolean f2762b = true;

    /* JADX INFO: renamed from: c */
    public boolean f2763c = true;

    /* JADX INFO: renamed from: d */
    public String f2764d = "";

    /* JADX INFO: renamed from: e */
    public String f2765e = "";

    /* JADX INFO: renamed from: f */
    public C1995ao f2766f = null;

    /* JADX INFO: renamed from: g */
    public Map<String, String> f2767g = null;

    /* JADX INFO: renamed from: h */
    public long f2768h = 0;

    /* JADX INFO: renamed from: j */
    private String f2770j = "";

    /* JADX INFO: renamed from: k */
    private String f2771k = "";

    /* JADX INFO: renamed from: l */
    private int f2772l = 0;

    /* JADX INFO: renamed from: i */
    public int f2769i = 0;

    static {
        HashMap map = new HashMap();
        f2759n = map;
        map.put("", "");
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        C1996ap c1996ap = (C1996ap) obj;
        return C2009l.m1783a(this.f2761a, c1996ap.f2761a) && C2009l.m1783a(this.f2762b, c1996ap.f2762b) && C2009l.m1783a(this.f2763c, c1996ap.f2763c) && C2009l.m1782a(this.f2764d, c1996ap.f2764d) && C2009l.m1782a(this.f2765e, c1996ap.f2765e) && C2009l.m1782a(this.f2766f, c1996ap.f2766f) && C2009l.m1782a(this.f2767g, c1996ap.f2767g) && C2009l.m1781a(this.f2768h, c1996ap.f2768h) && C2009l.m1782a(this.f2770j, c1996ap.f2770j) && C2009l.m1782a(this.f2771k, c1996ap.f2771k) && C2009l.m1780a(this.f2772l, c1996ap.f2772l) && C2009l.m1780a(this.f2769i, c1996ap.f2769i);
    }

    public final int hashCode() {
        try {
            throw new Exception("Need define key first!");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public final Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            if (f2760o) {
                return null;
            }
            throw new AssertionError();
        }
    }

    @Override // com.tencent.bugly.proguard.AbstractC2008k
    /* JADX INFO: renamed from: a */
    public final void mo1717a(C2007j c2007j) {
        c2007j.m1777a(this.f2761a, 0);
        c2007j.m1777a(this.f2762b, 1);
        c2007j.m1777a(this.f2763c, 2);
        String str = this.f2764d;
        if (str != null) {
            c2007j.m1773a(str, 3);
        }
        String str2 = this.f2765e;
        if (str2 != null) {
            c2007j.m1773a(str2, 4);
        }
        C1995ao c1995ao = this.f2766f;
        if (c1995ao != null) {
            c2007j.m1771a((AbstractC2008k) c1995ao, 5);
        }
        Map<String, String> map = this.f2767g;
        if (map != null) {
            c2007j.m1775a((Map) map, 6);
        }
        c2007j.m1770a(this.f2768h, 7);
        String str3 = this.f2770j;
        if (str3 != null) {
            c2007j.m1773a(str3, 8);
        }
        String str4 = this.f2771k;
        if (str4 != null) {
            c2007j.m1773a(str4, 9);
        }
        c2007j.m1769a(this.f2772l, 10);
        c2007j.m1769a(this.f2769i, 11);
    }

    @Override // com.tencent.bugly.proguard.AbstractC2008k
    /* JADX INFO: renamed from: a */
    public final void mo1716a(C2006i c2006i) {
        this.f2761a = c2006i.m1761a(0, true);
        this.f2762b = c2006i.m1761a(1, true);
        this.f2763c = c2006i.m1761a(2, true);
        this.f2764d = c2006i.m1762b(3, false);
        this.f2765e = c2006i.m1762b(4, false);
        this.f2766f = (C1995ao) c2006i.m1756a((AbstractC2008k) f2758m, 5, false);
        this.f2767g = (Map) c2006i.m1757a(f2759n, 6, false);
        this.f2768h = c2006i.m1755a(this.f2768h, 7, false);
        this.f2770j = c2006i.m1762b(8, false);
        this.f2771k = c2006i.m1762b(9, false);
        this.f2772l = c2006i.m1753a(this.f2772l, 10, false);
        this.f2769i = c2006i.m1753a(this.f2769i, 11, false);
    }

    @Override // com.tencent.bugly.proguard.AbstractC2008k
    /* JADX INFO: renamed from: a */
    public final void mo1718a(StringBuilder sb, int i) {
        C2005h c2005h = new C2005h(sb, i);
        c2005h.m1735a(this.f2761a, "enable");
        c2005h.m1735a(this.f2762b, "enableUserInfo");
        c2005h.m1735a(this.f2763c, "enableQuery");
        c2005h.m1732a(this.f2764d, "url");
        c2005h.m1732a(this.f2765e, "expUrl");
        c2005h.m1731a((AbstractC2008k) this.f2766f, "security");
        c2005h.m1733a((Map) this.f2767g, "valueMap");
        c2005h.m1730a(this.f2768h, "strategylastUpdateTime");
        c2005h.m1732a(this.f2770j, "httpsUrl");
        c2005h.m1732a(this.f2771k, "httpsExpUrl");
        c2005h.m1729a(this.f2772l, "eventRecordCount");
        c2005h.m1729a(this.f2769i, "eventTimeInterval");
    }
}
