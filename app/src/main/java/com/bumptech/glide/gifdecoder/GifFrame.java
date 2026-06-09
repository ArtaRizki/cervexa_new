package com.bumptech.glide.gifdecoder;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
class GifFrame {
    public static final int DISPOSAL_BACKGROUND = 2;
    public static final int DISPOSAL_NONE = 1;
    public static final int DISPOSAL_PREVIOUS = 3;
    public static final int DISPOSAL_UNSPECIFIED = 0;
    int bufferFrameStart;
    int delay;
    int dispose;

    /* JADX INFO: renamed from: ih */
    int f1908ih;
    boolean interlace;

    /* JADX INFO: renamed from: iw */
    int f1909iw;

    /* JADX INFO: renamed from: ix */
    int f1910ix;

    /* JADX INFO: renamed from: iy */
    int f1911iy;
    int[] lct;
    int transIndex;
    boolean transparency;

    @Retention(RetentionPolicy.SOURCE)
    @interface GifDisposalMethod {
    }

    GifFrame() {
    }
}
