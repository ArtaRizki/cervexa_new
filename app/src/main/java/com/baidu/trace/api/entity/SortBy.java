package com.baidu.trace.api.entity;

import com.baidu.trace.model.SortType;

/* JADX INFO: loaded from: classes.dex */
public class SortBy {

    /* JADX INFO: renamed from: a */
    private String f1422a;

    /* JADX INFO: renamed from: b */
    private SortType f1423b;

    private SortBy(String str, SortType sortType) {
        this.f1422a = str;
        this.f1423b = sortType;
    }

    public static SortBy buildCustomKey(String str, SortType sortType) {
        return new SortBy(str, sortType);
    }

    public static SortBy buildEntityDesc(SortType sortType) {
        return new SortBy("entity_desc", sortType);
    }

    public static SortBy buildEntityName(SortType sortType) {
        return new SortBy("entity_name", sortType);
    }

    public static SortBy buildLocTime(SortType sortType) {
        return new SortBy("loc_time", sortType);
    }

    public String getFieldName() {
        return this.f1422a;
    }

    public SortType getSortType() {
        return this.f1423b;
    }

    public void setSortType(SortType sortType) {
        this.f1423b = sortType;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("SortBy{");
        stringBuffer.append("fieldName='");
        stringBuffer.append(this.f1422a);
        stringBuffer.append('\'');
        stringBuffer.append(", sortType=");
        stringBuffer.append(this.f1423b);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
