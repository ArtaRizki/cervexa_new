package com.tencent.p023mm.opensdk.modelmsg;
public class WXWebpageObject implements WXMediaMessage.IMediaObject {
    public WXWebpageObject(String url) { this.webpageUrl = url; }
    public WXWebpageObject() {}
    public String webpageUrl;
    @Override public int type() { return 5; }
    @Override public boolean checkArgs() { return true; }
    @Override public void serialize(android.os.Bundle bundle) {}
    @Override public void unserialize(android.os.Bundle bundle) {}
}
