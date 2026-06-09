package com.baidu.trace;

import android.content.Context;
import android.os.Handler;
import com.baidu.trace.api.fence.MonitoredStatusRequest;
import com.baidu.trace.api.fence.OnFenceListener;

/* JADX INFO: renamed from: com.baidu.trace.af */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0807af implements Runnable {

    /* JADX INFO: renamed from: a */
    private /* synthetic */ Context f1234a;

    /* JADX INFO: renamed from: b */
    private /* synthetic */ Handler f1235b;

    /* JADX INFO: renamed from: c */
    private /* synthetic */ MonitoredStatusRequest f1236c;

    /* JADX INFO: renamed from: d */
    private /* synthetic */ OnFenceListener f1237d;

    RunnableC0807af(Context context, Handler handler, MonitoredStatusRequest monitoredStatusRequest, OnFenceListener onFenceListener) {
        this.f1234a = context;
        this.f1235b = handler;
        this.f1236c = monitoredStatusRequest;
        this.f1237d = onFenceListener;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C0791a.m1026b(this.f1234a, this.f1235b, this.f1236c, this.f1237d);
    }
}
