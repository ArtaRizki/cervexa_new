package com.baidu.mapapi.map;
public class MapStatus {
    public com.baidu.mapapi.model.LatLng target;
    public float zoom;
    public static class Builder {
        public Builder target(com.baidu.mapapi.model.LatLng target) { return this; }
        public Builder zoom(float zoom) { return this; }
        public MapStatus build() { return new MapStatus(); }
    }
}
