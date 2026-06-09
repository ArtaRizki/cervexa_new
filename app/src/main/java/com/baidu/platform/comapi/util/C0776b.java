package com.baidu.platform.comapi.util;

import android.os.Bundle;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.C0706a;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapapi.model.inner.Point;
import com.baidu.platform.comapi.location.CoordinateType;
import com.baidu.platform.comjni.tools.C0785a;
import com.baidu.platform.comjni.tools.JNITools;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: com.baidu.platform.comapi.util.b */
/* JADX INFO: loaded from: classes.dex */
public class C0776b {

    /* JADX INFO: renamed from: a */
    static Bundle f1062a = new Bundle();

    /* JADX INFO: renamed from: b */
    static double[] f1063b = {1.289059486E7d, 8362377.87d, 5591021.0d, 3481989.83d, 1678043.12d, 0.0d};

    /* JADX INFO: renamed from: c */
    static double[] f1064c = {7.5E7d, 6.0E7d, 4.5E7d, 3.0E7d, 1.5E7d, 0.0d};

    /* JADX INFO: renamed from: d */
    static double[][] f1065d = {new double[]{1.410526172116255E-8d, 8.98305509648872E-6d, -1.9939833816331d, 200.9824383106796d, -187.2403703815547d, 91.6087516669843d, -23.38765649603339d, 2.57121317296198d, -0.03801003308653d, 1.73379812E7d}, new double[]{-7.435856389565537E-9d, 8.983055097726239E-6d, -0.78625201886289d, 96.32687599759846d, -1.85204757529826d, -59.36935905485877d, 47.40033549296737d, -16.50741931063887d, 2.28786674699375d, 1.026014486E7d}, new double[]{-3.030883460898826E-8d, 8.98305509983578E-6d, 0.30071316287616d, 59.74293618442277d, 7.357984074871d, -25.38371002664745d, 13.45380521110908d, -3.29883767235584d, 0.32710905363475d, 6856817.37d}, new double[]{-1.981981304930552E-8d, 8.983055099779535E-6d, 0.03278182852591d, 40.31678527705744d, 0.65659298677277d, -4.44255534477492d, 0.85341911805263d, 0.12923347998204d, -0.04625736007561d, 4482777.06d}, new double[]{3.09191371068437E-9d, 8.983055096812155E-6d, 6.995724062E-5d, 23.10934304144901d, -2.3663490511E-4d, -0.6321817810242d, -0.00663494467273d, 0.03430082397953d, -0.00466043876332d, 2555164.4d}, new double[]{2.890871144776878E-9d, 8.983055095805407E-6d, -3.068298E-8d, 7.47137025468032d, -3.53937994E-6d, -0.02145144861037d, -1.234426596E-5d, 1.0322952773E-4d, -3.23890364E-6d, 826088.5d}};

    /* JADX INFO: renamed from: e */
    static double[][] f1066e = {new double[]{-0.0015702102444d, 111320.7020616939d, 1.704480524535203E15d, -1.033898737604234E16d, 2.611266785660388E16d, -3.51496691766537E16d, 2.659570071840392E16d, -1.072501245418824E16d, 1.800819912950474E15d, 82.5d}, new double[]{8.277824516172526E-4d, 111320.7020463578d, 6.477955746671607E8d, -4.082003173641316E9d, 1.077490566351142E10d, -1.517187553151559E10d, 1.205306533862167E10d, -5.124939663577472E9d, 9.133119359512032E8d, 67.5d}, new double[]{0.00337398766765d, 111320.7020202162d, 4481351.045890365d, -2.339375119931662E7d, 7.968221547186455E7d, -1.159649932797253E8d, 9.723671115602145E7d, -4.366194633752821E7d, 8477230.501135234d, 52.5d}, new double[]{0.00220636496208d, 111320.7020209128d, 51751.86112841131d, 3796837.749470245d, 992013.7397791013d, -1221952.21711287d, 1340652.697009075d, -620943.6990984312d, 144416.9293806241d, 37.5d}, new double[]{-3.441963504368392E-4d, 111320.7020576856d, 278.2353980772752d, 2485758.690035394d, 6070.750963243378d, 54821.18345352118d, 9540.606633304236d, -2710.55326746645d, 1405.483844121726d, 22.5d}, new double[]{-3.218135878613132E-4d, 111320.7020701615d, 0.00369383431289d, 823725.6402795718d, 0.46104986909093d, 2351.343141331292d, 1.58060784298199d, 8.77738589078284d, 0.37238884252424d, 7.45d}};

    /* JADX INFO: renamed from: com.baidu.platform.comapi.util.b$a */
    static class a {

        /* JADX INFO: renamed from: a */
        double f1067a;

        /* JADX INFO: renamed from: b */
        double f1068b;

        a() {
        }
    }

    /* JADX INFO: renamed from: a */
    public static int m774a(LatLng latLng, int i) {
        LatLng latLng2 = new LatLng(latLng.latitude + (((double) i) / 111000.0d), latLng.longitude);
        GeoPoint geoPointM778a = m778a(latLng);
        GeoPoint geoPointM778a2 = m778a(latLng2);
        return (int) Math.sqrt(Math.pow(geoPointM778a.getLatitudeE6() - geoPointM778a2.getLatitudeE6(), 2.0d) + Math.pow(geoPointM778a.getLongitudeE6() - geoPointM778a2.getLongitudeE6(), 2.0d));
    }

    /* JADX INFO: renamed from: a */
    public static LatLng m775a(float f, float f2, String str) {
        if (str == null) {
            return null;
        }
        if (str.equals("") || str.equals(CoordinateType.BD09LL)) {
            return new LatLng(f, f2);
        }
        if (!str.equals(CoordinateType.BD09LL) && !str.equals(CoordinateType.BD09MC) && !str.equals(CoordinateType.GCJ02) && !str.equals(CoordinateType.WGS84)) {
            return null;
        }
        Bundle bundle = new Bundle();
        JNITools.CoordinateEncryptEx(f, f2, str, bundle);
        if (bundle.isEmpty()) {
            return null;
        }
        return new LatLng(bundle.getDouble("y"), bundle.getDouble("x"));
    }

    /* JADX INFO: renamed from: a */
    public static LatLng m776a(GeoPoint geoPoint) {
        a aVar = new a();
        aVar.f1067a = geoPoint.getLongitudeE6();
        aVar.f1068b = geoPoint.getLatitudeE6();
        a aVar2 = new a();
        aVar2.f1067a = aVar.f1067a;
        if (aVar2.f1067a > 2.0037508342E7d) {
            aVar2.f1067a = 2.0037508342E7d;
        } else if (aVar2.f1067a < -2.0037508342E7d) {
            aVar2.f1067a = -2.0037508342E7d;
        }
        aVar2.f1068b = aVar.f1068b;
        if (aVar2.f1068b < 1.0E-6d && aVar2.f1068b >= 0.0d) {
            aVar2.f1068b = 1.0E-6d;
        } else if (aVar2.f1068b < 0.0d && aVar2.f1068b > -1.0E-6d) {
            aVar2.f1068b = -1.0E-6d;
        } else if (aVar2.f1068b > 2.0037508342E7d) {
            aVar2.f1068b = 2.0037508342E7d;
        } else if (aVar2.f1068b < -2.0037508342E7d) {
            aVar2.f1068b = -2.0037508342E7d;
        }
        double[] dArr = new double[10];
        int i = 0;
        while (true) {
            if (i >= 6) {
                break;
            }
            if (Math.abs(aVar2.f1068b) > f1063b[i]) {
                dArr = f1065d[i];
                break;
            }
            i++;
        }
        a aVarM779a = m779a(aVar2, dArr);
        return new LatLng(aVarM779a.f1068b, aVarM779a.f1067a);
    }

    /* JADX INFO: renamed from: a */
    public static LatLng m777a(String str) {
        if (str == null || str.length() <= 0) {
            return null;
        }
        f1062a.clear();
        f1062a.putString("strkey", str);
        JNITools.TransGeoStr2Pt(f1062a);
        GeoPoint geoPoint = new GeoPoint(0.0d, 0.0d);
        geoPoint.setLongitudeE6(f1062a.getInt("ptx"));
        geoPoint.setLatitudeE6(f1062a.getInt("pty"));
        return m776a(geoPoint);
    }

    /* JADX INFO: renamed from: a */
    public static GeoPoint m778a(LatLng latLng) {
        a aVar = new a();
        double[] dArr = new double[10];
        aVar.f1068b = Math.abs(latLng.latitude * 1000000.0d);
        if (aVar.f1068b < 0.1d) {
            aVar.f1068b = 0.1d;
        }
        int i = 0;
        while (true) {
            if (i >= f1064c.length) {
                break;
            }
            if (aVar.f1068b > f1064c[i]) {
                dArr = f1066e[i];
                break;
            }
            i++;
        }
        aVar.f1067a = latLng.longitude;
        aVar.f1068b = latLng.latitude;
        a aVarM779a = m779a(aVar, dArr);
        return new GeoPoint(aVarM779a.f1068b, aVarM779a.f1067a);
    }

    /* JADX INFO: renamed from: a */
    static a m779a(a aVar, double[] dArr) {
        a aVar2 = new a();
        aVar2.f1067a = dArr[0] + (dArr[1] * Math.abs(aVar.f1067a));
        double dAbs = Math.abs(aVar.f1068b) / dArr[9];
        aVar2.f1068b = dArr[2] + (dArr[3] * dAbs) + (dArr[4] * dAbs * dAbs) + (dArr[5] * dAbs * dAbs * dAbs) + (dArr[6] * dAbs * dAbs * dAbs * dAbs) + (dArr[7] * dAbs * dAbs * dAbs * dAbs * dAbs) + (dArr[8] * dAbs * dAbs * dAbs * dAbs * dAbs * dAbs);
        aVar2.f1067a *= (double) (aVar.f1067a < 0.0d ? -1 : 1);
        aVar2.f1068b *= (double) (aVar.f1068b < 0.0d ? -1 : 1);
        return aVar2;
    }

    /* JADX INFO: renamed from: b */
    public static LatLng m780b(String str) {
        if (str == null || str.length() <= 0) {
            return null;
        }
        f1062a.clear();
        f1062a.putString("strkey", str);
        JNITools.TransNodeStr2Pt(f1062a);
        return m776a(new GeoPoint(f1062a.getDouble("pty"), f1062a.getDouble("ptx")));
    }

    /* JADX INFO: renamed from: b */
    public static Point m781b(LatLng latLng) {
        if (latLng == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        JNITools.CoordinateEncryptMc((float) latLng.longitude, (float) latLng.latitude, bundle);
        Point point = new Point(0, 0);
        point.setmPtx((int) bundle.getDouble("x"));
        point.setmPty((int) bundle.getDouble("y"));
        return point;
    }

    /* JADX INFO: renamed from: c */
    public static List<LatLng> m782c(String str) {
        C0706a c0706aM898a = C0785a.m898a(str);
        ArrayList arrayList = new ArrayList();
        if (c0706aM898a == null || c0706aM898a.f717d == null) {
            return null;
        }
        ArrayList<ArrayList<Point>> arrayList2 = c0706aM898a.f717d;
        if (arrayList2.size() > 0) {
            ArrayList<Point> arrayList3 = arrayList2.get(0);
            for (int i = 0; i < arrayList3.size(); i++) {
                Point point = arrayList3.get(i);
                arrayList.add(m776a(new GeoPoint(point.f713y / 100, point.f712x / 100)));
            }
        }
        return arrayList;
    }

    /* JADX INFO: renamed from: d */
    public static List<List<LatLng>> m783d(String str) {
        C0706a c0706aM898a = C0785a.m898a(str);
        if (c0706aM898a == null || c0706aM898a.f717d == null) {
            return null;
        }
        ArrayList<ArrayList<Point>> arrayList = c0706aM898a.f717d;
        ArrayList arrayList2 = new ArrayList();
        for (ArrayList<Point> arrayList3 : arrayList) {
            ArrayList arrayList4 = new ArrayList();
            for (Point point : arrayList3) {
                arrayList4.add(m776a(new GeoPoint(point.f713y / 100, point.f712x / 100)));
            }
            arrayList2.add(arrayList4);
        }
        return arrayList2;
    }
}
