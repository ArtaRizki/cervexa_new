package com.baidu.mapapi.map;

import android.graphics.Point;
import android.graphics.PointF;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.C0731E;
import com.baidu.platform.comapi.map.C0746e;

/* JADX INFO: loaded from: classes.dex */
public final class Projection {

    /* JADX INFO: renamed from: a */
    private C0746e f526a;

    Projection(C0746e c0746e) {
        this.f526a = c0746e;
    }

    public LatLng fromScreenLocation(Point point) {
        C0746e c0746e;
        if (point == null || (c0746e = this.f526a) == null) {
            return null;
        }
        return CoordUtil.mc2ll(c0746e.m655b(point.x, point.y));
    }

    public float metersToEquatorPixels(float f) {
        if (f <= 0.0f) {
            return 0.0f;
        }
        return (float) (((double) f) / this.f526a.m625I());
    }

    public PointF toOpenGLLocation(LatLng latLng, MapStatus mapStatus) {
        if (latLng == null || mapStatus == null) {
            return null;
        }
        GeoPoint geoPointLl2mc = CoordUtil.ll2mc(latLng);
        C0731E c0731e = mapStatus.f388a;
        return new PointF((float) ((geoPointLl2mc.getLongitudeE6() - c0731e.f848d) / c0731e.f858n), (float) ((geoPointLl2mc.getLatitudeE6() - c0731e.f849e) / c0731e.f858n));
    }

    public PointF toOpenGLNormalization(LatLng latLng, MapStatus mapStatus) {
        if (latLng == null || mapStatus == null) {
            return null;
        }
        GeoPoint geoPointLl2mc = CoordUtil.ll2mc(latLng);
        C0731E.a aVar = mapStatus.f388a.f855k;
        return new PointF((float) ((((geoPointLl2mc.getLongitudeE6() - aVar.f864a) * 2.0d) / Math.abs(aVar.f865b - aVar.f864a)) - 1.0d), (float) ((((geoPointLl2mc.getLatitudeE6() - aVar.f867d) * 2.0d) / Math.abs(aVar.f866c - aVar.f867d)) - 1.0d));
    }

    public Point toScreenLocation(LatLng latLng) {
        if (latLng == null || this.f526a == null) {
            return null;
        }
        return this.f526a.m633a(CoordUtil.ll2mc(latLng));
    }
}
