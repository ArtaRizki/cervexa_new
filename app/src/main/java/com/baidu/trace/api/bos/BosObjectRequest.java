package com.baidu.trace.api.bos;

import com.baidu.trace.model.BaseRequest;

/* JADX INFO: loaded from: classes.dex */
public class BosObjectRequest extends BaseRequest {

    /* JADX INFO: renamed from: a */
    private String f1338a;

    /* JADX INFO: renamed from: b */
    private BosObjectType f1339b;

    public BosObjectRequest() {
        this.f1338a = null;
        this.f1339b = BosObjectType.image;
    }

    public BosObjectRequest(int i, long j) {
        super(i, j);
        this.f1338a = null;
        this.f1339b = BosObjectType.image;
    }

    public BosObjectRequest(int i, long j, String str, BosObjectType bosObjectType) {
        super(i, j);
        this.f1338a = null;
        this.f1339b = BosObjectType.image;
        this.f1338a = str;
        this.f1339b = bosObjectType;
    }

    public String getObjectKey() {
        return this.f1338a;
    }

    public BosObjectType getObjectType() {
        return this.f1339b;
    }

    public void setObjectKey(String str) {
        this.f1338a = str;
    }

    public void setObjectType(BosObjectType bosObjectType) {
        this.f1339b = bosObjectType;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("BosObjectRequest{");
        stringBuffer.append("objectKey='");
        stringBuffer.append(this.f1338a);
        stringBuffer.append('\'');
        stringBuffer.append(", objectType=");
        stringBuffer.append(this.f1339b);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
