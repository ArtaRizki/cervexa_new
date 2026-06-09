package com.tencent.bugly.crashreport.crash;

import android.content.Context;
import com.tencent.bugly.crashreport.common.info.C1958a;
import com.tencent.bugly.crashreport.common.info.C1959b;
import com.tencent.bugly.crashreport.common.strategy.C1961a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.C2020w;
import com.tencent.bugly.proguard.C2021x;
import com.tencent.bugly.proguard.C2022y;
import com.tencent.bugly.proguard.C2023z;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.tencent.bugly.crashreport.crash.d */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public final class C1973d {

    /* JADX INFO: renamed from: a */
    private static C1973d f2599a;

    /* JADX INFO: renamed from: b */
    private C1961a f2600b;

    /* JADX INFO: renamed from: c */
    private C1958a f2601c;

    /* JADX INFO: renamed from: d */
    private C1971b f2602d;

    /* JADX INFO: renamed from: e */
    private Context f2603e;

    /* JADX INFO: renamed from: a */
    static /* synthetic */ void m1642a(C1973d c1973d) {
        C2021x.m1871c("[ExtraCrashManager] Trying to notify Bugly agents.", new Object[0]);
        try {
            Class<?> cls = Class.forName("com.tencent.bugly.agent.GameAgent");
            c1973d.f2601c.getClass();
            C2023z.m1910a(cls, "sdkPackageName", "com.tencent.bugly", null);
            C2021x.m1871c("[ExtraCrashManager] Bugly game agent has been notified.", new Object[0]);
        } catch (Throwable unused) {
            C2021x.m1866a("[ExtraCrashManager] no game agent", new Object[0]);
        }
    }

    /* JADX INFO: renamed from: a */
    static /* synthetic */ void m1643a(C1973d c1973d, Thread thread, int i, String str, String str2, String str3, Map map) {
        String str4;
        String str5;
        String str6;
        Thread threadCurrentThread = thread == null ? Thread.currentThread() : thread;
        if (i == 4) {
            str4 = "Unity";
        } else if (i == 5 || i == 6) {
            str4 = "Cocos";
        } else {
            if (i != 8) {
                C2021x.m1872d("[ExtraCrashManager] Unknown extra crash type: %d", Integer.valueOf(i));
                return;
            }
            str4 = "H5";
        }
        C2021x.m1873e("[ExtraCrashManager] %s Crash Happen", str4);
        try {
            if (!c1973d.f2600b.m1553b()) {
                C2021x.m1872d("[ExtraCrashManager] There is no remote strategy, but still store it.", new Object[0]);
            }
            StrategyBean strategyBeanM1554c = c1973d.f2600b.m1554c();
            if (!strategyBeanM1554c.f2430e && c1973d.f2600b.m1553b()) {
                C2021x.m1873e("[ExtraCrashManager] Crash report was closed by remote , will not upload to Bugly , print local for helpful!", new Object[0]);
                C1971b.m1593a(str4, C2023z.m1897a(), c1973d.f2601c.f2400d, threadCurrentThread.getName(), str + "\n" + str2 + "\n" + str3, null);
                C2021x.m1873e("[ExtraCrashManager] Successfully handled.", new Object[0]);
                return;
            }
            if (i == 5 || i == 6) {
                if (!strategyBeanM1554c.f2435j) {
                    C2021x.m1873e("[ExtraCrashManager] %s report is disabled.", str4);
                    C2021x.m1873e("[ExtraCrashManager] Successfully handled.", new Object[0]);
                    return;
                }
            } else if (i == 8 && !strategyBeanM1554c.f2436k) {
                C2021x.m1873e("[ExtraCrashManager] %s report is disabled.", str4);
                C2021x.m1873e("[ExtraCrashManager] Successfully handled.", new Object[0]);
                return;
            }
            int i2 = i != 8 ? i : 5;
            CrashDetailBean crashDetailBean = new CrashDetailBean();
            crashDetailBean.f2466C = C1959b.m1529g();
            crashDetailBean.f2467D = C1959b.m1525e();
            crashDetailBean.f2468E = C1959b.m1533i();
            crashDetailBean.f2469F = c1973d.f2601c.m1500k();
            crashDetailBean.f2470G = c1973d.f2601c.m1499j();
            crashDetailBean.f2471H = c1973d.f2601c.m1501l();
            crashDetailBean.f2510w = C2023z.m1899a(c1973d.f2603e, C1972c.f2569e, (String) null);
            crashDetailBean.f2489b = i2;
            crashDetailBean.f2492e = c1973d.f2601c.m1497h();
            crashDetailBean.f2493f = c1973d.f2601c.f2407k;
            crashDetailBean.f2494g = c1973d.f2601c.m1506q();
            crashDetailBean.f2500m = c1973d.f2601c.m1495g();
            crashDetailBean.f2501n = str;
            crashDetailBean.f2502o = str2;
            str5 = "";
            if (str3 != null) {
                String[] strArrSplit = str3.split("\n");
                str5 = strArrSplit.length > 0 ? strArrSplit[0] : "";
                str6 = str3;
            } else {
                str6 = "";
            }
            crashDetailBean.f2503p = str5;
            crashDetailBean.f2504q = str6;
            crashDetailBean.f2505r = System.currentTimeMillis();
            crashDetailBean.f2508u = C2023z.m1904a(crashDetailBean.f2504q.getBytes());
            crashDetailBean.f2513z = C2023z.m1907a(C1972c.f2570f, false);
            crashDetailBean.f2464A = c1973d.f2601c.f2400d;
            crashDetailBean.f2465B = threadCurrentThread.getName() + "(" + threadCurrentThread.getId() + ")";
            crashDetailBean.f2472I = c1973d.f2601c.m1508s();
            crashDetailBean.f2495h = c1973d.f2601c.m1505p();
            crashDetailBean.f2476M = c1973d.f2601c.f2382a;
            crashDetailBean.f2477N = c1973d.f2601c.m1481a();
            if (!C1972c.m1609a().m1634p()) {
                c1973d.f2602d.m1607d(crashDetailBean);
            }
            crashDetailBean.f2480Q = c1973d.f2601c.m1515z();
            crashDetailBean.f2481R = c1973d.f2601c.m1473A();
            crashDetailBean.f2482S = c1973d.f2601c.m1509t();
            crashDetailBean.f2483T = c1973d.f2601c.m1514y();
            crashDetailBean.f2512y = C2022y.m1879a();
            if (crashDetailBean.f2478O == null) {
                crashDetailBean.f2478O = new LinkedHashMap();
            }
            if (map != null) {
                crashDetailBean.f2478O.putAll(map);
            }
            C1971b.m1593a(str4, C2023z.m1897a(), c1973d.f2601c.f2400d, threadCurrentThread.getName(), str + "\n" + str2 + "\n" + str3, crashDetailBean);
            if (!c1973d.f2602d.m1604a(crashDetailBean)) {
                c1973d.f2602d.m1602a(crashDetailBean, 3000L, false);
            }
            C2021x.m1873e("[ExtraCrashManager] Successfully handled.", new Object[0]);
        } catch (Throwable th) {
            try {
                if (!C2021x.m1867a(th)) {
                    th.printStackTrace();
                }
                C2021x.m1873e("[ExtraCrashManager] Successfully handled.", new Object[0]);
            } catch (Throwable th2) {
                C2021x.m1873e("[ExtraCrashManager] Successfully handled.", new Object[0]);
                throw th2;
            }
        }
    }

    private C1973d(Context context) {
        C1972c c1972cM1609a = C1972c.m1609a();
        if (c1972cM1609a == null) {
            return;
        }
        this.f2600b = C1961a.m1544a();
        this.f2601c = C1958a.m1471a(context);
        this.f2602d = c1972cM1609a.f2581p;
        this.f2603e = context;
        C2020w.m1858a().m1860a(new Runnable() { // from class: com.tencent.bugly.crashreport.crash.d.1
            @Override // java.lang.Runnable
            public final void run() {
                C1973d.m1642a(C1973d.this);
            }
        });
    }

    /* JADX INFO: renamed from: a */
    public static C1973d m1641a(Context context) {
        if (f2599a == null) {
            f2599a = new C1973d(context);
        }
        return f2599a;
    }

    /* JADX INFO: renamed from: a */
    public static void m1644a(final Thread thread, final int i, final String str, final String str2, final String str3, final Map<String, String> map) {
        C2020w.m1858a().m1860a(new Runnable() { // from class: com.tencent.bugly.crashreport.crash.d.2
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    if (C1973d.f2599a != null) {
                        C1973d.m1643a(C1973d.f2599a, thread, i, str, str2, str3, map);
                    } else {
                        C2021x.m1873e("[ExtraCrashManager] Extra crash manager has not been initialized.", new Object[0]);
                    }
                } catch (Throwable th) {
                    if (!C2021x.m1870b(th)) {
                        th.printStackTrace();
                    }
                    C2021x.m1873e("[ExtraCrashManager] Crash error %s %s %s", str, str2, str3);
                }
            }
        });
    }
}
