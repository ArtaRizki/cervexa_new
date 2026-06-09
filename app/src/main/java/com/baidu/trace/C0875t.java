package com.baidu.trace;

import com.baidu.trace.p010a.C0795d;
import com.baidu.trace.p012c.InterfaceC0856g;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.baidu.trace.t */
/* JADX INFO: loaded from: classes.dex */
public class C0875t implements InterfaceC0856g {
    @Override // com.baidu.trace.p012c.InterfaceC0856g
    /* JADX INFO: renamed from: a */
    public final void mo1083a() {
    }

    @Override // com.baidu.trace.p012c.InterfaceC0856g
    /* JADX INFO: renamed from: a */
    public final void mo1084a(JSONObject jSONObject) {
        C0827az.f1631a = 0;
        C0795d c0795d = new C0795d();
        try {
            try {
                c0795d.f1182a = (byte) jSONObject.getInt("operat_result");
            } catch (Exception unused) {
                c0795d.f1182a = (byte) 0;
            }
        } finally {
            C0842bc.m1163a().m1169a(c0795d);
        }
    }
}
