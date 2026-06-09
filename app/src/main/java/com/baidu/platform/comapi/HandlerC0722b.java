package com.baidu.platform.comapi;

import android.os.Handler;
import android.os.Message;

/* JADX INFO: renamed from: com.baidu.platform.comapi.b */
/* JADX INFO: loaded from: classes.dex */
class HandlerC0722b extends Handler {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ C0720a f793a;

    HandlerC0722b(C0720a c0720a) {
        this.f793a = c0720a;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        this.f793a.m525a(message);
    }
}
