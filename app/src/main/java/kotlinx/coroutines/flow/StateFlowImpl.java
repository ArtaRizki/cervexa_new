package kotlinx.coroutines.flow;

import com.google.zxing.client.result.optional.NDEFRecord;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.internal.ChannelFlowOperatorImpl;
import kotlinx.coroutines.flow.internal.FusibleFlow;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import kotlinx.coroutines.internal.Symbol;

/* JADX INFO: compiled from: StateFlow.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00060\u0002j\u0002`&2\b\u0012\u0004\u0012\u00028\u00000'2\b\u0012\u0004\u0012\u00028\u00000\u0015B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\bJ!\u0010\f\u001a\u00020\u000b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0096@ø\u0001\u0000¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000f\u0010\u0010J%\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u00152\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0018\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0016\u0010\u001a\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010\u0019R\u0016\u0010\u001b\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001b\u0010\u0019R\u001e\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u001c8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR*\u0010\u001f\u001a\u00028\u00002\u0006\u0010\u001f\u001a\u00028\u00008V@VX\u0096\u000e¢\u0006\u0012\u0012\u0004\b#\u0010$\u001a\u0004\b \u0010!\"\u0004\b\"\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006%"}, m2290d2 = {"Lkotlinx/coroutines/flow/StateFlowImpl;", NDEFRecord.TEXT_WELL_KNOWN_TYPE, "", "initialValue", "<init>", "(Ljava/lang/Object;)V", "Lkotlinx/coroutines/flow/StateFlowSlot;", "allocateSlot", "()Lkotlinx/coroutines/flow/StateFlowSlot;", "Lkotlinx/coroutines/flow/FlowCollector;", "collector", "", "collect", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "slot", "freeSlot", "(Lkotlinx/coroutines/flow/StateFlowSlot;)V", "Lkotlin/coroutines/CoroutineContext;", "context", "", "capacity", "Lkotlinx/coroutines/flow/internal/FusibleFlow;", "fuse", "(Lkotlin/coroutines/CoroutineContext;I)Lkotlinx/coroutines/flow/internal/FusibleFlow;", "nSlots", "I", "nextIndex", "sequence", "", "slots", "[Lkotlinx/coroutines/flow/StateFlowSlot;", "value", "getValue", "()Ljava/lang/Object;", "setValue", "getValue$annotations", "()V", "kotlinx-coroutines-core", "Lkotlinx/coroutines/internal/SynchronizedObject;", "Lkotlinx/coroutines/flow/MutableStateFlow;"}, m2291k = 1, m2292mv = {1, 4, 0})
final class StateFlowImpl<T> implements MutableStateFlow<T>, FusibleFlow<T> {
    private volatile Object _state;
    private int nSlots;
    private int nextIndex;
    private int sequence;
    private StateFlowSlot[] slots = new StateFlowSlot[2];

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.StateFlowImpl$collect$1 */
    /* JADX INFO: compiled from: StateFlow.kt */
    @Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0096@"}, m2290d2 = {"collect", "", NDEFRecord.TEXT_WELL_KNOWN_TYPE, "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, m2291k = 3, m2292mv = {1, 4, 0})
    @DebugMetadata(m2298c = "kotlinx.coroutines.flow.StateFlowImpl", m2299f = "StateFlow.kt", m2300i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1}, m2301l = {321, 279}, m2302m = "collect", m2303n = {"this", "collector", "slot", "prevState", "newState", "this", "collector", "slot", "prevState", "newState"}, m2304s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4"})
    static final class C25161 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C25161(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return StateFlowImpl.this.collect(null, this);
        }
    }

    public static /* synthetic */ void getValue$annotations() {
    }

    public StateFlowImpl(Object obj) {
        this._state = obj;
    }

    @Override // kotlinx.coroutines.flow.MutableStateFlow, kotlinx.coroutines.flow.StateFlow
    public T getValue() {
        Symbol symbol = NullSurrogateKt.NULL;
        T t = (T) this._state;
        if (t == symbol) {
            return null;
        }
        return t;
    }

    @Override // kotlinx.coroutines.flow.MutableStateFlow
    public void setValue(T t) {
        if (t == null) {
            t = (T) NullSurrogateKt.NULL;
        }
        synchronized (this) {
            if (Intrinsics.areEqual(this._state, t)) {
                return;
            }
            this._state = t;
            int i = this.sequence;
            if ((i & 1) == 0) {
                int i2 = i + 1;
                this.sequence = i2;
                StateFlowSlot[] stateFlowSlotArr = this.slots;
                Unit unit = Unit.INSTANCE;
                while (true) {
                    for (StateFlowSlot stateFlowSlot : stateFlowSlotArr) {
                        if (stateFlowSlot != null) {
                            stateFlowSlot.makePending();
                        }
                    }
                    synchronized (this) {
                        if (this.sequence == i2) {
                            this.sequence = i2 + 1;
                            return;
                        } else {
                            i2 = this.sequence;
                            stateFlowSlotArr = this.slots;
                            Unit unit2 = Unit.INSTANCE;
                        }
                    }
                }
            } else {
                this.sequence = i + 2;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0071, code lost:
    
        if ((!kotlin.jvm.internal.Intrinsics.areEqual(r6, r12)) != false) goto L27;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:25:0x006c A[Catch: all -> 0x005c, TryCatch #0 {all -> 0x005c, blocks: (B:13:0x003a, B:23:0x0068, B:25:0x006c, B:36:0x0093, B:38:0x0099, B:27:0x0073, B:31:0x007a, B:18:0x0058), top: B:43:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x008c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0099 A[Catch: all -> 0x005c, TRY_LEAVE, TryCatch #0 {all -> 0x005c, blocks: (B:13:0x003a, B:23:0x0068, B:25:0x006c, B:36:0x0093, B:38:0x0099, B:27:0x0073, B:31:0x007a, B:18:0x0058), top: B:43:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1, types: [kotlinx.coroutines.flow.StateFlowSlot] */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.lang.Object, kotlinx.coroutines.flow.StateFlowSlot] */
    /* JADX WARN: Type inference failed for: r2v5, types: [java.lang.Object] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x0097 -> B:23:0x0068). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x00a9 -> B:23:0x0068). Please report as a decompilation issue!!! */
    @Override // kotlinx.coroutines.flow.Flow
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object collect(kotlinx.coroutines.flow.FlowCollector<? super T> r11, kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            r10 = this;
            boolean r0 = r12 instanceof kotlinx.coroutines.flow.StateFlowImpl.C25161
            if (r0 == 0) goto L14
            r0 = r12
            kotlinx.coroutines.flow.StateFlowImpl$collect$1 r0 = (kotlinx.coroutines.flow.StateFlowImpl.C25161) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L19
        L14:
            kotlinx.coroutines.flow.StateFlowImpl$collect$1 r0 = new kotlinx.coroutines.flow.StateFlowImpl$collect$1
            r0.<init>(r12)
        L19:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L5e
            if (r2 == r5) goto L48
            if (r2 != r4) goto L40
            java.lang.Object r11 = r0.L$4
            java.lang.Object r11 = r0.L$3
            java.lang.Object r2 = r0.L$2
            kotlinx.coroutines.flow.StateFlowSlot r2 = (kotlinx.coroutines.flow.StateFlowSlot) r2
            java.lang.Object r6 = r0.L$1
            kotlinx.coroutines.flow.FlowCollector r6 = (kotlinx.coroutines.flow.FlowCollector) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.flow.StateFlowImpl r7 = (kotlinx.coroutines.flow.StateFlowImpl) r7
            kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.lang.Throwable -> L5c
            r12 = r11
            r11 = r6
            goto L68
        L40:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L48:
            java.lang.Object r11 = r0.L$4
            java.lang.Object r2 = r0.L$3
            java.lang.Object r2 = r0.L$2
            kotlinx.coroutines.flow.StateFlowSlot r2 = (kotlinx.coroutines.flow.StateFlowSlot) r2
            java.lang.Object r6 = r0.L$1
            kotlinx.coroutines.flow.FlowCollector r6 = (kotlinx.coroutines.flow.FlowCollector) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.flow.StateFlowImpl r7 = (kotlinx.coroutines.flow.StateFlowImpl) r7
            kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.lang.Throwable -> L5c
            goto L90
        L5c:
            r11 = move-exception
            goto Lac
        L5e:
            kotlin.ResultKt.throwOnFailure(r12)
            kotlinx.coroutines.flow.StateFlowSlot r12 = r10.allocateSlot()
            r7 = r10
            r2 = r12
            r12 = r3
        L68:
            java.lang.Object r6 = r7._state     // Catch: java.lang.Throwable -> L5c
            if (r12 == 0) goto L73
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r12)     // Catch: java.lang.Throwable -> L5c
            r8 = r8 ^ r5
            if (r8 == 0) goto L93
        L73:
            kotlinx.coroutines.internal.Symbol r8 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL     // Catch: java.lang.Throwable -> L5c
            if (r6 != r8) goto L79
            r8 = r3
            goto L7a
        L79:
            r8 = r6
        L7a:
            r0.L$0 = r7     // Catch: java.lang.Throwable -> L5c
            r0.L$1 = r11     // Catch: java.lang.Throwable -> L5c
            r0.L$2 = r2     // Catch: java.lang.Throwable -> L5c
            r0.L$3 = r12     // Catch: java.lang.Throwable -> L5c
            r0.L$4 = r6     // Catch: java.lang.Throwable -> L5c
            r0.label = r5     // Catch: java.lang.Throwable -> L5c
            java.lang.Object r12 = r11.emit(r8, r0)     // Catch: java.lang.Throwable -> L5c
            if (r12 != r1) goto L8d
            return r1
        L8d:
            r9 = r6
            r6 = r11
            r11 = r9
        L90:
            r12 = r11
            r11 = r6
            r6 = r12
        L93:
            boolean r8 = r2.takePending()     // Catch: java.lang.Throwable -> L5c
            if (r8 != 0) goto L68
            r0.L$0 = r7     // Catch: java.lang.Throwable -> L5c
            r0.L$1 = r11     // Catch: java.lang.Throwable -> L5c
            r0.L$2 = r2     // Catch: java.lang.Throwable -> L5c
            r0.L$3 = r12     // Catch: java.lang.Throwable -> L5c
            r0.L$4 = r6     // Catch: java.lang.Throwable -> L5c
            r0.label = r4     // Catch: java.lang.Throwable -> L5c
            java.lang.Object r6 = r2.awaitPending(r0)     // Catch: java.lang.Throwable -> L5c
            if (r6 != r1) goto L68
            return r1
        Lac:
            r7.freeSlot(r2)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.StateFlowImpl.collect(kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.flow.internal.FusibleFlow
    public FusibleFlow<T> fuse(CoroutineContext context, int capacity) {
        if (capacity == -1 || capacity == 0) {
            return this;
        }
        return new ChannelFlowOperatorImpl(this, context, capacity);
    }

    private final StateFlowSlot allocateSlot() {
        StateFlowSlot stateFlowSlot;
        synchronized (this) {
            int length = this.slots.length;
            if (this.nSlots >= length) {
                Object[] objArrCopyOf = Arrays.copyOf(this.slots, length * 2);
                Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "java.util.Arrays.copyOf(this, newSize)");
                this.slots = (StateFlowSlot[]) objArrCopyOf;
            }
            int i = this.nextIndex;
            do {
                stateFlowSlot = this.slots[i];
                if (stateFlowSlot == null) {
                    stateFlowSlot = new StateFlowSlot();
                    this.slots[i] = stateFlowSlot;
                }
                i++;
                if (i >= this.slots.length) {
                    i = 0;
                }
            } while (!stateFlowSlot.allocate());
            this.nextIndex = i;
            this.nSlots++;
        }
        return stateFlowSlot;
    }

    private final void freeSlot(StateFlowSlot slot) {
        synchronized (this) {
            slot.free();
            this.nSlots--;
            Unit unit = Unit.INSTANCE;
        }
    }
}
