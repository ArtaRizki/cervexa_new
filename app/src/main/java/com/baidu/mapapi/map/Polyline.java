package com.baidu.mapapi.map;

import android.os.Bundle;
import android.util.Log;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.EnumC0749h;
import com.jieli.lib.p015dv.control.utils.TopicKey;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class Polyline extends Overlay {

    /* JADX INFO: renamed from: a */
    int f503a;

    /* JADX INFO: renamed from: b */
    List<LatLng> f504b;

    /* JADX INFO: renamed from: c */
    int[] f505c;

    /* JADX INFO: renamed from: d */
    int[] f506d;

    /* JADX INFO: renamed from: e */
    int f507e;

    /* JADX INFO: renamed from: f */
    boolean f508f;

    /* JADX INFO: renamed from: g */
    boolean f509g = false;

    /* JADX INFO: renamed from: h */
    boolean f510h = true;

    /* JADX INFO: renamed from: i */
    BitmapDescriptor f511i;

    /* JADX INFO: renamed from: j */
    List<BitmapDescriptor> f512j;

    Polyline() {
        this.type = EnumC0749h.polyline;
    }

    /* JADX INFO: renamed from: a */
    private Bundle m343a(boolean z) {
        return (z ? BitmapDescriptorFactory.fromAsset("lineDashTexture.png") : this.f511i).m280b();
    }

    /* JADX INFO: renamed from: a */
    static void m344a(int[] iArr, Bundle bundle) {
        if (iArr == null || iArr.length <= 0) {
            return;
        }
        bundle.putIntArray("traffic_array", iArr);
    }

    /* JADX INFO: renamed from: b */
    private Bundle m345b(boolean z) {
        if (z) {
            Bundle bundle = new Bundle();
            bundle.putInt(TopicKey.TOTAL, 1);
            bundle.putBundle("texture_0", BitmapDescriptorFactory.fromAsset("lineDashTexture.png").m280b());
            return bundle;
        }
        Bundle bundle2 = new Bundle();
        int i = 0;
        for (int i2 = 0; i2 < this.f512j.size(); i2++) {
            if (this.f512j.get(i2) != null) {
                bundle2.putBundle("texture_" + String.valueOf(i), this.f512j.get(i2).m280b());
                i++;
            }
        }
        bundle2.putInt(TopicKey.TOTAL, i);
        return bundle2;
    }

    /* JADX INFO: renamed from: b */
    static void m346b(int[] iArr, Bundle bundle) {
        if (iArr == null || iArr.length <= 0) {
            return;
        }
        bundle.putIntArray("color_array", iArr);
        bundle.putInt(TopicKey.TOTAL, 1);
    }

    @Override // com.baidu.mapapi.map.Overlay
    /* JADX INFO: renamed from: a */
    Bundle mo237a(Bundle bundle) {
        super.mo237a(bundle);
        GeoPoint geoPointLl2mc = CoordUtil.ll2mc(this.f504b.get(0));
        bundle.putDouble("location_x", geoPointLl2mc.getLongitudeE6());
        bundle.putDouble("location_y", geoPointLl2mc.getLatitudeE6());
        bundle.putInt("width", this.f507e);
        Overlay.m339a(this.f504b, bundle);
        Overlay.m338a(this.f503a, bundle);
        m344a(this.f505c, bundle);
        m346b(this.f506d, bundle);
        int[] iArr = this.f505c;
        int i = 1;
        if (iArr != null && iArr.length > 0 && iArr.length > this.f504b.size() - 1) {
            Log.e("baidumapsdk", "the size of textureIndexs is larger than the size of points");
        }
        if (this.f508f) {
            bundle.putInt("dotline", 1);
        } else {
            bundle.putInt("dotline", 0);
        }
        bundle.putInt("focus", this.f509g ? 1 : 0);
        try {
            if (this.f511i != null) {
                bundle.putInt("custom", 1);
                bundle.putBundle("image_info", m343a(false));
            } else {
                if (this.f508f) {
                    bundle.putBundle("image_info", m343a(true));
                }
                bundle.putInt("custom", 0);
            }
            if (this.f512j != null) {
                bundle.putInt("customlist", 1);
                bundle.putBundle("image_info_list", m345b(false));
            } else {
                if (this.f508f && ((this.f505c != null && this.f505c.length > 0) || (this.f506d != null && this.f506d.length > 0))) {
                    bundle.putBundle("image_info_list", m345b(true));
                }
                bundle.putInt("customlist", 0);
            }
            if (!this.f510h) {
                i = 0;
            }
            bundle.putInt("keep", i);
        } catch (Exception unused) {
            Log.e("baidumapsdk", "load texture resource failed!");
            bundle.putInt("dotline", 0);
        }
        return bundle;
    }

    public int getColor() {
        return this.f503a;
    }

    public List<LatLng> getPoints() {
        return this.f504b;
    }

    public int getWidth() {
        return this.f507e;
    }

    public boolean isDottedLine() {
        return this.f508f;
    }

    public boolean isFocus() {
        return this.f509g;
    }

    public void setColor(int i) {
        this.f503a = i;
        this.listener.mo342b(this);
    }

    public void setDottedLine(boolean z) {
        this.f508f = z;
        this.listener.mo342b(this);
    }

    public void setFocus(boolean z) {
        this.f509g = z;
        this.listener.mo342b(this);
    }

    public void setPoints(List<LatLng> list) {
        if (list == null) {
            throw new IllegalArgumentException("points list can not be null");
        }
        if (list.size() < 2) {
            throw new IllegalArgumentException("points count can not less than 2 or more than 10000");
        }
        if (list.contains(null)) {
            throw new IllegalArgumentException("points list can not contains null");
        }
        this.f504b = list;
        this.listener.mo342b(this);
    }

    public void setWidth(int i) {
        if (i > 0) {
            this.f507e = i;
            this.listener.mo342b(this);
        }
    }
}
