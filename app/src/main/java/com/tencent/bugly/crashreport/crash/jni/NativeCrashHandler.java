package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import android.os.Build;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.InterfaceC1953a;
import com.tencent.bugly.crashreport.common.info.C1958a;
import com.tencent.bugly.crashreport.common.strategy.C1961a;
import com.tencent.bugly.crashreport.crash.C1971b;
import com.tencent.bugly.crashreport.crash.C1972c;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.proguard.C2020w;
import com.tencent.bugly.proguard.C2021x;
import com.tencent.bugly.proguard.C2023z;
import java.io.File;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public class NativeCrashHandler implements InterfaceC1953a {

    /* JADX INFO: renamed from: a */
    private static NativeCrashHandler f2639a = null;

    /* JADX INFO: renamed from: b */
    private static int f2640b = 1;

    /* JADX INFO: renamed from: m */
    private static boolean f2641m = false;

    /* JADX INFO: renamed from: n */
    private static boolean f2642n = false;

    /* JADX INFO: renamed from: p */
    private static boolean f2643p = true;

    /* JADX INFO: renamed from: c */
    private final Context f2644c;

    /* JADX INFO: renamed from: d */
    private final C1958a f2645d;

    /* JADX INFO: renamed from: e */
    private final C2020w f2646e;

    /* JADX INFO: renamed from: f */
    private NativeExceptionHandler f2647f;

    /* JADX INFO: renamed from: g */
    private String f2648g;

    /* JADX INFO: renamed from: h */
    private final boolean f2649h;

    /* JADX INFO: renamed from: i */
    private boolean f2650i = false;

    /* JADX INFO: renamed from: j */
    private boolean f2651j = false;

    /* JADX INFO: renamed from: k */
    private boolean f2652k = false;

    /* JADX INFO: renamed from: l */
    private boolean f2653l = false;

    /* JADX INFO: renamed from: o */
    private C1971b f2654o;

    protected native boolean appendNativeLog(String str, String str2, String str3);

    protected native boolean appendWholeNativeLog(String str);

    protected native String getNativeKeyValueList();

    protected native String getNativeLog();

    protected native boolean putNativeKeyValue(String str, String str2);

    protected native String regist(String str, boolean z, int i);

    protected native String removeNativeKeyValue(String str);

    protected native void setNativeInfo(int i, String str);

    protected native void testCrash();

    protected native String unregist();

    /* JADX INFO: renamed from: a */
    static /* synthetic */ boolean m1660a(NativeCrashHandler nativeCrashHandler, int i, String str) {
        return nativeCrashHandler.m1659a(999, str);
    }

    private NativeCrashHandler(Context context, C1958a c1958a, C1971b c1971b, C2020w c2020w, boolean z, String str) {
        this.f2644c = C2023z.m1891a(context);
        try {
            if (C2023z.m1914a(str)) {
                str = context.getDir("bugly", 0).getAbsolutePath();
            }
        } catch (Throwable unused) {
            str = "/data/data/" + C1958a.m1471a(context).f2399c + "/app_bugly";
        }
        this.f2654o = c1971b;
        this.f2648g = str;
        this.f2645d = c1958a;
        this.f2646e = c2020w;
        this.f2649h = z;
        this.f2647f = new C1978a(context, c1958a, c1971b, C1961a.m1544a());
    }

    public static synchronized NativeCrashHandler getInstance(Context context, C1958a c1958a, C1971b c1971b, C1961a c1961a, C2020w c2020w, boolean z, String str) {
        if (f2639a == null) {
            f2639a = new NativeCrashHandler(context, c1958a, c1971b, c2020w, z, str);
        }
        return f2639a;
    }

    public static synchronized NativeCrashHandler getInstance() {
        return f2639a;
    }

    public synchronized String getDumpFilePath() {
        return this.f2648g;
    }

    public synchronized void setDumpFilePath(String str) {
        this.f2648g = str;
    }

    public static void setShouldHandleInJava(boolean z) {
        f2643p = z;
        NativeCrashHandler nativeCrashHandler = f2639a;
        if (nativeCrashHandler != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(z);
            nativeCrashHandler.m1659a(999, sb.toString());
        }
    }

    public static boolean isShouldHandleInJava() {
        return f2643p;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(17:13|(1:15)(16:17|(1:19)|74|21|(1:23)|24|(1:26)|27|(1:29)(1:30)|31|(1:33)(1:34)|35|(1:37)|38|39|40)|16|74|21|(0)|24|(0)|27|(0)(0)|31|(0)(0)|35|(0)|38|39|40) */
    /* JADX WARN: Removed duplicated region for block: B:23:0x007e A[Catch: all -> 0x008c, TryCatch #3 {all -> 0x008c, blocks: (B:21:0x0074, B:23:0x007e, B:24:0x0080, B:26:0x008a), top: B:74:0x0074 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x008a A[Catch: all -> 0x008c, TRY_LEAVE, TryCatch #3 {all -> 0x008c, blocks: (B:21:0x0074, B:23:0x007e, B:24:0x0080, B:26:0x008a), top: B:74:0x0074 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0090 A[Catch: all -> 0x00f1, TryCatch #0 {all -> 0x00f1, blocks: (B:11:0x0015, B:13:0x001f, B:15:0x0051, B:16:0x005b, B:27:0x008c, B:29:0x0090, B:31:0x009f, B:33:0x00a3, B:35:0x00b2, B:37:0x00ca, B:38:0x00e0, B:34:0x00ab, B:30:0x0098, B:17:0x0063, B:19:0x0069), top: B:69:0x0015, outer: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0098 A[Catch: all -> 0x00f1, TryCatch #0 {all -> 0x00f1, blocks: (B:11:0x0015, B:13:0x001f, B:15:0x0051, B:16:0x005b, B:27:0x008c, B:29:0x0090, B:31:0x009f, B:33:0x00a3, B:35:0x00b2, B:37:0x00ca, B:38:0x00e0, B:34:0x00ab, B:30:0x0098, B:17:0x0063, B:19:0x0069), top: B:69:0x0015, outer: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a3 A[Catch: all -> 0x00f1, TryCatch #0 {all -> 0x00f1, blocks: (B:11:0x0015, B:13:0x001f, B:15:0x0051, B:16:0x005b, B:27:0x008c, B:29:0x0090, B:31:0x009f, B:33:0x00a3, B:35:0x00b2, B:37:0x00ca, B:38:0x00e0, B:34:0x00ab, B:30:0x0098, B:17:0x0063, B:19:0x0069), top: B:69:0x0015, outer: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00ab A[Catch: all -> 0x00f1, TryCatch #0 {all -> 0x00f1, blocks: (B:11:0x0015, B:13:0x001f, B:15:0x0051, B:16:0x005b, B:27:0x008c, B:29:0x0090, B:31:0x009f, B:33:0x00a3, B:35:0x00b2, B:37:0x00ca, B:38:0x00e0, B:34:0x00ab, B:30:0x0098, B:17:0x0063, B:19:0x0069), top: B:69:0x0015, outer: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00ca A[Catch: all -> 0x00f1, TryCatch #0 {all -> 0x00f1, blocks: (B:11:0x0015, B:13:0x001f, B:15:0x0051, B:16:0x005b, B:27:0x008c, B:29:0x0090, B:31:0x009f, B:33:0x00a3, B:35:0x00b2, B:37:0x00ca, B:38:0x00e0, B:34:0x00ab, B:30:0x0098, B:17:0x0063, B:19:0x0069), top: B:69:0x0015, outer: #2 }] */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized void m1658a(boolean r11) {
        /*
            Method dump skipped, instruction units count: 462
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler.m1658a(boolean):void");
    }

    public synchronized void startNativeMonitor() {
        if (!this.f2651j && !this.f2650i) {
            String str = "Bugly";
            boolean z = !C2023z.m1914a(this.f2645d.f2410n);
            String str2 = this.f2645d.f2410n;
            if (z) {
                str = str2;
            } else {
                this.f2645d.getClass();
            }
            boolean zM1661a = m1661a(str, z);
            this.f2651j = zM1661a;
            if (zM1661a || this.f2650i) {
                m1658a(this.f2649h);
                if (f2641m) {
                    setNativeAppVersion(this.f2645d.f2407k);
                    setNativeAppChannel(this.f2645d.f2409m);
                    setNativeAppPackage(this.f2645d.f2399c);
                    setNativeUserId(this.f2645d.m1495g());
                    setNativeIsAppForeground(this.f2645d.m1481a());
                    setNativeLaunchTime(this.f2645d.f2382a);
                }
                return;
            }
            return;
        }
        m1658a(this.f2649h);
    }

    public void checkUploadRecordCrash() {
        this.f2646e.m1860a(new Runnable() { // from class: com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler.1
            @Override // java.lang.Runnable
            public final void run() throws Throwable {
                if (C2023z.m1911a(NativeCrashHandler.this.f2644c, "native_record_lock", 10000L)) {
                    if (!NativeCrashHandler.f2643p) {
                        NativeCrashHandler.m1660a(NativeCrashHandler.this, 999, Bugly.SDK_IS_DEV);
                    }
                    CrashDetailBean crashDetailBeanM1670a = C1979b.m1670a(NativeCrashHandler.this.f2644c, NativeCrashHandler.this.f2648g, NativeCrashHandler.this.f2647f);
                    if (crashDetailBeanM1670a != null) {
                        C2021x.m1866a("[Native] Get crash from native record.", new Object[0]);
                        if (!NativeCrashHandler.this.f2654o.m1604a(crashDetailBeanM1670a)) {
                            NativeCrashHandler.this.f2654o.m1602a(crashDetailBeanM1670a, 3000L, false);
                        }
                        C1979b.m1676a(false, NativeCrashHandler.this.f2648g);
                    }
                    NativeCrashHandler.this.m1669a();
                    C2023z.m1926b(NativeCrashHandler.this.f2644c, "native_record_lock");
                    return;
                }
                C2021x.m1866a("[Native] Failed to lock file for handling native crash record.", new Object[0]);
            }
        });
    }

    /* JADX INFO: renamed from: a */
    private static boolean m1661a(String str, boolean z) {
        boolean z2;
        try {
            C2021x.m1866a("[Native] Trying to load so: %s", str);
            if (z) {
                System.load(str);
            } else {
                System.loadLibrary(str);
            }
            try {
                C2021x.m1866a("[Native] Successfully loaded SO: %s", str);
                return true;
            } catch (Throwable th) {
                th = th;
                z2 = true;
                C2021x.m1872d(th.getMessage(), new Object[0]);
                C2021x.m1872d("[Native] Failed to load so: %s", str);
                return z2;
            }
        } catch (Throwable th2) {
            th = th2;
            z2 = false;
        }
    }

    /* JADX INFO: renamed from: c */
    private synchronized void m1666c() {
        if (!this.f2652k) {
            C2021x.m1872d("[Native] Native crash report has already unregistered.", new Object[0]);
            return;
        }
        try {
        } catch (Throwable unused) {
            C2021x.m1871c("[Native] Failed to close native crash report.", new Object[0]);
        }
        if (unregist() != null) {
            C2021x.m1866a("[Native] Successfully closed native crash report.", new Object[0]);
            this.f2652k = false;
            return;
        }
        try {
            C2023z.m1895a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "enableHandler", null, new Class[]{Boolean.TYPE}, new Object[]{false});
            this.f2652k = false;
            C2021x.m1866a("[Native] Successfully closed native crash report.", new Object[0]);
            return;
        } catch (Throwable unused2) {
            C2021x.m1871c("[Native] Failed to close native crash report.", new Object[0]);
            this.f2651j = false;
            this.f2650i = false;
            return;
        }
    }

    public void testNativeCrash() {
        if (!this.f2651j) {
            C2021x.m1872d("[Native] Bugly SO file has not been load.", new Object[0]);
        } else {
            testCrash();
        }
    }

    public void testNativeCrash(boolean z, boolean z2, boolean z3) {
        StringBuilder sb = new StringBuilder();
        sb.append(z);
        m1659a(16, sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(z2);
        m1659a(17, sb2.toString());
        StringBuilder sb3 = new StringBuilder();
        sb3.append(z3);
        m1659a(18, sb3.toString());
        testNativeCrash();
    }

    public void dumpAnrNativeStack() {
        m1659a(19, "1");
    }

    public NativeExceptionHandler getNativeExceptionHandler() {
        return this.f2647f;
    }

    /* JADX INFO: renamed from: a */
    protected final void m1669a() {
        long jM1918b = C2023z.m1918b() - C1972c.f2571g;
        long jM1918b2 = C2023z.m1918b() + 86400000;
        File file = new File(this.f2648g);
        if (file.exists() && file.isDirectory()) {
            try {
                File[] fileArrListFiles = file.listFiles();
                if (fileArrListFiles != null && fileArrListFiles.length != 0) {
                    int i = 0;
                    int i2 = 0;
                    for (File file2 : fileArrListFiles) {
                        long jLastModified = file2.lastModified();
                        if (jLastModified < jM1918b || jLastModified >= jM1918b2) {
                            C2021x.m1866a("[Native] Delete record file: %s", file2.getAbsolutePath());
                            i++;
                            if (file2.delete()) {
                                i2++;
                            }
                        }
                    }
                    C2021x.m1871c("[Native] Number of record files overdue: %d, has deleted: %d", Integer.valueOf(i), Integer.valueOf(i2));
                }
            } catch (Throwable th) {
                C2021x.m1867a(th);
            }
        }
    }

    public void removeEmptyNativeRecordFiles() {
        C1979b.m1680c(this.f2648g);
    }

    /* JADX INFO: renamed from: b */
    private synchronized void m1663b(boolean z) {
        if (z) {
            startNativeMonitor();
        } else {
            m1666c();
        }
    }

    public synchronized boolean isUserOpened() {
        return this.f2653l;
    }

    /* JADX INFO: renamed from: c */
    private synchronized void m1667c(boolean z) {
        if (this.f2653l != z) {
            C2021x.m1866a("user change native %b", Boolean.valueOf(z));
            this.f2653l = z;
        }
    }

    public synchronized void setUserOpened(boolean z) {
        m1667c(z);
        boolean zIsUserOpened = isUserOpened();
        C1961a c1961aM1544a = C1961a.m1544a();
        if (c1961aM1544a != null) {
            zIsUserOpened = zIsUserOpened && c1961aM1544a.m1554c().f2430e;
        }
        if (zIsUserOpened != this.f2652k) {
            C2021x.m1866a("native changed to %b", Boolean.valueOf(zIsUserOpened));
            m1663b(zIsUserOpened);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0031 A[Catch: all -> 0x0043, TRY_LEAVE, TryCatch #0 {, blocks: (B:5:0x0005, B:7:0x000b, B:8:0x001a, B:10:0x0026, B:14:0x002d, B:16:0x0031), top: B:22:0x0005 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void onStrategyChanged(com.tencent.bugly.crashreport.common.strategy.StrategyBean r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            r0 = 1
            r1 = 0
            if (r5 == 0) goto L1a
            boolean r2 = r5.f2430e     // Catch: java.lang.Throwable -> L43
            boolean r3 = r4.f2652k     // Catch: java.lang.Throwable -> L43
            if (r2 == r3) goto L1a
            java.lang.String r2 = "server native changed to %b"
            java.lang.Object[] r3 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> L43
            boolean r5 = r5.f2430e     // Catch: java.lang.Throwable -> L43
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)     // Catch: java.lang.Throwable -> L43
            r3[r1] = r5     // Catch: java.lang.Throwable -> L43
            com.tencent.bugly.proguard.C2021x.m1872d(r2, r3)     // Catch: java.lang.Throwable -> L43
        L1a:
            com.tencent.bugly.crashreport.common.strategy.a r5 = com.tencent.bugly.crashreport.common.strategy.C1961a.m1544a()     // Catch: java.lang.Throwable -> L43
            com.tencent.bugly.crashreport.common.strategy.StrategyBean r5 = r5.m1554c()     // Catch: java.lang.Throwable -> L43
            boolean r5 = r5.f2430e     // Catch: java.lang.Throwable -> L43
            if (r5 == 0) goto L2c
            boolean r5 = r4.f2653l     // Catch: java.lang.Throwable -> L43
            if (r5 == 0) goto L2c
            r5 = 1
            goto L2d
        L2c:
            r5 = 0
        L2d:
            boolean r2 = r4.f2652k     // Catch: java.lang.Throwable -> L43
            if (r5 == r2) goto L41
            java.lang.String r2 = "native changed to %b"
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> L43
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r5)     // Catch: java.lang.Throwable -> L43
            r0[r1] = r3     // Catch: java.lang.Throwable -> L43
            com.tencent.bugly.proguard.C2021x.m1866a(r2, r0)     // Catch: java.lang.Throwable -> L43
            r4.m1663b(r5)     // Catch: java.lang.Throwable -> L43
        L41:
            monitor-exit(r4)
            return
        L43:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler.onStrategyChanged(com.tencent.bugly.crashreport.common.strategy.StrategyBean):void");
    }

    public boolean appendLogToNative(String str, String str2, String str3) {
        if ((this.f2650i || this.f2651j) && f2641m && str != null && str2 != null && str3 != null) {
            try {
                if (this.f2651j) {
                    return appendNativeLog(str, str2, str3);
                }
                Boolean bool = (Boolean) C2023z.m1895a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "appendNativeLog", null, new Class[]{String.class, String.class, String.class}, new Object[]{str, str2, str3});
                if (bool != null) {
                    return bool.booleanValue();
                }
                return false;
            } catch (UnsatisfiedLinkError unused) {
                f2641m = false;
            } catch (Throwable th) {
                if (!C2021x.m1867a(th)) {
                    th.printStackTrace();
                }
                return false;
            }
        }
        return false;
    }

    public String getLogFromNative() {
        if ((!this.f2650i && !this.f2651j) || !f2641m) {
            return null;
        }
        try {
            if (this.f2651j) {
                return getNativeLog();
            }
            return (String) C2023z.m1895a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "getNativeLog", null, null, null);
        } catch (UnsatisfiedLinkError unused) {
            f2641m = false;
            return null;
        } catch (Throwable th) {
            if (!C2021x.m1867a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public boolean putKeyValueToNative(String str, String str2) {
        if ((this.f2650i || this.f2651j) && f2641m && str != null && str2 != null) {
            try {
                if (this.f2651j) {
                    return putNativeKeyValue(str, str2);
                }
                Boolean bool = (Boolean) C2023z.m1895a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "putNativeKeyValue", null, new Class[]{String.class, String.class}, new Object[]{str, str2});
                if (bool != null) {
                    return bool.booleanValue();
                }
                return false;
            } catch (UnsatisfiedLinkError unused) {
                f2641m = false;
            } catch (Throwable th) {
                if (!C2021x.m1867a(th)) {
                    th.printStackTrace();
                }
                return false;
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: a */
    private boolean m1659a(int i, String str) {
        if (this.f2651j && f2642n) {
            try {
                setNativeInfo(i, str);
                return true;
            } catch (UnsatisfiedLinkError unused) {
                f2642n = false;
            } catch (Throwable th) {
                if (!C2021x.m1867a(th)) {
                    th.printStackTrace();
                }
                return false;
            }
        }
        return false;
    }

    public boolean filterSigabrtSysLog() {
        return m1659a(998, "true");
    }

    public boolean setNativeAppVersion(String str) {
        return m1659a(10, str);
    }

    public boolean setNativeAppChannel(String str) {
        return m1659a(12, str);
    }

    public boolean setNativeAppPackage(String str) {
        return m1659a(13, str);
    }

    public boolean setNativeUserId(String str) {
        return m1659a(11, str);
    }

    @Override // com.tencent.bugly.crashreport.InterfaceC1953a
    public boolean setNativeIsAppForeground(boolean z) {
        return m1659a(14, z ? "true" : Bugly.SDK_IS_DEV);
    }

    public boolean setNativeLaunchTime(long j) {
        try {
            return m1659a(15, String.valueOf(j));
        } catch (NumberFormatException e) {
            if (C2021x.m1867a(e)) {
                return false;
            }
            e.printStackTrace();
            return false;
        }
    }

    public void enableCatchAnrTrace() {
        if (Build.VERSION.SDK_INT > 30 || Build.VERSION.SDK_INT < 23) {
            return;
        }
        f2640b |= 2;
    }

    public boolean isEnableCatchAnrTrace() {
        return (f2640b & 2) == 2;
    }
}
