package com.baidu.platform.comapi.pano;

import com.baidu.mapapi.http.HttpClient;
import com.baidu.platform.comapi.pano.C0768a;

/* JADX INFO: renamed from: com.baidu.platform.comapi.pano.b */
/* JADX INFO: loaded from: classes.dex */
class C0769b extends HttpClient.ProtoResultCallback {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ C0768a.a f1043a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ C0768a f1044b;

    C0769b(C0768a c0768a, C0768a.a aVar) {
        this.f1044b = c0768a;
        this.f1043a = aVar;
    }

    @Override // com.baidu.mapapi.http.HttpClient.ProtoResultCallback
    public void onFailed(HttpClient.HttpStateError httpStateError) {
        this.f1043a.mo502a(httpStateError);
    }

    @Override // com.baidu.mapapi.http.HttpClient.ProtoResultCallback
    public void onSuccess(String str) {
        this.f1043a.mo503a(this.f1044b.m757a(str));
    }
}
