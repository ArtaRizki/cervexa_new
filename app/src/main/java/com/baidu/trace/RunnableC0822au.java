package com.baidu.trace;

import android.content.Context;
import com.baidu.mapapi.UIMsg;
import com.baidu.trace.model.StatusCodes;
import com.baidu.trace.p012c.C0854e;

/* JADX INFO: renamed from: com.baidu.trace.au */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0822au implements Runnable {

    /* JADX INFO: renamed from: a */
    private /* synthetic */ Context f1602a;

    /* JADX INFO: renamed from: b */
    private /* synthetic */ C0819ar f1603b;

    RunnableC0822au(C0819ar c0819ar, Context context) {
        this.f1603b = c0819ar;
        this.f1602a = context;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.f1603b.f1584j == null) {
            this.f1603b.f1584j = new C0812ak(this.f1602a);
        }
        if (C0854e.m1240e(this.f1602a)) {
            this.f1603b.f1584j.m1055a(UIMsg.m_AppUI.MSG_APP_DATA_OK);
            return;
        }
        C0791a.m1005a("BaiduTraceSDK", "Need ACCESS_FINE_LOCATION permission to get gps information");
        this.f1603b.m1094d();
        if (3 != C0812ak.f1252a) {
            this.f1603b.f1584j.m1053a(3, StatusCodes.MSG_NOT_GRANTED_PERMISSIONS);
        }
    }
}
