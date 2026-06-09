package com.baidu.trace.api.entity;

import com.baidu.trace.model.CoordType;
import com.baidu.trace.model.LatLng;

/* JADX INFO: loaded from: classes.dex */
public final class BoundSearchRequest extends CommonRequest {

    /* JADX INFO: renamed from: f */
    private LatLng f1388f;

    /* JADX INFO: renamed from: g */
    private LatLng f1389g;

    /* JADX INFO: renamed from: h */
    private CoordType f1390h;

    public BoundSearchRequest() {
        this.f1390h = CoordType.bd09ll;
    }

    public BoundSearchRequest(int i, long j) {
        super(i, j);
        this.f1390h = CoordType.bd09ll;
    }

    public BoundSearchRequest(int i, long j, FilterCondition filterCondition, CoordType coordType, int i2, int i3) {
        super(i, j, filterCondition, coordType, i2, i3);
        this.f1390h = CoordType.bd09ll;
    }

    public BoundSearchRequest(int i, long j, LatLng latLng, LatLng latLng2, CoordType coordType, FilterCondition filterCondition, SortBy sortBy, CoordType coordType2, int i2, int i3) {
        super(i, j, filterCondition, sortBy, coordType2, i2, i3);
        this.f1390h = CoordType.bd09ll;
        this.f1388f = latLng;
        this.f1389g = latLng2;
        this.f1390h = coordType;
    }

    public BoundSearchRequest(int i, long j, LatLng latLng, LatLng latLng2, CoordType coordType, FilterCondition filterCondition, CoordType coordType2, int i2, int i3) {
        super(i, j, filterCondition, coordType2, i2, i3);
        this.f1390h = CoordType.bd09ll;
        this.f1388f = latLng;
        this.f1389g = latLng2;
        this.f1390h = coordType;
    }

    public final CoordType getCoordTypeInput() {
        return this.f1390h;
    }

    @Override // com.baidu.trace.api.entity.CommonRequest
    public final FilterCondition getFilterCondition() {
        return this.f1391a;
    }

    public final LatLng getLowerLeft() {
        return this.f1388f;
    }

    public final LatLng getUpperRight() {
        return this.f1389g;
    }

    public final void setCoordTypeInput(CoordType coordType) {
        this.f1390h = coordType;
    }

    public final void setLowerLeft(LatLng latLng) {
        this.f1388f = latLng;
    }

    public final void setUpperRight(LatLng latLng) {
        this.f1389g = latLng;
    }

    @Override // com.baidu.trace.api.entity.CommonRequest
    public final String toString() {
        StringBuffer stringBuffer = new StringBuffer("BoundSearchRequest{");
        stringBuffer.append("tag=");
        stringBuffer.append(this.tag);
        stringBuffer.append(", serviceId=");
        stringBuffer.append(this.serviceId);
        stringBuffer.append(", lowerLeft=");
        stringBuffer.append(this.f1388f);
        stringBuffer.append(", upperRight=");
        stringBuffer.append(this.f1389g);
        stringBuffer.append(", coordTypeInput=");
        stringBuffer.append(this.f1390h);
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
