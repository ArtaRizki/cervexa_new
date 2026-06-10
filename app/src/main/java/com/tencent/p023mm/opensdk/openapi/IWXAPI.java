package com.tencent.p023mm.opensdk.openapi;
import android.content.Intent;
import com.tencent.p023mm.opensdk.modelbase.BaseReq;
import com.tencent.p023mm.opensdk.modelbase.BaseResp;
public interface IWXAPI {
    boolean handleIntent(Intent intent, IWXAPIEventHandler handler);
    boolean registerApp(String appId);
    boolean sendReq(BaseReq req);
}
