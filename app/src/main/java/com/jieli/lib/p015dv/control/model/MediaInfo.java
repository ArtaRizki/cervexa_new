package com.jieli.lib.p015dv.control.model;

import com.jieli.stream.p016dv.running2.util.IConstant;

/* JADX INFO: loaded from: classes.dex */
public class MediaInfo implements Cloneable {

    /* JADX INFO: renamed from: a */
    private String f2129a;

    /* JADX INFO: renamed from: b */
    private int f2130b;

    /* JADX INFO: renamed from: c */
    private int f2131c;

    /* JADX INFO: renamed from: d */
    private int f2132d = 30;

    /* JADX INFO: renamed from: e */
    private int f2133e = IConstant.AUDIO_SAMPLE_RATE_DEFAULT;

    /* JADX INFO: renamed from: f */
    private int f2134f;

    public int getSampleRate() {
        return this.f2133e;
    }

    public void setSampleRate(int i) {
        this.f2133e = i;
    }

    public int getWidth() {
        return this.f2130b;
    }

    public void setWidth(int i) {
        this.f2130b = i;
    }

    public int getHeight() {
        return this.f2131c;
    }

    public void setHeight(int i) {
        this.f2131c = i;
    }

    public String toString() {
        return "width:" + this.f2130b + ", height:" + this.f2131c + ", path:" + this.f2129a + ", frameRate:" + this.f2132d + ", sampleRate:" + this.f2133e + ", duration:" + this.f2134f;
    }

    public String getPath() {
        return this.f2129a;
    }

    public void setPath(String str) {
        this.f2129a = str;
    }

    public int getFrameRate() {
        return this.f2132d;
    }

    public void setFrameRate(int i) {
        this.f2132d = i;
    }

    protected Object clone() {
        try {
            return (MediaInfo) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getDuration() {
        return this.f2134f;
    }

    public void setDuration(int i) {
        this.f2134f = i;
    }
}
