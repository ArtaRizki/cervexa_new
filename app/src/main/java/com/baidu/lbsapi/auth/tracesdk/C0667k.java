package com.baidu.lbsapi.auth.tracesdk;

import com.baidu.lbsapi.auth.tracesdk.C0661e;

/* JADX INFO: renamed from: com.baidu.lbsapi.auth.tracesdk.k */
/* JADX INFO: loaded from: classes.dex */
class C0667k implements C0661e.a {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ String f179a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ LBSAuthManager f180b;

    C0667k(LBSAuthManager lBSAuthManager, String str) {
        this.f180b = lBSAuthManager;
        this.f179a = str;
    }

    @Override // com.baidu.lbsapi.auth.tracesdk.C0661e.a
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void mo216a(String str) {
        this.f180b.m178a(str, this.f179a);
    }
}
