package com.baidu.trace.api.fence;

import com.baidu.trace.model.CoordType;
import com.baidu.trace.model.LatLng;

/* JADX INFO: loaded from: classes.dex */
public final class CircleFence extends Fence {

    /* JADX INFO: renamed from: f */
    private LatLng f1432f;

    /* JADX INFO: renamed from: g */
    private double f1433g;

    /* JADX INFO: renamed from: h */
    private CoordType f1434h;

    private CircleFence(long j, String str, FenceType fenceType, String str2, LatLng latLng, double d, int i, CoordType coordType) {
        super(j, str, str2, i, fenceType);
        this.f1434h = CoordType.bd09ll;
        this.f1432f = latLng;
        this.f1433g = d;
        this.f1434h = coordType;
    }

    public static CircleFence buildLocalFence(long j, String str, String str2, LatLng latLng, double d, int i, CoordType coordType) {
        return new CircleFence(j, str, FenceType.local, str2, latLng, d, i, coordType);
    }

    public static CircleFence buildServerFence(long j, String str, String str2, LatLng latLng, double d, int i, CoordType coordType) {
        return new CircleFence(j, str, FenceType.server, str2, latLng, d, i, coordType);
    }

    public final LatLng getCenter() {
        return this.f1432f;
    }

    public final CoordType getCoordType() {
        return this.f1434h;
    }

    public final double getRadius() {
        return this.f1433g;
    }

    public final void setCenter(LatLng latLng) {
        this.f1432f = latLng;
    }

    public final void setCoordType(CoordType coordType) {
        this.f1434h = coordType;
    }

    public final void setRadius(double d) {
        this.f1433g = d;
    }

    @Override // com.baidu.trace.api.fence.Fence
    public final String toString() {
        return "CircleFence [fenceId=" + this.f1449a + ", fenceName=" + this.f1450b + ", fenceType=" + this.f1453e + ", monitoredPerson=" + this.f1451c + ", center=" + this.f1432f + ", radius=" + this.f1433g + ", denoise=" + this.f1452d + ", coordType=" + this.f1434h + "]";
    }
}
