package com.baidu.trace;

import android.content.Context;
import android.content.Intent;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import com.baidu.trace.model.StatusCodes;
import com.baidu.trace.p010a.C0798g;
import java.util.Iterator;

/* JADX INFO: renamed from: com.baidu.trace.ak */
/* JADX INFO: loaded from: classes.dex */
public final class C0812ak implements LocationListener {

    /* JADX INFO: renamed from: a */
    protected static int f1252a = -1;

    /* JADX INFO: renamed from: b */
    private LocationManager f1253b;

    /* JADX INFO: renamed from: c */
    private Location f1254c;

    /* JADX INFO: renamed from: d */
    private boolean f1255d;

    /* JADX INFO: renamed from: h */
    private Context f1259h;

    /* JADX INFO: renamed from: i */
    private a f1260i;

    /* JADX INFO: renamed from: e */
    private int f1256e = 0;

    /* JADX INFO: renamed from: f */
    private int f1257f = 10;

    /* JADX INFO: renamed from: g */
    private long f1258g = 0;

    /* JADX INFO: renamed from: j */
    private final GpsStatus.Listener f1261j = new C0813al(this);

    /* JADX INFO: renamed from: com.baidu.trace.ak$a */
    public class a {

        /* JADX INFO: renamed from: a */
        long f1262a;

        /* JADX INFO: renamed from: b */
        int f1263b;

        /* JADX INFO: renamed from: c */
        int f1264c;

        /* JADX INFO: renamed from: d */
        int f1265d;

        /* JADX INFO: renamed from: e */
        int f1266e;

        /* JADX INFO: renamed from: f */
        int f1267f;

        /* JADX INFO: renamed from: g */
        int f1268g;

        protected a(C0812ak c0812ak) {
        }
    }

    public C0812ak(Context context) {
        this.f1259h = context;
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService("location");
            this.f1253b = locationManager;
            if (locationManager == null || locationManager.getAllProviders() == null || !this.f1253b.getAllProviders().contains("gps")) {
                this.f1253b = null;
            } else {
                this.f1253b.addGpsStatusListener(this.f1261j);
                this.f1253b.sendExtraCommand("gps", "force_xtra_injection", new Bundle());
            }
        } catch (SecurityException unused) {
        } catch (Exception unused2) {
            this.f1253b = null;
        }
        this.f1260i = new a(this);
    }

    /* JADX INFO: renamed from: a */
    static /* synthetic */ int m1049a(C0812ak c0812ak, int i) {
        c0812ak.f1256e = 1;
        return 1;
    }

    /* JADX INFO: renamed from: a */
    static /* synthetic */ void m1051a(C0812ak c0812ak, int i, GpsStatus gpsStatus) {
        if (gpsStatus == null || i != 4) {
            return;
        }
        int maxSatellites = gpsStatus.getMaxSatellites();
        Iterator<GpsSatellite> it = gpsStatus.getSatellites().iterator();
        c0812ak.f1256e = 0;
        for (int i2 = 0; it.hasNext() && i2 <= maxSatellites; i2++) {
            if (it.next().usedInFix()) {
                c0812ak.f1256e++;
            }
        }
    }

    /* JADX INFO: renamed from: a */
    protected final void m1052a() {
        LocationManager locationManager = this.f1253b;
        if (locationManager != null) {
            locationManager.removeUpdates(this);
        }
        f1252a = -1;
    }

    /* JADX INFO: renamed from: a */
    protected final void m1053a(int i, String str) {
        f1252a = i;
        Intent intent = new Intent(StatusCodes.GPS_STATUS_ACTION);
        intent.putExtra("statusCode", i);
        intent.putExtra("statusMessage", str);
        this.f1259h.sendBroadcast(intent);
    }

    /* JADX INFO: renamed from: a */
    protected final void m1054a(C0798g c0798g, int i) {
        if (this.f1260i == null || System.currentTimeMillis() - this.f1258g >= i) {
            c0798g.f1195a = false;
            if (1 != f1252a) {
                m1053a(1, StatusCodes.MSG_GPS_SEARCHING);
                return;
            }
            return;
        }
        int i2 = this.f1256e;
        if (i2 == 0) {
            i2 = 1;
        }
        c0798g.f1197c = Integer.valueOf(i2).byteValue();
        c0798g.f1198d = Integer.valueOf(this.f1260i.f1263b).shortValue();
        c0798g.f1201g = Integer.valueOf(this.f1260i.f1266e).shortValue();
        c0798g.f1199e = Integer.valueOf(this.f1260i.f1264c).byteValue();
        c0798g.f1203i = this.f1260i.f1268g;
        c0798g.f1202h = this.f1260i.f1267f;
        c0798g.f1200f = Integer.valueOf(this.f1260i.f1265d).shortValue();
        c0798g.f1195a = (c0798g.f1197c <= 0 || c0798g.f1203i == 0 || c0798g.f1202h == 0) ? false : true;
        c0798g.f1196b = this.f1260i.f1262a;
        C0791a.m995a("G-GPS local time is: " + this.f1260i.f1262a);
        if (f1252a != 0) {
            m1053a(0, StatusCodes.MSG_GPS_LOCATED);
        }
    }

    /* JADX INFO: renamed from: a */
    protected final boolean m1055a(int i) {
        LocationManager locationManager = this.f1253b;
        if (locationManager == null) {
            return false;
        }
        boolean zIsProviderEnabled = locationManager.isProviderEnabled("gps");
        this.f1255d = zIsProviderEnabled;
        if (!zIsProviderEnabled) {
            if (2 != f1252a) {
                m1053a(2, StatusCodes.MSG_GPS_CLOSED);
            }
            return false;
        }
        try {
            if (this.f1254c == null) {
                this.f1254c = this.f1253b.getLastKnownLocation("gps") != null ? this.f1253b.getLastKnownLocation("gps") : new Location("gps");
            }
            this.f1253b.addGpsStatusListener(this.f1261j);
            this.f1253b.requestLocationUpdates(this.f1254c.getProvider(), i, this.f1257f, this);
        } catch (Exception unused) {
        }
        if (this.f1254c != null) {
            return true;
        }
        if (1 != f1252a) {
            m1053a(1, StatusCodes.MSG_GPS_SEARCHING);
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x000e  */
    @Override // android.location.LocationListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onLocationChanged(android.location.Location r11) {
        /*
            Method dump skipped, instruction units count: 230
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.trace.C0812ak.onLocationChanged(android.location.Location):void");
    }

    @Override // android.location.LocationListener
    public final void onProviderDisabled(String str) {
    }

    @Override // android.location.LocationListener
    public final void onProviderEnabled(String str) {
    }

    @Override // android.location.LocationListener
    public final void onStatusChanged(String str, int i, Bundle bundle) {
    }
}
