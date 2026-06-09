package com.baidu.trace.api.bos;

/* JADX INFO: loaded from: classes.dex */
public final class BosGeneratePresignedUrlResponse extends BosObjectResponse {

    /* JADX INFO: renamed from: c */
    private String f1334c;

    public BosGeneratePresignedUrlResponse() {
    }

    public BosGeneratePresignedUrlResponse(int i, int i2, String str) {
        super(i, i2, str);
    }

    public final String getUrl() {
        return this.f1334c;
    }

    public final void setUrl(String str) {
        this.f1334c = str;
    }

    @Override // com.baidu.trace.api.bos.BosObjectResponse
    public final String toString() {
        return "BosGeneratePresignedUrlResponse [tag=" + this.tag + ", status=" + this.status + ", message=" + this.message + ", objectType=" + this.f1340a + ", objectKey=" + this.f1341b + ", url=" + this.f1334c + "]";
    }
}
