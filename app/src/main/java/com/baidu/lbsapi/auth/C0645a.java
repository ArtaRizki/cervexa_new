package com.baidu.lbsapi.auth;

import android.util.Log;

/* JADX INFO: renamed from: com.baidu.lbsapi.auth.a */
/* JADX INFO: loaded from: classes.dex */
class C0645a {

    /* JADX INFO: renamed from: a */
    public static boolean f120a = false;

    /* JADX INFO: renamed from: b */
    private static String f121b = "BaiduApiAuth";

    /* JADX INFO: renamed from: a */
    public static String m129a() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[2];
        return stackTraceElement.getFileName() + "[" + stackTraceElement.getLineNumber() + "]";
    }

    /* JADX INFO: renamed from: a */
    public static void m130a(String str) {
        if (!f120a || Thread.currentThread().getStackTrace().length == 0) {
            return;
        }
        Log.d(f121b, m129a() + ";" + str);
    }

    /* JADX INFO: renamed from: b */
    public static void m131b(String str) {
        if (Thread.currentThread().getStackTrace().length == 0) {
            return;
        }
        Log.i(f121b, str);
    }

    /* JADX INFO: renamed from: c */
    public static void m132c(String str) {
        if (!f120a || Thread.currentThread().getStackTrace().length == 0) {
            return;
        }
        Log.e(f121b, m129a() + ";" + str);
    }
}
