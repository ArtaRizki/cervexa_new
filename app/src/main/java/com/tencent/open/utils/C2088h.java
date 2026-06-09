package com.tencent.open.utils;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: renamed from: com.tencent.open.utils.h */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public final class C2088h {

    /* JADX INFO: renamed from: c */
    private static Handler f3285c;

    /* JADX INFO: renamed from: d */
    private static HandlerThread f3286d;

    /* JADX INFO: renamed from: b */
    private static Object f3284b = new Object();

    /* JADX INFO: renamed from: a */
    public static final Executor f3283a = m2249c();

    /* JADX INFO: renamed from: c */
    private static Executor m2249c() {
        Executor threadPoolExecutor;
        if (Build.VERSION.SDK_INT >= 11) {
            threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue());
        } else {
            try {
                Field declaredField = AsyncTask.class.getDeclaredField("sExecutor");
                declaredField.setAccessible(true);
                threadPoolExecutor = (Executor) declaredField.get(null);
            } catch (Exception unused) {
                threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue());
            }
        }
        if (threadPoolExecutor instanceof ThreadPoolExecutor) {
            ((ThreadPoolExecutor) threadPoolExecutor).setCorePoolSize(3);
        }
        return threadPoolExecutor;
    }

    /* JADX INFO: renamed from: a */
    public static Handler m2246a() {
        if (f3285c == null) {
            synchronized (C2088h.class) {
                HandlerThread handlerThread = new HandlerThread("SDK_SUB");
                f3286d = handlerThread;
                handlerThread.start();
                f3285c = new Handler(f3286d.getLooper());
            }
        }
        return f3285c;
    }

    /* JADX INFO: renamed from: a */
    public static void m2247a(Runnable runnable) {
        m2246a().post(runnable);
    }

    /* JADX INFO: renamed from: b */
    public static Executor m2248b() {
        return new a();
    }

    /* JADX INFO: renamed from: com.tencent.open.utils.h$a */
    /* JADX INFO: compiled from: ProGuard */
    private static class a implements Executor {

        /* JADX INFO: renamed from: a */
        final Queue<Runnable> f3287a;

        /* JADX INFO: renamed from: b */
        Runnable f3288b;

        private a() {
            this.f3287a = new LinkedList();
        }

        @Override // java.util.concurrent.Executor
        public synchronized void execute(final Runnable runnable) {
            this.f3287a.offer(new Runnable() { // from class: com.tencent.open.utils.h.a.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        runnable.run();
                    } finally {
                        a.this.m2250a();
                    }
                }
            });
            if (this.f3288b == null) {
                m2250a();
            }
        }

        /* JADX INFO: renamed from: a */
        protected synchronized void m2250a() {
            Runnable runnablePoll = this.f3287a.poll();
            this.f3288b = runnablePoll;
            if (runnablePoll != null) {
                C2088h.f3283a.execute(this.f3288b);
            }
        }
    }
}
