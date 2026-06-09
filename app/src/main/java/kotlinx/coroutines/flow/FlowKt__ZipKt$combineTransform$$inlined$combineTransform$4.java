package kotlinx.coroutines.flow;

import com.google.zxing.client.result.optional.NDEFRecord;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.flow.internal.CombineKt;
import org.apache.commons.net.telnet.TelnetCommand;

/* JADX INFO: Add missing generic type declarations: [R] */
/* JADX INFO: compiled from: Zip.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, m2290d2 = {"<anonymous>", "", NDEFRecord.TEXT_WELL_KNOWN_TYPE, "R", "Lkotlinx/coroutines/flow/FlowCollector;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__ZipKt$combineTransform$6"}, m2291k = 3, m2292mv = {1, 4, 0})
@DebugMetadata(m2298c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransform$$inlined$combineTransform$4", m2299f = "Zip.kt", m2300i = {0}, m2301l = {TelnetCommand.f3466DO}, m2302m = "invokeSuspend", m2303n = {"$this$flow"}, m2304s = {"L$0"})
public final class FlowKt__ZipKt$combineTransform$$inlined$combineTransform$4<R> extends SuspendLambda implements Function2<FlowCollector<? super R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Flow[] $flows;
    final /* synthetic */ Function7 $transform$inlined;
    Object L$0;
    int label;

    /* JADX INFO: renamed from: p$ */
    private FlowCollector f3420p$;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__ZipKt$combineTransform$$inlined$combineTransform$4(Flow[] flowArr, Continuation continuation, Function7 function7) {
        super(2, continuation);
        this.$flows = flowArr;
        this.$transform$inlined = function7;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        FlowKt__ZipKt$combineTransform$$inlined$combineTransform$4 flowKt__ZipKt$combineTransform$$inlined$combineTransform$4 = new FlowKt__ZipKt$combineTransform$$inlined$combineTransform$4(this.$flows, continuation, this.$transform$inlined);
        flowKt__ZipKt$combineTransform$$inlined$combineTransform$4.f3420p$ = (FlowCollector) obj;
        return flowKt__ZipKt$combineTransform$$inlined$combineTransform$4;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return ((FlowKt__ZipKt$combineTransform$$inlined$combineTransform$4) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransform$$inlined$combineTransform$4$2 */
    /* JADX INFO: compiled from: Zip.kt */
    @Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u008a@¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, m2290d2 = {"<anonymous>", "", NDEFRecord.TEXT_WELL_KNOWN_TYPE, "R", "Lkotlinx/coroutines/flow/FlowCollector;", "it", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__ZipKt$combineTransform$6$2"}, m2291k = 3, m2292mv = {1, 4, 0})
    @DebugMetadata(m2298c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransform$$inlined$combineTransform$4$2", m2299f = "Zip.kt", m2300i = {0, 0, 0, 0, 0}, m2301l = {309}, m2302m = "invokeSuspend", m2303n = {"$this$combineInternal", "it", "continuation", "args", "$receiver"}, m2304s = {"L$0", "L$1", "L$2", "L$3", "L$4"})
    public static final class C25122 extends SuspendLambda implements Function3<FlowCollector<? super R>, Object[], Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;

        /* JADX INFO: renamed from: p$ */
        private FlowCollector f3421p$;
        private Object[] p$0;

        public C25122(Continuation continuation) {
            super(3, continuation);
        }

        public final Continuation<Unit> create(FlowCollector<? super R> flowCollector, Object[] objArr, Continuation<? super Unit> continuation) {
            C25122 c25122 = new C25122(continuation);
            c25122.f3421p$ = flowCollector;
            c25122.p$0 = objArr;
            return c25122;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object[] objArr, Continuation<? super Unit> continuation) {
            return ((C25122) create((FlowCollector) obj, objArr, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = this.f3421p$;
                Object[] objArr = this.p$0;
                Function7 function7 = FlowKt__ZipKt$combineTransform$$inlined$combineTransform$4.this.$transform$inlined;
                Object obj2 = objArr[0];
                Object obj3 = objArr[1];
                Object obj4 = objArr[2];
                Object obj5 = objArr[3];
                Object obj6 = objArr[4];
                this.L$0 = flowCollector;
                this.L$1 = objArr;
                this.L$2 = this;
                this.L$3 = objArr;
                this.L$4 = flowCollector;
                this.label = 1;
                if (function7.invoke(flowCollector, obj2, obj3, obj4, obj5, obj6, this) == coroutine_suspended) {
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

        public final Object invokeSuspend$$forInline(Object obj) {
            FlowCollector flowCollector = this.f3421p$;
            Object[] objArr = this.p$0;
            FlowKt__ZipKt$combineTransform$$inlined$combineTransform$4.this.$transform$inlined.invoke(flowCollector, objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], this);
            return Unit.INSTANCE;
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = this.f3420p$;
            Flow[] flowArr = this.$flows;
            C25111 c25111 = new C25111();
            C25122 c25122 = new C25122(null);
            this.L$0 = flowCollector;
            this.label = 1;
            if (CombineKt.combineInternal(flowCollector, flowArr, c25111, c25122, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransform$$inlined$combineTransform$4$1 */
    /* JADX INFO: compiled from: Zip.kt */
    @Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0004\b\u0001\u0010\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, m2290d2 = {"<anonymous>", "", NDEFRecord.TEXT_WELL_KNOWN_TYPE, "R", "invoke", "()[Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__ZipKt$combineTransform$6$1"}, m2291k = 3, m2292mv = {1, 4, 0})
    public static final class C25111 extends Lambda implements Function0<Object[]> {
        public C25111() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object[] invoke() {
            return new Object[FlowKt__ZipKt$combineTransform$$inlined$combineTransform$4.this.$flows.length];
        }
    }

    public final Object invokeSuspend$$forInline(Object obj) {
        FlowCollector flowCollector = this.f3420p$;
        Flow[] flowArr = this.$flows;
        C25111 c25111 = new C25111();
        C25122 c25122 = new C25122(null);
        InlineMarker.mark(0);
        CombineKt.combineInternal(flowCollector, flowArr, c25111, c25122, this);
        InlineMarker.mark(2);
        InlineMarker.mark(1);
        return Unit.INSTANCE;
    }
}
