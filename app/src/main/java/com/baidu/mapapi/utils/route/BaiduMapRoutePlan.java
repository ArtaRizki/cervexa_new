package com.baidu.mapapi.utils.route;

import android.content.Context;
import android.util.Log;
import com.baidu.mapapi.navi.IllegalNaviArgumentException;
import com.baidu.mapapi.utils.C0708a;
import com.baidu.mapapi.utils.OpenClientUtil;
import com.baidu.mapapi.utils.poi.IllegalPoiSearchArgumentException;
import com.baidu.mapapi.utils.route.RouteParaOption;

/* JADX INFO: loaded from: classes.dex */
public class BaiduMapRoutePlan {

    /* JADX INFO: renamed from: a */
    private static boolean f761a = true;

    /* JADX WARN: Removed duplicated region for block: B:20:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00df  */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void m504a(com.baidu.mapapi.utils.route.RouteParaOption r9, android.content.Context r10, int r11) {
        /*
            Method dump skipped, instruction units count: 275
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapapi.utils.route.BaiduMapRoutePlan.m504a(com.baidu.mapapi.utils.route.RouteParaOption, android.content.Context, int):void");
    }

    public static void finish(Context context) {
        if (context != null) {
            C0708a.m464a(context);
        }
    }

    public static boolean openBaiduMapDrivingRoute(RouteParaOption routeParaOption, Context context) {
        if (routeParaOption == null || context == null) {
            throw new IllegalPoiSearchArgumentException("para or context can not be null.");
        }
        if (routeParaOption.f763b == null && routeParaOption.f762a == null && routeParaOption.f765d == null && routeParaOption.f764c == null) {
            throw new IllegalNaviArgumentException("startPoint and endPoint and endName and startName not all null.");
        }
        if (routeParaOption.f764c == null && routeParaOption.f762a == null) {
            throw new IllegalNaviArgumentException("startPoint and startName not all null.");
        }
        if (routeParaOption.f765d == null && routeParaOption.f763b == null) {
            throw new IllegalNaviArgumentException("endPoint and endName not all null.");
        }
        if (((routeParaOption.f764c == null || routeParaOption.f764c.equals("")) && routeParaOption.f762a == null) || ((routeParaOption.f765d == null || routeParaOption.f765d.equals("")) && routeParaOption.f763b == null)) {
            Log.e(BaiduMapRoutePlan.class.getName(), "poi startName or endName can not be empty string while pt is null");
            return false;
        }
        if (routeParaOption.f767f == null) {
            routeParaOption.f767f = RouteParaOption.EBusStrategyType.bus_recommend_way;
        }
        int baiduMapVersion = OpenClientUtil.getBaiduMapVersion(context);
        if (baiduMapVersion == 0) {
            Log.e("baidumapsdk", "BaiduMap app is not installed.");
            if (!f761a) {
                throw new IllegalPoiSearchArgumentException("BaiduMap app is not installed.");
            }
            m504a(routeParaOption, context, 0);
            return true;
        }
        if (baiduMapVersion >= 810) {
            return C0708a.m470a(routeParaOption, context, 0);
        }
        Log.e("baidumapsdk", "Baidumap app version is too lowl.Version is greater than 8.1");
        if (!f761a) {
            throw new IllegalPoiSearchArgumentException("Baidumap app version is too lowl.Version is greater than 8.1");
        }
        m504a(routeParaOption, context, 0);
        return true;
    }

    public static boolean openBaiduMapTransitRoute(RouteParaOption routeParaOption, Context context) {
        if (routeParaOption == null || context == null) {
            throw new IllegalPoiSearchArgumentException("para or context can not be null.");
        }
        if (routeParaOption.f763b == null && routeParaOption.f762a == null && routeParaOption.f765d == null && routeParaOption.f764c == null) {
            throw new IllegalNaviArgumentException("startPoint and endPoint and endName and startName not all null.");
        }
        if (routeParaOption.f764c == null && routeParaOption.f762a == null) {
            throw new IllegalNaviArgumentException("startPoint and startName not all null.");
        }
        if (routeParaOption.f765d == null && routeParaOption.f763b == null) {
            throw new IllegalNaviArgumentException("endPoint and endName not all null.");
        }
        if (((routeParaOption.f764c == null || routeParaOption.f764c.equals("")) && routeParaOption.f762a == null) || ((routeParaOption.f765d == null || routeParaOption.f765d.equals("")) && routeParaOption.f763b == null)) {
            Log.e(BaiduMapRoutePlan.class.getName(), "poi startName or endName can not be empty string while pt is null");
            return false;
        }
        if (routeParaOption.f767f == null) {
            routeParaOption.f767f = RouteParaOption.EBusStrategyType.bus_recommend_way;
        }
        int baiduMapVersion = OpenClientUtil.getBaiduMapVersion(context);
        if (baiduMapVersion == 0) {
            Log.e("baidumapsdk", "BaiduMap app is not installed.");
            if (!f761a) {
                throw new IllegalPoiSearchArgumentException("BaiduMap app is not installed.");
            }
            m504a(routeParaOption, context, 1);
            return true;
        }
        if (baiduMapVersion >= 810) {
            return C0708a.m470a(routeParaOption, context, 1);
        }
        Log.e("baidumapsdk", "Baidumap app version is too lowl.Version is greater than 8.1");
        if (!f761a) {
            throw new IllegalPoiSearchArgumentException("Baidumap app version is too lowl.Version is greater than 8.1");
        }
        m504a(routeParaOption, context, 1);
        return true;
    }

    public static boolean openBaiduMapWalkingRoute(RouteParaOption routeParaOption, Context context) {
        if (routeParaOption == null || context == null) {
            throw new IllegalPoiSearchArgumentException("para or context can not be null.");
        }
        if (routeParaOption.f763b == null && routeParaOption.f762a == null && routeParaOption.f765d == null && routeParaOption.f764c == null) {
            throw new IllegalNaviArgumentException("startPoint and endPoint and endName and startName not all null.");
        }
        if (routeParaOption.f764c == null && routeParaOption.f762a == null) {
            throw new IllegalNaviArgumentException("startPoint and startName not all null.");
        }
        if (routeParaOption.f765d == null && routeParaOption.f763b == null) {
            throw new IllegalNaviArgumentException("endPoint and endName not all null.");
        }
        if (((routeParaOption.f764c == null || routeParaOption.f764c.equals("")) && routeParaOption.f762a == null) || ((routeParaOption.f765d == null || routeParaOption.f765d.equals("")) && routeParaOption.f763b == null)) {
            Log.e(BaiduMapRoutePlan.class.getName(), "poi startName or endName can not be empty string while pt is null");
            return false;
        }
        if (routeParaOption.f767f == null) {
            routeParaOption.f767f = RouteParaOption.EBusStrategyType.bus_recommend_way;
        }
        int baiduMapVersion = OpenClientUtil.getBaiduMapVersion(context);
        if (baiduMapVersion == 0) {
            Log.e("baidumapsdk", "BaiduMap app is not installed.");
            if (!f761a) {
                throw new IllegalPoiSearchArgumentException("BaiduMap app is not installed.");
            }
            m504a(routeParaOption, context, 2);
            return true;
        }
        if (baiduMapVersion >= 810) {
            return C0708a.m470a(routeParaOption, context, 2);
        }
        Log.e("baidumapsdk", "Baidumap app version is too lowl.Version is greater than 8.1");
        if (!f761a) {
            throw new IllegalPoiSearchArgumentException("Baidumap app version is too lowl.Version is greater than 8.1");
        }
        m504a(routeParaOption, context, 2);
        return true;
    }

    public static void setSupportWebRoute(boolean z) {
        f761a = z;
    }
}
