package com.baidu.trace.api.analysis;

import com.baidu.trace.model.BaseRequest;
import com.baidu.trace.model.CoordType;
import com.baidu.trace.model.ProcessOption;
import tv.danmaku.ijk.media.player.IjkMediaCodecInfo;

/* JADX INFO: loaded from: classes.dex */
public final class StayPointRequest extends BaseRequest {

    /* JADX INFO: renamed from: a */
    private String f1317a;

    /* JADX INFO: renamed from: b */
    private long f1318b;

    /* JADX INFO: renamed from: c */
    private long f1319c;

    /* JADX INFO: renamed from: d */
    private int f1320d;

    /* JADX INFO: renamed from: e */
    private int f1321e;

    /* JADX INFO: renamed from: f */
    private ProcessOption f1322f;

    /* JADX INFO: renamed from: g */
    private CoordType f1323g;

    public StayPointRequest() {
        this.f1320d = IjkMediaCodecInfo.RANK_LAST_CHANCE;
        this.f1321e = 20;
        this.f1323g = CoordType.bd09ll;
    }

    public StayPointRequest(int i, long j) {
        super(i, j);
        this.f1320d = IjkMediaCodecInfo.RANK_LAST_CHANCE;
        this.f1321e = 20;
        this.f1323g = CoordType.bd09ll;
    }

    public StayPointRequest(int i, long j, String str) {
        super(i, j);
        this.f1320d = IjkMediaCodecInfo.RANK_LAST_CHANCE;
        this.f1321e = 20;
        this.f1323g = CoordType.bd09ll;
        this.f1317a = str;
    }

    public StayPointRequest(int i, long j, String str, long j2, long j3, int i2, int i3, ProcessOption processOption, CoordType coordType) {
        super(i, j);
        this.f1320d = IjkMediaCodecInfo.RANK_LAST_CHANCE;
        this.f1321e = 20;
        this.f1323g = CoordType.bd09ll;
        this.f1317a = str;
        this.f1318b = j2;
        this.f1319c = j3;
        this.f1320d = i2;
        this.f1321e = i3;
        this.f1322f = processOption;
        this.f1323g = coordType;
    }

    public StayPointRequest(int i, long j, String str, long j2, long j3, int i2, ProcessOption processOption, CoordType coordType) {
        super(i, j);
        this.f1320d = IjkMediaCodecInfo.RANK_LAST_CHANCE;
        this.f1321e = 20;
        this.f1323g = CoordType.bd09ll;
        this.f1317a = str;
        this.f1318b = j2;
        this.f1319c = j3;
        this.f1320d = i2;
        this.f1322f = processOption;
        this.f1323g = coordType;
    }

    public final CoordType getCoordTypeOutput() {
        return this.f1323g;
    }

    public final long getEndTime() {
        return this.f1319c;
    }

    public final String getEntityName() {
        return this.f1317a;
    }

    public final ProcessOption getProcessOption() {
        return this.f1322f;
    }

    public final long getStartTime() {
        return this.f1318b;
    }

    public final int getStayRadius() {
        return this.f1321e;
    }

    public final int getStayTime() {
        return this.f1320d;
    }

    public final void setCoordTypeOutput(CoordType coordType) {
        this.f1323g = coordType;
    }

    public final void setEndTime(long j) {
        this.f1319c = j;
    }

    public final void setEntityName(String str) {
        this.f1317a = str;
    }

    public final void setProcessOption(ProcessOption processOption) {
        this.f1322f = processOption;
    }

    public final void setStartTime(long j) {
        this.f1318b = j;
    }

    public final void setStayRadius(int i) {
        this.f1321e = i;
    }

    public final void setStayTime(int i) {
        this.f1320d = i;
    }

    public final String toString() {
        StringBuffer stringBuffer = new StringBuffer("StayPointRequest{");
        stringBuffer.append("tag=");
        stringBuffer.append(this.tag);
        stringBuffer.append(", serviceId=");
        stringBuffer.append(this.serviceId);
        stringBuffer.append(", entityName='");
        stringBuffer.append(this.f1317a);
        stringBuffer.append('\'');
        stringBuffer.append(", startTime=");
        stringBuffer.append(this.f1318b);
        stringBuffer.append(", endTime=");
        stringBuffer.append(this.f1319c);
        stringBuffer.append(", stayTime=");
        stringBuffer.append(this.f1320d);
        stringBuffer.append(", stayRadius=");
        stringBuffer.append(this.f1321e);
        stringBuffer.append(", processOption=");
        stringBuffer.append(this.f1322f);
        stringBuffer.append(", coordTypeOutput=");
        stringBuffer.append(this.f1323g);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
