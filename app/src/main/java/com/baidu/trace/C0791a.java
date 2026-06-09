package com.baidu.trace;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.trace.api.analysis.DrivingBehaviorRequest;
import com.baidu.trace.api.analysis.DrivingBehaviorResponse;
import com.baidu.trace.api.analysis.EndPoint;
import com.baidu.trace.api.analysis.HarshAccelerationPoint;
import com.baidu.trace.api.analysis.HarshBreakingPoint;
import com.baidu.trace.api.analysis.HarshSteeringPoint;
import com.baidu.trace.api.analysis.OnAnalysisListener;
import com.baidu.trace.api.analysis.SpeedingInfo;
import com.baidu.trace.api.analysis.SpeedingPoint;
import com.baidu.trace.api.analysis.StartPoint;
import com.baidu.trace.api.analysis.StayPoint;
import com.baidu.trace.api.analysis.StayPointRequest;
import com.baidu.trace.api.analysis.StayPointResponse;
import com.baidu.trace.api.analysis.ThresholdOption;
import com.baidu.trace.api.analysis.TurnType;
import com.baidu.trace.api.bos.BosGeneratePresignedUrlRequest;
import com.baidu.trace.api.bos.BosGeneratePresignedUrlResponse;
import com.baidu.trace.api.bos.BosGetObjectRequest;
import com.baidu.trace.api.bos.BosGetObjectResponse;
import com.baidu.trace.api.bos.BosPutObjectRequest;
import com.baidu.trace.api.bos.BosPutObjectResponse;
import com.baidu.trace.api.bos.OnBosListener;
import com.baidu.trace.api.entity.AddEntityRequest;
import com.baidu.trace.api.entity.AddEntityResponse;
import com.baidu.trace.api.entity.AroundSearchRequest;
import com.baidu.trace.api.entity.BoundSearchRequest;
import com.baidu.trace.api.entity.CommonRequest;
import com.baidu.trace.api.entity.CommonResponse;
import com.baidu.trace.api.entity.DeleteEntityRequest;
import com.baidu.trace.api.entity.DeleteEntityResponse;
import com.baidu.trace.api.entity.DistrictSearchRequest;
import com.baidu.trace.api.entity.DistrictSearchResponse;
import com.baidu.trace.api.entity.EntityInfo;
import com.baidu.trace.api.entity.EntityListRequest;
import com.baidu.trace.api.entity.LatestLocation;
import com.baidu.trace.api.entity.LocRequest;
import com.baidu.trace.api.entity.OnEntityListener;
import com.baidu.trace.api.entity.PolygonSearchRequest;
import com.baidu.trace.api.entity.SearchRequest;
import com.baidu.trace.api.entity.UpdateEntityRequest;
import com.baidu.trace.api.entity.UpdateEntityResponse;
import com.baidu.trace.api.fence.AlarmPoint;
import com.baidu.trace.api.fence.CircleFence;
import com.baidu.trace.api.fence.CreateFenceRequest;
import com.baidu.trace.api.fence.CreateFenceResponse;
import com.baidu.trace.api.fence.DeleteFenceRequest;
import com.baidu.trace.api.fence.DeleteFenceResponse;
import com.baidu.trace.api.fence.DistrictFence;
import com.baidu.trace.api.fence.Fence;
import com.baidu.trace.api.fence.FenceAlarmInfo;
import com.baidu.trace.api.fence.FenceInfo;
import com.baidu.trace.api.fence.FenceListRequest;
import com.baidu.trace.api.fence.FenceListResponse;
import com.baidu.trace.api.fence.FenceShape;
import com.baidu.trace.api.fence.FenceType;
import com.baidu.trace.api.fence.HistoryAlarmRequest;
import com.baidu.trace.api.fence.HistoryAlarmResponse;
import com.baidu.trace.api.fence.MonitoredAction;
import com.baidu.trace.api.fence.MonitoredStatus;
import com.baidu.trace.api.fence.MonitoredStatusByLocationRequest;
import com.baidu.trace.api.fence.MonitoredStatusByLocationResponse;
import com.baidu.trace.api.fence.MonitoredStatusInfo;
import com.baidu.trace.api.fence.MonitoredStatusRequest;
import com.baidu.trace.api.fence.MonitoredStatusResponse;
import com.baidu.trace.api.fence.OnFenceListener;
import com.baidu.trace.api.fence.PolygonFence;
import com.baidu.trace.api.fence.PolylineFence;
import com.baidu.trace.api.fence.UpdateFenceRequest;
import com.baidu.trace.api.fence.UpdateFenceResponse;
import com.baidu.trace.api.track.AddPointRequest;
import com.baidu.trace.api.track.AddPointResponse;
import com.baidu.trace.api.track.AddPointsRequest;
import com.baidu.trace.api.track.AddPointsResponse;
import com.baidu.trace.api.track.DistanceRequest;
import com.baidu.trace.api.track.DistanceResponse;
import com.baidu.trace.api.track.FailInfo;
import com.baidu.trace.api.track.HistoryTrackRequest;
import com.baidu.trace.api.track.HistoryTrackResponse;
import com.baidu.trace.api.track.LatestPoint;
import com.baidu.trace.api.track.LatestPointRequest;
import com.baidu.trace.api.track.LatestPointResponse;
import com.baidu.trace.api.track.OnTrackListener;
import com.baidu.trace.api.track.SupplementMode;
import com.baidu.trace.api.track.TrackPoint;
import com.baidu.trace.model.BaseRequest;
import com.baidu.trace.model.BaseResponse;
import com.baidu.trace.model.CoordType;
import com.baidu.trace.model.LatLng;
import com.baidu.trace.model.Point;
import com.baidu.trace.model.ProcessOption;
import com.baidu.trace.model.SortType;
import com.baidu.trace.model.StatusCodes;
import com.baidu.trace.p012c.C0850a;
import com.baidu.trace.p012c.C0854e;
import com.baidu.trace.p012c.C0857h;
import com.bumptech.glide.load.Key;
import com.jieli.lib.p015dv.control.utils.TopicKey;
import com.tencent.tauth.AuthActivity;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import kotlin.UByte;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* JADX INFO: renamed from: com.baidu.trace.a */
/* JADX INFO: loaded from: classes.dex */
public class C0791a {

    /* JADX INFO: renamed from: a */
    public int f1167a;

    /* JADX INFO: renamed from: b */
    public int f1168b;

    /* JADX INFO: renamed from: c */
    public int f1169c;

    /* JADX INFO: renamed from: d */
    public int f1170d;

    /* JADX INFO: renamed from: e */
    public char f1171e;

    private C0791a(C0860f c0860f) {
        this.f1167a = -1;
        this.f1168b = -1;
        this.f1169c = -1;
        this.f1170d = -1;
        this.f1171e = (char) 0;
    }

    /* synthetic */ C0791a(C0860f c0860f, byte b) {
        this(c0860f);
    }

    /* JADX INFO: renamed from: a */
    protected static String m954a(List<Long> list) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Long l : list) {
            if (l != null) {
                stringBuffer.append(l);
                stringBuffer.append(",");
            }
        }
        return TextUtils.isEmpty(stringBuffer.toString()) ? "" : stringBuffer.substring(0, stringBuffer.length() - 1);
    }

    /* JADX INFO: renamed from: a */
    protected static String m955a(TreeMap<String, String> treeMap) throws UnsupportedEncodingException {
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, String> entry : treeMap.entrySet()) {
            stringBuffer.append(entry.getKey());
            stringBuffer.append("=");
            stringBuffer.append(entry.getValue());
            stringBuffer.append("&");
        }
        if (stringBuffer.length() > 0) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        return stringBuffer.toString();
    }

    /* JADX INFO: renamed from: a */
    protected static String m956a(TreeMap<String, String> treeMap, long j, C0850a.c cVar) {
        StringBuffer stringBuffer = new StringBuffer();
        treeMap.put("service_id", String.valueOf(j));
        treeMap.put("os", C0872q.f1847a);
        treeMap.put("sdk_version", C0872q.f1848b);
        treeMap.put("pcn", C0872q.f1850d);
        treeMap.put("token", C0858d.f1767a);
        try {
            treeMap.put("cuid", URLEncoder.encode(C0872q.f1849c, Key.STRING_CHARSET_NAME));
        } catch (Exception unused) {
        }
        try {
            String strM955a = m955a(treeMap);
            byte[] bArrDigest = MessageDigest.getInstance("MD5").digest(new String(strM955a + "99754106633f94d350db34d548d6091a").getBytes());
            StringBuffer stringBuffer2 = new StringBuffer();
            for (byte b : bArrDigest) {
                stringBuffer2.append(Integer.toHexString((b & UByte.MAX_VALUE) | 256).substring(1, 3));
            }
            if (C0850a.c.f1726b == cVar) {
                strM955a = m1022b(treeMap);
            }
            stringBuffer.append(strM955a);
            stringBuffer.append("&sign");
            stringBuffer.append("=");
            stringBuffer.append(stringBuffer2.toString());
        } catch (Exception unused2) {
        }
        return stringBuffer.toString();
    }

    /* JADX INFO: renamed from: a */
    protected static void m957a(int i, Handler handler, BaseResponse baseResponse, OnFenceListener onFenceListener) {
        handler.post(new RunnableC0810ai(i, onFenceListener, baseResponse));
    }

    /* JADX INFO: renamed from: a */
    protected static void m958a(Context context, Handler handler, FenceListRequest fenceListRequest, OnFenceListener onFenceListener) {
        if (FenceType.server != fenceListRequest.getFenceType()) {
            C0850a.f1715a.execute(new RunnableC0806ae(context, handler, fenceListRequest, onFenceListener));
            return;
        }
        TreeMap treeMap = new TreeMap();
        if (!TextUtils.isEmpty(fenceListRequest.getMonitoredPerson())) {
            try {
                treeMap.put("monitored_person", URLEncoder.encode(fenceListRequest.getMonitoredPerson(), Key.STRING_CHARSET_NAME));
            } catch (Exception unused) {
                treeMap.put("monitored_person", fenceListRequest.getMonitoredPerson());
            }
        }
        if (fenceListRequest.getFenceIds() != null && !fenceListRequest.getFenceIds().isEmpty()) {
            treeMap.put("fence_ids", m954a(fenceListRequest.getFenceIds()));
        }
        if (fenceListRequest.getCoordTypeOutput() != null) {
            treeMap.put("coord_type_output", fenceListRequest.getCoordTypeOutput().name());
        }
        C0850a.m1207a(fenceListRequest, 0, "fence/list", C0850a.c.f1725a, 4, m956a((TreeMap<String, String>) treeMap, fenceListRequest.getServiceId(), C0850a.c.f1725a), onFenceListener);
        treeMap.clear();
    }

    /* JADX INFO: renamed from: a */
    protected static void m959a(Context context, Handler handler, HistoryAlarmRequest historyAlarmRequest, OnFenceListener onFenceListener) {
        if (FenceType.server != historyAlarmRequest.getFenceType()) {
            C0850a.f1715a.execute(new RunnableC0809ah(context, handler, historyAlarmRequest, onFenceListener));
            return;
        }
        TreeMap treeMap = new TreeMap();
        if (!TextUtils.isEmpty(historyAlarmRequest.getMonitoredPerson())) {
            try {
                treeMap.put("monitored_person", URLEncoder.encode(historyAlarmRequest.getMonitoredPerson(), Key.STRING_CHARSET_NAME));
            } catch (Exception unused) {
                treeMap.put("monitored_person", historyAlarmRequest.getMonitoredPerson());
            }
        }
        if (historyAlarmRequest.getStartTime() > 0) {
            treeMap.put("start_time", String.valueOf(historyAlarmRequest.getStartTime()));
        }
        if (historyAlarmRequest.getEndTime() > 0) {
            treeMap.put("end_time", String.valueOf(historyAlarmRequest.getEndTime()));
        }
        if (historyAlarmRequest.getFenceIds() != null && !historyAlarmRequest.getFenceIds().isEmpty()) {
            treeMap.put("fence_ids", m954a(historyAlarmRequest.getFenceIds()));
        }
        if (historyAlarmRequest.getCoordTypeOutput() != null) {
            treeMap.put("coord_type_output", historyAlarmRequest.getCoordTypeOutput().name());
        }
        C0850a.m1207a(historyAlarmRequest, 0, "fence/historyalarm", C0850a.c.f1725a, 7, m956a((TreeMap<String, String>) treeMap, historyAlarmRequest.getServiceId(), C0850a.c.f1725a), onFenceListener);
        treeMap.clear();
    }

    /* JADX INFO: renamed from: a */
    protected static void m960a(Context context, Handler handler, MonitoredStatusByLocationRequest monitoredStatusByLocationRequest, OnFenceListener onFenceListener) {
        if (FenceType.server != monitoredStatusByLocationRequest.getFenceType()) {
            C0850a.f1715a.execute(new RunnableC0808ag(context, handler, monitoredStatusByLocationRequest, onFenceListener));
            return;
        }
        TreeMap treeMap = new TreeMap();
        if (!TextUtils.isEmpty(monitoredStatusByLocationRequest.getMonitoredPerson())) {
            try {
                treeMap.put("monitored_person", URLEncoder.encode(monitoredStatusByLocationRequest.getMonitoredPerson(), Key.STRING_CHARSET_NAME));
            } catch (Exception unused) {
                treeMap.put("monitored_person", monitoredStatusByLocationRequest.getMonitoredPerson());
            }
        }
        if (monitoredStatusByLocationRequest.getFenceIds() != null && !monitoredStatusByLocationRequest.getFenceIds().isEmpty()) {
            treeMap.put("fence_ids", m954a(monitoredStatusByLocationRequest.getFenceIds()));
        }
        if (monitoredStatusByLocationRequest.getLatLng() != null) {
            treeMap.put("longitude", String.valueOf(monitoredStatusByLocationRequest.getLatLng().longitude));
            treeMap.put("latitude", String.valueOf(monitoredStatusByLocationRequest.getLatLng().latitude));
        }
        if (monitoredStatusByLocationRequest.getCoordType() != null) {
            treeMap.put("coord_type", String.valueOf(monitoredStatusByLocationRequest.getCoordType().name()));
        }
        C0850a.m1207a(monitoredStatusByLocationRequest, 0, "fence/querystatusbylocation", C0850a.c.f1725a, 6, m956a((TreeMap<String, String>) treeMap, monitoredStatusByLocationRequest.getServiceId(), C0850a.c.f1725a), onFenceListener);
        treeMap.clear();
    }

    /* JADX INFO: renamed from: a */
    protected static void m961a(Context context, Handler handler, MonitoredStatusRequest monitoredStatusRequest, OnFenceListener onFenceListener) {
        if (FenceType.server != monitoredStatusRequest.getFenceType()) {
            C0850a.f1715a.execute(new RunnableC0807af(context, handler, monitoredStatusRequest, onFenceListener));
            return;
        }
        TreeMap treeMap = new TreeMap();
        if (!TextUtils.isEmpty(monitoredStatusRequest.getMonitoredPerson())) {
            try {
                treeMap.put("monitored_person", URLEncoder.encode(monitoredStatusRequest.getMonitoredPerson(), Key.STRING_CHARSET_NAME));
            } catch (Exception unused) {
                treeMap.put("monitored_person", monitoredStatusRequest.getMonitoredPerson());
            }
        }
        if (monitoredStatusRequest.getFenceIds() != null && !monitoredStatusRequest.getFenceIds().isEmpty()) {
            treeMap.put("fence_ids", m954a(monitoredStatusRequest.getFenceIds()));
        }
        C0850a.m1207a(monitoredStatusRequest, 0, "fence/querystatus", C0850a.c.f1725a, 5, m956a((TreeMap<String, String>) treeMap, monitoredStatusRequest.getServiceId(), C0850a.c.f1725a), onFenceListener);
        treeMap.clear();
    }

    /* JADX INFO: renamed from: a */
    protected static void m962a(Context context, IService iService, Handler handler, CreateFenceRequest createFenceRequest, OnFenceListener onFenceListener) {
        if (FenceType.server != createFenceRequest.getFence().getFenceType()) {
            C0850a.f1715a.execute(new RunnableC0803ab(context, handler, createFenceRequest, onFenceListener, iService));
            return;
        }
        Fence fence = createFenceRequest.getFence();
        if (fence instanceof CircleFence) {
            CircleFence circleFence = (CircleFence) fence;
            m988a(createFenceRequest, 1, FenceShape.circle, 0L, circleFence.getFenceName(), circleFence.getMonitoredPerson(), (String) null, circleFence.getCenter(), circleFence.getRadius(), (List<LatLng>) null, 0, circleFence.getCoordType(), circleFence.getDenoise(), onFenceListener);
            return;
        }
        if (fence instanceof PolygonFence) {
            PolygonFence polygonFence = (PolygonFence) fence;
            m988a(createFenceRequest, 1, FenceShape.polygon, 0L, polygonFence.getFenceName(), polygonFence.getMonitoredPerson(), (String) null, (LatLng) null, 0.0d, polygonFence.getVertexes(), 0, polygonFence.getCoordType(), polygonFence.getDenoise(), onFenceListener);
        } else if (fence instanceof PolylineFence) {
            PolylineFence polylineFence = (PolylineFence) fence;
            m988a(createFenceRequest, 1, FenceShape.polyline, 0L, polylineFence.getFenceName(), polylineFence.getMonitoredPerson(), (String) null, (LatLng) null, 0.0d, polylineFence.getVertexes(), polylineFence.getOffset(), polylineFence.getCoordType(), polylineFence.getDenoise(), onFenceListener);
        } else if (fence instanceof DistrictFence) {
            DistrictFence districtFence = (DistrictFence) fence;
            m988a(createFenceRequest, 1, FenceShape.district, 0L, districtFence.getFenceName(), districtFence.getMonitoredPerson(), districtFence.getKeyword(), (LatLng) null, 0.0d, (List<LatLng>) null, 0, (CoordType) null, districtFence.getDenoise(), onFenceListener);
        }
    }

    /* JADX INFO: renamed from: a */
    protected static void m963a(Context context, IService iService, Handler handler, DeleteFenceRequest deleteFenceRequest, OnFenceListener onFenceListener) {
        if (FenceType.server != deleteFenceRequest.getFenceType()) {
            C0850a.f1715a.execute(new RunnableC0805ad(context, handler, deleteFenceRequest, onFenceListener, iService));
            return;
        }
        TreeMap treeMap = new TreeMap();
        if (deleteFenceRequest.getFenceIds() != null && !deleteFenceRequest.getFenceIds().isEmpty()) {
            treeMap.put("fence_ids", m954a(deleteFenceRequest.getFenceIds()));
        }
        if (!TextUtils.isEmpty(deleteFenceRequest.getMonitoredPerson())) {
            try {
                treeMap.put("monitored_person", URLEncoder.encode(deleteFenceRequest.getMonitoredPerson(), Key.STRING_CHARSET_NAME));
            } catch (Exception unused) {
                treeMap.put("monitored_person", deleteFenceRequest.getMonitoredPerson());
            }
        }
        C0850a.m1207a(deleteFenceRequest, 0, "fence/delete", C0850a.c.f1726b, 3, m956a((TreeMap<String, String>) treeMap, deleteFenceRequest.getServiceId(), C0850a.c.f1726b), onFenceListener);
        treeMap.clear();
    }

    /* JADX INFO: renamed from: a */
    protected static void m964a(Context context, IService iService, Handler handler, UpdateFenceRequest updateFenceRequest, OnFenceListener onFenceListener) {
        if (FenceType.server != updateFenceRequest.getFence().getFenceType()) {
            C0850a.f1715a.execute(new RunnableC0804ac(context, handler, updateFenceRequest, onFenceListener, iService));
            return;
        }
        Fence fence = updateFenceRequest.getFence();
        if (fence instanceof CircleFence) {
            CircleFence circleFence = (CircleFence) fence;
            if (FenceType.server == circleFence.getFenceType()) {
                m988a(updateFenceRequest, 2, FenceShape.circle, circleFence.getFenceId(), circleFence.getFenceName(), circleFence.getMonitoredPerson(), (String) null, circleFence.getCenter(), circleFence.getRadius(), (List<LatLng>) null, 0, circleFence.getCoordType(), circleFence.getDenoise(), onFenceListener);
                return;
            }
            return;
        }
        if (fence instanceof PolygonFence) {
            PolygonFence polygonFence = (PolygonFence) fence;
            m988a(updateFenceRequest, 2, FenceShape.polygon, polygonFence.getFenceId(), polygonFence.getFenceName(), polygonFence.getMonitoredPerson(), (String) null, (LatLng) null, 0.0d, polygonFence.getVertexes(), 0, polygonFence.getCoordType(), polygonFence.getDenoise(), onFenceListener);
        } else if (fence instanceof PolylineFence) {
            PolylineFence polylineFence = (PolylineFence) fence;
            m988a(updateFenceRequest, 2, FenceShape.polyline, polylineFence.getFenceId(), polylineFence.getFenceName(), polylineFence.getMonitoredPerson(), (String) null, (LatLng) null, 0.0d, polylineFence.getVertexes(), polylineFence.getOffset(), polylineFence.getCoordType(), polylineFence.getDenoise(), onFenceListener);
        } else if (fence instanceof DistrictFence) {
            DistrictFence districtFence = (DistrictFence) fence;
            m988a(updateFenceRequest, 2, FenceShape.district, districtFence.getFenceId(), districtFence.getFenceName(), districtFence.getMonitoredPerson(), districtFence.getKeyword(), (LatLng) null, 0.0d, (List<LatLng>) null, 0, (CoordType) null, districtFence.getDenoise(), onFenceListener);
        }
    }

    /* JADX INFO: renamed from: a */
    protected static void m965a(DrivingBehaviorRequest drivingBehaviorRequest, OnAnalysisListener onAnalysisListener) {
        m993a(drivingBehaviorRequest, drivingBehaviorRequest.getEntityName(), drivingBehaviorRequest.getServiceId(), drivingBehaviorRequest.getStartTime(), drivingBehaviorRequest.getEndTime(), "analysis/drivingbehavior", 2, 0, 0, drivingBehaviorRequest.getSpeedingThreshold(), drivingBehaviorRequest.getThresholdOption(), drivingBehaviorRequest.getProcessOption(), drivingBehaviorRequest.getCoordTypeOutput(), onAnalysisListener);
    }

    /* JADX INFO: renamed from: a */
    protected static void m966a(StayPointRequest stayPointRequest, OnAnalysisListener onAnalysisListener) {
        m993a(stayPointRequest, stayPointRequest.getEntityName(), stayPointRequest.getServiceId(), stayPointRequest.getStartTime(), stayPointRequest.getEndTime(), "analysis/staypoint", 1, stayPointRequest.getStayTime(), stayPointRequest.getStayRadius(), 0, (ThresholdOption) null, stayPointRequest.getProcessOption(), stayPointRequest.getCoordTypeOutput(), onAnalysisListener);
    }

    /* JADX INFO: renamed from: a */
    protected static void m967a(BosGeneratePresignedUrlRequest bosGeneratePresignedUrlRequest, OnBosListener onBosListener) {
        if (TextUtils.isEmpty(bosGeneratePresignedUrlRequest.getObjectKey())) {
            onBosListener.onGeneratePresignedUrlCallback(new BosGeneratePresignedUrlResponse(bosGeneratePresignedUrlRequest.getTag(), 2, "objectKey参数错误"));
            return;
        }
        TreeMap treeMap = new TreeMap();
        try {
            treeMap.put("object_key", URLEncoder.encode(bosGeneratePresignedUrlRequest.getObjectKey(), Key.STRING_CHARSET_NAME));
        } catch (Exception unused) {
            treeMap.put("object_key", bosGeneratePresignedUrlRequest.getObjectKey());
        }
        if (bosGeneratePresignedUrlRequest.getObjectType() != null) {
            treeMap.put("object_type", bosGeneratePresignedUrlRequest.getObjectType().name());
        }
        String strM1262a = C0862h.m1262a(bosGeneratePresignedUrlRequest);
        if (!TextUtils.isEmpty(strM1262a)) {
            try {
                treeMap.put("image_command", URLEncoder.encode(strM1262a, Key.STRING_CHARSET_NAME));
            } catch (Exception unused2) {
                treeMap.put("image_command", strM1262a);
            }
        }
        C0850a.m1207a(bosGeneratePresignedUrlRequest, 3, "object/generatepresignedurl", C0850a.c.f1726b, 3, m956a((TreeMap<String, String>) treeMap, bosGeneratePresignedUrlRequest.getServiceId(), C0850a.c.f1726b), onBosListener);
        treeMap.clear();
    }

    /* JADX INFO: renamed from: a */
    protected static void m968a(BosGetObjectRequest bosGetObjectRequest, OnBosListener onBosListener) {
        if (TextUtils.isEmpty(bosGetObjectRequest.getObjectKey())) {
            onBosListener.onGetObjectCallback(new BosGetObjectResponse(bosGetObjectRequest.getTag(), 2, "objectKey参数错误"));
            return;
        }
        TreeMap treeMap = new TreeMap();
        try {
            treeMap.put("object_key", URLEncoder.encode(bosGetObjectRequest.getObjectKey(), Key.STRING_CHARSET_NAME));
        } catch (Exception unused) {
            treeMap.put("object_key", bosGetObjectRequest.getObjectKey());
        }
        if (bosGetObjectRequest.getObjectType() != null) {
            treeMap.put("object_type", bosGetObjectRequest.getObjectType().name());
        }
        C0850a.m1207a(bosGetObjectRequest, 3, "object/preparetoget", C0850a.c.f1726b, 2, m956a((TreeMap<String, String>) treeMap, bosGetObjectRequest.getServiceId(), C0850a.c.f1726b), onBosListener);
        treeMap.clear();
    }

    /* JADX INFO: renamed from: a */
    protected static void m969a(BosPutObjectRequest bosPutObjectRequest, OnBosListener onBosListener) {
        int iAvailable;
        if (TextUtils.isEmpty(bosPutObjectRequest.getObjectKey())) {
            onBosListener.onPutObjectCallback(new BosPutObjectResponse(bosPutObjectRequest.getTag(), 2, "objectKey参数错误"));
            return;
        }
        long length = 0;
        File file = bosPutObjectRequest.getFile();
        if (file != null) {
            length = file.length();
        } else {
            String stringData = bosPutObjectRequest.getStringData();
            if (stringData != null) {
                iAvailable = stringData.length();
            } else {
                byte[] byteArray = bosPutObjectRequest.getByteArray();
                if (byteArray != null) {
                    iAvailable = byteArray.length;
                } else {
                    InputStream streamData = bosPutObjectRequest.getStreamData();
                    if (streamData != null) {
                        try {
                            iAvailable = streamData.available();
                        } catch (IOException unused) {
                        }
                    }
                }
            }
            length = iAvailable;
        }
        bosPutObjectRequest.setObjectSize(length);
        TreeMap treeMap = new TreeMap();
        try {
            treeMap.put("object_key", URLEncoder.encode(bosPutObjectRequest.getObjectKey(), Key.STRING_CHARSET_NAME));
        } catch (Exception unused2) {
            treeMap.put("object_key", bosPutObjectRequest.getObjectKey());
        }
        if (bosPutObjectRequest.getObjectType() != null) {
            treeMap.put("object_type", bosPutObjectRequest.getObjectType().name());
        }
        treeMap.put("object_size", String.valueOf(length / 1024));
        C0850a.m1207a(bosPutObjectRequest, 3, "object/preparetoput", C0850a.c.f1726b, 1, m956a((TreeMap<String, String>) treeMap, bosPutObjectRequest.getServiceId(), C0850a.c.f1726b), onBosListener);
        treeMap.clear();
    }

    /* JADX INFO: renamed from: a */
    protected static void m970a(AddEntityRequest addEntityRequest, OnEntityListener onEntityListener) {
        TreeMap treeMap = new TreeMap();
        if (m1012a(0, addEntityRequest.getTag(), addEntityRequest.getEntityName(), addEntityRequest.getEntityDesc(), addEntityRequest.getColumns(), treeMap, onEntityListener)) {
            C0850a.m1207a(addEntityRequest, 1, "entity/add", C0850a.c.f1726b, 2, m956a((TreeMap<String, String>) treeMap, addEntityRequest.getServiceId(), C0850a.c.f1726b), onEntityListener);
        }
        treeMap.clear();
    }

    /* JADX INFO: renamed from: a */
    protected static void m971a(AroundSearchRequest aroundSearchRequest, OnEntityListener onEntityListener) {
        TreeMap treeMap = new TreeMap();
        if (aroundSearchRequest.getCenter() != null) {
            LatLng center = aroundSearchRequest.getCenter();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(center.latitude);
            stringBuffer.append(",");
            stringBuffer.append(center.longitude);
            treeMap.put("center", stringBuffer.toString());
        }
        if (aroundSearchRequest.getRadius() > 0.0d) {
            treeMap.put("radius", String.valueOf(aroundSearchRequest.getRadius()));
        }
        if (aroundSearchRequest.getCoordTypeInput() != null) {
            treeMap.put("coord_type_input", aroundSearchRequest.getCoordTypeInput().name());
        }
        if (aroundSearchRequest.getCoordTypeOutput() != null) {
            treeMap.put("coord_type_output", aroundSearchRequest.getCoordTypeOutput().name());
        }
        m973a(aroundSearchRequest, (TreeMap<String, String>) treeMap, "entity/aroundsearch", 8, onEntityListener);
        treeMap.clear();
    }

    /* JADX INFO: renamed from: a */
    protected static void m972a(BoundSearchRequest boundSearchRequest, OnEntityListener onEntityListener) {
        TreeMap treeMap = new TreeMap();
        if (boundSearchRequest.getCoordTypeInput() != null) {
            treeMap.put("coord_type_input", boundSearchRequest.getCoordTypeInput().name());
        }
        if (boundSearchRequest.getCoordTypeOutput() != null) {
            treeMap.put("coord_type_output", boundSearchRequest.getCoordTypeOutput().name());
        }
        if (boundSearchRequest.getLowerLeft() != null && boundSearchRequest.getUpperRight() != null) {
            LatLng lowerLeft = boundSearchRequest.getLowerLeft();
            LatLng upperRight = boundSearchRequest.getUpperRight();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(lowerLeft.latitude);
            stringBuffer.append(",");
            stringBuffer.append(lowerLeft.longitude);
            stringBuffer.append(";");
            stringBuffer.append(upperRight.latitude);
            stringBuffer.append(",");
            stringBuffer.append(upperRight.longitude);
            treeMap.put("bounds", stringBuffer.toString());
        }
        m973a(boundSearchRequest, (TreeMap<String, String>) treeMap, "entity/boundsearch", 7, onEntityListener);
        treeMap.clear();
    }

    /* JADX INFO: renamed from: a */
    private static void m973a(CommonRequest commonRequest, TreeMap<String, String> treeMap, String str, int i, OnEntityListener onEntityListener) {
        C0802aa.m1034a(commonRequest.getFilterCondition(), treeMap);
        C0802aa.m1035a(commonRequest.getSortBy(), treeMap);
        C0854e.m1225a(commonRequest.getPageIndex(), commonRequest.getPageSize(), treeMap);
        C0850a.m1207a(commonRequest, 1, str, C0850a.c.f1725a, i, m956a(treeMap, commonRequest.getServiceId(), C0850a.c.f1725a), onEntityListener);
    }

    /* JADX INFO: renamed from: a */
    protected static void m974a(DeleteEntityRequest deleteEntityRequest, OnEntityListener onEntityListener) {
        TreeMap treeMap = new TreeMap();
        if (m1012a(2, deleteEntityRequest.getTag(), deleteEntityRequest.getEntityName(), null, null, treeMap, onEntityListener)) {
            C0850a.m1207a(deleteEntityRequest, 1, "entity/delete", C0850a.c.f1726b, 3, m956a((TreeMap<String, String>) treeMap, deleteEntityRequest.getServiceId(), C0850a.c.f1726b), onEntityListener);
        }
        treeMap.clear();
    }

    /* JADX INFO: renamed from: a */
    protected static void m975a(DistrictSearchRequest districtSearchRequest, OnEntityListener onEntityListener) {
        TreeMap treeMap = new TreeMap();
        if (!TextUtils.isEmpty(districtSearchRequest.getKeyword())) {
            try {
                treeMap.put("keyword", URLEncoder.encode(districtSearchRequest.getKeyword(), Key.STRING_CHARSET_NAME));
            } catch (Exception unused) {
                treeMap.put("keyword", districtSearchRequest.getKeyword());
            }
        }
        if (districtSearchRequest.getReturnType() != null) {
            treeMap.put("return_type", districtSearchRequest.getReturnType().name());
        }
        if (districtSearchRequest.getCoordTypeOutput() != null) {
            treeMap.put("coord_type_output", districtSearchRequest.getCoordTypeOutput().name());
        }
        m973a(districtSearchRequest, (TreeMap<String, String>) treeMap, "entity/districtsearch", 10, onEntityListener);
        treeMap.clear();
    }

    /* JADX INFO: renamed from: a */
    protected static void m976a(EntityListRequest entityListRequest, OnEntityListener onEntityListener) {
        TreeMap treeMap = new TreeMap();
        if (entityListRequest.getCoordTypeOutput() != null) {
            treeMap.put("coord_type_output", entityListRequest.getCoordTypeOutput().name());
        }
        m973a(entityListRequest, (TreeMap<String, String>) treeMap, "entity/list", 1, onEntityListener);
        treeMap.clear();
    }

    /* JADX INFO: renamed from: a */
    protected static void m977a(LocRequest locRequest, String str, OnEntityListener onEntityListener) {
        TreeMap treeMap = new TreeMap();
        treeMap.put("apinfo", str);
        C0850a.m1207a(locRequest, 1, "internal/androidlocate", C0850a.c.f1726b, 5, m956a((TreeMap<String, String>) treeMap, locRequest.getServiceId(), C0850a.c.f1726b), onEntityListener);
        treeMap.clear();
    }

    /* JADX INFO: renamed from: a */
    protected static void m978a(PolygonSearchRequest polygonSearchRequest, OnEntityListener onEntityListener) {
        TreeMap treeMap = new TreeMap();
        if (polygonSearchRequest.getVertexes() != null) {
            C0802aa.m1037a(polygonSearchRequest.getVertexes(), (TreeMap<String, String>) treeMap);
        }
        if (polygonSearchRequest.getCoordTypeInput() != null) {
            treeMap.put("coord_type_input", polygonSearchRequest.getCoordTypeInput().name());
        }
        if (polygonSearchRequest.getCoordTypeOutput() != null) {
            treeMap.put("coord_type_output", polygonSearchRequest.getCoordTypeOutput().name());
        }
        m973a(polygonSearchRequest, (TreeMap<String, String>) treeMap, "entity/polygonsearch", 9, onEntityListener);
        treeMap.clear();
    }

    /* JADX INFO: renamed from: a */
    protected static void m979a(SearchRequest searchRequest, OnEntityListener onEntityListener) {
        TreeMap treeMap = new TreeMap();
        if (searchRequest.getCoordTypeOutput() != null) {
            treeMap.put("coord_type_output", searchRequest.getCoordTypeOutput().name());
        }
        String keyword = searchRequest.getKeyword();
        if (C0854e.m1231a(keyword)) {
            try {
                treeMap.put("query", URLEncoder.encode(keyword, Key.STRING_CHARSET_NAME));
            } catch (Exception unused) {
                treeMap.put("query", keyword);
            }
        }
        m973a(searchRequest, (TreeMap<String, String>) treeMap, "entity/search", 6, onEntityListener);
        treeMap.clear();
    }

    /* JADX INFO: renamed from: a */
    protected static void m980a(UpdateEntityRequest updateEntityRequest, OnEntityListener onEntityListener) {
        TreeMap treeMap = new TreeMap();
        if (m1012a(1, updateEntityRequest.getTag(), updateEntityRequest.getEntityName(), updateEntityRequest.getEntityDesc(), updateEntityRequest.getColumns(), treeMap, onEntityListener)) {
            C0850a.m1207a(updateEntityRequest, 1, "entity/update", C0850a.c.f1726b, 4, m956a((TreeMap<String, String>) treeMap, updateEntityRequest.getServiceId(), C0850a.c.f1726b), onEntityListener);
        }
        treeMap.clear();
    }

    /* JADX INFO: renamed from: a */
    protected static void m981a(CircleFence circleFence, StringBuffer stringBuffer, StringBuffer stringBuffer2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("latitude", circleFence.getCenter().latitude);
        jSONObject.put("longitude", circleFence.getCenter().longitude);
        if (stringBuffer2 != null) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("radius", circleFence.getRadius());
            jSONObject2.put("center", jSONObject);
            stringBuffer2.append(jSONObject2.toString());
        }
        if (stringBuffer != null) {
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("fenceName", circleFence.getFenceName());
            jSONObject3.put("monitoredPerson", circleFence.getMonitoredPerson());
            jSONObject3.put("coordType", circleFence.getCoordType());
            jSONObject3.put("denoise", circleFence.getDenoise());
            jSONObject3.put("fenceShape", CircleFence.class.getSimpleName());
            jSONObject3.put("radius", circleFence.getRadius());
            jSONObject3.put("center", jSONObject);
            stringBuffer.append(jSONObject3.toString());
        }
    }

    /* JADX INFO: renamed from: a */
    protected static void m982a(Fence fence, String str, LatLng latLng, List<MonitoredStatusInfo> list) {
        if (fence instanceof CircleFence) {
            CircleFence circleFence = (CircleFence) fence;
            long fenceId = fence.getFenceId();
            if (circleFence.getMonitoredPerson().equals(str)) {
                MonitoredStatusInfo monitoredStatusInfo = new MonitoredStatusInfo();
                monitoredStatusInfo.setMonitoredStatus(circleFence.getRadius() > C0854e.m1223a(circleFence.getCenter(), latLng) ? MonitoredStatus.in : MonitoredStatus.out);
                monitoredStatusInfo.setFenceId(fenceId);
                list.add(monitoredStatusInfo);
            }
        }
    }

    /* JADX INFO: renamed from: a */
    protected static void m983a(AddPointRequest addPointRequest, OnTrackListener onTrackListener) {
        TreeMap treeMap = new TreeMap();
        if (!TextUtils.isEmpty(addPointRequest.getEntityName())) {
            try {
                treeMap.put("entity_name", URLEncoder.encode(addPointRequest.getEntityName(), Key.STRING_CHARSET_NAME));
            } catch (Exception unused) {
                treeMap.put("entity_name", addPointRequest.getEntityName());
            }
        }
        AddPointResponse addPointResponse = new AddPointResponse(addPointRequest.getTag());
        Point point = addPointRequest.getPoint();
        if (point == null) {
            addPointResponse.setStatus(2);
            addPointResponse.setMessage("point参数错误");
            onTrackListener.onAddPointCallback(addPointResponse);
            return;
        }
        LatLng location = point.getLocation();
        if (!C0854e.m1230a(location)) {
            addPointResponse.setStatus(2);
            addPointResponse.setMessage("location参数错误");
            onTrackListener.onAddPointCallback(addPointResponse);
            return;
        }
        treeMap.put("latitude", String.valueOf(location.getLatitude()));
        treeMap.put("longitude", String.valueOf(location.getLongitude()));
        if (point.getLocTime() <= 0) {
            addPointResponse.setStatus(2);
            addPointResponse.setMessage("locTime参数错误");
            onTrackListener.onAddPointCallback(addPointResponse);
            return;
        }
        treeMap.put("loc_time", String.valueOf(point.getLocTime()));
        if (point.getCoordType() != null) {
            treeMap.put("coord_type_input", point.getCoordType().name());
        }
        treeMap.put("speed", String.valueOf(point.getSpeed()));
        treeMap.put("direction", String.valueOf(point.getDirection()));
        treeMap.put("height", String.valueOf(point.getHeight()));
        treeMap.put("radius", String.valueOf(point.getRadius()));
        if (!TextUtils.isEmpty(addPointRequest.getObjectName())) {
            treeMap.put("object_name", addPointRequest.getObjectName());
        }
        Map<String, String> columns = addPointRequest.getColumns();
        if (columns == null || m1018a(columns, (TreeMap<String, String>) treeMap, (JSONObject) null)) {
            C0850a.m1207a(addPointRequest, 2, "track/addpoint", C0850a.c.f1726b, 4, m956a((TreeMap<String, String>) treeMap, addPointRequest.getServiceId(), C0850a.c.f1726b), onTrackListener);
            treeMap.clear();
        } else {
            addPointResponse.setStatus(2);
            addPointResponse.setMessage("column_key参数错误");
            onTrackListener.onAddPointCallback(addPointResponse);
        }
    }

    /* JADX INFO: renamed from: a */
    protected static void m984a(AddPointsRequest addPointsRequest, OnTrackListener onTrackListener) {
        TreeMap treeMap = new TreeMap();
        AddPointsResponse addPointsResponse = new AddPointsResponse(addPointsRequest.getTag());
        Map<String, List<TrackPoint>> points = addPointsRequest.getPoints();
        if (points == null) {
            addPointsResponse.setStatus(2);
            addPointsResponse.setMessage("pointList参数错误");
            onTrackListener.onAddPointsCallback(addPointsResponse);
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        if (!m1017a(points, stringBuffer)) {
            addPointsResponse.setStatus(2);
            addPointsResponse.setMessage("pointList参数错误");
            onTrackListener.onAddPointsCallback(addPointsResponse);
        } else {
            if (!TextUtils.isEmpty(stringBuffer)) {
                try {
                    treeMap.put("point_list", URLEncoder.encode(stringBuffer.toString(), Key.STRING_CHARSET_NAME));
                } catch (Exception unused) {
                    treeMap.put("point_list", stringBuffer.toString());
                }
            }
            C0850a.m1207a(addPointsRequest, 2, "track/addpoints", C0850a.c.f1726b, 5, m956a((TreeMap<String, String>) treeMap, addPointsRequest.getServiceId(), C0850a.c.f1726b), onTrackListener);
            treeMap.clear();
        }
    }

    /* JADX INFO: renamed from: a */
    protected static void m985a(DistanceRequest distanceRequest, OnTrackListener onTrackListener) {
        TreeMap treeMap = new TreeMap();
        m992a(distanceRequest, "track/getdistance", 2, distanceRequest.getServiceId(), distanceRequest.getEntityName(), distanceRequest.getStartTime(), distanceRequest.getEndTime(), distanceRequest.isProcessed(), distanceRequest.getProcessOption(), distanceRequest.getSupplementMode(), null, null, 0, 0, treeMap, onTrackListener);
        treeMap.clear();
    }

    /* JADX INFO: renamed from: a */
    protected static void m986a(HistoryTrackRequest historyTrackRequest, OnTrackListener onTrackListener) {
        TreeMap treeMap = new TreeMap();
        m992a(historyTrackRequest, "track/gettrack", 1, historyTrackRequest.getServiceId(), historyTrackRequest.getEntityName(), historyTrackRequest.getStartTime(), historyTrackRequest.getEndTime(), historyTrackRequest.isProcessed(), historyTrackRequest.getProcessOption(), historyTrackRequest.getSupplementMode(), historyTrackRequest.getCoordTypeOutput(), historyTrackRequest.getSortType(), historyTrackRequest.getPageSize(), historyTrackRequest.getPageIndex(), treeMap, onTrackListener);
        treeMap.clear();
    }

    /* JADX INFO: renamed from: a */
    protected static void m987a(LatestPointRequest latestPointRequest, OnTrackListener onTrackListener) {
        TreeMap treeMap = new TreeMap();
        m992a(latestPointRequest, "track/getlatestpoint", 3, latestPointRequest.getServiceId(), latestPointRequest.getEntityName(), 0L, 0L, false, latestPointRequest.getProcessOption(), null, latestPointRequest.getCoordTypeOutput(), null, 0, 0, treeMap, onTrackListener);
        treeMap.clear();
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x00ee  */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void m988a(com.baidu.trace.model.BaseRequest r13, int r14, com.baidu.trace.api.fence.FenceShape r15, long r16, java.lang.String r18, java.lang.String r19, java.lang.String r20, com.baidu.trace.model.LatLng r21, double r22, java.util.List<com.baidu.trace.model.LatLng> r24, int r25, com.baidu.trace.model.CoordType r26, int r27, com.baidu.trace.api.fence.OnFenceListener r28) {
        /*
            Method dump skipped, instruction units count: 293
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.trace.C0791a.m988a(com.baidu.trace.model.BaseRequest, int, com.baidu.trace.api.fence.FenceShape, long, java.lang.String, java.lang.String, java.lang.String, com.baidu.trace.model.LatLng, double, java.util.List, int, com.baidu.trace.model.CoordType, int, com.baidu.trace.api.fence.OnFenceListener):void");
    }

    /* JADX INFO: renamed from: a */
    public static void m989a(BaseRequest baseRequest, int i, boolean z, int i2, String str, OnAnalysisListener onAnalysisListener) {
        String str2;
        String str3;
        String str4 = "stay_point";
        String str5 = "duration";
        int tag = baseRequest.getTag();
        if (i != 1) {
            if (i != 2) {
                return;
            }
            DrivingBehaviorResponse drivingBehaviorResponse = new DrivingBehaviorResponse(tag, i2, str);
            if (z) {
                m1000a(str, ((DrivingBehaviorRequest) baseRequest).getCoordTypeOutput(), drivingBehaviorResponse);
            }
            onAnalysisListener.onDrivingBehaviorCallback(drivingBehaviorResponse);
            return;
        }
        StayPointResponse stayPointResponse = new StayPointResponse(tag, i2, str);
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (m1029b(str, stayPointResponse)) {
                    if (jSONObject.has("staypoint_num")) {
                        stayPointResponse.setStayPointNum(jSONObject.getInt("staypoint_num"));
                    }
                    ArrayList arrayList = new ArrayList();
                    if (jSONObject.has("stay_points")) {
                        JSONArray jSONArray = jSONObject.getJSONArray("stay_points");
                        int length = jSONArray.length();
                        int i3 = 0;
                        while (i3 < length) {
                            StayPoint stayPoint = new StayPoint();
                            JSONObject jSONObject2 = jSONArray.getJSONObject(i3);
                            StayPointResponse stayPointResponse2 = stayPointResponse;
                            JSONArray jSONArray2 = jSONArray;
                            if (jSONObject2.has("start_time")) {
                                try {
                                    stayPoint.setStartTime(jSONObject2.getLong("start_time"));
                                } catch (Exception unused) {
                                    stayPointResponse = stayPointResponse2;
                                }
                            }
                            if (jSONObject2.has("end_time")) {
                                stayPoint.setEndTime(jSONObject2.getLong("end_time"));
                            }
                            if (jSONObject2.has(str5)) {
                                stayPoint.setDuration(jSONObject2.getInt(str5));
                            }
                            if (jSONObject2.has(str4)) {
                                JSONObject jSONObject3 = jSONObject2.getJSONObject(str4);
                                if (jSONObject3.has("longitude") && jSONObject3.has("latitude")) {
                                    str2 = str4;
                                    str3 = str5;
                                    stayPoint.setLocation(new LatLng(jSONObject3.getDouble("latitude"), jSONObject3.getDouble("longitude")));
                                } else {
                                    str2 = str4;
                                    str3 = str5;
                                }
                                if (jSONObject3.has("coord_type")) {
                                    stayPoint.setCoordType(CoordType.valueOf(jSONObject3.getString("coord_type")));
                                }
                            } else {
                                str2 = str4;
                                str3 = str5;
                            }
                            arrayList.add(stayPoint);
                            i3++;
                            stayPointResponse = stayPointResponse2;
                            jSONArray = jSONArray2;
                            str4 = str2;
                            str5 = str3;
                        }
                    }
                    stayPointResponse.setStayPoints(arrayList);
                }
            } catch (Exception unused2) {
            }
        }
        onAnalysisListener.onStayPointCallback(stayPointResponse);
    }

    /* JADX INFO: renamed from: a */
    public static void m990a(BaseRequest baseRequest, int i, boolean z, int i2, String str, OnFenceListener onFenceListener) {
        int tag = baseRequest.getTag();
        FenceShape fenceShape = FenceShape.circle;
        switch (i) {
            case 1:
                Fence fence = ((CreateFenceRequest) baseRequest).getFence();
                if (fence instanceof PolygonFence) {
                    fenceShape = FenceShape.polygon;
                } else if (fence instanceof PolylineFence) {
                    fenceShape = FenceShape.polyline;
                } else if (fence instanceof DistrictFence) {
                    fenceShape = FenceShape.district;
                }
                CreateFenceResponse createFenceResponse = new CreateFenceResponse(tag, i2, str, fence.getFenceName(), fenceShape, FenceType.server);
                if (z) {
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        if (m1029b(str, createFenceResponse)) {
                            if (jSONObject.has("fence_id")) {
                                createFenceResponse.setFenceId(jSONObject.getLong("fence_id"));
                            }
                            if (jSONObject.has("fence_name")) {
                                createFenceResponse.setFenceName(jSONObject.getString("fence_name"));
                            }
                            if (jSONObject.has("shape")) {
                                createFenceResponse.setFenceShape((FenceShape) FenceShape.valueOf(FenceShape.class, jSONObject.getString("shape")));
                            }
                            if (jSONObject.has("district")) {
                                createFenceResponse.setDistrict(jSONObject.getString("district"));
                            }
                            if (jSONObject.has("district_list")) {
                                JSONArray jSONArray = jSONObject.getJSONArray("district_list");
                                int length = jSONArray.length();
                                ArrayList arrayList = new ArrayList();
                                for (int i3 = 0; i3 < length; i3++) {
                                    arrayList.add(jSONArray.getString(i3));
                                }
                                createFenceResponse.setDistrictList(arrayList);
                            }
                            break;
                        }
                    } catch (Exception unused) {
                    }
                }
                onFenceListener.onCreateFenceCallback(createFenceResponse);
                return;
            case 2:
                Fence fence2 = ((UpdateFenceRequest) baseRequest).getFence();
                UpdateFenceResponse updateFenceResponse = new UpdateFenceResponse(tag, i2, str, fence2.getFenceId(), fence2.getFenceName(), FenceType.server);
                FenceShape fenceShape2 = FenceShape.circle;
                if (fence2 instanceof CircleFence) {
                    fenceShape2 = FenceShape.circle;
                } else if (fence2 instanceof PolygonFence) {
                    fenceShape2 = FenceShape.polygon;
                } else if (fence2 instanceof PolylineFence) {
                    fenceShape2 = FenceShape.polyline;
                } else if (fence2 instanceof DistrictFence) {
                    fenceShape2 = FenceShape.district;
                }
                updateFenceResponse.setFenceShape(fenceShape2);
                if (z) {
                    m997a(str, updateFenceResponse);
                }
                onFenceListener.onUpdateFenceCallback(updateFenceResponse);
                return;
            case 3:
                DeleteFenceResponse deleteFenceResponse = new DeleteFenceResponse(tag, i2, str, FenceType.server);
                if (z) {
                    m996a(str, deleteFenceResponse);
                }
                onFenceListener.onDeleteFenceCallback(deleteFenceResponse);
                return;
            case 4:
                FenceListResponse fenceListResponse = new FenceListResponse(tag, i2, str, FenceType.server);
                if (z) {
                    m1002a(str, ((FenceListRequest) baseRequest).getCoordTypeOutput(), fenceListResponse);
                }
                onFenceListener.onFenceListCallback(fenceListResponse);
                return;
            case 5:
                MonitoredStatusResponse monitoredStatusResponse = new MonitoredStatusResponse(tag, i2, str, FenceType.server);
                if (z) {
                    m999a(str, monitoredStatusResponse);
                }
                onFenceListener.onMonitoredStatusCallback(monitoredStatusResponse);
                return;
            case 6:
                MonitoredStatusByLocationResponse monitoredStatusByLocationResponse = new MonitoredStatusByLocationResponse(tag, i2, str, FenceType.server);
                if (z) {
                    m999a(str, monitoredStatusByLocationResponse);
                }
                onFenceListener.onMonitoredStatusByLocationCallback(monitoredStatusByLocationResponse);
                return;
            case 7:
                HistoryAlarmResponse historyAlarmResponse = new HistoryAlarmResponse(tag, i2, str, FenceType.server);
                if (z) {
                    m1003a(str, ((HistoryAlarmRequest) baseRequest).getCoordTypeOutput(), historyAlarmResponse);
                }
                onFenceListener.onHistoryAlarmCallback(historyAlarmResponse);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m991a(BaseRequest baseRequest, int i, boolean z, int i2, String str, OnTrackListener onTrackListener) {
        int tag = baseRequest.getTag();
        String entityName = baseRequest instanceof DistanceRequest ? ((DistanceRequest) baseRequest).getEntityName() : baseRequest instanceof HistoryTrackRequest ? ((HistoryTrackRequest) baseRequest).getEntityName() : baseRequest instanceof LatestPointRequest ? ((LatestPointRequest) baseRequest).getEntityName() : "";
        if (i != 1) {
            if (i == 2) {
                DistanceResponse distanceResponse = new DistanceResponse(tag, i2, str);
                if (z) {
                    try {
                        if (m1029b(str, distanceResponse)) {
                            JSONObject jSONObject = new JSONObject(str);
                            if (jSONObject.has("distance")) {
                                distanceResponse.setDistance(jSONObject.getDouble("distance"));
                            }
                        }
                    } catch (Exception unused) {
                    }
                }
                distanceResponse.setEntityName(entityName);
                onTrackListener.onDistanceCallback(distanceResponse);
                return;
            }
            if (i == 3) {
                LatestPointResponse latestPointResponse = new LatestPointResponse(tag, i2, str);
                if (z) {
                    m1004a(str, ((LatestPointRequest) baseRequest).getCoordTypeOutput(), latestPointResponse);
                }
                latestPointResponse.setEntityName(entityName);
                onTrackListener.onLatestPointCallback(latestPointResponse);
                return;
            }
            if (i == 4) {
                AddPointResponse addPointResponse = new AddPointResponse(tag, i2, str);
                if (z) {
                    m1029b(str, addPointResponse);
                }
                onTrackListener.onAddPointCallback(addPointResponse);
                return;
            }
            if (i != 5) {
                return;
            }
            AddPointsResponse addPointsResponse = new AddPointsResponse(tag, i2, str);
            if (z) {
                m998a(str, addPointsResponse);
            }
            onTrackListener.onAddPointsCallback(addPointsResponse);
            return;
        }
        HistoryTrackResponse historyTrackResponse = new HistoryTrackResponse(tag, i2, str);
        if (z) {
            CoordType coordTypeOutput = ((HistoryTrackRequest) baseRequest).getCoordTypeOutput();
            if (coordTypeOutput == null) {
                coordTypeOutput = CoordType.bd09ll;
            }
            try {
                if (m1029b(str, historyTrackResponse)) {
                    JSONObject jSONObject2 = new JSONObject(str);
                    if (jSONObject2.has(TopicKey.TOTAL)) {
                        historyTrackResponse.setTotal(jSONObject2.getInt(TopicKey.TOTAL));
                    }
                    if (jSONObject2.has("size")) {
                        historyTrackResponse.setSize(jSONObject2.getInt("size"));
                    }
                    if (jSONObject2.has("distance")) {
                        historyTrackResponse.setDistance(jSONObject2.getDouble("distance"));
                    }
                    if (jSONObject2.has("toll_distance")) {
                        historyTrackResponse.setTollDistance(jSONObject2.getDouble("toll_distance"));
                    }
                    if (jSONObject2.has("start_point")) {
                        JSONObject jSONObject3 = jSONObject2.getJSONObject("start_point");
                        Point point = new Point(null, coordTypeOutput);
                        m1010a(jSONObject3, point, (List<String>) null);
                        historyTrackResponse.setStartPoint(point);
                    }
                    if (jSONObject2.has("end_point")) {
                        JSONObject jSONObject4 = jSONObject2.getJSONObject("end_point");
                        Point point2 = new Point(null, coordTypeOutput);
                        m1010a(jSONObject4, point2, (List<String>) null);
                        historyTrackResponse.setEndPoint(point2);
                    }
                    ArrayList arrayList = new ArrayList();
                    if (jSONObject2.has("points")) {
                        JSONArray jSONArray = jSONObject2.getJSONArray("points");
                        int length = jSONArray.length();
                        for (int i3 = 0; i3 < length; i3++) {
                            JSONObject jSONObject5 = jSONArray.getJSONObject(i3);
                            Iterator<String> itKeys = jSONObject5.keys();
                            ArrayList arrayList2 = new ArrayList();
                            while (itKeys.hasNext()) {
                                arrayList2.add(itKeys.next());
                            }
                            TrackPoint trackPoint = new TrackPoint(coordTypeOutput);
                            m1010a(jSONObject5, trackPoint, arrayList2);
                            arrayList.add(trackPoint);
                        }
                    }
                    historyTrackResponse.setTrackPoints(arrayList);
                }
            } catch (Exception unused2) {
            }
        }
        historyTrackResponse.setEntityName(entityName);
        onTrackListener.onHistoryTrackCallback(historyTrackResponse);
    }

    /* JADX INFO: renamed from: a */
    private static void m992a(BaseRequest baseRequest, String str, int i, long j, String str2, long j2, long j3, boolean z, ProcessOption processOption, SupplementMode supplementMode, CoordType coordType, SortType sortType, int i2, int i3, TreeMap<String, String> treeMap, OnTrackListener onTrackListener) {
        if (!TextUtils.isEmpty(str2)) {
            try {
                treeMap.put("entity_name", URLEncoder.encode(str2, Key.STRING_CHARSET_NAME));
            } catch (Exception unused) {
                treeMap.put("entity_name", str2);
            }
        }
        if (j2 > 0) {
            treeMap.put("start_time", String.valueOf(j2));
        }
        if (j3 > 0) {
            treeMap.put("end_time", String.valueOf(j3));
        }
        treeMap.put("is_processed", String.valueOf(z ? 1 : 0));
        if (supplementMode != null) {
            treeMap.put("supplement_mode", supplementMode.name());
        }
        m994a(processOption, treeMap);
        if (coordType != null) {
            treeMap.put("coord_type_output", coordType.name());
        }
        if (sortType != null) {
            treeMap.put("sort_type", sortType.name());
        }
        if (i2 > 0) {
            treeMap.put("page_size", String.valueOf(i2));
        }
        if (i3 > 0) {
            treeMap.put("page_index", String.valueOf(i3));
        }
        C0850a.m1207a(baseRequest, 2, str, C0850a.c.f1725a, i, m956a(treeMap, j, C0850a.c.f1725a), onTrackListener);
    }

    /* JADX INFO: renamed from: a */
    private static void m993a(BaseRequest baseRequest, String str, long j, long j2, long j3, String str2, int i, int i2, int i3, int i4, ThresholdOption thresholdOption, ProcessOption processOption, CoordType coordType, OnAnalysisListener onAnalysisListener) {
        if (!C0854e.m1231a(str)) {
            if (baseRequest instanceof StayPointRequest) {
                onAnalysisListener.onStayPointCallback(new StayPointResponse(baseRequest.getTag(), 2, "entityName参数错误"));
                return;
            } else {
                if (baseRequest instanceof DrivingBehaviorRequest) {
                    onAnalysisListener.onDrivingBehaviorCallback(new DrivingBehaviorResponse(baseRequest.getTag(), 2, "entityName参数错误"));
                    return;
                }
                return;
            }
        }
        TreeMap treeMap = new TreeMap();
        try {
            treeMap.put("entity_name", URLEncoder.encode(str, Key.STRING_CHARSET_NAME));
        } catch (Exception unused) {
            treeMap.put("entity_name", str);
        }
        if (j2 > 0) {
            treeMap.put("start_time", String.valueOf(j2));
        }
        if (j3 > 0) {
            treeMap.put("end_time", String.valueOf(j3));
        }
        if (i2 > 0) {
            treeMap.put("stay_time", String.valueOf(i2));
        }
        if (i3 > 0) {
            treeMap.put("stay_radius", String.valueOf(i3));
        }
        if (thresholdOption != null) {
            if (thresholdOption.getSpeedingThreshold() > 0.0d) {
                treeMap.put("speeding_threshold", String.valueOf(thresholdOption.getSpeedingThreshold()));
            }
            if (thresholdOption.getHarshAccelerationThreshold() > 0.0d) {
                treeMap.put("harsh_acceleration_threshold", String.valueOf(thresholdOption.getHarshAccelerationThreshold()));
            }
            if (thresholdOption.getHarshBreakingThreshold() < 0.0d) {
                treeMap.put("harsh_breaking_threshold", String.valueOf(thresholdOption.getHarshBreakingThreshold()));
            }
            if (thresholdOption.getHarshSteeringThreshold() > 0.0d) {
                treeMap.put("harsh_steering_threshold", String.valueOf(thresholdOption.getHarshSteeringThreshold()));
            }
        }
        if (i4 > 0) {
            treeMap.put("speeding_threshold", String.valueOf(i4));
        }
        m994a(processOption, (TreeMap<String, String>) treeMap);
        if (coordType != null) {
            treeMap.put("coord_type_output", coordType.name());
        }
        C0850a.m1207a(baseRequest, 4, str2, C0850a.c.f1725a, i, m956a((TreeMap<String, String>) treeMap, j, C0850a.c.f1725a), onAnalysisListener);
        treeMap.clear();
    }

    /* JADX INFO: renamed from: a */
    protected static void m994a(ProcessOption processOption, TreeMap<String, String> treeMap) {
        StringBuffer stringBuffer = new StringBuffer("");
        if (processOption == null || !processOption.isNeedDenoise()) {
            stringBuffer.append("need_denoise=");
            stringBuffer.append(0);
        } else {
            stringBuffer.append("need_denoise=");
            stringBuffer.append(1);
        }
        if (processOption == null || !processOption.isNeedVacuate()) {
            stringBuffer.append(",need_vacuate");
            stringBuffer.append("=0");
        } else {
            stringBuffer.append(",need_vacuate");
            stringBuffer.append("=1");
        }
        if (processOption == null || !processOption.isNeedMapMatch()) {
            stringBuffer.append(",need_mapmatch");
            stringBuffer.append("=0");
        } else {
            stringBuffer.append(",need_mapmatch");
            stringBuffer.append("=1");
        }
        if (processOption != null && processOption.getRadiusThreshold() > 0) {
            stringBuffer.append(",radius_threshold");
            stringBuffer.append("=");
            stringBuffer.append(processOption.getRadiusThreshold());
        }
        if (processOption != null && processOption.getTransportMode() != null) {
            stringBuffer.append(",transport_mode");
            stringBuffer.append("=");
            stringBuffer.append(processOption.getTransportMode().name());
        }
        if (stringBuffer.length() > 0 && stringBuffer.charAt(0) == ',') {
            stringBuffer.replace(0, 1, "");
        }
        if (TextUtils.isEmpty(stringBuffer.toString())) {
            return;
        }
        treeMap.put("process_option", stringBuffer.toString());
    }

    /* JADX INFO: renamed from: a */
    public static void m995a(String str) {
        if (Thread.currentThread().getStackTrace().length == 0) {
            return;
        }
        C0857h.m1245a("INFO", str);
    }

    /* JADX INFO: renamed from: a */
    public static void m996a(String str, DeleteFenceResponse deleteFenceResponse) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (m1029b(str, deleteFenceResponse)) {
                ArrayList arrayList = new ArrayList();
                if (jSONObject.has("fence_ids")) {
                    JSONArray jSONArray = jSONObject.getJSONArray("fence_ids");
                    int length = jSONArray.length();
                    for (int i = 0; i < length; i++) {
                        arrayList.add(Long.valueOf(jSONArray.getLong(i)));
                    }
                }
                deleteFenceResponse.setFenceIds(arrayList);
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m997a(String str, UpdateFenceResponse updateFenceResponse) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (m1029b(str, updateFenceResponse)) {
                if (jSONObject.has("district")) {
                    updateFenceResponse.setDistrict(jSONObject.getString("district"));
                }
                if (jSONObject.has("district_list")) {
                    JSONArray jSONArray = jSONObject.getJSONArray("district_list");
                    int length = jSONArray.length();
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < length; i++) {
                        arrayList.add(jSONArray.getString(i));
                    }
                    updateFenceResponse.setDistrictList(arrayList);
                }
            }
        } catch (JSONException unused) {
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m998a(String str, AddPointsResponse addPointsResponse) {
        try {
            if (m1029b(str, addPointsResponse)) {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("success_num")) {
                    addPointsResponse.setSuccessNum(jSONObject.getInt("success_num"));
                }
                FailInfo failInfo = new FailInfo();
                if (jSONObject.has("fail_info")) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("fail_info");
                    ArrayList arrayList = new ArrayList();
                    if (jSONObject2.has("param_error")) {
                        JSONArray jSONArray = jSONObject2.getJSONArray("param_error");
                        for (int i = 0; i < jSONArray.length(); i++) {
                            failInfo.getClass();
                            FailInfo.ParamError paramError = new FailInfo.ParamError(failInfo);
                            m1007a(jSONArray.getJSONObject(i), paramError);
                            arrayList.add(paramError);
                        }
                    }
                    failInfo.setParamErrors(arrayList);
                    ArrayList arrayList2 = new ArrayList();
                    if (jSONObject2.has("internal_error")) {
                        JSONArray jSONArray2 = jSONObject2.getJSONArray("internal_error");
                        for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                            failInfo.getClass();
                            FailInfo.InternalError internalError = new FailInfo.InternalError(failInfo);
                            m1007a(jSONArray2.getJSONObject(i2), internalError);
                            arrayList2.add(internalError);
                        }
                    }
                    failInfo.setInternalErrors(arrayList2);
                }
                addPointsResponse.setFailInfo(failInfo);
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m999a(String str, BaseResponse baseResponse) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (m1029b(str, baseResponse)) {
                if (jSONObject.has("size")) {
                    if (baseResponse instanceof MonitoredStatusResponse) {
                        ((MonitoredStatusResponse) baseResponse).setSize(jSONObject.getInt("size"));
                    } else if (baseResponse instanceof MonitoredStatusByLocationResponse) {
                        ((MonitoredStatusByLocationResponse) baseResponse).setSize(jSONObject.getInt("size"));
                    }
                }
                ArrayList arrayList = new ArrayList();
                if (jSONObject.has("monitored_statuses")) {
                    JSONArray jSONArray = jSONObject.getJSONArray("monitored_statuses");
                    int length = jSONArray.length();
                    for (int i = 0; i < length; i++) {
                        MonitoredStatusInfo monitoredStatusInfo = new MonitoredStatusInfo();
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                        if (jSONObject2.has("fence_id")) {
                            monitoredStatusInfo.setFenceId(jSONObject2.getLong("fence_id"));
                        }
                        if (jSONObject2.has("monitored_status")) {
                            monitoredStatusInfo.setMonitoredStatus((MonitoredStatus) MonitoredStatus.valueOf(MonitoredStatus.class, jSONObject2.getString("monitored_status")));
                        }
                        arrayList.add(monitoredStatusInfo);
                    }
                }
                if (baseResponse instanceof MonitoredStatusResponse) {
                    ((MonitoredStatusResponse) baseResponse).setMonitoredStatusInfos(arrayList);
                } else if (baseResponse instanceof MonitoredStatusByLocationResponse) {
                    ((MonitoredStatusByLocationResponse) baseResponse).setMonitoredStatusInfos(arrayList);
                }
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m1000a(String str, CoordType coordType, DrivingBehaviorResponse drivingBehaviorResponse) {
        String str2;
        CoordType coordType2;
        String str3;
        String str4;
        JSONObject jSONObject;
        String str5;
        ArrayList arrayList;
        int i;
        String str6;
        ArrayList arrayList2;
        ArrayList arrayList3;
        ArrayList arrayList4;
        String str7 = "speeding_points";
        String str8 = "speeding_distance";
        if (coordType != null) {
            str2 = "harsh_steering";
            coordType2 = coordType;
        } else {
            str2 = "harsh_steering";
            coordType2 = CoordType.bd09ll;
        }
        try {
            JSONObject jSONObject2 = new JSONObject(str);
            if (m1029b(str, drivingBehaviorResponse)) {
                if (jSONObject2.has("distance")) {
                    str3 = "harsh_acceleration";
                    str4 = "speeding";
                    drivingBehaviorResponse.setDistance(jSONObject2.getDouble("distance"));
                } else {
                    str3 = "harsh_acceleration";
                    str4 = "speeding";
                }
                if (jSONObject2.has("duration")) {
                    drivingBehaviorResponse.setDuration(jSONObject2.getInt("duration"));
                }
                if (jSONObject2.has("average_speed")) {
                    drivingBehaviorResponse.setAverageSpeed(jSONObject2.getDouble("average_speed"));
                }
                if (jSONObject2.has("max_speed")) {
                    drivingBehaviorResponse.setMaxSpeed(jSONObject2.getDouble("max_speed"));
                }
                if (jSONObject2.has("speeding_num")) {
                    drivingBehaviorResponse.setSpeedingNum(jSONObject2.getInt("speeding_num"));
                }
                if (jSONObject2.has("harsh_acceleration_num")) {
                    drivingBehaviorResponse.setHarshAccelerationNum(jSONObject2.getInt("harsh_acceleration_num"));
                }
                if (jSONObject2.has("harsh_breaking_num")) {
                    drivingBehaviorResponse.setHarshBreakingNum(jSONObject2.getInt("harsh_breaking_num"));
                }
                if (jSONObject2.has("harsh_steering_num")) {
                    drivingBehaviorResponse.setHarshSteeringNum(jSONObject2.getInt("harsh_steering_num"));
                }
                if (jSONObject2.has("start_point")) {
                    JSONObject jSONObject3 = jSONObject2.getJSONObject("start_point");
                    StartPoint startPoint = new StartPoint(coordType2);
                    m1010a(jSONObject3, startPoint, (List<String>) null);
                    drivingBehaviorResponse.setStartPoint(startPoint);
                }
                if (jSONObject2.has("end_point")) {
                    JSONObject jSONObject4 = jSONObject2.getJSONObject("end_point");
                    EndPoint endPoint = new EndPoint(coordType2);
                    m1010a(jSONObject4, endPoint, (List<String>) null);
                    drivingBehaviorResponse.setEndPoint(endPoint);
                }
                int i2 = 4;
                String str9 = str2;
                String str10 = str4;
                String[] strArr = {str10, str3, "harsh_breaking", str9};
                ArrayList arrayList5 = new ArrayList();
                ArrayList arrayList6 = new ArrayList();
                ArrayList arrayList7 = new ArrayList();
                ArrayList arrayList8 = new ArrayList();
                int i3 = 0;
                while (i3 < i2 && jSONObject2.has(strArr[i3])) {
                    JSONArray jSONArray = jSONObject2.getJSONArray(strArr[i3]);
                    int length = jSONArray.length();
                    int i4 = 0;
                    while (true) {
                        jSONObject = jSONObject2;
                        if (i4 < length) {
                            if (str10.equals(strArr[i3])) {
                                JSONObject jSONObject5 = jSONArray.getJSONObject(i4);
                                str5 = str10;
                                SpeedingInfo speedingInfo = new SpeedingInfo();
                                i = length;
                                String str11 = str8;
                                if (jSONObject5.has(str11)) {
                                    arrayList2 = arrayList7;
                                    arrayList = arrayList8;
                                    speedingInfo.setDistance(jSONObject5.getDouble(str11));
                                } else {
                                    arrayList2 = arrayList7;
                                    arrayList = arrayList8;
                                }
                                String str12 = str7;
                                if (jSONObject5.has(str12)) {
                                    JSONArray jSONArray2 = jSONObject5.getJSONArray(str12);
                                    ArrayList arrayList9 = new ArrayList();
                                    str7 = str12;
                                    int length2 = jSONArray2.length();
                                    str6 = str11;
                                    int i5 = 0;
                                    while (i5 < length2) {
                                        int i6 = length2;
                                        SpeedingPoint speedingPoint = new SpeedingPoint(coordType2);
                                        JSONObject jSONObject6 = jSONArray2.getJSONObject(i5);
                                        m1010a(jSONObject6, speedingPoint, (List<String>) null);
                                        m1009a(jSONObject6, speedingPoint);
                                        arrayList9.add(speedingPoint);
                                        i5++;
                                        length2 = i6;
                                        jSONArray2 = jSONArray2;
                                    }
                                    speedingInfo.setPoints(arrayList9);
                                } else {
                                    str7 = str12;
                                    str6 = str11;
                                }
                                arrayList5.add(speedingInfo);
                            } else {
                                str5 = str10;
                                arrayList = arrayList8;
                                i = length;
                                str6 = str8;
                                arrayList2 = arrayList7;
                                if (str3.equals(strArr[i3])) {
                                    HarshAccelerationPoint harshAccelerationPoint = new HarshAccelerationPoint(coordType2);
                                    JSONObject jSONObject7 = jSONArray.getJSONObject(i4);
                                    m1010a(jSONObject7, harshAccelerationPoint, (List<String>) null);
                                    m1009a(jSONObject7, harshAccelerationPoint);
                                    arrayList6.add(harshAccelerationPoint);
                                } else {
                                    if ("harsh_breaking".equals(strArr[i3])) {
                                        HarshBreakingPoint harshBreakingPoint = new HarshBreakingPoint(coordType2);
                                        JSONObject jSONObject8 = jSONArray.getJSONObject(i4);
                                        m1010a(jSONObject8, harshBreakingPoint, (List<String>) null);
                                        m1009a(jSONObject8, harshBreakingPoint);
                                        arrayList3 = arrayList2;
                                        arrayList3.add(harshBreakingPoint);
                                    } else {
                                        arrayList3 = arrayList2;
                                        if (str9.equals(strArr[i3])) {
                                            HarshSteeringPoint harshSteeringPoint = new HarshSteeringPoint(coordType2);
                                            JSONObject jSONObject9 = jSONArray.getJSONObject(i4);
                                            m1010a(jSONObject9, harshSteeringPoint, (List<String>) null);
                                            m1009a(jSONObject9, harshSteeringPoint);
                                            arrayList4 = arrayList;
                                            arrayList4.add(harshSteeringPoint);
                                        }
                                        i4++;
                                        arrayList7 = arrayList3;
                                        arrayList8 = arrayList4;
                                        jSONObject2 = jSONObject;
                                        str10 = str5;
                                        length = i;
                                        str8 = str6;
                                    }
                                    arrayList4 = arrayList;
                                    i4++;
                                    arrayList7 = arrayList3;
                                    arrayList8 = arrayList4;
                                    jSONObject2 = jSONObject;
                                    str10 = str5;
                                    length = i;
                                    str8 = str6;
                                }
                            }
                            arrayList3 = arrayList2;
                            arrayList4 = arrayList;
                            i4++;
                            arrayList7 = arrayList3;
                            arrayList8 = arrayList4;
                            jSONObject2 = jSONObject;
                            str10 = str5;
                            length = i;
                            str8 = str6;
                        }
                    }
                    i3++;
                    str10 = str10;
                    i2 = 4;
                    arrayList7 = arrayList7;
                    jSONObject2 = jSONObject;
                }
                drivingBehaviorResponse.setSpeedings(arrayList5);
                drivingBehaviorResponse.setHarshAccelerationPoints(arrayList6);
                drivingBehaviorResponse.setHarshBreakingPoints(arrayList7);
                drivingBehaviorResponse.setHarshSteeringPoints(arrayList8);
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m1001a(String str, CoordType coordType, CommonResponse commonResponse) {
        int i;
        String str2;
        String str3 = "latest_location";
        CoordType coordType2 = coordType != null ? coordType : CoordType.bd09ll;
        try {
            if (m1029b(str, commonResponse)) {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has(TopicKey.TOTAL)) {
                    commonResponse.setTotal(jSONObject.getInt(TopicKey.TOTAL));
                }
                if (jSONObject.has("size")) {
                    commonResponse.setSize(jSONObject.getInt("size"));
                }
                ArrayList arrayList = new ArrayList();
                if (jSONObject.has("entities")) {
                    JSONArray jSONArray = jSONObject.getJSONArray("entities");
                    int length = jSONArray.length();
                    int i2 = 0;
                    while (i2 < length) {
                        EntityInfo entityInfo = new EntityInfo();
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                        Iterator<String> itKeys = jSONObject2.keys();
                        ArrayList<String> arrayList2 = new ArrayList();
                        while (itKeys.hasNext()) {
                            arrayList2.add(itKeys.next());
                            jSONArray = jSONArray;
                        }
                        JSONArray jSONArray2 = jSONArray;
                        if (jSONObject2.has("entity_name")) {
                            entityInfo.setEntityName(jSONObject2.getString("entity_name"));
                            arrayList2.remove("entity_name");
                        }
                        if (jSONObject2.has("entity_desc")) {
                            entityInfo.setEntityDesc(jSONObject2.getString("entity_desc"));
                            arrayList2.remove("entity_desc");
                        }
                        if (jSONObject2.has("create_time")) {
                            entityInfo.setCreateTime(jSONObject2.getString("create_time"));
                            arrayList2.remove("create_time");
                        }
                        if (jSONObject2.has("modify_time")) {
                            entityInfo.setModifyTime(jSONObject2.getString("modify_time"));
                            arrayList2.remove("modify_time");
                        }
                        if (jSONObject2.has(str3)) {
                            JSONObject jSONObject3 = jSONObject2.getJSONObject(str3);
                            arrayList2.remove(str3);
                            Iterator<String> itKeys2 = jSONObject3.keys();
                            i = length;
                            ArrayList arrayList3 = new ArrayList();
                            while (itKeys2.hasNext()) {
                                arrayList3.add(itKeys2.next());
                                str3 = str3;
                            }
                            str2 = str3;
                            LatestLocation latestLocation = new LatestLocation(coordType2);
                            m1010a(jSONObject3, latestLocation, arrayList3);
                            entityInfo.setLatestLocation(latestLocation);
                        } else {
                            i = length;
                            str2 = str3;
                        }
                        HashMap map = new HashMap();
                        for (String str4 : arrayList2) {
                            map.put(str4, jSONObject2.getString(str4));
                        }
                        entityInfo.setColumns(map);
                        arrayList.add(entityInfo);
                        i2++;
                        jSONArray = jSONArray2;
                        length = i;
                        str3 = str2;
                    }
                }
                if ((commonResponse instanceof DistrictSearchResponse) && jSONObject.has("district_list")) {
                    JSONArray jSONArray3 = jSONObject.getJSONArray("district_list");
                    int length2 = jSONArray3.length();
                    ArrayList arrayList4 = new ArrayList();
                    for (int i3 = 0; i3 < length2; i3++) {
                        arrayList4.add(jSONArray3.getString(i3));
                    }
                    ((DistrictSearchResponse) commonResponse).setDistrictList(arrayList4);
                }
                commonResponse.setEntities(arrayList);
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m1002a(String str, CoordType coordType, FenceListResponse fenceListResponse) {
        JSONArray jSONArray;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        LatLng latLng;
        String str7;
        String str8;
        double d;
        String str9;
        FenceInfo fenceInfo;
        FenceInfo fenceInfo2;
        String str10 = "vertexes";
        String str11 = "radius";
        String str12 = "monitored_person";
        String str13 = "fence_name";
        String str14 = "shape";
        String str15 = "modify_time";
        String str16 = "create_time";
        String str17 = "fence_id";
        String str18 = "district";
        String str19 = "denoise";
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (m1029b(str, fenceListResponse)) {
                if (jSONObject.has("size")) {
                    fenceListResponse.setSize(jSONObject.getInt("size"));
                }
                ArrayList arrayList = new ArrayList();
                if (jSONObject.has("fences")) {
                    JSONArray jSONArray2 = jSONObject.getJSONArray("fences");
                    int length = jSONArray2.length();
                    int i = 0;
                    while (i < length) {
                        int i2 = length;
                        FenceInfo fenceInfo3 = new FenceInfo();
                        JSONObject jSONObject2 = jSONArray2.getJSONObject(i);
                        long j = jSONObject2.has(str17) ? jSONObject2.getLong(str17) : 0L;
                        if (jSONObject2.has(str16)) {
                            jSONArray = jSONArray2;
                            fenceInfo3.setCreateTime(jSONObject2.getString(str16));
                        } else {
                            jSONArray = jSONArray2;
                        }
                        if (jSONObject2.has(str15)) {
                            fenceInfo3.setModifyTime(jSONObject2.getString(str15));
                        }
                        if (jSONObject2.has(str14)) {
                            str2 = str15;
                            fenceInfo3.setFenceShape((FenceShape) FenceShape.valueOf(FenceShape.class, jSONObject2.getString(str14)));
                        } else {
                            str2 = str15;
                        }
                        String string = jSONObject2.has(str13) ? jSONObject2.getString(str13) : "";
                        String string2 = jSONObject2.has(str12) ? jSONObject2.getString(str12) : "";
                        ArrayList arrayList2 = null;
                        String str20 = str12;
                        if (jSONObject2.has("longitude") && jSONObject2.has("latitude")) {
                            str3 = str13;
                            str4 = str14;
                            str5 = "";
                            str6 = str16;
                            latLng = new LatLng(jSONObject2.getDouble("latitude"), jSONObject2.getDouble("longitude"));
                        } else {
                            str3 = str13;
                            str4 = str14;
                            str5 = "";
                            str6 = str16;
                            latLng = null;
                        }
                        double d2 = jSONObject2.has(str11) ? jSONObject2.getDouble(str11) : 0.0d;
                        if (jSONObject2.has(str10)) {
                            ArrayList arrayList3 = new ArrayList();
                            JSONArray jSONArray3 = jSONObject2.getJSONArray(str10);
                            str7 = str10;
                            int length2 = jSONArray3.length();
                            str8 = str11;
                            int i3 = 0;
                            while (i3 < length2) {
                                int i4 = length2;
                                JSONObject jSONObject3 = jSONArray3.getJSONObject(i3);
                                arrayList3.add(new LatLng(jSONObject3.getDouble("latitude"), jSONObject3.getDouble("longitude")));
                                i3++;
                                length2 = i4;
                                jSONArray3 = jSONArray3;
                                d2 = d2;
                                str17 = str17;
                                fenceInfo3 = fenceInfo3;
                            }
                            d = d2;
                            str9 = str17;
                            fenceInfo = fenceInfo3;
                            arrayList2 = arrayList3;
                        } else {
                            str7 = str10;
                            str8 = str11;
                            d = d2;
                            str9 = str17;
                            fenceInfo = fenceInfo3;
                        }
                        CoordType coordTypeValueOf = coordType != null ? coordType : CoordType.bd09ll;
                        if (jSONObject2.has("coord_type")) {
                            coordTypeValueOf = CoordType.valueOf(jSONObject2.getString("coord_type"));
                        }
                        int i5 = jSONObject2.has("offset") ? jSONObject2.getInt("offset") : 0;
                        String str21 = str19;
                        int i6 = jSONObject2.has(str21) ? jSONObject2.getInt(str21) : 0;
                        String str22 = str18;
                        String string3 = jSONObject2.has(str22) ? jSONObject2.getString(str22) : str5;
                        if (fenceInfo.getFenceShape() == FenceShape.circle) {
                            CircleFence circleFenceBuildServerFence = CircleFence.buildServerFence(j, string, string2, latLng, d, i6, coordTypeValueOf);
                            fenceInfo2 = fenceInfo;
                            fenceInfo2.setCircleFence(circleFenceBuildServerFence);
                        } else {
                            fenceInfo2 = fenceInfo;
                            if (fenceInfo2.getFenceShape() == FenceShape.polygon) {
                                fenceInfo2.setPolygonFence(PolygonFence.buildServerFence(j, string, string2, arrayList2, i6, coordTypeValueOf));
                            } else if (fenceInfo2.getFenceShape() == FenceShape.polyline) {
                                fenceInfo2.setPolylineFence(PolylineFence.buildServerFence(j, string, string2, arrayList2, i5, i6, coordTypeValueOf));
                            } else if (fenceInfo2.getFenceShape() == FenceShape.district) {
                                DistrictFence districtFenceBuildServerFence = DistrictFence.buildServerFence(j, string, string2, i6, null);
                                districtFenceBuildServerFence.setDistrict(string3);
                                fenceInfo2.setDistrictFence(districtFenceBuildServerFence);
                            }
                        }
                        arrayList.add(fenceInfo2);
                        i++;
                        str19 = str21;
                        str18 = str22;
                        length = i2;
                        jSONArray2 = jSONArray;
                        str15 = str2;
                        str12 = str20;
                        str13 = str3;
                        str14 = str4;
                        str16 = str6;
                        str10 = str7;
                        str11 = str8;
                        str17 = str9;
                    }
                }
                fenceListResponse.setFenceInfos(arrayList);
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m1003a(String str, CoordType coordType, HistoryAlarmResponse historyAlarmResponse) {
        String str2;
        String str3 = "alarm_point";
        CoordType coordType2 = coordType != null ? coordType : CoordType.bd09ll;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (m1029b(str, historyAlarmResponse)) {
                if (jSONObject.has("size")) {
                    historyAlarmResponse.setSize(jSONObject.getInt("size"));
                }
                if (jSONObject.has(TopicKey.TOTAL)) {
                    historyAlarmResponse.setTotal(jSONObject.getInt(TopicKey.TOTAL));
                }
                ArrayList arrayList = new ArrayList();
                if (jSONObject.has("alarms")) {
                    JSONArray jSONArray = jSONObject.getJSONArray("alarms");
                    int length = jSONArray.length();
                    int i = 0;
                    while (i < length) {
                        FenceAlarmInfo fenceAlarmInfo = new FenceAlarmInfo();
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                        if (jSONObject2.has("fence_id")) {
                            fenceAlarmInfo.setFenceId(jSONObject2.getLong("fence_id"));
                        }
                        if (jSONObject2.has("fence_name")) {
                            fenceAlarmInfo.setFenceName(jSONObject2.getString("fence_name"));
                        }
                        if (jSONObject2.has("monitored_person")) {
                            fenceAlarmInfo.setMonitoredPerson(jSONObject2.getString("monitored_person"));
                        }
                        if (jSONObject2.has(AuthActivity.ACTION_KEY)) {
                            fenceAlarmInfo.setMonitoredAction((MonitoredAction) MonitoredAction.valueOf(MonitoredAction.class, jSONObject2.getString(AuthActivity.ACTION_KEY)));
                        }
                        if (jSONObject2.has(str3)) {
                            AlarmPoint alarmPoint = new AlarmPoint();
                            str2 = str3;
                            m1008a(jSONObject2.getJSONObject(str3), coordType2, alarmPoint, String.class);
                            fenceAlarmInfo.setCurrentPoint(alarmPoint);
                        } else {
                            str2 = str3;
                        }
                        if (jSONObject2.has("pre_point")) {
                            AlarmPoint alarmPoint2 = new AlarmPoint();
                            m1008a(jSONObject2.getJSONObject("pre_point"), coordType2, alarmPoint2, String.class);
                            fenceAlarmInfo.setPrePoint(alarmPoint2);
                        }
                        arrayList.add(fenceAlarmInfo);
                        i++;
                        str3 = str2;
                    }
                }
                historyAlarmResponse.setFenceAlarmInfos(arrayList);
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m1004a(String str, CoordType coordType, LatestPointResponse latestPointResponse) {
        if (coordType == null) {
            coordType = CoordType.bd09ll;
        }
        try {
            if (m1029b(str, latestPointResponse)) {
                JSONObject jSONObject = new JSONObject(str);
                LatestPoint latestPoint = new LatestPoint(null, coordType);
                if (jSONObject.has("latest_point")) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("latest_point");
                    Iterator<String> itKeys = jSONObject2.keys();
                    ArrayList arrayList = new ArrayList();
                    while (itKeys.hasNext()) {
                        arrayList.add(itKeys.next());
                    }
                    m1010a(jSONObject2, latestPoint, arrayList);
                    latestPointResponse.setLatestPoint(latestPoint);
                }
                if (jSONObject.has("limit_speed")) {
                    latestPointResponse.setLimitSpeed(jSONObject.getDouble("limit_speed"));
                }
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m1005a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        Log.e(str, str2);
    }

    /* JADX INFO: renamed from: a */
    protected static void m1006a(JSONObject jSONObject, CircleFence circleFence) throws JSONException {
        if (jSONObject.has("fenceName")) {
            circleFence.setFenceName(jSONObject.getString("fenceName"));
        }
        if (jSONObject.has("monitoredPerson")) {
            circleFence.setMonitoredPerson(jSONObject.getString("monitoredPerson"));
        }
        if (jSONObject.has("denoise")) {
            circleFence.setDenoise(jSONObject.getInt("denoise"));
        }
        if (jSONObject.has("radius")) {
            circleFence.setRadius(jSONObject.getDouble("radius"));
        }
        if (jSONObject.has("center")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("center");
            LatLng latLng = new LatLng();
            if (jSONObject2.has("latitude")) {
                latLng.latitude = jSONObject2.getDouble("latitude");
                latLng.longitude = jSONObject2.getDouble("longitude");
            }
            circleFence.setCenter(latLng);
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m1007a(JSONObject jSONObject, FailInfo.InternalError internalError) throws JSONException {
        if (jSONObject == null) {
            return;
        }
        Iterator<String> itKeys = jSONObject.keys();
        ArrayList arrayList = new ArrayList();
        while (itKeys.hasNext()) {
            arrayList.add(itKeys.next());
        }
        if (jSONObject.has("entity_name")) {
            internalError.setEntityName(jSONObject.getString("entity_name"));
            arrayList.remove("entity_name");
        }
        if (jSONObject.has(IjkMediaPlayer.OnNativeInvokeListener.ARG_ERROR)) {
            if (internalError instanceof FailInfo.ParamError) {
                ((FailInfo.ParamError) internalError).setError(jSONObject.getString(IjkMediaPlayer.OnNativeInvokeListener.ARG_ERROR));
            }
            arrayList.remove(IjkMediaPlayer.OnNativeInvokeListener.ARG_ERROR);
        }
        TrackPoint trackPoint = new TrackPoint();
        m1010a(jSONObject, trackPoint, arrayList);
        internalError.setTrackPoint(trackPoint);
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x0008 A[PHI: r0
  0x0008: PHI (r0v9 java.lang.String) = (r0v0 java.lang.String), (r0v1 java.lang.String) binds: [B:3:0x0006, B:6:0x0016] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void m1008a(org.json.JSONObject r5, com.baidu.trace.model.CoordType r6, com.baidu.trace.api.fence.AlarmPoint r7, java.lang.Class r8) throws org.json.JSONException {
        /*
            java.lang.String r0 = "loc_time"
            boolean r1 = r5.has(r0)
            if (r1 == 0) goto L10
        L8:
            long r0 = r5.getLong(r0)
            r7.setLocTime(r0)
            goto L19
        L10:
            java.lang.String r0 = "time"
            boolean r1 = r5.has(r0)
            if (r1 == 0) goto L19
            goto L8
        L19:
            java.lang.String r0 = "create_time"
            boolean r1 = r5.has(r0)
            if (r1 == 0) goto L28
            long r0 = r5.getLong(r0)
            r7.setCreateTime(r0)
        L28:
            java.lang.String r0 = "longitude"
            boolean r1 = r5.has(r0)
            if (r1 == 0) goto L48
            java.lang.String r1 = "latitude"
            boolean r2 = r5.has(r1)
            if (r2 == 0) goto L48
            com.baidu.trace.model.LatLng r2 = new com.baidu.trace.model.LatLng
            double r3 = r5.getDouble(r1)
            double r0 = r5.getDouble(r0)
            r2.<init>(r3, r0)
            r7.setLocation(r2)
        L48:
            java.lang.String r0 = "radius"
            boolean r1 = r5.has(r0)
            if (r1 == 0) goto L57
            double r0 = r5.getDouble(r0)
            r7.setRadius(r0)
        L57:
            if (r6 == 0) goto L5a
            goto L5c
        L5a:
            com.baidu.trace.model.CoordType r6 = com.baidu.trace.model.CoordType.bd09ll
        L5c:
            java.lang.String r0 = "coord_type"
            boolean r1 = r5.has(r0)
            if (r1 == 0) goto L90
            java.lang.Class<java.lang.String> r1 = java.lang.String.class
            boolean r1 = r1.isAssignableFrom(r8)
            if (r1 == 0) goto L75
            java.lang.String r5 = r5.getString(r0)
            com.baidu.trace.model.CoordType r6 = com.baidu.trace.model.CoordType.valueOf(r5)
            goto L90
        L75:
            java.lang.Class<java.lang.Integer> r1 = java.lang.Integer.class
            boolean r8 = r1.isAssignableFrom(r8)
            if (r8 == 0) goto L90
            int r5 = r5.getInt(r0)
            r6 = 1
            if (r5 == r6) goto L8e
            r6 = 2
            if (r5 == r6) goto L8b
            r6 = 3
            com.baidu.trace.model.CoordType r6 = com.baidu.trace.model.CoordType.bd09ll
            goto L90
        L8b:
            com.baidu.trace.model.CoordType r6 = com.baidu.trace.model.CoordType.gcj02
            goto L90
        L8e:
            com.baidu.trace.model.CoordType r6 = com.baidu.trace.model.CoordType.wgs84
        L90:
            r7.setCoordType(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.trace.C0791a.m1008a(org.json.JSONObject, com.baidu.trace.model.CoordType, com.baidu.trace.api.fence.AlarmPoint, java.lang.Class):void");
    }

    /* JADX INFO: renamed from: a */
    public static void m1009a(JSONObject jSONObject, Point point) throws JSONException {
        if (jSONObject.has("actual_speed") && (point instanceof SpeedingPoint)) {
            ((SpeedingPoint) point).setActualSpeed(jSONObject.getDouble("actual_speed"));
        }
        if (jSONObject.has("limit_speed") && (point instanceof SpeedingPoint)) {
            ((SpeedingPoint) point).setLimitSpeed(jSONObject.getDouble("limit_speed"));
        }
        if (jSONObject.has("acceleration")) {
            if (point instanceof HarshAccelerationPoint) {
                ((HarshAccelerationPoint) point).setAcceleration(jSONObject.getDouble("acceleration"));
            } else if (point instanceof HarshBreakingPoint) {
                ((HarshBreakingPoint) point).setAcceleration(jSONObject.getDouble("acceleration"));
            }
        }
        if (jSONObject.has("initial_speed")) {
            if (point instanceof HarshAccelerationPoint) {
                ((HarshAccelerationPoint) point).setInitialSpeed(jSONObject.getDouble("initial_speed"));
            } else if (point instanceof HarshBreakingPoint) {
                ((HarshBreakingPoint) point).setInitialSpeed(jSONObject.getDouble("initial_speed"));
            }
        }
        if (jSONObject.has("end_speed")) {
            if (point instanceof HarshAccelerationPoint) {
                ((HarshAccelerationPoint) point).setEndSpeed(jSONObject.getDouble("end_speed"));
            } else if (point instanceof HarshBreakingPoint) {
                ((HarshBreakingPoint) point).setEndSpeed(jSONObject.getDouble("end_speed"));
            }
        }
        if (jSONObject.has("centripetal_acceleration") && (point instanceof HarshSteeringPoint)) {
            ((HarshSteeringPoint) point).setCentripetalAcceleration(jSONObject.getDouble("centripetal_acceleration"));
        }
        if (jSONObject.has("turn_type") && (point instanceof HarshSteeringPoint)) {
            ((HarshSteeringPoint) point).setTurnType((TurnType) TurnType.valueOf(TurnType.class, jSONObject.getString("turn_type")));
        }
        if (jSONObject.has("speed") && (point instanceof HarshSteeringPoint)) {
            ((HarshSteeringPoint) point).setTurnSpeed(jSONObject.getDouble("speed"));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x004b A[PHI: r0
  0x004b: PHI (r0v14 java.lang.String) = (r0v2 java.lang.String), (r0v1 java.lang.String) binds: [B:23:0x0062, B:18:0x0049] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void m1010a(org.json.JSONObject r7, com.baidu.trace.model.Point r8, java.util.List<java.lang.String> r9) throws org.json.JSONException {
        /*
            Method dump skipped, instruction units count: 469
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.trace.C0791a.m1010a(org.json.JSONObject, com.baidu.trace.model.Point, java.util.List):void");
    }

    /* JADX INFO: renamed from: a */
    protected static boolean m1011a(double d, String str, BaseResponse baseResponse) {
        if (d >= 0.0d) {
            return true;
        }
        baseResponse.setStatus(2);
        baseResponse.setMessage(str + "参数错误");
        return false;
    }

    /* JADX INFO: renamed from: a */
    protected static boolean m1012a(int i, int i2, String str, String str2, Map<String, String> map, TreeMap<String, String> treeMap, OnEntityListener onEntityListener) {
        if (!C0854e.m1231a(str)) {
            if (i == 0) {
                onEntityListener.onAddEntityCallback(new AddEntityResponse(i2, 2, "entityName参数错误"));
            } else if (1 == i) {
                onEntityListener.onUpdateEntityCallback(new UpdateEntityResponse(i2, 2, "entityName参数错误"));
            } else if (2 == i) {
                onEntityListener.onDeleteEntityCallback(new DeleteEntityResponse(i2, 2, "entityName参数错误"));
            }
            return false;
        }
        try {
            treeMap.put("entity_name", URLEncoder.encode(str, Key.STRING_CHARSET_NAME));
        } catch (Exception unused) {
            treeMap.put("entity_name", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            try {
                treeMap.put("entity_desc", URLEncoder.encode(str2, Key.STRING_CHARSET_NAME));
            } catch (Exception unused2) {
                treeMap.put("entity_desc", str2);
            }
        }
        if (map != null && !map.isEmpty()) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (!C0854e.m1235b(entry.getKey())) {
                    if (i == 0) {
                        onEntityListener.onAddEntityCallback(new AddEntityResponse(i2, 2, "column_key参数错误"));
                    } else if (1 == i) {
                        onEntityListener.onUpdateEntityCallback(new UpdateEntityResponse(i2, 2, "column_key参数错误"));
                    }
                    return false;
                }
                if (!TextUtils.isEmpty(entry.getValue())) {
                    try {
                        treeMap.put(entry.getKey(), URLEncoder.encode(entry.getValue(), Key.STRING_CHARSET_NAME));
                    } catch (Exception unused3) {
                        treeMap.put(entry.getKey(), entry.getValue());
                    }
                }
            }
        }
        return true;
    }

    /* JADX INFO: renamed from: a */
    static /* synthetic */ boolean m1013a(Context context, Handler handler, DeleteFenceRequest deleteFenceRequest, StringBuffer stringBuffer, OnFenceListener onFenceListener) {
        DeleteFenceResponse deleteFenceResponse = new DeleteFenceResponse(deleteFenceRequest.getTag(), 0, StatusCodes.MSG_SUCCESS, null, FenceType.local);
        ArrayList arrayList = new ArrayList();
        String monitoredPerson = deleteFenceRequest.getMonitoredPerson();
        if (deleteFenceRequest.getFenceIds() != null) {
            ArrayList<Long> arrayList2 = new ArrayList();
            arrayList2.addAll(deleteFenceRequest.getFenceIds());
            for (Long l : arrayList2) {
                if (l != null && C0814am.m1073a(context, l.longValue(), monitoredPerson)) {
                    arrayList.add(l);
                    stringBuffer.append(String.valueOf(l));
                    stringBuffer.append(",");
                }
            }
            arrayList2.clear();
        } else if (!C0814am.m1075a(context, monitoredPerson)) {
            deleteFenceResponse.setStatus(StatusCodes.DELETE_FENCE_FAILED);
            deleteFenceResponse.setMessage(StatusCodes.MSG_DELETE_FENCE_FAILED);
            deleteFenceResponse.setFenceIds(arrayList);
            m957a(3, handler, deleteFenceResponse, onFenceListener);
            return false;
        }
        deleteFenceResponse.setFenceIds(arrayList);
        m957a(3, handler, deleteFenceResponse, onFenceListener);
        return true;
    }

    /* JADX INFO: renamed from: a */
    protected static boolean m1015a(String str, BaseRequest baseRequest, Object obj) {
        StringBuilder sb;
        String str2;
        if (baseRequest == null) {
            sb = new StringBuilder();
            sb.append(str);
            str2 = ", request object is null!";
        } else {
            if (obj != null) {
                return true;
            }
            sb = new StringBuilder();
            sb.append(str);
            str2 = ", listener is null!";
        }
        sb.append(str2);
        m1005a("BaiduTraceSDK", sb.toString());
        return false;
    }

    /* JADX INFO: renamed from: a */
    protected static boolean m1016a(String str, String str2, BaseResponse baseResponse) {
        if (C0854e.m1231a(str)) {
            return true;
        }
        baseResponse.setStatus(2);
        baseResponse.setMessage(str2 + "参数错误");
        return false;
    }

    /* JADX INFO: renamed from: a */
    protected static boolean m1017a(Map<String, List<TrackPoint>> map, StringBuffer stringBuffer) {
        if (map != null && stringBuffer != null) {
            JSONArray jSONArray = new JSONArray();
            try {
                for (Map.Entry<String, List<TrackPoint>> entry : map.entrySet()) {
                    String key = entry.getKey();
                    List<TrackPoint> value = entry.getValue();
                    if (value != null) {
                        for (TrackPoint trackPoint : value) {
                            if (trackPoint != null) {
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put("entity_name", key);
                                jSONObject.put("loc_time", trackPoint.getLocTime());
                                LatLng location = trackPoint.getLocation();
                                if (location != null) {
                                    jSONObject.put("latitude", location.getLatitude());
                                    jSONObject.put("longitude", location.getLongitude());
                                }
                                if (trackPoint.getCoordType() != null) {
                                    jSONObject.put("coord_type_input", trackPoint.getCoordType().name());
                                }
                                jSONObject.put("speed", trackPoint.getSpeed());
                                jSONObject.put("direction", trackPoint.getDirection());
                                jSONObject.put("height", trackPoint.getHeight());
                                jSONObject.put("radius", trackPoint.getRadius());
                                jSONObject.put("object_name", trackPoint.getObjectName());
                                Map<String, String> columns = trackPoint.getColumns();
                                if (columns != null && !m1018a(columns, (TreeMap<String, String>) null, jSONObject)) {
                                    return false;
                                }
                                jSONArray.put(jSONObject);
                            }
                        }
                    }
                }
                stringBuffer.append(jSONArray.toString());
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: a */
    protected static boolean m1018a(Map<String, String> map, TreeMap<String, String> treeMap, JSONObject jSONObject) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (!C0854e.m1235b(entry.getKey())) {
                return false;
            }
            if (!TextUtils.isEmpty(entry.getValue())) {
                if (treeMap != null) {
                    try {
                        treeMap.put(entry.getKey(), URLEncoder.encode(entry.getValue(), Key.STRING_CHARSET_NAME));
                    } catch (Exception unused) {
                        if (treeMap != null) {
                            treeMap.put(entry.getKey(), entry.getValue());
                        }
                    }
                } else if (jSONObject != null) {
                    jSONObject.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b */
    public static String m1019b(Context context, Handler handler, int i, Fence fence, OnFenceListener onFenceListener) {
        String str = StatusCodes.MSG_CREATE_FENCE_FAILED;
        CreateFenceResponse createFenceResponse = new CreateFenceResponse(i, 0, StatusCodes.MSG_SUCCESS, FenceType.local);
        if (fence != null) {
            CircleFence circleFence = (CircleFence) fence;
            createFenceResponse.setFenceName(circleFence.getFenceName());
            createFenceResponse.setFenceShape(FenceShape.circle);
            if (C0854e.m1230a(circleFence.getCenter())) {
                if (circleFence.getCoordType() != null && CoordType.bd09ll != circleFence.getCoordType()) {
                    double[] dArrWgsToBaidu = CoordType.wgs84 == circleFence.getCoordType() ? TraceJniInterface.wgsToBaidu(circleFence.getCenter().latitude, circleFence.getCenter().longitude) : CoordType.gcj02 == circleFence.getCoordType() ? TraceJniInterface.gcjToBaidu(circleFence.getCenter().latitude, circleFence.getCenter().longitude) : null;
                    if (dArrWgsToBaidu != null) {
                        circleFence.setCenter(new LatLng(dArrWgsToBaidu[0], dArrWgsToBaidu[1]));
                    }
                }
                circleFence.setCoordType(CoordType.bd09ll);
                if (!m1016a(circleFence.getFenceName(), "fenceName", createFenceResponse) || !m1016a(circleFence.getMonitoredPerson(), "monitoredPerson", createFenceResponse) || !m1011a(circleFence.getDenoise(), "denoise", createFenceResponse) || !m1011a(circleFence.getRadius(), "radius", createFenceResponse)) {
                    m957a(1, handler, createFenceResponse, onFenceListener);
                    return null;
                }
                StringBuffer stringBuffer = new StringBuffer();
                StringBuffer stringBuffer2 = new StringBuffer();
                try {
                    m981a(circleFence, stringBuffer2, stringBuffer);
                    long jM1056a = C0814am.m1056a(context, circleFence.getFenceName(), circleFence.getMonitoredPerson(), circleFence.getCoordType(), circleFence.getDenoise(), FenceShape.circle, stringBuffer.toString());
                    if (-1 != jM1056a) {
                        circleFence.setFenceId(jM1056a);
                        createFenceResponse.setFenceId(jM1056a);
                        m957a(1, handler, createFenceResponse, onFenceListener);
                        return stringBuffer2.toString();
                    }
                } catch (JSONException unused) {
                }
                createFenceResponse.setStatus(StatusCodes.CREATE_FENCE_FAILED);
            }
            createFenceResponse.setStatus(2);
            createFenceResponse.setMessage("center参数错误");
            m957a(1, handler, createFenceResponse, onFenceListener);
            return null;
        }
        createFenceResponse.setStatus(2);
        str = "fence参数错误";
        createFenceResponse.setMessage(str);
        m957a(1, handler, createFenceResponse, onFenceListener);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b */
    public static String m1020b(Context context, Handler handler, UpdateFenceRequest updateFenceRequest, OnFenceListener onFenceListener) {
        UpdateFenceResponse updateFenceResponse = new UpdateFenceResponse(updateFenceRequest.getTag(), 0, StatusCodes.MSG_SUCCESS, FenceType.local);
        if (updateFenceRequest.getFence() == null) {
            updateFenceResponse.setStatus(2);
            updateFenceResponse.setMessage("fence参数错误");
            m957a(2, handler, updateFenceResponse, onFenceListener);
            return null;
        }
        long fenceId = updateFenceRequest.getFence().getFenceId();
        CircleFence circleFenceM1058a = C0814am.m1058a(context, fenceId);
        if (circleFenceM1058a == null) {
            updateFenceResponse.setStatus(StatusCodes.NOT_EXIST_FENCE);
            updateFenceResponse.setMessage(StatusCodes.MSG_NOT_EXIST_FENCE);
            m957a(2, handler, updateFenceResponse, onFenceListener);
            return null;
        }
        CircleFence circleFence = (CircleFence) updateFenceRequest.getFence();
        if (!TextUtils.isEmpty(circleFence.getFenceName())) {
            if (!m1016a(circleFence.getFenceName(), "fenceName", updateFenceResponse)) {
                m957a(2, handler, updateFenceResponse, onFenceListener);
                return null;
            }
            circleFenceM1058a.setFenceName(circleFence.getFenceName());
        }
        if (!TextUtils.isEmpty(circleFence.getMonitoredPerson())) {
            if (!m1016a(circleFence.getMonitoredPerson(), "monitoredPerson", updateFenceResponse)) {
                m957a(2, handler, updateFenceResponse, onFenceListener);
                return null;
            }
            circleFenceM1058a.setMonitoredPerson(circleFence.getMonitoredPerson());
        }
        if (C0854e.m1230a(circleFence.getCenter())) {
            if (circleFence.getCoordType() == null || CoordType.bd09ll == circleFence.getCoordType()) {
                circleFenceM1058a.setCenter(circleFence.getCenter());
            } else {
                double[] dArrWgsToBaidu = CoordType.wgs84 == circleFence.getCoordType() ? TraceJniInterface.wgsToBaidu(circleFence.getCenter().latitude, circleFence.getCenter().longitude) : CoordType.gcj02 == circleFence.getCoordType() ? TraceJniInterface.gcjToBaidu(circleFence.getCenter().latitude, circleFence.getCenter().longitude) : null;
                if (dArrWgsToBaidu == null) {
                    updateFenceResponse.setStatus(2);
                    updateFenceResponse.setMessage("center参数错误");
                    m957a(2, handler, updateFenceResponse, onFenceListener);
                    return null;
                }
                circleFenceM1058a.setCenter(new LatLng(dArrWgsToBaidu[0], dArrWgsToBaidu[1]));
            }
            circleFenceM1058a.setCoordType(CoordType.bd09ll);
        }
        if (!m1011a(circleFence.getDenoise(), "denoise", updateFenceResponse) || !m1011a(circleFence.getRadius(), "radius", updateFenceResponse)) {
            m957a(2, handler, updateFenceResponse, onFenceListener);
            return null;
        }
        if (circleFence.getDenoise() > 0) {
            circleFenceM1058a.setDenoise(circleFence.getDenoise());
        }
        if (circleFence.getRadius() > 0.0d) {
            circleFenceM1058a.setRadius(circleFence.getRadius());
        }
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        try {
            m981a(circleFenceM1058a, stringBuffer2, stringBuffer);
            if (C0814am.m1074a(context, fenceId, circleFenceM1058a.getFenceName(), circleFenceM1058a.getMonitoredPerson(), circleFenceM1058a.getCoordType(), circleFenceM1058a.getDenoise(), FenceShape.circle, stringBuffer.toString())) {
                updateFenceResponse.setFenceName(circleFenceM1058a.getFenceName());
                updateFenceResponse.setFenceId(fenceId);
                m957a(2, handler, updateFenceResponse, onFenceListener);
                return stringBuffer2.toString();
            }
            updateFenceResponse.setStatus(StatusCodes.UPDATE_FENCE_FAILED);
            updateFenceResponse.setMessage(StatusCodes.MSG_UPDATE_FENCE_FAILED);
            m957a(2, handler, updateFenceResponse, onFenceListener);
            return null;
        } catch (JSONException unused) {
            updateFenceResponse.setStatus(StatusCodes.UPDATE_FENCE_FAILED);
            updateFenceResponse.setMessage(StatusCodes.MSG_UPDATE_FENCE_FAILED);
            updateFenceResponse.setFenceId(fenceId);
            m957a(2, handler, updateFenceResponse, onFenceListener);
            return null;
        }
    }

    /* JADX INFO: renamed from: b */
    protected static String m1021b(List<LatLng> list) {
        StringBuffer stringBuffer = new StringBuffer();
        for (LatLng latLng : list) {
            stringBuffer.append(latLng.latitude);
            stringBuffer.append(",");
            stringBuffer.append(latLng.longitude);
            stringBuffer.append(";");
        }
        return TextUtils.isEmpty(stringBuffer.toString()) ? "" : stringBuffer.substring(0, stringBuffer.length() - 1);
    }

    /* JADX INFO: renamed from: b */
    protected static String m1022b(TreeMap<String, String> treeMap) throws UnsupportedEncodingException {
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, String> entry : treeMap.entrySet()) {
            String value = entry.getValue();
            if (!TextUtils.isEmpty(value)) {
                boolean zContains = value.contains("%");
                stringBuffer.append(entry.getKey());
                stringBuffer.append("=");
                if (zContains) {
                    stringBuffer.append(URLEncoder.encode(value, Key.STRING_CHARSET_NAME));
                } else {
                    stringBuffer.append(value);
                }
                stringBuffer.append("&");
            }
        }
        if (stringBuffer.length() > 0) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        return stringBuffer.toString();
    }

    /* JADX INFO: renamed from: b */
    static /* synthetic */ void m1023b(Context context, Handler handler, FenceListRequest fenceListRequest, OnFenceListener onFenceListener) {
        FenceListResponse fenceListResponse = new FenceListResponse(fenceListRequest.getTag(), 0, StatusCodes.MSG_SUCCESS, 0, FenceType.local, null);
        ArrayList arrayList = new ArrayList();
        C0814am.m1065a(context, fenceListRequest.getMonitoredPerson(), fenceListRequest.getFenceIds(), arrayList);
        fenceListResponse.setSize(arrayList.size());
        fenceListResponse.setFenceInfos(arrayList);
        m957a(4, handler, fenceListResponse, onFenceListener);
    }

    /* JADX INFO: renamed from: b */
    static /* synthetic */ void m1024b(Context context, Handler handler, HistoryAlarmRequest historyAlarmRequest, OnFenceListener onFenceListener) {
        HistoryAlarmResponse historyAlarmResponse = new HistoryAlarmResponse(historyAlarmRequest.getTag(), 0, StatusCodes.MSG_SUCCESS, 0, 0, FenceType.local, null);
        ArrayList arrayList = new ArrayList();
        C0814am.m1064a(context, historyAlarmRequest, arrayList);
        historyAlarmResponse.setTotal(arrayList.size());
        historyAlarmResponse.setSize(arrayList.size());
        historyAlarmResponse.setFenceAlarmInfos(arrayList);
        m957a(7, handler, historyAlarmResponse, onFenceListener);
    }

    /* JADX INFO: renamed from: b */
    static /* synthetic */ void m1025b(Context context, Handler handler, MonitoredStatusByLocationRequest monitoredStatusByLocationRequest, OnFenceListener onFenceListener) {
        Fence fenceM1046b;
        int tag = monitoredStatusByLocationRequest.getTag();
        String monitoredPerson = monitoredStatusByLocationRequest.getMonitoredPerson();
        LatLng latLng = monitoredStatusByLocationRequest.getLatLng();
        CoordType coordType = monitoredStatusByLocationRequest.getCoordType();
        MonitoredStatusByLocationResponse monitoredStatusByLocationResponse = new MonitoredStatusByLocationResponse(tag, 0, StatusCodes.MSG_SUCCESS, FenceType.local);
        if (TextUtils.isEmpty(monitoredPerson)) {
            monitoredStatusByLocationResponse.setStatus(2);
            monitoredStatusByLocationResponse.setMessage("monitored_person参数错误");
        } else if (C0854e.m1230a(latLng)) {
            if (coordType != null && CoordType.bd09ll != coordType) {
                double[] dArrWgsToBaidu = CoordType.wgs84 == coordType ? TraceJniInterface.wgsToBaidu(latLng.latitude, latLng.longitude) : CoordType.gcj02 == coordType ? TraceJniInterface.gcjToBaidu(latLng.latitude, latLng.longitude) : null;
                if (dArrWgsToBaidu != null) {
                    latLng.latitude = dArrWgsToBaidu[0];
                    latLng.longitude = dArrWgsToBaidu[1];
                }
                monitoredStatusByLocationResponse.setStatus(2);
                monitoredStatusByLocationResponse.setMessage("location参数错误");
            }
            if (C0819ar.f1575a == null) {
                C0819ar.f1575a = new C0811aj(context);
            }
            C0814am.m1066a(context, (String) null, (String[]) null);
            if (C0819ar.f1575a.m1045a()) {
                m957a(6, handler, monitoredStatusByLocationResponse, onFenceListener);
                return;
            }
            ArrayList arrayList = new ArrayList();
            if (monitoredStatusByLocationRequest.getFenceIds() != null) {
                ArrayList<Long> arrayList2 = new ArrayList();
                arrayList2.addAll(monitoredStatusByLocationRequest.getFenceIds());
                for (Long l : arrayList2) {
                    if (l != null && (fenceM1046b = C0819ar.f1575a.m1046b(l.longValue())) != null) {
                        m982a(fenceM1046b, monitoredPerson, latLng, arrayList);
                    }
                }
                arrayList2.clear();
            } else {
                C0819ar.f1575a.m1044a(monitoredPerson, latLng, arrayList);
            }
            monitoredStatusByLocationResponse.setSize(arrayList.size());
            monitoredStatusByLocationResponse.setMonitoredStatusInfos(arrayList);
        } else {
            monitoredStatusByLocationResponse.setStatus(2);
            monitoredStatusByLocationResponse.setMessage("location参数错误");
        }
        m957a(6, handler, monitoredStatusByLocationResponse, onFenceListener);
    }

    /* JADX INFO: renamed from: b */
    static /* synthetic */ void m1026b(Context context, Handler handler, MonitoredStatusRequest monitoredStatusRequest, OnFenceListener onFenceListener) {
        MonitoredStatusResponse monitoredStatusResponse = new MonitoredStatusResponse(monitoredStatusRequest.getTag(), 0, StatusCodes.MSG_SUCCESS, 0, FenceType.local, null);
        ArrayList arrayList = new ArrayList();
        if (C0854e.m1231a(monitoredStatusRequest.getMonitoredPerson())) {
            C0814am.m1067a(context, monitoredStatusRequest.getFenceIds(), monitoredStatusRequest.getMonitoredPerson(), arrayList);
        }
        if (monitoredStatusRequest.getFenceIds() != null && arrayList.isEmpty()) {
            ArrayList<Long> arrayList2 = new ArrayList();
            arrayList2.addAll(monitoredStatusRequest.getFenceIds());
            for (Long l : arrayList2) {
                if (l != null) {
                    MonitoredStatusInfo monitoredStatusInfo = new MonitoredStatusInfo();
                    monitoredStatusInfo.setFenceId(l.longValue());
                    arrayList.add(monitoredStatusInfo);
                }
            }
            arrayList2.clear();
        }
        monitoredStatusResponse.setSize(arrayList.size());
        monitoredStatusResponse.setMonitoredStatusInfos(arrayList);
        m957a(5, handler, monitoredStatusResponse, onFenceListener);
    }

    /* JADX INFO: renamed from: b */
    protected static void m1027b(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("status") && jSONObject.getInt("status") == 0) {
                C0858d.f1767a = URLEncoder.encode(jSONObject.getString("token"), Key.STRING_CHARSET_NAME);
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b */
    public boolean m1028b() {
        return this.f1167a >= 0 && this.f1168b > 0;
    }

    /* JADX INFO: renamed from: b */
    public static boolean m1029b(String str, BaseResponse baseResponse) {
        String string;
        String str2;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("status_sp")) {
                if (jSONObject.has("status")) {
                    baseResponse.setStatus(jSONObject.getInt("status"));
                }
                if (!jSONObject.has("message")) {
                    if (jSONObject.has("status")) {
                        if (jSONObject.getInt("status") != 0) {
                            baseResponse.setMessage(StatusCodes.MSG_SERVICE_INTERNAL_ERROR);
                        } else {
                            string = StatusCodes.MSG_SUCCESS;
                        }
                    }
                    return true;
                }
                string = jSONObject.getString("message");
                baseResponse.setMessage(string);
                return true;
            }
            int i = jSONObject.getInt("status_sp");
            if (i != 1) {
                switch (i) {
                    case 104:
                    case 106:
                    case 107:
                        C0858d.m1248a();
                        baseResponse.setStatus(StatusCodes.AUTHENTICATION_UNFINISHED);
                        str2 = StatusCodes.MSG_AUTHENTICATION_UNFINISHED;
                        break;
                    case 105:
                    case 108:
                        C0858d.m1248a();
                        baseResponse.setStatus(StatusCodes.AUTHENTICATION_FAILED);
                        str2 = StatusCodes.MSG_AUTHENTICATION_FAILED;
                        break;
                    default:
                        baseResponse.setStatus(StatusCodes.REQUEST_FAILED);
                        str2 = StatusCodes.MSG_REQUEST_FAILED;
                        break;
                }
                baseResponse.setMessage(str2);
            } else {
                baseResponse.setStatus(i);
                baseResponse.setMessage(StatusCodes.MSG_SERVICE_INTERNAL_ERROR);
            }
            return false;
        } catch (Exception unused) {
            baseResponse.setStatus(StatusCodes.PARSE_RESPONSE_FAILED);
            baseResponse.setMessage(StatusCodes.MSG_PARSE_RESPONSE_FAILED);
            return false;
        }
    }

    /* JADX INFO: renamed from: a */
    public String m1030a() {
        if (!m1028b()) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append(this.f1171e);
        stringBuffer.append(TopicKey.HEIGHT);
        int i = this.f1169c;
        if (i != 460) {
            stringBuffer.append(i);
        }
        stringBuffer.append(String.format(Locale.CHINA, "h%xh%xh%x", Integer.valueOf(this.f1170d), Integer.valueOf(this.f1167a), Integer.valueOf(this.f1168b)));
        return stringBuffer.toString();
    }
}
