package com.jieli.lib.p015dv.control.json.bean;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.collection.ArrayMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class CmdInfo implements Parcelable {
    public static final Parcelable.Creator<CmdInfo> CREATOR = new Parcelable.Creator<CmdInfo>() { // from class: com.jieli.lib.dv.control.json.bean.CmdInfo.1
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public CmdInfo createFromParcel(Parcel parcel) {
            return new CmdInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public CmdInfo[] newArray(int i) {
            return new CmdInfo[i];
        }
    };

    /* JADX INFO: renamed from: b */
    private int f2120b;

    /* JADX INFO: renamed from: c */
    private String f2121c;

    /* JADX INFO: renamed from: d */
    private String f2122d;

    /* JADX INFO: renamed from: a */
    private String f2119a = getClass().getSimpleName();

    /* JADX INFO: renamed from: e */
    private ArrayMap<String, String> f2123e = null;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    CmdInfo(Parcel parcel) {
        this.f2120b = -100;
        this.f2122d = null;
        if (parcel != null) {
            this.f2120b = parcel.readInt();
            this.f2121c = parcel.readString();
            this.f2122d = parcel.readString();
            int i = parcel.readInt();
            for (int i2 = 0; i2 < i; i2++) {
                this.f2123e.put(parcel.readString(), parcel.readString());
            }
        }
    }

    public int getErrorType() {
        return this.f2120b;
    }

    public void setErrorType(int i) {
        this.f2120b = i;
    }

    public ArrayMap<String, String> getParams() {
        return this.f2123e;
    }

    public void setParams(String str, String str2) {
        ArrayMap<String, String> arrayMap = new ArrayMap<>();
        arrayMap.put(str, str2);
        setParams(arrayMap);
    }

    public void setParams(String str, String str2, String str3, String str4) {
        ArrayMap<String, String> arrayMap = new ArrayMap<>();
        arrayMap.put(str, str2);
        arrayMap.put(str3, str4);
        setParams(arrayMap);
    }

    public void setParams(String str, String str2, String str3, String str4, String str5, String str6) {
        ArrayMap<String, String> arrayMap = new ArrayMap<>();
        arrayMap.put(str, str2);
        arrayMap.put(str3, str4);
        arrayMap.put(str5, str6);
        setParams(arrayMap);
    }

    public void setParams(ArrayMap<String, String> arrayMap) {
        this.f2123e = arrayMap;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Topic:");
        sb.append(this.f2121c);
        sb.append(", Error:");
        sb.append(this.f2120b);
        sb.append(", Operation:");
        sb.append(this.f2122d);
        sb.append(", params:");
        ArrayMap<String, String> arrayMap = this.f2123e;
        sb.append(arrayMap == null ? null : arrayMap.toString());
        return sb.toString();
    }

    public String getOperation() {
        return this.f2122d;
    }

    public void setOperation(String str) {
        this.f2122d = str;
    }

    public String getTopic() {
        return this.f2121c;
    }

    public void setTopic(String str) {
        this.f2121c = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f2120b);
        parcel.writeString(this.f2121c);
        parcel.writeString(this.f2122d);
        ArrayMap<String, String> arrayMap = this.f2123e;
        if (arrayMap != null) {
            parcel.writeInt(arrayMap.size());
            for (Map.Entry<String, String> entry : this.f2123e.entrySet()) {
                parcel.writeString(entry.getKey());
                parcel.writeString(entry.getValue());
            }
        }
    }
}
