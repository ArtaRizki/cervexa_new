package com.baidu.mapapi.map;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

/* JADX INFO: renamed from: com.baidu.mapapi.map.x */
/* JADX INFO: loaded from: classes.dex */
class C0702x extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ View f701a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ WearMapView f702b;

    C0702x(WearMapView wearMapView, View view) {
        this.f702b = wearMapView;
        this.f701a = view;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        this.f701a.setVisibility(4);
        super.onAnimationEnd(animator);
    }
}
