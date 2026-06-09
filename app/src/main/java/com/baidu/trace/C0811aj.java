package com.baidu.trace;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.baidu.trace.api.fence.CircleFence;
import com.baidu.trace.api.fence.Fence;
import com.baidu.trace.api.fence.MonitoredAction;
import com.baidu.trace.api.fence.MonitoredStatus;
import com.baidu.trace.api.fence.MonitoredStatusInfo;
import com.baidu.trace.model.CoordType;
import com.baidu.trace.model.LatLng;
import com.baidu.trace.p012c.C0854e;
import com.jieli.stream.p016dv.running2.util.IConstant;
import com.tencent.tauth.AuthActivity;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.baidu.trace.aj */
/* JADX INFO: loaded from: classes.dex */
public final class C0811aj {

    /* JADX INFO: renamed from: a */
    private Map<Long, Fence> f1249a = new ConcurrentHashMap();

    /* JADX INFO: renamed from: b */
    private Map<Long, MonitoredStatus> f1250b = new ConcurrentHashMap();

    /* JADX INFO: renamed from: c */
    private C0848bi f1251c;

    public C0811aj(Context context) {
        try {
            File file = new File("/data/data/" + C0854e.m1238c(context) + "/shared_prefs", "fenceStatus.xml");
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: renamed from: a */
    private void m1038a(CircleFence circleFence, C0848bi c0848bi, MonitoredAction monitoredAction, Handler handler) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject.put("fence_id", circleFence.getFenceId());
            jSONObject.put("fence", circleFence.getFenceName());
            jSONObject.put("monitored_person", circleFence.getMonitoredPerson());
            jSONObject.put(AuthActivity.ACTION_KEY, monitoredAction.name());
            jSONObject2.put("time", C0854e.m1233b());
            jSONObject2.put("latitude", c0848bi.getLocation().latitude);
            jSONObject2.put("longitude", c0848bi.getLocation().longitude);
            jSONObject2.put("radius", c0848bi.getRadius());
            jSONObject2.put("coord_type", CoordType.bd09ll.name());
            jSONObject.put("cur_point", jSONObject2);
            if (this.f1251c == null) {
                this.f1251c = c0848bi;
            }
            jSONObject3.put("time", this.f1251c.getLocTime());
            jSONObject3.put("latitude", this.f1251c.getLocation().latitude);
            jSONObject3.put("longitude", this.f1251c.getLocation().longitude);
            jSONObject3.put("radius", this.f1251c.getRadius());
            jSONObject3.put("coord_type", CoordType.bd09ll.name());
            jSONObject.put("pre_point", jSONObject3);
            C0814am.m1068a(circleFence, monitoredAction, jSONObject2.toString(), jSONObject3.toString());
            Message messageObtainMessage = handler.obtainMessage(IConstant.OP_DOWNLOAD_FILES);
            messageObtainMessage.obj = jSONObject.toString();
            handler.sendMessage(messageObtainMessage);
        } catch (JSONException unused) {
        }
    }

    /* JADX INFO: renamed from: b */
    private void m1039b(long j, MonitoredStatus monitoredStatus) {
        this.f1250b.put(Long.valueOf(j), monitoredStatus);
        C0814am.m1062a(j, monitoredStatus);
    }

    /* JADX INFO: renamed from: a */
    protected final synchronized void m1040a(long j) {
        this.f1249a.remove(Long.valueOf(j));
    }

    /* JADX INFO: renamed from: a */
    protected final synchronized void m1041a(long j, Fence fence) {
        this.f1249a.put(Long.valueOf(j), fence);
    }

    /* JADX INFO: renamed from: a */
    protected final synchronized void m1042a(long j, MonitoredStatus monitoredStatus) {
        this.f1250b.put(Long.valueOf(j), monitoredStatus);
    }

    /* JADX INFO: renamed from: a */
    protected final synchronized void m1043a(C0848bi c0848bi, Handler handler) {
        MonitoredAction monitoredAction;
        for (Fence fence : this.f1249a.values()) {
            if (fence instanceof CircleFence) {
                CircleFence circleFence = (CircleFence) fence;
                if (C0881z.f1868c.equals(circleFence.getMonitoredPerson()) && (circleFence.getDenoise() <= 0 || c0848bi.getRadius() <= circleFence.getDenoise())) {
                    LatLng center = circleFence.getCenter();
                    MonitoredStatus monitoredStatus = MonitoredStatus.unknown;
                    if (this.f1250b.containsKey(Long.valueOf(circleFence.getFenceId()))) {
                        monitoredStatus = this.f1250b.get(Long.valueOf(circleFence.getFenceId()));
                    }
                    if (circleFence.getRadius() > C0854e.m1223a(center, c0848bi.getLocation())) {
                        if (MonitoredStatus.unknown == monitoredStatus || MonitoredStatus.out == monitoredStatus) {
                            m1039b(circleFence.getFenceId(), MonitoredStatus.in);
                            if (MonitoredStatus.out == monitoredStatus) {
                                monitoredAction = MonitoredAction.enter;
                                m1038a(circleFence, c0848bi, monitoredAction, handler);
                            }
                        }
                    } else if (MonitoredStatus.in == monitoredStatus) {
                        m1039b(circleFence.getFenceId(), MonitoredStatus.out);
                        monitoredAction = MonitoredAction.exit;
                        m1038a(circleFence, c0848bi, monitoredAction, handler);
                    } else if (MonitoredStatus.unknown == monitoredStatus) {
                        m1039b(circleFence.getFenceId(), MonitoredStatus.out);
                    }
                }
            }
        }
        this.f1251c = c0848bi;
    }

    /* JADX INFO: renamed from: a */
    protected final synchronized void m1044a(String str, LatLng latLng, List<MonitoredStatusInfo> list) {
        Iterator<Fence> it = this.f1249a.values().iterator();
        while (it.hasNext()) {
            C0791a.m982a(it.next(), str, latLng, list);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0011  */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final synchronized boolean m1045a() {
        /*
            r1 = this;
            monitor-enter(r1)
            java.util.Map<java.lang.Long, com.baidu.trace.api.fence.Fence> r0 = r1.f1249a     // Catch: java.lang.Throwable -> L13
            if (r0 == 0) goto L11
            java.util.Map<java.lang.Long, com.baidu.trace.api.fence.Fence> r0 = r1.f1249a     // Catch: java.lang.Throwable -> L13
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> L13
            if (r0 == 0) goto Le
            goto L11
        Le:
            r0 = 0
        Lf:
            monitor-exit(r1)
            return r0
        L11:
            r0 = 1
            goto Lf
        L13:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.trace.C0811aj.m1045a():boolean");
    }

    /* JADX INFO: renamed from: b */
    protected final synchronized Fence m1046b(long j) {
        if (this.f1249a == null) {
            return null;
        }
        return this.f1249a.get(Long.valueOf(j));
    }

    /* JADX INFO: renamed from: b */
    protected final synchronized void m1047b() {
        m1048c();
        this.f1249a = null;
        this.f1250b = null;
    }

    /* JADX INFO: renamed from: c */
    protected final synchronized void m1048c() {
        if (this.f1249a != null) {
            this.f1249a.clear();
        }
        if (this.f1250b != null) {
            this.f1250b.clear();
        }
    }
}
