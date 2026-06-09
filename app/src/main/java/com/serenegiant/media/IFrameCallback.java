package com.serenegiant.media;

/* JADX INFO: loaded from: classes2.dex */
public interface IFrameCallback {
    void onFinished();

    boolean onFrameAvailable(long j);

    void onPrepared();
}
