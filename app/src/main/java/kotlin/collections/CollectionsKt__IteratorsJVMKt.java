package kotlin.collections;

import com.google.zxing.client.result.optional.NDEFRecord;
import java.util.Enumeration;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: IteratorsJVM.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0086\u0002¨\u0006\u0004"}, m2290d2 = {"iterator", "", NDEFRecord.TEXT_WELL_KNOWN_TYPE, "Ljava/util/Enumeration;", "kotlin-stdlib"}, m2291k = 5, m2292mv = {1, 4, 0}, m2294xi = 1, m2295xs = "kotlin/collections/CollectionsKt")
public class CollectionsKt__IteratorsJVMKt extends CollectionsKt__IterablesKt {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: kotlin.collections.CollectionsKt__IteratorsJVMKt$iterator$1 */
    /* JADX INFO: compiled from: IteratorsJVM.kt */
    @Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000\u0013\n\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\t\u0010\u0002\u001a\u00020\u0003H\u0096\u0002J\u000e\u0010\u0004\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, m2290d2 = {"kotlin/collections/CollectionsKt__IteratorsJVMKt$iterator$1", "", "hasNext", "", "next", "()Ljava/lang/Object;", "kotlin-stdlib"}, m2291k = 1, m2292mv = {1, 4, 0})
    public static final class C21311<T> implements Iterator<T>, KMappedMarker {
        final /* synthetic */ Enumeration $this_iterator;

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        C21311(Enumeration<T> enumeration) {
            this.$this_iterator = enumeration;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.$this_iterator.hasMoreElements();
        }

        @Override // java.util.Iterator
        public T next() {
            return (T) this.$this_iterator.nextElement();
        }
    }

    public static final <T> Iterator<T> iterator(Enumeration<T> iterator) {
        Intrinsics.checkNotNullParameter(iterator, "$this$iterator");
        return new C21311(iterator);
    }
}
