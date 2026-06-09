package com.baidu.trace.p011b;

import android.content.Context;
import android.os.Handler;
import com.baidu.trace.C0825ax;
import java.io.DataInputStream;

/* JADX INFO: renamed from: com.baidu.trace.b.g */
/* JADX INFO: loaded from: classes.dex */
public final class C0835g extends Thread {

    /* JADX INFO: renamed from: a */
    private Context f1657a;

    /* JADX INFO: renamed from: b */
    private Handler f1658b;

    /* JADX INFO: renamed from: c */
    private boolean f1659c = true;

    /* JADX INFO: renamed from: d */
    private DataInputStream f1660d = null;

    public C0835g(Context context, Handler handler) {
        this.f1657a = null;
        this.f1658b = null;
        this.f1657a = context;
        this.f1658b = handler;
    }

    /* JADX INFO: renamed from: a */
    public final void m1159a() {
        this.f1659c = false;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        Handler handler;
        int i;
        while (this.f1659c) {
            if (C0832d.m1138a(this.f1657a)) {
                try {
                    DataInputStream dataInputStreamM1125c = C0829a.m1125c();
                    this.f1660d = dataInputStreamM1125c;
                    if (dataInputStreamM1125c != null && !C0825ax.m1116a(dataInputStreamM1125c)) {
                        throw new Exception();
                    }
                    this.f1660d = null;
                } catch (Exception unused) {
                    if (!this.f1659c) {
                        return;
                    }
                    this.f1659c = false;
                    handler = this.f1658b;
                    if (handler != null) {
                        i = 4;
                        handler.obtainMessage(i).sendToTarget();
                    }
                }
            } else {
                this.f1659c = false;
                handler = this.f1658b;
                if (handler != null) {
                    i = 19;
                    handler.obtainMessage(i).sendToTarget();
                }
            }
        }
    }
}
