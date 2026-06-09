package com.baidu.trace.p011b;

/* JADX INFO: renamed from: com.baidu.trace.b.e */
/* JADX INFO: loaded from: classes.dex */
public class C0833e {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ C0834f f1652a;

    C0833e(C0834f c0834f) {
        this.f1652a = c0834f;
    }

    /* JADX INFO: renamed from: a */
    public void m1152a() {
        C0834f.m1155a(this.f1652a, false);
        this.f1652a.f1654b.obtainMessage(1).sendToTarget();
    }

    /* JADX INFO: renamed from: b */
    public void m1153b() {
        if (this.f1652a.f1656d > 0) {
            this.f1652a.m1158b();
        } else {
            C0834f.m1155a(this.f1652a, false);
            this.f1652a.f1654b.obtainMessage(19).sendToTarget();
        }
    }
}
