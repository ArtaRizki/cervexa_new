package com.tencent.p023mm.opensdk.utils;

/* JADX INFO: loaded from: classes2.dex */
public class Log {
    private static ILog logImpl;

    /* JADX INFO: renamed from: d */
    public static void m2050d(String str, String str2) {
        ILog iLog = logImpl;
        if (iLog == null) {
            android.util.Log.d(str, str2);
        } else {
            iLog.m2045d(str, str2);
        }
    }

    /* JADX INFO: renamed from: e */
    public static void m2051e(String str, String str2) {
        ILog iLog = logImpl;
        if (iLog == null) {
            android.util.Log.e(str, str2);
        } else {
            iLog.m2046e(str, str2);
        }
    }

    /* JADX INFO: renamed from: i */
    public static void m2052i(String str, String str2) {
        ILog iLog = logImpl;
        if (iLog == null) {
            android.util.Log.i(str, str2);
        } else {
            iLog.m2047i(str, str2);
        }
    }

    public static void setLogImpl(ILog iLog) {
        logImpl = iLog;
    }

    /* JADX INFO: renamed from: v */
    public static void m2053v(String str, String str2) {
        ILog iLog = logImpl;
        if (iLog == null) {
            android.util.Log.v(str, str2);
        } else {
            iLog.m2048v(str, str2);
        }
    }

    /* JADX INFO: renamed from: w */
    public static void m2054w(String str, String str2) {
        ILog iLog = logImpl;
        if (iLog == null) {
            android.util.Log.w(str, str2);
        } else {
            iLog.m2049w(str, str2);
        }
    }
}
