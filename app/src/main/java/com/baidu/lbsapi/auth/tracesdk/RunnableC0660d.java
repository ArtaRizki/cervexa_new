package com.baidu.lbsapi.auth.tracesdk;

/* JADX INFO: renamed from: com.baidu.lbsapi.auth.tracesdk.d */
/* JADX INFO: loaded from: classes.dex */
class RunnableC0660d implements Runnable {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ C0659c f161a;

    RunnableC0660d(C0659c c0659c) {
        this.f161a = c0659c;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        C0657a.m189a("postWithHttps start Thread id = " + String.valueOf(Thread.currentThread().getId()));
        this.f161a.m206a(new C0663g(this.f161a.f158a).m222a(this.f161a.f159b));
    }
}
