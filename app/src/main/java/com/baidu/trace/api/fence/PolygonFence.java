package com.baidu.trace.api.fence;

import com.baidu.trace.model.CoordType;
import com.baidu.trace.model.LatLng;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class PolygonFence extends Fence {

    /* JADX INFO: renamed from: f */
    private List<LatLng> f1505f;

    /* JADX INFO: renamed from: g */
    private CoordType f1506g;

    private PolygonFence(long j, String str, FenceType fenceType, String str2, List<LatLng> list, int i, CoordType coordType) {
        super(j, str, str2, i, fenceType);
        this.f1506g = CoordType.bd09ll;
        this.f1505f = list;
        this.f1506g = coordType;
    }

    public static PolygonFence buildServerFence(long j, String str, String str2, List<LatLng> list, int i, CoordType coordType) {
        return new PolygonFence(j, str, FenceType.server, str2, list, i, coordType);
    }

    public final CoordType getCoordType() {
        return this.f1506g;
    }

    public final List<LatLng> getVertexes() {
        return this.f1505f;
    }

    public final void setCoordType(CoordType coordType) {
        this.f1506g = coordType;
    }

    public final void setVertexes(List<LatLng> list) {
        this.f1505f = list;
    }

    @Override // com.baidu.trace.api.fence.Fence
    public final String toString() {
        return "PolygonFence [fenceId=" + this.f1449a + ", fenceName=" + this.f1450b + ", fenceType=" + this.f1453e + ", monitoredPerson=" + this.f1451c + ", vertexes=" + this.f1505f + ", denoise=" + this.f1452d + ", coordType=" + this.f1506g + "]";
    }
}
