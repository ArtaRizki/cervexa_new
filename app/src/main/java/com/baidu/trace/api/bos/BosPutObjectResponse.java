package com.baidu.trace.api.bos;

import com.baidubce.services.bos.model.ObjectMetadata;

/* JADX INFO: loaded from: classes.dex */
public final class BosPutObjectResponse extends BosObjectResponse {

    /* JADX INFO: renamed from: c */
    private String f1348c;

    /* JADX INFO: renamed from: d */
    private ObjectMetadata f1349d;

    public BosPutObjectResponse() {
        this.f1349d = null;
    }

    public BosPutObjectResponse(int i, int i2, String str) {
        super(i, i2, str);
        this.f1349d = null;
    }

    public final String getETag() {
        return this.f1348c;
    }

    public final ObjectMetadata getMetaData() {
        return this.f1349d;
    }

    public final void setETag(String str) {
        this.f1348c = str;
    }

    public final void setMetaData(ObjectMetadata objectMetadata) {
        this.f1349d = objectMetadata;
    }

    @Override // com.baidu.trace.api.bos.BosObjectResponse
    public final String toString() {
        ObjectMetadata objectMetadata = this.f1349d;
        return "BosPutObjectResponse [tag=" + this.tag + ", status=" + this.status + ", message=" + this.message + ", objectType=" + this.f1340a + ", objectKey=" + this.f1341b + ", eTag=" + this.f1348c + ", metaData=" + (objectMetadata != null ? objectMetadata.toString() : "") + "]";
    }
}
