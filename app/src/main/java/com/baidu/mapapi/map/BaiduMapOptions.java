package com.baidu.mapapi.map;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.model.LatLng;
import com.baidu.platform.comapi.map.C0729C;

/* JADX INFO: loaded from: classes.dex */
public final class BaiduMapOptions implements Parcelable {
    public static final Parcelable.Creator<BaiduMapOptions> CREATOR = new C0682e();

    /* JADX INFO: renamed from: a */
    MapStatus f283a;

    /* JADX INFO: renamed from: b */
    boolean f284b;

    /* JADX INFO: renamed from: c */
    int f285c;

    /* JADX INFO: renamed from: d */
    boolean f286d;

    /* JADX INFO: renamed from: e */
    boolean f287e;

    /* JADX INFO: renamed from: f */
    boolean f288f;

    /* JADX INFO: renamed from: g */
    boolean f289g;

    /* JADX INFO: renamed from: h */
    boolean f290h;

    /* JADX INFO: renamed from: i */
    boolean f291i;

    /* JADX INFO: renamed from: j */
    LogoPosition f292j;

    /* JADX INFO: renamed from: k */
    Point f293k;

    /* JADX INFO: renamed from: l */
    Point f294l;

    public BaiduMapOptions() {
        this.f283a = new MapStatus(0.0f, new LatLng(39.914935d, 116.403119d), 0.0f, 12.0f, null, null);
        this.f284b = true;
        this.f285c = 1;
        this.f286d = true;
        this.f287e = true;
        this.f288f = true;
        this.f289g = true;
        this.f290h = true;
        this.f291i = true;
    }

    protected BaiduMapOptions(Parcel parcel) {
        this.f283a = new MapStatus(0.0f, new LatLng(39.914935d, 116.403119d), 0.0f, 12.0f, null, null);
        this.f284b = true;
        this.f285c = 1;
        this.f286d = true;
        this.f287e = true;
        this.f288f = true;
        this.f289g = true;
        this.f290h = true;
        this.f291i = true;
        this.f283a = (MapStatus) parcel.readParcelable(MapStatus.class.getClassLoader());
        this.f284b = parcel.readByte() != 0;
        this.f285c = parcel.readInt();
        this.f286d = parcel.readByte() != 0;
        this.f287e = parcel.readByte() != 0;
        this.f288f = parcel.readByte() != 0;
        this.f289g = parcel.readByte() != 0;
        this.f290h = parcel.readByte() != 0;
        this.f291i = parcel.readByte() != 0;
        this.f293k = (Point) parcel.readParcelable(Point.class.getClassLoader());
        this.f294l = (Point) parcel.readParcelable(Point.class.getClassLoader());
    }

    /* JADX INFO: renamed from: a */
    C0729C m277a() {
        return new C0729C().m571a(this.f283a.m318c()).m572a(this.f284b).m570a(this.f285c).m573b(this.f286d).m574c(this.f287e).m575d(this.f288f).m576e(this.f289g);
    }

    public BaiduMapOptions compassEnabled(boolean z) {
        this.f284b = z;
        return this;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BaiduMapOptions logoPosition(LogoPosition logoPosition) {
        this.f292j = logoPosition;
        return this;
    }

    public BaiduMapOptions mapStatus(MapStatus mapStatus) {
        if (mapStatus != null) {
            this.f283a = mapStatus;
        }
        return this;
    }

    public BaiduMapOptions mapType(int i) {
        this.f285c = i;
        return this;
    }

    public BaiduMapOptions overlookingGesturesEnabled(boolean z) {
        this.f288f = z;
        return this;
    }

    public BaiduMapOptions rotateGesturesEnabled(boolean z) {
        this.f286d = z;
        return this;
    }

    public BaiduMapOptions scaleControlEnabled(boolean z) {
        this.f291i = z;
        return this;
    }

    public BaiduMapOptions scaleControlPosition(Point point) {
        this.f293k = point;
        return this;
    }

    public BaiduMapOptions scrollGesturesEnabled(boolean z) {
        this.f287e = z;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f283a, i);
        parcel.writeByte(this.f284b ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.f285c);
        parcel.writeByte(this.f286d ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f287e ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f288f ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f289g ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f290h ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f291i ? (byte) 1 : (byte) 0);
        parcel.writeParcelable(this.f293k, i);
        parcel.writeParcelable(this.f294l, i);
    }

    public BaiduMapOptions zoomControlsEnabled(boolean z) {
        this.f290h = z;
        return this;
    }

    public BaiduMapOptions zoomControlsPosition(Point point) {
        this.f294l = point;
        return this;
    }

    public BaiduMapOptions zoomGesturesEnabled(boolean z) {
        this.f289g = z;
        return this;
    }
}
