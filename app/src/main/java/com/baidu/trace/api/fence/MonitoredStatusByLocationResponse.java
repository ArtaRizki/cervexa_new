package com.baidu.trace.api.fence;

import com.baidu.trace.model.BaseResponse;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class MonitoredStatusByLocationResponse extends BaseResponse {

    /* JADX INFO: renamed from: a */
    private int f1494a;

    /* JADX INFO: renamed from: b */
    private FenceType f1495b;

    /* JADX INFO: renamed from: c */
    private List<MonitoredStatusInfo> f1496c;

    public MonitoredStatusByLocationResponse(int i, int i2, String str, int i3, FenceType fenceType, List<MonitoredStatusInfo> list) {
        this(i, i2, str, fenceType);
        this.f1494a = i3;
        this.f1496c = list;
    }

    public MonitoredStatusByLocationResponse(int i, int i2, String str, FenceType fenceType) {
        super(i, i2, str);
        this.f1495b = fenceType;
    }

    public final FenceType getFenceType() {
        return this.f1495b;
    }

    public final List<MonitoredStatusInfo> getMonitoredStatusInfos() {
        return this.f1496c;
    }

    public final int getSize() {
        return this.f1494a;
    }

    public final void setFenceType(FenceType fenceType) {
        this.f1495b = fenceType;
    }

    public final void setMonitoredStatusInfos(List<MonitoredStatusInfo> list) {
        this.f1496c = list;
    }

    public final void setSize(int i) {
        this.f1494a = i;
    }

    public final String toString() {
        return "MonitoredStatusByLocationResponse [tag=" + this.tag + ", status=" + this.status + ", message=" + this.message + ", size=" + this.f1494a + ", fenceType=" + this.f1495b + ", monitoredStatusInfos=" + this.f1496c + "]";
    }
}
