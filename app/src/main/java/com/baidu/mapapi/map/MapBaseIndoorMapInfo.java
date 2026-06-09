package com.baidu.mapapi.map;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class MapBaseIndoorMapInfo {

    /* JADX INFO: renamed from: d */
    private static final String f376d = MapBaseIndoorMapInfo.class.getSimpleName();

    /* JADX INFO: renamed from: a */
    String f377a;

    /* JADX INFO: renamed from: b */
    String f378b;

    /* JADX INFO: renamed from: c */
    ArrayList<String> f379c;

    public enum SwitchFloorError {
        SWITCH_OK,
        FLOOR_INFO_ERROR,
        FLOOR_OVERLFLOW,
        FOCUSED_ID_ERROR,
        SWITCH_ERROR
    }

    public MapBaseIndoorMapInfo() {
    }

    public MapBaseIndoorMapInfo(MapBaseIndoorMapInfo mapBaseIndoorMapInfo) {
        this.f377a = mapBaseIndoorMapInfo.f377a;
        this.f378b = mapBaseIndoorMapInfo.f378b;
        this.f379c = mapBaseIndoorMapInfo.f379c;
    }

    public MapBaseIndoorMapInfo(String str, String str2, ArrayList<String> arrayList) {
        this.f377a = str;
        this.f378b = str2;
        this.f379c = arrayList;
    }

    public String getCurFloor() {
        return this.f378b;
    }

    public ArrayList<String> getFloors() {
        return this.f379c;
    }

    public String getID() {
        return this.f377a;
    }
}
