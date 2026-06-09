package com.baidu.lbsapi.auth.tracesdk;

import java.util.Hashtable;

/* JADX INFO: renamed from: com.baidu.lbsapi.auth.tracesdk.i */
/* JADX INFO: loaded from: classes.dex */
class RunnableC0665i implements Runnable {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ int f171a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ boolean f172b;

    /* JADX INFO: renamed from: c */
    final /* synthetic */ String f173c;

    /* JADX INFO: renamed from: d */
    final /* synthetic */ String f174d;

    /* JADX INFO: renamed from: e */
    final /* synthetic */ Hashtable f175e;

    /* JADX INFO: renamed from: f */
    final /* synthetic */ LBSAuthManager f176f;

    RunnableC0665i(LBSAuthManager lBSAuthManager, int i, boolean z, String str, String str2, Hashtable hashtable) {
        this.f176f = lBSAuthManager;
        this.f171a = i;
        this.f172b = z;
        this.f173c = str;
        this.f174d = str2;
        this.f175e = hashtable;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (C0657a.f156a) {
            C0657a.m189a("status = " + this.f171a + "; forced = " + this.f172b + "checkAK = " + this.f176f.m183b(this.f173c));
        }
        int i = this.f171a;
        if (i != 601 && !this.f172b && i != -1 && !this.f176f.m183b(this.f173c)) {
            if (602 == this.f171a) {
                if (C0657a.f156a) {
                    C0657a.m189a("authenticate wait  ");
                }
                LBSAuthManager.f149d.m227b();
            } else if (C0657a.f156a) {
                C0657a.m189a("authenticate else  ");
            }
            this.f176f.m178a((String) null, this.f173c);
            return;
        }
        if (C0657a.f156a) {
            C0657a.m189a("authenticate sendAuthRequest");
        }
        String[] strArrM197b = C0658b.m197b(LBSAuthManager.f148a);
        if (C0657a.f156a) {
            C0657a.m189a("authStrings.length:" + strArrM197b.length);
        }
        if (strArrM197b == null || strArrM197b.length <= 1) {
            this.f176f.m179a(this.f172b, this.f174d, this.f175e, this.f173c);
            return;
        }
        if (C0657a.f156a) {
            C0657a.m189a("more sha1 auth");
        }
        this.f176f.m180a(this.f172b, this.f174d, this.f175e, strArrM197b, this.f173c);
    }
}
