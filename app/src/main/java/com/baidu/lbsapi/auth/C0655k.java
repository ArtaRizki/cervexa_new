package com.baidu.lbsapi.auth;

import com.baidu.lbsapi.auth.C0649e;

/* JADX INFO: renamed from: com.baidu.lbsapi.auth.k */
/* JADX INFO: loaded from: classes.dex */
class C0655k implements C0649e.a<String> {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ String f143a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ LBSAuthManager f144b;

    C0655k(LBSAuthManager lBSAuthManager, String str) {
        this.f144b = lBSAuthManager;
        this.f143a = str;
    }

    @Override // com.baidu.lbsapi.auth.C0649e.a
    /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public void mo157a(String str) {
        this.f144b.m119a(str, this.f143a);
    }
}
