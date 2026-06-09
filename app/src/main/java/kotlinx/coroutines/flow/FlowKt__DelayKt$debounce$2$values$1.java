package kotlinx.coroutines.flow;

import com.google.zxing.client.result.optional.NDEFRecord;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.FlowKt__DelayKt;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;

/* JADX INFO: compiled from: Delay.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, m2290d2 = {"<anonymous>", "", NDEFRecord.TEXT_WELL_KNOWN_TYPE, "Lkotlinx/coroutines/channels/ProducerScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m2291k = 3, m2292mv = {1, 4, 0})
@DebugMetadata(m2298c = "kotlinx.coroutines.flow.FlowKt__DelayKt$debounce$2$values$1", m2299f = "Delay.kt", m2300i = {0, 0}, m2301l = {185}, m2302m = "invokeSuspend", m2303n = {"$this$produce", "$this$collect$iv"}, m2304s = {"L$0", "L$1"})
final class FlowKt__DelayKt$debounce$2$values$1 extends SuspendLambda implements Function2<ProducerScope<? super Object>, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    int label;

    /* JADX INFO: renamed from: p$ */
    private ProducerScope f3381p$;
    final /* synthetic */ FlowKt__DelayKt.C23702 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FlowKt__DelayKt$debounce$2$values$1(FlowKt__DelayKt.C23702 c23702, Continuation continuation) {
        super(2, continuation);
        this.this$0 = c23702;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        FlowKt__DelayKt$debounce$2$values$1 flowKt__DelayKt$debounce$2$values$1 = new FlowKt__DelayKt$debounce$2$values$1(this.this$0, continuation);
        flowKt__DelayKt$debounce$2$values$1.f3381p$ = (ProducerScope) obj;
        return flowKt__DelayKt$debounce$2$values$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(ProducerScope<? super Object> producerScope, Continuation<? super Unit> continuation) {
        return ((FlowKt__DelayKt$debounce$2$values$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = this.f3381p$;
            Flow flow = this.this$0.$this_debounce;
            FlowCollector flowCollector = new FlowCollector<T>() { // from class: kotlinx.coroutines.flow.FlowKt__DelayKt$debounce$2$values$1$invokeSuspend$$inlined$collect$1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public Object emit(Object obj2, Continuation continuation) {
                    ProducerScope producerScope2 = producerScope;
                    if (obj2 == null) {
                        obj2 = NullSurrogateKt.NULL;
                    }
                    Object objSend = producerScope2.send(obj2, continuation);
                    return objSend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSend : Unit.INSTANCE;
                }
            };
            this.L$0 = producerScope;
            this.L$1 = flow;
            this.label = 1;
            if (flow.collect(flowCollector, this) == coroutine_suspended) {
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
