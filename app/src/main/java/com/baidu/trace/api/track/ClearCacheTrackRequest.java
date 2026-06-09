package com.baidu.trace.api.track;

import com.baidu.trace.model.BaseRequest;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ClearCacheTrackRequest extends BaseRequest {

    /* JADX INFO: renamed from: a */
    private List<String> f1528a;

    /* JADX INFO: renamed from: b */
    private List<CacheTrackInfo> f1529b;

    public ClearCacheTrackRequest() {
        this.f1528a = null;
        this.f1529b = null;
    }

    public ClearCacheTrackRequest(int i, long j) {
        super(i, j);
        this.f1528a = null;
        this.f1529b = null;
    }

    public ClearCacheTrackRequest(int i, long j, List<String> list) {
        super(i, j);
        this.f1528a = null;
        this.f1529b = null;
        this.f1528a = list;
    }

    public final List<CacheTrackInfo> getCacheTrackInfos() {
        return this.f1529b;
    }

    public final List<String> getEntityNames() {
        return this.f1528a;
    }

    public final void setCacheTrackInfos(List<CacheTrackInfo> list) {
        this.f1529b = list;
    }

    public final void setEntityNames(List<String> list) {
        this.f1528a = list;
    }

    public final String toString() {
        StringBuffer stringBuffer = new StringBuffer("ClearCacheTrackRequest{");
        stringBuffer.append("tag=");
        stringBuffer.append(this.tag);
        stringBuffer.append(", serviceId=");
        stringBuffer.append(this.serviceId);
        stringBuffer.append(", entityNames=");
        stringBuffer.append(this.f1528a);
        stringBuffer.append(", cacheTrackInfos=");
        stringBuffer.append(this.f1529b);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
