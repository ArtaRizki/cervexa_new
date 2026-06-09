package com.baidu.p013vi;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
public class VMsg {

    /* JADX INFO: renamed from: a */
    private static final String f1891a = VMsg.class.getSimpleName();

    /* JADX INFO: renamed from: b */
    private static Handler f1892b;

    /* JADX INFO: renamed from: c */
    private static HandlerThread f1893c;

    /* JADX INFO: renamed from: com.baidu.vi.VMsg$a */
    static class HandlerC0882a extends Handler {
        public HandlerC0882a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            VMsg.OnUserCommand1(message.what, message.arg1, message.arg2, message.obj == null ? 0L : ((Long) message.obj).longValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void OnUserCommand1(int i, int i2, int i3, long j);

    public static void destroy() {
        f1893c.quit();
        f1893c = null;
        f1892b.removeCallbacksAndMessages(null);
        f1892b = null;
    }

    public static void init() {
        HandlerThread handlerThread = new HandlerThread("VIMsgThread");
        f1893c = handlerThread;
        handlerThread.start();
        f1892b = new HandlerC0882a(f1893c.getLooper());
    }

    private static void postMessage(int i, int i2, int i3, long j) {
        Handler handler = f1892b;
        if (handler == null) {
            return;
        }
        Message.obtain(handler, i, i2, i3, Long.valueOf(j)).sendToTarget();
    }
}
