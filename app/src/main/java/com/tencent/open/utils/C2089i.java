package com.tencent.open.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import com.bumptech.glide.load.Key;
import com.jieli.lib.p015dv.control.utils.TopicKey;
import com.tencent.bugly.Bugly;
import com.tencent.connect.common.Constants;
import com.tencent.open.p026a.C2061f;
import com.tencent.open.p027b.C2066a;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import kotlin.UByte;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.tencent.open.utils.i */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class C2089i {

    /* JADX INFO: renamed from: a */
    private static String f3291a = "";

    /* JADX INFO: renamed from: b */
    private static String f3292b = "";

    /* JADX INFO: renamed from: c */
    private static String f3293c = "";

    /* JADX INFO: renamed from: d */
    private static String f3294d = "";

    /* JADX INFO: renamed from: e */
    private static int f3295e = -1;

    /* JADX INFO: renamed from: f */
    private static String f3296f = null;

    /* JADX INFO: renamed from: g */
    private static String f3297g = "0123456789ABCDEF";

    /* JADX INFO: renamed from: a */
    private static char m2251a(int i) {
        int i2 = i & 15;
        return (char) (i2 < 10 ? i2 + 48 : (i2 - 10) + 97);
    }

    /* JADX INFO: renamed from: a */
    public static Bundle m2252a(String str) {
        Bundle bundle = new Bundle();
        if (str == null) {
            return bundle;
        }
        try {
            for (String str2 : str.split("&")) {
                String[] strArrSplit = str2.split("=");
                if (strArrSplit.length == 2) {
                    bundle.putString(URLDecoder.decode(strArrSplit[0]), URLDecoder.decode(strArrSplit[1]));
                }
            }
            return bundle;
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: renamed from: a */
    public static JSONObject m2258a(JSONObject jSONObject, String str) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        if (str != null) {
            for (String str2 : str.split("&")) {
                String[] strArrSplit = str2.split("=");
                if (strArrSplit.length == 2) {
                    try {
                        try {
                            strArrSplit[0] = URLDecoder.decode(strArrSplit[0]);
                            strArrSplit[1] = URLDecoder.decode(strArrSplit[1]);
                        } catch (Exception unused) {
                        }
                        jSONObject.put(strArrSplit[0], strArrSplit[1]);
                    } catch (JSONException e) {
                        C2061f.m2135e("openSDK_LOG.Util", "decodeUrlToJson has exception: " + e.getMessage());
                    }
                }
            }
        }
        return jSONObject;
    }

    /* JADX INFO: renamed from: b */
    public static Bundle m2262b(String str) {
        try {
            URL url = new URL(str.replace("auth://", "http://"));
            Bundle bundleM2252a = m2252a(url.getQuery());
            bundleM2252a.putAll(m2252a(url.getRef()));
            return bundleM2252a;
        } catch (MalformedURLException unused) {
            return new Bundle();
        }
    }

    /* JADX INFO: renamed from: c */
    public static JSONObject m2267c(String str) {
        try {
            URL url = new URL(str.replace("auth://", "http://"));
            JSONObject jSONObjectM2258a = m2258a((JSONObject) null, url.getQuery());
            m2258a(jSONObjectM2258a, url.getRef());
            return jSONObjectM2258a;
        } catch (MalformedURLException unused) {
            return new JSONObject();
        }
    }

    /* JADX INFO: renamed from: com.tencent.open.utils.i$a */
    /* JADX INFO: compiled from: ProGuard */
    public static class a {

        /* JADX INFO: renamed from: a */
        public String f3298a;

        /* JADX INFO: renamed from: b */
        public long f3299b;

        /* JADX INFO: renamed from: c */
        public long f3300c;

        public a(String str, int i) {
            this.f3298a = str;
            this.f3299b = i;
            if (str != null) {
                this.f3300c = str.length();
            }
        }
    }

    /* JADX INFO: renamed from: d */
    public static JSONObject m2271d(String str) throws JSONException {
        if (str.equals(Bugly.SDK_IS_DEV)) {
            str = "{value : false}";
        }
        if (str.equals("true")) {
            str = "{value : true}";
        }
        if (str.contains("allback(")) {
            str = str.replaceFirst("[\\s\\S]*allback\\(([\\s\\S]*)\\);[^\\)]*\\z", "$1").trim();
        }
        if (str.contains("online[0]=")) {
            str = "{online:" + str.charAt(str.length() - 2) + "}";
        }
        return new JSONObject(str);
    }

    /* JADX INFO: renamed from: a */
    public static String m2255a() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces != null && networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddressNextElement = inetAddresses.nextElement();
                    if (!inetAddressNextElement.isLoopbackAddress()) {
                        return inetAddressNextElement.getHostAddress().toString();
                    }
                }
            }
            return "";
        } catch (SocketException e) {
            C2061f.m2128a("openSDK_LOG.Util", "getUserIp SocketException ", e);
            return "";
        }
    }

    /* JADX INFO: renamed from: e */
    public static boolean m2274e(String str) {
        return str == null || str.length() == 0;
    }

    /* JADX INFO: renamed from: f */
    private static boolean m2276f(Context context) {
        Signature[] signatureArr;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.tencent.mtt", 64);
            String str = packageInfo.versionName;
            if (C2087g.m2235a(str, "4.3") >= 0 && !str.startsWith("4.4") && (signatureArr = packageInfo.signatures) != null) {
                try {
                    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                    messageDigest.update(signatureArr[0].toByteArray());
                    String strM2257a = m2257a(messageDigest.digest());
                    messageDigest.reset();
                    if (strM2257a.equals("d8391a394d4a179e6fe7bdb8a301258b")) {
                        return true;
                    }
                } catch (NoSuchAlgorithmException e) {
                    C2061f.m2135e("openSDK_LOG.Util", "isQQBrowerAvailable has exception: " + e.getMessage());
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return false;
    }

    /* JADX INFO: renamed from: a */
    public static boolean m2261a(Context context, String str) {
        boolean zM2276f;
        try {
            zM2276f = m2276f(context);
            try {
                if (zM2276f) {
                    m2259a(context, "com.tencent.mtt", "com.tencent.mtt.MainActivity", str);
                } else {
                    m2259a(context, "com.android.browser", "com.android.browser.BrowserActivity", str);
                }
                return true;
            } catch (Exception unused) {
                if (zM2276f) {
                    try {
                        try {
                            try {
                                m2259a(context, "com.android.browser", "com.android.browser.BrowserActivity", str);
                                return true;
                            } catch (Exception unused2) {
                                m2259a(context, "com.google.android.browser", "com.android.browser.BrowserActivity", str);
                                return true;
                            }
                        } catch (Exception unused3) {
                            m2259a(context, "com.android.chrome", "com.google.android.apps.chrome.Main", str);
                            return true;
                        }
                    } catch (Exception unused4) {
                        return false;
                    }
                }
                try {
                    try {
                        m2259a(context, "com.google.android.browser", "com.android.browser.BrowserActivity", str);
                        return true;
                    } catch (Exception unused5) {
                        m2259a(context, "com.android.chrome", "com.google.android.apps.chrome.Main", str);
                        return true;
                    }
                } catch (Exception unused6) {
                    return false;
                }
            }
        } catch (Exception unused7) {
            zM2276f = false;
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m2259a(Context context, String str, String str2, String str3) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(str, str2));
        intent.setAction("android.intent.action.VIEW");
        intent.addFlags(BasicMeasure.EXACTLY);
        intent.addFlags(268435456);
        intent.setData(Uri.parse(str3));
        context.startActivity(intent);
    }

    /* JADX INFO: renamed from: a */
    public static boolean m2260a(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (m2273e(context)) {
            try {
                packageManager.getPackageInfo(Constants.PACKAGE_QQ_PAD, 0);
                return true;
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        try {
            return C2087g.m2235a(packageManager.getPackageInfo("com.tencent.mobileqq", 0).versionName, "4.1") >= 0;
        } catch (PackageManager.NameNotFoundException e) {
            C2061f.m2131b("openSDK_LOG.Util", "NameNotFoundException", e);
            return false;
        }
    }

    /* JADX INFO: renamed from: f */
    public static String m2275f(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(m2280i(str));
            byte[] bArrDigest = messageDigest.digest();
            if (bArrDigest == null) {
                return str;
            }
            StringBuilder sb = new StringBuilder();
            for (byte b : bArrDigest) {
                sb.append(m2251a(b >>> 4));
                sb.append(m2251a(b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            C2061f.m2135e("openSDK_LOG.Util", "encrypt has exception: " + e.getMessage());
            return str;
        }
    }

    /* JADX INFO: renamed from: b */
    public static boolean m2265b() {
        return (Environment.getExternalStorageState().equals("mounted") ? Environment.getExternalStorageDirectory() : null) != null;
    }

    /* JADX INFO: renamed from: a */
    public static String m2257a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            String string = Integer.toString(b & UByte.MAX_VALUE, 16);
            if (string.length() == 1) {
                string = "0" + string;
            }
            sb.append(string);
        }
        return sb.toString();
    }

    /* JADX INFO: renamed from: b */
    public static final String m2263b(Context context) {
        CharSequence applicationLabel;
        if (context == null || (applicationLabel = context.getPackageManager().getApplicationLabel(context.getApplicationInfo())) == null) {
            return null;
        }
        return applicationLabel.toString();
    }

    /* JADX INFO: renamed from: g */
    public static final boolean m2278g(String str) {
        if (str == null) {
            return false;
        }
        return str.startsWith("http://") || str.startsWith("https://");
    }

    /* JADX INFO: renamed from: h */
    public static boolean m2279h(String str) {
        return str != null && new File(str).exists();
    }

    /* JADX INFO: renamed from: a */
    public static final String m2256a(String str, int i, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = Key.STRING_CHARSET_NAME;
        }
        try {
            if (str.getBytes(str2).length <= i) {
                return str;
            }
            int i2 = 0;
            int length = 0;
            while (i2 < str.length()) {
                int i3 = i2 + 1;
                length += str.substring(i2, i3).getBytes(str2).length;
                if (length > i) {
                    String strSubstring = str.substring(0, i2);
                    if (TextUtils.isEmpty(str3)) {
                        return strSubstring;
                    }
                    return strSubstring + str3;
                }
                i2 = i3;
            }
            return str;
        } catch (Exception e) {
            System.out.println("StructMsg sSubString error : " + e.getMessage());
            return str;
        }
    }

    /* JADX INFO: renamed from: c */
    public static boolean m2268c(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return true;
        }
        NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
        if (allNetworkInfo != null) {
            for (NetworkInfo networkInfo : allNetworkInfo) {
                if (networkInfo.isConnectedOrConnecting()) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: a */
    public static Bundle m2254a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12) {
        Bundle bundle = new Bundle();
        bundle.putString("openid", str);
        bundle.putString("report_type", str2);
        bundle.putString("act_type", str3);
        bundle.putString("via", str4);
        bundle.putString("app_id", str5);
        bundle.putString("result", str6);
        bundle.putString("type", str7);
        bundle.putString("login_status", str8);
        bundle.putString("need_user_auth", str9);
        bundle.putString("to_uin", str10);
        bundle.putString("call_source", str11);
        bundle.putString("to_type", str12);
        return bundle;
    }

    /* JADX INFO: renamed from: a */
    public static Bundle m2253a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.PARAM_PLATFORM, "1");
        bundle.putString("result", str);
        bundle.putString("code", str2);
        bundle.putString("tmcost", str3);
        bundle.putString(TopicKey.SAMPLE, str4);
        bundle.putString("cmd", str5);
        bundle.putString("uin", str6);
        bundle.putString("appid", str7);
        bundle.putString("share_type", str8);
        bundle.putString("detail", str9);
        bundle.putString("os_ver", Build.VERSION.RELEASE);
        bundle.putString("network", C2066a.m2150a(C2084d.m2215a()));
        bundle.putString("apn", C2066a.m2151b(C2084d.m2215a()));
        bundle.putString("model_name", Build.MODEL);
        bundle.putString("sdk_ver", Constants.SDK_VERSION);
        bundle.putString("packagename", C2084d.m2217b());
        bundle.putString("app_ver", m2270d(C2084d.m2215a(), C2084d.m2217b()));
        return bundle;
    }

    /* JADX INFO: renamed from: d */
    public static String m2269d(Context context) {
        Location lastKnownLocation;
        if (context == null) {
            return "";
        }
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService("location");
            Criteria criteria = new Criteria();
            criteria.setCostAllowed(false);
            criteria.setAccuracy(2);
            String bestProvider = locationManager.getBestProvider(criteria, true);
            if (bestProvider == null || (lastKnownLocation = locationManager.getLastKnownLocation(bestProvider)) == null) {
                return "";
            }
            String str = lastKnownLocation.getLatitude() + "*" + lastKnownLocation.getLongitude();
            f3296f = str;
            return str;
        } catch (Exception e) {
            C2061f.m2131b("openSDK_LOG.Util", "getLocation>>>", e);
        }
        return "";
    }

    /* JADX INFO: renamed from: b */
    public static void m2264b(Context context, String str) {
        if (context == null) {
            return;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
            String str2 = packageInfo.versionName;
            f3292b = str2;
            f3291a = str2.substring(0, str2.lastIndexOf(46));
            f3294d = f3292b.substring(f3292b.lastIndexOf(46) + 1, f3292b.length());
            f3295e = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            C2061f.m2135e("openSDK_LOG.Util", "getPackageInfo has exception: " + e.getMessage());
        } catch (Exception e2) {
            C2061f.m2135e("openSDK_LOG.Util", "getPackageInfo has exception: " + e2.getMessage());
        }
    }

    /* JADX INFO: renamed from: c */
    public static String m2266c(Context context, String str) {
        if (context == null) {
            return "";
        }
        m2264b(context, str);
        return f3292b;
    }

    /* JADX INFO: renamed from: d */
    public static String m2270d(Context context, String str) {
        if (context == null) {
            return "";
        }
        m2264b(context, str);
        return f3291a;
    }

    /* JADX INFO: renamed from: e */
    public static String m2272e(Context context, String str) {
        if (context == null) {
            return "";
        }
        String strM2270d = m2270d(context, str);
        f3293c = strM2270d;
        return strM2270d;
    }

    /* JADX INFO: renamed from: i */
    public static byte[] m2280i(String str) {
        try {
            return str.getBytes(Key.STRING_CHARSET_NAME);
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    /* JADX INFO: renamed from: e */
    public static boolean m2273e(Context context) {
        double dSqrt;
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            dSqrt = Math.sqrt(Math.pow(displayMetrics.widthPixels / displayMetrics.xdpi, 2.0d) + Math.pow(displayMetrics.heightPixels / displayMetrics.ydpi, 2.0d));
        } catch (Throwable unused) {
            dSqrt = 0.0d;
        }
        return dSqrt > 6.5d;
    }

    /* JADX INFO: renamed from: f */
    public static boolean m2277f(Context context, String str) {
        if (m2273e(context)) {
            if (C2087g.m2239a(context, Constants.PACKAGE_QQ_PAD) == null && C2087g.m2245c(context, str) < 0) {
                return true;
            }
        } else if (C2087g.m2245c(context, str) < 0) {
            return true;
        }
        return false;
    }
}
