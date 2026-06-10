package com.tencent.p023mm.opensdk.modelmsg;
import com.tencent.p023mm.opensdk.modelbase.BaseReq;
public class SendMessageToWX {
    public static class Req extends BaseReq {
        public WXMediaMessage message;
        public int scene;
    }
}
