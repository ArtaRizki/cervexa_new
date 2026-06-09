package com.tencent.bugly.crashreport.biz;

import android.os.Parcel;
import android.os.Parcelable;
import com.tencent.bugly.proguard.C2023z;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public class UserInfoBean implements Parcelable {
    public static final Parcelable.Creator<UserInfoBean> CREATOR = new Parcelable.Creator<UserInfoBean>() { // from class: com.tencent.bugly.crashreport.biz.UserInfoBean.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ UserInfoBean createFromParcel(Parcel parcel) {
            return new UserInfoBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ UserInfoBean[] newArray(int i) {
            return new UserInfoBean[i];
        }
    };

    /* JADX INFO: renamed from: a */
    public long f2305a;

    /* JADX INFO: renamed from: b */
    public int f2306b;

    /* JADX INFO: renamed from: c */
    public String f2307c;

    /* JADX INFO: renamed from: d */
    public String f2308d;

    /* JADX INFO: renamed from: e */
    public long f2309e;

    /* JADX INFO: renamed from: f */
    public long f2310f;

    /* JADX INFO: renamed from: g */
    public long f2311g;

    /* JADX INFO: renamed from: h */
    public long f2312h;

    /* JADX INFO: renamed from: i */
    public long f2313i;

    /* JADX INFO: renamed from: j */
    public String f2314j;

    /* JADX INFO: renamed from: k */
    public long f2315k;

    /* JADX INFO: renamed from: l */
    public boolean f2316l;

    /* JADX INFO: renamed from: m */
    public String f2317m;

    /* JADX INFO: renamed from: n */
    public String f2318n;

    /* JADX INFO: renamed from: o */
    public int f2319o;

    /* JADX INFO: renamed from: p */
    public int f2320p;

    /* JADX INFO: renamed from: q */
    public int f2321q;

    /* JADX INFO: renamed from: r */
    public Map<String, String> f2322r;

    /* JADX INFO: renamed from: s */
    public Map<String, String> f2323s;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public UserInfoBean() {
        this.f2315k = 0L;
        this.f2316l = false;
        this.f2317m = "unknown";
        this.f2320p = -1;
        this.f2321q = -1;
        this.f2322r = null;
        this.f2323s = null;
    }

    public UserInfoBean(Parcel parcel) {
        this.f2315k = 0L;
        this.f2316l = false;
        this.f2317m = "unknown";
        this.f2320p = -1;
        this.f2321q = -1;
        this.f2322r = null;
        this.f2323s = null;
        this.f2306b = parcel.readInt();
        this.f2307c = parcel.readString();
        this.f2308d = parcel.readString();
        this.f2309e = parcel.readLong();
        this.f2310f = parcel.readLong();
        this.f2311g = parcel.readLong();
        this.f2312h = parcel.readLong();
        this.f2313i = parcel.readLong();
        this.f2314j = parcel.readString();
        this.f2315k = parcel.readLong();
        this.f2316l = parcel.readByte() == 1;
        this.f2317m = parcel.readString();
        this.f2320p = parcel.readInt();
        this.f2321q = parcel.readInt();
        this.f2322r = C2023z.m1921b(parcel);
        this.f2323s = C2023z.m1921b(parcel);
        this.f2318n = parcel.readString();
        this.f2319o = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f2306b);
        parcel.writeString(this.f2307c);
        parcel.writeString(this.f2308d);
        parcel.writeLong(this.f2309e);
        parcel.writeLong(this.f2310f);
        parcel.writeLong(this.f2311g);
        parcel.writeLong(this.f2312h);
        parcel.writeLong(this.f2313i);
        parcel.writeString(this.f2314j);
        parcel.writeLong(this.f2315k);
        parcel.writeByte(this.f2316l ? (byte) 1 : (byte) 0);
        parcel.writeString(this.f2317m);
        parcel.writeInt(this.f2320p);
        parcel.writeInt(this.f2321q);
        C2023z.m1923b(parcel, this.f2322r);
        C2023z.m1923b(parcel, this.f2323s);
        parcel.writeString(this.f2318n);
        parcel.writeInt(this.f2319o);
    }
}
