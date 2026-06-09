package com.tencent.bugly;

import android.content.Context;
import android.text.TextUtils;
import com.serenegiant.net.NetworkChangedReceiver;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.bugly.crashreport.common.info.C1958a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.BuglyBroadcastReceiver;
import com.tencent.bugly.crashreport.crash.C1972c;
import com.tencent.bugly.crashreport.crash.C1973d;
import com.tencent.bugly.proguard.C2011n;
import com.tencent.bugly.proguard.C2021x;
import com.tencent.bugly.proguard.InterfaceC2012o;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public class CrashModule extends AbstractC1949a {
    public static final int MODULE_ID = 1004;

    /* JADX INFO: renamed from: c */
    private static int f2291c;

    /* JADX INFO: renamed from: e */
    private static CrashModule f2292e = new CrashModule();

    /* JADX INFO: renamed from: a */
    private long f2293a;

    /* JADX INFO: renamed from: b */
    private BuglyStrategy.C1948a f2294b;

    /* JADX INFO: renamed from: d */
    private boolean f2295d = false;

    public static CrashModule getInstance() {
        f2292e.f2296id = 1004;
        return f2292e;
    }

    public synchronized boolean hasInitialized() {
        return this.f2295d;
    }

    @Override // com.tencent.bugly.AbstractC1949a
    public synchronized void init(Context context, boolean z, BuglyStrategy buglyStrategy) {
        if (context != null) {
            if (!this.f2295d) {
                C2021x.m1866a("Initializing crash module.", new Object[0]);
                C2011n c2011nM1785a = C2011n.m1785a();
                int i = f2291c + 1;
                f2291c = i;
                c2011nM1785a.m1796a(1004, i);
                this.f2295d = true;
                CrashReport.setContext(context);
                m1417a(context, buglyStrategy);
                C1972c c1972cM1610a = C1972c.m1610a(1004, context, z, this.f2294b, (InterfaceC2012o) null, (String) null);
                c1972cM1610a.m1624f();
                if (buglyStrategy != null) {
                    c1972cM1610a.m1613a(buglyStrategy.getCallBackType());
                    c1972cM1610a.m1618a(buglyStrategy.getCloseErrorCallback());
                }
                if (buglyStrategy != null && buglyStrategy.isEnableCatchAnrTrace()) {
                    c1972cM1610a.m1629k();
                }
                c1972cM1610a.m1633o();
                if (buglyStrategy == null || buglyStrategy.isEnableNativeCrashMonitor()) {
                    c1972cM1610a.m1626h();
                } else {
                    C2021x.m1866a("[crash] Closed native crash monitor!", new Object[0]);
                    c1972cM1610a.m1625g();
                }
                if (buglyStrategy == null || buglyStrategy.isEnableANRCrashMonitor()) {
                    c1972cM1610a.m1627i();
                } else {
                    C2021x.m1866a("[crash] Closed ANR monitor!", new Object[0]);
                    c1972cM1610a.m1628j();
                }
                c1972cM1610a.m1614a(buglyStrategy != null ? buglyStrategy.getAppReportDelay() : 0L);
                c1972cM1610a.m1632n();
                C1973d.m1641a(context);
                BuglyBroadcastReceiver buglyBroadcastReceiver = BuglyBroadcastReceiver.getInstance();
                buglyBroadcastReceiver.addFilter(NetworkChangedReceiver.ACTION_GLOBAL_CONNECTIVITY_CHANGE);
                buglyBroadcastReceiver.register(context);
                C2011n c2011nM1785a2 = C2011n.m1785a();
                int i2 = f2291c - 1;
                f2291c = i2;
                c2011nM1785a2.m1796a(1004, i2);
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private synchronized void m1417a(Context context, BuglyStrategy buglyStrategy) {
        if (buglyStrategy == null) {
            return;
        }
        String libBuglySOFilePath = buglyStrategy.getLibBuglySOFilePath();
        if (!TextUtils.isEmpty(libBuglySOFilePath)) {
            C1958a.m1471a(context).f2410n = libBuglySOFilePath;
            C2021x.m1866a("setted libBugly.so file path :%s", libBuglySOFilePath);
        }
        if (buglyStrategy.getCrashHandleCallback() != null) {
            this.f2294b = buglyStrategy.getCrashHandleCallback();
            C2021x.m1866a("setted CrashHanldeCallback", new Object[0]);
        }
        if (buglyStrategy.getAppReportDelay() > 0) {
            long appReportDelay = buglyStrategy.getAppReportDelay();
            this.f2293a = appReportDelay;
            C2021x.m1866a("setted delay: %d", Long.valueOf(appReportDelay));
        }
    }

    @Override // com.tencent.bugly.AbstractC1949a
    public void onServerStrategyChanged(StrategyBean strategyBean) {
        C1972c c1972cM1609a;
        if (strategyBean == null || (c1972cM1609a = C1972c.m1609a()) == null) {
            return;
        }
        c1972cM1609a.m1615a(strategyBean);
    }

    @Override // com.tencent.bugly.AbstractC1949a
    public String[] getTables() {
        return new String[]{"t_cr"};
    }
}
