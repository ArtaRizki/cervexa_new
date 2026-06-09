package com.baidu.p013vi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: renamed from: com.baidu.vi.a */
/* JADX INFO: loaded from: classes.dex */
final class C0883a extends BroadcastReceiver {
    C0883a() {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        VDeviceAPI.onNetworkStateChanged();
    }
}
