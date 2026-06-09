package com.baidu.mapapi.navi;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import com.baidu.mapapi.VersionInfo;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapapi.utils.C0708a;
import com.baidu.mapapi.utils.OpenClientUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class BaiduMapNavigation {

    /* JADX INFO: renamed from: a */
    private static boolean f718a = true;

    /* JADX INFO: renamed from: a */
    private static String m454a(Context context) {
        PackageManager packageManager;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = context.getApplicationContext().getPackageManager();
            try {
                applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException unused) {
            }
        } catch (PackageManager.NameNotFoundException unused2) {
            packageManager = null;
        }
        return (String) packageManager.getApplicationLabel(applicationInfo);
    }

    /* JADX INFO: renamed from: a */
    private static void m455a(NaviParaOption naviParaOption, Context context) throws IllegalNaviArgumentException {
        if (naviParaOption == null || context == null) {
            throw new IllegalNaviArgumentException("para or context can not be null.");
        }
        if (naviParaOption.f719a == null || naviParaOption.f721c == null) {
            throw new IllegalNaviArgumentException("you must set start and end point.");
        }
        GeoPoint geoPointLl2mc = CoordUtil.ll2mc(naviParaOption.f719a);
        GeoPoint geoPointLl2mc2 = CoordUtil.ll2mc(naviParaOption.f721c);
        StringBuilder sb = new StringBuilder();
        sb.append("http://app.navi.baidu.com/mobile/#navi/naving/");
        sb.append("&sy=0");
        sb.append("&endp=");
        sb.append("&start=");
        sb.append("&startwd=");
        sb.append("&endwd=");
        sb.append("&fromprod=map_sdk");
        sb.append("&app_version=");
        sb.append(VersionInfo.VERSION_INFO);
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.put("type", "1");
            if (naviParaOption.f720b == null || naviParaOption.f720b.equals("")) {
                jSONObject.put("keyword", "");
            } else {
                jSONObject.put("keyword", naviParaOption.f720b);
            }
            jSONObject.put("xy", String.valueOf(geoPointLl2mc.getLongitudeE6()) + "," + String.valueOf(geoPointLl2mc.getLatitudeE6()));
            jSONArray.put(jSONObject);
            jSONObject2.put("type", "1");
            if (naviParaOption.f722d == null || naviParaOption.f722d.equals("")) {
                jSONObject.put("keyword", "");
            } else {
                jSONObject.put("keyword", naviParaOption.f722d);
            }
            jSONObject2.put("xy", String.valueOf(geoPointLl2mc2.getLongitudeE6()) + "," + String.valueOf(geoPointLl2mc2.getLatitudeE6()));
            jSONArray.put(jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (jSONArray.length() > 0) {
            sb.append("&positions=");
            sb.append(jSONArray.toString());
        }
        sb.append("&ctrl_type=");
        sb.append("&mrsl=");
        sb.append("/vt=map&state=entry");
        Uri uri = Uri.parse(sb.toString());
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setFlags(268435456);
        intent.setData(uri);
        context.startActivity(intent);
    }

    public static void finish(Context context) {
        if (context != null) {
            C0708a.m464a(context);
        }
    }

    public static boolean openBaiduMapBikeNavi(NaviParaOption naviParaOption, Context context) {
        String str;
        if (naviParaOption == null || context == null) {
            throw new IllegalNaviArgumentException("para or context can not be null.");
        }
        if (naviParaOption.f721c == null || naviParaOption.f719a == null) {
            throw new IllegalNaviArgumentException("start point or end point can not be null.");
        }
        int baiduMapVersion = OpenClientUtil.getBaiduMapVersion(context);
        if (baiduMapVersion == 0) {
            str = "BaiduMap app is not installed.";
        } else {
            if (baiduMapVersion >= 869) {
                return C0708a.m468a(naviParaOption, context, 8);
            }
            str = "Baidumap app version is too lowl.Version is greater than 8.6.6";
        }
        Log.e("baidumapsdk", str);
        return false;
    }

    public static boolean openBaiduMapNavi(NaviParaOption naviParaOption, Context context) {
        if (naviParaOption == null || context == null) {
            throw new IllegalNaviArgumentException("para or context can not be null.");
        }
        if (naviParaOption.f721c == null || naviParaOption.f719a == null) {
            throw new IllegalNaviArgumentException("start point or end point can not be null.");
        }
        int baiduMapVersion = OpenClientUtil.getBaiduMapVersion(context);
        if (baiduMapVersion == 0) {
            Log.e("baidumapsdk", "BaiduMap app is not installed.");
            if (!f718a) {
                throw new BaiduMapAppNotSupportNaviException("BaiduMap app is not installed.");
            }
            m455a(naviParaOption, context);
            return true;
        }
        if (baiduMapVersion >= 830) {
            return C0708a.m468a(naviParaOption, context, 5);
        }
        Log.e("baidumapsdk", "Baidumap app version is too lowl.Version is greater than 8.2");
        if (!f718a) {
            throw new BaiduMapAppNotSupportNaviException("Baidumap app version is too lowl.Version is greater than 8.2");
        }
        m455a(naviParaOption, context);
        return true;
    }

    public static boolean openBaiduMapWalkNavi(NaviParaOption naviParaOption, Context context) {
        String str;
        if (naviParaOption == null || context == null) {
            throw new IllegalNaviArgumentException("para or context can not be null.");
        }
        if (naviParaOption.f721c == null || naviParaOption.f719a == null) {
            throw new IllegalNaviArgumentException("start point or end point can not be null.");
        }
        int baiduMapVersion = OpenClientUtil.getBaiduMapVersion(context);
        if (baiduMapVersion == 0) {
            str = "BaiduMap app is not installed.";
        } else {
            if (baiduMapVersion >= 869) {
                return C0708a.m468a(naviParaOption, context, 7);
            }
            str = "Baidumap app version is too lowl.Version is greater than 8.6.6";
        }
        Log.e("baidumapsdk", str);
        return false;
    }

    @Deprecated
    public static void openWebBaiduMapNavi(NaviParaOption naviParaOption, Context context) throws IllegalNaviArgumentException {
        Uri uri;
        Intent intent;
        if (naviParaOption == null || context == null) {
            throw new IllegalNaviArgumentException("para or context can not be null.");
        }
        if (naviParaOption.f719a != null && naviParaOption.f721c != null) {
            GeoPoint geoPointLl2mc = CoordUtil.ll2mc(naviParaOption.f719a);
            GeoPoint geoPointLl2mc2 = CoordUtil.ll2mc(naviParaOption.f721c);
            uri = Uri.parse("http://daohang.map.baidu.com/mobile/#navi/naving/start=" + geoPointLl2mc.getLongitudeE6() + "," + geoPointLl2mc.getLatitudeE6() + "&endp=" + geoPointLl2mc2.getLongitudeE6() + "," + geoPointLl2mc2.getLatitudeE6() + "&fromprod=" + m454a(context) + "/vt=map&state=entry");
            intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setFlags(268435456);
        } else {
            if (naviParaOption.f720b == null || naviParaOption.f720b.equals("") || naviParaOption.f722d == null || naviParaOption.f722d.equals("")) {
                throw new IllegalNaviArgumentException("you must set start and end point or set the start and end name.");
            }
            uri = Uri.parse("http://daohang.map.baidu.com/mobile/#search/search/qt=nav&sn=2$$$$$$" + naviParaOption.f720b + "$$$$$$&en=2$$$$$$" + naviParaOption.f722d + "$$$$$$&fromprod=" + m454a(context));
            intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
        }
        intent.setData(uri);
        context.startActivity(intent);
    }

    public static void setSupportWebNavi(boolean z) {
        f718a = z;
    }
}
