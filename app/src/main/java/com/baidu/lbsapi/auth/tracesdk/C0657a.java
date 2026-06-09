package com.baidu.lbsapi.auth.tracesdk;

import android.util.Log;

/* JADX INFO: renamed from: com.baidu.lbsapi.auth.tracesdk.a */
/* JADX INFO: loaded from: classes.dex */
class C0657a {

    /* JADX INFO: renamed from: a */
    public static boolean f156a = false;

    /* JADX INFO: renamed from: b */
    private static String f157b = "BaiduApiAuth";

    /* JADX INFO: renamed from: a */
    public static String m188a() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[2];
        return stackTraceElement.getFileName() + "[" + stackTraceElement.getLineNumber() + "]";
    }

    /* JADX INFO: renamed from: a */
    public static void m189a(String str) {
        if (!f156a || Thread.currentThread().getStackTrace().length == 0) {
            return;
        }
        Log.d(f157b, m188a() + ";" + str);
    }

    /* JADX INFO: renamed from: b */
    public static void m190b(String str) {
        if (Thread.currentThread().getStackTrace().length == 0) {
            return;
        }
        Log.i(f157b, str);
    }

    /* JADX INFO: renamed from: c */
    public static void m191c(String str) {
        if (!f156a || Thread.currentThread().getStackTrace().length == 0) {
            return;
        }
        Log.e(f157b, m188a() + ";" + str);
    }
}
