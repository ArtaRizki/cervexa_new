package com.baidu.mapapi.model;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: renamed from: com.baidu.mapapi.model.a */
/* JADX INFO: loaded from: classes.dex */
final class C0703a implements Parcelable.Creator<LatLng> {
    C0703a() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public LatLng createFromParcel(Parcel parcel) {
        return new LatLng(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public LatLng[] newArray(int i) {
        return new LatLng[i];
    }
}
