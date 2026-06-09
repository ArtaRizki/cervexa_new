package com.tencent.bugly.proguard;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: com.tencent.bugly.proguard.j */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public final class C2007j {

    /* JADX INFO: renamed from: a */
    private ByteBuffer f2815a;

    /* JADX INFO: renamed from: b */
    private String f2816b;

    public C2007j(int i) {
        this.f2816b = "GBK";
        this.f2815a = ByteBuffer.allocate(i);
    }

    public C2007j() {
        this(128);
    }

    /* JADX INFO: renamed from: a */
    public final ByteBuffer m1767a() {
        return this.f2815a;
    }

    /* JADX INFO: renamed from: b */
    public final byte[] m1779b() {
        byte[] bArr = new byte[this.f2815a.position()];
        System.arraycopy(this.f2815a.array(), 0, bArr, 0, this.f2815a.position());
        return bArr;
    }

    /* JADX INFO: renamed from: a */
    private void m1764a(int i) {
        if (this.f2815a.remaining() < i) {
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate((this.f2815a.capacity() + i) << 1);
            byteBufferAllocate.put(this.f2815a.array(), 0, this.f2815a.position());
            this.f2815a = byteBufferAllocate;
        }
    }

    /* JADX INFO: renamed from: b */
    private void m1765b(byte b, int i) {
        if (i < 15) {
            this.f2815a.put((byte) (b | (i << 4)));
        } else if (i < 256) {
            this.f2815a.put((byte) (b | 240));
            this.f2815a.put((byte) i);
        } else {
            throw new C1999b("tag is too large: " + i);
        }
    }

    /* JADX INFO: renamed from: a */
    public final void m1777a(boolean z, int i) {
        m1768a(z ? (byte) 1 : (byte) 0, i);
    }

    /* JADX INFO: renamed from: a */
    public final void m1768a(byte b, int i) {
        m1764a(3);
        if (b == 0) {
            m1765b((byte) 12, i);
        } else {
            m1765b((byte) 0, i);
            this.f2815a.put(b);
        }
    }

    /* JADX INFO: renamed from: a */
    public final void m1776a(short s, int i) {
        m1764a(4);
        if (s >= -128 && s <= 127) {
            m1768a((byte) s, i);
        } else {
            m1765b((byte) 1, i);
            this.f2815a.putShort(s);
        }
    }

    /* JADX INFO: renamed from: a */
    public final void m1769a(int i, int i2) {
        m1764a(6);
        if (i >= -32768 && i <= 32767) {
            m1776a((short) i, i2);
        } else {
            m1765b((byte) 2, i2);
            this.f2815a.putInt(i);
        }
    }

    /* JADX INFO: renamed from: a */
    public final void m1770a(long j, int i) {
        m1764a(10);
        if (j >= -2147483648L && j <= 2147483647L) {
            m1769a((int) j, i);
        } else {
            m1765b((byte) 3, i);
            this.f2815a.putLong(j);
        }
    }

    /* JADX INFO: renamed from: a */
    public final void m1773a(String str, int i) {
        byte[] bytes;
        try {
            bytes = str.getBytes(this.f2816b);
        } catch (UnsupportedEncodingException unused) {
            bytes = str.getBytes();
        }
        m1764a(bytes.length + 10);
        if (bytes.length > 255) {
            m1765b((byte) 7, i);
            this.f2815a.putInt(bytes.length);
            this.f2815a.put(bytes);
        } else {
            m1765b((byte) 6, i);
            this.f2815a.put((byte) bytes.length);
            this.f2815a.put(bytes);
        }
    }

    /* JADX INFO: renamed from: a */
    public final <K, V> void m1775a(Map<K, V> map, int i) {
        m1764a(8);
        m1765b((byte) 8, i);
        m1769a(map == null ? 0 : map.size(), 0);
        if (map != null) {
            for (Map.Entry<K, V> entry : map.entrySet()) {
                m1772a(entry.getKey(), 0);
                m1772a(entry.getValue(), 1);
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public final void m1778a(byte[] bArr, int i) {
        m1764a(bArr.length + 8);
        m1765b((byte) 13, i);
        m1765b((byte) 0, 0);
        m1769a(bArr.length, 0);
        this.f2815a.put(bArr);
    }

    /* JADX INFO: renamed from: a */
    public final <T> void m1774a(Collection<T> collection, int i) {
        m1764a(8);
        m1765b((byte) 9, i);
        m1769a(collection == null ? 0 : collection.size(), 0);
        if (collection != null) {
            Iterator<T> it = collection.iterator();
            while (it.hasNext()) {
                m1772a(it.next(), 0);
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public final void m1771a(AbstractC2008k abstractC2008k, int i) {
        m1764a(2);
        m1765b((byte) 10, i);
        abstractC2008k.mo1717a(this);
        m1764a(2);
        m1765b((byte) 11, 0);
    }

    /* JADX INFO: renamed from: a */
    public final void m1772a(Object obj, int i) {
        if (obj instanceof Byte) {
            m1768a(((Byte) obj).byteValue(), i);
            return;
        }
        if (obj instanceof Boolean) {
            m1768a(((Boolean) obj).booleanValue() ? (byte) 1 : (byte) 0, i);
            return;
        }
        if (obj instanceof Short) {
            m1776a(((Short) obj).shortValue(), i);
            return;
        }
        if (obj instanceof Integer) {
            m1769a(((Integer) obj).intValue(), i);
            return;
        }
        if (obj instanceof Long) {
            m1770a(((Long) obj).longValue(), i);
            return;
        }
        if (obj instanceof Float) {
            float fFloatValue = ((Float) obj).floatValue();
            m1764a(6);
            m1765b((byte) 4, i);
            this.f2815a.putFloat(fFloatValue);
            return;
        }
        if (obj instanceof Double) {
            double dDoubleValue = ((Double) obj).doubleValue();
            m1764a(10);
            m1765b((byte) 5, i);
            this.f2815a.putDouble(dDoubleValue);
            return;
        }
        if (obj instanceof String) {
            m1773a((String) obj, i);
            return;
        }
        if (obj instanceof Map) {
            m1775a((Map) obj, i);
            return;
        }
        if (obj instanceof List) {
            m1774a((Collection) obj, i);
            return;
        }
        if (obj instanceof AbstractC2008k) {
            m1764a(2);
            m1765b((byte) 10, i);
            ((AbstractC2008k) obj).mo1717a(this);
            m1764a(2);
            m1765b((byte) 11, 0);
            return;
        }
        if (obj instanceof byte[]) {
            m1778a((byte[]) obj, i);
            return;
        }
        if (obj instanceof boolean[]) {
            boolean[] zArr = (boolean[]) obj;
            m1764a(8);
            m1765b((byte) 9, i);
            m1769a(zArr.length, 0);
            for (boolean z : zArr) {
                m1768a(z ? (byte) 1 : (byte) 0, 0);
            }
            return;
        }
        if (obj instanceof short[]) {
            short[] sArr = (short[]) obj;
            m1764a(8);
            m1765b((byte) 9, i);
            m1769a(sArr.length, 0);
            for (short s : sArr) {
                m1776a(s, 0);
            }
            return;
        }
        if (obj instanceof int[]) {
            int[] iArr = (int[]) obj;
            m1764a(8);
            m1765b((byte) 9, i);
            m1769a(iArr.length, 0);
            for (int i2 : iArr) {
                m1769a(i2, 0);
            }
            return;
        }
        if (obj instanceof long[]) {
            long[] jArr = (long[]) obj;
            m1764a(8);
            m1765b((byte) 9, i);
            m1769a(jArr.length, 0);
            for (long j : jArr) {
                m1770a(j, 0);
            }
            return;
        }
        if (obj instanceof float[]) {
            float[] fArr = (float[]) obj;
            m1764a(8);
            m1765b((byte) 9, i);
            m1769a(fArr.length, 0);
            for (float f : fArr) {
                m1764a(6);
                m1765b((byte) 4, 0);
                this.f2815a.putFloat(f);
            }
            return;
        }
        if (obj instanceof double[]) {
            double[] dArr = (double[]) obj;
            m1764a(8);
            m1765b((byte) 9, i);
            m1769a(dArr.length, 0);
            for (double d : dArr) {
                m1764a(10);
                m1765b((byte) 5, 0);
                this.f2815a.putDouble(d);
            }
            return;
        }
        if (obj.getClass().isArray()) {
            Object[] objArr = (Object[]) obj;
            m1764a(8);
            m1765b((byte) 9, i);
            m1769a(objArr.length, 0);
            for (Object obj2 : objArr) {
                m1772a(obj2, 0);
            }
            return;
        }
        if (obj instanceof Collection) {
            m1774a((Collection) obj, i);
        } else {
            throw new C1999b("write object error: unsupport type. " + obj.getClass());
        }
    }

    /* JADX INFO: renamed from: a */
    public final int m1766a(String str) {
        this.f2816b = str;
        return 0;
    }
}
