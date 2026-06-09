package com.baidu.trace.model;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: renamed from: com.baidu.trace.model.a */
/* JADX INFO: loaded from: classes.dex */
final class C0868a implements Parcelable.Creator<TraceLocation> {
    C0868a() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ TraceLocation createFromParcel(Parcel parcel) {
        return new TraceLocation(parcel, (byte) 0);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ TraceLocation[] newArray(int i) {
        return new TraceLocation[i];
    }
}
