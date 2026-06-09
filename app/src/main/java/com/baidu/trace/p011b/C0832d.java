package com.baidu.trace.p011b;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import com.baidu.trace.C0791a;
import com.baidu.trace.C0814am;
import com.baidu.trace.p012c.C0855f;

/* JADX INFO: renamed from: com.baidu.trace.b.d */
/* JADX INFO: loaded from: classes.dex */
public final class C0832d {

    /* JADX INFO: renamed from: k */
    private static int f1638k = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: l */
    private static boolean f1639l = false;

    /* JADX INFO: renamed from: m */
    private static long f1640m;

    /* JADX INFO: renamed from: a */
    private boolean f1641a = false;

    /* JADX INFO: renamed from: b */
    private boolean f1642b = false;

    /* JADX INFO: renamed from: c */
    private boolean f1643c = false;

    /* JADX INFO: renamed from: d */
    private Handler f1644d = null;

    /* JADX INFO: renamed from: e */
    private Context f1645e = null;

    /* JADX INFO: renamed from: f */
    private C0834f f1646f = null;

    /* JADX INFO: renamed from: g */
    private C0836h f1647g = null;

    /* JADX INFO: renamed from: h */
    private C0835g f1648h = null;

    /* JADX INFO: renamed from: i */
    private C0814am f1649i = null;

    /* JADX INFO: renamed from: j */
    private C0837i f1650j = null;

    /* JADX INFO: renamed from: com.baidu.trace.b.d$a */
    static class a {

        /* JADX INFO: renamed from: a */
        private static C0832d f1651a = new C0832d();
    }

    /* JADX INFO: renamed from: a */
    public static C0832d m1134a() {
        return a.f1651a;
    }

    /* JADX INFO: renamed from: a */
    public static void m1135a(long j) {
        f1640m = j;
    }

    /* JADX INFO: renamed from: a */
    public static void m1136a(boolean z) {
        f1639l = z;
    }

    /* JADX INFO: renamed from: a */
    protected static boolean m1137a(int i) {
        return i != f1638k;
    }

    /* JADX INFO: renamed from: a */
    public static boolean m1138a(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                C0791a.m1005a("BaiduTraceSDK", "current network is not available");
                return false;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected() && activeNetworkInfo.isAvailable();
        } catch (Exception unused) {
            C0791a.m1005a("BaiduTraceSDK", "current network is not available");
            return false;
        }
    }

    /* JADX INFO: renamed from: b */
    public static void m1139b(int i) {
        f1638k = i;
    }

    /* JADX INFO: renamed from: h */
    public static boolean m1140h() {
        return f1639l;
    }

    /* JADX INFO: renamed from: i */
    public static long m1141i() {
        return f1640m;
    }

    /* JADX INFO: renamed from: a */
    public final void m1142a(Handler handler, Context context) {
        this.f1644d = handler;
        this.f1645e = context;
    }

    /* JADX INFO: renamed from: a */
    public final void m1143a(String str) {
        if (this.f1643c) {
            return;
        }
        this.f1643c = true;
        if (this.f1649i != null) {
            this.f1649i = null;
        }
        C0814am c0814am = new C0814am(0, str);
        this.f1649i = c0814am;
        c0814am.start();
    }

    /* JADX INFO: renamed from: a */
    public final void m1144a(byte[] bArr, C0855f.a aVar) {
        if (this.f1650j != null) {
            this.f1650j = null;
        }
        C0837i c0837i = new C0837i(this.f1645e, this.f1644d, bArr, aVar);
        this.f1650j = c0837i;
        c0837i.start();
    }

    /* JADX INFO: renamed from: b */
    public final void m1145b() {
        this.f1650j = null;
        if (this.f1641a) {
            this.f1641a = false;
            C0834f c0834f = this.f1646f;
            if (c0834f != null) {
                c0834f.m1157a();
                this.f1646f = null;
            }
        }
        if (this.f1642b) {
            this.f1642b = false;
            C0835g c0835g = this.f1648h;
            if (c0835g != null) {
                c0835g.m1159a();
            }
        }
        C0829a.m1127e();
    }

    /* JADX INFO: renamed from: c */
    public final void m1146c() {
        if (this.f1641a) {
            return;
        }
        this.f1641a = true;
        if (this.f1646f != null) {
            this.f1646f = null;
        }
        C0834f c0834f = new C0834f(this.f1645e, this.f1644d);
        this.f1646f = c0834f;
        c0834f.start();
    }

    /* JADX INFO: renamed from: d */
    protected final void m1147d() {
        if (this.f1647g != null) {
            this.f1647g = null;
        }
        C0836h c0836h = new C0836h(this.f1644d);
        this.f1647g = c0836h;
        c0836h.start();
    }

    /* JADX INFO: renamed from: e */
    public final void m1148e() {
        C0836h c0836h = this.f1647g;
        if (c0836h != null) {
            c0836h.m1160a();
            this.f1647g = null;
        }
    }

    /* JADX INFO: renamed from: f */
    public final void m1149f() {
        this.f1643c = false;
    }

    /* JADX INFO: renamed from: g */
    public final boolean m1150g() {
        if (this.f1642b) {
            return true;
        }
        if (C0829a.m1124b() == null) {
            Handler handler = this.f1644d;
            if (handler == null) {
                return false;
            }
            handler.obtainMessage(4).sendToTarget();
            return false;
        }
        this.f1642b = true;
        C0835g c0835g = new C0835g(this.f1645e, this.f1644d);
        this.f1648h = c0835g;
        c0835g.start();
        return true;
    }
}
