package com.baidu.mapapi.map;

import android.graphics.Point;
import com.baidu.mapapi.map.C0689l;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;

/* JADX INFO: loaded from: classes.dex */
public class WeightedLatLng extends C0689l.a {
    public static final double DEFAULT_INTENSITY = 1.0d;

    /* JADX INFO: renamed from: a */
    private Point f657a;
    public final double intensity;
    public final LatLng latLng;

    public WeightedLatLng(LatLng latLng) {
        this(latLng, 1.0d);
    }

    public WeightedLatLng(LatLng latLng, double d) {
        if (latLng == null) {
            throw new IllegalArgumentException("latLng can not be null");
        }
        this.latLng = latLng;
        GeoPoint geoPointLl2mc = CoordUtil.ll2mc(latLng);
        this.f657a = new Point((int) geoPointLl2mc.getLongitudeE6(), (int) geoPointLl2mc.getLatitudeE6());
        if (d > 0.0d) {
            this.intensity = d;
        } else {
            this.intensity = 1.0d;
        }
    }

    @Override // com.baidu.mapapi.map.C0689l.a
    /* JADX INFO: renamed from: a */
    Point mo408a() {
        return this.f657a;
    }
}
