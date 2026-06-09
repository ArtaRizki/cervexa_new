package com.baidu.mapapi.http;

import com.baidu.mapapi.http.AsyncHttpClient;
import com.baidu.mapapi.http.HttpClient;

/* JADX INFO: renamed from: com.baidu.mapapi.http.a */
/* JADX INFO: loaded from: classes.dex */
class C0671a extends AsyncHttpClient.AbstractRunnableC0670a {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ HttpClient.ProtoResultCallback f220a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ String f221b;

    /* JADX INFO: renamed from: c */
    final /* synthetic */ AsyncHttpClient f222c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0671a(AsyncHttpClient asyncHttpClient, HttpClient.ProtoResultCallback protoResultCallback, String str) {
        super(null);
        this.f222c = asyncHttpClient;
        this.f220a = protoResultCallback;
        this.f221b = str;
    }

    @Override // com.baidu.mapapi.http.AsyncHttpClient.AbstractRunnableC0670a
    /* JADX INFO: renamed from: a */
    public void mo235a() throws Throwable {
        HttpClient httpClient = new HttpClient("GET", this.f220a);
        httpClient.setMaxTimeOut(this.f222c.f210a);
        httpClient.setReadTimeOut(this.f222c.f211b);
        httpClient.request(this.f221b);
    }
}
