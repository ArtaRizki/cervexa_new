package com.github.mikephil.charting.utils;

import com.github.mikephil.charting.utils.ObjectPool;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MPPointD extends ObjectPool.Poolable {
    private static ObjectPool<MPPointD> pool;

    /* JADX INFO: renamed from: x */
    public double f1968x;

    /* JADX INFO: renamed from: y */
    public double f1969y;

    static {
        ObjectPool<MPPointD> objectPoolCreate = ObjectPool.create(64, new MPPointD(0.0d, 0.0d));
        pool = objectPoolCreate;
        objectPoolCreate.setReplenishPercentage(0.5f);
    }

    public static MPPointD getInstance(double d, double d2) {
        MPPointD mPPointD = (MPPointD) pool.get();
        mPPointD.f1968x = d;
        mPPointD.f1969y = d2;
        return mPPointD;
    }

    public static void recycleInstance(MPPointD mPPointD) {
        pool.recycle(mPPointD);
    }

    public static void recycleInstances(List<MPPointD> list) {
        pool.recycle(list);
    }

    @Override // com.github.mikephil.charting.utils.ObjectPool.Poolable
    protected ObjectPool.Poolable instantiate() {
        return new MPPointD(0.0d, 0.0d);
    }

    private MPPointD(double d, double d2) {
        this.f1968x = d;
        this.f1969y = d2;
    }

    public String toString() {
        return "MPPointD, x: " + this.f1968x + ", y: " + this.f1969y;
    }
}
