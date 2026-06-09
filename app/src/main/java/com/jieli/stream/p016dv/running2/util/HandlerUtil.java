package com.jieli.stream.p016dv.running2.util;

import android.os.Handler;
import android.os.Looper;

/* JADX INFO: loaded from: classes.dex */
public class HandlerUtil {
    private static Handler mHandler;

    public static void post(Runnable runnable) {
        postDelayed(runnable, 0L);
    }

    public static void postDelayed(Runnable runnable, long j) {
        if (mHandler == null) {
            mHandler = new Handler(Looper.getMainLooper());
        }
        mHandler.postDelayed(runnable, j);
    }
}
