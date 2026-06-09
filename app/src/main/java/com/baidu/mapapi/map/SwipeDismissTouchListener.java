package com.baidu.mapapi.map;

import android.R;
import android.animation.ValueAnimator;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public class SwipeDismissTouchListener implements View.OnTouchListener {

    /* JADX INFO: renamed from: a */
    private int f530a;

    /* JADX INFO: renamed from: b */
    private int f531b;

    /* JADX INFO: renamed from: c */
    private int f532c;

    /* JADX INFO: renamed from: d */
    private long f533d;

    /* JADX INFO: renamed from: e */
    private View f534e;

    /* JADX INFO: renamed from: f */
    private DismissCallbacks f535f;

    /* JADX INFO: renamed from: g */
    private int f536g = 1;

    /* JADX INFO: renamed from: h */
    private float f537h;

    /* JADX INFO: renamed from: i */
    private float f538i;

    /* JADX INFO: renamed from: j */
    private boolean f539j;

    /* JADX INFO: renamed from: k */
    private int f540k;

    /* JADX INFO: renamed from: l */
    private Object f541l;

    /* JADX INFO: renamed from: m */
    private VelocityTracker f542m;

    /* JADX INFO: renamed from: n */
    private float f543n;

    /* JADX INFO: renamed from: o */
    private boolean f544o;

    /* JADX INFO: renamed from: p */
    private boolean f545p;

    public interface DismissCallbacks {
        boolean canDismiss(Object obj);

        void onDismiss(View view, Object obj);

        void onNotify();
    }

    public SwipeDismissTouchListener(View view, Object obj, DismissCallbacks dismissCallbacks) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(view.getContext());
        this.f530a = viewConfiguration.getScaledTouchSlop();
        this.f531b = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f532c = viewConfiguration.getScaledMaximumFlingVelocity();
        this.f533d = view.getContext().getResources().getInteger(R.integer.config_shortAnimTime);
        this.f534e = view;
        view.getContext();
        this.f541l = obj;
        this.f535f = dismissCallbacks;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public void m348a() {
        ViewGroup.LayoutParams layoutParams = this.f534e.getLayoutParams();
        int height = this.f534e.getHeight();
        ValueAnimator duration = ValueAnimator.ofInt(height, 1).setDuration(this.f533d);
        duration.addListener(new C0691n(this, layoutParams, height));
        duration.addUpdateListener(new C0692o(this, layoutParams));
        duration.start();
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0027, code lost:
    
        if (r10.f542m == null) goto L88;
     */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouch(android.view.View r11, android.view.MotionEvent r12) {
        /*
            Method dump skipped, instruction units count: 434
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapapi.map.SwipeDismissTouchListener.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }
}
