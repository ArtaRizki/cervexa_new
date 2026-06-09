package com.baidu.trace.p012c;

import android.app.ActivityManager;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.baidu.trace.TraceJniInterface;
import com.baidu.trace.model.CoordType;
import com.baidu.trace.model.LatLng;
import com.baidu.trace.model.TraceLocation;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;
import java.util.regex.Pattern;

/* JADX INFO: renamed from: com.baidu.trace.c.e */
/* JADX INFO: loaded from: classes.dex */
public final class C0854e {

    /* JADX INFO: renamed from: a */
    public static String f1737a;

    /* JADX INFO: renamed from: b */
    public static String f1738b;

    /* JADX INFO: renamed from: c */
    public static String f1739c;

    /* JADX INFO: renamed from: d */
    public static int f1740d;

    /* JADX INFO: renamed from: e */
    private static final Pattern f1741e = Pattern.compile("^(?!_)[a-zA-Z0-9_\\-]{1,128}");

    /* JADX INFO: renamed from: f */
    private static final Pattern f1742f = Pattern.compile("[a-zA-Z0-9_\\-一-龥]{1,128}");

    /* JADX INFO: renamed from: g */
    private static final SimpleDateFormat f1743g;

    /* JADX INFO: renamed from: h */
    private static List<ScanResult> f1744h;

    /* JADX INFO: renamed from: i */
    private static List<ScanResult> f1745i;

    /* JADX INFO: renamed from: j */
    private static WifiManager f1746j;

    /* JADX INFO: renamed from: k */
    private static boolean f1747k;

    static {
        new DecimalFormat("#0.##");
        f1743g = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        f1746j = null;
        f1737a = "4G";
        f1738b = "yingyan";
        f1739c = "com.baidu.trace";
        f1740d = 0;
        f1747k = false;
    }

    /* JADX INFO: renamed from: a */
    private static double m1222a(double d) {
        return (d * 3.141592653589793d) / 180.0d;
    }

    /* JADX INFO: renamed from: a */
    public static double m1223a(LatLng latLng, LatLng latLng2) {
        double dM1222a = m1222a(latLng.latitude);
        double dM1222a2 = m1222a(latLng2.latitude);
        return Math.round(((Math.asin(Math.sqrt(Math.pow(Math.sin((dM1222a - dM1222a2) / 2.0d), 2.0d) + ((Math.cos(dM1222a) * Math.cos(dM1222a2)) * Math.pow(Math.sin((m1222a(latLng.longitude) - m1222a(latLng2.longitude)) / 2.0d), 2.0d)))) * 2.0d) * 6378.137d) * 10000.0d) / 10;
    }

    /* JADX INFO: renamed from: a */
    public static int m1224a() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    /* JADX INFO: renamed from: a */
    public static void m1225a(int i, int i2, TreeMap<String, String> treeMap) {
        if (i > 0) {
            treeMap.put("page_index", String.valueOf(i));
        }
        if (i2 > 0) {
            treeMap.put("page_size", String.valueOf(i2));
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:29:0x006a  */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void m1226a(android.content.Context r4) {
        /*
            boolean r0 = com.baidu.trace.p012c.C0854e.f1747k
            if (r0 == 0) goto L5
            return
        L5:
            android.content.pm.PackageManager r0 = r4.getPackageManager()
            android.content.pm.ApplicationInfo r1 = r4.getApplicationInfo()
            java.lang.CharSequence r0 = r1.loadLabel(r0)
            java.lang.String r0 = r0.toString()
            com.baidu.trace.p012c.C0854e.f1738b = r0
            java.lang.String r0 = "connectivity"
            java.lang.Object r0 = r4.getSystemService(r0)
            android.net.ConnectivityManager r0 = (android.net.ConnectivityManager) r0
            r1 = 1
            if (r0 != 0) goto L23
            goto L72
        L23:
            android.net.NetworkInfo r0 = r0.getActiveNetworkInfo()
            if (r0 == 0) goto L72
            boolean r2 = r0.isAvailable()
            if (r2 != 0) goto L30
            goto L72
        L30:
            int r2 = r0.getType()
            if (r1 != r2) goto L3b
            java.lang.String r0 = "WIFI"
        L38:
            com.baidu.trace.p012c.C0854e.f1737a = r0
            goto L72
        L3b:
            int r2 = r0.getType()
            if (r2 != 0) goto L72
            java.lang.String r2 = r0.getSubtypeName()
            int r0 = r0.getSubtype()
            java.lang.String r3 = "3G"
            switch(r0) {
                case 1: goto L6d;
                case 2: goto L6d;
                case 3: goto L6a;
                case 4: goto L6d;
                case 5: goto L6a;
                case 6: goto L6a;
                case 7: goto L6d;
                case 8: goto L6a;
                case 9: goto L6a;
                case 10: goto L6a;
                case 11: goto L6d;
                case 12: goto L6a;
                case 13: goto L67;
                case 14: goto L6a;
                case 15: goto L6a;
                default: goto L4e;
            }
        L4e:
            java.lang.String r0 = "TD-SCDMA"
            boolean r0 = r2.equalsIgnoreCase(r0)
            if (r0 != 0) goto L6a
            java.lang.String r0 = "WCDMA"
            boolean r0 = r2.equalsIgnoreCase(r0)
            if (r0 != 0) goto L6a
            java.lang.String r0 = "CDMA2000"
            boolean r0 = r2.equalsIgnoreCase(r0)
            if (r0 == 0) goto L70
            goto L6a
        L67:
            java.lang.String r0 = "4G"
            goto L38
        L6a:
            com.baidu.trace.p012c.C0854e.f1737a = r3
            goto L72
        L6d:
            java.lang.String r0 = "2G"
            goto L38
        L70:
            com.baidu.trace.p012c.C0854e.f1737a = r2
        L72:
            m1238c(r4)
            com.baidu.trace.p012c.C0854e.f1747k = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.trace.p012c.C0854e.m1226a(android.content.Context):void");
    }

    /* JADX INFO: renamed from: a */
    public static void m1227a(TraceLocation traceLocation) {
        CoordType coordType;
        double[] dArrWgsToBaidu = TraceJniInterface.wgsToBaidu(traceLocation.getLatitude(), traceLocation.getLongitude());
        if (dArrWgsToBaidu == null || 2 != dArrWgsToBaidu.length) {
            coordType = CoordType.wgs84;
        } else {
            traceLocation.setLatitude(dArrWgsToBaidu[0]);
            traceLocation.setLongitude(dArrWgsToBaidu[1]);
            coordType = CoordType.bd09ll;
        }
        traceLocation.setCoordType(coordType);
    }

    /* JADX INFO: renamed from: a */
    public static boolean m1228a(int i, int i2, int i3) {
        return i >= i2 && i <= i3;
    }

    /* JADX INFO: renamed from: a */
    public static boolean m1229a(Context context, String str) {
        List<ActivityManager.RunningServiceInfo> runningServices;
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null || (runningServices = activityManager.getRunningServices(128)) == null) {
            return false;
        }
        Iterator<ActivityManager.RunningServiceInfo> it = runningServices.iterator();
        while (it.hasNext()) {
            if (str.equals(it.next().service.getClassName().toString())) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: a */
    public static boolean m1230a(LatLng latLng) {
        return latLng != null && 180.0d > Math.abs(latLng.longitude) && 90.0d > Math.abs(latLng.latitude);
    }

    /* JADX INFO: renamed from: a */
    public static boolean m1231a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return f1742f.matcher(str).matches();
    }

    /* JADX INFO: renamed from: a */
    public static boolean m1232a(List<ScanResult> list, List<ScanResult> list2) {
        if (list != null && list2 != null) {
            int size = list.size();
            int size2 = list2.size();
            int i = size + size2;
            if (size != 0 && size2 != 0) {
                int i2 = 0;
                for (int i3 = 0; i3 < size; i3++) {
                    String str = list.get(i3).BSSID;
                    if (str != null) {
                        int i4 = 0;
                        while (true) {
                            if (i4 >= size2) {
                                break;
                            }
                            if (str.equals(list2.get(i4).BSSID)) {
                                i2++;
                                break;
                            }
                            i4++;
                        }
                    }
                }
                int i5 = i2 << 1;
                int i6 = (int) (i * 0.5f);
                if (size > 5 && size2 > 5) {
                    return i5 > i6;
                }
                if (i5 == i) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: b */
    public static long m1233b() {
        return System.currentTimeMillis() / 1000;
    }

    /* JADX INFO: renamed from: b */
    public static String m1234b(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            return (telephonyManager == null || telephonyManager.getSimSerialNumber() == null) ? "" : telephonyManager.getSimSerialNumber();
        } catch (Exception unused) {
        }
        return "";
    }

    /* JADX INFO: renamed from: b */
    public static boolean m1235b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return f1741e.matcher(str).matches();
    }

    /* JADX INFO: renamed from: c */
    public static long m1236c(String str) {
        try {
            return f1743g.parse(str).getTime() / 1000;
        } catch (ParseException unused) {
            return 0L;
        }
    }

    /* JADX INFO: renamed from: c */
    public static String m1237c() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    }

    /* JADX INFO: renamed from: c */
    public static String m1238c(Context context) {
        try {
            f1739c = context.getPackageName();
        } catch (Exception unused) {
        }
        return f1739c;
    }

    /* JADX INFO: renamed from: d */
    public static boolean m1239d(Context context) {
        if (f1746j == null) {
            f1746j = (WifiManager) context.getSystemService("wifi");
        }
        f1746j.startScan();
        f1744h = f1746j.getScanResults();
        List<ScanResult> list = f1745i;
        if (list != null && list.size() != 0) {
            if (m1232a(f1744h, f1745i)) {
                return true;
            }
            f1745i = null;
        }
        f1745i = f1744h;
        return false;
    }

    /* JADX INFO: renamed from: e */
    public static boolean m1240e(Context context) {
        return Build.VERSION.SDK_INT < 23 || context.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0;
    }

    /* JADX INFO: renamed from: f */
    public static boolean m1241f(Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        return (context.checkSelfPermission("android.permission.ACCESS_WIFI_STATE") == 0) && (context.checkSelfPermission("android.permission.CHANGE_WIFI_STATE") == 0);
    }

    /* JADX INFO: renamed from: g */
    public static boolean m1242g(Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        return (context.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0) && (context.checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0);
    }
}
