package kotlinx.coroutines.flow.internal;

import com.generalplus.GoPlusDrone.Fragment.BaseFragment;
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
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.channels.ChannelCoroutine;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.selects.SelectBuilder;
import org.apache.commons.net.imap.IMAP;

/* JADX INFO: compiled from: Combine.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000l\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0000\u001an\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0005\"\u0004\b\u0001\u0010\u0006\"\u0004\b\u0002\u0010\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00050\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00060\u00032(\u0010\t\u001a$\b\u0001\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u0002H\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\nH\u0000ø\u0001\u0000¢\u0006\u0002\u0010\r\u001a\u001e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\f0\u000f*\u00020\u00102\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0002\u001a\u001e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u000f*\u00020\u00102\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0002\u001a\u008e\u0001\u0010\u0012\u001a\u00020\u0013\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0014*\b\u0012\u0004\u0012\u0002H\u00040\u00152\u0014\u0010\u0016\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002H\u00140\u00030\u00172\u0014\u0010\u0018\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00140\u00170\u001929\u0010\t\u001a5\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\n¢\u0006\u0002\b\u001aH\u0081@ø\u0001\u0000¢\u0006\u0002\u0010\u001b\u001a¢\u0001\u0010\u001c\u001a\u00020\u0013\"\u0004\b\u0000\u0010\u0005\"\u0004\b\u0001\u0010\u0006\"\u0004\b\u0002\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00152\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u00050\u00032\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00060\u00032W\u0010\t\u001aS\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u0015\u0012\u0013\u0012\u0011H\u0005¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\"\u0012\u0013\u0012\u0011H\u0006¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(#\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\u001f¢\u0006\u0002\b\u001aH\u0080@ø\u0001\u0000¢\u0006\u0002\u0010$\u001av\u0010%\u001a\u00020\u0013*\b\u0012\u0004\u0012\u00020\u00130&2\u0006\u0010'\u001a\u00020(2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\f0\u000f2\u000e\b\u0004\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00130\u001923\b\b\u0010%\u001a-\b\u0001\u0012\u0013\u0012\u00110\f¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(,\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0+H\u0082\bø\u0001\u0000¢\u0006\u0002\u0010-\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006."}, m2290d2 = {"getNull", "Lkotlinx/coroutines/internal/Symbol;", "zipImpl", "Lkotlinx/coroutines/flow/Flow;", "R", "T1", "T2", "flow", "flow2", "transform", "Lkotlin/Function3;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "asChannel", "Lkotlinx/coroutines/channels/ReceiveChannel;", "Lkotlinx/coroutines/CoroutineScope;", "asFairChannel", "combineInternal", "", NDEFRecord.TEXT_WELL_KNOWN_TYPE, "Lkotlinx/coroutines/flow/FlowCollector;", "flows", "", "arrayFactory", "Lkotlin/Function0;", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/flow/FlowCollector;[Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "combineTransformInternal", "first", "second", "Lkotlin/Function4;", "Lkotlin/ParameterName;", BaseFragment.DATA_NAME, "a", "b", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function4;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onReceive", "Lkotlinx/coroutines/selects/SelectBuilder;", "isClosed", "", "channel", "onClosed", "Lkotlin/Function2;", "value", "(Lkotlinx/coroutines/selects/SelectBuilder;ZLkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;)V", "kotlinx-coroutines-core"}, m2291k = 2, m2292mv = {1, 4, 0})
public final class CombineKt {
    public static final Symbol getNull() {
        return NullSurrogateKt.NULL;
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.internal.CombineKt$combineTransformInternal$2 */
    /* JADX INFO: compiled from: Combine.kt */
    @Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0004*\u00020\u0005H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007"}, m2290d2 = {"<anonymous>", "", "T1", "T2", "R", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m2291k = 3, m2292mv = {1, 4, 0})
    @DebugMetadata(m2298c = "kotlinx.coroutines.flow.internal.CombineKt$combineTransformInternal$2", m2299f = "Combine.kt", m2300i = {0, 0, 0, 0, 0, 0, 0}, m2301l = {146}, m2302m = "invokeSuspend", m2303n = {"$this$coroutineScope", "firstChannel", "secondChannel", "firstValue", "secondValue", "firstIsClosed", "secondIsClosed"}, m2304s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6"})
    static final class C25292 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Flow $first;
        final /* synthetic */ Flow $second;
        final /* synthetic */ FlowCollector $this_combineTransformInternal;
        final /* synthetic */ Function4 $transform;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;

        /* JADX INFO: renamed from: p$ */
        private CoroutineScope f3438p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C25292(FlowCollector flowCollector, Flow flow, Flow flow2, Function4 function4, Continuation continuation) {
            super(2, continuation);
            this.$this_combineTransformInternal = flowCollector;
            this.$first = flow;
            this.$second = flow2;
            this.$transform = function4;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C25292 c25292 = new C25292(this.$this_combineTransformInternal, this.$first, this.$second, this.$transform, continuation);
            c25292.f3438p$ = (CoroutineScope) obj;
            return c25292;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C25292) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Can't wrap try/catch for region: R(18:16|(1:59)|17|18|57|19|(1:21)(4:22|23|55|24)|25|26|53|27|28|61|29|(1:31)(1:32)|46|(1:48)|(1:50)(22:51|52|9|(1:11)|16|59|17|18|57|19|(0)(0)|25|26|53|27|28|61|29|(0)(0)|46|(0)|(0)(0))) */
        /* JADX WARN: Can't wrap try/catch for region: R(18:16|59|17|18|57|19|(1:21)(4:22|23|55|24)|25|26|53|27|28|61|29|(1:31)(1:32)|46|(1:48)|(1:50)(22:51|52|9|(1:11)|16|59|17|18|57|19|(0)(0)|25|26|53|27|28|61|29|(0)(0)|46|(0)|(0)(0))) */
        /* JADX WARN: Can't wrap try/catch for region: R(4:22|23|55|24) */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x014e, code lost:
        
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x0150, code lost:
        
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x0151, code lost:
        
            r25 = r27;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x0154, code lost:
        
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x0155, code lost:
        
            r25 = r27;
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x0158, code lost:
        
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x0159, code lost:
        
            r25 = r27;
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x016a, code lost:
        
            r17 = r13;
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x016c, code lost:
        
            r3.handleBuilderException(r0);
         */
        /* JADX WARN: Removed duplicated region for block: B:11:0x008c  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x00e1  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x00e4 A[Catch: all -> 0x0158, TRY_LEAVE, TryCatch #2 {all -> 0x0158, blocks: (B:19:0x00d8, B:22:0x00e4), top: B:57:0x00d8 }] */
        /* JADX WARN: Removed duplicated region for block: B:31:0x012c  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x012d A[Catch: all -> 0x014e, TRY_LEAVE, TryCatch #4 {all -> 0x014e, blocks: (B:29:0x0123, B:32:0x012d), top: B:61:0x0123 }] */
        /* JADX WARN: Removed duplicated region for block: B:48:0x0179  */
        /* JADX WARN: Removed duplicated region for block: B:50:0x017e A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:51:0x017f  */
        /* JADX WARN: Type inference failed for: r5v1, types: [T, kotlinx.coroutines.channels.ReceiveChannel] */
        /* JADX WARN: Type inference failed for: r6v1, types: [T, kotlinx.coroutines.channels.ReceiveChannel] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:51:0x017f -> B:52:0x018b). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r27) {
            /*
                Method dump skipped, instruction units count: 400
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.internal.CombineKt.C25292.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final <T1, T2, R> Object combineTransformInternal(FlowCollector<? super R> flowCollector, Flow<? extends T1> flow, Flow<? extends T2> flow2, Function4<? super FlowCollector<? super R>, ? super T1, ? super T2, ? super Continuation<? super Unit>, ? extends Object> function4, Continuation<? super Unit> continuation) {
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new C25292(flowCollector, flow, flow2, function4, null), continuation);
        return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2 */
    /* JADX INFO: compiled from: Combine.kt */
    @Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u00020\u0004H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, m2290d2 = {"<anonymous>", "", "R", NDEFRecord.TEXT_WELL_KNOWN_TYPE, "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m2291k = 3, m2292mv = {1, 4, 0})
    @DebugMetadata(m2298c = "kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2", m2299f = "Combine.kt", m2300i = {0, 0, 0, 0, 0, 0, 0}, m2301l = {146}, m2302m = "invokeSuspend", m2303n = {"$this$coroutineScope", "size", "channels", "latestValues", "isClosed", "nonClosed", "remainingNulls"}, m2304s = {"L$0", "I$0", "L$1", "L$2", "L$3", "L$4", "L$5"})
    static final class C25262 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function0 $arrayFactory;
        final /* synthetic */ Flow[] $flows;
        final /* synthetic */ FlowCollector $this_combineInternal;
        final /* synthetic */ Function3 $transform;
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;

        /* JADX INFO: renamed from: p$ */
        private CoroutineScope f3436p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C25262(FlowCollector flowCollector, Flow[] flowArr, Function0 function0, Function3 function3, Continuation continuation) {
            super(2, continuation);
            this.$this_combineInternal = flowCollector;
            this.$flows = flowArr;
            this.$arrayFactory = function0;
            this.$transform = function3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C25262 c25262 = new C25262(this.$this_combineInternal, this.$flows, this.$arrayFactory, this.$transform, continuation);
            c25262.f3436p$ = (CoroutineScope) obj;
            return c25262;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C25262) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:17:0x00a4  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x0171  */
        /* JADX WARN: Removed duplicated region for block: B:42:0x0176 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:43:0x0177  */
        /* JADX WARN: Removed duplicated region for block: B:45:0x018b  */
        /* JADX WARN: Type inference failed for: r7v0, types: [T, kotlinx.coroutines.channels.ReceiveChannel[]] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:43:0x0177 -> B:44:0x0187). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r29) {
            /*
                Method dump skipped, instruction units count: 398
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.internal.CombineKt.C25262.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final <R, T> Object combineInternal(FlowCollector<? super R> flowCollector, Flow<? extends T>[] flowArr, Function0<T[]> function0, Function3<? super FlowCollector<? super R>, ? super T[], ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super Unit> continuation) {
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new C25262(flowCollector, flowArr, function0, function3, null), continuation);
        return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.internal.CombineKt$onReceive$1 */
    /* JADX INFO: compiled from: Combine.kt */
    @Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, m2290d2 = {"<anonymous>", "", "it", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m2291k = 3, m2292mv = {1, 4, 0})
    @DebugMetadata(m2298c = "kotlinx.coroutines.flow.internal.CombineKt$onReceive$1", m2299f = "Combine.kt", m2300i = {0}, m2301l = {89}, m2302m = "invokeSuspend", m2303n = {"it"}, m2304s = {"L$0"})
    public static final class C25341 extends SuspendLambda implements Function2<Object, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function0 $onClosed;
        final /* synthetic */ Function2 $onReceive;
        Object L$0;
        int label;
        private Object p$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C25341(Function0 function0, Function2 function2, Continuation continuation) {
            super(2, continuation);
            this.$onClosed = function0;
            this.$onReceive = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C25341 c25341 = new C25341(this.$onClosed, this.$onReceive, continuation);
            c25341.p$0 = obj;
            return c25341;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
            return ((C25341) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Object obj2 = this.p$0;
                if (obj2 == null) {
                    this.$onClosed.invoke();
                } else {
                    Function2 function2 = this.$onReceive;
                    this.L$0 = obj2;
                    this.label = 1;
                    if (function2.invoke(obj2, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
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
            Object obj2 = this.p$0;
            if (obj2 == null) {
                this.$onClosed.invoke();
            } else {
                Function2 function2 = this.$onReceive;
                InlineMarker.mark(0);
                function2.invoke(obj2, this);
                InlineMarker.mark(2);
                InlineMarker.mark(1);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onReceive(SelectBuilder<? super Unit> selectBuilder, boolean z, ReceiveChannel<? extends Object> receiveChannel, Function0<Unit> function0, Function2<Object, ? super Continuation<? super Unit>, ? extends Object> function2) {
        if (z) {
            return;
        }
        selectBuilder.invoke(receiveChannel.getOnReceiveOrNull(), new C25341(function0, function2, null));
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.internal.CombineKt$asFairChannel$1 */
    /* JADX INFO: compiled from: Combine.kt */
    @Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, m2290d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m2291k = 3, m2292mv = {1, 4, 0})
    @DebugMetadata(m2298c = "kotlinx.coroutines.flow.internal.CombineKt$asFairChannel$1", m2299f = "Combine.kt", m2300i = {0, 0, 0}, m2301l = {IMAP.DEFAULT_PORT}, m2302m = "invokeSuspend", m2303n = {"$this$produce", "channel", "$this$collect$iv"}, m2304s = {"L$0", "L$1", "L$2"})
    static final class C25251 extends SuspendLambda implements Function2<ProducerScope<? super Object>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Flow $flow;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX INFO: renamed from: p$ */
        private ProducerScope f3435p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C25251(Flow flow, Continuation continuation) {
            super(2, continuation);
            this.$flow = flow;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C25251 c25251 = new C25251(this.$flow, continuation);
            c25251.f3435p$ = (ProducerScope) obj;
            return c25251;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ProducerScope<? super Object> producerScope, Continuation<? super Unit> continuation) {
            return ((C25251) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ProducerScope producerScope = this.f3435p$;
                SendChannel channel = producerScope.getChannel();
                if (channel == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.channels.ChannelCoroutine<kotlin.Any>");
                }
                final ChannelCoroutine channelCoroutine = (ChannelCoroutine) channel;
                Flow flow = this.$flow;
                FlowCollector<Object> flowCollector = new FlowCollector<Object>() { // from class: kotlinx.coroutines.flow.internal.CombineKt$asFairChannel$1$invokeSuspend$$inlined$collect$1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public Object emit(Object obj2, Continuation continuation) {
                        ChannelCoroutine channelCoroutine2 = channelCoroutine;
                        if (obj2 == null) {
                            obj2 = NullSurrogateKt.NULL;
                        }
                        Object objSendFair = channelCoroutine2.sendFair(obj2, continuation);
                        return objSendFair == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSendFair : Unit.INSTANCE;
                    }
                };
                this.L$0 = producerScope;
                this.L$1 = channelCoroutine;
                this.L$2 = flow;
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

    /* JADX INFO: Access modifiers changed from: private */
    public static final ReceiveChannel<Object> asFairChannel(CoroutineScope coroutineScope, Flow<?> flow) {
        return ProduceKt.produce$default(coroutineScope, null, 0, new C25251(flow, null), 3, null);
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.internal.CombineKt$asChannel$1 */
    /* JADX INFO: compiled from: Combine.kt */
    @Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, m2290d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m2291k = 3, m2292mv = {1, 4, 0})
    @DebugMetadata(m2298c = "kotlinx.coroutines.flow.internal.CombineKt$asChannel$1", m2299f = "Combine.kt", m2300i = {0, 0}, m2301l = {IMAP.DEFAULT_PORT}, m2302m = "invokeSuspend", m2303n = {"$this$produce", "$this$collect$iv"}, m2304s = {"L$0", "L$1"})
    static final class C25241 extends SuspendLambda implements Function2<ProducerScope<? super Object>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Flow $flow;
        Object L$0;
        Object L$1;
        int label;

        /* JADX INFO: renamed from: p$ */
        private ProducerScope f3434p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C25241(Flow flow, Continuation continuation) {
            super(2, continuation);
            this.$flow = flow;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C25241 c25241 = new C25241(this.$flow, continuation);
            c25241.f3434p$ = (ProducerScope) obj;
            return c25241;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ProducerScope<? super Object> producerScope, Continuation<? super Unit> continuation) {
            return ((C25241) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final ProducerScope producerScope = this.f3434p$;
                Flow flow = this.$flow;
                FlowCollector<Object> flowCollector = new FlowCollector<Object>() { // from class: kotlinx.coroutines.flow.internal.CombineKt$asChannel$1$invokeSuspend$$inlined$collect$1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public Object emit(Object obj2, Continuation continuation) {
                        SendChannel channel = producerScope.getChannel();
                        if (obj2 == null) {
                            obj2 = NullSurrogateKt.NULL;
                        }
                        Object objSend = channel.send(obj2, continuation);
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

    /* JADX INFO: Access modifiers changed from: private */
    public static final ReceiveChannel<Object> asChannel(CoroutineScope coroutineScope, Flow<?> flow) {
        return ProduceKt.produce$default(coroutineScope, null, 0, new C25241(flow, null), 3, null);
    }

    public static final <T1, T2, R> Flow<R> zipImpl(Flow<? extends T1> flow, Flow<? extends T2> flow2, Function3<? super T1, ? super T2, ? super Continuation<? super R>, ? extends Object> function3) {
        return new CombineKt$zipImpl$$inlined$unsafeFlow$1(flow, flow2, function3);
    }
}
