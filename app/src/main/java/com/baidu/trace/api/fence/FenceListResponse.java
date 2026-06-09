package com.baidu.trace.api.fence;

import com.baidu.trace.model.BaseResponse;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class FenceListResponse extends BaseResponse {

    /* JADX INFO: renamed from: a */
    private int f1471a;

    /* JADX INFO: renamed from: b */
    private FenceType f1472b;

    /* JADX INFO: renamed from: c */
    private List<FenceInfo> f1473c;

    public FenceListResponse(int i, int i2, String str, int i3, FenceType fenceType, List<FenceInfo> list) {
        this(i, i2, str, fenceType);
        this.f1471a = i3;
        this.f1473c = list;
    }

    public FenceListResponse(int i, int i2, String str, FenceType fenceType) {
        super(i, i2, str);
        this.f1472b = fenceType;
    }

    public final List<FenceInfo> getFenceInfos() {
        return this.f1473c;
    }

    public final FenceType getFenceType() {
        return this.f1472b;
    }

    public final int getSize() {
        return this.f1471a;
    }

    public final void setFenceInfos(List<FenceInfo> list) {
        this.f1473c = list;
    }

    public final void setFenceType(FenceType fenceType) {
        this.f1472b = fenceType;
    }

    public final void setSize(int i) {
        this.f1471a = i;
    }

    public final String toString() {
        return "FenceListResponse [tag=" + this.tag + ", status=" + this.status + ", message=" + this.message + ", size=" + this.f1471a + ", fenceType=" + this.f1472b + ", fenceInfos=" + this.f1473c + "]";
    }
}
