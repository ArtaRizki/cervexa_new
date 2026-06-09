package com.baidu.mapapi.utils.route;

import com.baidu.mapapi.model.LatLng;

/* JADX INFO: loaded from: classes.dex */
public class RouteParaOption {

    /* JADX INFO: renamed from: a */
    LatLng f762a;

    /* JADX INFO: renamed from: b */
    LatLng f763b;

    /* JADX INFO: renamed from: c */
    String f764c;

    /* JADX INFO: renamed from: d */
    String f765d;

    /* JADX INFO: renamed from: e */
    String f766e;

    /* JADX INFO: renamed from: f */
    EBusStrategyType f767f = EBusStrategyType.bus_recommend_way;

    public enum EBusStrategyType {
        bus_time_first,
        bus_transfer_little,
        bus_walk_little,
        bus_no_subway,
        bus_recommend_way
    }

    public RouteParaOption busStrategyType(EBusStrategyType eBusStrategyType) {
        this.f767f = eBusStrategyType;
        return this;
    }

    public RouteParaOption cityName(String str) {
        this.f766e = str;
        return this;
    }

    public RouteParaOption endName(String str) {
        this.f765d = str;
        return this;
    }

    public RouteParaOption endPoint(LatLng latLng) {
        this.f763b = latLng;
        return this;
    }

    public EBusStrategyType getBusStrategyType() {
        return this.f767f;
    }

    public String getCityName() {
        return this.f766e;
    }

    public String getEndName() {
        return this.f765d;
    }

    public LatLng getEndPoint() {
        return this.f763b;
    }

    public String getStartName() {
        return this.f764c;
    }

    public LatLng getStartPoint() {
        return this.f762a;
    }

    public RouteParaOption startName(String str) {
        this.f764c = str;
        return this;
    }

    public RouteParaOption startPoint(LatLng latLng) {
        this.f762a = latLng;
        return this;
    }
}
