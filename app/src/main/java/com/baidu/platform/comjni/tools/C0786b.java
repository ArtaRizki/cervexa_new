package com.baidu.platform.comjni.tools;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: renamed from: com.baidu.platform.comjni.tools.b */
/* JADX INFO: loaded from: classes.dex */
final class C0786b implements Parcelable.Creator<ParcelItem> {
    C0786b() {
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
