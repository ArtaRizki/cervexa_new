package com.tencent.bugly.crashreport.crash.anr;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.FileObserver;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.info.AppInfo;
import com.tencent.bugly.crashreport.common.info.C1958a;
import com.tencent.bugly.crashreport.common.info.C1959b;
import com.tencent.bugly.crashreport.common.strategy.C1961a;
import com.tencent.bugly.crashreport.crash.C1971b;
import com.tencent.bugly.crashreport.crash.C1972c;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.anr.TraceFileHelper;
import com.tencent.bugly.proguard.C1982ab;
import com.tencent.bugly.proguard.C2013p;
import com.tencent.bugly.proguard.C2020w;
import com.tencent.bugly.proguard.C2021x;
import com.tencent.bugly.proguard.C2022y;
import com.tencent.bugly.proguard.C2023z;
import com.tencent.bugly.proguard.InterfaceC1983ac;
import com.tencent.bugly.proguard.RunnableC1981aa;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: renamed from: com.tencent.bugly.crashreport.crash.anr.b */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public final class C1970b implements InterfaceC1983ac {

    /* JADX INFO: renamed from: m */
    private static C1970b f2536m;

    /* JADX INFO: renamed from: c */
    private final Context f2539c;

    /* JADX INFO: renamed from: d */
    private final C1958a f2540d;

    /* JADX INFO: renamed from: e */
    private final C2020w f2541e;

    /* JADX INFO: renamed from: f */
    private String f2542f;

    /* JADX INFO: renamed from: g */
    private final C1971b f2543g;

    /* JADX INFO: renamed from: h */
    private FileObserver f2544h;

    /* JADX INFO: renamed from: j */
    private C1982ab f2546j;

    /* JADX INFO: renamed from: k */
    private int f2547k;

    /* JADX INFO: renamed from: a */
    private AtomicInteger f2537a = new AtomicInteger(0);

    /* JADX INFO: renamed from: b */
    private long f2538b = -1;

    /* JADX INFO: renamed from: i */
    private boolean f2545i = true;

    /* JADX INFO: renamed from: l */
    private ActivityManager.ProcessErrorStateInfo f2548l = new ActivityManager.ProcessErrorStateInfo();

    /* JADX INFO: renamed from: a */
    static /* synthetic */ boolean m1570a(C1970b c1970b, String str) {
        return str.startsWith("bugly_trace_");
    }

    /* JADX INFO: renamed from: a */
    public static C1970b m1567a(Context context, C1961a c1961a, C1958a c1958a, C2020w c2020w, C2013p c2013p, C1971b c1971b, BuglyStrategy.C1948a c1948a) {
        if (f2536m == null) {
            f2536m = new C1970b(context, c1961a, c1958a, c2020w, c1971b);
        }
        return f2536m;
    }

    private C1970b(Context context, C1961a c1961a, C1958a c1958a, C2020w c2020w, C1971b c1971b) {
        this.f2539c = C2023z.m1891a(context);
        this.f2542f = context.getDir("bugly", 0).getAbsolutePath();
        this.f2540d = c1958a;
        this.f2541e = c2020w;
        this.f2543g = c1971b;
    }

    /* JADX INFO: renamed from: a */
    private ActivityManager.ProcessErrorStateInfo m1565a(Context context, long j) {
        try {
            C2021x.m1871c("to find!", new Object[0]);
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            int i = 0;
            while (true) {
                C2021x.m1871c("waiting!", new Object[0]);
                List<ActivityManager.ProcessErrorStateInfo> processesInErrorState = activityManager.getProcessesInErrorState();
                if (processesInErrorState != null) {
                    for (ActivityManager.ProcessErrorStateInfo processErrorStateInfo : processesInErrorState) {
                        if (processErrorStateInfo.condition == 2) {
                            C2021x.m1871c("found!", new Object[0]);
                            return processErrorStateInfo;
                        }
                    }
                }
                C2023z.m1922b(500L);
                int i2 = i + 1;
                if (i >= 40) {
                    C2021x.m1871c("end!", new Object[0]);
                    return null;
                }
                i = i2;
            }
        } catch (Exception e) {
            C2021x.m1870b(e);
            return null;
        } catch (OutOfMemoryError e2) {
            this.f2548l.pid = Process.myPid();
            this.f2548l.shortMsg = "bugly sdk waitForAnrProcessStateChanged encount error:" + e2.getMessage();
            return this.f2548l;
        }
    }

    /* JADX INFO: renamed from: a */
    private CrashDetailBean m1566a(C1969a c1969a) {
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        try {
            crashDetailBean.f2466C = C1959b.m1529g();
            crashDetailBean.f2467D = C1959b.m1525e();
            crashDetailBean.f2468E = C1959b.m1533i();
            crashDetailBean.f2469F = this.f2540d.m1500k();
            crashDetailBean.f2470G = this.f2540d.m1499j();
            crashDetailBean.f2471H = this.f2540d.m1501l();
            if (!C1959b.m1537m()) {
                crashDetailBean.f2510w = C2023z.m1899a(this.f2539c, C1972c.f2569e, (String) null);
            }
            crashDetailBean.f2489b = 3;
            crashDetailBean.f2492e = this.f2540d.m1497h();
            crashDetailBean.f2493f = this.f2540d.f2407k;
            crashDetailBean.f2494g = this.f2540d.m1506q();
            crashDetailBean.f2500m = this.f2540d.m1495g();
            crashDetailBean.f2501n = "ANR_EXCEPTION";
            crashDetailBean.f2502o = c1969a.f2534f;
            crashDetailBean.f2504q = c1969a.f2535g;
            crashDetailBean.f2479P = new HashMap();
            crashDetailBean.f2479P.put("BUGLY_CR_01", c1969a.f2533e);
            int iIndexOf = crashDetailBean.f2504q != null ? crashDetailBean.f2504q.indexOf("\n") : -1;
            crashDetailBean.f2503p = iIndexOf > 0 ? crashDetailBean.f2504q.substring(0, iIndexOf) : "GET_FAIL";
            crashDetailBean.f2505r = c1969a.f2531c;
            if (crashDetailBean.f2504q != null) {
                crashDetailBean.f2508u = C2023z.m1904a(crashDetailBean.f2504q.getBytes());
            }
            crashDetailBean.f2513z = c1969a.f2530b;
            crashDetailBean.f2464A = c1969a.f2529a;
            crashDetailBean.f2465B = "main(1)";
            crashDetailBean.f2472I = this.f2540d.m1508s();
            crashDetailBean.f2495h = this.f2540d.m1505p();
            crashDetailBean.f2496i = this.f2540d.m1474B();
            crashDetailBean.f2509v = c1969a.f2532d;
            crashDetailBean.f2475L = this.f2540d.f2411o;
            crashDetailBean.f2476M = this.f2540d.f2382a;
            crashDetailBean.f2477N = this.f2540d.m1481a();
            if (!C1959b.m1537m()) {
                this.f2543g.m1607d(crashDetailBean);
            }
            crashDetailBean.f2480Q = this.f2540d.m1515z();
            crashDetailBean.f2481R = this.f2540d.m1473A();
            crashDetailBean.f2482S = this.f2540d.m1509t();
            crashDetailBean.f2483T = this.f2540d.m1514y();
            crashDetailBean.f2512y = C2022y.m1879a();
        } catch (Throwable th) {
            if (!C2021x.m1867a(th)) {
                th.printStackTrace();
            }
        }
        return crashDetailBean;
    }

    /* JADX INFO: renamed from: a */
    private static boolean m1571a(String str, String str2, String str3) throws Throwable {
        Throwable th;
        TraceFileHelper.C1967a targetDumpInfo = TraceFileHelper.readTargetDumpInfo(str3, str, true);
        if (targetDumpInfo == null || targetDumpInfo.f2528d == null || targetDumpInfo.f2528d.size() <= 0) {
            C2021x.m1873e("not found trace dump for %s", str3);
            return false;
        }
        File file = new File(str2);
        try {
            if (!file.exists()) {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            }
            if (!file.exists() || !file.canWrite()) {
                C2021x.m1873e("backup file create fail %s", str2);
                return false;
            }
            BufferedWriter bufferedWriter = null;
            try {
                try {
                    BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(file, false));
                    try {
                        String[] strArr = targetDumpInfo.f2528d.get("main");
                        int i = 3;
                        if (strArr != null && strArr.length >= 3) {
                            String str4 = strArr[0];
                            String str5 = strArr[1];
                            bufferedWriter2.write("\"main\" tid=" + strArr[2] + " :\n" + str4 + "\n" + str5 + "\n\n");
                            bufferedWriter2.flush();
                        }
                        for (Map.Entry<String, String[]> entry : targetDumpInfo.f2528d.entrySet()) {
                            if (!entry.getKey().equals("main")) {
                                if (entry.getValue() != null && entry.getValue().length >= i) {
                                    String str6 = entry.getValue()[0];
                                    String str7 = entry.getValue()[1];
                                    bufferedWriter2.write("\"" + entry.getKey() + "\" tid=" + entry.getValue()[2] + " :\n" + str6 + "\n" + str7 + "\n\n");
                                    bufferedWriter2.flush();
                                }
                                i = 3;
                            }
                        }
                        try {
                            bufferedWriter2.close();
                        } catch (IOException e) {
                            if (!C2021x.m1867a(e)) {
                                e.printStackTrace();
                            }
                        }
                        return true;
                    } catch (IOException e2) {
                        e = e2;
                        bufferedWriter = bufferedWriter2;
                        if (!C2021x.m1867a(e)) {
                            e.printStackTrace();
                        }
                        C2021x.m1873e("dump trace fail %s", e.getClass().getName() + ":" + e.getMessage());
                        if (bufferedWriter != null) {
                            try {
                                bufferedWriter.close();
                            } catch (IOException e3) {
                                if (!C2021x.m1867a(e3)) {
                                    e3.printStackTrace();
                                }
                            }
                        }
                        return false;
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedWriter = bufferedWriter2;
                        if (bufferedWriter != null) {
                            try {
                                bufferedWriter.close();
                                throw th;
                            } catch (IOException e4) {
                                if (!C2021x.m1867a(e4)) {
                                    e4.printStackTrace();
                                    throw th;
                                }
                                throw th;
                            }
                        }
                        throw th;
                    }
                } catch (IOException e5) {
                    e = e5;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Exception e6) {
            if (!C2021x.m1867a(e6)) {
                e6.printStackTrace();
            }
            C2021x.m1873e("backup file create error! %s  %s", e6.getClass().getName() + ":" + e6.getMessage(), str2);
            return false;
        }
    }

    /* JADX INFO: renamed from: a */
    public final boolean m1584a() {
        return this.f2537a.get() != 0;
    }

    /* JADX INFO: renamed from: a */
    private boolean m1569a(Context context, String str, ActivityManager.ProcessErrorStateInfo processErrorStateInfo, long j, Map<String, String> map) {
        C1969a c1969a = new C1969a();
        c1969a.f2531c = j;
        c1969a.f2529a = processErrorStateInfo != null ? processErrorStateInfo.processName : AppInfo.m1462a(Process.myPid());
        c1969a.f2534f = processErrorStateInfo != null ? processErrorStateInfo.shortMsg : "";
        c1969a.f2533e = processErrorStateInfo != null ? processErrorStateInfo.longMsg : "";
        c1969a.f2530b = map;
        Thread thread = Looper.getMainLooper().getThread();
        if (map != null) {
            Iterator<String> it = map.keySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String next = it.next();
                if (next.startsWith(thread.getName())) {
                    c1969a.f2535g = map.get(next);
                    break;
                }
            }
        }
        if (TextUtils.isEmpty(c1969a.f2535g)) {
            c1969a.f2535g = "main stack is null , some error may be encountered.";
        }
        Object[] objArr = new Object[7];
        objArr[0] = Long.valueOf(c1969a.f2531c);
        objArr[1] = c1969a.f2532d;
        objArr[2] = c1969a.f2529a;
        objArr[3] = c1969a.f2535g;
        objArr[4] = c1969a.f2534f;
        objArr[5] = c1969a.f2533e;
        objArr[6] = Integer.valueOf(c1969a.f2530b == null ? 0 : c1969a.f2530b.size());
        C2021x.m1871c("anr tm:%d\ntr:%s\nproc:%s\nmain stack:%s\nsMsg:%s\n lMsg:%s\n threads:%d", objArr);
        C2021x.m1866a("found visiable anr , start to upload!", new Object[0]);
        CrashDetailBean crashDetailBeanM1566a = m1566a(c1969a);
        if (crashDetailBeanM1566a == null) {
            C2021x.m1873e("pack anr fail!", new Object[0]);
            return false;
        }
        C1972c.m1609a().m1616a(crashDetailBeanM1566a);
        if (crashDetailBeanM1566a.f2488a >= 0) {
            C2021x.m1866a("backup anr record success!", new Object[0]);
        } else {
            C2021x.m1872d("backup anr record fail!", new Object[0]);
        }
        if (str != null && new File(str).exists()) {
            c1969a.f2532d = new File(this.f2542f, "bugly_trace_" + j + ".txt").getAbsolutePath();
            this.f2537a.set(3);
            if (m1571a(str, c1969a.f2532d, c1969a.f2529a)) {
                C2021x.m1866a("backup trace success", new Object[0]);
            }
        } else {
            File fileM1579h = m1579h();
            C2021x.m1866a("traceFile is %s", fileM1579h);
            if (fileM1579h != null) {
                crashDetailBeanM1566a.f2509v = fileM1579h.getAbsolutePath();
            }
        }
        C1971b.m1593a("ANR", C2023z.m1897a(), c1969a.f2529a, "main", c1969a.f2535g, crashDetailBeanM1566a);
        if (!this.f2543g.m1604a(crashDetailBeanM1566a)) {
            this.f2543g.m1602a(crashDetailBeanM1566a, 3000L, true);
        }
        this.f2543g.m1606c(crashDetailBeanM1566a);
        return true;
    }

    /* JADX INFO: renamed from: a */
    public final void m1582a(String str) {
        synchronized (this) {
            if (this.f2537a.get() != 0) {
                C2021x.m1871c("trace started return ", new Object[0]);
                return;
            }
            this.f2537a.set(1);
            try {
                C2021x.m1871c("read trace first dump for create time!", new Object[0]);
                TraceFileHelper.C1967a firstDumpInfo = TraceFileHelper.readFirstDumpInfo(str, false);
                long jCurrentTimeMillis = firstDumpInfo != null ? firstDumpInfo.f2527c : -1L;
                if (jCurrentTimeMillis == -1) {
                    C2021x.m1872d("trace dump fail could not get time!", new Object[0]);
                    jCurrentTimeMillis = System.currentTimeMillis();
                }
                long j = jCurrentTimeMillis;
                if (Math.abs(j - this.f2538b) < 10000) {
                    C2021x.m1872d("should not process ANR too Fre in %d", 10000);
                } else {
                    this.f2538b = j;
                    this.f2537a.set(1);
                    try {
                        Map<String, String> mapM1907a = C2023z.m1907a(C1972c.f2570f, false);
                        if (mapM1907a == null || mapM1907a.size() <= 0) {
                            C2021x.m1872d("can't get all thread skip this anr", new Object[0]);
                        } else {
                            ActivityManager.ProcessErrorStateInfo processErrorStateInfoM1565a = m1565a(this.f2539c, 20000L);
                            this.f2548l = processErrorStateInfoM1565a;
                            if (processErrorStateInfoM1565a == null) {
                                C2021x.m1871c("proc state is unvisiable!", new Object[0]);
                            } else if (processErrorStateInfoM1565a.pid != Process.myPid()) {
                                C2021x.m1871c("not mind proc!", this.f2548l.processName);
                            } else {
                                C2021x.m1866a("found visiable anr , start to process!", new Object[0]);
                                m1569a(this.f2539c, str, this.f2548l, j, mapM1907a);
                            }
                        }
                    } catch (Throwable th) {
                        C2021x.m1867a(th);
                        C2021x.m1873e("get all thread stack fail!", new Object[0]);
                    }
                }
            } finally {
                try {
                } finally {
                }
            }
        }
    }

    /* JADX INFO: renamed from: d */
    private synchronized void m1575d() {
        if (m1577f()) {
            C2021x.m1872d("start when started!", new Object[0]);
            return;
        }
        FileObserver fileObserver = new FileObserver("/data/anr/", 8) { // from class: com.tencent.bugly.crashreport.crash.anr.b.1
            @Override // android.os.FileObserver
            public final void onEvent(int i, String str) {
                if (str == null) {
                    return;
                }
                final String str2 = "/data/anr/" + str;
                C2021x.m1872d("watching file %s", str2);
                if (str2.contains("trace")) {
                    C1970b.this.f2541e.m1860a(new Runnable() { // from class: com.tencent.bugly.crashreport.crash.anr.b.1.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            C1970b.this.m1582a(str2);
                        }
                    });
                } else {
                    C2021x.m1872d("not anr file %s", str2);
                }
            }
        };
        this.f2544h = fileObserver;
        try {
            fileObserver.startWatching();
            C2021x.m1866a("start anr monitor!", new Object[0]);
            this.f2541e.m1860a(new Runnable() { // from class: com.tencent.bugly.crashreport.crash.anr.b.2
                @Override // java.lang.Runnable
                public final void run() {
                    C1970b.this.m1586b();
                }
            });
        } catch (Throwable th) {
            this.f2544h = null;
            C2021x.m1872d("start anr monitor failed!", new Object[0]);
            if (C2021x.m1867a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    /* JADX INFO: renamed from: e */
    private synchronized void m1576e() {
        if (!m1577f()) {
            C2021x.m1872d("close when closed!", new Object[0]);
            return;
        }
        try {
            this.f2544h.stopWatching();
            this.f2544h = null;
            C2021x.m1872d("close anr monitor!", new Object[0]);
        } catch (Throwable th) {
            C2021x.m1872d("stop anr monitor failed!", new Object[0]);
            if (C2021x.m1867a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    /* JADX INFO: renamed from: f */
    private synchronized boolean m1577f() {
        return this.f2544h != null;
    }

    /* JADX INFO: renamed from: b */
    private synchronized void m1573b(boolean z) {
        if (Build.VERSION.SDK_INT <= 19) {
            if (z) {
                m1575d();
                return;
            } else {
                m1576e();
                return;
            }
        }
        if (z) {
            m1580i();
        } else {
            m1581j();
        }
    }

    /* JADX INFO: renamed from: g */
    private synchronized boolean m1578g() {
        return this.f2545i;
    }

    /* JADX INFO: renamed from: c */
    private synchronized void m1574c(boolean z) {
        if (this.f2545i != z) {
            C2021x.m1866a("user change anr %b", Boolean.valueOf(z));
            this.f2545i = z;
        }
    }

    /* JADX INFO: renamed from: a */
    public final void m1583a(boolean z) {
        m1574c(z);
        boolean zM1578g = m1578g();
        C1961a c1961aM1544a = C1961a.m1544a();
        if (c1961aM1544a != null) {
            zM1578g = zM1578g && c1961aM1544a.m1554c().f2430e;
        }
        if (zM1578g != m1577f()) {
            C2021x.m1866a("anr changed to %b", Boolean.valueOf(zM1578g));
            m1573b(zM1578g);
        }
    }

    /* JADX INFO: renamed from: b */
    protected final void m1586b() {
        int iIndexOf;
        long jM1918b = C2023z.m1918b() - C1972c.f2571g;
        File file = new File(this.f2542f);
        if (file.exists() && file.isDirectory()) {
            try {
                File[] fileArrListFiles = file.listFiles();
                if (fileArrListFiles != null && fileArrListFiles.length != 0) {
                    int i = 0;
                    int i2 = 0;
                    for (File file2 : fileArrListFiles) {
                        String name = file2.getName();
                        boolean z = true;
                        if (name.startsWith("bugly_trace_") || name.startsWith("bugly_trace_")) {
                            i2 = 12;
                        } else {
                            z = false;
                        }
                        C2021x.m1871c("Number Trace file : " + name, new Object[0]);
                        if (z) {
                            try {
                                iIndexOf = name.indexOf(".txt");
                            } catch (Throwable unused) {
                                C2021x.m1871c("Trace file that has invalid format: " + name, new Object[0]);
                            }
                            if (iIndexOf <= 0 || Long.parseLong(name.substring(i2, iIndexOf)) < jM1918b) {
                                if (file2.delete()) {
                                    i++;
                                }
                            }
                        }
                    }
                    C2021x.m1871c("Number of overdue trace files that has deleted: " + i, new Object[0]);
                }
            } catch (Throwable th) {
                C2021x.m1867a(th);
            }
        }
    }

    /* JADX INFO: renamed from: c */
    public final synchronized void m1587c() {
        C2021x.m1872d("customer decides whether to open or close.", new Object[0]);
    }

    @Override // com.tencent.bugly.proguard.InterfaceC1983ac
    /* JADX INFO: renamed from: a */
    public final boolean mo1585a(RunnableC1981aa runnableC1981aa) {
        Map<String, String> map = new HashMap<>();
        if (runnableC1981aa.m1703e().equals(Looper.getMainLooper())) {
            try {
                map = C2023z.m1907a(200000, false);
            } catch (Throwable th) {
                C2021x.m1870b(th);
                map.put("main", th.getMessage());
            }
            Map<String, String> map2 = map;
            C2021x.m1871c("onThreadBlock found visiable anr , start to process!", new Object[0]);
            String strM1522c = C1959b.m1522c(this.f2539c);
            if (!TextUtils.isEmpty(strM1522c) && (strM1522c.contains("XiaoMi") || strM1522c.contains("samsung"))) {
                this.f2548l = m1565a(this.f2539c, 20000L);
            }
            m1569a(this.f2539c, "", this.f2548l, System.currentTimeMillis(), map2);
        } else {
            C2021x.m1871c("anr handler onThreadBlock only care main thread ,current thread is: %s", runnableC1981aa.m1702d());
        }
        return true;
    }

    /* JADX INFO: renamed from: h */
    private File m1579h() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        File file = new File(this.f2542f);
        if (file.exists() && file.isDirectory()) {
            try {
                File[] fileArrListFiles = file.listFiles();
                if (fileArrListFiles != null && fileArrListFiles.length != 0) {
                    int i = 24;
                    int length = fileArrListFiles.length;
                    int i2 = 0;
                    while (i2 < length) {
                        File file2 = fileArrListFiles[i2];
                        String name = file2.getName();
                        if (name.startsWith("jni_mannual_bugly_trace_")) {
                            try {
                                int iIndexOf = name.indexOf(".txt");
                                if (iIndexOf > 0) {
                                    long j = Long.parseLong(name.substring(i, iIndexOf));
                                    long j2 = (jCurrentTimeMillis - j) / 1000;
                                    C2021x.m1871c("current time %d trace time is %d s", Long.valueOf(jCurrentTimeMillis), Long.valueOf(j));
                                    C2021x.m1871c("current time minus trace time is %d s", Long.valueOf(j2));
                                    if (j2 < 30) {
                                        return file2;
                                    }
                                } else {
                                    continue;
                                }
                            } catch (Throwable unused) {
                                C2021x.m1871c("Trace file that has invalid format: " + name, new Object[0]);
                            }
                        }
                        i2++;
                        i = 24;
                    }
                }
                return null;
            } catch (Throwable th) {
                C2021x.m1867a(th);
                return null;
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: i */
    private synchronized void m1580i() {
        if (m1577f()) {
            C2021x.m1872d("start when started!", new Object[0]);
            return;
        }
        if (TextUtils.isEmpty(this.f2542f)) {
            return;
        }
        if (this.f2546j == null || !this.f2546j.isAlive()) {
            C1982ab c1982ab = new C1982ab();
            this.f2546j = c1982ab;
            StringBuilder sb = new StringBuilder("Bugly-ThreadMonitor");
            int i = this.f2547k;
            this.f2547k = i + 1;
            sb.append(i);
            c1982ab.setName(sb.toString());
            this.f2546j.m1706a();
            this.f2546j.m1707a(this);
            this.f2546j.m1712d();
            this.f2541e.m1860a(new Runnable() { // from class: com.tencent.bugly.crashreport.crash.anr.b.3
                @Override // java.lang.Runnable
                public final void run() {
                    C1970b.this.m1586b();
                }
            });
        }
        FileObserver fileObserver = new FileObserver(this.f2542f, 256) { // from class: com.tencent.bugly.crashreport.crash.anr.b.4
            @Override // android.os.FileObserver
            public final void onEvent(int i2, String str) {
                if (str == null) {
                    return;
                }
                C2021x.m1872d("startWatchingPrivateAnrDir %s", str);
                if (C1970b.m1570a(C1970b.this, str)) {
                    if (C1970b.this.f2546j != null) {
                        C1970b.this.f2546j.m1708a(true);
                        return;
                    }
                    return;
                }
                C2021x.m1871c("trace file not caused by sigquit , ignore ", new Object[0]);
            }
        };
        this.f2544h = fileObserver;
        try {
            fileObserver.startWatching();
            C2021x.m1866a("startWatchingPrivateAnrDir! dumFilePath is %s", this.f2542f);
            this.f2541e.m1860a(new Runnable() { // from class: com.tencent.bugly.crashreport.crash.anr.b.5
                @Override // java.lang.Runnable
                public final void run() {
                    C1970b.this.m1586b();
                }
            });
        } catch (Throwable th) {
            this.f2544h = null;
            C2021x.m1872d("startWatchingPrivateAnrDir failed!", new Object[0]);
            if (C2021x.m1867a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    /* JADX INFO: renamed from: j */
    private synchronized void m1581j() {
        if (!m1577f()) {
            C2021x.m1872d("close when closed!", new Object[0]);
            return;
        }
        if (this.f2546j != null) {
            this.f2546j.m1711c();
            this.f2546j.m1709b();
            this.f2546j.m1710b(this);
            this.f2546j = null;
        }
        C2021x.m1866a("stopWatchingPrivateAnrDir", new Object[0]);
        try {
            this.f2544h.stopWatching();
            this.f2544h = null;
            C2021x.m1872d("close anr monitor!", new Object[0]);
        } catch (Throwable th) {
            C2021x.m1872d("stop anr monitor failed!", new Object[0]);
            if (C2021x.m1867a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }
}
