package com.baidu.trace.api.entity;

import com.baidu.trace.model.BaseRequest;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class AddEntityRequest extends BaseRequest {

    /* JADX INFO: renamed from: a */
    private String f1382a;

    /* JADX INFO: renamed from: b */
    private String f1383b;

    /* JADX INFO: renamed from: c */
    private Map<String, String> f1384c;

    public AddEntityRequest() {
    }

    public AddEntityRequest(int i, long j) {
        super(i, j);
    }

    public AddEntityRequest(int i, long j, String str) {
        super(i, j);
        this.f1382a = str;
    }

    public AddEntityRequest(int i, long j, String str, String str2, Map<String, String> map) {
        super(i, j);
        this.f1382a = str;
        this.f1383b = str2;
        this.f1384c = map;
    }

    public final Map<String, String> getColumns() {
        return this.f1384c;
    }

    public final String getEntityDesc() {
        return this.f1383b;
    }

    public final String getEntityName() {
        return this.f1382a;
    }

    public final void setColumns(Map<String, String> map) {
        this.f1384c = map;
    }

    public final void setEntityDesc(String str) {
        this.f1383b = str;
    }

    public final void setEntityName(String str) {
        this.f1382a = str;
    }

    public final String toString() {
        return "AddEntityRequest [tag=" + this.tag + ", serviceId=" + this.serviceId + ", entityName=" + this.f1382a + ", entityDesc=" + this.f1383b + ", columns=" + this.f1384c + "]";
    }
}
