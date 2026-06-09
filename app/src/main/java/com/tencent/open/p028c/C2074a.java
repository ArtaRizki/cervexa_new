package com.tencent.open.p028c;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.widget.RelativeLayout;

/* JADX INFO: renamed from: com.tencent.open.c.a */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class C2074a extends RelativeLayout {

    /* JADX INFO: renamed from: a */
    private static final String f3241a = C2074a.class.getName();

    /* JADX INFO: renamed from: b */
    private Rect f3242b;

    /* JADX INFO: renamed from: c */
    private boolean f3243c;

    /* JADX INFO: renamed from: d */
    private a f3244d;

    /* JADX INFO: renamed from: com.tencent.open.c.a$a */
    /* JADX INFO: compiled from: ProGuard */
    public interface a {
        /* JADX INFO: renamed from: a */
        void mo2193a();

        /* JADX INFO: renamed from: a */
        void mo2194a(int i);
    }

    public C2074a(Context context) {
        super(context);
        this.f3242b = null;
        this.f3243c = false;
        this.f3244d = null;
        if (0 == 0) {
            this.f3242b = new Rect();
        }
    }

    /* JADX INFO: renamed from: a */
    public void m2197a(a aVar) {
        this.f3244d = aVar;
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i2);
        Activity activity = (Activity) getContext();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(this.f3242b);
        int height = (activity.getWindowManager().getDefaultDisplay().getHeight() - this.f3242b.top) - size;
        a aVar = this.f3244d;
        if (aVar != null && size != 0) {
            if (height > 100) {
                aVar.mo2194a((Math.abs(this.f3242b.height()) - getPaddingBottom()) - getPaddingTop());
            } else {
                aVar.mo2193a();
            }
        }
        super.onMeasure(i, i2);
    }
}
