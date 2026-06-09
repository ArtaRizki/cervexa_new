package com.jieli.stream.p016dv.running2.data;

/* JADX INFO: loaded from: classes.dex */
public interface OnRecordStateListener {
    void onCompletion(String str);

    void onError(String str);

    void onPrepared();

    void onStop();
}
