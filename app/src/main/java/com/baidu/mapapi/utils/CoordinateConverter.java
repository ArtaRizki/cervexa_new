package com.baidu.mapapi.utils;
import com.baidu.mapapi.model.LatLng;
public class CoordinateConverter {
    public enum CoordType { GPS }
    public CoordinateConverter from(CoordType type) { return this; }
    public CoordinateConverter coord(LatLng latLng) { return this; }
    public LatLng convert() { return new LatLng(0,0); }
}
