package com.baidu.trace.api.fence;

import com.baidu.trace.model.BaseRequest;
import com.baidu.trace.model.CoordType;
import com.baidu.trace.model.LatLng;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class MonitoredStatusByLocationRequest extends BaseRequest {

    /* JADX INFO: renamed from: a */
    private String f1489a;

    /* JADX INFO: renamed from: b */
    private List<Long> f1490b;

    /* JADX INFO: renamed from: c */
    private LatLng f1491c;

    /* JADX INFO: renamed from: d */
    private CoordType f1492d;

    /* JADX INFO: renamed from: e */
    private FenceType f1493e;

    private MonitoredStatusByLocationRequest(int i, long j, String str, List<Long> list, LatLng latLng, CoordType coordType, FenceType fenceType) {
        super(i, j);
        this.f1489a = str;
        this.f1490b = list;
        this.f1491c = latLng;
        this.f1492d = coordType;
        this.f1493e = fenceType;
    }

    public static MonitoredStatusByLocationRequest buildLocalRequest(int i, long j, String str, List<Long> list, LatLng latLng, CoordType coordType) {
        return new MonitoredStatusByLocationRequest(i, j, str, list, latLng, coordType, FenceType.local);
    }

    public static MonitoredStatusByLocationRequest buildServerRequest(int i, long j, String str, List<Long> list, LatLng latLng, CoordType coordType) {
        return new MonitoredStatusByLocationRequest(i, j, str, list, latLng, coordType, FenceType.server);
    }

    public final CoordType getCoordType() {
        return this.f1492d;
    }

    public final List<Long> getFenceIds() {
        return this.f1490b;
    }

    public final FenceType getFenceType() {
        return this.f1493e;
    }

    public final LatLng getLatLng() {
        return this.f1491c;
    }

    public final String getMonitoredPerson() {
        return this.f1489a;
    }

    public final void setCoordType(CoordType coordType) {
        this.f1492d = coordType;
    }

    public final void setFenceIds(List<Long> list) {
        this.f1490b = list;
    }

    public final void setLatLng(LatLng latLng) {
        this.f1491c = latLng;
    }

    public final void setMonitoredPerson(String str) {
        this.f1489a = str;
    }

    public final String toString() {
        StringBuilder sb;
        if (FenceType.local == this.f1493e) {
            sb = new StringBuilder("MonitoredStatusByLocationRequest [tag=");
            sb.append(this.tag);
            sb.append(", serviceId=");
            sb.append(this.serviceId);
        } else {
            sb = new StringBuilder("MonitoredStatusByLocationRequest [tag=");
            sb.append(this.tag);
            sb.append(", serviceId=");
            sb.append(this.serviceId);
            sb.append(", monitoredPerson=");
            sb.append(this.f1489a);
        }
        sb.append(", fenceIds=");
        sb.append(this.f1490b);
        sb.append(", latLng=");
        sb.append(this.f1491c);
        sb.append(", coordType=");
        sb.append(this.f1492d);
        sb.append(", fenceType=");
        sb.append(this.f1493e);
        sb.append("]");
        return sb.toString();
    }
}
