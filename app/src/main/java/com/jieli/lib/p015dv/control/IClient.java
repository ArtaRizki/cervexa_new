package com.jieli.lib.p015dv.control;

import com.jieli.lib.p015dv.control.connect.listener.OnConnectStateListener;
import com.jieli.lib.p015dv.control.connect.response.SendResponse;
import com.jieli.lib.p015dv.control.json.bean.CmdInfo;
import com.jieli.lib.p015dv.control.receiver.listener.OnNotifyListener;

/* JADX INFO: loaded from: classes.dex */
public interface IClient {
    void close();

    void create(String str, int i);

    String getAddress();

    int getState();

    boolean isConnected();

    void registerConnectStateListener(OnConnectStateListener onConnectStateListener);

    void registerNotifyListener(OnNotifyListener onNotifyListener);

    void release();

    void send(CmdInfo cmdInfo, SendResponse sendResponse);

    void unregisterConnectStateListener(OnConnectStateListener onConnectStateListener);

    void unregisterNotifyListener(OnNotifyListener onNotifyListener);
}
