package com.baidu.mapapi.map;

import android.os.Bundle;
import androidx.core.view.ViewCompat;
import com.baidu.mapapi.model.LatLng;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class PolygonOptions extends OverlayOptions {

    /* JADX INFO: renamed from: a */
    int f497a;

    /* JADX INFO: renamed from: c */
    Bundle f499c;

    /* JADX INFO: renamed from: d */
    private Stroke f500d;

    /* JADX INFO: renamed from: f */
    private List<LatLng> f502f;

    /* JADX INFO: renamed from: e */
    private int f501e = ViewCompat.MEASURED_STATE_MASK;

    /* JADX INFO: renamed from: b */
    boolean f498b = true;

    @Override // com.baidu.mapapi.map.OverlayOptions
    /* JADX INFO: renamed from: a */
    Overlay mo238a() {
        Polygon polygon = new Polygon();
        polygon.f492r = this.f498b;
        polygon.f491q = this.f497a;
        polygon.f493s = this.f499c;
        List<LatLng> list = this.f502f;
        if (list == null || list.size() < 2) {
            throw new IllegalStateException("when you add polyline, you must at least supply 2 points");
        }
        polygon.f496c = this.f502f;
        polygon.f495b = this.f501e;
        polygon.f494a = this.f500d;
        return polygon;
    }

    public PolygonOptions extraInfo(Bundle bundle) {
        this.f499c = bundle;
        return this;
    }

    public PolygonOptions fillColor(int i) {
        this.f501e = i;
        return this;
    }

    public Bundle getExtraInfo() {
        return this.f499c;
    }

    public int getFillColor() {
        return this.f501e;
    }

    public List<LatLng> getPoints() {
        return this.f502f;
    }

    public Stroke getStroke() {
        return this.f500d;
    }

    public int getZIndex() {
        return this.f497a;
    }

    public boolean isVisible() {
        return this.f498b;
    }

    public PolygonOptions points(List<LatLng> list) {
        if (list == null) {
            throw new IllegalArgumentException("points list can not be null");
        }
        if (list.size() <= 2) {
            throw new IllegalArgumentException("points count can not less than three");
        }
        if (list.contains(null)) {
            throw new IllegalArgumentException("points list can not contains null");
        }
        int i = 0;
        while (i < list.size()) {
            int i2 = i + 1;
            for (int i3 = i2; i3 < list.size(); i3++) {
                if (list.get(i) == list.get(i3)) {
                    throw new IllegalArgumentException("points list can not has same points");
                }
            }
            i = i2;
        }
        this.f502f = list;
        return this;
    }

    public PolygonOptions stroke(Stroke stroke) {
        this.f500d = stroke;
        return this;
    }

    public PolygonOptions visible(boolean z) {
        this.f498b = z;
        return this;
    }

    public PolygonOptions zIndex(int i) {
        this.f497a = i;
        return this;
    }
}
