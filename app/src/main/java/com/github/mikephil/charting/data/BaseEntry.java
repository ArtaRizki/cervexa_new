package com.github.mikephil.charting.data;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseEntry {
    private Object mData;

    /* JADX INFO: renamed from: y */
    private float f1951y;

    public BaseEntry() {
        this.f1951y = 0.0f;
        this.mData = null;
    }

    public BaseEntry(float f) {
        this.f1951y = 0.0f;
        this.mData = null;
        this.f1951y = f;
    }

    public BaseEntry(float f, Object obj) {
        this(f);
        this.mData = obj;
    }

    public float getY() {
        return this.f1951y;
    }

    public void setY(float f) {
        this.f1951y = f;
    }

    public Object getData() {
        return this.mData;
    }

    public void setData(Object obj) {
        this.mData = obj;
    }
}
