package com.tencent.bugly.crashreport.common.info;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public class PlugInBean implements Parcelable {
    public static final Parcelable.Creator<PlugInBean> CREATOR = new Parcelable.Creator<PlugInBean>() { // from class: com.tencent.bugly.crashreport.common.info.PlugInBean.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ PlugInBean createFromParcel(Parcel parcel) {
            return new PlugInBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ PlugInBean[] newArray(int i) {
            return new PlugInBean[i];
        }
    };

    /* JADX INFO: renamed from: a */
    public final String f2353a;

    /* JADX INFO: renamed from: b */
    public final String f2354b;

    /* JADX INFO: renamed from: c */
    public final String f2355c;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PlugInBean(String str, String str2, String str3) {
        this.f2353a = str;
        this.f2354b = str2;
        this.f2355c = str3;
    }

    public String toString() {
        return "plid:" + this.f2353a + " plV:" + this.f2354b + " plUUID:" + this.f2355c;
    }

    public PlugInBean(Parcel parcel) {
        this.f2353a = parcel.readString();
        this.f2354b = parcel.readString();
        this.f2355c = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f2353a);
        parcel.writeString(this.f2354b);
        parcel.writeString(this.f2355c);
    }
}
