package com.baidu.trace;

import com.baidu.trace.api.fence.CreateFenceResponse;
import com.baidu.trace.api.fence.DeleteFenceResponse;
import com.baidu.trace.api.fence.FenceListResponse;
import com.baidu.trace.api.fence.HistoryAlarmResponse;
import com.baidu.trace.api.fence.MonitoredStatusByLocationResponse;
import com.baidu.trace.api.fence.MonitoredStatusResponse;
import com.baidu.trace.api.fence.OnFenceListener;
import com.baidu.trace.api.fence.UpdateFenceResponse;
import com.baidu.trace.model.BaseResponse;

/* JADX INFO: renamed from: com.baidu.trace.ai */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0810ai implements Runnable {

    /* JADX INFO: renamed from: a */
    private /* synthetic */ int f1246a;

    /* JADX INFO: renamed from: b */
    private /* synthetic */ OnFenceListener f1247b;

    /* JADX INFO: renamed from: c */
    private /* synthetic */ BaseResponse f1248c;

    RunnableC0810ai(int i, OnFenceListener onFenceListener, BaseResponse baseResponse) {
        this.f1246a = i;
        this.f1247b = onFenceListener;
        this.f1248c = baseResponse;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f1246a) {
            case 1:
                this.f1247b.onCreateFenceCallback((CreateFenceResponse) this.f1248c);
                break;
            case 2:
                this.f1247b.onUpdateFenceCallback((UpdateFenceResponse) this.f1248c);
                break;
            case 3:
                this.f1247b.onDeleteFenceCallback((DeleteFenceResponse) this.f1248c);
                break;
            case 4:
                this.f1247b.onFenceListCallback((FenceListResponse) this.f1248c);
                break;
            case 5:
                this.f1247b.onMonitoredStatusCallback((MonitoredStatusResponse) this.f1248c);
                break;
            case 6:
                this.f1247b.onMonitoredStatusByLocationCallback((MonitoredStatusByLocationResponse) this.f1248c);
                break;
            case 7:
                this.f1247b.onHistoryAlarmCallback((HistoryAlarmResponse) this.f1248c);
                break;
        }
    }
}
