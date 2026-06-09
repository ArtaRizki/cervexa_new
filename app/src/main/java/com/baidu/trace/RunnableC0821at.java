package com.baidu.trace;

import com.baidu.trace.model.CoordType;
import com.baidu.trace.model.LatLng;
import com.baidu.trace.p010a.C0798g;

/* JADX INFO: renamed from: com.baidu.trace.at */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0821at implements Runnable {

    /* JADX INFO: renamed from: a */
    private /* synthetic */ C0798g f1599a;

    /* JADX INFO: renamed from: b */
    private /* synthetic */ long f1600b;

    /* JADX INFO: renamed from: c */
    private /* synthetic */ C0819ar f1601c;

    RunnableC0821at(C0819ar c0819ar, C0798g c0798g, long j) {
        this.f1601c = c0819ar;
        this.f1599a = c0798g;
        this.f1600b = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C0819ar.m1088a(this.f1601c, new LatLng(((double) this.f1599a.f1203i) / 600000.0d, ((double) this.f1599a.f1202h) / 600000.0d), this.f1600b, CoordType.wgs84, this.f1599a.f1198d);
    }
}
