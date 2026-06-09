package kotlinx.coroutines.flow.internal;

import com.google.zxing.client.result.optional.NDEFRecord;
import com.tencent.connect.common.Constants;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: ChannelFlow.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\b \u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\b\u0012\u0004\u0012\u0002H\u00020\u0003B#\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u001f\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00010\u000eH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\u001f\u0010\u0010\u001a\u00020\f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00010\u0012H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010\u0013J'\u0010\u0014\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00010\u000e2\u0006\u0010\u0015\u001a\u00020\u0007H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0016J\u001f\u0010\u0017\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00010\u000eH¤@ø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\b\u0010\u0018\u001a\u00020\u0019H\u0016R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, m2290d2 = {"Lkotlinx/coroutines/flow/internal/ChannelFlowOperator;", "S", NDEFRecord.TEXT_WELL_KNOWN_TYPE, "Lkotlinx/coroutines/flow/internal/ChannelFlow;", "flow", "Lkotlinx/coroutines/flow/Flow;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/coroutines/CoroutineContext;I)V", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collectTo", Constants.PARAM_SCOPE, "Lkotlinx/coroutines/channels/ProducerScope;", "(Lkotlinx/coroutines/channels/ProducerScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collectWithContextUndispatched", "newContext", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "flowCollect", "toString", "", "kotlinx-coroutines-core"}, m2291k = 1, m2292mv = {1, 4, 0})
public abstract class ChannelFlowOperator<S, T> extends ChannelFlow<T> {
    public final Flow<S> flow;

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow, kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) {
        return collect$suspendImpl((ChannelFlowOperator) this, (FlowCollector) flowCollector, (Continuation) continuation);
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    protected Object collectTo(ProducerScope<? super T> producerScope, Continuation<? super Unit> continuation) {
        return collectTo$suspendImpl(this, producerScope, continuation);
    }

    protected abstract Object flowCollect(FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation);

    /* JADX WARN: Multi-variable type inference failed */
    public ChannelFlowOperator(Flow<? extends S> flow, CoroutineContext coroutineContext, int i) {
        super(coroutineContext, i);
        this.flow = flow;
    }

    final /* synthetic */ Object collectWithContextUndispatched(FlowCollector<? super T> flowCollector, CoroutineContext coroutineContext, Continuation<? super Unit> continuation) {
        Object objWithContextUndispatched$default = ChannelFlowKt.withContextUndispatched$default(coroutineContext, null, new C25202(null), ChannelFlowKt.withUndispatchedContextCollector(flowCollector, continuation.get$context()), continuation, 2, null);
        return objWithContextUndispatched$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContextUndispatched$default : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.internal.ChannelFlowOperator$collectWithContextUndispatched$2 */
    /* JADX INFO: compiled from: ChannelFlow.kt */
    @Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0005H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007"}, m2290d2 = {"<anonymous>", "", "S", NDEFRecord.TEXT_WELL_KNOWN_TYPE, "it", "Lkotlinx/coroutines/flow/FlowCollector;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m2291k = 3, m2292mv = {1, 4, 0})
    @DebugMetadata(m2298c = "kotlinx.coroutines.flow.internal.ChannelFlowOperator$collectWithContextUndispatched$2", m2299f = "ChannelFlow.kt", m2300i = {0}, m2301l = {123}, m2302m = "invokeSuspend", m2303n = {"it"}, m2304s = {"L$0"})
    static final class C25202 extends SuspendLambda implements Function2<FlowCollector<? super T>, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;
        private FlowCollector p$0;

        C25202(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C25202 c25202 = new C25202(continuation);
            c25202.p$0 = (FlowCollector) obj;
            return c25202;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
            return ((C25202) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector<? super T> flowCollector = this.p$0;
                ChannelFlowOperator channelFlowOperator = ChannelFlowOperator.this;
                this.L$0 = flowCollector;
                this.label = 1;
                if (channelFlowOperator.flowCollect(flowCollector, this) == coroutine_suspended) {
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

    static /* synthetic */ Object collectTo$suspendImpl(ChannelFlowOperator channelFlowOperator, ProducerScope producerScope, Continuation continuation) {
        Object objFlowCollect = channelFlowOperator.flowCollect(new SendingCollector(producerScope), continuation);
        return objFlowCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objFlowCollect : Unit.INSTANCE;
    }

    static /* synthetic */ Object collect$suspendImpl(ChannelFlowOperator channelFlowOperator, FlowCollector flowCollector, Continuation continuation) {
        if (channelFlowOperator.capacity == -3) {
            CoroutineContext context = continuation.get$context();
            CoroutineContext coroutineContextPlus = context.plus(channelFlowOperator.context);
            if (Intrinsics.areEqual(coroutineContextPlus, context)) {
                Object objFlowCollect = channelFlowOperator.flowCollect(flowCollector, continuation);
                return objFlowCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objFlowCollect : Unit.INSTANCE;
            }
            if (Intrinsics.areEqual((ContinuationInterceptor) coroutineContextPlus.get(ContinuationInterceptor.INSTANCE), (ContinuationInterceptor) context.get(ContinuationInterceptor.INSTANCE))) {
                Object objCollectWithContextUndispatched = channelFlowOperator.collectWithContextUndispatched(flowCollector, coroutineContextPlus, continuation);
                return objCollectWithContextUndispatched == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollectWithContextUndispatched : Unit.INSTANCE;
            }
        }
        Object objCollect = super.collect(flowCollector, continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    public String toString() {
        return this.flow + " -> " + super.toString();
    }
}
