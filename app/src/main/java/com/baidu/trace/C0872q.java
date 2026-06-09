package com.baidu.trace;

import android.content.Context;
import android.os.Build;
import com.baidu.lbsapi.auth.tracesdk.LBSAuthManager;
import com.baidu.trace.p012c.C0854e;
import com.jieli.stream.p016dv.running2.util.IConstant;

/* JADX INFO: renamed from: com.baidu.trace.q */
/* JADX INFO: loaded from: classes.dex */
public final class C0872q {

    /* JADX INFO: renamed from: a */
    protected static String f1847a = null;

    /* JADX INFO: renamed from: b */
    protected static String f1848b = null;

    /* JADX INFO: renamed from: c */
    protected static String f1849c = null;

    /* JADX INFO: renamed from: d */
    protected static String f1850d = null;

    /* JADX INFO: renamed from: e */
    private static boolean f1851e = false;

    /* JADX INFO: renamed from: a */
    protected static void m1272a(Context context, LBSAuthManager lBSAuthManager) {
        if (f1851e) {
            return;
        }
        f1847a = IConstant.ANDROID_DIR + Build.VERSION.RELEASE;
        f1848b = "3.0.7";
        f1849c = lBSAuthManager.getCUID();
        f1850d = C0854e.m1238c(context);
        lBSAuthManager.getKey();
        lBSAuthManager.getMCode();
        f1851e = true;
    }
}
