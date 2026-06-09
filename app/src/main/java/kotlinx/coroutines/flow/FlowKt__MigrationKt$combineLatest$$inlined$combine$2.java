package kotlinx.coroutines.flow;

import com.google.zxing.client.result.optional.NDEFRecord;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.flow.internal.CombineKt;
import okhttp3.internal.http.StatusLine;

/* JADX INFO: Add missing generic type declarations: [R] */
/* JADX INFO: compiled from: SafeCollector.common.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\t"}, m2290d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__MigrationKt$combine$$inlined$unsafeFlow$2", "kotlinx/coroutines/flow/FlowKt__MigrationKt$combine$$inlined$combine$2"}, m2291k = 1, m2292mv = {1, 4, 0})
public final class FlowKt__MigrationKt$combineLatest$$inlined$combine$2<R> implements Flow<R> {
    final /* synthetic */ Flow[] $flows$inlined;
    final /* synthetic */ Function5 $transform$inlined$1;

    public FlowKt__MigrationKt$combineLatest$$inlined$combine$2(Flow[] flowArr, Function5 function5) {
        this.$flows$inlined = flowArr;
        this.$transform$inlined$1 = function5;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector flowCollector, Continuation continuation) {
        Object objCombineInternal = CombineKt.combineInternal(flowCollector, this.$flows$inlined, new C24392(), new C24403(null, this), continuation);
        return objCombineInternal == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCombineInternal : Unit.INSTANCE;
    }

    public Object collect$$forInline(FlowCollector flowCollector, Continuation continuation) {
        InlineMarker.mark(4);
        new ContinuationImpl(continuation) { // from class: kotlinx.coroutines.flow.FlowKt__MigrationKt$combineLatest$$inlined$combine$2.1
            int label;
            /* synthetic */ Object result;

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return FlowKt__MigrationKt$combineLatest$$inlined$combine$2.this.collect(null, this);
            }
        };
        InlineMarker.mark(5);
        Flow[] flowArr = this.$flows$inlined;
        C24392 c24392 = new C24392();
        C24403 c24403 = new C24403(null, this);
        InlineMarker.mark(0);
        CombineKt.combineInternal(flowCollector, flowArr, c24392, c24403, continuation);
        InlineMarker.mark(2);
        InlineMarker.mark(1);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__MigrationKt$combineLatest$$inlined$combine$2$2 */
    /* JADX INFO: compiled from: Zip.kt */
    @Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0007\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0004\b\u0001\u0010\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\b"}, m2290d2 = {"<anonymous>", "", NDEFRecord.TEXT_WELL_KNOWN_TYPE, "R", "invoke", "()[Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$5$1", "kotlinx/coroutines/flow/FlowKt__MigrationKt$combine$$inlined$unsafeFlow$2$lambda$1", "kotlinx/coroutines/flow/FlowKt__MigrationKt$combine$$inlined$combine$2$2"}, m2291k = 3, m2292mv = {1, 4, 0})
    public static final class C24392 extends Lambda implements Function0<Object[]> {
        public C24392() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object[] invoke() {
            return new Object[FlowKt__MigrationKt$combineLatest$$inlined$combine$2.this.$flows$inlined.length];
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__MigrationKt$combineLatest$$inlined$combine$2$3 */
    /* JADX INFO: compiled from: Zip.kt */
    @Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u008a@¢\u0006\u0004\b\u0007\u0010\b¨\u0006\u000b"}, m2290d2 = {"<anonymous>", "", NDEFRecord.TEXT_WELL_KNOWN_TYPE, "R", "Lkotlinx/coroutines/flow/FlowCollector;", "it", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$5$2", "kotlinx/coroutines/flow/FlowKt__MigrationKt$combine$$inlined$unsafeFlow$2$lambda$2", "kotlinx/coroutines/flow/FlowKt__MigrationKt$combine$$inlined$combine$2$3"}, m2291k = 3, m2292mv = {1, 4, 0})
    @DebugMetadata(m2298c = "kotlinx.coroutines.flow.FlowKt__MigrationKt$combineLatest$$inlined$combine$2$3", m2299f = "Migration.kt", m2300i = {0, 0, 0, 0, 1, 1}, m2301l = {StatusLine.HTTP_PERM_REDIRECT, StatusLine.HTTP_PERM_REDIRECT}, m2302m = "invokeSuspend", m2303n = {"$this$combineInternal", "it", "continuation", "args", "$this$combineInternal", "it"}, m2304s = {"L$0", "L$1", "L$3", "L$4", "L$0", "L$1"})
    public static final class C24403 extends SuspendLambda implements Function3<FlowCollector<? super R>, Object[], Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;

        /* JADX INFO: renamed from: p$ */
        private FlowCollector f3400p$;
        private Object[] p$0;
        final /* synthetic */ FlowKt__MigrationKt$combineLatest$$inlined$combine$2 this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C24403(Continuation continuation, FlowKt__MigrationKt$combineLatest$$inlined$combine$2 flowKt__MigrationKt$combineLatest$$inlined$combine$2) {
            super(3, continuation);
            this.this$0 = flowKt__MigrationKt$combineLatest$$inlined$combine$2;
        }

        public final Continuation<Unit> create(FlowCollector<? super R> flowCollector, Object[] objArr, Continuation<? super Unit> continuation) {
            C24403 c24403 = new C24403(continuation, this.this$0);
            c24403.f3400p$ = flowCollector;
            c24403.p$0 = objArr;
            return c24403;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object[] objArr, Continuation<? super Unit> continuation) {
            return ((C24403) create((FlowCollector) obj, objArr, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend$$forInline(Object obj) {
            FlowCollector flowCollector = this.f3400p$;
            Object[] objArr = this.p$0;
            Function5 function5 = this.this$0.$transform$inlined$1;
            Object obj2 = objArr[0];
            Object obj3 = objArr[1];
            Object obj4 = objArr[2];
            Object obj5 = objArr[3];
            InlineMarker.mark(0);
            Object objInvoke = function5.invoke(obj2, obj3, obj4, obj5, this);
            InlineMarker.mark(1);
            InlineMarker.mark(0);
            flowCollector.emit(objInvoke, this);
            InlineMarker.mark(2);
            InlineMarker.mark(1);
            return Unit.INSTANCE;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            FlowCollector flowCollector;
            FlowCollector flowCollector2;
            Object[] objArr;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                flowCollector = this.f3400p$;
                Object[] objArr2 = this.p$0;
                Function5 function5 = this.this$0.$transform$inlined$1;
                Object obj2 = objArr2[0];
                Object obj3 = objArr2[1];
                Object obj4 = objArr2[2];
                Object obj5 = objArr2[3];
                this.L$0 = flowCollector;
                this.L$1 = objArr2;
                this.L$2 = flowCollector;
                this.L$3 = this;
                this.L$4 = objArr2;
                this.label = 1;
                Object objInvoke = function5.invoke(obj2, obj3, obj4, obj5, this);
                if (objInvoke == coroutine_suspended) {
                    return coroutine_suspended;
                }
                flowCollector2 = flowCollector;
                objArr = objArr2;
                obj = objInvoke;
            } else {
                if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                flowCollector = (FlowCollector) this.L$2;
                objArr = (Object[]) this.L$1;
                flowCollector2 = (FlowCollector) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            this.L$0 = flowCollector2;
            this.L$1 = objArr;
            this.label = 2;
            if (flowCollector.emit(obj, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }
}
