package com.baidu.trace;

import com.baidu.trace.p011b.C0829a;
import com.baidu.trace.p012c.C0855f;

/* JADX INFO: renamed from: com.baidu.trace.ba */
/* JADX INFO: loaded from: classes.dex */
final class C0840ba extends Thread {

    /* JADX INFO: renamed from: a */
    private /* synthetic */ C0855f.a f1673a;

    C0840ba(C0855f.a aVar) {
        this.f1673a = aVar;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        synchronized (this) {
            try {
                wait(10000L);
            } catch (InterruptedException unused) {
            }
        }
        if (C0855f.a.f1751a == this.f1673a) {
            if (C0827az.f1631a > 0) {
                C0829a.m1127e();
            }
        } else {
            if (C0855f.a.f1752b != this.f1673a || C0827az.f1632b <= 0) {
                return;
            }
            C0829a.m1127e();
        }
    }
}
