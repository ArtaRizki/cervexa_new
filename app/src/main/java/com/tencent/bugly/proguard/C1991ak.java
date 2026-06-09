package com.tencent.bugly.proguard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.tencent.bugly.proguard.ak */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public final class C1991ak extends AbstractC2008k {

    /* JADX INFO: renamed from: A */
    private static ArrayList<C1990aj> f2689A;

    /* JADX INFO: renamed from: B */
    private static Map<String, String> f2690B;

    /* JADX INFO: renamed from: C */
    private static Map<String, String> f2691C;

    /* JADX INFO: renamed from: v */
    private static Map<String, String> f2692v;

    /* JADX INFO: renamed from: w */
    private static C1989ai f2693w;

    /* JADX INFO: renamed from: x */
    private static C1988ah f2694x;

    /* JADX INFO: renamed from: y */
    private static ArrayList<C1988ah> f2695y;

    /* JADX INFO: renamed from: z */
    private static ArrayList<C1988ah> f2696z;

    /* JADX INFO: renamed from: a */
    public String f2697a = "";

    /* JADX INFO: renamed from: b */
    public long f2698b = 0;

    /* JADX INFO: renamed from: c */
    public String f2699c = "";

    /* JADX INFO: renamed from: d */
    public String f2700d = "";

    /* JADX INFO: renamed from: e */
    public String f2701e = "";

    /* JADX INFO: renamed from: f */
    public String f2702f = "";

    /* JADX INFO: renamed from: g */
    public String f2703g = "";

    /* JADX INFO: renamed from: h */
    public Map<String, String> f2704h = null;

    /* JADX INFO: renamed from: i */
    public String f2705i = "";

    /* JADX INFO: renamed from: j */
    public C1989ai f2706j = null;

    /* JADX INFO: renamed from: k */
    public int f2707k = 0;

    /* JADX INFO: renamed from: l */
    public String f2708l = "";

    /* JADX INFO: renamed from: m */
    public String f2709m = "";

    /* JADX INFO: renamed from: n */
    public C1988ah f2710n = null;

    /* JADX INFO: renamed from: o */
    public ArrayList<C1988ah> f2711o = null;

    /* JADX INFO: renamed from: p */
    public ArrayList<C1988ah> f2712p = null;

    /* JADX INFO: renamed from: q */
    public ArrayList<C1990aj> f2713q = null;

    /* JADX INFO: renamed from: r */
    public Map<String, String> f2714r = null;

    /* JADX INFO: renamed from: s */
    public Map<String, String> f2715s = null;

    /* JADX INFO: renamed from: t */
    private String f2716t = "";

    /* JADX INFO: renamed from: u */
    private boolean f2717u = true;

    @Override // com.tencent.bugly.proguard.AbstractC2008k
    /* JADX INFO: renamed from: a */
    public final void mo1717a(C2007j c2007j) {
        c2007j.m1773a(this.f2697a, 0);
        c2007j.m1770a(this.f2698b, 1);
        c2007j.m1773a(this.f2699c, 2);
        String str = this.f2700d;
        if (str != null) {
            c2007j.m1773a(str, 3);
        }
        String str2 = this.f2701e;
        if (str2 != null) {
            c2007j.m1773a(str2, 4);
        }
        String str3 = this.f2702f;
        if (str3 != null) {
            c2007j.m1773a(str3, 5);
        }
        String str4 = this.f2703g;
        if (str4 != null) {
            c2007j.m1773a(str4, 6);
        }
        Map<String, String> map = this.f2704h;
        if (map != null) {
            c2007j.m1775a((Map) map, 7);
        }
        String str5 = this.f2705i;
        if (str5 != null) {
            c2007j.m1773a(str5, 8);
        }
        C1989ai c1989ai = this.f2706j;
        if (c1989ai != null) {
            c2007j.m1771a((AbstractC2008k) c1989ai, 9);
        }
        c2007j.m1769a(this.f2707k, 10);
        String str6 = this.f2708l;
        if (str6 != null) {
            c2007j.m1773a(str6, 11);
        }
        String str7 = this.f2709m;
        if (str7 != null) {
            c2007j.m1773a(str7, 12);
        }
        C1988ah c1988ah = this.f2710n;
        if (c1988ah != null) {
            c2007j.m1771a((AbstractC2008k) c1988ah, 13);
        }
        ArrayList<C1988ah> arrayList = this.f2711o;
        if (arrayList != null) {
            c2007j.m1774a((Collection) arrayList, 14);
        }
        ArrayList<C1988ah> arrayList2 = this.f2712p;
        if (arrayList2 != null) {
            c2007j.m1774a((Collection) arrayList2, 15);
        }
        ArrayList<C1990aj> arrayList3 = this.f2713q;
        if (arrayList3 != null) {
            c2007j.m1774a((Collection) arrayList3, 16);
        }
        Map<String, String> map2 = this.f2714r;
        if (map2 != null) {
            c2007j.m1775a((Map) map2, 17);
        }
        Map<String, String> map3 = this.f2715s;
        if (map3 != null) {
            c2007j.m1775a((Map) map3, 18);
        }
        String str8 = this.f2716t;
        if (str8 != null) {
            c2007j.m1773a(str8, 19);
        }
        c2007j.m1777a(this.f2717u, 20);
    }

    static {
        HashMap map = new HashMap();
        f2692v = map;
        map.put("", "");
        f2693w = new C1989ai();
        f2694x = new C1988ah();
        f2695y = new ArrayList<>();
        f2695y.add(new C1988ah());
        f2696z = new ArrayList<>();
        f2696z.add(new C1988ah());
        f2689A = new ArrayList<>();
        f2689A.add(new C1990aj());
        HashMap map2 = new HashMap();
        f2690B = map2;
        map2.put("", "");
        HashMap map3 = new HashMap();
        f2691C = map3;
        map3.put("", "");
    }

    @Override // com.tencent.bugly.proguard.AbstractC2008k
    /* JADX INFO: renamed from: a */
    public final void mo1716a(C2006i c2006i) {
        this.f2697a = c2006i.m1762b(0, true);
        this.f2698b = c2006i.m1755a(this.f2698b, 1, true);
        this.f2699c = c2006i.m1762b(2, true);
        this.f2700d = c2006i.m1762b(3, false);
        this.f2701e = c2006i.m1762b(4, false);
        this.f2702f = c2006i.m1762b(5, false);
        this.f2703g = c2006i.m1762b(6, false);
        this.f2704h = (Map) c2006i.m1757a(f2692v, 7, false);
        this.f2705i = c2006i.m1762b(8, false);
        this.f2706j = (C1989ai) c2006i.m1756a((AbstractC2008k) f2693w, 9, false);
        this.f2707k = c2006i.m1753a(this.f2707k, 10, false);
        this.f2708l = c2006i.m1762b(11, false);
        this.f2709m = c2006i.m1762b(12, false);
        this.f2710n = (C1988ah) c2006i.m1756a((AbstractC2008k) f2694x, 13, false);
        this.f2711o = (ArrayList) c2006i.m1757a(f2695y, 14, false);
        this.f2712p = (ArrayList) c2006i.m1757a(f2696z, 15, false);
        this.f2713q = (ArrayList) c2006i.m1757a(f2689A, 16, false);
        this.f2714r = (Map) c2006i.m1757a(f2690B, 17, false);
        this.f2715s = (Map) c2006i.m1757a(f2691C, 18, false);
        this.f2716t = c2006i.m1762b(19, false);
        this.f2717u = c2006i.m1761a(20, false);
    }
}
