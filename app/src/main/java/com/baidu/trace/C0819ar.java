package com.baidu.trace;

import android.content.Context;
import android.os.Handler;
import android.os.RemoteException;
import android.preference.PreferenceManager;
import com.baidu.trace.api.entity.LocRequest;
import com.baidu.trace.api.entity.OnEntityListener;
import com.baidu.trace.model.CoordType;
import com.baidu.trace.model.LatLng;
import com.baidu.trace.model.LocationMode;
import com.baidu.trace.model.TraceLocation;
import com.baidu.trace.p010a.C0794c;
import com.baidu.trace.p010a.C0796e;
import com.baidu.trace.p010a.C0798g;
import com.baidu.trace.p010a.C0801j;
import com.baidu.trace.p012c.C0850a;
import com.baidu.trace.p012c.C0853d;
import com.baidu.trace.p012c.C0854e;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: renamed from: com.baidu.trace.ar */
/* JADX INFO: loaded from: classes.dex */
public class C0819ar {

    /* JADX INFO: renamed from: a */
    protected static C0811aj f1575a = null;

    /* JADX INFO: renamed from: b */
    public static int f1576b = 5000;

    /* JADX INFO: renamed from: c */
    public static int f1577c = 1;

    /* JADX INFO: renamed from: d */
    public static int f1578d = 600000;

    /* JADX INFO: renamed from: e */
    private Context f1579e;

    /* JADX INFO: renamed from: f */
    private WeakReference<LBSTraceService> f1580f;

    /* JADX INFO: renamed from: g */
    private LocRequest f1581g;

    /* JADX INFO: renamed from: h */
    private C0880y f1582h;

    /* JADX INFO: renamed from: i */
    private C0871p f1583i;

    /* JADX INFO: renamed from: j */
    private C0812ak f1584j;

    /* JADX INFO: renamed from: k */
    private C0798g f1585k;

    /* JADX INFO: renamed from: l */
    private ArrayList<C0801j> f1586l;

    /* JADX INFO: renamed from: m */
    private C0794c f1587m;

    /* JADX INFO: renamed from: n */
    private boolean f1588n;

    /* JADX INFO: renamed from: o */
    private boolean f1589o;

    /* JADX INFO: renamed from: p */
    private boolean f1590p;

    /* JADX INFO: renamed from: q */
    private boolean f1591q;

    /* JADX INFO: renamed from: r */
    private int f1592r;

    /* JADX INFO: renamed from: s */
    private Handler f1593s;

    /* JADX INFO: renamed from: t */
    private OnEntityListener f1594t;

    /* JADX INFO: renamed from: com.baidu.trace.ar$a */
    static class a {

        /* JADX INFO: renamed from: a */
        String f1595a;

        /* JADX INFO: renamed from: b */
        byte[] f1596b;

        /* JADX INFO: renamed from: c */
        long f1597c;

        public a(String str, byte[] bArr, long j) {
            this.f1595a = str;
            this.f1596b = bArr;
            this.f1597c = j;
        }
    }

    public C0819ar(Context context, Handler handler) {
        this.f1579e = null;
        this.f1580f = null;
        this.f1581g = null;
        this.f1585k = null;
        this.f1586l = null;
        this.f1587m = null;
        this.f1588n = true;
        this.f1589o = false;
        this.f1590p = true;
        this.f1591q = true;
        this.f1592r = LocationMode.High_Accuracy.ordinal();
        this.f1593s = null;
        this.f1594t = null;
        this.f1579e = context;
        this.f1582h = new C0880y(context);
        this.f1583i = new C0871p(this.f1579e);
        this.f1584j = new C0812ak(this.f1579e);
        this.f1593s = handler;
    }

    public C0819ar(WeakReference<LBSTraceService> weakReference, Handler handler) {
        Context context;
        Class cls;
        this.f1579e = null;
        this.f1580f = null;
        this.f1581g = null;
        this.f1585k = null;
        this.f1586l = null;
        this.f1587m = null;
        this.f1588n = true;
        this.f1589o = false;
        this.f1590p = true;
        this.f1591q = true;
        this.f1592r = LocationMode.High_Accuracy.ordinal();
        this.f1593s = null;
        this.f1594t = null;
        this.f1580f = weakReference;
        if (weakReference != null && weakReference.get() != null) {
            this.f1579e = this.f1580f.get().getServiceContext();
            this.f1592r = this.f1580f.get().getLocationMode();
        }
        this.f1582h = new C0880y(this.f1579e);
        this.f1583i = new C0871p(this.f1579e);
        this.f1584j = new C0812ak(this.f1579e);
        this.f1593s = handler;
        if (PreferenceManager.getDefaultSharedPreferences(this.f1579e).getBoolean("same_process", false)) {
            context = this.f1579e;
            cls = LBSTraceClient.class;
        } else {
            context = this.f1579e;
            cls = LBSTraceService.class;
        }
        C0850a.m1206a(context, cls);
        this.f1594t = new C0820as(this);
    }

    /* JADX INFO: renamed from: a */
    private ArrayList<C0796e> m1087a(long j) throws RemoteException {
        WeakReference<LBSTraceService> weakReference = this.f1580f;
        Map mapGatherAttribute = null;
        if (weakReference == null || weakReference.get() == null || this.f1580f.get().getClientListener() == null) {
            return null;
        }
        ArrayList<C0796e> arrayList = new ArrayList<>();
        try {
            if (this.f1580f != null && this.f1580f.get() != null) {
                mapGatherAttribute = this.f1580f.get().getClientListener().gatherAttribute(j);
            }
        } catch (Exception unused) {
        }
        if (mapGatherAttribute != null) {
            for (Map.Entry entry : mapGatherAttribute.entrySet()) {
                C0796e c0796e = new C0796e();
                if (!"_entity_name".equals(entry.getKey())) {
                    c0796e.f1183a = C0853d.m1220a((String) entry.getKey());
                    c0796e.f1184b = C0853d.m1220a((String) entry.getValue());
                    arrayList.add(c0796e);
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: renamed from: a */
    static /* synthetic */ void m1088a(C0819ar c0819ar, LatLng latLng, long j, CoordType coordType, double d) {
        if (f1575a == null) {
            return;
        }
        C0848bi c0848bi = new C0848bi(latLng, coordType, j, d);
        System.currentTimeMillis();
        double[] dArrProcessTrackPoint = TraceJniInterface.processTrackPoint(c0848bi.getLocation().latitude, c0848bi.getLocation().longitude, c0848bi.getRadius(), c0848bi.getLocTime(), c0848bi.getCoordType().ordinal());
        boolean z = true;
        if (dArrProcessTrackPoint == null || dArrProcessTrackPoint.length <= 3) {
            z = false;
        } else {
            c0848bi.setLocation(new LatLng(dArrProcessTrackPoint[0], dArrProcessTrackPoint[1]));
            c0848bi.setLocTime((long) dArrProcessTrackPoint[2]);
            c0848bi.setRadius(dArrProcessTrackPoint[3]);
            c0848bi.setCoordType(CoordType.bd09ll);
        }
        if (z) {
            f1575a.m1043a(c0848bi, c0819ar.f1593s);
        }
        System.currentTimeMillis();
    }

    /* JADX INFO: renamed from: a */
    protected final void m1089a() {
        C0798g c0798g;
        ArrayList<C0801j> arrayList;
        TraceJniInterface.m951a().clearCustomData();
        TraceJniInterface.m951a().clearWifiData();
        TraceJniInterface.m951a().clearBluetoothData();
        TraceJniInterface.m951a().clearNearbyCells();
        C0794c c0794c = null;
        if (LocationMode.Battery_Saving.ordinal() != this.f1592r) {
            int i = f1576b;
            int i2 = i / 2;
            int i3 = i * 2;
            c0798g = new C0798g();
            if (this.f1584j != null && C0854e.m1240e(this.f1579e) && this.f1584j.m1055a(i2)) {
                this.f1584j.m1054a(c0798g, i3);
                if (c0798g.f1195a) {
                    this.f1590p = false;
                    this.f1591q = false;
                    this.f1589o = true;
                }
                this.f1585k = c0798g;
            } else {
                this.f1589o = false;
                c0798g.f1195a = false;
            }
            m1094d();
            this.f1585k = c0798g;
        } else {
            c0798g = null;
        }
        if (c0798g == null || !c0798g.f1195a) {
            TraceJniInterface.m951a().setGPSData((byte) 0, (short) 0, (byte) 0, (short) 0, (short) 0, 0, 0);
        } else {
            TraceJniInterface.m951a().setGPSData(c0798g.f1197c, c0798g.f1198d, c0798g.f1199e, c0798g.f1200f, c0798g.f1201g, c0798g.f1202h, c0798g.f1203i);
        }
        if (LocationMode.Device_Sensors.ordinal() != this.f1592r) {
            if (this.f1590p) {
                arrayList = new ArrayList<>();
                if (C0854e.m1241f(this.f1579e) && this.f1582h.m1285a()) {
                    this.f1582h.m1284a(arrayList);
                }
            } else {
                arrayList = null;
            }
            this.f1586l = arrayList;
        } else {
            arrayList = null;
        }
        if (arrayList != null && arrayList.size() > 0) {
            for (int i4 = 0; i4 < arrayList.size(); i4++) {
                C0801j c0801j = arrayList.get(i4);
                TraceJniInterface.m951a().addWifiData(c0801j.f1209a, c0801j.f1210b, c0801j.f1211c, c0801j.f1212d);
            }
        }
        if (LocationMode.Device_Sensors.ordinal() != this.f1592r) {
            if (this.f1591q) {
                C0794c c0794c2 = new C0794c();
                if (C0854e.m1242g(this.f1579e) && this.f1583i.m1270a()) {
                    this.f1583i.m1269a(c0794c2);
                } else {
                    c0794c2.f1173a = false;
                }
                c0794c = c0794c2;
            }
            this.f1587m = c0794c;
        }
        if (c0794c == null || !c0794c.f1173a) {
            TraceJniInterface.m951a().setCellData((byte) 0, (byte) 0, (short) 0, (short) 0, 0, 0);
        } else {
            TraceJniInterface.m951a().setCellData(c0794c.f1174b, c0794c.f1175c, c0794c.f1176d, c0794c.f1177e, c0794c.f1178f, c0794c.f1179g);
            for (int i5 = 0; i5 < c0794c.f1180h.length && i5 < c0794c.f1181i.length; i5++) {
                TraceJniInterface.m951a().addNearbyCell(c0794c.f1180h[i5], c0794c.f1181i[i5]);
            }
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (c0798g != null && c0798g.f1195a && c0798g.f1196b > 0) {
            C0791a.m995a("L-GPS local time is: " + c0798g.f1196b);
            if (Math.abs(System.currentTimeMillis() - c0798g.f1196b) > f1578d) {
                jCurrentTimeMillis = c0798g.f1196b;
            }
        }
        C0791a.m995a("L-GPS locTime is: " + jCurrentTimeMillis);
        System.out.println("locTimeOffset : " + f1578d);
        TraceJniInterface.m951a().addCustomData("_entity_name".getBytes(), C0881z.f1868c.getBytes());
        ArrayList<C0796e> arrayListM1087a = m1087a(jCurrentTimeMillis);
        if (arrayListM1087a != null && arrayListM1087a.size() > 0) {
            for (int i6 = 0; i6 < arrayListM1087a.size(); i6++) {
                C0796e c0796e = arrayListM1087a.get(i6);
                TraceJniInterface.m951a().addCustomData(c0796e.f1183a, c0796e.f1184b);
            }
        }
        long j = jCurrentTimeMillis / 1000;
        byte[] bArrBuildLocationData = TraceJniInterface.m951a().buildLocationData((int) j);
        if (bArrBuildLocationData == null) {
            return;
        }
        if (f1576b <= 5000 && this.f1588n && c0798g != null && c0798g.f1195a) {
            this.f1588n = false;
            return;
        }
        a aVar = new a(C0881z.f1868c, bArrBuildLocationData, j);
        if (C0843bd.f1678c != null) {
            C0843bd.f1678c.offer(aVar);
        }
        C0811aj c0811aj = f1575a;
        if (c0811aj != null && !c0811aj.m1045a()) {
            long j2 = aVar.f1597c;
            if (c0798g == null || !c0798g.f1195a) {
                if (this.f1581g == null) {
                    this.f1581g = new LocRequest(C0881z.f1867b);
                }
                C0802aa.m1033a(this.f1579e, this.f1581g, this.f1594t);
            } else if (C0850a.f1715a != null) {
                C0850a.f1715a.execute(new RunnableC0821at(this, c0798g, j2));
            }
        }
        if (arrayList != null) {
            arrayList.clear();
        }
        if (arrayListM1087a != null) {
            arrayListM1087a.clear();
        }
    }

    /* JADX INFO: renamed from: a */
    public final void m1090a(int i) {
        this.f1592r = i;
    }

    /* JADX INFO: renamed from: a */
    protected final boolean m1091a(Context context, Handler handler, TraceLocation traceLocation) {
        C0812ak c0812ak = this.f1584j;
        if (c0812ak == null || !this.f1589o) {
            synchronized (C0819ar.class) {
                handler.post(new RunnableC0822au(this, context));
            }
            return false;
        }
        if (c0812ak == null) {
            return false;
        }
        C0798g c0798g = new C0798g();
        this.f1584j.m1054a(c0798g, 10000);
        if (c0798g.f1195a) {
            traceLocation.setLatitude(((double) c0798g.f1203i) / 600000.0d);
            traceLocation.setLongitude(((double) c0798g.f1202h) / 600000.0d);
            traceLocation.setRadius(c0798g.f1198d);
            traceLocation.setAltitude(c0798g.f1201g);
            traceLocation.setDirection(c0798g.f1199e << 1);
            traceLocation.setSpeed(c0798g.f1200f / 100.0f);
            traceLocation.setTime(C0854e.m1237c());
            C0854e.m1227a(traceLocation);
        }
        return c0798g.f1195a;
    }

    /* JADX INFO: renamed from: b */
    protected final void m1092b() {
        C0812ak c0812ak = this.f1584j;
        if (c0812ak != null) {
            c0812ak.m1052a();
            this.f1584j = null;
        }
    }

    /* JADX INFO: renamed from: c */
    protected final void m1093c() {
        m1092b();
        if (C0850a.f1715a != null && !PreferenceManager.getDefaultSharedPreferences(this.f1579e).getBoolean("same_process", true) && "FinalizableDelegatedExecutorService".equals(C0850a.f1715a.getClass().getSimpleName())) {
            C0850a.m1205a();
        }
        C0880y c0880y = this.f1582h;
        if (c0880y != null) {
            c0880y.m1286b();
            this.f1582h = null;
        }
        C0871p c0871p = this.f1583i;
        if (c0871p != null) {
            c0871p.m1271b();
            this.f1583i = null;
        }
        ArrayList<C0801j> arrayList = this.f1586l;
        if (arrayList != null) {
            arrayList.clear();
            this.f1586l = null;
        }
        if (this.f1587m != null) {
            this.f1587m = null;
        }
        if (this.f1585k != null) {
            this.f1585k = null;
        }
        if (this.f1579e != null) {
            this.f1579e = null;
        }
        WeakReference<LBSTraceService> weakReference = this.f1580f;
        if (weakReference != null) {
            weakReference.clear();
            this.f1580f = null;
        }
        if (this.f1593s != null) {
            this.f1593s = null;
        }
        C0811aj c0811aj = f1575a;
        if (c0811aj != null) {
            c0811aj.m1047b();
            f1575a = null;
        }
        TraceJniInterface.clearTrackData();
    }

    /* JADX INFO: renamed from: d */
    protected final void m1094d() {
        this.f1591q = true;
        this.f1590p = true;
        this.f1589o = false;
    }
}
