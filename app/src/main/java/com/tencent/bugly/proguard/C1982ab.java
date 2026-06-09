package com.tencent.bugly.proguard;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: renamed from: com.tencent.bugly.proguard.ab */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public final class C1982ab extends Thread {

    /* JADX INFO: renamed from: a */
    private boolean f2672a = false;

    /* JADX INFO: renamed from: b */
    private boolean f2673b = false;

    /* JADX INFO: renamed from: c */
    private List<RunnableC1981aa> f2674c = new ArrayList();

    /* JADX INFO: renamed from: d */
    private List<InterfaceC1983ac> f2675d = new ArrayList();

    /* JADX INFO: renamed from: e */
    private ArrayList<RunnableC1981aa> f2676e = new ArrayList<>();

    /* JADX INFO: renamed from: a */
    public final void m1706a() {
        m1704a(new Handler(Looper.getMainLooper()), 5000L);
    }

    /* JADX INFO: renamed from: b */
    public final void m1709b() {
        for (int i = 0; i < this.f2674c.size(); i++) {
            try {
                if (this.f2674c.get(i).m1702d().equals(Looper.getMainLooper().getThread().getName())) {
                    C2021x.m1871c("remove handler::%s", this.f2674c.get(i));
                    this.f2674c.remove(i);
                }
            } catch (Exception e) {
                C2021x.m1870b(e);
                return;
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private void m1704a(Handler handler, long j) {
        if (handler == null) {
            C2021x.m1873e("addThread handler should not be null", new Object[0]);
            return;
        }
        String name = handler.getLooper().getThread().getName();
        for (int i = 0; i < this.f2674c.size(); i++) {
            try {
                if (this.f2674c.get(i).m1702d().equals(handler.getLooper().getThread().getName())) {
                    C2021x.m1873e("addThread fail ,this thread has been added in monitor queue", new Object[0]);
                    return;
                }
            } catch (Exception e) {
                C2021x.m1870b(e);
            }
            this.f2674c.add(new RunnableC1981aa(handler, name, 5000L));
        }
        this.f2674c.add(new RunnableC1981aa(handler, name, 5000L));
    }

    /* JADX INFO: renamed from: c */
    public final boolean m1711c() {
        this.f2672a = true;
        if (!isAlive()) {
            return false;
        }
        try {
            interrupt();
        } catch (Exception e) {
            C2021x.m1870b(e);
        }
        return true;
    }

    /* JADX INFO: renamed from: d */
    public final boolean m1712d() {
        if (isAlive()) {
            return false;
        }
        try {
            start();
            return true;
        } catch (Exception e) {
            C2021x.m1870b(e);
            return false;
        }
    }

    /* JADX INFO: renamed from: e */
    private int m1705e() {
        int iMax = 0;
        for (int i = 0; i < this.f2674c.size(); i++) {
            try {
                iMax = Math.max(iMax, this.f2674c.get(i).m1701c());
            } catch (Exception e) {
                C2021x.m1870b(e);
            }
        }
        return iMax;
    }

    /* JADX INFO: renamed from: a */
    public final void m1707a(InterfaceC1983ac interfaceC1983ac) {
        if (this.f2675d.contains(interfaceC1983ac)) {
            C2021x.m1871c("addThreadMonitorListeners fail ,this threadMonitorListener has been added in monitor queue", new Object[0]);
        } else {
            this.f2675d.add(interfaceC1983ac);
        }
    }

    /* JADX INFO: renamed from: b */
    public final void m1710b(InterfaceC1983ac interfaceC1983ac) {
        this.f2675d.remove(interfaceC1983ac);
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        while (!this.f2672a) {
            for (int i = 0; i < this.f2674c.size(); i++) {
                try {
                    this.f2674c.get(i).m1698a();
                } catch (Exception e) {
                    C2021x.m1870b(e);
                } catch (OutOfMemoryError e2) {
                    C2021x.m1870b(e2);
                }
            }
            long jUptimeMillis = SystemClock.uptimeMillis();
            for (long jUptimeMillis2 = 2000; jUptimeMillis2 > 0 && !isInterrupted(); jUptimeMillis2 = 2000 - (SystemClock.uptimeMillis() - jUptimeMillis)) {
                sleep(jUptimeMillis2);
            }
            int iM1705e = m1705e();
            if (iM1705e != 0 && iM1705e != 1) {
                this.f2676e.clear();
                for (int i2 = 0; i2 < this.f2674c.size(); i2++) {
                    RunnableC1981aa runnableC1981aa = this.f2674c.get(i2);
                    if (runnableC1981aa.m1700b()) {
                        this.f2676e.add(runnableC1981aa);
                        runnableC1981aa.m1699a(LongCompanionObject.MAX_VALUE);
                    }
                }
                NativeCrashHandler nativeCrashHandler = NativeCrashHandler.getInstance();
                if (nativeCrashHandler != null && nativeCrashHandler.isEnableCatchAnrTrace()) {
                    nativeCrashHandler.dumpAnrNativeStack();
                    C2021x.m1871c("jni mannual dump anr trace", new Object[0]);
                } else {
                    C2021x.m1871c("do not enable jni mannual dump anr trace", new Object[0]);
                }
                int i3 = 0;
                while (true) {
                    if (this.f2673b) {
                        break;
                    }
                    C2021x.m1871c("do not enable anr continue check", new Object[0]);
                    sleep(2000L);
                    i3++;
                    if (i3 == 15) {
                        this.f2676e.clear();
                        break;
                    }
                }
                for (int i4 = 0; i4 < this.f2676e.size(); i4++) {
                    RunnableC1981aa runnableC1981aa2 = this.f2676e.get(i4);
                    for (int i5 = 0; i5 < this.f2675d.size(); i5++) {
                        C2021x.m1873e("main thread blocked,now begin to upload anr stack", new Object[0]);
                        this.f2675d.get(i5).mo1585a(runnableC1981aa2);
                        this.f2673b = false;
                    }
                }
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public final void m1708a(boolean z) {
        this.f2673b = true;
    }
}
