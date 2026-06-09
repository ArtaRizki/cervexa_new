package com.baidu.trace;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.CellInfo;
import android.telephony.TelephonyManager;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;
import kotlin.UByte;

/* JADX INFO: renamed from: com.baidu.trace.f */
/* JADX INFO: loaded from: classes.dex */
public final class C0860f {

    /* JADX INFO: renamed from: d */
    private static Method f1769d;

    /* JADX INFO: renamed from: e */
    private static Method f1770e;

    /* JADX INFO: renamed from: f */
    private static Method f1771f;

    /* JADX INFO: renamed from: g */
    private static Class<?> f1772g;

    /* JADX INFO: renamed from: m */
    private static char[] f1773m = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_.".toCharArray();

    /* JADX INFO: renamed from: a */
    private Context f1774a;

    /* JADX INFO: renamed from: b */
    private TelephonyManager f1775b;

    /* JADX INFO: renamed from: h */
    private WifiManager f1777h;

    /* JADX INFO: renamed from: l */
    private String f1781l;

    /* JADX INFO: renamed from: c */
    private C0791a f1776c = new C0791a(this, (byte) 0);

    /* JADX INFO: renamed from: i */
    private a f1778i = null;

    /* JADX INFO: renamed from: j */
    private String f1779j = null;

    /* JADX INFO: renamed from: k */
    private int f1780k = 0;

    /* JADX INFO: renamed from: com.baidu.trace.f$a */
    public class a {

        /* JADX INFO: renamed from: a */
        public List<ScanResult> f1782a;

        /* JADX INFO: renamed from: c */
        private long f1784c;

        public a(List<ScanResult> list) {
            this.f1782a = null;
            this.f1784c = 0L;
            this.f1782a = list;
            this.f1784c = System.currentTimeMillis();
            m1259b();
        }

        /* JADX INFO: renamed from: a */
        static /* synthetic */ boolean m1258a(a aVar) {
            long jCurrentTimeMillis = System.currentTimeMillis() - aVar.f1784c;
            return jCurrentTimeMillis < 0 || jCurrentTimeMillis > 500;
        }

        /*  JADX ERROR: JadxOverflowException in pass: LoopRegionVisitor
            jadx.core.utils.exceptions.JadxOverflowException: LoopRegionVisitor.assignOnlyInLoop endless recursion
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        /* JADX INFO: renamed from: b */
        private void m1259b() {
            /*
                r7 = this;
                int r0 = r7.m1260a()
                if (r0 > 0) goto L7
                return
            L7:
                java.util.List<android.net.wifi.ScanResult> r0 = r7.f1782a
                int r0 = r0.size()
                r1 = 1
                int r0 = r0 - r1
                r2 = 1
            L10:
                if (r0 <= 0) goto L4d
                if (r2 == 0) goto L4d
                r2 = 0
                r3 = 0
            L16:
                if (r2 >= r0) goto L49
                java.util.List<android.net.wifi.ScanResult> r4 = r7.f1782a
                java.lang.Object r4 = r4.get(r2)
                android.net.wifi.ScanResult r4 = (android.net.wifi.ScanResult) r4
                int r4 = r4.level
                java.util.List<android.net.wifi.ScanResult> r5 = r7.f1782a
                int r6 = r2 + 1
                java.lang.Object r5 = r5.get(r6)
                android.net.wifi.ScanResult r5 = (android.net.wifi.ScanResult) r5
                int r5 = r5.level
                if (r4 >= r5) goto L47
                java.util.List<android.net.wifi.ScanResult> r3 = r7.f1782a
                java.lang.Object r3 = r3.get(r6)
                android.net.wifi.ScanResult r3 = (android.net.wifi.ScanResult) r3
                java.util.List<android.net.wifi.ScanResult> r4 = r7.f1782a
                java.lang.Object r5 = r4.get(r2)
                r4.set(r6, r5)
                java.util.List<android.net.wifi.ScanResult> r4 = r7.f1782a
                r4.set(r2, r3)
                r3 = 1
            L47:
                r2 = r6
                goto L16
            L49:
                int r0 = r0 + (-1)
                r2 = r3
                goto L10
            L4d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.trace.C0860f.a.m1259b():void");
        }

        /* JADX INFO: renamed from: a */
        public final int m1260a() {
            List<ScanResult> list = this.f1782a;
            if (list == null) {
                return 0;
            }
            return list.size();
        }
    }

    public C0860f(Context context) {
        String deviceId = null;
        this.f1774a = null;
        this.f1775b = null;
        this.f1777h = null;
        this.f1781l = null;
        Context applicationContext = context.getApplicationContext();
        this.f1774a = applicationContext;
        String packageName = applicationContext.getPackageName();
        try {
            TelephonyManager telephonyManager = (TelephonyManager) this.f1774a.getSystemService("phone");
            this.f1775b = telephonyManager;
            deviceId = telephonyManager.getDeviceId();
        } catch (Exception unused) {
        }
        this.f1781l = "&" + packageName + "&" + deviceId;
        this.f1777h = (WifiManager) this.f1774a.getSystemService("wifi");
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x00b0 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00b6 A[Catch: Exception -> 0x00e6, TRY_LEAVE, TryCatch #0 {Exception -> 0x00e6, blocks: (B:20:0x00b2, B:22:0x00b6), top: B:25:0x00b2 }] */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.baidu.trace.C0791a m1250a(android.telephony.CellInfo r7) {
        /*
            Method dump skipped, instruction units count: 231
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.trace.C0860f.m1250a(android.telephony.CellInfo):com.baidu.trace.a");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(14:0|2|117|3|(2:9|(6:14|(8:113|18|(3:20|(1:22)|23)|24|(3:26|(2:31|27)|128)(1:32)|33|(1:35)|36)|37|(1:39)(12:40|(2:42|(4:119|44|47|(0)(4:115|51|(1:53)|54))(2:47|(1:49)))|61|(1:63)|111|64|(1:68)|69|(8:72|(1:74)(1:75)|76|77|(4:80|(2:82|(2:(1:88)|(1:122))(2:86|124))(2:92|123)|93|78)|121|(1:95)(1:96)|(1:98))(1:71)|(1:102)|103|(1:105)(2:107|108))|55|(1:57)))(1:8)|58|61|(0)|111|64|(4:66|68|69|(0)(0))(0)|(0)|103|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:100:0x0210, code lost:
    
        r1 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0213  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0228 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:107:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0137 A[Catch: Exception -> 0x0210, TryCatch #0 {Exception -> 0x0210, blocks: (B:64:0x012b, B:66:0x012f, B:69:0x0144, B:72:0x0150, B:76:0x015d, B:80:0x0171, B:82:0x017d, B:84:0x01a1, B:86:0x01ab, B:93:0x01d3, B:88:0x01b7, B:95:0x01dc, B:98:0x01fc, B:68:0x0137), top: B:111:0x012b }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0150 A[Catch: Exception -> 0x0210, TryCatch #0 {Exception -> 0x0210, blocks: (B:64:0x012b, B:66:0x012f, B:69:0x0144, B:72:0x0150, B:76:0x015d, B:80:0x0171, B:82:0x017d, B:84:0x01a1, B:86:0x01ab, B:93:0x01d3, B:88:0x01b7, B:95:0x01dc, B:98:0x01fc, B:68:0x0137), top: B:111:0x012b }] */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String m1251a(int r18) {
        /*
            Method dump skipped, instruction units count: 588
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.trace.C0860f.m1251a(int):java.lang.String");
    }

    /* JADX INFO: renamed from: a */
    private static String m1252a(String str) {
        if (str == null) {
            return null;
        }
        byte[] bytes = str.getBytes();
        byte bNextInt = (byte) new Random().nextInt(255);
        byte bNextInt2 = (byte) new Random().nextInt(255);
        byte[] bArr = new byte[bytes.length + 2];
        int length = bytes.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            bArr[i2] = (byte) (bytes[i] ^ bNextInt);
            i++;
            i2++;
        }
        bArr[i2] = bNextInt;
        bArr[i2 + 1] = bNextInt2;
        return m1253a(bArr);
    }

    /* JADX INFO: renamed from: a */
    private static String m1253a(byte[] bArr) {
        boolean z;
        char[] cArr = new char[((bArr.length + 2) / 3) << 2];
        int i = 0;
        int i2 = 0;
        while (i < bArr.length) {
            int i3 = (bArr[i] & UByte.MAX_VALUE) << 8;
            int i4 = i + 1;
            boolean z2 = true;
            if (i4 < bArr.length) {
                i3 |= bArr[i4] & UByte.MAX_VALUE;
                z = true;
            } else {
                z = false;
            }
            int i5 = i3 << 8;
            int i6 = i + 2;
            if (i6 < bArr.length) {
                i5 |= bArr[i6] & UByte.MAX_VALUE;
            } else {
                z2 = false;
            }
            int i7 = 64;
            cArr[i2 + 3] = f1773m[z2 ? 63 - (i5 & 63) : 64];
            int i8 = i5 >> 6;
            int i9 = i2 + 2;
            char[] cArr2 = f1773m;
            if (z) {
                i7 = 63 - (i8 & 63);
            }
            cArr[i9] = cArr2[i7];
            int i10 = i8 >> 6;
            char[] cArr3 = f1773m;
            cArr[i2 + 1] = cArr3[63 - (i10 & 63)];
            cArr[i2] = cArr3[63 - ((i10 >> 6) & 63)];
            i += 3;
            i2 += 4;
        }
        return new String(cArr);
    }

    /* JADX INFO: renamed from: b */
    private static int m1254b(int i) {
        if (i == Integer.MAX_VALUE) {
            return -1;
        }
        return i;
    }

    /* JADX INFO: renamed from: b */
    private C0791a m1255b() {
        if (Integer.valueOf(Build.VERSION.SDK_INT).intValue() < 17) {
            return null;
        }
        try {
            List<CellInfo> allCellInfo = this.f1775b.getAllCellInfo();
            if (allCellInfo == null || allCellInfo.size() <= 0) {
                return null;
            }
            C0791a c0791aM1250a = null;
            for (CellInfo cellInfo : allCellInfo) {
                try {
                    if (cellInfo.isRegistered() && (c0791aM1250a = m1250a(cellInfo)) != null) {
                        if (c0791aM1250a.m1028b()) {
                            return c0791aM1250a;
                        }
                        return null;
                    }
                } catch (Exception unused) {
                }
                return c0791aM1250a;
            }
            return c0791aM1250a;
        } catch (Exception | NoSuchMethodError unused2) {
            return null;
        }
    }

    /* JADX INFO: renamed from: c */
    private boolean m1256c() {
        this.f1779j = null;
        this.f1780k = 0;
        WifiInfo connectionInfo = this.f1777h.getConnectionInfo();
        if (connectionInfo == null) {
            return false;
        }
        try {
            String bssid = connectionInfo.getBSSID();
            String strReplace = bssid != null ? bssid.replace(":", "") : "";
            if (strReplace.length() != 12) {
                return false;
            }
            this.f1779j = new String(strReplace);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: renamed from: a */
    public final String m1257a() {
        try {
            return m1251a(10);
        } catch (Exception unused) {
            return null;
        }
    }
}
