package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.EnumC0749h;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class Polygon extends Overlay {

    /* JADX INFO: renamed from: a */
    Stroke f494a;

    /* JADX INFO: renamed from: b */
    int f495b;

    /* JADX INFO: renamed from: c */
    List<LatLng> f496c;

    Polygon() {
        this.type = EnumC0749h.polygon;
    }

    @Override // com.baidu.mapapi.map.Overlay
    /* JADX INFO: renamed from: a */
    Bundle mo237a(Bundle bundle) {
        super.mo237a(bundle);
        GeoPoint geoPointLl2mc = CoordUtil.ll2mc(this.f496c.get(0));
        bundle.putDouble("location_x", geoPointLl2mc.getLongitudeE6());
        bundle.putDouble("location_y", geoPointLl2mc.getLatitudeE6());
        Overlay.m339a(this.f496c, bundle);
        Overlay.m338a(this.f495b, bundle);
        if (this.f494a == null) {
            bundle.putInt("has_stroke", 0);
        } else {
            bundle.putInt("has_stroke", 1);
            bundle.putBundle("stroke", this.f494a.m347a(new Bundle()));
        }
        return bundle;
    }

    public int getFillColor() {
        return this.f495b;
    }

    public List<LatLng> getPoints() {
        return this.f496c;
    }

    public Stroke getStroke() {
        return this.f494a;
    }

    public void setFillColor(int i) {
        this.f495b = i;
        this.listener.mo342b(this);
    }

    public void setPoints(List<LatLng> list) {
        if (list == null) {
            throw new IllegalArgumentException("points list can not be null");
        }
        if (list.size() <= 2) {
            throw new IllegalArgumentException("points count can not less than three");
        }
        if (list.contains(null)) {
            throw new IllegalArgumentException("points list can not contains null");
        }
        int i = 0;
        while (i < list.size()) {
            int i2 = i + 1;
            for (int i3 = i2; i3 < list.size(); i3++) {
                if (list.get(i) == list.get(i3)) {
                    throw new IllegalArgumentException("points list can not has same points");
                }
            }
            i = i2;
        }
        this.f496c = list;
        this.listener.mo342b(this);
    }

    public void setStroke(Stroke stroke) {
        this.f494a = stroke;
        this.listener.mo342b(this);
    }
}
