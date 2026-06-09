package com.baidu.lbsapi.auth;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Locale;

/* JADX INFO: renamed from: com.baidu.lbsapi.auth.b */
/* JADX INFO: loaded from: classes.dex */
class C0646b {

    /* JADX INFO: renamed from: com.baidu.lbsapi.auth.b$a */
    static class a {
        /* JADX INFO: renamed from: a */
        public static String m143a(byte[] bArr) {
            char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
            StringBuilder sb = new StringBuilder(bArr.length * 2);
            for (int i = 0; i < bArr.length; i++) {
                sb.append(cArr[(bArr[i] & 240) >> 4]);
                sb.append(cArr[bArr[i] & 15]);
            }
            return sb.toString();
        }
    }

    /* JADX INFO: renamed from: a */
    static String m133a() {
        return Locale.getDefault().getLanguage();
    }

    /* JADX INFO: renamed from: a */
    protected static String m134a(Context context) {
        String packageName = context.getPackageName();
        return m135a(context, packageName) + ";" + packageName;
    }

    /* JADX INFO: renamed from: a */
    private static String m135a(Context context, String str) {
        String strM136a;
        try {
            strM136a = m136a((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(context.getPackageManager().getPackageInfo(str, 64).signatures[0].toByteArray())));
        } catch (PackageManager.NameNotFoundException | CertificateException unused) {
            strM136a = "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < strM136a.length(); i++) {
            stringBuffer.append(strM136a.charAt(i));
            if (i > 0 && i % 2 == 1 && i < strM136a.length() - 1) {
                stringBuffer.append(":");
            }
        }
        return stringBuffer.toString();
    }

    /* JADX INFO: renamed from: a */
    static String m136a(X509Certificate x509Certificate) {
        try {
            return a.m143a(m137a(x509Certificate.getEncoded()));
        } catch (CertificateEncodingException unused) {
            return null;
        }
    }

    /* JADX INFO: renamed from: a */
    static byte[] m137a(byte[] bArr) {
        try {
            return MessageDigest.getInstance("SHA1").digest(bArr);
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }

    /* JADX INFO: renamed from: b */
    protected static String[] m138b(Context context) {
        String packageName = context.getPackageName();
        String[] strArrM139b = m139b(context, packageName);
        if (strArrM139b == null || strArrM139b.length <= 0) {
            return null;
        }
        int length = strArrM139b.length;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = strArrM139b[i] + ";" + packageName;
            if (C0645a.f120a) {
                C0645a.m130a("mcode" + strArr[i]);
            }
        }
        return strArr;
    }

    /* JADX INFO: renamed from: b */
    private static String[] m139b(Context context, String str) {
        String[] strArr;
        Signature[] signatureArr;
        String[] strArr2 = null;
        try {
            signatureArr = context.getPackageManager().getPackageInfo(str, 64).signatures;
        } catch (PackageManager.NameNotFoundException | CertificateException unused) {
        }
        if (signatureArr == null || signatureArr.length <= 0) {
            strArr = null;
        } else {
            strArr = new String[signatureArr.length];
            for (int i = 0; i < signatureArr.length; i++) {
                try {
                    strArr[i] = m136a((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(signatureArr[i].toByteArray())));
                } catch (PackageManager.NameNotFoundException | CertificateException unused2) {
                }
            }
        }
        if (strArr != null && strArr.length > 0) {
            strArr2 = new String[strArr.length];
            for (int i2 = 0; i2 < strArr.length; i2++) {
                StringBuffer stringBuffer = new StringBuffer();
                for (int i3 = 0; i3 < strArr[i2].length(); i3++) {
                    stringBuffer.append(strArr[i2].charAt(i3));
                    if (i3 > 0 && i3 % 2 == 1 && i3 < strArr[i2].length() - 1) {
                        stringBuffer.append(":");
                    }
                }
                strArr2[i2] = stringBuffer.toString();
            }
        }
        return strArr2;
    }

    /* JADX INFO: renamed from: c */
    static String m140c(Context context) {
        String string = context.getSharedPreferences("mac", 0).getString("macaddr", null);
        if (string == null) {
            String strM142d = m142d(context);
            if (strM142d != null) {
                string = Base64.encodeToString(strM142d.getBytes(), 0);
                if (!TextUtils.isEmpty(string)) {
                    context.getSharedPreferences("mac", 0).edit().putString("macaddr", string).commit();
                }
            } else {
                string = "";
            }
        }
        if (C0645a.f120a) {
            C0645a.m130a("getMacID mac_adress: " + string);
        }
        return string;
    }

    /* JADX INFO: renamed from: c */
    private static boolean m141c(Context context, String str) {
        boolean z = context.checkCallingOrSelfPermission(str) != -1;
        if (C0645a.f120a) {
            C0645a.m130a("hasPermission " + z + " | " + str);
        }
        return z;
    }

    /* JADX INFO: renamed from: d */
    static String m142d(Context context) {
        String str;
        String macAddress = null;
        try {
            if (m141c(context, "android.permission.ACCESS_WIFI_STATE")) {
                WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
                macAddress = connectionInfo.getMacAddress();
                if (!TextUtils.isEmpty(macAddress)) {
                    Base64.encode(macAddress.getBytes(), 0);
                }
                if (C0645a.f120a) {
                    str = String.format("ssid=%s mac=%s", connectionInfo.getSSID(), connectionInfo.getMacAddress());
                    C0645a.m130a(str);
                }
            } else if (C0645a.f120a) {
                str = "You need the android.Manifest.permission.ACCESS_WIFI_STATE permission. Open AndroidManifest.xml and just before the final </manifest> tag add:android.permission.ACCESS_WIFI_STATE";
                C0645a.m130a(str);
            }
        } catch (Exception e) {
            if (C0645a.f120a) {
                C0645a.m130a(e.toString());
            }
        }
        return macAddress;
    }
}
