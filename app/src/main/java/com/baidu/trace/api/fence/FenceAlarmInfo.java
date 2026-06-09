package com.baidu.trace.api.fence;

/* JADX INFO: loaded from: classes.dex */
public final class FenceAlarmInfo extends FenceAlarmPushInfo {
    public FenceAlarmInfo() {
    }

    public FenceAlarmInfo(long j, String str, String str2, MonitoredAction monitoredAction, AlarmPoint alarmPoint, AlarmPoint alarmPoint2) {
        super(j, str, str2, monitoredAction, alarmPoint, alarmPoint2);
    }

    @Override // com.baidu.trace.api.fence.FenceAlarmPushInfo
    public final String toString() {
        return "FenceAlarmInfo [fenceId=" + this.f1454a + ", fenceName=" + this.f1455b + ", monitoredPerson=" + this.f1456c + ", monitoredAction=" + this.f1457d + ", currentPoint=" + this.f1458e + ", prePoint=" + this.f1459f + "]";
    }
}
