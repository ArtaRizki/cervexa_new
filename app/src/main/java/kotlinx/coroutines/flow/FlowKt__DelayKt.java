package kotlinx.coroutines.flow;

import com.google.zxing.client.result.optional.NDEFRecord;
import com.jieli.lib.p015dv.control.utils.TopicKey;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.internal.FlowCoroutineKt;

/* JADX INFO: compiled from: Delay.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a&\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u001a0\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\b\u001a$\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n*\u00020\f2\u0006\u0010\r\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u0004H\u0000\u001a&\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0004H\u0007\u001a0\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u0006H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, m2290d2 = {"debounce", "Lkotlinx/coroutines/flow/Flow;", NDEFRecord.TEXT_WELL_KNOWN_TYPE, "timeoutMillis", "", TopicKey.TIMEOUT, "Lkotlin/time/Duration;", "debounce-8GFy2Ro", "(Lkotlinx/coroutines/flow/Flow;D)Lkotlinx/coroutines/flow/Flow;", "fixedPeriodTicker", "Lkotlinx/coroutines/channels/ReceiveChannel;", "", "Lkotlinx/coroutines/CoroutineScope;", "delayMillis", "initialDelayMillis", "sample", "periodMillis", "period", "sample-8GFy2Ro", "kotlinx-coroutines-core"}, m2291k = 5, m2292mv = {1, 4, 0}, m2295xs = "kotlinx/coroutines/flow/FlowKt")
final /* synthetic */ class FlowKt__DelayKt {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__DelayKt$debounce$2 */
    /* JADX INFO: compiled from: Delay.kt */
    @Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007"}, m2290d2 = {"<anonymous>", "", NDEFRecord.TEXT_WELL_KNOWN_TYPE, "Lkotlinx/coroutines/CoroutineScope;", "downstream", "Lkotlinx/coroutines/flow/FlowCollector;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m2291k = 3, m2292mv = {1, 4, 0})
    @DebugMetadata(m2298c = "kotlinx.coroutines.flow.FlowKt__DelayKt$debounce$2", m2299f = "Delay.kt", m2300i = {0, 0, 0, 0}, m2301l = {188}, m2302m = "invokeSuspend", m2303n = {"$this$scopedFlow", "downstream", "values", "lastValue"}, m2304s = {"L$0", "L$1", "L$2", "L$3"})
    static final class C23702<T> extends SuspendLambda implements Function3<CoroutineScope, FlowCollector<? super T>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Flow $this_debounce;
        final /* synthetic */ long $timeoutMillis;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;

        /* JADX INFO: renamed from: p$ */
        private CoroutineScope f3380p$;
        private FlowCollector p$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C23702(Flow flow, long j, Continuation continuation) {
            super(3, continuation);
            this.$this_debounce = flow;
            this.$timeoutMillis = j;
        }

        public final Continuation<Unit> create(CoroutineScope coroutineScope, FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) {
            C23702 c23702 = new C23702(this.$this_debounce, this.$timeoutMillis, continuation);
            c23702.f3380p$ = coroutineScope;
            c23702.p$0 = flowCollector;
            return c23702;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, Object obj, Continuation<? super Unit> continuation) {
            return ((C23702) create(coroutineScope, (FlowCollector) obj, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Can't wrap try/catch for region: R(10:11|40|12|13|38|14|(4:16|17|42|18)(1:22)|29|(1:31)|(1:33)(4:34|35|9|(2:36|37)(0))) */
        /* JADX WARN: Can't wrap try/catch for region: R(4:16|17|42|18) */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x00bb, code lost:
        
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x00c2, code lost:
        
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:27:0x00c6, code lost:
        
            r16 = r10;
            r19 = r11;
         */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x00ca, code lost:
        
            r1.handleBuilderException(r0);
         */
        /* JADX WARN: Removed duplicated region for block: B:11:0x0067  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x00d7  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x00dc A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:34:0x00dd  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x00e4  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x00dd -> B:35:0x00df). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r21) {
            /*
                Method dump skipped, instruction units count: 231
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__DelayKt.C23702.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final <T> Flow<T> debounce(Flow<? extends T> flow, long j) {
        if (!(j > 0)) {
            throw new IllegalArgumentException("Debounce timeout should be positive".toString());
        }
        return FlowCoroutineKt.scopedFlow(new C23702(flow, j, null));
    }

    /* JADX INFO: renamed from: debounce-8GFy2Ro, reason: not valid java name */
    public static final <T> Flow<T> m3695debounce8GFy2Ro(Flow<? extends T> flow, double d) {
        return FlowKt.debounce(flow, DelayKt.m3668toDelayMillisLRDsOJo(d));
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2 */
    /* JADX INFO: compiled from: Delay.kt */
    @Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007"}, m2290d2 = {"<anonymous>", "", NDEFRecord.TEXT_WELL_KNOWN_TYPE, "Lkotlinx/coroutines/CoroutineScope;", "downstream", "Lkotlinx/coroutines/flow/FlowCollector;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m2291k = 3, m2292mv = {1, 4, 0})
    @DebugMetadata(m2298c = "kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2", m2299f = "Delay.kt", m2300i = {0, 0, 0, 0, 0}, m2301l = {188}, m2302m = "invokeSuspend", m2303n = {"$this$scopedFlow", "downstream", "values", "lastValue", "ticker"}, m2304s = {"L$0", "L$1", "L$2", "L$3", "L$4"})
    static final class C23752<T> extends SuspendLambda implements Function3<CoroutineScope, FlowCollector<? super T>, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $periodMillis;
        final /* synthetic */ Flow $this_sample;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;

        /* JADX INFO: renamed from: p$ */
        private CoroutineScope f3383p$;
        private FlowCollector p$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C23752(Flow flow, long j, Continuation continuation) {
            super(3, continuation);
            this.$this_sample = flow;
            this.$periodMillis = j;
        }

        public final Continuation<Unit> create(CoroutineScope coroutineScope, FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) {
            C23752 c23752 = new C23752(this.$this_sample, this.$periodMillis, continuation);
            c23752.f3383p$ = coroutineScope;
            c23752.p$0 = flowCollector;
            return c23752;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, Object obj, Continuation<? super Unit> continuation) {
            return ((C23752) create(coroutineScope, (FlowCollector) obj, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Can't wrap try/catch for region: R(12:11|31|12|13|29|14|15|21|(2:23|(1:25))(1:(0))|26|9|(2:27|28)(0)) */
        /* JADX WARN: Code restructure failed: missing block: B:16:0x00c7, code lost:
        
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:17:0x00c8, code lost:
        
            r3 = r8;
         */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x00cf, code lost:
        
            r3.handleBuilderException(r0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x00e2, code lost:
        
            if (r0 != r2) goto L26;
         */
        /* JADX WARN: Removed duplicated region for block: B:11:0x0074  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x00dc  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x00df  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x00e1 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:27:0x00e4  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r20) {
            /*
                Method dump skipped, instruction units count: 231
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__DelayKt.C23752.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final <T> Flow<T> sample(Flow<? extends T> flow, long j) {
        if (!(j > 0)) {
            throw new IllegalArgumentException("Sample period should be positive".toString());
        }
        return FlowCoroutineKt.scopedFlow(new C23752(flow, j, null));
    }

    public static /* synthetic */ ReceiveChannel fixedPeriodTicker$default(CoroutineScope coroutineScope, long j, long j2, int i, Object obj) {
        if ((i & 2) != 0) {
            j2 = j;
        }
        return FlowKt.fixedPeriodTicker(coroutineScope, j, j2);
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__DelayKt$fixedPeriodTicker$3 */
    /* JADX INFO: compiled from: Delay.kt */
    @Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m2290d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m2291k = 3, m2292mv = {1, 4, 0})
    @DebugMetadata(m2298c = "kotlinx.coroutines.flow.FlowKt__DelayKt$fixedPeriodTicker$3", m2299f = "Delay.kt", m2300i = {0, 1, 2}, m2301l = {157, 159, 160}, m2302m = "invokeSuspend", m2303n = {"$this$produce", "$this$produce", "$this$produce"}, m2304s = {"L$0", "L$0", "L$0"})
    static final class C23743 extends SuspendLambda implements Function2<ProducerScope<? super Unit>, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $delayMillis;
        final /* synthetic */ long $initialDelayMillis;
        Object L$0;
        int label;

        /* JADX INFO: renamed from: p$ */
        private ProducerScope f3382p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C23743(long j, long j2, Continuation continuation) {
            super(2, continuation);
            this.$initialDelayMillis = j;
            this.$delayMillis = j2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C23743 c23743 = new C23743(this.$initialDelayMillis, this.$delayMillis, continuation);
            c23743.f3382p$ = (ProducerScope) obj;
            return c23743;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ProducerScope<? super Unit> producerScope, Continuation<? super Unit> continuation) {
            return ((C23743) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x004d A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:20:0x005a A[RETURN] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0058 -> B:15:0x003d). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) {
            /*
                r7 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r7.label
                r2 = 3
                r3 = 2
                r4 = 1
                if (r1 == 0) goto L2a
                if (r1 == r4) goto L11
                if (r1 == r3) goto L21
                if (r1 != r2) goto L19
            L11:
                java.lang.Object r1 = r7.L$0
                kotlinx.coroutines.channels.ProducerScope r1 = (kotlinx.coroutines.channels.ProducerScope) r1
                kotlin.ResultKt.throwOnFailure(r8)
                goto L3c
            L19:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r0)
                throw r8
            L21:
                java.lang.Object r1 = r7.L$0
                kotlinx.coroutines.channels.ProducerScope r1 = (kotlinx.coroutines.channels.ProducerScope) r1
                kotlin.ResultKt.throwOnFailure(r8)
                r8 = r7
                goto L4e
            L2a:
                kotlin.ResultKt.throwOnFailure(r8)
                kotlinx.coroutines.channels.ProducerScope r1 = r7.f3382p$
                long r5 = r7.$initialDelayMillis
                r7.L$0 = r1
                r7.label = r4
                java.lang.Object r8 = kotlinx.coroutines.DelayKt.delay(r5, r7)
                if (r8 != r0) goto L3c
                return r0
            L3c:
                r8 = r7
            L3d:
                kotlinx.coroutines.channels.SendChannel r4 = r1.getChannel()
                kotlin.Unit r5 = kotlin.Unit.INSTANCE
                r8.L$0 = r1
                r8.label = r3
                java.lang.Object r4 = r4.send(r5, r8)
                if (r4 != r0) goto L4e
                return r0
            L4e:
                long r4 = r8.$delayMillis
                r8.L$0 = r1
                r8.label = r2
                java.lang.Object r4 = kotlinx.coroutines.DelayKt.delay(r4, r8)
                if (r4 != r0) goto L3d
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__DelayKt.C23743.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final ReceiveChannel<Unit> fixedPeriodTicker(CoroutineScope coroutineScope, long j, long j2) {
        if (!(j >= 0)) {
            throw new IllegalArgumentException(("Expected non-negative delay, but has " + j + " ms").toString());
        }
        if (!(j2 >= 0)) {
            throw new IllegalArgumentException(("Expected non-negative initial delay, but has " + j2 + " ms").toString());
        }
        return ProduceKt.produce$default(coroutineScope, null, 0, new C23743(j2, j, null), 1, null);
    }

    /* JADX INFO: renamed from: sample-8GFy2Ro, reason: not valid java name */
    public static final <T> Flow<T> m3696sample8GFy2Ro(Flow<? extends T> flow, double d) {
        return FlowKt.sample(flow, DelayKt.m3668toDelayMillisLRDsOJo(d));
    }
}
