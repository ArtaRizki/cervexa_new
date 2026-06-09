package com.tencent.bugly.crashreport.crash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.jieli.stream.p016dv.running2.util.IConstant;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.C1950b;
import com.tencent.bugly.crashreport.common.info.C1958a;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import com.tencent.bugly.crashreport.common.strategy.C1961a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import com.tencent.bugly.proguard.AbstractC2008k;
import com.tencent.bugly.proguard.C1980a;
import com.tencent.bugly.proguard.C1988ah;
import com.tencent.bugly.proguard.C1990aj;
import com.tencent.bugly.proguard.C1991ak;
import com.tencent.bugly.proguard.C1992al;
import com.tencent.bugly.proguard.C1993am;
import com.tencent.bugly.proguard.C2013p;
import com.tencent.bugly.proguard.C2015r;
import com.tencent.bugly.proguard.C2018u;
import com.tencent.bugly.proguard.C2021x;
import com.tencent.bugly.proguard.C2023z;
import com.tencent.bugly.proguard.InterfaceC2012o;
import com.tencent.bugly.proguard.InterfaceC2017t;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/* JADX INFO: renamed from: com.tencent.bugly.crashreport.crash.b */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public final class C1971b {

    /* JADX INFO: renamed from: a */
    private static int f2556a;

    /* JADX INFO: renamed from: b */
    private Context f2557b;

    /* JADX INFO: renamed from: c */
    private C2018u f2558c;

    /* JADX INFO: renamed from: d */
    private C2013p f2559d;

    /* JADX INFO: renamed from: e */
    private C1961a f2560e;

    /* JADX INFO: renamed from: f */
    private InterfaceC2012o f2561f;

    /* JADX INFO: renamed from: g */
    private BuglyStrategy.C1948a f2562g;

    public C1971b(int i, Context context, C2018u c2018u, C2013p c2013p, C1961a c1961a, BuglyStrategy.C1948a c1948a, InterfaceC2012o interfaceC2012o) {
        f2556a = i;
        this.f2557b = context;
        this.f2558c = c2018u;
        this.f2559d = c2013p;
        this.f2560e = c1961a;
        this.f2562g = c1948a;
        this.f2561f = interfaceC2012o;
    }

    /* JADX INFO: renamed from: a */
    private static List<C1964a> m1592a(List<C1964a> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        for (C1964a c1964a : list) {
            if (c1964a.f2517d && c1964a.f2515b <= jCurrentTimeMillis - 86400000) {
                arrayList.add(c1964a);
            }
        }
        return arrayList;
    }

    /* JADX INFO: renamed from: a */
    private CrashDetailBean m1589a(List<C1964a> list, CrashDetailBean crashDetailBean) {
        List<CrashDetailBean> listM1597b;
        String[] strArrSplit;
        if (list == null || list.size() == 0) {
            return crashDetailBean;
        }
        CrashDetailBean crashDetailBean2 = null;
        ArrayList arrayList = new ArrayList(10);
        for (C1964a c1964a : list) {
            if (c1964a.f2518e) {
                arrayList.add(c1964a);
            }
        }
        if (arrayList.size() > 0 && (listM1597b = m1597b(arrayList)) != null && listM1597b.size() > 0) {
            Collections.sort(listM1597b);
            for (int i = 0; i < listM1597b.size(); i++) {
                CrashDetailBean crashDetailBean3 = listM1597b.get(i);
                if (i == 0) {
                    crashDetailBean2 = crashDetailBean3;
                } else if (crashDetailBean3.f2506s != null && (strArrSplit = crashDetailBean3.f2506s.split("\n")) != null) {
                    for (String str : strArrSplit) {
                        if (!crashDetailBean2.f2506s.contains(str)) {
                            crashDetailBean2.f2507t++;
                            crashDetailBean2.f2506s += str + "\n";
                        }
                    }
                }
            }
        }
        if (crashDetailBean2 == null) {
            crashDetailBean.f2497j = true;
            crashDetailBean.f2507t = 0;
            crashDetailBean.f2506s = "";
            crashDetailBean2 = crashDetailBean;
        }
        for (C1964a c1964a2 : list) {
            if (!c1964a2.f2518e && !c1964a2.f2517d) {
                String str2 = crashDetailBean2.f2506s;
                StringBuilder sb = new StringBuilder();
                sb.append(c1964a2.f2515b);
                if (!str2.contains(sb.toString())) {
                    crashDetailBean2.f2507t++;
                    crashDetailBean2.f2506s += c1964a2.f2515b + "\n";
                }
            }
        }
        if (crashDetailBean2.f2505r != crashDetailBean.f2505r) {
            String str3 = crashDetailBean2.f2506s;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(crashDetailBean.f2505r);
            if (!str3.contains(sb2.toString())) {
                crashDetailBean2.f2507t++;
                crashDetailBean2.f2506s += crashDetailBean.f2505r + "\n";
            }
        }
        return crashDetailBean2;
    }

    /* JADX INFO: renamed from: a */
    public final boolean m1604a(CrashDetailBean crashDetailBean) {
        return m1605b(crashDetailBean);
    }

    /* JADX INFO: renamed from: b */
    public final boolean m1605b(CrashDetailBean crashDetailBean) {
        if (crashDetailBean == null) {
            return true;
        }
        if (C1972c.f2578n != null && !C1972c.f2578n.isEmpty()) {
            C2021x.m1871c("Crash filter for crash stack is: %s", C1972c.f2578n);
            if (crashDetailBean.f2504q.contains(C1972c.f2578n)) {
                C2021x.m1872d("This crash contains the filter string set. It will not be record and upload.", new Object[0]);
                return true;
            }
        }
        if (C1972c.f2579o != null && !C1972c.f2579o.isEmpty()) {
            C2021x.m1871c("Crash regular filter for crash stack is: %s", C1972c.f2579o);
            if (Pattern.compile(C1972c.f2579o).matcher(crashDetailBean.f2504q).find()) {
                C2021x.m1872d("This crash matches the regular filter string set. It will not be record and upload.", new Object[0]);
                return true;
            }
        }
        if (crashDetailBean.f2489b != 2) {
            C2015r c2015r = new C2015r();
            c2015r.f2861b = 1;
            c2015r.f2862c = crashDetailBean.f2464A;
            c2015r.f2863d = crashDetailBean.f2465B;
            c2015r.f2864e = crashDetailBean.f2505r;
            this.f2559d.m1829b(1);
            this.f2559d.m1828a(c2015r);
            C2021x.m1869b("[crash] a crash occur, handling...", new Object[0]);
        } else {
            C2021x.m1869b("[crash] a caught exception occur, handling...", new Object[0]);
        }
        List<C1964a> listM1596b = m1596b();
        ArrayList arrayList = null;
        if (listM1596b != null && listM1596b.size() > 0) {
            arrayList = new ArrayList(10);
            ArrayList arrayList2 = new ArrayList(10);
            arrayList.addAll(m1592a(listM1596b));
            listM1596b.removeAll(arrayList);
            if (listM1596b.size() > 20) {
                StringBuilder sb = new StringBuilder();
                sb.append("_id in ");
                sb.append("(");
                sb.append("SELECT _id");
                sb.append(" FROM t_cr");
                sb.append(" order by _id");
                sb.append(" limit 5");
                sb.append(")");
                String string = sb.toString();
                sb.setLength(0);
                try {
                    C2021x.m1871c("deleted first record %s data %d", "t_cr", Integer.valueOf(C2013p.m1807a().m1821a("t_cr", string, (String[]) null, (InterfaceC2012o) null, true)));
                } catch (Throwable th) {
                    if (!C2021x.m1867a(th)) {
                        th.printStackTrace();
                    }
                }
            }
            if (!C1950b.f2299c && C1972c.f2568d) {
                boolean z = false;
                for (C1964a c1964a : listM1596b) {
                    if (crashDetailBean.f2508u.equals(c1964a.f2516c)) {
                        if (c1964a.f2518e) {
                            z = true;
                        }
                        arrayList2.add(c1964a);
                    }
                }
                if (z || arrayList2.size() >= C1972c.f2567c) {
                    C2021x.m1866a("same crash occur too much do merged!", new Object[0]);
                    CrashDetailBean crashDetailBeanM1589a = m1589a(arrayList2, crashDetailBean);
                    for (C1964a c1964a2 : arrayList2) {
                        if (c1964a2.f2514a != crashDetailBeanM1589a.f2488a) {
                            arrayList.add(c1964a2);
                        }
                    }
                    m1608e(crashDetailBeanM1589a);
                    m1598c(arrayList);
                    C2021x.m1869b("[crash] save crash success. For this device crash many times, it will not upload crashes immediately", new Object[0]);
                    return true;
                }
            }
        }
        m1608e(crashDetailBean);
        if (arrayList != null && !arrayList.isEmpty()) {
            m1598c(arrayList);
        }
        C2021x.m1869b("[crash] save crash success", new Object[0]);
        return false;
    }

    /* JADX INFO: renamed from: a */
    public final List<CrashDetailBean> m1601a() {
        StrategyBean strategyBeanM1554c = C1961a.m1544a().m1554c();
        if (strategyBeanM1554c == null) {
            C2021x.m1872d("have not synced remote!", new Object[0]);
            return null;
        }
        if (!strategyBeanM1554c.f2430e) {
            C2021x.m1872d("Crashreport remote closed, please check your APP ID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
            C2021x.m1869b("[init] WARNING! Crashreport closed by server, please check your APP ID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
            return null;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        long jM1918b = C2023z.m1918b();
        List<C1964a> listM1596b = m1596b();
        C2021x.m1871c("Size of crash list loaded from DB: %s", Integer.valueOf(listM1596b.size()));
        if (listM1596b == null || listM1596b.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(m1592a(listM1596b));
        listM1596b.removeAll(arrayList);
        Iterator<C1964a> it = listM1596b.iterator();
        while (it.hasNext()) {
            C1964a next = it.next();
            if (next.f2515b < jM1918b - C1972c.f2571g) {
                it.remove();
                arrayList.add(next);
            } else if (next.f2517d) {
                if (next.f2515b >= jCurrentTimeMillis - 86400000) {
                    it.remove();
                } else if (!next.f2518e) {
                    it.remove();
                    arrayList.add(next);
                }
            } else if (next.f2519f >= 3 && next.f2515b < jCurrentTimeMillis - 86400000) {
                it.remove();
                arrayList.add(next);
            }
        }
        if (arrayList.size() > 0) {
            m1598c(arrayList);
        }
        ArrayList arrayList2 = new ArrayList();
        List<CrashDetailBean> listM1597b = m1597b(listM1596b);
        if (listM1597b != null && listM1597b.size() > 0) {
            String str = C1958a.m1472b().f2407k;
            Iterator<CrashDetailBean> it2 = listM1597b.iterator();
            while (it2.hasNext()) {
                CrashDetailBean next2 = it2.next();
                if (!str.equals(next2.f2493f)) {
                    it2.remove();
                    arrayList2.add(next2);
                }
            }
        }
        if (arrayList2.size() > 0) {
            m1599d(arrayList2);
        }
        return listM1597b;
    }

    /* JADX INFO: renamed from: c */
    public final void m1606c(CrashDetailBean crashDetailBean) {
        int i = crashDetailBean.f2489b;
        if (i != 0) {
            if (i != 1) {
                if (i == 3 && !C1972c.m1609a().m1636r()) {
                    return;
                }
            } else if (!C1972c.m1609a().m1635q()) {
                return;
            }
        } else if (!C1972c.m1609a().m1635q()) {
            return;
        }
        if (this.f2561f != null) {
            C2021x.m1871c("Calling 'onCrashHandleEnd' of RQD crash listener.", new Object[0]);
            int i2 = crashDetailBean.f2489b;
        }
    }

    /* JADX INFO: renamed from: a */
    public final void m1602a(CrashDetailBean crashDetailBean, long j, boolean z) {
        if (C1972c.f2576l) {
            C2021x.m1866a("try to upload right now", new Object[0]);
            ArrayList arrayList = new ArrayList();
            arrayList.add(crashDetailBean);
            m1603a(arrayList, 3000L, z, crashDetailBean.f2489b == 7, z);
        }
    }

    /* JADX INFO: renamed from: a */
    public final void m1603a(final List<CrashDetailBean> list, long j, boolean z, boolean z2, boolean z3) {
        C2018u c2018u;
        C1992al c1992al;
        if (C1958a.m1471a(this.f2557b).f2401e && (c2018u = this.f2558c) != null) {
            if (z3 || c2018u.m1852b(C1972c.f2565a)) {
                StrategyBean strategyBeanM1554c = this.f2560e.m1554c();
                if (!strategyBeanM1554c.f2430e) {
                    C2021x.m1872d("remote report is disable!", new Object[0]);
                    C2021x.m1869b("[crash] server closed bugly in this app. please check your appid if is correct, and re-install it", new Object[0]);
                    return;
                }
                if (list == null || list.size() == 0) {
                    return;
                }
                try {
                    String str = strategyBeanM1554c.f2442q;
                    String str2 = StrategyBean.f2427b;
                    Context context = this.f2557b;
                    C1958a c1958aM1472b = C1958a.m1472b();
                    if (context == null || list == null || list.size() == 0 || c1958aM1472b == null) {
                        C2021x.m1872d("enEXPPkg args == null!", new Object[0]);
                        c1992al = null;
                    } else {
                        c1992al = new C1992al();
                        c1992al.f2719a = new ArrayList<>();
                        Iterator<CrashDetailBean> it = list.iterator();
                        while (it.hasNext()) {
                            c1992al.f2719a.add(m1591a(context, it.next(), c1958aM1472b));
                        }
                    }
                    if (c1992al == null) {
                        C2021x.m1872d("create eupPkg fail!", new Object[0]);
                        return;
                    }
                    byte[] bArrM1690a = C1980a.m1690a((AbstractC2008k) c1992al);
                    if (bArrM1690a == null) {
                        C2021x.m1872d("send encode fail!", new Object[0]);
                        return;
                    }
                    C1993am c1993amM1682a = C1980a.m1682a(this.f2557b, 830, bArrM1690a);
                    if (c1993amM1682a == null) {
                        C2021x.m1872d("request package is null.", new Object[0]);
                        return;
                    }
                    InterfaceC2017t interfaceC2017t = new InterfaceC2017t() { // from class: com.tencent.bugly.crashreport.crash.b.1
                        @Override // com.tencent.bugly.proguard.InterfaceC2017t
                        /* JADX INFO: renamed from: a */
                        public final void mo1440a(boolean z4) {
                            C1971b.m1594a(z4, (List<CrashDetailBean>) list);
                        }
                    };
                    if (z) {
                        this.f2558c.m1849a(f2556a, c1993amM1682a, str, str2, interfaceC2017t, j, z2);
                    } else {
                        this.f2558c.m1850a(f2556a, c1993amM1682a, str, str2, interfaceC2017t, false);
                    }
                } catch (Throwable th) {
                    C2021x.m1873e("req cr error %s", th.toString());
                    if (C2021x.m1870b(th)) {
                        return;
                    }
                    th.printStackTrace();
                }
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m1594a(boolean z, List<CrashDetailBean> list) {
        if (list != null && list.size() > 0) {
            C2021x.m1871c("up finish update state %b", Boolean.valueOf(z));
            for (CrashDetailBean crashDetailBean : list) {
                C2021x.m1871c("pre uid:%s uc:%d re:%b me:%b", crashDetailBean.f2490c, Integer.valueOf(crashDetailBean.f2499l), Boolean.valueOf(crashDetailBean.f2491d), Boolean.valueOf(crashDetailBean.f2497j));
                crashDetailBean.f2499l++;
                crashDetailBean.f2491d = z;
                C2021x.m1871c("set uid:%s uc:%d re:%b me:%b", crashDetailBean.f2490c, Integer.valueOf(crashDetailBean.f2499l), Boolean.valueOf(crashDetailBean.f2491d), Boolean.valueOf(crashDetailBean.f2497j));
            }
            Iterator<CrashDetailBean> it = list.iterator();
            while (it.hasNext()) {
                C1972c.m1609a().m1616a(it.next());
            }
            C2021x.m1871c("update state size %d", Integer.valueOf(list.size()));
        }
        if (z) {
            return;
        }
        C2021x.m1869b("[crash] upload fail.", new Object[0]);
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00aa  */
    /* JADX INFO: renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m1607d(com.tencent.bugly.crashreport.crash.CrashDetailBean r13) {
        /*
            Method dump skipped, instruction units count: 544
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.C1971b.m1607d(com.tencent.bugly.crashreport.crash.CrashDetailBean):void");
    }

    /* JADX INFO: renamed from: f */
    private static ContentValues m1600f(CrashDetailBean crashDetailBean) {
        if (crashDetailBean == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (crashDetailBean.f2488a > 0) {
                contentValues.put("_id", Long.valueOf(crashDetailBean.f2488a));
            }
            contentValues.put("_tm", Long.valueOf(crashDetailBean.f2505r));
            contentValues.put("_s1", crashDetailBean.f2508u);
            int i = 1;
            contentValues.put("_up", Integer.valueOf(crashDetailBean.f2491d ? 1 : 0));
            if (!crashDetailBean.f2497j) {
                i = 0;
            }
            contentValues.put("_me", Integer.valueOf(i));
            contentValues.put("_uc", Integer.valueOf(crashDetailBean.f2499l));
            contentValues.put("_dt", C2023z.m1915a(crashDetailBean));
            return contentValues;
        } catch (Throwable th) {
            if (!C2021x.m1867a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* JADX INFO: renamed from: a */
    private static CrashDetailBean m1588a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex("_dt"));
            if (blob == null) {
                return null;
            }
            long j = cursor.getLong(cursor.getColumnIndex("_id"));
            CrashDetailBean crashDetailBean = (CrashDetailBean) C2023z.m1896a(blob, CrashDetailBean.CREATOR);
            if (crashDetailBean != null) {
                crashDetailBean.f2488a = j;
            }
            return crashDetailBean;
        } catch (Throwable th) {
            if (!C2021x.m1867a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* JADX INFO: renamed from: e */
    public final void m1608e(CrashDetailBean crashDetailBean) {
        ContentValues contentValuesM1600f;
        if (crashDetailBean == null || (contentValuesM1600f = m1600f(crashDetailBean)) == null) {
            return;
        }
        long jM1822a = C2013p.m1807a().m1822a("t_cr", contentValuesM1600f, (InterfaceC2012o) null, true);
        if (jM1822a >= 0) {
            C2021x.m1871c("insert %s success!", "t_cr");
            crashDetailBean.f2488a = jM1822a;
        }
    }

    /* JADX INFO: renamed from: b */
    private List<CrashDetailBean> m1597b(List<C1964a> list) {
        Cursor cursorM1823a;
        if (list == null || list.size() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("_id in ");
        sb.append("(");
        Iterator<C1964a> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next().f2514a);
            sb.append(",");
        }
        if (sb.toString().contains(",")) {
            sb = new StringBuilder(sb.substring(0, sb.lastIndexOf(",")));
        }
        sb.append(")");
        String string = sb.toString();
        sb.setLength(0);
        try {
            cursorM1823a = C2013p.m1807a().m1823a("t_cr", null, string, null, null, true);
            if (cursorM1823a == null) {
                return null;
            }
            try {
                ArrayList arrayList = new ArrayList();
                sb.append("_id in ");
                sb.append("(");
                int i = 0;
                while (cursorM1823a.moveToNext()) {
                    CrashDetailBean crashDetailBeanM1588a = m1588a(cursorM1823a);
                    if (crashDetailBeanM1588a != null) {
                        arrayList.add(crashDetailBeanM1588a);
                    } else {
                        try {
                            sb.append(cursorM1823a.getLong(cursorM1823a.getColumnIndex("_id")));
                            sb.append(",");
                            i++;
                        } catch (Throwable unused) {
                            C2021x.m1872d("unknown id!", new Object[0]);
                        }
                    }
                }
                if (sb.toString().contains(",")) {
                    sb = new StringBuilder(sb.substring(0, sb.lastIndexOf(",")));
                }
                sb.append(")");
                String string2 = sb.toString();
                if (i > 0) {
                    C2021x.m1872d("deleted %s illegal data %d", "t_cr", Integer.valueOf(C2013p.m1807a().m1821a("t_cr", string2, (String[]) null, (InterfaceC2012o) null, true)));
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

    /* JADX INFO: renamed from: b */
    private static C1964a m1595b(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            C1964a c1964a = new C1964a();
            c1964a.f2514a = cursor.getLong(cursor.getColumnIndex("_id"));
            c1964a.f2515b = cursor.getLong(cursor.getColumnIndex("_tm"));
            c1964a.f2516c = cursor.getString(cursor.getColumnIndex("_s1"));
            c1964a.f2517d = cursor.getInt(cursor.getColumnIndex("_up")) == 1;
            c1964a.f2518e = cursor.getInt(cursor.getColumnIndex("_me")) == 1;
            c1964a.f2519f = cursor.getInt(cursor.getColumnIndex("_uc"));
            return c1964a;
        } catch (Throwable th) {
            if (!C2021x.m1867a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* JADX INFO: renamed from: b */
    private List<C1964a> m1596b() {
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            Cursor cursorM1823a = C2013p.m1807a().m1823a("t_cr", new String[]{"_id", "_tm", "_s1", "_up", "_me", "_uc"}, null, null, null, true);
            if (cursorM1823a == null) {
                if (cursorM1823a != null) {
                    cursorM1823a.close();
                }
                return null;
            }
            try {
                if (cursorM1823a.getCount() <= 0) {
                    if (cursorM1823a != null) {
                        cursorM1823a.close();
                    }
                    return arrayList;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("_id in ");
                sb.append("(");
                int i = 0;
                while (cursorM1823a.moveToNext()) {
                    C1964a c1964aM1595b = m1595b(cursorM1823a);
                    if (c1964aM1595b != null) {
                        arrayList.add(c1964aM1595b);
                    } else {
                        try {
                            sb.append(cursorM1823a.getLong(cursorM1823a.getColumnIndex("_id")));
                            sb.append(",");
                            i++;
                        } catch (Throwable unused) {
                            C2021x.m1872d("unknown id!", new Object[0]);
                        }
                    }
                }
                if (sb.toString().contains(",")) {
                    sb = new StringBuilder(sb.substring(0, sb.lastIndexOf(",")));
                }
                sb.append(")");
                String string = sb.toString();
                sb.setLength(0);
                if (i > 0) {
                    C2021x.m1872d("deleted %s illegal data %d", "t_cr", Integer.valueOf(C2013p.m1807a().m1821a("t_cr", string, (String[]) null, (InterfaceC2012o) null, true)));
                }
                if (cursorM1823a != null) {
                    cursorM1823a.close();
                }
                return arrayList;
            } catch (Throwable th) {
                th = th;
                cursor = cursorM1823a;
                try {
                    if (!C2021x.m1867a(th)) {
                        th.printStackTrace();
                    }
                    return arrayList;
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX INFO: renamed from: c */
    private static void m1598c(List<C1964a> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("_id in ");
        sb.append("(");
        Iterator<C1964a> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next().f2514a);
            sb.append(",");
        }
        StringBuilder sb2 = new StringBuilder(sb.substring(0, sb.lastIndexOf(",")));
        sb2.append(")");
        String string = sb2.toString();
        sb2.setLength(0);
        try {
            C2021x.m1871c("deleted %s data %d", "t_cr", Integer.valueOf(C2013p.m1807a().m1821a("t_cr", string, (String[]) null, (InterfaceC2012o) null, true)));
        } catch (Throwable th) {
            if (C2021x.m1867a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    /* JADX INFO: renamed from: d */
    private static void m1599d(List<CrashDetailBean> list) {
        if (list != null) {
            try {
                if (list.size() == 0) {
                    return;
                }
                StringBuilder sb = new StringBuilder();
                for (CrashDetailBean crashDetailBean : list) {
                    sb.append(" or _id");
                    sb.append(" = ");
                    sb.append(crashDetailBean.f2488a);
                }
                String string = sb.toString();
                if (string.length() > 0) {
                    string = string.substring(4);
                }
                sb.setLength(0);
                C2021x.m1871c("deleted %s data %d", "t_cr", Integer.valueOf(C2013p.m1807a().m1821a("t_cr", string, (String[]) null, (InterfaceC2012o) null, true)));
            } catch (Throwable th) {
                if (C2021x.m1867a(th)) {
                    return;
                }
                th.printStackTrace();
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private static C1991ak m1591a(Context context, CrashDetailBean crashDetailBean, C1958a c1958a) {
        C1990aj c1990ajM1590a;
        C1990aj c1990ajM1590a2;
        C1990aj c1990aj;
        if (context == null || crashDetailBean == null || c1958a == null) {
            C2021x.m1872d("enExp args == null", new Object[0]);
            return null;
        }
        C1991ak c1991ak = new C1991ak();
        switch (crashDetailBean.f2489b) {
            case 0:
                c1991ak.f2697a = crashDetailBean.f2497j ? "200" : "100";
                break;
            case 1:
                c1991ak.f2697a = crashDetailBean.f2497j ? "201" : "101";
                break;
            case 2:
                c1991ak.f2697a = crashDetailBean.f2497j ? "202" : "102";
                break;
            case 3:
                c1991ak.f2697a = crashDetailBean.f2497j ? "203" : "103";
                break;
            case 4:
                c1991ak.f2697a = crashDetailBean.f2497j ? "204" : "104";
                break;
            case 5:
                c1991ak.f2697a = crashDetailBean.f2497j ? "207" : "107";
                break;
            case 6:
                c1991ak.f2697a = crashDetailBean.f2497j ? "206" : "106";
                break;
            case 7:
                c1991ak.f2697a = crashDetailBean.f2497j ? "208" : "108";
                break;
            default:
                C2021x.m1873e("crash type error! %d", Integer.valueOf(crashDetailBean.f2489b));
                break;
        }
        c1991ak.f2698b = crashDetailBean.f2505r;
        c1991ak.f2699c = crashDetailBean.f2501n;
        c1991ak.f2700d = crashDetailBean.f2502o;
        c1991ak.f2701e = crashDetailBean.f2503p;
        c1991ak.f2703g = crashDetailBean.f2504q;
        c1991ak.f2704h = crashDetailBean.f2513z;
        c1991ak.f2705i = crashDetailBean.f2490c;
        c1991ak.f2706j = null;
        c1991ak.f2708l = crashDetailBean.f2500m;
        c1991ak.f2709m = crashDetailBean.f2492e;
        c1991ak.f2702f = crashDetailBean.f2465B;
        c1991ak.f2710n = null;
        C2021x.m1871c("libInfo %s", c1991ak.f2711o);
        if (crashDetailBean.f2495h != null && crashDetailBean.f2495h.size() > 0) {
            c1991ak.f2712p = new ArrayList<>();
            for (Map.Entry<String, PlugInBean> entry : crashDetailBean.f2495h.entrySet()) {
                C1988ah c1988ah = new C1988ah();
                c1988ah.f2677a = entry.getValue().f2353a;
                c1988ah.f2678b = entry.getValue().f2355c;
                c1988ah.f2679c = entry.getValue().f2354b;
                c1991ak.f2712p.add(c1988ah);
            }
        }
        if (crashDetailBean.f2497j) {
            c1991ak.f2707k = crashDetailBean.f2507t;
            if (crashDetailBean.f2506s != null && crashDetailBean.f2506s.length() > 0) {
                if (c1991ak.f2713q == null) {
                    c1991ak.f2713q = new ArrayList<>();
                }
                try {
                    c1991ak.f2713q.add(new C1990aj((byte) 1, "alltimes.txt", crashDetailBean.f2506s.getBytes("utf-8")));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    c1991ak.f2713q = null;
                }
            }
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(c1991ak.f2707k);
            objArr[1] = Integer.valueOf(c1991ak.f2713q != null ? c1991ak.f2713q.size() : 0);
            C2021x.m1871c("crashcount:%d sz:%d", objArr);
        }
        if (crashDetailBean.f2510w != null) {
            if (c1991ak.f2713q == null) {
                c1991ak.f2713q = new ArrayList<>();
            }
            try {
                c1991ak.f2713q.add(new C1990aj((byte) 1, "log.txt", crashDetailBean.f2510w.getBytes("utf-8")));
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                c1991ak.f2713q = null;
            }
        }
        if (crashDetailBean.f2511x != null) {
            if (c1991ak.f2713q == null) {
                c1991ak.f2713q = new ArrayList<>();
            }
            try {
                c1991ak.f2713q.add(new C1990aj((byte) 1, "jniLog.txt", crashDetailBean.f2511x.getBytes("utf-8")));
            } catch (UnsupportedEncodingException e3) {
                e3.printStackTrace();
                c1991ak.f2713q = null;
            }
        }
        if (!C2023z.m1914a(crashDetailBean.f2485V)) {
            if (c1991ak.f2713q == null) {
                c1991ak.f2713q = new ArrayList<>();
            }
            try {
                c1990aj = new C1990aj((byte) 1, "crashInfos.txt", crashDetailBean.f2485V.getBytes("utf-8"));
            } catch (UnsupportedEncodingException e4) {
                e4.printStackTrace();
                c1990aj = null;
            }
            if (c1990aj != null) {
                C2021x.m1871c("attach crash infos", new Object[0]);
                c1991ak.f2713q.add(c1990aj);
            }
        }
        if (crashDetailBean.f2486W != null) {
            if (c1991ak.f2713q == null) {
                c1991ak.f2713q = new ArrayList<>();
            }
            C1990aj c1990ajM1590a3 = m1590a("backupRecord.zip", context, crashDetailBean.f2486W);
            if (c1990ajM1590a3 != null) {
                C2021x.m1871c("attach backup record", new Object[0]);
                c1991ak.f2713q.add(c1990ajM1590a3);
            }
        }
        if (crashDetailBean.f2512y != null && crashDetailBean.f2512y.length > 0) {
            C1990aj c1990aj2 = new C1990aj((byte) 2, "buglylog.zip", crashDetailBean.f2512y);
            C2021x.m1871c("attach user log", new Object[0]);
            if (c1991ak.f2713q == null) {
                c1991ak.f2713q = new ArrayList<>();
            }
            c1991ak.f2713q.add(c1990aj2);
        }
        if (crashDetailBean.f2489b == 3) {
            if (c1991ak.f2713q == null) {
                c1991ak.f2713q = new ArrayList<>();
            }
            C2021x.m1871c("crashBean.anrMessages:%s", crashDetailBean.f2479P);
            if (crashDetailBean.f2479P != null && crashDetailBean.f2479P.containsKey("BUGLY_CR_01")) {
                try {
                    if (!TextUtils.isEmpty(crashDetailBean.f2479P.get("BUGLY_CR_01"))) {
                        c1991ak.f2713q.add(new C1990aj((byte) 1, "anrMessage.txt", crashDetailBean.f2479P.get("BUGLY_CR_01").getBytes("utf-8")));
                        C2021x.m1871c("attach anr message", new Object[0]);
                    }
                } catch (UnsupportedEncodingException e5) {
                    e5.printStackTrace();
                    c1991ak.f2713q = null;
                }
                crashDetailBean.f2479P.remove("BUGLY_CR_01");
            }
            if (crashDetailBean.f2509v != null && NativeCrashHandler.getInstance().isEnableCatchAnrTrace() && (c1990ajM1590a2 = m1590a("trace.zip", context, crashDetailBean.f2509v)) != null) {
                C2021x.m1871c("attach traces", new Object[0]);
                c1991ak.f2713q.add(c1990ajM1590a2);
            }
        }
        if (crashDetailBean.f2489b == 1) {
            if (c1991ak.f2713q == null) {
                c1991ak.f2713q = new ArrayList<>();
            }
            if (crashDetailBean.f2509v != null && (c1990ajM1590a = m1590a("tomb.zip", context, crashDetailBean.f2509v)) != null) {
                C2021x.m1871c("attach tombs", new Object[0]);
                c1991ak.f2713q.add(c1990ajM1590a);
            }
        }
        if (c1958a.f2360D != null && !c1958a.f2360D.isEmpty()) {
            if (c1991ak.f2713q == null) {
                c1991ak.f2713q = new ArrayList<>();
            }
            StringBuilder sb = new StringBuilder();
            Iterator<String> it = c1958a.f2360D.iterator();
            while (it.hasNext()) {
                sb.append(it.next());
            }
            try {
                c1991ak.f2713q.add(new C1990aj((byte) 1, "martianlog.txt", sb.toString().getBytes("utf-8")));
                C2021x.m1871c("attach pageTracingList", new Object[0]);
            } catch (UnsupportedEncodingException e6) {
                e6.printStackTrace();
            }
        }
        if (crashDetailBean.f2484U != null && crashDetailBean.f2484U.length > 0) {
            if (c1991ak.f2713q == null) {
                c1991ak.f2713q = new ArrayList<>();
            }
            c1991ak.f2713q.add(new C1990aj((byte) 1, "userExtraByteData", crashDetailBean.f2484U));
            C2021x.m1871c("attach extraData", new Object[0]);
        }
        c1991ak.f2714r = new HashMap();
        Map<String, String> map = c1991ak.f2714r;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(crashDetailBean.f2466C);
        map.put("A9", sb2.toString());
        Map<String, String> map2 = c1991ak.f2714r;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(crashDetailBean.f2467D);
        map2.put("A11", sb3.toString());
        Map<String, String> map3 = c1991ak.f2714r;
        StringBuilder sb4 = new StringBuilder();
        sb4.append(crashDetailBean.f2468E);
        map3.put("A10", sb4.toString());
        c1991ak.f2714r.put("A23", crashDetailBean.f2493f);
        c1991ak.f2714r.put("A7", c1958a.f2403g);
        c1991ak.f2714r.put("A6", c1958a.m1503n());
        c1991ak.f2714r.put("A5", c1958a.m1502m());
        c1991ak.f2714r.put("A22", c1958a.m1497h());
        Map<String, String> map4 = c1991ak.f2714r;
        StringBuilder sb5 = new StringBuilder();
        sb5.append(crashDetailBean.f2470G);
        map4.put("A2", sb5.toString());
        Map<String, String> map5 = c1991ak.f2714r;
        StringBuilder sb6 = new StringBuilder();
        sb6.append(crashDetailBean.f2469F);
        map5.put("A1", sb6.toString());
        c1991ak.f2714r.put("A24", c1958a.f2405i);
        Map<String, String> map6 = c1991ak.f2714r;
        StringBuilder sb7 = new StringBuilder();
        sb7.append(crashDetailBean.f2471H);
        map6.put("A17", sb7.toString());
        c1991ak.f2714r.put("A25", c1958a.m1497h());
        c1991ak.f2714r.put("A15", c1958a.m1506q());
        Map<String, String> map7 = c1991ak.f2714r;
        StringBuilder sb8 = new StringBuilder();
        sb8.append(c1958a.m1507r());
        map7.put("A13", sb8.toString());
        c1991ak.f2714r.put("A34", crashDetailBean.f2464A);
        if (c1958a.f2421y != null) {
            c1991ak.f2714r.put("productIdentify", c1958a.f2421y);
        }
        try {
            c1991ak.f2714r.put("A26", URLEncoder.encode(crashDetailBean.f2472I, "utf-8"));
        } catch (UnsupportedEncodingException e7) {
            e7.printStackTrace();
        }
        if (crashDetailBean.f2489b == 1) {
            c1991ak.f2714r.put("A27", crashDetailBean.f2474K);
            c1991ak.f2714r.put("A28", crashDetailBean.f2473J);
            Map<String, String> map8 = c1991ak.f2714r;
            StringBuilder sb9 = new StringBuilder();
            sb9.append(crashDetailBean.f2498k);
            map8.put("A29", sb9.toString());
        }
        c1991ak.f2714r.put("A30", crashDetailBean.f2475L);
        Map<String, String> map9 = c1991ak.f2714r;
        StringBuilder sb10 = new StringBuilder();
        sb10.append(crashDetailBean.f2476M);
        map9.put("A18", sb10.toString());
        Map<String, String> map10 = c1991ak.f2714r;
        StringBuilder sb11 = new StringBuilder();
        sb11.append(!crashDetailBean.f2477N);
        map10.put("A36", sb11.toString());
        Map<String, String> map11 = c1991ak.f2714r;
        StringBuilder sb12 = new StringBuilder();
        sb12.append(c1958a.f2414r);
        map11.put("F02", sb12.toString());
        Map<String, String> map12 = c1991ak.f2714r;
        StringBuilder sb13 = new StringBuilder();
        sb13.append(c1958a.f2415s);
        map12.put("F03", sb13.toString());
        c1991ak.f2714r.put("F04", c1958a.m1491e());
        Map<String, String> map13 = c1991ak.f2714r;
        StringBuilder sb14 = new StringBuilder();
        sb14.append(c1958a.f2416t);
        map13.put("F05", sb14.toString());
        c1991ak.f2714r.put("F06", c1958a.f2413q);
        c1991ak.f2714r.put("F08", c1958a.f2419w);
        c1991ak.f2714r.put("F09", c1958a.f2420x);
        Map<String, String> map14 = c1991ak.f2714r;
        StringBuilder sb15 = new StringBuilder();
        sb15.append(c1958a.f2417u);
        map14.put("F10", sb15.toString());
        if (crashDetailBean.f2480Q >= 0) {
            Map<String, String> map15 = c1991ak.f2714r;
            StringBuilder sb16 = new StringBuilder();
            sb16.append(crashDetailBean.f2480Q);
            map15.put("C01", sb16.toString());
        }
        if (crashDetailBean.f2481R >= 0) {
            Map<String, String> map16 = c1991ak.f2714r;
            StringBuilder sb17 = new StringBuilder();
            sb17.append(crashDetailBean.f2481R);
            map16.put("C02", sb17.toString());
        }
        if (crashDetailBean.f2482S != null && crashDetailBean.f2482S.size() > 0) {
            for (Map.Entry<String, String> entry2 : crashDetailBean.f2482S.entrySet()) {
                c1991ak.f2714r.put("C03_" + entry2.getKey(), entry2.getValue());
            }
        }
        if (crashDetailBean.f2483T != null && crashDetailBean.f2483T.size() > 0) {
            for (Map.Entry<String, String> entry3 : crashDetailBean.f2483T.entrySet()) {
                c1991ak.f2714r.put("C04_" + entry3.getKey(), entry3.getValue());
            }
        }
        c1991ak.f2715s = null;
        if (crashDetailBean.f2478O != null && crashDetailBean.f2478O.size() > 0) {
            c1991ak.f2715s = crashDetailBean.f2478O;
            C2021x.m1866a("setted message size %d", Integer.valueOf(c1991ak.f2715s.size()));
        }
        Object[] objArr2 = new Object[12];
        objArr2[0] = crashDetailBean.f2501n;
        objArr2[1] = crashDetailBean.f2490c;
        objArr2[2] = c1958a.m1491e();
        objArr2[3] = Long.valueOf((crashDetailBean.f2505r - crashDetailBean.f2476M) / 1000);
        objArr2[4] = Boolean.valueOf(crashDetailBean.f2498k);
        objArr2[5] = Boolean.valueOf(crashDetailBean.f2477N);
        objArr2[6] = Boolean.valueOf(crashDetailBean.f2497j);
        objArr2[7] = Boolean.valueOf(crashDetailBean.f2489b == 1);
        objArr2[8] = Integer.valueOf(crashDetailBean.f2507t);
        objArr2[9] = crashDetailBean.f2506s;
        objArr2[10] = Boolean.valueOf(crashDetailBean.f2491d);
        objArr2[11] = Integer.valueOf(c1991ak.f2714r.size());
        C2021x.m1871c("%s rid:%s sess:%s ls:%ds isR:%b isF:%b isM:%b isN:%b mc:%d ,%s ,isUp:%b ,vm:%d", objArr2);
        return c1991ak;
    }

    /* JADX INFO: renamed from: a */
    private static C1990aj m1590a(String str, Context context, String str2) {
        FileInputStream fileInputStream;
        if (str2 == null || context == null) {
            C2021x.m1872d("rqdp{  createZipAttachment sourcePath == null || context == null ,pls check}", new Object[0]);
            return null;
        }
        C2021x.m1871c("zip %s", str2);
        File file = new File(str2);
        File file2 = new File(context.getCacheDir(), str);
        if (!C2023z.m1912a(file, file2, 5000)) {
            C2021x.m1872d("zip fail!", new Object[0]);
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            fileInputStream = new FileInputStream(file2);
        } catch (Throwable th) {
            th = th;
            fileInputStream = null;
        }
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                int i = fileInputStream.read(bArr);
                if (i <= 0) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, i);
                byteArrayOutputStream.flush();
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            C2021x.m1871c("read bytes :%d", Integer.valueOf(byteArray.length));
            C1990aj c1990aj = new C1990aj((byte) 2, file2.getName(), byteArray);
            try {
                fileInputStream.close();
            } catch (IOException e) {
                if (!C2021x.m1867a(e)) {
                    e.printStackTrace();
                }
            }
            if (file2.exists()) {
                C2021x.m1871c("del tmp", new Object[0]);
                file2.delete();
            }
            return c1990aj;
        } catch (Throwable th2) {
            th = th2;
            try {
                if (!C2021x.m1867a(th)) {
                    th.printStackTrace();
                }
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e2) {
                        if (!C2021x.m1867a(e2)) {
                            e2.printStackTrace();
                        }
                    }
                }
                if (file2.exists()) {
                    C2021x.m1871c("del tmp", new Object[0]);
                    file2.delete();
                }
                return null;
            } catch (Throwable th3) {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                        if (!C2021x.m1867a(e3)) {
                            e3.printStackTrace();
                        }
                    }
                }
                if (file2.exists()) {
                    C2021x.m1871c("del tmp", new Object[0]);
                    file2.delete();
                }
                throw th3;
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m1593a(String str, String str2, String str3, String str4, String str5, CrashDetailBean crashDetailBean) {
        String str6;
        C1958a c1958aM1472b = C1958a.m1472b();
        if (c1958aM1472b == null) {
            return;
        }
        C2021x.m1873e("#++++++++++Record By Bugly++++++++++#", new Object[0]);
        C2021x.m1873e("# You can use Bugly(http:\\\\bugly.qq.com) to get more Crash Detail!", new Object[0]);
        C2021x.m1873e("# PKG NAME: %s", c1958aM1472b.f2399c);
        C2021x.m1873e("# APP VER: %s", c1958aM1472b.f2407k);
        C2021x.m1873e("# SDK VER: %s", c1958aM1472b.f2402f);
        C2021x.m1873e("# LAUNCH TIME: %s", C2023z.m1903a(new Date(C1958a.m1472b().f2382a)));
        C2021x.m1873e("# CRASH TYPE: %s", str);
        C2021x.m1873e("# CRASH TIME: %s", str2);
        C2021x.m1873e("# CRASH PROCESS: %s", str3);
        C2021x.m1873e("# CRASH THREAD: %s", str4);
        if (crashDetailBean != null) {
            C2021x.m1873e("# REPORT ID: %s", crashDetailBean.f2490c);
            Object[] objArr = new Object[2];
            objArr[0] = c1958aM1472b.f2404h;
            objArr[1] = c1958aM1472b.m1507r().booleanValue() ? "ROOTED" : "UNROOT";
            C2021x.m1873e("# CRASH DEVICE: %s %s", objArr);
            C2021x.m1873e("# RUNTIME AVAIL RAM:%d ROM:%d SD:%d", Long.valueOf(crashDetailBean.f2466C), Long.valueOf(crashDetailBean.f2467D), Long.valueOf(crashDetailBean.f2468E));
            C2021x.m1873e("# RUNTIME TOTAL RAM:%d ROM:%d SD:%d", Long.valueOf(crashDetailBean.f2469F), Long.valueOf(crashDetailBean.f2470G), Long.valueOf(crashDetailBean.f2471H));
            if (!C2023z.m1914a(crashDetailBean.f2474K)) {
                C2021x.m1873e("# EXCEPTION FIRED BY %s %s", crashDetailBean.f2474K, crashDetailBean.f2473J);
            } else if (crashDetailBean.f2489b == 3) {
                Object[] objArr2 = new Object[1];
                if (crashDetailBean.f2479P == null) {
                    str6 = IConstant.DEFAULT_PATH;
                } else {
                    str6 = crashDetailBean.f2479P.get("BUGLY_CR_01");
                }
                objArr2[0] = str6;
                C2021x.m1873e("# EXCEPTION ANR MESSAGE:\n %s", objArr2);
            }
        }
        if (!C2023z.m1914a(str5)) {
            C2021x.m1873e("# CRASH STACK: ", new Object[0]);
            C2021x.m1873e(str5, new Object[0]);
        }
        C2021x.m1873e("#++++++++++++++++++++++++++++++++++++++++++#", new Object[0]);
    }
}
