package p035rx.internal.operators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p035rx.Observable;
import p035rx.Producer;
import p035rx.Subscriber;
import p035rx.Subscription;
import p035rx.exceptions.CompositeException;
import p035rx.functions.FuncN;
import p035rx.internal.util.RxRingBuffer;
import p035rx.internal.util.atomic.SpscLinkedArrayQueue;
import p035rx.plugins.RxJavaPlugins;

/* JADX INFO: loaded from: classes2.dex */
public final class OnSubscribeCombineLatest<T, R> implements Observable.OnSubscribe<R> {
    final int bufferSize;
    final FuncN<? extends R> combiner;
    final boolean delayError;
    final Observable<? extends T>[] sources;
    final Iterable<? extends Observable<? extends T>> sourcesIterable;

    public OnSubscribeCombineLatest(Iterable<? extends Observable<? extends T>> iterable, FuncN<? extends R> funcN) {
        this(null, iterable, funcN, RxRingBuffer.SIZE, false);
    }

    public OnSubscribeCombineLatest(Observable<? extends T>[] observableArr, Iterable<? extends Observable<? extends T>> iterable, FuncN<? extends R> funcN, int i, boolean z) {
        this.sources = observableArr;
        this.sourcesIterable = iterable;
        this.combiner = funcN;
        this.bufferSize = i;
        this.delayError = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004f  */
    @Override // p035rx.functions.Action1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void call(p035rx.Subscriber<? super R> r9) {
        /*
            r8 = this;
            rx.Observable<? extends T>[] r0 = r8.sources
            if (r0 != 0) goto L47
            java.lang.Iterable<? extends rx.Observable<? extends T>> r0 = r8.sourcesIterable
            boolean r1 = r0 instanceof java.util.List
            if (r1 == 0) goto L1c
            java.util.List r0 = (java.util.List) r0
            int r1 = r0.size()
            rx.Observable[] r1 = new p035rx.Observable[r1]
            java.lang.Object[] r0 = r0.toArray(r1)
            rx.Observable[] r0 = (p035rx.Observable[]) r0
            rx.Observable[] r0 = (p035rx.Observable[]) r0
            int r1 = r0.length
            goto L48
        L1c:
            r1 = 8
            rx.Observable[] r1 = new p035rx.Observable[r1]
            java.util.Iterator r0 = r0.iterator()
            r2 = 0
            r3 = 0
        L26:
            boolean r4 = r0.hasNext()
            if (r4 == 0) goto L44
            java.lang.Object r4 = r0.next()
            rx.Observable r4 = (p035rx.Observable) r4
            int r5 = r1.length
            if (r3 != r5) goto L3e
            int r5 = r3 >> 2
            int r5 = r5 + r3
            rx.Observable[] r5 = new p035rx.Observable[r5]
            java.lang.System.arraycopy(r1, r2, r5, r2, r3)
            r1 = r5
        L3e:
            int r5 = r3 + 1
            r1[r3] = r4
            r3 = r5
            goto L26
        L44:
            r0 = r1
            r4 = r3
            goto L49
        L47:
            int r1 = r0.length
        L48:
            r4 = r1
        L49:
            if (r4 != 0) goto L4f
            r9.onCompleted()
            return
        L4f:
            rx.internal.operators.OnSubscribeCombineLatest$LatestCoordinator r7 = new rx.internal.operators.OnSubscribeCombineLatest$LatestCoordinator
            rx.functions.FuncN<? extends R> r3 = r8.combiner
            int r5 = r8.bufferSize
            boolean r6 = r8.delayError
            r1 = r7
            r2 = r9
            r1.<init>(r2, r3, r4, r5, r6)
            r7.subscribe(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p035rx.internal.operators.OnSubscribeCombineLatest.call(rx.Subscriber):void");
    }

    static final class LatestCoordinator<T, R> extends AtomicInteger implements Producer, Subscription {
        static final Object MISSING = new Object();
        private static final long serialVersionUID = 8567835998786448817L;
        int active;
        final Subscriber<? super R> actual;
        final int bufferSize;
        volatile boolean cancelled;
        final FuncN<? extends R> combiner;
        int complete;
        final int count;
        final boolean delayError;
        volatile boolean done;
        final AtomicReference<Throwable> error;
        final Object[] latest;
        final SpscLinkedArrayQueue<Object> queue;
        final AtomicLong requested;
        final CombinerSubscriber<T, R>[] subscribers;

        public LatestCoordinator(Subscriber<? super R> subscriber, FuncN<? extends R> funcN, int i, int i2, boolean z) {
            this.actual = subscriber;
            this.combiner = funcN;
            this.count = i;
            this.bufferSize = i2;
            this.delayError = z;
            Object[] objArr = new Object[i];
            this.latest = objArr;
            Arrays.fill(objArr, MISSING);
            this.subscribers = new CombinerSubscriber[i];
            this.queue = new SpscLinkedArrayQueue<>(i2);
            this.requested = new AtomicLong();
            this.error = new AtomicReference<>();
        }

        public void subscribe(Observable<? extends T>[] observableArr) {
            CombinerSubscriber<T, R>[] combinerSubscriberArr = this.subscribers;
            int length = combinerSubscriberArr.length;
            for (int i = 0; i < length; i++) {
                combinerSubscriberArr[i] = new CombinerSubscriber<>(this, i);
            }
            lazySet(0);
            this.actual.add(this);
            this.actual.setProducer(this);
            for (int i2 = 0; i2 < length && !this.cancelled; i2++) {
                observableArr[i2].subscribe((Subscriber<? super Object>) combinerSubscriberArr[i2]);
            }
        }

        @Override // p035rx.Producer
        public void request(long j) {
            if (j < 0) {
                throw new IllegalArgumentException("n >= required but it was " + j);
            }
            if (j != 0) {
                BackpressureUtils.getAndAddRequest(this.requested, j);
                drain();
            }
        }

        @Override // p035rx.Subscription
        public void unsubscribe() {
            if (this.cancelled) {
                return;
            }
            this.cancelled = true;
            if (getAndIncrement() == 0) {
                cancel(this.queue);
            }
        }

        @Override // p035rx.Subscription
        public boolean isUnsubscribed() {
            return this.cancelled;
        }

        void cancel(Queue<?> queue) {
            queue.clear();
            for (CombinerSubscriber<T, R> combinerSubscriber : this.subscribers) {
                combinerSubscriber.unsubscribe();
            }
        }

        void combine(Object obj, int i) {
            boolean z;
            CombinerSubscriber<T, R> combinerSubscriber = this.subscribers[i];
            synchronized (this) {
                int length = this.latest.length;
                Object obj2 = this.latest[i];
                int i2 = this.active;
                if (obj2 == MISSING) {
                    i2++;
                    this.active = i2;
                }
                int i3 = this.complete;
                if (obj == null) {
                    i3++;
                    this.complete = i3;
                } else {
                    this.latest[i] = combinerSubscriber.f3484nl.getValue(obj);
                }
                boolean z2 = false;
                z = i2 == length;
                if (i3 == length || (obj == null && obj2 == MISSING)) {
                    z2 = true;
                }
                if (z2) {
                    this.done = true;
                } else if (obj != null && z) {
                    this.queue.offer(combinerSubscriber, this.latest.clone());
                } else if (obj == null && this.error.get() != null && (obj2 == MISSING || !this.delayError)) {
                    this.done = true;
                }
            }
            if (!z && obj != null) {
                combinerSubscriber.requestMore(1L);
            } else {
                drain();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:36:0x00a3, code lost:
        
            if (r3 == 0) goto L39;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x00a5, code lost:
        
            if (r14 != false) goto L39;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x00a7, code lost:
        
            r10.addAndGet(r3);
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x00aa, code lost:
        
            r12 = addAndGet(-r12);
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x00af, code lost:
        
            if (r12 != 0) goto L48;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x00b1, code lost:
        
            return;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void drain() {
            /*
                r22 = this;
                r7 = r22
                int r0 = r22.getAndIncrement()
                if (r0 == 0) goto L9
                return
            L9:
                rx.internal.util.atomic.SpscLinkedArrayQueue<java.lang.Object> r8 = r7.queue
                rx.Subscriber<? super R> r9 = r7.actual
                boolean r0 = r7.delayError
                java.util.concurrent.atomic.AtomicLong r10 = r7.requested
                r11 = 1
                r12 = 1
            L13:
                boolean r2 = r7.done
                boolean r3 = r8.isEmpty()
                r1 = r22
                r4 = r9
                r5 = r8
                r6 = r0
                boolean r1 = r1.checkTerminated(r2, r3, r4, r5, r6)
                if (r1 == 0) goto L25
                return
            L25:
                long r1 = r10.get()
                r3 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
                if (r5 != 0) goto L34
                r14 = 1
                goto L35
            L34:
                r14 = 0
            L35:
                r15 = 0
                r17 = r1
                r5 = r15
            L3a:
                int r1 = (r17 > r15 ? 1 : (r17 == r15 ? 0 : -1))
                if (r1 == 0) goto La0
                boolean r2 = r7.done
                java.lang.Object r1 = r8.peek()
                r4 = r1
                rx.internal.operators.OnSubscribeCombineLatest$CombinerSubscriber r4 = (rx.internal.operators.OnSubscribeCombineLatest.CombinerSubscriber) r4
                if (r4 != 0) goto L4c
                r19 = 1
                goto L4e
            L4c:
                r19 = 0
            L4e:
                r1 = r22
                r3 = r19
                r13 = r4
                r4 = r9
                r20 = r5
                r5 = r8
                r6 = r0
                boolean r1 = r1.checkTerminated(r2, r3, r4, r5, r6)
                if (r1 == 0) goto L5f
                return
            L5f:
                if (r19 == 0) goto L64
                r3 = r20
                goto La1
            L64:
                r8.poll()
                java.lang.Object r1 = r8.poll()
                java.lang.Object[] r1 = (java.lang.Object[]) r1
                java.lang.Object[] r1 = (java.lang.Object[]) r1
                if (r1 != 0) goto L81
                r7.cancelled = r11
                r7.cancel(r8)
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r1 = "Broken queue?! Sender received but not the array."
                r0.<init>(r1)
                r9.onError(r0)
                return
            L81:
                rx.functions.FuncN<? extends R> r2 = r7.combiner     // Catch: java.lang.Throwable -> L96
                java.lang.Object r1 = r2.call(r1)     // Catch: java.lang.Throwable -> L96
                r9.onNext(r1)
                r1 = 1
                r13.requestMore(r1)
                long r17 = r17 - r1
                r3 = r20
                long r5 = r3 - r1
                goto L3a
            L96:
                r0 = move-exception
                r7.cancelled = r11
                r7.cancel(r8)
                r9.onError(r0)
                return
            La0:
                r3 = r5
            La1:
                int r1 = (r3 > r15 ? 1 : (r3 == r15 ? 0 : -1))
                if (r1 == 0) goto Laa
                if (r14 != 0) goto Laa
                r10.addAndGet(r3)
            Laa:
                int r1 = -r12
                int r12 = r7.addAndGet(r1)
                if (r12 != 0) goto L13
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OnSubscribeCombineLatest.LatestCoordinator.drain():void");
        }

        boolean checkTerminated(boolean z, boolean z2, Subscriber<?> subscriber, Queue<?> queue, boolean z3) {
            if (this.cancelled) {
                cancel(queue);
                return true;
            }
            if (!z) {
                return false;
            }
            if (z3) {
                if (!z2) {
                    return false;
                }
                Throwable th = this.error.get();
                if (th != null) {
                    subscriber.onError(th);
                } else {
                    subscriber.onCompleted();
                }
                return true;
            }
            Throwable th2 = this.error.get();
            if (th2 != null) {
                cancel(queue);
                subscriber.onError(th2);
                return true;
            }
            if (!z2) {
                return false;
            }
            subscriber.onCompleted();
            return true;
        }

        void onError(Throwable th) {
            Throwable th2;
            Throwable compositeException;
            AtomicReference<Throwable> atomicReference = this.error;
            do {
                th2 = atomicReference.get();
                if (th2 == null) {
                    compositeException = th;
                } else if (th2 instanceof CompositeException) {
                    ArrayList arrayList = new ArrayList(((CompositeException) th2).getExceptions());
                    arrayList.add(th);
                    compositeException = new CompositeException(arrayList);
                } else {
                    compositeException = new CompositeException(Arrays.asList(th2, th));
                }
            } while (!atomicReference.compareAndSet(th2, compositeException));
        }
    }

    static final class CombinerSubscriber<T, R> extends Subscriber<T> {
        boolean done;
        final int index;

        /* JADX INFO: renamed from: nl */
        final NotificationLite<T> f3484nl = NotificationLite.instance();
        final LatestCoordinator<T, R> parent;

        public CombinerSubscriber(LatestCoordinator<T, R> latestCoordinator, int i) {
            this.parent = latestCoordinator;
            this.index = i;
            request(latestCoordinator.bufferSize);
        }

        @Override // p035rx.Observer
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            this.parent.combine(this.f3484nl.next(t), this.index);
        }

        @Override // p035rx.Observer
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
                return;
            }
            this.parent.onError(th);
            this.done = true;
            this.parent.combine(null, this.index);
        }

        @Override // p035rx.Observer
        public void onCompleted() {
            if (this.done) {
                return;
            }
            this.done = true;
            this.parent.combine(null, this.index);
        }

        public void requestMore(long j) {
            request(j);
        }
    }
}
