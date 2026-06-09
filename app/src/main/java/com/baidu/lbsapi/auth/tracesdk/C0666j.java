package com.baidu.lbsapi.auth.tracesdk;

import com.baidu.lbsapi.auth.tracesdk.C0659c;

/* JADX INFO: renamed from: com.baidu.lbsapi.auth.tracesdk.j */
/* JADX INFO: loaded from: classes.dex */
class C0666j implements C0659c.a {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ String f177a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ LBSAuthManager f178b;

    C0666j(LBSAuthManager lBSAuthManager, String str) {
        this.f178b = lBSAuthManager;
        this.f177a = str;
    }

    @Override // com.baidu.lbsapi.auth.tracesdk.C0659c.a
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void mo209a(String str) {
        this.f178b.m178a(str, this.f177a);
    }
}
