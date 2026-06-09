package com.tencent.bugly.crashreport.crash;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.serenegiant.net.NetworkChangedReceiver;
import com.tencent.bugly.crashreport.biz.C1956b;
import com.tencent.bugly.crashreport.common.info.C1958a;
import com.tencent.bugly.crashreport.common.info.C1959b;
import com.tencent.bugly.crashreport.common.strategy.C1961a;
import com.tencent.bugly.proguard.C2018u;
import com.tencent.bugly.proguard.C2021x;
import com.tencent.bugly.proguard.C2023z;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public class BuglyBroadcastReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: d */
    private static BuglyBroadcastReceiver f2457d;

    /* JADX INFO: renamed from: b */
    private Context f2459b;

    /* JADX INFO: renamed from: c */
    private String f2460c;

    /* JADX INFO: renamed from: e */
    private boolean f2461e = true;

    /* JADX INFO: renamed from: a */
    private IntentFilter f2458a = new IntentFilter();

    public static synchronized BuglyBroadcastReceiver getInstance() {
        if (f2457d == null) {
            f2457d = new BuglyBroadcastReceiver();
        }
        return f2457d;
    }

    public synchronized void addFilter(String str) {
        if (!this.f2458a.hasAction(str)) {
            this.f2458a.addAction(str);
        }
        C2021x.m1871c("add action %s", str);
    }

    public synchronized void register(Context context) {
        this.f2459b = context;
        C2023z.m1913a(new Runnable() { // from class: com.tencent.bugly.crashreport.crash.BuglyBroadcastReceiver.1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    C2021x.m1865a(BuglyBroadcastReceiver.f2457d.getClass(), "Register broadcast receiver of Bugly.", new Object[0]);
                    synchronized (this) {
                        BuglyBroadcastReceiver.this.f2459b.registerReceiver(BuglyBroadcastReceiver.f2457d, BuglyBroadcastReceiver.this.f2458a, "com.tencent.bugly.BuglyBroadcastReceiver.permission", null);
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    public synchronized void unregister(Context context) {
        try {
            C2021x.m1865a(getClass(), "Unregister broadcast receiver of Bugly.", new Object[0]);
            context.unregisterReceiver(this);
            this.f2459b = context;
        } catch (Throwable th) {
            if (C2021x.m1867a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        try {
            m1557a(context, intent);
        } catch (Throwable th) {
            if (C2021x.m1867a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    /* JADX INFO: renamed from: a */
    private synchronized boolean m1557a(Context context, Intent intent) {
        if (context != null && intent != null) {
            if (intent.getAction().equals(NetworkChangedReceiver.ACTION_GLOBAL_CONNECTIVITY_CHANGE)) {
                if (this.f2461e) {
                    this.f2461e = false;
                    return true;
                }
                String strM1520b = C1959b.m1520b(this.f2459b);
                C2021x.m1871c("is Connect BC " + strM1520b, new Object[0]);
                C2021x.m1866a("network %s changed to %s", this.f2460c, strM1520b);
                if (strM1520b == null) {
                    this.f2460c = null;
                    return true;
                }
                String str = this.f2460c;
                this.f2460c = strM1520b;
                long jCurrentTimeMillis = System.currentTimeMillis();
                C1961a c1961aM1544a = C1961a.m1544a();
                C2018u c2018uM1839a = C2018u.m1839a();
                C1958a c1958aM1471a = C1958a.m1471a(context);
                if (c1961aM1544a != null && c2018uM1839a != null && c1958aM1471a != null) {
                    if (!strM1520b.equals(str) && jCurrentTimeMillis - c2018uM1839a.m1846a(C1972c.f2565a) > 30000) {
                        C2021x.m1866a("try to upload crash on network changed.", new Object[0]);
                        C1972c c1972cM1609a = C1972c.m1609a();
                        if (c1972cM1609a != null) {
                            c1972cM1609a.m1614a(0L);
                        }
                        C2021x.m1866a("try to upload userinfo on network changed.", new Object[0]);
                        C1956b.f2337a.m1439b();
                    }
                    return true;
                }
                C2021x.m1872d("not inited BC not work", new Object[0]);
                return true;
            }
        }
        return false;
    }
}
