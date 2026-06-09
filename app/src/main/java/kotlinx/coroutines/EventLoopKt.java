package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: compiled from: EventLoop.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0000\u001a\b\u0010\u0002\u001a\u00020\u0003H\u0007¨\u0006\u0004"}, m2290d2 = {"createEventLoop", "Lkotlinx/coroutines/EventLoop;", "processNextEventInCurrentThread", "", "kotlinx-coroutines-core"}, m2291k = 2, m2292mv = {1, 4, 0})
public final class EventLoopKt {
    public static final EventLoop createEventLoop() {
        return new BlockingEventLoop(Thread.currentThread());
    }

    public static final long processNextEventInCurrentThread() {
        EventLoop eventLoopCurrentOrNull$kotlinx_coroutines_core = ThreadLocalEventLoop.INSTANCE.currentOrNull$kotlinx_coroutines_core();
        return eventLoopCurrentOrNull$kotlinx_coroutines_core != null ? eventLoopCurrentOrNull$kotlinx_coroutines_core.processNextEvent() : LongCompanionObject.MAX_VALUE;
    }
}
