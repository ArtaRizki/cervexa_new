package com.jieli.stream.p016dv.running2.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class ToastUtil {
    private static WeakReference<Context> contextWeakReference;
    private static String tag = ToastUtil.class.getSimpleName();

    public static void init(Context context) {
        if (context == null) {
            return;
        }
        contextWeakReference = new WeakReference<>(context.getApplicationContext());
    }

    private static void showToast(String str, int i) {
        WeakReference<Context> weakReference = contextWeakReference;
        if (weakReference == null) {
            throw new RuntimeException("u have not init toast utils");
        }
        if (weakReference.get() == null) {
            Dbug.m1388e(tag, "contextWeakReference.get is null ");
            return;
        }
        if (!TextUtils.isEmpty(str) && !str.contains("TF") && i >= 0) {
            Toast toastMakeText = Toast.makeText(contextWeakReference.get(), str, i);
            toastMakeText.setText(str);
            toastMakeText.setDuration(i);
            toastMakeText.show();
            return;
        }
        Dbug.m1388e(tag, "Error: msg=" + str + ", duration=" + i);
    }

    public static void showToastShort(String str) {
        showToast(str, 0);
    }

    public static void showToastLong(String str) {
        showToast(str, 1);
    }
}
