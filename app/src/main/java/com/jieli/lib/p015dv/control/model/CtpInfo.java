package com.jieli.lib.p015dv.control.model;

/* JADX INFO: loaded from: classes.dex */
public class CtpInfo {

    /* JADX INFO: renamed from: a */
    private byte[] f2124a;

    /* JADX INFO: renamed from: b */
    private byte[] f2125b;

    public byte[] getTopic() {
        return this.f2124a;
    }

    public void setTopic(byte[] bArr) {
        this.f2124a = bArr;
    }

    public byte[] getPayload() {
        return this.f2125b;
    }

    public void setPayload(byte[] bArr) {
        this.f2125b = bArr;
    }

    public String toString() {
        return "[topic:" + new String(this.f2124a) + ", payload:" + new String(this.f2125b) + "]";
    }
}
