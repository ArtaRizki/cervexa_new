package com.tencent.bugly.crashreport.crash;

import android.content.Context;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.info.AppInfo;
import com.tencent.bugly.crashreport.common.info.C1958a;
import com.tencent.bugly.crashreport.common.strategy.C1961a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.anr.C1970b;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import com.tencent.bugly.proguard.C2013p;
import com.tencent.bugly.proguard.C2015r;
import com.tencent.bugly.proguard.C2018u;
import com.tencent.bugly.proguard.C2020w;
import com.tencent.bugly.proguard.C2021x;
import com.tencent.bugly.proguard.C2023z;
import com.tencent.bugly.proguard.InterfaceC2012o;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.tencent.bugly.crashreport.crash.c */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public final class C1972c {

    /* JADX INFO: renamed from: a */
    public static int f2565a = 0;

    /* JADX INFO: renamed from: b */
    public static boolean f2566b = false;

    /* JADX INFO: renamed from: c */
    public static int f2567c = 2;

    /* JADX INFO: renamed from: d */
    public static boolean f2568d = true;

    /* JADX INFO: renamed from: e */
    public static int f2569e = 20480;

    /* JADX INFO: renamed from: f */
    public static int f2570f = 20480;

    /* JADX INFO: renamed from: g */
    public static long f2571g = 604800000;

    /* JADX INFO: renamed from: h */
    public static String f2572h = null;

    /* JADX INFO: renamed from: i */
    public static boolean f2573i = false;

    /* JADX INFO: renamed from: j */
    public static String f2574j = null;

    /* JADX INFO: renamed from: k */
    public static int f2575k = 5000;

    /* JADX INFO: renamed from: l */
    public static boolean f2576l = true;

    /* JADX INFO: renamed from: m */
    public static boolean f2577m = false;

    /* JADX INFO: renamed from: n */
    public static String f2578n;

    /* JADX INFO: renamed from: o */
    public static String f2579o;

    /* JADX INFO: renamed from: r */
    private static C1972c f2580r;

    /* JADX INFO: renamed from: p */
    public final C1971b f2581p;

    /* JADX INFO: renamed from: q */
    private final Context f2582q;

    /* JADX INFO: renamed from: s */
    private final C1974e f2583s;

    /* JADX INFO: renamed from: t */
    private final NativeCrashHandler f2584t;

    /* JADX INFO: renamed from: u */
    private C1961a f2585u;

    /* JADX INFO: renamed from: v */
    private C2020w f2586v;

    /* JADX INFO: renamed from: w */
    private final C1970b f2587w;

    /* JADX INFO: renamed from: x */
    private Boolean f2588x;

    /* JADX INFO: renamed from: y */
    private int f2589y = 31;

    /* JADX INFO: renamed from: z */
    private boolean f2590z = false;

    private C1972c(int i, Context context, C2020w c2020w, boolean z, BuglyStrategy.C1948a c1948a, InterfaceC2012o interfaceC2012o, String str) {
        f2565a = i;
        Context contextM1891a = C2023z.m1891a(context);
        this.f2582q = contextM1891a;
        this.f2585u = C1961a.m1544a();
        this.f2586v = c2020w;
        C2018u c2018uM1839a = C2018u.m1839a();
        C2013p c2013pM1807a = C2013p.m1807a();
        this.f2581p = new C1971b(i, contextM1891a, c2018uM1839a, c2013pM1807a, this.f2585u, c1948a, interfaceC2012o);
        C1958a c1958aM1471a = C1958a.m1471a(contextM1891a);
        this.f2583s = new C1974e(contextM1891a, this.f2581p, this.f2585u, c1958aM1471a);
        NativeCrashHandler nativeCrashHandler = NativeCrashHandler.getInstance(contextM1891a, c1958aM1471a, this.f2581p, this.f2585u, c2020w, z, str);
        this.f2584t = nativeCrashHandler;
        c1958aM1471a.f2361E = nativeCrashHandler;
        this.f2587w = C1970b.m1567a(contextM1891a, this.f2585u, c1958aM1471a, c2020w, c2013pM1807a, this.f2581p, c1948a);
    }

    /* JADX INFO: renamed from: a */
    public static synchronized C1972c m1610a(int i, Context context, boolean z, BuglyStrategy.C1948a c1948a, InterfaceC2012o interfaceC2012o, String str) {
        if (f2580r == null) {
            f2580r = new C1972c(1004, context, C2020w.m1858a(), z, c1948a, null, null);
        }
        return f2580r;
    }

    /* JADX INFO: renamed from: a */
    public static synchronized C1972c m1609a() {
        return f2580r;
    }

    /* JADX INFO: renamed from: a */
    public final void m1615a(StrategyBean strategyBean) {
        this.f2583s.m1651a(strategyBean);
        this.f2584t.onStrategyChanged(strategyBean);
        this.f2587w.m1587c();
        C2020w.m1858a().m1861a(new AnonymousClass2(), 3000L);
    }

    /* JADX INFO: renamed from: b */
    public final boolean m1620b() {
        Boolean bool = this.f2588x;
        if (bool != null) {
            return bool.booleanValue();
        }
        String str = C1958a.m1472b().f2400d;
        List<C2015r> listM1824a = C2013p.m1807a().m1824a(1);
        ArrayList arrayList = new ArrayList();
        if (listM1824a != null && listM1824a.size() > 0) {
            for (C2015r c2015r : listM1824a) {
                if (str.equals(c2015r.f2862c)) {
                    this.f2588x = true;
                    arrayList.add(c2015r);
                }
            }
            if (arrayList.size() > 0) {
                C2013p.m1807a().m1826a(arrayList);
            }
            return true;
        }
        this.f2588x = false;
        return false;
    }

    /* JADX INFO: renamed from: c */
    public final synchronized void m1621c() {
        this.f2583s.m1650a();
        this.f2584t.setUserOpened(true);
        this.f2587w.m1583a(true);
    }

    /* JADX INFO: renamed from: d */
    public final synchronized void m1622d() {
        this.f2583s.m1653b();
        this.f2584t.setUserOpened(false);
        this.f2587w.m1583a(false);
    }

    /* JADX INFO: renamed from: e */
    public final void m1623e() {
        this.f2583s.m1653b();
    }

    /* JADX INFO: renamed from: f */
    public final void m1624f() {
        this.f2583s.m1650a();
    }

    /* JADX INFO: renamed from: g */
    public final void m1625g() {
        this.f2584t.setUserOpened(false);
    }

    /* JADX INFO: renamed from: h */
    public final void m1626h() {
        this.f2584t.setUserOpened(true);
    }

    /* JADX INFO: renamed from: i */
    public final void m1627i() {
        this.f2587w.m1583a(true);
    }

    /* JADX INFO: renamed from: j */
    public final void m1628j() {
        this.f2587w.m1583a(false);
    }

    /* JADX INFO: renamed from: k */
    public final void m1629k() {
        this.f2584t.enableCatchAnrTrace();
    }

    /* JADX INFO: renamed from: a */
    public final synchronized void m1619a(boolean z, boolean z2, boolean z3) {
        this.f2584t.testNativeCrash(z, z2, z3);
    }

    /* JADX INFO: renamed from: l */
    public final synchronized void m1630l() {
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (i < 30) {
                try {
                    C2021x.m1866a("try main sleep for make a test anr! try:%d/30 , kill it if you don't want to wait!", Integer.valueOf(i2));
                    C2023z.m1922b(5000L);
                    i = i2;
                } catch (Throwable th) {
                    if (C2021x.m1867a(th)) {
                        return;
                    }
                    th.printStackTrace();
                    return;
                }
            }
        }
    }

    /* JADX INFO: renamed from: m */
    public final boolean m1631m() {
        return this.f2587w.m1584a();
    }

    /* JADX INFO: renamed from: a */
    public final void m1617a(final Thread thread, final Throwable th, boolean z, String str, byte[] bArr, final boolean z2) {
        final boolean z3 = false;
        final String str2 = null;
        final byte[] bArr2 = null;
        this.f2586v.m1860a(new Runnable() { // from class: com.tencent.bugly.crashreport.crash.c.1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    C2021x.m1871c("post a throwable %b", Boolean.valueOf(z3));
                    C1972c.this.f2583s.m1652a(thread, th, false, str2, bArr2);
                    if (z2) {
                        C2021x.m1866a("clear user datas", new Object[0]);
                        C1958a.m1471a(C1972c.this.f2582q).m1510u();
                    }
                } catch (Throwable th2) {
                    if (!C2021x.m1870b(th2)) {
                        th2.printStackTrace();
                    }
                    C2021x.m1873e("java catch error: %s", th.toString());
                }
            }
        });
    }

    /* JADX INFO: renamed from: a */
    public final void m1616a(CrashDetailBean crashDetailBean) {
        this.f2581p.m1608e(crashDetailBean);
    }

    /* JADX INFO: renamed from: com.tencent.bugly.crashreport.crash.c$2, reason: invalid class name */
    /* JADX INFO: compiled from: BUGLY */
    final class AnonymousClass2 extends Thread {
        AnonymousClass2() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            List<CrashDetailBean> list;
            if (C2023z.m1911a(C1972c.this.f2582q, "local_crash_lock", 10000L)) {
                List<CrashDetailBean> listM1601a = C1972c.this.f2581p.m1601a();
                if (listM1601a != null && listM1601a.size() > 0) {
                    C2021x.m1871c("Size of crash list: %s", Integer.valueOf(listM1601a.size()));
                    int size = listM1601a.size();
                    if (size > 20) {
                        ArrayList arrayList = new ArrayList();
                        Collections.sort(listM1601a);
                        for (int i = 0; i < 20; i++) {
                            arrayList.add(listM1601a.get((size - 1) - i));
                        }
                        list = arrayList;
                    } else {
                        list = listM1601a;
                    }
                    C1972c.this.f2581p.m1603a(list, 0L, false, false, false);
                }
                C2023z.m1926b(C1972c.this.f2582q, "local_crash_lock");
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public final void m1614a(long j) {
        C2020w.m1858a().m1861a(new AnonymousClass2(), j);
    }

    /* JADX INFO: renamed from: n */
    public final void m1632n() {
        this.f2584t.checkUploadRecordCrash();
    }

    /* JADX INFO: renamed from: o */
    public final void m1633o() {
        if (C1958a.m1472b().f2400d.equals(AppInfo.m1463a(this.f2582q))) {
            this.f2584t.removeEmptyNativeRecordFiles();
        }
    }

    /* JADX INFO: renamed from: a */
    public final void m1613a(int i) {
        this.f2589y = i;
    }

    /* JADX INFO: renamed from: a */
    public final void m1618a(boolean z) {
        this.f2590z = z;
    }

    /* JADX INFO: renamed from: p */
    public final boolean m1634p() {
        return this.f2590z;
    }

    /* JADX INFO: renamed from: q */
    public final boolean m1635q() {
        return (this.f2589y & 16) > 0;
    }

    /* JADX INFO: renamed from: r */
    public final boolean m1636r() {
        return (this.f2589y & 8) > 0;
    }

    /* JADX INFO: renamed from: s */
    public final boolean m1637s() {
        return (this.f2589y & 4) > 0;
    }

    /* JADX INFO: renamed from: t */
    public final boolean m1638t() {
        return (this.f2589y & 2) > 0;
    }

    /* JADX INFO: renamed from: u */
    public final boolean m1639u() {
        return (this.f2589y & 1) > 0;
    }
}
