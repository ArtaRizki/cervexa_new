package com.tencent.p023mm.opensdk.openapi;
import android.content.Context;
public class WXAPIFactory {
    public static IWXAPI createWXAPI(Context context, String appId, boolean checkSignature) {
        return new IWXAPI() {
            @Override public boolean handleIntent(android.content.Intent intent, IWXAPIEventHandler handler) { return false; }
            @Override public boolean registerApp(String appId) { return false; }
            @Override public boolean sendReq(com.tencent.p023mm.opensdk.modelbase.BaseReq req) { return false; }
        };
    }
}
