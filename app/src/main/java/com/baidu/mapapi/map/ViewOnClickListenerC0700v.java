package com.baidu.mapapi.map;

import android.view.View;
import com.baidu.platform.comapi.map.C0731E;
import tv.danmaku.ijk.media.player.IjkMediaCodecInfo;

/* JADX INFO: renamed from: com.baidu.mapapi.map.v */
/* JADX INFO: loaded from: classes.dex */
class ViewOnClickListenerC0700v implements View.OnClickListener {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ WearMapView f699a;

    ViewOnClickListenerC0700v(WearMapView wearMapView) {
        this.f699a = wearMapView;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        C0731E c0731eM620D = this.f699a.f631d.m716a().m620D();
        c0731eM620D.f845a -= 1.0f;
        this.f699a.f631d.m716a().m642a(c0731eM620D, IjkMediaCodecInfo.RANK_SECURE);
    }
}
