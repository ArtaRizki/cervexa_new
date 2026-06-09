package kotlinx.coroutines.flow;

import com.google.zxing.client.result.optional.NDEFRecord;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.flow.FlowKt__DelayKt;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import kotlinx.coroutines.internal.Symbol;

/* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__DelayKt$debounce$2$invokeSuspend$$inlined$select$lambda$1 */
/* JADX INFO: compiled from: Delay.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, m2290d2 = {"<anonymous>", "", NDEFRecord.TEXT_WELL_KNOWN_TYPE, "it", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__DelayKt$debounce$2$1$1"}, m2291k = 3, m2292mv = {1, 4, 0})
final class C2371x5d4af17d extends SuspendLambda implements Function2<Object, Continuation<? super Unit>, Object> {
    final /* synthetic */ FlowCollector $downstream$inlined;
    final /* synthetic */ Ref.ObjectRef $lastValue$inlined;
    final /* synthetic */ Ref.ObjectRef $values$inlined;
    Object L$0;
    int label;
    private Object p$0;
    final /* synthetic */ FlowKt__DelayKt.C23702 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C2371x5d4af17d(Continuation continuation, FlowKt__DelayKt.C23702 c23702, Ref.ObjectRef objectRef, Ref.ObjectRef objectRef2, FlowCollector flowCollector) {
        super(2, continuation);
        this.this$0 = c23702;
        this.$values$inlined = objectRef;
        this.$lastValue$inlined = objectRef2;
        this.$downstream$inlined = flowCollector;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        C2371x5d4af17d c2371x5d4af17d = new C2371x5d4af17d(continuation, this.this$0, this.$values$inlined, this.$lastValue$inlined, this.$downstream$inlined);
        c2371x5d4af17d.p$0 = obj;
        return c2371x5d4af17d;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return ((C2371x5d4af17d) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [T, kotlinx.coroutines.internal.Symbol] */
    /* JADX WARN: Type inference failed for: r6v1, types: [T, java.lang.Object] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ?? r6 = this.p$0;
            if (r6 == 0) {
                if (this.$lastValue$inlined.element != 0) {
                    FlowCollector flowCollector = this.$downstream$inlined;
                    Symbol symbol = NullSurrogateKt.NULL;
                    Object obj2 = this.$lastValue$inlined.element;
                    if (obj2 == symbol) {
                        obj2 = null;
                    }
                    this.L$0 = r6;
                    this.label = 1;
                    if (flowCollector.emit(obj2, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else {
                this.$lastValue$inlined.element = r6;
                return Unit.INSTANCE;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        this.$lastValue$inlined.element = NullSurrogateKt.DONE;
        return Unit.INSTANCE;
    }
}
