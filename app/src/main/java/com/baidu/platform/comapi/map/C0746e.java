package com.baidu.platform.comapi.map;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import com.baidu.mapapi.UIMsg;
import com.baidu.mapapi.common.EnvironmentUtilities;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.mapapi.map.MapBaseIndoorMapInfo;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.model.ParcelItem;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.GestureDetectorOnDoubleTapListenerC0751j;
import com.baidu.platform.comjni.map.basemap.BaseMapCallback;
import com.baidu.platform.comjni.map.basemap.C0781a;
import com.baidu.platform.comjni.map.basemap.InterfaceC0782b;
import com.baidu.platform.comjni.map.basemap.JNIBaseMap;
import com.jieli.lib.p015dv.control.utils.Constants;
import com.jieli.lib.p015dv.control.utils.TopicKey;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.baidu.platform.comapi.map.e */
/* JADX INFO: loaded from: classes.dex */
public class C0746e implements InterfaceC0782b {

    /* JADX INFO: renamed from: N */
    private static int f910N;

    /* JADX INFO: renamed from: O */
    private static int f911O;

    /* JADX INFO: renamed from: ai */
    private static List<JNIBaseMap> f912ai;

    /* JADX INFO: renamed from: A */
    private Context f915A;

    /* JADX INFO: renamed from: B */
    private List<AbstractC0745d> f916B;

    /* JADX INFO: renamed from: C */
    private C0727A f917C;

    /* JADX INFO: renamed from: D */
    private C0748g f918D;

    /* JADX INFO: renamed from: E */
    private C0756o f919E;

    /* JADX INFO: renamed from: F */
    private C0734H f920F;

    /* JADX INFO: renamed from: G */
    private C0737K f921G;

    /* JADX INFO: renamed from: H */
    private C0760s f922H;

    /* JADX INFO: renamed from: I */
    private C0755n f923I;

    /* JADX INFO: renamed from: J */
    private C0757p f924J;

    /* JADX INFO: renamed from: K */
    private C0742a f925K;

    /* JADX INFO: renamed from: L */
    private InterfaceC0758q f926L;

    /* JADX INFO: renamed from: M */
    private C0735I f927M;

    /* JADX INFO: renamed from: P */
    private int f928P;

    /* JADX INFO: renamed from: Q */
    private int f929Q;

    /* JADX INFO: renamed from: R */
    private int f930R;

    /* JADX INFO: renamed from: T */
    private VelocityTracker f932T;

    /* JADX INFO: renamed from: U */
    private long f933U;

    /* JADX INFO: renamed from: V */
    private long f934V;

    /* JADX INFO: renamed from: W */
    private long f935W;

    /* JADX INFO: renamed from: X */
    private long f936X;

    /* JADX INFO: renamed from: Y */
    private int f937Y;

    /* JADX INFO: renamed from: Z */
    private float f938Z;

    /* JADX INFO: renamed from: aa */
    private float f940aa;

    /* JADX INFO: renamed from: ab */
    private boolean f941ab;

    /* JADX INFO: renamed from: ac */
    private long f942ac;

    /* JADX INFO: renamed from: ad */
    private long f943ad;

    /* JADX INFO: renamed from: ae */
    private C0747f f944ae;

    /* JADX INFO: renamed from: af */
    private String f945af;

    /* JADX INFO: renamed from: ag */
    private C0743b f946ag;

    /* JADX INFO: renamed from: ah */
    private C0744c f947ah;

    /* JADX INFO: renamed from: g */
    C0781a f953g;

    /* JADX INFO: renamed from: h */
    long f954h;

    /* JADX INFO: renamed from: i */
    boolean f955i;

    /* JADX INFO: renamed from: j */
    public int f956j;

    /* JADX INFO: renamed from: l */
    boolean f957l;

    /* JADX INFO: renamed from: m */
    boolean f958m;

    /* JADX INFO: renamed from: n */
    boolean f959n;

    /* JADX INFO: renamed from: p */
    private boolean f960p;

    /* JADX INFO: renamed from: q */
    private boolean f961q;

    /* JADX INFO: renamed from: y */
    private C0739M f969y;

    /* JADX INFO: renamed from: z */
    private InterfaceC0738L f970z;

    /* JADX INFO: renamed from: o */
    private static final String f914o = GestureDetectorOnDoubleTapListenerC0751j.class.getSimpleName();

    /* JADX INFO: renamed from: k */
    static long f913k = 0;

    /* JADX INFO: renamed from: a */
    public float f939a = 22.0f;

    /* JADX INFO: renamed from: b */
    public float f948b = 3.0f;

    /* JADX INFO: renamed from: c */
    public float f949c = 22.0f;

    /* JADX INFO: renamed from: r */
    private boolean f962r = true;

    /* JADX INFO: renamed from: s */
    private boolean f963s = false;

    /* JADX INFO: renamed from: t */
    private boolean f964t = false;

    /* JADX INFO: renamed from: u */
    private boolean f965u = false;

    /* JADX INFO: renamed from: v */
    private boolean f966v = true;

    /* JADX INFO: renamed from: d */
    boolean f950d = true;

    /* JADX INFO: renamed from: e */
    boolean f951e = true;

    /* JADX INFO: renamed from: w */
    private boolean f967w = true;

    /* JADX INFO: renamed from: x */
    private boolean f968x = false;

    /* JADX INFO: renamed from: S */
    private GestureDetectorOnDoubleTapListenerC0751j.a f931S = new GestureDetectorOnDoubleTapListenerC0751j.a();

    /* JADX INFO: renamed from: f */
    List<InterfaceC0753l> f952f = new ArrayList();

    public C0746e(Context context, String str) {
        this.f915A = context;
        this.f945af = str;
    }

    /* JADX INFO: renamed from: N */
    private void m610N() {
        if (!this.f964t && !this.f961q && !this.f960p && !this.f965u) {
            this.f939a = this.f949c;
            return;
        }
        if (this.f939a > 20.0f) {
            this.f939a = 20.0f;
        }
        if (m620D().f845a > 20.0f) {
            C0731E c0731eM620D = m620D();
            c0731eM620D.f845a = 20.0f;
            m641a(c0731eM620D);
        }
    }

    /* JADX INFO: renamed from: a */
    private Activity m611a(Context context) {
        if (context == null) {
            return null;
        }
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return m611a(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    /* JADX INFO: renamed from: a */
    private void m612a(String str, String str2, long j) {
        try {
            Class<?> cls = Class.forName(str);
            cls.getMethod(str2, Long.TYPE).invoke(cls.newInstance(), Long.valueOf(j));
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: renamed from: e */
    private boolean m613e(Bundle bundle) {
        C0781a c0781a = this.f953g;
        if (c0781a == null) {
            return false;
        }
        return c0781a.m862e(bundle);
    }

    /* JADX INFO: renamed from: f */
    private boolean m614f(Bundle bundle) {
        C0781a c0781a;
        if (bundle == null || (c0781a = this.f953g) == null) {
            return false;
        }
        boolean zM858d = c0781a.m858d(bundle);
        if (zM858d) {
            m663c(zM858d);
            this.f953g.m844b(this.f969y.f906a);
        }
        return zM858d;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x005a A[PHI: r5
  0x005a: PHI (r5v3 android.os.Bundle) = (r5v0 android.os.Bundle), (r5v7 android.os.Bundle) binds: [B:21:0x0058, B:11:0x002e] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005f A[PHI: r5
  0x005f: PHI (r5v5 android.os.Bundle) = (r5v0 android.os.Bundle), (r5v7 android.os.Bundle) binds: [B:21:0x0058, B:11:0x002e] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX INFO: renamed from: g */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m615g(android.os.Bundle r5) {
        /*
            r4 = this;
            java.lang.String r0 = "param"
            java.lang.Object r1 = r5.get(r0)
            java.lang.String r2 = "type"
            java.lang.String r3 = "layer_addr"
            if (r1 == 0) goto L31
            java.lang.Object r5 = r5.get(r0)
            android.os.Bundle r5 = (android.os.Bundle) r5
            int r0 = r5.getInt(r2)
            com.baidu.platform.comapi.map.h r1 = com.baidu.platform.comapi.map.EnumC0749h.ground
            int r1 = r1.ordinal()
            if (r0 != r1) goto L1f
            goto L3d
        L1f:
            com.baidu.platform.comapi.map.h r1 = com.baidu.platform.comapi.map.EnumC0749h.arc
            int r1 = r1.ordinal()
            if (r0 < r1) goto L28
            goto L4d
        L28:
            com.baidu.platform.comapi.map.h r1 = com.baidu.platform.comapi.map.EnumC0749h.popup
            int r1 = r1.ordinal()
            if (r0 != r1) goto L5f
            goto L5a
        L31:
            int r0 = r5.getInt(r2)
            com.baidu.platform.comapi.map.h r1 = com.baidu.platform.comapi.map.EnumC0749h.ground
            int r1 = r1.ordinal()
            if (r0 != r1) goto L45
        L3d:
            com.baidu.platform.comapi.map.o r0 = r4.f919E
            long r0 = r0.f906a
        L41:
            r5.putLong(r3, r0)
            goto L64
        L45:
            com.baidu.platform.comapi.map.h r1 = com.baidu.platform.comapi.map.EnumC0749h.arc
            int r1 = r1.ordinal()
            if (r0 < r1) goto L52
        L4d:
            com.baidu.platform.comapi.map.n r0 = r4.f923I
            long r0 = r0.f906a
            goto L41
        L52:
            com.baidu.platform.comapi.map.h r1 = com.baidu.platform.comapi.map.EnumC0749h.popup
            int r1 = r1.ordinal()
            if (r0 != r1) goto L5f
        L5a:
            com.baidu.platform.comapi.map.s r0 = r4.f922H
            long r0 = r0.f906a
            goto L41
        L5f:
            com.baidu.platform.comapi.map.K r0 = r4.f921G
            long r0 = r0.f906a
            goto L41
        L64:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.platform.comapi.map.C0746e.m615g(android.os.Bundle):void");
    }

    /* JADX INFO: renamed from: j */
    public static void m616j(boolean z) {
        List<JNIBaseMap> listM824d = C0781a.m824d();
        f912ai = listM824d;
        if (listM824d == null || listM824d.size() == 0) {
            C0781a.m823b(0L, z);
            return;
        }
        C0781a.m823b(f912ai.get(0).f1116a, z);
        for (JNIBaseMap jNIBaseMap : f912ai) {
            jNIBaseMap.ClearLayer(jNIBaseMap.f1116a, -1L);
        }
    }

    /* JADX INFO: renamed from: A */
    void m617A() {
        this.f958m = false;
        this.f957l = false;
        Iterator<InterfaceC0753l> it = this.f952f.iterator();
        while (it.hasNext()) {
            it.next().mo423c(m620D());
        }
    }

    /* JADX INFO: renamed from: B */
    public boolean m618B() {
        C0781a c0781a = this.f953g;
        if (c0781a != null) {
            return c0781a.m838a(this.f920F.f906a);
        }
        return false;
    }

    /* JADX INFO: renamed from: C */
    public boolean m619C() {
        C0781a c0781a = this.f953g;
        if (c0781a != null) {
            return c0781a.m838a(this.f947ah.f906a);
        }
        return false;
    }

    /* JADX INFO: renamed from: D */
    public C0731E m620D() {
        C0781a c0781a = this.f953g;
        if (c0781a == null) {
            return null;
        }
        Bundle bundleM870j = c0781a.m870j();
        C0731E c0731e = new C0731E();
        c0731e.m578a(bundleM870j);
        return c0731e;
    }

    /* JADX INFO: renamed from: E */
    public LatLngBounds m621E() {
        C0781a c0781a = this.f953g;
        if (c0781a == null) {
            return null;
        }
        Bundle bundleM871k = c0781a.m871k();
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(CoordUtil.mc2ll(new GeoPoint(bundleM871k.getInt("minCoory"), bundleM871k.getInt("maxCoorx")))).include(CoordUtil.mc2ll(new GeoPoint(bundleM871k.getInt("maxCoory"), bundleM871k.getInt("minCoorx"))));
        return builder.build();
    }

    /* JADX INFO: renamed from: F */
    public int m622F() {
        return this.f928P;
    }

    /* JADX INFO: renamed from: G */
    public int m623G() {
        return this.f929Q;
    }

    /* JADX INFO: renamed from: H */
    public C0731E m624H() {
        C0781a c0781a = this.f953g;
        if (c0781a == null) {
            return null;
        }
        Bundle bundleM872l = c0781a.m872l();
        C0731E c0731e = new C0731E();
        c0731e.m578a(bundleM872l);
        return c0731e;
    }

    /* JADX INFO: renamed from: I */
    public double m625I() {
        return m620D().f857m;
    }

    /* JADX INFO: renamed from: J */
    void m626J() {
        if (this.f957l) {
            return;
        }
        this.f957l = true;
        this.f958m = false;
        Iterator<InterfaceC0753l> it = this.f952f.iterator();
        while (it.hasNext()) {
            it.next().mo413a(m620D());
        }
    }

    /* JADX INFO: renamed from: K */
    void m627K() {
        this.f957l = false;
        if (this.f958m) {
            return;
        }
        Iterator<InterfaceC0753l> it = this.f952f.iterator();
        while (it.hasNext()) {
            it.next().mo423c(m620D());
        }
    }

    /* JADX INFO: renamed from: L */
    void m628L() {
        this.f930R = 0;
        this.f931S.f995e = false;
        this.f931S.f998h = 0.0d;
    }

    /* JADX INFO: renamed from: M */
    void m629M() {
        C0781a c0781a = this.f953g;
        if (c0781a != null) {
            c0781a.m847b();
            this.f953g = null;
        }
    }

    /* JADX INFO: renamed from: a */
    public float m630a(int i, int i2, int i3, int i4, int i5, int i6) {
        if (!this.f955i) {
            return 12.0f;
        }
        if (this.f953g == null) {
            return 0.0f;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(TopicKey.LEFT, i);
        bundle.putInt("right", i3);
        bundle.putInt("bottom", i4);
        bundle.putInt("top", i2);
        bundle.putInt("hasHW", 1);
        bundle.putInt("width", i5);
        bundle.putInt("height", i6);
        return this.f953g.m851c(bundle);
    }

    /* JADX INFO: renamed from: a */
    int m631a(int i, int i2, int i3) {
        return C0781a.m822a(this.f954h, i, i2, i3);
    }

    @Override // com.baidu.platform.comjni.map.basemap.InterfaceC0782b
    /* JADX INFO: renamed from: a */
    public int mo632a(Bundle bundle, long j, int i, Bundle bundle2) {
        if (j == this.f918D.f906a) {
            bundle.putString("jsondata", this.f918D.m591a());
            bundle.putBundle(Constants.JSON_PARAM, this.f918D.m594b());
            return this.f918D.f884g;
        }
        if (j == this.f917C.f906a) {
            bundle.putString("jsondata", this.f917C.m591a());
            bundle.putBundle(Constants.JSON_PARAM, this.f917C.m594b());
            return this.f917C.f884g;
        }
        if (j == this.f924J.f906a) {
            bundle.putBundle(Constants.JSON_PARAM, this.f926L.mo429a(bundle2.getInt("x"), bundle2.getInt("y"), bundle2.getInt("zoom")));
            return this.f924J.f884g;
        }
        if (j != this.f969y.f906a) {
            return 0;
        }
        bundle.putBundle(Constants.JSON_PARAM, this.f970z.mo430a(bundle2.getInt("x"), bundle2.getInt("y"), bundle2.getInt("zoom"), this.f915A));
        return this.f969y.f884g;
    }

    /* JADX INFO: renamed from: a */
    public Point m633a(GeoPoint geoPoint) {
        return this.f927M.m589a(geoPoint);
    }

    /* JADX INFO: renamed from: a */
    void m634a() {
        C0781a c0781a = new C0781a();
        this.f953g = c0781a;
        c0781a.m835a();
        long jM852c = this.f953g.m852c();
        this.f954h = jM852c;
        m612a("com.baidu.platform.comapi.wnplatform.walkmap.WNaviBaiduMap", "setId", jM852c);
        this.f956j = SysOSUtil.getDensityDpi() < 180 ? 18 : SysOSUtil.getDensityDpi() < 240 ? 25 : SysOSUtil.getDensityDpi() < 320 ? 37 : 50;
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
        String str4 = str2 + str;
        String str5 = str2 + "/a/";
        String str6 = str2 + "/idrres/";
        String str7 = str3 + str;
        String str8 = str3 + str;
        String str9 = appCachePath + "/tmp/";
        String str10 = appSecondCachePath + "/tmp/";
        Activity activityM611a = m611a(this.f915A);
        if (activityM611a == null) {
            throw new RuntimeException("Please give the right context.");
        }
        Display defaultDisplay = activityM611a.getWindowManager().getDefaultDisplay();
        this.f953g.m840a(str4, str7, str9, str10, str8, str5, this.f945af, str6, defaultDisplay.getWidth(), defaultDisplay.getHeight(), SysOSUtil.getDensityDpi(), mapTmpStgMax, domTmpStgMax, itsTmpStgMax, 0);
        this.f953g.m863f();
    }

    /* JADX INFO: renamed from: a */
    public void m635a(float f, float f2) {
        this.f939a = f;
        this.f949c = f;
        this.f948b = f2;
    }

    /* JADX INFO: renamed from: a */
    void m636a(int i, int i2) {
        this.f928P = i;
        this.f929Q = i2;
    }

    /* JADX INFO: renamed from: a */
    public void m637a(Bitmap bitmap) {
        Bundle bundle;
        if (this.f953g == null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.put("type", 0);
            jSONObject2.put("x", f910N);
            jSONObject2.put("y", f911O);
            jSONObject2.put("hidetime", 1000);
            jSONArray.put(jSONObject2);
            jSONObject.put("data", jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (bitmap == null) {
            bundle = null;
        } else {
            Bundle bundle2 = new Bundle();
            ArrayList arrayList = new ArrayList();
            ParcelItem parcelItem = new ParcelItem();
            Bundle bundle3 = new Bundle();
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bitmap.getWidth() * bitmap.getHeight() * 4);
            bitmap.copyPixelsToBuffer(byteBufferAllocate);
            bundle3.putByteArray("imgdata", byteBufferAllocate.array());
            bundle3.putInt("imgindex", bitmap.hashCode());
            bundle3.putInt("imgH", bitmap.getHeight());
            bundle3.putInt("imgW", bitmap.getWidth());
            bundle3.putInt("hasIcon", 1);
            parcelItem.setBundle(bundle3);
            arrayList.add(parcelItem);
            if (arrayList.size() > 0) {
                ParcelItem[] parcelItemArr = new ParcelItem[arrayList.size()];
                for (int i = 0; i < arrayList.size(); i++) {
                    parcelItemArr[i] = (ParcelItem) arrayList.get(i);
                }
                bundle2.putParcelableArray("icondata", parcelItemArr);
            }
            bundle = bundle2;
        }
        m660b(jSONObject.toString(), bundle);
        this.f953g.m844b(this.f918D.f906a);
    }

    /* JADX INFO: renamed from: a */
    void m638a(Handler handler) {
        MessageCenter.registMessage(UIMsg.m_AppUI.MSG_APP_SAVESCREEN, handler);
        MessageCenter.registMessage(39, handler);
        MessageCenter.registMessage(41, handler);
        MessageCenter.registMessage(49, handler);
        MessageCenter.registMessage(UIMsg.m_AppUI.V_WM_VDATAENGINE, handler);
        MessageCenter.registMessage(50, handler);
        MessageCenter.registMessage(999, handler);
        BaseMapCallback.addLayerDataInterface(this.f954h, this);
    }

    /* JADX INFO: renamed from: a */
    public void m639a(LatLngBounds latLngBounds) {
        if (latLngBounds == null || this.f953g == null) {
            return;
        }
        LatLng latLng = latLngBounds.northeast;
        LatLng latLng2 = latLngBounds.southwest;
        GeoPoint geoPointLl2mc = CoordUtil.ll2mc(latLng);
        GeoPoint geoPointLl2mc2 = CoordUtil.ll2mc(latLng2);
        int longitudeE6 = (int) geoPointLl2mc.getLongitudeE6();
        int latitudeE6 = (int) geoPointLl2mc2.getLatitudeE6();
        int longitudeE62 = (int) geoPointLl2mc2.getLongitudeE6();
        int latitudeE62 = (int) geoPointLl2mc.getLatitudeE6();
        Bundle bundle = new Bundle();
        bundle.putInt("maxCoorx", longitudeE6);
        bundle.putInt("minCoory", latitudeE6);
        bundle.putInt("minCoorx", longitudeE62);
        bundle.putInt("maxCoory", latitudeE62);
        this.f953g.m845b(bundle);
    }

    /* JADX INFO: renamed from: a */
    void m640a(C0729C c0729c) {
        new C0731E();
        if (c0729c == null) {
            c0729c = new C0729C();
        }
        C0731E c0731e = c0729c.f833a;
        this.f966v = c0729c.f838f;
        this.f967w = c0729c.f836d;
        this.f950d = c0729c.f837e;
        this.f951e = c0729c.f839g;
        this.f953g.m831a(c0731e.m577a(this));
        this.f953g.m825a(EnumC0728B.DEFAULT.ordinal());
        this.f962r = c0729c.f834b;
        if (c0729c.f834b) {
            f910N = (int) (SysOSUtil.getDensity() * 40.0f);
            f911O = (int) (SysOSUtil.getDensity() * 40.0f);
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("x", f910N);
                jSONObject2.put("y", f911O);
                jSONObject2.put("hidetime", 1000);
                jSONArray.put(jSONObject2);
                jSONObject.put("data", jSONArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.f918D.m593a(jSONObject.toString());
            this.f953g.m830a(this.f918D.f906a, true);
        } else {
            this.f953g.m830a(this.f918D.f906a, false);
        }
        int i = c0729c.f835c;
        if (i == 2) {
            m649a(true);
        }
        if (i == 3) {
            this.f953g.m830a(this.f944ae.f906a, false);
        }
    }

    /* JADX INFO: renamed from: a */
    public void m641a(C0731E c0731e) {
        if (this.f953g == null) {
            return;
        }
        Bundle bundleM577a = c0731e.m577a(this);
        bundleM577a.putInt("animation", 0);
        bundleM577a.putInt("animatime", 0);
        this.f953g.m831a(bundleM577a);
    }

    /* JADX INFO: renamed from: a */
    public void m642a(C0731E c0731e, int i) {
        if (this.f953g == null) {
            return;
        }
        Bundle bundleM577a = c0731e.m577a(this);
        bundleM577a.putInt("animation", 1);
        bundleM577a.putInt("animatime", i);
        m705z();
        this.f953g.m831a(bundleM577a);
    }

    /* JADX INFO: renamed from: a */
    public void m643a(InterfaceC0738L interfaceC0738L) {
        this.f970z = interfaceC0738L;
    }

    /* JADX INFO: renamed from: a */
    void m644a(AbstractC0745d abstractC0745d) {
        C0781a c0781a = this.f953g;
        if (c0781a == null) {
            return;
        }
        abstractC0745d.f906a = c0781a.m826a(abstractC0745d.f908c, abstractC0745d.f909d, abstractC0745d.f907b);
        this.f916B.add(abstractC0745d);
    }

    /* JADX INFO: renamed from: a */
    public void m645a(InterfaceC0753l interfaceC0753l) {
        this.f952f.add(interfaceC0753l);
    }

    /* JADX INFO: renamed from: a */
    public void m646a(InterfaceC0758q interfaceC0758q) {
        this.f926L = interfaceC0758q;
    }

    /* JADX INFO: renamed from: a */
    public void m647a(String str, Bundle bundle) {
        if (this.f953g == null) {
            return;
        }
        this.f917C.m593a(str);
        this.f917C.m592a(bundle);
        this.f953g.m844b(this.f917C.f906a);
    }

    /* JADX INFO: renamed from: a */
    public void m648a(List<Bundle> list) {
        if (this.f953g == null || list == null) {
            return;
        }
        int size = list.size();
        Bundle[] bundleArr = new Bundle[list.size()];
        for (int i = 0; i < size; i++) {
            m615g(list.get(i));
            bundleArr[i] = list.get(i);
        }
        this.f953g.m834a(bundleArr);
    }

    /* JADX INFO: renamed from: a */
    public void m649a(boolean z) {
        C0781a c0781a = this.f953g;
        if (c0781a == null) {
            return;
        }
        if (!c0781a.m838a(this.f944ae.f906a)) {
            this.f953g.m830a(this.f944ae.f906a, true);
        }
        this.f961q = z;
        m610N();
        this.f953g.m833a(this.f961q);
    }

    @Override // com.baidu.platform.comjni.map.basemap.InterfaceC0782b
    /* JADX INFO: renamed from: a */
    public boolean mo650a(long j) {
        Iterator<AbstractC0745d> it = this.f916B.iterator();
        while (it.hasNext()) {
            if (it.next().f906a == j) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: a */
    public boolean m651a(Point point) {
        if (point == null || this.f953g == null || point.x < 0 || point.y < 0) {
            return false;
        }
        f910N = point.x;
        f911O = point.y;
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("x", f910N);
            jSONObject2.put("y", f911O);
            jSONObject2.put("hidetime", 1000);
            jSONArray.put(jSONObject2);
            jSONObject.put("data", jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.f918D.m593a(jSONObject.toString());
        this.f953g.m844b(this.f918D.f906a);
        return true;
    }

    /* JADX INFO: renamed from: a */
    public boolean m652a(Bundle bundle) {
        if (this.f953g == null) {
            return false;
        }
        C0739M c0739m = new C0739M();
        this.f969y = c0739m;
        long jM826a = this.f953g.m826a(c0739m.f908c, this.f969y.f909d, this.f969y.f907b);
        if (jM826a != 0) {
            this.f969y.f906a = jM826a;
            this.f916B.add(this.f969y);
            bundle.putLong("sdktileaddr", jM826a);
            if (m613e(bundle) && m614f(bundle)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:140:0x0340  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0358  */
    /* JADX WARN: Removed duplicated region for block: B:160:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x011d  */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    boolean m653a(android.view.MotionEvent r23) {
        /*
            Method dump skipped, instruction units count: 968
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.platform.comapi.map.C0746e.m653a(android.view.MotionEvent):boolean");
    }

    /* JADX INFO: renamed from: a */
    public boolean m654a(String str, String str2) {
        return this.f953g.m839a(str, str2);
    }

    /* JADX INFO: renamed from: b */
    public GeoPoint m655b(int i, int i2) {
        return this.f927M.m590a(i, i2);
    }

    /* JADX INFO: renamed from: b */
    void m656b() {
        this.f916B = new ArrayList();
        C0747f c0747f = new C0747f();
        this.f944ae = c0747f;
        m644a(c0747f);
        C0743b c0743b = new C0743b();
        this.f946ag = c0743b;
        m644a(c0743b);
        C0781a c0781a = this.f953g;
        if (c0781a != null) {
            c0781a.m861e(false);
        }
        C0756o c0756o = new C0756o();
        this.f919E = c0756o;
        m644a(c0756o);
        C0757p c0757p = new C0757p();
        this.f924J = c0757p;
        m644a(c0757p);
        C0742a c0742a = new C0742a();
        this.f925K = c0742a;
        m644a(c0742a);
        m644a(new C0759r());
        C0734H c0734h = new C0734H();
        this.f920F = c0734h;
        m644a(c0734h);
        C0744c c0744c = new C0744c();
        this.f947ah = c0744c;
        m644a(c0744c);
        C0755n c0755n = new C0755n();
        this.f923I = c0755n;
        m644a(c0755n);
        C0737K c0737k = new C0737K();
        this.f921G = c0737k;
        m644a(c0737k);
        C0748g c0748g = new C0748g();
        this.f918D = c0748g;
        m644a(c0748g);
        C0727A c0727a = new C0727A();
        this.f917C = c0727a;
        m644a(c0727a);
        C0760s c0760s = new C0760s();
        this.f922H = c0760s;
        m644a(c0760s);
    }

    /* JADX INFO: renamed from: b */
    public void m657b(Bundle bundle) {
        if (this.f953g == null) {
            return;
        }
        m615g(bundle);
        this.f953g.m864f(bundle);
    }

    /* JADX INFO: renamed from: b */
    void m658b(Handler handler) {
        MessageCenter.unregistMessage(UIMsg.m_AppUI.MSG_APP_SAVESCREEN, handler);
        MessageCenter.unregistMessage(41, handler);
        MessageCenter.unregistMessage(49, handler);
        MessageCenter.unregistMessage(39, handler);
        MessageCenter.unregistMessage(UIMsg.m_AppUI.V_WM_VDATAENGINE, handler);
        MessageCenter.unregistMessage(50, handler);
        MessageCenter.unregistMessage(999, handler);
        BaseMapCallback.removeLayerDataInterface(this.f954h);
    }

    /* JADX INFO: renamed from: b */
    void m659b(MotionEvent motionEvent) {
        if (this.f931S.f995e) {
            return;
        }
        long downTime = motionEvent.getDownTime();
        this.f943ad = downTime;
        if (downTime - this.f942ac < 400) {
            downTime = (Math.abs(motionEvent.getX() - this.f938Z) >= 120.0f || Math.abs(motionEvent.getY() - this.f940aa) >= 120.0f) ? this.f943ad : 0L;
        }
        this.f942ac = downTime;
        this.f938Z = motionEvent.getX();
        this.f940aa = motionEvent.getY();
        m631a(4, 0, (((int) motionEvent.getY()) << 16) | ((int) motionEvent.getX()));
        this.f941ab = true;
    }

    /* JADX INFO: renamed from: b */
    public void m660b(String str, Bundle bundle) {
        if (this.f953g == null) {
            return;
        }
        this.f918D.m593a(str);
        this.f918D.m592a(bundle);
        this.f953g.m844b(this.f918D.f906a);
    }

    /* JADX INFO: renamed from: b */
    public void m661b(boolean z) {
        this.f968x = z;
    }

    /* JADX INFO: renamed from: c */
    public void m662c(Bundle bundle) {
        if (this.f953g == null) {
            return;
        }
        m615g(bundle);
        this.f953g.m866g(bundle);
    }

    /* JADX INFO: renamed from: c */
    public void m663c(boolean z) {
        C0781a c0781a = this.f953g;
        if (c0781a == null) {
            return;
        }
        c0781a.m830a(this.f969y.f906a, z);
    }

    /* JADX INFO: renamed from: c */
    public boolean m664c() {
        return this.f968x;
    }

    /* JADX INFO: renamed from: c */
    boolean m665c(int i, int i2) {
        return i >= 0 && i <= this.f928P + 0 && i2 >= 0 && i2 <= this.f929Q + 0;
    }

    /* JADX INFO: renamed from: c */
    boolean m666c(MotionEvent motionEvent) {
        if (this.f931S.f995e || System.currentTimeMillis() - f913k < 300) {
            return true;
        }
        if (this.f959n) {
            Iterator<InterfaceC0753l> it = this.f952f.iterator();
            while (it.hasNext()) {
                it.next().mo425d(m655b((int) motionEvent.getX(), (int) motionEvent.getY()));
            }
            return true;
        }
        float fAbs = Math.abs(motionEvent.getX() - this.f938Z);
        float fAbs2 = Math.abs(motionEvent.getY() - this.f940aa);
        double density = SysOSUtil.getDensity();
        double density2 = SysOSUtil.getDensity();
        if (density > 1.5d) {
            density2 *= 1.5d;
        }
        float f = (float) density2;
        if (this.f941ab && fAbs / f <= 3.0f && fAbs2 / f <= 3.0f) {
            return true;
        }
        this.f941ab = false;
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        if (this.f950d) {
            m626J();
            m631a(3, 0, (y << 16) | x);
        }
        return false;
    }

    /* JADX INFO: renamed from: d */
    public void m667d(Bundle bundle) {
        if (this.f953g == null) {
            return;
        }
        m615g(bundle);
        this.f953g.m868h(bundle);
    }

    /* JADX INFO: renamed from: d */
    public void m668d(boolean z) {
        C0781a c0781a = this.f953g;
        if (c0781a == null) {
            return;
        }
        c0781a.m830a(this.f944ae.f906a, z);
    }

    /* JADX INFO: renamed from: d */
    public boolean m669d() {
        C0781a c0781a;
        C0739M c0739m = this.f969y;
        if (c0739m == null || (c0781a = this.f953g) == null) {
            return false;
        }
        return c0781a.m855c(c0739m.f906a);
    }

    /* JADX INFO: renamed from: d */
    boolean m670d(MotionEvent motionEvent) {
        if (this.f959n) {
            Iterator<InterfaceC0753l> it = this.f952f.iterator();
            while (it.hasNext()) {
                it.next().mo427e(m655b((int) motionEvent.getX(), (int) motionEvent.getY()));
            }
            this.f959n = false;
            return true;
        }
        boolean z = !this.f931S.f995e && motionEvent.getEventTime() - this.f943ad < 400 && Math.abs(motionEvent.getX() - this.f938Z) < 10.0f && Math.abs(motionEvent.getY() - this.f940aa) < 10.0f;
        m628L();
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (z) {
            return false;
        }
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        m631a(5, 0, (y << 16) | x);
        return true;
    }

    /* JADX INFO: renamed from: e */
    void m671e() {
        C0781a c0781a = this.f953g;
        if (c0781a == null) {
            return;
        }
        this.f927M = new C0735I(c0781a);
    }

    /* JADX INFO: renamed from: e */
    public void m672e(boolean z) {
        C0781a c0781a = this.f953g;
        if (c0781a == null) {
            return;
        }
        this.f965u = z;
        c0781a.m846b(z);
    }

    /* JADX INFO: renamed from: f */
    public void m673f(boolean z) {
        C0781a c0781a = this.f953g;
        if (c0781a == null) {
            return;
        }
        this.f960p = z;
        c0781a.m854c(z);
    }

    /* JADX INFO: renamed from: f */
    public boolean m674f() {
        return this.f960p;
    }

    /* JADX INFO: renamed from: g */
    public String m675g() {
        C0781a c0781a = this.f953g;
        if (c0781a == null) {
            return null;
        }
        return c0781a.m859e(this.f918D.f906a);
    }

    /* JADX INFO: renamed from: g */
    public void m676g(boolean z) {
        C0781a c0781a = this.f953g;
        if (c0781a == null) {
            return;
        }
        c0781a.m857d(z);
    }

    /* JADX INFO: renamed from: h */
    public void m677h(boolean z) {
        C0781a c0781a = this.f953g;
        if (c0781a == null) {
            return;
        }
        this.f962r = z;
        c0781a.m830a(this.f918D.f906a, z);
    }

    /* JADX INFO: renamed from: h */
    public boolean m678h() {
        return this.f965u;
    }

    /* JADX INFO: renamed from: i */
    public void m679i(boolean z) {
        this.f953g.m861e(z);
        this.f953g.m856d(this.f946ag.f906a);
        this.f953g.m856d(this.f947ah.f906a);
    }

    /* JADX INFO: renamed from: i */
    public boolean m680i() {
        C0781a c0781a = this.f953g;
        if (c0781a == null) {
            return false;
        }
        return c0781a.m873m();
    }

    /* JADX INFO: renamed from: j */
    public boolean m681j() {
        return this.f961q;
    }

    /* JADX INFO: renamed from: k */
    public void m682k(boolean z) {
        C0781a c0781a = this.f953g;
        if (c0781a == null) {
            return;
        }
        this.f963s = z;
        c0781a.m830a(this.f917C.f906a, z);
    }

    /* JADX INFO: renamed from: k */
    public boolean m683k() {
        return this.f953g.m838a(this.f944ae.f906a);
    }

    /* JADX INFO: renamed from: l */
    public void m684l(boolean z) {
        C0781a c0781a = this.f953g;
        if (c0781a == null) {
            return;
        }
        this.f964t = z;
        c0781a.m830a(this.f924J.f906a, z);
    }

    /* JADX INFO: renamed from: l */
    public boolean m685l() {
        C0781a c0781a = this.f953g;
        if (c0781a == null) {
            return false;
        }
        return c0781a.m877q();
    }

    /* JADX INFO: renamed from: m */
    public void m686m() {
        C0781a c0781a = this.f953g;
        if (c0781a == null) {
            return;
        }
        c0781a.m856d(this.f919E.f906a);
        this.f953g.m856d(this.f923I.f906a);
        this.f953g.m856d(this.f921G.f906a);
        this.f953g.m856d(this.f922H.f906a);
    }

    /* JADX INFO: renamed from: m */
    public void m687m(boolean z) {
        this.f950d = z;
    }

    /* JADX INFO: renamed from: n */
    public void m688n() {
        C0781a c0781a = this.f953g;
        if (c0781a == null) {
            return;
        }
        c0781a.m878r();
        this.f953g.m844b(this.f924J.f906a);
    }

    /* JADX INFO: renamed from: n */
    public void m689n(boolean z) {
        this.f951e = z;
    }

    /* JADX INFO: renamed from: o */
    public MapBaseIndoorMapInfo m690o() {
        return this.f953g.m879s();
    }

    /* JADX INFO: renamed from: o */
    public void m691o(boolean z) {
        this.f967w = z;
    }

    /* JADX INFO: renamed from: p */
    public void m692p(boolean z) {
        this.f966v = z;
    }

    /* JADX INFO: renamed from: p */
    public boolean m693p() {
        return this.f953g.m880t();
    }

    /* JADX INFO: renamed from: q */
    public void m694q(boolean z) {
        C0781a c0781a = this.f953g;
        if (c0781a != null) {
            c0781a.m830a(this.f920F.f906a, z);
        }
    }

    /* JADX INFO: renamed from: q */
    public boolean m695q() {
        return this.f962r;
    }

    /* JADX INFO: renamed from: r */
    public void m696r(boolean z) {
        C0781a c0781a = this.f953g;
        if (c0781a != null) {
            c0781a.m830a(this.f947ah.f906a, z);
        }
    }

    /* JADX INFO: renamed from: r */
    public boolean m697r() {
        return this.f963s;
    }

    /* JADX INFO: renamed from: s */
    public void m698s() {
        C0781a c0781a = this.f953g;
        if (c0781a == null) {
            return;
        }
        c0781a.m844b(this.f924J.f906a);
    }

    /* JADX INFO: renamed from: t */
    public void m699t() {
        C0781a c0781a = this.f953g;
        if (c0781a == null) {
            return;
        }
        c0781a.m865g();
    }

    /* JADX INFO: renamed from: u */
    public void m700u() {
        C0781a c0781a = this.f953g;
        if (c0781a == null) {
            return;
        }
        c0781a.m867h();
    }

    /* JADX INFO: renamed from: v */
    public boolean m701v() {
        return this.f950d;
    }

    /* JADX INFO: renamed from: w */
    public boolean m702w() {
        return this.f951e;
    }

    /* JADX INFO: renamed from: x */
    public boolean m703x() {
        return this.f967w;
    }

    /* JADX INFO: renamed from: y */
    public boolean m704y() {
        return this.f966v;
    }

    /* JADX INFO: renamed from: z */
    void m705z() {
        if (this.f957l || this.f958m) {
            return;
        }
        this.f958m = true;
        Iterator<InterfaceC0753l> it = this.f952f.iterator();
        while (it.hasNext()) {
            it.next().mo413a(m620D());
        }
    }
}
