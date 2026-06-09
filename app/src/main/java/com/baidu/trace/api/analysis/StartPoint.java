package com.baidu.trace.api.analysis;

import com.baidu.trace.model.CoordType;
import com.baidu.trace.model.Point;

/* JADX INFO: loaded from: classes.dex */
public class StartPoint extends Point {

    /* JADX INFO: renamed from: h */
    private String f1313h;

    public StartPoint() {
    }

    public StartPoint(CoordType coordType) {
        this.f1807b = coordType;
    }

    public StartPoint(String str) {
        this.f1313h = str;
    }

    public String getAddress() {
        return this.f1313h;
    }

    public void setAddress(String str) {
        this.f1313h = str;
    }

    @Override // com.baidu.trace.model.Point
    public String toString() {
        return "StartPoint [location=" + this.f1806a + ", coordType=" + this.f1807b + ", locTime=" + this.f1809d + ", address=" + this.f1313h + "]";
    }
}
