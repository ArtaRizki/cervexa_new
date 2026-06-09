package com.tencent.bugly.crashreport.crash;

import android.content.Context;
import android.os.Process;
import com.tencent.bugly.crashreport.common.info.C1958a;
import com.tencent.bugly.crashreport.common.info.C1959b;
import com.tencent.bugly.crashreport.common.strategy.C1961a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.C2021x;
import com.tencent.bugly.proguard.C2022y;
import com.tencent.bugly.proguard.C2023z;
import java.lang.Thread;
import java.util.HashMap;

/* JADX INFO: renamed from: com.tencent.bugly.crashreport.crash.e */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public final class C1974e implements Thread.UncaughtExceptionHandler {

    /* JADX INFO: renamed from: h */
    private static String f2611h;

    /* JADX INFO: renamed from: i */
    private static final Object f2612i = new Object();

    /* JADX INFO: renamed from: a */
    private Context f2613a;

    /* JADX INFO: renamed from: b */
    private C1971b f2614b;

    /* JADX INFO: renamed from: c */
    private C1961a f2615c;

    /* JADX INFO: renamed from: d */
    private C1958a f2616d;

    /* JADX INFO: renamed from: e */
    private Thread.UncaughtExceptionHandler f2617e;

    /* JADX INFO: renamed from: f */
    private Thread.UncaughtExceptionHandler f2618f;

    /* JADX INFO: renamed from: g */
    private boolean f2619g = false;

    /* JADX INFO: renamed from: j */
    private int f2620j;

    public C1974e(Context context, C1971b c1971b, C1961a c1961a, C1958a c1958a) {
        this.f2613a = context;
        this.f2614b = c1971b;
        this.f2615c = c1961a;
        this.f2616d = c1958a;
    }

    /* JADX INFO: renamed from: a */
    public final synchronized void m1650a() {
        if (this.f2620j >= 10) {
            C2021x.m1866a("java crash handler over %d, no need set.", 10);
            return;
        }
        this.f2619g = true;
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (defaultUncaughtExceptionHandler != null) {
            if (getClass().getName().equals(defaultUncaughtExceptionHandler.getClass().getName())) {
                return;
            }
            if ("com.android.internal.os.RuntimeInit$UncaughtHandler".equals(defaultUncaughtExceptionHandler.getClass().getName())) {
                C2021x.m1866a("backup system java handler: %s", defaultUncaughtExceptionHandler.toString());
                this.f2618f = defaultUncaughtExceptionHandler;
                this.f2617e = defaultUncaughtExceptionHandler;
            } else {
                C2021x.m1866a("backup java handler: %s", defaultUncaughtExceptionHandler.toString());
                this.f2617e = defaultUncaughtExceptionHandler;
            }
        }
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.f2620j++;
        C2021x.m1866a("registered java monitor: %s", toString());
    }

    /* JADX INFO: renamed from: b */
    public final synchronized void m1653b() {
        this.f2619g = false;
        C2021x.m1866a("close java monitor!", new Object[0]);
        if (Thread.getDefaultUncaughtExceptionHandler().getClass().getName().contains("bugly")) {
            C2021x.m1866a("Java monitor to unregister: %s", toString());
            Thread.setDefaultUncaughtExceptionHandler(this.f2617e);
            this.f2620j--;
        }
    }

    /* JADX INFO: renamed from: b */
    private CrashDetailBean m1648b(Thread thread, Throwable th, boolean z, String str, byte[] bArr) {
        String strM1645a;
        if (th == null) {
            C2021x.m1872d("We can do nothing with a null throwable.", new Object[0]);
            return null;
        }
        boolean zM1631m = C1972c.m1609a().m1631m();
        String str2 = (zM1631m && z) ? " This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful![Bugly]" : "";
        if (zM1631m && z) {
            C2021x.m1873e("This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful!", new Object[0]);
        }
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        crashDetailBean.f2466C = C1959b.m1529g();
        crashDetailBean.f2467D = C1959b.m1525e();
        crashDetailBean.f2468E = C1959b.m1533i();
        crashDetailBean.f2469F = this.f2616d.m1500k();
        crashDetailBean.f2470G = this.f2616d.m1499j();
        crashDetailBean.f2471H = this.f2616d.m1501l();
        crashDetailBean.f2510w = C2023z.m1899a(this.f2613a, C1972c.f2569e, (String) null);
        crashDetailBean.f2512y = C2022y.m1879a();
        Object[] objArr = new Object[1];
        objArr[0] = Integer.valueOf(crashDetailBean.f2512y == null ? 0 : crashDetailBean.f2512y.length);
        C2021x.m1866a("user log size:%d", objArr);
        crashDetailBean.f2489b = z ? 0 : 2;
        crashDetailBean.f2492e = this.f2616d.m1497h();
        crashDetailBean.f2493f = this.f2616d.f2407k;
        crashDetailBean.f2494g = this.f2616d.m1506q();
        crashDetailBean.f2500m = this.f2616d.m1495g();
        String name = th.getClass().getName();
        String strM1649b = m1649b(th, 1000);
        if (strM1649b == null) {
            strM1649b = "";
        }
        Object[] objArr2 = new Object[2];
        objArr2[0] = Integer.valueOf(th.getStackTrace().length);
        objArr2[1] = Boolean.valueOf(th.getCause() != null);
        C2021x.m1873e("stack frame :%d, has cause %b", objArr2);
        String string = th.getStackTrace().length > 0 ? th.getStackTrace()[0].toString() : "";
        Throwable cause = th;
        while (cause != null && cause.getCause() != null) {
            cause = cause.getCause();
        }
        if (cause != null && cause != th) {
            crashDetailBean.f2501n = cause.getClass().getName();
            crashDetailBean.f2502o = m1649b(cause, 1000);
            if (crashDetailBean.f2502o == null) {
                crashDetailBean.f2502o = "";
            }
            if (cause.getStackTrace().length > 0) {
                crashDetailBean.f2503p = cause.getStackTrace()[0].toString();
            }
            StringBuilder sb = new StringBuilder();
            sb.append(name);
            sb.append(":");
            sb.append(strM1649b);
            sb.append("\n");
            sb.append(string);
            sb.append("\n......");
            sb.append("\nCaused by:\n");
            sb.append(crashDetailBean.f2501n);
            sb.append(":");
            sb.append(crashDetailBean.f2502o);
            sb.append("\n");
            strM1645a = m1645a(cause, C1972c.f2570f);
            sb.append(strM1645a);
            crashDetailBean.f2504q = sb.toString();
        } else {
            crashDetailBean.f2501n = name;
            crashDetailBean.f2502o = strM1649b + str2;
            if (crashDetailBean.f2502o == null) {
                crashDetailBean.f2502o = "";
            }
            crashDetailBean.f2503p = string;
            strM1645a = m1645a(th, C1972c.f2570f);
            crashDetailBean.f2504q = strM1645a;
        }
        crashDetailBean.f2505r = System.currentTimeMillis();
        crashDetailBean.f2508u = C2023z.m1904a(crashDetailBean.f2504q.getBytes());
        try {
            crashDetailBean.f2513z = C2023z.m1907a(C1972c.f2570f, false);
            crashDetailBean.f2464A = this.f2616d.f2400d;
            crashDetailBean.f2465B = thread.getName() + "(" + thread.getId() + ")";
            crashDetailBean.f2513z.put(crashDetailBean.f2465B, strM1645a);
            crashDetailBean.f2472I = this.f2616d.m1508s();
            crashDetailBean.f2495h = this.f2616d.m1505p();
            crashDetailBean.f2496i = this.f2616d.m1474B();
            crashDetailBean.f2476M = this.f2616d.f2382a;
            crashDetailBean.f2477N = this.f2616d.m1481a();
            if (z) {
                this.f2614b.m1607d(crashDetailBean);
            } else {
                boolean z2 = str != null && str.length() > 0;
                boolean z3 = bArr != null && bArr.length > 0;
                if (z2) {
                    crashDetailBean.f2478O = new HashMap(1);
                    crashDetailBean.f2478O.put("UserData", str);
                }
                if (z3) {
                    crashDetailBean.f2484U = bArr;
                }
            }
            crashDetailBean.f2480Q = this.f2616d.m1515z();
            crashDetailBean.f2481R = this.f2616d.m1473A();
            crashDetailBean.f2482S = this.f2616d.m1509t();
            crashDetailBean.f2483T = this.f2616d.m1514y();
        } catch (Throwable th2) {
            C2021x.m1873e("handle crash error %s", th2.toString());
        }
        return crashDetailBean;
    }

    /* JADX INFO: renamed from: a */
    private static boolean m1647a(Thread thread) {
        synchronized (f2612i) {
            if (f2611h != null && thread.getName().equals(f2611h)) {
                return true;
            }
            f2611h = thread.getName();
            return false;
        }
    }

    /* JADX INFO: renamed from: a */
    public final void m1652a(Thread thread, Throwable th, boolean z, String str, byte[] bArr) {
        if (z) {
            C2021x.m1873e("Java Crash Happen cause by %s(%d)", thread.getName(), Long.valueOf(thread.getId()));
            if (m1647a(thread)) {
                C2021x.m1866a("this class has handled this exception", new Object[0]);
                if (this.f2618f != null) {
                    C2021x.m1866a("call system handler", new Object[0]);
                    this.f2618f.uncaughtException(thread, th);
                } else {
                    C2021x.m1873e("current process die", new Object[0]);
                    Process.killProcess(Process.myPid());
                    System.exit(1);
                }
            }
        } else {
            C2021x.m1873e("Java Catch Happen", new Object[0]);
        }
        try {
            if (!this.f2619g) {
                C2021x.m1871c("Java crash handler is disable. Just return.", new Object[0]);
                if (z) {
                    Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.f2617e;
                    if (uncaughtExceptionHandler != null && m1646a(uncaughtExceptionHandler)) {
                        C2021x.m1873e("sys default last handle start!", new Object[0]);
                        this.f2617e.uncaughtException(thread, th);
                        C2021x.m1873e("sys default last handle end!", new Object[0]);
                        return;
                    } else if (this.f2618f != null) {
                        C2021x.m1873e("system handle start!", new Object[0]);
                        this.f2618f.uncaughtException(thread, th);
                        C2021x.m1873e("system handle end!", new Object[0]);
                        return;
                    } else {
                        C2021x.m1873e("crashreport last handle start!", new Object[0]);
                        C2021x.m1873e("current process die", new Object[0]);
                        Process.killProcess(Process.myPid());
                        System.exit(1);
                        C2021x.m1873e("crashreport last handle end!", new Object[0]);
                        return;
                    }
                }
                return;
            }
            if (!this.f2615c.m1553b()) {
                C2021x.m1872d("no remote but still store!", new Object[0]);
            }
            if (!this.f2615c.m1554c().f2430e && this.f2615c.m1553b()) {
                C2021x.m1873e("crash report was closed by remote , will not upload to Bugly , print local for helpful!", new Object[0]);
                C1971b.m1593a(z ? "JAVA_CRASH" : "JAVA_CATCH", C2023z.m1897a(), this.f2616d.f2400d, thread.getName(), C2023z.m1902a(th), null);
                if (z) {
                    Thread.UncaughtExceptionHandler uncaughtExceptionHandler2 = this.f2617e;
                    if (uncaughtExceptionHandler2 != null && m1646a(uncaughtExceptionHandler2)) {
                        C2021x.m1873e("sys default last handle start!", new Object[0]);
                        this.f2617e.uncaughtException(thread, th);
                        C2021x.m1873e("sys default last handle end!", new Object[0]);
                        return;
                    } else if (this.f2618f != null) {
                        C2021x.m1873e("system handle start!", new Object[0]);
                        this.f2618f.uncaughtException(thread, th);
                        C2021x.m1873e("system handle end!", new Object[0]);
                        return;
                    } else {
                        C2021x.m1873e("crashreport last handle start!", new Object[0]);
                        C2021x.m1873e("current process die", new Object[0]);
                        Process.killProcess(Process.myPid());
                        System.exit(1);
                        C2021x.m1873e("crashreport last handle end!", new Object[0]);
                        return;
                    }
                }
                return;
            }
            CrashDetailBean crashDetailBeanM1648b = m1648b(thread, th, z, str, bArr);
            if (crashDetailBeanM1648b == null) {
                C2021x.m1873e("pkg crash datas fail!", new Object[0]);
                if (z) {
                    Thread.UncaughtExceptionHandler uncaughtExceptionHandler3 = this.f2617e;
                    if (uncaughtExceptionHandler3 != null && m1646a(uncaughtExceptionHandler3)) {
                        C2021x.m1873e("sys default last handle start!", new Object[0]);
                        this.f2617e.uncaughtException(thread, th);
                        C2021x.m1873e("sys default last handle end!", new Object[0]);
                        return;
                    } else if (this.f2618f != null) {
                        C2021x.m1873e("system handle start!", new Object[0]);
                        this.f2618f.uncaughtException(thread, th);
                        C2021x.m1873e("system handle end!", new Object[0]);
                        return;
                    } else {
                        C2021x.m1873e("crashreport last handle start!", new Object[0]);
                        C2021x.m1873e("current process die", new Object[0]);
                        Process.killProcess(Process.myPid());
                        System.exit(1);
                        C2021x.m1873e("crashreport last handle end!", new Object[0]);
                        return;
                    }
                }
                return;
            }
            C1971b.m1593a(z ? "JAVA_CRASH" : "JAVA_CATCH", C2023z.m1897a(), this.f2616d.f2400d, thread.getName(), C2023z.m1902a(th), crashDetailBeanM1648b);
            if (!this.f2614b.m1604a(crashDetailBeanM1648b)) {
                this.f2614b.m1602a(crashDetailBeanM1648b, 3000L, z);
            }
            if (z) {
                this.f2614b.m1606c(crashDetailBeanM1648b);
            }
            if (z) {
                Thread.UncaughtExceptionHandler uncaughtExceptionHandler4 = this.f2617e;
                if (uncaughtExceptionHandler4 != null && m1646a(uncaughtExceptionHandler4)) {
                    C2021x.m1873e("sys default last handle start!", new Object[0]);
                    this.f2617e.uncaughtException(thread, th);
                    C2021x.m1873e("sys default last handle end!", new Object[0]);
                } else if (this.f2618f != null) {
                    C2021x.m1873e("system handle start!", new Object[0]);
                    this.f2618f.uncaughtException(thread, th);
                    C2021x.m1873e("system handle end!", new Object[0]);
                } else {
                    C2021x.m1873e("crashreport last handle start!", new Object[0]);
                    C2021x.m1873e("current process die", new Object[0]);
                    Process.killProcess(Process.myPid());
                    System.exit(1);
                    C2021x.m1873e("crashreport last handle end!", new Object[0]);
                }
            }
        } catch (Throwable th2) {
            try {
                if (!C2021x.m1867a(th2)) {
                    th2.printStackTrace();
                }
                if (z) {
                    Thread.UncaughtExceptionHandler uncaughtExceptionHandler5 = this.f2617e;
                    if (uncaughtExceptionHandler5 != null && m1646a(uncaughtExceptionHandler5)) {
                        C2021x.m1873e("sys default last handle start!", new Object[0]);
                        this.f2617e.uncaughtException(thread, th);
                        C2021x.m1873e("sys default last handle end!", new Object[0]);
                    } else if (this.f2618f != null) {
                        C2021x.m1873e("system handle start!", new Object[0]);
                        this.f2618f.uncaughtException(thread, th);
                        C2021x.m1873e("system handle end!", new Object[0]);
                    } else {
                        C2021x.m1873e("crashreport last handle start!", new Object[0]);
                        C2021x.m1873e("current process die", new Object[0]);
                        Process.killProcess(Process.myPid());
                        System.exit(1);
                        C2021x.m1873e("crashreport last handle end!", new Object[0]);
                    }
                }
            } catch (Throwable th3) {
                if (z) {
                    Thread.UncaughtExceptionHandler uncaughtExceptionHandler6 = this.f2617e;
                    if (uncaughtExceptionHandler6 != null && m1646a(uncaughtExceptionHandler6)) {
                        C2021x.m1873e("sys default last handle start!", new Object[0]);
                        this.f2617e.uncaughtException(thread, th);
                        C2021x.m1873e("sys default last handle end!", new Object[0]);
                    } else if (this.f2618f != null) {
                        C2021x.m1873e("system handle start!", new Object[0]);
                        this.f2618f.uncaughtException(thread, th);
                        C2021x.m1873e("system handle end!", new Object[0]);
                    } else {
                        C2021x.m1873e("crashreport last handle start!", new Object[0]);
                        C2021x.m1873e("current process die", new Object[0]);
                        Process.killProcess(Process.myPid());
                        System.exit(1);
                        C2021x.m1873e("crashreport last handle end!", new Object[0]);
                    }
                }
                throw th3;
            }
        }
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final void uncaughtException(Thread thread, Throwable th) {
        synchronized (f2612i) {
            m1652a(thread, th, true, null, null);
        }
    }

    /* JADX INFO: renamed from: a */
    private static boolean m1646a(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        if (uncaughtExceptionHandler == null) {
            return true;
        }
        String name = uncaughtExceptionHandler.getClass().getName();
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            String className = stackTraceElement.getClassName();
            String methodName = stackTraceElement.getMethodName();
            if (name.equals(className) && "uncaughtException".equals(methodName)) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: renamed from: a */
    public final synchronized void m1651a(StrategyBean strategyBean) {
        if (strategyBean != null) {
            if (strategyBean.f2430e != this.f2619g) {
                C2021x.m1866a("java changed to %b", Boolean.valueOf(strategyBean.f2430e));
                if (strategyBean.f2430e) {
                    m1650a();
                    return;
                }
                m1653b();
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private static String m1645a(Throwable th, int i) {
        if (th == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        try {
            if (th.getStackTrace() != null) {
                for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                    if (i > 0 && sb.length() >= i) {
                        sb.append("\n[Stack over limit size :" + i + " , has been cutted !]");
                        return sb.toString();
                    }
                    sb.append(stackTraceElement.toString());
                    sb.append("\n");
                }
            }
        } catch (Throwable th2) {
            C2021x.m1873e("gen stack error %s", th2.toString());
        }
        return sb.toString();
    }

    /* JADX INFO: renamed from: b */
    private static String m1649b(Throwable th, int i) {
        if (th.getMessage() == null) {
            return "";
        }
        if (th.getMessage().length() <= 1000) {
            return th.getMessage();
        }
        return th.getMessage().substring(0, 1000) + "\n[Message over limit size:1000, has been cutted!]";
    }
}
