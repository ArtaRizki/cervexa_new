package com.tencent.open.p027b;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.tencent.open.p026a.C2061f;
import com.tencent.open.utils.C2084d;
import java.util.Locale;
import kotlin.text.Typography;

/* JADX INFO: renamed from: com.tencent.open.b.c */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class C2068c {

    /* JADX INFO: renamed from: a */
    static String f3188a;

    /* JADX INFO: renamed from: b */
    static String f3189b;

    /* JADX INFO: renamed from: c */
    static String f3190c;

    /* JADX INFO: renamed from: d */
    private static String f3191d;

    /* JADX INFO: renamed from: e */
    private static String f3192e;

    /* JADX INFO: renamed from: a */
    public static String m2155a() {
        WifiManager wifiManager;
        WifiInfo connectionInfo;
        try {
            Context contextM2215a = C2084d.m2215a();
            return (contextM2215a == null || (wifiManager = (WifiManager) contextM2215a.getSystemService("wifi")) == null || (connectionInfo = wifiManager.getConnectionInfo()) == null) ? "" : connectionInfo.getMacAddress();
        } catch (SecurityException e) {
            C2061f.m2131b("openSDK_LOG.MobileInfoUtil", "getLocalMacAddress>>>", e);
            return "";
        }
    }

    /* JADX INFO: renamed from: a */
    public static String m2156a(Context context) {
        if (!TextUtils.isEmpty(f3191d)) {
            return f3191d;
        }
        if (context == null) {
            return "";
        }
        f3191d = "";
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager != null) {
            f3191d = windowManager.getDefaultDisplay().getWidth() + "x" + windowManager.getDefaultDisplay().getHeight();
        }
        return f3191d;
    }

    /* JADX INFO: renamed from: b */
    public static String m2157b() {
        return Locale.getDefault().getLanguage();
    }

    /* JADX INFO: renamed from: b */
    public static String m2158b(Context context) {
        String str = f3188a;
        if (str != null && str.length() > 0) {
            return f3188a;
        }
        if (context == null) {
            return "";
        }
        try {
            String deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            f3188a = deviceId;
            return deviceId;
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX INFO: renamed from: c */
    public static String m2159c(Context context) {
        String str = f3189b;
        if (str != null && str.length() > 0) {
            return f3189b;
        }
        if (context == null) {
            return "";
        }
        try {
            String simSerialNumber = ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
            f3189b = simSerialNumber;
            return simSerialNumber;
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX INFO: renamed from: d */
    public static String m2160d(Context context) {
        String str = f3190c;
        if (str != null && str.length() > 0) {
            return f3190c;
        }
        if (context == null) {
            return "";
        }
        try {
            String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
            f3190c = string;
            return string;
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX INFO: renamed from: e */
    public static String m2161e(Context context) {
        try {
            if (f3192e == null) {
                WindowManager windowManager = (WindowManager) context.getSystemService("window");
                DisplayMetrics displayMetrics = new DisplayMetrics();
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                StringBuilder sb = new StringBuilder();
                sb.append("imei=");
                sb.append(m2158b(context));
                sb.append(Typography.amp);
                sb.append("model=");
                sb.append(Build.MODEL);
                sb.append(Typography.amp);
                sb.append("os=");
                sb.append(Build.VERSION.RELEASE);
                sb.append(Typography.amp);
                sb.append("apilevel=");
                sb.append(Build.VERSION.SDK_INT);
                sb.append(Typography.amp);
                String strM2151b = C2066a.m2151b(context);
                if (strM2151b == null) {
                    strM2151b = "";
                }
                sb.append("network=");
                sb.append(strM2151b);
                sb.append(Typography.amp);
                sb.append("sdcard=");
                sb.append(Environment.getExternalStorageState().equals("mounted") ? 1 : 0);
                sb.append(Typography.amp);
                sb.append("display=");
                sb.append(displayMetrics.widthPixels);
                sb.append('*');
                sb.append(displayMetrics.heightPixels);
                sb.append(Typography.amp);
                sb.append("manu=");
                sb.append(Build.MANUFACTURER);
                sb.append("&");
                sb.append("wifi=");
                sb.append(C2066a.m2154e(context));
                f3192e = sb.toString();
            }
            return f3192e;
        } catch (Exception unused) {
            return null;
        }
    }
}
