package com.baidu.trace;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.baidu.trace.api.fence.OnFenceListener;
import com.baidu.trace.api.fence.UpdateFenceRequest;

/* JADX INFO: renamed from: com.baidu.trace.ac */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0804ac implements Runnable {

    /* JADX INFO: renamed from: a */
    private /* synthetic */ Context f1220a;

    /* JADX INFO: renamed from: b */
    private /* synthetic */ Handler f1221b;

    /* JADX INFO: renamed from: c */
    private /* synthetic */ UpdateFenceRequest f1222c;

    /* JADX INFO: renamed from: d */
    private /* synthetic */ OnFenceListener f1223d;

    /* JADX INFO: renamed from: e */
    private /* synthetic */ IService f1224e;

    RunnableC0804ac(Context context, Handler handler, UpdateFenceRequest updateFenceRequest, OnFenceListener onFenceListener, IService iService) {
        this.f1220a = context;
        this.f1221b = handler;
        this.f1222c = updateFenceRequest;
        this.f1223d = onFenceListener;
        this.f1224e = iService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        IService iService;
        String strM1020b = C0791a.m1020b(this.f1220a, this.f1221b, this.f1222c, this.f1223d);
        if (TextUtils.isEmpty(strM1020b) || (iService = this.f1224e) == null) {
            return;
        }
        try {
            iService.handleLocalFence(1, this.f1222c.getFence().getFenceId(), strM1020b);
        } catch (Exception unused) {
        }
    }
}
