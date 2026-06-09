package com.baidu.trace.api.fence;

/* JADX INFO: loaded from: classes.dex */
public class FenceAlarmPushInfo {

    /* JADX INFO: renamed from: a */
    protected long f1454a;

    /* JADX INFO: renamed from: b */
    protected String f1455b;

    /* JADX INFO: renamed from: c */
    protected String f1456c;

    /* JADX INFO: renamed from: d */
    protected MonitoredAction f1457d;

    /* JADX INFO: renamed from: e */
    protected AlarmPoint f1458e;

    /* JADX INFO: renamed from: f */
    protected AlarmPoint f1459f;

    public FenceAlarmPushInfo() {
    }

    public FenceAlarmPushInfo(long j, String str, String str2, MonitoredAction monitoredAction, AlarmPoint alarmPoint, AlarmPoint alarmPoint2) {
        this.f1454a = j;
        this.f1455b = str;
        this.f1456c = str2;
        this.f1457d = monitoredAction;
        this.f1458e = alarmPoint;
        this.f1459f = alarmPoint2;
    }

    public AlarmPoint getCurrentPoint() {
        return this.f1458e;
    }

    public long getFenceId() {
        return this.f1454a;
    }

    public String getFenceName() {
        return this.f1455b;
    }

    public MonitoredAction getMonitoredAction() {
        return this.f1457d;
    }

    public String getMonitoredPerson() {
        return this.f1456c;
    }

    public AlarmPoint getPrePoint() {
        return this.f1459f;
    }

    public void setCurrentPoint(AlarmPoint alarmPoint) {
        this.f1458e = alarmPoint;
    }

    public void setFenceId(long j) {
        this.f1454a = j;
    }

    public void setFenceName(String str) {
        this.f1455b = str;
    }

    public void setMonitoredAction(MonitoredAction monitoredAction) {
        this.f1457d = monitoredAction;
    }

    public void setMonitoredPerson(String str) {
        this.f1456c = str;
    }

    public void setPrePoint(AlarmPoint alarmPoint) {
        this.f1459f = alarmPoint;
    }

    public String toString() {
        return "FenceAlarmPushInfo [fenceId=" + this.f1454a + ", fenceName=" + this.f1455b + ", monitoredPerson=" + this.f1456c + ", monitoredAction=" + this.f1457d + ", currentPoint=" + this.f1458e + ", prePoint=" + this.f1459f + "]";
    }
}
