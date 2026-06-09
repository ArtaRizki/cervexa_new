package com.baidu.trace.p011b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.baidu.trace.p012c.C0854e;
import com.serenegiant.net.NetworkChangedReceiver;

/* JADX INFO: renamed from: com.baidu.trace.b.c */
/* JADX INFO: loaded from: classes.dex */
public final class C0831c extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (context == null || intent == null) {
            return;
        }
        String action = intent.getAction();
        if (NetworkChangedReceiver.ACTION_GLOBAL_CONNECTIVITY_CHANGE.equals(action) || "com.baidu.trace.action.SOCKET_RECONNECT".equals(action)) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnected() || !activeNetworkInfo.isAvailable()) {
                C0832d.m1139b(Integer.MIN_VALUE);
                return;
            }
            int type = activeNetworkInfo.getType();
            if (C0832d.m1140h() && C0854e.m1233b() - C0832d.m1141i() > 60) {
                C0832d.m1136a(false);
            }
            if ((C0832d.m1137a(type) || C0832d.m1138a(context)) && !C0832d.m1140h()) {
                C0832d.m1139b(type);
                C0832d.m1136a(true);
                C0832d.m1135a(C0854e.m1233b());
                C0832d.m1134a().m1147d();
            }
        }
    }
}
