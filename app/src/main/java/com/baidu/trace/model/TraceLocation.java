package com.baidu.trace.model;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class TraceLocation extends BaseResponse implements Parcelable {
    public static final Parcelable.Creator<TraceLocation> CREATOR = new C0868a();

    /* JADX INFO: renamed from: a */
    private LocType f1822a;

    /* JADX INFO: renamed from: b */
    private String f1823b;

    /* JADX INFO: renamed from: c */
    private String f1824c;

    /* JADX INFO: renamed from: d */
    private String f1825d;

    /* JADX INFO: renamed from: e */
    private double f1826e;

    /* JADX INFO: renamed from: f */
    private double f1827f;

    /* JADX INFO: renamed from: g */
    private CoordType f1828g;

    /* JADX INFO: renamed from: h */
    private double f1829h;

    /* JADX INFO: renamed from: i */
    private float f1830i;

    /* JADX INFO: renamed from: j */
    private float f1831j;

    /* JADX INFO: renamed from: k */
    private int f1832k;

    /* JADX INFO: renamed from: l */
    private String f1833l;

    public TraceLocation() {
        this.f1822a = LocType.NONE;
        this.f1823b = "";
        this.f1824c = "";
        this.f1825d = "";
        this.f1826e = 0.0d;
        this.f1827f = 0.0d;
        this.f1828g = CoordType.bd09ll;
        this.f1829h = 0.0d;
        this.f1830i = 0.0f;
        this.f1831j = 0.0f;
        this.f1832k = 0;
        this.f1833l = "";
    }

    public TraceLocation(int i, int i2, String str) {
        super(i, i2, str);
        this.f1822a = LocType.NONE;
        this.f1823b = "";
        this.f1824c = "";
        this.f1825d = "";
        this.f1826e = 0.0d;
        this.f1827f = 0.0d;
        this.f1828g = CoordType.bd09ll;
        this.f1829h = 0.0d;
        this.f1830i = 0.0f;
        this.f1831j = 0.0f;
        this.f1832k = 0;
        this.f1833l = "";
    }

    public TraceLocation(int i, int i2, String str, String str2, String str3, String str4, double d, double d2, CoordType coordType, double d3, float f, float f2, int i3, String str5) {
        super(i, i2, str);
        this.f1822a = LocType.NONE;
        this.f1823b = "";
        this.f1824c = "";
        this.f1825d = "";
        this.f1826e = 0.0d;
        this.f1827f = 0.0d;
        this.f1828g = CoordType.bd09ll;
        this.f1829h = 0.0d;
        this.f1830i = 0.0f;
        this.f1831j = 0.0f;
        this.f1832k = 0;
        this.f1833l = "";
        this.f1823b = str2;
        this.f1824c = str3;
        this.f1825d = str4;
        this.f1826e = d;
        this.f1827f = d2;
        this.f1828g = coordType;
        this.f1829h = d3;
        this.f1830i = f;
        this.f1831j = f2;
        this.f1832k = i3;
        this.f1833l = str5;
    }

    private TraceLocation(Parcel parcel) {
        this.f1822a = LocType.NONE;
        this.f1823b = "";
        this.f1824c = "";
        this.f1825d = "";
        this.f1826e = 0.0d;
        this.f1827f = 0.0d;
        this.f1828g = CoordType.bd09ll;
        this.f1829h = 0.0d;
        this.f1830i = 0.0f;
        this.f1831j = 0.0f;
        this.f1832k = 0;
        this.f1833l = "";
        readFromParcel(parcel);
    }

    /* synthetic */ TraceLocation(Parcel parcel, byte b) {
        this(parcel);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final int getAltitude() {
        return this.f1832k;
    }

    public final String getBuilding() {
        return this.f1825d;
    }

    public final CoordType getCoordType() {
        return this.f1828g;
    }

    public final float getDirection() {
        return this.f1830i;
    }

    public final String getFloor() {
        return this.f1823b;
    }

    public final String getIndoor() {
        return this.f1824c;
    }

    public final double getLatitude() {
        return this.f1826e;
    }

    public final LocType getLocType() {
        return this.f1822a;
    }

    public final double getLongitude() {
        return this.f1827f;
    }

    public final double getRadius() {
        return this.f1829h;
    }

    public final float getSpeed() {
        return this.f1831j;
    }

    public final String getTime() {
        return this.f1833l;
    }

    public final void readFromParcel(Parcel parcel) {
        CoordType coordType;
        this.f1823b = parcel.readString();
        this.f1824c = parcel.readString();
        this.f1825d = parcel.readString();
        this.f1826e = parcel.readDouble();
        this.f1827f = parcel.readDouble();
        int i = parcel.readInt();
        if (i == 0) {
            coordType = CoordType.wgs84;
        } else {
            if (i != 1) {
                if (i == 2) {
                    coordType = CoordType.bd09ll;
                }
                this.f1829h = parcel.readDouble();
                this.f1830i = parcel.readFloat();
                this.f1831j = parcel.readFloat();
                this.f1832k = parcel.readInt();
                this.f1833l = parcel.readString();
            }
            coordType = CoordType.gcj02;
        }
        this.f1828g = coordType;
        this.f1829h = parcel.readDouble();
        this.f1830i = parcel.readFloat();
        this.f1831j = parcel.readFloat();
        this.f1832k = parcel.readInt();
        this.f1833l = parcel.readString();
    }

    public final void setAltitude(int i) {
        this.f1832k = i;
    }

    public final void setBuilding(String str) {
        this.f1825d = str;
    }

    public final void setCoordType(CoordType coordType) {
        this.f1828g = coordType;
    }

    public final void setDirection(float f) {
        this.f1830i = f;
    }

    public final void setFloor(String str) {
        this.f1823b = str;
    }

    public final void setIndoor(String str) {
        this.f1824c = str;
    }

    public final void setLatitude(double d) {
        this.f1826e = d;
    }

    public final void setLocType(LocType locType) {
        this.f1822a = locType;
    }

    public final void setLongitude(double d) {
        this.f1827f = d;
    }

    public final void setRadius(double d) {
        this.f1829h = d;
    }

    public final void setSpeed(float f) {
        this.f1831j = f;
    }

    public final void setTime(String str) {
        this.f1833l = str;
    }

    public final String toString() {
        StringBuffer stringBuffer = new StringBuffer("TraceLocation{");
        stringBuffer.append("tag=");
        stringBuffer.append(this.tag);
        stringBuffer.append(", status=");
        stringBuffer.append(this.status);
        stringBuffer.append(", message='");
        stringBuffer.append(this.message);
        stringBuffer.append('\'');
        stringBuffer.append(", locType=");
        stringBuffer.append(this.f1822a);
        stringBuffer.append(", floor='");
        stringBuffer.append(this.f1823b);
        stringBuffer.append('\'');
        stringBuffer.append(", indoor='");
        stringBuffer.append(this.f1824c);
        stringBuffer.append('\'');
        stringBuffer.append(", building='");
        stringBuffer.append(this.f1825d);
        stringBuffer.append('\'');
        stringBuffer.append(", latitude=");
        stringBuffer.append(this.f1826e);
        stringBuffer.append(", longitude=");
        stringBuffer.append(this.f1827f);
        stringBuffer.append(", coordType=");
        stringBuffer.append(this.f1828g);
        stringBuffer.append(", radius=");
        stringBuffer.append(this.f1829h);
        stringBuffer.append(", direction=");
        stringBuffer.append(this.f1830i);
        stringBuffer.append(", speed=");
        stringBuffer.append(this.f1831j);
        stringBuffer.append(", altitude=");
        stringBuffer.append(this.f1832k);
        stringBuffer.append(", time='");
        stringBuffer.append(this.f1833l);
        stringBuffer.append('\'');
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1823b);
        parcel.writeString(this.f1824c);
        parcel.writeString(this.f1825d);
        parcel.writeDouble(this.f1826e);
        parcel.writeDouble(this.f1827f);
        parcel.writeInt(this.f1828g.ordinal());
        parcel.writeDouble(this.f1829h);
        parcel.writeFloat(this.f1830i);
        parcel.writeFloat(this.f1831j);
        parcel.writeInt(this.f1832k);
        parcel.writeString(this.f1833l);
    }
}
