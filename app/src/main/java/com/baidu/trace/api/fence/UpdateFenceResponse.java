package com.baidu.trace.api.fence;

import com.baidu.trace.model.BaseResponse;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class UpdateFenceResponse extends BaseResponse {

    /* JADX INFO: renamed from: a */
    private long f1511a;

    /* JADX INFO: renamed from: b */
    private String f1512b;

    /* JADX INFO: renamed from: c */
    private FenceType f1513c;

    /* JADX INFO: renamed from: d */
    private FenceShape f1514d;

    /* JADX INFO: renamed from: e */
    private String f1515e;

    /* JADX INFO: renamed from: f */
    private List<String> f1516f;

    public UpdateFenceResponse() {
    }

    public UpdateFenceResponse(int i, int i2, String str, long j, String str2, FenceType fenceType) {
        super(i, i2, str);
        this.f1511a = j;
        this.f1512b = str2;
        this.f1513c = fenceType;
    }

    public UpdateFenceResponse(int i, int i2, String str, FenceType fenceType) {
        super(i, i2, str);
        this.f1513c = fenceType;
    }

    public final String getDistrict() {
        return this.f1515e;
    }

    public final List<String> getDistrictList() {
        return this.f1516f;
    }

    public final long getFenceId() {
        return this.f1511a;
    }

    public final String getFenceName() {
        return this.f1512b;
    }

    public final FenceShape getFenceShape() {
        return this.f1514d;
    }

    public final FenceType getFenceType() {
        return this.f1513c;
    }

    public final void setDistrict(String str) {
        this.f1515e = str;
    }

    public final void setDistrictList(List<String> list) {
        this.f1516f = list;
    }

    public final void setFenceId(long j) {
        this.f1511a = j;
    }

    public final void setFenceName(String str) {
        this.f1512b = str;
    }

    public final void setFenceShape(FenceShape fenceShape) {
        this.f1514d = fenceShape;
    }

    public final void setFenceType(FenceType fenceType) {
        this.f1513c = fenceType;
    }

    public final String toString() {
        StringBuilder sb;
        Object obj;
        if (FenceShape.district != this.f1514d) {
            sb = new StringBuilder("UpdateFenceResponse [tag=");
            sb.append(this.tag);
            sb.append(", status=");
            sb.append(this.status);
            sb.append(", message=");
            sb.append(this.message);
            sb.append(", fenceId=");
            sb.append(this.f1511a);
            sb.append(", fenceName=");
            sb.append(this.f1512b);
            sb.append(", fenceType=");
            sb.append(this.f1513c);
            sb.append(", fenceShape=");
            obj = this.f1514d;
        } else {
            sb = new StringBuilder("UpdateFenceResponse [tag=");
            sb.append(this.tag);
            sb.append(", status=");
            sb.append(this.status);
            sb.append(", message=");
            sb.append(this.message);
            sb.append(", fenceId=");
            sb.append(this.f1511a);
            sb.append(", fenceName=");
            sb.append(this.f1512b);
            sb.append(", fenceType=");
            sb.append(this.f1513c);
            sb.append(", fenceShape=");
            sb.append(this.f1514d);
            sb.append(", district=");
            sb.append(this.f1515e);
            sb.append(", districtList=");
            obj = this.f1516f;
        }
        sb.append(obj);
        sb.append("]");
        return sb.toString();
    }
}
