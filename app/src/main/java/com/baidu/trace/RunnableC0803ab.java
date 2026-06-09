package com.baidu.trace;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.baidu.trace.api.fence.CreateFenceRequest;
import com.baidu.trace.api.fence.OnFenceListener;

/* JADX INFO: renamed from: com.baidu.trace.ab */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0803ab implements Runnable {

    /* JADX INFO: renamed from: a */
    private /* synthetic */ Context f1215a;

    /* JADX INFO: renamed from: b */
    private /* synthetic */ Handler f1216b;

    /* JADX INFO: renamed from: c */
    private /* synthetic */ CreateFenceRequest f1217c;

    /* JADX INFO: renamed from: d */
    private /* synthetic */ OnFenceListener f1218d;

    /* JADX INFO: renamed from: e */
    private /* synthetic */ IService f1219e;

    RunnableC0803ab(Context context, Handler handler, CreateFenceRequest createFenceRequest, OnFenceListener onFenceListener, IService iService) {
        this.f1215a = context;
        this.f1216b = handler;
        this.f1217c = createFenceRequest;
        this.f1218d = onFenceListener;
        this.f1219e = iService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        IService iService;
        String strM1019b = C0791a.m1019b(this.f1215a, this.f1216b, this.f1217c.getTag(), this.f1217c.getFence(), this.f1218d);
        if (TextUtils.isEmpty(strM1019b) || (iService = this.f1219e) == null) {
            return;
        }
        try {
            iService.handleLocalFence(0, this.f1217c.getFence().getFenceId(), strM1019b);
        } catch (Exception unused) {
        }
    }
}
