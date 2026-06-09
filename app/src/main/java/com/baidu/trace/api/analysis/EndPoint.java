package com.baidu.trace.api.analysis;

import com.baidu.trace.model.CoordType;
import com.baidu.trace.model.Point;

/* JADX INFO: loaded from: classes.dex */
public class EndPoint extends Point {

    /* JADX INFO: renamed from: h */
    private String f1299h;

    public EndPoint() {
    }

    public EndPoint(CoordType coordType) {
        this.f1807b = coordType;
    }

    public EndPoint(String str) {
        this.f1299h = str;
    }

    public String getAddress() {
        return this.f1299h;
    }

    public void setAddress(String str) {
        this.f1299h = str;
    }

    @Override // com.baidu.trace.model.Point
    public String toString() {
        return "EndPoint [location=" + this.f1806a + ", coordType=" + this.f1807b + ", locTime=" + this.f1809d + ", address=" + this.f1299h + "]";
    }
}
