package com.baidu.mapapi.map;

import android.graphics.Point;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.C0746e;

/* JADX INFO: loaded from: classes.dex */
public final class MapStatusUpdate {

    /* JADX INFO: renamed from: l */
    private static final String f399l = MapStatusUpdate.class.getSimpleName();

    /* JADX INFO: renamed from: a */
    int f400a;

    /* JADX INFO: renamed from: b */
    MapStatus f401b;

    /* JADX INFO: renamed from: c */
    LatLng f402c;

    /* JADX INFO: renamed from: d */
    LatLngBounds f403d;

    /* JADX INFO: renamed from: e */
    int f404e;

    /* JADX INFO: renamed from: f */
    int f405f;

    /* JADX INFO: renamed from: g */
    float f406g;

    /* JADX INFO: renamed from: h */
    int f407h;

    /* JADX INFO: renamed from: i */
    int f408i;

    /* JADX INFO: renamed from: j */
    float f409j;

    /* JADX INFO: renamed from: k */
    Point f410k;

    MapStatusUpdate() {
    }

    MapStatusUpdate(int i) {
        this.f400a = i;
    }

    /* JADX INFO: renamed from: a */
    MapStatus m319a(C0746e c0746e, MapStatus mapStatus) {
        switch (this.f400a) {
            case 1:
                return this.f401b;
            case 2:
                return new MapStatus(mapStatus.rotate, this.f402c, mapStatus.overlook, mapStatus.zoom, mapStatus.targetScreen, null);
            case 3:
                GeoPoint geoPointLl2mc = CoordUtil.ll2mc(this.f403d.southwest);
                GeoPoint geoPointLl2mc2 = CoordUtil.ll2mc(this.f403d.northeast);
                float fM630a = c0746e.m630a((int) geoPointLl2mc.getLongitudeE6(), (int) geoPointLl2mc2.getLatitudeE6(), (int) geoPointLl2mc2.getLongitudeE6(), (int) geoPointLl2mc.getLatitudeE6(), mapStatus.f388a.f854j.right - mapStatus.f388a.f854j.left, mapStatus.f388a.f854j.bottom - mapStatus.f388a.f854j.top);
                return new MapStatus(mapStatus.rotate, this.f403d.getCenter(), mapStatus.overlook, fM630a, mapStatus.targetScreen, null);
            case 4:
                return new MapStatus(mapStatus.rotate, this.f402c, mapStatus.overlook, this.f406g, mapStatus.targetScreen, null);
            case 5:
                c0746e.m622F();
                GeoPoint geoPointM655b = c0746e.m655b((c0746e.m622F() / 2) + this.f407h, (c0746e.m623G() / 2) + this.f408i);
                return new MapStatus(mapStatus.rotate, CoordUtil.mc2ll(geoPointM655b), mapStatus.overlook, mapStatus.zoom, mapStatus.targetScreen, geoPointM655b.getLongitudeE6(), geoPointM655b.getLatitudeE6(), null);
            case 6:
                return new MapStatus(mapStatus.rotate, mapStatus.target, mapStatus.overlook, mapStatus.zoom + this.f409j, mapStatus.targetScreen, mapStatus.m315a(), mapStatus.m316b(), null);
            case 7:
                LatLng latLngMc2ll = CoordUtil.mc2ll(c0746e.m655b(this.f410k.x, this.f410k.y));
                return new MapStatus(mapStatus.rotate, latLngMc2ll, mapStatus.overlook, this.f409j + mapStatus.zoom, this.f410k, null);
            case 8:
                return new MapStatus(mapStatus.rotate, mapStatus.target, mapStatus.overlook, this.f406g, mapStatus.targetScreen, mapStatus.m315a(), mapStatus.m316b(), null);
            case 9:
                GeoPoint geoPointLl2mc3 = CoordUtil.ll2mc(this.f403d.southwest);
                GeoPoint geoPointLl2mc4 = CoordUtil.ll2mc(this.f403d.northeast);
                float fM630a2 = c0746e.m630a((int) geoPointLl2mc3.getLongitudeE6(), (int) geoPointLl2mc4.getLatitudeE6(), (int) geoPointLl2mc4.getLongitudeE6(), (int) geoPointLl2mc3.getLatitudeE6(), this.f404e, this.f405f);
                return new MapStatus(mapStatus.rotate, this.f403d.getCenter(), mapStatus.overlook, fM630a2, mapStatus.targetScreen, null);
            default:
                return null;
        }
    }
}
