package com.baidu.mapapi.favorite;

import android.util.Log;
import com.baidu.mapapi.BMapManager;
import com.baidu.platform.comapi.favrite.C0726a;
import com.baidu.platform.comapi.favrite.FavSyncPoi;
import com.baidu.platform.comapi.map.C0750i;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class FavoriteManager {

    /* JADX INFO: renamed from: a */
    private static FavoriteManager f201a;

    /* JADX INFO: renamed from: b */
    private static C0726a f202b;

    private FavoriteManager() {
    }

    public static FavoriteManager getInstance() {
        if (f201a == null) {
            f201a = new FavoriteManager();
        }
        return f201a;
    }

    public int add(FavoritePoiInfo favoritePoiInfo) {
        String str;
        if (f202b == null) {
            str = "you may have not call init method!";
        } else {
            if (favoritePoiInfo != null && favoritePoiInfo.f205c != null) {
                if (favoritePoiInfo.f204b == null || favoritePoiInfo.f204b.equals("")) {
                    Log.e("baidumapsdk", "poiName can not be null or empty!");
                    return -1;
                }
                FavSyncPoi favSyncPoiM232a = C0669a.m232a(favoritePoiInfo);
                int iM545a = f202b.m545a(favSyncPoiM232a.f802b, favSyncPoiM232a);
                if (iM545a == 1) {
                    favoritePoiInfo.f203a = favSyncPoiM232a.f801a;
                    favoritePoiInfo.f209g = Long.parseLong(favSyncPoiM232a.f808h);
                }
                return iM545a;
            }
            str = "object or pt can not be null!";
        }
        Log.e("baidumapsdk", str);
        return 0;
    }

    public boolean clearAllFavPois() {
        C0726a c0726a = f202b;
        if (c0726a != null) {
            return c0726a.m550c();
        }
        Log.e("baidumapsdk", "you may have not call init method!");
        return false;
    }

    public boolean deleteFavPoi(String str) {
        if (f202b == null) {
            Log.e("baidumapsdk", "you may have not call init method!");
            return false;
        }
        if (str == null || str.equals("")) {
            return false;
        }
        return f202b.m546a(str);
    }

    public void destroy() {
        C0726a c0726a = f202b;
        if (c0726a != null) {
            c0726a.m548b();
            f202b = null;
            BMapManager.destroy();
            C0750i.m709b();
        }
    }

    public List<FavoritePoiInfo> getAllFavPois() {
        JSONArray jSONArrayOptJSONArray;
        C0726a c0726a = f202b;
        if (c0726a == null) {
            Log.e("baidumapsdk", "you may have not call init method!");
            return null;
        }
        String strM554f = c0726a.m554f();
        if (strM554f != null && !strM554f.equals("")) {
            try {
                JSONObject jSONObject = new JSONObject(strM554f);
                if (jSONObject.optInt("favpoinum") != 0 && (jSONArrayOptJSONArray = jSONObject.optJSONArray("favcontents")) != null && jSONArrayOptJSONArray.length() > 0) {
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                        JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i);
                        if (jSONObject2 != null) {
                            arrayList.add(C0669a.m231a(jSONObject2));
                        }
                    }
                    return arrayList;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public FavoritePoiInfo getFavPoi(String str) {
        FavSyncPoi favSyncPoiM547b;
        if (f202b == null) {
            Log.e("baidumapsdk", "you may have not call init method!");
            return null;
        }
        if (str == null || str.equals("") || (favSyncPoiM547b = f202b.m547b(str)) == null) {
            return null;
        }
        return C0669a.m230a(favSyncPoiM547b);
    }

    public void init() {
        if (f202b == null) {
            C0750i.m706a();
            BMapManager.init();
            f202b = C0726a.m540a();
        }
    }

    public boolean updateFavPoi(String str, FavoritePoiInfo favoritePoiInfo) {
        String str2;
        if (f202b == null) {
            str2 = "you may have not call init method!";
        } else {
            if (str == null || str.equals("") || favoritePoiInfo == null) {
                return false;
            }
            if (favoritePoiInfo == null || favoritePoiInfo.f205c == null) {
                str2 = "object or pt can not be null!";
            } else {
                if (favoritePoiInfo.f204b != null && !favoritePoiInfo.f204b.equals("")) {
                    favoritePoiInfo.f203a = str;
                    return f202b.m549b(str, C0669a.m232a(favoritePoiInfo));
                }
                str2 = "poiName can not be null or empty!";
            }
        }
        Log.e("baidumapsdk", str2);
        return false;
    }
}
