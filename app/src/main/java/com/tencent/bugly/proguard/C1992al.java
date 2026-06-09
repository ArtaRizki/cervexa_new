package com.tencent.bugly.proguard;

import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: renamed from: com.tencent.bugly.proguard.al */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public final class C1992al extends AbstractC2008k implements Cloneable {

    /* JADX INFO: renamed from: b */
    private static ArrayList<C1991ak> f2718b;

    /* JADX INFO: renamed from: a */
    public ArrayList<C1991ak> f2719a = null;

    @Override // com.tencent.bugly.proguard.AbstractC2008k
    /* JADX INFO: renamed from: a */
    public final void mo1718a(StringBuilder sb, int i) {
    }

    @Override // com.tencent.bugly.proguard.AbstractC2008k
    /* JADX INFO: renamed from: a */
    public final void mo1717a(C2007j c2007j) {
        c2007j.m1774a((Collection) this.f2719a, 0);
    }

    @Override // com.tencent.bugly.proguard.AbstractC2008k
    /* JADX INFO: renamed from: a */
    public final void mo1716a(C2006i c2006i) {
        if (f2718b == null) {
            f2718b = new ArrayList<>();
            f2718b.add(new C1991ak());
        }
        this.f2719a = (ArrayList) c2006i.m1757a(f2718b, 0, true);
    }
}
