package com.baidu.trace.api.analysis;

import com.baidu.trace.model.BaseResponse;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class DrivingBehaviorResponse extends BaseResponse {

    /* JADX INFO: renamed from: a */
    private double f1285a;

    /* JADX INFO: renamed from: b */
    private int f1286b;

    /* JADX INFO: renamed from: c */
    private double f1287c;

    /* JADX INFO: renamed from: d */
    private double f1288d;

    /* JADX INFO: renamed from: e */
    private int f1289e;

    /* JADX INFO: renamed from: f */
    private int f1290f;

    /* JADX INFO: renamed from: g */
    private int f1291g;

    /* JADX INFO: renamed from: h */
    private int f1292h;

    /* JADX INFO: renamed from: i */
    private StartPoint f1293i;

    /* JADX INFO: renamed from: j */
    private EndPoint f1294j;

    /* JADX INFO: renamed from: k */
    private List<SpeedingInfo> f1295k;

    /* JADX INFO: renamed from: l */
    private List<HarshAccelerationPoint> f1296l;

    /* JADX INFO: renamed from: m */
    private List<HarshBreakingPoint> f1297m;

    /* JADX INFO: renamed from: n */
    private List<HarshSteeringPoint> f1298n;

    public DrivingBehaviorResponse() {
    }

    public DrivingBehaviorResponse(int i, int i2, String str) {
        super(i, i2, str);
    }

    public DrivingBehaviorResponse(int i, int i2, String str, double d, int i3, double d2, double d3, int i4, int i5, int i6, int i7, StartPoint startPoint, EndPoint endPoint, List<SpeedingInfo> list, List<HarshAccelerationPoint> list2, List<HarshBreakingPoint> list3, List<HarshSteeringPoint> list4) {
        super(i, i2, str);
        this.f1285a = d;
        this.f1286b = i3;
        this.f1287c = d2;
        this.f1288d = d3;
        this.f1289e = i4;
        this.f1290f = i5;
        this.f1291g = i6;
        this.f1292h = i7;
        this.f1293i = startPoint;
        this.f1294j = endPoint;
        this.f1295k = list;
        this.f1296l = list2;
        this.f1297m = list3;
        this.f1298n = list4;
    }

    public final double getAverageSpeed() {
        return this.f1287c;
    }

    public final double getDistance() {
        return this.f1285a;
    }

    public final int getDuration() {
        return this.f1286b;
    }

    public final EndPoint getEndPoint() {
        return this.f1294j;
    }

    public final int getHarshAccelerationNum() {
        return this.f1290f;
    }

    public final List<HarshAccelerationPoint> getHarshAccelerationPoints() {
        return this.f1296l;
    }

    public final int getHarshBreakingNum() {
        return this.f1291g;
    }

    public final List<HarshBreakingPoint> getHarshBreakingPoints() {
        return this.f1297m;
    }

    public final int getHarshSteeringNum() {
        return this.f1292h;
    }

    public final List<HarshSteeringPoint> getHarshSteeringPoints() {
        return this.f1298n;
    }

    public final double getMaxSpeed() {
        return this.f1288d;
    }

    public final int getSpeedingNum() {
        return this.f1289e;
    }

    public final List<SpeedingInfo> getSpeedings() {
        return this.f1295k;
    }

    public final StartPoint getStartPoint() {
        return this.f1293i;
    }

    public final void setAverageSpeed(double d) {
        this.f1287c = d;
    }

    public final void setDistance(double d) {
        this.f1285a = d;
    }

    public final void setDuration(int i) {
        this.f1286b = i;
    }

    public final void setEndPoint(EndPoint endPoint) {
        this.f1294j = endPoint;
    }

    public final void setHarshAccelerationNum(int i) {
        this.f1290f = i;
    }

    public final void setHarshAccelerationPoints(List<HarshAccelerationPoint> list) {
        this.f1296l = list;
    }

    public final void setHarshBreakingNum(int i) {
        this.f1291g = i;
    }

    public final void setHarshBreakingPoints(List<HarshBreakingPoint> list) {
        this.f1297m = list;
    }

    public final void setHarshSteeringNum(int i) {
        this.f1292h = i;
    }

    public final void setHarshSteeringPoints(List<HarshSteeringPoint> list) {
        this.f1298n = list;
    }

    public final void setMaxSpeed(double d) {
        this.f1288d = d;
    }

    public final void setSpeedingNum(int i) {
        this.f1289e = i;
    }

    public final void setSpeedings(List<SpeedingInfo> list) {
        this.f1295k = list;
    }

    public final void setStartPoint(StartPoint startPoint) {
        this.f1293i = startPoint;
    }

    public final String toString() {
        StringBuffer stringBuffer = new StringBuffer("[");
        List<SpeedingInfo> list = this.f1295k;
        if (list != null && list.size() > 0) {
            int size = this.f1295k.size();
            for (int i = 0; i < size; i++) {
                stringBuffer.append("[");
                SpeedingInfo speedingInfo = this.f1295k.get(i);
                if (speedingInfo != null && speedingInfo.getPoints() != null && speedingInfo.getPoints().size() != 0) {
                    List<SpeedingPoint> points = speedingInfo.getPoints();
                    int size2 = points.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        stringBuffer.append(points.get(i2).toString());
                        if (i2 < size2 - 1) {
                            stringBuffer.append(",");
                        }
                    }
                    stringBuffer.append("]");
                    if (i < size - 1) {
                        stringBuffer.append(",");
                    }
                }
            }
        }
        stringBuffer.append("]");
        return "DrivingBehaviorResponse [tag=" + this.tag + ", status=" + this.status + ", message=" + this.message + ", distance=" + this.f1285a + ", duration=" + this.f1286b + ", averageSpeed=" + this.f1287c + ", maxSpeed=" + this.f1288d + ", speedingNum=" + this.f1289e + ", harshAccelerationNum=" + this.f1290f + ", harshBreakingNum=" + this.f1291g + ", harshSteeringNum=" + this.f1292h + ", startPoint=" + this.f1293i + ", endPoint=" + this.f1294j + ", speedingPoints=" + stringBuffer.toString() + ", harshAccelerationPoints=" + this.f1296l + ", harshBreakingPoints=" + this.f1297m + ", harshSteeringPoints=" + this.f1298n + "]";
    }
}
