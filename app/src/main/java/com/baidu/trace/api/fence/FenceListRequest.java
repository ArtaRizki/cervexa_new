package com.baidu.trace.api.fence;

import com.baidu.trace.model.BaseRequest;
import com.baidu.trace.model.CoordType;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class FenceListRequest extends BaseRequest {

    /* JADX INFO: renamed from: a */
    private String f1467a;

    /* JADX INFO: renamed from: b */
    private List<Long> f1468b;

    /* JADX INFO: renamed from: c */
    private CoordType f1469c;

    /* JADX INFO: renamed from: d */
    private FenceType f1470d;

    private FenceListRequest(int i, long j, String str, List<Long> list, CoordType coordType, FenceType fenceType) {
        super(i, j);
        this.f1469c = CoordType.bd09ll;
        this.f1467a = str;
        this.f1468b = list;
        this.f1469c = coordType;
        this.f1470d = fenceType;
    }

    public static FenceListRequest buildLocalRequest(int i, long j, String str, List<Long> list) {
        return new FenceListRequest(i, j, str, list, CoordType.bd09ll, FenceType.local);
    }

    public static FenceListRequest buildServerRequest(int i, long j, String str, List<Long> list, CoordType coordType) {
        return new FenceListRequest(i, j, str, list, coordType, FenceType.server);
    }

    public final CoordType getCoordTypeOutput() {
        return this.f1469c;
    }

    public final List<Long> getFenceIds() {
        return this.f1468b;
    }

    public final FenceType getFenceType() {
        return this.f1470d;
    }

    public final String getMonitoredPerson() {
        return this.f1467a;
    }

    public final void setCoordTypeOutput(CoordType coordType) {
        if (FenceType.server == this.f1470d) {
            this.f1469c = coordType;
        }
    }

    public final void setFenceIds(List<Long> list) {
        this.f1468b = list;
    }

    public final void setMonitoredPerson(String str) {
        this.f1467a = str;
    }

    public final String toString() {
        return "FenceListRequest [tag=" + this.tag + ", serviceId=" + this.serviceId + ", monitoredPerson=" + this.f1467a + ", fenceIds=" + this.f1468b + ", coordTypeOutput=" + this.f1469c + ", fenceType=" + this.f1470d + "]";
    }
}
