package com.baidu.trace.api.fence;

/* JADX INFO: loaded from: classes.dex */
public final class MonitoredStatusInfo {

    /* JADX INFO: renamed from: a */
    private long f1497a;

    /* JADX INFO: renamed from: b */
    private MonitoredStatus f1498b;

    public MonitoredStatusInfo() {
        this.f1498b = MonitoredStatus.unknown;
    }

    public MonitoredStatusInfo(long j, MonitoredStatus monitoredStatus) {
        this.f1498b = MonitoredStatus.unknown;
        this.f1497a = j;
        this.f1498b = monitoredStatus;
    }

    public final long getFenceId() {
        return this.f1497a;
    }

    public final MonitoredStatus getMonitoredStatus() {
        return this.f1498b;
    }

    public final void setFenceId(long j) {
        this.f1497a = j;
    }

    public final void setMonitoredStatus(MonitoredStatus monitoredStatus) {
        this.f1498b = monitoredStatus;
    }

    public final String toString() {
        return "MonitoredStatusInfo [fenceId=" + this.f1497a + ", monitoredStatus=" + this.f1498b + "]";
    }
}
