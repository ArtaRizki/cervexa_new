package com.baidu.trace.api.analysis;

import com.baidu.trace.model.BaseRequest;
import com.baidu.trace.model.CoordType;
import com.baidu.trace.model.ProcessOption;

/* JADX INFO: loaded from: classes.dex */
public final class DrivingBehaviorRequest extends BaseRequest {

    /* JADX INFO: renamed from: a */
    private String f1278a;

    /* JADX INFO: renamed from: b */
    private long f1279b;

    /* JADX INFO: renamed from: c */
    private long f1280c;

    /* JADX INFO: renamed from: d */
    private int f1281d;

    /* JADX INFO: renamed from: e */
    private ThresholdOption f1282e;

    /* JADX INFO: renamed from: f */
    private ProcessOption f1283f;

    /* JADX INFO: renamed from: g */
    private CoordType f1284g;

    public DrivingBehaviorRequest() {
        this.f1281d = 0;
        this.f1282e = null;
        this.f1284g = CoordType.bd09ll;
    }

    public DrivingBehaviorRequest(int i, long j) {
        super(i, j);
        this.f1281d = 0;
        this.f1282e = null;
        this.f1284g = CoordType.bd09ll;
    }

    public DrivingBehaviorRequest(int i, long j, String str) {
        super(i, j);
        this.f1281d = 0;
        this.f1282e = null;
        this.f1284g = CoordType.bd09ll;
        this.f1278a = str;
    }

    public DrivingBehaviorRequest(int i, long j, String str, long j2, long j3, int i2, ProcessOption processOption, CoordType coordType) {
        super(i, j);
        this.f1281d = 0;
        this.f1282e = null;
        this.f1284g = CoordType.bd09ll;
        this.f1278a = str;
        this.f1279b = j2;
        this.f1280c = j3;
        this.f1281d = i2;
        this.f1283f = processOption;
        this.f1284g = coordType;
    }

    public DrivingBehaviorRequest(int i, long j, String str, long j2, long j3, ThresholdOption thresholdOption, ProcessOption processOption, CoordType coordType) {
        super(i, j);
        this.f1281d = 0;
        this.f1282e = null;
        this.f1284g = CoordType.bd09ll;
        this.f1278a = str;
        this.f1279b = j2;
        this.f1280c = j3;
        this.f1282e = thresholdOption;
        this.f1283f = processOption;
        this.f1284g = coordType;
    }

    public final CoordType getCoordTypeOutput() {
        return this.f1284g;
    }

    public final long getEndTime() {
        return this.f1280c;
    }

    public final String getEntityName() {
        return this.f1278a;
    }

    public final ProcessOption getProcessOption() {
        return this.f1283f;
    }

    public final int getSpeedingThreshold() {
        return this.f1281d;
    }

    public final long getStartTime() {
        return this.f1279b;
    }

    public final ThresholdOption getThresholdOption() {
        return this.f1282e;
    }

    public final void setCoordTypeOutput(CoordType coordType) {
        this.f1284g = coordType;
    }

    public final void setEndTime(long j) {
        this.f1280c = j;
    }

    public final void setEntityName(String str) {
        this.f1278a = str;
    }

    public final void setProcessOption(ProcessOption processOption) {
        this.f1283f = processOption;
    }

    public final void setSpeedingThreshold(int i) {
        this.f1281d = i;
    }

    public final void setStartTime(long j) {
        this.f1279b = j;
    }

    public final void setThresholdOption(ThresholdOption thresholdOption) {
        this.f1282e = thresholdOption;
    }

    public final String toString() {
        return "DrivingBehaviorRequest [tag=" + this.tag + ", serviceId=" + this.serviceId + ", entityName=" + this.f1278a + ", startTime=" + this.f1279b + ", endTime=" + this.f1280c + ", thresholdOption=" + this.f1282e + ", processOption=" + this.f1283f + ", coordTypeOutput=" + this.f1284g + "]";
    }
}
