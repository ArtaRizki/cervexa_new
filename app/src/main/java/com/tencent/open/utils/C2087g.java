package com.tencent.open.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import com.tencent.connect.common.Constants;
import com.tencent.open.p026a.C2061f;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;

/* JADX INFO: renamed from: com.tencent.open.utils.g */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class C2087g {
    /* JADX INFO: renamed from: a */
    public static String m2237a(int i) {
        if (i == 10103) {
            return "shareToQQ";
        }
        if (i == 10104) {
            return "shareToQzone";
        }
        if (i == 10105) {
            return "addToQQFavorites";
        }
        if (i == 10106) {
            return "sendToMyComputer";
        }
        if (i == 10107) {
            return "shareToTroopBar";
        }
        if (i == 11101) {
            return "action_login";
        }
        if (i == 10100) {
            return "action_request";
        }
        return null;
    }

    /* JADX INFO: renamed from: a */
    public static String m2239a(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    /* JADX INFO: renamed from: a */
    public static int m2235a(String str, String str2) {
        if (str == null && str2 == null) {
            return 0;
        }
        if (str != null && str2 == null) {
            return 1;
        }
        if (str == null && str2 != null) {
            return -1;
        }
        String[] strArrSplit = str.split("\\.");
        String[] strArrSplit2 = str2.split("\\.");
        int i = 0;
        while (i < strArrSplit.length && i < strArrSplit2.length) {
            try {
                int i2 = Integer.parseInt(strArrSplit[i]);
                int i3 = Integer.parseInt(strArrSplit2[i]);
                if (i2 < i3) {
                    return -1;
                }
                if (i2 > i3) {
                    return 1;
                }
                i++;
            } catch (NumberFormatException unused) {
                return str.compareTo(str2);
            }
        }
        if (strArrSplit.length > i) {
            return 1;
        }
        return strArrSplit2.length > i ? -1 : 0;
    }

    /* JADX INFO: renamed from: a */
    public static boolean m2241a(Context context, String str, String str2) {
        C2061f.m2127a("openSDK_LOG.SystemUtils", "OpenUi, validateAppSignatureForPackage");
        try {
            for (Signature signature : context.getPackageManager().getPackageInfo(str, 64).signatures) {
                if (C2089i.m2275f(signature.toCharsString()).equals(str2)) {
                    return true;
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return false;
    }

    /* JADX INFO: renamed from: b */
    public static String m2243b(Context context, String str) {
        String strM2257a = "";
        C2061f.m2127a("openSDK_LOG.SystemUtils", "OpenUi, getSignValidString");
        try {
            String packageName = context.getPackageName();
            Signature[] signatureArr = context.getPackageManager().getPackageInfo(packageName, 64).signatures;
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(signatureArr[0].toByteArray());
            String strM2257a2 = C2089i.m2257a(messageDigest.digest());
            messageDigest.reset();
            C2061f.m2127a("openSDK_LOG.SystemUtils", "-->sign: " + strM2257a2);
            messageDigest.update(C2089i.m2280i(packageName + "_" + strM2257a2 + "_" + str + ""));
            strM2257a = C2089i.m2257a(messageDigest.digest());
            messageDigest.reset();
            StringBuilder sb = new StringBuilder();
            sb.append("-->signEncryped: ");
            sb.append(strM2257a);
            C2061f.m2127a("openSDK_LOG.SystemUtils", sb.toString());
            return strM2257a;
        } catch (Exception e) {
            e.printStackTrace();
            C2061f.m2131b("openSDK_LOG.SystemUtils", "OpenUi, getSignValidString error", e);
            return strM2257a;
        }
    }

    /* JADX INFO: renamed from: a */
    public static boolean m2240a(Context context, Intent intent) {
        return (context == null || intent == null || context.getPackageManager().queryIntentActivities(intent, 0).size() == 0) ? false : true;
    }

    /* JADX INFO: renamed from: a */
    public static String m2238a(Context context) {
        return context.getApplicationInfo().loadLabel(context.getPackageManager()).toString();
    }

    /* JADX INFO: renamed from: c */
    public static int m2245c(Context context, String str) {
        return m2235a(m2239a(context, "com.tencent.mobileqq"), str);
    }

    /* JADX INFO: renamed from: b */
    public static boolean m2244b(Context context) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo("com.tencent.mobileqq", 0);
        } catch (PackageManager.NameNotFoundException e) {
            C2061f.m2131b("openSDK_LOG.SystemUtils", "checkMobileQQ NameNotFoundException", e);
            e.printStackTrace();
            packageInfo = null;
        }
        if (packageInfo != null) {
            String str = packageInfo.versionName;
            try {
                C2061f.m2130b("MobileQQ verson", str);
                String[] strArrSplit = str.split("\\.");
                int i = Integer.parseInt(strArrSplit[0]);
                return i > 4 || (i == 4 && Integer.parseInt(strArrSplit[1]) >= 1);
            } catch (Exception e2) {
                C2061f.m2131b("openSDK_LOG.SystemUtils", "checkMobileQQ Exception", e2);
                e2.printStackTrace();
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x00c5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00be A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:76:? A[SYNTHETIC] */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean m2242a(java.lang.String r10, java.lang.String r11, int r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 201
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.utils.C2087g.m2242a(java.lang.String, java.lang.String, int):boolean");
    }

    /* JADX INFO: renamed from: a */
    private static long m2236a(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[8192];
        long j = 0;
        while (true) {
            int i = inputStream.read(bArr, 0, 8192);
            if (i != -1) {
                outputStream.write(bArr, 0, i);
                j += (long) i;
            } else {
                C2061f.m2133c("openSDK_LOG.SystemUtils", "-->copy, copyed size is: " + j);
                return j;
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public static int m2234a(String str) {
        return "shareToQQ".equals(str) ? Constants.REQUEST_QQ_SHARE : "shareToQzone".equals(str) ? Constants.REQUEST_QZONE_SHARE : "addToQQFavorites".equals(str) ? Constants.REQUEST_QQ_FAVORITES : "sendToMyComputer".equals(str) ? Constants.REQUEST_SEND_TO_MY_COMPUTER : "shareToTroopBar".equals(str) ? Constants.REQUEST_SHARE_TO_TROOP_BAR : "action_login".equals(str) ? Constants.REQUEST_LOGIN : "action_request".equals(str) ? 10100 : -1;
    }
}
