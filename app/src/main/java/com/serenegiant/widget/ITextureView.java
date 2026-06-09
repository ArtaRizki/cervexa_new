package com.serenegiant.widget;

import android.view.TextureView;

/* JADX INFO: loaded from: classes2.dex */
public interface ITextureView {
    void register(TextureView.SurfaceTextureListener surfaceTextureListener);

    void unregister(TextureView.SurfaceTextureListener surfaceTextureListener);
}
