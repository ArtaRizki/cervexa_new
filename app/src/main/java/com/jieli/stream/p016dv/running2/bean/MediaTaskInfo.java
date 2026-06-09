package com.jieli.stream.p016dv.running2.bean;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class MediaTaskInfo implements Serializable {
    private FileInfo info;

    /* JADX INFO: renamed from: op */
    private int f2237op;

    public FileInfo getInfo() {
        return this.info;
    }

    public MediaTaskInfo setInfo(FileInfo fileInfo) {
        this.info = fileInfo;
        return this;
    }

    public int getOp() {
        return this.f2237op;
    }

    public MediaTaskInfo setOp(int i) {
        this.f2237op = i;
        return this;
    }
}
