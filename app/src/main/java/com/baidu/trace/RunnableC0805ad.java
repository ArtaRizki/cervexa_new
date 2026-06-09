package com.baidu.trace;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.baidu.trace.api.fence.DeleteFenceRequest;
import com.baidu.trace.api.fence.OnFenceListener;

/* JADX INFO: renamed from: com.baidu.trace.ad */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0805ad implements Runnable {

    /* JADX INFO: renamed from: a */
    private /* synthetic */ Context f1225a;

    /* JADX INFO: renamed from: b */
    private /* synthetic */ Handler f1226b;

    /* JADX INFO: renamed from: c */
    private /* synthetic */ DeleteFenceRequest f1227c;

    /* JADX INFO: renamed from: d */
    private /* synthetic */ OnFenceListener f1228d;

    /* JADX INFO: renamed from: e */
    private /* synthetic */ IService f1229e;

    RunnableC0805ad(Context context, Handler handler, DeleteFenceRequest deleteFenceRequest, OnFenceListener onFenceListener, IService iService) {
        this.f1225a = context;
        this.f1226b = handler;
        this.f1227c = deleteFenceRequest;
        this.f1228d = onFenceListener;
        this.f1229e = iService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StringBuffer stringBuffer = new StringBuffer();
        boolean zM1013a = C0791a.m1013a(this.f1225a, this.f1226b, this.f1227c, stringBuffer, this.f1228d);
        if (this.f1229e != null) {
            if (this.f1227c.getFenceIds() == null || !TextUtils.isEmpty(stringBuffer.toString())) {
                try {
                    if (this.f1227c.getFenceIds() != null) {
                        this.f1229e.handleLocalFence(2, 0L, stringBuffer.substring(0, stringBuffer.length() - 1));
                    } else if (zM1013a) {
                        this.f1229e.handleLocalFence(3, 0L, null);
                    }
                } catch (Exception unused) {
                }
            }
        }
    }
}
