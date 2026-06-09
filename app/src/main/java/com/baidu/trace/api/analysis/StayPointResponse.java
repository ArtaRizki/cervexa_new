package com.baidu.trace.api.analysis;

import com.baidu.trace.model.BaseResponse;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class StayPointResponse extends BaseResponse {

    /* JADX INFO: renamed from: a */
    private int f1324a;

    /* JADX INFO: renamed from: b */
    private List<StayPoint> f1325b;

    public StayPointResponse(int i, int i2, String str) {
        super(i, i2, str);
    }

    public StayPointResponse(int i, int i2, String str, int i3, List<StayPoint> list) {
        super(i, i2, str);
        this.f1324a = i3;
        this.f1325b = list;
    }

    public final int getStayPointNum() {
        return this.f1324a;
    }

    public final List<StayPoint> getStayPoints() {
        return this.f1325b;
    }

    public final void setStayPointNum(int i) {
        this.f1324a = i;
    }

    public final void setStayPoints(List<StayPoint> list) {
        this.f1325b = list;
    }

    public final String toString() {
        return "StayPointResponse [tag=" + this.tag + ", status=" + this.status + ", message=" + this.message + ", stayPointNum=" + this.f1324a + ", stayPoints=" + this.f1325b + "]";
    }
}
