package kotlinx.coroutines.internal;

import com.jieli.lib.p015dv.control.utils.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Atomic.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u0001H&J\u0016\u0010\r\u001a\u0004\u0018\u00010\u00012\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\u0004H&R\u001e\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u000e"}, m2290d2 = {"Lkotlinx/coroutines/internal/AtomicDesc;", "", "()V", "atomicOp", "Lkotlinx/coroutines/internal/AtomicOp;", "getAtomicOp", "()Lkotlinx/coroutines/internal/AtomicOp;", "setAtomicOp", "(Lkotlinx/coroutines/internal/AtomicOp;)V", "complete", "", Constants.JSON_OP, "failure", "prepare", "kotlinx-coroutines-core"}, m2291k = 1, m2292mv = {1, 4, 0})
public abstract class AtomicDesc {
    public AtomicOp<?> atomicOp;

    public abstract void complete(AtomicOp<?> op, Object failure);

    public abstract Object prepare(AtomicOp<?> op);

    public final AtomicOp<?> getAtomicOp() {
        AtomicOp<?> atomicOp = this.atomicOp;
        if (atomicOp == null) {
            Intrinsics.throwUninitializedPropertyAccessException("atomicOp");
        }
        return atomicOp;
    }

    public final void setAtomicOp(AtomicOp<?> atomicOp) {
        this.atomicOp = atomicOp;
    }
}
