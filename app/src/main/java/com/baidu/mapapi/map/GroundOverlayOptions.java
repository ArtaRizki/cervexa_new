package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;

/* JADX INFO: loaded from: classes.dex */
public final class GroundOverlayOptions extends OverlayOptions {

    /* JADX INFO: renamed from: a */
    int f336a;

    /* JADX INFO: renamed from: c */
    Bundle f338c;

    /* JADX INFO: renamed from: d */
    private BitmapDescriptor f339d;

    /* JADX INFO: renamed from: e */
    private LatLng f340e;

    /* JADX INFO: renamed from: f */
    private int f341f;

    /* JADX INFO: renamed from: g */
    private int f342g;

    /* JADX INFO: renamed from: j */
    private LatLngBounds f345j;

    /* JADX INFO: renamed from: h */
    private float f343h = 0.5f;

    /* JADX INFO: renamed from: i */
    private float f344i = 0.5f;

    /* JADX INFO: renamed from: k */
    private float f346k = 1.0f;

    /* JADX INFO: renamed from: b */
    boolean f337b = true;

    @Override // com.baidu.mapapi.map.OverlayOptions
    /* JADX INFO: renamed from: a */
    Overlay mo238a() {
        LatLngBounds latLngBounds;
        int i;
        LatLng latLng;
        GroundOverlay groundOverlay = new GroundOverlay();
        groundOverlay.f492r = this.f337b;
        groundOverlay.f491q = this.f336a;
        groundOverlay.f493s = this.f338c;
        BitmapDescriptor bitmapDescriptor = this.f339d;
        if (bitmapDescriptor == null) {
            throw new IllegalStateException("when you add ground overlay, you must set the image");
        }
        groundOverlay.f328b = bitmapDescriptor;
        if (this.f345j != null || (latLng = this.f340e) == null) {
            if (this.f340e != null || (latLngBounds = this.f345j) == null) {
                throw new IllegalStateException("when you add ground overlay, you must set one of position or bounds");
            }
            groundOverlay.f334h = latLngBounds;
            i = 1;
        } else {
            if (this.f341f <= 0 || this.f342g <= 0) {
                throw new IllegalArgumentException("when you add ground overlay, the width and height must greater than 0");
            }
            groundOverlay.f329c = latLng;
            groundOverlay.f332f = this.f343h;
            groundOverlay.f333g = this.f344i;
            groundOverlay.f330d = this.f341f;
            groundOverlay.f331e = this.f342g;
            i = 2;
        }
        groundOverlay.f327a = i;
        groundOverlay.f335i = this.f346k;
        return groundOverlay;
    }

    public GroundOverlayOptions anchor(float f, float f2) {
        if (f >= 0.0f && f <= 1.0f && f2 >= 0.0f && f2 <= 1.0f) {
            this.f343h = f;
            this.f344i = f2;
        }
        return this;
    }

    public GroundOverlayOptions dimensions(int i) {
        this.f341f = i;
        this.f342g = Integer.MAX_VALUE;
        return this;
    }

    public GroundOverlayOptions dimensions(int i, int i2) {
        this.f341f = i;
        this.f342g = i2;
        return this;
    }

    public GroundOverlayOptions extraInfo(Bundle bundle) {
        this.f338c = bundle;
        return this;
    }

    public float getAnchorX() {
        return this.f343h;
    }

    public float getAnchorY() {
        return this.f344i;
    }

    public LatLngBounds getBounds() {
        return this.f345j;
    }

    public Bundle getExtraInfo() {
        return this.f338c;
    }

    public int getHeight() {
        int i = this.f342g;
        return i == Integer.MAX_VALUE ? (int) ((this.f341f * this.f339d.f295a.getHeight()) / this.f339d.f295a.getWidth()) : i;
    }

    public BitmapDescriptor getImage() {
        return this.f339d;
    }

    public LatLng getPosition() {
        return this.f340e;
    }

    public float getTransparency() {
        return this.f346k;
    }

    public int getWidth() {
        return this.f341f;
    }

    public int getZIndex() {
        return this.f336a;
    }

    public GroundOverlayOptions image(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor == null) {
            throw new IllegalArgumentException("image can not be null");
        }
        this.f339d = bitmapDescriptor;
        return this;
    }

    public boolean isVisible() {
        return this.f337b;
    }

    public GroundOverlayOptions position(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("position can not be null");
        }
        this.f340e = latLng;
        return this;
    }

    public GroundOverlayOptions positionFromBounds(LatLngBounds latLngBounds) {
        if (latLngBounds == null) {
            throw new IllegalArgumentException("bounds can not be null");
        }
        this.f345j = latLngBounds;
        return this;
    }

    public GroundOverlayOptions transparency(float f) {
        if (f <= 1.0f && f >= 0.0f) {
            this.f346k = f;
        }
        return this;
    }

    public GroundOverlayOptions visible(boolean z) {
        this.f337b = z;
        return this;
    }

    public GroundOverlayOptions zIndex(int i) {
        this.f336a = i;
        return this;
    }
}
