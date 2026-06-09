package com.tencent.connect.auth;

import com.tencent.tauth.IUiListener;
import java.util.HashMap;

/* JADX INFO: renamed from: com.tencent.connect.auth.b */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class C2030b {

    /* JADX INFO: renamed from: a */
    public static C2030b f3010a;

    /* JADX INFO: renamed from: d */
    static final /* synthetic */ boolean f3011d = !C2030b.class.desiredAssertionStatus();

    /* JADX INFO: renamed from: e */
    private static int f3012e = 0;

    /* JADX INFO: renamed from: b */
    public HashMap<String, a> f3013b = new HashMap<>();

    /* JADX INFO: renamed from: c */
    public final String f3014c = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /* JADX INFO: renamed from: com.tencent.connect.auth.b$a */
    /* JADX INFO: compiled from: ProGuard */
    public static class a {

        /* JADX INFO: renamed from: a */
        public IUiListener f3015a;

        /* JADX INFO: renamed from: b */
        public DialogC2029a f3016b;

        /* JADX INFO: renamed from: c */
        public String f3017c;
    }

    /* JADX INFO: renamed from: a */
    public static C2030b m1985a() {
        if (f3010a == null) {
            f3010a = new C2030b();
        }
        return f3010a;
    }

    /* JADX INFO: renamed from: b */
    public static int m1986b() {
        int i = f3012e + 1;
        f3012e = i;
        return i;
    }

    /* JADX INFO: renamed from: a */
    public String m1987a(a aVar) {
        int iM1986b = m1986b();
        try {
            this.f3013b.put("" + iM1986b, aVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return "" + iM1986b;
    }

    /* JADX INFO: renamed from: c */
    public String m1988c() {
        int iCeil = (int) Math.ceil((Math.random() * 20.0d) + 3.0d);
        char[] charArray = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        int length = charArray.length;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < iCeil; i++) {
            stringBuffer.append(charArray[(int) (Math.random() * ((double) length))]);
        }
        return stringBuffer.toString();
    }
}
