package com.baidu.trace.api.track;

import com.baidu.trace.model.BaseRequest;

/* JADX INFO: loaded from: classes.dex */
public final class QueryCacheTrackRequest extends BaseRequest {

    /* JADX INFO: renamed from: a */
    private String f1569a;

    public QueryCacheTrackRequest() {
    }

    public QueryCacheTrackRequest(int i, long j) {
        super(i, j);
    }

    public QueryCacheTrackRequest(int i, long j, String str) {
        super(i, j);
        this.f1569a = str;
    }

    public final String getEntityName() {
        return this.f1569a;
    }

    public final void setEntityName(String str) {
        this.f1569a = str;
    }

    public final String toString() {
        return "QueryCacheTrackRequest [tag=" + this.tag + ", serviceId=" + this.serviceId + ", entityName=" + this.f1569a + "]";
    }
}
