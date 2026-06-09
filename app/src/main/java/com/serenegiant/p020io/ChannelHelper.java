package com.serenegiant.p020io;

import com.bumptech.glide.load.Key;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public class ChannelHelper {
    private static final Charset UTF8 = Charset.forName(Key.STRING_CHARSET_NAME);

    public static boolean readBoolean(ByteChannel byteChannel) throws IOException {
        return readBoolean(byteChannel, null);
    }

    public static boolean readBoolean(ByteChannel byteChannel, ByteBuffer byteBuffer) throws IOException {
        ByteBuffer byteBufferCheckBuffer = checkBuffer(byteBuffer, 1);
        if (byteChannel.read(byteBufferCheckBuffer) != 1) {
            throw new IOException();
        }
        byteBufferCheckBuffer.clear();
        return byteBufferCheckBuffer.get() != 0;
    }

    public static byte readByte(ByteChannel byteChannel) throws IOException {
        return readByte(byteChannel, null);
    }

    public static byte readByte(ByteChannel byteChannel, ByteBuffer byteBuffer) throws IOException {
        ByteBuffer byteBufferCheckBuffer = checkBuffer(byteBuffer, 1);
        if (byteChannel.read(byteBufferCheckBuffer) != 1) {
            throw new IOException();
        }
        byteBufferCheckBuffer.clear();
        return byteBufferCheckBuffer.get();
    }

    public static char readChar(ByteChannel byteChannel) throws IOException {
        return readChar(byteChannel, null);
    }

    public static char readChar(ByteChannel byteChannel, ByteBuffer byteBuffer) throws IOException {
        ByteBuffer byteBufferCheckBuffer = checkBuffer(byteBuffer, 2);
        if (byteChannel.read(byteBufferCheckBuffer) != 2) {
            throw new IOException();
        }
        byteBufferCheckBuffer.clear();
        return byteBufferCheckBuffer.getChar();
    }

    public static short readShort(ByteChannel byteChannel) throws IOException {
        return readShort(byteChannel, null);
    }

    public static short readShort(ByteChannel byteChannel, ByteBuffer byteBuffer) throws IOException {
        ByteBuffer byteBufferCheckBuffer = checkBuffer(byteBuffer, 2);
        if (byteChannel.read(byteBufferCheckBuffer) != 2) {
            throw new IOException();
        }
        byteBufferCheckBuffer.clear();
        return byteBufferCheckBuffer.getShort();
    }

    public static int readInt(ByteChannel byteChannel) throws IOException {
        return readInt(byteChannel, null);
    }

    public static int readInt(ByteChannel byteChannel, ByteBuffer byteBuffer) throws IOException {
        ByteBuffer byteBufferCheckBuffer = checkBuffer(byteBuffer, 4);
        if (byteChannel.read(byteBufferCheckBuffer) != 4) {
            throw new IOException();
        }
        byteBufferCheckBuffer.clear();
        return byteBufferCheckBuffer.getInt();
    }

    public static long readLong(ByteChannel byteChannel) throws IOException {
        return readLong(byteChannel, null);
    }

    public static long readLong(ByteChannel byteChannel, ByteBuffer byteBuffer) throws IOException {
        ByteBuffer byteBufferCheckBuffer = checkBuffer(byteBuffer, 8);
        if (byteChannel.read(byteBufferCheckBuffer) != 8) {
            throw new IOException();
        }
        byteBufferCheckBuffer.clear();
        return byteBufferCheckBuffer.getLong();
    }

    public static float readFloat(ByteChannel byteChannel) throws IOException {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        if (byteChannel.read(byteBufferAllocate) != 4) {
            throw new IOException();
        }
        byteBufferAllocate.clear();
        return byteBufferAllocate.getFloat();
    }

    public static float readFloat(ByteChannel byteChannel, ByteBuffer byteBuffer) throws IOException {
        ByteBuffer byteBufferCheckBuffer = checkBuffer(byteBuffer, 4);
        if (byteChannel.read(byteBufferCheckBuffer) != 4) {
            throw new IOException();
        }
        byteBufferCheckBuffer.clear();
        return byteBufferCheckBuffer.getFloat();
    }

    public static double readDouble(ByteChannel byteChannel) throws IOException {
        return readDouble(byteChannel, null);
    }

    public static double readDouble(ByteChannel byteChannel, ByteBuffer byteBuffer) throws IOException {
        ByteBuffer byteBufferCheckBuffer = checkBuffer(byteBuffer, 8);
        if (byteChannel.read(byteBufferCheckBuffer) != 8) {
            throw new IOException();
        }
        byteBufferCheckBuffer.clear();
        return byteBufferCheckBuffer.getDouble();
    }

    public static String readString(ByteChannel byteChannel) throws IOException {
        int i = readInt(byteChannel);
        byte[] bArr = new byte[i];
        if (byteChannel.read(ByteBuffer.wrap(bArr)) != i) {
            throw new IOException();
        }
        return new String(bArr, UTF8);
    }

    public static boolean[] readBooleanArray(ByteChannel byteChannel) throws IOException {
        int i = readInt(byteChannel);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(i);
        if (byteChannel.read(byteBufferAllocate) != i) {
            throw new IOException();
        }
        byteBufferAllocate.clear();
        boolean[] zArr = new boolean[i];
        for (int i2 = 0; i2 < i; i2++) {
            zArr[i2] = byteBufferAllocate.get() != 0;
        }
        return zArr;
    }

    public static byte[] readByteArray(ByteChannel byteChannel) throws IOException {
        int i = readInt(byteChannel);
        byte[] bArr = new byte[i];
        if (byteChannel.read(ByteBuffer.wrap(bArr)) == i) {
            return bArr;
        }
        throw new IOException();
    }

    public static char[] readCharArray(ByteChannel byteChannel) throws IOException {
        int i = readInt(byteChannel);
        int i2 = i * 2;
        ByteBuffer byteBufferOrder = ByteBuffer.allocate(i2).order(ByteOrder.BIG_ENDIAN);
        if (byteChannel.read(byteBufferOrder) != i2) {
            throw new IOException();
        }
        byteBufferOrder.clear();
        CharBuffer charBufferAsCharBuffer = byteBufferOrder.asCharBuffer();
        if (charBufferAsCharBuffer.hasArray()) {
            return charBufferAsCharBuffer.array();
        }
        char[] cArr = new char[i];
        charBufferAsCharBuffer.get(cArr);
        return cArr;
    }

    public static short[] readShortArray(ByteChannel byteChannel) throws IOException {
        int i = readInt(byteChannel);
        int i2 = i * 2;
        ByteBuffer byteBufferOrder = ByteBuffer.allocate(i2).order(ByteOrder.BIG_ENDIAN);
        if (byteChannel.read(byteBufferOrder) != i2) {
            throw new IOException();
        }
        byteBufferOrder.clear();
        ShortBuffer shortBufferAsShortBuffer = byteBufferOrder.asShortBuffer();
        if (shortBufferAsShortBuffer.hasArray()) {
            return shortBufferAsShortBuffer.array();
        }
        short[] sArr = new short[i];
        shortBufferAsShortBuffer.get(sArr);
        return sArr;
    }

    public static int[] readIntArray(ByteChannel byteChannel) throws IOException {
        int i = readInt(byteChannel);
        int i2 = i * 4;
        ByteBuffer byteBufferOrder = ByteBuffer.allocate(i2).order(ByteOrder.BIG_ENDIAN);
        if (byteChannel.read(byteBufferOrder) != i2) {
            throw new IOException();
        }
        byteBufferOrder.clear();
        IntBuffer intBufferAsIntBuffer = byteBufferOrder.asIntBuffer();
        if (intBufferAsIntBuffer.hasArray()) {
            return intBufferAsIntBuffer.array();
        }
        int[] iArr = new int[i];
        intBufferAsIntBuffer.get(iArr);
        return iArr;
    }

    public static long[] readLongArray(ByteChannel byteChannel) throws IOException {
        int i = readInt(byteChannel);
        int i2 = i * 8;
        ByteBuffer byteBufferOrder = ByteBuffer.allocate(i2).order(ByteOrder.BIG_ENDIAN);
        if (byteChannel.read(byteBufferOrder) != i2) {
            throw new IOException();
        }
        byteBufferOrder.clear();
        LongBuffer longBufferAsLongBuffer = byteBufferOrder.asLongBuffer();
        if (longBufferAsLongBuffer.hasArray()) {
            return longBufferAsLongBuffer.array();
        }
        long[] jArr = new long[i];
        longBufferAsLongBuffer.get(jArr);
        return jArr;
    }

    public static float[] readFloatArray(ByteChannel byteChannel) throws IOException {
        int i = readInt(byteChannel);
        int i2 = i * 4;
        ByteBuffer byteBufferOrder = ByteBuffer.allocate(i2).order(ByteOrder.BIG_ENDIAN);
        if (byteChannel.read(byteBufferOrder) != i2) {
            throw new IOException();
        }
        byteBufferOrder.clear();
        FloatBuffer floatBufferAsFloatBuffer = byteBufferOrder.asFloatBuffer();
        if (floatBufferAsFloatBuffer.hasArray()) {
            return floatBufferAsFloatBuffer.array();
        }
        float[] fArr = new float[i];
        floatBufferAsFloatBuffer.get(fArr);
        return fArr;
    }

    public static double[] readDoubleArray(ByteChannel byteChannel) throws IOException {
        int i = readInt(byteChannel);
        int i2 = i * 8;
        ByteBuffer byteBufferOrder = ByteBuffer.allocate(i2).order(ByteOrder.BIG_ENDIAN);
        if (byteChannel.read(byteBufferOrder) != i2) {
            throw new IOException();
        }
        byteBufferOrder.clear();
        DoubleBuffer doubleBufferAsDoubleBuffer = byteBufferOrder.asDoubleBuffer();
        if (doubleBufferAsDoubleBuffer.hasArray()) {
            return doubleBufferAsDoubleBuffer.array();
        }
        double[] dArr = new double[i];
        doubleBufferAsDoubleBuffer.get(dArr);
        return dArr;
    }

    public static ByteBuffer readByteBuffer(ByteChannel byteChannel) throws IOException {
        return readByteBuffer(byteChannel, null, true);
    }

    public static ByteBuffer readByteBuffer(ByteChannel byteChannel, ByteBuffer byteBuffer) throws IOException {
        return readByteBuffer(byteChannel, byteBuffer, false);
    }

    public static ByteBuffer readByteBuffer(ByteChannel byteChannel, ByteBuffer byteBuffer, boolean z) throws IOException {
        int i = readInt(byteChannel);
        int iPosition = byteBuffer != null ? byteBuffer.position() : 0;
        if (byteBuffer == null || byteBuffer.remaining() < i) {
            if (!z) {
                throw new IOException();
            }
            if (byteBuffer == null) {
                byteBuffer = ByteBuffer.allocateDirect(i);
            } else {
                ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(byteBuffer.limit() + i);
                byteBuffer.flip();
                byteBufferAllocateDirect.put(byteBuffer);
                byteBuffer = byteBufferAllocateDirect;
            }
        }
        int i2 = iPosition + i;
        byteBuffer.limit(i2);
        if (byteChannel.read(byteBuffer) != i) {
            throw new IOException();
        }
        byteBuffer.position(iPosition);
        byteBuffer.limit(i2);
        return byteBuffer;
    }

    public static void write(ByteChannel byteChannel, boolean z) throws IOException {
        write(byteChannel, z, (ByteBuffer) null);
    }

    public static void write(ByteChannel byteChannel, boolean z, ByteBuffer byteBuffer) throws IOException {
        ByteBuffer byteBufferCheckBuffer = checkBuffer(byteBuffer, 1);
        byteBufferCheckBuffer.put(z ? (byte) 1 : (byte) 0);
        byteBufferCheckBuffer.flip();
        byteChannel.write(byteBufferCheckBuffer);
    }

    public static void write(ByteChannel byteChannel, byte b) throws IOException {
        write(byteChannel, b, (ByteBuffer) null);
    }

    public static void write(ByteChannel byteChannel, byte b, ByteBuffer byteBuffer) throws IOException {
        ByteBuffer byteBufferCheckBuffer = checkBuffer(byteBuffer, 1);
        byteBufferCheckBuffer.put(b);
        byteBufferCheckBuffer.flip();
        byteChannel.write(byteBufferCheckBuffer);
    }

    public static void write(ByteChannel byteChannel, char c) throws IOException {
        write(byteChannel, c, (ByteBuffer) null);
    }

    public static void write(ByteChannel byteChannel, char c, ByteBuffer byteBuffer) throws IOException {
        ByteBuffer byteBufferCheckBuffer = checkBuffer(byteBuffer, 2);
        byteBufferCheckBuffer.putChar(c);
        byteBufferCheckBuffer.flip();
        byteChannel.write(byteBufferCheckBuffer);
    }

    public static void write(ByteChannel byteChannel, short s) throws IOException {
        write(byteChannel, s, (ByteBuffer) null);
    }

    public static void write(ByteChannel byteChannel, short s, ByteBuffer byteBuffer) throws IOException {
        ByteBuffer byteBufferCheckBuffer = checkBuffer(byteBuffer, 2);
        byteBufferCheckBuffer.putShort(s);
        byteBufferCheckBuffer.flip();
        byteChannel.write(byteBufferCheckBuffer);
    }

    public static void write(ByteChannel byteChannel, int i) throws IOException {
        write(byteChannel, i, (ByteBuffer) null);
    }

    public static void write(ByteChannel byteChannel, int i, ByteBuffer byteBuffer) throws IOException {
        ByteBuffer byteBufferCheckBuffer = checkBuffer(byteBuffer, 4);
        byteBufferCheckBuffer.putInt(i);
        byteBufferCheckBuffer.flip();
        byteChannel.write(byteBufferCheckBuffer);
    }

    public static void write(ByteChannel byteChannel, long j) throws IOException {
        write(byteChannel, j, (ByteBuffer) null);
    }

    public static void write(ByteChannel byteChannel, long j, ByteBuffer byteBuffer) throws IOException {
        ByteBuffer byteBufferCheckBuffer = checkBuffer(byteBuffer, 8);
        byteBufferCheckBuffer.putLong(j);
        byteBufferCheckBuffer.flip();
        byteChannel.write(byteBufferCheckBuffer);
    }

    public static void write(ByteChannel byteChannel, float f) throws IOException {
        write(byteChannel, f, (ByteBuffer) null);
    }

    public static void write(ByteChannel byteChannel, float f, ByteBuffer byteBuffer) throws IOException {
        ByteBuffer byteBufferCheckBuffer = checkBuffer(byteBuffer, 4);
        byteBufferCheckBuffer.putFloat(f);
        byteBufferCheckBuffer.flip();
        byteChannel.write(byteBufferCheckBuffer);
    }

    public static void write(ByteChannel byteChannel, double d) throws IOException {
        write(byteChannel, d, (ByteBuffer) null);
    }

    public static void write(ByteChannel byteChannel, double d, ByteBuffer byteBuffer) throws IOException {
        ByteBuffer byteBufferCheckBuffer = checkBuffer(byteBuffer, 8);
        byteBufferCheckBuffer.putDouble(d);
        byteBufferCheckBuffer.flip();
        byteChannel.write(byteBufferCheckBuffer);
    }

    public static void write(ByteChannel byteChannel, String str) throws IOException {
        byte[] bytes = str.getBytes(UTF8);
        write(byteChannel, bytes.length);
        byteChannel.write(ByteBuffer.wrap(bytes));
    }

    public static void write(ByteChannel byteChannel, boolean[] zArr) throws IOException {
        write(byteChannel, zArr, (ByteBuffer) null);
    }

    public static void write(ByteChannel byteChannel, boolean[] zArr, ByteBuffer byteBuffer) throws IOException {
        int length = zArr.length;
        write(byteChannel, length, byteBuffer);
        ByteBuffer byteBufferCheckBuffer = checkBuffer(byteBuffer, length);
        for (boolean z : zArr) {
            byteBufferCheckBuffer.put(z ? (byte) 1 : (byte) 0);
        }
        byteBufferCheckBuffer.flip();
        byteChannel.write(byteBufferCheckBuffer);
    }

    public static void write(ByteChannel byteChannel, byte[] bArr) throws IOException {
        write(byteChannel, bArr.length);
        byteChannel.write(ByteBuffer.wrap(bArr));
    }

    public static void write(ByteChannel byteChannel, char[] cArr) throws IOException {
        write(byteChannel, cArr, (ByteBuffer) null);
    }

    public static void write(ByteChannel byteChannel, char[] cArr, ByteBuffer byteBuffer) throws IOException {
        int length = cArr.length;
        write(byteChannel, length, byteBuffer);
        ByteBuffer byteBufferCheckBuffer = checkBuffer(byteBuffer, length * 2);
        for (char c : cArr) {
            byteBufferCheckBuffer.putChar(c);
        }
        byteBufferCheckBuffer.flip();
        byteChannel.write(byteBufferCheckBuffer);
    }

    public static void write(ByteChannel byteChannel, short[] sArr) throws IOException {
        write(byteChannel, sArr, (ByteBuffer) null);
    }

    public static void write(ByteChannel byteChannel, short[] sArr, ByteBuffer byteBuffer) throws IOException {
        int length = sArr.length;
        write(byteChannel, length, byteBuffer);
        ByteBuffer byteBufferCheckBuffer = checkBuffer(byteBuffer, length * 2);
        for (short s : sArr) {
            byteBufferCheckBuffer.putShort(s);
        }
        byteBufferCheckBuffer.flip();
        byteChannel.write(byteBufferCheckBuffer);
    }

    public static void write(ByteChannel byteChannel, int[] iArr) throws IOException {
        write(byteChannel, iArr, (ByteBuffer) null);
    }

    public static void write(ByteChannel byteChannel, int[] iArr, ByteBuffer byteBuffer) throws IOException {
        int length = iArr.length;
        write(byteChannel, length, byteBuffer);
        ByteBuffer byteBufferCheckBuffer = checkBuffer(byteBuffer, length * 4);
        for (int i : iArr) {
            byteBufferCheckBuffer.putInt(i);
        }
        byteBufferCheckBuffer.flip();
        byteChannel.write(byteBufferCheckBuffer);
    }

    public static void write(ByteChannel byteChannel, long[] jArr) throws IOException {
        write(byteChannel, jArr, (ByteBuffer) null);
    }

    public static void write(ByteChannel byteChannel, long[] jArr, ByteBuffer byteBuffer) throws IOException {
        int length = jArr.length;
        write(byteChannel, length, byteBuffer);
        ByteBuffer byteBufferCheckBuffer = checkBuffer(byteBuffer, length * 8);
        for (long j : jArr) {
            byteBufferCheckBuffer.putLong(j);
        }
        byteBufferCheckBuffer.flip();
        byteChannel.write(byteBufferCheckBuffer);
    }

    public static void write(ByteChannel byteChannel, float[] fArr) throws IOException {
        write(byteChannel, fArr, (ByteBuffer) null);
    }

    public static void write(ByteChannel byteChannel, float[] fArr, ByteBuffer byteBuffer) throws IOException {
        int length = fArr.length;
        write(byteChannel, length, byteBuffer);
        ByteBuffer byteBufferCheckBuffer = checkBuffer(byteBuffer, length * 4);
        for (float f : fArr) {
            byteBufferCheckBuffer.putFloat(f);
        }
        byteBufferCheckBuffer.flip();
        byteChannel.write(byteBufferCheckBuffer);
    }

    public static void write(ByteChannel byteChannel, double[] dArr) throws IOException {
        write(byteChannel, dArr, (ByteBuffer) null);
    }

    public static void write(ByteChannel byteChannel, double[] dArr, ByteBuffer byteBuffer) throws IOException {
        int length = dArr.length;
        write(byteChannel, length, byteBuffer);
        ByteBuffer byteBufferCheckBuffer = checkBuffer(byteBuffer, length * 8);
        for (double d : dArr) {
            byteBufferCheckBuffer.putDouble(d);
        }
        byteBufferCheckBuffer.flip();
        byteChannel.write(byteBufferCheckBuffer);
    }

    public static void write(ByteChannel byteChannel, ByteBuffer byteBuffer) throws IOException {
        write(byteChannel, byteBuffer.remaining());
        byteChannel.write(byteBuffer);
    }

    private static ByteBuffer checkBuffer(ByteBuffer byteBuffer, int i) {
        if (byteBuffer == null || byteBuffer.capacity() < i) {
            byteBuffer = ByteBuffer.allocateDirect(i);
        }
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
        byteBuffer.clear();
        byteBuffer.limit(i);
        return byteBuffer;
    }
}
