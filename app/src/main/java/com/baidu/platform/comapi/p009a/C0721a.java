package com.baidu.platform.comapi.p009a;

import android.content.Context;

/* JADX INFO: renamed from: com.baidu.platform.comapi.a.a */
/* JADX INFO: loaded from: classes.dex */
public class C0721a {

    /* JADX INFO: renamed from: a */
    private static int f792a = 621133959;

    /* JADX INFO: renamed from: a */
    public static boolean m531a(Context context) {
        return m533c(context);
    }

    /* JADX INFO: renamed from: b */
    private static int m532b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.baidu.BaiduMap", 64).signatures[0].hashCode();
        } catch (Exception unused) {
            return 0;
        }
    }

    /* JADX INFO: renamed from: c */
    private static boolean m533c(Context context) {
        return m532b(context) == f792a;
    }
}
