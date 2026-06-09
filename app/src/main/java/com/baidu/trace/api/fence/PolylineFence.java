package com.baidu.trace.api.fence;

import com.baidu.trace.model.CoordType;
import com.baidu.trace.model.LatLng;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class PolylineFence extends Fence {

    /* JADX INFO: renamed from: f */
    private List<LatLng> f1507f;

    /* JADX INFO: renamed from: g */
    private int f1508g;

    /* JADX INFO: renamed from: h */
    private CoordType f1509h;

    private PolylineFence(long j, String str, FenceType fenceType, String str2, List<LatLng> list, int i, int i2, CoordType coordType) {
        super(j, str, str2, i2, fenceType);
        this.f1509h = CoordType.bd09ll;
        this.f1507f = list;
        this.f1508g = i;
        this.f1509h = coordType;
    }

    public static PolylineFence buildServerFence(long j, String str, String str2, List<LatLng> list, int i, int i2, CoordType coordType) {
        return new PolylineFence(j, str, FenceType.server, str2, list, i, i2, coordType);
    }

    public final CoordType getCoordType() {
        return this.f1509h;
    }

    public final int getOffset() {
        return this.f1508g;
    }

    public final List<LatLng> getVertexes() {
        return this.f1507f;
    }

    public final void setCoordType(CoordType coordType) {
        this.f1509h = coordType;
    }

    public final void setOffset(int i) {
        this.f1508g = i;
    }

    public final void setVertexes(List<LatLng> list) {
        this.f1507f = list;
    }

    @Override // com.baidu.trace.api.fence.Fence
    public final String toString() {
        return "PolylineFence [fenceId=" + this.f1449a + ", fenceName=" + this.f1450b + ", fenceType=" + this.f1453e + ", monitoredPerson=" + this.f1451c + ", vertexes=" + this.f1507f + ", offset=" + this.f1508g + ", denoise=" + this.f1452d + ", coordType=" + this.f1509h + "]";
    }
}
