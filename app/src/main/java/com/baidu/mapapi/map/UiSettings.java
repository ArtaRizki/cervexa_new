package com.baidu.mapapi.map;

import com.baidu.platform.comapi.map.C0746e;

/* JADX INFO: loaded from: classes.dex */
public final class UiSettings {

    /* JADX INFO: renamed from: a */
    private C0746e f618a;

    UiSettings(C0746e c0746e) {
        this.f618a = c0746e;
    }

    public boolean isCompassEnabled() {
        return this.f618a.m695q();
    }

    public boolean isOverlookingGesturesEnabled() {
        return this.f618a.m704y();
    }

    public boolean isRotateGesturesEnabled() {
        return this.f618a.m703x();
    }

    public boolean isScrollGesturesEnabled() {
        return this.f618a.m701v();
    }

    public boolean isZoomGesturesEnabled() {
        return this.f618a.m702w();
    }

    public void setAllGesturesEnabled(boolean z) {
        setRotateGesturesEnabled(z);
        setScrollGesturesEnabled(z);
        setOverlookingGesturesEnabled(z);
        setZoomGesturesEnabled(z);
    }

    public void setCompassEnabled(boolean z) {
        this.f618a.m677h(z);
    }

    public void setOverlookingGesturesEnabled(boolean z) {
        this.f618a.m692p(z);
    }

    public void setRotateGesturesEnabled(boolean z) {
        this.f618a.m691o(z);
    }

    public void setScrollGesturesEnabled(boolean z) {
        this.f618a.m687m(z);
    }

    public void setZoomGesturesEnabled(boolean z) {
        this.f618a.m689n(z);
    }
}
