package com.baidu.trace.model;

/* JADX INFO: loaded from: classes.dex */
public class ProcessOption {

    /* JADX INFO: renamed from: a */
    private boolean f1813a;

    /* JADX INFO: renamed from: b */
    private boolean f1814b;

    /* JADX INFO: renamed from: c */
    private boolean f1815c;

    /* JADX INFO: renamed from: d */
    private int f1816d;

    /* JADX INFO: renamed from: e */
    private TransportMode f1817e;

    public ProcessOption() {
        this.f1813a = true;
        this.f1814b = true;
        this.f1815c = false;
        this.f1816d = 0;
        this.f1817e = TransportMode.driving;
    }

    public ProcessOption(boolean z, boolean z2, boolean z3, int i, TransportMode transportMode) {
        this.f1813a = true;
        this.f1814b = true;
        this.f1815c = false;
        this.f1816d = 0;
        this.f1817e = TransportMode.driving;
        this.f1813a = z;
        this.f1814b = z2;
        this.f1815c = z3;
        this.f1816d = i;
        this.f1817e = transportMode;
    }

    public int getRadiusThreshold() {
        return this.f1816d;
    }

    public TransportMode getTransportMode() {
        return this.f1817e;
    }

    public boolean isNeedDenoise() {
        return this.f1813a;
    }

    public boolean isNeedMapMatch() {
        return this.f1815c;
    }

    public boolean isNeedVacuate() {
        return this.f1814b;
    }

    public ProcessOption setNeedDenoise(boolean z) {
        this.f1813a = z;
        return this;
    }

    public ProcessOption setNeedMapMatch(boolean z) {
        this.f1815c = z;
        return this;
    }

    public ProcessOption setNeedVacuate(boolean z) {
        this.f1814b = z;
        return this;
    }

    public ProcessOption setRadiusThreshold(int i) {
        this.f1816d = i;
        return this;
    }

    public ProcessOption setTransportMode(TransportMode transportMode) {
        this.f1817e = transportMode;
        return this;
    }

    public String toString() {
        return "ProcessOption [needDenoise=" + this.f1813a + ", needVacuate=" + this.f1814b + ", needMapMatch=" + this.f1815c + ", radiusThreshold=" + this.f1816d + ", transportMode=" + this.f1817e + "]";
    }
}
