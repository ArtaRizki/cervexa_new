package com.tencent.bugly.crashreport;

import android.util.Log;
import com.jieli.stream.p016dv.running2.util.IConstant;
import com.tencent.bugly.C1950b;
import com.tencent.bugly.proguard.C2022y;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public class BuglyLog {
    /* JADX INFO: renamed from: v */
    public static void m1427v(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = IConstant.DEFAULT_PATH;
        }
        if (C1950b.f2299c) {
            Log.v(str, str2);
        }
        C2022y.m1877a("V", str, str2);
    }

    /* JADX INFO: renamed from: d */
    public static void m1423d(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = IConstant.DEFAULT_PATH;
        }
        if (C1950b.f2299c) {
            Log.d(str, str2);
        }
        C2022y.m1877a("D", str, str2);
    }

    /* JADX INFO: renamed from: i */
    public static void m1426i(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = IConstant.DEFAULT_PATH;
        }
        if (C1950b.f2299c) {
            Log.i(str, str2);
        }
        C2022y.m1877a("I", str, str2);
    }

    /* JADX INFO: renamed from: w */
    public static void m1428w(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = IConstant.DEFAULT_PATH;
        }
        if (C1950b.f2299c) {
            Log.w(str, str2);
        }
        C2022y.m1877a("W", str, str2);
    }

    /* JADX INFO: renamed from: e */
    public static void m1424e(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = IConstant.DEFAULT_PATH;
        }
        if (C1950b.f2299c) {
            Log.e(str, str2);
        }
        C2022y.m1877a("E", str, str2);
    }

    /* JADX INFO: renamed from: e */
    public static void m1425e(String str, String str2, Throwable th) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = IConstant.DEFAULT_PATH;
        }
        if (C1950b.f2299c) {
            Log.e(str, str2, th);
        }
        C2022y.m1878a("E", str, th);
    }

    public static void setCache(int i) {
        C2022y.m1875a(i);
    }
}
