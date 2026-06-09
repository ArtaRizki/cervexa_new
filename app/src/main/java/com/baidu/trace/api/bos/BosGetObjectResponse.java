package com.baidu.trace.api.bos;

import com.baidubce.services.bos.model.ObjectMetadata;
import java.io.ByteArrayOutputStream;

/* JADX INFO: loaded from: classes.dex */
public final class BosGetObjectResponse extends BosObjectResponse {

    /* JADX INFO: renamed from: c */
    private String f1335c;

    /* JADX INFO: renamed from: d */
    private ObjectMetadata f1336d;

    /* JADX INFO: renamed from: e */
    private ByteArrayOutputStream f1337e;

    public BosGetObjectResponse() {
        this.f1336d = null;
        this.f1337e = null;
    }

    public BosGetObjectResponse(int i, int i2, String str) {
        super(i, i2, str);
        this.f1336d = null;
        this.f1337e = null;
    }

    public final String getETag() {
        return this.f1335c;
    }

    public final ObjectMetadata getMetaData() {
        return this.f1336d;
    }

    public final ByteArrayOutputStream getObjectContent() {
        return this.f1337e;
    }

    public final void setETag(String str) {
        this.f1335c = str;
    }

    public final void setMetaData(ObjectMetadata objectMetadata) {
        this.f1336d = objectMetadata;
    }

    public final void setObjectContent(ByteArrayOutputStream byteArrayOutputStream) {
        this.f1337e = byteArrayOutputStream;
    }

    @Override // com.baidu.trace.api.bos.BosObjectResponse
    public final String toString() {
        ObjectMetadata objectMetadata = this.f1336d;
        String string = objectMetadata != null ? objectMetadata.toString() : "";
        ByteArrayOutputStream byteArrayOutputStream = this.f1337e;
        return "BosGetObjectResponse [tag=" + this.tag + ", status=" + this.status + ", message=" + this.message + ", objectType=" + this.f1340a + ", objectKey=" + this.f1341b + ", eTag=" + this.f1335c + ", metaData=" + string + ", objectContent size=" + (byteArrayOutputStream != null ? byteArrayOutputStream.size() : 0) + "]";
    }
}
