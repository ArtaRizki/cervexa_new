package com.baidu.mapapi.map;

import android.graphics.Point;

/* JADX INFO: renamed from: com.baidu.mapapi.map.f */
/* JADX INFO: loaded from: classes.dex */
class C0683f {

    /* JADX INFO: renamed from: a */
    public final double f662a;

    /* JADX INFO: renamed from: b */
    public final double f663b;

    /* JADX INFO: renamed from: c */
    public final double f664c;

    /* JADX INFO: renamed from: d */
    public final double f665d;

    /* JADX INFO: renamed from: e */
    public final double f666e;

    /* JADX INFO: renamed from: f */
    public final double f667f;

    public C0683f(double d, double d2, double d3, double d4) {
        this.f662a = d;
        this.f663b = d3;
        this.f664c = d2;
        this.f665d = d4;
        this.f666e = (d + d2) / 2.0d;
        this.f667f = (d3 + d4) / 2.0d;
    }

    /* JADX INFO: renamed from: a */
    public boolean m433a(double d, double d2) {
        return this.f662a <= d && d <= this.f664c && this.f663b <= d2 && d2 <= this.f665d;
    }

    /* JADX INFO: renamed from: a */
    public boolean m434a(double d, double d2, double d3, double d4) {
        return d < this.f664c && this.f662a < d2 && d3 < this.f665d && this.f663b < d4;
    }

    /* JADX INFO: renamed from: a */
    public boolean m435a(Point point) {
        return m433a(point.x, point.y);
    }

    /* JADX INFO: renamed from: a */
    public boolean m436a(C0683f c0683f) {
        return m434a(c0683f.f662a, c0683f.f664c, c0683f.f663b, c0683f.f665d);
    }

    /* JADX INFO: renamed from: b */
    public boolean m437b(C0683f c0683f) {
        return c0683f.f662a >= this.f662a && c0683f.f664c <= this.f664c && c0683f.f663b >= this.f663b && c0683f.f665d <= this.f665d;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("minX: " + this.f662a);
        sb.append(" minY: " + this.f663b);
        sb.append(" maxX: " + this.f664c);
        sb.append(" maxY: " + this.f665d);
        sb.append(" midX: " + this.f666e);
        sb.append(" midY: " + this.f667f);
        return sb.toString();
    }
}
