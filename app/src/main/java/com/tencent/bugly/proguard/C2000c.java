package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: renamed from: com.tencent.bugly.proguard.c */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public class C2000c extends C1980a {

    /* JADX INFO: renamed from: d */
    protected HashMap<String, byte[]> f2789d = null;

    /* JADX INFO: renamed from: e */
    private HashMap<String, Object> f2790e = new HashMap<>();

    /* JADX INFO: renamed from: f */
    private C2006i f2791f = new C2006i();

    @Override // com.tencent.bugly.proguard.C1980a
    /* JADX INFO: renamed from: a */
    public final /* bridge */ /* synthetic */ void mo1694a(String str) {
        super.mo1694a(str);
    }

    /* JADX INFO: renamed from: c */
    public void mo1720c() {
        this.f2789d = new HashMap<>();
    }

    @Override // com.tencent.bugly.proguard.C1980a
    /* JADX INFO: renamed from: a */
    public <T> void mo1695a(String str, T t) {
        if (this.f2789d == null) {
            super.mo1695a(str, t);
            return;
        }
        if (str == null) {
            throw new IllegalArgumentException("put key can not is null");
        }
        if (t == null) {
            throw new IllegalArgumentException("put value can not is null");
        }
        if (t instanceof Set) {
            throw new IllegalArgumentException("can not support Set");
        }
        C2007j c2007j = new C2007j();
        c2007j.m1766a(this.f2663b);
        c2007j.m1772a(t, 0);
        this.f2789d.put(str, C2009l.m1784a(c2007j.m1767a()));
    }

    /* JADX INFO: renamed from: b */
    public final <T> T m1719b(String str, T t) throws C1999b {
        HashMap<String, byte[]> map = this.f2789d;
        if (map != null) {
            if (!map.containsKey(str)) {
                return null;
            }
            if (this.f2790e.containsKey(str)) {
                return (T) this.f2790e.get(str);
            }
            try {
                this.f2791f.m1760a(this.f2789d.get(str));
                this.f2791f.m1754a(this.f2663b);
                T t2 = (T) this.f2791f.m1757a((Object) t, 0, true);
                if (t2 != null) {
                    this.f2790e.put(str, t2);
                }
                return t2;
            } catch (Exception e) {
                throw new C1999b(e);
            }
        }
        if (!this.f2662a.containsKey(str)) {
            return null;
        }
        if (this.f2790e.containsKey(str)) {
            return (T) this.f2790e.get(str);
        }
        byte[] value = new byte[0];
        Iterator<Map.Entry<String, byte[]>> it = this.f2662a.get(str).entrySet().iterator();
        if (it.hasNext()) {
            Map.Entry<String, byte[]> next = it.next();
            next.getKey();
            value = next.getValue();
        }
        try {
            this.f2791f.m1760a(value);
            this.f2791f.m1754a(this.f2663b);
            T t3 = (T) this.f2791f.m1757a((Object) t, 0, true);
            this.f2790e.put(str, t3);
            return t3;
        } catch (Exception e2) {
            throw new C1999b(e2);
        }
    }

    @Override // com.tencent.bugly.proguard.C1980a
    /* JADX INFO: renamed from: a */
    public byte[] mo1697a() {
        if (this.f2789d != null) {
            C2007j c2007j = new C2007j(0);
            c2007j.m1766a(this.f2663b);
            c2007j.m1775a((Map) this.f2789d, 0);
            return C2009l.m1784a(c2007j.m1767a());
        }
        return super.mo1697a();
    }

    @Override // com.tencent.bugly.proguard.C1980a
    /* JADX INFO: renamed from: a */
    public void mo1696a(byte[] bArr) {
        try {
            super.mo1696a(bArr);
        } catch (Exception unused) {
            this.f2791f.m1760a(bArr);
            this.f2791f.m1754a(this.f2663b);
            HashMap map = new HashMap(1);
            map.put("", new byte[0]);
            this.f2789d = this.f2791f.m1758a((Map) map, 0, false);
        }
    }
}
