package org.apache.commons.net;

import java.util.EventListener;

/* JADX INFO: loaded from: classes2.dex */
public interface ProtocolCommandListener extends EventListener {
    void protocolCommandSent(ProtocolCommandEvent protocolCommandEvent);

    void protocolReplyReceived(ProtocolCommandEvent protocolCommandEvent);
}
