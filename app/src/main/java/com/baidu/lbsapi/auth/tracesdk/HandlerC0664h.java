package com.baidu.lbsapi.auth.tracesdk;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: renamed from: com.baidu.lbsapi.auth.tracesdk.h */
/* JADX INFO: loaded from: classes.dex */
class HandlerC0664h extends Handler {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ LBSAuthManager f170a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HandlerC0664h(LBSAuthManager lBSAuthManager, Looper looper) {
        super(looper);
        this.f170a = lBSAuthManager;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (C0657a.f156a) {
            C0657a.m189a("handleMessage !!");
        }
        LBSAuthManagerListener lBSAuthManagerListener = (LBSAuthManagerListener) LBSAuthManager.f151f.get(message.getData().getString("listenerKey"));
        if (C0657a.f156a) {
            C0657a.m189a("handleMessage listener = " + lBSAuthManagerListener);
        }
        if (lBSAuthManagerListener != null) {
            lBSAuthManagerListener.onAuthResult(message.what, message.obj.toString());
        }
    }
}
