package com.baidu.trace.model;

import com.baidu.trace.api.fence.FenceAlarmPushInfo;

/* JADX INFO: loaded from: classes.dex */
public final class PushMessage {

    /* JADX INFO: renamed from: a */
    private String f1819a;

    /* JADX INFO: renamed from: b */
    private FenceAlarmPushInfo f1820b;

    public final FenceAlarmPushInfo getFenceAlarmPushInfo() {
        return this.f1820b;
    }

    public final String getMessage() {
        return this.f1819a;
    }

    public final void setFenceAlarmPushInfo(FenceAlarmPushInfo fenceAlarmPushInfo) {
        this.f1820b = fenceAlarmPushInfo;
    }

    public final void setMessage(String str) {
        this.f1819a = str;
    }

    public final String toString() {
        return "PushMessage [fenceAlarmPushInfo=" + this.f1820b + "]";
    }
}
