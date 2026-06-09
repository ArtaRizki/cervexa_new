package com.tencent.open.p026a;

import android.os.Environment;
import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import com.tencent.open.p026a.C2059d;
import com.tencent.open.utils.C2084d;
import java.io.File;

/* JADX INFO: renamed from: com.tencent.open.a.f */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class C2061f {

    /* JADX INFO: renamed from: a */
    public static C2061f f3173a = null;

    /* JADX INFO: renamed from: c */
    protected static final C2057b f3174c = new C2057b(m2132c(), C2058c.f3167m, C2058c.f3161g, C2058c.f3162h, C2058c.f3157c, C2058c.f3163i, 10, C2058c.f3159e, C2058c.f3168n);

    /* JADX INFO: renamed from: d */
    private static boolean f3175d = false;

    /* JADX INFO: renamed from: b */
    protected C2056a f3176b = new C2056a(f3174c);

    /* JADX INFO: renamed from: a */
    public static C2061f m2126a() {
        if (f3173a == null) {
            synchronized (C2061f.class) {
                if (f3173a == null) {
                    f3173a = new C2061f();
                    f3175d = true;
                }
            }
        }
        return f3173a;
    }

    private C2061f() {
    }

    /* JADX INFO: renamed from: a */
    protected void m2136a(int i, String str, String str2, Throwable th) {
        C2056a c2056a;
        if (f3175d) {
            String strM2217b = C2084d.m2217b();
            if (!TextUtils.isEmpty(strM2217b)) {
                String str3 = strM2217b + " SDK_VERSION:" + Constants.SDK_VERSION;
                if (this.f3176b == null) {
                    return;
                }
                C2060e.f3172a.m2147b(32, Thread.currentThread(), System.currentTimeMillis(), "openSDK_LOG", str3, null);
                this.f3176b.m2147b(32, Thread.currentThread(), System.currentTimeMillis(), "openSDK_LOG", str3, null);
                f3175d = false;
            }
        }
        C2060e.f3172a.m2147b(i, Thread.currentThread(), System.currentTimeMillis(), str, str2, th);
        if (!C2059d.a.m2115a(C2058c.f3156b, i) || (c2056a = this.f3176b) == null) {
            return;
        }
        c2056a.m2147b(i, Thread.currentThread(), System.currentTimeMillis(), str, str2, th);
    }

    /* JADX INFO: renamed from: a */
    public static final void m2127a(String str, String str2) {
        m2126a().m2136a(1, str, str2, null);
    }

    /* JADX INFO: renamed from: b */
    public static final void m2130b(String str, String str2) {
        m2126a().m2136a(2, str, str2, null);
    }

    /* JADX INFO: renamed from: a */
    public static final void m2128a(String str, String str2, Throwable th) {
        m2126a().m2136a(2, str, str2, th);
    }

    /* JADX INFO: renamed from: c */
    public static final void m2133c(String str, String str2) {
        m2126a().m2136a(4, str, str2, null);
    }

    /* JADX INFO: renamed from: d */
    public static final void m2134d(String str, String str2) {
        m2126a().m2136a(8, str, str2, null);
    }

    /* JADX INFO: renamed from: e */
    public static final void m2135e(String str, String str2) {
        m2126a().m2136a(16, str, str2, null);
    }

    /* JADX INFO: renamed from: b */
    public static final void m2131b(String str, String str2, Throwable th) {
        m2126a().m2136a(16, str, str2, th);
    }

    /* JADX INFO: renamed from: b */
    public static void m2129b() {
        synchronized (C2061f.class) {
            m2126a().m2137d();
            if (f3173a != null) {
                f3173a = null;
            }
        }
    }

    /* JADX INFO: renamed from: c */
    protected static File m2132c() {
        String str = C2058c.f3158d;
        C2059d.c cVarM2117b = C2059d.b.m2117b();
        if (cVarM2117b != null && cVarM2117b.m2124c() > C2058c.f3160f) {
            return new File(Environment.getExternalStorageDirectory(), str);
        }
        return new File(C2084d.m2218c(), str);
    }

    /* JADX INFO: renamed from: d */
    protected void m2137d() {
        C2056a c2056a = this.f3176b;
        if (c2056a != null) {
            c2056a.m2091a();
            this.f3176b.m2095b();
            this.f3176b = null;
        }
    }
}
