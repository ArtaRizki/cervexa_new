package com.jieli.stream.p016dv.running2.util;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class Dbug {
    private static boolean IS_DEBUG = false;

    public static void openOrCloseDebug(boolean z) {
        IS_DEBUG = z;
    }

    /* JADX INFO: renamed from: v */
    public static void m1390v(String str, String str2) {
        if (IS_DEBUG) {
            Log.v(str, str2);
        }
    }

    /* JADX INFO: renamed from: d */
    public static void m1387d(String str, String str2) {
        if (IS_DEBUG) {
            Log.d(str, str2);
        }
    }

    /* JADX INFO: renamed from: i */
    public static void m1389i(String str, String str2) {
        if (IS_DEBUG) {
            Log.i(str, str2);
        }
    }

    /* JADX INFO: renamed from: w */
    public static void m1391w(String str, String str2) {
        if (IS_DEBUG) {
            Log.w(str, str2);
        }
    }

    /* JADX INFO: renamed from: e */
    public static void m1388e(String str, String str2) {
        if (IS_DEBUG) {
            Log.e(str, str2);
        }
    }
}
