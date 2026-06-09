package com.baidu.mapapi.map;

import android.view.View;
import com.baidu.platform.comapi.map.C0731E;
import tv.danmaku.ijk.media.player.IjkMediaCodecInfo;

/* JADX INFO: renamed from: com.baidu.mapapi.map.j */
/* JADX INFO: loaded from: classes.dex */
class ViewOnClickListenerC0687j implements View.OnClickListener {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ MapView f673a;

    ViewOnClickListenerC0687j(MapView mapView) {
        this.f673a = mapView;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        C0731E c0731eM620D = this.f673a.f414c.m716a().m620D();
        c0731eM620D.f845a -= 1.0f;
        this.f673a.f414c.m716a().m642a(c0731eM620D, IjkMediaCodecInfo.RANK_SECURE);
    }
}
