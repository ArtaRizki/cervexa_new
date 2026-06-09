package com.baidu.trace;

import com.baidu.trace.p010a.C0793b;
import com.baidu.trace.p010a.C0796e;
import com.baidu.trace.p012c.InterfaceC0856g;
import java.util.ArrayList;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.baidu.trace.c */
/* JADX INFO: loaded from: classes.dex */
public final class C0849c implements InterfaceC0856g {

    /* JADX INFO: renamed from: a */
    private static ArrayList<C0796e> f1714a;

    /* JADX INFO: renamed from: b */
    protected static void m1202b() {
        ArrayList<C0796e> arrayList = f1714a;
        if (arrayList != null) {
            arrayList.clear();
            f1714a = null;
        }
    }

    @Override // com.baidu.trace.p012c.InterfaceC0856g
    /* JADX INFO: renamed from: a */
    public final void mo1083a() {
        if (f1714a == null) {
            f1714a = new ArrayList<>();
        }
        TraceJniInterface.m951a().clearAttributeData();
        for (C0796e c0796e : f1714a) {
            TraceJniInterface.m951a().addAttributeData(c0796e.f1183a, c0796e.f1184b);
        }
    }

    @Override // com.baidu.trace.p012c.InterfaceC0856g
    /* JADX INFO: renamed from: a */
    public final void mo1084a(JSONObject jSONObject) {
        new C0793b();
        C0842bc.m1163a();
        C0842bc.m1165b();
    }
}
