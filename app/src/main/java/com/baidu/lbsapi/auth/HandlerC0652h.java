package com.baidu.lbsapi.auth;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: renamed from: com.baidu.lbsapi.auth.h */
/* JADX INFO: loaded from: classes.dex */
class HandlerC0652h extends Handler {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ LBSAuthManager f134a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HandlerC0652h(LBSAuthManager lBSAuthManager, Looper looper) {
        super(looper);
        this.f134a = lBSAuthManager;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (C0645a.f120a) {
            C0645a.m130a("handleMessage !!");
        }
        LBSAuthManagerListener lBSAuthManagerListener = (LBSAuthManagerListener) LBSAuthManager.f115f.get(message.getData().getString("listenerKey"));
        if (C0645a.f120a) {
            C0645a.m130a("handleMessage listener = " + lBSAuthManagerListener);
        }
        if (lBSAuthManagerListener != null) {
            lBSAuthManagerListener.onAuthResult(message.what, message.obj.toString());
        }
    }
}
