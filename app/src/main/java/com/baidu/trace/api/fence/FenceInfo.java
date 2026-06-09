package com.baidu.trace.api.fence;

/* JADX INFO: loaded from: classes.dex */
public final class FenceInfo {

    /* JADX INFO: renamed from: a */
    private FenceShape f1460a;

    /* JADX INFO: renamed from: b */
    private CircleFence f1461b;

    /* JADX INFO: renamed from: c */
    private PolygonFence f1462c;

    /* JADX INFO: renamed from: d */
    private PolylineFence f1463d;

    /* JADX INFO: renamed from: e */
    private DistrictFence f1464e;

    /* JADX INFO: renamed from: f */
    private String f1465f;

    /* JADX INFO: renamed from: g */
    private String f1466g;

    public FenceInfo() {
    }

    public FenceInfo(FenceShape fenceShape, CircleFence circleFence, PolygonFence polygonFence, PolylineFence polylineFence, DistrictFence districtFence, String str, String str2) {
        this.f1460a = fenceShape;
        this.f1461b = circleFence;
        this.f1462c = polygonFence;
        this.f1463d = polylineFence;
        this.f1464e = districtFence;
        this.f1465f = str;
        this.f1466g = str2;
    }

    public FenceInfo(FenceShape fenceShape, CircleFence circleFence, PolygonFence polygonFence, PolylineFence polylineFence, String str, String str2) {
        this.f1460a = fenceShape;
        this.f1461b = circleFence;
        this.f1462c = polygonFence;
        this.f1463d = polylineFence;
        this.f1465f = str;
        this.f1466g = str2;
    }

    public final CircleFence getCircleFence() {
        return this.f1461b;
    }

    public final String getCreateTime() {
        return this.f1465f;
    }

    public final DistrictFence getDistrictFence() {
        return this.f1464e;
    }

    public final FenceShape getFenceShape() {
        return this.f1460a;
    }

    public final String getModifyTime() {
        return this.f1466g;
    }

    public final PolygonFence getPolygonFence() {
        return this.f1462c;
    }

    public final PolylineFence getPolylineFence() {
        return this.f1463d;
    }

    public final void setCircleFence(CircleFence circleFence) {
        this.f1461b = circleFence;
    }

    public final void setCreateTime(String str) {
        this.f1465f = str;
    }

    public final void setDistrictFence(DistrictFence districtFence) {
        this.f1464e = districtFence;
    }

    public final void setFenceShape(FenceShape fenceShape) {
        this.f1460a = fenceShape;
    }

    public final void setModifyTime(String str) {
        this.f1466g = str;
    }

    public final void setPolygonFence(PolygonFence polygonFence) {
        this.f1462c = polygonFence;
    }

    public final void setPolylineFence(PolylineFence polylineFence) {
        this.f1463d = polylineFence;
    }

    public final String toString() {
        StringBuilder sb;
        Object obj;
        Object obj2;
        if (FenceShape.circle == this.f1460a) {
            sb = new StringBuilder("FenceInfo [fenceShape=");
            sb.append(this.f1460a);
            sb.append(", circleFence=");
            obj2 = this.f1461b;
        } else if (FenceShape.polygon == this.f1460a) {
            sb = new StringBuilder("FenceInfo [fenceShape=");
            sb.append(this.f1460a);
            sb.append(", polygonFence=");
            obj2 = this.f1462c;
        } else if (FenceShape.polyline == this.f1460a) {
            sb = new StringBuilder("FenceInfo [fenceShape=");
            sb.append(this.f1460a);
            sb.append(", polylineFence=");
            obj2 = this.f1463d;
        } else {
            if (FenceShape.district == this.f1460a) {
                sb = new StringBuilder("FenceInfo [fenceShape=");
                obj = this.f1460a;
            } else {
                sb = new StringBuilder("FenceInfo [fenceShape=");
                sb.append(this.f1460a);
                sb.append(", circleFence=");
                sb.append(this.f1461b);
                sb.append(", polygonFence=");
                sb.append(this.f1462c);
                sb.append(", polylineFence=");
                obj = this.f1463d;
            }
            sb.append(obj);
            sb.append(", districtFence=");
            obj2 = this.f1464e;
        }
        sb.append(obj2);
        sb.append(", createTime=");
        sb.append(this.f1465f);
        sb.append(", modifyTime=");
        sb.append(this.f1466g);
        sb.append("]");
        return sb.toString();
    }
}
