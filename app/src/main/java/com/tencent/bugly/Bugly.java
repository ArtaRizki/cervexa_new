package com.tencent.bugly;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.bugly.crashreport.common.info.C1958a;
import com.tencent.bugly.proguard.C2013p;
import com.tencent.bugly.proguard.C2021x;
import com.tencent.bugly.proguard.C2023z;
import com.tencent.bugly.proguard.InterfaceC2012o;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public class Bugly {
    public static final String SDK_IS_DEV = "false";

    /* JADX INFO: renamed from: a */
    private static boolean f2270a = false;
    public static Context applicationContext = null;

    /* JADX INFO: renamed from: b */
    private static String[] f2271b = {"BuglyCrashModule", "BuglyRqdModule", "BuglyBetaModule"};

    /* JADX INFO: renamed from: c */
    private static String[] f2272c = {"BuglyRqdModule", "BuglyCrashModule", "BuglyBetaModule"};
    public static boolean enable = true;
    public static Boolean isDev;

    public static void init(Context context, String str, boolean z) {
        init(context, str, z, null);
    }

    public static synchronized void init(Context context, String str, boolean z, BuglyStrategy buglyStrategy) {
        if (f2270a) {
            return;
        }
        f2270a = true;
        Context contextM1891a = C2023z.m1891a(context);
        applicationContext = contextM1891a;
        if (contextM1891a == null) {
            Log.e(C2021x.f2906a, "init arg 'context' should not be null!");
            return;
        }
        if (isDev()) {
            f2271b = f2272c;
        }
        for (String str2 : f2271b) {
            try {
                if (str2.equals("BuglyCrashModule")) {
                    C1950b.m1421a(CrashModule.getInstance());
                } else if (!str2.equals("BuglyBetaModule") && !str2.equals("BuglyRqdModule")) {
                    str2.equals("BuglyFeedbackModule");
                }
            } catch (Throwable th) {
                C2021x.m1870b(th);
            }
        }
        C1950b.f2297a = enable;
        C1950b.m1420a(applicationContext, str, z, buglyStrategy);
    }

    public static synchronized String getAppChannel() {
        byte[] bArr;
        C1958a c1958aM1472b = C1958a.m1472b();
        if (c1958aM1472b == null) {
            return null;
        }
        if (TextUtils.isEmpty(c1958aM1472b.f2409m)) {
            C2013p c2013pM1807a = C2013p.m1807a();
            if (c2013pM1807a == null) {
                return c1958aM1472b.f2409m;
            }
            Map<String, byte[]> mapM1825a = c2013pM1807a.m1825a(556, (InterfaceC2012o) null, true);
            if (mapM1825a != null && (bArr = mapM1825a.get("app_channel")) != null) {
                return new String(bArr);
            }
        }
        return c1958aM1472b.f2409m;
    }

    public static boolean isDev() {
        if (isDev == null) {
            isDev = Boolean.valueOf(Boolean.parseBoolean(SDK_IS_DEV.replace("@", "")));
        }
        return isDev.booleanValue();
    }
}
