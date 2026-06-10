package com.tencent.p023mm.opensdk.openapi;
import com.tencent.p023mm.opensdk.modelbase.BaseReq;
import com.tencent.p023mm.opensdk.modelbase.BaseResp;
public interface IWXAPIEventHandler {
    void onReq(BaseReq req);
    void onResp(BaseResp resp);
}
