package com.baidu.trace.api.analysis;

import com.baidu.trace.model.CoordType;
import com.baidu.trace.model.LatLng;
import com.baidu.trace.model.Point;

/* JADX INFO: loaded from: classes.dex */
public class SpeedingPoint extends Point {

    /* JADX INFO: renamed from: h */
    private double f1311h;

    /* JADX INFO: renamed from: i */
    private double f1312i;

    public SpeedingPoint() {
    }

    public SpeedingPoint(CoordType coordType) {
        this.f1807b = coordType;
    }

    public SpeedingPoint(LatLng latLng, CoordType coordType, long j, double d, double d2) {
        this.f1806a = latLng;
        this.f1807b = coordType;
        this.f1809d = j;
        this.f1311h = d;
        this.f1312i = d2;
    }

    public double getActualSpeed() {
        return this.f1311h;
    }

    public double getLimitSpeed() {
        return this.f1312i;
    }

    public void setActualSpeed(double d) {
        this.f1311h = d;
    }

    public void setLimitSpeed(double d) {
        this.f1312i = d;
    }

    @Override // com.baidu.trace.model.Point
    public String toString() {
        return "SpeedingPoint [location=" + this.f1806a + ", coordType=" + this.f1807b + ", locTime=" + this.f1809d + ", actualSpeed=" + this.f1311h + ", limitSpeed=" + this.f1312i + "]";
    }
}
