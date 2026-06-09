package com.baidu.lbsapi.auth;

/* JADX INFO: renamed from: com.baidu.lbsapi.auth.d */
/* JADX INFO: loaded from: classes.dex */
class RunnableC0648d implements Runnable {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ C0647c f125a;

    RunnableC0648d(C0647c c0647c) {
        this.f125a = c0647c;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        C0645a.m130a("postWithHttps start Thread id = " + String.valueOf(Thread.currentThread().getId()));
        this.f125a.m147a(new C0651g(this.f125a.f122a).m163a(this.f125a.f123b));
    }
}
