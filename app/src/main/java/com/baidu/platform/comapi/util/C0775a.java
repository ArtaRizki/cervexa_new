package com.baidu.platform.comapi.util;

import android.content.Context;
import android.content.pm.PackageManager;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/* JADX INFO: renamed from: com.baidu.platform.comapi.util.a */
/* JADX INFO: loaded from: classes.dex */
public class C0775a {

    /* JADX INFO: renamed from: com.baidu.platform.comapi.util.a$a */
    static class a {
        /* JADX INFO: renamed from: a */
        public static String m773a(byte[] bArr) {
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
    public static String m769a(Context context) {
        String packageName = context.getPackageName();
        return m770a(context, packageName) + ";" + packageName;
    }

    /* JADX INFO: renamed from: a */
    private static String m770a(Context context, String str) {
        String strM771a;
        try {
            strM771a = m771a((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(context.getPackageManager().getPackageInfo(str, 64).signatures[0].toByteArray())));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            strM771a = "";
        } catch (CertificateException e2) {
            e2.printStackTrace();
            strM771a = "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < strM771a.length(); i++) {
            stringBuffer.append(strM771a.charAt(i));
            if (i > 0 && i % 2 == 1 && i < strM771a.length() - 1) {
                stringBuffer.append(":");
            }
        }
        return stringBuffer.toString();
    }

    /* JADX INFO: renamed from: a */
    static String m771a(X509Certificate x509Certificate) {
        try {
            return a.m773a(m772a(x509Certificate.getEncoded()));
        } catch (CertificateEncodingException unused) {
            return null;
        }
    }

    /* JADX INFO: renamed from: a */
    static byte[] m772a(byte[] bArr) {
        try {
            return MessageDigest.getInstance("SHA1").digest(bArr);
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }
}
