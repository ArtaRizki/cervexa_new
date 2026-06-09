package com.tencent.bugly.crashreport.common.strategy;

import android.os.Parcel;
import android.os.Parcelable;
import com.tencent.bugly.proguard.C2023z;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public class StrategyBean implements Parcelable {
    public static final Parcelable.Creator<StrategyBean> CREATOR = new Parcelable.Creator<StrategyBean>() { // from class: com.tencent.bugly.crashreport.common.strategy.StrategyBean.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ StrategyBean createFromParcel(Parcel parcel) {
            return new StrategyBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ StrategyBean[] newArray(int i) {
            return new StrategyBean[i];
        }
    };

    /* JADX INFO: renamed from: a */
    public static String f2426a = "https://android.bugly.qq.com/rqd/async";

    /* JADX INFO: renamed from: b */
    public static String f2427b = "https://android.bugly.qq.com/rqd/async";

    /* JADX INFO: renamed from: c */
    public long f2428c;

    /* JADX INFO: renamed from: d */
    public long f2429d;

    /* JADX INFO: renamed from: e */
    public boolean f2430e;

    /* JADX INFO: renamed from: f */
    public boolean f2431f;

    /* JADX INFO: renamed from: g */
    public boolean f2432g;

    /* JADX INFO: renamed from: h */
    public boolean f2433h;

    /* JADX INFO: renamed from: i */
    public boolean f2434i;

    /* JADX INFO: renamed from: j */
    public boolean f2435j;

    /* JADX INFO: renamed from: k */
    public boolean f2436k;

    /* JADX INFO: renamed from: l */
    public boolean f2437l;

    /* JADX INFO: renamed from: m */
    public boolean f2438m;

    /* JADX INFO: renamed from: n */
    public long f2439n;

    /* JADX INFO: renamed from: o */
    public long f2440o;

    /* JADX INFO: renamed from: p */
    public String f2441p;

    /* JADX INFO: renamed from: q */
    public String f2442q;

    /* JADX INFO: renamed from: r */
    public String f2443r;

    /* JADX INFO: renamed from: s */
    public Map<String, String> f2444s;

    /* JADX INFO: renamed from: t */
    public int f2445t;

    /* JADX INFO: renamed from: u */
    public long f2446u;

    /* JADX INFO: renamed from: v */
    public long f2447v;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public StrategyBean() {
        this.f2428c = -1L;
        this.f2429d = -1L;
        this.f2430e = true;
        this.f2431f = true;
        this.f2432g = true;
        this.f2433h = true;
        this.f2434i = false;
        this.f2435j = true;
        this.f2436k = true;
        this.f2437l = true;
        this.f2438m = true;
        this.f2440o = 30000L;
        this.f2441p = f2426a;
        this.f2442q = f2427b;
        this.f2445t = 10;
        this.f2446u = 300000L;
        this.f2447v = -1L;
        this.f2429d = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append("S(@L@L");
        sb.append("@)");
        sb.toString();
        sb.setLength(0);
        sb.append("*^@K#K");
        sb.append("@!");
        this.f2443r = sb.toString();
    }

    public StrategyBean(Parcel parcel) {
        this.f2428c = -1L;
        this.f2429d = -1L;
        boolean z = true;
        this.f2430e = true;
        this.f2431f = true;
        this.f2432g = true;
        this.f2433h = true;
        this.f2434i = false;
        this.f2435j = true;
        this.f2436k = true;
        this.f2437l = true;
        this.f2438m = true;
        this.f2440o = 30000L;
        this.f2441p = f2426a;
        this.f2442q = f2427b;
        this.f2445t = 10;
        this.f2446u = 300000L;
        this.f2447v = -1L;
        try {
            this.f2429d = parcel.readLong();
            this.f2430e = parcel.readByte() == 1;
            this.f2431f = parcel.readByte() == 1;
            this.f2432g = parcel.readByte() == 1;
            this.f2441p = parcel.readString();
            this.f2442q = parcel.readString();
            this.f2443r = parcel.readString();
            this.f2444s = C2023z.m1921b(parcel);
            this.f2433h = parcel.readByte() == 1;
            this.f2434i = parcel.readByte() == 1;
            this.f2437l = parcel.readByte() == 1;
            this.f2438m = parcel.readByte() == 1;
            this.f2440o = parcel.readLong();
            this.f2435j = parcel.readByte() == 1;
            if (parcel.readByte() != 1) {
                z = false;
            }
            this.f2436k = z;
            this.f2439n = parcel.readLong();
            this.f2445t = parcel.readInt();
            this.f2446u = parcel.readLong();
            this.f2447v = parcel.readLong();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.f2429d);
        parcel.writeByte(this.f2430e ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f2431f ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f2432g ? (byte) 1 : (byte) 0);
        parcel.writeString(this.f2441p);
        parcel.writeString(this.f2442q);
        parcel.writeString(this.f2443r);
        C2023z.m1923b(parcel, this.f2444s);
        parcel.writeByte(this.f2433h ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f2434i ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f2437l ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f2438m ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.f2440o);
        parcel.writeByte(this.f2435j ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f2436k ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.f2439n);
        parcel.writeInt(this.f2445t);
        parcel.writeLong(this.f2446u);
        parcel.writeLong(this.f2447v);
    }
}
