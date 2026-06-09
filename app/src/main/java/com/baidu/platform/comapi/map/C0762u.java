package com.baidu.platform.comapi.map;

import android.os.Handler;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.UIMsg;
import com.baidu.mapapi.common.EnvironmentUtilities;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comjni.map.basemap.C0781a;
import com.generalplus.GoPlusDrone.Fragment.BaseFragment;
import com.jieli.lib.p015dv.control.utils.TopicKey;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.baidu.platform.comapi.map.u */
/* JADX INFO: loaded from: classes.dex */
public class C0762u {

    /* JADX INFO: renamed from: a */
    private static final String f1016a = C0762u.class.getSimpleName();

    /* JADX INFO: renamed from: c */
    private static C0762u f1017c;

    /* JADX INFO: renamed from: b */
    private C0781a f1018b;

    /* JADX INFO: renamed from: d */
    private C0767z f1019d;

    /* JADX INFO: renamed from: e */
    private Handler f1020e;

    private C0762u() {
    }

    /* JADX INFO: renamed from: a */
    public static C0762u m731a() throws Throwable {
        if (f1017c == null) {
            C0762u c0762u = new C0762u();
            f1017c = c0762u;
            c0762u.m734g();
        }
        return f1017c;
    }

    /* JADX INFO: renamed from: g */
    private void m734g() throws Throwable {
        m735h();
        this.f1019d = new C0767z();
        HandlerC0763v handlerC0763v = new HandlerC0763v(this);
        this.f1020e = handlerC0763v;
        MessageCenter.registMessage(UIMsg.m_AppUI.V_WM_VDATAENGINE, handlerC0763v);
    }

    /* JADX INFO: renamed from: h */
    private void m735h() throws Throwable {
        EnvironmentUtilities.initAppDirectory(BMapManager.getContext());
        C0781a c0781a = new C0781a();
        this.f1018b = c0781a;
        c0781a.m835a();
        String moduleFileName = SysOSUtil.getModuleFileName();
        String appSDCardPath = EnvironmentUtilities.getAppSDCardPath();
        String appCachePath = EnvironmentUtilities.getAppCachePath();
        String appSecondCachePath = EnvironmentUtilities.getAppSecondCachePath();
        int mapTmpStgMax = EnvironmentUtilities.getMapTmpStgMax();
        int domTmpStgMax = EnvironmentUtilities.getDomTmpStgMax();
        int itsTmpStgMax = EnvironmentUtilities.getItsTmpStgMax();
        String str = SysOSUtil.getDensityDpi() >= 180 ? "/h/" : "/l/";
        String str2 = moduleFileName + "/cfg";
        String str3 = appSDCardPath + "/vmp";
        String str4 = str3 + str;
        String str5 = str3 + str;
        String str6 = appCachePath + "/tmp/";
        this.f1018b.m840a(str2 + str, str4, str6, appSecondCachePath + "/tmp/", str5, str2 + "/a/", null, str2 + "/idrres/", SysOSUtil.getScreenSizeX(), SysOSUtil.getScreenSizeY(), SysOSUtil.getDensityDpi(), mapTmpStgMax, domTmpStgMax, itsTmpStgMax, 0);
        this.f1018b.m863f();
    }

    /* JADX INFO: renamed from: a */
    public ArrayList<C0761t> m736a(String str) {
        C0781a c0781a;
        JSONArray jSONArrayOptJSONArray;
        if (!str.equals("") && (c0781a = this.f1018b) != null) {
            String strM829a = c0781a.m829a(str);
            if (strM829a == null || strM829a.equals("")) {
                return null;
            }
            ArrayList<C0761t> arrayList = new ArrayList<>();
            try {
                JSONObject jSONObject = new JSONObject(strM829a);
                if (jSONObject.length() == 0 || (jSONArrayOptJSONArray = jSONObject.optJSONArray("dataset")) == null) {
                    return null;
                }
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    C0761t c0761t = new C0761t();
                    JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i);
                    c0761t.f1011a = jSONObject2.optInt("id");
                    c0761t.f1012b = jSONObject2.optString(BaseFragment.DATA_NAME);
                    c0761t.f1013c = jSONObject2.optInt("mapsize");
                    c0761t.f1014d = jSONObject2.optInt("cty");
                    if (jSONObject2.has("child")) {
                        JSONArray jSONArrayOptJSONArray2 = jSONObject2.optJSONArray("child");
                        ArrayList<C0761t> arrayList2 = new ArrayList<>();
                        for (int i2 = 0; i2 < jSONArrayOptJSONArray2.length(); i2++) {
                            C0761t c0761t2 = new C0761t();
                            JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray2.optJSONObject(i2);
                            c0761t2.f1011a = jSONObjectOptJSONObject.optInt("id");
                            c0761t2.f1012b = jSONObjectOptJSONObject.optString(BaseFragment.DATA_NAME);
                            c0761t2.f1013c = jSONObjectOptJSONObject.optInt("mapsize");
                            c0761t2.f1014d = jSONObjectOptJSONObject.optInt("cty");
                            arrayList2.add(c0761t2);
                        }
                        c0761t.m730a(arrayList2);
                    }
                    arrayList.add(c0761t);
                }
                return arrayList;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: a */
    public void m737a(InterfaceC0766y interfaceC0766y) {
        C0767z c0767z = this.f1019d;
        if (c0767z != null) {
            c0767z.m754a(interfaceC0766y);
        }
    }

    /* JADX INFO: renamed from: a */
    public boolean m738a(int i) {
        C0781a c0781a = this.f1018b;
        if (c0781a == null || i < 0) {
            return false;
        }
        return c0781a.m848b(i);
    }

    /* JADX INFO: renamed from: a */
    public boolean m739a(boolean z, boolean z2) {
        C0781a c0781a = this.f1018b;
        if (c0781a == null) {
            return false;
        }
        return c0781a.m841a(z, z2);
    }

    /* JADX INFO: renamed from: b */
    public void m740b() {
        MessageCenter.unregistMessage(UIMsg.m_AppUI.V_WM_VDATAENGINE, this.f1020e);
        this.f1018b.m847b();
        f1017c = null;
    }

    /* JADX INFO: renamed from: b */
    public void m741b(InterfaceC0766y interfaceC0766y) {
        C0767z c0767z = this.f1019d;
        if (c0767z != null) {
            c0767z.m755b(interfaceC0766y);
        }
    }

    /* JADX INFO: renamed from: b */
    public boolean m742b(int i) {
        C0781a c0781a = this.f1018b;
        if (c0781a == null || i < 0) {
            return false;
        }
        return c0781a.m837a(i, false, 0);
    }

    /* JADX INFO: renamed from: c */
    public ArrayList<C0761t> m743c() {
        C0781a c0781a = this.f1018b;
        if (c0781a == null) {
            return null;
        }
        String strM875o = c0781a.m875o();
        ArrayList<C0761t> arrayList = new ArrayList<>();
        try {
            JSONArray jSONArrayOptJSONArray = new JSONObject(strM875o).optJSONArray("dataset");
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                C0761t c0761t = new C0761t();
                JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                c0761t.f1011a = jSONObjectOptJSONObject.optInt("id");
                c0761t.f1012b = jSONObjectOptJSONObject.optString(BaseFragment.DATA_NAME);
                c0761t.f1013c = jSONObjectOptJSONObject.optInt("mapsize");
                c0761t.f1014d = jSONObjectOptJSONObject.optInt("cty");
                if (jSONObjectOptJSONObject.has("child")) {
                    JSONArray jSONArrayOptJSONArray2 = jSONObjectOptJSONObject.optJSONArray("child");
                    ArrayList<C0761t> arrayList2 = new ArrayList<>();
                    for (int i2 = 0; i2 < jSONArrayOptJSONArray2.length(); i2++) {
                        C0761t c0761t2 = new C0761t();
                        JSONObject jSONObjectOptJSONObject2 = jSONArrayOptJSONArray2.optJSONObject(i2);
                        c0761t2.f1011a = jSONObjectOptJSONObject2.optInt("id");
                        c0761t2.f1012b = jSONObjectOptJSONObject2.optString(BaseFragment.DATA_NAME);
                        c0761t2.f1013c = jSONObjectOptJSONObject2.optInt("mapsize");
                        c0761t2.f1014d = jSONObjectOptJSONObject2.optInt("cty");
                        arrayList2.add(c0761t2);
                    }
                    c0761t.m730a(arrayList2);
                }
                arrayList.add(c0761t);
            }
            return arrayList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: renamed from: c */
    public boolean m744c(int i) {
        C0781a c0781a = this.f1018b;
        if (c0781a == null || i < 0) {
            return false;
        }
        return c0781a.m850b(i, false, 0);
    }

    /* JADX INFO: renamed from: d */
    public ArrayList<C0761t> m745d() {
        C0781a c0781a = this.f1018b;
        ArrayList<C0761t> arrayList = null;
        if (c0781a == null) {
            return null;
        }
        String strM829a = c0781a.m829a("");
        ArrayList<C0761t> arrayList2 = new ArrayList<>();
        try {
            JSONArray jSONArrayOptJSONArray = new JSONObject(strM829a).optJSONArray("dataset");
            int i = 0;
            while (i < jSONArrayOptJSONArray.length()) {
                C0761t c0761t = new C0761t();
                JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                c0761t.f1011a = jSONObjectOptJSONObject.optInt("id");
                c0761t.f1012b = jSONObjectOptJSONObject.optString(BaseFragment.DATA_NAME);
                c0761t.f1013c = jSONObjectOptJSONObject.optInt("mapsize");
                c0761t.f1014d = jSONObjectOptJSONObject.optInt("cty");
                if (jSONObjectOptJSONObject.has("child")) {
                    JSONArray jSONArrayOptJSONArray2 = jSONObjectOptJSONObject.optJSONArray("child");
                    ArrayList<C0761t> arrayList3 = new ArrayList<>();
                    for (int i2 = 0; i2 < jSONArrayOptJSONArray2.length(); i2++) {
                        C0761t c0761t2 = new C0761t();
                        JSONObject jSONObjectOptJSONObject2 = jSONArrayOptJSONArray2.optJSONObject(i2);
                        try {
                            c0761t2.f1011a = jSONObjectOptJSONObject2.optInt("id");
                            c0761t2.f1012b = jSONObjectOptJSONObject2.optString(BaseFragment.DATA_NAME);
                            c0761t2.f1013c = jSONObjectOptJSONObject2.optInt("mapsize");
                            c0761t2.f1014d = jSONObjectOptJSONObject2.optInt("cty");
                            arrayList3.add(c0761t2);
                        } catch (JSONException unused) {
                            return null;
                        } catch (Exception unused2) {
                            return null;
                        }
                    }
                    c0761t.m730a(arrayList3);
                }
                arrayList2.add(c0761t);
                i++;
                arrayList = null;
            }
            return arrayList2;
        } catch (JSONException unused3) {
            return arrayList;
        } catch (Exception unused4) {
            return arrayList;
        }
    }

    /* JADX INFO: renamed from: d */
    public boolean m746d(int i) {
        C0781a c0781a = this.f1018b;
        if (c0781a == null) {
            return false;
        }
        return c0781a.m850b(0, true, i);
    }

    /* JADX INFO: renamed from: e */
    public ArrayList<C0765x> m747e() {
        String strM874n;
        C0781a c0781a = this.f1018b;
        if (c0781a != null && (strM874n = c0781a.m874n()) != null && !strM874n.equals("")) {
            ArrayList<C0765x> arrayList = new ArrayList<>();
            try {
                JSONObject jSONObject = new JSONObject(strM874n);
                if (jSONObject.length() == 0) {
                    return null;
                }
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("dataset");
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    C0765x c0765x = new C0765x();
                    C0764w c0764w = new C0764w();
                    JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                    c0764w.f1022a = jSONObjectOptJSONObject.optInt("id");
                    c0764w.f1023b = jSONObjectOptJSONObject.optString(BaseFragment.DATA_NAME);
                    c0764w.f1024c = jSONObjectOptJSONObject.optString("pinyin");
                    c0764w.f1029h = jSONObjectOptJSONObject.optInt("mapoldsize");
                    c0764w.f1030i = jSONObjectOptJSONObject.optInt("ratio");
                    c0764w.f1033l = jSONObjectOptJSONObject.optInt("status");
                    c0764w.f1028g = new GeoPoint(jSONObjectOptJSONObject.optInt("y"), jSONObjectOptJSONObject.optInt("x"));
                    if (jSONObjectOptJSONObject.optInt("up") == 1) {
                        c0764w.f1031j = true;
                    } else {
                        c0764w.f1031j = false;
                    }
                    c0764w.f1026e = jSONObjectOptJSONObject.optInt("lev");
                    if (c0764w.f1031j) {
                        c0764w.f1032k = jSONObjectOptJSONObject.optInt("mapsize");
                    } else {
                        c0764w.f1032k = 0;
                    }
                    c0765x.m752a(c0764w);
                    arrayList.add(c0765x);
                }
                return arrayList;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: e */
    public boolean m748e(int i) {
        C0781a c0781a = this.f1018b;
        if (c0781a == null || i < 0) {
            return false;
        }
        return c0781a.m849b(i, false);
    }

    /* JADX INFO: renamed from: f */
    public boolean m749f(int i) {
        C0781a c0781a = this.f1018b;
        if (c0781a == null || i < 0) {
            return false;
        }
        return c0781a.m836a(i, false);
    }

    /* JADX INFO: renamed from: g */
    public C0765x m750g(int i) {
        String strM853c;
        C0781a c0781a = this.f1018b;
        if (c0781a != null && i >= 0 && (strM853c = c0781a.m853c(i)) != null && !strM853c.equals("")) {
            C0765x c0765x = new C0765x();
            C0764w c0764w = new C0764w();
            try {
                JSONObject jSONObject = new JSONObject(strM853c);
                if (jSONObject.length() == 0) {
                    return null;
                }
                c0764w.f1022a = jSONObject.optInt("id");
                c0764w.f1023b = jSONObject.optString(BaseFragment.DATA_NAME);
                c0764w.f1024c = jSONObject.optString("pinyin");
                c0764w.f1025d = jSONObject.optString("headchar");
                c0764w.f1029h = jSONObject.optInt("mapoldsize");
                c0764w.f1030i = jSONObject.optInt("ratio");
                c0764w.f1033l = jSONObject.optInt("status");
                c0764w.f1028g = new GeoPoint(jSONObject.optInt("y"), jSONObject.optInt("x"));
                if (jSONObject.optInt("up") == 1) {
                    c0764w.f1031j = true;
                } else {
                    c0764w.f1031j = false;
                }
                c0764w.f1026e = jSONObject.optInt("lev");
                if (c0764w.f1031j) {
                    c0764w.f1032k = jSONObject.optInt("mapsize");
                } else {
                    c0764w.f1032k = 0;
                }
                c0764w.f1027f = jSONObject.optInt(TopicKey.VERSION);
                c0765x.m752a(c0764w);
                return c0765x;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
