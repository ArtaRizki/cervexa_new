package com.baidu.mapapi.model;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: renamed from: com.baidu.mapapi.model.b */
/* JADX INFO: loaded from: classes.dex */
final class C0704b implements Parcelable.Creator<LatLngBounds> {
    C0704b() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public LatLngBounds createFromParcel(Parcel parcel) {
        return new LatLngBounds(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public LatLngBounds[] newArray(int i) {
        return new LatLngBounds[i];
    }
}
