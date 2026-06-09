package com.tencent.bugly.crashreport.common.info;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.os.Process;
import android.text.TextUtils;
import com.tencent.bugly.C1950b;
import com.tencent.bugly.crashreport.InterfaceC1953a;
import com.tencent.bugly.proguard.C2021x;
import com.tencent.bugly.proguard.C2023z;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/* JADX INFO: renamed from: com.tencent.bugly.crashreport.common.info.a */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public final class C1958a {

    /* JADX INFO: renamed from: Z */
    private static C1958a f2356Z;

    /* JADX INFO: renamed from: B */
    public boolean f2358B;

    /* JADX INFO: renamed from: F */
    public SharedPreferences f2362F;

    /* JADX INFO: renamed from: G */
    private final Context f2363G;

    /* JADX INFO: renamed from: H */
    private String f2364H;

    /* JADX INFO: renamed from: I */
    private String f2365I;

    /* JADX INFO: renamed from: U */
    private String f2377U;

    /* JADX INFO: renamed from: c */
    public String f2399c;

    /* JADX INFO: renamed from: d */
    public final String f2400d;

    /* JADX INFO: renamed from: g */
    public final String f2403g;

    /* JADX INFO: renamed from: h */
    public final String f2404h;

    /* JADX INFO: renamed from: i */
    public final String f2405i;

    /* JADX INFO: renamed from: j */
    public long f2406j;

    /* JADX INFO: renamed from: k */
    public String f2407k;

    /* JADX INFO: renamed from: l */
    public String f2408l;

    /* JADX INFO: renamed from: m */
    public String f2409m;

    /* JADX INFO: renamed from: p */
    public List<String> f2412p;

    /* JADX INFO: renamed from: v */
    public boolean f2418v;

    /* JADX INFO: renamed from: w */
    public String f2419w;

    /* JADX INFO: renamed from: x */
    public String f2420x;

    /* JADX INFO: renamed from: y */
    public String f2421y;

    /* JADX INFO: renamed from: z */
    public String f2422z;

    /* JADX INFO: renamed from: e */
    public boolean f2401e = true;

    /* JADX INFO: renamed from: f */
    public String f2402f = "3.3.92";

    /* JADX INFO: renamed from: J */
    private String f2366J = "unknown";

    /* JADX INFO: renamed from: K */
    private String f2367K = "";

    /* JADX INFO: renamed from: L */
    private String f2368L = null;

    /* JADX INFO: renamed from: M */
    private long f2369M = -1;

    /* JADX INFO: renamed from: N */
    private long f2370N = -1;

    /* JADX INFO: renamed from: O */
    private long f2371O = -1;

    /* JADX INFO: renamed from: P */
    private String f2372P = null;

    /* JADX INFO: renamed from: Q */
    private String f2373Q = null;

    /* JADX INFO: renamed from: R */
    private Map<String, PlugInBean> f2374R = null;

    /* JADX INFO: renamed from: S */
    private boolean f2375S = true;

    /* JADX INFO: renamed from: T */
    private String f2376T = null;

    /* JADX INFO: renamed from: V */
    private Boolean f2378V = null;

    /* JADX INFO: renamed from: W */
    private String f2379W = null;

    /* JADX INFO: renamed from: n */
    public String f2410n = null;

    /* JADX INFO: renamed from: o */
    public String f2411o = null;

    /* JADX INFO: renamed from: X */
    private Map<String, PlugInBean> f2380X = null;

    /* JADX INFO: renamed from: Y */
    private Map<String, PlugInBean> f2381Y = null;

    /* JADX INFO: renamed from: aa */
    private int f2383aa = -1;

    /* JADX INFO: renamed from: ab */
    private int f2384ab = -1;

    /* JADX INFO: renamed from: ac */
    private Map<String, String> f2385ac = new HashMap();

    /* JADX INFO: renamed from: ad */
    private Map<String, String> f2386ad = new HashMap();

    /* JADX INFO: renamed from: ae */
    private Map<String, String> f2387ae = new HashMap();

    /* JADX INFO: renamed from: af */
    private boolean f2388af = true;

    /* JADX INFO: renamed from: q */
    public String f2413q = "unknown";

    /* JADX INFO: renamed from: r */
    public long f2414r = 0;

    /* JADX INFO: renamed from: s */
    public long f2415s = 0;

    /* JADX INFO: renamed from: t */
    public long f2416t = 0;

    /* JADX INFO: renamed from: u */
    public long f2417u = 0;

    /* JADX INFO: renamed from: A */
    public boolean f2357A = false;

    /* JADX INFO: renamed from: ag */
    private Boolean f2389ag = null;

    /* JADX INFO: renamed from: ah */
    private Boolean f2390ah = null;

    /* JADX INFO: renamed from: C */
    public HashMap<String, String> f2359C = new HashMap<>();

    /* JADX INFO: renamed from: D */
    public List<String> f2360D = new ArrayList();

    /* JADX INFO: renamed from: E */
    public InterfaceC1953a f2361E = null;

    /* JADX INFO: renamed from: ai */
    private final Object f2391ai = new Object();

    /* JADX INFO: renamed from: aj */
    private final Object f2392aj = new Object();

    /* JADX INFO: renamed from: ak */
    private final Object f2393ak = new Object();

    /* JADX INFO: renamed from: al */
    private final Object f2394al = new Object();

    /* JADX INFO: renamed from: am */
    private final Object f2395am = new Object();

    /* JADX INFO: renamed from: an */
    private final Object f2396an = new Object();

    /* JADX INFO: renamed from: ao */
    private final Object f2397ao = new Object();

    /* JADX INFO: renamed from: a */
    public final long f2382a = System.currentTimeMillis();

    /* JADX INFO: renamed from: b */
    public final byte f2398b = 1;

    private C1958a(Context context) {
        this.f2407k = null;
        this.f2408l = null;
        this.f2377U = null;
        this.f2409m = null;
        this.f2412p = null;
        this.f2418v = false;
        this.f2419w = null;
        this.f2420x = null;
        this.f2421y = null;
        this.f2422z = "";
        this.f2358B = false;
        this.f2363G = C2023z.m1891a(context);
        PackageInfo packageInfoM1466b = AppInfo.m1466b(context);
        if (packageInfoM1466b != null) {
            try {
                String str = packageInfoM1466b.versionName;
                this.f2407k = str;
                this.f2419w = str;
                this.f2420x = Integer.toString(packageInfoM1466b.versionCode);
            } catch (Throwable th) {
                if (!C2021x.m1867a(th)) {
                    th.printStackTrace();
                }
            }
        }
        this.f2399c = AppInfo.m1463a(context);
        this.f2400d = AppInfo.m1462a(Process.myPid());
        this.f2403g = C1959b.m1535k();
        this.f2404h = C1959b.m1516a();
        this.f2408l = AppInfo.m1467c(context);
        this.f2405i = "Android " + C1959b.m1519b() + ",level " + C1959b.m1521c();
        Map<String, String> mapM1468d = AppInfo.m1468d(context);
        if (mapM1468d != null) {
            try {
                this.f2412p = AppInfo.m1464a(mapM1468d);
                String str2 = mapM1468d.get("BUGLY_APPID");
                if (str2 != null) {
                    this.f2377U = str2;
                    m1488c("APP_ID", str2);
                }
                String str3 = mapM1468d.get("BUGLY_APP_VERSION");
                if (str3 != null) {
                    this.f2407k = str3;
                }
                String str4 = mapM1468d.get("BUGLY_APP_CHANNEL");
                if (str4 != null) {
                    this.f2409m = str4;
                }
                String str5 = mapM1468d.get("BUGLY_ENABLE_DEBUG");
                if (str5 != null) {
                    this.f2418v = str5.equalsIgnoreCase("true");
                }
                String str6 = mapM1468d.get("com.tencent.rdm.uuid");
                if (str6 != null) {
                    this.f2421y = str6;
                }
                String str7 = mapM1468d.get("BUGLY_APP_BUILD_NO");
                if (!TextUtils.isEmpty(str7)) {
                    Integer.parseInt(str7);
                }
                String str8 = mapM1468d.get("BUGLY_AREA");
                if (str8 != null) {
                    this.f2422z = str8;
                }
            } catch (Throwable th2) {
                if (!C2021x.m1867a(th2)) {
                    th2.printStackTrace();
                }
            }
        }
        try {
            if (!context.getDatabasePath("bugly_db_").exists()) {
                this.f2358B = true;
                C2021x.m1871c("App is first time to be installed on the device.", new Object[0]);
            }
        } catch (Throwable th3) {
            if (C1950b.f2299c) {
                th3.printStackTrace();
            }
        }
        this.f2362F = C2023z.m1892a("BUGLY_COMMON_VALUES", context);
        C2021x.m1871c("com info create end", new Object[0]);
    }

    /* JADX INFO: renamed from: a */
    public final boolean m1481a() {
        return this.f2388af;
    }

    /* JADX INFO: renamed from: a */
    public final void m1480a(boolean z) {
        this.f2388af = z;
        InterfaceC1953a interfaceC1953a = this.f2361E;
        if (interfaceC1953a != null) {
            interfaceC1953a.setNativeIsAppForeground(z);
        }
    }

    /* JADX INFO: renamed from: a */
    public static synchronized C1958a m1471a(Context context) {
        if (f2356Z == null) {
            f2356Z = new C1958a(context);
        }
        return f2356Z;
    }

    /* JADX INFO: renamed from: b */
    public static synchronized C1958a m1472b() {
        return f2356Z;
    }

    /* JADX INFO: renamed from: c */
    public final String m1486c() {
        return this.f2402f;
    }

    /* JADX INFO: renamed from: d */
    public final void m1489d() {
        synchronized (this.f2391ai) {
            this.f2364H = UUID.randomUUID().toString();
        }
    }

    /* JADX INFO: renamed from: e */
    public final String m1491e() {
        String str;
        synchronized (this.f2391ai) {
            if (this.f2364H == null) {
                synchronized (this.f2391ai) {
                    this.f2364H = UUID.randomUUID().toString();
                }
            }
            str = this.f2364H;
        }
        return str;
    }

    /* JADX INFO: renamed from: f */
    public final String m1493f() {
        if (C2023z.m1914a((String) null)) {
            return this.f2377U;
        }
        return null;
    }

    /* JADX INFO: renamed from: a */
    public final void m1478a(String str) {
        this.f2377U = str;
        m1488c("APP_ID", str);
    }

    /* JADX INFO: renamed from: g */
    public final String m1495g() {
        String str;
        synchronized (this.f2396an) {
            str = this.f2366J;
        }
        return str;
    }

    /* JADX INFO: renamed from: b */
    public final void m1483b(String str) {
        synchronized (this.f2396an) {
            if (str == null) {
                str = "10000";
            }
            this.f2366J = str;
        }
    }

    /* JADX INFO: renamed from: b */
    public final void m1485b(boolean z) {
        this.f2375S = z;
    }

    /* JADX INFO: renamed from: h */
    public final String m1497h() {
        String str = this.f2365I;
        if (str != null) {
            return str;
        }
        String str2 = "";
        if (this.f2375S) {
            if (this.f2368L == null) {
                this.f2368L = C2023z.m1928c("androidid", "");
                String strM1517a = C1959b.m1517a(this.f2363G);
                this.f2368L = strM1517a;
                if (!TextUtils.isEmpty(strM1517a)) {
                    C2023z.m1925b("androidid", this.f2368L);
                }
            }
            str2 = this.f2368L;
        }
        this.f2365I = str2;
        return str2;
    }

    /* JADX INFO: renamed from: c */
    public final void m1487c(String str) {
        this.f2365I = str;
        synchronized (this.f2397ao) {
            this.f2386ad.put("E8", str);
        }
    }

    /* JADX INFO: renamed from: d */
    public final synchronized void m1490d(String str) {
    }

    /* JADX INFO: renamed from: i */
    public final synchronized String m1498i() {
        return this.f2367K;
    }

    /* JADX INFO: renamed from: e */
    public final synchronized void m1492e(String str) {
        this.f2367K = str;
    }

    /* JADX INFO: renamed from: j */
    public final long m1499j() {
        if (this.f2369M <= 0) {
            this.f2369M = C1959b.m1523d();
        }
        return this.f2369M;
    }

    /* JADX INFO: renamed from: k */
    public final long m1500k() {
        if (this.f2370N <= 0) {
            this.f2370N = C1959b.m1527f();
        }
        return this.f2370N;
    }

    /* JADX INFO: renamed from: l */
    public final long m1501l() {
        if (this.f2371O <= 0) {
            this.f2371O = C1959b.m1532h();
        }
        return this.f2371O;
    }

    /* JADX INFO: renamed from: m */
    public final String m1502m() {
        if (this.f2372P == null) {
            this.f2372P = C1959b.m1518a(this.f2363G, true);
        }
        return this.f2372P;
    }

    /* JADX INFO: renamed from: n */
    public final String m1503n() {
        if (this.f2373Q == null) {
            this.f2373Q = C1959b.m1524d(this.f2363G);
        }
        return this.f2373Q;
    }

    /* JADX INFO: renamed from: a */
    public final void m1479a(String str, String str2) {
        if (str == null || str2 == null) {
            return;
        }
        synchronized (this.f2392aj) {
            this.f2359C.put(str, str2);
        }
    }

    /* JADX INFO: renamed from: o */
    public final String m1504o() {
        try {
            Map<String, ?> all = this.f2363G.getSharedPreferences("BuglySdkInfos", 0).getAll();
            if (!all.isEmpty()) {
                synchronized (this.f2392aj) {
                    for (Map.Entry<String, ?> entry : all.entrySet()) {
                        try {
                            this.f2359C.put(entry.getKey(), entry.getValue().toString());
                        } catch (Throwable th) {
                            C2021x.m1867a(th);
                        }
                    }
                }
            }
        } catch (Throwable th2) {
            C2021x.m1867a(th2);
        }
        if (!this.f2359C.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry2 : this.f2359C.entrySet()) {
                sb.append("[");
                sb.append(entry2.getKey());
                sb.append(",");
                sb.append(entry2.getValue());
                sb.append("] ");
            }
            C2021x.m1871c("SDK_INFO = %s", sb.toString());
            m1488c("SDK_INFO", sb.toString());
            return sb.toString();
        }
        C2021x.m1871c("SDK_INFO is empty", new Object[0]);
        return null;
    }

    /* JADX INFO: renamed from: p */
    public final synchronized Map<String, PlugInBean> m1505p() {
        return null;
    }

    /* JADX INFO: renamed from: q */
    public final String m1506q() {
        if (this.f2376T == null) {
            this.f2376T = C1959b.m1534j();
        }
        return this.f2376T;
    }

    /* JADX INFO: renamed from: r */
    public final Boolean m1507r() {
        if (this.f2378V == null) {
            this.f2378V = Boolean.valueOf(C1959b.m1536l());
        }
        return this.f2378V;
    }

    /* JADX INFO: renamed from: s */
    public final String m1508s() {
        if (this.f2379W == null) {
            String str = C1959b.m1522c(this.f2363G);
            this.f2379W = str;
            C2021x.m1866a("ROM ID: %s", str);
        }
        return this.f2379W;
    }

    /* JADX INFO: renamed from: t */
    public final Map<String, String> m1509t() {
        synchronized (this.f2393ak) {
            if (this.f2385ac.size() <= 0) {
                return null;
            }
            return new HashMap(this.f2385ac);
        }
    }

    /* JADX INFO: renamed from: f */
    public final String m1494f(String str) {
        String strRemove;
        if (C2023z.m1914a(str)) {
            C2021x.m1872d("key should not be empty %s", str);
            return null;
        }
        synchronized (this.f2393ak) {
            strRemove = this.f2385ac.remove(str);
        }
        return strRemove;
    }

    /* JADX INFO: renamed from: u */
    public final void m1510u() {
        synchronized (this.f2393ak) {
            this.f2385ac.clear();
        }
    }

    /* JADX INFO: renamed from: g */
    public final String m1496g(String str) {
        String str2;
        if (C2023z.m1914a(str)) {
            C2021x.m1872d("key should not be empty %s", str);
            return null;
        }
        synchronized (this.f2393ak) {
            str2 = this.f2385ac.get(str);
        }
        return str2;
    }

    /* JADX INFO: renamed from: b */
    public final void m1484b(String str, String str2) {
        if (C2023z.m1914a(str) || C2023z.m1914a(str2)) {
            C2021x.m1872d("key&value should not be empty %s %s", str, str2);
            return;
        }
        synchronized (this.f2393ak) {
            this.f2385ac.put(str, str2);
        }
    }

    /* JADX INFO: renamed from: v */
    public final int m1511v() {
        int size;
        synchronized (this.f2393ak) {
            size = this.f2385ac.size();
        }
        return size;
    }

    /* JADX INFO: renamed from: w */
    public final Set<String> m1512w() {
        Set<String> setKeySet;
        synchronized (this.f2393ak) {
            setKeySet = this.f2385ac.keySet();
        }
        return setKeySet;
    }

    /* JADX INFO: renamed from: x */
    public final Map<String, String> m1513x() {
        synchronized (this.f2397ao) {
            if (this.f2386ad.size() <= 0) {
                return null;
            }
            return new HashMap(this.f2386ad);
        }
    }

    /* JADX INFO: renamed from: c */
    public final void m1488c(String str, String str2) {
        if (C2023z.m1914a(str) || C2023z.m1914a(str2)) {
            C2021x.m1872d("server key&value should not be empty %s %s", str, str2);
            return;
        }
        synchronized (this.f2394al) {
            this.f2387ae.put(str, str2);
        }
    }

    /* JADX INFO: renamed from: y */
    public final Map<String, String> m1514y() {
        synchronized (this.f2394al) {
            if (this.f2387ae.size() <= 0) {
                return null;
            }
            return new HashMap(this.f2387ae);
        }
    }

    /* JADX INFO: renamed from: a */
    public final void m1477a(int i) {
        synchronized (this.f2395am) {
            int i2 = this.f2383aa;
            if (i2 != i) {
                this.f2383aa = i;
                C2021x.m1866a("user scene tag %d changed to tag %d", Integer.valueOf(i2), Integer.valueOf(this.f2383aa));
            }
        }
    }

    /* JADX INFO: renamed from: z */
    public final int m1515z() {
        int i;
        synchronized (this.f2395am) {
            i = this.f2383aa;
        }
        return i;
    }

    /* JADX INFO: renamed from: b */
    public final void m1482b(int i) {
        int i2 = this.f2384ab;
        if (i2 != 24096) {
            this.f2384ab = 24096;
            C2021x.m1866a("server scene tag %d changed to tag %d", Integer.valueOf(i2), Integer.valueOf(this.f2384ab));
        }
    }

    /* JADX INFO: renamed from: A */
    public final int m1473A() {
        return this.f2384ab;
    }

    /* JADX INFO: renamed from: B */
    public final synchronized Map<String, PlugInBean> m1474B() {
        return null;
    }

    /* JADX INFO: renamed from: C */
    public static int m1470C() {
        return C1959b.m1521c();
    }

    /* JADX INFO: renamed from: D */
    public final boolean m1475D() {
        if (this.f2389ag == null) {
            this.f2389ag = Boolean.valueOf(C1959b.m1526e(this.f2363G));
            C2021x.m1866a("Is it a virtual machine? " + this.f2389ag, new Object[0]);
        }
        return this.f2389ag.booleanValue();
    }

    /* JADX INFO: renamed from: E */
    public final boolean m1476E() {
        if (this.f2390ah == null) {
            this.f2390ah = Boolean.valueOf(C1959b.m1528f(this.f2363G));
            C2021x.m1866a("Does it has hook frame? " + this.f2390ah, new Object[0]);
        }
        return this.f2390ah.booleanValue();
    }
}
