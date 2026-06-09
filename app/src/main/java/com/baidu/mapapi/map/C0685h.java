package com.baidu.mapapi.map;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: renamed from: com.baidu.mapapi.map.h */
/* JADX INFO: loaded from: classes.dex */
final class C0685h implements Parcelable.Creator<MapStatus> {
    C0685h() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public MapStatus createFromParcel(Parcel parcel) {
        return new MapStatus(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public MapStatus[] newArray(int i) {
        return new MapStatus[i];
    }
}
