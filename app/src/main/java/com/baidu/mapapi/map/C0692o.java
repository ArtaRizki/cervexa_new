package com.baidu.mapapi.map;

import android.animation.ValueAnimator;
import android.view.ViewGroup;

/* JADX INFO: renamed from: com.baidu.mapapi.map.o */
/* JADX INFO: loaded from: classes.dex */
class C0692o implements ValueAnimator.AnimatorUpdateListener {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ ViewGroup.LayoutParams f683a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ SwipeDismissTouchListener f684b;

    C0692o(SwipeDismissTouchListener swipeDismissTouchListener, ViewGroup.LayoutParams layoutParams) {
        this.f684b = swipeDismissTouchListener;
        this.f683a = layoutParams;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f683a.height = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        this.f684b.f534e.setLayoutParams(this.f683a);
    }
}
