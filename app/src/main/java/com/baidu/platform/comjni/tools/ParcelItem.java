package com.baidu.platform.comjni.tools;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class ParcelItem implements Parcelable {

    /* JADX INFO: renamed from: a */
    public static final Parcelable.Creator<ParcelItem> f1126a = new C0786b();

    /* JADX INFO: renamed from: b */
    private Bundle f1127b;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Bundle getBundle() {
        return this.f1127b;
    }

    public void setBundle(Bundle bundle) {
        this.f1127b = bundle;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.f1127b);
    }
}
