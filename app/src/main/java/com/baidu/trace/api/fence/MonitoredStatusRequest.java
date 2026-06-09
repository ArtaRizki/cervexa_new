package com.baidu.trace.api.fence;

import com.baidu.trace.model.BaseRequest;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class MonitoredStatusRequest extends BaseRequest {

    /* JADX INFO: renamed from: a */
    private String f1499a;

    /* JADX INFO: renamed from: b */
    private List<Long> f1500b;

    /* JADX INFO: renamed from: c */
    private FenceType f1501c;

    private MonitoredStatusRequest(int i, long j, String str, List<Long> list, FenceType fenceType) {
        super(i, j);
        this.f1499a = str;
        this.f1500b = list;
        this.f1501c = fenceType;
    }

    public static MonitoredStatusRequest buildLocalRequest(int i, long j, String str, List<Long> list) {
        return new MonitoredStatusRequest(i, j, str, list, FenceType.local);
    }

    public static MonitoredStatusRequest buildServerRequest(int i, long j, String str, List<Long> list) {
        return new MonitoredStatusRequest(i, j, str, list, FenceType.server);
    }

    public final List<Long> getFenceIds() {
        return this.f1500b;
    }

    public final FenceType getFenceType() {
        return this.f1501c;
    }

    public final String getMonitoredPerson() {
        return this.f1499a;
    }

    public final void setFenceIds(List<Long> list) {
        this.f1500b = list;
    }

    public final void setMonitoredPerson(String str) {
        this.f1499a = str;
    }

    public final String toString() {
        return "MonitoredStatusRequest [tag=" + this.tag + ", serviceId=" + this.serviceId + ", monitoredPerson=" + this.f1499a + ", fenceIds=" + this.f1500b + ", fenceType=" + this.f1501c + "]";
    }
}
