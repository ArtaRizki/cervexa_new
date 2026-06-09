package com.baidu.trace;

import com.baidu.trace.api.bos.BosPutObjectResponse;
import com.baidu.trace.api.bos.OnBosListener;

/* JADX INFO: renamed from: com.baidu.trace.i */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0863i implements Runnable {

    /* JADX INFO: renamed from: a */
    private /* synthetic */ OnBosListener f1791a;

    /* JADX INFO: renamed from: b */
    private /* synthetic */ BosPutObjectResponse f1792b;

    RunnableC0863i(OnBosListener onBosListener, BosPutObjectResponse bosPutObjectResponse) {
        this.f1791a = onBosListener;
        this.f1792b = bosPutObjectResponse;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f1791a.onPutObjectCallback(this.f1792b);
    }
}
