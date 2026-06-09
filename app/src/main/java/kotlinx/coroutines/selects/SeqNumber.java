package kotlinx.coroutines.selects;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import kotlin.Metadata;

/* JADX INFO: compiled from: Select.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0000\b\u0000\u0018\u00002\u00020\u0007B\u0007¢\u0006\u0004\b\u0001\u0010\u0002J\r\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, m2290d2 = {"Lkotlinx/coroutines/selects/SeqNumber;", "<init>", "()V", "", "next", "()J", "kotlinx-coroutines-core", ""}, m2291k = 1, m2292mv = {1, 4, 0})
public final class SeqNumber {
    private static final AtomicLongFieldUpdater number$FU = AtomicLongFieldUpdater.newUpdater(SeqNumber.class, "number");
    private volatile long number = 1;

    public final long next() {
        return number$FU.incrementAndGet(this);
    }
}
