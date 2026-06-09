package com.baidu.trace;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.baidu.trace.p010a.C0792a;
import com.baidu.trace.p010a.C0795d;
import com.baidu.trace.p010a.C0797f;
import com.baidu.trace.p010a.C0799h;
import com.baidu.trace.p010a.C0800i;
import com.baidu.trace.p012c.C0853d;
import com.baidu.trace.p012c.C0854e;
import com.jieli.stream.p016dv.running2.util.IConstant;

/* JADX INFO: renamed from: com.baidu.trace.bc */
/* JADX INFO: loaded from: classes.dex */
public final class C0842bc {

    /* JADX INFO: renamed from: b */
    private static final C0842bc f1675b = new C0842bc();

    /* JADX INFO: renamed from: a */
    private Handler f1676a;

    private C0842bc() {
    }

    /* JADX INFO: renamed from: a */
    protected static C0842bc m1163a() {
        return f1675b;
    }

    /* JADX INFO: renamed from: a */
    protected static void m1164a(C0799h c0799h) {
        C0854e.f1740d = C0854e.m1224a();
        C0824aw.m1111b(c0799h.f1204a);
    }

    /* JADX INFO: renamed from: b */
    protected static void m1165b() {
    }

    /* JADX INFO: renamed from: a */
    protected final void m1166a(int i) {
        Handler handler = this.f1676a;
        if (handler == null) {
            return;
        }
        Message messageObtainMessage = handler.obtainMessage(32);
        messageObtainMessage.arg1 = i;
        messageObtainMessage.sendToTarget();
    }

    /* JADX INFO: renamed from: a */
    protected final void m1167a(Handler handler) {
        this.f1676a = handler;
    }

    /* JADX INFO: renamed from: a */
    protected final void m1168a(C0792a c0792a) {
        C0854e.f1740d = C0854e.m1224a();
        C0828b.f1633a = c0792a.f1172a;
        Handler handler = this.f1676a;
        if (handler != null) {
            Message messageObtainMessage = handler.obtainMessage(141);
            messageObtainMessage.arg1 = 141;
            messageObtainMessage.sendToTarget();
        }
    }

    /* JADX INFO: renamed from: a */
    protected final void m1169a(C0795d c0795d) {
        C0854e.f1740d = C0854e.m1224a();
        if (this.f1676a != null) {
            if (1 == c0795d.f1182a) {
                this.f1676a.obtainMessage(28).sendToTarget();
                return;
            }
            Message messageObtainMessage = this.f1676a.obtainMessage(30);
            messageObtainMessage.arg1 = 1241;
            messageObtainMessage.sendToTarget();
        }
    }

    /* JADX INFO: renamed from: a */
    protected final void m1170a(C0797f c0797f) {
        C0881z.f1879n = c0797f.f1185a;
        C0881z.f1880o = c0797f.f1193i;
        C0881z.f1888w = c0797f.f1194j;
        C0881z.f1882q = c0797f.f1187c;
        if (Trace.f1157a == 5 && C0881z.f1888w > C0819ar.f1577c) {
            C0819ar.f1576b = C0881z.f1888w * 1000;
        }
        if (Trace.f1158b == 30 && C0881z.f1882q * 1000 >= C0819ar.f1576b) {
            C0843bd.f1683i = C0881z.f1882q * 1000;
        }
        if (c0797f.f1186b >= 5) {
            C0881z.f1881p = c0797f.f1186b;
        }
        C0881z.f1883r = c0797f.f1188d;
        C0881z.f1884s = c0797f.f1189e;
        C0881z.f1885t = c0797f.f1190f;
        C0881z.f1886u = c0797f.f1191g;
        C0881z.f1887v = c0797f.f1192h;
        C0854e.f1740d = C0854e.m1224a();
        if (1 == C0881z.f1879n) {
            Handler handler = this.f1676a;
            if (handler != null) {
                handler.obtainMessage(29).sendToTarget();
                return;
            }
            return;
        }
        Handler handler2 = this.f1676a;
        if (handler2 != null) {
            Message messageObtainMessage = handler2.obtainMessage(30);
            messageObtainMessage.arg1 = 1241;
            messageObtainMessage.sendToTarget();
        }
    }

    /* JADX INFO: renamed from: a */
    protected final void m1171a(C0800i c0800i, long j) {
        C0854e.f1740d = C0854e.m1224a();
        byte[] bArrM1220a = C0853d.m1220a(c0800i.f1205a);
        int i = 0;
        for (int i2 = 0; i2 < C0826ay.f1628c.length && i2 < bArrM1220a.length; i2++) {
            C0826ay.f1628c[i2] = bArrM1220a[i2];
            i++;
        }
        while (i < C0826ay.f1628c.length) {
            C0826ay.f1628c[i] = 0;
            i++;
        }
        C0826ay.f1629d = c0800i.f1207c;
        C0826ay.f1630e = c0800i.f1208d;
        C0826ay.f1627b = c0800i.f1206b;
        C0826ay.f1626a = (byte) 1;
        if (C0826ay.f1629d == 1) {
            C0873r.m1273a(C0826ay.f1630e);
            C0826ay.f1630e = null;
        }
        Handler handler = this.f1676a;
        if (handler != null) {
            Message messageObtainMessage = handler.obtainMessage(IConstant.OP_ENTER_EDIT_MODE);
            Bundle bundle = new Bundle();
            bundle.putByteArray("ak", C0826ay.f1628c);
            bundle.putInt("pushId", C0826ay.f1627b);
            bundle.putLong("timeStamp", j);
            bundle.putByte("infoType", C0826ay.f1629d);
            bundle.putString("infoContent", C0826ay.f1630e);
            messageObtainMessage.setData(bundle);
            messageObtainMessage.sendToTarget();
        }
    }

    /* JADX INFO: renamed from: c */
    protected final boolean m1172c() {
        if (this.f1676a == null) {
            return false;
        }
        C0861g.m1261b();
        this.f1676a.obtainMessage(31).sendToTarget();
        return true;
    }
}
