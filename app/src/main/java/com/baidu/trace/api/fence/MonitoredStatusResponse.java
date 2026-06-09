package com.baidu.trace.api.fence;

import com.baidu.trace.model.BaseResponse;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class MonitoredStatusResponse extends BaseResponse {

    /* JADX INFO: renamed from: a */
    private int f1502a;

    /* JADX INFO: renamed from: b */
    private FenceType f1503b;

    /* JADX INFO: renamed from: c */
    private List<MonitoredStatusInfo> f1504c;

    public MonitoredStatusResponse(int i, int i2, String str, int i3, FenceType fenceType, List<MonitoredStatusInfo> list) {
        this(i, i2, str, fenceType);
        this.f1502a = i3;
        this.f1504c = list;
    }

    public MonitoredStatusResponse(int i, int i2, String str, FenceType fenceType) {
        super(i, i2, str);
        this.f1503b = fenceType;
    }

    public final FenceType getFenceType() {
        return this.f1503b;
    }

    public final List<MonitoredStatusInfo> getMonitoredStatusInfos() {
        return this.f1504c;
    }

    public final int getSize() {
        return this.f1502a;
    }

    public final void setFenceType(FenceType fenceType) {
        this.f1503b = fenceType;
    }

    public final void setMonitoredStatusInfos(List<MonitoredStatusInfo> list) {
        this.f1504c = list;
    }

    public final void setSize(int i) {
        this.f1502a = i;
    }

    public final String toString() {
        return "MonitoredStatusResponse [tag=" + this.tag + ", status=" + this.status + ", message=" + this.message + ", size=" + this.f1502a + ", fenceType=" + this.f1503b + ", monitoredStatusInfos=" + this.f1504c + "]";
    }
}
