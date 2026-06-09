package com.baidu.mapapi.common;

import android.content.Context;
import com.baidu.platform.comapi.util.C0778d;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class EnvironmentUtilities {

    /* JADX INFO: renamed from: a */
    static String f194a;

    /* JADX INFO: renamed from: b */
    static String f195b;

    /* JADX INFO: renamed from: c */
    static String f196c;

    /* JADX INFO: renamed from: d */
    static int f197d;

    /* JADX INFO: renamed from: e */
    static int f198e;

    /* JADX INFO: renamed from: f */
    static int f199f;

    /* JADX INFO: renamed from: g */
    private static C0778d f200g;

    public static String getAppCachePath() {
        return f195b;
    }

    public static String getAppSDCardPath() {
        String str = f194a + "/BaiduMapSDKNew";
        if (str.length() != 0) {
            File file = new File(str);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        return str;
    }

    public static String getAppSecondCachePath() {
        return f196c;
    }

    public static int getDomTmpStgMax() {
        return f198e;
    }

    public static int getItsTmpStgMax() {
        return f199f;
    }

    public static int getMapTmpStgMax() {
        return f197d;
    }

    public static String getSDCardPath() {
        return f194a;
    }

    public static void initAppDirectory(Context context) throws Throwable {
        String strM786c;
        if (f200g == null) {
            C0778d c0778dM788a = C0778d.m788a();
            f200g = c0778dM788a;
            c0778dM788a.m792a(context);
        }
        String str = f194a;
        if (str == null || str.length() <= 0) {
            f194a = f200g.m794b().m784a();
            strM786c = f200g.m794b().m786c();
        } else {
            strM786c = f194a + File.separator + "BaiduMapSDKNew" + File.separator + "cache";
        }
        f195b = strM786c;
        f196c = f200g.m794b().m787d();
        f197d = 20971520;
        f198e = 52428800;
        f199f = 5242880;
    }

    public static void setSDCardPath(String str) {
        f194a = str;
    }
}
