package com.baidu.mapapi.map;

import android.graphics.Point;
import android.view.ViewGroup;
import com.baidu.mapapi.model.LatLng;

/* JADX INFO: loaded from: classes.dex */
public final class MapViewLayoutParams extends ViewGroup.LayoutParams {
    public static final int ALIGN_BOTTOM = 16;
    public static final int ALIGN_CENTER_HORIZONTAL = 4;
    public static final int ALIGN_CENTER_VERTICAL = 32;
    public static final int ALIGN_LEFT = 1;
    public static final int ALIGN_RIGHT = 2;
    public static final int ALIGN_TOP = 8;

    /* JADX INFO: renamed from: a */
    LatLng f436a;

    /* JADX INFO: renamed from: b */
    Point f437b;

    /* JADX INFO: renamed from: c */
    ELayoutMode f438c;

    /* JADX INFO: renamed from: d */
    float f439d;

    /* JADX INFO: renamed from: e */
    float f440e;

    /* JADX INFO: renamed from: f */
    int f441f;

    public static final class Builder {

        /* JADX INFO: renamed from: a */
        private int f442a;

        /* JADX INFO: renamed from: b */
        private int f443b;

        /* JADX INFO: renamed from: c */
        private LatLng f444c;

        /* JADX INFO: renamed from: d */
        private Point f445d;

        /* JADX INFO: renamed from: e */
        private ELayoutMode f446e = ELayoutMode.absoluteMode;

        /* JADX INFO: renamed from: f */
        private int f447f = 4;

        /* JADX INFO: renamed from: g */
        private int f448g = 16;

        /* JADX INFO: renamed from: h */
        private int f449h;

        public Builder align(int i, int i2) {
            if (i == 1 || i == 2 || i == 4) {
                this.f447f = i;
            }
            if (i2 == 8 || i2 == 16 || i2 == 32) {
                this.f448g = i2;
            }
            return this;
        }

        public MapViewLayoutParams build() {
            boolean z = true;
            if (this.f446e != ELayoutMode.mapMode ? this.f446e != ELayoutMode.absoluteMode || this.f445d != null : this.f444c != null) {
                z = false;
            }
            if (z) {
                throw new IllegalStateException("if it is map mode, you must supply position info; else if it is absolute mode, you must supply the point info");
            }
            return new MapViewLayoutParams(this.f442a, this.f443b, this.f444c, this.f445d, this.f446e, this.f447f, this.f448g, this.f449h);
        }

        public Builder height(int i) {
            this.f443b = i;
            return this;
        }

        public Builder layoutMode(ELayoutMode eLayoutMode) {
            this.f446e = eLayoutMode;
            return this;
        }

        public Builder point(Point point) {
            this.f445d = point;
            return this;
        }

        public Builder position(LatLng latLng) {
            this.f444c = latLng;
            return this;
        }

        public Builder width(int i) {
            this.f442a = i;
            return this;
        }

        public Builder yOffset(int i) {
            this.f449h = i;
            return this;
        }
    }

    public enum ELayoutMode {
        mapMode,
        absoluteMode
    }

    MapViewLayoutParams(int i, int i2, LatLng latLng, Point point, ELayoutMode eLayoutMode, int i3, int i4, int i5) {
        super(i, i2);
        this.f436a = latLng;
        this.f437b = point;
        this.f438c = eLayoutMode;
        if (i3 == 1) {
            this.f439d = 0.0f;
        } else if (i3 != 2) {
            this.f439d = 0.5f;
        } else {
            this.f439d = 1.0f;
        }
        if (i4 == 8) {
            this.f440e = 0.0f;
        } else if (i4 == 16 || i4 != 32) {
            this.f440e = 1.0f;
        } else {
            this.f440e = 0.5f;
        }
        this.f441f = i5;
    }
}
