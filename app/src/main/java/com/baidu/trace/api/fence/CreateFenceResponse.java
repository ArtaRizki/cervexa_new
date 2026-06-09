package com.baidu.trace.api.fence;

import com.baidu.trace.model.BaseResponse;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class CreateFenceResponse extends BaseResponse {

    /* JADX INFO: renamed from: a */
    private long f1436a;

    /* JADX INFO: renamed from: b */
    private String f1437b;

    /* JADX INFO: renamed from: c */
    private FenceType f1438c;

    /* JADX INFO: renamed from: d */
    private FenceShape f1439d;

    /* JADX INFO: renamed from: e */
    private String f1440e;

    /* JADX INFO: renamed from: f */
    private List<String> f1441f;

    public CreateFenceResponse(int i, int i2, String str, FenceType fenceType) {
        super(i, i2, str);
        this.f1438c = fenceType;
    }

    public CreateFenceResponse(int i, int i2, String str, String str2, FenceShape fenceShape, FenceType fenceType) {
        this(i, i2, str, fenceType);
        this.f1437b = str2;
        this.f1439d = fenceShape;
    }

    public final String getDistrict() {
        return this.f1440e;
    }

    public final List<String> getDistrictList() {
        return this.f1441f;
    }

    public final long getFenceId() {
        return this.f1436a;
    }

    public final String getFenceName() {
        return this.f1437b;
    }

    public final FenceShape getFenceShape() {
        return this.f1439d;
    }

    public final FenceType getFenceType() {
        return this.f1438c;
    }

    public final void setDistrict(String str) {
        this.f1440e = str;
    }

    public final void setDistrictList(List<String> list) {
        this.f1441f = list;
    }

    public final void setFenceId(long j) {
        this.f1436a = j;
    }

    public final void setFenceName(String str) {
        this.f1437b = str;
    }

    public final void setFenceShape(FenceShape fenceShape) {
        this.f1439d = fenceShape;
    }

    public final void setFenceType(FenceType fenceType) {
        this.f1438c = fenceType;
    }

    public final String toString() {
        StringBuilder sb;
        Object obj;
        if (FenceShape.district != this.f1439d) {
            sb = new StringBuilder("CreateFenceResponse [tag=");
            sb.append(this.tag);
            sb.append(", status=");
            sb.append(this.status);
            sb.append(", message=");
            sb.append(this.message);
            sb.append(", fenceId=");
            sb.append(this.f1436a);
            sb.append(", fenceName=");
            sb.append(this.f1437b);
            sb.append(", fenceType=");
            sb.append(this.f1438c);
            sb.append(", fenceShape=");
            obj = this.f1439d;
        } else {
            sb = new StringBuilder("CreateFenceResponse [tag=");
            sb.append(this.tag);
            sb.append(", status=");
            sb.append(this.status);
            sb.append(", message=");
            sb.append(this.message);
            sb.append(", fenceId=");
            sb.append(this.f1436a);
            sb.append(", fenceName=");
            sb.append(this.f1437b);
            sb.append(", fenceType=");
            sb.append(this.f1438c);
            sb.append(", fenceShape=");
            sb.append(this.f1439d);
            sb.append(", district=");
            sb.append(this.f1440e);
            sb.append(", districtList=");
            obj = this.f1441f;
        }
        sb.append(obj);
        sb.append("]");
        return sb.toString();
    }
}
