package com.baidu.trace;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.baidu.lbsapi.auth.tracesdk.LBSAuthManager;
import com.baidu.trace.IService;
import com.baidu.trace.api.fence.CircleFence;
import com.baidu.trace.model.CoordType;
import com.baidu.trace.model.LocationMode;
import com.baidu.trace.model.ProtocolType;
import com.baidu.trace.model.TraceLocation;
import com.baidu.trace.p011b.C0831c;
import com.baidu.trace.p011b.C0832d;
import com.baidu.trace.p012c.C0850a;
import com.baidu.trace.p012c.C0854e;
import com.baidu.trace.p012c.C0855f;
import com.baidu.trace.p012c.C0857h;
import com.serenegiant.net.NetworkChangedReceiver;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import org.apache.commons.net.imap.IMAP;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class LBSTraceService extends Service {

    /* JADX INFO: renamed from: e */
    private Context f1145e;

    /* JADX INFO: renamed from: f */
    private C0874s f1146f;

    /* JADX INFO: renamed from: a */
    private HandlerC0790a f1141a = new HandlerC0790a(this);

    /* JADX INFO: renamed from: b */
    private ServiceBinder f1142b = new ServiceBinder(this);

    /* JADX INFO: renamed from: c */
    private C0832d f1143c = null;

    /* JADX INFO: renamed from: d */
    private C0843bd f1144d = null;

    /* JADX INFO: renamed from: g */
    private boolean f1147g = false;

    /* JADX INFO: renamed from: h */
    private IListener f1148h = null;

    /* JADX INFO: renamed from: i */
    private boolean f1149i = false;

    /* JADX INFO: renamed from: j */
    private boolean f1150j = false;

    /* JADX INFO: renamed from: k */
    private C0831c f1151k = new C0831c();

    /* JADX INFO: renamed from: l */
    private int f1152l = LocationMode.High_Accuracy.ordinal();

    /* JADX INFO: renamed from: m */
    private int f1153m = ProtocolType.HTTPS.ordinal();

    /* JADX INFO: renamed from: n */
    private C0855f.b f1154n = C0855f.b.NOT_START;

    /* JADX INFO: renamed from: com.baidu.trace.LBSTraceService$1 */
    final /* synthetic */ class C07891 {

        /* JADX INFO: renamed from: a */
        static final /* synthetic */ int[] f1155a;

        static {
            int[] iArr = new int[C0855f.b.values().length];
            f1155a = iArr;
            try {
                iArr[C0855f.b.LOGGING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1155a[C0855f.b.LOGOUTING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1155a[C0855f.b.NOT_START.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1155a[C0855f.b.STARTED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    static class ServiceBinder extends IService.Stub {
        private WeakReference<LBSTraceService> service;

        public ServiceBinder(LBSTraceService lBSTraceService) {
            this.service = null;
            this.service = new WeakReference<>(lBSTraceService);
        }

        @Override // com.baidu.trace.IService
        public void handleExtendedOperate(int i) throws RemoteException {
            if (this.service.get().f1147g) {
                C0861g.m1261b();
                this.service.get().m935a((short) 18, C0854e.m1224a(), null);
            }
        }

        @Override // com.baidu.trace.IService
        public boolean handleLocalFence(int i, long j, String str) throws JSONException, RemoteException {
            SharedPreferences.Editor editorEdit = this.service.get().f1145e.getSharedPreferences("com.baidu.trace_fence_status", 0).edit();
            if (i == 0) {
                JSONObject jSONObject = new JSONObject(str);
                CircleFence circleFenceBuildLocalFence = CircleFence.buildLocalFence(j, null, null, null, 0.0d, 0, CoordType.bd09ll);
                C0791a.m1006a(jSONObject, circleFenceBuildLocalFence);
                if (C0819ar.f1575a != null) {
                    C0819ar.f1575a.m1041a(j, circleFenceBuildLocalFence);
                }
            } else if (i == 1) {
                JSONObject jSONObject2 = new JSONObject(str);
                CircleFence circleFenceBuildLocalFence2 = CircleFence.buildLocalFence(j, null, null, null, 0.0d, 0, CoordType.bd09ll);
                C0791a.m1006a(jSONObject2, circleFenceBuildLocalFence2);
                if (C0819ar.f1575a != null) {
                    C0819ar.f1575a.m1041a(j, circleFenceBuildLocalFence2);
                    editorEdit.remove(String.valueOf(j));
                    editorEdit.apply();
                }
            } else if (i != 2) {
                if (i == 3 && C0819ar.f1575a != null) {
                    C0819ar.f1575a.m1048c();
                    editorEdit.clear();
                    editorEdit.apply();
                }
            } else if (C0819ar.f1575a != null) {
                try {
                    for (String str2 : str.split(",")) {
                        C0819ar.f1575a.m1040a(Long.valueOf(str2).longValue());
                        editorEdit.remove(str2);
                        editorEdit.apply();
                    }
                } catch (JSONException | Exception unused) {
                }
            }
            return false;
        }

        @Override // com.baidu.trace.IService
        public boolean queryRealTimeLoc(TraceLocation traceLocation) throws RemoteException {
            return false;
        }

        @Override // com.baidu.trace.IService
        public void registerListener(IListener iListener) throws RemoteException {
            this.service.get().setClientListener(iListener);
            if (this.service.get().f1149i) {
                iListener.startTraceCallback(19);
                LBSTraceService.m923a(this.service.get(), false);
            }
        }

        @Override // com.baidu.trace.IService
        public boolean setCacheSize(int i) throws RemoteException {
            return this.service.get().m938b(i);
        }

        @Override // com.baidu.trace.IService
        public boolean setInterval(int i, int i2) throws RemoteException {
            this.service.get().m933a(i, i2);
            return true;
        }

        @Override // com.baidu.trace.IService
        public boolean setLocTimeOffset(int i) throws RemoteException {
            return this.service.get().m940c(i);
        }

        @Override // com.baidu.trace.IService
        public boolean setLocationMode(int i) throws RemoteException {
            return this.service.get().m942d(i);
        }

        @Override // com.baidu.trace.IService
        public boolean setProtocolType(int i) throws RemoteException {
            C0850a.f1716b = i == 0 ? ProtocolType.HTTP : ProtocolType.HTTPS;
            return true;
        }

        @Override // com.baidu.trace.IService
        public int startGather() throws RemoteException {
            return LBSTraceService.m926c(this.service.get());
        }

        @Override // com.baidu.trace.IService
        public int stopGather() throws RemoteException {
            return LBSTraceService.m924b(this.service.get());
        }

        @Override // com.baidu.trace.IService
        public void stopTrace(long j, String str) throws RemoteException {
            try {
                this.service.get().m945f();
            } catch (RemoteException unused) {
            }
        }

        @Override // com.baidu.trace.IService
        public void unregisterListener() throws RemoteException {
            this.service.get().setClientListener(null);
        }
    }

    /* JADX INFO: renamed from: com.baidu.trace.LBSTraceService$a */
    static class HandlerC0790a extends Handler {

        /* JADX INFO: renamed from: a */
        private WeakReference<LBSTraceService> f1156a;

        public HandlerC0790a(LBSTraceService lBSTraceService) {
            this.f1156a = null;
            this.f1156a = new WeakReference<>(lBSTraceService);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            super.handleMessage(message);
            LBSTraceService lBSTraceService = this.f1156a.get();
            if (lBSTraceService == null) {
            }
            int i = message.what;
            if (i == 1) {
                lBSTraceService.m931a();
                return;
            }
            if (i == 4) {
                lBSTraceService.m941d();
                return;
            }
            if (i == 19) {
                lBSTraceService.m943e();
                return;
            }
            if (i == 141) {
                lBSTraceService.m944e(message.arg1);
                return;
            }
            if (i == 161) {
                lBSTraceService.m934a(message);
                return;
            }
            if (i == 163) {
                lBSTraceService.m937b(message);
                return;
            }
            if (i == 23) {
                lBSTraceService.m946g();
                return;
            }
            if (i == 24) {
                lBSTraceService.m947h();
                return;
            }
            switch (i) {
                case 28:
                    lBSTraceService.m936b();
                    break;
                case 29:
                    lBSTraceService.m939c();
                    break;
                case 30:
                    lBSTraceService.m932a(message.arg1);
                    break;
                case 31:
                    if (lBSTraceService.f1147g) {
                        lBSTraceService.m935a((short) 18, C0854e.m1224a(), null);
                    }
                    break;
                case 32:
                    LBSTraceService.m919a(lBSTraceService, message.arg1);
                    break;
            }
        }
    }

    static {
        LBSTraceService.class.getSimpleName();
    }

    /* JADX INFO: renamed from: a */
    private static HashMap<String, String> m918a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            String[] strArrSplit = str.split("-\\|-");
            if (strArrSplit != null && strArrSplit.length >= 10) {
                HashMap<String, String> map = new HashMap<>();
                map.put("ak", strArrSplit[0]);
                map.put("serviceId", strArrSplit[1]);
                map.put("entityName", strArrSplit[2]);
                map.put("mcode", strArrSplit[3]);
                map.put("pcn", strArrSplit[4]);
                map.put("md", strArrSplit[5]);
                map.put("cpu", strArrSplit[6]);
                map.put("locationMode", strArrSplit[7]);
                map.put("protocolType", strArrSplit[8]);
                map.put("isNeedObjectStorage", strArrSplit[9]);
                return map;
            }
        } catch (Exception unused) {
        }
        return null;
    }

    /* JADX INFO: renamed from: a */
    static /* synthetic */ void m919a(LBSTraceService lBSTraceService, int i) {
        String string = null;
        if (i == 0) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("accessKey", C0861g.f1785a);
                jSONObject.put("secretKey", C0861g.f1787c);
                jSONObject.put("token", C0861g.f1788d);
                jSONObject.put("expireTime", C0861g.f1786b);
                string = jSONObject.toString();
            } catch (JSONException unused) {
                i = 1;
            }
        }
        IListener iListener = lBSTraceService.f1148h;
        if (iListener != null) {
            try {
                iListener.extendedOperationCallback(i, string);
            } catch (RemoteException unused2) {
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private void m920a(boolean z, boolean z2) {
        if (Trace.f1158b != 30 && Trace.f1158b >= 2) {
            C0843bd.f1683i = Trace.f1158b * 1000;
        }
        if (Trace.f1157a != 5 && Trace.f1157a >= C0819ar.f1577c) {
            C0819ar.f1576b = Trace.f1157a * 1000;
        }
        C0843bd.m1187d();
        C0843bd c0843bd = this.f1144d;
        if (c0843bd != null) {
            if (c0843bd.isAlive()) {
                this.f1144d.m1200a(z, z2);
                return;
            } else {
                this.f1144d.m1201c();
                this.f1144d = null;
            }
        }
        C0843bd c0843bd2 = new C0843bd(this);
        this.f1144d = c0843bd2;
        c0843bd2.m1200a(z, z2);
        this.f1144d.start();
    }

    /* JADX INFO: renamed from: a */
    private boolean m921a(int i, long j) {
        StringBuilder sb;
        String strM1274a = this.f1146f.m1274a("pushInfo");
        boolean z = true;
        if (strM1274a == null) {
            this.f1146f.m1276a("pushInfo", j + "_" + i + ";");
            this.f1146f.m1275a();
            return true;
        }
        String[] strArrSplit = strM1274a.split(";");
        int length = strArrSplit.length;
        int i2 = 0;
        while (i2 < length) {
            String str = strArrSplit[i2];
            if (TextUtils.isEmpty(str)) {
                return z;
            }
            String[] strArrSplit2 = str.split("_");
            if (strArrSplit2.length < 2) {
                sb = new StringBuilder();
            } else {
                try {
                    long jLongValue = Long.valueOf(strArrSplit2[0]).longValue();
                    if (i == Integer.valueOf(strArrSplit2[1]).intValue()) {
                        return false;
                    }
                    if (j - jLongValue >= 900) {
                        sb = new StringBuilder();
                    } else {
                        i2++;
                        z = true;
                    }
                } catch (Exception unused) {
                    return false;
                }
            }
            sb.append(str);
            sb.append(";");
            strM1274a = strM1274a.replace(sb.toString(), "");
            i2++;
            z = true;
        }
        this.f1146f.m1276a("pushInfo", strM1274a + j + "_" + i + ";");
        this.f1146f.m1275a();
        return true;
    }

    /* JADX INFO: renamed from: a */
    static /* synthetic */ boolean m923a(LBSTraceService lBSTraceService, boolean z) {
        lBSTraceService.f1149i = false;
        return false;
    }

    /* JADX INFO: renamed from: b */
    static /* synthetic */ int m924b(LBSTraceService lBSTraceService) {
        if (lBSTraceService.f1144d == null) {
            return 56;
        }
        if (!C0843bd.f1679d) {
            return 57;
        }
        C0843bd.f1679d = false;
        lBSTraceService.f1146f.m1276a("is_gather", String.valueOf(false));
        lBSTraceService.f1146f.m1275a();
        return 55;
    }

    /* JADX INFO: renamed from: b */
    private void m925b(boolean z, boolean z2) {
        C0843bd c0843bd = this.f1144d;
        if (c0843bd != null) {
            c0843bd.m1200a(z, z2);
        } else {
            m920a(z, z2);
        }
    }

    /* JADX INFO: renamed from: c */
    static /* synthetic */ int m926c(LBSTraceService lBSTraceService) {
        if (lBSTraceService.f1144d == null) {
            return 53;
        }
        if (C0843bd.f1679d) {
            return 54;
        }
        C0843bd.f1679d = true;
        lBSTraceService.f1146f.m1276a("is_gather", String.valueOf(true));
        lBSTraceService.f1146f.m1275a();
        return 52;
    }

    /* JADX INFO: renamed from: f */
    private void m929f(int i) {
        IListener iListener = this.f1148h;
        if (iListener != null) {
            try {
                iListener.startTraceCallback(19);
            } catch (RemoteException unused) {
            }
            this.f1149i = false;
        } else {
            this.f1149i = true;
        }
        this.f1154n = C0855f.b.LOGGING;
        if (this.f1150j) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NetworkChangedReceiver.ACTION_GLOBAL_CONNECTIVITY_CHANGE);
        intentFilter.addAction("com.baidu.trace.action.SOCKET_RECONNECT");
        registerReceiver(this.f1151k, intentFilter);
        this.f1150j = true;
    }

    /* JADX INFO: renamed from: i */
    private void m930i() {
        TraceJniInterface.m951a().socketDisconnection();
        C0832d c0832d = this.f1143c;
        if (c0832d != null) {
            c0832d.m1145b();
        }
    }

    /* JADX INFO: renamed from: a */
    protected final void m931a() {
        if (C0855f.b.LOGOUTING == this.f1154n) {
            m930i();
            return;
        }
        if (this.f1150j) {
            unregisterReceiver(this.f1151k);
            this.f1150j = false;
        }
        C0832d.m1136a(false);
        C0843bd c0843bd = this.f1144d;
        if (c0843bd != null) {
            c0843bd.m1199a(0L);
        }
        if (C0855f.b.LOGOUTING == this.f1154n) {
            m930i();
            return;
        }
        C0825ax.m1115a();
        C0824aw.m1110b();
        if (!m935a((short) 16, C0854e.m1224a(), C0855f.a.f1751a)) {
            m932a(10000);
            return;
        }
        C0832d c0832d = this.f1143c;
        if (c0832d != null) {
            c0832d.m1150g();
        }
    }

    /* JADX INFO: renamed from: a */
    protected final void m932a(int i) {
        IListener iListener = this.f1148h;
        if (iListener != null) {
            try {
                iListener.startTraceCallback(i);
            } catch (RemoteException unused) {
            }
        }
        this.f1154n = C0855f.b.NOT_START;
        TraceJniInterface.m951a().socketDisconnection();
        C0832d c0832d = this.f1143c;
        if (c0832d != null) {
            c0832d.m1145b();
        }
        m946g();
        C0843bd c0843bd = this.f1144d;
        if (c0843bd != null) {
            c0843bd.m1198a();
            if (this.f1144d.f1686a != null) {
                this.f1144d.f1686a.m1093c();
            }
            this.f1144d.m1201c();
            this.f1144d = null;
        }
        C0843bd.m1181b();
    }

    /* JADX INFO: renamed from: a */
    protected final void m933a(int i, int i2) {
        if (i >= C0819ar.f1577c && i2 >= 2) {
            C0819ar.f1576b = i * 1000;
            C0843bd.f1683i = i2 * 1000;
            this.f1146f.m1276a("gather_interval", String.valueOf(i));
            this.f1146f.m1276a("pack_interval", String.valueOf(i2));
            this.f1146f.m1275a();
        }
        C0843bd.m1187d();
        Trace.m949a(i, i2);
    }

    /* JADX INFO: renamed from: a */
    protected final void m934a(Message message) {
        IListener iListener;
        C0832d c0832d;
        Bundle data = message.getData();
        int i = data.getInt("pushId");
        byte b = data.getByte("infoType");
        String string = data.getString("infoContent");
        long j = data.getLong("timeStamp");
        byte[] bArrM1117a = C0825ax.m1117a((short) 13, C0854e.m1224a());
        if (bArrM1117a != null && (c0832d = this.f1143c) != null) {
            c0832d.m1144a(bArrM1117a, (C0855f.a) null);
        }
        if (!m921a(i, j) || (iListener = this.f1148h) == null) {
            return;
        }
        try {
            iListener.pushCallback(b, string);
        } catch (RemoteException unused) {
        }
    }

    /* JADX INFO: renamed from: a */
    protected final boolean m935a(short s, int i, C0855f.a aVar) {
        if (18 == s && !TextUtils.isEmpty(C0861g.f1785a) && !TextUtils.isEmpty(C0861g.f1787c) && !TextUtils.isEmpty(C0861g.f1788d)) {
            return true;
        }
        byte[] bArrM1117a = C0825ax.m1117a(s, i);
        if (bArrM1117a == null) {
            return false;
        }
        C0832d c0832d = this.f1143c;
        if (c0832d != null) {
            c0832d.m1144a(bArrM1117a, aVar);
        }
        return true;
    }

    /* JADX INFO: renamed from: b */
    protected final void m936b() {
        if (C0855f.b.LOGOUTING == this.f1154n) {
            m930i();
        } else {
            if (m935a((short) 17, C0854e.m1224a(), C0855f.a.f1752b)) {
                return;
            }
            m932a(10000);
        }
    }

    /* JADX INFO: renamed from: b */
    protected final void m937b(Message message) {
        IListener iListener = this.f1148h;
        if (iListener != null) {
            try {
                iListener.pushCallback((byte) 4, (String) message.obj);
            } catch (RemoteException unused) {
            }
        }
    }

    /* JADX INFO: renamed from: b */
    protected final boolean m938b(int i) {
        C0814am.m1061a(i);
        SharedPreferences.Editor editorEdit = PreferenceManager.getDefaultSharedPreferences(this.f1145e).edit();
        editorEdit.putInt("cacheSize", i);
        editorEdit.apply();
        return true;
    }

    /* JADX INFO: renamed from: c */
    protected final void m939c() {
        if (C0855f.b.LOGOUTING == this.f1154n) {
            m930i();
            return;
        }
        this.f1154n = C0855f.b.STARTED;
        C0832d c0832d = this.f1143c;
        if (c0832d != null) {
            c0832d.m1149f();
            this.f1143c.m1143a(C0881z.f1868c);
        }
        C0843bd c0843bd = this.f1144d;
        if (c0843bd != null) {
            c0843bd.m1200a(true, false);
        } else {
            m920a(true, false);
        }
        IListener iListener = this.f1148h;
        if (iListener != null) {
            try {
                iListener.startTraceCallback(124);
            } catch (RemoteException unused) {
            }
        }
        if (this.f1147g) {
            m935a((short) 18, C0854e.m1224a(), null);
        }
    }

    /* JADX INFO: renamed from: c */
    protected final boolean m940c(int i) {
        if (i <= 0) {
            return false;
        }
        Trace.m950b(i);
        C0819ar.f1578d = i;
        this.f1146f.m1276a("loc_time_offset", String.valueOf(i));
        this.f1146f.m1275a();
        return true;
    }

    /* JADX INFO: renamed from: d */
    protected final void m941d() {
        TraceJniInterface.m951a().socketDisconnection();
        C0832d c0832d = this.f1143c;
        if (c0832d != null) {
            c0832d.m1145b();
        }
        C0854e.f1740d = 0;
        if (C0855f.b.LOGOUTING == this.f1154n) {
            m944e(0);
            return;
        }
        m925b(false, true);
        if (!C0832d.m1138a(this.f1145e)) {
            m929f(19);
        } else {
            this.f1154n = C0855f.b.LOGGING;
            this.f1143c.m1146c();
        }
    }

    /* JADX INFO: renamed from: d */
    protected final boolean m942d(int i) {
        C0843bd c0843bd = this.f1144d;
        if (c0843bd == null || c0843bd.f1686a == null) {
            return false;
        }
        this.f1144d.f1686a.m1090a(i);
        return true;
    }

    /* JADX INFO: renamed from: e */
    protected final void m943e() {
        C0832d.m1136a(false);
        if (C0855f.b.LOGOUTING == this.f1154n) {
            m944e(0);
        } else {
            m929f(19);
            m925b(false, true);
        }
    }

    /* JADX INFO: renamed from: e */
    protected final void m944e(int i) {
        IListener iListener;
        m946g();
        if (this.f1150j) {
            unregisterReceiver(this.f1151k);
            this.f1150j = false;
        }
        this.f1154n = C0855f.b.NOT_START;
        C0854e.f1740d = 0;
        if (C0843bd.m1188e() > 0) {
            C0814am.m1072a(true);
        }
        if (!PreferenceManager.getDefaultSharedPreferences(this.f1145e).getBoolean("same_process", true)) {
            C0814am.m1060a();
        }
        try {
            if (this.f1148h != null) {
                int i2 = IMAP.DEFAULT_PORT;
                if (143 == i || C0843bd.f1682h || 141 != i) {
                    iListener = this.f1148h;
                } else {
                    iListener = this.f1148h;
                    i2 = 142;
                }
                iListener.stopTraceCallback(i2);
            }
        } catch (RemoteException unused) {
        }
        stopSelf();
    }

    /* JADX INFO: renamed from: f */
    protected final void m945f() throws RemoteException {
        if (C0855f.b.NOT_START == this.f1154n) {
            IListener iListener = this.f1148h;
            if (iListener != null) {
                iListener.stopTraceCallback(27);
                return;
            }
            return;
        }
        C0832d c0832d = this.f1143c;
        if (c0832d != null) {
            c0832d.m1148e();
        }
        if (C0855f.b.LOGOUTING == this.f1154n) {
            this.f1144d.m1200a(false, false);
            C0843bd.f1679d = false;
            this.f1148h.stopTraceCallback(182);
        }
        this.f1154n = C0855f.b.LOGOUTING;
        this.f1144d.m1200a(false, false);
        C0843bd.f1679d = false;
        this.f1144d.f1687e = true;
    }

    /* JADX INFO: renamed from: g */
    protected final void m946g() {
        this.f1146f.m1276a("client", null);
        this.f1146f.m1276a("gather_interval", null);
        this.f1146f.m1276a("pack_interval", null);
        this.f1146f.m1276a("loc_time_offset", null);
        this.f1146f.m1275a();
    }

    public final IListener getClientListener() {
        return this.f1148h;
    }

    public final int getLocationMode() {
        return this.f1152l;
    }

    public final Context getServiceContext() {
        return this.f1145e;
    }

    public final HandlerC0790a getTraceHandler() {
        return this.f1141a;
    }

    /* JADX INFO: renamed from: h */
    protected final void m947h() {
        if (C0855f.b.STARTED == this.f1154n || C0855f.b.LOGOUTING == this.f1154n || this.f1143c == null) {
            return;
        }
        this.f1154n = C0855f.b.LOGGING;
        this.f1143c.m1145b();
        this.f1143c.m1146c();
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return this.f1142b;
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        Context applicationContext = getApplicationContext();
        this.f1145e = applicationContext;
        this.f1146f = new C0874s(applicationContext);
        C0823av.m1096a(this.f1145e);
        C0842bc.m1163a().m1167a(this.f1141a);
        C0832d c0832dM1134a = C0832d.m1134a();
        this.f1143c = c0832dM1134a;
        c0832dM1134a.m1142a(this.f1141a, this.f1145e);
        if (C0858d.f1768b == null) {
            C0858d.f1768b = LBSAuthManager.getInstance(this.f1145e);
        }
        C0872q.m1272a(this.f1145e, C0858d.f1768b);
        C0854e.m1226a(this.f1145e);
        C0814am.m1063a(this.f1145e);
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.f1145e);
        if (defaultSharedPreferences.contains("cacheSize")) {
            C0814am.m1061a(defaultSharedPreferences.getInt("cacheSize", 0));
        }
        String str = C0854e.f1739c;
        C0857h.m1246b();
        if (C0858d.f1767a == null) {
            C0858d.m1248a();
        }
    }

    @Override // android.app.Service
    public final void onDestroy() {
        super.onDestroy();
        if (this.f1143c != null && C0855f.b.LOGOUTING != this.f1154n) {
            this.f1143c.m1145b();
        }
        if (this.f1150j) {
            unregisterReceiver(this.f1151k);
            this.f1150j = false;
        }
        try {
            stopForeground(true);
        } catch (Exception unused) {
        }
        C0843bd c0843bd = this.f1144d;
        if (c0843bd != null) {
            c0843bd.m1198a();
            if (this.f1144d.f1686a != null) {
                this.f1144d.f1686a.m1093c();
            }
            this.f1144d.m1201c();
            this.f1144d = null;
        }
        C0832d c0832d = this.f1143c;
        if (c0832d != null) {
            c0832d.m1145b();
            this.f1143c = null;
        }
        C0874s c0874s = this.f1146f;
        if (c0874s != null) {
            c0874s.m1277b();
            this.f1146f = null;
        }
        if (!PreferenceManager.getDefaultSharedPreferences(this.f1145e).getBoolean("same_process", true)) {
            C0858d.m1249b();
        }
        C0825ax.m1118b();
        C0849c.m1202b();
        C0861g.m1261b();
        C0824aw.m1113d();
        C0843bd.m1181b();
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x02c8 A[PHI: r2 r3
  0x02c8: PHI (r2v3 java.util.HashMap<java.lang.String, java.lang.String>) = 
  (r2v2 java.util.HashMap<java.lang.String, java.lang.String>)
  (r2v6 java.util.HashMap<java.lang.String, java.lang.String>)
 binds: [B:114:0x02c6, B:106:0x02b8] A[DONT_GENERATE, DONT_INLINE]
  0x02c8: PHI (r3v3 java.lang.String) = (r3v2 java.lang.String), (r3v7 java.lang.String) binds: [B:114:0x02c6, B:106:0x02b8] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0131  */
    @Override // android.app.Service
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int onStartCommand(android.content.Intent r27, int r28, int r29) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1133
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.trace.LBSTraceService.onStartCommand(android.content.Intent, int, int):int");
    }

    @Override // android.app.Service
    public final boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public final void setClientListener(IListener iListener) {
        this.f1148h = iListener;
    }
}
