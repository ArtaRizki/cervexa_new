package com.baidu.mapapi.map;

import android.graphics.Color;

/* JADX INFO: loaded from: classes.dex */
public class MyLocationConfiguration {
    public int accuracyCircleFillColor;
    public int accuracyCircleStrokeColor;
    public final BitmapDescriptor customMarker;
    public final boolean enableDirection;
    public final LocationMode locationMode;

    public enum LocationMode {
        NORMAL,
        FOLLOWING,
        COMPASS
    }

    public MyLocationConfiguration(LocationMode locationMode, boolean z, BitmapDescriptor bitmapDescriptor) {
        this.accuracyCircleFillColor = 4521984;
        this.accuracyCircleStrokeColor = 4653056;
        this.locationMode = locationMode == null ? LocationMode.NORMAL : locationMode;
        this.enableDirection = z;
        this.customMarker = bitmapDescriptor;
        this.accuracyCircleFillColor = m337a(this.accuracyCircleFillColor);
        this.accuracyCircleStrokeColor = m337a(this.accuracyCircleStrokeColor);
    }

    public MyLocationConfiguration(LocationMode locationMode, boolean z, BitmapDescriptor bitmapDescriptor, int i, int i2) {
        this.accuracyCircleFillColor = 4521984;
        this.accuracyCircleStrokeColor = 4653056;
        this.locationMode = locationMode == null ? LocationMode.NORMAL : locationMode;
        this.enableDirection = z;
        this.customMarker = bitmapDescriptor;
        this.accuracyCircleFillColor = m337a(i);
        this.accuracyCircleStrokeColor = m337a(i2);
    }

    /* JADX INFO: renamed from: a */
    private int m337a(int i) {
        return Color.argb(((-16777216) & i) >> 24, i & 255, (65280 & i) >> 8, (16711680 & i) >> 16);
    }
}
