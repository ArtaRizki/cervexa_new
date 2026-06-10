package com.tencent.p023mm.opensdk.modelmsg;
import android.graphics.Bitmap;
public class WXImageObject implements WXMediaMessage.IMediaObject {
    public WXImageObject(Bitmap bitmap) {}
    public WXImageObject() {}
    @Override public int type() { return 2; }
    @Override public boolean checkArgs() { return true; }
    @Override public void serialize(android.os.Bundle bundle) {}
    @Override public void unserialize(android.os.Bundle bundle) {}
}
