package androidx.activity.contextaware;

import android.content.Context;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;

/* JADX INFO: renamed from: androidx.activity.contextaware.ContextAwareKt$withContextAvailable$$inlined$suspendCancellableCoroutine$lambda$1 */
/* JADX INFO: compiled from: ContextAware.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006¸\u0006\u0000"}, m2290d2 = {"androidx/activity/contextaware/ContextAwareKt$withContextAvailable$2$listener$1", "Landroidx/activity/contextaware/OnContextAvailableListener;", "onContextAvailable", "", "context", "Landroid/content/Context;", "activity-ktx_release"}, m2291k = 1, m2292mv = {1, 4, 0})
public final class C0038x8a9e0a55 implements OnContextAvailableListener {
    final /* synthetic */ CancellableContinuation $co;
    final /* synthetic */ Function1 $onContextAvailable$inlined;
    final /* synthetic */ ContextAware $this_withContextAvailable$inlined;

    public C0038x8a9e0a55(CancellableContinuation cancellableContinuation, ContextAware contextAware, Function1 function1) {
        this.$co = cancellableContinuation;
        this.$this_withContextAvailable$inlined = contextAware;
        this.$onContextAvailable$inlined = function1;
    }

    @Override // androidx.activity.contextaware.OnContextAvailableListener
    public void onContextAvailable(Context context) {
        Object objM2353constructorimpl;
        Intrinsics.checkNotNullParameter(context, "context");
        CancellableContinuation cancellableContinuation = this.$co;
        try {
            Result.Companion companion = Result.INSTANCE;
            C0038x8a9e0a55 c0038x8a9e0a55 = this;
            objM2353constructorimpl = Result.m2353constructorimpl(this.$onContextAvailable$inlined.invoke(context));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM2353constructorimpl = Result.m2353constructorimpl(ResultKt.createFailure(th));
        }
        cancellableContinuation.resumeWith(objM2353constructorimpl);
    }
}
