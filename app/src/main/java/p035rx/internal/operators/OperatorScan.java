package p035rx.internal.operators;

import android.Manifest;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.jvm.internal.LongCompanionObject;
import p035rx.Observable;
import p035rx.Observer;
import p035rx.Producer;
import p035rx.Subscriber;
import p035rx.exceptions.Exceptions;
import p035rx.functions.Func0;
import p035rx.functions.Func2;
import p035rx.internal.util.atomic.SpscLinkedAtomicQueue;
import p035rx.internal.util.unsafe.SpscLinkedQueue;
import p035rx.internal.util.unsafe.UnsafeAccess;

/* JADX INFO: loaded from: classes2.dex */
public final class OperatorScan<R, T> implements Observable.Operator<R, T> {
    private static final Object NO_INITIAL_VALUE = new Object();
    final Func2<R, ? super T, R> accumulator;
    private final Func0<R> initialValueFactory;

    public OperatorScan(final R r, Func2<R, ? super T, R> func2) {
        this((Func0) new Func0<R>() { // from class: rx.internal.operators.OperatorScan.1
            @Override // p035rx.functions.Func0, java.util.concurrent.Callable
            public R call() {
                return (R) r;
            }
        }, (Func2) func2);
    }

    public OperatorScan(Func0<R> func0, Func2<R, ? super T, R> func2) {
        this.initialValueFactory = func0;
        this.accumulator = func2;
    }

    public OperatorScan(Func2<R, ? super T, R> func2) {
        this(NO_INITIAL_VALUE, func2);
    }

    @Override // p035rx.functions.Func1
    public Subscriber<? super T> call(final Subscriber<? super R> subscriber) {
        final R rCall = this.initialValueFactory.call();
        if (rCall == NO_INITIAL_VALUE) {
            return new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorScan.2
                boolean once;
                R value;

                /* JADX WARN: Type inference fix 'apply assigned field type' failed
                java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
                	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
                	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
                 */
                @Override // p035rx.Observer
                public void onNext(T t) {
                    if (!this.once) {
                        this.once = true;
                    } else {
                        try {
                            t = OperatorScan.this.accumulator.call(this.value, t);
                        } catch (Throwable th) {
                            Exceptions.throwOrReport(th, subscriber, t);
                            return;
                        }
                    }
                    this.value = (R) t;
                    subscriber.onNext(t);
                }

                @Override // p035rx.Observer
                public void onError(Throwable th) {
                    subscriber.onError(th);
                }

                @Override // p035rx.Observer
                public void onCompleted() {
                    subscriber.onCompleted();
                }
            };
        }
        final InitialProducer initialProducer = new InitialProducer(rCall, subscriber);
        Subscriber<T> subscriber2 = new Subscriber<T>() { // from class: rx.internal.operators.OperatorScan.3
            private R value;

            {
                this.value = (R) rCall;
            }

            @Override // p035rx.Observer
            public void onNext(T t) {
                try {
                    R rCall2 = OperatorScan.this.accumulator.call(this.value, t);
                    this.value = rCall2;
                    initialProducer.onNext(rCall2);
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this, t);
                }
            }

            @Override // p035rx.Observer
            public void onError(Throwable th) {
                initialProducer.onError(th);
            }

            @Override // p035rx.Observer
            public void onCompleted() {
                initialProducer.onCompleted();
            }

            @Override // p035rx.Subscriber
            public void setProducer(Producer producer) {
                initialProducer.setProducer(producer);
            }
        };
        subscriber.add(subscriber2);
        subscriber.setProducer(initialProducer);
        return subscriber2;
    }

    static final class InitialProducer<R> implements Producer, Observer<R> {
        final Subscriber<? super R> child;
        volatile boolean done;
        boolean emitting;
        Throwable error;
        boolean missed;
        long missedRequested;
        volatile Producer producer;
        final Queue<Object> queue;
        final AtomicLong requested;

        public InitialProducer(R r, Subscriber<? super R> subscriber) {
            Queue<Object> spscLinkedAtomicQueue;
            this.child = subscriber;
            if (UnsafeAccess.isUnsafeAvailable()) {
                spscLinkedAtomicQueue = new SpscLinkedQueue<>();
            } else {
                spscLinkedAtomicQueue = new SpscLinkedAtomicQueue<>();
            }
            this.queue = spscLinkedAtomicQueue;
            spscLinkedAtomicQueue.offer(NotificationLite.instance().next(r));
            this.requested = new AtomicLong();
        }

        @Override // p035rx.Observer
        public void onNext(R r) {
            this.queue.offer(NotificationLite.instance().next(r));
            emit();
        }

        boolean checkTerminated(boolean z, boolean z2, Subscriber<? super R> subscriber) {
            if (subscriber.isUnsubscribed()) {
                return true;
            }
            if (!z) {
                return false;
            }
            Throwable th = this.error;
            if (th != null) {
                subscriber.onError(th);
                return true;
            }
            if (!z2) {
                return false;
            }
            subscriber.onCompleted();
            return true;
        }

        @Override // p035rx.Observer
        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            emit();
        }

        @Override // p035rx.Observer
        public void onCompleted() {
            this.done = true;
            emit();
        }

        @Override // p035rx.Producer
        public void request(long j) {
            if (j < 0) {
                throw new IllegalArgumentException("n >= required but it was " + j);
            }
            if (j != 0) {
                BackpressureUtils.getAndAddRequest(this.requested, j);
                Producer producer = this.producer;
                if (producer == null) {
                    synchronized (this.requested) {
                        producer = this.producer;
                        if (producer == null) {
                            this.missedRequested = BackpressureUtils.addCap(this.missedRequested, j);
                        }
                    }
                }
                if (producer != null) {
                    producer.request(j);
                }
                emit();
            }
        }

        public void setProducer(Producer producer) {
            long j;
            if (producer == null) {
                throw null;
            }
            synchronized (this.requested) {
                if (this.producer != null) {
                    throw new IllegalStateException("Can't set more than one Producer!");
                }
                j = this.missedRequested;
                if (j != LongCompanionObject.MAX_VALUE) {
                    j--;
                }
                this.missedRequested = 0L;
                this.producer = producer;
            }
            if (j > 0) {
                producer.request(j);
            }
            emit();
        }

        void emit() {
            synchronized (this) {
                if (this.emitting) {
                    this.missed = true;
                } else {
                    this.emitting = true;
                    emitLoop();
                }
            }
        }

        void emitLoop() {
            Subscriber<? super R> subscriber = this.child;
            Queue<Object> queue = this.queue;
            NotificationLite notificationLiteInstance = NotificationLite.instance();
            AtomicLong atomicLong = this.requested;
            long jAddAndGet = atomicLong.get();
            while (true) {
                boolean z = jAddAndGet == LongCompanionObject.MAX_VALUE;
                if (checkTerminated(this.done, queue.isEmpty(), subscriber)) {
                    return;
                }
                long j = 0;
                while (jAddAndGet != 0) {
                    boolean z2 = this.done;
                    Object objPoll = queue.poll();
                    boolean z3 = objPoll == null;
                    if (checkTerminated(z2, z3, subscriber)) {
                        return;
                    }
                    if (z3) {
                        break;
                    }
                    Manifest manifest = (Object) notificationLiteInstance.getValue(objPoll);
                    try {
                        subscriber.onNext(manifest);
                        jAddAndGet--;
                        j--;
                    } catch (Throwable th) {
                        Exceptions.throwOrReport(th, subscriber, manifest);
                        return;
                    }
                }
                if (j != 0 && !z) {
                    jAddAndGet = atomicLong.addAndGet(j);
                }
                synchronized (this) {
                    if (!this.missed) {
                        this.emitting = false;
                        return;
                    }
                    this.missed = false;
                }
            }
        }
    }
}
