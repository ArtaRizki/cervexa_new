package com.baidu.mapapi.model;

import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapapi.model.inner.Point;
import com.baidu.platform.comapi.util.C0776b;
import com.baidu.platform.comjni.tools.C0785a;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CoordUtil {
    public static LatLng Coordinate_encryptEx(float f, float f2, String str) {
        return C0776b.m775a(f, f2, str);
    }

    public static LatLng decodeLocation(String str) {
        return C0776b.m777a(str);
    }

    public static List<LatLng> decodeLocationList(String str) {
        return C0776b.m782c(str);
    }

    public static List<List<LatLng>> decodeLocationList2D(String str) {
        return C0776b.m783d(str);
    }

    public static LatLng decodeNodeLocation(String str) {
        return C0776b.m780b(str);
    }

    public static double getDistance(Point point, Point point2) {
        return C0785a.m897a(point, point2);
    }

    public static int getMCDistanceByOneLatLngAndRadius(LatLng latLng, int i) {
        return C0776b.m774a(latLng, i);
    }

    public static GeoPoint ll2mc(LatLng latLng) {
        return C0776b.m778a(latLng);
    }

    public static Point ll2point(LatLng latLng) {
        return C0776b.m781b(latLng);
    }

    public static LatLng mc2ll(GeoPoint geoPoint) {
        return C0776b.m776a(geoPoint);
    }
}
