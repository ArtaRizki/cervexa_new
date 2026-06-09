package com.baidu.trace.api.entity;

import com.baidu.trace.model.CoordType;

/* JADX INFO: loaded from: classes.dex */
public final class EntityListRequest extends CommonRequest {

    /* JADX INFO: renamed from: f */
    private CoordType f1409f;

    public EntityListRequest() {
        this.f1409f = CoordType.bd09ll;
    }

    public EntityListRequest(int i, long j) {
        super(i, j);
        this.f1409f = CoordType.bd09ll;
    }

    public EntityListRequest(int i, long j, FilterCondition filterCondition, CoordType coordType, int i2, int i3) {
        super(i, j, filterCondition, coordType, i2, i3);
        this.f1409f = CoordType.bd09ll;
    }

    @Override // com.baidu.trace.api.entity.CommonRequest
    public final CoordType getCoordTypeOutput() {
        return this.f1409f;
    }

    @Override // com.baidu.trace.api.entity.CommonRequest
    public final void setCoordTypeOutput(CoordType coordType) {
        this.f1409f = coordType;
    }

    @Override // com.baidu.trace.api.entity.CommonRequest
    public final String toString() {
        return "EntityListRequest [tag=" + this.tag + ", serviceId=" + this.serviceId + ", coordTypeOutput=" + this.f1409f + ", filterCondition=" + this.f1391a + ", pageIndex=" + this.f1394d + ", pageSize=" + this.f1395e + "]";
    }
}
