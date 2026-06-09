package kotlinx.coroutines.flow.internal;

import com.google.zxing.client.result.optional.NDEFRecord;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: ChannelFlow.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bg\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002J\"\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, m2290d2 = {"Lkotlinx/coroutines/flow/internal/FusibleFlow;", NDEFRecord.TEXT_WELL_KNOWN_TYPE, "Lkotlinx/coroutines/flow/Flow;", "fuse", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "", "kotlinx-coroutines-core"}, m2291k = 1, m2292mv = {1, 4, 0})
public interface FusibleFlow<T> extends Flow<T> {
    FusibleFlow<T> fuse(CoroutineContext context, int capacity);

    /* JADX INFO: compiled from: ChannelFlow.kt */
    @Metadata(m2288bv = {1, 0, 3}, m2291k = 3, m2292mv = {1, 4, 0})
    public static final class DefaultImpls {
        public static /* synthetic */ FusibleFlow fuse$default(FusibleFlow fusibleFlow, CoroutineContext coroutineContext, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: fuse");
            }
            if ((i2 & 1) != 0) {
                coroutineContext = EmptyCoroutineContext.INSTANCE;
            }
            if ((i2 & 2) != 0) {
                i = -3;
            }
            return fusibleFlow.fuse(coroutineContext, i);
        }
    }
}
