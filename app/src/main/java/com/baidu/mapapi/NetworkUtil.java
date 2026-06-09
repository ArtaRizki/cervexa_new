package com.baidu.mapapi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.baidu.platform.comapi.util.SysUpdateObservable;

/* JADX INFO: loaded from: classes.dex */
public class NetworkUtil {
    public static NetworkInfo getActiveNetworkInfo(Context context) {
        try {
            return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0031  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getCurrentNetMode(android.content.Context r2) {
        /*
            android.net.NetworkInfo r0 = getActiveNetworkInfo(r2)
            r1 = 1
            if (r0 == 0) goto L31
            int r0 = r0.getType()
            if (r0 != r1) goto Le
            goto L32
        Le:
            java.lang.String r0 = "phone"
            java.lang.Object r2 = r2.getSystemService(r0)
            android.telephony.TelephonyManager r2 = (android.telephony.TelephonyManager) r2
            int r2 = r2.getNetworkType()
            switch(r2) {
                case 1: goto L2f;
                case 2: goto L2f;
                case 3: goto L2c;
                case 4: goto L2a;
                case 5: goto L28;
                case 6: goto L28;
                case 7: goto L28;
                case 8: goto L25;
                case 9: goto L2c;
                case 10: goto L2c;
                case 11: goto L23;
                case 12: goto L28;
                case 13: goto L21;
                case 14: goto L1e;
                case 15: goto L2c;
                default: goto L1d;
            }
        L1d:
            goto L31
        L1e:
            r1 = 10
            goto L32
        L21:
            r1 = 4
            goto L32
        L23:
            r1 = 2
            goto L32
        L25:
            r1 = 8
            goto L32
        L28:
            r1 = 7
            goto L32
        L2a:
            r1 = 5
            goto L32
        L2c:
            r1 = 9
            goto L32
        L2f:
            r1 = 6
            goto L32
        L31:
            r1 = 0
        L32:
            java.lang.String r2 = java.lang.Integer.toString(r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapapi.NetworkUtil.getCurrentNetMode(android.content.Context):java.lang.String");
    }

    public static boolean initConnectState() {
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean isWifiConnected(android.net.NetworkInfo r3) {
        /*
            r0 = 1
            r1 = 0
            if (r3 == 0) goto L16
            int r2 = r3.getType()     // Catch: java.lang.Exception -> L11
            if (r0 != r2) goto L16
            boolean r3 = r3.isConnected()     // Catch: java.lang.Exception -> L11
            if (r3 == 0) goto L16
            goto L17
        L11:
            r3 = move-exception
            r3.printStackTrace()
            goto L18
        L16:
            r0 = 0
        L17:
            r1 = r0
        L18:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapapi.NetworkUtil.isWifiConnected(android.net.NetworkInfo):boolean");
    }

    public static void updateNetworkProxy(Context context) {
        SysUpdateObservable.getInstance().updateNetworkProxy(context);
    }
}
