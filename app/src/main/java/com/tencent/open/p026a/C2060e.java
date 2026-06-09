package com.tencent.open.p026a;

import android.util.Log;

/* JADX INFO: renamed from: com.tencent.open.a.e */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public final class C2060e extends AbstractC2064i {

    /* JADX INFO: renamed from: a */
    public static final C2060e f3172a = new C2060e();

    @Override // com.tencent.open.p026a.AbstractC2064i
    /* JADX INFO: renamed from: a */
    protected void mo2092a(int i, Thread thread, long j, String str, String str2, Throwable th) {
        if (i == 1) {
            Log.v(str, str2, th);
            return;
        }
        if (i == 2) {
            Log.d(str, str2, th);
            return;
        }
        if (i == 4) {
            Log.i(str, str2, th);
            return;
        }
        if (i == 8) {
            Log.w(str, str2, th);
        } else if (i == 16) {
            Log.e(str, str2, th);
        } else {
            if (i != 32) {
                return;
            }
            Log.e(str, str2, th);
        }
    }
}
