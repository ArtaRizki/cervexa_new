package com.baidu.trace;

import android.text.TextUtils;
import com.baidu.trace.C0819ar;
import com.baidu.trace.p010a.C0799h;
import com.baidu.trace.p012c.InterfaceC0856g;
import java.util.Deque;
import java.util.LinkedList;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.baidu.trace.aw */
/* JADX INFO: loaded from: classes.dex */
public class C0824aw implements InterfaceC0856g {

    /* JADX INFO: renamed from: a */
    public static Deque<C0819ar.a> f1617a = null;

    /* JADX INFO: renamed from: b */
    protected static long f1618b = 2;

    /* JADX INFO: renamed from: c */
    protected static int f1619c = 0;

    /* JADX INFO: renamed from: d */
    private static byte f1620d = 1;

    /* JADX INFO: renamed from: e */
    private static Deque<C0819ar.a> f1621e = null;

    /* JADX INFO: renamed from: f */
    private static int f1622f = 0;

    /* JADX INFO: renamed from: g */
    private static String f1623g = "";

    /* JADX INFO: renamed from: a */
    public static void m1108a(int i) {
        if (C0881z.f1884s == 1) {
            LinkedList linkedList = new LinkedList();
            while (f1621e.size() > 0) {
                linkedList.offer(f1621e.poll());
            }
            C0843bd.m1176a(i, linkedList);
        }
        f1622f = 0;
    }

    /* JADX INFO: renamed from: a */
    private void m1109a(Deque<C0819ar.a> deque) {
        int i = f1620d == 1 ? (C0881z.f1883r << 5) * 3 : C0881z.f1883r << 5;
        int size = deque.size();
        while (f1622f < i && size > 0 && f1621e.size() < 10) {
            C0819ar.a aVarPoll = deque.poll();
            size--;
            if (aVarPoll != null && aVarPoll.f1596b != null) {
                if (i - f1622f < aVarPoll.f1596b.length) {
                    deque.offerFirst(aVarPoll);
                    return;
                } else {
                    f1621e.offer(aVarPoll);
                    f1622f += aVarPoll.f1596b.length;
                }
            }
        }
    }

    /* JADX INFO: renamed from: b */
    protected static void m1110b() {
        if (f1621e == null) {
            f1621e = new LinkedList();
        }
        if (f1617a == null) {
            f1617a = new LinkedList();
        }
    }

    /* JADX INFO: renamed from: b */
    public static void m1111b(int i) {
        if (i <= 0) {
            return;
        }
        if ((C0843bd.f1681g || (!C0843bd.f1679d && C0843bd.f1682h)) && f1619c > 0 && !TextUtils.isEmpty(f1623g)) {
            C0814am.m1070a(f1623g, f1619c);
        }
        C0843bd.m1175a(i);
    }

    /* JADX INFO: renamed from: c */
    public static void m1112c() {
        while (f1621e.size() > 0) {
            f1617a.offerLast(f1621e.poll());
        }
        f1622f = 0;
    }

    /* JADX INFO: renamed from: d */
    protected static void m1113d() {
        Deque<C0819ar.a> deque = f1621e;
        if (deque != null) {
            deque.clear();
            f1621e = null;
        }
        Deque<C0819ar.a> deque2 = f1617a;
        if (deque2 != null) {
            deque2.clear();
            f1617a = null;
        }
    }

    /* JADX INFO: renamed from: e */
    private static byte[] m1114e() {
        int i = f1622f;
        byte[] bArr = new byte[i];
        int size = f1621e.size();
        int length = 0;
        for (int i2 = 0; i2 < size; i2++) {
            C0819ar.a aVarPoll = f1621e.poll();
            if (aVarPoll != null && aVarPoll.f1596b != null) {
                if (aVarPoll.f1596b.length + length > i) {
                    return bArr;
                }
                for (int i3 = 0; i3 < aVarPoll.f1596b.length; i3++) {
                    bArr[length + i3] = aVarPoll.f1596b[i3];
                }
                length += aVarPoll.f1596b.length;
                f1621e.offer(aVarPoll);
            }
        }
        return bArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0034  */
    @Override // com.baidu.trace.p012c.InterfaceC0856g
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void mo1083a() {
        /*
            r3 = this;
            m1110b()
            r0 = 0
            com.baidu.trace.C0824aw.f1622f = r0
            java.util.Deque<com.baidu.trace.ar$a> r0 = com.baidu.trace.C0824aw.f1617a
            int r0 = r0.size()
            if (r0 <= 0) goto L14
            java.util.Deque<com.baidu.trace.ar$a> r0 = com.baidu.trace.C0824aw.f1617a
        L10:
            r3.m1109a(r0)
            goto L3b
        L14:
            boolean r0 = com.baidu.trace.C0843bd.f1681g
            if (r0 != 0) goto L20
            boolean r0 = com.baidu.trace.C0843bd.f1679d
            if (r0 != 0) goto L34
            boolean r0 = com.baidu.trace.C0843bd.f1682h
            if (r0 == 0) goto L34
        L20:
            java.util.LinkedList r0 = new java.util.LinkedList
            r0.<init>()
            java.lang.String r1 = com.baidu.trace.C0881z.f1868c
            r2 = 10
            java.lang.String r1 = com.baidu.trace.C0814am.m1059a(r1, r2, r0)
            com.baidu.trace.C0824aw.f1623g = r1
            int r1 = com.baidu.trace.C0824aw.f1619c
            if (r1 <= 0) goto L34
            goto L10
        L34:
            java.lang.String r0 = com.baidu.trace.C0881z.f1868c
            com.baidu.trace.C0824aw.f1623g = r0
            java.util.Deque<com.baidu.trace.ar$a> r0 = com.baidu.trace.C0843bd.f1678c
            goto L10
        L3b:
            com.baidu.trace.TraceJniInterface r0 = com.baidu.trace.TraceJniInterface.m951a()
            byte r1 = com.baidu.trace.C0824aw.f1620d
            byte[] r2 = m1114e()
            r0.setPackData(r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.trace.C0824aw.mo1083a():void");
    }

    @Override // com.baidu.trace.p012c.InterfaceC0856g
    /* JADX INFO: renamed from: a */
    public final void mo1084a(JSONObject jSONObject) {
        C0799h c0799h = new C0799h();
        try {
            c0799h.f1204a = jSONObject.getInt("msg_flag");
            C0842bc.m1163a();
            C0842bc.m1164a(c0799h);
        } catch (Exception unused) {
        }
    }
}
