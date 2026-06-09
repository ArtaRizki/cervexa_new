package com.baidu.trace.api.entity;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class EntityInfo {

    /* JADX INFO: renamed from: a */
    private String f1403a;

    /* JADX INFO: renamed from: b */
    private String f1404b;

    /* JADX INFO: renamed from: c */
    private String f1405c;

    /* JADX INFO: renamed from: d */
    private String f1406d;

    /* JADX INFO: renamed from: e */
    private Map<String, String> f1407e;

    /* JADX INFO: renamed from: f */
    private LatestLocation f1408f;

    public EntityInfo() {
    }

    public EntityInfo(String str, String str2, String str3, String str4, Map<String, String> map, LatestLocation latestLocation) {
        this.f1403a = str;
        this.f1404b = str2;
        this.f1405c = str3;
        this.f1406d = str4;
        this.f1407e = map;
        this.f1408f = latestLocation;
    }

    public final Map<String, String> getColumns() {
        return this.f1407e;
    }

    public final String getCreateTime() {
        return this.f1406d;
    }

    public final String getEntityDesc() {
        return this.f1404b;
    }

    public final String getEntityName() {
        return this.f1403a;
    }

    public final LatestLocation getLatestLocation() {
        return this.f1408f;
    }

    public final String getModifyTime() {
        return this.f1405c;
    }

    public final void setColumns(Map<String, String> map) {
        this.f1407e = map;
    }

    public final void setCreateTime(String str) {
        this.f1406d = str;
    }

    public final void setEntityDesc(String str) {
        this.f1404b = str;
    }

    public final void setEntityName(String str) {
        this.f1403a = str;
    }

    public final void setLatestLocation(LatestLocation latestLocation) {
        this.f1408f = latestLocation;
    }

    public final void setModifyTime(String str) {
        this.f1405c = str;
    }

    public final String toString() {
        return "EntityInfo [entityName=" + this.f1403a + ", entityDesc=" + this.f1404b + ", modifyTime=" + this.f1405c + ", createTime=" + this.f1406d + ", columns=" + this.f1407e + ", latestLocation=" + this.f1408f + "]";
    }
}
