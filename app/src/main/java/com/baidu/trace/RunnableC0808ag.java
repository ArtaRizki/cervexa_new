package com.baidu.trace;

import android.content.Context;
import android.os.Handler;
import com.baidu.trace.api.fence.MonitoredStatusByLocationRequest;
import com.baidu.trace.api.fence.OnFenceListener;

/* JADX INFO: renamed from: com.baidu.trace.ag */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0808ag implements Runnable {

    /* JADX INFO: renamed from: a */
    private /* synthetic */ Context f1238a;

    /* JADX INFO: renamed from: b */
    private /* synthetic */ Handler f1239b;

    /* JADX INFO: renamed from: c */
    private /* synthetic */ MonitoredStatusByLocationRequest f1240c;

    /* JADX INFO: renamed from: d */
    private /* synthetic */ OnFenceListener f1241d;

    RunnableC0808ag(Context context, Handler handler, MonitoredStatusByLocationRequest monitoredStatusByLocationRequest, OnFenceListener onFenceListener) {
        this.f1238a = context;
        this.f1239b = handler;
        this.f1240c = monitoredStatusByLocationRequest;
        this.f1241d = onFenceListener;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C0791a.m1025b(this.f1238a, this.f1239b, this.f1240c, this.f1241d);
    }
}
