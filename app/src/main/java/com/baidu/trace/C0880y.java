package com.baidu.trace;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import java.util.List;

/* JADX INFO: renamed from: com.baidu.trace.y */
/* JADX INFO: loaded from: classes.dex */
public final class C0880y {

    /* JADX INFO: renamed from: a */
    private WifiManager f1862a;

    /* JADX INFO: renamed from: b */
    private List<ScanResult> f1863b;

    /* JADX INFO: renamed from: c */
    private List<ScanResult> f1864c;

    /* JADX INFO: renamed from: d */
    private long f1865d;

    protected C0880y(Context context) {
        this.f1862a = (WifiManager) context.getSystemService("wifi");
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0093  */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final void m1284a(java.util.ArrayList<com.baidu.trace.p010a.C0801j> r9) {
        /*
            Method dump skipped, instruction units count: 332
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.trace.C0880y.m1284a(java.util.ArrayList):void");
    }

    /* JADX INFO: renamed from: a */
    protected final boolean m1285a() {
        WifiManager wifiManager = this.f1862a;
        if (wifiManager == null) {
            return false;
        }
        boolean zIsWifiEnabled = wifiManager.isWifiEnabled();
        return (zIsWifiEnabled || Build.VERSION.SDK_INT < 18) ? zIsWifiEnabled : this.f1862a.isScanAlwaysAvailable();
    }

    /* JADX INFO: renamed from: b */
    protected final void m1286b() {
        List<ScanResult> list = this.f1863b;
        if (list != null) {
            list.clear();
            this.f1863b = null;
        }
        List<ScanResult> list2 = this.f1864c;
        if (list2 != null) {
            list2.clear();
            this.f1864c = null;
        }
    }
}
