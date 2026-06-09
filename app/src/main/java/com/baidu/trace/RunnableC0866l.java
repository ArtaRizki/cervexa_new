package com.baidu.trace;

import com.baidu.trace.api.bos.BosPutObjectResponse;
import com.baidu.trace.api.bos.OnBosListener;

/* JADX INFO: renamed from: com.baidu.trace.l */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0866l implements Runnable {

    /* JADX INFO: renamed from: a */
    private /* synthetic */ OnBosListener f1798a;

    /* JADX INFO: renamed from: b */
    private /* synthetic */ BosPutObjectResponse f1799b;

    RunnableC0866l(OnBosListener onBosListener, BosPutObjectResponse bosPutObjectResponse) {
        this.f1798a = onBosListener;
        this.f1799b = bosPutObjectResponse;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f1798a.onPutObjectCallback(this.f1799b);
    }
}
