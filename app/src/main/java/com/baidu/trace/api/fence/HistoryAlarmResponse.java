package com.baidu.trace.api.fence;

import com.baidu.trace.model.BaseResponse;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class HistoryAlarmResponse extends BaseResponse {

    /* JADX INFO: renamed from: a */
    private int f1482a;

    /* JADX INFO: renamed from: b */
    private int f1483b;

    /* JADX INFO: renamed from: c */
    private FenceType f1484c;

    /* JADX INFO: renamed from: d */
    private List<FenceAlarmInfo> f1485d;

    public HistoryAlarmResponse(int i, int i2, String str, int i3, int i4, FenceType fenceType, List<FenceAlarmInfo> list) {
        this(i, i2, str, fenceType);
        this.f1482a = i3;
        this.f1483b = i4;
        this.f1485d = list;
    }

    public HistoryAlarmResponse(int i, int i2, String str, FenceType fenceType) {
        super(i, i2, str);
        this.f1484c = fenceType;
    }

    public final List<FenceAlarmInfo> getFenceAlarmInfos() {
        return this.f1485d;
    }

    public final FenceType getFenceType() {
        return this.f1484c;
    }

    public final int getSize() {
        return this.f1483b;
    }

    public final int getTotal() {
        return this.f1482a;
    }

    public final void setFenceAlarmInfos(List<FenceAlarmInfo> list) {
        this.f1485d = list;
    }

    public final void setFenceType(FenceType fenceType) {
        this.f1484c = fenceType;
    }

    public final void setSize(int i) {
        this.f1483b = i;
    }

    public final void setTotal(int i) {
        this.f1482a = i;
    }

    public final String toString() {
        return "HistoryAlarmResponse [tag=" + this.tag + ", status=" + this.status + ", message=" + this.message + ", total=" + this.f1482a + ", size=" + this.f1483b + ", fenceType=" + this.f1484c + ", fenceAlarmInfos=" + this.f1485d + "]";
    }
}
