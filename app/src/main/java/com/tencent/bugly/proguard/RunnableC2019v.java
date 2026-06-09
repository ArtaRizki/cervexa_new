package com.tencent.bugly.proguard;

import android.content.Context;
import com.bumptech.glide.load.Key;
import com.tencent.bugly.crashreport.common.info.C1958a;
import com.tencent.bugly.crashreport.common.strategy.C1961a;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: renamed from: com.tencent.bugly.proguard.v */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public final class RunnableC2019v implements Runnable {

    /* JADX INFO: renamed from: a */
    private int f2884a;

    /* JADX INFO: renamed from: b */
    private int f2885b;

    /* JADX INFO: renamed from: c */
    private final Context f2886c;

    /* JADX INFO: renamed from: d */
    private final int f2887d;

    /* JADX INFO: renamed from: e */
    private final byte[] f2888e;

    /* JADX INFO: renamed from: f */
    private final C1958a f2889f;

    /* JADX INFO: renamed from: g */
    private final C1961a f2890g;

    /* JADX INFO: renamed from: h */
    private final C2016s f2891h;

    /* JADX INFO: renamed from: i */
    private final C2018u f2892i;

    /* JADX INFO: renamed from: j */
    private final int f2893j;

    /* JADX INFO: renamed from: k */
    private final InterfaceC2017t f2894k;

    /* JADX INFO: renamed from: l */
    private final InterfaceC2017t f2895l;

    /* JADX INFO: renamed from: m */
    private String f2896m;

    /* JADX INFO: renamed from: n */
    private final String f2897n;

    /* JADX INFO: renamed from: o */
    private final Map<String, String> f2898o;

    /* JADX INFO: renamed from: p */
    private int f2899p;

    /* JADX INFO: renamed from: q */
    private long f2900q;

    /* JADX INFO: renamed from: r */
    private long f2901r;

    /* JADX INFO: renamed from: s */
    private boolean f2902s;

    public RunnableC2019v(Context context, int i, int i2, byte[] bArr, String str, String str2, InterfaceC2017t interfaceC2017t, boolean z, boolean z2) {
        this(context, i, i2, bArr, str, str2, interfaceC2017t, 2, 30000, z2, null);
    }

    public RunnableC2019v(Context context, int i, int i2, byte[] bArr, String str, String str2, InterfaceC2017t interfaceC2017t, int i3, int i4, boolean z, Map<String, String> map) {
        this.f2884a = 2;
        this.f2885b = 30000;
        this.f2896m = null;
        this.f2899p = 0;
        this.f2900q = 0L;
        this.f2901r = 0L;
        this.f2902s = false;
        this.f2886c = context;
        this.f2889f = C1958a.m1471a(context);
        this.f2888e = bArr;
        this.f2890g = C1961a.m1544a();
        this.f2891h = C2016s.m1833a(context);
        this.f2892i = C2018u.m1839a();
        this.f2893j = i;
        this.f2896m = str;
        this.f2897n = str2;
        this.f2894k = interfaceC2017t;
        this.f2895l = null;
        this.f2887d = i2;
        if (i3 > 0) {
            this.f2884a = i3;
        }
        if (i4 > 0) {
            this.f2885b = i4;
        }
        this.f2902s = z;
        this.f2898o = map;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0017  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x001a  */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m1854a(com.tencent.bugly.proguard.C1994an r4, boolean r5, int r6, java.lang.String r7) {
        /*
            r3 = this;
            int r4 = r3.f2887d
            r0 = 630(0x276, float:8.83E-43)
            if (r4 == r0) goto L1a
            r0 = 640(0x280, float:8.97E-43)
            if (r4 == r0) goto L17
            r0 = 830(0x33e, float:1.163E-42)
            if (r4 == r0) goto L1a
            r0 = 840(0x348, float:1.177E-42)
            if (r4 == r0) goto L17
            java.lang.String r4 = java.lang.String.valueOf(r4)
            goto L1c
        L17:
            java.lang.String r4 = "userinfo"
            goto L1c
        L1a:
            java.lang.String r4 = "crash"
        L1c:
            r0 = 1
            r1 = 0
            if (r5 == 0) goto L2a
            java.lang.Object[] r6 = new java.lang.Object[r0]
            r6[r1] = r4
            java.lang.String r4 = "[Upload] Success: %s"
            com.tencent.bugly.proguard.C2021x.m1866a(r4, r6)
            goto L3d
        L2a:
            r2 = 3
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r2[r1] = r6
            r2[r0] = r4
            r4 = 2
            r2[r4] = r7
            java.lang.String r4 = "[Upload] Failed to upload(%d) %s: %s"
            com.tencent.bugly.proguard.C2021x.m1873e(r4, r2)
        L3d:
            long r6 = r3.f2900q
            long r0 = r3.f2901r
            long r6 = r6 + r0
            r0 = 0
            int r4 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r4 <= 0) goto L5d
            com.tencent.bugly.proguard.u r4 = r3.f2892i
            boolean r6 = r3.f2902s
            long r6 = r4.m1847a(r6)
            long r0 = r3.f2900q
            long r6 = r6 + r0
            long r0 = r3.f2901r
            long r6 = r6 + r0
            com.tencent.bugly.proguard.u r4 = r3.f2892i
            boolean r0 = r3.f2902s
            r4.m1851a(r6, r0)
        L5d:
            com.tencent.bugly.proguard.t r4 = r3.f2894k
            if (r4 == 0) goto L64
            r4.mo1440a(r5)
        L64:
            com.tencent.bugly.proguard.t r4 = r3.f2895l
            if (r4 == 0) goto L6b
            r4.mo1440a(r5)
        L6b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.RunnableC2019v.m1854a(com.tencent.bugly.proguard.an, boolean, int, java.lang.String):void");
    }

    /* JADX INFO: renamed from: a */
    private static boolean m1855a(C1994an c1994an, C1958a c1958a, C1961a c1961a) {
        if (c1994an == null) {
            C2021x.m1872d("resp == null!", new Object[0]);
            return false;
        }
        if (c1994an.f2748a != 0) {
            C2021x.m1873e("resp result error %d", Byte.valueOf(c1994an.f2748a));
            return false;
        }
        try {
            if (!C2023z.m1914a(c1994an.f2752e) && !C1958a.m1472b().m1498i().equals(c1994an.f2752e)) {
                C2013p.m1807a().m1827a(C1961a.f2448a, "device", c1994an.f2752e.getBytes(Key.STRING_CHARSET_NAME), (InterfaceC2012o) null, true);
                c1958a.m1492e(c1994an.f2752e);
            }
        } catch (Throwable th) {
            C2021x.m1867a(th);
        }
        c1958a.f2406j = c1994an.f2751d;
        if (c1994an.f2749b == 510) {
            if (c1994an.f2750c == null) {
                C2021x.m1873e("[Upload] Strategy data is null. Response cmd: %d", Integer.valueOf(c1994an.f2749b));
                return false;
            }
            C1996ap c1996ap = (C1996ap) C1980a.m1685a(c1994an.f2750c, C1996ap.class);
            if (c1996ap == null) {
                C2021x.m1873e("[Upload] Failed to decode strategy from server. Response cmd: %d", Integer.valueOf(c1994an.f2749b));
                return false;
            }
            c1961a.m1552a(c1996ap);
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:126:0x0224 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01be A[Catch: all -> 0x0338, TryCatch #0 {all -> 0x0338, blocks: (B:3:0x0007, B:5:0x001a, B:8:0x0022, B:11:0x0027, B:13:0x003b, B:15:0x003f, B:17:0x0043, B:20:0x0049, B:22:0x0051, B:24:0x0057, B:26:0x0084, B:27:0x0089, B:29:0x00b8, B:32:0x00c0, B:34:0x00c6, B:35:0x00da, B:38:0x00e2, B:40:0x00f9, B:41:0x0106, B:44:0x0149, B:46:0x015c, B:49:0x0164, B:52:0x016b, B:55:0x0173, B:67:0x01be, B:69:0x01ea, B:70:0x01f2, B:72:0x01f8, B:73:0x0219, B:78:0x0253, B:80:0x0266, B:82:0x0277, B:83:0x027f, B:85:0x0285, B:86:0x02a0, B:89:0x02a9, B:91:0x02b0, B:94:0x02b8, B:96:0x02be, B:98:0x02c5, B:102:0x02db, B:104:0x02ee, B:106:0x02f5, B:101:0x02d8, B:109:0x02fd, B:57:0x017d, B:59:0x0183, B:60:0x018b, B:62:0x0199, B:63:0x01a5, B:64:0x01b2, B:111:0x0323, B:113:0x032a, B:115:0x0331), top: B:123:0x0007 }] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void run() {
        /*
            Method dump skipped, instruction units count: 835
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.RunnableC2019v.run():void");
    }

    /* JADX INFO: renamed from: a */
    public final void m1856a(long j) {
        this.f2899p++;
        this.f2900q += j;
    }

    /* JADX INFO: renamed from: b */
    public final void m1857b(long j) {
        this.f2901r += j;
    }

    /* JADX INFO: renamed from: a */
    private static String m1853a(String str) {
        if (C2023z.m1914a(str)) {
            return str;
        }
        try {
            return String.format("%s?aid=%s", str, UUID.randomUUID().toString());
        } catch (Throwable th) {
            C2021x.m1867a(th);
            return str;
        }
    }
}
