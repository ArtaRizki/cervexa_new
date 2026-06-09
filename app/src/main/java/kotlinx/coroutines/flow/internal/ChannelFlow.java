package kotlinx.coroutines.flow.internal;

import com.google.zxing.client.result.optional.NDEFRecord;
import com.tencent.connect.common.Constants;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.channels.BroadcastChannel;
import kotlinx.coroutines.channels.BroadcastKt;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: ChannelFlow.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u001e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u001f\u0010\u001b\u001a\u00020\f2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00000\u001dH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u001eJ\u001f\u0010\u001f\u001a\u00020\f2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\nH¤@ø\u0001\u0000¢\u0006\u0002\u0010 J\u001e\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H$J\u001e\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0016\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00000$2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010%\u001a\u00020\u0014H\u0016R\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R9\u0010\b\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\r0\t8@X\u0080\u0004ø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006&"}, m2290d2 = {"Lkotlinx/coroutines/flow/internal/ChannelFlow;", NDEFRecord.TEXT_WELL_KNOWN_TYPE, "Lkotlinx/coroutines/flow/internal/FusibleFlow;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "", "(Lkotlin/coroutines/CoroutineContext;I)V", "collectToFun", "Lkotlin/Function2;", "Lkotlinx/coroutines/channels/ProducerScope;", "Lkotlin/coroutines/Continuation;", "", "", "getCollectToFun$kotlinx_coroutines_core", "()Lkotlin/jvm/functions/Function2;", "produceCapacity", "getProduceCapacity", "()I", "additionalToStringProps", "", "broadcastImpl", "Lkotlinx/coroutines/channels/BroadcastChannel;", Constants.PARAM_SCOPE, "Lkotlinx/coroutines/CoroutineScope;", "start", "Lkotlinx/coroutines/CoroutineStart;", "collect", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collectTo", "(Lkotlinx/coroutines/channels/ProducerScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "create", "fuse", "produceImpl", "Lkotlinx/coroutines/channels/ReceiveChannel;", "toString", "kotlinx-coroutines-core"}, m2291k = 1, m2292mv = {1, 4, 0})
public abstract class ChannelFlow<T> implements FusibleFlow<T> {
    public final int capacity;
    public final CoroutineContext context;

    public String additionalToStringProps() {
        return "";
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) {
        return collect$suspendImpl(this, flowCollector, continuation);
    }

    protected abstract Object collectTo(ProducerScope<? super T> producerScope, Continuation<? super Unit> continuation);

    protected abstract ChannelFlow<T> create(CoroutineContext context, int capacity);

    public ChannelFlow(CoroutineContext coroutineContext, int i) {
        this.context = coroutineContext;
        this.capacity = i;
    }

    public final Function2<ProducerScope<? super T>, Continuation<? super Unit>, Object> getCollectToFun$kotlinx_coroutines_core() {
        return new ChannelFlow$collectToFun$1(this, null);
    }

    private final int getProduceCapacity() {
        int i = this.capacity;
        if (i == -3) {
            return -2;
        }
        return i;
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x000f  */
    @Override // kotlinx.coroutines.flow.internal.FusibleFlow
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public kotlinx.coroutines.flow.internal.FusibleFlow<T> fuse(kotlin.coroutines.CoroutineContext r4, int r5) {
        /*
            r3 = this;
            kotlin.coroutines.CoroutineContext r0 = r3.context
            kotlin.coroutines.CoroutineContext r4 = r4.plus(r0)
            int r0 = r3.capacity
            r1 = -3
            r2 = -1
            if (r0 != r1) goto Ld
            goto L57
        Ld:
            if (r5 != r1) goto L11
        Lf:
            r5 = r0
            goto L57
        L11:
            r1 = -2
            if (r0 != r1) goto L15
            goto L57
        L15:
            if (r5 != r1) goto L18
            goto Lf
        L18:
            if (r0 != r2) goto L1c
        L1a:
            r5 = -1
            goto L57
        L1c:
            if (r5 != r2) goto L1f
            goto L1a
        L1f:
            boolean r0 = kotlinx.coroutines.DebugKt.getASSERTIONS_ENABLED()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L39
            int r0 = r3.capacity
            if (r0 < 0) goto L2d
            r0 = 1
            goto L2e
        L2d:
            r0 = 0
        L2e:
            if (r0 == 0) goto L31
            goto L39
        L31:
            java.lang.AssertionError r4 = new java.lang.AssertionError
            r4.<init>()
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            throw r4
        L39:
            boolean r0 = kotlinx.coroutines.DebugKt.getASSERTIONS_ENABLED()
            if (r0 == 0) goto L4e
            if (r5 < 0) goto L42
            goto L43
        L42:
            r1 = 0
        L43:
            if (r1 == 0) goto L46
            goto L4e
        L46:
            java.lang.AssertionError r4 = new java.lang.AssertionError
            r4.<init>()
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            throw r4
        L4e:
            int r0 = r3.capacity
            int r5 = r5 + r0
            if (r5 < 0) goto L54
            goto L57
        L54:
            r5 = 2147483647(0x7fffffff, float:NaN)
        L57:
            kotlin.coroutines.CoroutineContext r0 = r3.context
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r0)
            if (r0 == 0) goto L67
            int r0 = r3.capacity
            if (r5 != r0) goto L67
            r4 = r3
            kotlinx.coroutines.flow.internal.FusibleFlow r4 = (kotlinx.coroutines.flow.internal.FusibleFlow) r4
            return r4
        L67:
            kotlinx.coroutines.flow.internal.ChannelFlow r4 = r3.create(r4, r5)
            kotlinx.coroutines.flow.internal.FusibleFlow r4 = (kotlinx.coroutines.flow.internal.FusibleFlow) r4
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.internal.ChannelFlow.fuse(kotlin.coroutines.CoroutineContext, int):kotlinx.coroutines.flow.internal.FusibleFlow");
    }

    public BroadcastChannel<T> broadcastImpl(CoroutineScope scope, CoroutineStart start) {
        return BroadcastKt.broadcast$default(scope, this.context, getProduceCapacity(), start, null, getCollectToFun$kotlinx_coroutines_core(), 8, null);
    }

    public ReceiveChannel<T> produceImpl(CoroutineScope scope) {
        return ProduceKt.produce$default(scope, this.context, getProduceCapacity(), CoroutineStart.ATOMIC, null, getCollectToFun$kotlinx_coroutines_core(), 8, null);
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.internal.ChannelFlow$collect$2 */
    /* JADX INFO: compiled from: ChannelFlow.kt */
    @Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, m2290d2 = {"<anonymous>", "", NDEFRecord.TEXT_WELL_KNOWN_TYPE, "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m2291k = 3, m2292mv = {1, 4, 0})
    @DebugMetadata(m2298c = "kotlinx.coroutines.flow.internal.ChannelFlow$collect$2", m2299f = "ChannelFlow.kt", m2300i = {0}, m2301l = {101}, m2302m = "invokeSuspend", m2303n = {"$this$coroutineScope"}, m2304s = {"L$0"})
    static final class C25172 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ FlowCollector $collector;
        Object L$0;
        int label;

        /* JADX INFO: renamed from: p$ */
        private CoroutineScope f3429p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C25172(FlowCollector flowCollector, Continuation continuation) {
            super(2, continuation);
            this.$collector = flowCollector;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C25172 c25172 = new C25172(this.$collector, continuation);
            c25172.f3429p$ = (CoroutineScope) obj;
            return c25172;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C25172) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f3429p$;
                FlowCollector flowCollector = this.$collector;
                ReceiveChannel<T> receiveChannelProduceImpl = ChannelFlow.this.produceImpl(coroutineScope);
                this.L$0 = coroutineScope;
                this.label = 1;
                if (FlowKt.emitAll(flowCollector, receiveChannelProduceImpl, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    static /* synthetic */ Object collect$suspendImpl(ChannelFlow channelFlow, FlowCollector flowCollector, Continuation continuation) {
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new C25172(flowCollector, null), continuation);
        return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }

    public String toString() {
        return DebugStringsKt.getClassSimpleName(this) + '[' + additionalToStringProps() + "context=" + this.context + ", capacity=" + this.capacity + ']';
    }
}
