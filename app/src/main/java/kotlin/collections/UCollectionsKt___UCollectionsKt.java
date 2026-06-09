package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: _UCollections.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0005\u001a\u001c\u0010\u0000\u001a\u00020\u0007*\b\u0012\u0004\u0012\u00020\u00070\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\n0\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\u0005\u001a\u001a\u0010\f\u001a\u00020\r*\b\u0012\u0004\u0012\u00020\u00030\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a\u001a\u0010\u0010\u001a\u00020\u0011*\b\u0012\u0004\u0012\u00020\u00010\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a\u001a\u0010\u0013\u001a\u00020\u0014*\b\u0012\u0004\u0012\u00020\u00070\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a\u001a\u0010\u0016\u001a\u00020\u0017*\b\u0012\u0004\u0012\u00020\n0\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0018\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, m2290d2 = {"sum", "Lkotlin/UInt;", "", "Lkotlin/UByte;", "sumOfUByte", "(Ljava/lang/Iterable;)I", "sumOfUInt", "Lkotlin/ULong;", "sumOfULong", "(Ljava/lang/Iterable;)J", "Lkotlin/UShort;", "sumOfUShort", "toUByteArray", "Lkotlin/UByteArray;", "", "(Ljava/util/Collection;)[B", "toUIntArray", "Lkotlin/UIntArray;", "(Ljava/util/Collection;)[I", "toULongArray", "Lkotlin/ULongArray;", "(Ljava/util/Collection;)[J", "toUShortArray", "Lkotlin/UShortArray;", "(Ljava/util/Collection;)[S", "kotlin-stdlib"}, m2291k = 5, m2292mv = {1, 4, 0}, m2294xi = 1, m2295xs = "kotlin/collections/UCollectionsKt")
class UCollectionsKt___UCollectionsKt {
    public static final byte[] toUByteArray(Collection<UByte> toUByteArray) {
        Intrinsics.checkNotNullParameter(toUByteArray, "$this$toUByteArray");
        byte[] bArrM2414constructorimpl = UByteArray.m2414constructorimpl(toUByteArray.size());
        Iterator<UByte> it = toUByteArray.iterator();
        int i = 0;
        while (it.hasNext()) {
            UByteArray.m2425setVurrAj0(bArrM2414constructorimpl, i, it.next().getData());
            i++;
        }
        return bArrM2414constructorimpl;
    }

    public static final int[] toUIntArray(Collection<UInt> toUIntArray) {
        Intrinsics.checkNotNullParameter(toUIntArray, "$this$toUIntArray");
        int[] iArrM2484constructorimpl = UIntArray.m2484constructorimpl(toUIntArray.size());
        Iterator<UInt> it = toUIntArray.iterator();
        int i = 0;
        while (it.hasNext()) {
            UIntArray.m2495setVXSXFK8(iArrM2484constructorimpl, i, it.next().getData());
            i++;
        }
        return iArrM2484constructorimpl;
    }

    public static final long[] toULongArray(Collection<ULong> toULongArray) {
        Intrinsics.checkNotNullParameter(toULongArray, "$this$toULongArray");
        long[] jArrM2554constructorimpl = ULongArray.m2554constructorimpl(toULongArray.size());
        Iterator<ULong> it = toULongArray.iterator();
        int i = 0;
        while (it.hasNext()) {
            ULongArray.m2565setk8EXiF4(jArrM2554constructorimpl, i, it.next().getData());
            i++;
        }
        return jArrM2554constructorimpl;
    }

    public static final short[] toUShortArray(Collection<UShort> toUShortArray) {
        Intrinsics.checkNotNullParameter(toUShortArray, "$this$toUShortArray");
        short[] sArrM2650constructorimpl = UShortArray.m2650constructorimpl(toUShortArray.size());
        Iterator<UShort> it = toUShortArray.iterator();
        int i = 0;
        while (it.hasNext()) {
            UShortArray.m2661set01HTLdE(sArrM2650constructorimpl, i, it.next().getData());
            i++;
        }
        return sArrM2650constructorimpl;
    }

    public static final int sumOfUInt(Iterable<UInt> sum) {
        Intrinsics.checkNotNullParameter(sum, "$this$sum");
        Iterator<UInt> it = sum.iterator();
        int iM2439constructorimpl = 0;
        while (it.hasNext()) {
            iM2439constructorimpl = UInt.m2439constructorimpl(iM2439constructorimpl + it.next().getData());
        }
        return iM2439constructorimpl;
    }

    public static final long sumOfULong(Iterable<ULong> sum) {
        Intrinsics.checkNotNullParameter(sum, "$this$sum");
        Iterator<ULong> it = sum.iterator();
        long jM2509constructorimpl = 0;
        while (it.hasNext()) {
            jM2509constructorimpl = ULong.m2509constructorimpl(jM2509constructorimpl + it.next().getData());
        }
        return jM2509constructorimpl;
    }

    public static final int sumOfUByte(Iterable<UByte> sum) {
        Intrinsics.checkNotNullParameter(sum, "$this$sum");
        Iterator<UByte> it = sum.iterator();
        int iM2439constructorimpl = 0;
        while (it.hasNext()) {
            iM2439constructorimpl = UInt.m2439constructorimpl(iM2439constructorimpl + UInt.m2439constructorimpl(it.next().getData() & UByte.MAX_VALUE));
        }
        return iM2439constructorimpl;
    }

    public static final int sumOfUShort(Iterable<UShort> sum) {
        Intrinsics.checkNotNullParameter(sum, "$this$sum");
        Iterator<UShort> it = sum.iterator();
        int iM2439constructorimpl = 0;
        while (it.hasNext()) {
            iM2439constructorimpl = UInt.m2439constructorimpl(iM2439constructorimpl + UInt.m2439constructorimpl(it.next().getData() & UShort.MAX_VALUE));
        }
        return iM2439constructorimpl;
    }
}
