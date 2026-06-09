package com.xyzlf.share.library.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/* JADX INFO: loaded from: classes2.dex */
public class ToastUtil {
    public static void showToast(Context context, int i, boolean z) {
        if (context == null) {
            return;
        }
        String string = null;
        try {
            string = context.getString(i);
        } catch (Exception unused) {
        }
        if (TextUtils.isEmpty(string)) {
            return;
        }
        showToast(context, string, z);
    }

    public static void showToast(Context context, String str, boolean z) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(context, str, !z ? 1 : 0).show();
    }
}
