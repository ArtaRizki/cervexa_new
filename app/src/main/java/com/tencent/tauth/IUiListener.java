package com.tencent.tauth;
public interface IUiListener {
    void onComplete(Object response);
    void onError(UiError e);
    void onCancel();
}
