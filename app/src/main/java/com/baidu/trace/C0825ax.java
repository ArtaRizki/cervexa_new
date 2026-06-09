package com.baidu.trace;

import com.baidu.trace.p012c.InterfaceC0856g;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.baidu.trace.ax */
/* JADX INFO: loaded from: classes.dex */
public final class C0825ax {

    /* JADX INFO: renamed from: a */
    private static int f1624a;

    /* JADX INFO: renamed from: b */
    private static Map<Short, InterfaceC0856g> f1625b;

    /* JADX INFO: renamed from: a */
    protected static void m1115a() {
        if (f1625b == null) {
            f1625b = new HashMap();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:63:0x010c, code lost:
    
        return true;
     */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean m1116a(java.io.DataInputStream r17) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 269
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.trace.C0825ax.m1116a(java.io.DataInputStream):boolean");
    }

    /* JADX INFO: renamed from: a */
    public static byte[] m1117a(short s, int i) {
        InterfaceC0856g interfaceC0856g;
        Map<Short, InterfaceC0856g> map = f1625b;
        byte[] bArrBuildRequestProtocolData = null;
        if (map == null || !map.containsKey(Short.valueOf(s))) {
            try {
                interfaceC0856g = (InterfaceC0856g) Class.forName(C0869n.f1835a.get(Short.valueOf(s))).newInstance();
            } catch (Exception unused) {
            }
        } else {
            interfaceC0856g = f1625b.get(Short.valueOf(s));
        }
        interfaceC0856g.mo1083a();
        try {
            bArrBuildRequestProtocolData = TraceJniInterface.m951a().buildRequestProtocolData(s, i);
        } catch (Exception unused2) {
        }
        if (bArrBuildRequestProtocolData != null) {
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : bArrBuildRequestProtocolData) {
                stringBuffer.append((int) b);
            }
        }
        return bArrBuildRequestProtocolData;
    }

    /* JADX INFO: renamed from: b */
    protected static void m1118b() {
        Map<Short, InterfaceC0856g> map = f1625b;
        if (map != null) {
            map.clear();
            f1625b = null;
        }
    }
}
