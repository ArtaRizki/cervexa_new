package com.baidu.trace.api.analysis;

import com.baidu.trace.model.CoordType;
import com.baidu.trace.model.LatLng;
import com.baidu.trace.model.Point;

/* JADX INFO: loaded from: classes.dex */
public class StayPoint extends Point {

    /* JADX INFO: renamed from: h */
    private long f1314h;

    /* JADX INFO: renamed from: i */
    private long f1315i;

    /* JADX INFO: renamed from: j */
    private int f1316j;

    public StayPoint() {
    }

    public StayPoint(long j, long j2, int i, LatLng latLng, CoordType coordType) {
        this.f1314h = j;
        this.f1315i = j2;
        this.f1316j = i;
        this.f1806a = latLng;
        this.f1807b = coordType;
    }

    public int getDuration() {
        return this.f1316j;
    }

    public long getEndTime() {
        return this.f1315i;
    }

    @Override // com.baidu.trace.model.Point
    public LatLng getLocation() {
        return this.f1806a;
    }

    public long getStartTime() {
        return this.f1314h;
    }

    public void setDuration(int i) {
        this.f1316j = i;
    }

    public void setEndTime(long j) {
        this.f1315i = j;
    }

    @Override // com.baidu.trace.model.Point
    public void setLocation(LatLng latLng) {
        this.f1806a = latLng;
    }

    public void setStartTime(long j) {
        this.f1314h = j;
    }

    @Override // com.baidu.trace.model.Point
    public String toString() {
        return "StayPoint [startTime=" + this.f1314h + ", endTime=" + this.f1315i + ", duration=" + this.f1316j + ", location=" + this.f1806a + ", coordType=" + this.f1807b + "]";
    }
}
