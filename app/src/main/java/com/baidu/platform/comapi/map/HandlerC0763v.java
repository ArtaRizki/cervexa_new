package com.baidu.platform.comapi.map;

import android.os.Handler;
import android.os.Message;

/* JADX INFO: renamed from: com.baidu.platform.comapi.map.v */
/* JADX INFO: loaded from: classes.dex */
class HandlerC0763v extends Handler {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ C0762u f1021a;

    HandlerC0763v(C0762u c0762u) {
        this.f1021a = c0762u;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (C0762u.f1017c != null) {
            this.f1021a.f1019d.m753a(message);
        }
    }
}
