package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.tencent.bugly.proguard.aq */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public final class C1997aq extends AbstractC2008k {

    /* JADX INFO: renamed from: i */
    private static Map<String, String> f2773i;

    /* JADX INFO: renamed from: a */
    public long f2774a = 0;

    /* JADX INFO: renamed from: b */
    public byte f2775b = 0;

    /* JADX INFO: renamed from: c */
    public String f2776c = "";

    /* JADX INFO: renamed from: d */
    public String f2777d = "";

    /* JADX INFO: renamed from: e */
    public String f2778e = "";

    /* JADX INFO: renamed from: f */
    public Map<String, String> f2779f = null;

    /* JADX INFO: renamed from: h */
    private String f2781h = "";

    /* JADX INFO: renamed from: g */
    public boolean f2780g = true;

    @Override // com.tencent.bugly.proguard.AbstractC2008k
    /* JADX INFO: renamed from: a */
    public final void mo1717a(C2007j c2007j) {
        c2007j.m1770a(this.f2774a, 0);
        c2007j.m1768a(this.f2775b, 1);
        String str = this.f2776c;
        if (str != null) {
            c2007j.m1773a(str, 2);
        }
        String str2 = this.f2777d;
        if (str2 != null) {
            c2007j.m1773a(str2, 3);
        }
        String str3 = this.f2778e;
        if (str3 != null) {
            c2007j.m1773a(str3, 4);
        }
        Map<String, String> map = this.f2779f;
        if (map != null) {
            c2007j.m1775a((Map) map, 5);
        }
        String str4 = this.f2781h;
        if (str4 != null) {
            c2007j.m1773a(str4, 6);
        }
        c2007j.m1777a(this.f2780g, 7);
    }

    static {
        HashMap map = new HashMap();
        f2773i = map;
        map.put("", "");
    }

    @Override // com.tencent.bugly.proguard.AbstractC2008k
    /* JADX INFO: renamed from: a */
    public final void mo1716a(C2006i c2006i) {
        this.f2774a = c2006i.m1755a(this.f2774a, 0, true);
        this.f2775b = c2006i.m1752a(this.f2775b, 1, true);
        this.f2776c = c2006i.m1762b(2, false);
        this.f2777d = c2006i.m1762b(3, false);
        this.f2778e = c2006i.m1762b(4, false);
        this.f2779f = (Map) c2006i.m1757a(f2773i, 5, false);
        this.f2781h = c2006i.m1762b(6, false);
        this.f2780g = c2006i.m1761a(7, false);
    }
}
