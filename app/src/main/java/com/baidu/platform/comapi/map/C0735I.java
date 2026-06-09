package com.baidu.platform.comapi.map;

import android.graphics.Point;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comjni.map.basemap.C0781a;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.baidu.platform.comapi.map.I */
/* JADX INFO: loaded from: classes.dex */
public class C0735I {

    /* JADX INFO: renamed from: a */
    private C0781a f880a;

    public C0735I(C0781a c0781a) {
        this.f880a = c0781a;
    }

    /* JADX INFO: renamed from: a */
    public Point m589a(GeoPoint geoPoint) {
        if (geoPoint == null) {
            return null;
        }
        Point point = new Point(0, 0);
        String strM843b = this.f880a.m843b((int) geoPoint.getLongitudeE6(), (int) geoPoint.getLatitudeE6());
        if (strM843b != null) {
            try {
                JSONObject jSONObject = new JSONObject(strM843b);
                point.x = jSONObject.getInt("scrx");
                point.y = jSONObject.getInt("scry");
                return point;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return point;
    }

    /* JADX INFO: renamed from: a */
    public GeoPoint m590a(int i, int i2) {
        String strM827a = this.f880a.m827a(i, i2);
        GeoPoint geoPoint = new GeoPoint(0.0d, 0.0d);
        if (strM827a == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(strM827a);
            geoPoint.setLongitudeE6(jSONObject.getInt("geox"));
            geoPoint.setLatitudeE6(jSONObject.getInt("geoy"));
            return geoPoint;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
