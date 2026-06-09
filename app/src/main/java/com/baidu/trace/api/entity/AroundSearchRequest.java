package com.baidu.trace.api.entity;

import com.baidu.trace.model.CoordType;
import com.baidu.trace.model.LatLng;

/* JADX INFO: loaded from: classes.dex */
public final class AroundSearchRequest extends CommonRequest {

    /* JADX INFO: renamed from: f */
    private LatLng f1385f;

    /* JADX INFO: renamed from: g */
    private double f1386g;

    /* JADX INFO: renamed from: h */
    private CoordType f1387h;

    public AroundSearchRequest() {
        this.f1387h = CoordType.bd09ll;
    }

    public AroundSearchRequest(int i, long j) {
        super(i, j);
        this.f1387h = CoordType.bd09ll;
    }

    public AroundSearchRequest(int i, long j, FilterCondition filterCondition, CoordType coordType, int i2, int i3) {
        super(i, j, filterCondition, coordType, i2, i3);
        this.f1387h = CoordType.bd09ll;
    }

    public AroundSearchRequest(int i, long j, LatLng latLng, double d, CoordType coordType, FilterCondition filterCondition, SortBy sortBy, CoordType coordType2, int i2, int i3) {
        super(i, j, filterCondition, sortBy, coordType2, i2, i3);
        this.f1387h = CoordType.bd09ll;
        this.f1385f = latLng;
        this.f1386g = d;
        this.f1387h = coordType;
    }

    public AroundSearchRequest(int i, long j, LatLng latLng, double d, CoordType coordType, FilterCondition filterCondition, CoordType coordType2, int i2, int i3) {
        super(i, j, filterCondition, coordType2, i2, i3);
        this.f1387h = CoordType.bd09ll;
        this.f1385f = latLng;
        this.f1386g = d;
        this.f1387h = coordType;
    }

    public final LatLng getCenter() {
        return this.f1385f;
    }

    public final CoordType getCoordTypeInput() {
        return this.f1387h;
    }

    public final double getRadius() {
        return this.f1386g;
    }

    public final void setCenter(LatLng latLng) {
        this.f1385f = latLng;
    }

    public final void setCoordTypeInput(CoordType coordType) {
        this.f1387h = coordType;
    }

    public final void setRadius(double d) {
        this.f1386g = d;
    }

    @Override // com.baidu.trace.api.entity.CommonRequest
    public final String toString() {
        StringBuffer stringBuffer = new StringBuffer("AroundSearchRequest{");
        stringBuffer.append("tag=");
        stringBuffer.append(this.tag);
        stringBuffer.append(", serviceId=");
        stringBuffer.append(this.serviceId);
        stringBuffer.append(", center=");
        stringBuffer.append(this.f1385f);
        stringBuffer.append(", radius=");
        stringBuffer.append(this.f1386g);
        stringBuffer.append(", coordTypeInput=");
        stringBuffer.append(this.f1387h);
        stringBuffer.append(", filterCondition=");
        stringBuffer.append(this.f1391a);
        stringBuffer.append(", sortBy=");
        stringBuffer.append(this.f1392b);
        stringBuffer.append(", coordTypeOutput=");
        stringBuffer.append(this.f1393c);
        stringBuffer.append(", pageIndex=");
        stringBuffer.append(this.f1394d);
        stringBuffer.append(", pageSize=");
        stringBuffer.append(this.f1395e);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
