package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.EnumC0749h;

/* JADX INFO: loaded from: classes.dex */
public final class Circle extends Overlay {

    /* JADX INFO: renamed from: a */
    LatLng f298a;

    /* JADX INFO: renamed from: b */
    int f299b;

    /* JADX INFO: renamed from: c */
    int f300c;

    /* JADX INFO: renamed from: d */
    Stroke f301d;

    Circle() {
        this.type = EnumC0749h.circle;
    }

    @Override // com.baidu.mapapi.map.Overlay
    /* JADX INFO: renamed from: a */
    Bundle mo237a(Bundle bundle) {
        super.mo237a(bundle);
        GeoPoint geoPointLl2mc = CoordUtil.ll2mc(this.f298a);
        bundle.putDouble("location_x", geoPointLl2mc.getLongitudeE6());
        bundle.putDouble("location_y", geoPointLl2mc.getLatitudeE6());
        bundle.putInt("radius", CoordUtil.getMCDistanceByOneLatLngAndRadius(this.f298a, this.f300c));
        Overlay.m338a(this.f299b, bundle);
        if (this.f301d == null) {
            bundle.putInt("has_stroke", 0);
        } else {
            bundle.putInt("has_stroke", 1);
            bundle.putBundle("stroke", this.f301d.m347a(new Bundle()));
        }
        return bundle;
    }

    public LatLng getCenter() {
        return this.f298a;
    }

    public int getFillColor() {
        return this.f299b;
    }

    public int getRadius() {
        return this.f300c;
    }

    public Stroke getStroke() {
        return this.f301d;
    }

    public void setCenter(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("circle center can not be null");
        }
        this.f298a = latLng;
        this.listener.mo342b(this);
    }

    public void setFillColor(int i) {
        this.f299b = i;
        this.listener.mo342b(this);
    }

    public void setRadius(int i) {
        this.f300c = i;
        this.listener.mo342b(this);
    }

    public void setStroke(Stroke stroke) {
        this.f301d = stroke;
        this.listener.mo342b(this);
    }
}
