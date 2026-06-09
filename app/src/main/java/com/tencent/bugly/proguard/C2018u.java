package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import com.tencent.bugly.C1950b;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/* JADX INFO: renamed from: com.tencent.bugly.proguard.u */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public final class C2018u {

    /* JADX INFO: renamed from: a */
    private static C2018u f2870a;

    /* JADX INFO: renamed from: c */
    private final Context f2872c;

    /* JADX INFO: renamed from: e */
    private long f2874e;

    /* JADX INFO: renamed from: f */
    private long f2875f;

    /* JADX INFO: renamed from: d */
    private Map<Integer, Long> f2873d = new HashMap();

    /* JADX INFO: renamed from: g */
    private LinkedBlockingQueue<Runnable> f2876g = new LinkedBlockingQueue<>();

    /* JADX INFO: renamed from: h */
    private LinkedBlockingQueue<Runnable> f2877h = new LinkedBlockingQueue<>();

    /* JADX INFO: renamed from: i */
    private final Object f2878i = new Object();

    /* JADX INFO: renamed from: j */
    private int f2879j = 0;

    /* JADX INFO: renamed from: b */
    private final C2013p f2871b = C2013p.m1807a();

    /* JADX INFO: renamed from: b */
    static /* synthetic */ int m1844b(C2018u c2018u) {
        int i = c2018u.f2879j - 1;
        c2018u.f2879j = i;
        return i;
    }

    private C2018u(Context context) {
        this.f2872c = context;
    }

    /* JADX INFO: renamed from: a */
    public static synchronized C2018u m1840a(Context context) {
        if (f2870a == null) {
            f2870a = new C2018u(context);
        }
        return f2870a;
    }

    /* JADX INFO: renamed from: a */
    public static synchronized C2018u m1839a() {
        return f2870a;
    }

    /* JADX INFO: renamed from: a */
    public final void m1849a(int i, C1993am c1993am, String str, String str2, InterfaceC2017t interfaceC2017t, long j, boolean z) {
        try {
        } catch (Throwable th) {
            th = th;
        }
        try {
            m1842a(new RunnableC2019v(this.f2872c, i, c1993am.f2728g, C1980a.m1691a((Object) c1993am), str, str2, interfaceC2017t, true, z), true, true, j);
        } catch (Throwable th2) {
            th = th2;
            if (C2021x.m1867a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    /* JADX INFO: renamed from: a */
    public final void m1850a(int i, C1993am c1993am, String str, String str2, InterfaceC2017t interfaceC2017t, boolean z) {
        try {
        } catch (Throwable th) {
            th = th;
        }
        try {
            m1842a(new RunnableC2019v(this.f2872c, i, c1993am.f2728g, C1980a.m1691a((Object) c1993am), str, str2, interfaceC2017t, 0, 0, false, null), z, false, 0L);
        } catch (Throwable th2) {
            th = th2;
            if (C2021x.m1867a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    /* JADX INFO: renamed from: a */
    public final long m1847a(boolean z) {
        long jM1919b;
        long jM1918b = C2023z.m1918b();
        int i = z ? 5 : 3;
        List<C2015r> listM1824a = this.f2871b.m1824a(i);
        if (listM1824a != null && listM1824a.size() > 0) {
            jM1919b = 0;
            try {
                C2015r c2015r = listM1824a.get(0);
                if (c2015r.f2864e >= jM1918b) {
                    jM1919b = C2023z.m1919b(c2015r.f2866g);
                    if (i == 3) {
                        this.f2874e = jM1919b;
                    } else {
                        this.f2875f = jM1919b;
                    }
                    listM1824a.remove(c2015r);
                }
            } catch (Throwable th) {
                C2021x.m1867a(th);
            }
            if (listM1824a.size() > 0) {
                this.f2871b.m1826a(listM1824a);
            }
        } else {
            jM1919b = z ? this.f2875f : this.f2874e;
        }
        C2021x.m1871c("[UploadManager] Local network consume: %d KB", Long.valueOf(jM1919b / 1024));
        return jM1919b;
    }

    /* JADX INFO: renamed from: a */
    protected final synchronized void m1851a(long j, boolean z) {
        int i = z ? 5 : 3;
        C2015r c2015r = new C2015r();
        c2015r.f2861b = i;
        c2015r.f2864e = C2023z.m1918b();
        c2015r.f2862c = "";
        c2015r.f2863d = "";
        c2015r.f2866g = C2023z.m1930c(j);
        this.f2871b.m1829b(i);
        this.f2871b.m1828a(c2015r);
        if (z) {
            this.f2875f = j;
        } else {
            this.f2874e = j;
        }
        C2021x.m1871c("[UploadManager] Network total consume: %d KB", Long.valueOf(j / 1024));
    }

    /* JADX INFO: renamed from: a */
    public final synchronized void m1848a(int i, long j) {
        if (i < 0) {
            C2021x.m1873e("[UploadManager] Unknown uploading ID: %d", Integer.valueOf(i));
            return;
        }
        this.f2873d.put(Integer.valueOf(i), Long.valueOf(j));
        C2015r c2015r = new C2015r();
        c2015r.f2861b = i;
        c2015r.f2864e = j;
        c2015r.f2862c = "";
        c2015r.f2863d = "";
        c2015r.f2866g = new byte[0];
        this.f2871b.m1829b(i);
        this.f2871b.m1828a(c2015r);
        C2021x.m1871c("[UploadManager] Uploading(ID:%d) time: %s", Integer.valueOf(i), C2023z.m1898a(j));
    }

    /* JADX INFO: renamed from: a */
    public final synchronized long m1846a(int i) {
        if (i >= 0) {
            Long l = this.f2873d.get(Integer.valueOf(i));
            if (l != null) {
                return l.longValue();
            }
        } else {
            C2021x.m1873e("[UploadManager] Unknown upload ID: %d", Integer.valueOf(i));
        }
        return 0L;
    }

    /* JADX INFO: renamed from: b */
    public final boolean m1852b(int i) {
        if (C1950b.f2299c) {
            C2021x.m1871c("Uploading frequency will not be checked if SDK is in debug mode.", new Object[0]);
            return true;
        }
        long jCurrentTimeMillis = System.currentTimeMillis() - m1846a(i);
        C2021x.m1871c("[UploadManager] Time interval is %d seconds since last uploading(ID: %d).", Long.valueOf(jCurrentTimeMillis / 1000), Integer.valueOf(i));
        if (jCurrentTimeMillis >= 30000) {
            return true;
        }
        C2021x.m1866a("[UploadManager] Data only be uploaded once in %d seconds.", 30L);
        return false;
    }

    /* JADX INFO: renamed from: c */
    private void m1845c(int i) {
        C2020w c2020wM1858a = C2020w.m1858a();
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        final LinkedBlockingQueue linkedBlockingQueue2 = new LinkedBlockingQueue();
        synchronized (this.f2878i) {
            C2021x.m1871c("[UploadManager] Try to poll all upload task need and put them into temp queue (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            int size = this.f2876g.size();
            final int size2 = this.f2877h.size();
            if (size == 0 && size2 == 0) {
                C2021x.m1871c("[UploadManager] There is no upload task in queue.", new Object[0]);
                return;
            }
            if (c2020wM1858a == null || !c2020wM1858a.m1863c()) {
                size2 = 0;
            }
            for (int i2 = 0; i2 < size; i2++) {
                Runnable runnablePeek = this.f2876g.peek();
                if (runnablePeek == null) {
                    break;
                }
                try {
                    linkedBlockingQueue.put(runnablePeek);
                    this.f2876g.poll();
                } catch (Throwable th) {
                    C2021x.m1873e("[UploadManager] Failed to add upload task to temp urgent queue: %s", th.getMessage());
                }
            }
            for (int i3 = 0; i3 < size2; i3++) {
                Runnable runnablePeek2 = this.f2877h.peek();
                if (runnablePeek2 == null) {
                    break;
                }
                try {
                    linkedBlockingQueue2.put(runnablePeek2);
                    this.f2877h.poll();
                } catch (Throwable th2) {
                    C2021x.m1873e("[UploadManager] Failed to add upload task to temp urgent queue: %s", th2.getMessage());
                }
            }
            if (size > 0) {
                C2021x.m1871c("[UploadManager] Execute urgent upload tasks of queue which has %d tasks (pid=%d | tid=%d)", Integer.valueOf(size), Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            }
            for (int i4 = 0; i4 < size; i4++) {
                final Runnable runnable = (Runnable) linkedBlockingQueue.poll();
                if (runnable == null) {
                    break;
                }
                synchronized (this.f2878i) {
                    if (this.f2879j >= 2 && c2020wM1858a != null) {
                        c2020wM1858a.m1860a(runnable);
                    } else {
                        C2021x.m1866a("[UploadManager] Create and start a new thread to execute a upload task: %s", "BUGLY_ASYNC_UPLOAD");
                        if (C2023z.m1905a(new Runnable() { // from class: com.tencent.bugly.proguard.u.1
                            @Override // java.lang.Runnable
                            public final void run() {
                                runnable.run();
                                synchronized (C2018u.this.f2878i) {
                                    C2018u.m1844b(C2018u.this);
                                }
                            }
                        }, "BUGLY_ASYNC_UPLOAD") != null) {
                            synchronized (this.f2878i) {
                                this.f2879j++;
                            }
                        } else {
                            C2021x.m1872d("[UploadManager] Failed to start a thread to execute asynchronous upload task, will try again next time.", new Object[0]);
                            m1843a(runnable, true);
                        }
                    }
                }
            }
            if (size2 > 0) {
                C2021x.m1871c("[UploadManager] Execute upload tasks of queue which has %d tasks (pid=%d | tid=%d)", Integer.valueOf(size2), Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            }
            if (c2020wM1858a != null) {
                c2020wM1858a.m1860a(new Runnable(this) { // from class: com.tencent.bugly.proguard.u.2
                    @Override // java.lang.Runnable
                    public final void run() {
                        Runnable runnable2;
                        for (int i5 = 0; i5 < size2 && (runnable2 = (Runnable) linkedBlockingQueue2.poll()) != null; i5++) {
                            runnable2.run();
                        }
                    }
                });
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private boolean m1843a(Runnable runnable, boolean z) {
        if (runnable == null) {
            C2021x.m1866a("[UploadManager] Upload task should not be null", new Object[0]);
            return false;
        }
        try {
            C2021x.m1871c("[UploadManager] Add upload task to queue (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            synchronized (this.f2878i) {
                if (z) {
                    this.f2876g.put(runnable);
                } else {
                    this.f2877h.put(runnable);
                }
            }
            return true;
        } catch (Throwable th) {
            C2021x.m1873e("[UploadManager] Failed to add upload task to queue: %s", th.getMessage());
            return false;
        }
    }

    /* JADX INFO: renamed from: a */
    private void m1842a(Runnable runnable, boolean z, boolean z2, long j) {
        if (runnable == null) {
            C2021x.m1872d("[UploadManager] Upload task should not be null", new Object[0]);
        }
        C2021x.m1871c("[UploadManager] Add upload task (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        if (!z2) {
            m1843a(runnable, z);
            m1845c(0);
            return;
        }
        if (runnable == null) {
            C2021x.m1872d("[UploadManager] Upload task should not be null", new Object[0]);
            return;
        }
        C2021x.m1871c("[UploadManager] Execute synchronized upload task (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        Thread threadM1905a = C2023z.m1905a(runnable, "BUGLY_SYNC_UPLOAD");
        if (threadM1905a == null) {
            C2021x.m1873e("[UploadManager] Failed to start a thread to execute synchronized upload task, add it to queue.", new Object[0]);
            m1843a(runnable, true);
            return;
        }
        try {
            threadM1905a.join(j);
        } catch (Throwable th) {
            C2021x.m1873e("[UploadManager] Failed to join upload synchronized task with message: %s. Add it to queue.", th.getMessage());
            m1843a(runnable, true);
            m1845c(0);
        }
    }
}
