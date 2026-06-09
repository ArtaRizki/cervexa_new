package com.baidu.mapapi.utils.poi;

import com.baidu.mapapi.model.LatLng;

/* JADX INFO: loaded from: classes.dex */
public class PoiParaOption {

    /* JADX INFO: renamed from: a */
    String f756a;

    /* JADX INFO: renamed from: b */
    String f757b;

    /* JADX INFO: renamed from: c */
    LatLng f758c;

    /* JADX INFO: renamed from: d */
    int f759d;

    public PoiParaOption center(LatLng latLng) {
        this.f758c = latLng;
        return this;
    }

    public LatLng getCenter() {
        return this.f758c;
    }

    public String getKey() {
        return this.f757b;
    }

    public int getRadius() {
        return this.f759d;
    }

    public String getUid() {
        return this.f756a;
    }

    public PoiParaOption key(String str) {
        this.f757b = str;
        return this;
    }

    public PoiParaOption radius(int i) {
        this.f759d = i;
        return this;
    }

    public PoiParaOption uid(String str) {
        this.f756a = str;
        return this;
    }
}
