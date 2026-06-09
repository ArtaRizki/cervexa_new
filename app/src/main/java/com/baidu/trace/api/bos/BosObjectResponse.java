package com.baidu.trace.api.bos;

import com.baidu.trace.model.BaseResponse;

/* JADX INFO: loaded from: classes.dex */
public class BosObjectResponse extends BaseResponse {

    /* JADX INFO: renamed from: a */
    protected BosObjectType f1340a;

    /* JADX INFO: renamed from: b */
    protected String f1341b;

    public BosObjectResponse() {
        this.f1340a = BosObjectType.image;
        this.f1341b = "";
    }

    public BosObjectResponse(int i, int i2, String str) {
        super(i, i2, str);
        this.f1340a = BosObjectType.image;
        this.f1341b = "";
    }

    public String getObjectKey() {
        return this.f1341b;
    }

    public BosObjectType getObjectType() {
        return this.f1340a;
    }

    public void setObjectKey(String str) {
        this.f1341b = str;
    }

    public void setObjectType(BosObjectType bosObjectType) {
        this.f1340a = bosObjectType;
    }

    public String toString() {
        return "BosObjectResponse [tag=" + this.tag + ", status=" + this.status + ", message=" + this.message + ", objectType=" + this.f1340a + ", objectKey=" + this.f1341b + "]";
    }
}
