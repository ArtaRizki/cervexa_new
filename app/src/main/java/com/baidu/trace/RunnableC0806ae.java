package com.baidu.trace;

import android.content.Context;
import android.os.Handler;
import com.baidu.trace.api.fence.FenceListRequest;
import com.baidu.trace.api.fence.OnFenceListener;

/* JADX INFO: renamed from: com.baidu.trace.ae */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0806ae implements Runnable {

    /* JADX INFO: renamed from: a */
    private /* synthetic */ Context f1230a;

    /* JADX INFO: renamed from: b */
    private /* synthetic */ Handler f1231b;

    /* JADX INFO: renamed from: c */
    private /* synthetic */ FenceListRequest f1232c;

    /* JADX INFO: renamed from: d */
    private /* synthetic */ OnFenceListener f1233d;

    RunnableC0806ae(Context context, Handler handler, FenceListRequest fenceListRequest, OnFenceListener onFenceListener) {
        this.f1230a = context;
        this.f1231b = handler;
        this.f1232c = fenceListRequest;
        this.f1233d = onFenceListener;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C0791a.m1023b(this.f1230a, this.f1231b, this.f1232c, this.f1233d);
    }
}
