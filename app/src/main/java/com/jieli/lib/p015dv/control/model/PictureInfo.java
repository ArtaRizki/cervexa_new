package com.jieli.lib.p015dv.control.model;

/* JADX INFO: loaded from: classes.dex */
public class PictureInfo extends MediaInfo implements Cloneable {

    /* JADX INFO: renamed from: a */
    private long f2135a;

    /* JADX INFO: renamed from: b */
    private boolean f2136b = false;

    public long getTime() {
        return this.f2135a;
    }

    public void setTime(long j) {
        this.f2135a = j;
    }

    @Override // com.jieli.lib.p015dv.control.model.MediaInfo
    public String toString() {
        return super.toString() + ", time:" + this.f2135a + ", isLast:" + this.f2136b;
    }

    @Override // com.jieli.lib.p015dv.control.model.MediaInfo
    public Object clone() {
        return super.clone();
    }

    public boolean isLast() {
        return this.f2136b;
    }

    public void setLast(boolean z) {
        this.f2136b = z;
    }
}
