package com.baidu.platform.comapi.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.baidu.android.bbalbs.common.util.CommonParam;
import com.baidu.mapapi.VersionInfo;
import com.baidu.platform.comjni.util.AppMD5;
import com.baidu.platform.comjni.util.C0787a;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.baidu.platform.comapi.util.e */
/* JADX INFO: loaded from: classes.dex */
public class C0779e {

    /* JADX INFO: renamed from: b */
    static String f1088b;

    /* JADX INFO: renamed from: c */
    static String f1089c;

    /* JADX INFO: renamed from: d */
    static String f1090d;

    /* JADX INFO: renamed from: e */
    static String f1091e;

    /* JADX INFO: renamed from: f */
    static int f1092f;

    /* JADX INFO: renamed from: g */
    static int f1093g;

    /* JADX INFO: renamed from: h */
    static int f1094h;

    /* JADX INFO: renamed from: i */
    static int f1095i;

    /* JADX INFO: renamed from: j */
    static int f1096j;

    /* JADX INFO: renamed from: k */
    static int f1097k;

    /* JADX INFO: renamed from: l */
    static String f1098l;

    /* JADX INFO: renamed from: m */
    static String f1099m;

    /* JADX INFO: renamed from: s */
    static String f1105s;

    /* JADX INFO: renamed from: t */
    static String f1106t;

    /* JADX INFO: renamed from: w */
    public static Context f1109w;

    /* JADX INFO: renamed from: z */
    public static String f1112z;

    /* JADX INFO: renamed from: A */
    private static final String f1081A = C0779e.class.getSimpleName();

    /* JADX INFO: renamed from: B */
    private static C0787a f1082B = new C0787a();

    /* JADX INFO: renamed from: a */
    static String f1087a = "02";

    /* JADX INFO: renamed from: n */
    static String f1100n = "baidu";

    /* JADX INFO: renamed from: o */
    static String f1101o = "baidu";

    /* JADX INFO: renamed from: p */
    static String f1102p = "";

    /* JADX INFO: renamed from: q */
    static String f1103q = "";

    /* JADX INFO: renamed from: r */
    static String f1104r = "";

    /* JADX INFO: renamed from: u */
    static String f1107u = "-1";

    /* JADX INFO: renamed from: v */
    static String f1108v = "-1";

    /* JADX INFO: renamed from: x */
    public static final int f1110x = Integer.parseInt(Build.VERSION.SDK);

    /* JADX INFO: renamed from: y */
    public static float f1111y = 1.0f;

    /* JADX INFO: renamed from: C */
    private static boolean f1083C = true;

    /* JADX INFO: renamed from: D */
    private static int f1084D = 0;

    /* JADX INFO: renamed from: E */
    private static int f1085E = 0;

    /* JADX INFO: renamed from: F */
    private static Map<String, String> f1086F = new HashMap();

    /* JADX INFO: renamed from: a */
    public static void m796a() {
        m804d();
    }

    /* JADX INFO: renamed from: a */
    public static void m797a(String str) {
        f1098l = str;
        m808f();
    }

    /* JADX INFO: renamed from: a */
    public static void m798a(String str, String str2) {
        f1107u = str2;
        f1108v = str;
        m808f();
    }

    /* JADX INFO: renamed from: a */
    public static byte[] m799a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0].toByteArray();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: renamed from: b */
    public static Bundle m800b() {
        Bundle bundle = new Bundle();
        bundle.putString("cpu", f1102p);
        bundle.putString("resid", f1087a);
        bundle.putString("channel", f1100n);
        bundle.putString("glr", f1103q);
        bundle.putString("glv", f1104r);
        bundle.putString("mb", m810g());
        bundle.putString("sv", m812i());
        bundle.putString("os", m814k());
        bundle.putInt("dpi_x", m815l());
        bundle.putInt("dpi_y", m815l());
        bundle.putString("net", f1098l);
        bundle.putString("cuid", m818o());
        bundle.putByteArray("signature", m799a(f1109w));
        bundle.putString("pcn", f1109w.getPackageName());
        bundle.putInt("screen_x", m811h());
        bundle.putInt("screen_y", m813j());
        C0787a c0787a = f1082B;
        if (c0787a != null) {
            c0787a.m905a(bundle);
            Log.d("phoneInfo", "mAppSysOSAPI not null");
        }
        Log.d("phoneInfo", bundle.toString());
        return bundle;
    }

    /* JADX INFO: renamed from: b */
    public static void m801b(Context context) {
        Map<String, String> map;
        String str;
        String strEncodeUrlParamsValue = "pcn";
        Object objValueOf = "cuid";
        String strEncodeUrlParamsValue2 = "dpi";
        String strEncodeUrlParamsValue3 = "os";
        Object objValueOf2 = "sv";
        String strEncodeUrlParamsValue4 = "mb";
        String strEncodeUrlParamsValue5 = "channel";
        String strEncodeUrlParamsValue6 = "resid";
        f1109w = context;
        f1105s = context.getFilesDir().getAbsolutePath();
        f1106t = context.getCacheDir().getAbsolutePath();
        f1089c = Build.MODEL;
        f1090d = "Android" + Build.VERSION.SDK;
        f1088b = context.getPackageName();
        m803c(context);
        m805d(context);
        m807e(context);
        m809f(context);
        try {
            try {
                LocationManager locationManager = (LocationManager) context.getSystemService("location");
                f1084D = locationManager.isProviderEnabled("gps") ? 1 : 0;
                f1085E = locationManager.isProviderEnabled("network") ? 1 : 0;
                f1086F.put("resid", AppMD5.encodeUrlParamsValue(f1087a));
                Map<String, String> map2 = f1086F;
                strEncodeUrlParamsValue6 = AppMD5.encodeUrlParamsValue(m816m());
                map2.put("channel", strEncodeUrlParamsValue6);
                Map<String, String> map3 = f1086F;
                strEncodeUrlParamsValue5 = AppMD5.encodeUrlParamsValue(m810g());
                map3.put("mb", strEncodeUrlParamsValue5);
                Map<String, String> map4 = f1086F;
                strEncodeUrlParamsValue4 = AppMD5.encodeUrlParamsValue(m812i());
                map4.put("sv", strEncodeUrlParamsValue4);
                f1086F.put("os", AppMD5.encodeUrlParamsValue(m814k()));
                Map<String, String> map5 = f1086F;
                objValueOf2 = Integer.valueOf(m815l());
                strEncodeUrlParamsValue3 = AppMD5.encodeUrlParamsValue(String.format("%d,%d", Integer.valueOf(m815l()), objValueOf2));
                map5.put("dpi", strEncodeUrlParamsValue3);
                Map<String, String> map6 = f1086F;
                strEncodeUrlParamsValue2 = AppMD5.encodeUrlParamsValue(m818o());
                map6.put("cuid", strEncodeUrlParamsValue2);
                f1086F.put("pcn", AppMD5.encodeUrlParamsValue(f1109w.getPackageName()));
                map = f1086F;
                objValueOf = Integer.valueOf(m813j());
                str = String.format("%d,%d", Integer.valueOf(m811h()), objValueOf);
            } catch (Exception unused) {
                Log.w("baidumapsdk", "LocationManager error");
                f1086F.put("resid", AppMD5.encodeUrlParamsValue(f1087a));
                Map<String, String> map7 = f1086F;
                strEncodeUrlParamsValue6 = AppMD5.encodeUrlParamsValue(m816m());
                map7.put("channel", strEncodeUrlParamsValue6);
                Map<String, String> map8 = f1086F;
                strEncodeUrlParamsValue5 = AppMD5.encodeUrlParamsValue(m810g());
                map8.put("mb", strEncodeUrlParamsValue5);
                Map<String, String> map9 = f1086F;
                strEncodeUrlParamsValue4 = AppMD5.encodeUrlParamsValue(m812i());
                map9.put("sv", strEncodeUrlParamsValue4);
                f1086F.put("os", AppMD5.encodeUrlParamsValue(m814k()));
                Map<String, String> map10 = f1086F;
                objValueOf2 = Integer.valueOf(m815l());
                strEncodeUrlParamsValue3 = AppMD5.encodeUrlParamsValue(String.format("%d,%d", Integer.valueOf(m815l()), objValueOf2));
                map10.put("dpi", strEncodeUrlParamsValue3);
                Map<String, String> map11 = f1086F;
                strEncodeUrlParamsValue2 = AppMD5.encodeUrlParamsValue(m818o());
                map11.put("cuid", strEncodeUrlParamsValue2);
                f1086F.put("pcn", AppMD5.encodeUrlParamsValue(f1109w.getPackageName()));
                map = f1086F;
                objValueOf = Integer.valueOf(m813j());
                str = String.format("%d,%d", Integer.valueOf(m811h()), objValueOf);
            }
            strEncodeUrlParamsValue = AppMD5.encodeUrlParamsValue(str);
            map.put("screen", strEncodeUrlParamsValue);
            C0787a c0787a = f1082B;
            if (c0787a != null) {
                c0787a.m904a();
            }
        } catch (Throwable th) {
            f1086F.put(strEncodeUrlParamsValue6, AppMD5.encodeUrlParamsValue(f1087a));
            f1086F.put(strEncodeUrlParamsValue5, AppMD5.encodeUrlParamsValue(m816m()));
            f1086F.put(strEncodeUrlParamsValue4, AppMD5.encodeUrlParamsValue(m810g()));
            f1086F.put((String) objValueOf2, AppMD5.encodeUrlParamsValue(m812i()));
            f1086F.put(strEncodeUrlParamsValue3, AppMD5.encodeUrlParamsValue(m814k()));
            f1086F.put(strEncodeUrlParamsValue2, AppMD5.encodeUrlParamsValue(String.format("%d,%d", Integer.valueOf(m815l()), Integer.valueOf(m815l()))));
            f1086F.put((String) objValueOf, AppMD5.encodeUrlParamsValue(m818o()));
            f1086F.put(strEncodeUrlParamsValue, AppMD5.encodeUrlParamsValue(f1109w.getPackageName()));
            f1086F.put("screen", AppMD5.encodeUrlParamsValue(String.format("%d,%d", Integer.valueOf(m811h()), Integer.valueOf(m813j()))));
            throw th;
        }
    }

    /* JADX INFO: renamed from: c */
    public static String m802c() {
        if (f1086F == null) {
            return null;
        }
        Date date = new Date();
        long time = date.getTime() + ((long) (date.getSeconds() * 1000));
        f1086F.put("ctm", AppMD5.encodeUrlParamsValue(String.format("%f", Double.valueOf((time / 1000) + ((time % 1000) / 1000.0d)))));
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : f1086F.entrySet()) {
            sb.append("&");
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
        }
        return sb.toString();
    }

    /* JADX INFO: renamed from: c */
    private static void m803c(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            String apiVersion = VersionInfo.getApiVersion();
            f1091e = apiVersion;
            if (apiVersion != null && !apiVersion.equals("")) {
                f1091e = f1091e.replace('_', '.');
            }
            f1092f = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            f1091e = "1.0.0";
            f1092f = 1;
        }
    }

    /* JADX INFO: renamed from: d */
    public static void m804d() {
    }

    /* JADX INFO: renamed from: d */
    private static void m805d(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Display defaultDisplay = windowManager != null ? windowManager.getDefaultDisplay() : null;
        if (defaultDisplay != null) {
            f1093g = defaultDisplay.getWidth();
            f1094h = defaultDisplay.getHeight();
            defaultDisplay.getMetrics(displayMetrics);
        }
        f1111y = displayMetrics.density;
        f1095i = (int) displayMetrics.xdpi;
        f1096j = (int) displayMetrics.ydpi;
        if (f1110x > 3) {
            f1097k = displayMetrics.densityDpi;
        } else {
            f1097k = 160;
        }
        if (f1097k == 0) {
            f1097k = 160;
        }
    }

    /* JADX INFO: renamed from: e */
    public static String m806e() {
        return f1098l;
    }

    /* JADX INFO: renamed from: e */
    private static void m807e(Context context) {
        f1099m = Settings.Secure.getString(context.getContentResolver(), "android_id");
    }

    /* JADX INFO: renamed from: f */
    public static void m808f() {
        f1086F.put("net", AppMD5.encodeUrlParamsValue(m806e()));
        f1086F.put("appid", AppMD5.encodeUrlParamsValue(f1107u));
        f1086F.put("bduid", "");
        if (f1082B == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("cpu", f1102p);
        bundle.putString("resid", f1087a);
        bundle.putString("channel", f1100n);
        bundle.putString("glr", f1103q);
        bundle.putString("glv", f1104r);
        bundle.putString("mb", m810g());
        bundle.putString("sv", m812i());
        bundle.putString("os", m814k());
        bundle.putInt("dpi_x", m815l());
        bundle.putInt("dpi_y", m815l());
        bundle.putString("net", f1098l);
        bundle.putString("cuid", m818o());
        bundle.putString("pcn", f1109w.getPackageName());
        bundle.putInt("screen_x", m811h());
        bundle.putInt("screen_y", m813j());
        bundle.putString("appid", f1107u);
        bundle.putString("duid", f1108v);
        if (!TextUtils.isEmpty(f1112z)) {
            bundle.putString("token", f1112z);
        }
        f1082B.m905a(bundle);
        SysUpdateObservable.getInstance().updatePhoneInfo();
    }

    /* JADX INFO: renamed from: f */
    private static void m809f(Context context) {
        f1098l = "0";
    }

    /* JADX INFO: renamed from: g */
    public static String m810g() {
        return f1089c;
    }

    /* JADX INFO: renamed from: h */
    public static int m811h() {
        return f1093g;
    }

    /* JADX INFO: renamed from: i */
    public static String m812i() {
        return f1091e;
    }

    /* JADX INFO: renamed from: j */
    public static int m813j() {
        return f1094h;
    }

    /* JADX INFO: renamed from: k */
    public static String m814k() {
        return f1090d;
    }

    /* JADX INFO: renamed from: l */
    public static int m815l() {
        return f1097k;
    }

    /* JADX INFO: renamed from: m */
    public static String m816m() {
        return f1100n;
    }

    /* JADX INFO: renamed from: n */
    public static String m817n() {
        return f1105s;
    }

    /* JADX INFO: renamed from: o */
    public static String m818o() {
        String strM75a;
        try {
            strM75a = CommonParam.m75a(f1109w);
        } catch (Exception unused) {
            strM75a = "";
        }
        return strM75a == null ? "" : strM75a;
    }
}
