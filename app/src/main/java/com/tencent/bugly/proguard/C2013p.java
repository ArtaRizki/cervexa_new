package com.tencent.bugly.proguard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.tencent.bugly.AbstractC1949a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: com.tencent.bugly.proguard.p */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public final class C2013p {

    /* JADX INFO: renamed from: a */
    private static C2013p f2835a = null;

    /* JADX INFO: renamed from: b */
    private static C2014q f2836b = null;

    /* JADX INFO: renamed from: c */
    private static boolean f2837c = false;

    private C2013p(Context context, List<AbstractC1949a> list) {
        f2836b = new C2014q(context, list);
    }

    /* JADX INFO: renamed from: a */
    public static synchronized C2013p m1808a(Context context, List<AbstractC1949a> list) {
        if (f2835a == null) {
            f2835a = new C2013p(context, list);
        }
        return f2835a;
    }

    /* JADX INFO: renamed from: a */
    public static synchronized C2013p m1807a() {
        return f2835a;
    }

    /* JADX INFO: renamed from: a */
    public final long m1822a(String str, ContentValues contentValues, InterfaceC2012o interfaceC2012o, boolean z) {
        return m1804a(str, contentValues, (InterfaceC2012o) null);
    }

    /* JADX INFO: renamed from: a */
    public final Cursor m1823a(String str, String[] strArr, String str2, String[] strArr2, InterfaceC2012o interfaceC2012o, boolean z) {
        return m1806a(false, str, strArr, str2, null, null, null, null, null, null);
    }

    /* JADX INFO: renamed from: a */
    public final int m1821a(String str, String str2, String[] strArr, InterfaceC2012o interfaceC2012o, boolean z) {
        return m1802a(str, str2, (String[]) null, (InterfaceC2012o) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public synchronized long m1804a(String str, ContentValues contentValues, InterfaceC2012o interfaceC2012o) {
        long j;
        j = 0;
        try {
            SQLiteDatabase writableDatabase = f2836b.getWritableDatabase();
            if (writableDatabase != null && contentValues != null) {
                long jReplace = writableDatabase.replace(str, "_id", contentValues);
                if (jReplace >= 0) {
                    C2021x.m1871c("[Database] insert %s success.", str);
                } else {
                    C2021x.m1872d("[Database] replace %s error.", str);
                }
                j = jReplace;
            }
        } catch (Throwable th) {
            try {
                if (!C2021x.m1867a(th)) {
                    th.printStackTrace();
                }
                if (interfaceC2012o != null) {
                }
            } finally {
                if (interfaceC2012o != null) {
                    Long.valueOf(0L);
                }
            }
        }
        return j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public synchronized Cursor m1806a(boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6, InterfaceC2012o interfaceC2012o) {
        Cursor cursorQuery;
        cursorQuery = null;
        try {
            SQLiteDatabase writableDatabase = f2836b.getWritableDatabase();
            if (writableDatabase != null) {
                cursorQuery = writableDatabase.query(z, str, strArr, str2, strArr2, str3, str4, str5, str6);
            }
        } finally {
        }
        return cursorQuery;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public synchronized int m1802a(String str, String str2, String[] strArr, InterfaceC2012o interfaceC2012o) {
        int iDelete;
        try {
            SQLiteDatabase writableDatabase = f2836b.getWritableDatabase();
            iDelete = writableDatabase != null ? writableDatabase.delete(str, str2, strArr) : 0;
        } catch (Throwable th) {
            try {
                if (!C2021x.m1867a(th)) {
                    th.printStackTrace();
                }
                if (interfaceC2012o != null) {
                }
            } finally {
                if (interfaceC2012o != null) {
                    Integer.valueOf(0);
                }
            }
        }
        return iDelete;
    }

    /* JADX INFO: renamed from: a */
    public final boolean m1827a(int i, String str, byte[] bArr, InterfaceC2012o interfaceC2012o, boolean z) {
        if (!z) {
            a aVar = new a(4, null);
            aVar.m1830a(i, str, bArr);
            C2020w.m1858a().m1860a(aVar);
            return true;
        }
        return m1813a(i, str, bArr, (InterfaceC2012o) null);
    }

    /* JADX INFO: renamed from: a */
    public final Map<String, byte[]> m1825a(int i, InterfaceC2012o interfaceC2012o, boolean z) {
        return m1810a(i, (InterfaceC2012o) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public boolean m1813a(int i, String str, byte[] bArr, InterfaceC2012o interfaceC2012o) {
        boolean zM1817b = false;
        try {
            C2015r c2015r = new C2015r();
            c2015r.f2860a = i;
            c2015r.f2865f = str;
            c2015r.f2864e = System.currentTimeMillis();
            c2015r.f2866g = bArr;
            zM1817b = m1817b(c2015r);
        } catch (Throwable th) {
            try {
                if (!C2021x.m1867a(th)) {
                    th.printStackTrace();
                }
                if (interfaceC2012o != null) {
                }
            } finally {
                if (interfaceC2012o != null) {
                    Boolean.valueOf(false);
                }
            }
        }
        return zM1817b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public Map<String, byte[]> m1810a(int i, InterfaceC2012o interfaceC2012o) {
        HashMap map = null;
        try {
            List<C2015r> listM1819c = m1819c(i);
            if (listM1819c == null) {
                return null;
            }
            HashMap map2 = new HashMap();
            try {
                for (C2015r c2015r : listM1819c) {
                    byte[] bArr = c2015r.f2866g;
                    if (bArr != null) {
                        map2.put(c2015r.f2865f, bArr);
                    }
                }
                return map2;
            } catch (Throwable th) {
                th = th;
                map = map2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        if (C2021x.m1867a(th)) {
            return map;
        }
        th.printStackTrace();
        return map;
    }

    /* JADX INFO: renamed from: a */
    public final synchronized boolean m1828a(C2015r c2015r) {
        ContentValues contentValuesM1818c;
        if (c2015r == null) {
            return false;
        }
        try {
            SQLiteDatabase writableDatabase = f2836b.getWritableDatabase();
            if (writableDatabase == null || (contentValuesM1818c = m1818c(c2015r)) == null) {
                return false;
            }
            long jReplace = writableDatabase.replace("t_lr", "_id", contentValuesM1818c);
            if (jReplace < 0) {
                return false;
            }
            C2021x.m1871c("[Database] insert %s success.", "t_lr");
            c2015r.f2860a = jReplace;
            return true;
        } catch (Throwable th) {
            try {
                if (!C2021x.m1867a(th)) {
                    th.printStackTrace();
                }
                return false;
            } finally {
            }
        }
    }

    /* JADX INFO: renamed from: b */
    private synchronized boolean m1817b(C2015r c2015r) {
        ContentValues contentValuesM1820d;
        if (c2015r == null) {
            return false;
        }
        try {
            SQLiteDatabase writableDatabase = f2836b.getWritableDatabase();
            if (writableDatabase == null || (contentValuesM1820d = m1820d(c2015r)) == null) {
                return false;
            }
            long jReplace = writableDatabase.replace("t_pf", "_id", contentValuesM1820d);
            if (jReplace < 0) {
                return false;
            }
            C2021x.m1871c("[Database] insert %s success.", "t_pf");
            c2015r.f2860a = jReplace;
            return true;
        } catch (Throwable th) {
            try {
                if (!C2021x.m1867a(th)) {
                    th.printStackTrace();
                }
                return false;
            } finally {
            }
        }
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00aa A[Catch: all -> 0x00b3, TRY_LEAVE, TryCatch #1 {all -> 0x00b3, blocks: (B:36:0x00a4, B:38:0x00aa), top: B:52:0x00a4, outer: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00af A[Catch: all -> 0x00bc, TRY_ENTER, TryCatch #4 {, blocks: (B:3:0x0001, B:14:0x0031, B:31:0x009b, B:40:0x00af, B:43:0x00b6, B:44:0x00b9, B:36:0x00a4, B:38:0x00aa), top: B:58:0x0001, inners: #1 }] */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized java.util.List<com.tencent.bugly.proguard.C2015r> m1824a(int r12) {
        /*
            r11 = this;
            monitor-enter(r11)
            com.tencent.bugly.proguard.q r0 = com.tencent.bugly.proguard.C2013p.f2836b     // Catch: java.lang.Throwable -> Lbc
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()     // Catch: java.lang.Throwable -> Lbc
            r9 = 0
            if (r0 == 0) goto Lba
            if (r12 < 0) goto L20
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L1c
            java.lang.String r2 = "_tp = "
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L1c
            r1.append(r12)     // Catch: java.lang.Throwable -> L1c
            java.lang.String r12 = r1.toString()     // Catch: java.lang.Throwable -> L1c
            r4 = r12
            goto L21
        L1c:
            r12 = move-exception
            r0 = r9
            goto La4
        L20:
            r4 = r9
        L21:
            java.lang.String r2 = "t_lr"
            r3 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r1 = r0
            android.database.Cursor r12 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L1c
            if (r12 != 0) goto L36
            if (r12 == 0) goto L34
            r12.close()     // Catch: java.lang.Throwable -> Lbc
        L34:
            monitor-exit(r11)
            return r9
        L36:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La0
            r1.<init>()     // Catch: java.lang.Throwable -> La0
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch: java.lang.Throwable -> La0
            r2.<init>()     // Catch: java.lang.Throwable -> La0
        L40:
            boolean r3 = r12.moveToNext()     // Catch: java.lang.Throwable -> La0
            r4 = 0
            if (r3 == 0) goto L71
            com.tencent.bugly.proguard.r r3 = m1809a(r12)     // Catch: java.lang.Throwable -> La0
            if (r3 == 0) goto L51
            r2.add(r3)     // Catch: java.lang.Throwable -> La0
            goto L40
        L51:
            java.lang.String r3 = "_id"
            int r3 = r12.getColumnIndex(r3)     // Catch: java.lang.Throwable -> L69
            long r5 = r12.getLong(r3)     // Catch: java.lang.Throwable -> L69
            java.lang.String r3 = " or _id"
            r1.append(r3)     // Catch: java.lang.Throwable -> L69
            java.lang.String r3 = " = "
            r1.append(r3)     // Catch: java.lang.Throwable -> L69
            r1.append(r5)     // Catch: java.lang.Throwable -> L69
            goto L40
        L69:
            java.lang.String r3 = "[Database] unknown id."
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> La0
            com.tencent.bugly.proguard.C2021x.m1872d(r3, r4)     // Catch: java.lang.Throwable -> La0
            goto L40
        L71:
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> La0
            int r3 = r1.length()     // Catch: java.lang.Throwable -> La0
            if (r3 <= 0) goto L99
            r3 = 4
            java.lang.String r1 = r1.substring(r3)     // Catch: java.lang.Throwable -> La0
            java.lang.String r3 = "t_lr"
            int r0 = r0.delete(r3, r1, r9)     // Catch: java.lang.Throwable -> La0
            java.lang.String r1 = "[Database] deleted %s illegal data %d"
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> La0
            java.lang.String r5 = "t_lr"
            r3[r4] = r5     // Catch: java.lang.Throwable -> La0
            r4 = 1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch: java.lang.Throwable -> La0
            r3[r4] = r0     // Catch: java.lang.Throwable -> La0
            com.tencent.bugly.proguard.C2021x.m1872d(r1, r3)     // Catch: java.lang.Throwable -> La0
        L99:
            if (r12 == 0) goto L9e
            r12.close()     // Catch: java.lang.Throwable -> Lbc
        L9e:
            monitor-exit(r11)
            return r2
        La0:
            r0 = move-exception
            r10 = r0
            r0 = r12
            r12 = r10
        La4:
            boolean r1 = com.tencent.bugly.proguard.C2021x.m1867a(r12)     // Catch: java.lang.Throwable -> Lb3
            if (r1 != 0) goto Lad
            r12.printStackTrace()     // Catch: java.lang.Throwable -> Lb3
        Lad:
            if (r0 == 0) goto Lba
            r0.close()     // Catch: java.lang.Throwable -> Lbc
            goto Lba
        Lb3:
            r12 = move-exception
            if (r0 == 0) goto Lb9
            r0.close()     // Catch: java.lang.Throwable -> Lbc
        Lb9:
            throw r12     // Catch: java.lang.Throwable -> Lbc
        Lba:
            monitor-exit(r11)
            return r9
        Lbc:
            r12 = move-exception
            monitor-exit(r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2013p.m1824a(int):java.util.List");
    }

    /* JADX INFO: renamed from: a */
    public final synchronized void m1826a(List<C2015r> list) {
        if (list != null) {
            if (list.size() != 0) {
                SQLiteDatabase writableDatabase = f2836b.getWritableDatabase();
                if (writableDatabase != null) {
                    StringBuilder sb = new StringBuilder();
                    for (C2015r c2015r : list) {
                        sb.append(" or _id");
                        sb.append(" = ");
                        sb.append(c2015r.f2860a);
                    }
                    String string = sb.toString();
                    if (string.length() > 0) {
                        string = string.substring(4);
                    }
                    sb.setLength(0);
                    try {
                        C2021x.m1871c("[Database] deleted %s data %d", "t_lr", Integer.valueOf(writableDatabase.delete("t_lr", string, null)));
                    } catch (Throwable th) {
                        if (C2021x.m1867a(th)) {
                            return;
                        }
                        th.printStackTrace();
                    }
                }
            }
        }
    }

    /* JADX INFO: renamed from: b */
    public final synchronized void m1829b(int i) {
        String str;
        SQLiteDatabase writableDatabase = f2836b.getWritableDatabase();
        if (writableDatabase != null) {
            if (i >= 0) {
                try {
                    str = "_tp = " + i;
                } catch (Throwable th) {
                    if (C2021x.m1867a(th)) {
                        return;
                    }
                    th.printStackTrace();
                    return;
                }
            } else {
                str = null;
            }
            C2021x.m1871c("[Database] deleted %s data %d", "t_lr", Integer.valueOf(writableDatabase.delete("t_lr", str, null)));
        }
    }

    /* JADX INFO: renamed from: c */
    private static ContentValues m1818c(C2015r c2015r) {
        if (c2015r == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (c2015r.f2860a > 0) {
                contentValues.put("_id", Long.valueOf(c2015r.f2860a));
            }
            contentValues.put("_tp", Integer.valueOf(c2015r.f2861b));
            contentValues.put("_pc", c2015r.f2862c);
            contentValues.put("_th", c2015r.f2863d);
            contentValues.put("_tm", Long.valueOf(c2015r.f2864e));
            if (c2015r.f2866g != null) {
                contentValues.put("_dt", c2015r.f2866g);
            }
            return contentValues;
        } catch (Throwable th) {
            if (!C2021x.m1867a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* JADX INFO: renamed from: a */
    private static C2015r m1809a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            C2015r c2015r = new C2015r();
            c2015r.f2860a = cursor.getLong(cursor.getColumnIndex("_id"));
            c2015r.f2861b = cursor.getInt(cursor.getColumnIndex("_tp"));
            c2015r.f2862c = cursor.getString(cursor.getColumnIndex("_pc"));
            c2015r.f2863d = cursor.getString(cursor.getColumnIndex("_th"));
            c2015r.f2864e = cursor.getLong(cursor.getColumnIndex("_tm"));
            c2015r.f2866g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return c2015r;
        } catch (Throwable th) {
            if (!C2021x.m1867a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* JADX INFO: renamed from: c */
    private synchronized List<C2015r> m1819c(int i) {
        Cursor cursorQuery;
        try {
            SQLiteDatabase writableDatabase = f2836b.getWritableDatabase();
            if (writableDatabase != null) {
                String str = "_id = " + i;
                cursorQuery = writableDatabase.query("t_pf", null, str, null, null, null, null);
                if (cursorQuery == null) {
                    return null;
                }
                try {
                    StringBuilder sb = new StringBuilder();
                    ArrayList arrayList = new ArrayList();
                    while (cursorQuery.moveToNext()) {
                        C2015r c2015rM1816b = m1816b(cursorQuery);
                        if (c2015rM1816b != null) {
                            arrayList.add(c2015rM1816b);
                        } else {
                            try {
                                String string = cursorQuery.getString(cursorQuery.getColumnIndex("_tp"));
                                sb.append(" or _tp");
                                sb.append(" = ");
                                sb.append(string);
                            } catch (Throwable unused) {
                                C2021x.m1872d("[Database] unknown id.", new Object[0]);
                            }
                        }
                    }
                    if (sb.length() > 0) {
                        sb.append(" and _id");
                        sb.append(" = ");
                        sb.append(i);
                        C2021x.m1872d("[Database] deleted %s illegal data %d.", "t_pf", Integer.valueOf(writableDatabase.delete("t_pf", str.substring(4), null)));
                    }
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return arrayList;
                } catch (Throwable th) {
                    th = th;
                    try {
                        if (!C2021x.m1867a(th)) {
                            th.printStackTrace();
                        }
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return null;
                    } finally {
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
            cursorQuery = null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public synchronized boolean m1812a(int i, String str, InterfaceC2012o interfaceC2012o) {
        boolean z;
        String str2;
        z = false;
        try {
            SQLiteDatabase writableDatabase = f2836b.getWritableDatabase();
            if (writableDatabase != null) {
                if (C2023z.m1914a(str)) {
                    str2 = "_id = " + i;
                } else {
                    str2 = "_id = " + i + " and _tp = \"" + str + "\"";
                }
                int iDelete = writableDatabase.delete("t_pf", str2, null);
                C2021x.m1871c("[Database] deleted %s data %d", "t_pf", Integer.valueOf(iDelete));
                if (iDelete > 0) {
                    z = true;
                }
            }
        } catch (Throwable th) {
            try {
                if (!C2021x.m1867a(th)) {
                    th.printStackTrace();
                }
                if (interfaceC2012o != null) {
                }
            } finally {
                if (interfaceC2012o != null) {
                    Boolean.valueOf(false);
                }
            }
        }
        return z;
    }

    /* JADX INFO: renamed from: d */
    private static ContentValues m1820d(C2015r c2015r) {
        if (c2015r != null && !C2023z.m1914a(c2015r.f2865f)) {
            try {
                ContentValues contentValues = new ContentValues();
                if (c2015r.f2860a > 0) {
                    contentValues.put("_id", Long.valueOf(c2015r.f2860a));
                }
                contentValues.put("_tp", c2015r.f2865f);
                contentValues.put("_tm", Long.valueOf(c2015r.f2864e));
                if (c2015r.f2866g != null) {
                    contentValues.put("_dt", c2015r.f2866g);
                }
                return contentValues;
            } catch (Throwable th) {
                if (!C2021x.m1867a(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: b */
    private static C2015r m1816b(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            C2015r c2015r = new C2015r();
            c2015r.f2860a = cursor.getLong(cursor.getColumnIndex("_id"));
            c2015r.f2864e = cursor.getLong(cursor.getColumnIndex("_tm"));
            c2015r.f2865f = cursor.getString(cursor.getColumnIndex("_tp"));
            c2015r.f2866g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return c2015r;
        } catch (Throwable th) {
            if (!C2021x.m1867a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* JADX INFO: renamed from: com.tencent.bugly.proguard.p$a */
    /* JADX INFO: compiled from: BUGLY */
    class a extends Thread {

        /* JADX INFO: renamed from: a */
        private int f2838a;

        /* JADX INFO: renamed from: b */
        private InterfaceC2012o f2839b;

        /* JADX INFO: renamed from: c */
        private String f2840c;

        /* JADX INFO: renamed from: d */
        private ContentValues f2841d;

        /* JADX INFO: renamed from: e */
        private boolean f2842e;

        /* JADX INFO: renamed from: f */
        private String[] f2843f;

        /* JADX INFO: renamed from: g */
        private String f2844g;

        /* JADX INFO: renamed from: h */
        private String[] f2845h;

        /* JADX INFO: renamed from: i */
        private String f2846i;

        /* JADX INFO: renamed from: j */
        private String f2847j;

        /* JADX INFO: renamed from: k */
        private String f2848k;

        /* JADX INFO: renamed from: l */
        private String f2849l;

        /* JADX INFO: renamed from: m */
        private String f2850m;

        /* JADX INFO: renamed from: n */
        private String[] f2851n;

        /* JADX INFO: renamed from: o */
        private int f2852o;

        /* JADX INFO: renamed from: p */
        private String f2853p;

        /* JADX INFO: renamed from: q */
        private byte[] f2854q;

        public a(int i, InterfaceC2012o interfaceC2012o) {
            this.f2838a = i;
            this.f2839b = interfaceC2012o;
        }

        /* JADX INFO: renamed from: a */
        public final void m1831a(boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
            this.f2842e = z;
            this.f2840c = str;
            this.f2843f = strArr;
            this.f2844g = str2;
            this.f2845h = strArr2;
            this.f2846i = str3;
            this.f2847j = str4;
            this.f2848k = str5;
            this.f2849l = str6;
        }

        /* JADX INFO: renamed from: a */
        public final void m1830a(int i, String str, byte[] bArr) {
            this.f2852o = i;
            this.f2853p = str;
            this.f2854q = bArr;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            switch (this.f2838a) {
                case 1:
                    C2013p.this.m1804a(this.f2840c, this.f2841d, this.f2839b);
                    break;
                case 2:
                    C2013p.this.m1802a(this.f2840c, this.f2850m, this.f2851n, this.f2839b);
                    break;
                case 3:
                    Cursor cursorM1806a = C2013p.this.m1806a(this.f2842e, this.f2840c, this.f2843f, this.f2844g, this.f2845h, this.f2846i, this.f2847j, this.f2848k, this.f2849l, this.f2839b);
                    if (cursorM1806a != null) {
                        cursorM1806a.close();
                    }
                    break;
                case 4:
                    C2013p.this.m1813a(this.f2852o, this.f2853p, this.f2854q, this.f2839b);
                    break;
                case 5:
                    C2013p.this.m1810a(this.f2852o, this.f2839b);
                    break;
                case 6:
                    C2013p.this.m1812a(this.f2852o, this.f2853p, this.f2839b);
                    break;
            }
        }
    }
}
