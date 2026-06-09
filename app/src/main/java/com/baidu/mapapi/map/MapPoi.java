package com.baidu.mapapi.map;

import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class MapPoi {

    /* JADX INFO: renamed from: d */
    private static final String f384d = MapPoi.class.getSimpleName();

    /* JADX INFO: renamed from: a */
    String f385a;

    /* JADX INFO: renamed from: b */
    LatLng f386b;

    /* JADX INFO: renamed from: c */
    String f387c;

    /* JADX INFO: renamed from: a */
    void m313a(JSONObject jSONObject) {
        this.f385a = jSONObject.optString("tx");
        this.f386b = CoordUtil.decodeNodeLocation(jSONObject.optString("geo"));
        this.f387c = jSONObject.optString("ud");
    }

    public String getName() {
        return this.f385a;
    }

    public LatLng getPosition() {
        return this.f386b;
    }

    public String getUid() {
        return this.f387c;
    }
}
