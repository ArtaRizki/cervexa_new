package com.baidu.trace.api.analysis;

import com.baidu.trace.model.CoordType;
import com.baidu.trace.model.LatLng;
import com.baidu.trace.model.Point;

/* JADX INFO: loaded from: classes.dex */
public class HarshSteeringPoint extends Point {

    /* JADX INFO: renamed from: h */
    private double f1306h;

    /* JADX INFO: renamed from: i */
    private TurnType f1307i;

    /* JADX INFO: renamed from: j */
    private double f1308j;

    public HarshSteeringPoint() {
    }

    public HarshSteeringPoint(CoordType coordType) {
        this.f1807b = coordType;
    }

    public HarshSteeringPoint(LatLng latLng, CoordType coordType, long j, double d, TurnType turnType, double d2) {
        this.f1806a = latLng;
        this.f1807b = coordType;
        this.f1809d = j;
        this.f1306h = d;
        this.f1307i = turnType;
        this.f1308j = d2;
    }

    public double getCentripetalAcceleration() {
        return this.f1306h;
    }

    public double getTurnSpeed() {
        return this.f1308j;
    }

    public TurnType getTurnType() {
        return this.f1307i;
    }

    public void setCentripetalAcceleration(double d) {
        this.f1306h = d;
    }

    public void setTurnSpeed(double d) {
        this.f1308j = d;
    }

    public void setTurnType(TurnType turnType) {
        this.f1307i = turnType;
    }

    @Override // com.baidu.trace.model.Point
    public String toString() {
        return "HarshSteeringPoint [location=" + this.f1806a + ", coordType=" + this.f1807b + ", locTime=" + this.f1809d + ", centripetalAcceleration=" + this.f1306h + ", turnType=" + this.f1307i + ", turnSpeed=" + this.f1308j + "]";
    }
}
