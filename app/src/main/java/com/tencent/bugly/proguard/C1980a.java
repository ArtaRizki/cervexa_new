package com.tencent.bugly.proguard;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.bugly.AbstractC1949a;
import com.tencent.bugly.C1950b;
import com.tencent.bugly.crashreport.biz.UserInfoBean;
import com.tencent.bugly.crashreport.common.info.C1958a;
import com.tencent.bugly.crashreport.common.info.C1959b;
import com.tencent.bugly.crashreport.common.strategy.C1961a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: renamed from: com.tencent.bugly.proguard.a */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public class C1980a {

    /* JADX INFO: renamed from: e */
    private static Proxy f2661e;

    /* JADX INFO: renamed from: a */
    protected HashMap<String, HashMap<String, byte[]>> f2662a = new HashMap<>();

    /* JADX INFO: renamed from: b */
    protected String f2663b;

    /* JADX INFO: renamed from: c */
    C2006i f2664c;

    /* JADX INFO: renamed from: d */
    private HashMap<String, Object> f2665d;

    C1980a() {
        new HashMap();
        this.f2665d = new HashMap<>();
        this.f2663b = "GBK";
        this.f2664c = new C2006i();
    }

    /* JADX INFO: renamed from: a */
    public static void m1687a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            f2661e = null;
        } else {
            f2661e = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(str, i));
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m1688a(InetAddress inetAddress, int i) {
        if (inetAddress == null) {
            f2661e = null;
        } else {
            f2661e = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(inetAddress, i));
        }
    }

    /* JADX INFO: renamed from: a */
    public void mo1694a(String str) {
        this.f2663b = str;
    }

    /* JADX INFO: renamed from: b */
    public static Proxy m1693b() {
        return f2661e;
    }

    /* JADX INFO: renamed from: a */
    public static C1997aq m1683a(UserInfoBean userInfoBean) {
        if (userInfoBean == null) {
            return null;
        }
        C1997aq c1997aq = new C1997aq();
        c1997aq.f2774a = userInfoBean.f2309e;
        c1997aq.f2778e = userInfoBean.f2314j;
        c1997aq.f2777d = userInfoBean.f2307c;
        c1997aq.f2776c = userInfoBean.f2308d;
        c1997aq.f2780g = userInfoBean.f2319o == 1;
        int i = userInfoBean.f2306b;
        if (i == 1) {
            c1997aq.f2775b = (byte) 1;
        } else if (i == 2) {
            c1997aq.f2775b = (byte) 4;
        } else if (i == 3) {
            c1997aq.f2775b = (byte) 2;
        } else if (i == 4) {
            c1997aq.f2775b = (byte) 3;
        } else {
            if (userInfoBean.f2306b < 10 || userInfoBean.f2306b >= 20) {
                C2021x.m1873e("unknown uinfo type %d ", Integer.valueOf(userInfoBean.f2306b));
                return null;
            }
            c1997aq.f2775b = (byte) userInfoBean.f2306b;
        }
        c1997aq.f2779f = new HashMap();
        if (userInfoBean.f2320p >= 0) {
            Map<String, String> map = c1997aq.f2779f;
            StringBuilder sb = new StringBuilder();
            sb.append(userInfoBean.f2320p);
            map.put("C01", sb.toString());
        }
        if (userInfoBean.f2321q >= 0) {
            Map<String, String> map2 = c1997aq.f2779f;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(userInfoBean.f2321q);
            map2.put("C02", sb2.toString());
        }
        if (userInfoBean.f2322r != null && userInfoBean.f2322r.size() > 0) {
            for (Map.Entry<String, String> entry : userInfoBean.f2322r.entrySet()) {
                c1997aq.f2779f.put("C03_" + entry.getKey(), entry.getValue());
            }
        }
        if (userInfoBean.f2323s != null && userInfoBean.f2323s.size() > 0) {
            for (Map.Entry<String, String> entry2 : userInfoBean.f2323s.entrySet()) {
                c1997aq.f2779f.put("C04_" + entry2.getKey(), entry2.getValue());
            }
        }
        Map<String, String> map3 = c1997aq.f2779f;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(!userInfoBean.f2316l);
        map3.put("A36", sb3.toString());
        Map<String, String> map4 = c1997aq.f2779f;
        StringBuilder sb4 = new StringBuilder();
        sb4.append(userInfoBean.f2311g);
        map4.put("F02", sb4.toString());
        Map<String, String> map5 = c1997aq.f2779f;
        StringBuilder sb5 = new StringBuilder();
        sb5.append(userInfoBean.f2312h);
        map5.put("F03", sb5.toString());
        c1997aq.f2779f.put("F04", userInfoBean.f2314j);
        Map<String, String> map6 = c1997aq.f2779f;
        StringBuilder sb6 = new StringBuilder();
        sb6.append(userInfoBean.f2313i);
        map6.put("F05", sb6.toString());
        c1997aq.f2779f.put("F06", userInfoBean.f2317m);
        Map<String, String> map7 = c1997aq.f2779f;
        StringBuilder sb7 = new StringBuilder();
        sb7.append(userInfoBean.f2315k);
        map7.put("F10", sb7.toString());
        C2021x.m1871c("summary type %d vm:%d", Byte.valueOf(c1997aq.f2775b), Integer.valueOf(c1997aq.f2779f.size()));
        return c1997aq;
    }

    /* JADX INFO: renamed from: a */
    public static String m1686a(ArrayList<String> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        while (true) {
            String str = "map";
            if (i < arrayList.size()) {
                String str2 = arrayList.get(i);
                if (str2.equals("java.lang.Integer") || str2.equals("int")) {
                    str = "int32";
                } else if (str2.equals("java.lang.Boolean") || str2.equals("boolean")) {
                    str = "bool";
                } else if (str2.equals("java.lang.Byte") || str2.equals("byte")) {
                    str = "char";
                } else if (str2.equals("java.lang.Double") || str2.equals("double")) {
                    str = "double";
                } else if (str2.equals("java.lang.Float") || str2.equals("float")) {
                    str = "float";
                } else if (str2.equals("java.lang.Long") || str2.equals("long")) {
                    str = "int64";
                } else if (str2.equals("java.lang.Short") || str2.equals("short")) {
                    str = "short";
                } else {
                    if (str2.equals("java.lang.Character")) {
                        throw new IllegalArgumentException("can not support java.lang.Character");
                    }
                    if (str2.equals("java.lang.String")) {
                        str = "string";
                    } else if (str2.equals("java.util.List")) {
                        str = "list";
                    } else if (!str2.equals("java.util.Map")) {
                        str = str2;
                    }
                }
                arrayList.set(i, str);
                i++;
            } else {
                Collections.reverse(arrayList);
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    String str3 = arrayList.get(i2);
                    if (str3.equals("list")) {
                        int i3 = i2 - 1;
                        arrayList.set(i3, "<" + arrayList.get(i3));
                        arrayList.set(0, arrayList.get(0) + ">");
                    } else if (str3.equals("map")) {
                        int i4 = i2 - 1;
                        arrayList.set(i4, "<" + arrayList.get(i4) + ",");
                        arrayList.set(0, arrayList.get(0) + ">");
                    } else if (str3.equals("Array")) {
                        int i5 = i2 - 1;
                        arrayList.set(i5, "<" + arrayList.get(i5));
                        arrayList.set(0, arrayList.get(0) + ">");
                    }
                }
                Collections.reverse(arrayList);
                Iterator<String> it = arrayList.iterator();
                while (it.hasNext()) {
                    stringBuffer.append(it.next());
                }
                return stringBuffer.toString();
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public <T> void mo1695a(String str, T t) {
        if (str == null) {
            throw new IllegalArgumentException("put key can not is null");
        }
        if (t == null) {
            throw new IllegalArgumentException("put value can not is null");
        }
        if (t instanceof Set) {
            throw new IllegalArgumentException("can not support Set");
        }
        C2007j c2007j = new C2007j();
        c2007j.m1766a(this.f2663b);
        c2007j.m1772a(t, 0);
        byte[] bArrM1784a = C2009l.m1784a(c2007j.m1767a());
        HashMap<String, byte[]> map = new HashMap<>(1);
        ArrayList<String> arrayList = new ArrayList<>(1);
        m1689a(arrayList, t);
        map.put(m1686a(arrayList), bArrM1784a);
        this.f2665d.remove(str);
        this.f2662a.put(str, map);
    }

    /* JADX INFO: renamed from: a */
    public static C1998ar m1684a(List<UserInfoBean> list, int i) {
        C1958a c1958aM1472b;
        if (list == null || list.size() == 0 || (c1958aM1472b = C1958a.m1472b()) == null) {
            return null;
        }
        c1958aM1472b.m1504o();
        C1998ar c1998ar = new C1998ar();
        c1998ar.f2785b = c1958aM1472b.f2400d;
        c1998ar.f2786c = c1958aM1472b.m1497h();
        ArrayList<C1997aq> arrayList = new ArrayList<>();
        Iterator<UserInfoBean> it = list.iterator();
        while (it.hasNext()) {
            C1997aq c1997aqM1683a = m1683a(it.next());
            if (c1997aqM1683a != null) {
                arrayList.add(c1997aqM1683a);
            }
        }
        c1998ar.f2787d = arrayList;
        c1998ar.f2788e = new HashMap();
        c1998ar.f2788e.put("A7", c1958aM1472b.f2403g);
        c1998ar.f2788e.put("A6", c1958aM1472b.m1503n());
        c1998ar.f2788e.put("A5", c1958aM1472b.m1502m());
        Map<String, String> map = c1998ar.f2788e;
        StringBuilder sb = new StringBuilder();
        sb.append(c1958aM1472b.m1500k());
        map.put("A2", sb.toString());
        Map<String, String> map2 = c1998ar.f2788e;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(c1958aM1472b.m1500k());
        map2.put("A1", sb2.toString());
        c1998ar.f2788e.put("A24", c1958aM1472b.f2405i);
        Map<String, String> map3 = c1998ar.f2788e;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(c1958aM1472b.m1501l());
        map3.put("A17", sb3.toString());
        c1998ar.f2788e.put("A15", c1958aM1472b.m1506q());
        Map<String, String> map4 = c1998ar.f2788e;
        StringBuilder sb4 = new StringBuilder();
        sb4.append(c1958aM1472b.m1507r());
        map4.put("A13", sb4.toString());
        c1998ar.f2788e.put("F08", c1958aM1472b.f2419w);
        c1998ar.f2788e.put("F09", c1958aM1472b.f2420x);
        Map<String, String> mapM1514y = c1958aM1472b.m1514y();
        if (mapM1514y != null && mapM1514y.size() > 0) {
            for (Map.Entry<String, String> entry : mapM1514y.entrySet()) {
                c1998ar.f2788e.put("C04_" + entry.getKey(), entry.getValue());
            }
        }
        if (i == 1) {
            c1998ar.f2784a = (byte) 1;
        } else {
            if (i != 2) {
                C2021x.m1873e("unknown up type %d ", Integer.valueOf(i));
                return null;
            }
            c1998ar.f2784a = (byte) 2;
        }
        return c1998ar;
    }

    /* JADX INFO: renamed from: a */
    public static <T extends AbstractC2008k> T m1685a(byte[] bArr, Class<T> cls) {
        if (bArr != null && bArr.length > 0) {
            try {
                T tNewInstance = cls.newInstance();
                C2006i c2006i = new C2006i(bArr);
                c2006i.m1754a("utf-8");
                tNewInstance.mo1716a(c2006i);
                return tNewInstance;
            } catch (Throwable th) {
                if (!C2021x.m1870b(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: a */
    public static C1993am m1682a(Context context, int i, byte[] bArr) {
        C1958a c1958aM1472b = C1958a.m1472b();
        StrategyBean strategyBeanM1554c = C1961a.m1544a().m1554c();
        if (c1958aM1472b == null || strategyBeanM1554c == null) {
            C2021x.m1873e("Can not create request pkg for parameters is invalid.", new Object[0]);
            return null;
        }
        try {
            C1993am c1993am = new C1993am();
            synchronized (c1958aM1472b) {
                c1993am.f2722a = 1;
                c1993am.f2723b = c1958aM1472b.m1493f();
                c1993am.f2724c = c1958aM1472b.f2399c;
                c1993am.f2725d = c1958aM1472b.f2407k;
                c1993am.f2726e = c1958aM1472b.f2409m;
                c1993am.f2727f = c1958aM1472b.f2402f;
                c1993am.f2728g = i;
                if (bArr == null) {
                    bArr = "".getBytes();
                }
                c1993am.f2729h = bArr;
                c1993am.f2730i = c1958aM1472b.f2404h;
                c1993am.f2731j = c1958aM1472b.f2405i;
                c1993am.f2732k = new HashMap();
                c1993am.f2733l = c1958aM1472b.m1491e();
                c1993am.f2734m = strategyBeanM1554c.f2439n;
                c1993am.f2736o = c1958aM1472b.m1497h();
                c1993am.f2737p = C1959b.m1520b(context);
                c1993am.f2738q = System.currentTimeMillis();
                c1993am.f2739r = c1958aM1472b.m1498i();
                c1993am.f2740s = c1958aM1472b.m1497h();
                c1993am.f2741t = c1993am.f2737p;
                c1958aM1472b.getClass();
                c1993am.f2735n = "com.tencent.bugly";
                c1993am.f2732k.put("A26", c1958aM1472b.m1508s());
                Map<String, String> map = c1993am.f2732k;
                StringBuilder sb = new StringBuilder();
                sb.append(c1958aM1472b.m1475D());
                map.put("A62", sb.toString());
                Map<String, String> map2 = c1993am.f2732k;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(c1958aM1472b.m1476E());
                map2.put("A63", sb2.toString());
                Map<String, String> map3 = c1993am.f2732k;
                StringBuilder sb3 = new StringBuilder();
                sb3.append(c1958aM1472b.f2358B);
                map3.put("F11", sb3.toString());
                Map<String, String> map4 = c1993am.f2732k;
                StringBuilder sb4 = new StringBuilder();
                sb4.append(c1958aM1472b.f2357A);
                map4.put("F12", sb4.toString());
                c1993am.f2732k.put("D3", c1958aM1472b.f2408l);
                if (C1950b.f2298b != null) {
                    for (AbstractC1949a abstractC1949a : C1950b.f2298b) {
                        if (abstractC1949a.versionKey != null && abstractC1949a.version != null) {
                            c1993am.f2732k.put(abstractC1949a.versionKey, abstractC1949a.version);
                        }
                    }
                }
                c1993am.f2732k.put("G15", C2023z.m1928c("G15", ""));
                c1993am.f2732k.put("D4", C2023z.m1928c("D4", "0"));
            }
            Map<String, String> mapM1513x = c1958aM1472b.m1513x();
            if (mapM1513x != null) {
                for (Map.Entry<String, String> entry : mapM1513x.entrySet()) {
                    c1993am.f2732k.put(entry.getKey(), entry.getValue());
                }
            }
            return c1993am;
        } catch (Throwable th) {
            if (!C2021x.m1870b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* JADX INFO: renamed from: a */
    private void m1689a(ArrayList<String> arrayList, Object obj) {
        if (obj.getClass().isArray()) {
            if (!obj.getClass().getComponentType().toString().equals("byte")) {
                throw new IllegalArgumentException("only byte[] is supported");
            }
            if (Array.getLength(obj) > 0) {
                arrayList.add("java.util.List");
                m1689a(arrayList, Array.get(obj, 0));
                return;
            } else {
                arrayList.add("Array");
                arrayList.add("?");
                return;
            }
        }
        if (obj instanceof Array) {
            throw new IllegalArgumentException("can not support Array, please use List");
        }
        if (obj instanceof List) {
            arrayList.add("java.util.List");
            List list = (List) obj;
            if (list.size() > 0) {
                m1689a(arrayList, list.get(0));
                return;
            } else {
                arrayList.add("?");
                return;
            }
        }
        if (obj instanceof Map) {
            arrayList.add("java.util.Map");
            Map map = (Map) obj;
            if (map.size() > 0) {
                Object next = map.keySet().iterator().next();
                Object obj2 = map.get(next);
                arrayList.add(next.getClass().getName());
                m1689a(arrayList, obj2);
                return;
            }
            arrayList.add("?");
            arrayList.add("?");
            return;
        }
        arrayList.add(obj.getClass().getName());
    }

    /* JADX INFO: renamed from: a */
    public byte[] mo1697a() {
        C2007j c2007j = new C2007j(0);
        c2007j.m1766a(this.f2663b);
        c2007j.m1775a((Map) this.f2662a, 0);
        return C2009l.m1784a(c2007j.m1767a());
    }

    /* JADX INFO: renamed from: a */
    public static byte[] m1691a(Object obj) {
        try {
            C2001d c2001d = new C2001d();
            c2001d.mo1720c();
            c2001d.mo1694a("utf-8");
            c2001d.m1721a(1);
            c2001d.m1722b("RqdServer");
            c2001d.m1723c("sync");
            c2001d.mo1695a("detail", obj);
            return c2001d.mo1697a();
        } catch (Throwable th) {
            if (C2021x.m1870b(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: renamed from: a */
    public void mo1696a(byte[] bArr) {
        this.f2664c.m1760a(bArr);
        this.f2664c.m1754a(this.f2663b);
        HashMap map = new HashMap(1);
        HashMap map2 = new HashMap(1);
        map2.put("", new byte[0]);
        map.put("", map2);
        this.f2662a = this.f2664c.m1758a((Map) map, 0, false);
    }

    /* JADX INFO: renamed from: b */
    public static C1994an m1692b(byte[] bArr) {
        if (bArr != null) {
            try {
                C2001d c2001d = new C2001d();
                c2001d.mo1720c();
                c2001d.mo1694a("utf-8");
                c2001d.mo1696a(bArr);
                Object objB = c2001d.m1719b("detail", new C1994an());
                if (C1994an.class.isInstance(objB)) {
                    return (C1994an) C1994an.class.cast(objB);
                }
                return null;
            } catch (Throwable th) {
                if (!C2021x.m1870b(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: a */
    public static byte[] m1690a(AbstractC2008k abstractC2008k) {
        try {
            C2007j c2007j = new C2007j();
            c2007j.m1766a("utf-8");
            abstractC2008k.mo1717a(c2007j);
            return c2007j.m1779b();
        } catch (Throwable th) {
            if (C2021x.m1870b(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }
}
