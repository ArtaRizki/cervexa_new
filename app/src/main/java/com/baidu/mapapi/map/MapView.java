package com.baidu.mapapi.map;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
public class MapView extends FrameLayout {
    public MapView(Context context) { super(context); }
    public MapView(Context context, AttributeSet attrs) { super(context, attrs); }
    public MapView(Context context, AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); }
    public BaiduMap getMap() { return new BaiduMap(); }
    public void showZoomControls(boolean show) {}
    public void onPause() {}
    public void onResume() {}
    public void onDestroy() {}
}
