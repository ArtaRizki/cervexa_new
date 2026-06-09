package com.serenegiant.p019db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import com.serenegiant.graphics.BitmapHelper;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes.dex */
public class SQLiteBlobHelper {
    public static byte[] floatArrayToByteArray(float[] fArr, int i, int i2) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate((i2 * 32) / 8);
        byteBufferAllocate.order(ByteOrder.nativeOrder());
        int i3 = (i2 % 8) + i;
        int i4 = i2 + i;
        while (i < i3) {
            byteBufferAllocate.putFloat(fArr[i]);
            i++;
        }
        while (i3 < i4) {
            byteBufferAllocate.putFloat(fArr[i3]);
            byteBufferAllocate.putFloat(fArr[i3 + 1]);
            byteBufferAllocate.putFloat(fArr[i3 + 2]);
            byteBufferAllocate.putFloat(fArr[i3 + 3]);
            byteBufferAllocate.putFloat(fArr[i3 + 4]);
            byteBufferAllocate.putFloat(fArr[i3 + 5]);
            byteBufferAllocate.putFloat(fArr[i3 + 6]);
            byteBufferAllocate.putFloat(fArr[i3 + 7]);
            i3 += 8;
        }
        byteBufferAllocate.flip();
        return byteBufferAllocate.array();
    }

    public static float[] byteArrayToFloatArray(byte[] bArr) {
        if (bArr == null || bArr.length < 4) {
            return null;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.nativeOrder());
        int iLimit = byteBufferWrap.limit() / 4;
        float[] fArr = new float[iLimit];
        int i = iLimit % 8;
        for (int i2 = 0; i2 < i; i2++) {
            fArr[i2] = byteBufferWrap.getFloat();
        }
        while (i < iLimit) {
            fArr[i] = byteBufferWrap.getFloat();
            fArr[i + 1] = byteBufferWrap.getFloat();
            fArr[i + 2] = byteBufferWrap.getFloat();
            fArr[i + 3] = byteBufferWrap.getFloat();
            fArr[i + 4] = byteBufferWrap.getFloat();
            fArr[i + 5] = byteBufferWrap.getFloat();
            fArr[i + 6] = byteBufferWrap.getFloat();
            fArr[i + 7] = byteBufferWrap.getFloat();
            i += 8;
        }
        return fArr;
    }

    public static byte[] doubleArrayToByteArray(double[] dArr, int i, int i2) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate((i2 * 64) / 8);
        byteBufferAllocate.order(ByteOrder.nativeOrder());
        int i3 = (i2 % 8) + i;
        int i4 = i2 + i;
        while (i < i3) {
            byteBufferAllocate.putDouble(dArr[i]);
            i++;
        }
        while (i3 < i4) {
            byteBufferAllocate.putDouble(dArr[i3]);
            byteBufferAllocate.putDouble(dArr[i3 + 1]);
            byteBufferAllocate.putDouble(dArr[i3 + 2]);
            byteBufferAllocate.putDouble(dArr[i3 + 3]);
            byteBufferAllocate.putDouble(dArr[i3 + 4]);
            byteBufferAllocate.putDouble(dArr[i3 + 5]);
            byteBufferAllocate.putDouble(dArr[i3 + 6]);
            byteBufferAllocate.putDouble(dArr[i3 + 7]);
            i3 += 8;
        }
        byteBufferAllocate.flip();
        return byteBufferAllocate.array();
    }

    public static double[] byteArrayToDoubleArray(byte[] bArr) {
        if (bArr == null || bArr.length < 8) {
            return null;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.nativeOrder());
        int iLimit = byteBufferWrap.limit() / 8;
        double[] dArr = new double[iLimit];
        int i = iLimit % 8;
        for (int i2 = 0; i2 < i; i2++) {
            dArr[i2] = byteBufferWrap.getDouble();
        }
        while (i < iLimit) {
            dArr[i] = byteBufferWrap.getDouble();
            dArr[i + 1] = byteBufferWrap.getDouble();
            dArr[i + 2] = byteBufferWrap.getDouble();
            dArr[i + 3] = byteBufferWrap.getDouble();
            dArr[i + 4] = byteBufferWrap.getDouble();
            dArr[i + 5] = byteBufferWrap.getDouble();
            dArr[i + 6] = byteBufferWrap.getDouble();
            dArr[i + 7] = byteBufferWrap.getDouble();
            i += 8;
        }
        return dArr;
    }

    public static byte[] intArrayToByteArray(int[] iArr, int i, int i2) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate((i2 * 32) / 8);
        byteBufferAllocate.order(ByteOrder.nativeOrder());
        int i3 = (i2 % 8) + i;
        int i4 = i2 + i;
        while (i < i3) {
            byteBufferAllocate.putInt(iArr[i]);
            i++;
        }
        while (i3 < i4) {
            byteBufferAllocate.putInt(iArr[i3]);
            byteBufferAllocate.putInt(iArr[i3 + 1]);
            byteBufferAllocate.putInt(iArr[i3 + 2]);
            byteBufferAllocate.putInt(iArr[i3 + 3]);
            byteBufferAllocate.putInt(iArr[i3 + 4]);
            byteBufferAllocate.putInt(iArr[i3 + 5]);
            byteBufferAllocate.putInt(iArr[i3 + 6]);
            byteBufferAllocate.putInt(iArr[i3 + 7]);
            i3 += 8;
        }
        byteBufferAllocate.flip();
        return byteBufferAllocate.array();
    }

    public static int[] byteArrayToIntArray(byte[] bArr) {
        if (bArr == null || bArr.length < 4) {
            return null;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.nativeOrder());
        int iLimit = byteBufferWrap.limit() / 4;
        int[] iArr = new int[iLimit];
        int i = iLimit % 8;
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = byteBufferWrap.getInt();
        }
        while (i < iLimit) {
            iArr[i] = byteBufferWrap.getInt();
            iArr[i + 1] = byteBufferWrap.getInt();
            iArr[i + 2] = byteBufferWrap.getInt();
            iArr[i + 3] = byteBufferWrap.getInt();
            iArr[i + 4] = byteBufferWrap.getInt();
            iArr[i + 5] = byteBufferWrap.getInt();
            iArr[i + 6] = byteBufferWrap.getInt();
            iArr[i + 7] = byteBufferWrap.getInt();
            i += 8;
        }
        return iArr;
    }

    public static byte[] shortArrayToByteArray(short[] sArr, int i, int i2) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate((i2 * 16) / 8);
        byteBufferAllocate.order(ByteOrder.nativeOrder());
        int i3 = (i2 % 8) + i;
        int i4 = i2 + i;
        while (i < i3) {
            byteBufferAllocate.putShort(sArr[i]);
            i++;
        }
        while (i3 < i4) {
            byteBufferAllocate.putShort(sArr[i3]);
            byteBufferAllocate.putShort(sArr[i3 + 1]);
            byteBufferAllocate.putShort(sArr[i3 + 2]);
            byteBufferAllocate.putShort(sArr[i3 + 3]);
            byteBufferAllocate.putShort(sArr[i3 + 4]);
            byteBufferAllocate.putShort(sArr[i3 + 5]);
            byteBufferAllocate.putShort(sArr[i3 + 6]);
            byteBufferAllocate.putShort(sArr[i3 + 7]);
            i3 += 8;
        }
        byteBufferAllocate.flip();
        return byteBufferAllocate.array();
    }

    public static short[] byteArrayToShortArray(byte[] bArr) {
        if (bArr == null || bArr.length < 2) {
            return null;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.nativeOrder());
        int iLimit = byteBufferWrap.limit() / 2;
        short[] sArr = new short[iLimit];
        int i = iLimit % 8;
        for (int i2 = 0; i2 < i; i2++) {
            sArr[i2] = byteBufferWrap.getShort();
        }
        while (i < iLimit) {
            sArr[i] = byteBufferWrap.getShort();
            sArr[i + 1] = byteBufferWrap.getShort();
            sArr[i + 2] = byteBufferWrap.getShort();
            sArr[i + 3] = byteBufferWrap.getShort();
            sArr[i + 4] = byteBufferWrap.getShort();
            sArr[i + 5] = byteBufferWrap.getShort();
            sArr[i + 6] = byteBufferWrap.getShort();
            sArr[i + 7] = byteBufferWrap.getShort();
            i += 8;
        }
        return sArr;
    }

    public static byte[] longArrayToByteArray(long[] jArr, int i, int i2) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate((i2 * 64) / 8);
        byteBufferAllocate.order(ByteOrder.nativeOrder());
        int i3 = (i2 % 8) + i;
        int i4 = i2 + i;
        while (i < i3) {
            byteBufferAllocate.putLong(jArr[i]);
            i++;
        }
        while (i3 < i4) {
            byteBufferAllocate.putLong(jArr[i3]);
            byteBufferAllocate.putLong(jArr[i3 + 1]);
            byteBufferAllocate.putLong(jArr[i3 + 2]);
            byteBufferAllocate.putLong(jArr[i3 + 3]);
            byteBufferAllocate.putLong(jArr[i3 + 4]);
            byteBufferAllocate.putLong(jArr[i3 + 5]);
            byteBufferAllocate.putLong(jArr[i3 + 6]);
            byteBufferAllocate.putLong(jArr[i3 + 7]);
            i3 += 8;
        }
        byteBufferAllocate.flip();
        return byteBufferAllocate.array();
    }

    public static long[] byteArrayToLongArray(byte[] bArr) {
        if (bArr == null || bArr.length < 8) {
            return null;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.nativeOrder());
        int iLimit = byteBufferWrap.limit() / 8;
        long[] jArr = new long[iLimit];
        int i = iLimit % 8;
        for (int i2 = 0; i2 < i; i2++) {
            jArr[i2] = byteBufferWrap.getLong();
        }
        while (i < iLimit) {
            jArr[i] = byteBufferWrap.getLong();
            jArr[i + 1] = byteBufferWrap.getLong();
            jArr[i + 2] = byteBufferWrap.getLong();
            jArr[i + 3] = byteBufferWrap.getLong();
            jArr[i + 4] = byteBufferWrap.getLong();
            jArr[i + 5] = byteBufferWrap.getLong();
            jArr[i + 6] = byteBufferWrap.getLong();
            jArr[i + 7] = byteBufferWrap.getLong();
            i += 8;
        }
        return jArr;
    }

    public static void bindBlobFloatArray(SQLiteStatement sQLiteStatement, int i, float[] fArr) {
        sQLiteStatement.bindBlob(i, floatArrayToByteArray(fArr, 0, fArr.length));
    }

    public static void bindBlobFloatArray(SQLiteStatement sQLiteStatement, int i, float[] fArr, int i2, int i3) {
        sQLiteStatement.bindBlob(i, floatArrayToByteArray(fArr, i2, i3));
    }

    public static void bindBlobDoubleArray(SQLiteStatement sQLiteStatement, int i, double[] dArr) {
        sQLiteStatement.bindBlob(i, doubleArrayToByteArray(dArr, 0, dArr.length));
    }

    public static void bindBlobDoubleArray(SQLiteStatement sQLiteStatement, int i, double[] dArr, int i2, int i3) {
        sQLiteStatement.bindBlob(i, doubleArrayToByteArray(dArr, i2, i3));
    }

    public static void bindBlobIntArray(SQLiteStatement sQLiteStatement, int i, int[] iArr) {
        sQLiteStatement.bindBlob(i, intArrayToByteArray(iArr, 0, iArr.length));
    }

    public static void bindBlobIntArray(SQLiteStatement sQLiteStatement, int i, int[] iArr, int i2, int i3) {
        sQLiteStatement.bindBlob(i, intArrayToByteArray(iArr, i2, i3));
    }

    public static void bindBlobShortArray(SQLiteStatement sQLiteStatement, int i, short[] sArr) {
        sQLiteStatement.bindBlob(i, shortArrayToByteArray(sArr, 0, sArr.length));
    }

    public static void bindBlobShortArray(SQLiteStatement sQLiteStatement, int i, short[] sArr, int i2, int i3) {
        sQLiteStatement.bindBlob(i, shortArrayToByteArray(sArr, i2, i3));
    }

    public static void bindBlobLongArray(SQLiteStatement sQLiteStatement, int i, long[] jArr) {
        sQLiteStatement.bindBlob(i, longArrayToByteArray(jArr, 0, jArr.length));
    }

    public static void bindBlobLongArray(SQLiteStatement sQLiteStatement, int i, long[] jArr, int i2, int i3) {
        sQLiteStatement.bindBlob(i, longArrayToByteArray(jArr, i2, i3));
    }

    public static void bindBlobBitmap(SQLiteStatement sQLiteStatement, int i, Bitmap bitmap) {
        sQLiteStatement.bindBlob(i, BitmapHelper.BitmapToByteArray(bitmap));
    }

    public static float[] getBlobFloatArray(Cursor cursor, int i) {
        return byteArrayToFloatArray(cursor.getBlob(i));
    }

    public static float[] getBlobFloatArray(Cursor cursor, String str, float[] fArr) {
        float[] fArrByteArrayToFloatArray = byteArrayToFloatArray(getBlob(cursor, str, null));
        return fArrByteArrayToFloatArray == null ? fArr : fArrByteArrayToFloatArray;
    }

    public static double[] getBlobDoubleArray(Cursor cursor, int i) {
        return byteArrayToDoubleArray(cursor.getBlob(i));
    }

    public static double[] getBlobDoubleArray(Cursor cursor, String str, double[] dArr) {
        double[] dArrByteArrayToDoubleArray = byteArrayToDoubleArray(getBlob(cursor, str, null));
        return dArrByteArrayToDoubleArray == null ? dArr : dArrByteArrayToDoubleArray;
    }

    public static byte[] getBlob(Cursor cursor, String str, byte[] bArr) {
        try {
            return cursor.getBlob(cursor.getColumnIndexOrThrow(str));
        } catch (Exception unused) {
            return bArr;
        }
    }

    public static int[] getBlobIntArray(Cursor cursor, int i) {
        return byteArrayToIntArray(cursor.getBlob(i));
    }

    public static int[] getBlobIntArray(Cursor cursor, String str, int[] iArr) {
        int[] iArrByteArrayToIntArray = byteArrayToIntArray(getBlob(cursor, str, null));
        return iArrByteArrayToIntArray == null ? iArr : iArrByteArrayToIntArray;
    }

    public static short[] getBlobShortArray(Cursor cursor, int i) {
        return byteArrayToShortArray(cursor.getBlob(i));
    }

    public static short[] getBlobShortArray(Cursor cursor, String str, short[] sArr) {
        short[] sArrByteArrayToShortArray = byteArrayToShortArray(getBlob(cursor, str, null));
        return sArrByteArrayToShortArray == null ? sArr : sArrByteArrayToShortArray;
    }

    public static long[] getBlobLongArray(Cursor cursor, int i) {
        return byteArrayToLongArray(cursor.getBlob(i));
    }

    public static long[] getBlobLongArray(Cursor cursor, String str, long[] jArr) {
        long[] jArrByteArrayToLongArray = byteArrayToLongArray(getBlob(cursor, str, null));
        return jArrByteArrayToLongArray == null ? jArr : jArrByteArrayToLongArray;
    }

    public static Bitmap getBlobBitmap(Cursor cursor, int i) {
        return BitmapHelper.asBitmap(cursor.getBlob(i));
    }

    public static Bitmap getBlobBitmap(Cursor cursor, String str) {
        return BitmapHelper.asBitmap(getBlob(cursor, str, null));
    }

    public static Bitmap getBlobBitmap(Cursor cursor, int i, int i2, int i3) {
        return BitmapHelper.asBitmap(cursor.getBlob(i), i2, i3);
    }

    public static Bitmap getBlobBitmap(Cursor cursor, String str, int i, int i2) {
        return BitmapHelper.asBitmap(getBlob(cursor, str, null), i, i2);
    }

    public static Bitmap getBlobBitmapStrictSize(Cursor cursor, int i, int i2, int i3) {
        return BitmapHelper.asBitmapStrictSize(cursor.getBlob(i), i2, i3);
    }

    public static Bitmap getBlobBitmapStrictSize(Cursor cursor, String str, int i, int i2) {
        return BitmapHelper.asBitmapStrictSize(getBlob(cursor, str, null), i, i2);
    }
}
