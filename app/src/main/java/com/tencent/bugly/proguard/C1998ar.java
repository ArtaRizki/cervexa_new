package com.tencent.bugly.proguard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.tencent.bugly.proguard.ar */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public final class C1998ar extends AbstractC2008k implements Cloneable {

    /* JADX INFO: renamed from: f */
    private static ArrayList<C1997aq> f2782f;

    /* JADX INFO: renamed from: g */
    private static Map<String, String> f2783g;

    /* JADX INFO: renamed from: a */
    public byte f2784a = 0;

    /* JADX INFO: renamed from: b */
    public String f2785b = "";

    /* JADX INFO: renamed from: c */
    public String f2786c = "";

    /* JADX INFO: renamed from: d */
    public ArrayList<C1997aq> f2787d = null;

    /* JADX INFO: renamed from: e */
    public Map<String, String> f2788e = null;

    @Override // com.tencent.bugly.proguard.AbstractC2008k
    /* JADX INFO: renamed from: a */
    public final void mo1718a(StringBuilder sb, int i) {
    }

    @Override // com.tencent.bugly.proguard.AbstractC2008k
    /* JADX INFO: renamed from: a */
    public final void mo1717a(C2007j c2007j) {
        c2007j.m1768a(this.f2784a, 0);
        String str = this.f2785b;
        if (str != null) {
            c2007j.m1773a(str, 1);
        }
        String str2 = this.f2786c;
        if (str2 != null) {
            c2007j.m1773a(str2, 2);
        }
        ArrayList<C1997aq> arrayList = this.f2787d;
        if (arrayList != null) {
            c2007j.m1774a((Collection) arrayList, 3);
        }
        Map<String, String> map = this.f2788e;
        if (map != null) {
            c2007j.m1775a((Map) map, 4);
        }
    }

    @Override // com.tencent.bugly.proguard.AbstractC2008k
    /* JADX INFO: renamed from: a */
    public final void mo1716a(C2006i c2006i) {
        this.f2784a = c2006i.m1752a(this.f2784a, 0, true);
        this.f2785b = c2006i.m1762b(1, false);
        this.f2786c = c2006i.m1762b(2, false);
        if (f2782f == null) {
            f2782f = new ArrayList<>();
            f2782f.add(new C1997aq());
        }
        this.f2787d = (ArrayList) c2006i.m1757a(f2782f, 3, false);
        if (f2783g == null) {
            HashMap map = new HashMap();
            f2783g = map;
            map.put("", "");
        }
        this.f2788e = (Map) c2006i.m1757a(f2783g, 4, false);
    }
}
