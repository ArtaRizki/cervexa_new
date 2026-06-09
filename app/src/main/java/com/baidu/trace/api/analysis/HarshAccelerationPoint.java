package com.baidu.trace.api.analysis;

import com.baidu.trace.model.CoordType;
import com.baidu.trace.model.LatLng;
import com.baidu.trace.model.Point;

/* JADX INFO: loaded from: classes.dex */
public class HarshAccelerationPoint extends Point {

    /* JADX INFO: renamed from: h */
    private double f1300h;

    /* JADX INFO: renamed from: i */
    private double f1301i;

    /* JADX INFO: renamed from: j */
    private double f1302j;

    public HarshAccelerationPoint() {
    }

    public HarshAccelerationPoint(CoordType coordType) {
        this.f1807b = coordType;
    }

    public HarshAccelerationPoint(LatLng latLng, CoordType coordType, long j, double d, double d2, double d3) {
        this.f1806a = latLng;
        this.f1807b = coordType;
        this.f1809d = j;
        this.f1300h = d;
        this.f1301i = d2;
        this.f1302j = d3;
    }

    public double getAcceleration() {
        return this.f1300h;
    }

    public double getEndSpeed() {
        return this.f1302j;
    }

    public double getInitialSpeed() {
        return this.f1301i;
    }

    public void setAcceleration(double d) {
        this.f1300h = d;
    }

    public void setEndSpeed(double d) {
        this.f1302j = d;
    }

    public void setInitialSpeed(double d) {
        this.f1301i = d;
    }

    @Override // com.baidu.trace.model.Point
    public String toString() {
        return "HarshAccelerationPoint [location=" + this.f1806a + ", coordType=" + this.f1807b + ", locTime=" + this.f1809d + ", acceleration=" + this.f1300h + ", initialSpeed=" + this.f1301i + ", endSpeed=" + this.f1302j + "]";
    }
}
