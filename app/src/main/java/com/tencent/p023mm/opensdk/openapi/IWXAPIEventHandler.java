package com.tencent.p023mm.opensdk.openapi;

import com.tencent.p023mm.opensdk.modelbase.BaseReq;
import com.tencent.p023mm.opensdk.modelbase.BaseResp;

/* JADX INFO: loaded from: classes2.dex */
public interface IWXAPIEventHandler {
    void onReq(BaseReq baseReq);

    void onResp(BaseResp baseResp);
}
