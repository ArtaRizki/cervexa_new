package com.jieli.lib.p015dv.control.model;

/* JADX INFO: loaded from: classes.dex */
public class GpsInfo {

    /* JADX INFO: renamed from: a */
    private double f2126a;

    /* JADX INFO: renamed from: b */
    private double f2127b;

    /* JADX INFO: renamed from: c */
    private double f2128c;

    public double getLatitude() {
        return this.f2126a;
    }

    public void setLatitude(double d) {
        this.f2126a = d;
    }

    public double getLongitude() {
        return this.f2127b;
    }

    public void setLongitude(double d) {
        this.f2127b = d;
    }

    public double getSpeed() {
        return this.f2128c;
    }

    public void setSpeed(double d) {
        this.f2128c = d;
    }

    public String toString() {
        return "[Latitude:" + this.f2126a + ",Longitude:" + this.f2127b + ",Speed:" + this.f2128c + "]";
    }
}
