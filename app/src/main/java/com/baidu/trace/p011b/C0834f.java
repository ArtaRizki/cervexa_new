package com.baidu.trace.p011b;

import android.content.Context;
import android.os.Handler;
import com.baidu.trace.p011b.C0829a;

/* JADX INFO: renamed from: com.baidu.trace.b.f */
/* JADX INFO: loaded from: classes.dex */
public final class C0834f extends Thread {

    /* JADX INFO: renamed from: a */
    private Context f1653a;

    /* JADX INFO: renamed from: b */
    private Handler f1654b;

    /* JADX INFO: renamed from: c */
    private boolean f1655c = true;

    /* JADX INFO: renamed from: d */
    private int f1656d = 10;

    public C0834f(Context context, Handler handler) {
        this.f1653a = null;
        this.f1653a = context;
        this.f1654b = handler;
    }

    /* JADX INFO: renamed from: a */
    static /* synthetic */ boolean m1155a(C0834f c0834f, boolean z) {
        c0834f.f1655c = false;
        return false;
    }

    /* JADX INFO: renamed from: a */
    public final void m1157a() {
        this.f1655c = false;
    }

    /* JADX INFO: renamed from: b */
    public final void m1158b() {
        C0829a.m1127e();
        this.f1656d--;
        try {
            sleep(6000L);
        } catch (InterruptedException unused) {
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        while (this.f1655c) {
            if (C0832d.m1138a(this.f1653a)) {
                C0829a.m1122a(C0829a.a.f1636a, new C0833e(this));
            } else {
                this.f1655c = false;
                Handler handler = this.f1654b;
                if (handler != null) {
                    handler.obtainMessage(19).sendToTarget();
                }
            }
        }
    }
}
