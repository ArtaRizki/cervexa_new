package com.jieli.lib.p015dv.control.projection;

import android.text.TextUtils;
import com.jieli.lib.p015dv.control.projection.protocol.TcpStreaming;
import com.jieli.lib.p015dv.control.projection.protocol.UdpStreaming;

/* JADX INFO: loaded from: classes.dex */
public final class StreamingPush implements IPushStream {

    /* JADX INFO: renamed from: a */
    private IPushStream f2155a;

    public enum Protocol {
        TCP,
        UDP
    }

    public StreamingPush() {
        this(Protocol.UDP);
    }

    public StreamingPush(Protocol protocol) {
        if (protocol == Protocol.UDP) {
            this.f2155a = new UdpStreaming();
        } else {
            this.f2155a = new TcpStreaming();
        }
    }

    @Override // com.jieli.lib.p015dv.control.projection.IPushStream
    public void create(String str) {
        this.f2155a.create(str);
    }

    @Override // com.jieli.lib.p015dv.control.projection.IPushStream
    public void create(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            throw new NullPointerException("Destination IP is null");
        }
        this.f2155a.create(str, i);
    }

    @Override // com.jieli.lib.p015dv.control.projection.IPushStream
    public void close() {
        this.f2155a.close();
    }

    @Override // com.jieli.lib.p015dv.control.projection.IPushStream
    public boolean send(int i, byte[] bArr) {
        return this.f2155a.send(i, bArr);
    }

    @Override // com.jieli.lib.p015dv.control.projection.IPushStream
    public void setOnSendStateListener(OnSendStateListener onSendStateListener) {
        this.f2155a.setOnSendStateListener(onSendStateListener);
    }
}
