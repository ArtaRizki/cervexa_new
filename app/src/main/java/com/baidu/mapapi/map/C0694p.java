package com.baidu.mapapi.map;

import android.view.View;
import com.baidu.mapapi.map.SwipeDismissTouchListener;

/* JADX INFO: renamed from: com.baidu.mapapi.map.p */
/* JADX INFO: loaded from: classes.dex */
class C0694p implements SwipeDismissTouchListener.DismissCallbacks {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ SwipeDismissView f689a;

    C0694p(SwipeDismissView swipeDismissView) {
        this.f689a = swipeDismissView;
    }

    @Override // com.baidu.mapapi.map.SwipeDismissTouchListener.DismissCallbacks
    public boolean canDismiss(Object obj) {
        return true;
    }

    @Override // com.baidu.mapapi.map.SwipeDismissTouchListener.DismissCallbacks
    public void onDismiss(View view, Object obj) {
        if (this.f689a.f546a == null) {
            return;
        }
        this.f689a.f546a.onDismiss();
    }

    @Override // com.baidu.mapapi.map.SwipeDismissTouchListener.DismissCallbacks
    public void onNotify() {
        if (this.f689a.f546a == null) {
            return;
        }
        this.f689a.f546a.onNotify();
    }
}
