package com.baidu.trace.api.track;

import com.baidu.trace.model.CoordType;
import com.baidu.trace.model.LatLng;
import com.baidu.trace.model.Point;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class LatestPoint extends Point {

    /* JADX INFO: renamed from: h */
    private String f1560h;

    /* JADX INFO: renamed from: i */
    private String f1561i;

    /* JADX INFO: renamed from: j */
    private Map<String, String> f1562j;

    public LatestPoint() {
    }

    public LatestPoint(LatLng latLng, CoordType coordType) {
        super(latLng, coordType);
    }

    public LatestPoint(LatLng latLng, CoordType coordType, String str, String str2, Map<String, String> map) {
        super(latLng, coordType);
        this.f1560h = str;
        this.f1561i = str2;
        this.f1562j = map;
    }

    public Map<String, String> getColumns() {
        return this.f1562j;
    }

    public String getFloor() {
        return this.f1560h;
    }

    public String getObjectName() {
        return this.f1561i;
    }

    public void setColumns(Map<String, String> map) {
        this.f1562j = map;
    }

    public void setFloor(String str) {
        this.f1560h = str;
    }

    public void setObjectName(String str) {
        this.f1561i = str;
    }

    @Override // com.baidu.trace.model.Point
    public String toString() {
        return "LatestPoint [location=" + this.f1806a + ", coordType=" + this.f1807b + ", radius=" + this.f1808c + ", locTime=" + this.f1809d + ", direction=" + this.f1810e + ", speed=" + this.f1811f + ", height=" + this.f1812g + ", floor=" + this.f1560h + ", objectName=" + this.f1561i + ", columns=" + this.f1562j + "]";
    }
}
