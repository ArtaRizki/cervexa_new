package com.tencent.bugly.proguard;

import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: renamed from: com.tencent.bugly.proguard.ai */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public final class C1989ai extends AbstractC2008k implements Cloneable {

    /* JADX INFO: renamed from: c */
    private static ArrayList<String> f2682c;

    /* JADX INFO: renamed from: a */
    private String f2683a = "";

    /* JADX INFO: renamed from: b */
    private ArrayList<String> f2684b = null;

    @Override // com.tencent.bugly.proguard.AbstractC2008k
    /* JADX INFO: renamed from: a */
    public final void mo1718a(StringBuilder sb, int i) {
    }

    @Override // com.tencent.bugly.proguard.AbstractC2008k
    /* JADX INFO: renamed from: a */
    public final void mo1717a(C2007j c2007j) {
        c2007j.m1773a(this.f2683a, 0);
        ArrayList<String> arrayList = this.f2684b;
        if (arrayList != null) {
            c2007j.m1774a((Collection) arrayList, 1);
        }
    }

    @Override // com.tencent.bugly.proguard.AbstractC2008k
    /* JADX INFO: renamed from: a */
    public final void mo1716a(C2006i c2006i) {
        this.f2683a = c2006i.m1762b(0, true);
        if (f2682c == null) {
            ArrayList<String> arrayList = new ArrayList<>();
            f2682c = arrayList;
            arrayList.add("");
        }
        this.f2684b = (ArrayList) c2006i.m1757a(f2682c, 1, false);
    }
}
