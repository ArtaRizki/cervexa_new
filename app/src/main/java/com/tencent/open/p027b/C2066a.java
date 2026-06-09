package com.tencent.open.p027b;

import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import com.tencent.open.p026a.C2061f;

/* JADX INFO: renamed from: com.tencent.open.b.a */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class C2066a {

    /* JADX INFO: renamed from: a */
    protected static final Uri f3186a = Uri.parse("content://telephony/carriers/preferapn");

    /* JADX INFO: renamed from: a */
    public static String m2150a(Context context) {
        int iM2153d = m2153d(context);
        if (iM2153d == 2) {
            return "wifi";
        }
        if (iM2153d == 1) {
            return "cmwap";
        }
        if (iM2153d == 4) {
            return "cmnet";
        }
        if (iM2153d == 16) {
            return "uniwap";
        }
        if (iM2153d == 8) {
            return "uninet";
        }
        if (iM2153d == 64) {
            return "wap";
        }
        if (iM2153d == 32) {
            return "net";
        }
        if (iM2153d == 512) {
            return "ctwap";
        }
        if (iM2153d == 256) {
            return "ctnet";
        }
        if (iM2153d == 2048) {
            return "3gnet";
        }
        if (iM2153d == 1024) {
            return "3gwap";
        }
        String strM2151b = m2151b(context);
        return (strM2151b == null || strM2151b.length() == 0) ? "none" : strM2151b;
    }

    /* JADX INFO: renamed from: b */
    public static String m2151b(Context context) {
        try {
            Cursor cursorQuery = context.getContentResolver().query(f3186a, null, null, null, null);
            if (cursorQuery == null) {
                return null;
            }
            cursorQuery.moveToFirst();
            if (cursorQuery.isAfterLast()) {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return null;
            }
            String string = cursorQuery.getString(cursorQuery.getColumnIndex("apn"));
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return string;
        } catch (SecurityException e) {
            C2061f.m2135e("openSDK_LOG.APNUtil", "getApn has exception: " + e.getMessage());
            return "";
        } catch (Exception e2) {
            C2061f.m2135e("openSDK_LOG.APNUtil", "getApn has exception: " + e2.getMessage());
            return "";
        }
    }

    /* JADX INFO: renamed from: c */
    public static String m2152c(Context context) {
        try {
            Cursor cursorQuery = context.getContentResolver().query(f3186a, null, null, null, null);
            if (cursorQuery == null) {
                return null;
            }
            cursorQuery.moveToFirst();
            if (cursorQuery.isAfterLast()) {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return null;
            }
            String string = cursorQuery.getString(cursorQuery.getColumnIndex("proxy"));
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return string;
        } catch (SecurityException e) {
            C2061f.m2135e("openSDK_LOG.APNUtil", "getApnProxy has exception: " + e.getMessage());
            return "";
        }
    }

    /* JADX INFO: renamed from: d */
    public static int m2153d(Context context) {
        NetworkInfo activeNetworkInfo;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
                return 128;
            }
            if (activeNetworkInfo.getTypeName().toUpperCase().equals("WIFI")) {
                return 2;
            }
            String lowerCase = activeNetworkInfo.getExtraInfo().toLowerCase();
            if (lowerCase.startsWith("cmwap")) {
                return 1;
            }
            if (!lowerCase.startsWith("cmnet") && !lowerCase.startsWith("epc.tmobile.com")) {
                if (lowerCase.startsWith("uniwap")) {
                    return 16;
                }
                if (lowerCase.startsWith("uninet")) {
                    return 8;
                }
                if (lowerCase.startsWith("wap")) {
                    return 64;
                }
                if (lowerCase.startsWith("net")) {
                    return 32;
                }
                if (lowerCase.startsWith("ctwap")) {
                    return 512;
                }
                if (lowerCase.startsWith("ctnet")) {
                    return 256;
                }
                if (lowerCase.startsWith("3gwap")) {
                    return 1024;
                }
                if (lowerCase.startsWith("3gnet")) {
                    return 2048;
                }
                if (lowerCase.startsWith("#777")) {
                    String strM2152c = m2152c(context);
                    if (strM2152c != null) {
                        if (strM2152c.length() > 0) {
                            return 512;
                        }
                    }
                    return 256;
                }
            }
            return 4;
        } catch (Exception e) {
            C2061f.m2135e("openSDK_LOG.APNUtil", "getMProxyType has exception: " + e.getMessage());
        }
        return 128;
    }

    /* JADX INFO: renamed from: e */
    public static String m2154e(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        return (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) ? "MOBILE" : activeNetworkInfo.getTypeName();
    }
}
