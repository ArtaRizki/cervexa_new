package com.serenegiant.utils;

/* JADX INFO: loaded from: classes2.dex */
public class FpsCounter {
    private int cnt;
    private float fps;
    private int prevCnt;
    private long prevTime;
    private long startTime;
    private float totalFps;

    public FpsCounter() {
        reset();
    }

    public synchronized FpsCounter reset() {
        this.prevCnt = 0;
        this.cnt = 0;
        long jNanoTime = Time.nanoTime() - 1;
        this.prevTime = jNanoTime;
        this.startTime = jNanoTime;
        return this;
    }

    public synchronized void count() {
        this.cnt++;
    }

    public synchronized FpsCounter update() {
        long jNanoTime = Time.nanoTime();
        this.fps = ((this.cnt - this.prevCnt) * 1.0E9f) / (jNanoTime - this.prevTime);
        this.prevCnt = this.cnt;
        this.prevTime = jNanoTime;
        this.totalFps = (this.cnt * 1.0E9f) / (jNanoTime - this.startTime);
        return this;
    }

    public synchronized float getFps() {
        return this.fps;
    }

    public synchronized float getTotalFps() {
        return this.totalFps;
    }
}
