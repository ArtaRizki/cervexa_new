package com.baidu.mapapi.map;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.C0731E;

/* JADX INFO: loaded from: classes.dex */
public final class MapStatus implements Parcelable {
    public static final Parcelable.Creator<MapStatus> CREATOR = new C0685h();

    /* JADX INFO: renamed from: a */
    C0731E f388a;

    /* JADX INFO: renamed from: b */
    private double f389b;
    public final LatLngBounds bound;

    /* JADX INFO: renamed from: c */
    private double f390c;
    public final float overlook;
    public final float rotate;
    public final LatLng target;
    public final Point targetScreen;
    public WinRound winRound;
    public final float zoom;

    public static final class Builder {

        /* JADX INFO: renamed from: a */
        private float f391a;

        /* JADX INFO: renamed from: b */
        private LatLng f392b;

        /* JADX INFO: renamed from: c */
        private float f393c;

        /* JADX INFO: renamed from: d */
        private float f394d;

        /* JADX INFO: renamed from: e */
        private Point f395e;

        /* JADX INFO: renamed from: f */
        private LatLngBounds f396f;

        /* JADX INFO: renamed from: g */
        private double f397g;

        /* JADX INFO: renamed from: h */
        private double f398h;

        public Builder() {
            this.f391a = -2.1474836E9f;
            this.f392b = null;
            this.f393c = -2.1474836E9f;
            this.f394d = -2.1474836E9f;
            this.f395e = null;
            this.f396f = null;
            this.f397g = 0.0d;
            this.f398h = 0.0d;
        }

        public Builder(MapStatus mapStatus) {
            this.f391a = -2.1474836E9f;
            this.f392b = null;
            this.f393c = -2.1474836E9f;
            this.f394d = -2.1474836E9f;
            this.f395e = null;
            this.f396f = null;
            this.f397g = 0.0d;
            this.f398h = 0.0d;
            this.f391a = mapStatus.rotate;
            this.f392b = mapStatus.target;
            this.f393c = mapStatus.overlook;
            this.f394d = mapStatus.zoom;
            this.f395e = mapStatus.targetScreen;
            this.f397g = mapStatus.m315a();
            this.f398h = mapStatus.m316b();
        }

        public MapStatus build() {
            return new MapStatus(this.f391a, this.f392b, this.f393c, this.f394d, this.f395e, this.f396f);
        }

        public Builder overlook(float f) {
            this.f393c = f;
            return this;
        }

        public Builder rotate(float f) {
            this.f391a = f;
            return this;
        }

        public Builder target(LatLng latLng) {
            this.f392b = latLng;
            return this;
        }

        public Builder targetScreen(Point point) {
            this.f395e = point;
            return this;
        }

        public Builder zoom(float f) {
            this.f394d = f;
            return this;
        }
    }

    MapStatus(float f, LatLng latLng, float f2, float f3, Point point, double d, double d2, LatLngBounds latLngBounds) {
        this.rotate = f;
        this.target = latLng;
        this.overlook = f2;
        this.zoom = f3;
        this.targetScreen = point;
        this.f389b = d;
        this.f390c = d2;
        this.bound = latLngBounds;
    }

    MapStatus(float f, LatLng latLng, float f2, float f3, Point point, LatLngBounds latLngBounds) {
        this.rotate = f;
        this.target = latLng;
        this.overlook = f2;
        this.zoom = f3;
        this.targetScreen = point;
        if (latLng != null) {
            this.f389b = CoordUtil.ll2mc(latLng).getLongitudeE6();
            this.f390c = CoordUtil.ll2mc(this.target).getLatitudeE6();
        }
        this.bound = latLngBounds;
    }

    MapStatus(float f, LatLng latLng, float f2, float f3, Point point, C0731E c0731e, double d, double d2, LatLngBounds latLngBounds, WinRound winRound) {
        this.rotate = f;
        this.target = latLng;
        this.overlook = f2;
        this.zoom = f3;
        this.targetScreen = point;
        this.f388a = c0731e;
        this.f389b = d;
        this.f390c = d2;
        this.bound = latLngBounds;
        this.winRound = winRound;
    }

    protected MapStatus(Parcel parcel) {
        this.rotate = parcel.readFloat();
        this.target = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
        this.overlook = parcel.readFloat();
        this.zoom = parcel.readFloat();
        this.targetScreen = (Point) parcel.readParcelable(Point.class.getClassLoader());
        this.bound = (LatLngBounds) parcel.readParcelable(LatLngBounds.class.getClassLoader());
        this.f389b = parcel.readDouble();
        this.f390c = parcel.readDouble();
    }

    /* JADX INFO: renamed from: a */
    static MapStatus m314a(C0731E c0731e) {
        if (c0731e == null) {
            return null;
        }
        float f = c0731e.f846b;
        double d = c0731e.f849e;
        double d2 = c0731e.f848d;
        LatLng latLngMc2ll = CoordUtil.mc2ll(new GeoPoint(d, d2));
        float f2 = c0731e.f847c;
        float f3 = c0731e.f845a;
        Point point = new Point(c0731e.f850f, c0731e.f851g);
        LatLng latLngMc2ll2 = CoordUtil.mc2ll(new GeoPoint(c0731e.f855k.f868e.f713y, c0731e.f855k.f868e.f712x));
        LatLng latLngMc2ll3 = CoordUtil.mc2ll(new GeoPoint(c0731e.f855k.f869f.f713y, c0731e.f855k.f869f.f712x));
        LatLng latLngMc2ll4 = CoordUtil.mc2ll(new GeoPoint(c0731e.f855k.f871h.f713y, c0731e.f855k.f871h.f712x));
        LatLng latLngMc2ll5 = CoordUtil.mc2ll(new GeoPoint(c0731e.f855k.f870g.f713y, c0731e.f855k.f870g.f712x));
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(latLngMc2ll2);
        builder.include(latLngMc2ll3);
        builder.include(latLngMc2ll4);
        builder.include(latLngMc2ll5);
        return new MapStatus(f, latLngMc2ll, f2, f3, point, c0731e, d2, d, builder.build(), c0731e.f854j);
    }

    /* JADX INFO: renamed from: a */
    double m315a() {
        return this.f389b;
    }

    /* JADX INFO: renamed from: b */
    double m316b() {
        return this.f390c;
    }

    /* JADX INFO: renamed from: b */
    C0731E m317b(C0731E c0731e) {
        float f = this.rotate;
        if (f != -2.1474836E9f) {
            c0731e.f846b = (int) f;
        }
        float f2 = this.zoom;
        if (f2 != -2.1474836E9f) {
            c0731e.f845a = f2;
        }
        float f3 = this.overlook;
        if (f3 != -2.1474836E9f) {
            c0731e.f847c = (int) f3;
        }
        LatLng latLng = this.target;
        if (latLng != null) {
            CoordUtil.ll2mc(latLng);
            c0731e.f848d = this.f389b;
            c0731e.f849e = this.f390c;
        }
        Point point = this.targetScreen;
        if (point != null) {
            c0731e.f850f = point.x;
            c0731e.f851g = this.targetScreen.y;
        }
        return c0731e;
    }

    /* JADX INFO: renamed from: c */
    C0731E m318c() {
        return m317b(new C0731E());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.target != null) {
            sb.append("target lat: " + this.target.latitude + "\n");
            sb.append("target lng: " + this.target.longitude + "\n");
        }
        if (this.targetScreen != null) {
            sb.append("target screen x: " + this.targetScreen.x + "\n");
            sb.append("target screen y: " + this.targetScreen.y + "\n");
        }
        sb.append("zoom: " + this.zoom + "\n");
        sb.append("rotate: " + this.rotate + "\n");
        sb.append("overlook: " + this.overlook + "\n");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.rotate);
        parcel.writeParcelable(this.target, i);
        parcel.writeFloat(this.overlook);
        parcel.writeFloat(this.zoom);
        parcel.writeParcelable(this.targetScreen, i);
        parcel.writeParcelable(this.bound, i);
        parcel.writeDouble(this.f389b);
        parcel.writeDouble(this.f390c);
    }
}
