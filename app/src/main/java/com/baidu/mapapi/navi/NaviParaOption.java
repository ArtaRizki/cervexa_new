package com.baidu.mapapi.navi;

import com.baidu.mapapi.model.LatLng;

/* JADX INFO: loaded from: classes.dex */
public class NaviParaOption {

    /* JADX INFO: renamed from: a */
    LatLng f719a;

    /* JADX INFO: renamed from: b */
    String f720b;

    /* JADX INFO: renamed from: c */
    LatLng f721c;

    /* JADX INFO: renamed from: d */
    String f722d;

    public NaviParaOption endName(String str) {
        this.f722d = str;
        return this;
    }

    public NaviParaOption endPoint(LatLng latLng) {
        this.f721c = latLng;
        return this;
    }

    public String getEndName() {
        return this.f722d;
    }

    public LatLng getEndPoint() {
        return this.f721c;
    }

    public String getStartName() {
        return this.f720b;
    }

    public LatLng getStartPoint() {
        return this.f719a;
    }

    public NaviParaOption startName(String str) {
        this.f720b = str;
        return this;
    }

    public NaviParaOption startPoint(LatLng latLng) {
        this.f719a = latLng;
        return this;
    }
}
