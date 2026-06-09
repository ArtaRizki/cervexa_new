package com.baidu.mapapi.map;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: renamed from: com.baidu.mapapi.map.m */
/* JADX INFO: loaded from: classes.dex */
class C0690m extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ SwipeDismissTouchListener f679a;

    C0690m(SwipeDismissTouchListener swipeDismissTouchListener) {
        this.f679a = swipeDismissTouchListener;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        this.f679a.m348a();
    }
}
