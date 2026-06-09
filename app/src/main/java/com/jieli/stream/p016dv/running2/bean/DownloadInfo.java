package com.jieli.stream.p016dv.running2.bean;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class DownloadInfo implements Serializable {
    private int duration;
    private int offset;
    private String path;

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int i) {
        this.duration = i;
    }

    public int getOffset() {
        return this.offset;
    }

    public void setOffset(int i) {
        this.offset = i;
    }
}
