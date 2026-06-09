package com.baidu.trace.api.track;

import com.baidu.trace.model.BaseResponse;

/* JADX INFO: loaded from: classes.dex */
public final class DistanceResponse extends BaseResponse {

    /* JADX INFO: renamed from: a */
    private String f1536a;

    /* JADX INFO: renamed from: b */
    private double f1537b;

    public DistanceResponse() {
    }

    public DistanceResponse(int i, int i2, String str) {
        super(i, i2, str);
    }

    public DistanceResponse(int i, int i2, String str, String str2, double d) {
        super(i, i2, str);
        this.f1536a = str2;
        this.f1537b = d;
    }

    public final double getDistance() {
        return this.f1537b;
    }

    public final String getEntityName() {
        return this.f1536a;
    }

    public final void setDistance(double d) {
        this.f1537b = d;
    }

    public final void setEntityName(String str) {
        this.f1536a = str;
    }

    public final String toString() {
        return "DistanceResponse [tag=" + this.tag + ", status=" + this.status + ", message=" + this.message + ", entityName=" + this.f1536a + ", distance=" + this.f1537b + "]";
    }
}
