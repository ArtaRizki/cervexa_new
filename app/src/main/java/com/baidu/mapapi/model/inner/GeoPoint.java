package com.baidu.mapapi.model.inner;

/* JADX INFO: loaded from: classes.dex */
public class GeoPoint {

    /* JADX INFO: renamed from: a */
    private double f710a;

    /* JADX INFO: renamed from: b */
    private double f711b;

    public GeoPoint(double d, double d2) {
        this.f710a = d;
        this.f711b = d2;
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        GeoPoint geoPoint = (GeoPoint) obj;
        return this.f710a == geoPoint.f710a && this.f711b == geoPoint.f711b;
    }

    public double getLatitudeE6() {
        return this.f710a;
    }

    public double getLongitudeE6() {
        return this.f711b;
    }

    public void setLatitudeE6(double d) {
        this.f710a = d;
    }

    public void setLongitudeE6(double d) {
        this.f711b = d;
    }

    public String toString() {
        return "GeoPoint: Latitude: " + this.f710a + ", Longitude: " + this.f711b;
    }
}
