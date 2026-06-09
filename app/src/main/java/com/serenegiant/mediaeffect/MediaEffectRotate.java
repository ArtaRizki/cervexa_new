package com.serenegiant.mediaeffect;

import android.media.effect.EffectContext;

/* JADX INFO: loaded from: classes2.dex */
public class MediaEffectRotate extends MediaEffect {
    public MediaEffectRotate(EffectContext effectContext, int i) {
        super(effectContext, "android.media.effect.effects.RotateEffect");
        setParameter(i);
    }

    public MediaEffectRotate setParameter(int i) {
        setParameter("angle", Integer.valueOf(i));
        return this;
    }
}
