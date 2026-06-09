package com.tencent.tauth;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class UiError {
    public int errorCode;
    public String errorDetail;
    public String errorMessage;

    public UiError(int i, String str, String str2) {
        this.errorMessage = str;
        this.errorCode = i;
        this.errorDetail = str2;
    }
}
