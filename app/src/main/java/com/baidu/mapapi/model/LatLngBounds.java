package com.baidu.mapapi.model;
public class LatLngBounds {
    public static class Builder {
        public Builder include(LatLng point) { return this; }
        public LatLngBounds build() { return new LatLngBounds(); }
    }
}
