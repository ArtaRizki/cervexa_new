package com.baidu.trace.api.fence;

import com.baidu.trace.model.BaseResponse;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class DeleteFenceResponse extends BaseResponse {

    /* JADX INFO: renamed from: a */
    private List<Long> f1445a;

    /* JADX INFO: renamed from: b */
    private FenceType f1446b;

    public DeleteFenceResponse() {
    }

    public DeleteFenceResponse(int i, int i2, String str, FenceType fenceType) {
        super(i, i2, str);
        this.f1446b = fenceType;
    }

    public DeleteFenceResponse(int i, int i2, String str, List<Long> list, FenceType fenceType) {
        this(i, i2, str, fenceType);
        this.f1445a = list;
    }

    public final List<Long> getFenceIds() {
        return this.f1445a;
    }

    public final FenceType getFenceType() {
        return this.f1446b;
    }

    public final void setFenceIds(List<Long> list) {
        this.f1445a = list;
    }

    public final void setFenceType(FenceType fenceType) {
        this.f1446b = fenceType;
    }

    public final String toString() {
        return "DeleteFenceResponse [tag=" + this.tag + ", status=" + this.status + ", message=" + this.message + ", fenceType=" + this.f1446b + ", fenceIds=" + this.f1445a + "]";
    }
}
