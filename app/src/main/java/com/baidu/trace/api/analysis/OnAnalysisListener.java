package com.baidu.trace.api.analysis;

/* JADX INFO: loaded from: classes.dex */
public interface OnAnalysisListener {
    void onDrivingBehaviorCallback(DrivingBehaviorResponse drivingBehaviorResponse);

    void onStayPointCallback(StayPointResponse stayPointResponse);
}
