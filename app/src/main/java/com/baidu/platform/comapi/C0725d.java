package com.baidu.platform.comapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.baidu.mapapi.NetworkUtil;
import com.baidu.platform.comapi.util.C0779e;

/* JADX INFO: renamed from: com.baidu.platform.comapi.d */
/* JADX INFO: loaded from: classes.dex */
public class C0725d extends BroadcastReceiver {

    /* JADX INFO: renamed from: a */
    public static final String f800a = C0725d.class.getSimpleName();

    /* JADX INFO: renamed from: a */
    public void m539a(Context context) {
        String currentNetMode = NetworkUtil.getCurrentNetMode(context);
        if (C0779e.m806e().equals(currentNetMode)) {
            return;
        }
        C0779e.m797a(currentNetMode);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        m539a(context);
        NetworkUtil.updateNetworkProxy(context);
    }
}
