package com.baidu.mapapi.map;

import android.os.Bundle;
import androidx.core.view.ViewCompat;
import com.baidu.mapapi.model.LatLng;

/* JADX INFO: loaded from: classes.dex */
public final class CircleOptions extends OverlayOptions {

    /* JADX INFO: renamed from: d */
    private static final String f302d = CircleOptions.class.getSimpleName();

    /* JADX INFO: renamed from: a */
    int f303a;

    /* JADX INFO: renamed from: c */
    Bundle f305c;

    /* JADX INFO: renamed from: e */
    private LatLng f306e;

    /* JADX INFO: renamed from: g */
    private int f308g;

    /* JADX INFO: renamed from: h */
    private Stroke f309h;

    /* JADX INFO: renamed from: f */
    private int f307f = ViewCompat.MEASURED_STATE_MASK;

    /* JADX INFO: renamed from: b */
    boolean f304b = true;

    @Override // com.baidu.mapapi.map.OverlayOptions
    /* JADX INFO: renamed from: a */
    Overlay mo238a() {
        Circle circle = new Circle();
        circle.f492r = this.f304b;
        circle.f491q = this.f303a;
        circle.f493s = this.f305c;
        circle.f299b = this.f307f;
        circle.f298a = this.f306e;
        circle.f300c = this.f308g;
        circle.f301d = this.f309h;
        return circle;
    }

    public CircleOptions center(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("circle center can not be null");
        }
        this.f306e = latLng;
        return this;
    }

    public CircleOptions extraInfo(Bundle bundle) {
        this.f305c = bundle;
        return this;
    }

    public CircleOptions fillColor(int i) {
        this.f307f = i;
        return this;
    }

    public LatLng getCenter() {
        return this.f306e;
    }

    public Bundle getExtraInfo() {
        return this.f305c;
    }

    public int getFillColor() {
        return this.f307f;
    }

    public int getRadius() {
        return this.f308g;
    }

    public Stroke getStroke() {
        return this.f309h;
    }

    public int getZIndex() {
        return this.f303a;
    }

    public boolean isVisible() {
        return this.f304b;
    }

    public CircleOptions radius(int i) {
        this.f308g = i;
        return this;
    }

    public CircleOptions stroke(Stroke stroke) {
        this.f309h = stroke;
        return this;
    }

    public CircleOptions visible(boolean z) {
        this.f304b = z;
        return this;
    }

    public CircleOptions zIndex(int i) {
        this.f303a = i;
        return this;
    }
}
