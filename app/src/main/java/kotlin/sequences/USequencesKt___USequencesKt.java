package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: _USequences.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0005\u001a\u001c\u0010\u0000\u001a\u00020\u0007*\b\u0012\u0004\u0012\u00020\u00070\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\n0\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, m2290d2 = {"sum", "Lkotlin/UInt;", "Lkotlin/sequences/Sequence;", "Lkotlin/UByte;", "sumOfUByte", "(Lkotlin/sequences/Sequence;)I", "sumOfUInt", "Lkotlin/ULong;", "sumOfULong", "(Lkotlin/sequences/Sequence;)J", "Lkotlin/UShort;", "sumOfUShort", "kotlin-stdlib"}, m2291k = 5, m2292mv = {1, 4, 0}, m2294xi = 1, m2295xs = "kotlin/sequences/USequencesKt")
class USequencesKt___USequencesKt {
    public static final int sumOfUInt(Sequence<UInt> sum) {
        Intrinsics.checkNotNullParameter(sum, "$this$sum");
        Iterator<UInt> it = sum.iterator();
        int iM2439constructorimpl = 0;
        while (it.hasNext()) {
            iM2439constructorimpl = UInt.m2439constructorimpl(iM2439constructorimpl + it.next().getData());
        }
        return iM2439constructorimpl;
    }

    public static final long sumOfULong(Sequence<ULong> sum) {
        Intrinsics.checkNotNullParameter(sum, "$this$sum");
        Iterator<ULong> it = sum.iterator();
        long jM2509constructorimpl = 0;
        while (it.hasNext()) {
            jM2509constructorimpl = ULong.m2509constructorimpl(jM2509constructorimpl + it.next().getData());
        }
        return jM2509constructorimpl;
    }

    public static final int sumOfUByte(Sequence<UByte> sum) {
        Intrinsics.checkNotNullParameter(sum, "$this$sum");
        Iterator<UByte> it = sum.iterator();
        int iM2439constructorimpl = 0;
        while (it.hasNext()) {
            iM2439constructorimpl = UInt.m2439constructorimpl(iM2439constructorimpl + UInt.m2439constructorimpl(it.next().getData() & UByte.MAX_VALUE));
        }
        return iM2439constructorimpl;
    }

    public static final int sumOfUShort(Sequence<UShort> sum) {
        Intrinsics.checkNotNullParameter(sum, "$this$sum");
        Iterator<UShort> it = sum.iterator();
        int iM2439constructorimpl = 0;
        while (it.hasNext()) {
            iM2439constructorimpl = UInt.m2439constructorimpl(iM2439constructorimpl + UInt.m2439constructorimpl(it.next().getData() & UShort.MAX_VALUE));
        }
        return iM2439constructorimpl;
    }
}
