package com.baidu.trace;

import com.baidu.trace.api.entity.OnEntityListener;
import com.baidu.trace.model.CoordType;
import com.baidu.trace.model.LatLng;
import com.baidu.trace.model.TraceLocation;
import com.baidu.trace.p012c.C0854e;

/* JADX INFO: renamed from: com.baidu.trace.as */
/* JADX INFO: loaded from: classes.dex */
final class C0820as extends OnEntityListener {

    /* JADX INFO: renamed from: a */
    private /* synthetic */ C0819ar f1598a;

    C0820as(C0819ar c0819ar) {
        this.f1598a = c0819ar;
    }

    @Override // com.baidu.trace.api.entity.OnEntityListener
    public final void onReceiveLocation(TraceLocation traceLocation) {
        if (traceLocation.getStatus() == 0 && Math.abs(traceLocation.getLongitude()) >= 0.1d && Math.abs(traceLocation.getLatitude()) >= 0.1d && Math.abs(traceLocation.getRadius()) >= 0.1d) {
            LatLng latLng = new LatLng(traceLocation.getLatitude(), traceLocation.getLongitude());
            long jM1236c = C0854e.m1236c(traceLocation.getTime());
            if (jM1236c <= 0) {
                jM1236c = C0854e.m1233b();
            }
            C0819ar.m1088a(this.f1598a, latLng, jM1236c, CoordType.bd09ll, traceLocation.getRadius());
        }
    }
}
