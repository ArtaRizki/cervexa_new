package com.baidu.trace;

import android.os.Handler;
import android.os.Message;

/* JADX INFO: renamed from: com.baidu.trace.bb */
/* JADX INFO: loaded from: classes.dex */
final class C0841bb extends Thread {

    /* JADX INFO: renamed from: a */
    private /* synthetic */ Handler f1674a;

    C0841bb(Handler handler) {
        this.f1674a = handler;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        Handler handler;
        synchronized (this) {
            try {
                wait(10000L);
            } catch (InterruptedException unused) {
            }
        }
        if (C0843bd.f1680f > 0 && (handler = this.f1674a) != null) {
            Message messageObtainMessage = handler.obtainMessage(141);
            messageObtainMessage.arg1 = 141;
            messageObtainMessage.sendToTarget();
        }
        C0843bd.f1680f = 0;
    }
}
