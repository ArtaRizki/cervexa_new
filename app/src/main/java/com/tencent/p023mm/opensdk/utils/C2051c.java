package com.tencent.p023mm.opensdk.utils;

import android.net.Uri;
import android.provider.BaseColumns;

/* JADX INFO: renamed from: com.tencent.mm.opensdk.utils.c */
/* JADX INFO: loaded from: classes2.dex */
public final class C2051c {

    /* JADX INFO: renamed from: com.tencent.mm.opensdk.utils.c$a */
    public static final class a {
        /* JADX INFO: renamed from: a */
        public static Object m2058a(int i, String str) {
            try {
                switch (i) {
                    case 1:
                        return Integer.valueOf(str);
                    case 2:
                        return Long.valueOf(str);
                    case 3:
                        return str;
                    case 4:
                        return Boolean.valueOf(str);
                    case 5:
                        return Float.valueOf(str);
                    case 6:
                        return Double.valueOf(str);
                    default:
                        Log.m2051e("MicroMsg.SDK.PluginProvider.Resolver", "unknown type");
                        return null;
                }
            } catch (Exception e) {
                Log.m2051e("MicroMsg.SDK.PluginProvider.Resolver", "resolveObj exception:" + e.getMessage());
                return null;
            }
        }
    }

    /* JADX INFO: renamed from: com.tencent.mm.opensdk.utils.c$b */
    public static final class b implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.parse("content://com.tencent.mm.sdk.plugin.provider/sharedpref");
    }
}
