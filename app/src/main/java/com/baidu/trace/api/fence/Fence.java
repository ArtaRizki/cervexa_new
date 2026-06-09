package com.baidu.trace.api.fence;

/* JADX INFO: loaded from: classes.dex */
public abstract class Fence {

    /* JADX INFO: renamed from: a */
    protected long f1449a;

    /* JADX INFO: renamed from: b */
    protected String f1450b;

    /* JADX INFO: renamed from: c */
    protected String f1451c;

    /* JADX INFO: renamed from: d */
    protected int f1452d;

    /* JADX INFO: renamed from: e */
    protected FenceType f1453e;

    protected Fence() {
    }

    protected Fence(long j, String str, String str2, int i, FenceType fenceType) {
        this.f1449a = j;
        this.f1450b = str;
        this.f1453e = fenceType;
        this.f1452d = i;
        this.f1451c = str2;
    }

    public int getDenoise() {
        return this.f1452d;
    }

    public long getFenceId() {
        return this.f1449a;
    }

    public String getFenceName() {
        return this.f1450b;
    }

    public FenceType getFenceType() {
        return this.f1453e;
    }

    public String getMonitoredPerson() {
        return this.f1451c;
    }

    public void setDenoise(int i) {
        this.f1452d = i;
    }

    public void setFenceId(long j) {
        this.f1449a = j;
    }

    public void setFenceName(String str) {
        this.f1450b = str;
    }

    public void setMonitoredPerson(String str) {
        this.f1451c = str;
    }

    public String toString() {
        return "Fence [fenceId=" + this.f1449a + ", fenceName=" + this.f1450b + ", monitoredPerson= " + this.f1451c + ", denoise=" + this.f1452d + ", fenceType=" + this.f1453e + "]";
    }
}
