package com.baidu.trace;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.baidu.trace.C0819ar;
import com.baidu.trace.p011b.C0829a;
import com.baidu.trace.p012c.C0854e;
import java.lang.ref.WeakReference;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/* JADX INFO: renamed from: com.baidu.trace.bd */
/* JADX INFO: loaded from: classes.dex */
public final class C0843bd extends Thread {

    /* JADX INFO: renamed from: b */
    protected static int f1677b = C0819ar.f1576b;

    /* JADX INFO: renamed from: c */
    protected static Deque<C0819ar.a> f1678c = null;

    /* JADX INFO: renamed from: d */
    protected static boolean f1679d = false;

    /* JADX INFO: renamed from: f */
    protected static int f1680f = 0;

    /* JADX INFO: renamed from: g */
    protected static boolean f1681g = false;

    /* JADX INFO: renamed from: h */
    protected static boolean f1682h = false;

    /* JADX INFO: renamed from: i */
    protected static int f1683i = 30000;

    /* JADX INFO: renamed from: q */
    private static Map<Integer, Integer> f1684q;

    /* JADX INFO: renamed from: r */
    private static Map<Integer, Queue<C0819ar.a>> f1685r;

    /* JADX INFO: renamed from: a */
    protected C0819ar f1686a;

    /* JADX INFO: renamed from: j */
    private Context f1688j;

    /* JADX INFO: renamed from: k */
    private WeakReference<LBSTraceService> f1689k;

    /* JADX INFO: renamed from: m */
    private b f1691m;

    /* JADX INFO: renamed from: n */
    private Handler f1692n;

    /* JADX INFO: renamed from: l */
    private a f1690l = null;

    /* JADX INFO: renamed from: o */
    private int f1693o = 0;

    /* JADX INFO: renamed from: p */
    private long f1694p = 0;

    /* JADX INFO: renamed from: s */
    private boolean f1695s = false;

    /* JADX INFO: renamed from: t */
    private boolean f1696t = false;

    /* JADX INFO: renamed from: e */
    protected boolean f1687e = false;

    /* JADX INFO: renamed from: u */
    private long f1697u = 0;

    /* JADX INFO: renamed from: v */
    private long f1698v = System.currentTimeMillis();

    /* JADX INFO: renamed from: w */
    private long f1699w = System.currentTimeMillis();

    /* JADX INFO: renamed from: com.baidu.trace.bd$a */
    static class a extends Handler {
        a() {
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            super.handleMessage(message);
        }
    }

    /* JADX INFO: renamed from: com.baidu.trace.bd$b */
    class b implements Runnable {
        private b() {
        }

        /* synthetic */ b(C0843bd c0843bd, byte b) {
            this();
        }

        /* JADX WARN: Removed duplicated region for block: B:60:0x0157  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void run() {
            /*
                Method dump skipped, instruction units count: 603
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.trace.C0843bd.b.run():void");
        }
    }

    public C0843bd(LBSTraceService lBSTraceService) {
        this.f1688j = null;
        this.f1689k = null;
        this.f1691m = null;
        this.f1692n = null;
        this.f1686a = null;
        WeakReference<LBSTraceService> weakReference = new WeakReference<>(lBSTraceService);
        this.f1689k = weakReference;
        this.f1692n = weakReference.get().getTraceHandler();
        this.f1688j = this.f1689k.get().getServiceContext();
        this.f1686a = new C0819ar(this.f1689k, this.f1692n);
        this.f1691m = new b(this, (byte) 0);
        f1684q = new LinkedHashMap();
        f1685r = new LinkedHashMap();
        f1678c = new LinkedList();
    }

    /* JADX INFO: renamed from: a */
    static /* synthetic */ int m1173a(C0843bd c0843bd, int i) {
        c0843bd.f1693o = 0;
        return 0;
    }

    /* JADX INFO: renamed from: a */
    protected static void m1175a(int i) {
        f1685r.remove(Integer.valueOf(i));
        f1684q.remove(Integer.valueOf(i));
    }

    /* JADX INFO: renamed from: a */
    protected static void m1176a(int i, Queue<C0819ar.a> queue) {
        f1685r.put(Integer.valueOf(i), queue);
        f1684q.put(Integer.valueOf(i), Integer.valueOf(C0854e.m1224a()));
    }

    /* JADX INFO: renamed from: a */
    protected static void m1177a(Handler handler) {
        if (C0854e.f1740d <= 0 || C0881z.f1884s != 1 || C0854e.m1224a() - C0854e.f1740d <= 60 || handler == null) {
            return;
        }
        handler.obtainMessage(4).sendToTarget();
    }

    /* JADX INFO: renamed from: a */
    protected static void m1178a(boolean z) {
        Integer num;
        Map<Integer, Queue<C0819ar.a>> map = f1685r;
        if (map == null || map.size() == 0) {
            return;
        }
        Iterator<Map.Entry<Integer, Queue<C0819ar.a>>> it = f1685r.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Queue<C0819ar.a>> next = it.next();
            if (next != null) {
                Queue<C0819ar.a> value = next.getValue();
                Integer key = next.getKey();
                if (value != null && key != null && (num = f1684q.get(key)) != null) {
                    int iM1224a = C0854e.m1224a();
                    if (z && iM1224a - num.intValue() < C0824aw.f1618b) {
                        value.clear();
                        return;
                    }
                    while (value.size() > 0) {
                        C0824aw.f1617a.offerLast(value.poll());
                    }
                    try {
                        f1684q.remove(key);
                        it.remove();
                    } catch (Exception unused) {
                    }
                }
            }
        }
    }

    /* JADX INFO: renamed from: b */
    protected static void m1181b() {
        Map<Integer, Integer> map = f1684q;
        if (map != null) {
            map.clear();
            f1684q = null;
        }
        Map<Integer, Queue<C0819ar.a>> map2 = f1685r;
        if (map2 != null) {
            map2.clear();
            f1685r = null;
        }
        Deque<C0819ar.a> deque = f1678c;
        if (deque != null) {
            deque.clear();
            f1678c = null;
        }
    }

    /* JADX INFO: renamed from: d */
    protected static void m1187d() {
        int i;
        int i2 = f1683i / C0819ar.f1576b;
        if (C0819ar.f1576b <= C0881z.f1881p * 1000) {
            i = C0819ar.f1576b;
        } else {
            int i3 = (C0819ar.f1576b / 5000) * 5000;
            C0819ar.f1576b = i3;
            if (i3 % (C0881z.f1881p * 1000) != 0) {
                f1677b = 5000;
                f1683i = C0819ar.f1576b * i2;
            }
            i = C0881z.f1881p * 1000;
        }
        f1677b = i;
        f1683i = C0819ar.f1576b * i2;
    }

    /* JADX INFO: renamed from: e */
    protected static int m1188e() {
        Deque<C0819ar.a> deque = f1678c;
        int size = deque != null ? 0 + deque.size() : 0;
        return C0824aw.f1617a != null ? size + C0824aw.f1617a.size() : size;
    }

    /* JADX INFO: renamed from: e */
    static /* synthetic */ int m1189e(C0843bd c0843bd) {
        int i = c0843bd.f1693o;
        c0843bd.f1693o = i + 1;
        return i;
    }

    /* JADX INFO: renamed from: j */
    static /* synthetic */ boolean m1194j(C0843bd c0843bd) {
        int iM1224a = C0854e.m1224a();
        byte[] bArrM1117a = C0825ax.m1117a((short) 9, iM1224a);
        if (bArrM1117a == null || bArrM1117a.length <= 0) {
            C0824aw.m1112c();
            return false;
        }
        C0824aw.m1108a(iM1224a);
        C0829a.m1123a(bArrM1117a, c0843bd.f1692n);
        return true;
    }

    /* JADX INFO: renamed from: l */
    static /* synthetic */ void m1196l(C0843bd c0843bd) {
        byte[] bArrBuildHeartbeatData = TraceJniInterface.m951a().buildHeartbeatData();
        if (bArrBuildHeartbeatData != null) {
            C0829a.m1123a(bArrBuildHeartbeatData, c0843bd.f1692n);
        }
    }

    /* JADX INFO: renamed from: a */
    protected final void m1198a() {
        f1680f = 0;
        f1681g = false;
        f1682h = false;
        f1679d = false;
        this.f1696t = false;
        this.f1695s = false;
        this.f1687e = false;
    }

    /* JADX INFO: renamed from: a */
    protected final void m1199a(long j) {
        this.f1694p = 0L;
    }

    /* JADX INFO: renamed from: a */
    protected final void m1200a(boolean z, boolean z2) {
        this.f1696t = z;
        this.f1695s = z2;
        if (z2) {
            return;
        }
        this.f1693o = 0;
    }

    /* JADX INFO: renamed from: c */
    protected final void m1201c() {
        b bVar;
        a aVar = this.f1690l;
        if (aVar != null && (bVar = this.f1691m) != null) {
            aVar.removeCallbacks(bVar);
            if (Build.VERSION.SDK_INT >= 18) {
                this.f1690l.getLooper().quitSafely();
            } else {
                this.f1690l.getLooper().quit();
            }
        }
        this.f1691m = null;
        this.f1690l = null;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        Looper.prepare();
        a aVar = new a();
        this.f1690l = aVar;
        aVar.post(this.f1691m);
        Looper.loop();
    }
}
