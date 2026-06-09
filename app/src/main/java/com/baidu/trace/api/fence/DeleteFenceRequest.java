package com.baidu.trace.api.fence;

import com.baidu.trace.model.BaseRequest;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class DeleteFenceRequest extends BaseRequest {

    /* JADX INFO: renamed from: a */
    private String f1442a;

    /* JADX INFO: renamed from: b */
    private List<Long> f1443b;

    /* JADX INFO: renamed from: c */
    private FenceType f1444c;

    private DeleteFenceRequest(int i, long j, String str, List<Long> list, FenceType fenceType) {
        super(i, j);
        this.f1442a = str;
        this.f1443b = list;
        this.f1444c = fenceType;
    }

    public static DeleteFenceRequest buildLocalRequest(int i, long j, String str, List<Long> list) {
        return new DeleteFenceRequest(i, j, str, list, FenceType.local);
    }

    public static DeleteFenceRequest buildServerRequest(int i, long j, String str, List<Long> list) {
        return new DeleteFenceRequest(i, j, str, list, FenceType.server);
    }

    public final List<Long> getFenceIds() {
        return this.f1443b;
    }

    public final FenceType getFenceType() {
        return this.f1444c;
    }

    public final String getMonitoredPerson() {
        return this.f1442a;
    }

    public final void setFenceIds(List<Long> list) {
        this.f1443b = list;
    }

    public final void setMonitoredPerson(String str) {
        this.f1442a = str;
    }

    public final String toString() {
        return "DeleteFenceRequest [tag=" + this.tag + ", serviceId=" + this.serviceId + ", monitoredPerson=" + this.f1442a + ", fenceIds=" + this.f1443b + ", fenceType=" + this.f1444c + "]";
    }
}
