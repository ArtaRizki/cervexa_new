package com.baidu.trace;

import com.baidu.trace.api.bos.BosGetObjectResponse;
import com.baidu.trace.api.bos.OnBosListener;

/* JADX INFO: renamed from: com.baidu.trace.j */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0864j implements Runnable {

    /* JADX INFO: renamed from: a */
    private /* synthetic */ OnBosListener f1793a;

    /* JADX INFO: renamed from: b */
    private /* synthetic */ BosGetObjectResponse f1794b;

    RunnableC0864j(OnBosListener onBosListener, BosGetObjectResponse bosGetObjectResponse) {
        this.f1793a = onBosListener;
        this.f1794b = bosGetObjectResponse;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f1793a.onGetObjectCallback(this.f1794b);
    }
}
