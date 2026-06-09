package kotlinx.coroutines.channels;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.YieldKt;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.InlineList;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.LockFreeLinkedList_commonKt;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;

/* JADX INFO: compiled from: AbstractChannel.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\b \u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u000004:\u0004abcdB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\n\u0010\u000bJ#\u0010\u000f\u001a\u000e\u0012\u0002\b\u00030\rj\u0006\u0012\u0002\b\u0003`\u000e2\u0006\u0010\f\u001a\u00028\u0000H\u0004¢\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u00112\u0006\u0010\f\u001a\u00028\u0000H\u0004¢\u0006\u0004\b\u0012\u0010\u0013J\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0015\u001a\u00020\u0014H\u0014¢\u0006\u0004\b\u0017\u0010\u0018J\u001b\u0010\u001c\u001a\u00020\u001b2\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u0019H\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010\u001e\u001a\u00020\u00042\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u0019H\u0002¢\u0006\u0004\b\u001e\u0010\u001fJ)\u0010#\u001a\u00020\u001b2\u0018\u0010\"\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u001b0 j\u0002`!H\u0016¢\u0006\u0004\b#\u0010$J\u0019\u0010%\u001a\u00020\u001b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002¢\u0006\u0004\b%\u0010&J\u0015\u0010'\u001a\u00020\u00062\u0006\u0010\f\u001a\u00028\u0000¢\u0006\u0004\b'\u0010(J\u0017\u0010)\u001a\u00020\u00162\u0006\u0010\f\u001a\u00028\u0000H\u0014¢\u0006\u0004\b)\u0010*J#\u0010-\u001a\u00020\u00162\u0006\u0010\f\u001a\u00028\u00002\n\u0010,\u001a\u0006\u0012\u0002\b\u00030+H\u0014¢\u0006\u0004\b-\u0010.J\u0017\u00100\u001a\u00020\u001b2\u0006\u0010\u001a\u001a\u00020/H\u0014¢\u0006\u0004\b0\u00101JX\u00107\u001a\u00020\u001b\"\u0004\b\u0001\u001022\f\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00010+2\u0006\u0010\f\u001a\u00028\u00002(\u00106\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u000004\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u000105\u0012\u0006\u0012\u0004\u0018\u00010\u001603H\u0002ø\u0001\u0000¢\u0006\u0004\b7\u00108J\u001b\u0010\u0015\u001a\u00020\u001b2\u0006\u0010\f\u001a\u00028\u0000H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0015\u00109J\u001d\u0010;\u001a\b\u0012\u0002\b\u0003\u0018\u00010:2\u0006\u0010\f\u001a\u00028\u0000H\u0004¢\u0006\u0004\b;\u0010<J\u001b\u0010>\u001a\u00020\u001b2\u0006\u0010\f\u001a\u00028\u0000H\u0080@ø\u0001\u0000¢\u0006\u0004\b=\u00109J\u001b\u0010?\u001a\u00020\u001b2\u0006\u0010\f\u001a\u00028\u0000H\u0082@ø\u0001\u0000¢\u0006\u0004\b?\u00109J\u0017\u0010@\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010:H\u0014¢\u0006\u0004\b@\u0010AJ\u0011\u0010B\u001a\u0004\u0018\u00010\u0014H\u0004¢\u0006\u0004\bB\u0010CJ\u000f\u0010E\u001a\u00020DH\u0016¢\u0006\u0004\bE\u0010FJ#\u0010G\u001a\u00020\u001b*\u0006\u0012\u0002\b\u0003052\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u0019H\u0002¢\u0006\u0004\bG\u0010HR\u0016\u0010J\u001a\u00020D8T@\u0014X\u0094\u0004¢\u0006\u0006\u001a\u0004\bI\u0010FR\u001c\u0010M\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00198D@\u0004X\u0084\u0004¢\u0006\u0006\u001a\u0004\bK\u0010LR\u001c\u0010O\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00198D@\u0004X\u0084\u0004¢\u0006\u0006\u001a\u0004\bN\u0010LR\u0016\u0010P\u001a\u00020\u00068$@$X¤\u0004¢\u0006\u0006\u001a\u0004\bP\u0010QR\u0016\u0010R\u001a\u00020\u00068$@$X¤\u0004¢\u0006\u0006\u001a\u0004\bR\u0010QR\u0013\u0010S\u001a\u00020\u00068F@\u0006¢\u0006\u0006\u001a\u0004\bS\u0010QR\u0016\u0010T\u001a\u00020\u00068V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\bT\u0010QR\u0016\u0010U\u001a\u00020\u00068D@\u0004X\u0084\u0004¢\u0006\u0006\u001a\u0004\bU\u0010QR%\u0010Y\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u0000040V8F@\u0006¢\u0006\u0006\u001a\u0004\bW\u0010XR\u001c\u0010[\u001a\u00020Z8\u0004@\u0004X\u0084\u0004¢\u0006\f\n\u0004\b[\u0010\\\u001a\u0004\b]\u0010^R\u0016\u0010`\u001a\u00020D8B@\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b_\u0010F\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006e"}, m2290d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel;", "E", "<init>", "()V", "", "cause", "", "close", "(Ljava/lang/Throwable;)Z", "", "countQueueSize", "()I", "element", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "Lkotlinx/coroutines/internal/AddLastDesc;", "describeSendBuffered", "(Ljava/lang/Object;)Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "Lkotlinx/coroutines/channels/AbstractSendChannel$TryOfferDesc;", "describeTryOffer", "(Ljava/lang/Object;)Lkotlinx/coroutines/channels/AbstractSendChannel$TryOfferDesc;", "Lkotlinx/coroutines/channels/Send;", "send", "", "enqueueSend", "(Lkotlinx/coroutines/channels/Send;)Ljava/lang/Object;", "Lkotlinx/coroutines/channels/Closed;", "closed", "", "helpClose", "(Lkotlinx/coroutines/channels/Closed;)V", "helpCloseAndGetSendException", "(Lkotlinx/coroutines/channels/Closed;)Ljava/lang/Throwable;", "Lkotlin/Function1;", "Lkotlinx/coroutines/channels/Handler;", "handler", "invokeOnClose", "(Lkotlin/jvm/functions/Function1;)V", "invokeOnCloseHandler", "(Ljava/lang/Throwable;)V", "offer", "(Ljava/lang/Object;)Z", "offerInternal", "(Ljava/lang/Object;)Ljava/lang/Object;", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "offerSelectInternal", "(Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "onClosedIdempotent", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)V", "R", "Lkotlin/Function2;", "Lkotlinx/coroutines/channels/SendChannel;", "Lkotlin/coroutines/Continuation;", "block", "registerSelectSend", "(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/coroutines/channels/ReceiveOrClosed;", "sendBuffered", "(Ljava/lang/Object;)Lkotlinx/coroutines/channels/ReceiveOrClosed;", "sendFair$kotlinx_coroutines_core", "sendFair", "sendSuspend", "takeFirstReceiveOrPeekClosed", "()Lkotlinx/coroutines/channels/ReceiveOrClosed;", "takeFirstSendOrPeekClosed", "()Lkotlinx/coroutines/channels/Send;", "", "toString", "()Ljava/lang/String;", "helpCloseAndResumeWithSendException", "(Lkotlin/coroutines/Continuation;Lkotlinx/coroutines/channels/Closed;)V", "getBufferDebugString", "bufferDebugString", "getClosedForReceive", "()Lkotlinx/coroutines/channels/Closed;", "closedForReceive", "getClosedForSend", "closedForSend", "isBufferAlwaysFull", "()Z", "isBufferFull", "isClosedForSend", "isFull", "isFullImpl", "Lkotlinx/coroutines/selects/SelectClause2;", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "onSend", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "getQueue", "()Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "getQueueDebugStateString", "queueDebugStateString", "SendBuffered", "SendBufferedDesc", "SendSelect", "TryOfferDesc", "kotlinx-coroutines-core"}, m2291k = 1, m2292mv = {1, 4, 0})
public abstract class AbstractSendChannel<E> implements SendChannel<E> {
    private static final AtomicReferenceFieldUpdater onCloseHandler$FU = AtomicReferenceFieldUpdater.newUpdater(AbstractSendChannel.class, Object.class, "onCloseHandler");
    private final LockFreeLinkedListHead queue = new LockFreeLinkedListHead();
    private volatile Object onCloseHandler = null;

    protected String getBufferDebugString() {
        return "";
    }

    protected abstract boolean isBufferAlwaysFull();

    protected abstract boolean isBufferFull();

    protected void onClosedIdempotent(LockFreeLinkedListNode closed) {
    }

    protected final LockFreeLinkedListHead getQueue() {
        return this.queue;
    }

    protected Object offerInternal(E element) {
        ReceiveOrClosed<E> receiveOrClosedTakeFirstReceiveOrPeekClosed;
        Symbol symbolTryResumeReceive;
        do {
            receiveOrClosedTakeFirstReceiveOrPeekClosed = takeFirstReceiveOrPeekClosed();
            if (receiveOrClosedTakeFirstReceiveOrPeekClosed == null) {
                return AbstractChannelKt.OFFER_FAILED;
            }
            symbolTryResumeReceive = receiveOrClosedTakeFirstReceiveOrPeekClosed.tryResumeReceive(element, null);
        } while (symbolTryResumeReceive == null);
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(symbolTryResumeReceive == CancellableContinuationImplKt.RESUME_TOKEN)) {
                throw new AssertionError();
            }
        }
        receiveOrClosedTakeFirstReceiveOrPeekClosed.completeResumeReceive(element);
        return receiveOrClosedTakeFirstReceiveOrPeekClosed.getOfferResult();
    }

    protected Object offerSelectInternal(E element, SelectInstance<?> select) {
        TryOfferDesc<E> tryOfferDescDescribeTryOffer = describeTryOffer(element);
        Object objPerformAtomicTrySelect = select.performAtomicTrySelect(tryOfferDescDescribeTryOffer);
        if (objPerformAtomicTrySelect != null) {
            return objPerformAtomicTrySelect;
        }
        ReceiveOrClosed<? super E> result = tryOfferDescDescribeTryOffer.getResult();
        result.completeResumeReceive(element);
        return result.getOfferResult();
    }

    protected final Closed<?> getClosedForSend() {
        LockFreeLinkedListNode prevNode = this.queue.getPrevNode();
        if (!(prevNode instanceof Closed)) {
            prevNode = null;
        }
        Closed<?> closed = (Closed) prevNode;
        if (closed == null) {
            return null;
        }
        helpClose(closed);
        return closed;
    }

    protected final Closed<?> getClosedForReceive() {
        LockFreeLinkedListNode nextNode = this.queue.getNextNode();
        if (!(nextNode instanceof Closed)) {
            nextNode = null;
        }
        Closed<?> closed = (Closed) nextNode;
        if (closed == null) {
            return null;
        }
        helpClose(closed);
        return closed;
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x000f, code lost:
    
        r1 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final kotlinx.coroutines.channels.Send takeFirstSendOrPeekClosed() {
        /*
            r4 = this;
            kotlinx.coroutines.internal.LockFreeLinkedListHead r0 = r4.queue
            kotlinx.coroutines.internal.LockFreeLinkedListNode r0 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r0
        L4:
            java.lang.Object r1 = r0.getNext()
            if (r1 == 0) goto L31
            kotlinx.coroutines.internal.LockFreeLinkedListNode r1 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r1
            r2 = 0
            if (r1 != r0) goto L11
        Lf:
            r1 = r2
            goto L2a
        L11:
            boolean r3 = r1 instanceof kotlinx.coroutines.channels.Send
            if (r3 != 0) goto L16
            goto Lf
        L16:
            r2 = r1
            kotlinx.coroutines.channels.Send r2 = (kotlinx.coroutines.channels.Send) r2
            boolean r2 = r2 instanceof kotlinx.coroutines.channels.Closed
            if (r2 == 0) goto L24
            boolean r2 = r1.isRemoved()
            if (r2 != 0) goto L24
            goto L2a
        L24:
            kotlinx.coroutines.internal.LockFreeLinkedListNode r2 = r1.removeOrNext()
            if (r2 != 0) goto L2d
        L2a:
            kotlinx.coroutines.channels.Send r1 = (kotlinx.coroutines.channels.Send) r1
            return r1
        L2d:
            r2.helpRemovePrev()
            goto L4
        L31:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
        */
        //  java.lang.String r1 = "null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */"
        /*
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.AbstractSendChannel.takeFirstSendOrPeekClosed():kotlinx.coroutines.channels.Send");
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected final ReceiveOrClosed<?> sendBuffered(E element) {
        LockFreeLinkedListNode prevNode;
        LockFreeLinkedListHead lockFreeLinkedListHead = this.queue;
        SendBuffered sendBuffered = new SendBuffered(element);
        do {
            prevNode = lockFreeLinkedListHead.getPrevNode();
            if (prevNode instanceof ReceiveOrClosed) {
                return (ReceiveOrClosed) prevNode;
            }
        } while (!prevNode.addNext(sendBuffered, lockFreeLinkedListHead));
        return null;
    }

    protected final LockFreeLinkedListNode.AddLastDesc<?> describeSendBuffered(E element) {
        return new SendBufferedDesc(this.queue, element);
    }

    /* JADX INFO: compiled from: AbstractChannel.kt */
    @Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0012\u0018\u0000*\u0004\b\u0001\u0010\u00012\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0003`\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00028\u0001¢\u0006\u0002\u0010\bJ\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0014¨\u0006\r"}, m2290d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$SendBufferedDesc;", "E", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "Lkotlinx/coroutines/channels/AbstractSendChannel$SendBuffered;", "Lkotlinx/coroutines/internal/AddLastDesc;", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "element", "(Lkotlinx/coroutines/internal/LockFreeLinkedListHead;Ljava/lang/Object;)V", "failure", "", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "kotlinx-coroutines-core"}, m2291k = 1, m2292mv = {1, 4, 0})
    private static class SendBufferedDesc<E> extends LockFreeLinkedListNode.AddLastDesc<SendBuffered<? extends E>> {
        public SendBufferedDesc(LockFreeLinkedListHead lockFreeLinkedListHead, E e) {
            super(lockFreeLinkedListHead, new SendBuffered(e));
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        protected Object failure(LockFreeLinkedListNode affected) {
            if (affected instanceof Closed) {
                return affected;
            }
            if (affected instanceof ReceiveOrClosed) {
                return AbstractChannelKt.OFFER_FAILED;
            }
            return null;
        }
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final boolean isClosedForSend() {
        return getClosedForSend() != null;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean isFull() {
        return isFullImpl();
    }

    protected final boolean isFullImpl() {
        return !(this.queue.getNextNode() instanceof ReceiveOrClosed) && isBufferFull();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final Object send(E e, Continuation<? super Unit> continuation) {
        Object objSendSuspend;
        return (offerInternal(e) != AbstractChannelKt.OFFER_SUCCESS && (objSendSuspend = sendSuspend(e, continuation)) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? objSendSuspend : Unit.INSTANCE;
    }

    public final Object sendFair$kotlinx_coroutines_core(E e, Continuation<? super Unit> continuation) {
        if (offerInternal(e) == AbstractChannelKt.OFFER_SUCCESS) {
            Object objYield = YieldKt.yield(continuation);
            return objYield == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objYield : Unit.INSTANCE;
        }
        Object objSendSuspend = sendSuspend(e, continuation);
        return objSendSuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSendSuspend : Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final boolean offer(E element) throws Throwable {
        Object objOfferInternal = offerInternal(element);
        if (objOfferInternal == AbstractChannelKt.OFFER_SUCCESS) {
            return true;
        }
        if (objOfferInternal == AbstractChannelKt.OFFER_FAILED) {
            Closed<?> closedForSend = getClosedForSend();
            if (closedForSend == null) {
                return false;
            }
            throw StackTraceRecoveryKt.recoverStackTrace(helpCloseAndGetSendException(closedForSend));
        }
        if (objOfferInternal instanceof Closed) {
            throw StackTraceRecoveryKt.recoverStackTrace(helpCloseAndGetSendException((Closed) objOfferInternal));
        }
        throw new IllegalStateException(("offerInternal returned " + objOfferInternal).toString());
    }

    private final Throwable helpCloseAndGetSendException(Closed<?> closed) {
        helpClose(closed);
        return closed.getSendException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void helpCloseAndResumeWithSendException(Continuation<?> continuation, Closed<?> closed) {
        helpClose(closed);
        Throwable sendException = closed.getSendException();
        Result.Companion companion = Result.INSTANCE;
        continuation.resumeWith(Result.m2353constructorimpl(ResultKt.createFailure(sendException)));
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x003f, code lost:
    
        if (r3 != false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0043, code lost:
    
        return kotlinx.coroutines.channels.AbstractChannelKt.ENQUEUE_FAILED;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0044, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected java.lang.Object enqueueSend(kotlinx.coroutines.channels.Send r5) {
        /*
            r4 = this;
            boolean r0 = r4.isBufferAlwaysFull()
            if (r0 == 0) goto L1d
            kotlinx.coroutines.internal.LockFreeLinkedListHead r0 = r4.queue
            kotlinx.coroutines.internal.LockFreeLinkedListNode r0 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r0
        La:
            kotlinx.coroutines.internal.LockFreeLinkedListNode r1 = r0.getPrevNode()
            boolean r2 = r1 instanceof kotlinx.coroutines.channels.ReceiveOrClosed
            if (r2 == 0) goto L13
            return r1
        L13:
            r2 = r5
            kotlinx.coroutines.internal.LockFreeLinkedListNode r2 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r2
            boolean r1 = r1.addNext(r2, r0)
            if (r1 == 0) goto La
            goto L44
        L1d:
            kotlinx.coroutines.internal.LockFreeLinkedListHead r0 = r4.queue
            kotlinx.coroutines.internal.LockFreeLinkedListNode r0 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r0
            kotlinx.coroutines.channels.AbstractSendChannel$enqueueSend$$inlined$addLastIfPrevAndIf$1 r1 = new kotlinx.coroutines.channels.AbstractSendChannel$enqueueSend$$inlined$addLastIfPrevAndIf$1
            kotlinx.coroutines.internal.LockFreeLinkedListNode r5 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r5
            r1.<init>(r5)
            kotlinx.coroutines.internal.LockFreeLinkedListNode$CondAddOp r1 = (kotlinx.coroutines.internal.LockFreeLinkedListNode.CondAddOp) r1
        L2a:
            kotlinx.coroutines.internal.LockFreeLinkedListNode r2 = r0.getPrevNode()
            boolean r3 = r2 instanceof kotlinx.coroutines.channels.ReceiveOrClosed
            if (r3 == 0) goto L33
            return r2
        L33:
            int r2 = r2.tryCondAddNext(r5, r0, r1)
            r3 = 1
            if (r2 == r3) goto L3f
            r3 = 2
            if (r2 == r3) goto L3e
            goto L2a
        L3e:
            r3 = 0
        L3f:
            if (r3 != 0) goto L44
            java.lang.Object r5 = kotlinx.coroutines.channels.AbstractChannelKt.ENQUEUE_FAILED
            return r5
        L44:
            r5 = 0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.AbstractSendChannel.enqueueSend(kotlinx.coroutines.channels.Send):java.lang.Object");
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    /* JADX INFO: renamed from: close */
    public boolean cancel(Throwable cause) {
        boolean z;
        Closed<?> closed = new Closed<>(cause);
        LockFreeLinkedListHead lockFreeLinkedListHead = this.queue;
        while (true) {
            LockFreeLinkedListNode prevNode = lockFreeLinkedListHead.getPrevNode();
            z = true;
            if (!(!(prevNode instanceof Closed))) {
                z = false;
                break;
            }
            if (prevNode.addNext(closed, lockFreeLinkedListHead)) {
                break;
            }
        }
        if (!z) {
            LockFreeLinkedListNode prevNode2 = this.queue.getPrevNode();
            if (prevNode2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.channels.Closed<*>");
            }
            closed = (Closed) prevNode2;
        }
        helpClose(closed);
        if (z) {
            invokeOnCloseHandler(cause);
        }
        return z;
    }

    private final void invokeOnCloseHandler(Throwable cause) {
        Object obj = this.onCloseHandler;
        if (obj == null || obj == AbstractChannelKt.HANDLER_INVOKED || !onCloseHandler$FU.compareAndSet(this, obj, AbstractChannelKt.HANDLER_INVOKED)) {
            return;
        }
        ((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(obj, 1)).invoke(cause);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public void invokeOnClose(Function1<? super Throwable, Unit> handler) {
        if (!onCloseHandler$FU.compareAndSet(this, null, handler)) {
            Object obj = this.onCloseHandler;
            if (obj == AbstractChannelKt.HANDLER_INVOKED) {
                throw new IllegalStateException("Another handler was already registered and successfully invoked");
            }
            throw new IllegalStateException("Another handler was already registered: " + obj);
        }
        Closed<?> closedForSend = getClosedForSend();
        if (closedForSend == null || !onCloseHandler$FU.compareAndSet(this, handler, AbstractChannelKt.HANDLER_INVOKED)) {
            return;
        }
        handler.invoke(closedForSend.closeCause);
    }

    private final void helpClose(Closed<?> closed) {
        Object objM3700constructorimpl$default = InlineList.m3700constructorimpl$default(null, 1, null);
        while (true) {
            LockFreeLinkedListNode prevNode = closed.getPrevNode();
            if (!(prevNode instanceof Receive)) {
                prevNode = null;
            }
            Receive receive = (Receive) prevNode;
            if (receive == null) {
                break;
            } else if (!receive.remove()) {
                receive.helpRemove();
            } else {
                objM3700constructorimpl$default = InlineList.m3705plusUZ7vuAc(objM3700constructorimpl$default, receive);
            }
        }
        if (objM3700constructorimpl$default != null) {
            if (!(objM3700constructorimpl$default instanceof ArrayList)) {
                ((Receive) objM3700constructorimpl$default).resumeReceiveClosed(closed);
            } else if (objM3700constructorimpl$default != null) {
                ArrayList arrayList = (ArrayList) objM3700constructorimpl$default;
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    ((Receive) arrayList.get(size)).resumeReceiveClosed(closed);
                }
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.ArrayList<E> /* = java.util.ArrayList<E> */");
            }
        }
        onClosedIdempotent(closed);
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x000f, code lost:
    
        r1 = 0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [kotlinx.coroutines.internal.LockFreeLinkedListNode] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected kotlinx.coroutines.channels.ReceiveOrClosed<E> takeFirstReceiveOrPeekClosed() {
        /*
            r4 = this;
            kotlinx.coroutines.internal.LockFreeLinkedListHead r0 = r4.queue
            kotlinx.coroutines.internal.LockFreeLinkedListNode r0 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r0
        L4:
            java.lang.Object r1 = r0.getNext()
            if (r1 == 0) goto L31
            kotlinx.coroutines.internal.LockFreeLinkedListNode r1 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r1
            r2 = 0
            if (r1 != r0) goto L11
        Lf:
            r1 = r2
            goto L2a
        L11:
            boolean r3 = r1 instanceof kotlinx.coroutines.channels.ReceiveOrClosed
            if (r3 != 0) goto L16
            goto Lf
        L16:
            r2 = r1
            kotlinx.coroutines.channels.ReceiveOrClosed r2 = (kotlinx.coroutines.channels.ReceiveOrClosed) r2
            boolean r2 = r2 instanceof kotlinx.coroutines.channels.Closed
            if (r2 == 0) goto L24
            boolean r2 = r1.isRemoved()
            if (r2 != 0) goto L24
            goto L2a
        L24:
            kotlinx.coroutines.internal.LockFreeLinkedListNode r2 = r1.removeOrNext()
            if (r2 != 0) goto L2d
        L2a:
            kotlinx.coroutines.channels.ReceiveOrClosed r1 = (kotlinx.coroutines.channels.ReceiveOrClosed) r1
            return r1
        L2d:
            r2.helpRemovePrev()
            goto L4
        L31:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
        */
        //  java.lang.String r1 = "null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */"
        /*
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.AbstractSendChannel.takeFirstReceiveOrPeekClosed():kotlinx.coroutines.channels.ReceiveOrClosed");
    }

    protected final TryOfferDesc<E> describeTryOffer(E element) {
        return new TryOfferDesc<>(element, this.queue);
    }

    /* JADX INFO: compiled from: AbstractChannel.kt */
    @Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u0000*\u0004\b\u0001\u0010\u00012\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0003`\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00028\u0001\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0012\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u0014J\u0016\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\n\u0010\u000f\u001a\u00060\u0010j\u0002`\u0011H\u0016R\u0012\u0010\u0005\u001a\u00028\u00018\u0006X\u0087\u0004¢\u0006\u0004\n\u0002\u0010\t¨\u0006\u0012"}, m2290d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$TryOfferDesc;", "E", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$RemoveFirstDesc;", "Lkotlinx/coroutines/channels/ReceiveOrClosed;", "Lkotlinx/coroutines/internal/RemoveFirstDesc;", "element", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "(Ljava/lang/Object;Lkotlinx/coroutines/internal/LockFreeLinkedListHead;)V", "Ljava/lang/Object;", "failure", "", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "onPrepare", "prepareOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "Lkotlinx/coroutines/internal/PrepareOp;", "kotlinx-coroutines-core"}, m2291k = 1, m2292mv = {1, 4, 0})
    protected static final class TryOfferDesc<E> extends LockFreeLinkedListNode.RemoveFirstDesc<ReceiveOrClosed<? super E>> {
        public final E element;

        public TryOfferDesc(E e, LockFreeLinkedListHead lockFreeLinkedListHead) {
            super(lockFreeLinkedListHead);
            this.element = e;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.RemoveFirstDesc, kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        protected Object failure(LockFreeLinkedListNode affected) {
            if (affected instanceof Closed) {
                return affected;
            }
            if (affected instanceof ReceiveOrClosed) {
                return null;
            }
            return AbstractChannelKt.OFFER_FAILED;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public Object onPrepare(LockFreeLinkedListNode.PrepareOp prepareOp) {
            Object obj = prepareOp.affected;
            if (obj == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.channels.ReceiveOrClosed<E>");
            }
            Symbol symbolTryResumeReceive = ((ReceiveOrClosed) obj).tryResumeReceive(this.element, prepareOp);
            if (symbolTryResumeReceive == null) {
                return LockFreeLinkedList_commonKt.REMOVE_PREPARED;
            }
            if (symbolTryResumeReceive == AtomicKt.RETRY_ATOMIC) {
                return AtomicKt.RETRY_ATOMIC;
            }
            if (!DebugKt.getASSERTIONS_ENABLED()) {
                return null;
            }
            if (symbolTryResumeReceive == CancellableContinuationImplKt.RESUME_TOKEN) {
                return null;
            }
            throw new AssertionError();
        }
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final SelectClause2<E, SendChannel<E>> getOnSend() {
        return new SelectClause2<E, SendChannel<? super E>>() { // from class: kotlinx.coroutines.channels.AbstractSendChannel$onSend$1
            @Override // kotlinx.coroutines.selects.SelectClause2
            public <R> void registerSelectClause2(SelectInstance<? super R> select, E param, Function2<? super SendChannel<? super E>, ? super Continuation<? super R>, ? extends Object> block) throws Throwable {
                this.this$0.registerSelectSend(select, param, block);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <R> void registerSelectSend(SelectInstance<? super R> select, E element, Function2<? super SendChannel<? super E>, ? super Continuation<? super R>, ? extends Object> block) throws Throwable {
        while (!select.isSelected()) {
            if (isFullImpl()) {
                SendSelect sendSelect = new SendSelect(element, this, select, block);
                Object objEnqueueSend = enqueueSend(sendSelect);
                if (objEnqueueSend == null) {
                    select.disposeOnSelect(sendSelect);
                    return;
                }
                if (objEnqueueSend instanceof Closed) {
                    throw StackTraceRecoveryKt.recoverStackTrace(helpCloseAndGetSendException((Closed) objEnqueueSend));
                }
                if (objEnqueueSend != AbstractChannelKt.ENQUEUE_FAILED && !(objEnqueueSend instanceof Receive)) {
                    throw new IllegalStateException(("enqueueSend returned " + objEnqueueSend + ' ').toString());
                }
            }
            Object objOfferSelectInternal = offerSelectInternal(element, select);
            if (objOfferSelectInternal == SelectKt.getALREADY_SELECTED()) {
                return;
            }
            if (objOfferSelectInternal != AbstractChannelKt.OFFER_FAILED && objOfferSelectInternal != AtomicKt.RETRY_ATOMIC) {
                if (objOfferSelectInternal == AbstractChannelKt.OFFER_SUCCESS) {
                    UndispatchedKt.startCoroutineUnintercepted(block, this, select.getCompletion());
                    return;
                } else {
                    if (objOfferSelectInternal instanceof Closed) {
                        throw StackTraceRecoveryKt.recoverStackTrace(helpCloseAndGetSendException((Closed) objOfferSelectInternal));
                    }
                    throw new IllegalStateException(("offerSelectInternal returned " + objOfferSelectInternal).toString());
                }
            }
        }
    }

    public String toString() {
        return DebugStringsKt.getClassSimpleName(this) + '@' + DebugStringsKt.getHexAddress(this) + '{' + getQueueDebugStateString() + '}' + getBufferDebugString();
    }

    private final String getQueueDebugStateString() {
        String string;
        LockFreeLinkedListNode nextNode = this.queue.getNextNode();
        if (nextNode == this.queue) {
            return "EmptyQueue";
        }
        if (nextNode instanceof Closed) {
            string = nextNode.toString();
        } else if (nextNode instanceof Receive) {
            string = "ReceiveQueued";
        } else if (nextNode instanceof Send) {
            string = "SendQueued";
        } else {
            string = "UNEXPECTED:" + nextNode;
        }
        LockFreeLinkedListNode prevNode = this.queue.getPrevNode();
        if (prevNode == nextNode) {
            return string;
        }
        String str = string + ",queueSize=" + countQueueSize();
        if (!(prevNode instanceof Closed)) {
            return str;
        }
        return str + ",closedForSend=" + prevNode;
    }

    private final int countQueueSize() {
        Object next = this.queue.getNext();
        if (next == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
        }
        int i = 0;
        for (LockFreeLinkedListNode nextNode = (LockFreeLinkedListNode) next; !Intrinsics.areEqual(nextNode, r0); nextNode = nextNode.getNextNode()) {
            if (nextNode instanceof LockFreeLinkedListNode) {
                i++;
            }
        }
        return i;
    }

    /* JADX INFO: compiled from: AbstractChannel.kt */
    @Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u0001*\u0004\b\u0002\u0010\u00022\u00020\u00032\u00020\u0004BX\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00020\n\u0012(\u0010\u000b\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\r\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00060\fø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0014H\u0016J\u0014\u0010\u0016\u001a\u00020\u00142\n\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\u0014\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016R7\u0010\u000b\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\r\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00060\f8\u0006X\u0087\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0010R\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00020\n8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001f"}, m2290d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$SendSelect;", "E", "R", "Lkotlinx/coroutines/channels/Send;", "Lkotlinx/coroutines/DisposableHandle;", "pollResult", "", "channel", "Lkotlinx/coroutines/channels/AbstractSendChannel;", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/channels/SendChannel;", "Lkotlin/coroutines/Continuation;", "(Ljava/lang/Object;Lkotlinx/coroutines/channels/AbstractSendChannel;Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "getPollResult", "()Ljava/lang/Object;", "completeResumeSend", "", "dispose", "resumeSendClosed", "closed", "Lkotlinx/coroutines/channels/Closed;", "toString", "", "tryResumeSend", "Lkotlinx/coroutines/internal/Symbol;", "otherOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "kotlinx-coroutines-core"}, m2291k = 1, m2292mv = {1, 4, 0})
    private static final class SendSelect<E, R> extends Send implements DisposableHandle {
        public final Function2<SendChannel<? super E>, Continuation<? super R>, Object> block;
        public final AbstractSendChannel<E> channel;
        private final Object pollResult;
        public final SelectInstance<R> select;

        @Override // kotlinx.coroutines.channels.Send
        public Object getPollResult() {
            return this.pollResult;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public SendSelect(Object obj, AbstractSendChannel<E> abstractSendChannel, SelectInstance<? super R> selectInstance, Function2<? super SendChannel<? super E>, ? super Continuation<? super R>, ? extends Object> function2) {
            this.pollResult = obj;
            this.channel = abstractSendChannel;
            this.select = selectInstance;
            this.block = function2;
        }

        @Override // kotlinx.coroutines.channels.Send
        public Symbol tryResumeSend(LockFreeLinkedListNode.PrepareOp otherOp) {
            return (Symbol) this.select.trySelectOther(otherOp);
        }

        @Override // kotlinx.coroutines.channels.Send
        public void completeResumeSend() {
            ContinuationKt.startCoroutine(this.block, this.channel, this.select.getCompletion());
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public void dispose() {
            remove();
        }

        @Override // kotlinx.coroutines.channels.Send
        public void resumeSendClosed(Closed<?> closed) {
            if (this.select.trySelect()) {
                this.select.resumeSelectWithException(closed.getSendException());
            }
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public String toString() {
            return "SendSelect@" + DebugStringsKt.getHexAddress(this) + '(' + getPollResult() + ")[" + this.channel + ", " + this.select + ']';
        }
    }

    /* JADX INFO: compiled from: AbstractChannel.kt */
    @Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0001¢\u0006\u0002\u0010\u0004J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0014\u0010\f\u001a\u00020\u000b2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0014\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016R\u0012\u0010\u0003\u001a\u00028\u00018\u0006X\u0087\u0004¢\u0006\u0004\n\u0002\u0010\u0005R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0015"}, m2290d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$SendBuffered;", "E", "Lkotlinx/coroutines/channels/Send;", "element", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "pollResult", "", "getPollResult", "()Ljava/lang/Object;", "completeResumeSend", "", "resumeSendClosed", "closed", "Lkotlinx/coroutines/channels/Closed;", "toString", "", "tryResumeSend", "Lkotlinx/coroutines/internal/Symbol;", "otherOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "kotlinx-coroutines-core"}, m2291k = 1, m2292mv = {1, 4, 0})
    public static final class SendBuffered<E> extends Send {
        public final E element;

        @Override // kotlinx.coroutines.channels.Send
        public void completeResumeSend() {
        }

        @Override // kotlinx.coroutines.channels.Send
        public void resumeSendClosed(Closed<?> closed) {
        }

        public SendBuffered(E e) {
            this.element = e;
        }

        @Override // kotlinx.coroutines.channels.Send
        public Object getPollResult() {
            return this.element;
        }

        @Override // kotlinx.coroutines.channels.Send
        public Symbol tryResumeSend(LockFreeLinkedListNode.PrepareOp otherOp) {
            Symbol symbol = CancellableContinuationImplKt.RESUME_TOKEN;
            if (otherOp != null) {
                otherOp.finishPrepare();
            }
            return symbol;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public String toString() {
            return "SendBuffered@" + DebugStringsKt.getHexAddress(this) + '(' + this.element + ')';
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0060 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final /* synthetic */ java.lang.Object sendSuspend(E r5, kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r4 = this;
            kotlin.coroutines.Continuation r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.intercepted(r6)
            kotlinx.coroutines.CancellableContinuationImpl r0 = kotlinx.coroutines.CancellableContinuationKt.getOrCreateCancellableContinuation(r0)
            r1 = r0
            kotlinx.coroutines.CancellableContinuation r1 = (kotlinx.coroutines.CancellableContinuation) r1
        Lb:
            boolean r2 = r4.isFullImpl()
            if (r2 == 0) goto L58
            kotlinx.coroutines.channels.SendElement r2 = new kotlinx.coroutines.channels.SendElement
            r2.<init>(r5, r1)
            r3 = r2
            kotlinx.coroutines.channels.Send r3 = (kotlinx.coroutines.channels.Send) r3
            java.lang.Object r3 = r4.enqueueSend(r3)
            if (r3 != 0) goto L25
            kotlinx.coroutines.internal.LockFreeLinkedListNode r2 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r2
            kotlinx.coroutines.CancellableContinuationKt.removeOnCancellation(r1, r2)
            goto L7e
        L25:
            boolean r2 = r3 instanceof kotlinx.coroutines.channels.Closed
            if (r2 == 0) goto L31
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            kotlinx.coroutines.channels.Closed r3 = (kotlinx.coroutines.channels.Closed) r3
            access$helpCloseAndResumeWithSendException(r4, r1, r3)
            goto L7e
        L31:
            java.lang.Object r2 = kotlinx.coroutines.channels.AbstractChannelKt.ENQUEUE_FAILED
            if (r3 != r2) goto L36
            goto L58
        L36:
            boolean r2 = r3 instanceof kotlinx.coroutines.channels.Receive
            if (r2 == 0) goto L3b
            goto L58
        L3b:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "enqueueSend returned "
            r5.append(r6)
            r5.append(r3)
            java.lang.String r5 = r5.toString()
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r5 = r5.toString()
            r6.<init>(r5)
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            throw r6
        L58:
            java.lang.Object r2 = r4.offerInternal(r5)
            java.lang.Object r3 = kotlinx.coroutines.channels.AbstractChannelKt.OFFER_SUCCESS
            if (r2 != r3) goto L6e
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            kotlin.Result$Companion r2 = kotlin.Result.INSTANCE
            java.lang.Object r5 = kotlin.Result.m2353constructorimpl(r5)
            r1.resumeWith(r5)
            goto L7e
        L6e:
            java.lang.Object r3 = kotlinx.coroutines.channels.AbstractChannelKt.OFFER_FAILED
            if (r2 != r3) goto L73
            goto Lb
        L73:
            boolean r5 = r2 instanceof kotlinx.coroutines.channels.Closed
            if (r5 == 0) goto L8c
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            kotlinx.coroutines.channels.Closed r2 = (kotlinx.coroutines.channels.Closed) r2
            access$helpCloseAndResumeWithSendException(r4, r1, r2)
        L7e:
            java.lang.Object r5 = r0.getResult()
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r5 != r0) goto L8b
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r6)
        L8b:
            return r5
        L8c:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "offerInternal returned "
            r5.append(r6)
            r5.append(r2)
            java.lang.String r5 = r5.toString()
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r5 = r5.toString()
            r6.<init>(r5)
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.AbstractSendChannel.sendSuspend(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
