package com.baidu.mapapi.map;

import android.os.Bundle;
import androidx.core.view.ViewCompat;
import com.baidu.mapapi.model.LatLng;

/* JADX INFO: loaded from: classes.dex */
public final class DotOptions extends OverlayOptions {

    /* JADX INFO: renamed from: a */
    int f313a;

    /* JADX INFO: renamed from: c */
    Bundle f315c;

    /* JADX INFO: renamed from: d */
    private LatLng f316d;

    /* JADX INFO: renamed from: e */
    private int f317e = ViewCompat.MEASURED_STATE_MASK;

    /* JADX INFO: renamed from: f */
    private int f318f = 5;

    /* JADX INFO: renamed from: b */
    boolean f314b = true;

    @Override // com.baidu.mapapi.map.OverlayOptions
    /* JADX INFO: renamed from: a */
    Overlay mo238a() {
        Dot dot = new Dot();
        dot.f492r = this.f314b;
        dot.f491q = this.f313a;
        dot.f493s = this.f315c;
        dot.f311b = this.f317e;
        dot.f310a = this.f316d;
        dot.f312c = this.f318f;
        return dot;
    }

    public DotOptions center(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("dot center can not be null");
        }
        this.f316d = latLng;
        return this;
    }

    public DotOptions color(int i) {
        this.f317e = i;
        return this;
    }

    public DotOptions extraInfo(Bundle bundle) {
        this.f315c = bundle;
        return this;
    }

    public LatLng getCenter() {
        return this.f316d;
    }

    public int getColor() {
        return this.f317e;
    }

    public Bundle getExtraInfo() {
        return this.f315c;
    }

    public int getRadius() {
        return this.f318f;
    }

    public int getZIndex() {
        return this.f313a;
    }

    public boolean isVisible() {
        return this.f314b;
    }

    public DotOptions radius(int i) {
        if (i > 0) {
            this.f318f = i;
        }
        return this;
    }

    public DotOptions visible(boolean z) {
        this.f314b = z;
        return this;
    }

    public DotOptions zIndex(int i) {
        this.f313a = i;
        return this;
    }
}
