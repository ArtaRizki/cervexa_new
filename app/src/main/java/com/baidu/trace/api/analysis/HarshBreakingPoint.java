package com.baidu.trace.api.analysis;

import com.baidu.trace.model.CoordType;
import com.baidu.trace.model.LatLng;
import com.baidu.trace.model.Point;

/* JADX INFO: loaded from: classes.dex */
public class HarshBreakingPoint extends Point {

    /* JADX INFO: renamed from: h */
    private double f1303h;

    /* JADX INFO: renamed from: i */
    private double f1304i;

    /* JADX INFO: renamed from: j */
    private double f1305j;

    public HarshBreakingPoint() {
    }

    public HarshBreakingPoint(CoordType coordType) {
        this.f1807b = coordType;
    }

    public HarshBreakingPoint(LatLng latLng, CoordType coordType, long j, double d, double d2, double d3) {
        this.f1806a = latLng;
        this.f1807b = coordType;
        this.f1809d = j;
        this.f1303h = d;
        this.f1304i = d2;
        this.f1305j = d3;
    }

    public double getAcceleration() {
        return this.f1303h;
    }

    public double getEndSpeed() {
        return this.f1305j;
    }

    public double getInitialSpeed() {
        return this.f1304i;
    }

    public void setAcceleration(double d) {
        this.f1303h = d;
    }

    public void setEndSpeed(double d) {
        this.f1305j = d;
    }

    public void setInitialSpeed(double d) {
        this.f1304i = d;
    }

    @Override // com.baidu.trace.model.Point
    public String toString() {
        return "HarshBreakingPoint [location=" + this.f1806a + ", coordType=" + this.f1807b + ", locTime=" + this.f1809d + ", acceleration=" + this.f1303h + ", initialSpeed=" + this.f1304i + ", endSpeed=" + this.f1305j + "]";
    }
}
