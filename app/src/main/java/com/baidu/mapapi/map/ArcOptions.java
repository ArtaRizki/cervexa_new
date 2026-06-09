package com.baidu.mapapi.map;

import android.os.Bundle;
import androidx.core.view.ViewCompat;
import com.baidu.mapapi.model.LatLng;

/* JADX INFO: loaded from: classes.dex */
public final class ArcOptions extends OverlayOptions {

    /* JADX INFO: renamed from: d */
    private static final String f229d = ArcOptions.class.getSimpleName();

    /* JADX INFO: renamed from: a */
    int f230a;

    /* JADX INFO: renamed from: c */
    Bundle f232c;

    /* JADX INFO: renamed from: g */
    private LatLng f235g;

    /* JADX INFO: renamed from: h */
    private LatLng f236h;

    /* JADX INFO: renamed from: i */
    private LatLng f237i;

    /* JADX INFO: renamed from: e */
    private int f233e = ViewCompat.MEASURED_STATE_MASK;

    /* JADX INFO: renamed from: f */
    private int f234f = 5;

    /* JADX INFO: renamed from: b */
    boolean f231b = true;

    @Override // com.baidu.mapapi.map.OverlayOptions
    /* JADX INFO: renamed from: a */
    Overlay mo238a() {
        Arc arc = new Arc();
        arc.f492r = this.f231b;
        arc.f491q = this.f230a;
        arc.f493s = this.f232c;
        arc.f224a = this.f233e;
        arc.f225b = this.f234f;
        arc.f226c = this.f235g;
        arc.f227d = this.f236h;
        arc.f228e = this.f237i;
        return arc;
    }

    public ArcOptions color(int i) {
        this.f233e = i;
        return this;
    }

    public ArcOptions extraInfo(Bundle bundle) {
        this.f232c = bundle;
        return this;
    }

    public int getColor() {
        return this.f233e;
    }

    public LatLng getEndPoint() {
        return this.f237i;
    }

    public Bundle getExtraInfo() {
        return this.f232c;
    }

    public LatLng getMiddlePoint() {
        return this.f236h;
    }

    public LatLng getStartPoint() {
        return this.f235g;
    }

    public int getWidth() {
        return this.f234f;
    }

    public int getZIndex() {
        return this.f230a;
    }

    public boolean isVisible() {
        return this.f231b;
    }

    public ArcOptions points(LatLng latLng, LatLng latLng2, LatLng latLng3) {
        if (latLng == null || latLng2 == null || latLng3 == null) {
            throw new IllegalArgumentException("start and middle and end points can not be null");
        }
        if (latLng == latLng2 || latLng == latLng3 || latLng2 == latLng3) {
            throw new IllegalArgumentException("start and middle and end points can not be same");
        }
        this.f235g = latLng;
        this.f236h = latLng2;
        this.f237i = latLng3;
        return this;
    }

    public ArcOptions visible(boolean z) {
        this.f231b = z;
        return this;
    }

    public ArcOptions width(int i) {
        if (i > 0) {
            this.f234f = i;
        }
        return this;
    }

    public ArcOptions zIndex(int i) {
        this.f230a = i;
        return this;
    }
}
