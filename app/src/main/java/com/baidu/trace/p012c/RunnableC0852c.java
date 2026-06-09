package com.baidu.trace.p012c;

import android.text.TextUtils;
import com.baidu.trace.model.StatusCodes;
import com.baidu.trace.p012c.C0850a;

/* JADX INFO: renamed from: com.baidu.trace.c.c */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0852c implements Runnable {

    /* JADX INFO: renamed from: a */
    private /* synthetic */ StringBuffer f1735a;

    /* JADX INFO: renamed from: b */
    private /* synthetic */ C0850a.d f1736b;

    RunnableC0852c(C0850a.d dVar, StringBuffer stringBuffer) {
        this.f1736b = dVar;
        this.f1735a = stringBuffer;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (TextUtils.isEmpty(this.f1735a.toString())) {
            C0850a.d.m1215a(this.f1736b.f1727a, this.f1736b.f1732f, StatusCodes.REQUEST_FAILED, StatusCodes.MSG_REQUEST_FAILED, this.f1736b.f1728b, this.f1736b.f1733g);
        } else {
            C0850a.d.m1216a(this.f1736b.f1727a, this.f1736b.f1732f, this.f1735a.toString(), this.f1736b.f1728b, this.f1736b.f1733g);
        }
    }
}
