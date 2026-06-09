package com.baidu.platform.comjni.map.basemap;

import android.os.Bundle;
import android.util.Log;
import com.baidu.mapapi.map.MapBaseIndoorMapInfo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.baidu.platform.comjni.map.basemap.a */
/* JADX INFO: loaded from: classes.dex */
public class C0781a {

    /* JADX INFO: renamed from: a */
    private static final String f1117a = C0781a.class.getSimpleName();

    /* JADX INFO: renamed from: d */
    private static List<JNIBaseMap> f1118d = new ArrayList();

    /* JADX INFO: renamed from: b */
    private long f1119b = 0;

    /* JADX INFO: renamed from: c */
    private JNIBaseMap f1120c;

    public C0781a() {
        this.f1120c = null;
        this.f1120c = new JNIBaseMap();
    }

    /* JADX INFO: renamed from: a */
    public static int m822a(long j, int i, int i2, int i3) {
        return JNIBaseMap.MapProc(j, i, i2, i3);
    }

    /* JADX INFO: renamed from: b */
    public static void m823b(long j, boolean z) {
        JNIBaseMap.SetMapCustomEnable(j, z);
    }

    /* JADX INFO: renamed from: d */
    public static List<JNIBaseMap> m824d() {
        return f1118d;
    }

    /* JADX INFO: renamed from: a */
    public int m825a(int i) {
        return this.f1120c.SetMapControlMode(this.f1119b, i);
    }

    /* JADX INFO: renamed from: a */
    public long m826a(int i, int i2, String str) {
        return this.f1120c.AddLayer(this.f1119b, i, i2, str);
    }

    /* JADX INFO: renamed from: a */
    public String m827a(int i, int i2) {
        return this.f1120c.ScrPtToGeoPoint(this.f1119b, i, i2);
    }

    /* JADX INFO: renamed from: a */
    public String m828a(int i, int i2, int i3, int i4) {
        return this.f1120c.GetNearlyObjID(this.f1119b, i, i2, i3, i4);
    }

    /* JADX INFO: renamed from: a */
    public String m829a(String str) {
        return this.f1120c.OnSchcityGet(this.f1119b, str);
    }

    /* JADX INFO: renamed from: a */
    public void m830a(long j, boolean z) {
        this.f1120c.ShowLayers(this.f1119b, j, z);
    }

    /* JADX INFO: renamed from: a */
    public void m831a(Bundle bundle) {
        this.f1120c.SetMapStatus(this.f1119b, bundle);
    }

    /* JADX INFO: renamed from: a */
    public void m832a(String str, Bundle bundle) {
        this.f1120c.SaveScreenToLocal(this.f1119b, str, bundle);
    }

    /* JADX INFO: renamed from: a */
    public void m833a(boolean z) {
        this.f1120c.ShowSatelliteMap(this.f1119b, z);
    }

    /* JADX INFO: renamed from: a */
    public void m834a(Bundle[] bundleArr) {
        this.f1120c.addOverlayItems(this.f1119b, bundleArr, bundleArr.length);
    }

    /* JADX INFO: renamed from: a */
    public boolean m835a() {
        this.f1119b = f1118d.size() == 0 ? this.f1120c.Create() : this.f1120c.CreateDuplicate(f1118d.get(0).f1116a);
        this.f1120c.f1116a = this.f1119b;
        f1118d.add(this.f1120c);
        this.f1120c.SetCallback(this.f1119b, null);
        return true;
    }

    /* JADX INFO: renamed from: a */
    public boolean m836a(int i, boolean z) {
        return this.f1120c.OnRecordReload(this.f1119b, i, z);
    }

    /* JADX INFO: renamed from: a */
    public boolean m837a(int i, boolean z, int i2) {
        return this.f1120c.OnRecordStart(this.f1119b, i, z, i2);
    }

    /* JADX INFO: renamed from: a */
    public boolean m838a(long j) {
        return this.f1120c.LayersIsShow(this.f1119b, j);
    }

    /* JADX INFO: renamed from: a */
    public boolean m839a(String str, String str2) {
        return this.f1120c.SwitchBaseIndoorMapFloor(this.f1119b, str, str2);
    }

    /* JADX INFO: renamed from: a */
    public boolean m840a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        return this.f1120c.Init(this.f1119b, str, str2, str3, str4, str5, str6, str7, str8, i, i2, i3, i4, i5, i6, i7);
    }

    /* JADX INFO: renamed from: a */
    public boolean m841a(boolean z, boolean z2) {
        return this.f1120c.OnRecordImport(this.f1119b, z, z2);
    }

    /* JADX INFO: renamed from: a */
    public int[] m842a(int[] iArr, int i, int i2) {
        return this.f1120c.GetScreenBuf(this.f1119b, iArr, i, i2);
    }

    /* JADX INFO: renamed from: b */
    public String m843b(int i, int i2) {
        return this.f1120c.GeoPtToScrPoint(this.f1119b, i, i2);
    }

    /* JADX INFO: renamed from: b */
    public void m844b(long j) {
        this.f1120c.UpdateLayers(this.f1119b, j);
    }

    /* JADX INFO: renamed from: b */
    public void m845b(Bundle bundle) {
        this.f1120c.setMapStatusLimits(this.f1119b, bundle);
    }

    /* JADX INFO: renamed from: b */
    public void m846b(boolean z) {
        this.f1120c.ShowHotMap(this.f1119b, z);
    }

    /* JADX INFO: renamed from: b */
    public boolean m847b() {
        this.f1120c.Release(this.f1119b);
        f1118d.remove(this.f1120c);
        return true;
    }

    /* JADX INFO: renamed from: b */
    public boolean m848b(int i) {
        return this.f1120c.OnRecordAdd(this.f1119b, i);
    }

    /* JADX INFO: renamed from: b */
    public boolean m849b(int i, boolean z) {
        return this.f1120c.OnRecordRemove(this.f1119b, i, z);
    }

    /* JADX INFO: renamed from: b */
    public boolean m850b(int i, boolean z, int i2) {
        return this.f1120c.OnRecordSuspend(this.f1119b, i, z, i2);
    }

    /* JADX INFO: renamed from: c */
    public float m851c(Bundle bundle) {
        return this.f1120c.GetZoomToBound(this.f1119b, bundle);
    }

    /* JADX INFO: renamed from: c */
    public long m852c() {
        return this.f1119b;
    }

    /* JADX INFO: renamed from: c */
    public String m853c(int i) {
        return this.f1120c.OnRecordGetAt(this.f1119b, i);
    }

    /* JADX INFO: renamed from: c */
    public void m854c(boolean z) {
        this.f1120c.ShowTrafficMap(this.f1119b, z);
    }

    /* JADX INFO: renamed from: c */
    public boolean m855c(long j) {
        return this.f1120c.cleanSDKTileDataCache(this.f1119b, j);
    }

    /* JADX INFO: renamed from: d */
    public void m856d(long j) {
        this.f1120c.ClearLayer(this.f1119b, j);
    }

    /* JADX INFO: renamed from: d */
    public void m857d(boolean z) {
        this.f1120c.enableDrawHouseHeight(this.f1119b, z);
    }

    /* JADX INFO: renamed from: d */
    public boolean m858d(Bundle bundle) {
        return this.f1120c.updateSDKTile(this.f1119b, bundle);
    }

    /* JADX INFO: renamed from: e */
    public String m859e(long j) {
        return this.f1120c.getCompassPosition(this.f1119b, j);
    }

    /* JADX INFO: renamed from: e */
    public void m860e() {
        this.f1120c.OnPause(this.f1119b);
    }

    /* JADX INFO: renamed from: e */
    public void m861e(boolean z) {
        this.f1120c.ShowBaseIndoorMap(this.f1119b, z);
    }

    /* JADX INFO: renamed from: e */
    public boolean m862e(Bundle bundle) {
        return this.f1120c.addtileOverlay(this.f1119b, bundle);
    }

    /* JADX INFO: renamed from: f */
    public void m863f() {
        this.f1120c.OnResume(this.f1119b);
    }

    /* JADX INFO: renamed from: f */
    public void m864f(Bundle bundle) {
        this.f1120c.addOneOverlayItem(this.f1119b, bundle);
    }

    /* JADX INFO: renamed from: g */
    public void m865g() {
        this.f1120c.OnBackground(this.f1119b);
    }

    /* JADX INFO: renamed from: g */
    public void m866g(Bundle bundle) {
        this.f1120c.updateOneOverlayItem(this.f1119b, bundle);
    }

    /* JADX INFO: renamed from: h */
    public void m867h() {
        this.f1120c.OnForeground(this.f1119b);
    }

    /* JADX INFO: renamed from: h */
    public void m868h(Bundle bundle) {
        this.f1120c.removeOneOverlayItem(this.f1119b, bundle);
    }

    /* JADX INFO: renamed from: i */
    public void m869i() {
        this.f1120c.ResetImageRes(this.f1119b);
    }

    /* JADX INFO: renamed from: j */
    public Bundle m870j() {
        return this.f1120c.GetMapStatus(this.f1119b);
    }

    /* JADX INFO: renamed from: k */
    public Bundle m871k() {
        Bundle mapStatusLimits = this.f1120c.getMapStatusLimits(this.f1119b);
        Log.d("test", "GetMapStatusLimits, maddr: " + this.f1119b + "bundle: " + mapStatusLimits);
        return mapStatusLimits;
    }

    /* JADX INFO: renamed from: l */
    public Bundle m872l() {
        return this.f1120c.getDrawingMapStatus(this.f1119b);
    }

    /* JADX INFO: renamed from: m */
    public boolean m873m() {
        return this.f1120c.GetBaiduHotMapCityInfo(this.f1119b);
    }

    /* JADX INFO: renamed from: n */
    public String m874n() {
        return this.f1120c.OnRecordGetAll(this.f1119b);
    }

    /* JADX INFO: renamed from: o */
    public String m875o() {
        return this.f1120c.OnHotcityGet(this.f1119b);
    }

    /* JADX INFO: renamed from: p */
    public void m876p() {
        this.f1120c.PostStatInfo(this.f1119b);
    }

    /* JADX INFO: renamed from: q */
    public boolean m877q() {
        return this.f1120c.isDrawHouseHeightEnable(this.f1119b);
    }

    /* JADX INFO: renamed from: r */
    public void m878r() {
        this.f1120c.clearHeatMapLayerCache(this.f1119b);
    }

    /* JADX INFO: renamed from: s */
    public MapBaseIndoorMapInfo m879s() {
        String str = this.f1120c.getfocusedBaseIndoorMapInfo(this.f1119b);
        if (str == null) {
            return null;
        }
        String strOptString = "";
        String str2 = new String();
        ArrayList arrayList = new ArrayList(1);
        try {
            JSONObject jSONObject = new JSONObject(str);
            strOptString = jSONObject.optString("focusindoorid");
            str2 = jSONObject.optString("curfloor");
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("floorlist");
            if (jSONArrayOptJSONArray == null) {
                return null;
            }
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(jSONArrayOptJSONArray.get(i).toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new MapBaseIndoorMapInfo(strOptString, str2, arrayList);
    }

    /* JADX INFO: renamed from: t */
    public boolean m880t() {
        return this.f1120c.IsBaseIndoorMapMode(this.f1119b);
    }
}
