package com.baidu.mapapi.map;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.mapapi.map.MapBaseIndoorMapInfo;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapViewLayoutParams;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.model.ParcelItem;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.C0731E;
import com.baidu.platform.comapi.map.C0746e;
import com.baidu.platform.comapi.map.EnumC0730D;
import com.baidu.platform.comapi.map.EnumC0749h;
import com.baidu.platform.comapi.map.GestureDetectorOnDoubleTapListenerC0732F;
import com.baidu.platform.comapi.map.GestureDetectorOnDoubleTapListenerC0751j;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.microedition.khronos.opengles.GL10;
import org.apache.commons.net.nntp.NNTPReply;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaCodecInfo;

/* JADX INFO: loaded from: classes.dex */
public class BaiduMap {
    public static final int MAP_TYPE_NONE = 3;
    public static final int MAP_TYPE_NORMAL = 1;
    public static final int MAP_TYPE_SATELLITE = 2;

    /* JADX INFO: renamed from: e */
    private static final String f238e = BaiduMap.class.getSimpleName();

    /* JADX INFO: renamed from: A */
    private OnMapDrawFrameCallback f239A;

    /* JADX INFO: renamed from: B */
    private OnBaseIndoorMapListener f240B;

    /* JADX INFO: renamed from: C */
    private TileOverlay f241C;

    /* JADX INFO: renamed from: D */
    private HeatMap f242D;

    /* JADX INFO: renamed from: E */
    private Lock f243E;

    /* JADX INFO: renamed from: F */
    private Lock f244F;

    /* JADX INFO: renamed from: G */
    private InfoWindow f245G;

    /* JADX INFO: renamed from: H */
    private Marker f246H;

    /* JADX INFO: renamed from: I */
    private View f247I;

    /* JADX INFO: renamed from: J */
    private Marker f248J;

    /* JADX INFO: renamed from: K */
    private MyLocationData f249K;

    /* JADX INFO: renamed from: L */
    private MyLocationConfiguration f250L;

    /* JADX INFO: renamed from: M */
    private boolean f251M;

    /* JADX INFO: renamed from: N */
    private boolean f252N;

    /* JADX INFO: renamed from: O */
    private boolean f253O;

    /* JADX INFO: renamed from: P */
    private boolean f254P;

    /* JADX INFO: renamed from: Q */
    private Point f255Q;

    /* JADX INFO: renamed from: a */
    MapView f256a;

    /* JADX INFO: renamed from: b */
    TextureMapView f257b;

    /* JADX INFO: renamed from: c */
    WearMapView f258c;

    /* JADX INFO: renamed from: d */
    EnumC0730D f259d;

    /* JADX INFO: renamed from: f */
    private Projection f260f;

    /* JADX INFO: renamed from: g */
    private UiSettings f261g;

    /* JADX INFO: renamed from: h */
    private GestureDetectorOnDoubleTapListenerC0751j f262h;

    /* JADX INFO: renamed from: i */
    private C0746e f263i;

    /* JADX INFO: renamed from: j */
    private GestureDetectorOnDoubleTapListenerC0732F f264j;

    /* JADX INFO: renamed from: k */
    private List<Overlay> f265k;

    /* JADX INFO: renamed from: l */
    private List<Marker> f266l;

    /* JADX INFO: renamed from: m */
    private List<Marker> f267m;

    /* JADX INFO: renamed from: n */
    private Overlay.InterfaceC0675a f268n;

    /* JADX INFO: renamed from: o */
    private OnMapStatusChangeListener f269o;

    /* JADX INFO: renamed from: p */
    private OnMapTouchListener f270p;

    /* JADX INFO: renamed from: q */
    private OnMapClickListener f271q;

    /* JADX INFO: renamed from: r */
    private OnMapLoadedCallback f272r;

    /* JADX INFO: renamed from: s */
    private OnMapRenderCallback f273s;

    /* JADX INFO: renamed from: t */
    private OnMapDoubleClickListener f274t;

    /* JADX INFO: renamed from: u */
    private OnMapLongClickListener f275u;

    /* JADX INFO: renamed from: v */
    private CopyOnWriteArrayList<OnMarkerClickListener> f276v;

    /* JADX INFO: renamed from: w */
    private CopyOnWriteArrayList<OnPolylineClickListener> f277w;

    /* JADX INFO: renamed from: x */
    private OnMarkerDragListener f278x;

    /* JADX INFO: renamed from: y */
    private OnMyLocationClickListener f279y;

    /* JADX INFO: renamed from: z */
    private SnapshotReadyCallback f280z;

    /* JADX INFO: renamed from: com.baidu.mapapi.map.BaiduMap$1 */
    /* synthetic */ class C06721 {

        /* JADX INFO: renamed from: a */
        static final /* synthetic */ int[] f281a;

        /* JADX INFO: renamed from: b */
        static final /* synthetic */ int[] f282b;

        static {
            int[] iArr = new int[EnumC0730D.values().length];
            f282b = iArr;
            try {
                iArr[EnumC0730D.TextureView.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f282b[EnumC0730D.GLSurfaceView.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[MyLocationConfiguration.LocationMode.values().length];
            f281a = iArr2;
            try {
                iArr2[MyLocationConfiguration.LocationMode.COMPASS.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f281a[MyLocationConfiguration.LocationMode.FOLLOWING.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f281a[MyLocationConfiguration.LocationMode.NORMAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public interface OnBaseIndoorMapListener {
        void onBaseIndoorMapMode(boolean z, MapBaseIndoorMapInfo mapBaseIndoorMapInfo);
    }

    public interface OnMapClickListener {
        void onMapClick(LatLng latLng);

        boolean onMapPoiClick(MapPoi mapPoi);
    }

    public interface OnMapDoubleClickListener {
        void onMapDoubleClick(LatLng latLng);
    }

    public interface OnMapDrawFrameCallback {
        void onMapDrawFrame(MapStatus mapStatus);

        @Deprecated
        void onMapDrawFrame(GL10 gl10, MapStatus mapStatus);
    }

    public interface OnMapLoadedCallback {
        void onMapLoaded();
    }

    public interface OnMapLongClickListener {
        void onMapLongClick(LatLng latLng);
    }

    public interface OnMapRenderCallback {
        void onMapRenderFinished();
    }

    public interface OnMapStatusChangeListener {
        void onMapStatusChange(MapStatus mapStatus);

        void onMapStatusChangeFinish(MapStatus mapStatus);

        void onMapStatusChangeStart(MapStatus mapStatus);
    }

    public interface OnMapTouchListener {
        void onTouch(MotionEvent motionEvent);
    }

    public interface OnMarkerClickListener {
        boolean onMarkerClick(Marker marker);
    }

    public interface OnMarkerDragListener {
        void onMarkerDrag(Marker marker);

        void onMarkerDragEnd(Marker marker);

        void onMarkerDragStart(Marker marker);
    }

    public interface OnMyLocationClickListener {
        boolean onMyLocationClick();
    }

    public interface OnPolylineClickListener {
        boolean onPolylineClick(Polyline polyline);
    }

    public interface SnapshotReadyCallback {
        void onSnapshotReady(Bitmap bitmap);
    }

    BaiduMap(GestureDetectorOnDoubleTapListenerC0732F gestureDetectorOnDoubleTapListenerC0732F) {
        this.f276v = new CopyOnWriteArrayList<>();
        this.f277w = new CopyOnWriteArrayList<>();
        this.f243E = new ReentrantLock();
        this.f244F = new ReentrantLock();
        this.f264j = gestureDetectorOnDoubleTapListenerC0732F;
        this.f263i = gestureDetectorOnDoubleTapListenerC0732F.m585b();
        this.f259d = EnumC0730D.TextureView;
        m249c();
    }

    BaiduMap(GestureDetectorOnDoubleTapListenerC0751j gestureDetectorOnDoubleTapListenerC0751j) {
        this.f276v = new CopyOnWriteArrayList<>();
        this.f277w = new CopyOnWriteArrayList<>();
        this.f243E = new ReentrantLock();
        this.f244F = new ReentrantLock();
        this.f262h = gestureDetectorOnDoubleTapListenerC0751j;
        this.f263i = gestureDetectorOnDoubleTapListenerC0751j.m716a();
        this.f259d = EnumC0730D.GLSurfaceView;
        m249c();
    }

    /* JADX INFO: renamed from: a */
    private Point m240a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int iIntValue = 0;
        int iIntValue2 = 0;
        for (String str2 : str.replaceAll("^\\{", "").replaceAll("\\}$", "").split(",")) {
            String[] strArrSplit = str2.replaceAll("\"", "").split(":");
            if ("x".equals(strArrSplit[0])) {
                iIntValue = Integer.valueOf(strArrSplit[1]).intValue();
            }
            if ("y".equals(strArrSplit[0])) {
                iIntValue2 = Integer.valueOf(strArrSplit[1]).intValue();
            }
        }
        return new Point(iIntValue, iIntValue2);
    }

    /* JADX INFO: renamed from: a */
    private C0731E m243a(MapStatusUpdate mapStatusUpdate) {
        C0746e c0746e = this.f263i;
        if (c0746e == null) {
            return null;
        }
        return mapStatusUpdate.m319a(this.f263i, getMapStatus()).m317b(c0746e.m620D());
    }

    /* JADX INFO: renamed from: a */
    private final void m245a(MyLocationData myLocationData, MyLocationConfiguration myLocationConfiguration) {
        BaiduMap baiduMap;
        Bundle bundle;
        MapStatus.Builder builderZoom;
        float f;
        if (myLocationData == null || myLocationConfiguration == null || !isMyLocationEnabled()) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        GeoPoint geoPointLl2mc = CoordUtil.ll2mc(new LatLng(myLocationData.latitude, myLocationData.longitude));
        try {
            jSONObject.put("type", 0);
            jSONObject2.put("ptx", geoPointLl2mc.getLongitudeE6());
            jSONObject2.put("pty", geoPointLl2mc.getLatitudeE6());
            jSONObject2.put("radius", CoordUtil.getMCDistanceByOneLatLngAndRadius(r1, (int) myLocationData.accuracy));
            float f2 = myLocationData.direction;
            if (myLocationConfiguration.enableDirection) {
                f = myLocationData.direction % 360.0f;
                if (f > 180.0f) {
                    f -= 360.0f;
                } else if (f < -180.0f) {
                    f += 360.0f;
                }
            } else {
                f = -1.0f;
            }
            jSONObject2.put("direction", f);
            jSONObject2.put("iconarrownor", "NormalLocArrow");
            jSONObject2.put("iconarrownorid", 28);
            jSONObject2.put("iconarrowfoc", "FocusLocArrow");
            jSONObject2.put("iconarrowfocid", 29);
            jSONObject2.put("lineid", myLocationConfiguration.accuracyCircleStrokeColor);
            jSONObject2.put("areaid", myLocationConfiguration.accuracyCircleFillColor);
            jSONArray.put(jSONObject2);
            jSONObject.put("data", jSONArray);
            if (myLocationConfiguration.locationMode == MyLocationConfiguration.LocationMode.COMPASS) {
                jSONObject3.put("ptx", geoPointLl2mc.getLongitudeE6());
                jSONObject3.put("pty", geoPointLl2mc.getLatitudeE6());
                try {
                    jSONObject3.put("radius", 0);
                    jSONObject3.put("direction", 0);
                    jSONObject3.put("iconarrownor", "direction_wheel");
                    jSONObject3.put("iconarrownorid", 54);
                    jSONObject3.put("iconarrowfoc", "direction_wheel");
                    jSONObject3.put("iconarrowfocid", 54);
                    jSONArray.put(jSONObject3);
                } catch (JSONException e) {
                    e = e;
                    e.printStackTrace();
                }
            }
        } catch (JSONException e2) {
            e = e2;
        }
        if (myLocationConfiguration.customMarker == null) {
            bundle = null;
            baiduMap = this;
        } else {
            ArrayList<BitmapDescriptor> arrayList = new ArrayList();
            arrayList.add(myLocationConfiguration.customMarker);
            Bundle bundle2 = new Bundle();
            ArrayList arrayList2 = new ArrayList();
            for (BitmapDescriptor bitmapDescriptor : arrayList) {
                ParcelItem parcelItem = new ParcelItem();
                Bundle bundle3 = new Bundle();
                Bitmap bitmap = bitmapDescriptor.f295a;
                ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bitmap.getWidth() * bitmap.getHeight() * 4);
                bitmap.copyPixelsToBuffer(byteBufferAllocate);
                bundle3.putByteArray("imgdata", byteBufferAllocate.array());
                bundle3.putInt("imgindex", bitmapDescriptor.hashCode());
                bundle3.putInt("imgH", bitmap.getHeight());
                bundle3.putInt("imgW", bitmap.getWidth());
                parcelItem.setBundle(bundle3);
                arrayList2.add(parcelItem);
            }
            if (arrayList2.size() > 0) {
                ParcelItem[] parcelItemArr = new ParcelItem[arrayList2.size()];
                for (int i = 0; i < arrayList2.size(); i++) {
                    parcelItemArr[i] = (ParcelItem) arrayList2.get(i);
                }
                bundle2.putParcelableArray("icondata", parcelItemArr);
            }
            baiduMap = this;
            bundle = bundle2;
        }
        C0746e c0746e = baiduMap.f263i;
        if (c0746e != null) {
            c0746e.m647a(jSONObject.toString(), bundle);
        }
        int i2 = C06721.f281a[myLocationConfiguration.locationMode.ordinal()];
        if (i2 == 1) {
            builderZoom = new MapStatus.Builder().rotate(myLocationData.direction).overlook(-45.0f).target(new LatLng(myLocationData.latitude, myLocationData.longitude)).targetScreen(getMapStatus().targetScreen).zoom(getMapStatus().zoom);
        } else if (i2 != 2) {
            return;
        } else {
            builderZoom = new MapStatus.Builder().target(new LatLng(myLocationData.latitude, myLocationData.longitude)).zoom(getMapStatus().zoom).rotate(getMapStatus().rotate).overlook(getMapStatus().overlook).targetScreen(getMapStatus().targetScreen);
        }
        baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builderZoom.build()));
    }

    /* JADX INFO: renamed from: c */
    private void m249c() {
        this.f265k = new CopyOnWriteArrayList();
        this.f266l = new CopyOnWriteArrayList();
        this.f267m = new CopyOnWriteArrayList();
        this.f255Q = new Point((int) (SysOSUtil.getDensity() * 40.0f), (int) (SysOSUtil.getDensity() * 40.0f));
        this.f261g = new UiSettings(this.f263i);
        this.f268n = new C0678a(this);
        this.f263i.m645a(new C0679b(this));
        this.f263i.m646a(new C0680c(this));
        this.f263i.m643a(new C0681d(this));
        this.f251M = this.f263i.m618B();
        this.f252N = this.f263i.m619C();
    }

    /* JADX INFO: renamed from: a */
    void m273a() {
        C0746e c0746e = this.f263i;
        if (c0746e == null) {
            return;
        }
        c0746e.m698s();
    }

    /* JADX INFO: renamed from: a */
    void m274a(HeatMap heatMap) {
        this.f243E.lock();
        try {
            if (this.f242D != null && this.f263i != null && heatMap == this.f242D) {
                this.f242D.m307b();
                this.f242D.m308c();
                this.f242D.f352a = null;
                this.f263i.m688n();
                this.f242D = null;
                this.f263i.m684l(false);
            }
        } finally {
            this.f243E.unlock();
        }
    }

    /* JADX INFO: renamed from: a */
    void m275a(TileOverlay tileOverlay) {
        this.f244F.lock();
        if (tileOverlay != null) {
            try {
                if (this.f241C == tileOverlay) {
                    tileOverlay.m379b();
                    tileOverlay.f603a = null;
                    if (this.f263i != null) {
                        this.f263i.m663c(false);
                    }
                }
            } finally {
                this.f241C = null;
                this.f244F.unlock();
            }
        }
    }

    public void addHeatMap(HeatMap heatMap) {
        if (heatMap == null) {
            return;
        }
        this.f243E.lock();
        try {
            if (heatMap == this.f242D) {
                return;
            }
            if (this.f242D != null) {
                this.f242D.m307b();
                this.f242D.m308c();
                this.f242D.f352a = null;
                this.f263i.m688n();
            }
            this.f242D = heatMap;
            heatMap.f352a = this;
            this.f263i.m684l(true);
        } finally {
            this.f243E.unlock();
        }
    }

    public final Overlay addOverlay(OverlayOptions overlayOptions) {
        if (overlayOptions == null) {
            return null;
        }
        Overlay overlayMo238a = overlayOptions.mo238a();
        overlayMo238a.listener = this.f268n;
        if (overlayMo238a instanceof Marker) {
            Marker marker = (Marker) overlayMo238a;
            if (marker.f463n != null && marker.f463n.size() != 0) {
                this.f266l.add(marker);
                C0746e c0746e = this.f263i;
                if (c0746e != null) {
                    c0746e.m661b(true);
                }
            }
            this.f267m.add(marker);
        }
        Bundle bundle = new Bundle();
        overlayMo238a.mo237a(bundle);
        C0746e c0746e2 = this.f263i;
        if (c0746e2 != null) {
            c0746e2.m657b(bundle);
        }
        this.f265k.add(overlayMo238a);
        return overlayMo238a;
    }

    public final List<Overlay> addOverlays(List<OverlayOptions> list) {
        int i;
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        Bundle[] bundleArr = new Bundle[size];
        int i2 = 0;
        for (OverlayOptions overlayOptions : list) {
            if (overlayOptions != null) {
                Bundle bundle = new Bundle();
                Overlay overlayMo238a = overlayOptions.mo238a();
                overlayMo238a.listener = this.f268n;
                if (overlayMo238a instanceof Marker) {
                    Marker marker = (Marker) overlayMo238a;
                    if (marker.f463n != null && marker.f463n.size() != 0) {
                        this.f266l.add(marker);
                        C0746e c0746e = this.f263i;
                        if (c0746e != null) {
                            c0746e.m661b(true);
                        }
                    }
                    this.f267m.add(marker);
                }
                this.f265k.add(overlayMo238a);
                arrayList.add(overlayMo238a);
                overlayMo238a.mo237a(bundle);
                bundleArr[i2] = bundle;
                i2++;
            }
        }
        int i3 = size / NNTPReply.SERVICE_DISCONTINUED;
        for (int i4 = 0; i4 < i3 + 1; i4++) {
            ArrayList arrayList2 = new ArrayList();
            for (int i5 = 0; i5 < 400 && (i = (i4 * NNTPReply.SERVICE_DISCONTINUED) + i5) < size; i5++) {
                if (bundleArr[i] != null) {
                    arrayList2.add(bundleArr[i]);
                }
            }
            C0746e c0746e2 = this.f263i;
            if (c0746e2 != null) {
                c0746e2.m648a(arrayList2);
            }
        }
        return arrayList;
    }

    public TileOverlay addTileLayer(TileOverlayOptions tileOverlayOptions) {
        if (tileOverlayOptions == null) {
            return null;
        }
        TileOverlay tileOverlay = this.f241C;
        if (tileOverlay != null) {
            tileOverlay.m379b();
            this.f241C.f603a = null;
        }
        C0746e c0746e = this.f263i;
        if (c0746e == null || !c0746e.m652a(tileOverlayOptions.m381a())) {
            return null;
        }
        TileOverlay tileOverlayM382a = tileOverlayOptions.m382a(this);
        this.f241C = tileOverlayM382a;
        return tileOverlayM382a;
    }

    public final void animateMapStatus(MapStatusUpdate mapStatusUpdate) {
        animateMapStatus(mapStatusUpdate, IjkMediaCodecInfo.RANK_SECURE);
    }

    public final void animateMapStatus(MapStatusUpdate mapStatusUpdate, int i) {
        if (mapStatusUpdate == null || i <= 0) {
            return;
        }
        C0731E c0731eM243a = m243a(mapStatusUpdate);
        C0746e c0746e = this.f263i;
        if (c0746e == null) {
            return;
        }
        if (this.f254P) {
            c0746e.m642a(c0731eM243a, i);
        } else {
            c0746e.m641a(c0731eM243a);
        }
    }

    /* JADX INFO: renamed from: b */
    boolean m276b() {
        C0746e c0746e = this.f263i;
        if (c0746e == null) {
            return false;
        }
        return c0746e.m669d();
    }

    public final void clear() {
        this.f265k.clear();
        this.f266l.clear();
        this.f267m.clear();
        C0746e c0746e = this.f263i;
        if (c0746e != null) {
            c0746e.m661b(false);
            this.f263i.m686m();
        }
        hideInfoWindow();
    }

    public final Point getCompassPosition() {
        C0746e c0746e = this.f263i;
        if (c0746e != null) {
            return m240a(c0746e.m675g());
        }
        return null;
    }

    public MapBaseIndoorMapInfo getFocusedBaseIndoorMapInfo() {
        return this.f263i.m690o();
    }

    public final MyLocationConfiguration getLocationConfigeration() {
        return this.f250L;
    }

    public final MyLocationData getLocationData() {
        return this.f249K;
    }

    public final MapStatus getMapStatus() {
        C0746e c0746e = this.f263i;
        if (c0746e == null) {
            return null;
        }
        return MapStatus.m314a(c0746e.m620D());
    }

    public final LatLngBounds getMapStatusLimit() {
        C0746e c0746e = this.f263i;
        if (c0746e == null) {
            return null;
        }
        return c0746e.m621E();
    }

    public final int getMapType() {
        C0746e c0746e = this.f263i;
        if (c0746e == null) {
            return 1;
        }
        if (c0746e.m683k()) {
            return this.f263i.m681j() ? 2 : 1;
        }
        return 3;
    }

    public List<Marker> getMarkersInBounds(LatLngBounds latLngBounds) {
        if (getMapStatus() == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (this.f267m.size() == 0) {
            return null;
        }
        for (Marker marker : this.f267m) {
            if (latLngBounds.contains(marker.getPosition())) {
                arrayList.add(marker);
            }
        }
        return arrayList;
    }

    public final float getMaxZoomLevel() {
        C0746e c0746e = this.f263i;
        if (c0746e == null) {
            return 0.0f;
        }
        return c0746e.f939a;
    }

    public final float getMinZoomLevel() {
        C0746e c0746e = this.f263i;
        if (c0746e == null) {
            return 0.0f;
        }
        return c0746e.f948b;
    }

    public final Projection getProjection() {
        return this.f260f;
    }

    public final UiSettings getUiSettings() {
        return this.f261g;
    }

    public void hideInfoWindow() {
        InfoWindow infoWindow = this.f245G;
        if (infoWindow != null) {
            if (infoWindow.f370b != null) {
                int i = C06721.f282b[this.f259d.ordinal()];
                if (i == 1) {
                    TextureMapView textureMapView = this.f257b;
                    if (textureMapView != null) {
                        textureMapView.removeView(this.f247I);
                    }
                } else if (i == 2 && this.f262h != null) {
                    this.f256a.removeView(this.f247I);
                }
                this.f247I = null;
            }
            this.f245G = null;
            this.f246H.remove();
            this.f246H = null;
        }
    }

    public final boolean isBaiduHeatMapEnabled() {
        C0746e c0746e = this.f263i;
        if (c0746e == null) {
            return false;
        }
        return c0746e.m678h();
    }

    public boolean isBaseIndoorMapMode() {
        return this.f263i.m693p();
    }

    public final boolean isBuildingsEnabled() {
        C0746e c0746e = this.f263i;
        if (c0746e == null) {
            return false;
        }
        return c0746e.m685l();
    }

    public final boolean isMyLocationEnabled() {
        C0746e c0746e = this.f263i;
        if (c0746e == null) {
            return false;
        }
        return c0746e.m697r();
    }

    public final boolean isSupportBaiduHeatMap() {
        C0746e c0746e = this.f263i;
        if (c0746e == null) {
            return false;
        }
        return c0746e.m680i();
    }

    public final boolean isTrafficEnabled() {
        C0746e c0746e = this.f263i;
        if (c0746e == null) {
            return false;
        }
        return c0746e.m674f();
    }

    public final void removeMarkerClickListener(OnMarkerClickListener onMarkerClickListener) {
        if (this.f276v.contains(onMarkerClickListener)) {
            this.f276v.remove(onMarkerClickListener);
        }
    }

    public final void setBaiduHeatMapEnabled(boolean z) {
        C0746e c0746e = this.f263i;
        if (c0746e != null) {
            c0746e.m672e(z);
        }
    }

    public final void setBuildingsEnabled(boolean z) {
        C0746e c0746e = this.f263i;
        if (c0746e != null) {
            c0746e.m676g(z);
        }
    }

    public void setCompassIcon(Bitmap bitmap) {
        if (bitmap == null) {
            throw new IllegalArgumentException("compass's icon can not be null");
        }
        this.f263i.m637a(bitmap);
    }

    public void setCompassPosition(Point point) {
        if (this.f263i.m651a(point)) {
            this.f255Q = point;
        }
    }

    public final void setIndoorEnable(boolean z) {
        C0746e c0746e = this.f263i;
        if (c0746e != null) {
            this.f253O = z;
            c0746e.m679i(z);
        }
        OnBaseIndoorMapListener onBaseIndoorMapListener = this.f240B;
        if (onBaseIndoorMapListener == null || z) {
            return;
        }
        onBaseIndoorMapListener.onBaseIndoorMapMode(false, null);
    }

    public final void setMapStatus(MapStatusUpdate mapStatusUpdate) {
        if (mapStatusUpdate == null) {
            return;
        }
        C0731E c0731eM243a = m243a(mapStatusUpdate);
        C0746e c0746e = this.f263i;
        if (c0746e == null) {
            return;
        }
        c0746e.m641a(c0731eM243a);
        OnMapStatusChangeListener onMapStatusChangeListener = this.f269o;
        if (onMapStatusChangeListener != null) {
            onMapStatusChangeListener.onMapStatusChange(getMapStatus());
        }
    }

    public final void setMapStatusLimits(LatLngBounds latLngBounds) {
        C0746e c0746e = this.f263i;
        if (c0746e == null) {
            return;
        }
        c0746e.m639a(latLngBounds);
        setMapStatus(MapStatusUpdateFactory.newLatLngBounds(latLngBounds));
    }

    public final void setMapType(int i) {
        C0746e c0746e = this.f263i;
        if (c0746e == null) {
            return;
        }
        if (i == 1) {
            c0746e.m649a(false);
            this.f263i.m694q(this.f251M);
            this.f263i.m696r(this.f252N);
            this.f263i.m668d(true);
            this.f263i.m679i(this.f253O);
        } else if (i == 2) {
            c0746e.m649a(true);
            this.f263i.m694q(this.f251M);
            this.f263i.m696r(this.f252N);
            this.f263i.m668d(true);
        } else if (i == 3) {
            if (c0746e.m618B()) {
                this.f263i.m694q(false);
            }
            if (this.f263i.m619C()) {
                this.f263i.m696r(false);
            }
            this.f263i.m668d(false);
            this.f263i.m679i(false);
        }
        GestureDetectorOnDoubleTapListenerC0751j gestureDetectorOnDoubleTapListenerC0751j = this.f262h;
        if (gestureDetectorOnDoubleTapListenerC0751j != null) {
            gestureDetectorOnDoubleTapListenerC0751j.m717a(i);
        }
    }

    public final void setMaxAndMinZoomLevel(float f, float f2) {
        C0746e c0746e;
        if (f <= 21.0f && f2 >= 3.0f && f >= f2 && (c0746e = this.f263i) != null) {
            c0746e.m635a(f, f2);
        }
    }

    public final void setMyLocationConfigeration(MyLocationConfiguration myLocationConfiguration) {
        this.f250L = myLocationConfiguration;
        m245a(this.f249K, myLocationConfiguration);
    }

    public final void setMyLocationData(MyLocationData myLocationData) {
        this.f249K = myLocationData;
        if (this.f250L == null) {
            this.f250L = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, false, null);
        }
        m245a(myLocationData, this.f250L);
    }

    public final void setMyLocationEnabled(boolean z) {
        C0746e c0746e = this.f263i;
        if (c0746e != null) {
            c0746e.m682k(z);
        }
    }

    public final void setOnBaseIndoorMapListener(OnBaseIndoorMapListener onBaseIndoorMapListener) {
        this.f240B = onBaseIndoorMapListener;
    }

    public final void setOnMapClickListener(OnMapClickListener onMapClickListener) {
        this.f271q = onMapClickListener;
    }

    public final void setOnMapDoubleClickListener(OnMapDoubleClickListener onMapDoubleClickListener) {
        this.f274t = onMapDoubleClickListener;
    }

    public final void setOnMapDrawFrameCallback(OnMapDrawFrameCallback onMapDrawFrameCallback) {
        this.f239A = onMapDrawFrameCallback;
    }

    public void setOnMapLoadedCallback(OnMapLoadedCallback onMapLoadedCallback) {
        this.f272r = onMapLoadedCallback;
    }

    public final void setOnMapLongClickListener(OnMapLongClickListener onMapLongClickListener) {
        this.f275u = onMapLongClickListener;
    }

    public void setOnMapRenderCallbadk(OnMapRenderCallback onMapRenderCallback) {
        this.f273s = onMapRenderCallback;
    }

    public final void setOnMapStatusChangeListener(OnMapStatusChangeListener onMapStatusChangeListener) {
        this.f269o = onMapStatusChangeListener;
    }

    public final void setOnMapTouchListener(OnMapTouchListener onMapTouchListener) {
        this.f270p = onMapTouchListener;
    }

    public final void setOnMarkerClickListener(OnMarkerClickListener onMarkerClickListener) {
        if (onMarkerClickListener == null || this.f276v.contains(onMarkerClickListener)) {
            return;
        }
        this.f276v.add(onMarkerClickListener);
    }

    public final void setOnMarkerDragListener(OnMarkerDragListener onMarkerDragListener) {
        this.f278x = onMarkerDragListener;
    }

    public final void setOnMyLocationClickListener(OnMyLocationClickListener onMyLocationClickListener) {
        this.f279y = onMyLocationClickListener;
    }

    public final void setOnPolylineClickListener(OnPolylineClickListener onPolylineClickListener) {
        if (onPolylineClickListener != null) {
            this.f277w.add(onPolylineClickListener);
        }
    }

    @Deprecated
    public final void setPadding(int i, int i2, int i3, int i4) {
        C0746e c0746e;
        MapView mapView;
        if (i < 0 || i2 < 0 || i3 < 0 || i4 < 0 || (c0746e = this.f263i) == null) {
            return;
        }
        c0746e.m620D();
        int i5 = C06721.f282b[this.f259d.ordinal()];
        if (i5 != 1) {
            if (i5 == 2 && (mapView = this.f256a) != null) {
                MapStatusUpdate mapStatusUpdateNewMapStatus = MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().targetScreen(new Point(((this.f256a.getWidth() + i) - i3) / 2, ((this.f256a.getHeight() + i2) - i4) / 2)).build());
                this.f263i.m651a(new Point((int) (i + (this.f255Q.x * (((mapView.getWidth() - i) - i3) / this.f256a.getWidth()))), (int) (i2 + (this.f255Q.y * (((this.f256a.getHeight() - i2) - i4) / this.f256a.getHeight())))));
                setMapStatus(mapStatusUpdateNewMapStatus);
                this.f256a.setPadding(i, i2, i3, i4);
                this.f256a.invalidate();
                return;
            }
            return;
        }
        if (this.f257b == null) {
            return;
        }
        MapStatusUpdate mapStatusUpdateNewMapStatus2 = MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().targetScreen(new Point(((this.f257b.getWidth() + i) - i3) / 2, ((this.f257b.getHeight() + i2) - i4) / 2)).build());
        this.f263i.m651a(new Point((int) (i + (this.f255Q.x * (((r0.getWidth() - i) - i3) / this.f257b.getWidth()))), (int) (i2 + (this.f255Q.y * (((this.f257b.getHeight() - i2) - i4) / this.f257b.getHeight())))));
        setMapStatus(mapStatusUpdateNewMapStatus2);
        this.f257b.setPadding(i, i2, i3, i4);
        this.f257b.invalidate();
    }

    public final void setTrafficEnabled(boolean z) {
        C0746e c0746e = this.f263i;
        if (c0746e != null) {
            c0746e.m673f(z);
        }
    }

    public final void setViewPadding(int i, int i2, int i3, int i4) {
        MapView mapView;
        if (i < 0 || i2 < 0 || i3 < 0 || i4 < 0 || this.f263i == null) {
            return;
        }
        int i5 = C06721.f282b[this.f259d.ordinal()];
        if (i5 != 1) {
            if (i5 == 2 && (mapView = this.f256a) != null) {
                this.f263i.m651a(new Point((int) (i + (this.f255Q.x * (((mapView.getWidth() - i) - i3) / this.f256a.getWidth()))), (int) (i2 + (this.f255Q.y * (((this.f256a.getHeight() - i2) - i4) / this.f256a.getHeight())))));
                this.f256a.setPadding(i, i2, i3, i4);
                this.f256a.invalidate();
                return;
            }
            return;
        }
        if (this.f257b == null) {
            return;
        }
        this.f263i.m651a(new Point((int) (i + (this.f255Q.x * (((r0.getWidth() - i) - i3) / this.f257b.getWidth()))), (int) (i2 + (this.f255Q.y * (((this.f257b.getHeight() - i2) - i4) / this.f257b.getHeight())))));
        this.f257b.setPadding(i, i2, i3, i4);
        this.f257b.invalidate();
    }

    public void showInfoWindow(InfoWindow infoWindow) {
        if (infoWindow != null) {
            hideInfoWindow();
            if (infoWindow.f370b != null) {
                View view = infoWindow.f370b;
                this.f247I = view;
                view.destroyDrawingCache();
                MapViewLayoutParams mapViewLayoutParamsBuild = new MapViewLayoutParams.Builder().layoutMode(MapViewLayoutParams.ELayoutMode.mapMode).position(infoWindow.f371c).yOffset(infoWindow.f373e).build();
                int i = C06721.f282b[this.f259d.ordinal()];
                if (i == 1) {
                    TextureMapView textureMapView = this.f257b;
                    if (textureMapView != null) {
                        textureMapView.addView(this.f247I, mapViewLayoutParamsBuild);
                    }
                } else if (i == 2 && this.f262h != null) {
                    this.f256a.addView(this.f247I, mapViewLayoutParamsBuild);
                }
            }
            this.f245G = infoWindow;
            Overlay overlayMo238a = new MarkerOptions().perspective(false).icon(infoWindow.f370b != null ? BitmapDescriptorFactory.fromView(infoWindow.f370b) : infoWindow.f369a).position(infoWindow.f371c).zIndex(Integer.MAX_VALUE).m336a(infoWindow.f373e).mo238a();
            overlayMo238a.listener = this.f268n;
            overlayMo238a.type = EnumC0749h.popup;
            Bundle bundle = new Bundle();
            overlayMo238a.mo237a(bundle);
            C0746e c0746e = this.f263i;
            if (c0746e != null) {
                c0746e.m657b(bundle);
            }
            this.f265k.add(overlayMo238a);
            this.f246H = (Marker) overlayMo238a;
        }
    }

    public final void showMapIndoorPoi(boolean z) {
        C0746e c0746e = this.f263i;
        if (c0746e != null) {
            c0746e.m696r(z);
            this.f252N = z;
        }
    }

    public final void showMapPoi(boolean z) {
        C0746e c0746e = this.f263i;
        if (c0746e != null) {
            c0746e.m694q(z);
            this.f251M = z;
        }
    }

    public final void snapshot(SnapshotReadyCallback snapshotReadyCallback) {
        GestureDetectorOnDoubleTapListenerC0751j gestureDetectorOnDoubleTapListenerC0751j;
        this.f280z = snapshotReadyCallback;
        int i = C06721.f282b[this.f259d.ordinal()];
        if (i != 1) {
            if (i == 2 && (gestureDetectorOnDoubleTapListenerC0751j = this.f262h) != null) {
                gestureDetectorOnDoubleTapListenerC0751j.m718a("anything", null);
                return;
            }
            return;
        }
        GestureDetectorOnDoubleTapListenerC0732F gestureDetectorOnDoubleTapListenerC0732F = this.f264j;
        if (gestureDetectorOnDoubleTapListenerC0732F != null) {
            gestureDetectorOnDoubleTapListenerC0732F.m584a("anything", null);
        }
    }

    public final void snapshotScope(Rect rect, SnapshotReadyCallback snapshotReadyCallback) {
        GestureDetectorOnDoubleTapListenerC0751j gestureDetectorOnDoubleTapListenerC0751j;
        this.f280z = snapshotReadyCallback;
        int i = C06721.f282b[this.f259d.ordinal()];
        if (i != 1) {
            if (i == 2 && (gestureDetectorOnDoubleTapListenerC0751j = this.f262h) != null) {
                gestureDetectorOnDoubleTapListenerC0751j.m718a("anything", rect);
                return;
            }
            return;
        }
        GestureDetectorOnDoubleTapListenerC0732F gestureDetectorOnDoubleTapListenerC0732F = this.f264j;
        if (gestureDetectorOnDoubleTapListenerC0732F != null) {
            gestureDetectorOnDoubleTapListenerC0732F.m584a("anything", rect);
        }
    }

    public MapBaseIndoorMapInfo.SwitchFloorError switchBaseIndoorMapFloor(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return MapBaseIndoorMapInfo.SwitchFloorError.FLOOR_INFO_ERROR;
        }
        MapBaseIndoorMapInfo focusedBaseIndoorMapInfo = getFocusedBaseIndoorMapInfo();
        if (!str2.equals(focusedBaseIndoorMapInfo.f377a)) {
            return MapBaseIndoorMapInfo.SwitchFloorError.FOCUSED_ID_ERROR;
        }
        ArrayList<String> floors = focusedBaseIndoorMapInfo.getFloors();
        return (floors == null || !floors.contains(str)) ? MapBaseIndoorMapInfo.SwitchFloorError.FLOOR_OVERLFLOW : this.f263i.m654a(str, str2) ? MapBaseIndoorMapInfo.SwitchFloorError.SWITCH_OK : MapBaseIndoorMapInfo.SwitchFloorError.SWITCH_ERROR;
    }
}
