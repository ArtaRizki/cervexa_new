package com.baidu.trace;

import com.baidu.lbsapi.auth.tracesdk.LBSAuthManagerListener;

/* JADX INFO: renamed from: com.baidu.trace.e */
/* JADX INFO: loaded from: classes.dex */
final class C0859e implements LBSAuthManagerListener {
    C0859e() {
    }

    @Override // com.baidu.lbsapi.auth.tracesdk.LBSAuthManagerListener
    public final void onAuthResult(int i, String str) {
        if (i == 0) {
            C0791a.m1027b(str);
        }
    }
}
