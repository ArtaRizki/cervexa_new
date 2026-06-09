package com.baidu.trace.p011b;

import android.os.Handler;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: renamed from: com.baidu.trace.b.a */
/* JADX INFO: loaded from: classes.dex */
public final class C0829a {

    /* JADX INFO: renamed from: a */
    private static a f1634a = a.f1636a;

    /* JADX INFO: renamed from: b */
    private static InterfaceC0830b f1635b = null;

    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX INFO: renamed from: com.baidu.trace.b.a$a */
    public static final class a {

        /* JADX INFO: renamed from: a */
        public static final a f1636a = new a("TCP", 0);

        /* JADX INFO: renamed from: b */
        public static final a f1637b = new a("UDP", 1);

        private a(String str, int i) {
        }
    }

    /* JADX INFO: renamed from: a */
    public static a m1121a() {
        return f1634a;
    }

    /* JADX INFO: renamed from: a */
    public static void m1122a(a aVar, C0833e c0833e) {
        int iMo1132c;
        f1634a = aVar;
        if (a.f1636a == aVar) {
            InterfaceC0830b interfaceC0830b = f1635b;
            if (interfaceC0830b != null) {
                interfaceC0830b.mo1128a();
                if (f1635b instanceof C0839k) {
                    f1635b = null;
                    f1635b = C0838j.m1161e();
                }
            } else {
                f1635b = C0838j.m1161e();
            }
            f1635b.mo1129a(c0833e);
            return;
        }
        if (a.f1637b == aVar) {
            InterfaceC0830b interfaceC0830b2 = f1635b;
            if (interfaceC0830b2 != null) {
                interfaceC0830b2.mo1128a();
                InterfaceC0830b interfaceC0830b3 = f1635b;
                if (interfaceC0830b3 instanceof C0838j) {
                    iMo1132c = interfaceC0830b3.mo1132c();
                    f1635b = null;
                }
                f1635b.mo1129a(c0833e);
            }
            iMo1132c = 8300;
            f1635b = C0839k.m1162a(iMo1132c);
            f1635b.mo1129a(c0833e);
        }
    }

    /* JADX INFO: renamed from: a */
    public static synchronized void m1123a(byte[] bArr, Handler handler) {
        if (m1126d()) {
            try {
                f1635b.mo1130a(bArr);
            } catch (Exception unused) {
                if (handler != null) {
                    handler.obtainMessage(4).sendToTarget();
                }
            }
        } else if (handler != null) {
            handler.obtainMessage(4).sendToTarget();
        }
    }

    /* JADX INFO: renamed from: b */
    public static InterfaceC0830b m1124b() {
        return f1635b;
    }

    /* JADX INFO: renamed from: c */
    protected static synchronized DataInputStream m1125c() throws IOException {
        if (!m1126d()) {
            throw new IOException();
        }
        InputStream inputStreamMo1133d = f1635b.mo1133d();
        if (inputStreamMo1133d == null) {
            return null;
        }
        return new DataInputStream(inputStreamMo1133d);
    }

    /* JADX INFO: renamed from: d */
    public static boolean m1126d() {
        InterfaceC0830b interfaceC0830b = f1635b;
        if (interfaceC0830b == null) {
            return false;
        }
        try {
            return interfaceC0830b.mo1131b();
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: renamed from: e */
    public static void m1127e() {
        InterfaceC0830b interfaceC0830b = f1635b;
        if (interfaceC0830b != null) {
            interfaceC0830b.mo1128a();
        }
    }
}
