package com.baidu.trace.api.track;

/* JADX INFO: loaded from: classes.dex */
public class CacheTrackInfo {

    /* JADX INFO: renamed from: a */
    private String f1524a;

    /* JADX INFO: renamed from: b */
    private int f1525b;

    /* JADX INFO: renamed from: c */
    private long f1526c;

    /* JADX INFO: renamed from: d */
    private long f1527d;

    public CacheTrackInfo() {
    }

    public CacheTrackInfo(String str, int i, long j, long j2) {
        this.f1524a = str;
        this.f1525b = i;
        this.f1526c = j;
        this.f1527d = j2;
    }

    public CacheTrackInfo(String str, long j, long j2) {
        this.f1524a = str;
        this.f1526c = j;
        this.f1527d = j2;
    }

    public long getEndTime() {
        return this.f1527d;
    }

    public String getEntityName() {
        return this.f1524a;
    }

    public long getStartTime() {
        return this.f1526c;
    }

    public int getTotal() {
        return this.f1525b;
    }

    public void setEndTime(long j) {
        this.f1527d = j;
    }

    public void setEntityName(String str) {
        this.f1524a = str;
    }

    public void setStartTime(long j) {
        this.f1526c = j;
    }

    public void setTotal(int i) {
        this.f1525b = i;
    }

    public String toString() {
        return "CacheTrackInfo [entityName=" + this.f1524a + ", total=" + this.f1525b + ", startTime=" + this.f1526c + ", endTime=" + this.f1527d + "]";
    }
}
