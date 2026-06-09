package com.baidu.trace;

import com.baidu.trace.api.fence.AlarmPoint;
import com.baidu.trace.model.CoordType;
import com.baidu.trace.model.LatLng;

/* JADX INFO: renamed from: com.baidu.trace.bi */
/* JADX INFO: loaded from: classes.dex */
final class C0848bi extends AlarmPoint {
    public C0848bi(LatLng latLng, CoordType coordType, long j, double d) {
        super(latLng, coordType, j, d);
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        C0848bi c0848bi = (C0848bi) obj;
        boolean z = this.f1429c == c0848bi.f1429c;
        double d = this.f1427a.latitude;
        double d2 = this.f1427a.longitude;
        double d3 = c0848bi.f1427a.latitude;
        double d4 = c0848bi.f1427a.longitude;
        if (Double.doubleToLongBits(d) != Double.doubleToLongBits(d3)) {
            z = false;
        }
        if (Double.doubleToLongBits(d2) != Double.doubleToLongBits(d4)) {
            return false;
        }
        return z;
    }

    public final int hashCode() {
        return super.hashCode();
    }

    @Override // com.baidu.trace.api.fence.AlarmPoint
    public final String toString() {
        return "TrackPoint [location=" + this.f1427a + ", coordType=" + this.f1428b + ", locTime=" + this.f1429c + ", radius=" + this.f1430d + "]";
    }
}
