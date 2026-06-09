package com.tencent.bugly.proguard;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.tencent.bugly.proguard.d */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public final class C2001d extends C2000c {

    /* JADX INFO: renamed from: f */
    private static HashMap<String, byte[]> f2792f;

    /* JADX INFO: renamed from: g */
    private static HashMap<String, HashMap<String, byte[]>> f2793g;

    /* JADX INFO: renamed from: e */
    private C2003f f2794e;

    public C2001d() {
        C2003f c2003f = new C2003f();
        this.f2794e = c2003f;
        c2003f.f2799a = (short) 2;
    }

    @Override // com.tencent.bugly.proguard.C2000c, com.tencent.bugly.proguard.C1980a
    /* JADX INFO: renamed from: a */
    public final <T> void mo1695a(String str, T t) {
        if (str.startsWith(".")) {
            throw new IllegalArgumentException("put name can not startwith . , now is " + str);
        }
        super.mo1695a(str, t);
    }

    @Override // com.tencent.bugly.proguard.C2000c
    /* JADX INFO: renamed from: c */
    public final void mo1720c() {
        super.mo1720c();
        this.f2794e.f2799a = (short) 3;
    }

    @Override // com.tencent.bugly.proguard.C2000c, com.tencent.bugly.proguard.C1980a
    /* JADX INFO: renamed from: a */
    public final byte[] mo1697a() {
        if (this.f2794e.f2799a == 2) {
            if (this.f2794e.f2801c.equals("")) {
                throw new IllegalArgumentException("servantName can not is null");
            }
            if (this.f2794e.f2802d.equals("")) {
                throw new IllegalArgumentException("funcName can not is null");
            }
        } else {
            if (this.f2794e.f2801c == null) {
                this.f2794e.f2801c = "";
            }
            if (this.f2794e.f2802d == null) {
                this.f2794e.f2802d = "";
            }
        }
        C2007j c2007j = new C2007j(0);
        c2007j.m1766a(this.f2663b);
        if (this.f2794e.f2799a == 2) {
            c2007j.m1775a((Map) this.f2662a, 0);
        } else {
            c2007j.m1775a((Map) this.f2789d, 0);
        }
        this.f2794e.f2803e = C2009l.m1784a(c2007j.m1767a());
        C2007j c2007j2 = new C2007j(0);
        c2007j2.m1766a(this.f2663b);
        this.f2794e.mo1717a(c2007j2);
        byte[] bArrM1784a = C2009l.m1784a(c2007j2.m1767a());
        int length = bArrM1784a.length + 4;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(length);
        byteBufferAllocate.putInt(length).put(bArrM1784a).flip();
        return byteBufferAllocate.array();
    }

    @Override // com.tencent.bugly.proguard.C2000c, com.tencent.bugly.proguard.C1980a
    /* JADX INFO: renamed from: a */
    public final void mo1696a(byte[] bArr) {
        if (bArr.length < 4) {
            throw new IllegalArgumentException("decode package must include size head");
        }
        try {
            C2006i c2006i = new C2006i(bArr, 4);
            c2006i.m1754a(this.f2663b);
            this.f2794e.mo1716a(c2006i);
            if (this.f2794e.f2799a == 3) {
                C2006i c2006i2 = new C2006i(this.f2794e.f2803e);
                c2006i2.m1754a(this.f2663b);
                if (f2792f == null) {
                    HashMap<String, byte[]> map = new HashMap<>();
                    f2792f = map;
                    map.put("", new byte[0]);
                }
                this.f2789d = c2006i2.m1758a((Map) f2792f, 0, false);
                return;
            }
            C2006i c2006i3 = new C2006i(this.f2794e.f2803e);
            c2006i3.m1754a(this.f2663b);
            if (f2793g == null) {
                f2793g = new HashMap<>();
                HashMap<String, byte[]> map2 = new HashMap<>();
                map2.put("", new byte[0]);
                f2793g.put("", map2);
            }
            this.f2662a = c2006i3.m1758a((Map) f2793g, 0, false);
            new HashMap();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX INFO: renamed from: b */
    public final void m1722b(String str) {
        this.f2794e.f2801c = str;
    }

    /* JADX INFO: renamed from: c */
    public final void m1723c(String str) {
        this.f2794e.f2802d = str;
    }

    /* JADX INFO: renamed from: a */
    public final void m1721a(int i) {
        this.f2794e.f2800b = 1;
    }
}
