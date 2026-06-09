package com.tencent.bugly.crashreport.biz;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.biz.C1955a.AnonymousClass2;
import com.tencent.bugly.crashreport.common.info.C1958a;
import com.tencent.bugly.crashreport.common.strategy.C1961a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.C2020w;
import com.tencent.bugly.proguard.C2021x;
import com.tencent.bugly.proguard.C2023z;

/* JADX INFO: renamed from: com.tencent.bugly.crashreport.biz.b */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public class C1956b {

    /* JADX INFO: renamed from: a */
    public static C1955a f2337a = null;

    /* JADX INFO: renamed from: b */
    private static boolean f2338b = false;

    /* JADX INFO: renamed from: c */
    private static int f2339c = 10;

    /* JADX INFO: renamed from: d */
    private static long f2340d = 300000;

    /* JADX INFO: renamed from: e */
    private static long f2341e = 30000;

    /* JADX INFO: renamed from: f */
    private static long f2342f = 0;

    /* JADX INFO: renamed from: g */
    private static int f2343g = 0;

    /* JADX INFO: renamed from: h */
    private static long f2344h = 0;

    /* JADX INFO: renamed from: i */
    private static long f2345i = 0;

    /* JADX INFO: renamed from: j */
    private static long f2346j = 0;

    /* JADX INFO: renamed from: k */
    private static Application.ActivityLifecycleCallbacks f2347k = null;

    /* JADX INFO: renamed from: l */
    private static Class<?> f2348l = null;

    /* JADX INFO: renamed from: m */
    private static boolean f2349m = true;

    /* JADX INFO: renamed from: a */
    static /* synthetic */ String m1441a(String str, String str2) {
        return C2023z.m1897a() + "  " + str + "  " + str2 + "\n";
    }

    /* JADX INFO: renamed from: g */
    static /* synthetic */ int m1456g() {
        int i = f2343g;
        f2343g = i + 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0068 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0069  */
    /* JADX INFO: renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void m1452c(android.content.Context r14, com.tencent.bugly.BuglyStrategy r15) {
        /*
            Method dump skipped, instruction units count: 273
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.biz.C1956b.m1452c(android.content.Context, com.tencent.bugly.BuglyStrategy):void");
    }

    /* JADX INFO: renamed from: a */
    public static void m1445a(final Context context, final BuglyStrategy buglyStrategy) {
        long appReportDelay;
        if (f2338b) {
            return;
        }
        boolean z = C1958a.m1471a(context).f2401e;
        f2349m = z;
        f2337a = new C1955a(context, z);
        f2338b = true;
        if (buglyStrategy != null) {
            f2348l = buglyStrategy.getUserInfoActivity();
            appReportDelay = buglyStrategy.getAppReportDelay();
        } else {
            appReportDelay = 0;
        }
        if (appReportDelay <= 0) {
            m1452c(context, buglyStrategy);
        } else {
            C2020w.m1858a().m1861a(new Runnable() { // from class: com.tencent.bugly.crashreport.biz.b.1
                @Override // java.lang.Runnable
                public final void run() {
                    C1956b.m1452c(context, buglyStrategy);
                }
            }, appReportDelay);
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m1443a(long j) {
        if (j < 0) {
            j = C1961a.m1544a().m1554c().f2440o;
        }
        f2342f = j;
    }

    /* JADX INFO: renamed from: a */
    public static void m1446a(StrategyBean strategyBean, boolean z) {
        C2020w c2020wM1858a;
        C1955a c1955a = f2337a;
        if (c1955a != null && !z && (c2020wM1858a = C2020w.m1858a()) != null) {
            c2020wM1858a.m1860a(c1955a.new AnonymousClass2());
        }
        if (strategyBean == null) {
            return;
        }
        if (strategyBean.f2440o > 0) {
            f2341e = strategyBean.f2440o;
        }
        if (strategyBean.f2445t > 0) {
            f2339c = strategyBean.f2445t;
        }
        if (strategyBean.f2446u > 0) {
            f2340d = strategyBean.f2446u;
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m1442a() {
        C1955a c1955a = f2337a;
        if (c1955a != null) {
            c1955a.m1438a(2, false, 0L);
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m1444a(Context context) {
        if (!f2338b || context == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 14) {
            Application application = context.getApplicationContext() instanceof Application ? (Application) context.getApplicationContext() : null;
            if (application != null) {
                try {
                    if (f2347k != null) {
                        application.unregisterActivityLifecycleCallbacks(f2347k);
                    }
                } catch (Exception e) {
                    if (!C2021x.m1867a(e)) {
                        e.printStackTrace();
                    }
                }
            }
        }
        f2338b = false;
    }
}
