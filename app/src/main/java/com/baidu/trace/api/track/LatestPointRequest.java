package com.baidu.trace.api.track;

import com.baidu.trace.model.BaseRequest;
import com.baidu.trace.model.CoordType;
import com.baidu.trace.model.ProcessOption;

/* JADX INFO: loaded from: classes.dex */
public final class LatestPointRequest extends BaseRequest {

    /* JADX INFO: renamed from: a */
    private String f1563a;

    /* JADX INFO: renamed from: b */
    private ProcessOption f1564b;

    /* JADX INFO: renamed from: c */
    private CoordType f1565c;

    public LatestPointRequest() {
        this.f1565c = CoordType.bd09ll;
    }

    public LatestPointRequest(int i, long j) {
        super(i, j);
        this.f1565c = CoordType.bd09ll;
    }

    public LatestPointRequest(int i, long j, String str) {
        this(i, j);
        this.f1563a = str;
    }

    public LatestPointRequest(int i, long j, String str, ProcessOption processOption, CoordType coordType) {
        this(i, j, str);
        this.f1564b = processOption;
        this.f1565c = coordType;
    }

    public final CoordType getCoordTypeOutput() {
        return this.f1565c;
    }

    public final String getEntityName() {
        return this.f1563a;
    }

    public final ProcessOption getProcessOption() {
        return this.f1564b;
    }

    public final void setCoordTypeOutput(CoordType coordType) {
        this.f1565c = coordType;
    }

    public final void setEntityName(String str) {
        this.f1563a = str;
    }

    public final void setProcessOption(ProcessOption processOption) {
        this.f1564b = processOption;
    }

    public final String toString() {
        return "LatestPointRequest [tag=" + this.tag + ", serviceId=" + this.serviceId + ", entityName=" + this.f1563a + ", processOption=" + this.f1564b + ", coordTypeOutput=" + this.f1565c + "]";
    }
}
