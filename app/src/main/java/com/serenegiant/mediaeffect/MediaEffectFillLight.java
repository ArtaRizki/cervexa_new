package com.serenegiant.mediaeffect;

import android.media.effect.EffectContext;

/* JADX INFO: loaded from: classes2.dex */
public class MediaEffectFillLight extends MediaEffect {
    public MediaEffectFillLight(EffectContext effectContext, float f) {
        super(effectContext, "android.media.effect.effects.FillLightEffect");
        setParameter(f);
    }

    public MediaEffectFillLight setParameter(float f) {
        setParameter("strength", Float.valueOf(f));
        return this;
    }
}
