package com.baidu.trace.model;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public interface OnCustomAttributeListener {
    Map<String, String> onTrackAttributeCallback();

    Map<String, String> onTrackAttributeCallback(long j);
}
