package com.baidu.android.bbalbs.common.util;

import com.baidu.android.bbalbs.common.util.C0643b;
import java.util.Comparator;

/* JADX INFO: renamed from: com.baidu.android.bbalbs.common.util.c */
/* JADX INFO: loaded from: classes.dex */
class C0644c implements Comparator<C0643b.a> {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ C0643b f111a;

    C0644c(C0643b c0643b) {
        this.f111a = c0643b;
    }

    @Override // java.util.Comparator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(C0643b.a aVar, C0643b.a aVar2) {
        int i = aVar2.f105b - aVar.f105b;
        if (i == 0) {
            if (aVar.f107d && aVar2.f107d) {
                return 0;
            }
            if (aVar.f107d) {
                return -1;
            }
            if (aVar2.f107d) {
                return 1;
            }
        }
        return i;
    }
}
