package com.baidu.trace.api.fence;

import com.baidu.trace.model.BaseRequest;
import com.baidu.trace.model.CoordType;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class HistoryAlarmRequest extends BaseRequest {

    /* JADX INFO: renamed from: a */
    private long f1476a;

    /* JADX INFO: renamed from: b */
    private long f1477b;

    /* JADX INFO: renamed from: c */
    private String f1478c;

    /* JADX INFO: renamed from: d */
    private List<Long> f1479d;

    /* JADX INFO: renamed from: e */
    private CoordType f1480e;

    /* JADX INFO: renamed from: f */
    private FenceType f1481f;

    private HistoryAlarmRequest(int i, long j, long j2, long j3, String str, List<Long> list, CoordType coordType, FenceType fenceType) {
        super(i, j);
        this.f1480e = CoordType.bd09ll;
        this.f1476a = j2;
        this.f1477b = j3;
        this.f1478c = str;
        this.f1479d = list;
        this.f1480e = coordType;
        this.f1481f = fenceType;
    }

    public static HistoryAlarmRequest buildLocalRequest(int i, long j, long j2, long j3, String str, List<Long> list) {
        return new HistoryAlarmRequest(i, j, j2, j3, str, list, CoordType.bd09ll, FenceType.local);
    }

    public static HistoryAlarmRequest buildServerRequest(int i, long j, long j2, long j3, String str, List<Long> list, CoordType coordType) {
        return new HistoryAlarmRequest(i, j, j2, j3, str, list, coordType, FenceType.server);
    }

    public final CoordType getCoordTypeOutput() {
        return this.f1480e;
    }

    public final long getEndTime() {
        return this.f1477b;
    }

    public final List<Long> getFenceIds() {
        return this.f1479d;
    }

    public final FenceType getFenceType() {
        return this.f1481f;
    }

    public final String getMonitoredPerson() {
        return this.f1478c;
    }

    public final long getStartTime() {
        return this.f1476a;
    }

    public final void setCoordTypeOutput(CoordType coordType) {
        if (FenceType.server == this.f1481f) {
            this.f1480e = coordType;
        }
    }

    public final void setEndTime(long j) {
        this.f1477b = j;
    }

    public final void setFenceIds(List<Long> list) {
        this.f1479d = list;
    }

    public final void setMonitoredPerson(String str) {
        this.f1478c = str;
    }

    public final void setStartTime(long j) {
        this.f1476a = j;
    }

    public final String toString() {
        return "HistoryAlarmRequest [tag=" + this.tag + ", serviceId=" + this.serviceId + ", startTime=" + this.f1476a + ", endTime=" + this.f1477b + ", monitoredPerson=" + this.f1478c + ", fenceIds=" + this.f1479d + ", coordTypeOutput=" + this.f1480e + ", fenceType=" + this.f1481f + "]";
    }
}
