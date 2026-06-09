package com.baidu.platform.comapi.commonutils;

import android.content.Context;
import com.baidu.mapapi.NetworkUtil;
import com.baidu.platform.comapi.util.SysUpdateObserver;
import com.baidu.platform.comjni.map.commonmemcache.C0783a;

/* JADX INFO: loaded from: classes.dex */
public class SysUpdateUtil implements SysUpdateObserver {

    /* JADX INFO: renamed from: a */
    static C0783a f795a = new C0783a();

    /* JADX INFO: renamed from: b */
    public static boolean f796b = false;

    /* JADX INFO: renamed from: c */
    public static String f797c = "";

    /* JADX INFO: renamed from: d */
    public static int f798d = 0;

    @Override // com.baidu.platform.comapi.util.SysUpdateObserver
    public void init() {
        C0783a c0783a = f795a;
        if (c0783a != null) {
            c0783a.m881a();
            f795a.m882b();
        }
    }

    @Override // com.baidu.platform.comapi.util.SysUpdateObserver
    public void updateNetworkInfo(Context context) {
        NetworkUtil.updateNetworkProxy(context);
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x00c2, code lost:
    
        if ("10.0.0.200".equals(r9.trim()) != false) goto L50;
     */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00d5  */
    @Override // com.baidu.platform.comapi.util.SysUpdateObserver
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void updateNetworkProxy(android.content.Context r9) {
        /*
            Method dump skipped, instruction units count: 217
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.platform.comapi.commonutils.SysUpdateUtil.updateNetworkProxy(android.content.Context):void");
    }

    @Override // com.baidu.platform.comapi.util.SysUpdateObserver
    public void updatePhoneInfo() {
        C0783a c0783a = f795a;
        if (c0783a != null) {
            c0783a.m882b();
        }
    }
}
