package com.tencent.p023mm.opensdk.utils;

/* JADX INFO: renamed from: com.tencent.mm.opensdk.utils.d */
/* JADX INFO: loaded from: classes2.dex */
public final class C2052d {
    /* JADX INFO: renamed from: a */
    public static boolean m2059a(String str) {
        return str == null || str.length() <= 0;
    }

    /* JADX INFO: renamed from: b */
    public static int m2060b(String str) {
        if (str != null) {
            try {
                if (str.length() > 0) {
                    return Integer.parseInt(str);
                }
            } catch (Exception unused) {
            }
        }
        return 0;
    }
}
