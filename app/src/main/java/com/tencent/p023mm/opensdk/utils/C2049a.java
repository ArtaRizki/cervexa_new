package com.tencent.p023mm.opensdk.utils;

import android.os.Bundle;

/* JADX INFO: renamed from: com.tencent.mm.opensdk.utils.a */
/* JADX INFO: loaded from: classes2.dex */
public final class C2049a {
    /* JADX INFO: renamed from: a */
    public static int m2055a(Bundle bundle, String str) {
        if (bundle == null) {
            return -1;
        }
        try {
            return bundle.getInt(str, -1);
        } catch (Exception e) {
            Log.m2051e("MicroMsg.IntentUtil", "getIntExtra exception:" + e.getMessage());
            return -1;
        }
    }

    /* JADX INFO: renamed from: b */
    public static String m2056b(Bundle bundle, String str) {
        if (bundle == null) {
            return null;
        }
        try {
            return bundle.getString(str);
        } catch (Exception e) {
            Log.m2051e("MicroMsg.IntentUtil", "getStringExtra exception:" + e.getMessage());
            return null;
        }
    }
}
