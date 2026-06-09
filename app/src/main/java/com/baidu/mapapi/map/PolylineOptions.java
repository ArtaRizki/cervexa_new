package com.baidu.mapapi.map;

import android.os.Bundle;
import android.util.Log;
import androidx.core.view.ViewCompat;
import com.baidu.mapapi.model.LatLng;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class PolylineOptions extends OverlayOptions {

    /* JADX INFO: renamed from: a */
    int f513a;

    /* JADX INFO: renamed from: d */
    Bundle f516d;

    /* JADX INFO: renamed from: f */
    private List<LatLng> f518f;

    /* JADX INFO: renamed from: g */
    private List<Integer> f519g;

    /* JADX INFO: renamed from: h */
    private List<Integer> f520h;

    /* JADX INFO: renamed from: j */
    private BitmapDescriptor f522j;

    /* JADX INFO: renamed from: k */
    private List<BitmapDescriptor> f523k;

    /* JADX INFO: renamed from: e */
    private int f517e = ViewCompat.MEASURED_STATE_MASK;

    /* JADX INFO: renamed from: i */
    private int f521i = 5;

    /* JADX INFO: renamed from: l */
    private boolean f524l = true;

    /* JADX INFO: renamed from: m */
    private boolean f525m = false;

    /* JADX INFO: renamed from: b */
    boolean f514b = true;

    /* JADX INFO: renamed from: c */
    boolean f515c = false;

    @Override // com.baidu.mapapi.map.OverlayOptions
    /* JADX INFO: renamed from: a */
    Overlay mo238a() {
        Polyline polyline = new Polyline();
        polyline.f492r = this.f514b;
        polyline.f508f = this.f515c;
        polyline.f491q = this.f513a;
        polyline.f493s = this.f516d;
        List<LatLng> list = this.f518f;
        if (list == null || list.size() < 2) {
            throw new IllegalStateException("when you add polyline, you must at least supply 2 points");
        }
        polyline.f504b = this.f518f;
        polyline.f503a = this.f517e;
        polyline.f507e = this.f521i;
        polyline.f511i = this.f522j;
        polyline.f512j = this.f523k;
        polyline.f509g = this.f524l;
        polyline.f510h = this.f525m;
        List<Integer> list2 = this.f519g;
        if (list2 != null && list2.size() < this.f518f.size() - 1) {
            ArrayList arrayList = new ArrayList((this.f518f.size() - 1) - this.f519g.size());
            List<Integer> list3 = this.f519g;
            list3.addAll(list3.size(), arrayList);
        }
        List<Integer> list4 = this.f519g;
        int i = 0;
        if (list4 != null && list4.size() > 0) {
            int[] iArr = new int[this.f519g.size()];
            Iterator<Integer> it = this.f519g.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                iArr[i2] = it.next().intValue();
                i2++;
            }
            polyline.f505c = iArr;
        }
        List<Integer> list5 = this.f520h;
        if (list5 != null && list5.size() < this.f518f.size() - 1) {
            ArrayList arrayList2 = new ArrayList((this.f518f.size() - 1) - this.f520h.size());
            List<Integer> list6 = this.f520h;
            list6.addAll(list6.size(), arrayList2);
        }
        List<Integer> list7 = this.f520h;
        if (list7 != null && list7.size() > 0) {
            int[] iArr2 = new int[this.f520h.size()];
            Iterator<Integer> it2 = this.f520h.iterator();
            while (it2.hasNext()) {
                iArr2[i] = it2.next().intValue();
                i++;
            }
            polyline.f506d = iArr2;
        }
        return polyline;
    }

    public PolylineOptions color(int i) {
        this.f517e = i;
        return this;
    }

    public PolylineOptions colorsValues(List<Integer> list) {
        if (list == null) {
            throw new IllegalArgumentException("colors list can not be null");
        }
        if (list.contains(null)) {
            throw new IllegalArgumentException("colors list can not contains null");
        }
        this.f520h = list;
        return this;
    }

    public PolylineOptions customTexture(BitmapDescriptor bitmapDescriptor) {
        this.f522j = bitmapDescriptor;
        return this;
    }

    public PolylineOptions customTextureList(List<BitmapDescriptor> list) {
        if (list == null) {
            throw new IllegalArgumentException("customTexture list can not be null");
        }
        if (list.size() == 0) {
            Log.e("baidumapsdk", "custom texture list is empty,the texture will not work");
        }
        Iterator<BitmapDescriptor> it = list.iterator();
        while (it.hasNext()) {
            if (it.next() == null) {
                Log.e("baidumapsdk", "the custom texture item is null,it will be discard");
            }
        }
        this.f523k = list;
        return this;
    }

    public PolylineOptions dottedLine(boolean z) {
        this.f515c = z;
        return this;
    }

    public PolylineOptions extraInfo(Bundle bundle) {
        this.f516d = bundle;
        return this;
    }

    public PolylineOptions focus(boolean z) {
        this.f524l = z;
        return this;
    }

    public int getColor() {
        return this.f517e;
    }

    public BitmapDescriptor getCustomTexture() {
        return this.f522j;
    }

    public List<BitmapDescriptor> getCustomTextureList() {
        return this.f523k;
    }

    public Bundle getExtraInfo() {
        return this.f516d;
    }

    public List<LatLng> getPoints() {
        return this.f518f;
    }

    public List<Integer> getTextureIndexs() {
        return this.f519g;
    }

    public int getWidth() {
        return this.f521i;
    }

    public int getZIndex() {
        return this.f513a;
    }

    public boolean isDottedLine() {
        return this.f515c;
    }

    public boolean isFocus() {
        return this.f524l;
    }

    public boolean isVisible() {
        return this.f514b;
    }

    public PolylineOptions keepScale(boolean z) {
        this.f525m = z;
        return this;
    }

    public PolylineOptions points(List<LatLng> list) {
        if (list == null) {
            throw new IllegalArgumentException("points list can not be null");
        }
        if (list.size() < 2) {
            throw new IllegalArgumentException("points count can not less than 2");
        }
        if (list.contains(null)) {
            throw new IllegalArgumentException("points list can not contains null");
        }
        this.f518f = list;
        return this;
    }

    public PolylineOptions textureIndex(List<Integer> list) {
        if (list == null) {
            throw new IllegalArgumentException("indexs list can not be null");
        }
        if (list.contains(null)) {
            throw new IllegalArgumentException("index list can not contains null");
        }
        this.f519g = list;
        return this;
    }

    public PolylineOptions visible(boolean z) {
        this.f514b = z;
        return this;
    }

    public PolylineOptions width(int i) {
        if (i > 0) {
            this.f521i = i;
        }
        return this;
    }

    public PolylineOptions zIndex(int i) {
        this.f513a = i;
        return this;
    }
}
