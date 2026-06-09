package com.tencent.bugly.crashreport.common.strategy;

import android.content.Context;
import com.tencent.bugly.AbstractC1949a;
import com.tencent.bugly.crashreport.biz.C1956b;
import com.tencent.bugly.crashreport.common.info.C1958a;
import com.tencent.bugly.proguard.C1996ap;
import com.tencent.bugly.proguard.C2013p;
import com.tencent.bugly.proguard.C2015r;
import com.tencent.bugly.proguard.C2020w;
import com.tencent.bugly.proguard.C2021x;
import com.tencent.bugly.proguard.C2023z;
import com.tencent.bugly.proguard.InterfaceC2012o;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: com.tencent.bugly.crashreport.common.strategy.a */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public final class C1961a {

    /* JADX INFO: renamed from: a */
    public static int f2448a = 1000;

    /* JADX INFO: renamed from: b */
    private static C1961a f2449b;

    /* JADX INFO: renamed from: h */
    private static String f2450h;

    /* JADX INFO: renamed from: c */
    private final List<AbstractC1949a> f2451c;

    /* JADX INFO: renamed from: d */
    private final C2020w f2452d;

    /* JADX INFO: renamed from: e */
    private final StrategyBean f2453e;

    /* JADX INFO: renamed from: f */
    private StrategyBean f2454f = null;

    /* JADX INFO: renamed from: g */
    private Context f2455g;

    private C1961a(Context context, List<AbstractC1949a> list) {
        String str;
        this.f2455g = context;
        if (C1958a.m1471a(context) != null) {
            String str2 = C1958a.m1471a(context).f2422z;
            if (!"oversea".equals(str2)) {
                str = "na_https".equals(str2) ? "https://astat.bugly.cros.wr.pvp.net/:8180/rqd/async" : "https://astat.bugly.qcloud.com/rqd/async";
            }
            StrategyBean.f2426a = str;
            StrategyBean.f2427b = str;
        }
        this.f2453e = new StrategyBean();
        this.f2451c = list;
        this.f2452d = C2020w.m1858a();
    }

    /* JADX INFO: renamed from: a */
    public static synchronized C1961a m1545a(Context context, List<AbstractC1949a> list) {
        if (f2449b == null) {
            f2449b = new C1961a(context, list);
        }
        return f2449b;
    }

    /* JADX INFO: renamed from: a */
    public final void m1550a(long j) {
        this.f2452d.m1861a(new Thread() { // from class: com.tencent.bugly.crashreport.common.strategy.a.1
            @Override // java.lang.Thread, java.lang.Runnable
            public final void run() {
                try {
                    Map<String, byte[]> mapM1825a = C2013p.m1807a().m1825a(C1961a.f2448a, (InterfaceC2012o) null, true);
                    if (mapM1825a != null) {
                        byte[] bArr = mapM1825a.get("device");
                        byte[] bArr2 = mapM1825a.get("gateway");
                        if (bArr != null) {
                            C1958a.m1471a(C1961a.this.f2455g).m1492e(new String(bArr));
                        }
                        if (bArr2 != null) {
                            C1958a.m1471a(C1961a.this.f2455g).m1490d(new String(bArr2));
                        }
                    }
                    C1961a.this.f2454f = C1961a.m1548d();
                    if (C1961a.this.f2454f != null) {
                        if (C2023z.m1914a(C1961a.f2450h) || !C2023z.m1929c(C1961a.f2450h)) {
                            C1961a.this.f2454f.f2441p = StrategyBean.f2426a;
                            C1961a.this.f2454f.f2442q = StrategyBean.f2427b;
                        } else {
                            C1961a.this.f2454f.f2441p = C1961a.f2450h;
                            C1961a.this.f2454f.f2442q = C1961a.f2450h;
                        }
                    }
                } catch (Throwable th) {
                    if (!C2021x.m1867a(th)) {
                        th.printStackTrace();
                    }
                }
                C1961a c1961a = C1961a.this;
                c1961a.m1551a(c1961a.f2454f, false);
            }
        }, j);
    }

    /* JADX INFO: renamed from: a */
    public static synchronized C1961a m1544a() {
        return f2449b;
    }

    /* JADX INFO: renamed from: b */
    public final synchronized boolean m1553b() {
        return this.f2454f != null;
    }

    /* JADX INFO: renamed from: c */
    public final StrategyBean m1554c() {
        StrategyBean strategyBean = this.f2454f;
        if (strategyBean != null) {
            if (!C2023z.m1929c(strategyBean.f2441p)) {
                this.f2454f.f2441p = StrategyBean.f2426a;
            }
            if (!C2023z.m1929c(this.f2454f.f2442q)) {
                this.f2454f.f2442q = StrategyBean.f2427b;
            }
            return this.f2454f;
        }
        if (!C2023z.m1914a(f2450h) && C2023z.m1929c(f2450h)) {
            this.f2453e.f2441p = f2450h;
            this.f2453e.f2442q = f2450h;
        }
        return this.f2453e;
    }

    /* JADX INFO: renamed from: a */
    protected final void m1551a(StrategyBean strategyBean, boolean z) {
        C2021x.m1871c("[Strategy] Notify %s", C1956b.class.getName());
        C1956b.m1446a(strategyBean, z);
        for (AbstractC1949a abstractC1949a : this.f2451c) {
            try {
                C2021x.m1871c("[Strategy] Notify %s", abstractC1949a.getClass().getName());
                abstractC1949a.onServerStrategyChanged(strategyBean);
            } catch (Throwable th) {
                if (!C2021x.m1867a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m1546a(String str) {
        if (C2023z.m1914a(str) || !C2023z.m1929c(str)) {
            C2021x.m1872d("URL user set is invalid.", new Object[0]);
        } else {
            f2450h = str;
        }
    }

    /* JADX INFO: renamed from: a */
    public final void m1552a(C1996ap c1996ap) {
        if (c1996ap == null) {
            return;
        }
        if (this.f2454f == null || c1996ap.f2768h != this.f2454f.f2439n) {
            StrategyBean strategyBean = new StrategyBean();
            strategyBean.f2430e = c1996ap.f2761a;
            strategyBean.f2432g = c1996ap.f2763c;
            strategyBean.f2431f = c1996ap.f2762b;
            if (C2023z.m1914a(f2450h) || !C2023z.m1929c(f2450h)) {
                if (C2023z.m1929c(c1996ap.f2764d)) {
                    C2021x.m1871c("[Strategy] Upload url changes to %s", c1996ap.f2764d);
                    strategyBean.f2441p = c1996ap.f2764d;
                }
                if (C2023z.m1929c(c1996ap.f2765e)) {
                    C2021x.m1871c("[Strategy] Exception upload url changes to %s", c1996ap.f2765e);
                    strategyBean.f2442q = c1996ap.f2765e;
                }
            }
            if (c1996ap.f2766f != null && !C2023z.m1914a(c1996ap.f2766f.f2756a)) {
                strategyBean.f2443r = c1996ap.f2766f.f2756a;
            }
            if (c1996ap.f2768h != 0) {
                strategyBean.f2439n = c1996ap.f2768h;
            }
            if (c1996ap.f2767g != null && c1996ap.f2767g.size() > 0) {
                strategyBean.f2444s = c1996ap.f2767g;
                String str = c1996ap.f2767g.get("B11");
                if (str != null && str.equals("1")) {
                    strategyBean.f2433h = true;
                } else {
                    strategyBean.f2433h = false;
                }
                String str2 = c1996ap.f2767g.get("B3");
                if (str2 != null) {
                    strategyBean.f2447v = Long.valueOf(str2).longValue();
                }
                strategyBean.f2440o = c1996ap.f2769i;
                strategyBean.f2446u = c1996ap.f2769i;
                String str3 = c1996ap.f2767g.get("B27");
                if (str3 != null && str3.length() > 0) {
                    try {
                        int i = Integer.parseInt(str3);
                        if (i > 0) {
                            strategyBean.f2445t = i;
                        }
                    } catch (Exception e) {
                        if (!C2021x.m1867a(e)) {
                            e.printStackTrace();
                        }
                    }
                }
                String str4 = c1996ap.f2767g.get("B25");
                if (str4 != null && str4.equals("1")) {
                    strategyBean.f2435j = true;
                } else {
                    strategyBean.f2435j = false;
                }
            }
            C2021x.m1866a("[Strategy] enableCrashReport:%b, enableQuery:%b, enableUserInfo:%b, enableAnr:%b, enableBlock:%b, enableSession:%b, enableSessionTimer:%b, sessionOverTime:%d, enableCocos:%b, strategyLastUpdateTime:%d", Boolean.valueOf(strategyBean.f2430e), Boolean.valueOf(strategyBean.f2432g), Boolean.valueOf(strategyBean.f2431f), Boolean.valueOf(strategyBean.f2433h), Boolean.valueOf(strategyBean.f2434i), Boolean.valueOf(strategyBean.f2437l), Boolean.valueOf(strategyBean.f2438m), Long.valueOf(strategyBean.f2440o), Boolean.valueOf(strategyBean.f2435j), Long.valueOf(strategyBean.f2439n));
            this.f2454f = strategyBean;
            if (!C2023z.m1929c(c1996ap.f2764d)) {
                C2021x.m1871c("[Strategy] download url is null", new Object[0]);
                this.f2454f.f2441p = "";
            }
            if (!C2023z.m1929c(c1996ap.f2765e)) {
                C2021x.m1871c("[Strategy] download crashurl is null", new Object[0]);
                this.f2454f.f2442q = "";
            }
            C2013p.m1807a().m1829b(2);
            C2015r c2015r = new C2015r();
            c2015r.f2861b = 2;
            c2015r.f2860a = strategyBean.f2428c;
            c2015r.f2864e = strategyBean.f2429d;
            c2015r.f2866g = C2023z.m1915a(strategyBean);
            C2013p.m1807a().m1828a(c2015r);
            m1551a(strategyBean, true);
        }
    }

    /* JADX INFO: renamed from: d */
    public static StrategyBean m1548d() {
        List<C2015r> listM1824a = C2013p.m1807a().m1824a(2);
        if (listM1824a == null || listM1824a.size() <= 0) {
            return null;
        }
        C2015r c2015r = listM1824a.get(0);
        if (c2015r.f2866g != null) {
            return (StrategyBean) C2023z.m1896a(c2015r.f2866g, StrategyBean.CREATOR);
        }
        return null;
    }
}
