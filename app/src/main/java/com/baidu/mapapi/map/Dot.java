package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.EnumC0749h;

/* JADX INFO: loaded from: classes.dex */
public final class Dot extends Overlay {

    /* JADX INFO: renamed from: a */
    LatLng f310a;

    /* JADX INFO: renamed from: b */
    int f311b;

    /* JADX INFO: renamed from: c */
    int f312c;

    Dot() {
        this.type = EnumC0749h.dot;
    }

    @Override // com.baidu.mapapi.map.Overlay
    /* JADX INFO: renamed from: a */
    Bundle mo237a(Bundle bundle) {
        super.mo237a(bundle);
        GeoPoint geoPointLl2mc = CoordUtil.ll2mc(this.f310a);
        bundle.putDouble("location_x", geoPointLl2mc.getLongitudeE6());
        bundle.putDouble("location_y", geoPointLl2mc.getLatitudeE6());
        bundle.putInt("radius", this.f312c);
        Overlay.m338a(this.f311b, bundle);
        return bundle;
    }

    public LatLng getCenter() {
        return this.f310a;
    }

    public int getColor() {
        return this.f311b;
    }

    public int getRadius() {
        return this.f312c;
    }

    public void setCenter(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("dot center can not be null");
        }
        this.f310a = latLng;
        this.listener.mo342b(this);
    }

    public void setColor(int i) {
        this.f311b = i;
        this.listener.mo342b(this);
    }

    public void setRadius(int i) {
        if (i > 0) {
            this.f312c = i;
            this.listener.mo342b(this);
        }
    }
}
