package com.baidu.trace;

import android.location.GpsStatus;
import android.os.Build;

/* JADX INFO: renamed from: com.baidu.trace.al */
/* JADX INFO: loaded from: classes.dex */
final class C0813al implements GpsStatus.Listener {

    /* JADX INFO: renamed from: a */
    private /* synthetic */ C0812ak f1269a;

    C0813al(C0812ak c0812ak) {
        this.f1269a = c0812ak;
    }

    @Override // android.location.GpsStatus.Listener
    public final void onGpsStatusChanged(int i) {
        if (Build.VERSION.SDK_INT < 24) {
            C0812ak.m1051a(this.f1269a, i, this.f1269a.f1253b.getGpsStatus(null));
        } else {
            try {
                C0812ak.m1051a(this.f1269a, i, this.f1269a.f1253b.getGpsStatus(null));
            } catch (Exception unused) {
                C0812ak.m1049a(this.f1269a, 1);
            }
        }
    }
}
