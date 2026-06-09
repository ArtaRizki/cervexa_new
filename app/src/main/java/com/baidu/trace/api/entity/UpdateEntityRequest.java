package com.baidu.trace.api.entity;

import com.baidu.trace.model.BaseRequest;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class UpdateEntityRequest extends BaseRequest {

    /* JADX INFO: renamed from: a */
    private String f1424a;

    /* JADX INFO: renamed from: b */
    private String f1425b;

    /* JADX INFO: renamed from: c */
    private Map<String, String> f1426c;

    public UpdateEntityRequest() {
    }

    public UpdateEntityRequest(int i, long j) {
        super(i, j);
    }

    public UpdateEntityRequest(int i, long j, String str) {
        super(i, j);
        this.f1424a = str;
    }

    public UpdateEntityRequest(int i, long j, String str, String str2, Map<String, String> map) {
        super(i, j);
        this.f1424a = str;
        this.f1425b = str2;
        this.f1426c = map;
    }

    public final Map<String, String> getColumns() {
        return this.f1426c;
    }

    public final String getEntityDesc() {
        return this.f1425b;
    }

    public final String getEntityName() {
        return this.f1424a;
    }

    public final void setColumns(Map<String, String> map) {
        this.f1426c = map;
    }

    public final void setEntityDesc(String str) {
        this.f1425b = str;
    }

    public final void setEntityName(String str) {
        this.f1424a = str;
    }

    public final String toString() {
        return "UpdateEntityRequest [tag=" + this.tag + ", serviceId=" + this.serviceId + ", entityName=" + this.f1424a + ", entityDesc=" + this.f1425b + ", columns=" + this.f1426c + "]";
    }
}
