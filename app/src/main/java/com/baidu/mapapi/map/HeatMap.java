package com.baidu.mapapi.map;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.util.SparseIntArray;
import androidx.collection.LongSparseArray;
import com.baidu.mapapi.model.LatLng;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import org.apache.commons.net.ftp.FTPReply;

/* JADX INFO: loaded from: classes.dex */
public class HeatMap {
    public static final Gradient DEFAULT_GRADIENT;
    public static final double DEFAULT_OPACITY = 0.6d;
    public static final int DEFAULT_RADIUS = 12;

    /* JADX INFO: renamed from: b */
    private static final String f347b = HeatMap.class.getSimpleName();

    /* JADX INFO: renamed from: c */
    private static final SparseIntArray f348c;

    /* JADX INFO: renamed from: d */
    private static final int[] f349d;

    /* JADX INFO: renamed from: e */
    private static final float[] f350e;

    /* JADX INFO: renamed from: r */
    private static int f351r;

    /* JADX INFO: renamed from: a */
    BaiduMap f352a;

    /* JADX INFO: renamed from: f */
    private C0689l<WeightedLatLng> f353f;

    /* JADX INFO: renamed from: g */
    private Collection<WeightedLatLng> f354g;

    /* JADX INFO: renamed from: h */
    private int f355h;

    /* JADX INFO: renamed from: i */
    private Gradient f356i;

    /* JADX INFO: renamed from: j */
    private double f357j;

    /* JADX INFO: renamed from: k */
    private C0683f f358k;

    /* JADX INFO: renamed from: l */
    private int[] f359l;

    /* JADX INFO: renamed from: m */
    private double[] f360m;

    /* JADX INFO: renamed from: n */
    private double[] f361n;

    /* JADX INFO: renamed from: o */
    private HashMap<String, Tile> f362o;

    /* JADX INFO: renamed from: p */
    private ExecutorService f363p;

    /* JADX INFO: renamed from: q */
    private HashSet<String> f364q;

    public static class Builder {

        /* JADX INFO: renamed from: a */
        private Collection<WeightedLatLng> f365a;

        /* JADX INFO: renamed from: b */
        private int f366b = 12;

        /* JADX INFO: renamed from: c */
        private Gradient f367c = HeatMap.DEFAULT_GRADIENT;

        /* JADX INFO: renamed from: d */
        private double f368d = 0.6d;

        public HeatMap build() {
            if (this.f365a != null) {
                return new HeatMap(this, null);
            }
            throw new IllegalStateException("No input data: you must use either .data or .weightedData before building");
        }

        public Builder data(Collection<LatLng> collection) {
            if (collection == null || collection.isEmpty()) {
                throw new IllegalArgumentException("No input points.");
            }
            if (collection.contains(null)) {
                throw new IllegalArgumentException("input points can not contain null.");
            }
            return weightedData(HeatMap.m302c(collection));
        }

        public Builder gradient(Gradient gradient) {
            if (gradient == null) {
                throw new IllegalArgumentException("gradient can not be null");
            }
            this.f367c = gradient;
            return this;
        }

        public Builder opacity(double d) {
            this.f368d = d;
            if (d < 0.0d || d > 1.0d) {
                throw new IllegalArgumentException("Opacity must be in range [0, 1]");
            }
            return this;
        }

        public Builder radius(int i) {
            this.f366b = i;
            if (i < 10 || i > 50) {
                throw new IllegalArgumentException("Radius not within bounds.");
            }
            return this;
        }

        public Builder weightedData(Collection<WeightedLatLng> collection) {
            if (collection == null || collection.isEmpty()) {
                throw new IllegalArgumentException("No input points.");
            }
            if (collection.contains(null)) {
                throw new IllegalArgumentException("input points can not contain null.");
            }
            ArrayList arrayList = new ArrayList();
            for (WeightedLatLng weightedLatLng : collection) {
                LatLng latLng = weightedLatLng.latLng;
                if (latLng.latitude < 0.37532d || latLng.latitude > 54.562495d || latLng.longitude < 72.508319d || latLng.longitude > 135.942198d) {
                    arrayList.add(weightedLatLng);
                }
            }
            collection.removeAll(arrayList);
            this.f365a = collection;
            return this;
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        f348c = sparseIntArray;
        sparseIntArray.put(3, 8388608);
        f348c.put(4, 4194304);
        f348c.put(5, 2097152);
        f348c.put(6, 1048576);
        f348c.put(7, 524288);
        f348c.put(8, 262144);
        f348c.put(9, 131072);
        f348c.put(10, 65536);
        f348c.put(11, 32768);
        f348c.put(12, 16384);
        f348c.put(13, 8192);
        f348c.put(14, 4096);
        f348c.put(15, 2048);
        f348c.put(16, 1024);
        f348c.put(17, 512);
        f348c.put(18, 256);
        f348c.put(19, 128);
        f348c.put(20, 64);
        int[] iArr = {Color.rgb(0, 0, 200), Color.rgb(0, FTPReply.DATA_CONNECTION_OPEN, 0), Color.rgb(255, 0, 0)};
        f349d = iArr;
        float[] fArr = {0.08f, 0.4f, 1.0f};
        f350e = fArr;
        DEFAULT_GRADIENT = new Gradient(iArr, fArr);
        f351r = 0;
    }

    private HeatMap(Builder builder) {
        this.f362o = new HashMap<>();
        this.f363p = Executors.newFixedThreadPool(1);
        this.f364q = new HashSet<>();
        this.f354g = builder.f365a;
        this.f355h = builder.f366b;
        this.f356i = builder.f367c;
        this.f357j = builder.f368d;
        int i = this.f355h;
        this.f360m = m296a(i, ((double) i) / 3.0d);
        m291a(this.f356i);
        m300b(this.f354g);
    }

    /* synthetic */ HeatMap(Builder builder, RunnableC0684g runnableC0684g) {
        this(builder);
    }

    /* JADX INFO: renamed from: a */
    private static double m287a(Collection<WeightedLatLng> collection, C0683f c0683f, int i, int i2) {
        double d = c0683f.f662a;
        double d2 = c0683f.f664c;
        double d3 = c0683f.f663b;
        double d4 = d2 - d;
        double d5 = c0683f.f665d - d3;
        if (d4 <= d5) {
            d4 = d5;
        }
        double d6 = ((double) ((int) (((double) (i2 / (i * 2))) + 0.5d))) / d4;
        LongSparseArray longSparseArray = new LongSparseArray();
        double dDoubleValue = 0.0d;
        for (WeightedLatLng weightedLatLng : collection) {
            double d7 = weightedLatLng.mo408a().x;
            int i3 = (int) ((((double) weightedLatLng.mo408a().y) - d3) * d6);
            long j = (int) ((d7 - d) * d6);
            LongSparseArray longSparseArray2 = (LongSparseArray) longSparseArray.get(j);
            if (longSparseArray2 == null) {
                longSparseArray2 = new LongSparseArray();
                longSparseArray.put(j, longSparseArray2);
            }
            long j2 = i3;
            Double dValueOf = (Double) longSparseArray2.get(j2);
            if (dValueOf == null) {
                dValueOf = Double.valueOf(0.0d);
            }
            LongSparseArray longSparseArray3 = longSparseArray;
            double d8 = d;
            Double dValueOf2 = Double.valueOf(dValueOf.doubleValue() + weightedLatLng.intensity);
            longSparseArray2.put(j2, dValueOf2);
            if (dValueOf2.doubleValue() > dDoubleValue) {
                dDoubleValue = dValueOf2.doubleValue();
            }
            longSparseArray = longSparseArray3;
            d = d8;
        }
        return dDoubleValue;
    }

    /* JADX INFO: renamed from: a */
    private static Bitmap m288a(double[][] dArr, int[] iArr, double d) {
        int i = iArr[iArr.length - 1];
        double length = ((double) (iArr.length - 1)) / d;
        int length2 = dArr.length;
        int[] iArr2 = new int[length2 * length2];
        for (int i2 = 0; i2 < length2; i2++) {
            for (int i3 = 0; i3 < length2; i3++) {
                double d2 = dArr[i3][i2];
                int i4 = (i2 * length2) + i3;
                int i5 = (int) (d2 * length);
                if (d2 == 0.0d) {
                    iArr2[i4] = 0;
                } else if (i5 < iArr.length) {
                    iArr2[i4] = iArr[i5];
                } else {
                    iArr2[i4] = i;
                }
            }
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(length2, length2, Bitmap.Config.ARGB_8888);
        bitmapCreateBitmap.setPixels(iArr2, 0, length2, 0, 0, length2, length2);
        return bitmapCreateBitmap;
    }

    /* JADX INFO: renamed from: a */
    private static Tile m289a(Bitmap bitmap) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bitmap.getWidth() * bitmap.getHeight() * 4);
        bitmap.copyPixelsToBuffer(byteBufferAllocate);
        return new Tile(256, 256, byteBufferAllocate.array());
    }

    /* JADX INFO: renamed from: a */
    private void m291a(Gradient gradient) {
        this.f356i = gradient;
        this.f359l = gradient.m283a(this.f357j);
    }

    /* JADX INFO: renamed from: a */
    private synchronized void m293a(String str, Tile tile) {
        this.f362o.put(str, tile);
    }

    /* JADX INFO: renamed from: a */
    private synchronized boolean m294a(String str) {
        return this.f364q.contains(str);
    }

    /* JADX INFO: renamed from: a */
    private double[] m295a(int i) {
        int i2;
        double[] dArr = new double[20];
        int i3 = 5;
        while (true) {
            if (i3 >= 11) {
                break;
            }
            dArr[i3] = m287a(this.f354g, this.f358k, i, (int) (Math.pow(2.0d, i3 - 3) * 1280.0d));
            if (i3 == 5) {
                for (int i4 = 0; i4 < i3; i4++) {
                    dArr[i4] = dArr[i3];
                }
            }
            i3++;
        }
        for (i2 = 11; i2 < 20; i2++) {
            dArr[i2] = dArr[10];
        }
        return dArr;
    }

    /* JADX INFO: renamed from: a */
    private static double[] m296a(int i, double d) {
        double[] dArr = new double[(i * 2) + 1];
        for (int i2 = -i; i2 <= i; i2++) {
            dArr[i2 + i] = Math.exp(((double) ((-i2) * i2)) / ((2.0d * d) * d));
        }
        return dArr;
    }

    /* JADX INFO: renamed from: a */
    private static double[][] m297a(double[][] dArr, double[] dArr2) {
        int iFloor = (int) Math.floor(((double) dArr2.length) / 2.0d);
        int length = dArr.length;
        int i = length - (iFloor * 2);
        int i2 = 1;
        int i3 = (iFloor + i) - 1;
        double[][] dArr3 = (double[][]) Array.newInstance((Class<?>) double.class, length, length);
        int i4 = 0;
        while (true) {
            double d = 0.0d;
            if (i4 >= length) {
                break;
            }
            int i5 = 0;
            while (i5 < length) {
                double d2 = dArr[i4][i5];
                if (d2 != d) {
                    int i6 = i4 + iFloor;
                    if (i3 < i6) {
                        i6 = i3;
                    }
                    int i7 = i6 + 1;
                    int i8 = i4 - iFloor;
                    for (int i9 = iFloor > i8 ? iFloor : i8; i9 < i7; i9++) {
                        double[] dArr4 = dArr3[i9];
                        dArr4[i5] = dArr4[i5] + (dArr2[i9 - i8] * d2);
                    }
                }
                i5++;
                d = 0.0d;
            }
            i4++;
        }
        double[][] dArr5 = (double[][]) Array.newInstance((Class<?>) double.class, i, i);
        int i10 = iFloor;
        while (i10 < i3 + 1) {
            int i11 = 0;
            while (i11 < length) {
                double d3 = dArr3[i10][i11];
                if (d3 != 0.0d) {
                    int i12 = i11 + iFloor;
                    if (i3 < i12) {
                        i12 = i3;
                    }
                    int i13 = i12 + i2;
                    int i14 = i11 - iFloor;
                    for (int i15 = iFloor > i14 ? iFloor : i14; i15 < i13; i15++) {
                        double[] dArr6 = dArr5[i10 - iFloor];
                        int i16 = i15 - iFloor;
                        dArr6[i16] = dArr6[i16] + (dArr2[i15 - i14] * d3);
                    }
                }
                i11++;
                i2 = 1;
            }
            i10++;
            i2 = 1;
        }
        return dArr5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b */
    public void m298b(int i, int i2, int i3) {
        double d = f348c.get(i3);
        int i4 = this.f355h;
        double d2 = (((double) i4) * d) / 256.0d;
        double d3 = ((2.0d * d2) + d) / ((double) ((i4 * 2) + 256));
        if (i < 0 || i2 < 0) {
            return;
        }
        double d4 = (((double) i) * d) - d2;
        double d5 = (((double) (i + 1)) * d) + d2;
        double d6 = (((double) i2) * d) - d2;
        double d7 = (((double) (i2 + 1)) * d) + d2;
        C0683f c0683f = new C0683f(d4, d5, d6, d7);
        if (c0683f.m436a(new C0683f(this.f358k.f662a - d2, this.f358k.f664c + d2, this.f358k.f663b - d2, this.f358k.f665d + d2))) {
            Collection<T> collectionM443a = this.f353f.m443a(c0683f);
            if (collectionM443a.isEmpty()) {
                return;
            }
            int i5 = this.f355h;
            double[][] dArr = (double[][]) Array.newInstance((Class<?>) double.class, (i5 * 2) + 256, (i5 * 2) + 256);
            for (T t : collectionM443a) {
                Point pointMo408a = t.mo408a();
                int i6 = (int) ((((double) pointMo408a.x) - d4) / d3);
                int i7 = (int) ((d7 - ((double) pointMo408a.y)) / d3);
                int i8 = this.f355h;
                if (i6 >= (i8 * 2) + 256) {
                    i6 = ((i8 * 2) + 256) - 1;
                }
                int i9 = this.f355h;
                if (i7 >= (i9 * 2) + 256) {
                    i7 = ((i9 * 2) + 256) - 1;
                }
                double[] dArr2 = dArr[i6];
                dArr2[i7] = dArr2[i7] + t.intensity;
                d7 = d7;
            }
            Bitmap bitmapM288a = m288a(m297a(dArr, this.f360m), this.f359l, this.f361n[i3 - 1]);
            Tile tileM289a = m289a(bitmapM288a);
            bitmapM288a.recycle();
            m293a(i + "_" + i2 + "_" + i3, tileM289a);
            if (this.f362o.size() > f351r) {
                m306a();
            }
            BaiduMap baiduMap = this.f352a;
            if (baiduMap != null) {
                baiduMap.m273a();
            }
        }
    }

    /* JADX INFO: renamed from: b */
    private synchronized void m299b(String str) {
        this.f364q.add(str);
    }

    /* JADX INFO: renamed from: b */
    private void m300b(Collection<WeightedLatLng> collection) {
        this.f354g = collection;
        if (collection.isEmpty()) {
            throw new IllegalArgumentException("No input points.");
        }
        C0683f c0683fM303d = m303d(this.f354g);
        this.f358k = c0683fM303d;
        this.f353f = new C0689l<>(c0683fM303d);
        Iterator<WeightedLatLng> it = this.f354g.iterator();
        while (it.hasNext()) {
            this.f353f.m444a(it.next());
        }
        this.f361n = m295a(this.f355h);
    }

    /* JADX INFO: renamed from: c */
    private synchronized Tile m301c(String str) {
        if (!this.f362o.containsKey(str)) {
            return null;
        }
        Tile tile = this.f362o.get(str);
        this.f362o.remove(str);
        return tile;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: c */
    public static Collection<WeightedLatLng> m302c(Collection<LatLng> collection) {
        ArrayList arrayList = new ArrayList();
        Iterator<LatLng> it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(new WeightedLatLng(it.next()));
        }
        return arrayList;
    }

    /* JADX INFO: renamed from: d */
    private static C0683f m303d(Collection<WeightedLatLng> collection) {
        Iterator<WeightedLatLng> it = collection.iterator();
        WeightedLatLng next = it.next();
        double d = next.mo408a().x;
        double d2 = d;
        double d3 = next.mo408a().x;
        double d4 = next.mo408a().y;
        double d5 = next.mo408a().y;
        while (it.hasNext()) {
            WeightedLatLng next2 = it.next();
            double d6 = next2.mo408a().x;
            double d7 = next2.mo408a().y;
            if (d6 < d2) {
                d2 = d6;
            }
            if (d6 > d3) {
                d3 = d6;
            }
            if (d7 < d4) {
                d4 = d7;
            }
            if (d7 > d5) {
                d5 = d7;
            }
        }
        return new C0683f(d2, d3, d4, d5);
    }

    /* JADX INFO: renamed from: d */
    private synchronized void m304d() {
        this.f362o.clear();
    }

    /* JADX INFO: renamed from: a */
    Tile m305a(int i, int i2, int i3) {
        String str = i + "_" + i2 + "_" + i3;
        Tile tileM301c = m301c(str);
        if (tileM301c != null) {
            return tileM301c;
        }
        if (m294a(str)) {
            return null;
        }
        BaiduMap baiduMap = this.f352a;
        if (baiduMap != null && f351r == 0) {
            MapStatus mapStatus = baiduMap.getMapStatus();
            f351r = (((mapStatus.f388a.f854j.right - mapStatus.f388a.f854j.left) / 256) + 2) * (((mapStatus.f388a.f854j.bottom - mapStatus.f388a.f854j.top) / 256) + 2) * 4;
        }
        if (this.f362o.size() > f351r) {
            m306a();
        }
        if (this.f363p.isShutdown()) {
            return null;
        }
        try {
            this.f363p.execute(new RunnableC0684g(this, i, i2, i3));
            m299b(str);
            return null;
        } catch (RejectedExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: renamed from: a */
    synchronized void m306a() {
        this.f364q.clear();
        this.f362o.clear();
    }

    /* JADX INFO: renamed from: b */
    void m307b() {
        m304d();
    }

    /* JADX INFO: renamed from: c */
    void m308c() {
        this.f363p.shutdownNow();
    }

    public void removeHeatMap() {
        BaiduMap baiduMap = this.f352a;
        if (baiduMap != null) {
            baiduMap.m274a(this);
        }
    }
}
