package com.baidu.trace.model;

/* JADX INFO: loaded from: classes.dex */
public class BaseRequest {

    /* JADX INFO: renamed from: a */
    private boolean f1802a = false;
    public long serviceId;
    public int tag;

    public BaseRequest() {
    }

    public BaseRequest(int i, long j) {
        this.tag = i;
        this.serviceId = j;
    }

    public void cancel() {
        this.f1802a = true;
    }

    public long getServiceId() {
        return this.serviceId;
    }

    public int getTag() {
        return this.tag;
    }

    public boolean isCanceled() {
        return this.f1802a;
    }

    public void setServiceId(long j) {
        this.serviceId = j;
    }

    public void setTag(int i) {
        this.tag = i;
    }
}
