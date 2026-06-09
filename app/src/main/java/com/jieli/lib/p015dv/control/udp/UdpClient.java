package com.jieli.lib.p015dv.control.udp;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import com.jieli.lib.p015dv.control.ClientImpl;
import com.jieli.lib.p015dv.control.connect.listener.OnConnectStateListener;
import com.jieli.lib.p015dv.control.connect.response.SendResponse;
import com.jieli.lib.p015dv.control.json.bean.CmdInfo;
import com.jieli.lib.p015dv.control.receiver.listener.OnNotifyListener;
import com.jieli.lib.p015dv.control.utils.ClientContext;
import com.jieli.lib.p015dv.control.utils.Constants;
import com.jieli.lib.p015dv.control.utils.Dlog;
import com.jieli.lib.p015dv.control.utils.ListenerHelper;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/* JADX INFO: loaded from: classes.dex */
public class UdpClient implements Handler.Callback {
    public static final int UDP_CLIENT_PORT = 2228;

    /* JADX INFO: renamed from: a */
    private static String f2199a = "UdpClient";

    /* JADX INFO: renamed from: b */
    private static UdpClient f2200b;

    /* JADX INFO: renamed from: c */
    private HandlerThread f2201c;

    /* JADX INFO: renamed from: d */
    private Handler f2202d;

    /* JADX INFO: renamed from: e */
    private DatagramSocket f2203e;

    /* JADX INFO: renamed from: f */
    private InetAddress f2204f;

    /* JADX INFO: renamed from: g */
    private UdpSocketReceiver f2205g;

    /* JADX INFO: renamed from: h */
    private String f2206h;

    /* JADX INFO: renamed from: i */
    private int f2207i;

    public static UdpClient getInstance() {
        if (f2200b == null) {
            synchronized (UdpClient.class) {
                if (f2200b == null) {
                    f2200b = new UdpClient();
                }
            }
        }
        return f2200b;
    }

    public String getServerAddress() {
        return this.f2206h;
    }

    public int getServerPort() {
        return this.f2207i;
    }

    public void createClient(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            Dlog.m1386w(f2199a, "address is null.");
            return;
        }
        Dlog.m1384i(f2199a, "create udp channel.");
        this.f2207i = i;
        this.f2206h = str;
        try {
            this.f2203e = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        try {
            this.f2204f = InetAddress.getByName(this.f2206h);
        } catch (UnknownHostException e2) {
            e2.printStackTrace();
        }
        if (this.f2203e == null || this.f2204f == null) {
            return;
        }
        Dlog.m1386w(f2199a, "init udp channel ok.");
        UdpSocketReceiver udpSocketReceiver = this.f2205g;
        if (udpSocketReceiver == null || !udpSocketReceiver.isReceiver()) {
            this.f2205g = new UdpSocketReceiver(this.f2203e);
            Dlog.m1386w(f2199a, "start Receiver thread ok.");
            this.f2205g.start();
        }
        if (this.f2201c == null) {
            HandlerThread handlerThread = new HandlerThread(f2199a);
            this.f2201c = handlerThread;
            handlerThread.start();
            this.f2202d = new Handler(this.f2201c.getLooper(), this);
        }
    }

    public void registerConnectStateListener(OnConnectStateListener onConnectStateListener) {
        ListenerHelper.getInstance().addConnectStateListener(onConnectStateListener);
    }

    public void unregisterConnectStateListener(OnConnectStateListener onConnectStateListener) {
        ListenerHelper.getInstance().removeConnectStateListener(onConnectStateListener);
    }

    public void registerNotifyListener(OnNotifyListener onNotifyListener) {
        ListenerHelper.getInstance().addNotifyListener(onNotifyListener);
    }

    public void unregisterNotifyListener(OnNotifyListener onNotifyListener) {
        ListenerHelper.getInstance().removeNotifyListener(onNotifyListener);
    }

    public void send(CmdInfo cmdInfo, SendResponse sendResponse) {
        Handler handler = this.f2202d;
        if (handler == null) {
            m1377a(sendResponse, -1);
            return;
        }
        handler.removeMessages(516);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.EXTRA_CMD_INFO, cmdInfo);
        Message messageObtain = Message.obtain();
        messageObtain.setData(bundle);
        messageObtain.obj = sendResponse;
        messageObtain.what = 516;
        this.f2202d.sendMessage(messageObtain);
    }

    /* JADX INFO: renamed from: a */
    private synchronized void m1378a(CmdInfo cmdInfo, SendResponse sendResponse) {
        if (cmdInfo != null) {
            byte[] bArr = ClientImpl.getPackage(cmdInfo);
            if (bArr == null) {
                m1377a(sendResponse, -1);
                return;
            }
            if (this.f2203e != null) {
                try {
                    this.f2203e.send(new DatagramPacket(bArr, bArr.length, this.f2204f, this.f2207i));
                    m1377a(sendResponse, 1);
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            m1377a(sendResponse, -1);
        } else {
            m1377a(sendResponse, -1);
        }
    }

    /* JADX INFO: renamed from: a */
    private void m1377a(final SendResponse sendResponse, final int i) {
        ClientContext.post(new Runnable() { // from class: com.jieli.lib.dv.control.udp.UdpClient.1
            @Override // java.lang.Runnable
            public void run() {
                SendResponse sendResponse2 = sendResponse;
                if (sendResponse2 != null) {
                    sendResponse2.onResponse(Integer.valueOf(i));
                }
            }
        });
    }

    public void closeClient(boolean z) {
        f2200b = null;
        UdpSocketReceiver udpSocketReceiver = this.f2205g;
        if (udpSocketReceiver != null) {
            if (udpSocketReceiver.isReceiver()) {
                this.f2205g.stopReceiver();
            }
            this.f2205g = null;
        }
        DatagramSocket datagramSocket = this.f2203e;
        if (datagramSocket != null) {
            datagramSocket.close();
            this.f2203e = null;
        }
        if (this.f2201c != null) {
            if (Build.VERSION.SDK_INT >= 18) {
                this.f2201c.quitSafely();
            } else {
                this.f2201c.quit();
            }
            this.f2201c = null;
        }
        Handler handler = this.f2202d;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        this.f2202d = null;
        if (z) {
            ListenerHelper.getInstance().release();
        }
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message == null || message.what != 516) {
            return false;
        }
        SendResponse sendResponse = (SendResponse) message.obj;
        Bundle data = message.getData();
        if (data != null) {
            m1378a((CmdInfo) data.getParcelable(Constants.EXTRA_CMD_INFO), sendResponse);
            return false;
        }
        m1377a(sendResponse, -1);
        return false;
    }
}
