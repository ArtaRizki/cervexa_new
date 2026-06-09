package com.baidu.mapapi.utils;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.baidu.mapframework.open.aidl.IComOpenClient;
import com.baidu.mapframework.open.aidl.InterfaceC0717b;

/* JADX INFO: renamed from: com.baidu.mapapi.utils.d */
/* JADX INFO: loaded from: classes.dex */
class BinderC0711d extends InterfaceC0717b.a {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ ServiceConnectionC0710c f749a;

    BinderC0711d(ServiceConnectionC0710c serviceConnectionC0710c) {
        this.f749a = serviceConnectionC0710c;
    }

    @Override // com.baidu.mapframework.open.aidl.InterfaceC0717b
    /* JADX INFO: renamed from: a */
    public void mo497a(IBinder iBinder) throws RemoteException {
        Log.d(C0708a.f728c, "onClientReady");
        if (C0708a.f730e != null) {
            IComOpenClient unused = C0708a.f730e = null;
        }
        IComOpenClient unused2 = C0708a.f730e = IComOpenClient.AbstractBinderC0715a.m507a(iBinder);
        if (!C0708a.f745t) {
            C0708a.m466a(C0708a.f726a);
        }
        boolean unused3 = C0708a.f745t = true;
    }
}
