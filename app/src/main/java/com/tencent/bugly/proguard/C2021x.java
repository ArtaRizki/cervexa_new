package com.tencent.bugly.proguard;

import android.util.Log;
import com.jieli.stream.p016dv.running2.util.IConstant;
import java.util.Locale;

/* JADX INFO: renamed from: com.tencent.bugly.proguard.x */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public final class C2021x {

    /* JADX INFO: renamed from: a */
    public static String f2906a = "CrashReport";

    /* JADX INFO: renamed from: b */
    public static boolean f2907b = false;

    /* JADX INFO: renamed from: c */
    private static String f2908c = "CrashReportInfo";

    /* JADX INFO: renamed from: a */
    private static boolean m1864a(int i, String str, Object... objArr) {
        if (!f2907b) {
            return false;
        }
        if (str == null) {
            str = IConstant.DEFAULT_PATH;
        } else if (objArr != null && objArr.length != 0) {
            str = String.format(Locale.US, str, objArr);
        }
        if (i == 0) {
            Log.i(f2906a, str);
            return true;
        }
        if (i == 1) {
            Log.d(f2906a, str);
            return true;
        }
        if (i == 2) {
            Log.w(f2906a, str);
            return true;
        }
        if (i == 3) {
            Log.e(f2906a, str);
            return true;
        }
        if (i != 5) {
            return false;
        }
        Log.i(f2908c, str);
        return true;
    }

    /* JADX INFO: renamed from: a */
    public static boolean m1866a(String str, Object... objArr) {
        return m1864a(0, str, objArr);
    }

    /* JADX INFO: renamed from: a */
    public static boolean m1865a(Class cls, String str, Object... objArr) {
        return m1864a(0, String.format(Locale.US, "[%s] %s", cls.getSimpleName(), str), objArr);
    }

    /* JADX INFO: renamed from: b */
    public static boolean m1869b(String str, Object... objArr) {
        return m1864a(5, str, objArr);
    }

    /* JADX INFO: renamed from: c */
    public static boolean m1871c(String str, Object... objArr) {
        return m1864a(1, str, objArr);
    }

    /* JADX INFO: renamed from: b */
    public static boolean m1868b(Class cls, String str, Object... objArr) {
        return m1864a(1, String.format(Locale.US, "[%s] %s", cls.getSimpleName(), str), objArr);
    }

    /* JADX INFO: renamed from: d */
    public static boolean m1872d(String str, Object... objArr) {
        return m1864a(2, str, objArr);
    }

    /* JADX INFO: renamed from: a */
    public static boolean m1867a(Throwable th) {
        if (f2907b) {
            return m1864a(2, C2023z.m1902a(th), new Object[0]);
        }
        return false;
    }

    /* JADX INFO: renamed from: e */
    public static boolean m1873e(String str, Object... objArr) {
        return m1864a(3, str, objArr);
    }

    /* JADX INFO: renamed from: b */
    public static boolean m1870b(Throwable th) {
        if (f2907b) {
            return m1864a(3, C2023z.m1902a(th), new Object[0]);
        }
        return false;
    }
}
