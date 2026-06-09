package com.baidu.mapapi.map;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import com.baidu.mapapi.map.WearMapView;

/* JADX INFO: loaded from: classes.dex */
public class SwipeDismissView extends RelativeLayout {

    /* JADX INFO: renamed from: a */
    WearMapView.OnDismissCallback f546a;

    public SwipeDismissView(Context context, AttributeSet attributeSet, int i, View view) {
        super(context, attributeSet, i);
        this.f546a = null;
        m353a(context, view);
    }

    public SwipeDismissView(Context context, AttributeSet attributeSet, View view) {
        super(context, attributeSet);
        this.f546a = null;
        m353a(context, view);
    }

    public SwipeDismissView(Context context, View view) {
        super(context);
        this.f546a = null;
        m353a(context, view);
    }

    /* JADX INFO: renamed from: a */
    void m353a(Context context, View view) {
        setOnTouchListener(new SwipeDismissTouchListener(view, new Object(), new C0694p(this)));
    }

    public void setCallback(WearMapView.OnDismissCallback onDismissCallback) {
        this.f546a = onDismissCallback;
    }
}
