package com.baidu.mapapi.map;

/* JADX INFO: loaded from: classes.dex */
public class MyLocationData {
    public final float accuracy;
    public final float direction;
    public final double latitude;
    public final double longitude;
    public final int satellitesNum;
    public final float speed;

    public static class Builder {

        /* JADX INFO: renamed from: a */
        private double f484a;

        /* JADX INFO: renamed from: b */
        private double f485b;

        /* JADX INFO: renamed from: c */
        private float f486c;

        /* JADX INFO: renamed from: d */
        private float f487d;

        /* JADX INFO: renamed from: e */
        private float f488e;

        /* JADX INFO: renamed from: f */
        private int f489f;

        public Builder accuracy(float f) {
            this.f488e = f;
            return this;
        }

        public MyLocationData build() {
            return new MyLocationData(this.f484a, this.f485b, this.f486c, this.f487d, this.f488e, this.f489f);
        }

        public Builder direction(float f) {
            this.f487d = f;
            return this;
        }

        public Builder latitude(double d) {
            this.f484a = d;
            return this;
        }

        public Builder longitude(double d) {
            this.f485b = d;
            return this;
        }

        public Builder satellitesNum(int i) {
            this.f489f = i;
            return this;
        }

        public Builder speed(float f) {
            this.f486c = f;
            return this;
        }
    }

    MyLocationData(double d, double d2, float f, float f2, float f3, int i) {
        this.latitude = d;
        this.longitude = d2;
        this.speed = f;
        this.direction = f2;
        this.accuracy = f3;
        this.satellitesNum = i;
    }
}
