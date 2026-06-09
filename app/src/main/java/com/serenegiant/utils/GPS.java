package com.serenegiant.utils;

/* JADX INFO: loaded from: classes2.dex */
public class GPS {
    public static final Datum BESSEL = new Datum(6377397.155d, 6356079.0d, 299.152813d, true);
    public static final Datum WGS84 = new Datum(6378137.0d, 6356752.314245d, 298.257223563d, true);
    public static final Datum GRS80 = new Datum(6378137.0d, 6356752.31414d, 298.257222101d, true);

    public static class Datum {

        /* JADX INFO: renamed from: a */
        public final double f2257a;
        public final double ae2;

        /* JADX INFO: renamed from: b */
        public final double f2258b;

        /* JADX INFO: renamed from: e2 */
        public final double f2259e2;
        public final double inv_ellipticity;

        public Datum(double d, double d2, double d3, boolean z) {
            this.f2257a = d;
            this.f2258b = d2;
            this.inv_ellipticity = z ? d3 : 1.0d / d3;
            double d4 = d * d;
            double d5 = (d4 - (d2 * d2)) / d4;
            this.f2259e2 = d5;
            this.ae2 = d * (1.0d - d5);
        }
    }

    public static double distance(Datum datum, double d, double d2, double d3, double d4, double d5, double d6) {
        double radians = Math.toRadians(d);
        double radians2 = Math.toRadians(d4);
        double radians3 = Math.toRadians(d2);
        double radians4 = Math.toRadians(d5);
        double d7 = radians - radians2;
        double d8 = radians3 - radians4;
        double d9 = (radians + radians2) / 2.0d;
        double dSin = Math.sin(d9);
        double dSqrt = Math.sqrt(1.0d - ((datum.f2259e2 * dSin) * dSin));
        double d10 = d7 * (datum.ae2 / ((dSqrt * dSqrt) * dSqrt));
        double dCos = d8 * (datum.f2257a / dSqrt) * Math.cos(d9);
        return Math.sqrt((d10 * d10) + (dCos * dCos));
    }
}
