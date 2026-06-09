package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import com.tencent.bugly.crashreport.common.info.AppInfo;
import com.tencent.bugly.crashreport.common.info.C1958a;
import com.tencent.bugly.crashreport.common.info.C1959b;
import com.tencent.bugly.crashreport.common.strategy.C1961a;
import com.tencent.bugly.crashreport.crash.C1971b;
import com.tencent.bugly.crashreport.crash.C1972c;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.proguard.C2021x;
import com.tencent.bugly.proguard.C2022y;
import com.tencent.bugly.proguard.C2023z;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: renamed from: com.tencent.bugly.crashreport.crash.jni.a */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public final class C1978a implements NativeExceptionHandler {

    /* JADX INFO: renamed from: a */
    private final Context f2656a;

    /* JADX INFO: renamed from: b */
    private final C1971b f2657b;

    /* JADX INFO: renamed from: c */
    private final C1958a f2658c;

    /* JADX INFO: renamed from: d */
    private final C1961a f2659d;

    public C1978a(Context context, C1958a c1958a, C1971b c1971b, C1961a c1961a) {
        this.f2656a = context;
        this.f2657b = c1971b;
        this.f2658c = c1958a;
        this.f2659d = c1961a;
    }

    @Override // com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler
    public final CrashDetailBean packageCrashDatas(String str, String str2, long j, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, byte[] bArr, Map<String, String> map, boolean z, boolean z2) {
        int i;
        String str12;
        int iIndexOf;
        boolean zM1631m = C1972c.m1609a().m1631m();
        if (zM1631m) {
            C2021x.m1873e("This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful!", new Object[0]);
        }
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        crashDetailBean.f2489b = 1;
        crashDetailBean.f2492e = this.f2658c.m1497h();
        crashDetailBean.f2493f = this.f2658c.f2407k;
        crashDetailBean.f2494g = this.f2658c.m1506q();
        crashDetailBean.f2500m = this.f2658c.m1495g();
        crashDetailBean.f2501n = str3;
        crashDetailBean.f2502o = zM1631m ? " This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful![Bugly]" : "";
        crashDetailBean.f2503p = str4;
        crashDetailBean.f2504q = str5 != null ? str5 : "";
        crashDetailBean.f2505r = j;
        crashDetailBean.f2508u = C2023z.m1904a(crashDetailBean.f2504q.getBytes());
        crashDetailBean.f2464A = str;
        crashDetailBean.f2465B = str2;
        crashDetailBean.f2472I = this.f2658c.m1508s();
        crashDetailBean.f2495h = this.f2658c.m1505p();
        crashDetailBean.f2496i = this.f2658c.m1474B();
        crashDetailBean.f2509v = str8;
        NativeCrashHandler nativeCrashHandler = NativeCrashHandler.getInstance();
        String dumpFilePath = nativeCrashHandler != null ? nativeCrashHandler.getDumpFilePath() : null;
        String strM1675a = C1979b.m1675a(dumpFilePath, str8);
        if (!C2023z.m1914a(strM1675a)) {
            crashDetailBean.f2485V = strM1675a;
        }
        crashDetailBean.f2486W = C1979b.m1677b(dumpFilePath);
        crashDetailBean.f2510w = C1979b.m1674a(str9, C1972c.f2569e, null, false);
        crashDetailBean.f2511x = C1979b.m1674a(str10, C1972c.f2569e, null, true);
        crashDetailBean.f2473J = str7;
        crashDetailBean.f2474K = str6;
        crashDetailBean.f2475L = str11;
        crashDetailBean.f2469F = this.f2658c.m1500k();
        crashDetailBean.f2470G = this.f2658c.m1499j();
        crashDetailBean.f2471H = this.f2658c.m1501l();
        if (z) {
            crashDetailBean.f2466C = C1959b.m1529g();
            crashDetailBean.f2467D = C1959b.m1525e();
            crashDetailBean.f2468E = C1959b.m1533i();
            if (crashDetailBean.f2510w == null) {
                crashDetailBean.f2510w = C2023z.m1899a(this.f2656a, C1972c.f2569e, (String) null);
            }
            crashDetailBean.f2512y = C2022y.m1879a();
            crashDetailBean.f2476M = this.f2658c.f2382a;
            crashDetailBean.f2477N = this.f2658c.m1481a();
            crashDetailBean.f2513z = C2023z.m1907a(C1972c.f2570f, false);
            int iIndexOf2 = crashDetailBean.f2504q.indexOf("java:\n");
            if (iIndexOf2 > 0 && (i = iIndexOf2 + 6) < crashDetailBean.f2504q.length()) {
                String strSubstring = crashDetailBean.f2504q.substring(i, crashDetailBean.f2504q.length() - 1);
                if (strSubstring.length() > 0 && crashDetailBean.f2513z.containsKey(crashDetailBean.f2465B) && (iIndexOf = (str12 = crashDetailBean.f2513z.get(crashDetailBean.f2465B)).indexOf(strSubstring)) > 0) {
                    String strSubstring2 = str12.substring(iIndexOf);
                    crashDetailBean.f2513z.put(crashDetailBean.f2465B, strSubstring2);
                    crashDetailBean.f2504q = crashDetailBean.f2504q.substring(0, i);
                    crashDetailBean.f2504q += strSubstring2;
                }
            }
            if (str == null) {
                crashDetailBean.f2464A = this.f2658c.f2400d;
            }
            this.f2657b.m1607d(crashDetailBean);
            crashDetailBean.f2480Q = this.f2658c.m1515z();
            crashDetailBean.f2481R = this.f2658c.m1473A();
            crashDetailBean.f2482S = this.f2658c.m1509t();
            crashDetailBean.f2483T = this.f2658c.m1514y();
        } else {
            crashDetailBean.f2466C = -1L;
            crashDetailBean.f2467D = -1L;
            crashDetailBean.f2468E = -1L;
            if (crashDetailBean.f2510w == null) {
                crashDetailBean.f2510w = "this crash is occurred at last process! Log is miss, when get an terrible ABRT Native Exception etc.";
            }
            crashDetailBean.f2476M = -1L;
            crashDetailBean.f2480Q = -1;
            crashDetailBean.f2481R = -1;
            crashDetailBean.f2482S = map;
            crashDetailBean.f2483T = this.f2658c.m1514y();
            crashDetailBean.f2513z = null;
            if (str == null) {
                crashDetailBean.f2464A = "unknown(record)";
            }
            if (bArr != null) {
                crashDetailBean.f2512y = bArr;
            }
        }
        return crashDetailBean;
    }

    @Override // com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler
    public final void handleNativeException(int i, int i2, long j, long j2, String str, String str2, String str3, String str4, int i3, String str5, int i4, int i5, int i6, String str6, String str7) {
        C2021x.m1866a("Native Crash Happen v1", new Object[0]);
        handleNativeException2(i, i2, j, j2, str, str2, str3, str4, i3, str5, i4, i5, i6, str6, str7, null);
    }

    @Override // com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler
    public final void handleNativeException2(int i, int i2, long j, long j2, String str, String str2, String str3, String str4, int i3, String str5, int i4, int i5, int i6, String str6, String str7, String[] strArr) {
        String str8;
        String str9;
        String str10;
        boolean z;
        boolean z2;
        C2021x.m1866a("Native Crash Happen v2", new Object[0]);
        try {
            String strM1673a = C1979b.m1673a(str3);
            if (i3 > 0) {
                str9 = str + "(" + str5 + ")";
                str8 = "UNKNOWN";
                str10 = "KERNEL";
            } else {
                String strM1462a = i4 > 0 ? AppInfo.m1462a(i4) : "UNKNOWN";
                str8 = strM1462a.equals(String.valueOf(i4)) ? strM1462a : strM1462a + "(" + i4 + ")";
                str9 = str;
                str10 = str5;
            }
            HashMap map = new HashMap();
            if (strArr != null) {
                for (int i7 = 0; i7 < strArr.length; i7++) {
                    String str11 = strArr[i7];
                    if (str11 != null) {
                        C2021x.m1866a("Extra message[%d]: %s", Integer.valueOf(i7), str11);
                        String[] strArrSplit = str11.split("=");
                        if (strArrSplit.length == 2) {
                            map.put(strArrSplit[0], strArrSplit[1]);
                        } else {
                            C2021x.m1872d("bad extraMsg %s", str11);
                        }
                    }
                }
            } else {
                C2021x.m1871c("not found extraMsg", new Object[0]);
            }
            String str12 = (String) map.get("HasPendingException");
            if (str12 == null || !str12.equals("true")) {
                z = false;
            } else {
                C2021x.m1866a("Native crash happened with a Java pending exception.", new Object[0]);
                z = true;
            }
            String str13 = (String) map.get("ExceptionProcessName");
            if (str13 == null || str13.length() == 0) {
                str13 = this.f2658c.f2400d;
            } else {
                C2021x.m1871c("Name of crash process: %s", str13);
            }
            String str14 = str13;
            String str15 = (String) map.get("ExceptionThreadName");
            if (str15 == null || str15.length() == 0) {
                Thread threadCurrentThread = Thread.currentThread();
                str15 = threadCurrentThread.getName() + "(" + threadCurrentThread.getId() + ")";
            } else {
                C2021x.m1871c("Name of crash thread: %s", str15);
                Iterator<Thread> it = Thread.getAllStackTraces().keySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z2 = false;
                        break;
                    }
                    Thread next = it.next();
                    if (next.getName().equals(str15)) {
                        str15 = str15 + "(" + next.getId() + ")";
                        z2 = true;
                        break;
                    }
                }
                if (!z2) {
                    str15 = str15 + "(" + i2 + ")";
                }
            }
            String str16 = str15;
            long j3 = (j * 1000) + (j2 / 1000);
            String str17 = (String) map.get("SysLogPath");
            String str18 = (String) map.get("JniLogPath");
            if (!this.f2659d.m1553b()) {
                C2021x.m1872d("no remote but still store!", new Object[0]);
            }
            if (!this.f2659d.m1554c().f2430e && this.f2659d.m1553b()) {
                C2021x.m1873e("crash report was closed by remote , will not upload to Bugly , print local for helpful!", new Object[0]);
                C1971b.m1593a("NATIVE_CRASH", C2023z.m1897a(), str14, str16, str9 + "\n" + str2 + "\n" + strM1673a, null);
                C2023z.m1924b(str4);
                return;
            }
            String str19 = str9;
            try {
                CrashDetailBean crashDetailBeanPackageCrashDatas = packageCrashDatas(str14, str16, j3, str9, str2, strM1673a, str10, str8, str4, str17, str18, str7, null, null, true, z);
                if (crashDetailBeanPackageCrashDatas == null) {
                    C2021x.m1873e("pkg crash datas fail!", new Object[0]);
                    return;
                }
                C1971b.m1593a("NATIVE_CRASH", C2023z.m1897a(), str14, str16, str19 + "\n" + str2 + "\n" + strM1673a, crashDetailBeanPackageCrashDatas);
                try {
                    boolean z3 = !this.f2657b.m1605b(crashDetailBeanPackageCrashDatas);
                    NativeCrashHandler nativeCrashHandler = NativeCrashHandler.getInstance();
                    C1979b.m1676a(true, nativeCrashHandler != null ? nativeCrashHandler.getDumpFilePath() : null);
                    if (z3) {
                        this.f2657b.m1602a(crashDetailBeanPackageCrashDatas, 3000L, true);
                    }
                    this.f2657b.m1606c(crashDetailBeanPackageCrashDatas);
                    C1972c.m1609a().m1623e();
                    return;
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
        }
        if (C2021x.m1867a(th)) {
            return;
        }
        th.printStackTrace();
    }
}
