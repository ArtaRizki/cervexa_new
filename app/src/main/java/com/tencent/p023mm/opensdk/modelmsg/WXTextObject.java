package com.tencent.p023mm.opensdk.modelmsg;
public class WXTextObject implements WXMediaMessage.IMediaObject {
    public WXTextObject(String text) {}
    public WXTextObject() {}
    public String text;
    @Override public int type() { return 1; }
    @Override public boolean checkArgs() { return true; }
    @Override public void serialize(android.os.Bundle bundle) {}
    @Override public void unserialize(android.os.Bundle bundle) {}
}
