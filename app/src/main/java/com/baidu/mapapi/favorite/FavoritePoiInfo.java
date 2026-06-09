package com.baidu.mapapi.favorite;

import com.baidu.mapapi.model.LatLng;

/* JADX INFO: loaded from: classes.dex */
public class FavoritePoiInfo {

    /* JADX INFO: renamed from: a */
    String f203a;

    /* JADX INFO: renamed from: b */
    String f204b;

    /* JADX INFO: renamed from: c */
    LatLng f205c;

    /* JADX INFO: renamed from: d */
    String f206d;

    /* JADX INFO: renamed from: e */
    String f207e;

    /* JADX INFO: renamed from: f */
    String f208f;

    /* JADX INFO: renamed from: g */
    long f209g;

    public FavoritePoiInfo addr(String str) {
        this.f206d = str;
        return this;
    }

    public FavoritePoiInfo cityName(String str) {
        this.f207e = str;
        return this;
    }

    public String getAddr() {
        return this.f206d;
    }

    public String getCityName() {
        return this.f207e;
    }

    public String getID() {
        return this.f203a;
    }

    public String getPoiName() {
        return this.f204b;
    }

    public LatLng getPt() {
        return this.f205c;
    }

    public long getTimeStamp() {
        return this.f209g;
    }

    public String getUid() {
        return this.f208f;
    }

    public FavoritePoiInfo poiName(String str) {
        this.f204b = str;
        return this;
    }

    /* JADX INFO: renamed from: pt */
    public FavoritePoiInfo m229pt(LatLng latLng) {
        this.f205c = latLng;
        return this;
    }

    public FavoritePoiInfo uid(String str) {
        this.f208f = str;
        return this;
    }
}
