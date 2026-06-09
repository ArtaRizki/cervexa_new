package com.baidu.mapapi.utils;

import android.content.Context;

/* JADX INFO: renamed from: com.baidu.mapapi.utils.e */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0712e implements Runnable {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ Context f750a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ int f751b;

    RunnableC0712e(Context context, int i) {
        this.f750a = context;
        this.f751b = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        do {
            if (System.currentTimeMillis() - jCurrentTimeMillis > 3000) {
                C0708a.m464a(this.f750a);
                C0708a.m463a(this.f751b, this.f750a);
            }
        } while (!C0708a.f747v.isInterrupted());
    }
}
