package com.baidu.trace.api.track;

import com.baidu.trace.model.BaseResponse;
import com.baidu.trace.model.Point;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class HistoryTrackResponse extends BaseResponse {

    /* JADX INFO: renamed from: a */
    private int f1553a;

    /* JADX INFO: renamed from: b */
    private int f1554b;

    /* JADX INFO: renamed from: c */
    private String f1555c;

    /* JADX INFO: renamed from: d */
    private double f1556d;

    /* JADX INFO: renamed from: e */
    private double f1557e;

    /* JADX INFO: renamed from: f */
    private Point f1558f;

    /* JADX INFO: renamed from: g */
    private Point f1559g;
    public List<TrackPoint> trackPoints;

    public HistoryTrackResponse() {
    }

    public HistoryTrackResponse(int i, int i2, String str) {
        super(i, i2, str);
    }

    public HistoryTrackResponse(int i, int i2, String str, int i3, int i4, String str2, double d, double d2, Point point, Point point2, List<TrackPoint> list) {
        super(i, i2, str);
        this.f1553a = i3;
        this.f1554b = i4;
        this.f1555c = str2;
        this.f1556d = d;
        this.f1557e = d2;
        this.f1558f = point;
        this.f1559g = point2;
        this.trackPoints = list;
    }

    public final double getDistance() {
        return this.f1556d;
    }

    public final Point getEndPoint() {
        return this.f1559g;
    }

    public final String getEntityName() {
        return this.f1555c;
    }

    public final int getSize() {
        return this.f1554b;
    }

    public final Point getStartPoint() {
        return this.f1558f;
    }

    public final double getTollDistance() {
        return this.f1557e;
    }

    public final int getTotal() {
        return this.f1553a;
    }

    public final List<TrackPoint> getTrackPoints() {
        return this.trackPoints;
    }

    public final void setDistance(double d) {
        this.f1556d = d;
    }

    public final void setEndPoint(Point point) {
        this.f1559g = point;
    }

    public final void setEntityName(String str) {
        this.f1555c = str;
    }

    public final void setSize(int i) {
        this.f1554b = i;
    }

    public final void setStartPoint(Point point) {
        this.f1558f = point;
    }

    public final void setTollDistance(double d) {
        this.f1557e = d;
    }

    public final void setTotal(int i) {
        this.f1553a = i;
    }

    public final void setTrackPoints(List<TrackPoint> list) {
        this.trackPoints = list;
    }

    public final String toString() {
        return "HistoryTrackResponse [tag=" + this.tag + ", status=" + this.status + ", message=" + this.message + ", total=" + this.f1553a + ", size=" + this.f1554b + ", entityName=" + this.f1555c + ", distance=" + this.f1556d + ", tollDistance=" + this.f1557e + ", startPoint=" + this.f1558f + ", endPoint=" + this.f1559g + ", trackPoints=" + this.trackPoints + "]";
    }
}
