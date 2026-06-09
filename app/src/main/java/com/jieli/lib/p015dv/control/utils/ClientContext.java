package com.jieli.lib.p015dv.control.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

/* JADX INFO: loaded from: classes.dex */
public class ClientContext {

    /* JADX INFO: renamed from: a */
    private static Context f2217a;

    /* JADX INFO: renamed from: b */
    private static Handler f2218b;

    public static void set(Context context) {
        f2217a = context;
    }

    public static Context get() {
        return f2217a;
    }

    public static void post(Runnable runnable) {
        postDelayed(runnable, 0L);
    }

    public static void postDelayed(Runnable runnable, long j) {
        if (f2218b == null) {
            f2218b = new Handler(Looper.getMainLooper());
        }
        f2218b.postDelayed(runnable, j);
    }
}
