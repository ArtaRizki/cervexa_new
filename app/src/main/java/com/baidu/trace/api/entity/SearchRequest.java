package com.baidu.trace.api.entity;

import com.baidu.trace.model.CoordType;

/* JADX INFO: loaded from: classes.dex */
public final class SearchRequest extends CommonRequest {

    /* JADX INFO: renamed from: f */
    private String f1421f;

    public SearchRequest() {
    }

    public SearchRequest(int i, long j) {
        super(i, j);
    }

    public SearchRequest(int i, long j, String str, FilterCondition filterCondition, SortBy sortBy, CoordType coordType, int i2, int i3) {
        super(i, j, filterCondition, sortBy, coordType, i2, i3);
        this.f1421f = str;
    }

    public SearchRequest(int i, long j, String str, FilterCondition filterCondition, CoordType coordType, int i2, int i3) {
        super(i, j, filterCondition, coordType, i2, i3);
        this.f1421f = str;
    }

    public final String getKeyword() {
        return this.f1421f;
    }

    public final void setKeyword(String str) {
        this.f1421f = str;
    }

    @Override // com.baidu.trace.api.entity.CommonRequest
    public final String toString() {
        StringBuffer stringBuffer = new StringBuffer("SearchRequest{");
        stringBuffer.append("tag='");
        stringBuffer.append(this.tag);
        stringBuffer.append(", serviceId='");
        stringBuffer.append(this.serviceId);
        stringBuffer.append(", keyword='");
        stringBuffer.append(this.f1421f);
        stringBuffer.append('\'');
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
