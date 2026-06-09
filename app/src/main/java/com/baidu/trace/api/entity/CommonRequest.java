package com.baidu.trace.api.entity;

import com.baidu.trace.model.BaseRequest;
import com.baidu.trace.model.CoordType;

/* JADX INFO: loaded from: classes.dex */
public class CommonRequest extends BaseRequest {

    /* JADX INFO: renamed from: a */
    protected FilterCondition f1391a;

    /* JADX INFO: renamed from: b */
    protected SortBy f1392b;

    /* JADX INFO: renamed from: c */
    protected CoordType f1393c;

    /* JADX INFO: renamed from: d */
    protected int f1394d;

    /* JADX INFO: renamed from: e */
    protected int f1395e;

    public CommonRequest() {
        this.f1393c = CoordType.bd09ll;
        this.f1394d = 1;
        this.f1395e = 100;
    }

    public CommonRequest(int i, long j) {
        super(i, j);
        this.f1393c = CoordType.bd09ll;
        this.f1394d = 1;
        this.f1395e = 100;
    }

    public CommonRequest(int i, long j, FilterCondition filterCondition, SortBy sortBy, CoordType coordType, int i2, int i3) {
        super(i, j);
        this.f1393c = CoordType.bd09ll;
        this.f1394d = 1;
        this.f1395e = 100;
        this.f1391a = filterCondition;
        this.f1392b = sortBy;
        this.f1393c = coordType;
        this.f1394d = i2;
        this.f1395e = i3;
    }

    public CommonRequest(int i, long j, FilterCondition filterCondition, CoordType coordType, int i2, int i3) {
        super(i, j);
        this.f1393c = CoordType.bd09ll;
        this.f1394d = 1;
        this.f1395e = 100;
        this.f1391a = filterCondition;
        this.f1393c = coordType;
        this.f1394d = i2;
        this.f1395e = i3;
    }

    public CoordType getCoordTypeOutput() {
        return this.f1393c;
    }

    public FilterCondition getFilterCondition() {
        return this.f1391a;
    }

    public int getPageIndex() {
        return this.f1394d;
    }

    public int getPageSize() {
        return this.f1395e;
    }

    public SortBy getSortBy() {
        return this.f1392b;
    }

    public void setCoordTypeOutput(CoordType coordType) {
        this.f1393c = coordType;
    }

    public void setFilterCondition(FilterCondition filterCondition) {
        this.f1391a = filterCondition;
    }

    public void setPageIndex(int i) {
        this.f1394d = i;
    }

    public void setPageSize(int i) {
        this.f1395e = i;
    }

    public void setSortBy(SortBy sortBy) {
        this.f1392b = sortBy;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("CommonRequest{");
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
        stringBuffer.append(", pageIndex=");
        stringBuffer.append(this.f1394d);
        stringBuffer.append(", pageSize=");
        stringBuffer.append(this.f1395e);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
