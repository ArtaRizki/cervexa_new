package com.baidu.mapapi.favorite;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.Point;
import com.baidu.platform.comapi.favrite.FavSyncPoi;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.baidu.mapapi.favorite.a */
/* JADX INFO: loaded from: classes.dex */
class C0669a {
    /* JADX INFO: renamed from: a */
    static FavoritePoiInfo m230a(FavSyncPoi favSyncPoi) {
        if (favSyncPoi == null || favSyncPoi.f803c == null || favSyncPoi.f802b.equals("")) {
            return null;
        }
        FavoritePoiInfo favoritePoiInfo = new FavoritePoiInfo();
        favoritePoiInfo.f203a = favSyncPoi.f801a;
        favoritePoiInfo.f204b = favSyncPoi.f802b;
        favoritePoiInfo.f205c = new LatLng(((double) favSyncPoi.f803c.f713y) / 1000000.0d, ((double) favSyncPoi.f803c.f712x) / 1000000.0d);
        favoritePoiInfo.f207e = favSyncPoi.f805e;
        favoritePoiInfo.f208f = favSyncPoi.f806f;
        favoritePoiInfo.f206d = favSyncPoi.f804d;
        favoritePoiInfo.f209g = Long.parseLong(favSyncPoi.f808h);
        return favoritePoiInfo;
    }

    /* JADX INFO: renamed from: a */
    static FavoritePoiInfo m231a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        FavoritePoiInfo favoritePoiInfo = new FavoritePoiInfo();
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("pt");
        if (jSONObjectOptJSONObject != null) {
            favoritePoiInfo.f205c = new LatLng(((double) jSONObjectOptJSONObject.optInt("y")) / 1000000.0d, ((double) jSONObjectOptJSONObject.optInt("x")) / 1000000.0d);
        }
        favoritePoiInfo.f204b = jSONObject.optString("uspoiname");
        favoritePoiInfo.f209g = Long.parseLong(jSONObject.optString("addtimesec"));
        favoritePoiInfo.f206d = jSONObject.optString("addr");
        favoritePoiInfo.f208f = jSONObject.optString("uspoiuid");
        favoritePoiInfo.f207e = jSONObject.optString("ncityid");
        favoritePoiInfo.f203a = jSONObject.optString("key");
        return favoritePoiInfo;
    }

    /* JADX INFO: renamed from: a */
    static FavSyncPoi m232a(FavoritePoiInfo favoritePoiInfo) {
        if (favoritePoiInfo == null || favoritePoiInfo.f205c == null || favoritePoiInfo.f204b == null || favoritePoiInfo.f204b.equals("")) {
            return null;
        }
        FavSyncPoi favSyncPoi = new FavSyncPoi();
        favSyncPoi.f802b = favoritePoiInfo.f204b;
        favSyncPoi.f803c = new Point((int) (favoritePoiInfo.f205c.longitude * 1000000.0d), (int) (favoritePoiInfo.f205c.latitude * 1000000.0d));
        favSyncPoi.f804d = favoritePoiInfo.f206d;
        favSyncPoi.f805e = favoritePoiInfo.f207e;
        favSyncPoi.f806f = favoritePoiInfo.f208f;
        favSyncPoi.f809i = false;
        return favSyncPoi;
    }
}
