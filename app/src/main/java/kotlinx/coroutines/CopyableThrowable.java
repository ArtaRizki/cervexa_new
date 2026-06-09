package kotlinx.coroutines;

import com.google.zxing.client.result.optional.NDEFRecord;
import java.lang.Throwable;
import kotlin.Metadata;
import kotlinx.coroutines.CopyableThrowable;

/* JADX INFO: compiled from: Debug.common.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\bg\u0018\u0000*\u0012\b\u0000\u0010\u0001*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u00002\u00020\u0003J\u000f\u0010\u0004\u001a\u0004\u0018\u00018\u0000H&¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, m2290d2 = {"Lkotlinx/coroutines/CopyableThrowable;", NDEFRecord.TEXT_WELL_KNOWN_TYPE, "", "", "createCopy", "()Ljava/lang/Throwable;", "kotlinx-coroutines-core"}, m2291k = 1, m2292mv = {1, 4, 0})
public interface CopyableThrowable<T extends Throwable & CopyableThrowable<T>> {
    T createCopy();
}
