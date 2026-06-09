package com.tencent.bugly.proguard;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.tencent.bugly.crashreport.common.info.C1958a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: com.tencent.bugly.proguard.n */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public final class C2011n {

    /* JADX INFO: renamed from: a */
    public static final long f2824a = System.currentTimeMillis();

    /* JADX INFO: renamed from: b */
    private static C2011n f2825b;

    /* JADX INFO: renamed from: c */
    private Context f2826c;

    /* JADX INFO: renamed from: f */
    private SharedPreferences f2829f;

    /* JADX INFO: renamed from: e */
    private Map<Integer, Map<String, C2010m>> f2828e = new HashMap();

    /* JADX INFO: renamed from: d */
    private String f2827d = C1958a.m1472b().f2400d;

    private C2011n(Context context) {
        this.f2826c = context;
        this.f2829f = context.getSharedPreferences("crashrecord", 0);
    }

    /* JADX INFO: renamed from: a */
    public static synchronized C2011n m1786a(Context context) {
        if (f2825b == null) {
            f2825b = new C2011n(context);
        }
        return f2825b;
    }

    /* JADX INFO: renamed from: a */
    public static synchronized C2011n m1785a() {
        return f2825b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b */
    public synchronized boolean m1792b(int i) {
        try {
            List<C2010m> listM1795c = m1795c(i);
            if (listM1795c == null) {
                return false;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (C2010m c2010m : listM1795c) {
                if (c2010m.f2818b != null && c2010m.f2818b.equalsIgnoreCase(this.f2827d) && c2010m.f2820d > 0) {
                    arrayList.add(c2010m);
                }
                if (c2010m.f2819c + 86400000 < jCurrentTimeMillis) {
                    arrayList2.add(c2010m);
                }
            }
            Collections.sort(arrayList);
            if (arrayList.size() >= 2) {
                if (arrayList.size() <= 0 || ((C2010m) arrayList.get(arrayList.size() - 1)).f2819c + 86400000 >= jCurrentTimeMillis) {
                    return true;
                }
                listM1795c.clear();
                m1789a(i, listM1795c);
                return false;
            }
            listM1795c.removeAll(arrayList2);
            m1789a(i, listM1795c);
            return false;
        } catch (Exception unused) {
            C2021x.m1873e("isFrequentCrash failed", new Object[0]);
            return false;
        }
    }

    /* JADX INFO: renamed from: a */
    public final void m1796a(int i, final int i2) {
        final int i3 = 1004;
        C2020w.m1858a().m1860a(new Runnable() { // from class: com.tencent.bugly.proguard.n.1
            @Override // java.lang.Runnable
            public final void run() {
                C2010m c2010m;
                try {
                    if (TextUtils.isEmpty(C2011n.this.f2827d)) {
                        return;
                    }
                    List<C2010m> listM1795c = C2011n.this.m1795c(i3);
                    if (listM1795c == null) {
                        listM1795c = new ArrayList();
                    }
                    if (C2011n.this.f2828e.get(Integer.valueOf(i3)) == null) {
                        C2011n.this.f2828e.put(Integer.valueOf(i3), new HashMap());
                    }
                    if (((Map) C2011n.this.f2828e.get(Integer.valueOf(i3))).get(C2011n.this.f2827d) != null) {
                        c2010m = (C2010m) ((Map) C2011n.this.f2828e.get(Integer.valueOf(i3))).get(C2011n.this.f2827d);
                        c2010m.f2820d = i2;
                    } else {
                        c2010m = new C2010m();
                        c2010m.f2817a = i3;
                        c2010m.f2823g = C2011n.f2824a;
                        c2010m.f2818b = C2011n.this.f2827d;
                        c2010m.f2822f = C1958a.m1472b().f2407k;
                        c2010m.f2821e = C1958a.m1472b().f2402f;
                        c2010m.f2819c = System.currentTimeMillis();
                        c2010m.f2820d = i2;
                        ((Map) C2011n.this.f2828e.get(Integer.valueOf(i3))).put(C2011n.this.f2827d, c2010m);
                    }
                    ArrayList arrayList = new ArrayList();
                    boolean z = false;
                    for (C2010m c2010m2 : listM1795c) {
                        if (c2010m2.f2823g == c2010m.f2823g && c2010m2.f2818b != null && c2010m2.f2818b.equalsIgnoreCase(c2010m.f2818b)) {
                            z = true;
                            c2010m2.f2820d = c2010m.f2820d;
                        }
                        if ((c2010m2.f2821e != null && !c2010m2.f2821e.equalsIgnoreCase(c2010m.f2821e)) || ((c2010m2.f2822f != null && !c2010m2.f2822f.equalsIgnoreCase(c2010m.f2822f)) || c2010m2.f2820d <= 0)) {
                            arrayList.add(c2010m2);
                        }
                    }
                    listM1795c.removeAll(arrayList);
                    if (!z) {
                        listM1795c.add(c2010m);
                    }
                    C2011n.this.m1789a(i3, listM1795c);
                } catch (Exception unused) {
                    C2021x.m1873e("saveCrashRecord failed", new Object[0]);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0046 A[Catch: all -> 0x005c, Exception -> 0x005e, PHI: r6
  0x0046: PHI (r6v10 java.io.ObjectInputStream) = (r6v9 java.io.ObjectInputStream), (r6v11 java.io.ObjectInputStream) binds: [B:17:0x0044, B:22:0x0052] A[DONT_GENERATE, DONT_INLINE], TRY_ENTER, TRY_LEAVE, TryCatch #1 {Exception -> 0x005e, blocks: (B:4:0x0003, B:10:0x0034, B:18:0x0046, B:26:0x0058, B:27:0x005b), top: B:37:0x0003, outer: #4 }] */
    /* JADX WARN: Type inference failed for: r6v4, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v5, types: [java.io.ObjectInputStream] */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX INFO: renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized <T extends java.util.List<?>> T m1795c(int r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            r0 = 0
            r1 = 0
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            android.content.Context r3 = r5.f2826c     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            java.lang.String r4 = "crashrecord"
            java.io.File r3 = r3.getDir(r4, r1)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            r4.<init>()     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            r4.append(r6)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            java.lang.String r6 = r4.toString()     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            r2.<init>(r3, r6)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            boolean r6 = r2.exists()     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            if (r6 != 0) goto L24
            monitor-exit(r5)
            return r0
        L24:
            java.io.ObjectInputStream r6 = new java.io.ObjectInputStream     // Catch: java.lang.Throwable -> L39 java.lang.ClassNotFoundException -> L3c java.io.IOException -> L4a
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L39 java.lang.ClassNotFoundException -> L3c java.io.IOException -> L4a
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L39 java.lang.ClassNotFoundException -> L3c java.io.IOException -> L4a
            r6.<init>(r3)     // Catch: java.lang.Throwable -> L39 java.lang.ClassNotFoundException -> L3c java.io.IOException -> L4a
            java.lang.Object r2 = r6.readObject()     // Catch: java.lang.ClassNotFoundException -> L3d java.io.IOException -> L4b java.lang.Throwable -> L55
            java.util.List r2 = (java.util.List) r2     // Catch: java.lang.ClassNotFoundException -> L3d java.io.IOException -> L4b java.lang.Throwable -> L55
            r6.close()     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            monitor-exit(r5)
            return r2
        L39:
            r2 = move-exception
            r6 = r0
            goto L56
        L3c:
            r6 = r0
        L3d:
            java.lang.String r2 = "get object error"
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L55
            com.tencent.bugly.proguard.C2021x.m1866a(r2, r3)     // Catch: java.lang.Throwable -> L55
            if (r6 == 0) goto L65
        L46:
            r6.close()     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            goto L65
        L4a:
            r6 = r0
        L4b:
            java.lang.String r2 = "open record file error"
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L55
            com.tencent.bugly.proguard.C2021x.m1866a(r2, r3)     // Catch: java.lang.Throwable -> L55
            if (r6 == 0) goto L65
            goto L46
        L55:
            r2 = move-exception
        L56:
            if (r6 == 0) goto L5b
            r6.close()     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
        L5b:
            throw r2     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
        L5c:
            r6 = move-exception
            goto L67
        L5e:
            java.lang.String r6 = "readCrashRecord error"
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L5c
            com.tencent.bugly.proguard.C2021x.m1873e(r6, r1)     // Catch: java.lang.Throwable -> L5c
        L65:
            monitor-exit(r5)
            return r0
        L67:
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2011n.m1795c(int):java.util.List");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:27:0x004f A[Catch: all -> 0x0053, Exception -> 0x0055, TRY_ENTER, TryCatch #4 {Exception -> 0x0055, blocks: (B:7:0x0006, B:11:0x002d, B:21:0x0046, B:27:0x004f, B:28:0x0052), top: B:36:0x0006, outer: #2 }] */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized <T extends java.util.List<?>> void m1789a(int r5, T r6) {
        /*
            r4 = this;
            monitor-enter(r4)
            if (r6 != 0) goto L5
            monitor-exit(r4)
            return
        L5:
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            android.content.Context r2 = r4.f2826c     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            java.lang.String r3 = "crashrecord"
            java.io.File r2 = r2.getDir(r3, r0)     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            r3.<init>()     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            r3.append(r5)     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            java.lang.String r5 = r3.toString()     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            r1.<init>(r2, r5)     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            r5 = 0
            java.io.ObjectOutputStream r2 = new java.io.ObjectOutputStream     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L37
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L37
            r3.<init>(r1)     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L37
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L37
            r2.writeObject(r6)     // Catch: java.io.IOException -> L31 java.lang.Throwable -> L4c
            r2.close()     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            goto L5c
        L31:
            r5 = move-exception
            goto L3a
        L33:
            r6 = move-exception
            r2 = r5
            r5 = r6
            goto L4d
        L37:
            r6 = move-exception
            r2 = r5
            r5 = r6
        L3a:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L4c
            java.lang.String r5 = "open record file error"
            java.lang.Object[] r6 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> L4c
            com.tencent.bugly.proguard.C2021x.m1866a(r5, r6)     // Catch: java.lang.Throwable -> L4c
            if (r2 == 0) goto L4a
            r2.close()     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            goto L5c
        L4a:
            monitor-exit(r4)
            return
        L4c:
            r5 = move-exception
        L4d:
            if (r2 == 0) goto L52
            r2.close()     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
        L52:
            throw r5     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
        L53:
            r5 = move-exception
            goto L5e
        L55:
            java.lang.String r5 = "writeCrashRecord error"
            java.lang.Object[] r6 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> L53
            com.tencent.bugly.proguard.C2021x.m1873e(r5, r6)     // Catch: java.lang.Throwable -> L53
        L5c:
            monitor-exit(r4)
            return
        L5e:
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2011n.m1789a(int, java.util.List):void");
    }

    /* JADX INFO: renamed from: a */
    public final synchronized boolean m1797a(final int i) {
        boolean z;
        z = true;
        try {
            z = this.f2829f.getBoolean(i + "_" + this.f2827d, true);
            C2020w.m1858a().m1860a(new Runnable() { // from class: com.tencent.bugly.proguard.n.2
                @Override // java.lang.Runnable
                public final void run() {
                    boolean zM1792b = C2011n.this.m1792b(i);
                    C2011n.this.f2829f.edit().putBoolean(i + "_" + C2011n.this.f2827d, !zM1792b).commit();
                }
            });
        } catch (Exception unused) {
            C2021x.m1873e("canInit error", new Object[0]);
            return z;
        }
        return z;
    }
}
