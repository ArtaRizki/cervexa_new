package p035rx.internal.util;

import java.util.Queue;
import p035rx.Observer;
import p035rx.Subscription;
import p035rx.exceptions.MissingBackpressureException;
import p035rx.internal.operators.NotificationLite;
import p035rx.internal.util.unsafe.SpmcArrayQueue;
import p035rx.internal.util.unsafe.SpscArrayQueue;
import p035rx.internal.util.unsafe.UnsafeAccess;

/* JADX INFO: loaded from: classes2.dex */
public class RxRingBuffer implements Subscription {
    public static final int SIZE;
    public static ObjectPool<Queue<Object>> SPMC_POOL;
    public static ObjectPool<Queue<Object>> SPSC_POOL;
    static int _size;

    /* JADX INFO: renamed from: on */
    private static final NotificationLite<Object> f3524on = NotificationLite.instance();
    private final ObjectPool<Queue<Object>> pool;
    private Queue<Object> queue;
    private final int size;
    public volatile Object terminalState;

    public static RxRingBuffer getSpscInstance() {
        if (UnsafeAccess.isUnsafeAvailable()) {
            return new RxRingBuffer(SPSC_POOL, SIZE);
        }
        return new RxRingBuffer();
    }

    public static RxRingBuffer getSpmcInstance() {
        if (UnsafeAccess.isUnsafeAvailable()) {
            return new RxRingBuffer(SPMC_POOL, SIZE);
        }
        return new RxRingBuffer();
    }

    static {
        _size = 128;
        if (PlatformDependent.isAndroid()) {
            _size = 16;
        }
        String property = System.getProperty("rx.ring-buffer.size");
        if (property != null) {
            try {
                _size = Integer.parseInt(property);
            } catch (Exception e) {
                System.err.println("Failed to set 'rx.buffer.size' with value " + property + " => " + e.getMessage());
            }
        }
        SIZE = _size;
        SPSC_POOL = new ObjectPool<Queue<Object>>() { // from class: rx.internal.util.RxRingBuffer.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // p035rx.internal.util.ObjectPool
            public Queue<Object> createObject() {
                return new SpscArrayQueue(RxRingBuffer.SIZE);
            }
        };
        SPMC_POOL = new ObjectPool<Queue<Object>>() { // from class: rx.internal.util.RxRingBuffer.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // p035rx.internal.util.ObjectPool
            public Queue<Object> createObject() {
                return new SpmcArrayQueue(RxRingBuffer.SIZE);
            }
        };
    }

    private RxRingBuffer(Queue<Object> queue, int i) {
        this.queue = queue;
        this.pool = null;
        this.size = i;
    }

    private RxRingBuffer(ObjectPool<Queue<Object>> objectPool, int i) {
        this.pool = objectPool;
        this.queue = objectPool.borrowObject();
        this.size = i;
    }

    public synchronized void release() {
        Queue<Object> queue = this.queue;
        ObjectPool<Queue<Object>> objectPool = this.pool;
        if (objectPool != null && queue != null) {
            queue.clear();
            this.queue = null;
            objectPool.returnObject(queue);
        }
    }

    @Override // p035rx.Subscription
    public void unsubscribe() {
        release();
    }

    RxRingBuffer() {
        this(new SynchronizedQueue(SIZE), SIZE);
    }

    public void onNext(Object obj) throws MissingBackpressureException {
        boolean z;
        boolean z2;
        synchronized (this) {
            Queue<Object> queue = this.queue;
            z = true;
            z2 = false;
            if (queue != null) {
                z2 = !queue.offer(f3524on.next(obj));
                z = false;
            }
        }
        if (z) {
            throw new IllegalStateException("This instance has been unsubscribed and the queue is no longer usable.");
        }
        if (z2) {
            throw new MissingBackpressureException();
        }
    }

    public void onCompleted() {
        if (this.terminalState == null) {
            this.terminalState = f3524on.completed();
        }
    }

    public void onError(Throwable th) {
        if (this.terminalState == null) {
            this.terminalState = f3524on.error(th);
        }
    }

    public int available() {
        return this.size - count();
    }

    public int capacity() {
        return this.size;
    }

    public int count() {
        Queue<Object> queue = this.queue;
        if (queue == null) {
            return 0;
        }
        return queue.size();
    }

    public boolean isEmpty() {
        Queue<Object> queue = this.queue;
        if (queue == null) {
            return true;
        }
        return queue.isEmpty();
    }

    public Object poll() {
        synchronized (this) {
            Queue<Object> queue = this.queue;
            if (queue == null) {
                return null;
            }
            Object objPoll = queue.poll();
            Object obj = this.terminalState;
            if (objPoll == null && obj != null && queue.peek() == null) {
                this.terminalState = null;
                objPoll = obj;
            }
            return objPoll;
        }
    }

    public Object peek() {
        synchronized (this) {
            Queue<Object> queue = this.queue;
            if (queue == null) {
                return null;
            }
            Object objPeek = queue.peek();
            Object obj = this.terminalState;
            if (objPeek == null && obj != null && queue.peek() == null) {
                objPeek = obj;
            }
            return objPeek;
        }
    }

    public boolean isCompleted(Object obj) {
        return f3524on.isCompleted(obj);
    }

    public boolean isError(Object obj) {
        return f3524on.isError(obj);
    }

    public Object getValue(Object obj) {
        return f3524on.getValue(obj);
    }

    public boolean accept(Object obj, Observer observer) {
        return f3524on.accept(observer, obj);
    }

    public Throwable asError(Object obj) {
        return f3524on.getError(obj);
    }

    @Override // p035rx.Subscription
    public boolean isUnsubscribed() {
        return this.queue == null;
    }
}
