package com.tencent.p023mm.opensdk.diffdev.p025a;

import com.tencent.p023mm.opensdk.diffdev.OAuthListener;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: renamed from: com.tencent.mm.opensdk.diffdev.a.c */
/* JADX INFO: loaded from: classes2.dex */
final class RunnableC2044c implements Runnable {

    /* JADX INFO: renamed from: g */
    final /* synthetic */ C2043b f3072g;

    RunnableC2044c(C2043b c2043b) {
        this.f3072g = c2043b;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f3072g.f3071f.f3068c);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((OAuthListener) it.next()).onQrcodeScanned();
        }
    }
}
