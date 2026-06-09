package com.baidu.mapapi.map;

import android.graphics.drawable.Drawable;
import com.baidu.platform.comapi.map.EnumC0749h;

/* JADX INFO: loaded from: classes.dex */
public class ItemizedOverlay extends Overlay {

    /* JADX INFO: renamed from: a */
    MapView f374a;

    public ItemizedOverlay(Drawable drawable, MapView mapView) {
        this.type = EnumC0749h.marker;
        this.f374a = mapView;
    }

    public void addItem(OverlayOptions overlayOptions) {
        if (overlayOptions == null || overlayOptions == null) {
            return;
        }
        this.f374a.getMap().addOverlay(overlayOptions);
    }

    public void reAddAll() {
    }

    public void removeAll() {
        this.f374a.getMap().clear();
    }
}
