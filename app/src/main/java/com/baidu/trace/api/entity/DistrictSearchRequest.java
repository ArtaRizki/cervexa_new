package com.baidu.trace.api.entity;

import com.baidu.trace.model.CoordType;

/* JADX INFO: loaded from: classes.dex */
public final class DistrictSearchRequest extends CommonRequest {

    /* JADX INFO: renamed from: f */
    private String f1400f;

    /* JADX INFO: renamed from: g */
    private ReturnType f1401g;

    public DistrictSearchRequest() {
        this.f1401g = ReturnType.all;
    }

    public DistrictSearchRequest(int i, long j, FilterCondition filterCondition, SortBy sortBy, CoordType coordType, String str, ReturnType returnType, int i2, int i3) {
        super(i, j, filterCondition, sortBy, coordType, i2, i3);
        this.f1401g = ReturnType.all;
        this.f1400f = str;
        this.f1401g = returnType;
    }

    public DistrictSearchRequest(int i, long j, FilterCondition filterCondition, CoordType coordType, String str, ReturnType returnType, int i2, int i3) {
        super(i, j, filterCondition, coordType, i2, i3);
        this.f1401g = ReturnType.all;
        this.f1400f = str;
        this.f1401g = returnType;
    }

    public DistrictSearchRequest(int i, long j, String str, ReturnType returnType) {
        super(i, j);
        this.f1401g = ReturnType.all;
        this.f1400f = str;
        this.f1401g = returnType;
    }

    public final String getKeyword() {
        return this.f1400f;
    }

    public final ReturnType getReturnType() {
        return this.f1401g;
    }

    public final void setKeyword(String str) {
        this.f1400f = str;
    }

    public final void setReturnType(ReturnType returnType) {
        this.f1401g = returnType;
    }

    @Override // com.baidu.trace.api.entity.CommonRequest
    public final String toString() {
        StringBuffer stringBuffer = new StringBuffer("DistrictSearchRequest{");
        stringBuffer.append("tag=");
        stringBuffer.append(this.tag);
        stringBuffer.append(", serviceId=");
        stringBuffer.append(this.serviceId);
        stringBuffer.append(", filterCondition=");
        stringBuffer.append(this.f1391a);
        stringBuffer.append(", sortBy=");
        stringBuffer.append(this.f1392b);
        stringBuffer.append(", coordTypeOutput=");
        stringBuffer.append(this.f1393c);
        stringBuffer.append(", keyword='");
        stringBuffer.append(this.f1400f);
        stringBuffer.append("'");
        stringBuffer.append(", returnType=");
        stringBuffer.append(this.f1401g);
        stringBuffer.append(", pageIndex=");
        stringBuffer.append(this.f1394d);
        stringBuffer.append(", pageSize=");
        stringBuffer.append(this.f1395e);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
