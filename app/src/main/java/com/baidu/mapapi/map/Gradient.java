package com.baidu.mapapi.map;

import android.graphics.Color;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class Gradient {

    /* JADX INFO: renamed from: a */
    private final int f319a;

    /* JADX INFO: renamed from: b */
    private final int[] f320b;

    /* JADX INFO: renamed from: c */
    private final float[] f321c;

    /* JADX INFO: renamed from: com.baidu.mapapi.map.Gradient$a */
    private class C0674a {

        /* JADX INFO: renamed from: b */
        private final int f323b;

        /* JADX INFO: renamed from: c */
        private final int f324c;

        /* JADX INFO: renamed from: d */
        private final float f325d;

        private C0674a(int i, int i2, float f) {
            this.f323b = i;
            this.f324c = i2;
            this.f325d = f;
        }
    }

    public Gradient(int[] iArr, float[] fArr) {
        this(iArr, fArr, 1000);
    }

    private Gradient(int[] iArr, float[] fArr, int i) {
        if (iArr == null || fArr == null) {
            throw new IllegalArgumentException("colors and startPoints should not be null");
        }
        if (iArr.length != fArr.length) {
            throw new IllegalArgumentException("colors and startPoints should be same length");
        }
        if (iArr.length == 0) {
            throw new IllegalArgumentException("No colors have been defined");
        }
        for (int i2 = 1; i2 < fArr.length; i2++) {
            if (fArr[i2] <= fArr[i2 - 1]) {
                throw new IllegalArgumentException("startPoints should be in increasing order");
            }
        }
        this.f319a = i;
        int[] iArr2 = new int[iArr.length];
        this.f320b = iArr2;
        this.f321c = new float[fArr.length];
        System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        System.arraycopy(fArr, 0, this.f321c, 0, fArr.length);
    }

    /* JADX INFO: renamed from: a */
    private static int m281a(int i, int i2, float f) {
        int iAlpha = (int) (((Color.alpha(i2) - Color.alpha(i)) * f) + Color.alpha(i));
        float[] fArr = new float[3];
        Color.RGBToHSV(Color.red(i), Color.green(i), Color.blue(i), fArr);
        float[] fArr2 = new float[3];
        Color.RGBToHSV(Color.red(i2), Color.green(i2), Color.blue(i2), fArr2);
        if (fArr[0] - fArr2[0] > 180.0f) {
            fArr2[0] = fArr2[0] + 360.0f;
        } else if (fArr2[0] - fArr[0] > 180.0f) {
            fArr[0] = fArr[0] + 360.0f;
        }
        float[] fArr3 = new float[3];
        for (int i3 = 0; i3 < 3; i3++) {
            fArr3[i3] = ((fArr2[i3] - fArr[i3]) * f) + fArr[i3];
        }
        return Color.HSVToColor(iAlpha, fArr3);
    }

    /* JADX INFO: renamed from: a */
    private HashMap<Integer, C0674a> m282a() {
        HashMap<Integer, C0674a> map = new HashMap<>();
        if (this.f321c[0] != 0.0f) {
            map.put(0, new C0674a(Color.argb(0, Color.red(this.f320b[0]), Color.green(this.f320b[0]), Color.blue(this.f320b[0])), this.f320b[0], this.f319a * this.f321c[0]));
        }
        for (int i = 1; i < this.f320b.length; i++) {
            int i2 = i - 1;
            Integer numValueOf = Integer.valueOf((int) (this.f319a * this.f321c[i2]));
            int[] iArr = this.f320b;
            int i3 = iArr[i2];
            int i4 = iArr[i];
            float f = this.f319a;
            float[] fArr = this.f321c;
            map.put(numValueOf, new C0674a(i3, i4, (fArr[i] - fArr[i2]) * f));
        }
        float[] fArr2 = this.f321c;
        if (fArr2[fArr2.length - 1] != 1.0f) {
            int length = fArr2.length - 1;
            Integer numValueOf2 = Integer.valueOf((int) (this.f319a * fArr2[length]));
            int[] iArr2 = this.f320b;
            map.put(numValueOf2, new C0674a(iArr2[length], iArr2[length], this.f319a * (1.0f - this.f321c[length])));
        }
        return map;
    }

    /* JADX INFO: renamed from: a */
    int[] m283a(double d) {
        HashMap<Integer, C0674a> mapM282a = m282a();
        int[] iArr = new int[this.f319a];
        C0674a c0674a = mapM282a.get(0);
        int i = 0;
        for (int i2 = 0; i2 < this.f319a; i2++) {
            if (mapM282a.containsKey(Integer.valueOf(i2))) {
                c0674a = mapM282a.get(Integer.valueOf(i2));
                i = i2;
            }
            iArr[i2] = m281a(c0674a.f323b, c0674a.f324c, (i2 - i) / c0674a.f325d);
        }
        if (d != 1.0d) {
            for (int i3 = 0; i3 < this.f319a; i3++) {
                int i4 = iArr[i3];
                iArr[i3] = Color.argb((int) (((double) Color.alpha(i4)) * d), Color.red(i4), Color.green(i4), Color.blue(i4));
            }
        }
        return iArr;
    }
}
