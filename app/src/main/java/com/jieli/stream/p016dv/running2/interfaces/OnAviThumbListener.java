package com.jieli.stream.p016dv.running2.interfaces;

import com.jieli.media.codec.bean.MediaMeta;

/* JADX INFO: loaded from: classes.dex */
public interface OnAviThumbListener {
    void onCompleted(byte[] bArr, MediaMeta mediaMeta);

    void onError(String str);
}
