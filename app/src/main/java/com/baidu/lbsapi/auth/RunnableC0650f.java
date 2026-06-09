package com.baidu.lbsapi.auth;

import java.util.HashMap;
import java.util.List;

/* JADX INFO: renamed from: com.baidu.lbsapi.auth.f */
/* JADX INFO: loaded from: classes.dex */
class RunnableC0650f implements Runnable {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ C0649e f129a;

    RunnableC0650f(C0649e c0649e) {
        this.f129a = c0649e;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        C0649e c0649e = this.f129a;
        c0649e.m155a((List<HashMap<String, String>>) c0649e.f127b);
    }
}
