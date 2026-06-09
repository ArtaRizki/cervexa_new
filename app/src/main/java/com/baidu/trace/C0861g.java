package com.baidu.trace;

import com.baidu.trace.p012c.InterfaceC0856g;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.baidu.trace.g */
/* JADX INFO: loaded from: classes.dex */
public final class C0861g implements InterfaceC0856g {

    /* JADX INFO: renamed from: a */
    protected static String f1785a;

    /* JADX INFO: renamed from: b */
    protected static long f1786b;

    /* JADX INFO: renamed from: c */
    protected static String f1787c;

    /* JADX INFO: renamed from: d */
    protected static String f1788d;

    /* JADX INFO: renamed from: b */
    protected static void m1261b() {
        f1785a = null;
        f1787c = null;
        f1788d = null;
    }

    @Override // com.baidu.trace.p012c.InterfaceC0856g
    /* JADX INFO: renamed from: a */
    public final void mo1083a() {
    }

    @Override // com.baidu.trace.p012c.InterfaceC0856g
    /* JADX INFO: renamed from: a */
    public final void mo1084a(JSONObject jSONObject) {
        try {
            if (jSONObject.has("access_key") && jSONObject.has("expire_time") && jSONObject.has("secret_key") && jSONObject.has("token")) {
                f1785a = jSONObject.getString("access_key");
                f1786b = jSONObject.getLong("expire_time");
                f1787c = jSONObject.getString("secret_key");
                f1788d = jSONObject.getString("token");
                C0862h.m1263a();
                C0842bc.m1163a().m1166a(0);
                return;
            }
            m1261b();
            C0842bc.m1163a().m1166a(1);
        } catch (Exception unused) {
            m1261b();
            C0842bc.m1163a().m1166a(1);
        }
    }
}
