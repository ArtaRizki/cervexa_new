package com.baidu.lbsapi.auth;

import com.baidu.lbsapi.auth.C0647c;

/* JADX INFO: renamed from: com.baidu.lbsapi.auth.j */
/* JADX INFO: loaded from: classes.dex */
class C0654j implements C0647c.a<String> {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ String f141a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ LBSAuthManager f142b;

    C0654j(LBSAuthManager lBSAuthManager, String str) {
        this.f142b = lBSAuthManager;
        this.f141a = str;
    }

    @Override // com.baidu.lbsapi.auth.C0647c.a
    /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public void mo150a(String str) {
        this.f142b.m119a(str, this.f141a);
    }
}
