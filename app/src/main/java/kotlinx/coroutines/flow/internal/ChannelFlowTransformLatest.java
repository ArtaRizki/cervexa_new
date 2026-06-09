package kotlinx.coroutines.flow.internal;

import com.generalplus.GoPlusDrone.Fragment.BaseFragment;
import com.google.zxing.client.result.optional.NDEFRecord;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: Merge.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003Bn\u0012B\u0010\u0004\u001a>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0006\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0005¢\u0006\u0002\b\r\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013ø\u0001\u0000¢\u0006\u0002\u0010\u0014J\u001e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00010\u00172\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0014J\u001f\u0010\u0018\u001a\u00020\u000b2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00010\u0006H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010\u001aRO\u0010\u0004\u001a>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0006\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0005¢\u0006\u0002\b\rX\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0015\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"}, m2290d2 = {"Lkotlinx/coroutines/flow/internal/ChannelFlowTransformLatest;", NDEFRecord.TEXT_WELL_KNOWN_TYPE, "R", "Lkotlinx/coroutines/flow/internal/ChannelFlowOperator;", "transform", "Lkotlin/Function3;", "Lkotlinx/coroutines/flow/FlowCollector;", "Lkotlin/ParameterName;", BaseFragment.DATA_NAME, "value", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "flow", "Lkotlinx/coroutines/flow/Flow;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "", "(Lkotlin/jvm/functions/Function3;Lkotlinx/coroutines/flow/Flow;Lkotlin/coroutines/CoroutineContext;I)V", "Lkotlin/jvm/functions/Function3;", "create", "Lkotlinx/coroutines/flow/internal/ChannelFlow;", "flowCollect", "collector", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, m2291k = 1, m2292mv = {1, 4, 0})
public final class ChannelFlowTransformLatest<T, R> extends ChannelFlowOperator<T, R> {
    private final Function3<FlowCollector<? super R>, T, Continuation<? super Unit>, Object> transform;

    public /* synthetic */ ChannelFlowTransformLatest(Function3 function3, Flow flow, EmptyCoroutineContext emptyCoroutineContext, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(function3, flow, (i2 & 4) != 0 ? EmptyCoroutineContext.INSTANCE : emptyCoroutineContext, (i2 & 8) != 0 ? -2 : i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ChannelFlowTransformLatest(Function3<? super FlowCollector<? super R>, ? super T, ? super Continuation<? super Unit>, ? extends Object> function3, Flow<? extends T> flow, CoroutineContext coroutineContext, int i) {
        super(flow, coroutineContext, i);
        this.transform = function3;
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    protected ChannelFlow<R> create(CoroutineContext context, int capacity) {
        return new ChannelFlowTransformLatest(this.transform, this.flow, context, capacity);
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3 */
    /* JADX INFO: compiled from: Merge.kt */
    @Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u00020\u0004H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, m2290d2 = {"<anonymous>", "", NDEFRecord.TEXT_WELL_KNOWN_TYPE, "R", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m2291k = 3, m2292mv = {1, 4, 0})
    @DebugMetadata(m2298c = "kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3", m2299f = "Merge.kt", m2300i = {0, 0, 0}, m2301l = {99}, m2302m = "invokeSuspend", m2303n = {"$this$flowScope", "previousFlow", "$this$collect$iv"}, m2304s = {"L$0", "L$1", "L$2"})
    static final class C25213 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ FlowCollector $collector;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX INFO: renamed from: p$ */
        private CoroutineScope f3431p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C25213(FlowCollector flowCollector, Continuation continuation) {
            super(2, continuation);
            this.$collector = flowCollector;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C25213 c25213 = new C25213(this.$collector, continuation);
            c25213.f3431p$ = (CoroutineScope) obj;
            return c25213;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C25213) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't change immutable type kotlin.coroutines.Continuation to kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3 for r5v1 'this'  kotlin.coroutines.Continuation
            	at jadx.core.dex.instructions.args.SSAVar.setType(SSAVar.java:114)
            	at jadx.core.dex.instructions.args.RegisterArg.setType(RegisterArg.java:52)
            	at jadx.core.dex.visitors.ModVisitor.removeCheckCast(ModVisitor.java:417)
            	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:152)
            	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r6) {
            /*
                r5 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r5.label
                r2 = 1
                if (r1 == 0) goto L23
                if (r1 != r2) goto L1b
                java.lang.Object r0 = r5.L$2
                kotlinx.coroutines.flow.Flow r0 = (kotlinx.coroutines.flow.Flow) r0
                java.lang.Object r0 = r5.L$1
                kotlin.jvm.internal.Ref$ObjectRef r0 = (kotlin.jvm.internal.Ref.ObjectRef) r0
                java.lang.Object r0 = r5.L$0
                kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
                kotlin.ResultKt.throwOnFailure(r6)
                goto L4c
            L1b:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r0)
                throw r6
            L23:
                kotlin.ResultKt.throwOnFailure(r6)
                kotlinx.coroutines.CoroutineScope r6 = r5.f3431p$
                kotlin.jvm.internal.Ref$ObjectRef r1 = new kotlin.jvm.internal.Ref$ObjectRef
                r1.<init>()
                r3 = 0
                kotlinx.coroutines.Job r3 = (kotlinx.coroutines.Job) r3
                r1.element = r3
                kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest r3 = kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest.this
                kotlinx.coroutines.flow.Flow<S> r3 = r3.flow
                kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1 r4 = new kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1
                r4.<init>(r5, r6, r1)
                kotlinx.coroutines.flow.FlowCollector r4 = (kotlinx.coroutines.flow.FlowCollector) r4
                r5.L$0 = r6
                r5.L$1 = r1
                r5.L$2 = r3
                r5.label = r2
                java.lang.Object r6 = r3.collect(r4, r5)
                if (r6 != r0) goto L4c
                return r0
            L4c:
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest.C25213.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlowOperator
    protected Object flowCollect(FlowCollector<? super R> flowCollector, Continuation<? super Unit> continuation) {
        if (DebugKt.getASSERTIONS_ENABLED() && !Boxing.boxBoolean(flowCollector instanceof SendingCollector).booleanValue()) {
            throw new AssertionError();
        }
        Object objFlowScope = FlowCoroutineKt.flowScope(new C25213(flowCollector, null), continuation);
        return objFlowScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objFlowScope : Unit.INSTANCE;
    }
}
