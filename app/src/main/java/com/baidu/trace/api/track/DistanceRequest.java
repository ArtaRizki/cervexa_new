package com.baidu.trace.api.track;

import com.baidu.trace.model.BaseRequest;
import com.baidu.trace.model.ProcessOption;

/* JADX INFO: loaded from: classes.dex */
public final class DistanceRequest extends BaseRequest {

    /* JADX INFO: renamed from: a */
    private String f1530a;

    /* JADX INFO: renamed from: b */
    private long f1531b;

    /* JADX INFO: renamed from: c */
    private long f1532c;

    /* JADX INFO: renamed from: d */
    private boolean f1533d;

    /* JADX INFO: renamed from: e */
    private ProcessOption f1534e;

    /* JADX INFO: renamed from: f */
    private SupplementMode f1535f;

    public DistanceRequest() {
        this.f1533d = false;
    }

    public DistanceRequest(int i, long j) {
        super(i, j);
        this.f1533d = false;
    }

    public DistanceRequest(int i, long j, String str) {
        super(i, j);
        this.f1533d = false;
        this.f1530a = str;
    }

    public DistanceRequest(int i, long j, String str, long j2, long j3, boolean z, ProcessOption processOption, SupplementMode supplementMode) {
        super(i, j);
        this.f1533d = false;
        this.f1530a = str;
        this.f1531b = j2;
        this.f1532c = j3;
        this.f1533d = z;
        this.f1534e = processOption;
        this.f1535f = supplementMode;
    }

    public final long getEndTime() {
        return this.f1532c;
    }

    public final String getEntityName() {
        return this.f1530a;
    }

    public final ProcessOption getProcessOption() {
        return this.f1534e;
    }

    public final long getStartTime() {
        return this.f1531b;
    }

    public final SupplementMode getSupplementMode() {
        return this.f1535f;
    }

    public final boolean isProcessed() {
        return this.f1533d;
    }

    public final void setEndTime(long j) {
        this.f1532c = j;
    }

    public final void setEntityName(String str) {
        this.f1530a = str;
    }

    public final void setProcessOption(ProcessOption processOption) {
        this.f1534e = processOption;
    }

    public final void setProcessed(boolean z) {
        this.f1533d = z;
    }

    public final void setStartTime(long j) {
        this.f1531b = j;
    }

    public final void setSupplementMode(SupplementMode supplementMode) {
        this.f1535f = supplementMode;
    }

    public final String toString() {
        return "DistanceRequest [tag=" + this.tag + ", serviceId=" + this.serviceId + ", entityName=" + this.f1530a + ", startTime=" + this.f1531b + ", endTime=" + this.f1532c + ", isProcessed=" + this.f1533d + ", processOption=" + this.f1534e + ", supplementMode=" + this.f1535f + "]";
    }
}
