package com.baidu.trace.api.fence;

import com.baidu.trace.model.CoordType;
import com.baidu.trace.model.LatLng;

/* JADX INFO: loaded from: classes.dex */
public class AlarmPoint {

    /* JADX INFO: renamed from: a */
    protected LatLng f1427a;

    /* JADX INFO: renamed from: b */
    protected CoordType f1428b;

    /* JADX INFO: renamed from: c */
    protected long f1429c;

    /* JADX INFO: renamed from: d */
    protected double f1430d;

    /* JADX INFO: renamed from: e */
    private long f1431e;

    public AlarmPoint() {
    }

    public AlarmPoint(LatLng latLng, CoordType coordType, long j, double d) {
        this.f1427a = latLng;
        this.f1428b = coordType;
        this.f1429c = j;
        this.f1430d = d;
    }

    public AlarmPoint(LatLng latLng, CoordType coordType, long j, long j2, double d) {
        this.f1427a = latLng;
        this.f1428b = coordType;
        this.f1429c = j;
        this.f1431e = j2;
        this.f1430d = d;
    }

    public CoordType getCoordType() {
        return this.f1428b;
    }

    public long getCreateTime() {
        return this.f1431e;
    }

    public long getLocTime() {
        return this.f1429c;
    }

    public LatLng getLocation() {
        return this.f1427a;
    }

    public double getRadius() {
        return this.f1430d;
    }

    public void setCoordType(CoordType coordType) {
        this.f1428b = coordType;
    }

    public void setCreateTime(long j) {
        this.f1431e = j;
    }

    public void setLocTime(long j) {
        this.f1429c = j;
    }

    public void setLocation(LatLng latLng) {
        this.f1427a = latLng;
    }

    public void setRadius(double d) {
        this.f1430d = d;
    }

    public String toString() {
        return "AlarmPoint [location=" + this.f1427a + ", coordType=" + this.f1428b + ", locTime=" + this.f1429c + ", createTime=" + this.f1431e + ", radius = " + this.f1430d + "]";
    }
}
