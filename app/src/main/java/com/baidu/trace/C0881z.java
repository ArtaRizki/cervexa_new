package com.baidu.trace;

import android.content.Context;
import com.baidu.lbsapi.auth.tracesdk.LBSAuthManager;
import com.baidu.trace.p010a.C0797f;
import com.baidu.trace.p012c.C0854e;
import com.baidu.trace.p012c.C0855f;
import com.baidu.trace.p012c.InterfaceC0856g;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.baidu.trace.z */
/* JADX INFO: loaded from: classes.dex */
public class C0881z implements InterfaceC0856g {

    /* JADX INFO: renamed from: a */
    protected static String f1866a = "";

    /* JADX INFO: renamed from: b */
    protected static long f1867b = 0;

    /* JADX INFO: renamed from: c */
    protected static String f1868c = "";

    /* JADX INFO: renamed from: d */
    protected static String f1869d = "";

    /* JADX INFO: renamed from: e */
    protected static String f1870e = "";

    /* JADX INFO: renamed from: f */
    protected static String f1871f = "";

    /* JADX INFO: renamed from: g */
    protected static String f1872g = "";

    /* JADX INFO: renamed from: h */
    protected static String f1873h = "";

    /* JADX INFO: renamed from: i */
    protected static String f1874i = "";

    /* JADX INFO: renamed from: j */
    protected static String f1875j = "";

    /* JADX INFO: renamed from: k */
    protected static String f1876k = "";

    /* JADX INFO: renamed from: l */
    protected static String f1877l = "";

    /* JADX INFO: renamed from: m */
    protected static String f1878m = "";

    /* JADX INFO: renamed from: n */
    protected static byte f1879n = 0;

    /* JADX INFO: renamed from: o */
    protected static byte f1880o = 0;

    /* JADX INFO: renamed from: p */
    protected static byte f1881p = 15;

    /* JADX INFO: renamed from: q */
    protected static byte f1882q = 30;

    /* JADX INFO: renamed from: r */
    protected static byte f1883r = 0;

    /* JADX INFO: renamed from: s */
    protected static byte f1884s = 1;

    /* JADX INFO: renamed from: t */
    protected static byte f1885t = 0;

    /* JADX INFO: renamed from: u */
    protected static byte f1886u = 0;

    /* JADX INFO: renamed from: v */
    protected static byte f1887v = 0;

    /* JADX INFO: renamed from: w */
    protected static byte f1888w = 5;

    /* JADX INFO: renamed from: a */
    protected static void m1287a(Context context) {
        LBSAuthManager lBSAuthManager = LBSAuthManager.getInstance(context);
        f1876k = lBSAuthManager.getCUID();
        f1873h = lBSAuthManager.getDeviceID();
        f1874i = lBSAuthManager.getIMEI();
        f1875j = C0854e.m1234b(context);
        f1871f = C0855f.f1750c;
        f1872g = "3.0.7";
        f1877l = C0855f.f1748a;
        f1878m = C0855f.f1749b;
    }

    @Override // com.baidu.trace.p012c.InterfaceC0856g
    /* JADX INFO: renamed from: a */
    public final void mo1083a() {
    }

    @Override // com.baidu.trace.p012c.InterfaceC0856g
    /* JADX INFO: renamed from: a */
    public final void mo1084a(JSONObject jSONObject) {
        C0827az.f1632b = 0;
        C0797f c0797f = new C0797f();
        try {
            try {
                c0797f.f1185a = (byte) jSONObject.getInt("operat_result");
                c0797f.f1193i = (byte) jSONObject.getInt("is_activated");
                c0797f.f1186b = (byte) jSONObject.getInt("heartbeat_period");
                c0797f.f1187c = (byte) jSONObject.getInt("packed_data_transmit_period");
                c0797f.f1188d = (byte) jSONObject.getInt("packed_data_capacity");
                c0797f.f1189e = (byte) jSONObject.getInt("packed_data_need_response");
                c0797f.f1190f = (byte) jSONObject.getInt("module_switch");
                c0797f.f1191g = (byte) jSONObject.getInt("sensor_wakeup_threshold");
                c0797f.f1192h = (byte) jSONObject.getInt("volume_size");
                c0797f.f1194j = (byte) jSONObject.getInt("location_info_sample_period");
            } catch (Exception unused) {
                c0797f.f1185a = (byte) 0;
                c0797f.f1186b = (byte) 15;
                c0797f.f1190f = (byte) 31;
                c0797f.f1188d = (byte) 96;
                c0797f.f1189e = (byte) 1;
                c0797f.f1187c = (byte) 30;
                c0797f.f1194j = (byte) 5;
                c0797f.f1191g = (byte) 5;
                c0797f.f1192h = (byte) 50;
            }
        } finally {
            C0842bc.m1163a().m1170a(c0797f);
        }
    }
}
