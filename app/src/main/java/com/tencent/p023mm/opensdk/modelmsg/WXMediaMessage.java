package com.tencent.p023mm.opensdk.modelmsg;
public class WXMediaMessage {
    public IMediaObject mediaObject;
    public String title;
    public String description;
    public byte[] thumbData;
    public interface IMediaObject {
        int type();
        boolean checkArgs();
        void serialize(android.os.Bundle bundle);
        void unserialize(android.os.Bundle bundle);
    }
}
