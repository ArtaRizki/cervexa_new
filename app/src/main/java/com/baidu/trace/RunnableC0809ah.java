package com.baidu.trace;

import android.content.Context;
import android.os.Handler;
import com.baidu.trace.api.fence.HistoryAlarmRequest;
import com.baidu.trace.api.fence.OnFenceListener;

/* JADX INFO: renamed from: com.baidu.trace.ah */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0809ah implements Runnable {

    /* JADX INFO: renamed from: a */
    private /* synthetic */ Context f1242a;

    /* JADX INFO: renamed from: b */
    private /* synthetic */ Handler f1243b;

    /* JADX INFO: renamed from: c */
    private /* synthetic */ HistoryAlarmRequest f1244c;

    /* JADX INFO: renamed from: d */
    private /* synthetic */ OnFenceListener f1245d;

    RunnableC0809ah(Context context, Handler handler, HistoryAlarmRequest historyAlarmRequest, OnFenceListener onFenceListener) {
        this.f1242a = context;
        this.f1243b = handler;
        this.f1244c = historyAlarmRequest;
        this.f1245d = onFenceListener;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C0791a.m1024b(this.f1242a, this.f1243b, this.f1244c, this.f1245d);
    }
}
