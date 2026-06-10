package com.baidu.mapapi.map;
public class BaiduMap {
    public void setMapStatus(MapStatusUpdate status) {}
    public void setOnMapLoadedCallback(Object callback) {}
    public void clear() {}
    public Marker addOverlay(OverlayOptions options) { return new Marker(); }
    public void animateMapStatus(MapStatusUpdate status) {}
    public MapStatus getMapStatus() { return new MapStatus(); }
}
