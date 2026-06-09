package com.baidu.trace.p011b;

import android.content.Context;
import android.os.Handler;
import com.baidu.trace.C0827az;
import com.baidu.trace.p012c.C0854e;
import com.baidu.trace.p012c.C0855f;

/* JADX INFO: renamed from: com.baidu.trace.b.i */
/* JADX INFO: loaded from: classes.dex */
public final class C0837i extends Thread {

    /* JADX INFO: renamed from: a */
    private Context f1663a;

    /* JADX INFO: renamed from: b */
    private Handler f1664b;

    /* JADX INFO: renamed from: c */
    private byte[] f1665c;

    /* JADX INFO: renamed from: d */
    private C0855f.a f1666d;

    public C0837i(Context context, Handler handler, byte[] bArr, C0855f.a aVar) {
        this.f1663a = null;
        this.f1664b = null;
        this.f1665c = null;
        this.f1666d = null;
        this.f1663a = context;
        this.f1664b = handler;
        this.f1665c = bArr;
        this.f1666d = aVar;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        if (!C0832d.m1138a(this.f1663a)) {
            Handler handler = this.f1664b;
            if (handler != null) {
                handler.obtainMessage(19).sendToTarget();
                return;
            }
            return;
        }
        byte[] bArr = this.f1665c;
        if (bArr != null && bArr.length > 0) {
            if (this.f1666d != null) {
                if (C0855f.a.f1751a == this.f1666d) {
                    C0827az.f1631a = C0854e.m1224a();
                } else if (C0855f.a.f1752b == this.f1666d) {
                    C0827az.f1632b = C0854e.m1224a();
                }
                C0827az.m1120a(this.f1666d);
            }
            C0829a.m1123a(this.f1665c, this.f1664b);
        }
        this.f1665c = null;
    }
}
