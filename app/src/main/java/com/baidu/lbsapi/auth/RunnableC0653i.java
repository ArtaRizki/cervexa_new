package com.baidu.lbsapi.auth;

import java.util.Hashtable;

/* JADX INFO: renamed from: com.baidu.lbsapi.auth.i */
/* JADX INFO: loaded from: classes.dex */
class RunnableC0653i implements Runnable {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ int f135a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ boolean f136b;

    /* JADX INFO: renamed from: c */
    final /* synthetic */ String f137c;

    /* JADX INFO: renamed from: d */
    final /* synthetic */ String f138d;

    /* JADX INFO: renamed from: e */
    final /* synthetic */ Hashtable f139e;

    /* JADX INFO: renamed from: f */
    final /* synthetic */ LBSAuthManager f140f;

    RunnableC0653i(LBSAuthManager lBSAuthManager, int i, boolean z, String str, String str2, Hashtable hashtable) {
        this.f140f = lBSAuthManager;
        this.f135a = i;
        this.f136b = z;
        this.f137c = str;
        this.f138d = str2;
        this.f139e = hashtable;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (C0645a.f120a) {
            C0645a.m130a("status = " + this.f135a + "; forced = " + this.f136b + "checkAK = " + this.f140f.m124b(this.f137c));
        }
        int i = this.f135a;
        if (i != 601 && !this.f136b && i != -1 && !this.f140f.m124b(this.f137c)) {
            if (602 == this.f135a) {
                if (C0645a.f120a) {
                    C0645a.m130a("authenticate wait  ");
                }
                LBSAuthManager.f113d.m166b();
            } else if (C0645a.f120a) {
                C0645a.m130a("authenticate else  ");
            }
            this.f140f.m119a((String) null, this.f137c);
            return;
        }
        if (C0645a.f120a) {
            C0645a.m130a("authenticate sendAuthRequest");
        }
        String[] strArrM138b = C0646b.m138b(LBSAuthManager.f112a);
        if (C0645a.f120a) {
            C0645a.m130a("authStrings.length:" + strArrM138b.length);
        }
        if (strArrM138b == null || strArrM138b.length <= 1) {
            this.f140f.m120a(this.f136b, this.f138d, this.f139e, this.f137c);
            return;
        }
        if (C0645a.f120a) {
            C0645a.m130a("more sha1 auth");
        }
        this.f140f.m121a(this.f136b, this.f138d, (Hashtable<String, String>) this.f139e, strArrM138b, this.f137c);
    }
}
