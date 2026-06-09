package com.baidu.mapapi.map;

import android.view.View;
import com.baidu.platform.comapi.map.C0731E;
import tv.danmaku.ijk.media.player.IjkMediaCodecInfo;

/* JADX INFO: renamed from: com.baidu.mapapi.map.r */
/* JADX INFO: loaded from: classes.dex */
class ViewOnClickListenerC0696r implements View.OnClickListener {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ TextureMapView f691a;

    ViewOnClickListenerC0696r(TextureMapView textureMapView) {
        this.f691a = textureMapView;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        C0731E c0731eM620D = this.f691a.f576b.m585b().m620D();
        c0731eM620D.f845a -= 1.0f;
        this.f691a.f576b.m585b().m642a(c0731eM620D, IjkMediaCodecInfo.RANK_SECURE);
    }
}
