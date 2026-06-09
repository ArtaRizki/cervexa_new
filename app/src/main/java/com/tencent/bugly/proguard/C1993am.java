package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.tencent.bugly.proguard.am */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public final class C1993am extends AbstractC2008k {

    /* JADX INFO: renamed from: y */
    private static byte[] f2720y = {0};

    /* JADX INFO: renamed from: z */
    private static Map<String, String> f2721z;

    /* JADX INFO: renamed from: a */
    public int f2722a = 0;

    /* JADX INFO: renamed from: b */
    public String f2723b = "";

    /* JADX INFO: renamed from: c */
    public String f2724c = "";

    /* JADX INFO: renamed from: d */
    public String f2725d = "";

    /* JADX INFO: renamed from: e */
    public String f2726e = "";

    /* JADX INFO: renamed from: f */
    public String f2727f = "";

    /* JADX INFO: renamed from: g */
    public int f2728g = 0;

    /* JADX INFO: renamed from: h */
    public byte[] f2729h = null;

    /* JADX INFO: renamed from: i */
    public String f2730i = "";

    /* JADX INFO: renamed from: j */
    public String f2731j = "";

    /* JADX INFO: renamed from: k */
    public Map<String, String> f2732k = null;

    /* JADX INFO: renamed from: l */
    public String f2733l = "";

    /* JADX INFO: renamed from: m */
    public long f2734m = 0;

    /* JADX INFO: renamed from: n */
    public String f2735n = "";

    /* JADX INFO: renamed from: o */
    public String f2736o = "";

    /* JADX INFO: renamed from: p */
    public String f2737p = "";

    /* JADX INFO: renamed from: q */
    public long f2738q = 0;

    /* JADX INFO: renamed from: u */
    private String f2742u = "";

    /* JADX INFO: renamed from: r */
    public String f2739r = "";

    /* JADX INFO: renamed from: v */
    private String f2743v = "";

    /* JADX INFO: renamed from: w */
    private String f2744w = "";

    /* JADX INFO: renamed from: s */
    public String f2740s = "";

    /* JADX INFO: renamed from: t */
    public String f2741t = "";

    /* JADX INFO: renamed from: x */
    private String f2745x = "";

    @Override // com.tencent.bugly.proguard.AbstractC2008k
    /* JADX INFO: renamed from: a */
    public final void mo1717a(C2007j c2007j) {
        c2007j.m1769a(this.f2722a, 0);
        c2007j.m1773a(this.f2723b, 1);
        c2007j.m1773a(this.f2724c, 2);
        c2007j.m1773a(this.f2725d, 3);
        String str = this.f2726e;
        if (str != null) {
            c2007j.m1773a(str, 4);
        }
        c2007j.m1773a(this.f2727f, 5);
        c2007j.m1769a(this.f2728g, 6);
        c2007j.m1778a(this.f2729h, 7);
        String str2 = this.f2730i;
        if (str2 != null) {
            c2007j.m1773a(str2, 8);
        }
        String str3 = this.f2731j;
        if (str3 != null) {
            c2007j.m1773a(str3, 9);
        }
        Map<String, String> map = this.f2732k;
        if (map != null) {
            c2007j.m1775a((Map) map, 10);
        }
        String str4 = this.f2733l;
        if (str4 != null) {
            c2007j.m1773a(str4, 11);
        }
        c2007j.m1770a(this.f2734m, 12);
        String str5 = this.f2735n;
        if (str5 != null) {
            c2007j.m1773a(str5, 13);
        }
        String str6 = this.f2736o;
        if (str6 != null) {
            c2007j.m1773a(str6, 14);
        }
        String str7 = this.f2737p;
        if (str7 != null) {
            c2007j.m1773a(str7, 15);
        }
        c2007j.m1770a(this.f2738q, 16);
        String str8 = this.f2742u;
        if (str8 != null) {
            c2007j.m1773a(str8, 17);
        }
        String str9 = this.f2739r;
        if (str9 != null) {
            c2007j.m1773a(str9, 18);
        }
        String str10 = this.f2743v;
        if (str10 != null) {
            c2007j.m1773a(str10, 19);
        }
        String str11 = this.f2744w;
        if (str11 != null) {
            c2007j.m1773a(str11, 20);
        }
        String str12 = this.f2740s;
        if (str12 != null) {
            c2007j.m1773a(str12, 21);
        }
        String str13 = this.f2741t;
        if (str13 != null) {
            c2007j.m1773a(str13, 22);
        }
        String str14 = this.f2745x;
        if (str14 != null) {
            c2007j.m1773a(str14, 23);
        }
    }

    static {
        HashMap map = new HashMap();
        f2721z = map;
        map.put("", "");
    }

    @Override // com.tencent.bugly.proguard.AbstractC2008k
    /* JADX INFO: renamed from: a */
    public final void mo1716a(C2006i c2006i) {
        this.f2722a = c2006i.m1753a(this.f2722a, 0, true);
        this.f2723b = c2006i.m1762b(1, true);
        this.f2724c = c2006i.m1762b(2, true);
        this.f2725d = c2006i.m1762b(3, true);
        this.f2726e = c2006i.m1762b(4, false);
        this.f2727f = c2006i.m1762b(5, true);
        this.f2728g = c2006i.m1753a(this.f2728g, 6, true);
        this.f2729h = c2006i.m1763c(7, true);
        this.f2730i = c2006i.m1762b(8, false);
        this.f2731j = c2006i.m1762b(9, false);
        this.f2732k = (Map) c2006i.m1757a(f2721z, 10, false);
        this.f2733l = c2006i.m1762b(11, false);
        this.f2734m = c2006i.m1755a(this.f2734m, 12, false);
        this.f2735n = c2006i.m1762b(13, false);
        this.f2736o = c2006i.m1762b(14, false);
        this.f2737p = c2006i.m1762b(15, false);
        this.f2738q = c2006i.m1755a(this.f2738q, 16, false);
        this.f2742u = c2006i.m1762b(17, false);
        this.f2739r = c2006i.m1762b(18, false);
        this.f2743v = c2006i.m1762b(19, false);
        this.f2744w = c2006i.m1762b(20, false);
        this.f2740s = c2006i.m1762b(21, false);
        this.f2741t = c2006i.m1762b(22, false);
        this.f2745x = c2006i.m1762b(23, false);
    }
}
