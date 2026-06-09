package com.baidu.mapapi.utils;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.baidu.mapapi.common.AppTools;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapapi.navi.NaviParaOption;
import com.baidu.mapapi.utils.poi.DispathcPoiData;
import com.baidu.mapapi.utils.poi.PoiParaOption;
import com.baidu.mapapi.utils.route.RouteParaOption;
import com.baidu.mapframework.open.aidl.IComOpenClient;
import com.baidu.mapframework.open.aidl.InterfaceC0716a;
import com.baidu.platform.comapi.location.CoordinateType;
import com.baidu.platform.comapi.p009a.C0721a;
import com.generalplus.GoPlusDrone.Fragment.BaseFragment;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.baidu.mapapi.utils.a */
/* JADX INFO: loaded from: classes.dex */
public class C0708a {

    /* JADX INFO: renamed from: d */
    private static InterfaceC0716a f729d;

    /* JADX INFO: renamed from: e */
    private static IComOpenClient f730e;

    /* JADX INFO: renamed from: f */
    private static int f731f;

    /* JADX INFO: renamed from: o */
    private static RouteParaOption.EBusStrategyType f740o;

    /* JADX INFO: renamed from: v */
    private static Thread f747v;

    /* JADX INFO: renamed from: c */
    private static final String f728c = C0708a.class.getName();

    /* JADX INFO: renamed from: a */
    public static int f726a = -1;

    /* JADX INFO: renamed from: g */
    private static String f732g = null;

    /* JADX INFO: renamed from: h */
    private static String f733h = null;

    /* JADX INFO: renamed from: i */
    private static String f734i = null;

    /* JADX INFO: renamed from: j */
    private static List<DispathcPoiData> f735j = new ArrayList();

    /* JADX INFO: renamed from: k */
    private static LatLng f736k = null;

    /* JADX INFO: renamed from: l */
    private static LatLng f737l = null;

    /* JADX INFO: renamed from: m */
    private static String f738m = null;

    /* JADX INFO: renamed from: n */
    private static String f739n = null;

    /* JADX INFO: renamed from: p */
    private static String f741p = null;

    /* JADX INFO: renamed from: q */
    private static String f742q = null;

    /* JADX INFO: renamed from: r */
    private static LatLng f743r = null;

    /* JADX INFO: renamed from: s */
    private static int f744s = 0;

    /* JADX INFO: renamed from: t */
    private static boolean f745t = false;

    /* JADX INFO: renamed from: u */
    private static boolean f746u = false;

    /* JADX INFO: renamed from: b */
    static ServiceConnection f727b = new ServiceConnectionC0710c();

    /* JADX INFO: renamed from: a */
    public static String m462a() {
        return AppTools.getBaiduMapToken();
    }

    /* JADX INFO: renamed from: a */
    public static void m463a(int i, Context context) {
        switch (i) {
            case 0:
            case 1:
            case 2:
                m482c(context, i);
                break;
            case 3:
                m481c(context);
                break;
            case 4:
                m484d(context);
                break;
            case 5:
                m486e(context);
                break;
            case 7:
                m487f(context);
                break;
            case 8:
                m489g(context);
                break;
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m464a(Context context) {
        if (f746u) {
            context.unbindService(f727b);
            f746u = false;
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m465a(List<DispathcPoiData> list, Context context) {
        f732g = context.getPackageName();
        f733h = m474b(context);
        f734i = "";
        List<DispathcPoiData> list2 = f735j;
        if (list2 != null) {
            list2.clear();
        }
        Iterator<DispathcPoiData> it = list.iterator();
        while (it.hasNext()) {
            f735j.add(it.next());
        }
    }

    /* JADX INFO: renamed from: a */
    public static boolean m466a(int i) {
        switch (i) {
            case 0:
            case 1:
            case 2:
                return m490g();
            case 3:
                return m491h();
            case 4:
                return m496m();
            case 5:
                return m493j();
            case 6:
                return m492i();
            case 7:
                return m494k();
            case 8:
                return m495l();
            default:
                return false;
        }
    }

    /* JADX INFO: renamed from: a */
    public static boolean m467a(Context context, int i) {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!C0721a.m531a(context)) {
            Log.d(f728c, "package sign verify failed");
            return false;
        }
        f745t = false;
        switch (i) {
            case 0:
                f726a = 0;
                break;
            case 1:
                f726a = 1;
                break;
            case 2:
                f726a = 2;
                break;
            case 3:
                f726a = 3;
                break;
            case 4:
                f726a = 4;
                break;
            case 5:
                f726a = 5;
                break;
            case 6:
                f726a = 6;
                break;
            case 7:
                f726a = 7;
                break;
            case 8:
                f726a = 8;
                break;
        }
        if (f729d == null || !f746u) {
            m475b(context, i);
        } else {
            if (f730e != null) {
                f745t = true;
                return m466a(i);
            }
            f729d.mo508a(new BinderC0709b(i));
        }
        return true;
    }

    /* JADX INFO: renamed from: a */
    public static boolean m468a(NaviParaOption naviParaOption, Context context, int i) {
        m476b(naviParaOption, context, i);
        return m467a(context, i);
    }

    /* JADX INFO: renamed from: a */
    public static boolean m469a(PoiParaOption poiParaOption, Context context, int i) {
        m477b(poiParaOption, context, i);
        return m467a(context, i);
    }

    /* JADX INFO: renamed from: a */
    public static boolean m470a(RouteParaOption routeParaOption, Context context, int i) {
        m478b(routeParaOption, context, i);
        return m467a(context, i);
    }

    /* JADX INFO: renamed from: a */
    public static boolean m471a(List<DispathcPoiData> list, Context context, int i) {
        m465a(list, context);
        return m467a(context, i);
    }

    /* JADX INFO: renamed from: b */
    public static String m474b(Context context) {
        PackageManager packageManager;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = context.getPackageManager();
            try {
                applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException unused) {
            }
        } catch (PackageManager.NameNotFoundException unused2) {
            packageManager = null;
        }
        return (String) packageManager.getApplicationLabel(applicationInfo);
    }

    /* JADX INFO: renamed from: b */
    private static void m475b(Context context, int i) {
        Intent intent = new Intent();
        String strM462a = m462a();
        if (strM462a == null) {
            return;
        }
        intent.putExtra("api_token", strM462a);
        intent.setAction("com.baidu.map.action.OPEN_SERVICE");
        intent.setPackage("com.baidu.BaiduMap");
        boolean zBindService = context.bindService(intent, f727b, 1);
        f746u = zBindService;
        if (!zBindService) {
            Log.e("baidumapsdk", "bind service failed，call openapi");
            m463a(i, context);
        } else {
            Thread thread = new Thread(new RunnableC0712e(context, i));
            f747v = thread;
            thread.setDaemon(true);
            f747v.start();
        }
    }

    /* JADX INFO: renamed from: b */
    private static void m476b(NaviParaOption naviParaOption, Context context, int i) {
        f732g = context.getPackageName();
        f738m = null;
        f736k = null;
        f739n = null;
        f737l = null;
        if (naviParaOption.getStartPoint() != null) {
            f736k = naviParaOption.getStartPoint();
        }
        if (naviParaOption.getEndPoint() != null) {
            f737l = naviParaOption.getEndPoint();
        }
        if (naviParaOption.getStartName() != null) {
            f738m = naviParaOption.getStartName();
        }
        if (naviParaOption.getEndName() != null) {
            f739n = naviParaOption.getEndName();
        }
    }

    /* JADX INFO: renamed from: b */
    private static void m477b(PoiParaOption poiParaOption, Context context, int i) {
        f741p = null;
        f742q = null;
        f743r = null;
        f744s = 0;
        f732g = context.getPackageName();
        if (poiParaOption.getUid() != null) {
            f741p = poiParaOption.getUid();
        }
        if (poiParaOption.getKey() != null) {
            f742q = poiParaOption.getKey();
        }
        if (poiParaOption.getCenter() != null) {
            f743r = poiParaOption.getCenter();
        }
        if (poiParaOption.getRadius() != 0) {
            f744s = poiParaOption.getRadius();
        }
    }

    /* JADX INFO: renamed from: b */
    private static void m478b(RouteParaOption routeParaOption, Context context, int i) {
        int i2;
        f738m = null;
        f736k = null;
        f739n = null;
        f737l = null;
        f732g = context.getPackageName();
        if (routeParaOption.getStartPoint() != null) {
            f736k = routeParaOption.getStartPoint();
        }
        if (routeParaOption.getEndPoint() != null) {
            f737l = routeParaOption.getEndPoint();
        }
        if (routeParaOption.getStartName() != null) {
            f738m = routeParaOption.getStartName();
        }
        if (routeParaOption.getEndName() != null) {
            f739n = routeParaOption.getEndName();
        }
        if (routeParaOption.getBusStrategyType() != null) {
            f740o = routeParaOption.getBusStrategyType();
        }
        if (i != 0) {
            i2 = 1;
            if (i != 1) {
                i2 = 2;
                if (i != 2) {
                    return;
                }
            }
        } else {
            i2 = 0;
        }
        f731f = i2;
    }

    /* JADX INFO: renamed from: c */
    private static void m481c(Context context) {
        Thread thread = f747v;
        if (thread != null) {
            thread.interrupt();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("baidumap://map/place/detail?");
        sb.append("uid=");
        sb.append(f741p);
        sb.append("&show_type=");
        sb.append("detail_page");
        sb.append("&src=");
        sb.append("sdk_[" + f732g + "]");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(sb.toString()));
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x009e  */
    /* JADX INFO: renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void m482c(android.content.Context r8, int r9) {
        /*
            Method dump skipped, instruction units count: 239
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapapi.utils.C0708a.m482c(android.content.Context, int):void");
    }

    /* JADX INFO: renamed from: d */
    private static void m484d(Context context) {
        Thread thread = f747v;
        if (thread != null) {
            thread.interrupt();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("baidumap://map/nearbysearch?");
        sb.append("center=");
        sb.append(f743r.latitude);
        sb.append(",");
        sb.append(f743r.longitude);
        sb.append("&query=");
        sb.append(f742q);
        sb.append("&radius=");
        sb.append(f744s);
        sb.append("&src=");
        sb.append("sdk_[" + f732g + "]");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(sb.toString()));
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    /* JADX INFO: renamed from: e */
    private static void m486e(Context context) {
        Thread thread = f747v;
        if (thread != null) {
            thread.interrupt();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("baidumap://map/navi?");
        sb.append("origin=");
        sb.append(f736k.latitude);
        sb.append(",");
        sb.append(f736k.longitude);
        sb.append("&location=");
        sb.append(f737l.latitude);
        sb.append(",");
        sb.append(f737l.longitude);
        sb.append("&src=");
        sb.append("sdk_[" + f732g + "]");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(sb.toString()));
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    /* JADX INFO: renamed from: f */
    private static void m487f(Context context) {
        Thread thread = f747v;
        if (thread != null) {
            thread.interrupt();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("baidumap://map/walknavi?");
        sb.append("origin=");
        sb.append(f736k.latitude);
        sb.append(",");
        sb.append(f736k.longitude);
        sb.append("&destination=");
        sb.append(f737l.latitude);
        sb.append(",");
        sb.append(f737l.longitude);
        sb.append("&src=");
        sb.append("sdk_[" + f732g + "]");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(sb.toString()));
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    /* JADX INFO: renamed from: g */
    private static void m489g(Context context) {
        Thread thread = f747v;
        if (thread != null) {
            thread.interrupt();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("baidumap://map/bikenavi?");
        sb.append("origin=");
        sb.append(f736k.latitude);
        sb.append(",");
        sb.append(f736k.longitude);
        sb.append("&destination=");
        sb.append(f737l.latitude);
        sb.append(",");
        sb.append(f737l.longitude);
        sb.append("&src=");
        sb.append("sdk_[" + f732g + "]");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(sb.toString()));
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    /* JADX INFO: renamed from: g */
    private static boolean m490g() {
        String strMo505a;
        try {
            Log.d(f728c, "callDispatchTakeOutRoute");
            strMo505a = f730e.mo505a("map.android.baidu.mainmap");
        } catch (RemoteException e) {
            Log.d(f728c, "callDispatchTakeOut exception", e);
        }
        if (strMo505a == null) {
            Log.d(f728c, "callDispatchTakeOut com not found");
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putString("target", "route_search_page");
        Bundle bundle2 = new Bundle();
        bundle2.putInt("route_type", f731f);
        bundle2.putInt("bus_strategy", f740o.ordinal());
        bundle2.putInt("cross_city_bus_strategy", 5);
        if (f736k != null) {
            bundle2.putInt("start_type", 1);
            bundle2.putInt("start_longitude", (int) CoordUtil.ll2mc(f736k).getLongitudeE6());
            bundle2.putInt("start_latitude", (int) CoordUtil.ll2mc(f736k).getLatitudeE6());
        } else {
            bundle2.putInt("start_type", 2);
            bundle2.putInt("start_longitude", 0);
            bundle2.putInt("start_latitude", 0);
        }
        if (f738m != null) {
            bundle2.putString("start_keyword", f738m);
        } else {
            bundle2.putString("start_keyword", "地图上的点");
        }
        bundle2.putString("start_uid", "");
        if (f737l != null) {
            bundle2.putInt("end_type", 1);
            bundle2.putInt("end_longitude", (int) CoordUtil.ll2mc(f737l).getLongitudeE6());
            bundle2.putInt("end_latitude", (int) CoordUtil.ll2mc(f737l).getLatitudeE6());
        } else {
            bundle2.putInt("end_type", 2);
            bundle2.putInt("end_longitude", 0);
            bundle2.putInt("end_latitude", 0);
        }
        if (f739n != null) {
            bundle2.putString("end_keyword", f739n);
        } else {
            bundle2.putString("end_keyword", "地图上的点");
        }
        bundle2.putString("end_uid", "");
        bundle.putBundle("base_params", bundle2);
        Bundle bundle3 = new Bundle();
        bundle3.putString("launch_from", "sdk_[" + f732g + "]");
        bundle.putBundle("ext_params", bundle3);
        return f730e.mo506a("map.android.baidu.mainmap", strMo505a, bundle);
    }

    /* JADX INFO: renamed from: h */
    private static boolean m491h() {
        try {
            Log.d(f728c, "callDispatchTakeOutPoiDetials");
            String strMo505a = f730e.mo505a("map.android.baidu.mainmap");
            if (strMo505a == null) {
                Log.d(f728c, "callDispatchTakeOut com not found");
                return false;
            }
            Bundle bundle = new Bundle();
            bundle.putString("target", "request_poi_detail_page");
            Bundle bundle2 = new Bundle();
            bundle2.putString("uid", f741p != null ? f741p : "");
            bundle.putBundle("base_params", bundle2);
            Bundle bundle3 = new Bundle();
            bundle3.putString("launch_from", "sdk_[" + f732g + "]");
            bundle.putBundle("ext_params", bundle3);
            return f730e.mo506a("map.android.baidu.mainmap", strMo505a, bundle);
        } catch (RemoteException e) {
            Log.d(f728c, "callDispatchTakeOut exception", e);
            return false;
        }
    }

    /* JADX INFO: renamed from: i */
    private static boolean m492i() {
        List<DispathcPoiData> list = f735j;
        if (list != null && list.size() > 0) {
            try {
                Log.d(f728c, "callDispatchPoiToBaiduMap");
                String strMo505a = f730e.mo505a("map.android.baidu.mainmap");
                if (strMo505a != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("target", "favorite_page");
                    Bundle bundle2 = new Bundle();
                    JSONArray jSONArray = new JSONArray();
                    int i = 0;
                    for (int i2 = 0; i2 < f735j.size(); i2++) {
                        if (f735j.get(i2).name != null && !f735j.get(i2).name.equals("") && f735j.get(i2).f755pt != null) {
                            JSONObject jSONObject = new JSONObject();
                            try {
                                jSONObject.put(BaseFragment.DATA_NAME, f735j.get(i2).name);
                                GeoPoint geoPointLl2mc = CoordUtil.ll2mc(f735j.get(i2).f755pt);
                                jSONObject.put("ptx", geoPointLl2mc.getLongitudeE6());
                                jSONObject.put("pty", geoPointLl2mc.getLatitudeE6());
                                jSONObject.put("addr", f735j.get(i2).addr);
                                jSONObject.put("uid", f735j.get(i2).uid);
                                i++;
                                jSONArray.put(jSONObject);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (i == 0) {
                        return false;
                    }
                    bundle2.putString("data", jSONArray.toString());
                    bundle2.putString("from", f733h);
                    bundle2.putString("pkg", f732g);
                    bundle2.putString("cls", f734i);
                    bundle2.putInt("count", i);
                    bundle.putBundle("base_params", bundle2);
                    Bundle bundle3 = new Bundle();
                    bundle3.putString("launch_from", "sdk_[" + f732g + "]");
                    bundle.putBundle("ext_params", bundle3);
                    return f730e.mo506a("map.android.baidu.mainmap", strMo505a, bundle);
                }
                Log.d(f728c, "callDispatchPoiToBaiduMap com not found");
            } catch (RemoteException e2) {
                Log.d(f728c, "callDispatchPoiToBaiduMap exception", e2);
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: j */
    private static boolean m493j() {
        String strMo505a;
        try {
            Log.d(f728c, "callDispatchTakeOutRouteNavi");
            strMo505a = f730e.mo505a("map.android.baidu.mainmap");
        } catch (RemoteException e) {
            Log.d(f728c, "callDispatchTakeOut exception", e);
        }
        if (strMo505a == null) {
            Log.d(f728c, "callDispatchTakeOut com not found");
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putString("target", "navigation_page");
        Bundle bundle2 = new Bundle();
        bundle2.putString("coord_type", CoordinateType.BD09LL);
        bundle2.putString("type", "DIS");
        StringBuffer stringBuffer = new StringBuffer();
        if (f738m != null) {
            stringBuffer.append("name:" + f738m + "|");
        }
        stringBuffer.append(String.format("latlng:%f,%f", Double.valueOf(f736k.latitude), Double.valueOf(f736k.longitude)));
        StringBuffer stringBuffer2 = new StringBuffer();
        if (f739n != null) {
            stringBuffer2.append("name:" + f739n + "|");
        }
        stringBuffer2.append(String.format("latlng:%f,%f", Double.valueOf(f737l.latitude), Double.valueOf(f737l.longitude)));
        bundle2.putString("origin", stringBuffer.toString());
        bundle2.putString("destination", stringBuffer2.toString());
        bundle.putBundle("base_params", bundle2);
        Bundle bundle3 = new Bundle();
        bundle3.putString("launch_from", "sdk_[" + f732g + "]");
        bundle.putBundle("ext_params", bundle3);
        return f730e.mo506a("map.android.baidu.mainmap", strMo505a, bundle);
    }

    /* JADX INFO: renamed from: k */
    private static boolean m494k() {
        String strMo505a;
        try {
            Log.d(f728c, "callDispatchTakeOutRouteNavi");
            strMo505a = f730e.mo505a("map.android.baidu.mainmap");
        } catch (Exception e) {
            Log.d(f728c, "callDispatchTakeOut exception", e);
        }
        if (strMo505a == null) {
            Log.d(f728c, "callDispatchTakeOut com not found");
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putString("target", "walknavi_page");
        Bundle bundle2 = new Bundle();
        bundle2.putString("coord_type", CoordinateType.BD09LL);
        StringBuffer stringBuffer = new StringBuffer();
        if (f738m != null) {
            stringBuffer.append("name:" + f738m + "|");
        }
        stringBuffer.append(String.format("latlng:%f,%f", Double.valueOf(f736k.latitude), Double.valueOf(f736k.longitude)));
        StringBuffer stringBuffer2 = new StringBuffer();
        if (f739n != null) {
            stringBuffer2.append("name:" + f739n + "|");
        }
        stringBuffer2.append(String.format("latlng:%f,%f", Double.valueOf(f737l.latitude), Double.valueOf(f737l.longitude)));
        bundle2.putString("origin", stringBuffer.toString());
        bundle2.putString("destination", stringBuffer2.toString());
        bundle.putBundle("base_params", bundle2);
        Bundle bundle3 = new Bundle();
        bundle3.putString("launch_from", "sdk_[" + f732g + "]");
        bundle.putBundle("ext_params", bundle3);
        return f730e.mo506a("map.android.baidu.mainmap", strMo505a, bundle);
    }

    /* JADX INFO: renamed from: l */
    private static boolean m495l() {
        String strMo505a;
        try {
            Log.d(f728c, "callDispatchTakeOutRouteRidingNavi");
            strMo505a = f730e.mo505a("map.android.baidu.mainmap");
        } catch (RemoteException e) {
            Log.d(f728c, "callDispatchTakeOut exception", e);
        }
        if (strMo505a == null) {
            Log.d(f728c, "callDispatchTakeOut com not found");
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putString("target", "bikenavi_page");
        Bundle bundle2 = new Bundle();
        bundle2.putString("coord_type", CoordinateType.BD09LL);
        StringBuffer stringBuffer = new StringBuffer();
        if (f738m != null) {
            stringBuffer.append("name:" + f738m + "|");
        }
        stringBuffer.append(String.format("latlng:%f,%f", Double.valueOf(f736k.latitude), Double.valueOf(f736k.longitude)));
        StringBuffer stringBuffer2 = new StringBuffer();
        if (f739n != null) {
            stringBuffer2.append("name:" + f739n + "|");
        }
        stringBuffer2.append(String.format("latlng:%f,%f", Double.valueOf(f737l.latitude), Double.valueOf(f737l.longitude)));
        bundle2.putString("origin", stringBuffer.toString());
        bundle2.putString("destination", stringBuffer2.toString());
        bundle.putBundle("base_params", bundle2);
        Bundle bundle3 = new Bundle();
        bundle3.putString("launch_from", "sdk_[" + f732g + "]");
        bundle.putBundle("ext_params", bundle3);
        return f730e.mo506a("map.android.baidu.mainmap", strMo505a, bundle);
    }

    /* JADX INFO: renamed from: m */
    private static boolean m496m() {
        try {
            Log.d(f728c, "callDispatchTakeOutPoiNearbySearch");
            String strMo505a = f730e.mo505a("map.android.baidu.mainmap");
            if (strMo505a == null) {
                Log.d(f728c, "callDispatchTakeOut com not found");
                return false;
            }
            Bundle bundle = new Bundle();
            bundle.putString("target", "poi_search_page");
            Bundle bundle2 = new Bundle();
            if (f742q != null) {
                bundle2.putString("search_key", f742q);
            } else {
                bundle2.putString("search_key", "");
            }
            if (f743r != null) {
                bundle2.putInt("center_pt_x", (int) CoordUtil.ll2mc(f743r).getLongitudeE6());
                bundle2.putInt("center_pt_y", (int) CoordUtil.ll2mc(f743r).getLatitudeE6());
            } else {
                bundle2.putString("search_key", "");
            }
            if (f744s != 0) {
                bundle2.putInt("search_radius", f744s);
            } else {
                bundle2.putInt("search_radius", 1000);
            }
            bundle2.putBoolean("is_direct_search", true);
            bundle2.putBoolean("is_direct_area_search", true);
            bundle.putBundle("base_params", bundle2);
            Bundle bundle3 = new Bundle();
            bundle3.putString("launch_from", "sdk_[" + f732g + "]");
            bundle.putBundle("ext_params", bundle3);
            return f730e.mo506a("map.android.baidu.mainmap", strMo505a, bundle);
        } catch (RemoteException e) {
            Log.d(f728c, "callDispatchTakeOut exception", e);
            return false;
        }
    }
}
