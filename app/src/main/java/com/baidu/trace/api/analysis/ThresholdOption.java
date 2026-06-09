package com.baidu.trace.api.analysis;

/* JADX INFO: loaded from: classes.dex */
public final class ThresholdOption {

    /* JADX INFO: renamed from: a */
    private double f1326a;

    /* JADX INFO: renamed from: b */
    private double f1327b;

    /* JADX INFO: renamed from: c */
    private double f1328c;

    /* JADX INFO: renamed from: d */
    private double f1329d;

    public ThresholdOption() {
    }

    public ThresholdOption(double d, double d2, double d3, double d4) {
        this.f1326a = d;
        this.f1327b = d2;
        this.f1328c = d3;
        this.f1329d = d4;
    }

    public final double getHarshAccelerationThreshold() {
        return this.f1327b;
    }

    public final double getHarshBreakingThreshold() {
        return this.f1328c;
    }

    public final double getHarshSteeringThreshold() {
        return this.f1329d;
    }

    public final double getSpeedingThreshold() {
        return this.f1326a;
    }

    public final ThresholdOption setHarshAccelerationThreshold(double d) {
        this.f1327b = d;
        return this;
    }

    public final ThresholdOption setHarshBreakingThreshold(double d) {
        this.f1328c = d;
        return this;
    }

    public final ThresholdOption setHarshSteeringThreshold(double d) {
        this.f1329d = d;
        return this;
    }

    public final ThresholdOption setSpeedingThreshold(double d) {
        this.f1326a = d;
        return this;
    }

    public final String toString() {
        StringBuffer stringBuffer = new StringBuffer("ThresholdOption{");
        stringBuffer.append("speedingThreshold=");
        stringBuffer.append(this.f1326a);
        stringBuffer.append(", harshAccelerationThreshold=");
        stringBuffer.append(this.f1327b);
        stringBuffer.append(", harshBreakingThreshold=");
        stringBuffer.append(this.f1328c);
        stringBuffer.append(", harshSteeringThreshold=");
        stringBuffer.append(this.f1329d);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
