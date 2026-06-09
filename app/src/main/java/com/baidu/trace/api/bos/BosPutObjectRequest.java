package com.baidu.trace.api.bos;

import com.jieli.stream.p016dv.running2.util.IConstant;
import java.io.File;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class BosPutObjectRequest extends BosObjectRequest {

    /* JADX INFO: renamed from: a */
    private File f1343a;

    /* JADX INFO: renamed from: b */
    private InputStream f1344b;

    /* JADX INFO: renamed from: c */
    private byte[] f1345c;

    /* JADX INFO: renamed from: d */
    private String f1346d;

    /* JADX INFO: renamed from: e */
    private long f1347e;

    private BosPutObjectRequest(int i, long j, String str, BosObjectType bosObjectType, File file, InputStream inputStream, byte[] bArr, String str2) {
        super(i, j, str, bosObjectType);
        this.f1343a = null;
        this.f1344b = null;
        this.f1345c = null;
        this.f1346d = null;
        this.f1347e = 0L;
        this.f1343a = file;
        this.f1344b = inputStream;
        this.f1345c = bArr;
        this.f1346d = str2;
    }

    public static BosPutObjectRequest buildByteArrayRequest(int i, long j, String str, BosObjectType bosObjectType, byte[] bArr) {
        return new BosPutObjectRequest(i, j, str, bosObjectType, null, null, bArr, null);
    }

    public static BosPutObjectRequest buildFileRequest(int i, long j, String str, BosObjectType bosObjectType, File file) {
        return new BosPutObjectRequest(i, j, str, bosObjectType, file, null, null, null);
    }

    public static BosPutObjectRequest buildStreamReqeust(int i, long j, String str, BosObjectType bosObjectType, InputStream inputStream) {
        return new BosPutObjectRequest(i, j, str, bosObjectType, null, inputStream, null, null);
    }

    public static BosPutObjectRequest buildStringRequest(int i, long j, String str, BosObjectType bosObjectType, String str2) {
        return new BosPutObjectRequest(i, j, str, bosObjectType, null, null, null, str2);
    }

    public final byte[] getByteArray() {
        return this.f1345c;
    }

    public final File getFile() {
        return this.f1343a;
    }

    public final long getObjectSize() {
        return this.f1347e;
    }

    public final InputStream getStreamData() {
        return this.f1344b;
    }

    public final String getStringData() {
        return this.f1346d;
    }

    public final void setByteArray(byte[] bArr) {
        this.f1345c = bArr;
    }

    public final void setFile(File file) {
        this.f1343a = file;
    }

    public final void setObjectSize(long j) {
        this.f1347e = j;
    }

    public final void setStreamData(InputStream inputStream) {
        this.f1344b = inputStream;
    }

    public final void setStringData(String str) {
        this.f1346d = str;
    }

    @Override // com.baidu.trace.api.bos.BosObjectRequest
    public final String toString() {
        StringBuffer stringBuffer = new StringBuffer("BosPutObjectRequest{");
        stringBuffer.append("file=");
        stringBuffer.append(this.f1343a);
        stringBuffer.append(", streamData=");
        stringBuffer.append(this.f1344b);
        stringBuffer.append(", byteArray=");
        if (this.f1345c == null) {
            stringBuffer.append(IConstant.DEFAULT_PATH);
        } else {
            stringBuffer.append('[');
            int i = 0;
            while (i < this.f1345c.length) {
                stringBuffer.append(i == 0 ? "" : ", ");
                stringBuffer.append((int) this.f1345c[i]);
                i++;
            }
            stringBuffer.append(']');
        }
        stringBuffer.append(", stringData='");
        stringBuffer.append(this.f1346d);
        stringBuffer.append('\'');
        stringBuffer.append(", objectSize=");
        stringBuffer.append(this.f1347e);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
