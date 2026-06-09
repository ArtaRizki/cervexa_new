package com.baidu.trace.api.entity;

import com.baidu.trace.model.CoordType;
import com.baidu.trace.model.LatLng;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class PolygonSearchRequest extends CommonRequest {

    /* JADX INFO: renamed from: f */
    private List<LatLng> f1418f;

    /* JADX INFO: renamed from: g */
    private CoordType f1419g;

    public PolygonSearchRequest() {
        this.f1419g = CoordType.bd09ll;
    }

    public PolygonSearchRequest(int i, long j, FilterCondition filterCondition, SortBy sortBy, CoordType coordType, List<LatLng> list, CoordType coordType2, int i2, int i3) {
        super(i, j, filterCondition, sortBy, coordType, i2, i3);
        this.f1419g = CoordType.bd09ll;
        this.f1418f = list;
        this.f1419g = coordType2;
    }

    public PolygonSearchRequest(int i, long j, FilterCondition filterCondition, CoordType coordType, List<LatLng> list, CoordType coordType2, int i2, int i3) {
        super(i, j, filterCondition, coordType, i2, i3);
        this.f1419g = CoordType.bd09ll;
        this.f1418f = list;
        this.f1419g = coordType2;
    }

    public PolygonSearchRequest(int i, long j, List<LatLng> list, CoordType coordType) {
        super(i, j);
        this.f1419g = CoordType.bd09ll;
        this.f1418f = list;
        this.f1419g = coordType;
    }

    public final CoordType getCoordTypeInput() {
        return this.f1419g;
    }

    public final List<LatLng> getVertexes() {
        return this.f1418f;
    }

    public final void setCoordTypeInput(CoordType coordType) {
        this.f1419g = coordType;
    }

    public final void setVertexes(List<LatLng> list) {
        this.f1418f = list;
    }

    @Override // com.baidu.trace.api.entity.CommonRequest
    public final String toString() {
        StringBuffer stringBuffer = new StringBuffer("PolygonSearchRequest{");
        stringBuffer.append("tag=");
        stringBuffer.append(this.tag);
        stringBuffer.append(", serviceId=");
        stringBuffer.append(this.serviceId);
        stringBuffer.append(", vertexes=");
        stringBuffer.append(this.f1418f);
        stringBuffer.append(", coordTypeInput=");
        stringBuffer.append(this.f1419g);
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
