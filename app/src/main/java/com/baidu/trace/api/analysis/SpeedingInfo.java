package com.baidu.trace.api.analysis;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SpeedingInfo {

    /* JADX INFO: renamed from: a */
    private double f1309a;

    /* JADX INFO: renamed from: b */
    private List<SpeedingPoint> f1310b;

    public SpeedingInfo() {
    }

    public SpeedingInfo(double d, List<SpeedingPoint> list) {
        this.f1309a = d;
        this.f1310b = list;
    }

    public double getDistance() {
        return this.f1309a;
    }

    public List<SpeedingPoint> getPoints() {
        return this.f1310b;
    }

    public void setDistance(double d) {
        this.f1309a = d;
    }

    public void setPoints(List<SpeedingPoint> list) {
        this.f1310b = list;
    }

    public String toString() {
        return "SpeedingInfo [distance=" + this.f1309a + ", points=" + this.f1310b + "]";
    }
}
