package com.baidu.mapapi.map;

/* JADX INFO: renamed from: com.baidu.mapapi.map.g */
/* JADX INFO: loaded from: classes.dex */
class RunnableC0684g implements Runnable {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ int f668a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ int f669b;

    /* JADX INFO: renamed from: c */
    final /* synthetic */ int f670c;

    /* JADX INFO: renamed from: d */
    final /* synthetic */ HeatMap f671d;

    RunnableC0684g(HeatMap heatMap, int i, int i2, int i3) {
        this.f671d = heatMap;
        this.f668a = i;
        this.f669b = i2;
        this.f670c = i3;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f671d.m298b(this.f668a, this.f669b, this.f670c);
    }
}
