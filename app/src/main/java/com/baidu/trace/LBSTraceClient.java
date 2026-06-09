package com.baidu.trace;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.text.TextUtils;
import com.baidu.lbsapi.auth.tracesdk.LBSAuthManager;
import com.baidu.trace.IListener;
import com.baidu.trace.api.analysis.DrivingBehaviorRequest;
import com.baidu.trace.api.analysis.OnAnalysisListener;
import com.baidu.trace.api.analysis.StayPointRequest;
import com.baidu.trace.api.bos.BosGeneratePresignedUrlRequest;
import com.baidu.trace.api.bos.BosGetObjectRequest;
import com.baidu.trace.api.bos.BosPutObjectRequest;
import com.baidu.trace.api.bos.OnBosListener;
import com.baidu.trace.api.entity.AddEntityRequest;
import com.baidu.trace.api.entity.AroundSearchRequest;
import com.baidu.trace.api.entity.BoundSearchRequest;
import com.baidu.trace.api.entity.DeleteEntityRequest;
import com.baidu.trace.api.entity.DistrictSearchRequest;
import com.baidu.trace.api.entity.EntityListRequest;
import com.baidu.trace.api.entity.LocRequest;
import com.baidu.trace.api.entity.OnEntityListener;
import com.baidu.trace.api.entity.PolygonSearchRequest;
import com.baidu.trace.api.entity.SearchRequest;
import com.baidu.trace.api.entity.UpdateEntityRequest;
import com.baidu.trace.api.fence.AlarmPoint;
import com.baidu.trace.api.fence.CreateFenceRequest;
import com.baidu.trace.api.fence.DeleteFenceRequest;
import com.baidu.trace.api.fence.FenceAlarmPushInfo;
import com.baidu.trace.api.fence.FenceListRequest;
import com.baidu.trace.api.fence.HistoryAlarmRequest;
import com.baidu.trace.api.fence.MonitoredAction;
import com.baidu.trace.api.fence.MonitoredStatusByLocationRequest;
import com.baidu.trace.api.fence.MonitoredStatusRequest;
import com.baidu.trace.api.fence.OnFenceListener;
import com.baidu.trace.api.fence.UpdateFenceRequest;
import com.baidu.trace.api.track.AddPointRequest;
import com.baidu.trace.api.track.AddPointsRequest;
import com.baidu.trace.api.track.ClearCacheTrackRequest;
import com.baidu.trace.api.track.ClearCacheTrackResponse;
import com.baidu.trace.api.track.DistanceRequest;
import com.baidu.trace.api.track.HistoryTrackRequest;
import com.baidu.trace.api.track.LatestPointRequest;
import com.baidu.trace.api.track.OnTrackListener;
import com.baidu.trace.api.track.QueryCacheTrackRequest;
import com.baidu.trace.api.track.QueryCacheTrackResponse;
import com.baidu.trace.model.CoordType;
import com.baidu.trace.model.LatLng;
import com.baidu.trace.model.LocType;
import com.baidu.trace.model.LocationMode;
import com.baidu.trace.model.OnCustomAttributeListener;
import com.baidu.trace.model.OnTraceListener;
import com.baidu.trace.model.ProtocolType;
import com.baidu.trace.model.PushMessage;
import com.baidu.trace.model.StatusCodes;
import com.baidu.trace.model.TraceLocation;
import com.baidu.trace.p012c.C0850a;
import com.baidu.trace.p012c.C0854e;
import com.baidu.trace.p012c.C0857h;
import com.jieli.stream.p016dv.running2.util.IConstant;
import com.tencent.tauth.AuthActivity;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class LBSTraceClient {

    /* JADX INFO: renamed from: a */
    protected static LBSTraceClient f1128a;

    /* JADX INFO: renamed from: b */
    private Context f1129b;

    /* JADX INFO: renamed from: e */
    private ClientListener f1132e;

    /* JADX INFO: renamed from: c */
    private Intent f1130c = null;

    /* JADX INFO: renamed from: d */
    private IService f1131d = null;

    /* JADX INFO: renamed from: f */
    private HandlerC0788a f1133f = new HandlerC0788a(this);

    /* JADX INFO: renamed from: g */
    private LocationMode f1134g = LocationMode.High_Accuracy;

    /* JADX INFO: renamed from: h */
    private OnTraceListener f1135h = null;

    /* JADX INFO: renamed from: i */
    private OnCustomAttributeListener f1136i = null;

    /* JADX INFO: renamed from: j */
    private boolean f1137j = false;

    /* JADX INFO: renamed from: k */
    private C0819ar f1138k = null;

    /* JADX INFO: renamed from: l */
    private ServiceConnection f1139l = new ServiceConnectionC0817ap(this);

    static class ClientListener extends IListener.Stub {
        private WeakReference<LBSTraceClient> traceClient;

        public ClientListener(LBSTraceClient lBSTraceClient) {
            this.traceClient = null;
            this.traceClient = new WeakReference<>(lBSTraceClient);
        }

        private void sendMessage(int i) {
            Message message = new Message();
            message.what = i;
            WeakReference<LBSTraceClient> weakReference = this.traceClient;
            if (weakReference == null || weakReference.get().f1133f == null) {
                return;
            }
            this.traceClient.get().f1133f.sendMessage(message);
        }

        @Override // com.baidu.trace.IListener
        public void extendedOperationCallback(int i, String str) throws RemoteException {
            int i2 = 172;
            if (i != 0) {
                sendMessage(172);
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                C0861g.f1785a = jSONObject.getString("accessKey");
                C0861g.f1787c = jSONObject.getString("secretKey");
                C0861g.f1788d = jSONObject.getString("token");
                C0861g.f1786b = jSONObject.getLong("expireTime");
                C0862h.m1263a();
                i2 = 171;
            } catch (JSONException unused) {
            }
            sendMessage(i2);
        }

        @Override // com.baidu.trace.IListener
        public Map<String, String> gatherAttribute(long j) throws RemoteException {
            LBSTraceClient lBSTraceClient = this.traceClient.get();
            if (lBSTraceClient == null || lBSTraceClient.f1136i == null) {
                return null;
            }
            C0791a.m995a("LC-GPS locTime is: " + j);
            Map<String, String> mapOnTrackAttributeCallback = lBSTraceClient.f1136i.onTrackAttributeCallback(j);
            return mapOnTrackAttributeCallback == null ? lBSTraceClient.f1136i.onTrackAttributeCallback() : mapOnTrackAttributeCallback;
        }

        @Override // com.baidu.trace.IListener
        public void pushCallback(byte b, String str) throws RemoteException {
            Message message = new Message();
            message.arg1 = b;
            message.obj = str;
            message.what = IConstant.OP_EXIT_EDIT_MODE;
            WeakReference<LBSTraceClient> weakReference = this.traceClient;
            if (weakReference == null || weakReference.get().f1133f == null) {
                return;
            }
            this.traceClient.get().f1133f.sendMessage(message);
        }

        @Override // com.baidu.trace.IListener
        public void startGatherCallback(int i) throws RemoteException {
            sendMessage(i);
        }

        @Override // com.baidu.trace.IListener
        public void startTraceCallback(int i) throws RemoteException {
            sendMessage(i);
        }

        @Override // com.baidu.trace.IListener
        public void stopGatherCallback(int i) throws RemoteException {
            sendMessage(i);
        }

        @Override // com.baidu.trace.IListener
        public void stopTraceCallback(int i) throws RemoteException {
            sendMessage(i);
        }
    }

    /* JADX INFO: renamed from: com.baidu.trace.LBSTraceClient$a */
    static class HandlerC0788a extends Handler {

        /* JADX INFO: renamed from: a */
        private WeakReference<LBSTraceClient> f1140a;

        public HandlerC0788a(LBSTraceClient lBSTraceClient) {
            this.f1140a = null;
            this.f1140a = new WeakReference<>(lBSTraceClient);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            PushMessage pushMessage;
            JSONObject jSONObject;
            String str;
            CoordType coordType;
            Class cls;
            MonitoredAction monitoredActionValueOf;
            super.handleMessage(message);
            LBSTraceClient lBSTraceClient = this.f1140a.get();
            if (lBSTraceClient == null) {
                C0791a.m995a("LBSTraceClient instance is null");
                return;
            }
            int i = message.what;
            if (i == 18) {
                if (lBSTraceClient.f1135h != null) {
                    lBSTraceClient.f1135h.onStartTraceCallback(10003, StatusCodes.MSG_START_TRACE_NETWORK_CONNECT_FAILED);
                    return;
                }
                return;
            }
            if (i == 19) {
                if (lBSTraceClient.f1135h != null) {
                    lBSTraceClient.f1135h.onStartTraceCallback(10004, StatusCodes.MSG_START_TRACE_NETWORK_CLOSED);
                    return;
                }
                return;
            }
            if (i == 22) {
                if (lBSTraceClient.f1135h != null) {
                    lBSTraceClient.f1135h.onStartTraceCallback(10005, StatusCodes.MSG_TRACE_STARTING);
                    return;
                }
                return;
            }
            if (i == 25) {
                if (lBSTraceClient.f1135h != null) {
                    lBSTraceClient.f1135h.onStartTraceCallback(10006, StatusCodes.MSG_TRACE_STARTED);
                    return;
                }
                return;
            }
            if (i == 27) {
                if (lBSTraceClient.f1135h != null) {
                    lBSTraceClient.f1135h.onStopTraceCallback(StatusCodes.NOT_START_TRACE, "服务未开启");
                    return;
                }
                return;
            }
            if (i == 124) {
                if (lBSTraceClient.f1135h != null) {
                    lBSTraceClient.f1135h.onStartTraceCallback(0, StatusCodes.MSG_SUCCESS);
                    return;
                }
                return;
            }
            if (i == 152) {
                if (lBSTraceClient.f1135h != null) {
                    lBSTraceClient.f1135h.onStopTraceCallback(StatusCodes.STOP_TRACE_FAILED, StatusCodes.MSG_STOP_TRACE_FAILED);
                    return;
                }
                return;
            }
            if (i == 162) {
                if (lBSTraceClient.f1135h == null) {
                    return;
                }
                byte b = (byte) message.arg1;
                String str2 = (String) message.obj;
                PushMessage pushMessage2 = new PushMessage();
                FenceAlarmPushInfo fenceAlarmPushInfo = new FenceAlarmPushInfo();
                try {
                    jSONObject = new JSONObject(str2);
                    if (jSONObject.has("fence_id")) {
                        str = "longitude";
                        fenceAlarmPushInfo.setFenceId(jSONObject.getLong("fence_id"));
                    } else {
                        str = "longitude";
                    }
                    if (jSONObject.has("fence")) {
                        fenceAlarmPushInfo.setFenceName(jSONObject.getString("fence"));
                    }
                    if (jSONObject.has("monitored_person")) {
                        fenceAlarmPushInfo.setMonitoredPerson(jSONObject.getString("monitored_person"));
                    }
                } catch (Exception unused) {
                    pushMessage = pushMessage2;
                }
                if (jSONObject.has(AuthActivity.ACTION_KEY)) {
                    if (3 == b) {
                        int i2 = jSONObject.getInt(AuthActivity.ACTION_KEY);
                        if (i2 == 1) {
                            monitoredActionValueOf = MonitoredAction.enter;
                        } else if (i2 == 2) {
                            monitoredActionValueOf = MonitoredAction.exit;
                        }
                        fenceAlarmPushInfo.setMonitoredAction(monitoredActionValueOf);
                    } else if (4 == b) {
                        monitoredActionValueOf = MonitoredAction.valueOf(jSONObject.getString(AuthActivity.ACTION_KEY));
                        fenceAlarmPushInfo.setMonitoredAction(monitoredActionValueOf);
                    }
                    pushMessage.setFenceAlarmPushInfo(fenceAlarmPushInfo);
                    lBSTraceClient.f1135h.onPushCallback(b, pushMessage);
                    return;
                }
                AlarmPoint alarmPoint = new AlarmPoint();
                AlarmPoint alarmPoint2 = new AlarmPoint();
                if (jSONObject.has("time")) {
                    alarmPoint.setLocTime(jSONObject.getLong("time"));
                }
                if (jSONObject.has("create_time")) {
                    alarmPoint.setCreateTime(jSONObject.getLong("create_time"));
                }
                if (jSONObject.has("latitude") && jSONObject.has(str)) {
                    pushMessage = pushMessage2;
                    try {
                        alarmPoint.setLocation(new LatLng(jSONObject.getDouble("latitude"), jSONObject.getDouble(str)));
                    } catch (Exception unused2) {
                    }
                } else {
                    pushMessage = pushMessage2;
                }
                if (jSONObject.has("coord_type")) {
                    CoordType coordType2 = CoordType.bd09ll;
                    int i3 = jSONObject.getInt("coord_type");
                    if (i3 == 1) {
                        coordType2 = CoordType.wgs84;
                    } else if (i3 == 2) {
                        coordType2 = CoordType.gcj02;
                    } else if (i3 == 3) {
                        coordType2 = CoordType.bd09ll;
                    }
                    alarmPoint.setCoordType(coordType2);
                }
                if (jSONObject.has("radius")) {
                    alarmPoint.setRadius(jSONObject.getDouble("radius"));
                }
                if (jSONObject.has("cur_point")) {
                    C0791a.m1008a(jSONObject.getJSONObject("cur_point"), CoordType.bd09ll, alarmPoint, String.class);
                }
                fenceAlarmPushInfo.setCurrentPoint(alarmPoint);
                if (jSONObject.has("pre_point")) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("pre_point");
                    if (3 == b) {
                        coordType = CoordType.bd09ll;
                        cls = Integer.class;
                    } else {
                        if (4 == b) {
                            coordType = CoordType.bd09ll;
                            cls = String.class;
                        }
                        fenceAlarmPushInfo.setPrePoint(alarmPoint2);
                    }
                    C0791a.m1008a(jSONObject2, coordType, alarmPoint2, cls);
                    fenceAlarmPushInfo.setPrePoint(alarmPoint2);
                }
                pushMessage.setFenceAlarmPushInfo(fenceAlarmPushInfo);
                lBSTraceClient.f1135h.onPushCallback(b, pushMessage);
                return;
            }
            if (i == 1241) {
                if (lBSTraceClient.f1135h != null) {
                    lBSTraceClient.f1135h.onStartTraceCallback(10001, StatusCodes.MSG_START_TRACE_FAILED);
                    return;
                }
                return;
            }
            if (i == 10000) {
                if (lBSTraceClient.f1135h != null) {
                    lBSTraceClient.f1135h.onStartTraceCallback(10000, "请求发送失败");
                    return;
                }
                return;
            }
            if (i == 142 || i == 143) {
                try {
                    try {
                        lBSTraceClient.f1131d.unregisterListener();
                        lBSTraceClient.f1129b.unbindService(lBSTraceClient.f1139l);
                        lBSTraceClient.f1129b.stopService(lBSTraceClient.f1130c);
                        if (lBSTraceClient.f1135h != null) {
                            if (142 == message.what) {
                                lBSTraceClient.f1135h.onStopTraceCallback(0, StatusCodes.MSG_SUCCESS);
                            } else {
                                lBSTraceClient.f1135h.onStopTraceCallback(StatusCodes.CACHE_TRACK_NOT_UPLOAD, StatusCodes.MSG_CACHE_TRACK_NOT_UPLOAD_);
                            }
                        }
                        lBSTraceClient.f1131d = null;
                    } catch (Exception e) {
                        StringWriter stringWriter = new StringWriter();
                        e.printStackTrace(new PrintWriter(stringWriter));
                        C0791a.m995a("unbind and stop LBSTraceService failed, Exception : " + stringWriter.toString());
                        if (lBSTraceClient.f1135h != null) {
                            lBSTraceClient.f1135h.onStopTraceCallback(StatusCodes.STOP_TRACE_FAILED, StatusCodes.MSG_STOP_TRACE_FAILED);
                        }
                    }
                    return;
                } finally {
                    C0857h.m1244a();
                }
            }
            if (i == 171) {
                if (lBSTraceClient.f1135h != null) {
                    lBSTraceClient.f1135h.onInitBOSCallback(0, StatusCodes.MSG_SUCCESS);
                    return;
                }
                return;
            }
            if (i == 172) {
                if (lBSTraceClient.f1135h != null) {
                    lBSTraceClient.f1135h.onInitBOSCallback(1, StatusCodes.MSG_FAILED);
                    return;
                }
                return;
            }
            if (i == 181) {
                if (lBSTraceClient.f1135h != null) {
                    lBSTraceClient.f1135h.onStartTraceCallback(10007, "服务正在停止");
                    return;
                }
                return;
            }
            if (i == 182) {
                if (lBSTraceClient.f1135h != null) {
                    lBSTraceClient.f1135h.onStopTraceCallback(StatusCodes.STOPPING_TRACE, "服务正在停止");
                    return;
                }
                return;
            }
            switch (i) {
                case 52:
                    if (lBSTraceClient.f1135h != null) {
                        lBSTraceClient.f1135h.onStartGatherCallback(0, StatusCodes.MSG_SUCCESS);
                        return;
                    }
                    return;
                case 53:
                    if (lBSTraceClient.f1135h != null) {
                        lBSTraceClient.f1135h.onStartGatherCallback(StatusCodes.START_GATHER_FAILED, StatusCodes.MSG_START_GATHER_FAILED);
                        return;
                    }
                    return;
                case 54:
                    if (lBSTraceClient.f1135h != null) {
                        lBSTraceClient.f1135h.onStartGatherCallback(StatusCodes.GATHER_STARTED, StatusCodes.MSG_GATHER_STARTED);
                        return;
                    }
                    return;
                case 55:
                    if (lBSTraceClient.f1135h != null) {
                        lBSTraceClient.f1135h.onStopGatherCallback(StatusCodes.GATHER_STOPPED, StatusCodes.MSG_GATHER_STOPPED);
                        return;
                    }
                    return;
                case 56:
                    if (lBSTraceClient.f1135h != null) {
                        lBSTraceClient.f1135h.onStopGatherCallback(StatusCodes.STOP_GATHER_FAILED, StatusCodes.MSG_STOP_GATHER_FAILED);
                        return;
                    }
                    return;
                case 57:
                    if (lBSTraceClient.f1135h != null) {
                        lBSTraceClient.f1135h.onStopGatherCallback(0, StatusCodes.MSG_SUCCESS);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public LBSTraceClient(Context context) {
        this.f1132e = null;
        this.f1129b = context.getApplicationContext();
        this.f1132e = new ClientListener(this);
        m911c();
    }

    /* JADX INFO: renamed from: b */
    private void m909b() {
        try {
            this.f1129b.startService(this.f1130c);
            this.f1129b.bindService(this.f1130c, this.f1139l, 1);
        } catch (Exception e) {
            StringWriter stringWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(stringWriter));
            C0791a.m995a("start and bind service failed, Exception : " + stringWriter.toString());
            OnTraceListener onTraceListener = this.f1135h;
            if (onTraceListener != null) {
                onTraceListener.onBindServiceCallback(1, StatusCodes.MSG_FAILED);
            }
        }
    }

    /* JADX INFO: renamed from: c */
    private void m911c() {
        if (this.f1137j) {
            return;
        }
        this.f1137j = true;
        C0823av.m1096a(this.f1129b);
        C0858d.f1768b = LBSAuthManager.getInstance(this.f1129b);
        C0872q.m1272a(this.f1129b, C0858d.f1768b);
        C0854e.m1226a(this.f1129b);
        C0850a.m1206a(this.f1129b, LBSTraceClient.class);
        C0814am.m1063a(this.f1129b);
        String str = C0854e.f1739c;
        C0857h.m1246b();
        C0858d.m1248a();
    }

    /* JADX INFO: renamed from: a */
    protected final void m917a() {
        try {
            this.f1131d.handleExtendedOperate(0);
        } catch (Exception unused) {
        }
    }

    public final void addEntity(AddEntityRequest addEntityRequest, OnEntityListener onEntityListener) {
        if (C0791a.m1015a("addEntity", addEntityRequest, onEntityListener)) {
            C0791a.m970a(addEntityRequest, onEntityListener);
        }
    }

    public final void addPoint(AddPointRequest addPointRequest, OnTrackListener onTrackListener) {
        if (C0791a.m1015a("addPoint", addPointRequest, onTrackListener)) {
            C0791a.m983a(addPointRequest, onTrackListener);
        }
    }

    public final void addPoints(AddPointsRequest addPointsRequest, OnTrackListener onTrackListener) {
        if (C0791a.m1015a("addPoints", addPointsRequest, onTrackListener)) {
            C0791a.m984a(addPointsRequest, onTrackListener);
        }
    }

    public final void aroundSearchEntity(AroundSearchRequest aroundSearchRequest, OnEntityListener onEntityListener) {
        if (C0791a.m1015a("aroundSearchEntity", aroundSearchRequest, onEntityListener)) {
            C0791a.m971a(aroundSearchRequest, onEntityListener);
        }
    }

    public final void boundSearchEntity(BoundSearchRequest boundSearchRequest, OnEntityListener onEntityListener) {
        if (C0791a.m1015a("boundSearchEntity", boundSearchRequest, onEntityListener)) {
            C0791a.m972a(boundSearchRequest, onEntityListener);
        }
    }

    public final void clear() {
        C0814am.m1060a();
        C0850a.m1205a();
        C0858d.m1249b();
        C0819ar c0819ar = this.f1138k;
        if (c0819ar != null) {
            c0819ar.m1093c();
            this.f1138k = null;
        }
        f1128a = null;
        this.f1137j = false;
    }

    public final void clearCacheTrack(ClearCacheTrackRequest clearCacheTrackRequest, OnTrackListener onTrackListener) {
        if (C0791a.m1015a("clearCacheTrack", clearCacheTrackRequest, onTrackListener)) {
            Context context = this.f1129b;
            HandlerC0788a handlerC0788a = this.f1133f;
            ClearCacheTrackResponse clearCacheTrackResponse = new ClearCacheTrackResponse(clearCacheTrackRequest.getTag(), 0, StatusCodes.MSG_SUCCESS);
            C0850a.f1715a.execute(new RunnableC0846bg(context, clearCacheTrackRequest.getEntityNames(), clearCacheTrackRequest.getCacheTrackInfos(), clearCacheTrackResponse, handlerC0788a, onTrackListener));
        }
    }

    public final void createFence(CreateFenceRequest createFenceRequest, OnFenceListener onFenceListener) {
        if (C0791a.m1015a("createFence", createFenceRequest, onFenceListener)) {
            C0791a.m962a(this.f1129b, this.f1131d, this.f1133f, createFenceRequest, onFenceListener);
        }
    }

    public final void deleteEntity(DeleteEntityRequest deleteEntityRequest, OnEntityListener onEntityListener) {
        if (C0791a.m1015a("deleteEntity", deleteEntityRequest, onEntityListener)) {
            C0791a.m974a(deleteEntityRequest, onEntityListener);
        }
    }

    public final void deleteFence(DeleteFenceRequest deleteFenceRequest, OnFenceListener onFenceListener) {
        if (C0791a.m1015a("deleteFence", deleteFenceRequest, onFenceListener)) {
            C0791a.m963a(this.f1129b, this.f1131d, this.f1133f, deleteFenceRequest, onFenceListener);
        }
    }

    public final void districtSearchEntity(DistrictSearchRequest districtSearchRequest, OnEntityListener onEntityListener) {
        if (C0791a.m1015a("districtSearchRequest", districtSearchRequest, onEntityListener)) {
            C0791a.m975a(districtSearchRequest, onEntityListener);
        }
    }

    public final void generatePresignedUrl(BosGeneratePresignedUrlRequest bosGeneratePresignedUrlRequest, OnBosListener onBosListener) {
        if (C0791a.m1015a("generatePresignedUrl", bosGeneratePresignedUrlRequest, onBosListener)) {
            C0791a.m967a(bosGeneratePresignedUrlRequest, onBosListener);
        }
    }

    public final void getObject(BosGetObjectRequest bosGetObjectRequest, OnBosListener onBosListener) {
        if (C0791a.m1015a("getObject", bosGetObjectRequest, onBosListener)) {
            C0791a.m968a(bosGetObjectRequest, onBosListener);
        }
    }

    public final boolean initThreadPoolConfig(int i, int i2, int i3) {
        return C0850a.m1208a(i, i2, i3);
    }

    public final void polygonSearchEntity(PolygonSearchRequest polygonSearchRequest, OnEntityListener onEntityListener) {
        if (C0791a.m1015a("polygonSearchRequest", polygonSearchRequest, onEntityListener)) {
            C0791a.m978a(polygonSearchRequest, onEntityListener);
        }
    }

    public final void putObject(BosPutObjectRequest bosPutObjectRequest, OnBosListener onBosListener) {
        if (C0791a.m1015a("putObject", bosPutObjectRequest, onBosListener)) {
            C0791a.m969a(bosPutObjectRequest, onBosListener);
        }
    }

    public final void queryCacheTrack(QueryCacheTrackRequest queryCacheTrackRequest, OnTrackListener onTrackListener) {
        if (C0791a.m1015a("queryCacheTrack", queryCacheTrackRequest, onTrackListener)) {
            Context context = this.f1129b;
            HandlerC0788a handlerC0788a = this.f1133f;
            C0850a.f1715a.execute(new RunnableC0844be(context, queryCacheTrackRequest, new QueryCacheTrackResponse(queryCacheTrackRequest.getTag(), 0, StatusCodes.MSG_SUCCESS), handlerC0788a, onTrackListener));
        }
    }

    public final void queryDistance(DistanceRequest distanceRequest, OnTrackListener onTrackListener) {
        if (C0791a.m1015a("queryDistance", distanceRequest, onTrackListener)) {
            C0791a.m985a(distanceRequest, onTrackListener);
        }
    }

    public final void queryDrivingBehavior(DrivingBehaviorRequest drivingBehaviorRequest, OnAnalysisListener onAnalysisListener) {
        if (C0791a.m1015a("queryDrivingBehavior", drivingBehaviorRequest, onAnalysisListener)) {
            C0791a.m965a(drivingBehaviorRequest, onAnalysisListener);
        }
    }

    public final void queryEntityList(EntityListRequest entityListRequest, OnEntityListener onEntityListener) {
        if (C0791a.m1015a("queryEntityList", entityListRequest, onEntityListener)) {
            C0791a.m976a(entityListRequest, onEntityListener);
        }
    }

    public final void queryFenceHistoryAlarmInfo(HistoryAlarmRequest historyAlarmRequest, OnFenceListener onFenceListener) {
        if (C0791a.m1015a("queryFenceHistoryAlarmInfo", historyAlarmRequest, onFenceListener)) {
            C0791a.m959a(this.f1129b, this.f1133f, historyAlarmRequest, onFenceListener);
        }
    }

    public final void queryFenceList(FenceListRequest fenceListRequest, OnFenceListener onFenceListener) {
        if (C0791a.m1015a("queryFenceList", fenceListRequest, onFenceListener)) {
            C0791a.m958a(this.f1129b, this.f1133f, fenceListRequest, onFenceListener);
        }
    }

    public final void queryHistoryTrack(HistoryTrackRequest historyTrackRequest, OnTrackListener onTrackListener) {
        if (C0791a.m1015a("queryHistoryTrack", historyTrackRequest, onTrackListener)) {
            C0791a.m986a(historyTrackRequest, onTrackListener);
        }
    }

    public final void queryLatestPoint(LatestPointRequest latestPointRequest, OnTrackListener onTrackListener) {
        if (C0791a.m1015a("queryLatestPoint", latestPointRequest, onTrackListener)) {
            C0791a.m987a(latestPointRequest, onTrackListener);
        }
    }

    public final void queryMonitoredStatus(MonitoredStatusRequest monitoredStatusRequest, OnFenceListener onFenceListener) {
        if (C0791a.m1015a("queryMonitoredStatus", monitoredStatusRequest, onFenceListener)) {
            C0791a.m961a(this.f1129b, this.f1133f, monitoredStatusRequest, onFenceListener);
        }
    }

    public final void queryMonitoredStatusByLocation(MonitoredStatusByLocationRequest monitoredStatusByLocationRequest, OnFenceListener onFenceListener) {
        if (C0791a.m1015a("queryMonitoredStatusByLocation", monitoredStatusByLocationRequest, onFenceListener)) {
            C0791a.m960a(this.f1129b, this.f1133f, monitoredStatusByLocationRequest, onFenceListener);
        }
    }

    public final void queryRealTimeLoc(LocRequest locRequest, OnEntityListener onEntityListener) {
        if (C0791a.m1015a("queryRealTimeLoc", locRequest, onEntityListener)) {
            if (this.f1138k == null) {
                synchronized (LBSTraceClient.class) {
                    if (this.f1138k == null) {
                        this.f1138k = new C0819ar(this.f1129b, this.f1133f);
                    }
                }
            }
            TraceLocation traceLocation = new TraceLocation(locRequest.getTag(), 0, StatusCodes.MSG_SUCCESS, "", "", "", 0.0d, 0.0d, CoordType.bd09ll, 0.0d, 0.0f, 0.0f, 0, "");
            if (!this.f1138k.m1091a(this.f1129b, this.f1133f, traceLocation)) {
                C0802aa.m1033a(this.f1129b, locRequest, onEntityListener);
            } else {
                if (locRequest.isCanceled()) {
                    return;
                }
                traceLocation.setLocType(LocType.GPS);
                onEntityListener.onReceiveLocation(traceLocation);
            }
        }
    }

    public final void queryStayPoint(StayPointRequest stayPointRequest, OnAnalysisListener onAnalysisListener) {
        if (C0791a.m1015a("queryStayPoint", stayPointRequest, onAnalysisListener)) {
            C0791a.m966a(stayPointRequest, onAnalysisListener);
        }
    }

    public final void searchEntity(SearchRequest searchRequest, OnEntityListener onEntityListener) {
        if (C0791a.m1015a("searchEntity", searchRequest, onEntityListener)) {
            C0791a.m979a(searchRequest, onEntityListener);
        }
    }

    public final boolean setCacheSize(int i) {
        if (i < 50) {
            return false;
        }
        IService iService = this.f1131d;
        if (iService == null) {
            if (!Trace.m948a(i)) {
                return false;
            }
            C0814am.m1061a(i);
            return true;
        }
        try {
            if (iService.setCacheSize(i)) {
                C0814am.m1061a(i);
                return true;
            }
        } catch (Exception unused) {
        }
        return false;
    }

    public final boolean setInterval(int i, int i2) {
        if (i > 0 && i <= 300 && i2 >= i && i2 >= 2 && i2 <= 300 && i2 % i == 0) {
            boolean zM949a = Trace.m949a(i, i2);
            IService iService = this.f1131d;
            if (iService == null) {
                return zM949a;
            }
            try {
                return iService.setInterval(i, i2);
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public final boolean setLocTimeOffset(int i) {
        if (i <= 0) {
            return false;
        }
        boolean zM950b = Trace.m950b(i);
        IService iService = this.f1131d;
        if (iService == null) {
            return zM950b;
        }
        try {
            return iService.setLocTimeOffset(i);
        } catch (Exception unused) {
            return false;
        }
    }

    public final LBSTraceClient setLocationMode(LocationMode locationMode) {
        if (locationMode == null) {
            locationMode = LocationMode.High_Accuracy;
        }
        this.f1134g = locationMode;
        IService iService = this.f1131d;
        if (iService == null) {
            return this;
        }
        try {
            iService.setLocationMode(locationMode.ordinal());
        } catch (Exception unused) {
        }
        return this;
    }

    public final void setOnCustomAttributeListener(OnCustomAttributeListener onCustomAttributeListener) {
        this.f1136i = onCustomAttributeListener;
    }

    public final void setOnTraceListener(OnTraceListener onTraceListener) {
        this.f1135h = onTraceListener;
    }

    public final LBSTraceClient setProtocolType(ProtocolType protocolType) {
        if (protocolType == null) {
            protocolType = ProtocolType.HTTPS;
        }
        C0850a.f1716b = protocolType;
        IService iService = this.f1131d;
        if (iService == null) {
            return this;
        }
        try {
            iService.setProtocolType(C0850a.f1716b.ordinal());
        } catch (Exception unused) {
        }
        return this;
    }

    public final void startGather(OnTraceListener onTraceListener) {
        OnTraceListener onTraceListener2;
        int i;
        String str;
        if (onTraceListener != null) {
            this.f1135h = onTraceListener;
        }
        IService iService = this.f1131d;
        if (iService == null) {
            if (C0854e.m1229a(this.f1129b, LBSTraceService.class.getName())) {
                if (this.f1130c == null) {
                    this.f1130c = new Intent(this.f1129b, (Class<?>) LBSTraceService.class);
                }
                this.f1130c.putExtra("operateType", 1);
                m909b();
                return;
            }
            OnTraceListener onTraceListener3 = this.f1135h;
            if (onTraceListener3 != null) {
                onTraceListener3.onStartGatherCallback(StatusCodes.START_GATHER_NOT_STARTED, "服务未开启");
                return;
            }
            return;
        }
        try {
            int iStartGather = iService.startGather();
            if (this.f1135h == null) {
                return;
            }
            if (52 == iStartGather) {
                onTraceListener2 = this.f1135h;
                i = 0;
                str = StatusCodes.MSG_SUCCESS;
            } else if (54 != iStartGather) {
                this.f1135h.onStartGatherCallback(StatusCodes.START_GATHER_FAILED, StatusCodes.MSG_START_GATHER_FAILED);
                return;
            } else {
                onTraceListener2 = this.f1135h;
                i = StatusCodes.GATHER_STARTED;
                str = StatusCodes.MSG_GATHER_STARTED;
            }
            onTraceListener2.onStartGatherCallback(i, str);
        } catch (Exception unused) {
            if (this.f1135h == null) {
                return;
            }
            if (C0854e.m1229a(this.f1129b, LBSTraceService.class.getName())) {
                this.f1135h.onStartGatherCallback(StatusCodes.START_GATHER_REQUEST_FAILED, "请求发送失败");
            } else {
                this.f1135h.onStartGatherCallback(StatusCodes.START_GATHER_NOT_STARTED, "服务未开启");
            }
        }
    }

    public final void startTrace(Trace trace, OnTraceListener onTraceListener) {
        String str;
        if (trace == null) {
            C0791a.m1005a("BaiduTraceSDK", "Trace instance is null");
            return;
        }
        if (onTraceListener != null) {
            this.f1135h = onTraceListener;
        }
        try {
            m911c();
            if (f1128a == null) {
                f1128a = this;
            }
            long serviceId = trace.getServiceId();
            if (serviceId > 0) {
                C0881z.f1867b = serviceId;
                String key = C0858d.f1768b.getKey();
                if (!TextUtils.isEmpty(key)) {
                    String entityName = trace.getEntityName();
                    if (C0854e.m1231a(entityName)) {
                        if (this.f1130c == null) {
                            this.f1130c = new Intent(this.f1129b, (Class<?>) LBSTraceService.class);
                        }
                        Bundle bundle = new Bundle();
                        bundle.putInt("locationMode", this.f1134g.ordinal());
                        bundle.putInt("protocolType", C0850a.f1716b.ordinal());
                        bundle.putInt("gatherInterval", Trace.f1157a);
                        bundle.putInt("packInterval", Trace.f1158b);
                        bundle.putInt("locTimeOffset", Trace.f1160d);
                        bundle.putLong("serviceId", serviceId);
                        bundle.putBoolean("isNeedObjectStorage", trace.isNeedObjectStorage());
                        bundle.putString("ak", key);
                        bundle.putString("entityName", entityName);
                        bundle.putString("mcode", C0858d.f1768b.getMCode());
                        bundle.putString("pcn", C0854e.m1238c(this.f1129b));
                        if (50 <= Trace.f1159c) {
                            bundle.putInt("cacheSize", Trace.f1159c);
                        }
                        if (trace.getNotification() != null) {
                            bundle.putParcelable("notification", trace.getNotification());
                        }
                        this.f1130c.putExtra("initData", bundle);
                        this.f1130c.putExtra("operateType", 0);
                        this.f1130c.putExtra("processID", Process.myPid());
                        m909b();
                        return;
                    }
                    if (this.f1135h == null) {
                        return;
                    }
                    this.f1135h.onStartTraceCallback(10002, "entity_name参数错误");
                    str = "entityName is empty string or null";
                } else {
                    if (this.f1135h == null) {
                        return;
                    }
                    this.f1135h.onStartTraceCallback(10002, "ak参数错误");
                    str = "ak is empty string or null";
                }
            } else {
                if (this.f1135h == null) {
                    return;
                }
                this.f1135h.onStartTraceCallback(10002, "service_id参数错误");
                str = "serviceId is invalid";
            }
            C0791a.m1005a("BaiduTraceSDK", str);
        } catch (Exception unused) {
            OnTraceListener onTraceListener2 = this.f1135h;
            if (onTraceListener2 != null) {
                onTraceListener2.onStartTraceCallback(10000, "请求发送失败");
            }
        }
    }

    public final void stopGather(OnTraceListener onTraceListener) {
        OnTraceListener onTraceListener2;
        int i;
        String str;
        if (onTraceListener != null) {
            this.f1135h = onTraceListener;
        }
        IService iService = this.f1131d;
        if (iService == null) {
            if (C0854e.m1229a(this.f1129b, LBSTraceService.class.getName())) {
                if (this.f1130c == null) {
                    this.f1130c = new Intent(this.f1129b, (Class<?>) LBSTraceService.class);
                }
                this.f1130c.putExtra("operateType", 2);
                m909b();
                return;
            }
            OnTraceListener onTraceListener3 = this.f1135h;
            if (onTraceListener3 != null) {
                onTraceListener3.onStopGatherCallback(StatusCodes.STOP_GATHER_NOT_STARTED, "服务未开启");
                return;
            }
            return;
        }
        try {
            int iStopGather = iService.stopGather();
            if (this.f1135h == null) {
                return;
            }
            if (55 == iStopGather) {
                onTraceListener2 = this.f1135h;
                i = 0;
                str = StatusCodes.MSG_SUCCESS;
            } else if (57 != iStopGather) {
                this.f1135h.onStopGatherCallback(StatusCodes.STOP_GATHER_FAILED, StatusCodes.MSG_STOP_GATHER_FAILED);
                return;
            } else {
                onTraceListener2 = this.f1135h;
                i = StatusCodes.GATHER_STOPPED;
                str = StatusCodes.MSG_GATHER_STOPPED;
            }
            onTraceListener2.onStopGatherCallback(i, str);
        } catch (Exception unused) {
            if (this.f1135h == null) {
                return;
            }
            if (C0854e.m1229a(this.f1129b, LBSTraceService.class.getName())) {
                this.f1135h.onStartGatherCallback(StatusCodes.STOP_GATHER_REQUEST_FAILED, "请求发送失败");
            } else {
                this.f1135h.onStopGatherCallback(StatusCodes.STOP_GATHER_NOT_STARTED, "服务未开启");
            }
        }
    }

    public final void stopRealTimeLoc() {
        C0819ar c0819ar = this.f1138k;
        if (c0819ar != null) {
            c0819ar.m1092b();
        }
    }

    public final void stopTrace(Trace trace, OnTraceListener onTraceListener) {
        if (trace == null) {
            C0791a.m1005a("BaiduTraceSDK", "Trace instance is null");
            return;
        }
        if (onTraceListener != null) {
            this.f1135h = onTraceListener;
        }
        IService iService = this.f1131d;
        if (iService == null) {
            OnTraceListener onTraceListener2 = this.f1135h;
            if (onTraceListener2 != null) {
                onTraceListener2.onStopTraceCallback(StatusCodes.NOT_START_TRACE, "服务未开启");
                return;
            }
            return;
        }
        try {
            iService.stopTrace(trace.getServiceId(), trace.getEntityName());
        } catch (Exception unused) {
            OnTraceListener onTraceListener3 = this.f1135h;
            if (onTraceListener3 != null) {
                onTraceListener3.onStopTraceCallback(StatusCodes.STOP_TRACE_REQUEST_FAILED, "请求发送失败");
            }
        }
    }

    public final void updateEntity(UpdateEntityRequest updateEntityRequest, OnEntityListener onEntityListener) {
        if (C0791a.m1015a("updateEntity", updateEntityRequest, onEntityListener)) {
            C0791a.m980a(updateEntityRequest, onEntityListener);
        }
    }

    public final void updateFence(UpdateFenceRequest updateFenceRequest, OnFenceListener onFenceListener) {
        if (C0791a.m1015a("updateFence", updateFenceRequest, onFenceListener)) {
            C0791a.m964a(this.f1129b, this.f1131d, this.f1133f, updateFenceRequest, onFenceListener);
        }
    }
}
