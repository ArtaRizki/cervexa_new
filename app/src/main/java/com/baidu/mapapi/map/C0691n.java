package com.baidu.mapapi.map;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.ViewGroup;

/* JADX INFO: renamed from: com.baidu.mapapi.map.n */
/* JADX INFO: loaded from: classes.dex */
class C0691n extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ ViewGroup.LayoutParams f680a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ int f681b;

    /* JADX INFO: renamed from: c */
    final /* synthetic */ SwipeDismissTouchListener f682c;

    C0691n(SwipeDismissTouchListener swipeDismissTouchListener, ViewGroup.LayoutParams layoutParams, int i) {
        this.f682c = swipeDismissTouchListener;
        this.f680a = layoutParams;
        this.f681b = i;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        this.f682c.f535f.onDismiss(this.f682c.f534e, this.f682c.f541l);
        this.f682c.f534e.setTranslationX(0.0f);
        this.f680a.height = this.f681b;
        this.f682c.f534e.setLayoutParams(this.f680a);
    }
}
