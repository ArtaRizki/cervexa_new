package com.jieli.stream.p016dv.running2.interfaces;

/* JADX INFO: loaded from: classes.dex */
public interface OnDownloadListener {
    void onCompletion();

    void onError(int i, String str);

    void onProgress(int i);

    void onStartLoad();
}
