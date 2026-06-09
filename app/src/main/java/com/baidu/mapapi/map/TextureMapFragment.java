package com.baidu.mapapi.map;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public class TextureMapFragment extends Fragment {

    /* JADX INFO: renamed from: a */
    private static final String f570a = TextureMapFragment.class.getSimpleName();

    /* JADX INFO: renamed from: b */
    private TextureMapView f571b;

    /* JADX INFO: renamed from: c */
    private BaiduMapOptions f572c;

    public TextureMapFragment() {
    }

    private TextureMapFragment(BaiduMapOptions baiduMapOptions) {
        this.f572c = baiduMapOptions;
    }

    public static TextureMapFragment newInstance() {
        return new TextureMapFragment();
    }

    public static TextureMapFragment newInstance(BaiduMapOptions baiduMapOptions) {
        return new TextureMapFragment(baiduMapOptions);
    }

    public BaiduMap getBaiduMap() {
        TextureMapView textureMapView = this.f571b;
        if (textureMapView == null) {
            return null;
        }
        return textureMapView.getMap();
    }

    public TextureMapView getMapView() {
        return this.f571b;
    }

    @Override // android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    @Override // android.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override // android.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        TextureMapView textureMapView = new TextureMapView(getActivity(), this.f572c);
        this.f571b = textureMapView;
        return textureMapView;
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.f571b.onDestroy();
    }

    @Override // android.app.Fragment
    public void onDetach() {
        super.onDetach();
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        this.f571b.onPause();
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        this.f571b.onResume();
    }

    @Override // android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    @Override // android.app.Fragment
    public void onStart() {
        super.onStart();
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
    }
}
