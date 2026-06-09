package com.baidu.trace.api.entity;

import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class FilterCondition {

    /* JADX INFO: renamed from: a */
    private List<String> f1410a;

    /* JADX INFO: renamed from: b */
    private long f1411b;

    /* JADX INFO: renamed from: c */
    private long f1412c;

    /* JADX INFO: renamed from: d */
    private Map<String, String> f1413d;

    public FilterCondition() {
    }

    public FilterCondition(List<String> list, long j, long j2, Map<String, String> map) {
        this.f1410a = list;
        this.f1411b = j;
        this.f1412c = j2;
        this.f1413d = map;
    }

    public long getActiveTime() {
        return this.f1411b;
    }

    public Map<String, String> getColumns() {
        return this.f1413d;
    }

    public List<String> getEntityNames() {
        return this.f1410a;
    }

    public long getInActiveTime() {
        return this.f1412c;
    }

    public void setActiveTime(long j) {
        this.f1411b = j;
    }

    public void setColumns(Map<String, String> map) {
        this.f1413d = map;
    }

    public void setEntityNames(List<String> list) {
        this.f1410a = list;
    }

    public void setInActiveTime(long j) {
        this.f1412c = j;
    }

    public String toString() {
        return "FilterCondition [entityNames=" + this.f1410a + ", activeTime=" + this.f1411b + ", inActiveTime=" + this.f1412c + ", columns=" + this.f1413d + "]";
    }
}
