package com.baidu.trace;

import com.baidu.trace.api.bos.BosGetObjectResponse;
import com.baidu.trace.api.bos.OnBosListener;

/* JADX INFO: renamed from: com.baidu.trace.m */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0867m implements Runnable {

    /* JADX INFO: renamed from: a */
    private /* synthetic */ OnBosListener f1800a;

    /* JADX INFO: renamed from: b */
    private /* synthetic */ BosGetObjectResponse f1801b;

    RunnableC0867m(OnBosListener onBosListener, BosGetObjectResponse bosGetObjectResponse) {
        this.f1800a = onBosListener;
        this.f1801b = bosGetObjectResponse;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f1800a.onGetObjectCallback(this.f1801b);
    }
}
