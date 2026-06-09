package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.EnumC0749h;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class Overlay {
    protected InterfaceC0675a listener;

    /* JADX INFO: renamed from: p */
    String f490p = System.currentTimeMillis() + "_" + hashCode();

    /* JADX INFO: renamed from: q */
    int f491q;

    /* JADX INFO: renamed from: r */
    boolean f492r;

    /* JADX INFO: renamed from: s */
    Bundle f493s;
    public EnumC0749h type;

    /* JADX INFO: renamed from: com.baidu.mapapi.map.Overlay$a */
    interface InterfaceC0675a {
        /* JADX INFO: renamed from: a */
        void mo341a(Overlay overlay);

        /* JADX INFO: renamed from: b */
        void mo342b(Overlay overlay);
    }

    protected Overlay() {
    }

    /* JADX INFO: renamed from: a */
    static void m338a(int i, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putFloat("red", ((i >> 16) & 255) / 255.0f);
        bundle2.putFloat("green", ((i >> 8) & 255) / 255.0f);
        bundle2.putFloat("blue", (i & 255) / 255.0f);
        bundle2.putFloat("alpha", (i >>> 24) / 255.0f);
        bundle.putBundle("color", bundle2);
    }

    /* JADX INFO: renamed from: a */
    static void m339a(List<LatLng> list, Bundle bundle) {
        int size = list.size();
        double[] dArr = new double[size];
        double[] dArr2 = new double[size];
        for (int i = 0; i < size; i++) {
            GeoPoint geoPointLl2mc = CoordUtil.ll2mc(list.get(i));
            dArr[i] = geoPointLl2mc.getLongitudeE6();
            dArr2[i] = geoPointLl2mc.getLatitudeE6();
        }
        bundle.putDoubleArray("x_array", dArr);
        bundle.putDoubleArray("y_array", dArr2);
    }

    /* JADX INFO: renamed from: a */
    Bundle mo340a() {
        Bundle bundle = new Bundle();
        bundle.putString("id", this.f490p);
        bundle.putInt("type", this.type.ordinal());
        return bundle;
    }

    /* JADX INFO: renamed from: a */
    Bundle mo237a(Bundle bundle) {
        bundle.putString("id", this.f490p);
        bundle.putInt("type", this.type.ordinal());
        bundle.putInt("visibility", this.f492r ? 1 : 0);
        bundle.putInt("z_index", this.f491q);
        return bundle;
    }

    public Bundle getExtraInfo() {
        return this.f493s;
    }

    public int getZIndex() {
        return this.f491q;
    }

    public boolean isVisible() {
        return this.f492r;
    }

    public void remove() {
        this.listener.mo341a(this);
    }

    public void setExtraInfo(Bundle bundle) {
        this.f493s = bundle;
    }

    public void setVisible(boolean z) {
        this.f492r = z;
        this.listener.mo342b(this);
    }

    public void setZIndex(int i) {
        this.f491q = i;
        this.listener.mo342b(this);
    }
}
