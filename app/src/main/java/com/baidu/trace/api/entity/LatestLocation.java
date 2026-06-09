package com.baidu.trace.api.entity;

import com.baidu.trace.model.CoordType;
import com.baidu.trace.model.Point;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class LatestLocation extends Point {

    /* JADX INFO: renamed from: h */
    private String f1414h;

    /* JADX INFO: renamed from: i */
    private double f1415i;

    /* JADX INFO: renamed from: j */
    private String f1416j;

    /* JADX INFO: renamed from: k */
    private Map<String, String> f1417k;

    public LatestLocation() {
    }

    public LatestLocation(CoordType coordType) {
        this.f1807b = coordType;
    }

    public LatestLocation(String str, double d) {
        this.f1414h = str;
        this.f1415i = d;
    }

    public Map<String, String> getColumns() {
        return this.f1417k;
    }

    public double getDistance() {
        return this.f1415i;
    }

    public String getFloor() {
        return this.f1414h;
    }

    public String getObjectName() {
        return this.f1416j;
    }

    public void setColumns(Map<String, String> map) {
        this.f1417k = map;
    }

    public void setDistance(double d) {
        this.f1415i = d;
    }

    public void setFloor(String str) {
        this.f1414h = str;
    }

    public void setObjectName(String str) {
        this.f1416j = str;
    }

    @Override // com.baidu.trace.model.Point
    public String toString() {
        return "LatestLocation [location=" + this.f1806a + ", coordType=" + this.f1807b + ", radius=" + this.f1808c + ", locTime=" + this.f1809d + ", direction=" + this.f1810e + ", speed=" + this.f1811f + ", height=" + this.f1812g + ", floor=" + this.f1414h + ", distance=" + this.f1415i + ", objectName=" + this.f1416j + ", columns=" + this.f1417k + "]";
    }
}
