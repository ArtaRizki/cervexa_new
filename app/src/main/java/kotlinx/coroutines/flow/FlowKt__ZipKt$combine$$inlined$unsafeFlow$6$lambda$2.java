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
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.InlineMarker;

/* JADX INFO: Add missing generic type declarations: [R, T] */
/* JADX INFO: compiled from: Zip.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u008a@¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, m2290d2 = {"<anonymous>", "", NDEFRecord.TEXT_WELL_KNOWN_TYPE, "R", "Lkotlinx/coroutines/flow/FlowCollector;", "it", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$6$2"}, m2291k = 3, m2292mv = {1, 4, 0})
@DebugMetadata(m2298c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$6$lambda$2", m2299f = "Zip.kt", m2300i = {0, 0, 1, 1}, m2301l = {269, 269}, m2302m = "invokeSuspend", m2303n = {"$this$combineInternal", "it", "$this$combineInternal", "it"}, m2304s = {"L$0", "L$1", "L$0", "L$1"})
public final class FlowKt__ZipKt$combine$$inlined$unsafeFlow$6$lambda$2<R, T> extends SuspendLambda implements Function3<FlowCollector<? super R>, T[], Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX INFO: renamed from: p$ */
    private FlowCollector f3413p$;
    private Object[] p$0;
    final /* synthetic */ FlowKt__ZipKt$combine$$inlined$unsafeFlow$6 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__ZipKt$combine$$inlined$unsafeFlow$6$lambda$2(Continuation continuation, FlowKt__ZipKt$combine$$inlined$unsafeFlow$6 flowKt__ZipKt$combine$$inlined$unsafeFlow$6) {
        super(3, continuation);
        this.this$0 = flowKt__ZipKt$combine$$inlined$unsafeFlow$6;
    }

    public final Continuation<Unit> create(FlowCollector<? super R> flowCollector, T[] tArr, Continuation<? super Unit> continuation) {
        FlowKt__ZipKt$combine$$inlined$unsafeFlow$6$lambda$2 flowKt__ZipKt$combine$$inlined$unsafeFlow$6$lambda$2 = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$6$lambda$2(continuation, this.this$0);
        flowKt__ZipKt$combine$$inlined$unsafeFlow$6$lambda$2.f3413p$ = flowCollector;
        flowKt__ZipKt$combine$$inlined$unsafeFlow$6$lambda$2.p$0 = tArr;
        return flowKt__ZipKt$combine$$inlined$unsafeFlow$6$lambda$2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Continuation<? super Unit> continuation) {
        return ((FlowKt__ZipKt$combine$$inlined$unsafeFlow$6$lambda$2) create((FlowCollector) obj, (Object[]) obj2, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        FlowCollector flowCollector;
        Object[] objArr;
        FlowCollector flowCollector2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector3 = this.f3413p$;
            Object[] objArr2 = this.p$0;
            Function2 function2 = this.this$0.$transform$inlined;
            this.L$0 = flowCollector3;
            this.L$1 = objArr2;
            this.L$2 = flowCollector3;
            this.label = 1;
            Object objInvoke = function2.invoke(objArr2, this);
            if (objInvoke == coroutine_suspended) {
                return coroutine_suspended;
            }
            flowCollector = flowCollector3;
            objArr = objArr2;
            obj = objInvoke;
            flowCollector2 = flowCollector3;
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            FlowCollector flowCollector4 = (FlowCollector) this.L$2;
            objArr = (Object[]) this.L$1;
            FlowCollector flowCollector5 = (FlowCollector) this.L$0;
            ResultKt.throwOnFailure(obj);
            flowCollector2 = flowCollector4;
            flowCollector = flowCollector5;
        }
        this.L$0 = flowCollector;
        this.L$1 = objArr;
        this.label = 2;
        if (flowCollector2.emit(obj, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Object invokeSuspend$$forInline(Object obj) {
        FlowCollector flowCollector = this.f3413p$;
        Object objInvoke = this.this$0.$transform$inlined.invoke(this.p$0, this);
        InlineMarker.mark(0);
        flowCollector.emit(objInvoke, this);
        InlineMarker.mark(2);
        InlineMarker.mark(1);
        return Unit.INSTANCE;
    }
}
