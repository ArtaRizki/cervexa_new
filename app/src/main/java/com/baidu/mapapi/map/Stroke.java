package com.baidu.mapapi.map;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public final class Stroke {
    public final int color;
    public final int strokeWidth;

    public Stroke(int i, int i2) {
        this.strokeWidth = i <= 0 ? 5 : i;
        this.color = i2;
    }

    /* JADX INFO: renamed from: a */
    Bundle m347a(Bundle bundle) {
        bundle.putInt("width", this.strokeWidth);
        Overlay.m338a(this.color, bundle);
        return bundle;
    }
}
