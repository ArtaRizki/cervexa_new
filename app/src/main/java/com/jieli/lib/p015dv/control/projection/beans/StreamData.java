package com.jieli.lib.p015dv.control.projection.beans;

import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public class StreamData {

    /* JADX INFO: renamed from: a */
    private int f2170a;

    /* JADX INFO: renamed from: b */
    private int f2171b;

    /* JADX INFO: renamed from: c */
    private int f2172c;

    /* JADX INFO: renamed from: d */
    private int f2173d;

    /* JADX INFO: renamed from: e */
    private int f2174e;

    /* JADX INFO: renamed from: f */
    private int f2175f;

    /* JADX INFO: renamed from: g */
    private int f2176g;

    /* JADX INFO: renamed from: h */
    private byte[] f2177h;

    public int getType() {
        return this.f2170a;
    }

    public void setType(int i) {
        this.f2170a = i;
    }

    public int getSave() {
        return this.f2171b;
    }

    public void setSave(int i) {
        this.f2171b = i;
    }

    public int getPayloadLen() {
        return this.f2172c;
    }

    public void setPayloadLen(int i) {
        this.f2172c = i;
    }

    public int getSeq() {
        return this.f2173d;
    }

    public void setSeq(int i) {
        this.f2173d = i;
    }

    public int getFrameSize() {
        return this.f2174e;
    }

    public void setFrameSize(int i) {
        this.f2174e = i;
    }

    public int getOffset() {
        return this.f2175f;
    }

    public void setOffset(int i) {
        this.f2175f = i;
    }

    public int getDateFlag() {
        return this.f2176g;
    }

    public void setDateFlag(int i) {
        this.f2176g = i;
    }

    public byte[] getPayload() {
        return this.f2177h;
    }

    public void setPayload(byte[] bArr) {
        this.f2177h = bArr;
    }

    public String toString() {
        String str = "{ \"type\": " + this.f2170a + ", \n\"save\": " + this.f2171b + ", \n\"payloadLen\": " + this.f2172c + ", \n\"frameSize\": " + this.f2174e + ", \n\"offset\": " + this.f2175f + ", \n\"dateFlag\": " + this.f2176g;
        if (this.f2177h != null) {
            String str2 = new String(this.f2177h);
            if (!TextUtils.isEmpty(str2)) {
                str = str + ", \n\"payload\": \"" + str2 + "\"";
            }
        }
        return str + "}";
    }
}
