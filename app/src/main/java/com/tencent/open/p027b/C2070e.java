package com.tencent.open.p027b;

import com.tencent.open.utils.C2084d;
import com.tencent.open.utils.C2085e;

/* JADX INFO: renamed from: com.tencent.open.b.e */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class C2070e {
    /* JADX INFO: renamed from: a */
    public static int m2167a(String str) {
        int iM2230a = C2085e.m2221a(C2084d.m2215a(), str).m2230a("Common_BusinessReportFrequency");
        if (iM2230a == 0) {
            return 100;
        }
        return iM2230a;
    }

    /* JADX INFO: renamed from: a */
    public static int m2166a() {
        int iM2230a = C2085e.m2221a(C2084d.m2215a(), (String) null).m2230a("Common_HttpRetryCount");
        if (iM2230a == 0) {
            return 2;
        }
        return iM2230a;
    }
}
