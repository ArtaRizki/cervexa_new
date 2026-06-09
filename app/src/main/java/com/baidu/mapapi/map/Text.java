package com.baidu.mapapi.map;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.EnumC0749h;
import vi.com.gdi.bgl.android.java.EnvDrawText;

/* JADX INFO: loaded from: classes.dex */
public final class Text extends Overlay {

    /* JADX INFO: renamed from: k */
    private static final String f547k = Text.class.getSimpleName();

    /* JADX INFO: renamed from: a */
    String f548a;

    /* JADX INFO: renamed from: b */
    LatLng f549b;

    /* JADX INFO: renamed from: c */
    int f550c;

    /* JADX INFO: renamed from: d */
    int f551d;

    /* JADX INFO: renamed from: e */
    int f552e;

    /* JADX INFO: renamed from: f */
    Typeface f553f;

    /* JADX INFO: renamed from: g */
    int f554g;

    /* JADX INFO: renamed from: h */
    int f555h;

    /* JADX INFO: renamed from: i */
    float f556i;

    /* JADX INFO: renamed from: j */
    int f557j;

    Text() {
        this.type = EnumC0749h.text;
    }

    @Override // com.baidu.mapapi.map.Overlay
    /* JADX INFO: renamed from: a */
    Bundle mo340a() {
        Typeface typeface = this.f553f;
        if (typeface != null) {
            EnvDrawText.removeFontCache(typeface.hashCode());
        }
        return super.mo340a();
    }

    @Override // com.baidu.mapapi.map.Overlay
    /* JADX INFO: renamed from: a */
    Bundle mo237a(Bundle bundle) {
        super.mo237a(bundle);
        if (this.f549b == null) {
            throw new IllegalStateException("when you add a text overlay, you must provide text and the position info.");
        }
        bundle.putString("text", this.f548a);
        GeoPoint geoPointLl2mc = CoordUtil.ll2mc(this.f549b);
        bundle.putDouble("location_x", geoPointLl2mc.getLongitudeE6());
        bundle.putDouble("location_y", geoPointLl2mc.getLatitudeE6());
        int i = this.f551d;
        bundle.putInt("font_color", Color.argb(i >>> 24, i & 255, (i >> 8) & 255, (i >> 16) & 255));
        int i2 = this.f550c;
        bundle.putInt("bg_color", Color.argb(i2 >>> 24, i2 & 255, (i2 >> 8) & 255, (i2 >> 16) & 255));
        bundle.putInt("font_size", this.f552e);
        Typeface typeface = this.f553f;
        if (typeface != null) {
            EnvDrawText.registFontCache(typeface.hashCode(), this.f553f);
            bundle.putInt("type_face", this.f553f.hashCode());
        }
        int i3 = this.f554g;
        float f = 1.0f;
        bundle.putFloat("align_x", i3 != 1 ? i3 != 2 ? 0.5f : 1.0f : 0.0f);
        int i4 = this.f555h;
        if (i4 == 8) {
            f = 0.0f;
        } else if (i4 != 16) {
            f = 0.5f;
        }
        bundle.putFloat("align_y", f);
        bundle.putFloat("rotate", this.f556i);
        bundle.putInt("update", this.f557j);
        return bundle;
    }

    public float getAlignX() {
        return this.f554g;
    }

    public float getAlignY() {
        return this.f555h;
    }

    public int getBgColor() {
        return this.f550c;
    }

    public int getFontColor() {
        return this.f551d;
    }

    public int getFontSize() {
        return this.f552e;
    }

    public LatLng getPosition() {
        return this.f549b;
    }

    public float getRotate() {
        return this.f556i;
    }

    public String getText() {
        return this.f548a;
    }

    public Typeface getTypeface() {
        return this.f553f;
    }

    public void setAlign(int i, int i2) {
        this.f554g = i;
        this.f555h = i2;
        this.f557j = 1;
        this.listener.mo342b(this);
    }

    public void setBgColor(int i) {
        this.f550c = i;
        this.f557j = 1;
        this.listener.mo342b(this);
    }

    public void setFontColor(int i) {
        this.f551d = i;
        this.f557j = 1;
        this.listener.mo342b(this);
    }

    public void setFontSize(int i) {
        this.f552e = i;
        this.f557j = 1;
        this.listener.mo342b(this);
    }

    public void setPosition(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("position can not be null");
        }
        this.f549b = latLng;
        this.f557j = 1;
        this.listener.mo342b(this);
    }

    public void setRotate(float f) {
        this.f556i = f;
        this.f557j = 1;
        this.listener.mo342b(this);
    }

    public void setText(String str) {
        if (str == null || str.equals("")) {
            throw new IllegalArgumentException("text can not be null or empty");
        }
        this.f548a = str;
        this.f557j = 1;
        this.listener.mo342b(this);
    }

    public void setTypeface(Typeface typeface) {
        this.f553f = typeface;
        this.f557j = 1;
        this.listener.mo342b(this);
    }
}
