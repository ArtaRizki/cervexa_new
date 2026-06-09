package com.tencent.bugly.proguard;

import com.jieli.stream.p016dv.running2.util.IConstant;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: com.tencent.bugly.proguard.h */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public final class C2005h {

    /* JADX INFO: renamed from: a */
    private StringBuilder f2809a;

    /* JADX INFO: renamed from: b */
    private int f2810b;

    /* JADX INFO: renamed from: a */
    private void m1727a(String str) {
        for (int i = 0; i < this.f2810b; i++) {
            this.f2809a.append('\t');
        }
        if (str != null) {
            StringBuilder sb = this.f2809a;
            sb.append(str);
            sb.append(": ");
        }
    }

    public C2005h(StringBuilder sb, int i) {
        this.f2810b = 0;
        this.f2809a = sb;
        this.f2810b = i;
    }

    /* JADX INFO: renamed from: a */
    public final C2005h m1735a(boolean z, String str) {
        m1727a(str);
        StringBuilder sb = this.f2809a;
        sb.append(z ? 'T' : 'F');
        sb.append('\n');
        return this;
    }

    /* JADX INFO: renamed from: a */
    public final C2005h m1728a(byte b, String str) {
        m1727a(str);
        StringBuilder sb = this.f2809a;
        sb.append((int) b);
        sb.append('\n');
        return this;
    }

    /* JADX INFO: renamed from: a */
    public final C2005h m1734a(short s, String str) {
        m1727a(str);
        StringBuilder sb = this.f2809a;
        sb.append((int) s);
        sb.append('\n');
        return this;
    }

    /* JADX INFO: renamed from: a */
    public final C2005h m1729a(int i, String str) {
        m1727a(str);
        StringBuilder sb = this.f2809a;
        sb.append(i);
        sb.append('\n');
        return this;
    }

    /* JADX INFO: renamed from: a */
    public final C2005h m1730a(long j, String str) {
        m1727a(str);
        StringBuilder sb = this.f2809a;
        sb.append(j);
        sb.append('\n');
        return this;
    }

    /* JADX INFO: renamed from: a */
    public final C2005h m1732a(String str, String str2) {
        m1727a(str2);
        if (str == null) {
            this.f2809a.append("null\n");
        } else {
            StringBuilder sb = this.f2809a;
            sb.append(str);
            sb.append('\n');
        }
        return this;
    }

    /* JADX INFO: renamed from: a */
    public final C2005h m1736a(byte[] bArr, String str) {
        m1727a(str);
        if (bArr == null) {
            this.f2809a.append("null\n");
            return this;
        }
        if (bArr.length == 0) {
            StringBuilder sb = this.f2809a;
            sb.append(bArr.length);
            sb.append(", []\n");
            return this;
        }
        StringBuilder sb2 = this.f2809a;
        sb2.append(bArr.length);
        sb2.append(", [\n");
        C2005h c2005h = new C2005h(this.f2809a, this.f2810b + 1);
        for (byte b : bArr) {
            c2005h.m1727a(null);
            StringBuilder sb3 = c2005h.f2809a;
            sb3.append((int) b);
            sb3.append('\n');
        }
        m1727a(null);
        StringBuilder sb4 = this.f2809a;
        sb4.append(']');
        sb4.append('\n');
        return this;
    }

    /* JADX INFO: renamed from: a */
    public final <K, V> C2005h m1733a(Map<K, V> map, String str) {
        m1727a(str);
        if (map == null) {
            this.f2809a.append("null\n");
            return this;
        }
        if (map.isEmpty()) {
            StringBuilder sb = this.f2809a;
            sb.append(map.size());
            sb.append(", {}\n");
            return this;
        }
        StringBuilder sb2 = this.f2809a;
        sb2.append(map.size());
        sb2.append(", {\n");
        C2005h c2005h = new C2005h(this.f2809a, this.f2810b + 1);
        C2005h c2005h2 = new C2005h(this.f2809a, this.f2810b + 2);
        for (Map.Entry<K, V> entry : map.entrySet()) {
            c2005h.m1727a(null);
            StringBuilder sb3 = c2005h.f2809a;
            sb3.append('(');
            sb3.append('\n');
            c2005h2.m1725a(entry.getKey(), (String) null);
            c2005h2.m1725a(entry.getValue(), (String) null);
            c2005h.m1727a(null);
            StringBuilder sb4 = c2005h.f2809a;
            sb4.append(')');
            sb4.append('\n');
        }
        m1727a(null);
        StringBuilder sb5 = this.f2809a;
        sb5.append('}');
        sb5.append('\n');
        return this;
    }

    /* JADX INFO: renamed from: a */
    private <T> C2005h m1726a(T[] tArr, String str) {
        m1727a(str);
        if (tArr == null) {
            this.f2809a.append("null\n");
            return this;
        }
        if (tArr.length == 0) {
            StringBuilder sb = this.f2809a;
            sb.append(tArr.length);
            sb.append(", []\n");
            return this;
        }
        StringBuilder sb2 = this.f2809a;
        sb2.append(tArr.length);
        sb2.append(", [\n");
        C2005h c2005h = new C2005h(this.f2809a, this.f2810b + 1);
        for (T t : tArr) {
            c2005h.m1725a(t, (String) null);
        }
        m1727a(null);
        StringBuilder sb3 = this.f2809a;
        sb3.append(']');
        sb3.append('\n');
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: a */
    private <T> C2005h m1725a(T t, String str) {
        if (t == 0) {
            this.f2809a.append("null\n");
        } else if (t instanceof Byte) {
            byte bByteValue = ((Byte) t).byteValue();
            m1727a(str);
            StringBuilder sb = this.f2809a;
            sb.append((int) bByteValue);
            sb.append('\n');
        } else if (t instanceof Boolean) {
            boolean zBooleanValue = ((Boolean) t).booleanValue();
            m1727a(str);
            StringBuilder sb2 = this.f2809a;
            sb2.append(zBooleanValue ? 'T' : 'F');
            sb2.append('\n');
        } else if (t instanceof Short) {
            short sShortValue = ((Short) t).shortValue();
            m1727a(str);
            StringBuilder sb3 = this.f2809a;
            sb3.append((int) sShortValue);
            sb3.append('\n');
        } else if (t instanceof Integer) {
            int iIntValue = ((Integer) t).intValue();
            m1727a(str);
            StringBuilder sb4 = this.f2809a;
            sb4.append(iIntValue);
            sb4.append('\n');
        } else if (t instanceof Long) {
            long jLongValue = ((Long) t).longValue();
            m1727a(str);
            StringBuilder sb5 = this.f2809a;
            sb5.append(jLongValue);
            sb5.append('\n');
        } else if (t instanceof Float) {
            float fFloatValue = ((Float) t).floatValue();
            m1727a(str);
            StringBuilder sb6 = this.f2809a;
            sb6.append(fFloatValue);
            sb6.append('\n');
        } else if (t instanceof Double) {
            double dDoubleValue = ((Double) t).doubleValue();
            m1727a(str);
            StringBuilder sb7 = this.f2809a;
            sb7.append(dDoubleValue);
            sb7.append('\n');
        } else if (t instanceof String) {
            m1732a((String) t, str);
        } else if (t instanceof Map) {
            m1733a((Map) t, str);
        } else if (t instanceof List) {
            List list = (List) t;
            if (list == null) {
                m1727a(str);
                this.f2809a.append("null\t");
            } else {
                m1726a(list.toArray(), str);
            }
        } else if (t instanceof AbstractC2008k) {
            m1731a((AbstractC2008k) t, str);
        } else if (t instanceof byte[]) {
            m1736a((byte[]) t, str);
        } else if (t instanceof boolean[]) {
            m1725a((boolean[]) t, str);
        } else {
            int i = 0;
            if (t instanceof short[]) {
                short[] sArr = (short[]) t;
                m1727a(str);
                if (sArr == null) {
                    this.f2809a.append("null\n");
                } else if (sArr.length == 0) {
                    StringBuilder sb8 = this.f2809a;
                    sb8.append(sArr.length);
                    sb8.append(", []\n");
                } else {
                    StringBuilder sb9 = this.f2809a;
                    sb9.append(sArr.length);
                    sb9.append(", [\n");
                    C2005h c2005h = new C2005h(this.f2809a, this.f2810b + 1);
                    int length = sArr.length;
                    while (i < length) {
                        short s = sArr[i];
                        c2005h.m1727a(null);
                        StringBuilder sb10 = c2005h.f2809a;
                        sb10.append((int) s);
                        sb10.append('\n');
                        i++;
                    }
                    m1727a(null);
                    StringBuilder sb11 = this.f2809a;
                    sb11.append(']');
                    sb11.append('\n');
                }
            } else if (t instanceof int[]) {
                int[] iArr = (int[]) t;
                m1727a(str);
                if (iArr == null) {
                    this.f2809a.append("null\n");
                } else if (iArr.length == 0) {
                    StringBuilder sb12 = this.f2809a;
                    sb12.append(iArr.length);
                    sb12.append(", []\n");
                } else {
                    StringBuilder sb13 = this.f2809a;
                    sb13.append(iArr.length);
                    sb13.append(", [\n");
                    C2005h c2005h2 = new C2005h(this.f2809a, this.f2810b + 1);
                    int length2 = iArr.length;
                    while (i < length2) {
                        int i2 = iArr[i];
                        c2005h2.m1727a(null);
                        StringBuilder sb14 = c2005h2.f2809a;
                        sb14.append(i2);
                        sb14.append('\n');
                        i++;
                    }
                    m1727a(null);
                    StringBuilder sb15 = this.f2809a;
                    sb15.append(']');
                    sb15.append('\n');
                }
            } else if (t instanceof long[]) {
                long[] jArr = (long[]) t;
                m1727a(str);
                if (jArr == null) {
                    this.f2809a.append("null\n");
                } else if (jArr.length == 0) {
                    StringBuilder sb16 = this.f2809a;
                    sb16.append(jArr.length);
                    sb16.append(", []\n");
                } else {
                    StringBuilder sb17 = this.f2809a;
                    sb17.append(jArr.length);
                    sb17.append(", [\n");
                    C2005h c2005h3 = new C2005h(this.f2809a, this.f2810b + 1);
                    int length3 = jArr.length;
                    while (i < length3) {
                        long j = jArr[i];
                        c2005h3.m1727a(null);
                        StringBuilder sb18 = c2005h3.f2809a;
                        sb18.append(j);
                        sb18.append('\n');
                        i++;
                    }
                    m1727a(null);
                    StringBuilder sb19 = this.f2809a;
                    sb19.append(']');
                    sb19.append('\n');
                }
            } else if (t instanceof float[]) {
                float[] fArr = (float[]) t;
                m1727a(str);
                if (fArr == null) {
                    this.f2809a.append("null\n");
                } else if (fArr.length == 0) {
                    StringBuilder sb20 = this.f2809a;
                    sb20.append(fArr.length);
                    sb20.append(", []\n");
                } else {
                    StringBuilder sb21 = this.f2809a;
                    sb21.append(fArr.length);
                    sb21.append(", [\n");
                    C2005h c2005h4 = new C2005h(this.f2809a, this.f2810b + 1);
                    int length4 = fArr.length;
                    while (i < length4) {
                        float f = fArr[i];
                        c2005h4.m1727a(null);
                        StringBuilder sb22 = c2005h4.f2809a;
                        sb22.append(f);
                        sb22.append('\n');
                        i++;
                    }
                    m1727a(null);
                    StringBuilder sb23 = this.f2809a;
                    sb23.append(']');
                    sb23.append('\n');
                }
            } else if (t instanceof double[]) {
                double[] dArr = (double[]) t;
                m1727a(str);
                if (dArr == null) {
                    this.f2809a.append("null\n");
                } else if (dArr.length == 0) {
                    StringBuilder sb24 = this.f2809a;
                    sb24.append(dArr.length);
                    sb24.append(", []\n");
                } else {
                    StringBuilder sb25 = this.f2809a;
                    sb25.append(dArr.length);
                    sb25.append(", [\n");
                    C2005h c2005h5 = new C2005h(this.f2809a, this.f2810b + 1);
                    int length5 = dArr.length;
                    while (i < length5) {
                        double d = dArr[i];
                        c2005h5.m1727a(null);
                        StringBuilder sb26 = c2005h5.f2809a;
                        sb26.append(d);
                        sb26.append('\n');
                        i++;
                    }
                    m1727a(null);
                    StringBuilder sb27 = this.f2809a;
                    sb27.append(']');
                    sb27.append('\n');
                }
            } else if (t.getClass().isArray()) {
                m1726a((Object[]) t, str);
            } else {
                throw new C1999b("write object error: unsupport type.");
            }
        }
        return this;
    }

    /* JADX INFO: renamed from: a */
    public final C2005h m1731a(AbstractC2008k abstractC2008k, String str) {
        m1727a(str);
        StringBuilder sb = this.f2809a;
        sb.append('{');
        sb.append('\n');
        if (abstractC2008k == null) {
            StringBuilder sb2 = this.f2809a;
            sb2.append('\t');
            sb2.append(IConstant.DEFAULT_PATH);
        } else {
            abstractC2008k.mo1718a(this.f2809a, this.f2810b + 1);
        }
        m1727a(null);
        StringBuilder sb3 = this.f2809a;
        sb3.append('}');
        sb3.append('\n');
        return this;
    }
}
