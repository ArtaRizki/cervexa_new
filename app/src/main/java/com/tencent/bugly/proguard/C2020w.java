package com.tencent.bugly.proguard;

import com.tencent.bugly.C1950b;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: renamed from: com.tencent.bugly.proguard.w */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public final class C2020w {

    /* JADX INFO: renamed from: a */
    private static final AtomicInteger f2903a = new AtomicInteger(1);

    /* JADX INFO: renamed from: b */
    private static C2020w f2904b;

    /* JADX INFO: renamed from: c */
    private ScheduledExecutorService f2905c;

    protected C2020w() {
        this.f2905c = null;
        ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(3, new ThreadFactory(this) { // from class: com.tencent.bugly.proguard.w.1
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable);
                thread.setName("BuglyThread-" + C2020w.f2903a.getAndIncrement());
                return thread;
            }
        });
        this.f2905c = scheduledExecutorServiceNewScheduledThreadPool;
        if (scheduledExecutorServiceNewScheduledThreadPool == null || scheduledExecutorServiceNewScheduledThreadPool.isShutdown()) {
            C2021x.m1872d("[AsyncTaskHandler] ScheduledExecutorService is not valiable!", new Object[0]);
        }
    }

    /* JADX INFO: renamed from: a */
    public static synchronized C2020w m1858a() {
        if (f2904b == null) {
            f2904b = new C2020w();
        }
        return f2904b;
    }

    /* JADX INFO: renamed from: a */
    public final synchronized boolean m1861a(Runnable runnable, long j) {
        if (!m1863c()) {
            C2021x.m1872d("[AsyncTaskHandler] Async handler was closed, should not post task.", new Object[0]);
            return false;
        }
        if (runnable == null) {
            C2021x.m1872d("[AsyncTaskHandler] Task input is null.", new Object[0]);
            return false;
        }
        if (j <= 0) {
            j = 0;
        }
        C2021x.m1871c("[AsyncTaskHandler] Post a delay(time: %dms) task: %s", Long.valueOf(j), runnable.getClass().getName());
        try {
            this.f2905c.schedule(runnable, j, TimeUnit.MILLISECONDS);
            return true;
        } catch (Throwable th) {
            if (C1950b.f2299c) {
                th.printStackTrace();
            }
            return false;
        }
    }

    /* JADX INFO: renamed from: a */
    public final synchronized boolean m1860a(Runnable runnable) {
        if (!m1863c()) {
            C2021x.m1872d("[AsyncTaskHandler] Async handler was closed, should not post task.", new Object[0]);
            return false;
        }
        if (runnable == null) {
            C2021x.m1872d("[AsyncTaskHandler] Task input is null.", new Object[0]);
            return false;
        }
        C2021x.m1871c("[AsyncTaskHandler] Post a normal task: %s", runnable.getClass().getName());
        try {
            this.f2905c.execute(runnable);
            return true;
        } catch (Throwable th) {
            if (C1950b.f2299c) {
                th.printStackTrace();
            }
            return false;
        }
    }

    /* JADX INFO: renamed from: b */
    public final synchronized void m1862b() {
        if (this.f2905c != null && !this.f2905c.isShutdown()) {
            C2021x.m1871c("[AsyncTaskHandler] Close async handler.", new Object[0]);
            this.f2905c.shutdownNow();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0010  */
    /* JADX INFO: renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized boolean m1863c() {
        /*
            r1 = this;
            monitor-enter(r1)
            java.util.concurrent.ScheduledExecutorService r0 = r1.f2905c     // Catch: java.lang.Throwable -> L12
            if (r0 == 0) goto L10
            java.util.concurrent.ScheduledExecutorService r0 = r1.f2905c     // Catch: java.lang.Throwable -> L12
            boolean r0 = r0.isShutdown()     // Catch: java.lang.Throwable -> L12
            if (r0 != 0) goto L10
            r0 = 1
        Le:
            monitor-exit(r1)
            return r0
        L10:
            r0 = 0
            goto Le
        L12:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2020w.m1863c():boolean");
    }
}
