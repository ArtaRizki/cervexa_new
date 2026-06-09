package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.EnumC0749h;

/* JADX INFO: loaded from: classes.dex */
public final class GroundOverlay extends Overlay {

    /* JADX INFO: renamed from: j */
    private static final String f326j = GroundOverlay.class.getSimpleName();

    /* JADX INFO: renamed from: a */
    int f327a;

    /* JADX INFO: renamed from: b */
    BitmapDescriptor f328b;

    /* JADX INFO: renamed from: c */
    LatLng f329c;

    /* JADX INFO: renamed from: d */
    double f330d;

    /* JADX INFO: renamed from: e */
    double f331e;

    /* JADX INFO: renamed from: f */
    float f332f;

    /* JADX INFO: renamed from: g */
    float f333g;

    /* JADX INFO: renamed from: h */
    LatLngBounds f334h;

    /* JADX INFO: renamed from: i */
    float f335i;

    GroundOverlay() {
        this.type = EnumC0749h.ground;
    }

    @Override // com.baidu.mapapi.map.Overlay
    /* JADX INFO: renamed from: a */
    Bundle mo237a(Bundle bundle) {
        super.mo237a(bundle);
        bundle.putBundle("image_info", this.f328b.m280b());
        if (this.f327a == 1) {
            GeoPoint geoPointLl2mc = CoordUtil.ll2mc(this.f334h.southwest);
            double longitudeE6 = geoPointLl2mc.getLongitudeE6();
            double latitudeE6 = geoPointLl2mc.getLatitudeE6();
            GeoPoint geoPointLl2mc2 = CoordUtil.ll2mc(this.f334h.northeast);
            double longitudeE62 = geoPointLl2mc2.getLongitudeE6();
            double latitudeE62 = geoPointLl2mc2.getLatitudeE6();
            double d = longitudeE62 - longitudeE6;
            this.f330d = d;
            double d2 = latitudeE62 - latitudeE6;
            this.f331e = d2;
            this.f329c = CoordUtil.mc2ll(new GeoPoint(latitudeE6 + (d2 / 2.0d), longitudeE6 + (d / 2.0d)));
            this.f332f = 0.5f;
            this.f333g = 0.5f;
        }
        double d3 = this.f330d;
        if (d3 <= 0.0d || this.f331e <= 0.0d) {
            throw new IllegalStateException("when you add ground overlay, the width and height must greater than 0");
        }
        bundle.putDouble("x_distance", d3);
        if (this.f331e == 2.147483647E9d) {
            this.f331e = (int) ((this.f330d * ((double) this.f328b.f295a.getHeight())) / ((double) this.f328b.f295a.getWidth()));
        }
        bundle.putDouble("y_distance", this.f331e);
        GeoPoint geoPointLl2mc3 = CoordUtil.ll2mc(this.f329c);
        bundle.putDouble("location_x", geoPointLl2mc3.getLongitudeE6());
        bundle.putDouble("location_y", geoPointLl2mc3.getLatitudeE6());
        bundle.putFloat("anchor_x", this.f332f);
        bundle.putFloat("anchor_y", this.f333g);
        bundle.putFloat("transparency", this.f335i);
        return bundle;
    }

    public float getAnchorX() {
        return this.f332f;
    }

    public float getAnchorY() {
        return this.f333g;
    }

    public LatLngBounds getBounds() {
        return this.f334h;
    }

    public double getHeight() {
        return this.f331e;
    }

    public BitmapDescriptor getImage() {
        return this.f328b;
    }

    public LatLng getPosition() {
        return this.f329c;
    }

    public float getTransparency() {
        return this.f335i;
    }

    public double getWidth() {
        return this.f330d;
    }

    public void setAnchor(float f, float f2) {
        if (f < 0.0f || f > 1.0f || f2 < 0.0f || f2 > 1.0f) {
            return;
        }
        this.f332f = f;
        this.f333g = f2;
        this.listener.mo342b(this);
    }

    public void setDimensions(int i) {
        this.f330d = i;
        this.f331e = 2.147483647E9d;
        this.listener.mo342b(this);
    }

    public void setDimensions(int i, int i2) {
        this.f330d = i;
        this.f331e = i2;
        this.listener.mo342b(this);
    }

    public void setImage(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor == null) {
            throw new IllegalArgumentException("image can not be null");
        }
        this.f328b = bitmapDescriptor;
        this.listener.mo342b(this);
    }

    public void setPosition(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("position can not be null");
        }
        this.f327a = 2;
        this.f329c = latLng;
        this.listener.mo342b(this);
    }

    public void setPositionFromBounds(LatLngBounds latLngBounds) {
        if (latLngBounds == null) {
            throw new IllegalArgumentException("bounds can not be null");
        }
        this.f327a = 1;
        this.f334h = latLngBounds;
        this.listener.mo342b(this);
    }

    public void setTransparency(float f) {
        if (f > 1.0f || f < 0.0f) {
            return;
        }
        this.f335i = f;
        this.listener.mo342b(this);
    }
}
