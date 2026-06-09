package com.baidu.trace.api.track;

import com.baidu.trace.model.CoordType;
import com.baidu.trace.model.Point;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class TrackPoint extends Point {

    /* JADX INFO: renamed from: h */
    private String f1572h;

    /* JADX INFO: renamed from: i */
    private String f1573i;

    /* JADX INFO: renamed from: j */
    private Map<String, String> f1574j;

    public TrackPoint() {
    }

    public TrackPoint(CoordType coordType) {
        this.f1807b = coordType;
    }

    public TrackPoint(String str, String str2, Map<String, String> map) {
        this.f1572h = str;
        this.f1573i = str2;
        this.f1574j = map;
    }

    public Map<String, String> getColumns() {
        return this.f1574j;
    }

    public String getCreateTime() {
        return this.f1572h;
    }

    public String getObjectName() {
        return this.f1573i;
    }

    public void setColumns(Map<String, String> map) {
        this.f1574j = map;
    }

    public void setCreateTime(String str) {
        this.f1572h = str;
    }

    public void setObjectName(String str) {
        this.f1573i = str;
    }

    @Override // com.baidu.trace.model.Point
    public String toString() {
        return "TrackPoint [location=" + this.f1806a + ", coordType=" + this.f1807b + ", radius=" + this.f1808c + ", locTime=" + this.f1809d + ", direction=" + this.f1810e + ", speed=" + this.f1811f + ", height=" + this.f1812g + ", createTime=" + this.f1572h + ", objectName=" + this.f1573i + ", columns=" + this.f1574j + "]";
    }
}
