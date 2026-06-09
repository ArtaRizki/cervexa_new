package com.serenegiant.media;

/* JADX INFO: loaded from: classes2.dex */
public interface IVideoEncoder extends Encoder {
    int getHeight();

    int getWidth();

    void setVideoSize(int i, int i2) throws IllegalStateException, IllegalArgumentException;
}
