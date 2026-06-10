package com.baidu.mapapi.map;
import android.os.Bundle;
public class MarkerOptions extends OverlayOptions {
    public MarkerOptions position(com.baidu.mapapi.model.LatLng latLng) { return this; }
    public MarkerOptions icon(BitmapDescriptor icon) { return this; }
    public MarkerOptions zIndex(int zIndex) { return this; }
    public MarkerOptions draggable(boolean draggable) { return this; }
    public MarkerOptions extraInfo(Bundle bundle) { return this; }
    public MarkerOptions flat(boolean flat) { return this; }
    public MarkerOptions anchor(float x, float y) { return this; }
    public MarkerOptions rotate(float rotate) { return this; }
}
