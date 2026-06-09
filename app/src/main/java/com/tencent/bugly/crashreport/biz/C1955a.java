package com.tencent.bugly.crashreport.biz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.tencent.bugly.crashreport.common.info.C1958a;
import com.tencent.bugly.crashreport.common.strategy.C1961a;
import com.tencent.bugly.proguard.C2013p;
import com.tencent.bugly.proguard.C2020w;
import com.tencent.bugly.proguard.C2021x;
import com.tencent.bugly.proguard.C2023z;
import com.tencent.bugly.proguard.InterfaceC2012o;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: com.tencent.bugly.crashreport.biz.a */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public final class C1955a {

    /* JADX INFO: renamed from: a */
    private Context f2324a;

    /* JADX INFO: renamed from: b */
    private long f2325b;

    /* JADX INFO: renamed from: c */
    private int f2326c;

    /* JADX INFO: renamed from: d */
    private boolean f2327d;

    /* JADX INFO: renamed from: a */
    static /* synthetic */ void m1432a(C1955a c1955a, UserInfoBean userInfoBean, boolean z) {
        List<UserInfoBean> listM1436a;
        if (userInfoBean != null) {
            if (!z && userInfoBean.f2306b != 1 && (listM1436a = c1955a.m1436a(C1958a.m1471a(c1955a.f2324a).f2400d)) != null && listM1436a.size() >= 20) {
                C2021x.m1866a("[UserInfo] There are too many user info in local: %d", Integer.valueOf(listM1436a.size()));
                return;
            }
            long jM1822a = C2013p.m1807a().m1822a("t_ui", m1429a(userInfoBean), (InterfaceC2012o) null, true);
            if (jM1822a >= 0) {
                C2021x.m1871c("[Database] insert %s success with ID: %d", "t_ui", Long.valueOf(jM1822a));
                userInfoBean.f2305a = jM1822a;
            }
        }
    }

    public C1955a(Context context, boolean z) {
        this.f2327d = true;
        this.f2324a = context;
        this.f2327d = z;
    }

    /* JADX INFO: renamed from: a */
    public final void m1438a(int i, boolean z, long j) {
        C1961a c1961aM1544a = C1961a.m1544a();
        if (c1961aM1544a != null && !c1961aM1544a.m1554c().f2431f && i != 1 && i != 3) {
            C2021x.m1873e("UserInfo is disable", new Object[0]);
            return;
        }
        if (i == 1 || i == 3) {
            this.f2326c++;
        }
        C1958a c1958aM1471a = C1958a.m1471a(this.f2324a);
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean.f2306b = i;
        userInfoBean.f2307c = c1958aM1471a.f2400d;
        userInfoBean.f2308d = c1958aM1471a.m1495g();
        userInfoBean.f2309e = System.currentTimeMillis();
        userInfoBean.f2310f = -1L;
        userInfoBean.f2318n = c1958aM1471a.f2407k;
        userInfoBean.f2319o = i == 1 ? 1 : 0;
        userInfoBean.f2316l = c1958aM1471a.m1481a();
        userInfoBean.f2317m = c1958aM1471a.f2413q;
        userInfoBean.f2311g = c1958aM1471a.f2414r;
        userInfoBean.f2312h = c1958aM1471a.f2415s;
        userInfoBean.f2313i = c1958aM1471a.f2416t;
        userInfoBean.f2315k = c1958aM1471a.f2417u;
        userInfoBean.f2322r = c1958aM1471a.m1509t();
        userInfoBean.f2323s = c1958aM1471a.m1514y();
        userInfoBean.f2320p = c1958aM1471a.m1515z();
        userInfoBean.f2321q = c1958aM1471a.m1473A();
        C2020w.m1858a().m1861a(new a(userInfoBean, z), 0L);
    }

    /* JADX INFO: renamed from: a */
    public final void m1437a() {
        this.f2325b = C2023z.m1918b() + 86400000;
        C2020w.m1858a().m1861a(new b(), (this.f2325b - System.currentTimeMillis()) + 5000);
    }

    /* JADX INFO: renamed from: com.tencent.bugly.crashreport.biz.a$a */
    /* JADX INFO: compiled from: BUGLY */
    class a implements Runnable {

        /* JADX INFO: renamed from: a */
        private boolean f2331a;

        /* JADX INFO: renamed from: b */
        private UserInfoBean f2332b;

        public a(UserInfoBean userInfoBean, boolean z) {
            this.f2332b = userInfoBean;
            this.f2331a = z;
        }

        @Override // java.lang.Runnable
        public final void run() {
            C1958a c1958aM1472b;
            try {
                if (this.f2332b != null) {
                    UserInfoBean userInfoBean = this.f2332b;
                    if (userInfoBean != null && (c1958aM1472b = C1958a.m1472b()) != null) {
                        userInfoBean.f2314j = c1958aM1472b.m1491e();
                    }
                    C2021x.m1871c("[UserInfo] Record user info.", new Object[0]);
                    C1955a.m1432a(C1955a.this, this.f2332b, false);
                }
                if (this.f2331a) {
                    C1955a c1955a = C1955a.this;
                    C2020w c2020wM1858a = C2020w.m1858a();
                    if (c2020wM1858a != null) {
                        c2020wM1858a.m1860a(c1955a.new AnonymousClass2());
                    }
                }
            } catch (Throwable th) {
                if (C2021x.m1867a(th)) {
                    return;
                }
                th.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00f2 A[Catch: all -> 0x0173, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x0007, B:11:0x000f, B:15:0x0017, B:17:0x001d, B:21:0x0027, B:23:0x003c, B:26:0x0045, B:28:0x004c, B:29:0x004f, B:31:0x0055, B:33:0x0069, B:34:0x0079, B:38:0x0081, B:39:0x008b, B:40:0x0090, B:42:0x0096, B:44:0x00a4, B:46:0x00b1, B:47:0x00b4, B:49:0x00c2, B:51:0x00c6, B:53:0x00cb, B:55:0x00d0, B:58:0x00d7, B:61:0x00ec, B:63:0x00f2, B:65:0x00f7, B:68:0x00fe, B:72:0x0116, B:74:0x011c, B:77:0x0125, B:79:0x012b, B:82:0x0134, B:84:0x013e, B:87:0x0147, B:91:0x0165, B:94:0x016a, B:59:0x00e6), top: B:100:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x011c A[Catch: all -> 0x0173, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x0007, B:11:0x000f, B:15:0x0017, B:17:0x001d, B:21:0x0027, B:23:0x003c, B:26:0x0045, B:28:0x004c, B:29:0x004f, B:31:0x0055, B:33:0x0069, B:34:0x0079, B:38:0x0081, B:39:0x008b, B:40:0x0090, B:42:0x0096, B:44:0x00a4, B:46:0x00b1, B:47:0x00b4, B:49:0x00c2, B:51:0x00c6, B:53:0x00cb, B:55:0x00d0, B:58:0x00d7, B:61:0x00ec, B:63:0x00f2, B:65:0x00f7, B:68:0x00fe, B:72:0x0116, B:74:0x011c, B:77:0x0125, B:79:0x012b, B:82:0x0134, B:84:0x013e, B:87:0x0147, B:91:0x0165, B:94:0x016a, B:59:0x00e6), top: B:100:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0125 A[Catch: all -> 0x0173, TRY_ENTER, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x0007, B:11:0x000f, B:15:0x0017, B:17:0x001d, B:21:0x0027, B:23:0x003c, B:26:0x0045, B:28:0x004c, B:29:0x004f, B:31:0x0055, B:33:0x0069, B:34:0x0079, B:38:0x0081, B:39:0x008b, B:40:0x0090, B:42:0x0096, B:44:0x00a4, B:46:0x00b1, B:47:0x00b4, B:49:0x00c2, B:51:0x00c6, B:53:0x00cb, B:55:0x00d0, B:58:0x00d7, B:61:0x00ec, B:63:0x00f2, B:65:0x00f7, B:68:0x00fe, B:72:0x0116, B:74:0x011c, B:77:0x0125, B:79:0x012b, B:82:0x0134, B:84:0x013e, B:87:0x0147, B:91:0x0165, B:94:0x016a, B:59:0x00e6), top: B:100:0x0001 }] */
    /* JADX INFO: renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void m1435c() {
        /*
            Method dump skipped, instruction units count: 374
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.biz.C1955a.m1435c():void");
    }

    /* JADX INFO: renamed from: b */
    public final void m1439b() {
        C2020w c2020wM1858a = C2020w.m1858a();
        if (c2020wM1858a != null) {
            c2020wM1858a.m1860a(new AnonymousClass2());
        }
    }

    /* JADX INFO: renamed from: com.tencent.bugly.crashreport.biz.a$2, reason: invalid class name */
    /* JADX INFO: compiled from: BUGLY */
    final class AnonymousClass2 implements Runnable {
        AnonymousClass2() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                C1955a.this.m1435c();
            } catch (Throwable th) {
                C2021x.m1867a(th);
            }
        }
    }

    /* JADX INFO: renamed from: com.tencent.bugly.crashreport.biz.a$b */
    /* JADX INFO: compiled from: BUGLY */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (jCurrentTimeMillis < C1955a.this.f2325b) {
                C2020w.m1858a().m1861a(C1955a.this.new b(), (C1955a.this.f2325b - jCurrentTimeMillis) + 5000);
            } else {
                C1955a.this.m1438a(3, false, 0L);
                C1955a.this.m1437a();
            }
        }
    }

    /* JADX INFO: renamed from: com.tencent.bugly.crashreport.biz.a$c */
    /* JADX INFO: compiled from: BUGLY */
    class c implements Runnable {

        /* JADX INFO: renamed from: a */
        private long f2335a;

        public c(long j) {
            this.f2335a = 21600000L;
            this.f2335a = j;
        }

        @Override // java.lang.Runnable
        public final void run() {
            C1955a c1955a = C1955a.this;
            C2020w c2020wM1858a = C2020w.m1858a();
            if (c2020wM1858a != null) {
                c2020wM1858a.m1860a(c1955a.new AnonymousClass2());
            }
            C1955a c1955a2 = C1955a.this;
            long j = this.f2335a;
            C2020w.m1858a().m1861a(c1955a2.new c(j), j);
        }
    }

    /* JADX INFO: renamed from: a */
    public final List<UserInfoBean> m1436a(String str) {
        Cursor cursorM1823a;
        String str2;
        try {
            if (C2023z.m1914a(str)) {
                str2 = null;
            } else {
                str2 = "_pc = '" + str + "'";
            }
            cursorM1823a = C2013p.m1807a().m1823a("t_ui", null, str2, null, null, true);
            if (cursorM1823a == null) {
                return null;
            }
            try {
                StringBuilder sb = new StringBuilder();
                ArrayList arrayList = new ArrayList();
                while (cursorM1823a.moveToNext()) {
                    UserInfoBean userInfoBeanM1430a = m1430a(cursorM1823a);
                    if (userInfoBeanM1430a != null) {
                        arrayList.add(userInfoBeanM1430a);
                    } else {
                        try {
                            long j = cursorM1823a.getLong(cursorM1823a.getColumnIndex("_id"));
                            sb.append(" or _id");
                            sb.append(" = ");
                            sb.append(j);
                        } catch (Throwable unused) {
                            C2021x.m1872d("[Database] unknown id.", new Object[0]);
                        }
                    }
                }
                String string = sb.toString();
                if (string.length() > 0) {
                    C2021x.m1872d("[Database] deleted %s error data %d", "t_ui", Integer.valueOf(C2013p.m1807a().m1821a("t_ui", string.substring(4), (String[]) null, (InterfaceC2012o) null, true)));
                }
                if (cursorM1823a != null) {
                    cursorM1823a.close();
                }
                return arrayList;
            } catch (Throwable th) {
                th = th;
                try {
                    if (!C2021x.m1867a(th)) {
                        th.printStackTrace();
                    }
                    if (cursorM1823a != null) {
                        cursorM1823a.close();
                    }
                    return null;
                } finally {
                    if (cursorM1823a != null) {
                        cursorM1823a.close();
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
            cursorM1823a = null;
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m1433a(List<UserInfoBean> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size() && i < 50; i++) {
            UserInfoBean userInfoBean = list.get(i);
            sb.append(" or _id");
            sb.append(" = ");
            sb.append(userInfoBean.f2305a);
        }
        String string = sb.toString();
        if (string.length() > 0) {
            string = string.substring(4);
        }
        String str = string;
        sb.setLength(0);
        try {
            C2021x.m1871c("[Database] deleted %s data %d", "t_ui", Integer.valueOf(C2013p.m1807a().m1821a("t_ui", str, (String[]) null, (InterfaceC2012o) null, true)));
        } catch (Throwable th) {
            if (C2021x.m1867a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    /* JADX INFO: renamed from: a */
    private static ContentValues m1429a(UserInfoBean userInfoBean) {
        if (userInfoBean == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (userInfoBean.f2305a > 0) {
                contentValues.put("_id", Long.valueOf(userInfoBean.f2305a));
            }
            contentValues.put("_tm", Long.valueOf(userInfoBean.f2309e));
            contentValues.put("_ut", Long.valueOf(userInfoBean.f2310f));
            contentValues.put("_tp", Integer.valueOf(userInfoBean.f2306b));
            contentValues.put("_pc", userInfoBean.f2307c);
            contentValues.put("_dt", C2023z.m1915a(userInfoBean));
            return contentValues;
        } catch (Throwable th) {
            if (!C2021x.m1867a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* JADX INFO: renamed from: a */
    private static UserInfoBean m1430a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex("_dt"));
            if (blob == null) {
                return null;
            }
            long j = cursor.getLong(cursor.getColumnIndex("_id"));
            UserInfoBean userInfoBean = (UserInfoBean) C2023z.m1896a(blob, UserInfoBean.CREATOR);
            if (userInfoBean != null) {
                userInfoBean.f2305a = j;
            }
            return userInfoBean;
        } catch (Throwable th) {
            if (!C2021x.m1867a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }
}
