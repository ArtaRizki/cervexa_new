package com.serenegiant.mediaeffect;

/* JADX INFO: loaded from: classes2.dex */
public class MediaEffectEmboss extends MediaEffectKernel {
    private float mIntensity;

    public MediaEffectEmboss() {
        this(1.0f);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MediaEffectEmboss(float f) {
        super(new float[]{(-2.0f) * f, -f, 0.0f, -f, 1.0f, f, 0.0f, f, 2.0f * f});
        this.mIntensity = f;
    }

    public MediaEffectEmboss setParameter(float f) {
        if (this.mIntensity != f) {
            this.mIntensity = f;
            float f2 = -f;
            setParameter(new float[]{(-2.0f) * f, f2, 0.0f, f2, 1.0f, f, 0.0f, f, f * 2.0f}, 0.0f);
        }
        return this;
    }
}
