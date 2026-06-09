package com.baidu.mapapi.map;

import android.os.Bundle;
import android.util.Log;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.model.inner.GeoPoint;

/* JADX INFO: loaded from: classes.dex */
public final class TileOverlayOptions {

    /* JADX INFO: renamed from: c */
    private static Bundle f608c;

    /* JADX INFO: renamed from: j */
    private static final String f609j = TileOverlayOptions.class.getSimpleName();

    /* JADX INFO: renamed from: b */
    private TileProvider f611b;
    public int datasource;
    public String urlString;

    /* JADX INFO: renamed from: a */
    private int f610a = 20971520;

    /* JADX INFO: renamed from: d */
    private int f612d = 20;

    /* JADX INFO: renamed from: e */
    private int f613e = 3;

    /* JADX INFO: renamed from: f */
    private int f614f = 15786414;

    /* JADX INFO: renamed from: g */
    private int f615g = -20037726;

    /* JADX INFO: renamed from: h */
    private int f616h = -15786414;

    /* JADX INFO: renamed from: i */
    private int f617i = 20037726;

    public TileOverlayOptions() {
        Bundle bundle = new Bundle();
        f608c = bundle;
        bundle.putInt("rectr", this.f614f);
        f608c.putInt("rectb", this.f615g);
        f608c.putInt("rectl", this.f616h);
        f608c.putInt("rectt", this.f617i);
    }

    /* JADX INFO: renamed from: a */
    private TileOverlayOptions m380a(int i, int i2) {
        this.f612d = i;
        this.f613e = i2;
        return this;
    }

    /* JADX INFO: renamed from: a */
    Bundle m381a() {
        f608c.putString("url", this.urlString);
        f608c.putInt("datasource", this.datasource);
        f608c.putInt("maxDisplay", this.f612d);
        f608c.putInt("minDisplay", this.f613e);
        f608c.putInt("sdktiletmpmax", this.f610a);
        return f608c;
    }

    /* JADX INFO: renamed from: a */
    TileOverlay m382a(BaiduMap baiduMap) {
        return new TileOverlay(baiduMap, this.f611b);
    }

    public TileOverlayOptions setMaxTileTmp(int i) {
        this.f610a = i;
        return this;
    }

    public TileOverlayOptions setPositionFromBounds(LatLngBounds latLngBounds) {
        if (latLngBounds == null) {
            throw new IllegalArgumentException("bound can not be null");
        }
        GeoPoint geoPointLl2mc = CoordUtil.ll2mc(latLngBounds.northeast);
        GeoPoint geoPointLl2mc2 = CoordUtil.ll2mc(latLngBounds.southwest);
        double latitudeE6 = geoPointLl2mc.getLatitudeE6();
        double longitudeE6 = geoPointLl2mc2.getLongitudeE6();
        double latitudeE62 = geoPointLl2mc2.getLatitudeE6();
        double longitudeE62 = geoPointLl2mc.getLongitudeE6();
        if (latitudeE6 <= latitudeE62 || longitudeE62 <= longitudeE6) {
            Log.e(f609j, "bounds is illegal, use default bounds");
        } else {
            f608c.putInt("rectr", (int) longitudeE62);
            f608c.putInt("rectb", (int) latitudeE62);
            f608c.putInt("rectl", (int) longitudeE6);
            f608c.putInt("rectt", (int) latitudeE6);
        }
        return this;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.baidu.mapapi.map.TileOverlayOptions tileProvider(com.baidu.mapapi.map.TileProvider r4) {
        /*
            r3 = this;
            r0 = 0
            if (r4 != 0) goto L4
            return r0
        L4:
            boolean r1 = r4 instanceof com.baidu.mapapi.map.UrlTileProvider
            if (r1 == 0) goto L3f
            r1 = 1
            r3.datasource = r1
            r1 = r4
            com.baidu.mapapi.map.UrlTileProvider r1 = (com.baidu.mapapi.map.UrlTileProvider) r1
            java.lang.String r1 = r1.getTileUrl()
            if (r1 == 0) goto L37
            java.lang.String r2 = ""
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L37
            java.lang.String r2 = "{x}"
            boolean r2 = r1.contains(r2)
            if (r2 == 0) goto L37
            java.lang.String r2 = "{y}"
            boolean r2 = r1.contains(r2)
            if (r2 == 0) goto L37
            java.lang.String r2 = "{z}"
            boolean r2 = r1.contains(r2)
            if (r2 == 0) goto L37
            r3.urlString = r1
            goto L46
        L37:
            java.lang.String r4 = com.baidu.mapapi.map.TileOverlayOptions.f609j
            java.lang.String r1 = "tile url template is illegal, must contains {x}、{y}、{z}"
        L3b:
            android.util.Log.e(r4, r1)
            return r0
        L3f:
            boolean r1 = r4 instanceof com.baidu.mapapi.map.FileTileProvider
            if (r1 == 0) goto L64
            r0 = 0
            r3.datasource = r0
        L46:
            r3.f611b = r4
            int r0 = r4.getMaxDisLevel()
            int r4 = r4.getMinDisLevel()
            r1 = 21
            if (r0 > r1) goto L5c
            r1 = 3
            if (r4 >= r1) goto L58
            goto L5c
        L58:
            r3.m380a(r0, r4)
            goto L63
        L5c:
            java.lang.String r4 = com.baidu.mapapi.map.TileOverlayOptions.f609j
            java.lang.String r0 = "display level is illegal"
            android.util.Log.e(r4, r0)
        L63:
            return r3
        L64:
            java.lang.String r4 = com.baidu.mapapi.map.TileOverlayOptions.f609j
            java.lang.String r1 = "tileProvider must be UrlTileProvider or FileTileProvider"
            goto L3b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapapi.map.TileOverlayOptions.tileProvider(com.baidu.mapapi.map.TileProvider):com.baidu.mapapi.map.TileOverlayOptions");
    }
}
