package com.baidu.trace.model;

/* JADX INFO: loaded from: classes.dex */
public class Point {

    /* JADX INFO: renamed from: a */
    protected LatLng f1806a;

    /* JADX INFO: renamed from: b */
    protected CoordType f1807b;

    /* JADX INFO: renamed from: c */
    protected double f1808c;

    /* JADX INFO: renamed from: d */
    protected long f1809d;

    /* JADX INFO: renamed from: e */
    protected int f1810e;

    /* JADX INFO: renamed from: f */
    protected double f1811f;

    /* JADX INFO: renamed from: g */
    protected double f1812g;

    public Point() {
        this.f1807b = CoordType.bd09ll;
    }

    public Point(LatLng latLng, CoordType coordType) {
        this.f1807b = CoordType.bd09ll;
        this.f1806a = latLng;
        this.f1807b = coordType;
    }

    public CoordType getCoordType() {
        return this.f1807b;
    }

    public int getDirection() {
        return this.f1810e;
    }

    public double getHeight() {
        return this.f1812g;
    }

    public long getLocTime() {
        return this.f1809d;
    }

    public LatLng getLocation() {
        return this.f1806a;
    }

    public double getRadius() {
        return this.f1808c;
    }

    public double getSpeed() {
        return this.f1811f;
    }

    public void setCoordType(CoordType coordType) {
        this.f1807b = coordType;
    }

    public void setDirection(int i) {
        this.f1810e = i;
    }

    public void setHeight(double d) {
        this.f1812g = d;
    }

    public void setLocTime(long j) {
        this.f1809d = j;
    }

    public void setLocation(LatLng latLng) {
        this.f1806a = latLng;
    }

    public void setRadius(double d) {
        this.f1808c = d;
    }

    public void setSpeed(double d) {
        this.f1811f = d;
    }

    public String toString() {
        return "Point [location=" + this.f1806a + ", coordType=" + this.f1807b + ", radius=" + this.f1808c + ", locTime=" + this.f1809d + ", direction=" + this.f1810e + ", speed=" + this.f1811f + ", height=" + this.f1812g + "]";
    }
}
