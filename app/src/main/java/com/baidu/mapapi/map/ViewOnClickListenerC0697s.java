package com.baidu.mapapi.map;

import android.view.View;
import com.baidu.platform.comapi.map.C0731E;
import tv.danmaku.ijk.media.player.IjkMediaCodecInfo;

/* JADX INFO: renamed from: com.baidu.mapapi.map.s */
/* JADX INFO: loaded from: classes.dex */
class ViewOnClickListenerC0697s implements View.OnClickListener {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ TextureMapView f692a;

    ViewOnClickListenerC0697s(TextureMapView textureMapView) {
        this.f692a = textureMapView;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        C0731E c0731eM620D = this.f692a.f576b.m585b().m620D();
        c0731eM620D.f845a += 1.0f;
        this.f692a.f576b.m585b().m642a(c0731eM620D, IjkMediaCodecInfo.RANK_SECURE);
    }
}
