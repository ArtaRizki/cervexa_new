package com.tencent.bugly;

import android.content.Context;
import android.util.Log;
import com.tencent.bugly.crashreport.common.info.C1958a;
import com.tencent.bugly.proguard.C2013p;
import com.tencent.bugly.proguard.C2021x;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: com.tencent.bugly.b */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public final class C1950b {

    /* JADX INFO: renamed from: a */
    public static boolean f2297a = true;

    /* JADX INFO: renamed from: b */
    public static List<AbstractC1949a> f2298b = new ArrayList();

    /* JADX INFO: renamed from: c */
    public static boolean f2299c;

    /* JADX INFO: renamed from: d */
    private static C2013p f2300d;

    /* JADX INFO: renamed from: e */
    private static boolean f2301e;

    /* JADX INFO: renamed from: a */
    private static boolean m1422a(C1958a c1958a) {
        List<String> list = c1958a.f2412p;
        c1958a.getClass();
        return list != null && list.contains("bugly");
    }

    /* JADX INFO: renamed from: a */
    public static synchronized void m1418a(Context context) {
        m1419a(context, null);
    }

    /* JADX INFO: renamed from: a */
    public static synchronized void m1419a(Context context, BuglyStrategy buglyStrategy) {
        if (f2301e) {
            C2021x.m1872d("[init] initial Multi-times, ignore this.", new Object[0]);
            return;
        }
        if (context == null) {
            Log.w(C2021x.f2906a, "[init] context of init() is null, check it.");
            return;
        }
        C1958a c1958aM1471a = C1958a.m1471a(context);
        if (m1422a(c1958aM1471a)) {
            f2297a = false;
            return;
        }
        String strM1493f = c1958aM1471a.m1493f();
        if (strM1493f == null) {
            Log.e(C2021x.f2906a, "[init] meta data of BUGLY_APPID in AndroidManifest.xml should be set.");
        } else {
            m1420a(context, strM1493f, c1958aM1471a.f2418v, buglyStrategy);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:80:0x0211 A[Catch: all -> 0x0224, TryCatch #0 {, blocks: (B:4:0x0009, B:6:0x000e, B:10:0x0019, B:14:0x0024, B:18:0x002e, B:20:0x0032, B:21:0x006e, B:23:0x00b0, B:26:0x00b4, B:28:0x00c2, B:30:0x00d0, B:32:0x00d6, B:33:0x00ec, B:34:0x00fb, B:36:0x0101, B:38:0x010b, B:40:0x0111, B:41:0x0127, B:47:0x0157, B:53:0x016b, B:55:0x0175, B:57:0x017b, B:58:0x0191, B:59:0x01a0, B:61:0x01a6, B:63:0x01ac, B:64:0x01c2, B:65:0x01ce, B:42:0x013b, B:44:0x0146, B:46:0x0150, B:50:0x0164, B:52:0x0168, B:67:0x01db, B:77:0x0209, B:78:0x020c, B:80:0x0211, B:82:0x0218, B:74:0x0200, B:76:0x0206, B:69:0x01e3, B:71:0x01f3), top: B:88:0x0009, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01e3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized void m1420a(android.content.Context r20, java.lang.String r21, boolean r22, com.tencent.bugly.BuglyStrategy r23) {
        /*
            Method dump skipped, instruction units count: 551
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.C1950b.m1420a(android.content.Context, java.lang.String, boolean, com.tencent.bugly.BuglyStrategy):void");
    }

    /* JADX INFO: renamed from: a */
    public static synchronized void m1421a(AbstractC1949a abstractC1949a) {
        if (!f2298b.contains(abstractC1949a)) {
            f2298b.add(abstractC1949a);
        }
    }
}
