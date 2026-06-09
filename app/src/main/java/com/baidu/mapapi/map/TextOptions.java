package com.baidu.mapapi.map;

import android.graphics.Typeface;
import android.os.Bundle;
import androidx.core.view.ViewCompat;
import com.baidu.mapapi.model.LatLng;

/* JADX INFO: loaded from: classes.dex */
public final class TextOptions extends OverlayOptions {
    public static final int ALIGN_BOTTOM = 16;
    public static final int ALIGN_CENTER_HORIZONTAL = 4;
    public static final int ALIGN_CENTER_VERTICAL = 32;
    public static final int ALIGN_LEFT = 1;
    public static final int ALIGN_RIGHT = 2;
    public static final int ALIGN_TOP = 8;

    /* JADX INFO: renamed from: a */
    int f558a;

    /* JADX INFO: renamed from: c */
    Bundle f560c;

    /* JADX INFO: renamed from: d */
    private String f561d;

    /* JADX INFO: renamed from: e */
    private LatLng f562e;

    /* JADX INFO: renamed from: f */
    private int f563f;

    /* JADX INFO: renamed from: i */
    private Typeface f566i;

    /* JADX INFO: renamed from: l */
    private float f569l;

    /* JADX INFO: renamed from: g */
    private int f564g = ViewCompat.MEASURED_STATE_MASK;

    /* JADX INFO: renamed from: h */
    private int f565h = 12;

    /* JADX INFO: renamed from: j */
    private int f567j = 4;

    /* JADX INFO: renamed from: k */
    private int f568k = 32;

    /* JADX INFO: renamed from: b */
    boolean f559b = true;

    @Override // com.baidu.mapapi.map.OverlayOptions
    /* JADX INFO: renamed from: a */
    Overlay mo238a() {
        Text text = new Text();
        text.f492r = this.f559b;
        text.f491q = this.f558a;
        text.f493s = this.f560c;
        text.f548a = this.f561d;
        text.f549b = this.f562e;
        text.f550c = this.f563f;
        text.f551d = this.f564g;
        text.f552e = this.f565h;
        text.f553f = this.f566i;
        text.f554g = this.f567j;
        text.f555h = this.f568k;
        text.f556i = this.f569l;
        return text;
    }

    public TextOptions align(int i, int i2) {
        this.f567j = i;
        this.f568k = i2;
        return this;
    }

    public TextOptions bgColor(int i) {
        this.f563f = i;
        return this;
    }

    public TextOptions extraInfo(Bundle bundle) {
        this.f560c = bundle;
        return this;
    }

    public TextOptions fontColor(int i) {
        this.f564g = i;
        return this;
    }

    public TextOptions fontSize(int i) {
        this.f565h = i;
        return this;
    }

    public float getAlignX() {
        return this.f567j;
    }

    public float getAlignY() {
        return this.f568k;
    }

    public int getBgColor() {
        return this.f563f;
    }

    public Bundle getExtraInfo() {
        return this.f560c;
    }

    public int getFontColor() {
        return this.f564g;
    }

    public int getFontSize() {
        return this.f565h;
    }

    public LatLng getPosition() {
        return this.f562e;
    }

    public float getRotate() {
        return this.f569l;
    }

    public String getText() {
        return this.f561d;
    }

    public Typeface getTypeface() {
        return this.f566i;
    }

    public int getZIndex() {
        return this.f558a;
    }

    public boolean isVisible() {
        return this.f559b;
    }

    public TextOptions position(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("position can not be null");
        }
        this.f562e = latLng;
        return this;
    }

    public TextOptions rotate(float f) {
        this.f569l = f;
        return this;
    }

    public TextOptions text(String str) {
        if (str == null || str.equals("")) {
            throw new IllegalArgumentException("text can not be null or empty");
        }
        this.f561d = str;
        return this;
    }

    public TextOptions typeface(Typeface typeface) {
        this.f566i = typeface;
        return this;
    }

    public TextOptions visible(boolean z) {
        this.f559b = z;
        return this;
    }

    public TextOptions zIndex(int i) {
        this.f558a = i;
        return this;
    }
}
