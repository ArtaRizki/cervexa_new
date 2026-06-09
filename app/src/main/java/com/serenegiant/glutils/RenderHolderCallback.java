package com.serenegiant.glutils;

import android.view.Surface;

/* JADX INFO: loaded from: classes.dex */
public interface RenderHolderCallback {
    void onCreate(Surface surface);

    void onDestroy();

    void onFrameAvailable();
}
