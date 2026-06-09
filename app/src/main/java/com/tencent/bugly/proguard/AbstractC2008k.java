package com.tencent.bugly.proguard;

import java.io.Serializable;

/* JADX INFO: renamed from: com.tencent.bugly.proguard.k */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC2008k implements Serializable {
    /* JADX INFO: renamed from: a */
    public abstract void mo1716a(C2006i c2006i);

    /* JADX INFO: renamed from: a */
    public abstract void mo1717a(C2007j c2007j);

    /* JADX INFO: renamed from: a */
    public abstract void mo1718a(StringBuilder sb, int i);

    public String toString() {
        StringBuilder sb = new StringBuilder();
        mo1718a(sb, 0);
        return sb.toString();
    }
}
