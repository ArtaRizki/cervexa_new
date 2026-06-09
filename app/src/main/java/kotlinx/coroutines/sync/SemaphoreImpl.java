package kotlinx.coroutines.sync;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.internal.ConcurrentLinkedListKt;
import kotlinx.coroutines.internal.ConcurrentLinkedListNode;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.SegmentOrClosed;

/* JADX INFO: compiled from: Semaphore.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\b\u0002\u0018\u00002\u00020\u0019B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\u0007\u001a\u00020\u0006H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ\u0013\u0010\t\u001a\u00020\u0006H\u0082@ø\u0001\u0000¢\u0006\u0004\b\t\u0010\bJ\u001d\u0010\r\u001a\u00020\f2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\nH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\fH\u0002¢\u0006\u0004\b\u0013\u0010\u0012R\u0016\u0010\u0016\u001a\u00020\u00018V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0002\u001a\u00020\u00018\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010\u0017\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, m2290d2 = {"Lkotlinx/coroutines/sync/SemaphoreImpl;", "", "permits", "acquiredPermits", "<init>", "(II)V", "", "acquire", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "acquireSlowPath", "Lkotlinx/coroutines/CancellableContinuation;", "cont", "", "addAcquireToQueue", "(Lkotlinx/coroutines/CancellableContinuation;)Z", "release", "()V", "tryAcquire", "()Z", "tryResumeNextFromQueue", "getAvailablePermits", "()I", "availablePermits", "I", "kotlinx-coroutines-core", "Lkotlinx/coroutines/sync/Semaphore;"}, m2291k = 1, m2292mv = {1, 4, 0})
final class SemaphoreImpl implements Semaphore {
    volatile int _availablePermits;
    private volatile long deqIdx = 0;
    private volatile long enqIdx = 0;
    private volatile Object head;
    private final int permits;
    private volatile Object tail;
    private static final AtomicReferenceFieldUpdater head$FU = AtomicReferenceFieldUpdater.newUpdater(SemaphoreImpl.class, Object.class, "head");
    private static final AtomicLongFieldUpdater deqIdx$FU = AtomicLongFieldUpdater.newUpdater(SemaphoreImpl.class, "deqIdx");
    private static final AtomicReferenceFieldUpdater tail$FU = AtomicReferenceFieldUpdater.newUpdater(SemaphoreImpl.class, Object.class, "tail");
    private static final AtomicLongFieldUpdater enqIdx$FU = AtomicLongFieldUpdater.newUpdater(SemaphoreImpl.class, "enqIdx");
    static final AtomicIntegerFieldUpdater _availablePermits$FU = AtomicIntegerFieldUpdater.newUpdater(SemaphoreImpl.class, "_availablePermits");

    public SemaphoreImpl(int i, int i2) {
        this.permits = i;
        if (!(this.permits > 0)) {
            throw new IllegalArgumentException(("Semaphore should have at least 1 permit, but had " + this.permits).toString());
        }
        if (!(i2 >= 0 && this.permits >= i2)) {
            throw new IllegalArgumentException(("The number of acquired permits should be in 0.." + this.permits).toString());
        }
        SemaphoreSegment semaphoreSegment = new SemaphoreSegment(0L, null, 2);
        this.head = semaphoreSegment;
        this.tail = semaphoreSegment;
        this._availablePermits = this.permits - i2;
    }

    @Override // kotlinx.coroutines.sync.Semaphore
    public int getAvailablePermits() {
        return Math.max(this._availablePermits, 0);
    }

    @Override // kotlinx.coroutines.sync.Semaphore
    public Object acquire(Continuation<? super Unit> continuation) {
        if (_availablePermits$FU.getAndDecrement(this) > 0) {
            return Unit.INSTANCE;
        }
        Object objAcquireSlowPath = acquireSlowPath(continuation);
        return objAcquireSlowPath == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAcquireSlowPath : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean addAcquireToQueue(CancellableContinuation<? super Unit> cont) {
        Object objM3711constructorimpl;
        boolean z;
        SemaphoreSegment semaphoreSegment = (SemaphoreSegment) this.tail;
        long andIncrement = enqIdx$FU.getAndIncrement(this);
        long j = andIncrement / ((long) SemaphoreKt.SEGMENT_SIZE);
        do {
            SemaphoreSegment semaphoreSegment2 = semaphoreSegment;
            while (true) {
                if (semaphoreSegment2.getId() < j || semaphoreSegment2.getRemoved()) {
                    Object obj = semaphoreSegment2.get_next();
                    if (obj == ConcurrentLinkedListKt.CLOSED) {
                        objM3711constructorimpl = SegmentOrClosed.m3711constructorimpl(ConcurrentLinkedListKt.CLOSED);
                        break;
                    }
                    SemaphoreSegment semaphoreSegmentCreateSegment = (Segment) ((ConcurrentLinkedListNode) obj);
                    if (semaphoreSegmentCreateSegment == null) {
                        semaphoreSegmentCreateSegment = SemaphoreKt.createSegment(semaphoreSegment2.getId() + 1, (SemaphoreSegment) semaphoreSegment2);
                        if (semaphoreSegment2.trySetNext(semaphoreSegmentCreateSegment)) {
                            if (semaphoreSegment2.getRemoved()) {
                                semaphoreSegment2.remove();
                            }
                        }
                    }
                    semaphoreSegment2 = semaphoreSegmentCreateSegment;
                } else {
                    objM3711constructorimpl = SegmentOrClosed.m3711constructorimpl(semaphoreSegment2);
                    break;
                }
            }
            if (SegmentOrClosed.m3716isClosedimpl(objM3711constructorimpl)) {
                break;
            }
            Segment segmentM3714getSegmentimpl = SegmentOrClosed.m3714getSegmentimpl(objM3711constructorimpl);
            while (true) {
                Segment segment = (Segment) this.tail;
                if (segment.getId() >= segmentM3714getSegmentimpl.getId()) {
                    break;
                }
                if (!segmentM3714getSegmentimpl.tryIncPointers$kotlinx_coroutines_core()) {
                    z = false;
                    break;
                }
                if (tail$FU.compareAndSet(this, segment, segmentM3714getSegmentimpl)) {
                    if (segment.decPointers$kotlinx_coroutines_core()) {
                        segment.remove();
                    }
                } else if (segmentM3714getSegmentimpl.decPointers$kotlinx_coroutines_core()) {
                    segmentM3714getSegmentimpl.remove();
                }
            }
            z = true;
        } while (!z);
        SemaphoreSegment semaphoreSegment3 = (SemaphoreSegment) SegmentOrClosed.m3714getSegmentimpl(objM3711constructorimpl);
        int i = (int) (andIncrement % ((long) SemaphoreKt.SEGMENT_SIZE));
        if (semaphoreSegment3.acquirers.compareAndSet(i, null, cont)) {
            cont.invokeOnCancellation(new CancelSemaphoreAcquisitionHandler(semaphoreSegment3, i));
            return true;
        }
        if (semaphoreSegment3.acquirers.compareAndSet(i, SemaphoreKt.PERMIT, SemaphoreKt.TAKEN)) {
            Unit unit = Unit.INSTANCE;
            Result.Companion companion = Result.INSTANCE;
            cont.resumeWith(Result.m2353constructorimpl(unit));
            return true;
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(semaphoreSegment3.acquirers.get(i) == SemaphoreKt.BROKEN)) {
                throw new AssertionError();
            }
        }
        return false;
    }

    private final boolean tryResumeNextFromQueue() {
        Object objM3711constructorimpl;
        int i;
        boolean z;
        SemaphoreSegment semaphoreSegment = (SemaphoreSegment) this.head;
        long andIncrement = deqIdx$FU.getAndIncrement(this);
        long j = andIncrement / ((long) SemaphoreKt.SEGMENT_SIZE);
        do {
            SemaphoreSegment semaphoreSegment2 = semaphoreSegment;
            while (true) {
                if (semaphoreSegment2.getId() < j || semaphoreSegment2.getRemoved()) {
                    Object obj = semaphoreSegment2.get_next();
                    if (obj == ConcurrentLinkedListKt.CLOSED) {
                        objM3711constructorimpl = SegmentOrClosed.m3711constructorimpl(ConcurrentLinkedListKt.CLOSED);
                        break;
                    }
                    SemaphoreSegment semaphoreSegmentCreateSegment = (Segment) ((ConcurrentLinkedListNode) obj);
                    if (semaphoreSegmentCreateSegment == null) {
                        semaphoreSegmentCreateSegment = SemaphoreKt.createSegment(semaphoreSegment2.getId() + 1, (SemaphoreSegment) semaphoreSegment2);
                        if (semaphoreSegment2.trySetNext(semaphoreSegmentCreateSegment)) {
                            if (semaphoreSegment2.getRemoved()) {
                                semaphoreSegment2.remove();
                            }
                        }
                    }
                    semaphoreSegment2 = semaphoreSegmentCreateSegment;
                } else {
                    objM3711constructorimpl = SegmentOrClosed.m3711constructorimpl(semaphoreSegment2);
                    break;
                }
            }
            if (SegmentOrClosed.m3716isClosedimpl(objM3711constructorimpl)) {
                break;
            }
            Segment segmentM3714getSegmentimpl = SegmentOrClosed.m3714getSegmentimpl(objM3711constructorimpl);
            while (true) {
                Segment segment = (Segment) this.head;
                if (segment.getId() >= segmentM3714getSegmentimpl.getId()) {
                    break;
                }
                if (!segmentM3714getSegmentimpl.tryIncPointers$kotlinx_coroutines_core()) {
                    z = false;
                    break;
                }
                if (head$FU.compareAndSet(this, segment, segmentM3714getSegmentimpl)) {
                    if (segment.decPointers$kotlinx_coroutines_core()) {
                        segment.remove();
                    }
                } else if (segmentM3714getSegmentimpl.decPointers$kotlinx_coroutines_core()) {
                    segmentM3714getSegmentimpl.remove();
                }
            }
            z = true;
        } while (!z);
        SemaphoreSegment semaphoreSegment3 = (SemaphoreSegment) SegmentOrClosed.m3714getSegmentimpl(objM3711constructorimpl);
        semaphoreSegment3.cleanPrev();
        if (semaphoreSegment3.getId() > j) {
            return false;
        }
        int i2 = (int) (andIncrement % ((long) SemaphoreKt.SEGMENT_SIZE));
        Object andSet = semaphoreSegment3.acquirers.getAndSet(i2, SemaphoreKt.PERMIT);
        if (andSet == null) {
            int i3 = SemaphoreKt.MAX_SPIN_CYCLES;
            for (i = 0; i < i3; i++) {
                if (semaphoreSegment3.acquirers.get(i2) == SemaphoreKt.TAKEN) {
                    return true;
                }
            }
            return !semaphoreSegment3.acquirers.compareAndSet(i2, SemaphoreKt.PERMIT, SemaphoreKt.BROKEN);
        }
        if (andSet == SemaphoreKt.CANCELLED) {
            return false;
        }
        return SemaphoreKt.tryResume((CancellableContinuation) andSet);
    }

    @Override // kotlinx.coroutines.sync.Semaphore
    public boolean tryAcquire() {
        int i;
        do {
            i = this._availablePermits;
            if (i <= 0) {
                return false;
            }
        } while (!_availablePermits$FU.compareAndSet(this, i, i - 1));
        return true;
    }

    final /* synthetic */ Object acquireSlowPath(Continuation<? super Unit> continuation) {
        CancellableContinuationImpl orCreateCancellableContinuation = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(continuation));
        CancellableContinuationImpl cancellableContinuationImpl = orCreateCancellableContinuation;
        while (true) {
            if (addAcquireToQueue(cancellableContinuationImpl)) {
                break;
            }
            if (_availablePermits$FU.getAndDecrement(this) > 0) {
                Unit unit = Unit.INSTANCE;
                Result.Companion companion = Result.INSTANCE;
                cancellableContinuationImpl.resumeWith(Result.m2353constructorimpl(unit));
                break;
            }
        }
        Object result = orCreateCancellableContinuation.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    @Override // kotlinx.coroutines.sync.Semaphore
    public void release() {
        while (true) {
            int i = this._availablePermits;
            if (!(i < this.permits)) {
                throw new IllegalStateException(("The number of released permits cannot be greater than " + this.permits).toString());
            }
            if (_availablePermits$FU.compareAndSet(this, i, i + 1) && (i >= 0 || tryResumeNextFromQueue())) {
                return;
            }
        }
    }
}
