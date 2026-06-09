package com.baidu.trace;

import com.baidu.trace.p010a.C0800i;
import com.baidu.trace.p012c.InterfaceC0856g;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.baidu.trace.ay */
/* JADX INFO: loaded from: classes.dex */
public class C0826ay implements InterfaceC0856g {

    /* JADX INFO: renamed from: a */
    protected static byte f1626a;

    /* JADX INFO: renamed from: b */
    protected static int f1627b;

    /* JADX INFO: renamed from: c */
    protected static byte[] f1628c = new byte[32];

    /* JADX INFO: renamed from: d */
    protected static byte f1629d;

    /* JADX INFO: renamed from: e */
    protected static String f1630e;

    @Override // com.baidu.trace.p012c.InterfaceC0856g
    /* JADX INFO: renamed from: a */
    public final void mo1083a() {
        TraceJniInterface.m951a().setPushResult(f1627b, f1626a);
    }

    @Override // com.baidu.trace.p012c.InterfaceC0856g
    /* JADX INFO: renamed from: a */
    public final void mo1084a(JSONObject jSONObject) {
        C0800i c0800i = new C0800i();
        try {
            c0800i.f1205a = jSONObject.getString("ak");
            c0800i.f1206b = jSONObject.getInt("msg_flag");
            c0800i.f1207c = (byte) jSONObject.getInt("info_type");
            c0800i.f1208d = jSONObject.getString("info_content");
            C0842bc.m1163a().m1171a(c0800i, jSONObject.getLong("time_flag"));
        } catch (Exception unused) {
        }
    }
}
