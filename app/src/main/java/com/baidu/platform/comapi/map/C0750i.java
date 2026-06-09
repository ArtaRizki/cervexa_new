package com.baidu.platform.comapi.map;

import android.content.Context;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.NetworkUtil;
import com.baidu.mapapi.common.BaiduMapSDKException;
import com.baidu.p013vi.VMsg;
import com.baidu.platform.comapi.NativeLoader;
import com.baidu.platform.comapi.commonutils.SysUpdateUtil;
import com.baidu.platform.comapi.util.SysUpdateObservable;
import com.baidu.platform.comjni.engine.AppEngine;

/* JADX INFO: renamed from: com.baidu.platform.comapi.map.i */
/* JADX INFO: loaded from: classes.dex */
public class C0750i {

    /* JADX INFO: renamed from: a */
    private static int f982a;

    /* JADX INFO: renamed from: b */
    private static Context f983b = BMapManager.getContext();

    static {
        if (!com.baidu.mapapi.VersionInfo.getApiVersion().equals(VersionInfo.getApiVersion())) {
            throw new BaiduMapSDKException("the version of map is not match with base");
        }
        NativeLoader.getInstance().loadLibrary(VersionInfo.getKitName());
        AppEngine.InitClass();
        m707a(BMapManager.getContext());
        SysUpdateObservable.getInstance().addObserver(new SysUpdateUtil());
        SysUpdateObservable.getInstance().init();
    }

    /* JADX INFO: renamed from: a */
    public static void m706a() {
        if (f982a == 0) {
            if (f983b == null) {
                throw new IllegalStateException("you have not supplyed the global app context info from SDKInitializer.initialize(Context) function.");
            }
            VMsg.init();
            AppEngine.InitEngine(f983b);
            AppEngine.StartSocketProc();
            NetworkUtil.updateNetworkProxy(f983b);
        }
        f982a++;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x00e3  */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void m707a(android.content.Context r21) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 431
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.platform.comapi.map.C0750i.m707a(android.content.Context):void");
    }

    /* JADX INFO: renamed from: a */
    public static void m708a(boolean z) {
        C0746e.m616j(z);
    }

    /* JADX INFO: renamed from: b */
    public static void m709b() {
        int i = f982a - 1;
        f982a = i;
        if (i == 0) {
            AppEngine.UnInitEngine();
            VMsg.destroy();
        }
    }
}
