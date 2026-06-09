package com.baidu.trace.api.track;

import com.baidu.trace.model.BaseRequest;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class AddPointsRequest extends BaseRequest {

    /* JADX INFO: renamed from: a */
    private Map<String, List<TrackPoint>> f1521a;

    public AddPointsRequest() {
        this.f1521a = null;
    }

    public AddPointsRequest(int i, long j) {
        super(i, j);
        this.f1521a = null;
    }

    public AddPointsRequest(int i, long j, Map<String, List<TrackPoint>> map) {
        super(i, j);
        this.f1521a = null;
        this.f1521a = map;
    }

    public Map<String, List<TrackPoint>> getPoints() {
        return this.f1521a;
    }

    public void setPoints(Map<String, List<TrackPoint>> map) {
        this.f1521a = map;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("AddPointsRequest{");
        stringBuffer.append("tag=");
        stringBuffer.append(this.tag);
        stringBuffer.append(", serviceId=");
        stringBuffer.append(this.serviceId);
        stringBuffer.append(", points=");
        stringBuffer.append(this.f1521a);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
