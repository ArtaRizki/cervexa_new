package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.tencent.bugly.proguard.f */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public final class C2003f extends AbstractC2008k {

    /* JADX INFO: renamed from: e */
    public byte[] f2803e;

    /* JADX INFO: renamed from: i */
    private Map<String, String> f2807i;

    /* JADX INFO: renamed from: j */
    private Map<String, String> f2808j;

    /* JADX INFO: renamed from: m */
    private static /* synthetic */ boolean f2798m = !C2003f.class.desiredAssertionStatus();

    /* JADX INFO: renamed from: k */
    private static byte[] f2796k = null;

    /* JADX INFO: renamed from: l */
    private static Map<String, String> f2797l = null;

    /* JADX INFO: renamed from: a */
    public short f2799a = 0;

    /* JADX INFO: renamed from: f */
    private byte f2804f = 0;

    /* JADX INFO: renamed from: g */
    private int f2805g = 0;

    /* JADX INFO: renamed from: b */
    public int f2800b = 0;

    /* JADX INFO: renamed from: c */
    public String f2801c = null;

    /* JADX INFO: renamed from: d */
    public String f2802d = null;

    /* JADX INFO: renamed from: h */
    private int f2806h = 0;

    public final boolean equals(Object obj) {
        C2003f c2003f = (C2003f) obj;
        return C2009l.m1780a(1, (int) c2003f.f2799a) && C2009l.m1780a(1, (int) c2003f.f2804f) && C2009l.m1780a(1, c2003f.f2805g) && C2009l.m1780a(1, c2003f.f2800b) && C2009l.m1782a((Object) 1, (Object) c2003f.f2801c) && C2009l.m1782a((Object) 1, (Object) c2003f.f2802d) && C2009l.m1782a((Object) 1, (Object) c2003f.f2803e) && C2009l.m1780a(1, c2003f.f2806h) && C2009l.m1782a((Object) 1, (Object) c2003f.f2807i) && C2009l.m1782a((Object) 1, (Object) c2003f.f2808j);
    }

    public final Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            if (f2798m) {
                return null;
            }
            throw new AssertionError();
        }
    }

    @Override // com.tencent.bugly.proguard.AbstractC2008k
    /* JADX INFO: renamed from: a */
    public final void mo1717a(C2007j c2007j) {
        c2007j.m1776a(this.f2799a, 1);
        c2007j.m1768a(this.f2804f, 2);
        c2007j.m1769a(this.f2805g, 3);
        c2007j.m1769a(this.f2800b, 4);
        c2007j.m1773a(this.f2801c, 5);
        c2007j.m1773a(this.f2802d, 6);
        c2007j.m1778a(this.f2803e, 7);
        c2007j.m1769a(this.f2806h, 8);
        c2007j.m1775a((Map) this.f2807i, 9);
        c2007j.m1775a((Map) this.f2808j, 10);
    }

    @Override // com.tencent.bugly.proguard.AbstractC2008k
    /* JADX INFO: renamed from: a */
    public final void mo1716a(C2006i c2006i) {
        try {
            this.f2799a = c2006i.m1759a(this.f2799a, 1, true);
            this.f2804f = c2006i.m1752a(this.f2804f, 2, true);
            this.f2805g = c2006i.m1753a(this.f2805g, 3, true);
            this.f2800b = c2006i.m1753a(this.f2800b, 4, true);
            this.f2801c = c2006i.m1762b(5, true);
            this.f2802d = c2006i.m1762b(6, true);
            if (f2796k == null) {
                f2796k = new byte[]{0};
            }
            this.f2803e = c2006i.m1763c(7, true);
            this.f2806h = c2006i.m1753a(this.f2806h, 8, true);
            if (f2797l == null) {
                HashMap map = new HashMap();
                f2797l = map;
                map.put("", "");
            }
            this.f2807i = (Map) c2006i.m1757a(f2797l, 9, true);
            if (f2797l == null) {
                HashMap map2 = new HashMap();
                f2797l = map2;
                map2.put("", "");
            }
            this.f2808j = (Map) c2006i.m1757a(f2797l, 10, true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("RequestPacket decode error " + C2002e.m1724a(this.f2803e));
            throw new RuntimeException(e);
        }
    }

    @Override // com.tencent.bugly.proguard.AbstractC2008k
    /* JADX INFO: renamed from: a */
    public final void mo1718a(StringBuilder sb, int i) {
        C2005h c2005h = new C2005h(sb, i);
        c2005h.m1734a(this.f2799a, "iVersion");
        c2005h.m1728a(this.f2804f, "cPacketType");
        c2005h.m1729a(this.f2805g, "iMessageType");
        c2005h.m1729a(this.f2800b, "iRequestId");
        c2005h.m1732a(this.f2801c, "sServantName");
        c2005h.m1732a(this.f2802d, "sFuncName");
        c2005h.m1736a(this.f2803e, "sBuffer");
        c2005h.m1729a(this.f2806h, "iTimeout");
        c2005h.m1733a((Map) this.f2807i, "context");
        c2005h.m1733a((Map) this.f2808j, "status");
    }
}
