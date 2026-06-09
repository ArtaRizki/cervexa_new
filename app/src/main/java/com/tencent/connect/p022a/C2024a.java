package com.tencent.connect.p022a;

import android.content.Context;
import com.tencent.connect.auth.QQToken;
import com.tencent.open.utils.C2085e;
import java.lang.reflect.Method;

/* JADX INFO: renamed from: com.tencent.connect.a.a */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class C2024a {

    /* JADX INFO: renamed from: a */
    private static Class<?> f2935a = null;

    /* JADX INFO: renamed from: b */
    private static Class<?> f2936b = null;

    /* JADX INFO: renamed from: c */
    private static Method f2937c = null;

    /* JADX INFO: renamed from: d */
    private static Method f2938d = null;

    /* JADX INFO: renamed from: e */
    private static Method f2939e = null;

    /* JADX INFO: renamed from: f */
    private static Method f2940f = null;

    /* JADX INFO: renamed from: g */
    private static boolean f2941g = false;

    /* JADX INFO: renamed from: a */
    public static boolean m1932a(Context context, QQToken qQToken) {
        return C2085e.m2221a(context, qQToken.getAppId()).m2231b("Common_ta_enable");
    }

    /* JADX INFO: renamed from: b */
    public static void m1933b(Context context, QQToken qQToken) {
        try {
            if (m1932a(context, qQToken)) {
                f2940f.invoke(f2935a, true);
            } else {
                f2940f.invoke(f2935a, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: renamed from: c */
    public static void m1934c(Context context, QQToken qQToken) {
        String str = "Aqc" + qQToken.getAppId();
        try {
            f2935a = Class.forName("com.tencent.stat.StatConfig");
            Class<?> cls = Class.forName("com.tencent.stat.StatService");
            f2936b = cls;
            f2937c = cls.getMethod("reportQQ", Context.class, String.class);
            f2938d = f2936b.getMethod("trackCustomEvent", Context.class, String.class, String[].class);
            f2939e = f2936b.getMethod("commitEvents", Context.class, Integer.TYPE);
            f2940f = f2935a.getMethod("setEnableStatService", Boolean.TYPE);
            m1933b(context, qQToken);
            f2935a.getMethod("setAutoExceptionCaught", Boolean.TYPE).invoke(f2935a, false);
            f2935a.getMethod("setEnableSmartReporting", Boolean.TYPE).invoke(f2935a, true);
            f2935a.getMethod("setSendPeriodMinutes", Integer.TYPE).invoke(f2935a, 1440);
            Class<?> cls2 = Class.forName("com.tencent.stat.StatReportStrategy");
            f2935a.getMethod("setStatSendStrategy", cls2).invoke(f2935a, cls2.getField("PERIOD").get(null));
            f2936b.getMethod("startStatService", Context.class, String.class, String.class).invoke(f2936b, context, str, Class.forName("com.tencent.stat.common.StatConstants").getField("VERSION").get(null));
            f2941g = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: renamed from: d */
    public static void m1935d(Context context, QQToken qQToken) {
        if (f2941g) {
            m1933b(context, qQToken);
            if (qQToken.getOpenId() != null) {
                try {
                    f2937c.invoke(f2936b, context, qQToken.getOpenId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m1931a(Context context, QQToken qQToken, String str, String... strArr) {
        if (f2941g) {
            m1933b(context, qQToken);
            try {
                f2938d.invoke(f2936b, context, str, strArr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
