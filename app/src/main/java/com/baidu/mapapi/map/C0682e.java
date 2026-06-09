package com.baidu.mapapi.map;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: renamed from: com.baidu.mapapi.map.e */
/* JADX INFO: loaded from: classes.dex */
final class C0682e implements Parcelable.Creator<BaiduMapOptions> {
    C0682e() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public BaiduMapOptions createFromParcel(Parcel parcel) {
        return new BaiduMapOptions(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public BaiduMapOptions[] newArray(int i) {
        return new BaiduMapOptions[i];
    }
}
