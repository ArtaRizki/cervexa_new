package com.baidu.trace;

import com.baidu.trace.p010a.C0792a;
import com.baidu.trace.p012c.InterfaceC0856g;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.baidu.trace.b */
/* JADX INFO: loaded from: classes.dex */
public class C0828b implements InterfaceC0856g {

    /* JADX INFO: renamed from: a */
    protected static byte f1633a;

    @Override // com.baidu.trace.p012c.InterfaceC0856g
    /* JADX INFO: renamed from: a */
    public final void mo1083a() {
    }

    @Override // com.baidu.trace.p012c.InterfaceC0856g
    /* JADX INFO: renamed from: a */
    public final void mo1084a(JSONObject jSONObject) {
        C0792a c0792a = new C0792a();
        try {
            try {
                c0792a.f1172a = (byte) jSONObject.getInt("operat_result");
            } catch (Exception unused) {
                c0792a.f1172a = (byte) 0;
            }
        } finally {
            C0842bc.m1163a().m1168a(c0792a);
        }
    }
}
