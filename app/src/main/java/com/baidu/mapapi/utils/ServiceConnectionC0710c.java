package com.baidu.mapapi.utils;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.baidu.mapframework.open.aidl.InterfaceC0716a;

/* JADX INFO: renamed from: com.baidu.mapapi.utils.c */
/* JADX INFO: loaded from: classes.dex */
final class ServiceConnectionC0710c implements ServiceConnection {
    ServiceConnectionC0710c() {
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (C0708a.f747v != null) {
            C0708a.f747v.interrupt();
        }
        Log.d(C0708a.f728c, "onServiceConnected " + componentName);
        try {
            if (C0708a.f729d != null) {
                InterfaceC0716a unused = C0708a.f729d = null;
            }
            InterfaceC0716a unused2 = C0708a.f729d = InterfaceC0716a.a.m509a(iBinder);
            C0708a.f729d.mo508a(new BinderC0711d(this));
        } catch (RemoteException e) {
            Log.d(C0708a.f728c, "getComOpenClient ", e);
            if (C0708a.f729d != null) {
                InterfaceC0716a unused3 = C0708a.f729d = null;
            }
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        Log.d(C0708a.f728c, "onServiceDisconnected " + componentName);
        if (C0708a.f729d != null) {
            InterfaceC0716a unused = C0708a.f729d = null;
            boolean unused2 = C0708a.f746u = false;
        }
    }
}
