package com.baidu.mapapi.utils;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.baidu.mapframework.open.aidl.IComOpenClient;
import com.baidu.mapframework.open.aidl.InterfaceC0717b;

/* JADX INFO: renamed from: com.baidu.mapapi.utils.b */
/* JADX INFO: loaded from: classes.dex */
final class BinderC0709b extends InterfaceC0717b.a {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ int f748a;

    BinderC0709b(int i) {
        this.f748a = i;
    }

    @Override // com.baidu.mapframework.open.aidl.InterfaceC0717b
    /* JADX INFO: renamed from: a */
    public void mo497a(IBinder iBinder) throws RemoteException {
        Log.d(C0708a.f728c, "onClientReady");
        if (C0708a.f730e != null) {
            IComOpenClient unused = C0708a.f730e = null;
        }
        IComOpenClient unused2 = C0708a.f730e = IComOpenClient.AbstractBinderC0715a.m507a(iBinder);
        C0708a.m466a(this.f748a);
        boolean unused3 = C0708a.f745t = true;
    }
}
