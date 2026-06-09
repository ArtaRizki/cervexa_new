package com.tencent.bugly.proguard;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: com.tencent.bugly.proguard.i */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public final class C2006i {

    /* JADX INFO: renamed from: a */
    private ByteBuffer f2811a;

    /* JADX INFO: renamed from: b */
    private String f2812b = "GBK";

    /* JADX INFO: renamed from: com.tencent.bugly.proguard.i$a */
    /* JADX INFO: compiled from: BUGLY */
    public static class a {

        /* JADX INFO: renamed from: a */
        public byte f2813a;

        /* JADX INFO: renamed from: b */
        public int f2814b;
    }

    public C2006i() {
    }

    public C2006i(byte[] bArr) {
        this.f2811a = ByteBuffer.wrap(bArr);
    }

    public C2006i(byte[] bArr, int i) {
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        this.f2811a = byteBufferWrap;
        byteBufferWrap.position(4);
    }

    /* JADX INFO: renamed from: a */
    public final void m1760a(byte[] bArr) {
        ByteBuffer byteBuffer = this.f2811a;
        if (byteBuffer != null) {
            byteBuffer.clear();
        }
        this.f2811a = ByteBuffer.wrap(bArr);
    }

    /* JADX INFO: renamed from: a */
    private static int m1739a(a aVar, ByteBuffer byteBuffer) {
        byte b = byteBuffer.get();
        aVar.f2813a = (byte) (b & 15);
        aVar.f2814b = (b & 240) >> 4;
        if (aVar.f2814b != 15) {
            return 1;
        }
        aVar.f2814b = byteBuffer.get();
        return 2;
    }

    /* JADX INFO: renamed from: a */
    private boolean m1743a(int i) {
        a aVar;
        try {
            aVar = new a();
            while (true) {
                int iM1739a = m1739a(aVar, this.f2811a.duplicate());
                if (i <= aVar.f2814b || aVar.f2813a == 11) {
                    break;
                }
                this.f2811a.position(this.f2811a.position() + iM1739a);
                m1742a(aVar.f2813a);
            }
        } catch (C2004g | BufferUnderflowException unused) {
        }
        return i == aVar.f2814b;
    }

    /* JADX INFO: renamed from: a */
    private void m1741a() {
        a aVar = new a();
        do {
            m1739a(aVar, this.f2811a);
            m1742a(aVar.f2813a);
        } while (aVar.f2813a != 11);
    }

    /* JADX INFO: renamed from: a */
    private void m1742a(byte b) {
        int i = 0;
        switch (b) {
            case 0:
                ByteBuffer byteBuffer = this.f2811a;
                byteBuffer.position(byteBuffer.position() + 1);
                return;
            case 1:
                ByteBuffer byteBuffer2 = this.f2811a;
                byteBuffer2.position(byteBuffer2.position() + 2);
                return;
            case 2:
                ByteBuffer byteBuffer3 = this.f2811a;
                byteBuffer3.position(byteBuffer3.position() + 4);
                return;
            case 3:
                ByteBuffer byteBuffer4 = this.f2811a;
                byteBuffer4.position(byteBuffer4.position() + 8);
                return;
            case 4:
                ByteBuffer byteBuffer5 = this.f2811a;
                byteBuffer5.position(byteBuffer5.position() + 4);
                return;
            case 5:
                ByteBuffer byteBuffer6 = this.f2811a;
                byteBuffer6.position(byteBuffer6.position() + 8);
                return;
            case 6:
                int i2 = this.f2811a.get();
                if (i2 < 0) {
                    i2 += 256;
                }
                ByteBuffer byteBuffer7 = this.f2811a;
                byteBuffer7.position(byteBuffer7.position() + i2);
                return;
            case 7:
                int i3 = this.f2811a.getInt();
                ByteBuffer byteBuffer8 = this.f2811a;
                byteBuffer8.position(byteBuffer8.position() + i3);
                return;
            case 8:
                int iM1753a = m1753a(0, 0, true);
                while (i < (iM1753a << 1)) {
                    a aVar = new a();
                    m1739a(aVar, this.f2811a);
                    m1742a(aVar.f2813a);
                    i++;
                }
                return;
            case 9:
                int iM1753a2 = m1753a(0, 0, true);
                while (i < iM1753a2) {
                    a aVar2 = new a();
                    m1739a(aVar2, this.f2811a);
                    m1742a(aVar2.f2813a);
                    i++;
                }
                return;
            case 10:
                m1741a();
                return;
            case 11:
            case 12:
                return;
            case 13:
                a aVar3 = new a();
                m1739a(aVar3, this.f2811a);
                if (aVar3.f2813a != 0) {
                    throw new C2004g("skipField with invalid type, type value: " + ((int) b) + ", " + ((int) aVar3.f2813a));
                }
                int iM1753a3 = m1753a(0, 0, true);
                ByteBuffer byteBuffer9 = this.f2811a;
                byteBuffer9.position(byteBuffer9.position() + iM1753a3);
                return;
            default:
                throw new C2004g("invalid type.");
        }
    }

    /* JADX INFO: renamed from: a */
    public final boolean m1761a(int i, boolean z) {
        return m1752a((byte) 0, i, z) != 0;
    }

    /* JADX INFO: renamed from: a */
    public final byte m1752a(byte b, int i, boolean z) {
        if (!m1743a(i)) {
            if (z) {
                throw new C2004g("require field not exist.");
            }
            return b;
        }
        a aVar = new a();
        m1739a(aVar, this.f2811a);
        byte b2 = aVar.f2813a;
        if (b2 == 0) {
            return this.f2811a.get();
        }
        if (b2 == 12) {
            return (byte) 0;
        }
        throw new C2004g("type mismatch.");
    }

    /* JADX INFO: renamed from: a */
    public final short m1759a(short s, int i, boolean z) {
        if (!m1743a(i)) {
            if (z) {
                throw new C2004g("require field not exist.");
            }
            return s;
        }
        a aVar = new a();
        m1739a(aVar, this.f2811a);
        byte b = aVar.f2813a;
        if (b == 0) {
            return this.f2811a.get();
        }
        if (b == 1) {
            return this.f2811a.getShort();
        }
        if (b == 12) {
            return (short) 0;
        }
        throw new C2004g("type mismatch.");
    }

    /* JADX INFO: renamed from: a */
    public final int m1753a(int i, int i2, boolean z) {
        if (!m1743a(i2)) {
            if (z) {
                throw new C2004g("require field not exist.");
            }
            return i;
        }
        a aVar = new a();
        m1739a(aVar, this.f2811a);
        byte b = aVar.f2813a;
        if (b == 0) {
            return this.f2811a.get();
        }
        if (b == 1) {
            return this.f2811a.getShort();
        }
        if (b == 2) {
            return this.f2811a.getInt();
        }
        if (b == 12) {
            return 0;
        }
        throw new C2004g("type mismatch.");
    }

    /* JADX INFO: renamed from: a */
    public final long m1755a(long j, int i, boolean z) {
        int i2;
        if (!m1743a(i)) {
            if (z) {
                throw new C2004g("require field not exist.");
            }
            return j;
        }
        a aVar = new a();
        m1739a(aVar, this.f2811a);
        byte b = aVar.f2813a;
        if (b == 0) {
            i2 = this.f2811a.get();
        } else if (b == 1) {
            i2 = this.f2811a.getShort();
        } else {
            if (b != 2) {
                if (b == 3) {
                    return this.f2811a.getLong();
                }
                if (b == 12) {
                    return 0L;
                }
                throw new C2004g("type mismatch.");
            }
            i2 = this.f2811a.getInt();
        }
        return i2;
    }

    /* JADX INFO: renamed from: a */
    private float m1738a(float f, int i, boolean z) {
        if (!m1743a(i)) {
            if (z) {
                throw new C2004g("require field not exist.");
            }
            return f;
        }
        a aVar = new a();
        m1739a(aVar, this.f2811a);
        byte b = aVar.f2813a;
        if (b == 4) {
            return this.f2811a.getFloat();
        }
        if (b == 12) {
            return 0.0f;
        }
        throw new C2004g("type mismatch.");
    }

    /* JADX INFO: renamed from: a */
    private double m1737a(double d, int i, boolean z) {
        if (!m1743a(i)) {
            if (z) {
                throw new C2004g("require field not exist.");
            }
            return d;
        }
        a aVar = new a();
        m1739a(aVar, this.f2811a);
        byte b = aVar.f2813a;
        if (b == 4) {
            return this.f2811a.getFloat();
        }
        if (b == 5) {
            return this.f2811a.getDouble();
        }
        if (b == 12) {
            return 0.0d;
        }
        throw new C2004g("type mismatch.");
    }

    /* JADX INFO: renamed from: b */
    public final String m1762b(int i, boolean z) {
        if (!m1743a(i)) {
            if (z) {
                throw new C2004g("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        m1739a(aVar, this.f2811a);
        byte b = aVar.f2813a;
        if (b == 6) {
            int i2 = this.f2811a.get();
            if (i2 < 0) {
                i2 += 256;
            }
            byte[] bArr = new byte[i2];
            this.f2811a.get(bArr);
            try {
                return new String(bArr, this.f2812b);
            } catch (UnsupportedEncodingException unused) {
                return new String(bArr);
            }
        }
        if (b == 7) {
            int i3 = this.f2811a.getInt();
            if (i3 > 104857600 || i3 < 0) {
                throw new C2004g("String too long: " + i3);
            }
            byte[] bArr2 = new byte[i3];
            this.f2811a.get(bArr2);
            try {
                return new String(bArr2, this.f2812b);
            } catch (UnsupportedEncodingException unused2) {
                return new String(bArr2);
            }
        }
        throw new C2004g("type mismatch.");
    }

    /* JADX INFO: renamed from: a */
    public final <K, V> HashMap<K, V> m1758a(Map<K, V> map, int i, boolean z) {
        return (HashMap) m1740a(new HashMap(), map, i, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: a */
    private <K, V> Map<K, V> m1740a(Map<K, V> map, Map<K, V> map2, int i, boolean z) {
        if (map2 == null || map2.isEmpty()) {
            return new HashMap();
        }
        Map.Entry<K, V> next = map2.entrySet().iterator().next();
        K key = next.getKey();
        V value = next.getValue();
        if (m1743a(i)) {
            a aVar = new a();
            m1739a(aVar, this.f2811a);
            if (aVar.f2813a == 8) {
                int iM1753a = m1753a(0, 0, true);
                if (iM1753a < 0) {
                    throw new C2004g("size invalid: " + iM1753a);
                }
                for (int i2 = 0; i2 < iM1753a; i2++) {
                    map.put(m1757a(key, 0, true), m1757a(value, 1, true));
                }
            } else {
                throw new C2004g("type mismatch.");
            }
        } else if (z) {
            throw new C2004g("require field not exist.");
        }
        return map;
    }

    /* JADX INFO: renamed from: d */
    private boolean[] m1746d(int i, boolean z) {
        if (!m1743a(i)) {
            if (z) {
                throw new C2004g("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        m1739a(aVar, this.f2811a);
        if (aVar.f2813a == 9) {
            int iM1753a = m1753a(0, 0, true);
            if (iM1753a < 0) {
                throw new C2004g("size invalid: " + iM1753a);
            }
            boolean[] zArr = new boolean[iM1753a];
            for (int i2 = 0; i2 < iM1753a; i2++) {
                zArr[i2] = m1752a((byte) 0, 0, true) != 0;
            }
            return zArr;
        }
        throw new C2004g("type mismatch.");
    }

    /* JADX INFO: renamed from: c */
    public final byte[] m1763c(int i, boolean z) {
        if (!m1743a(i)) {
            if (z) {
                throw new C2004g("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        m1739a(aVar, this.f2811a);
        byte b = aVar.f2813a;
        if (b == 9) {
            int iM1753a = m1753a(0, 0, true);
            if (iM1753a < 0) {
                throw new C2004g("size invalid: " + iM1753a);
            }
            byte[] bArr = new byte[iM1753a];
            for (int i2 = 0; i2 < iM1753a; i2++) {
                bArr[i2] = m1752a(bArr[0], 0, true);
            }
            return bArr;
        }
        if (b == 13) {
            a aVar2 = new a();
            m1739a(aVar2, this.f2811a);
            if (aVar2.f2813a != 0) {
                throw new C2004g("type mismatch, tag: " + i + ", type: " + ((int) aVar.f2813a) + ", " + ((int) aVar2.f2813a));
            }
            int iM1753a2 = m1753a(0, 0, true);
            if (iM1753a2 < 0) {
                throw new C2004g("invalid size, tag: " + i + ", type: " + ((int) aVar.f2813a) + ", " + ((int) aVar2.f2813a) + ", size: " + iM1753a2);
            }
            byte[] bArr2 = new byte[iM1753a2];
            this.f2811a.get(bArr2);
            return bArr2;
        }
        throw new C2004g("type mismatch.");
    }

    /* JADX INFO: renamed from: e */
    private short[] m1747e(int i, boolean z) {
        if (!m1743a(i)) {
            if (z) {
                throw new C2004g("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        m1739a(aVar, this.f2811a);
        if (aVar.f2813a == 9) {
            int iM1753a = m1753a(0, 0, true);
            if (iM1753a < 0) {
                throw new C2004g("size invalid: " + iM1753a);
            }
            short[] sArr = new short[iM1753a];
            for (int i2 = 0; i2 < iM1753a; i2++) {
                sArr[i2] = m1759a(sArr[0], 0, true);
            }
            return sArr;
        }
        throw new C2004g("type mismatch.");
    }

    /* JADX INFO: renamed from: f */
    private int[] m1748f(int i, boolean z) {
        if (!m1743a(i)) {
            if (z) {
                throw new C2004g("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        m1739a(aVar, this.f2811a);
        if (aVar.f2813a == 9) {
            int iM1753a = m1753a(0, 0, true);
            if (iM1753a < 0) {
                throw new C2004g("size invalid: " + iM1753a);
            }
            int[] iArr = new int[iM1753a];
            for (int i2 = 0; i2 < iM1753a; i2++) {
                iArr[i2] = m1753a(iArr[0], 0, true);
            }
            return iArr;
        }
        throw new C2004g("type mismatch.");
    }

    /* JADX INFO: renamed from: g */
    private long[] m1749g(int i, boolean z) {
        if (!m1743a(i)) {
            if (z) {
                throw new C2004g("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        m1739a(aVar, this.f2811a);
        if (aVar.f2813a == 9) {
            int iM1753a = m1753a(0, 0, true);
            if (iM1753a < 0) {
                throw new C2004g("size invalid: " + iM1753a);
            }
            long[] jArr = new long[iM1753a];
            for (int i2 = 0; i2 < iM1753a; i2++) {
                jArr[i2] = m1755a(jArr[0], 0, true);
            }
            return jArr;
        }
        throw new C2004g("type mismatch.");
    }

    /* JADX INFO: renamed from: h */
    private float[] m1750h(int i, boolean z) {
        if (!m1743a(i)) {
            if (z) {
                throw new C2004g("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        m1739a(aVar, this.f2811a);
        if (aVar.f2813a == 9) {
            int iM1753a = m1753a(0, 0, true);
            if (iM1753a < 0) {
                throw new C2004g("size invalid: " + iM1753a);
            }
            float[] fArr = new float[iM1753a];
            for (int i2 = 0; i2 < iM1753a; i2++) {
                fArr[i2] = m1738a(fArr[0], 0, true);
            }
            return fArr;
        }
        throw new C2004g("type mismatch.");
    }

    /* JADX INFO: renamed from: i */
    private double[] m1751i(int i, boolean z) {
        if (!m1743a(i)) {
            if (z) {
                throw new C2004g("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        m1739a(aVar, this.f2811a);
        if (aVar.f2813a == 9) {
            int iM1753a = m1753a(0, 0, true);
            if (iM1753a < 0) {
                throw new C2004g("size invalid: " + iM1753a);
            }
            double[] dArr = new double[iM1753a];
            for (int i2 = 0; i2 < iM1753a; i2++) {
                dArr[i2] = m1737a(dArr[0], 0, true);
            }
            return dArr;
        }
        throw new C2004g("type mismatch.");
    }

    /* JADX INFO: renamed from: a */
    private <T> T[] m1744a(T[] tArr, int i, boolean z) {
        if (tArr == null || tArr.length == 0) {
            throw new C2004g("unable to get type of key and value.");
        }
        return (T[]) m1745b(tArr[0], i, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: b */
    private <T> T[] m1745b(T t, int i, boolean z) {
        if (!m1743a(i)) {
            if (z) {
                throw new C2004g("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        m1739a(aVar, this.f2811a);
        if (aVar.f2813a == 9) {
            int iM1753a = m1753a(0, 0, true);
            if (iM1753a < 0) {
                throw new C2004g("size invalid: " + iM1753a);
            }
            T[] tArr = (T[]) ((Object[]) Array.newInstance(t.getClass(), iM1753a));
            for (int i2 = 0; i2 < iM1753a; i2++) {
                tArr[i2] = m1757a((Object) t, 0, true);
            }
            return tArr;
        }
        throw new C2004g("type mismatch.");
    }

    /* JADX INFO: renamed from: a */
    public final AbstractC2008k m1756a(AbstractC2008k abstractC2008k, int i, boolean z) {
        if (!m1743a(i)) {
            if (z) {
                throw new C2004g("require field not exist.");
            }
            return null;
        }
        try {
            AbstractC2008k abstractC2008k2 = (AbstractC2008k) abstractC2008k.getClass().newInstance();
            a aVar = new a();
            m1739a(aVar, this.f2811a);
            if (aVar.f2813a != 10) {
                throw new C2004g("type mismatch.");
            }
            abstractC2008k2.mo1716a(this);
            m1741a();
            return abstractC2008k2;
        } catch (Exception e) {
            throw new C2004g(e.getMessage());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: a */
    public final <T> Object m1757a(T t, int i, boolean z) {
        if (t instanceof Byte) {
            return Byte.valueOf(m1752a((byte) 0, i, z));
        }
        if (t instanceof Boolean) {
            return Boolean.valueOf(m1752a((byte) 0, i, z) != 0);
        }
        if (t instanceof Short) {
            return Short.valueOf(m1759a((short) 0, i, z));
        }
        if (t instanceof Integer) {
            return Integer.valueOf(m1753a(0, i, z));
        }
        if (t instanceof Long) {
            return Long.valueOf(m1755a(0L, i, z));
        }
        if (t instanceof Float) {
            return Float.valueOf(m1738a(0.0f, i, z));
        }
        if (t instanceof Double) {
            return Double.valueOf(m1737a(0.0d, i, z));
        }
        if (t instanceof String) {
            return String.valueOf(m1762b(i, z));
        }
        if (t instanceof Map) {
            return (HashMap) m1740a(new HashMap(), (Map) t, i, z);
        }
        if (t instanceof List) {
            List list = (List) t;
            if (list == null || list.isEmpty()) {
                return new ArrayList();
            }
            Object[] objArrM1745b = m1745b(list.get(0), i, z);
            if (objArrM1745b == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (Object obj : objArrM1745b) {
                arrayList.add(obj);
            }
            return arrayList;
        }
        if (t instanceof AbstractC2008k) {
            return m1756a((AbstractC2008k) t, i, z);
        }
        if (t.getClass().isArray()) {
            if ((t instanceof byte[]) || (t instanceof Byte[])) {
                return m1763c(i, z);
            }
            if (t instanceof boolean[]) {
                return m1746d(i, z);
            }
            if (t instanceof short[]) {
                return m1747e(i, z);
            }
            if (t instanceof int[]) {
                return m1748f(i, z);
            }
            if (t instanceof long[]) {
                return m1749g(i, z);
            }
            if (t instanceof float[]) {
                return m1750h(i, z);
            }
            if (t instanceof double[]) {
                return m1751i(i, z);
            }
            return m1744a((Object[]) t, i, z);
        }
        throw new C2004g("read object error: unsupport type.");
    }

    /* JADX INFO: renamed from: a */
    public final int m1754a(String str) {
        this.f2812b = str;
        return 0;
    }
}
