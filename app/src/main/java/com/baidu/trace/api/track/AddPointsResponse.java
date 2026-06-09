package com.baidu.trace.api.track;

import com.baidu.trace.model.BaseResponse;

/* JADX INFO: loaded from: classes.dex */
public class AddPointsResponse extends BaseResponse {

    /* JADX INFO: renamed from: a */
    private int f1522a;

    /* JADX INFO: renamed from: b */
    private FailInfo f1523b;

    public AddPointsResponse() {
    }

    public AddPointsResponse(int i) {
        this.tag = i;
    }

    public AddPointsResponse(int i, int i2, String str) {
        super(i, i2, str);
    }

    public AddPointsResponse(int i, int i2, String str, int i3, FailInfo failInfo) {
        super(i, i2, str);
        this.f1522a = i3;
        this.f1523b = failInfo;
    }

    public FailInfo getFailInfo() {
        return this.f1523b;
    }

    public int getSuccessNum() {
        return this.f1522a;
    }

    public void setFailInfo(FailInfo failInfo) {
        this.f1523b = failInfo;
    }

    public void setSuccessNum(int i) {
        this.f1522a = i;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("AddPointsResponse{");
        stringBuffer.append("tag=");
        stringBuffer.append(this.tag);
        stringBuffer.append(", status=");
        stringBuffer.append(this.status);
        stringBuffer.append(", message=");
        stringBuffer.append(this.message);
        stringBuffer.append('\'');
        stringBuffer.append(", successNum=");
        stringBuffer.append(this.f1522a);
        stringBuffer.append(", failInfo=");
        stringBuffer.append(this.f1523b);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
