package com.baidu.mapapi.model;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: renamed from: com.baidu.mapapi.model.c */
/* JADX INFO: loaded from: classes.dex */
final class C0705c implements Parcelable.Creator<ParcelItem> {
    C0705c() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public ParcelItem createFromParcel(Parcel parcel) {
        ParcelItem parcelItem = new ParcelItem();
        parcelItem.setBundle(parcel.readBundle());
        return parcelItem;
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public ParcelItem[] newArray(int i) {
        return new ParcelItem[i];
    }
}
