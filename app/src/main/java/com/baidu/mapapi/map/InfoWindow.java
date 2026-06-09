package com.baidu.mapapi.map;

import android.view.View;
import com.baidu.mapapi.model.LatLng;

/* JADX INFO: loaded from: classes.dex */
public class InfoWindow {

    /* JADX INFO: renamed from: a */
    BitmapDescriptor f369a;

    /* JADX INFO: renamed from: b */
    View f370b;

    /* JADX INFO: renamed from: c */
    LatLng f371c;

    /* JADX INFO: renamed from: d */
    OnInfoWindowClickListener f372d;

    /* JADX INFO: renamed from: e */
    int f373e;

    public interface OnInfoWindowClickListener {
        void onInfoWindowClick();
    }

    public InfoWindow(View view, LatLng latLng, int i) {
        if (view == null || latLng == null) {
            throw new IllegalArgumentException("view and position can not be null");
        }
        this.f370b = view;
        this.f371c = latLng;
        this.f373e = i;
    }

    public InfoWindow(BitmapDescriptor bitmapDescriptor, LatLng latLng, int i, OnInfoWindowClickListener onInfoWindowClickListener) {
        if (bitmapDescriptor == null || latLng == null) {
            throw new IllegalArgumentException("bitmapDescriptor and position can not be null");
        }
        this.f369a = bitmapDescriptor;
        this.f371c = latLng;
        this.f372d = onInfoWindowClickListener;
        this.f373e = i;
    }
}
