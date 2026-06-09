package kotlin.text;

import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.UnsignedKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UStrings.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000,\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0014\u0010\u0010\u001a\u00020\u0002*\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001a\u001c\u0010\u0010\u001a\u00020\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a\u0011\u0010\u0013\u001a\u0004\u0018\u00010\u0002*\u00020\u0001H\u0007ø\u0001\u0000\u001a\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000\u001a\u0014\u0010\u0014\u001a\u00020\u0007*\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a\u001c\u0010\u0014\u001a\u00020\u0007*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0016\u001a\u0011\u0010\u0017\u001a\u0004\u0018\u00010\u0007*\u00020\u0001H\u0007ø\u0001\u0000\u001a\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u0007*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000\u001a\u0014\u0010\u0018\u001a\u00020\n*\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0019\u001a\u001c\u0010\u0018\u001a\u00020\n*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001a\u001a\u0011\u0010\u001b\u001a\u0004\u0018\u00010\n*\u00020\u0001H\u0007ø\u0001\u0000\u001a\u0019\u0010\u001b\u001a\u0004\u0018\u00010\n*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000\u001a\u0014\u0010\u001c\u001a\u00020\r*\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001d\u001a\u001c\u0010\u001c\u001a\u00020\r*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001e\u001a\u0011\u0010\u001f\u001a\u0004\u0018\u00010\r*\u00020\u0001H\u0007ø\u0001\u0000\u001a\u0019\u0010\u001f\u001a\u0004\u0018\u00010\r*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006 "}, m2290d2 = {"toString", "", "Lkotlin/UByte;", "radix", "", "toString-LxnNnR4", "(BI)Ljava/lang/String;", "Lkotlin/UInt;", "toString-V7xB4Y4", "(II)Ljava/lang/String;", "Lkotlin/ULong;", "toString-JSWoG40", "(JI)Ljava/lang/String;", "Lkotlin/UShort;", "toString-olVBNx4", "(SI)Ljava/lang/String;", "toUByte", "(Ljava/lang/String;)B", "(Ljava/lang/String;I)B", "toUByteOrNull", "toUInt", "(Ljava/lang/String;)I", "(Ljava/lang/String;I)I", "toUIntOrNull", "toULong", "(Ljava/lang/String;)J", "(Ljava/lang/String;I)J", "toULongOrNull", "toUShort", "(Ljava/lang/String;)S", "(Ljava/lang/String;I)S", "toUShortOrNull", "kotlin-stdlib"}, m2291k = 2, m2292mv = {1, 4, 0})
public final class UStringsKt {
    /* JADX INFO: renamed from: toString-LxnNnR4, reason: not valid java name */
    public static final String m3601toStringLxnNnR4(byte b, int i) {
        String string = Integer.toString(b & UByte.MAX_VALUE, CharsKt.checkRadix(i));
        Intrinsics.checkNotNullExpressionValue(string, "java.lang.Integer.toStri…(this, checkRadix(radix))");
        return string;
    }

    /* JADX INFO: renamed from: toString-olVBNx4, reason: not valid java name */
    public static final String m3603toStringolVBNx4(short s, int i) {
        String string = Integer.toString(s & UShort.MAX_VALUE, CharsKt.checkRadix(i));
        Intrinsics.checkNotNullExpressionValue(string, "java.lang.Integer.toStri…(this, checkRadix(radix))");
        return string;
    }

    /* JADX INFO: renamed from: toString-V7xB4Y4, reason: not valid java name */
    public static final String m3602toStringV7xB4Y4(int i, int i2) {
        String string = Long.toString(((long) i) & 4294967295L, CharsKt.checkRadix(i2));
        Intrinsics.checkNotNullExpressionValue(string, "java.lang.Long.toString(this, checkRadix(radix))");
        return string;
    }

    /* JADX INFO: renamed from: toString-JSWoG40, reason: not valid java name */
    public static final String m3600toStringJSWoG40(long j, int i) {
        return UnsignedKt.ulongToString(j, CharsKt.checkRadix(i));
    }

    public static final byte toUByte(String toUByte) {
        Intrinsics.checkNotNullParameter(toUByte, "$this$toUByte");
        UByte uByteOrNull = toUByteOrNull(toUByte);
        if (uByteOrNull != null) {
            return uByteOrNull.getData();
        }
        StringsKt.numberFormatError(toUByte);
        throw new KotlinNothingValueException();
    }

    public static final byte toUByte(String toUByte, int i) {
        Intrinsics.checkNotNullParameter(toUByte, "$this$toUByte");
        UByte uByteOrNull = toUByteOrNull(toUByte, i);
        if (uByteOrNull != null) {
            return uByteOrNull.getData();
        }
        StringsKt.numberFormatError(toUByte);
        throw new KotlinNothingValueException();
    }

    public static final short toUShort(String toUShort) {
        Intrinsics.checkNotNullParameter(toUShort, "$this$toUShort");
        UShort uShortOrNull = toUShortOrNull(toUShort);
        if (uShortOrNull != null) {
            return uShortOrNull.getData();
        }
        StringsKt.numberFormatError(toUShort);
        throw new KotlinNothingValueException();
    }

    public static final short toUShort(String toUShort, int i) {
        Intrinsics.checkNotNullParameter(toUShort, "$this$toUShort");
        UShort uShortOrNull = toUShortOrNull(toUShort, i);
        if (uShortOrNull != null) {
            return uShortOrNull.getData();
        }
        StringsKt.numberFormatError(toUShort);
        throw new KotlinNothingValueException();
    }

    public static final int toUInt(String toUInt) {
        Intrinsics.checkNotNullParameter(toUInt, "$this$toUInt");
        UInt uIntOrNull = toUIntOrNull(toUInt);
        if (uIntOrNull != null) {
            return uIntOrNull.getData();
        }
        StringsKt.numberFormatError(toUInt);
        throw new KotlinNothingValueException();
    }

    public static final int toUInt(String toUInt, int i) {
        Intrinsics.checkNotNullParameter(toUInt, "$this$toUInt");
        UInt uIntOrNull = toUIntOrNull(toUInt, i);
        if (uIntOrNull != null) {
            return uIntOrNull.getData();
        }
        StringsKt.numberFormatError(toUInt);
        throw new KotlinNothingValueException();
    }

    public static final long toULong(String toULong) {
        Intrinsics.checkNotNullParameter(toULong, "$this$toULong");
        ULong uLongOrNull = toULongOrNull(toULong);
        if (uLongOrNull != null) {
            return uLongOrNull.getData();
        }
        StringsKt.numberFormatError(toULong);
        throw new KotlinNothingValueException();
    }

    public static final long toULong(String toULong, int i) {
        Intrinsics.checkNotNullParameter(toULong, "$this$toULong");
        ULong uLongOrNull = toULongOrNull(toULong, i);
        if (uLongOrNull != null) {
            return uLongOrNull.getData();
        }
        StringsKt.numberFormatError(toULong);
        throw new KotlinNothingValueException();
    }

    public static final UByte toUByteOrNull(String toUByteOrNull) {
        Intrinsics.checkNotNullParameter(toUByteOrNull, "$this$toUByteOrNull");
        return toUByteOrNull(toUByteOrNull, 10);
    }

    public static final UByte toUByteOrNull(String toUByteOrNull, int i) {
        Intrinsics.checkNotNullParameter(toUByteOrNull, "$this$toUByteOrNull");
        UInt uIntOrNull = toUIntOrNull(toUByteOrNull, i);
        if (uIntOrNull == null) {
            return null;
        }
        int data = uIntOrNull.getData();
        if (UnsignedKt.uintCompare(data, UInt.m2439constructorimpl(255)) > 0) {
            return null;
        }
        return UByte.m2365boximpl(UByte.m2371constructorimpl((byte) data));
    }

    public static final UShort toUShortOrNull(String toUShortOrNull) {
        Intrinsics.checkNotNullParameter(toUShortOrNull, "$this$toUShortOrNull");
        return toUShortOrNull(toUShortOrNull, 10);
    }

    public static final UShort toUShortOrNull(String toUShortOrNull, int i) {
        Intrinsics.checkNotNullParameter(toUShortOrNull, "$this$toUShortOrNull");
        UInt uIntOrNull = toUIntOrNull(toUShortOrNull, i);
        if (uIntOrNull == null) {
            return null;
        }
        int data = uIntOrNull.getData();
        if (UnsignedKt.uintCompare(data, UInt.m2439constructorimpl(65535)) > 0) {
            return null;
        }
        return UShort.m2601boximpl(UShort.m2607constructorimpl((short) data));
    }

    public static final UInt toUIntOrNull(String toUIntOrNull) {
        Intrinsics.checkNotNullParameter(toUIntOrNull, "$this$toUIntOrNull");
        return toUIntOrNull(toUIntOrNull, 10);
    }

    public static final UInt toUIntOrNull(String toUIntOrNull, int i) {
        Intrinsics.checkNotNullParameter(toUIntOrNull, "$this$toUIntOrNull");
        CharsKt.checkRadix(i);
        int length = toUIntOrNull.length();
        if (length == 0) {
            return null;
        }
        int i2 = 0;
        char cCharAt = toUIntOrNull.charAt(0);
        int i3 = 1;
        if (Intrinsics.compare((int) cCharAt, 48) >= 0) {
            i3 = 0;
        } else if (length == 1 || cCharAt != '+') {
            return null;
        }
        int iM2439constructorimpl = UInt.m2439constructorimpl(i);
        int iM2668uintDivideJ1ME1BU = 119304647;
        while (i3 < length) {
            int iDigitOf = CharsKt.digitOf(toUIntOrNull.charAt(i3), i);
            if (iDigitOf < 0) {
                return null;
            }
            if (UnsignedKt.uintCompare(i2, iM2668uintDivideJ1ME1BU) > 0) {
                if (iM2668uintDivideJ1ME1BU == 119304647) {
                    iM2668uintDivideJ1ME1BU = UnsignedKt.m2668uintDivideJ1ME1BU(-1, iM2439constructorimpl);
                    if (UnsignedKt.uintCompare(i2, iM2668uintDivideJ1ME1BU) > 0) {
                    }
                }
                return null;
            }
            int iM2439constructorimpl2 = UInt.m2439constructorimpl(i2 * iM2439constructorimpl);
            int iM2439constructorimpl3 = UInt.m2439constructorimpl(UInt.m2439constructorimpl(iDigitOf) + iM2439constructorimpl2);
            if (UnsignedKt.uintCompare(iM2439constructorimpl3, iM2439constructorimpl2) < 0) {
                return null;
            }
            i3++;
            i2 = iM2439constructorimpl3;
        }
        return UInt.m2433boximpl(i2);
    }

    public static final ULong toULongOrNull(String toULongOrNull) {
        Intrinsics.checkNotNullParameter(toULongOrNull, "$this$toULongOrNull");
        return toULongOrNull(toULongOrNull, 10);
    }

    public static final ULong toULongOrNull(String toULongOrNull, int i) {
        Intrinsics.checkNotNullParameter(toULongOrNull, "$this$toULongOrNull");
        CharsKt.checkRadix(i);
        int length = toULongOrNull.length();
        if (length == 0) {
            return null;
        }
        long j = -1;
        int i2 = 0;
        char cCharAt = toULongOrNull.charAt(0);
        if (Intrinsics.compare((int) cCharAt, 48) < 0) {
            if (length == 1 || cCharAt != '+') {
                return null;
            }
            i2 = 1;
        }
        long jM2509constructorimpl = ULong.m2509constructorimpl(i);
        long j2 = 0;
        long jM2670ulongDivideeb3DHEI = 512409557603043100L;
        while (i2 < length) {
            int iDigitOf = CharsKt.digitOf(toULongOrNull.charAt(i2), i);
            if (iDigitOf < 0) {
                return null;
            }
            if (UnsignedKt.ulongCompare(j2, jM2670ulongDivideeb3DHEI) > 0) {
                if (jM2670ulongDivideeb3DHEI == 512409557603043100L) {
                    jM2670ulongDivideeb3DHEI = UnsignedKt.m2670ulongDivideeb3DHEI(j, jM2509constructorimpl);
                    if (UnsignedKt.ulongCompare(j2, jM2670ulongDivideeb3DHEI) > 0) {
                    }
                }
                return null;
            }
            long jM2509constructorimpl2 = ULong.m2509constructorimpl(j2 * jM2509constructorimpl);
            long jM2509constructorimpl3 = ULong.m2509constructorimpl(ULong.m2509constructorimpl(((long) UInt.m2439constructorimpl(iDigitOf)) & 4294967295L) + jM2509constructorimpl2);
            if (UnsignedKt.ulongCompare(jM2509constructorimpl3, jM2509constructorimpl2) < 0) {
                return null;
            }
            i2++;
            j2 = jM2509constructorimpl3;
            j = -1;
        }
        return ULong.m2503boximpl(j2);
    }
}
