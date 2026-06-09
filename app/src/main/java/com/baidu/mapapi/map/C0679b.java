package com.baidu.mapapi.map;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.view.MotionEvent;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.C0731E;
import com.baidu.platform.comapi.map.InterfaceC0753l;
import java.util.Iterator;
import javax.microedition.khronos.opengles.GL10;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaCodecInfo;

/* JADX INFO: renamed from: com.baidu.mapapi.map.b */
/* JADX INFO: loaded from: classes.dex */
class C0679b implements InterfaceC0753l {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ BaiduMap f659a;

    C0679b(BaiduMap baiduMap) {
        this.f659a = baiduMap;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: a */
    public void mo409a() {
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: a */
    public void mo410a(Bitmap bitmap) {
        if (this.f659a.f280z != null) {
            this.f659a.f280z.onSnapshotReady(bitmap);
        }
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: a */
    public void mo411a(MotionEvent motionEvent) {
        if (this.f659a.f270p != null) {
            this.f659a.f270p.onTouch(motionEvent);
        }
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: a */
    public void mo412a(GeoPoint geoPoint) {
        if (this.f659a.f271q != null) {
            this.f659a.f271q.onMapClick(CoordUtil.mc2ll(geoPoint));
        }
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: a */
    public void mo413a(C0731E c0731e) {
        if (this.f659a.f247I != null) {
            this.f659a.f247I.setVisibility(4);
        }
        if (this.f659a.f269o != null) {
            this.f659a.f269o.onMapStatusChangeStart(MapStatus.m314a(c0731e));
        }
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: a */
    public void mo414a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONArray("dataset").optJSONObject(0);
            GeoPoint geoPointM655b = this.f659a.f263i.m655b(jSONObject.optInt("px"), jSONObject.optInt("py"));
            int iOptInt = jSONObjectOptJSONObject.optInt("ty");
            if (iOptInt == 17) {
                if (this.f659a.f271q != null) {
                    MapPoi mapPoi = new MapPoi();
                    mapPoi.m313a(jSONObjectOptJSONObject);
                    this.f659a.f271q.onMapPoiClick(mapPoi);
                    return;
                }
                return;
            }
            if (iOptInt != 18) {
                if (iOptInt == 19) {
                    if (this.f659a.f263i != null) {
                        C0731E c0731eM620D = this.f659a.f263i.m620D();
                        c0731eM620D.f847c = 0;
                        c0731eM620D.f846b = 0;
                        this.f659a.f263i.m642a(c0731eM620D, IjkMediaCodecInfo.RANK_SECURE);
                        return;
                    }
                    return;
                }
                if (iOptInt != 90909) {
                    if (iOptInt == 90910) {
                        String strOptString = jSONObjectOptJSONObject.optString("polyline_id");
                        for (Overlay overlay : this.f659a.f265k) {
                            if ((overlay instanceof Polyline) && overlay.f490p.equals(strOptString)) {
                                if (this.f659a.f277w.isEmpty()) {
                                    mo412a(geoPointM655b);
                                } else {
                                    Iterator it = this.f659a.f277w.iterator();
                                    while (it.hasNext()) {
                                        ((BaiduMap.OnPolylineClickListener) it.next()).onPolylineClick((Polyline) overlay);
                                    }
                                }
                            }
                        }
                        return;
                    }
                    return;
                }
                String strOptString2 = jSONObjectOptJSONObject.optString("marker_id");
                if (this.f659a.f245G == null || !strOptString2.equals(this.f659a.f246H.f490p)) {
                    for (Overlay overlay2 : this.f659a.f265k) {
                        if ((overlay2 instanceof Marker) && overlay2.f490p.equals(strOptString2)) {
                            if (!this.f659a.f276v.isEmpty()) {
                                Iterator it2 = this.f659a.f276v.iterator();
                                while (it2.hasNext()) {
                                    ((BaiduMap.OnMarkerClickListener) it2.next()).onMarkerClick((Marker) overlay2);
                                }
                                return;
                            }
                            mo412a(geoPointM655b);
                        }
                    }
                    return;
                }
                InfoWindow.OnInfoWindowClickListener onInfoWindowClickListener = this.f659a.f245G.f372d;
                if (onInfoWindowClickListener != null) {
                    onInfoWindowClickListener.onInfoWindowClick();
                    return;
                }
            } else if (this.f659a.f279y != null) {
                this.f659a.f279y.onMyLocationClick();
                return;
            }
            mo412a(geoPointM655b);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: a */
    public void mo415a(GL10 gl10, C0731E c0731e) {
        if (this.f659a.f239A != null) {
            this.f659a.f239A.onMapDrawFrame(MapStatus.m314a(c0731e));
        }
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: a */
    public void mo416a(boolean z) {
        if (this.f659a.f240B != null) {
            this.f659a.f240B.onBaseIndoorMapMode(z, this.f659a.getFocusedBaseIndoorMapInfo());
        }
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: b */
    public void mo417b() {
        BaiduMap baiduMap = this.f659a;
        baiduMap.f260f = new Projection(baiduMap.f263i);
        this.f659a.f254P = true;
        if (this.f659a.f272r != null) {
            this.f659a.f272r.onMapLoaded();
        }
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: b */
    public void mo418b(GeoPoint geoPoint) {
        if (this.f659a.f274t != null) {
            this.f659a.f274t.onMapDoubleClick(CoordUtil.mc2ll(geoPoint));
        }
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: b */
    public void mo419b(C0731E c0731e) {
        if (this.f659a.f269o != null) {
            this.f659a.f269o.onMapStatusChange(MapStatus.m314a(c0731e));
        }
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: b */
    public boolean mo420b(String str) {
        try {
            JSONObject jSONObjectOptJSONObject = new JSONObject(str).optJSONArray("dataset").optJSONObject(0);
            if (jSONObjectOptJSONObject.optInt("ty") != 90909) {
                return false;
            }
            String strOptString = jSONObjectOptJSONObject.optString("marker_id");
            if (this.f659a.f246H != null && strOptString.equals(this.f659a.f246H.f490p)) {
                return false;
            }
            for (Overlay overlay : this.f659a.f265k) {
                if ((overlay instanceof Marker) && overlay.f490p.equals(strOptString)) {
                    Marker marker = (Marker) overlay;
                    if (!marker.f455f) {
                        return false;
                    }
                    this.f659a.f248J = marker;
                    this.f659a.f248J.setPosition(this.f659a.f260f.fromScreenLocation(new Point(this.f659a.f260f.toScreenLocation(this.f659a.f248J.f450a).x, r5.y - 60)));
                    if (this.f659a.f278x != null) {
                        this.f659a.f278x.onMarkerDragStart(this.f659a.f248J);
                    }
                    return true;
                }
            }
            return false;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: c */
    public void mo421c() {
        if (this.f659a.f273s != null) {
            this.f659a.f273s.onMapRenderFinished();
        }
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: c */
    public void mo422c(GeoPoint geoPoint) {
        if (this.f659a.f275u != null) {
            this.f659a.f275u.onMapLongClick(CoordUtil.mc2ll(geoPoint));
        }
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: c */
    public void mo423c(C0731E c0731e) {
        if (this.f659a.f247I != null) {
            this.f659a.f247I.setVisibility(0);
        }
        if (this.f659a.f269o != null) {
            this.f659a.f269o.onMapStatusChangeFinish(MapStatus.m314a(c0731e));
        }
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: d */
    public void mo424d() {
        this.f659a.f243E.lock();
        try {
            if (this.f659a.f242D != null) {
                this.f659a.f242D.m306a();
            }
        } finally {
            this.f659a.f243E.unlock();
        }
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: d */
    public void mo425d(GeoPoint geoPoint) {
        if (this.f659a.f248J == null || !this.f659a.f248J.f455f) {
            return;
        }
        this.f659a.f248J.setPosition(this.f659a.f260f.fromScreenLocation(new Point(this.f659a.f260f.toScreenLocation(CoordUtil.mc2ll(geoPoint)).x, r3.y - 60)));
        if (this.f659a.f278x == null || !this.f659a.f248J.f455f) {
            return;
        }
        this.f659a.f278x.onMarkerDrag(this.f659a.f248J);
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: e */
    public void mo426e() {
        this.f659a.f243E.lock();
        try {
            if (this.f659a.f242D != null) {
                this.f659a.f242D.m306a();
                this.f659a.f263i.m688n();
            }
        } finally {
            this.f659a.f243E.unlock();
        }
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: e */
    public void mo427e(GeoPoint geoPoint) {
        if (this.f659a.f248J == null || !this.f659a.f248J.f455f) {
            return;
        }
        this.f659a.f248J.setPosition(this.f659a.f260f.fromScreenLocation(new Point(this.f659a.f260f.toScreenLocation(CoordUtil.mc2ll(geoPoint)).x, r3.y - 60)));
        if (this.f659a.f278x != null && this.f659a.f248J.f455f) {
            this.f659a.f278x.onMarkerDragEnd(this.f659a.f248J);
        }
        this.f659a.f248J = null;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: f */
    public void mo428f() {
        this.f659a.f263i.m661b(false);
        this.f659a.f243E.lock();
        try {
            if (this.f659a.f242D != null) {
                this.f659a.m274a(this.f659a.f242D);
            }
        } finally {
            this.f659a.f243E.unlock();
        }
    }
}
