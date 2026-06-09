package com.baidu.p002a.p003a.p004a.p005a.p007b;

import com.baidu.p002a.p003a.p004a.p005a.p007b.C0636c;
import java.util.Comparator;

/* JADX INFO: renamed from: com.baidu.a.a.a.a.b.d */
/* JADX INFO: loaded from: classes.dex */
class C0637d implements Comparator {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ C0636c f96a;

    C0637d(C0636c c0636c) {
        this.f96a = c0636c;
    }

    @Override // java.util.Comparator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(C0636c.a aVar, C0636c.a aVar2) {
        int i = aVar2.f90b - aVar.f90b;
        if (i == 0) {
            if (aVar.f92d && aVar2.f92d) {
                return 0;
            }
            if (aVar.f92d) {
                return -1;
            }
            if (aVar2.f92d) {
                return 1;
            }
        }
        return i;
    }
}
