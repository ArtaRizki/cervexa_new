package com.baidu.trace.api.fence;

/* JADX INFO: loaded from: classes.dex */
public interface OnFenceListener {
    void onCreateFenceCallback(CreateFenceResponse createFenceResponse);

    void onDeleteFenceCallback(DeleteFenceResponse deleteFenceResponse);

    void onFenceListCallback(FenceListResponse fenceListResponse);

    void onHistoryAlarmCallback(HistoryAlarmResponse historyAlarmResponse);

    void onMonitoredStatusByLocationCallback(MonitoredStatusByLocationResponse monitoredStatusByLocationResponse);

    void onMonitoredStatusCallback(MonitoredStatusResponse monitoredStatusResponse);

    void onUpdateFenceCallback(UpdateFenceResponse updateFenceResponse);
}
