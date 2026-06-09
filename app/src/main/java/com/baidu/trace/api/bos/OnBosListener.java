package com.baidu.trace.api.bos;

/* JADX INFO: loaded from: classes.dex */
public interface OnBosListener {
    void onGeneratePresignedUrlCallback(BosGeneratePresignedUrlResponse bosGeneratePresignedUrlResponse);

    void onGetObjectCallback(BosGetObjectResponse bosGetObjectResponse);

    void onPutObjectCallback(BosPutObjectResponse bosPutObjectResponse);
}
