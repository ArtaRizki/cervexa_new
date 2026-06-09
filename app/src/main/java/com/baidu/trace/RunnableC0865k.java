package com.baidu.trace;

import com.baidu.trace.api.bos.BosGetObjectResponse;
import com.baidu.trace.api.bos.BosObjectResponse;
import com.baidu.trace.api.bos.BosPutObjectResponse;
import com.baidu.trace.api.bos.OnBosListener;

/* JADX INFO: renamed from: com.baidu.trace.k */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0865k implements Runnable {

    /* JADX INFO: renamed from: a */
    private /* synthetic */ int f1795a;

    /* JADX INFO: renamed from: b */
    private /* synthetic */ OnBosListener f1796b;

    /* JADX INFO: renamed from: c */
    private /* synthetic */ BosObjectResponse f1797c;

    RunnableC0865k(int i, OnBosListener onBosListener, BosObjectResponse bosObjectResponse) {
        this.f1795a = i;
        this.f1796b = onBosListener;
        this.f1797c = bosObjectResponse;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.f1795a;
        if (i == 1) {
            this.f1796b.onPutObjectCallback((BosPutObjectResponse) this.f1797c);
        } else {
            if (i != 2) {
                return;
            }
            this.f1796b.onGetObjectCallback((BosGetObjectResponse) this.f1797c);
        }
    }
}
