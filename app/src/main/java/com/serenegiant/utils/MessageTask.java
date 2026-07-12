package com.serenegiant.utils;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/* JADX INFO: loaded from: classes2.dex */
public abstract class MessageTask implements Runnable {
    protected static final int REQUEST_TASK_NON = 0;
    protected static final int REQUEST_TASK_QUIT = -9;
    protected static final int REQUEST_TASK_RUN = -1;
    protected static final int REQUEST_TASK_RUN_AND_WAIT = -2;
    protected static final int REQUEST_TASK_START = -8;
    private static final String TAG = MessageTask.class.getSimpleName();
    private volatile boolean mFinished;
    private volatile boolean mIsRunning;
    private final int mMaxRequest;
    private final LinkedBlockingQueue<Request> mRequestPool;
    private final LinkedBlockingDeque<Request> mRequestQueue;
    private final Object mSync;
    private Thread mWorkerThread;

    public static class TaskBreak extends RuntimeException {
    }

    protected void onBeforeStop() {
    }

    protected boolean onError(Exception exc) {
        return true;
    }

    protected abstract void onInit(int i, int i2, Object obj);

    protected abstract void onRelease();

    protected abstract void onStart();

    protected abstract void onStop();

    protected abstract Object processRequest(int i, int i2, int i3, Object obj) throws TaskBreak;

    protected static final class Request {
        int arg1;
        int arg2;
        Object obj;
        int request;
        int request_for_result;
        Object result;

        private Request() {
            this.request_for_result = 0;
            this.request = 0;
        }

        public Request(int i, int i2, int i3, Object obj) {
            this.request = i;
            this.arg1 = i2;
            this.arg2 = i3;
            this.obj = obj;
            this.request_for_result = 0;
        }

        public void setResult(Object obj) {
            synchronized (this) {
                this.result = obj;
                this.request_for_result = 0;
                this.request = 0;
                notifyAll();
            }
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Request)) {
                return super.equals(obj);
            }
            Request request = (Request) obj;
            return this.request == request.request && this.request_for_result == request.request_for_result && this.arg1 == request.arg1 && this.arg2 == request.arg2 && this.obj == request.obj;
        }
    }

    public MessageTask() {
        this.mSync = new Object();
        this.mMaxRequest = -1;
        this.mRequestPool = new LinkedBlockingQueue<>();
        this.mRequestQueue = new LinkedBlockingDeque<>();
    }

    public MessageTask(int i) {
        this.mSync = new Object();
        this.mMaxRequest = -1;
        this.mRequestPool = new LinkedBlockingQueue<>();
        this.mRequestQueue = new LinkedBlockingDeque<>();
        for (int i2 = 0; i2 < i && this.mRequestPool.offer(new Request()); i2++) {
        }
    }

    public MessageTask(int i, int i2) {
        this.mSync = new Object();
        this.mMaxRequest = i;
        this.mRequestPool = new LinkedBlockingQueue<>(i);
        this.mRequestQueue = new LinkedBlockingDeque<>(i);
        for (int i3 = 0; i3 < i2 && this.mRequestPool.offer(new Request()); i3++) {
        }
    }

    protected void init(int i, int i2, Object obj) {
        this.mFinished = false;
        this.mRequestQueue.offer(obtain(-8, i, i2, obj));
    }

    protected Request takeRequest() throws InterruptedException {
        return this.mRequestQueue.take();
    }

    public boolean waitReady() {
        boolean z;
        synchronized (this.mSync) {
            while (!this.mIsRunning && !this.mFinished) {
                try {
                    this.mSync.wait(500L);
                } catch (InterruptedException unused) {
                }
            }
            z = this.mIsRunning;
        }
        return z;
    }

    public boolean isRunning() {
        return this.mIsRunning;
    }

    public boolean isFinished() {
        return this.mFinished;
    }

    /* JADX WARN: Removed duplicated region for block: B:83:0x00bc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() {
        /*
            Method dump skipped, instruction units count: 230
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        return;
    }

    protected boolean callOnError(Exception exc) {
        try {
            return onError(exc);
        } catch (Exception unused) {
            return true;
        }
    }

    protected Request obtain(int i, int i2, int i3, Object obj) {
        Request requestPoll = this.mRequestPool.poll();
        if (requestPoll != null) {
            requestPoll.request = i;
            requestPoll.arg1 = i2;
            requestPoll.arg2 = i3;
            requestPoll.obj = obj;
            return requestPoll;
        }
        return new Request(i, i2, i3, obj);
    }

    public boolean offer(int i, int i2, int i3, Object obj) {
        return !this.mFinished && this.mRequestQueue.offer(obtain(i, i2, i3, obj));
    }

    public boolean offer(int i, int i2, Object obj) {
        return !this.mFinished && this.mRequestQueue.offer(obtain(i, i2, 0, obj));
    }

    public boolean offer(int i, int i2, int i3) {
        return !this.mFinished && this.mIsRunning && this.mRequestQueue.offer(obtain(i, i2, i3, null));
    }

    public boolean offer(int i, int i2) {
        return !this.mFinished && this.mIsRunning && this.mRequestQueue.offer(obtain(i, i2, 0, null));
    }

    public boolean offer(int i) {
        return !this.mFinished && this.mIsRunning && this.mRequestQueue.offer(obtain(i, 0, 0, null));
    }

    public boolean offer(int i, Object obj) {
        return !this.mFinished && this.mIsRunning && this.mRequestQueue.offer(obtain(i, 0, 0, obj));
    }

    public boolean offerFirst(int i, int i2, int i3, Object obj) {
        return !this.mFinished && this.mIsRunning && this.mRequestQueue.offerFirst(obtain(i, i2, i3, obj));
    }

    public Object offerAndWait(int i, int i2, int i3, Object obj) {
        if (this.mFinished || i <= 0) {
            return null;
        }
        Request requestObtain = obtain(-2, i2, i3, obj);
        synchronized (requestObtain) {
            requestObtain.request_for_result = i;
            requestObtain.result = null;
            this.mRequestQueue.offer(requestObtain);
            while (this.mIsRunning && requestObtain.request_for_result != 0) {
                try {
                    requestObtain.wait(100L);
                } catch (InterruptedException unused) {
                }
            }
        }
        return requestObtain.result;
    }

    public boolean queueEvent(Runnable runnable) {
        return (this.mFinished || runnable == null || !offer(-1, runnable)) ? false : true;
    }

    public void removeRequest(Request request) {
        for (Request request2 : this.mRequestQueue) {
            if (!this.mIsRunning || this.mFinished) {
                return;
            }
            if (request2.equals(request)) {
                this.mRequestQueue.remove(request2);
                this.mRequestPool.offer(request2);
            }
        }
    }

    public void removeRequest(int i) {
        for (Request request : this.mRequestQueue) {
            if (!this.mIsRunning || this.mFinished) {
                return;
            }
            if (request.request == i) {
                this.mRequestQueue.remove(request);
                this.mRequestPool.offer(request);
            }
        }
    }

    public void release() {
        release(false);
    }

    public void release(boolean z) {
        boolean z2 = this.mIsRunning;
        this.mIsRunning = false;
        if (this.mFinished) {
            return;
        }
        this.mRequestQueue.clear();
        this.mRequestQueue.offerFirst(obtain(-9, 0, 0, null));
        synchronized (this.mSync) {
            if (z2) {
                long id = Thread.currentThread().getId();
                if ((this.mWorkerThread != null ? this.mWorkerThread.getId() : id) != id) {
                    if (z && this.mWorkerThread != null) {
                        this.mWorkerThread.interrupt();
                    }
                    while (!this.mFinished) {
                        try {
                            this.mSync.wait(300L);
                        } catch (InterruptedException unused) {
                        }
                    }
                }
            }
        }
    }

    public void releaseSelf() {
        this.mIsRunning = false;
        if (this.mFinished) {
            return;
        }
        this.mRequestQueue.clear();
        this.mRequestQueue.offerFirst(obtain(-9, 0, 0, null));
    }

    public void userBreak() throws TaskBreak {
        throw new TaskBreak();
    }
}

