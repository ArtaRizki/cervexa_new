package com.baidu.trace.api.track;

import com.baidu.trace.model.BaseResponse;

/* JADX INFO: loaded from: classes.dex */
public final class LatestPointResponse extends BaseResponse {

    /* JADX INFO: renamed from: a */
    private String f1566a;

    /* JADX INFO: renamed from: b */
    private LatestPoint f1567b;

    /* JADX INFO: renamed from: c */
    private double f1568c;

    public LatestPointResponse() {
    }

    public LatestPointResponse(int i, int i2, String str) {
        super(i, i2, str);
    }

    public LatestPointResponse(int i, int i2, String str, String str2, LatestPoint latestPoint, double d) {
        super(i, i2, str);
        this.f1566a = str2;
        this.f1567b = latestPoint;
        this.f1568c = d;
    }

    public final String getEntityName() {
        return this.f1566a;
    }

    public final LatestPoint getLatestPoint() {
        return this.f1567b;
    }

    public final double getLimitSpeed() {
        return this.f1568c;
    }

    public final void setEntityName(String str) {
        this.f1566a = str;
    }

    public final void setLatestPoint(LatestPoint latestPoint) {
        this.f1567b = latestPoint;
    }

    public final void setLimitSpeed(double d) {
        this.f1568c = d;
    }

    public final String toString() {
        return "LatestPointResponse [tag=" + this.tag + ", status=" + this.status + ", message=" + this.message + ", entityName=" + this.f1566a + ", latestPoint=" + this.f1567b + ", limitSpeed=" + this.f1568c + "]";
    }
}
