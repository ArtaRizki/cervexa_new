package com.bumptech.glide.request;

/* JADX INFO: loaded from: classes.dex */
public interface RequestCoordinator {
    boolean canNotifyStatusChanged(Request request);

    boolean canSetImage(Request request);

    boolean isAnyResourceSet();

    void onRequestSuccess(Request request);
}
