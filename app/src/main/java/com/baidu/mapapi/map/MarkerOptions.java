package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.LatLng;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class MarkerOptions extends OverlayOptions {

    /* JADX INFO: renamed from: a */
    int f465a;

    /* JADX INFO: renamed from: c */
    Bundle f467c;

    /* JADX INFO: renamed from: d */
    private LatLng f468d;

    /* JADX INFO: renamed from: e */
    private BitmapDescriptor f469e;

    /* JADX INFO: renamed from: j */
    private float f474j;

    /* JADX INFO: renamed from: k */
    private String f475k;

    /* JADX INFO: renamed from: l */
    private int f476l;

    /* JADX INFO: renamed from: n */
    private ArrayList<BitmapDescriptor> f478n;

    /* JADX INFO: renamed from: f */
    private float f470f = 0.5f;

    /* JADX INFO: renamed from: g */
    private float f471g = 1.0f;

    /* JADX INFO: renamed from: h */
    private boolean f472h = true;

    /* JADX INFO: renamed from: i */
    private boolean f473i = false;

    /* JADX INFO: renamed from: m */
    private boolean f477m = false;

    /* JADX INFO: renamed from: o */
    private int f479o = 20;

    /* JADX INFO: renamed from: p */
    private float f480p = 1.0f;

    /* JADX INFO: renamed from: q */
    private int f481q = MarkerAnimateType.none.ordinal();

    /* JADX INFO: renamed from: b */
    boolean f466b = true;

    public enum MarkerAnimateType {
        none,
        drop,
        grow
    }

    /* JADX INFO: renamed from: a */
    MarkerOptions m336a(int i) {
        this.f476l = i;
        return this;
    }

    @Override // com.baidu.mapapi.map.OverlayOptions
    /* JADX INFO: renamed from: a */
    Overlay mo238a() {
        Marker marker = new Marker();
        marker.f492r = this.f466b;
        marker.f491q = this.f465a;
        marker.f493s = this.f467c;
        LatLng latLng = this.f468d;
        if (latLng == null) {
            throw new IllegalStateException("when you add marker, you must set the position");
        }
        marker.f450a = latLng;
        if (this.f469e == null && this.f478n == null) {
            throw new IllegalStateException("when you add marker, you must set the icon or icons");
        }
        marker.f451b = this.f469e;
        marker.f452c = this.f470f;
        marker.f453d = this.f471g;
        marker.f454e = this.f472h;
        marker.f455f = this.f473i;
        marker.f456g = this.f474j;
        marker.f457h = this.f475k;
        marker.f458i = this.f476l;
        marker.f459j = this.f477m;
        marker.f463n = this.f478n;
        marker.f464o = this.f479o;
        marker.f461l = this.f480p;
        marker.f462m = this.f481q;
        return marker;
    }

    public MarkerOptions alpha(float f) {
        if (f < 0.0f || f > 1.0f) {
            this.f480p = 1.0f;
            return this;
        }
        this.f480p = f;
        return this;
    }

    public MarkerOptions anchor(float f, float f2) {
        if (f >= 0.0f && f <= 1.0f && f2 >= 0.0f && f2 <= 1.0f) {
            this.f470f = f;
            this.f471g = f2;
        }
        return this;
    }

    public MarkerOptions animateType(MarkerAnimateType markerAnimateType) {
        if (markerAnimateType == null) {
            markerAnimateType = MarkerAnimateType.none;
        }
        this.f481q = markerAnimateType.ordinal();
        return this;
    }

    public MarkerOptions draggable(boolean z) {
        this.f473i = z;
        return this;
    }

    public MarkerOptions extraInfo(Bundle bundle) {
        this.f467c = bundle;
        return this;
    }

    public MarkerOptions flat(boolean z) {
        this.f477m = z;
        return this;
    }

    public float getAlpha() {
        return this.f480p;
    }

    public float getAnchorX() {
        return this.f470f;
    }

    public float getAnchorY() {
        return this.f471g;
    }

    public MarkerAnimateType getAnimateType() {
        int i = this.f481q;
        return i != 1 ? i != 2 ? MarkerAnimateType.none : MarkerAnimateType.grow : MarkerAnimateType.drop;
    }

    public Bundle getExtraInfo() {
        return this.f467c;
    }

    public BitmapDescriptor getIcon() {
        return this.f469e;
    }

    public ArrayList<BitmapDescriptor> getIcons() {
        return this.f478n;
    }

    public int getPeriod() {
        return this.f479o;
    }

    public LatLng getPosition() {
        return this.f468d;
    }

    public float getRotate() {
        return this.f474j;
    }

    public String getTitle() {
        return this.f475k;
    }

    public int getZIndex() {
        return this.f465a;
    }

    public MarkerOptions icon(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor == null) {
            throw new IllegalArgumentException("marker's icon can not be null");
        }
        this.f469e = bitmapDescriptor;
        return this;
    }

    public MarkerOptions icons(ArrayList<BitmapDescriptor> arrayList) {
        if (arrayList == null) {
            throw new IllegalArgumentException("marker's icons can not be null");
        }
        if (arrayList.size() == 0) {
            return this;
        }
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) == null || arrayList.get(i).f295a == null) {
                return this;
            }
        }
        this.f478n = arrayList;
        return this;
    }

    public boolean isDraggable() {
        return this.f473i;
    }

    public boolean isFlat() {
        return this.f477m;
    }

    public boolean isPerspective() {
        return this.f472h;
    }

    public boolean isVisible() {
        return this.f466b;
    }

    public MarkerOptions period(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("marker's period must be greater than zero ");
        }
        this.f479o = i;
        return this;
    }

    public MarkerOptions perspective(boolean z) {
        this.f472h = z;
        return this;
    }

    public MarkerOptions position(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("marker's position can not be null");
        }
        this.f468d = latLng;
        return this;
    }

    public MarkerOptions rotate(float f) {
        while (f < 0.0f) {
            f += 360.0f;
        }
        this.f474j = f % 360.0f;
        return this;
    }

    public MarkerOptions title(String str) {
        this.f475k = str;
        return this;
    }

    public MarkerOptions visible(boolean z) {
        this.f466b = z;
        return this;
    }

    public MarkerOptions zIndex(int i) {
        this.f465a = i;
        return this;
    }
}
