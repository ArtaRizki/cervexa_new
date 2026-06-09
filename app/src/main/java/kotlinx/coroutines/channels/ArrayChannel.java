package kotlinx.coroutines.channels;

import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.channels.AbstractChannel;
import kotlinx.coroutines.channels.AbstractSendChannel;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;

/* JADX INFO: compiled from: ArrayChannel.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u000009B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\t\u001a\u00020\b2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006H\u0014¢\u0006\u0004\b\t\u0010\nJ\u0019\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\f\u001a\u00020\u000bH\u0014¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0012\u0010\u0005J\u0017\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00028\u0000H\u0014¢\u0006\u0004\b\u0014\u0010\u0015J#\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00028\u00002\n\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u0016H\u0014¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\bH\u0014¢\u0006\u0004\b\u001b\u0010\u001cJ\u0011\u0010\u001d\u001a\u0004\u0018\u00010\rH\u0014¢\u0006\u0004\b\u001d\u0010\u001eJ\u001d\u0010\u001f\u001a\u0004\u0018\u00010\r2\n\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u0016H\u0014¢\u0006\u0004\b\u001f\u0010 R\u001e\u0010\"\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0!8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\"\u0010#R\u0016\u0010'\u001a\u00020$8T@\u0014X\u0094\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&R\u0019\u0010\u0003\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0003\u0010(\u001a\u0004\b)\u0010*R\u0016\u0010+\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b+\u0010(R\u0016\u0010,\u001a\u00020\b8D@\u0004X\u0084\u0004¢\u0006\u0006\u001a\u0004\b,\u0010-R\u0016\u0010.\u001a\u00020\b8D@\u0004X\u0084\u0004¢\u0006\u0006\u001a\u0004\b.\u0010-R\u0016\u0010/\u001a\u00020\b8D@\u0004X\u0084\u0004¢\u0006\u0006\u001a\u0004\b/\u0010-R\u0016\u00100\u001a\u00020\b8D@\u0004X\u0084\u0004¢\u0006\u0006\u001a\u0004\b0\u0010-R\u0016\u00101\u001a\u00020\b8V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b1\u0010-R\u0016\u00102\u001a\u00020\b8V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b2\u0010-R\u0016\u00103\u001a\u00020\b8V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b3\u0010-R\u001a\u00106\u001a\u000604j\u0002`58\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b6\u00107¨\u00068"}, m2290d2 = {"Lkotlinx/coroutines/channels/ArrayChannel;", "E", "", "capacity", "<init>", "(I)V", "Lkotlinx/coroutines/channels/Receive;", "receive", "", "enqueueReceiveInternal", "(Lkotlinx/coroutines/channels/Receive;)Z", "Lkotlinx/coroutines/channels/Send;", "send", "", "enqueueSend", "(Lkotlinx/coroutines/channels/Send;)Ljava/lang/Object;", "currentSize", "", "ensureCapacity", "element", "offerInternal", "(Ljava/lang/Object;)Ljava/lang/Object;", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "offerSelectInternal", "(Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "wasClosed", "onCancelIdempotent", "(Z)V", "pollInternal", "()Ljava/lang/Object;", "pollSelectInternal", "(Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "", "buffer", "[Ljava/lang/Object;", "", "getBufferDebugString", "()Ljava/lang/String;", "bufferDebugString", "I", "getCapacity", "()I", "head", "isBufferAlwaysEmpty", "()Z", "isBufferAlwaysFull", "isBufferEmpty", "isBufferFull", "isClosedForReceive", "isEmpty", "isFull", "Ljava/util/concurrent/locks/ReentrantLock;", "Lkotlinx/coroutines/internal/ReentrantLock;", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "kotlinx-coroutines-core", "Lkotlinx/coroutines/channels/AbstractChannel;"}, m2291k = 1, m2292mv = {1, 4, 0})
public class ArrayChannel<E> extends AbstractChannel<E> {
    private Object[] buffer;
    private final int capacity;
    private int head;
    private final ReentrantLock lock;
    private volatile int size;

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected final boolean isBufferAlwaysEmpty() {
        return false;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected final boolean isBufferAlwaysFull() {
        return false;
    }

    public final int getCapacity() {
        return this.capacity;
    }

    public ArrayChannel(int i) {
        this.capacity = i;
        if (!(i >= 1)) {
            throw new IllegalArgumentException(("ArrayChannel capacity must be at least 1, but " + this.capacity + " was specified").toString());
        }
        this.lock = new ReentrantLock();
        this.buffer = new Object[Math.min(this.capacity, 8)];
        this.size = 0;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected final boolean isBufferEmpty() {
        return this.size == 0;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected final boolean isBufferFull() {
        return this.size == this.capacity;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel, kotlinx.coroutines.channels.SendChannel
    public boolean isFull() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            return isFullImpl();
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel, kotlinx.coroutines.channels.ReceiveChannel
    public boolean isEmpty() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            return isEmptyImpl();
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel, kotlinx.coroutines.channels.ReceiveChannel
    public boolean isClosedForReceive() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            return super.isClosedForReceive();
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected Object offerInternal(E element) {
        ReceiveOrClosed<E> receiveOrClosedTakeFirstReceiveOrPeekClosed;
        Symbol symbolTryResumeReceive;
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            int i = this.size;
            Closed<?> closedForSend = getClosedForSend();
            if (closedForSend != null) {
                return closedForSend;
            }
            if (i < this.capacity) {
                this.size = i + 1;
                if (i == 0) {
                    do {
                        receiveOrClosedTakeFirstReceiveOrPeekClosed = takeFirstReceiveOrPeekClosed();
                        if (receiveOrClosedTakeFirstReceiveOrPeekClosed != null) {
                            if (receiveOrClosedTakeFirstReceiveOrPeekClosed instanceof Closed) {
                                this.size = i;
                                Intrinsics.checkNotNull(receiveOrClosedTakeFirstReceiveOrPeekClosed);
                                return receiveOrClosedTakeFirstReceiveOrPeekClosed;
                            }
                            Intrinsics.checkNotNull(receiveOrClosedTakeFirstReceiveOrPeekClosed);
                            symbolTryResumeReceive = receiveOrClosedTakeFirstReceiveOrPeekClosed.tryResumeReceive(element, null);
                        }
                    } while (symbolTryResumeReceive == null);
                    if (DebugKt.getASSERTIONS_ENABLED()) {
                        if (!(symbolTryResumeReceive == CancellableContinuationImplKt.RESUME_TOKEN)) {
                            throw new AssertionError();
                        }
                    }
                    this.size = i;
                    Unit unit = Unit.INSTANCE;
                    reentrantLock.unlock();
                    Intrinsics.checkNotNull(receiveOrClosedTakeFirstReceiveOrPeekClosed);
                    receiveOrClosedTakeFirstReceiveOrPeekClosed.completeResumeReceive(element);
                    Intrinsics.checkNotNull(receiveOrClosedTakeFirstReceiveOrPeekClosed);
                    return receiveOrClosedTakeFirstReceiveOrPeekClosed.getOfferResult();
                }
                ensureCapacity(i);
                this.buffer[(this.head + i) % this.buffer.length] = element;
                return AbstractChannelKt.OFFER_SUCCESS;
            }
            return AbstractChannelKt.OFFER_FAILED;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected Object offerSelectInternal(E element, SelectInstance<?> select) {
        Object objPerformAtomicTrySelect;
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            int i = this.size;
            Closed<?> closedForSend = getClosedForSend();
            if (closedForSend != null) {
                return closedForSend;
            }
            if (i < this.capacity) {
                this.size = i + 1;
                if (i == 0) {
                    do {
                        AbstractSendChannel.TryOfferDesc<E> tryOfferDescDescribeTryOffer = describeTryOffer(element);
                        objPerformAtomicTrySelect = select.performAtomicTrySelect(tryOfferDescDescribeTryOffer);
                        if (objPerformAtomicTrySelect == null) {
                            this.size = i;
                            ReceiveOrClosed<? super E> result = tryOfferDescDescribeTryOffer.getResult();
                            Unit unit = Unit.INSTANCE;
                            reentrantLock.unlock();
                            Intrinsics.checkNotNull(result);
                            result.completeResumeReceive(element);
                            Intrinsics.checkNotNull(result);
                            return result.getOfferResult();
                        }
                        if (objPerformAtomicTrySelect == AbstractChannelKt.OFFER_FAILED) {
                        }
                    } while (objPerformAtomicTrySelect == AtomicKt.RETRY_ATOMIC);
                    if (objPerformAtomicTrySelect != SelectKt.getALREADY_SELECTED() && !(objPerformAtomicTrySelect instanceof Closed)) {
                        throw new IllegalStateException(("performAtomicTrySelect(describeTryOffer) returned " + objPerformAtomicTrySelect).toString());
                    }
                    this.size = i;
                    return objPerformAtomicTrySelect;
                }
                if (!select.trySelect()) {
                    this.size = i;
                    return SelectKt.getALREADY_SELECTED();
                }
                ensureCapacity(i);
                this.buffer[(this.head + i) % this.buffer.length] = element;
                return AbstractChannelKt.OFFER_SUCCESS;
            }
            return AbstractChannelKt.OFFER_FAILED;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected Object enqueueSend(Send send) {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            return super.enqueueSend(send);
        } finally {
            reentrantLock.unlock();
        }
    }

    private final void ensureCapacity(int currentSize) {
        Object[] objArr = this.buffer;
        if (currentSize >= objArr.length) {
            Object[] objArr2 = new Object[Math.min(objArr.length * 2, this.capacity)];
            for (int i = 0; i < currentSize; i++) {
                Object[] objArr3 = this.buffer;
                objArr2[i] = objArr3[(this.head + i) % objArr3.length];
            }
            this.buffer = objArr2;
            this.head = 0;
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected Object pollInternal() {
        Send send = (Send) null;
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            int i = this.size;
            if (i == 0) {
                Object closedForSend = getClosedForSend();
                if (closedForSend == null) {
                    closedForSend = AbstractChannelKt.POLL_FAILED;
                }
                return closedForSend;
            }
            Object obj = this.buffer[this.head];
            this.buffer[this.head] = null;
            this.size = i - 1;
            Object pollResult = AbstractChannelKt.POLL_FAILED;
            if (i == this.capacity) {
                while (true) {
                    Send sendTakeFirstSendOrPeekClosed = takeFirstSendOrPeekClosed();
                    if (sendTakeFirstSendOrPeekClosed == null) {
                        break;
                    }
                    Intrinsics.checkNotNull(sendTakeFirstSendOrPeekClosed);
                    Symbol symbolTryResumeSend = sendTakeFirstSendOrPeekClosed.tryResumeSend(null);
                    if (symbolTryResumeSend != null) {
                        if (DebugKt.getASSERTIONS_ENABLED()) {
                            if (!(symbolTryResumeSend == CancellableContinuationImplKt.RESUME_TOKEN)) {
                                throw new AssertionError();
                            }
                        }
                        Intrinsics.checkNotNull(sendTakeFirstSendOrPeekClosed);
                        pollResult = sendTakeFirstSendOrPeekClosed.getPollResult();
                        send = sendTakeFirstSendOrPeekClosed;
                        z = true;
                    } else {
                        send = sendTakeFirstSendOrPeekClosed;
                    }
                }
            }
            if (pollResult != AbstractChannelKt.POLL_FAILED && !(pollResult instanceof Closed)) {
                this.size = i;
                this.buffer[(this.head + i) % this.buffer.length] = pollResult;
            }
            this.head = (this.head + 1) % this.buffer.length;
            Unit unit = Unit.INSTANCE;
            if (z) {
                Intrinsics.checkNotNull(send);
                send.completeResumeSend();
            }
            return obj;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected Object pollSelectInternal(SelectInstance<?> select) {
        boolean z;
        Send result = (Send) null;
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            int i = this.size;
            if (i == 0) {
                Object closedForSend = getClosedForSend();
                if (closedForSend == null) {
                    closedForSend = AbstractChannelKt.POLL_FAILED;
                }
                return closedForSend;
            }
            Object obj = this.buffer[this.head];
            this.buffer[this.head] = null;
            this.size = i - 1;
            Object pollResult = AbstractChannelKt.POLL_FAILED;
            if (i == this.capacity) {
                while (true) {
                    AbstractChannel.TryPollDesc<E> tryPollDescDescribeTryPoll = describeTryPoll();
                    Object objPerformAtomicTrySelect = select.performAtomicTrySelect(tryPollDescDescribeTryPoll);
                    if (objPerformAtomicTrySelect == null) {
                        result = tryPollDescDescribeTryPoll.getResult();
                        Intrinsics.checkNotNull(result);
                        pollResult = result.getPollResult();
                        break;
                    }
                    if (objPerformAtomicTrySelect == AbstractChannelKt.POLL_FAILED) {
                        break;
                    }
                    if (objPerformAtomicTrySelect != AtomicKt.RETRY_ATOMIC) {
                        if (objPerformAtomicTrySelect == SelectKt.getALREADY_SELECTED()) {
                            this.size = i;
                            this.buffer[this.head] = obj;
                            return objPerformAtomicTrySelect;
                        }
                        if (objPerformAtomicTrySelect instanceof Closed) {
                            result = (Send) objPerformAtomicTrySelect;
                            pollResult = objPerformAtomicTrySelect;
                        } else {
                            throw new IllegalStateException(("performAtomicTrySelect(describeTryOffer) returned " + objPerformAtomicTrySelect).toString());
                        }
                    }
                }
                z = true;
            } else {
                z = false;
            }
            if (pollResult != AbstractChannelKt.POLL_FAILED && !(pollResult instanceof Closed)) {
                this.size = i;
                this.buffer[(this.head + i) % this.buffer.length] = pollResult;
            } else if (!select.trySelect()) {
                this.size = i;
                this.buffer[this.head] = obj;
                return SelectKt.getALREADY_SELECTED();
            }
            this.head = (this.head + 1) % this.buffer.length;
            Unit unit = Unit.INSTANCE;
            if (z) {
                Intrinsics.checkNotNull(result);
                result.completeResumeSend();
            }
            return obj;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected boolean enqueueReceiveInternal(Receive<? super E> receive) {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            return super.enqueueReceiveInternal(receive);
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected void onCancelIdempotent(boolean wasClosed) {
        if (wasClosed) {
            ReentrantLock reentrantLock = this.lock;
            reentrantLock.lock();
            try {
                int i = this.size;
                for (int i2 = 0; i2 < i; i2++) {
                    this.buffer[this.head] = 0;
                    this.head = (this.head + 1) % this.buffer.length;
                }
                this.size = 0;
                Unit unit = Unit.INSTANCE;
            } finally {
                reentrantLock.unlock();
            }
        }
        super.onCancelIdempotent(wasClosed);
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected String getBufferDebugString() {
        return "(buffer:capacity=" + this.capacity + ",size=" + this.size + ')';
    }
}
