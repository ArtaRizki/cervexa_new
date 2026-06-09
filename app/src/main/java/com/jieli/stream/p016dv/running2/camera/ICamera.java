package com.jieli.stream.p016dv.running2.camera;

import android.graphics.SurfaceTexture;
import android.view.SurfaceHolder;

/* JADX INFO: loaded from: classes.dex */
public interface ICamera {
    void close();

    boolean isPreviewing();

    boolean open();

    void setDisplayOrientation(int i);

    void startPreview(SurfaceTexture surfaceTexture);

    void startPreview(SurfaceHolder surfaceHolder);

    void stopPreview();
}
