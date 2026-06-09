package com.baidu.platform.comapi;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.VersionInfo;
import com.baidu.platform.comapi.util.C0779e;
import com.baidu.platform.comapi.util.PermissionCheck;
import com.baidu.platform.comapi.util.SysUpdateObservable;
import com.baidu.platform.comjni.tools.C0785a;
import com.serenegiant.net.NetworkChangedReceiver;

/* JADX INFO: renamed from: com.baidu.platform.comapi.a */
/* JADX INFO: loaded from: classes.dex */
public class C0720a implements PermissionCheck.InterfaceC0774c {

    /* JADX INFO: renamed from: f */
    private static C0720a f786f;

    /* JADX INFO: renamed from: b */
    private Context f788b;

    /* JADX INFO: renamed from: c */
    private Handler f789c;

    /* JADX INFO: renamed from: d */
    private C0725d f790d;

    /* JADX INFO: renamed from: e */
    private int f791e;

    /* JADX INFO: renamed from: a */
    private static final String f785a = C0720a.class.getSimpleName();

    /* JADX INFO: renamed from: g */
    private static int f787g = -100;

    static {
        NativeLoader.getInstance().loadLibrary(VersionInfo.getKitName());
        C0785a.m901b();
    }

    private C0720a() {
    }

    /* JADX INFO: renamed from: a */
    public static C0720a m521a() {
        if (f786f == null) {
            f786f = new C0720a();
        }
        return f786f;
    }

    /* JADX INFO: renamed from: f */
    private void m522f() {
        C0725d c0725d;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NetworkChangedReceiver.ACTION_GLOBAL_CONNECTIVITY_CHANGE);
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        Context context = this.f788b;
        if (context == null || (c0725d = this.f790d) == null) {
            return;
        }
        context.registerReceiver(c0725d, intentFilter);
    }

    /* JADX INFO: renamed from: g */
    private void m523g() {
        Context context;
        C0725d c0725d = this.f790d;
        if (c0725d == null || (context = this.f788b) == null) {
            return;
        }
        context.unregisterReceiver(c0725d);
    }

    /* JADX INFO: renamed from: a */
    public void m524a(Context context) {
        this.f788b = context;
    }

    /* JADX INFO: renamed from: a */
    public void m525a(Message message) {
        Intent intent;
        if (message.what != 2012) {
            if (message.arg2 == 3) {
                this.f788b.sendBroadcast(new Intent(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR));
            }
            if (message.arg2 != 2 && message.arg2 != 404 && message.arg2 != 5 && message.arg2 != 8) {
                return;
            } else {
                intent = new Intent(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
            }
        } else if (message.arg1 == 0) {
            intent = new Intent(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK);
        } else {
            Intent intent2 = new Intent(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
            intent2.putExtra(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE, message.arg1);
            intent = intent2;
        }
        this.f788b.sendBroadcast(intent);
    }

    @Override // com.baidu.platform.comapi.util.PermissionCheck.InterfaceC0774c
    /* JADX INFO: renamed from: a */
    public void mo526a(PermissionCheck.C0773b c0773b) {
        if (c0773b == null) {
            return;
        }
        if (c0773b.f1055a == 0) {
            C0779e.f1112z = c0773b.f1059e;
            C0779e.m798a(c0773b.f1056b, c0773b.f1057c);
        } else {
            Log.e("baidumapsdk", "Authentication Error\n" + c0773b.toString());
        }
        if (this.f789c == null || c0773b.f1055a == f787g) {
            return;
        }
        f787g = c0773b.f1055a;
        Message.obtain(this.f789c, 2012, c0773b.f1055a, c0773b.f1055a, null).sendToTarget();
    }

    /* JADX INFO: renamed from: b */
    public void m527b() {
        if (this.f791e == 0) {
            if (this.f788b == null) {
                throw new IllegalStateException("you have not supplyed the global app context info from SDKInitializer.initialize(Context) function.");
            }
            this.f790d = new C0725d();
            m522f();
            SysUpdateObservable.getInstance().updateNetworkInfo(this.f788b);
        }
        this.f791e++;
    }

    /* JADX INFO: renamed from: c */
    public boolean m528c() {
        if (this.f788b == null) {
            throw new IllegalStateException("you have not supplyed the global app context info from SDKInitializer.initialize(Context) function.");
        }
        this.f789c = new HandlerC0722b(this);
        C0779e.m801b(this.f788b);
        C0779e.m808f();
        PermissionCheck.init(this.f788b);
        PermissionCheck.setPermissionCheckResultListener(this);
        PermissionCheck.permissionCheck();
        return true;
    }

    /* JADX INFO: renamed from: d */
    public void m529d() {
        int i = this.f791e - 1;
        this.f791e = i;
        if (i == 0) {
            m523g();
            C0779e.m796a();
        }
    }

    /* JADX INFO: renamed from: e */
    public Context m530e() {
        Context context = this.f788b;
        if (context != null) {
            return context;
        }
        throw new IllegalStateException("you have not supplyed the global app context info from SDKInitializer.initialize(Context) function.");
    }
}
