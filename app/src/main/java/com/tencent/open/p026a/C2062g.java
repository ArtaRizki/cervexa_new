package com.tencent.open.p026a;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: renamed from: com.tencent.open.a.g */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class C2062g implements Iterable<String> {

    /* JADX INFO: renamed from: a */
    private ConcurrentLinkedQueue<String> f3177a;

    /* JADX INFO: renamed from: b */
    private AtomicInteger f3178b;

    public C2062g() {
        this.f3177a = null;
        this.f3178b = null;
        this.f3177a = new ConcurrentLinkedQueue<>();
        this.f3178b = new AtomicInteger(0);
    }

    /* JADX INFO: renamed from: a */
    public int m2139a(String str) {
        int length = str.length();
        this.f3177a.add(str);
        return this.f3178b.addAndGet(length);
    }

    /* JADX INFO: renamed from: a */
    public void m2140a(Writer writer, char[] cArr) throws IOException {
        if (writer == null || cArr == null || cArr.length == 0) {
            return;
        }
        int length = cArr.length;
        int i = length;
        int i2 = 0;
        for (String str : this) {
            int length2 = str.length();
            int i3 = 0;
            while (length2 > 0) {
                int i4 = i > length2 ? length2 : i;
                int i5 = i3 + i4;
                str.getChars(i3, i5, cArr, i2);
                i -= i4;
                i2 += i4;
                length2 -= i4;
                if (i == 0) {
                    writer.write(cArr, 0, length);
                    i = length;
                    i3 = i5;
                    i2 = 0;
                } else {
                    i3 = i5;
                }
            }
        }
        if (i2 > 0) {
            writer.write(cArr, 0, i2);
        }
        writer.flush();
    }

    /* JADX INFO: renamed from: a */
    public int m2138a() {
        return this.f3178b.get();
    }

    /* JADX INFO: renamed from: b */
    public void m2141b() {
        this.f3177a.clear();
        this.f3178b.set(0);
    }

    @Override // java.lang.Iterable
    public Iterator<String> iterator() {
        return this.f3177a.iterator();
    }
}
