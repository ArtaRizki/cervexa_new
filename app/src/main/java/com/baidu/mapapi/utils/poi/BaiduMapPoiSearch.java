package com.baidu.mapapi.utils.poi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.baidu.mapapi.http.HttpClient;
import com.baidu.mapapi.utils.C0708a;
import com.baidu.mapapi.utils.OpenClientUtil;
import com.baidu.mapapi.utils.route.BaiduMapRoutePlan;
import com.baidu.platform.comapi.pano.C0768a;
import com.baidu.platform.comapi.pano.PanoStateError;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BaiduMapPoiSearch {

    /* JADX INFO: renamed from: a */
    private static boolean f752a = true;

    /* JADX INFO: renamed from: com.baidu.mapapi.utils.poi.BaiduMapPoiSearch$1 */
    /* synthetic */ class C07131 {

        /* JADX INFO: renamed from: a */
        static final /* synthetic */ int[] f753a;

        /* JADX INFO: renamed from: b */
        static final /* synthetic */ int[] f754b;

        static {
            int[] iArr = new int[HttpClient.HttpStateError.values().length];
            f754b = iArr;
            try {
                iArr[HttpClient.HttpStateError.NETWORK_ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f754b[HttpClient.HttpStateError.INNER_ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[PanoStateError.values().length];
            f753a = iArr2;
            try {
                iArr2[PanoStateError.PANO_UID_ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f753a[PanoStateError.PANO_NOT_FOUND.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f753a[PanoStateError.PANO_NO_TOKEN.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f753a[PanoStateError.PANO_NO_ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m498a(PoiParaOption poiParaOption, Context context) {
        Uri uri = Uri.parse("http://api.map.baidu.com/place/detail?uid=" + poiParaOption.f756a + "&output=html&src=" + context.getPackageName());
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setFlags(268435456);
        intent.setData(uri);
        context.startActivity(intent);
    }

    /* JADX INFO: renamed from: b */
    private static void m500b(PoiParaOption poiParaOption, Context context) {
        Uri uri = Uri.parse("http://api.map.baidu.com/place/search?query=" + poiParaOption.f757b + "&location=" + poiParaOption.f758c.latitude + "," + poiParaOption.f758c.longitude + "&radius=" + poiParaOption.f759d + "&output=html&src=" + context.getPackageName());
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setFlags(268435456);
        intent.setData(uri);
        context.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b */
    public static void m501b(String str, Context context) {
        if (str == null || str.length() == 0) {
            throw new RuntimeException("pano id can not be null.");
        }
        if (context == null) {
            throw new RuntimeException("context cannot be null.");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("baidumap://map/streetscape?");
        sb.append("panoid=");
        sb.append(str);
        sb.append("&pid=");
        sb.append(str);
        sb.append("&panotype=");
        sb.append("street");
        sb.append("&src=");
        sb.append("sdk_[" + context.getPackageName() + "]");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(sb.toString()));
        intent.setFlags(268435456);
        if (intent.resolveActivity(context.getPackageManager()) == null) {
            throw new RuntimeException("BaiduMap app is not installed.");
        }
        context.startActivity(intent);
    }

    public static boolean dispatchPoiToBaiduMap(List<DispathcPoiData> list, Context context) throws Exception {
        String str;
        if (list.isEmpty() || list.size() <= 0) {
            throw new NullPointerException("dispatch poidata is null");
        }
        int baiduMapVersion = OpenClientUtil.getBaiduMapVersion(context);
        if (baiduMapVersion == 0) {
            str = "BaiduMap app is not installed.";
        } else {
            if (baiduMapVersion >= 840) {
                return C0708a.m471a(list, context, 6);
            }
            str = "Baidumap app version is too lowl.Version is greater than 8.4";
        }
        Log.e("baidumapsdk", str);
        return false;
    }

    public static void finish(Context context) {
        if (context != null) {
            C0708a.m464a(context);
        }
    }

    public static void openBaiduMapPanoShow(String str, Context context) {
        new C0768a().m760a(str, new C0714a(context));
    }

    public static boolean openBaiduMapPoiDetialsPage(PoiParaOption poiParaOption, Context context) {
        if (poiParaOption == null || context == null) {
            throw new IllegalPoiSearchArgumentException("para or context can not be null.");
        }
        if (poiParaOption.f756a == null) {
            throw new IllegalPoiSearchArgumentException("poi uid can not be null.");
        }
        if (poiParaOption.f756a.equals("")) {
            Log.e(BaiduMapRoutePlan.class.getName(), "poi uid can not be empty string");
            return false;
        }
        int baiduMapVersion = OpenClientUtil.getBaiduMapVersion(context);
        if (baiduMapVersion == 0) {
            Log.e("baidumapsdk", "BaiduMap app is not installed.");
            if (!f752a) {
                throw new IllegalPoiSearchArgumentException("BaiduMap app is not installed.");
            }
            m498a(poiParaOption, context);
            return true;
        }
        if (baiduMapVersion >= 810) {
            return C0708a.m469a(poiParaOption, context, 3);
        }
        Log.e("baidumapsdk", "Baidumap app version is too lowl.Version is greater than 8.1");
        if (!f752a) {
            throw new IllegalPoiSearchArgumentException("Baidumap app version is too lowl.Version is greater than 8.1");
        }
        m498a(poiParaOption, context);
        return true;
    }

    public static boolean openBaiduMapPoiNearbySearch(PoiParaOption poiParaOption, Context context) {
        if (poiParaOption == null || context == null) {
            throw new IllegalPoiSearchArgumentException("para or context can not be null.");
        }
        if (poiParaOption.f757b == null) {
            throw new IllegalPoiSearchArgumentException("poi search key can not be null.");
        }
        if (poiParaOption.f758c == null) {
            throw new IllegalPoiSearchArgumentException("poi search center can not be null.");
        }
        if (poiParaOption.f758c.longitude == 0.0d || poiParaOption.f758c.latitude == 0.0d) {
            throw new IllegalPoiSearchArgumentException("poi search center longitude or latitude can not be 0.");
        }
        if (poiParaOption.f759d == 0) {
            throw new IllegalPoiSearchArgumentException("poi search radius larger than 0.");
        }
        if (poiParaOption.f757b.equals("")) {
            Log.e(BaiduMapRoutePlan.class.getName(), "poi key can not be empty string");
            return false;
        }
        int baiduMapVersion = OpenClientUtil.getBaiduMapVersion(context);
        if (baiduMapVersion == 0) {
            Log.e("baidumapsdk", "BaiduMap app is not installed.");
            if (!f752a) {
                throw new IllegalPoiSearchArgumentException("BaiduMap app is not installed.");
            }
            m500b(poiParaOption, context);
            return true;
        }
        if (baiduMapVersion >= 810) {
            return C0708a.m469a(poiParaOption, context, 4);
        }
        Log.e("baidumapsdk", "Baidumap app version is too lowl.Version is greater than 8.1");
        if (!f752a) {
            throw new IllegalPoiSearchArgumentException("Baidumap app version is too lowl.Version is greater than 8.1");
        }
        m500b(poiParaOption, context);
        return true;
    }

    public static void setSupportWebPoi(boolean z) {
        f752a = z;
    }
}
