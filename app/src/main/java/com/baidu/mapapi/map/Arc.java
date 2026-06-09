package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.EnumC0749h;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class Arc extends Overlay {

    /* JADX INFO: renamed from: f */
    private static final String f223f = Arc.class.getSimpleName();

    /* JADX INFO: renamed from: a */
    int f224a;

    /* JADX INFO: renamed from: b */
    int f225b;

    /* JADX INFO: renamed from: c */
    LatLng f226c;

    /* JADX INFO: renamed from: d */
    LatLng f227d;

    /* JADX INFO: renamed from: e */
    LatLng f228e;

    Arc() {
        this.type = EnumC0749h.arc;
    }

    @Override // com.baidu.mapapi.map.Overlay
    /* JADX INFO: renamed from: a */
    Bundle mo237a(Bundle bundle) {
        super.mo237a(bundle);
        ArrayList arrayList = new ArrayList();
        arrayList.clear();
        arrayList.add(this.f226c);
        arrayList.add(this.f227d);
        arrayList.add(this.f228e);
        GeoPoint geoPointLl2mc = CoordUtil.ll2mc((LatLng) arrayList.get(0));
        bundle.putDouble("location_x", geoPointLl2mc.getLongitudeE6());
        bundle.putDouble("location_y", geoPointLl2mc.getLatitudeE6());
        bundle.putInt("width", this.f225b);
        Overlay.m339a(arrayList, bundle);
        Overlay.m338a(this.f224a, bundle);
        return bundle;
    }

    public int getColor() {
        return this.f224a;
    }

    public LatLng getEndPoint() {
        return this.f228e;
    }

    public LatLng getMiddlePoint() {
        return this.f227d;
    }

    public LatLng getStartPoint() {
        return this.f226c;
    }

    public int getWidth() {
        return this.f225b;
    }

    public void setColor(int i) {
        this.f224a = i;
        this.listener.mo342b(this);
    }

    public void setPoints(LatLng latLng, LatLng latLng2, LatLng latLng3) {
        if (latLng == null || latLng2 == null || latLng3 == null) {
            throw new IllegalArgumentException("start and middle and end points can not be null");
        }
        if (latLng == latLng2 || latLng == latLng3 || latLng2 == latLng3) {
            throw new IllegalArgumentException("start and middle and end points can not be same");
        }
        this.f226c = latLng;
        this.f227d = latLng2;
        this.f228e = latLng3;
        this.listener.mo342b(this);
    }

    public void setWidth(int i) {
        if (i > 0) {
            this.f225b = i;
            this.listener.mo342b(this);
        }
    }
}
