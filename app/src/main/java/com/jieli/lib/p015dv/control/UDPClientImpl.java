package com.jieli.lib.p015dv.control;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.jieli.lib.p015dv.control.connect.listener.OnConnectStateListener;
import com.jieli.lib.p015dv.control.connect.response.SendResponse;
import com.jieli.lib.p015dv.control.json.bean.CmdInfo;
import com.jieli.lib.p015dv.control.json.bean.NotifyInfo;
import com.jieli.lib.p015dv.control.receiver.listener.OnNotifyListener;
import com.jieli.lib.p015dv.control.receiver.task.UDPCmdReceiver;
import com.jieli.lib.p015dv.control.utils.ClientContext;
import com.jieli.lib.p015dv.control.utils.Constants;
import com.jieli.lib.p015dv.control.utils.Dlog;
import com.jieli.lib.p015dv.control.utils.proxy.ProxyInterceptor;
import com.jieli.lib.p015dv.control.utils.proxy.ProxyUtils;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class UDPClientImpl implements Handler.Callback, IClient, ProxyInterceptor {
    public static final int MSG_RECEIVED_DATA = 101;

    /* JADX INFO: renamed from: a */
    private static String f2080a = UDPClientImpl.class.getSimpleName();

    /* JADX INFO: renamed from: b */
    private static IClient f2081b = null;

    /* JADX INFO: renamed from: c */
    private HandlerThread f2082c;

    /* JADX INFO: renamed from: d */
    private Handler f2083d;

    /* JADX INFO: renamed from: e */
    private DatagramSocket f2084e;

    /* JADX INFO: renamed from: f */
    private String f2085f;

    /* JADX INFO: renamed from: h */
    private UDPCmdReceiver f2087h;

    /* JADX INFO: renamed from: i */
    private CopyOnWriteArrayList<OnNotifyListener> f2088i;

    /* JADX INFO: renamed from: j */
    private CopyOnWriteArrayList<OnConnectStateListener> f2089j;

    /* JADX INFO: renamed from: g */
    private int f2086g = -1;

    /* JADX INFO: renamed from: k */
    private int f2090k = -1;

    @Override // com.jieli.lib.p015dv.control.utils.proxy.ProxyInterceptor
    public boolean onIntercept(Object obj, Method method, Object[] objArr) {
        return false;
    }

    private UDPClientImpl(Context context) {
        HandlerThread handlerThread = new HandlerThread(f2080a);
        this.f2082c = handlerThread;
        handlerThread.start();
        this.f2083d = new Handler(this.f2082c.getLooper(), this);
        this.f2089j = new CopyOnWriteArrayList<>();
        this.f2088i = new CopyOnWriteArrayList<>();
    }

    public static IClient getInstance(Context context) {
        if (f2081b == null) {
            synchronized (UDPClientImpl.class) {
                if (f2081b == null) {
                    UDPClientImpl uDPClientImpl = new UDPClientImpl(context);
                    f2081b = (IClient) ProxyUtils.getProxy(uDPClientImpl, IClient.class, uDPClientImpl);
                }
            }
        }
        return f2081b;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 100:
                m1318a(message);
                break;
            case 101:
                m1320a((NotifyInfo) message.obj);
                break;
            case 102:
                m1316a();
                break;
            case 103:
                m1322b();
                break;
        }
        return false;
    }

    @Override // com.jieli.lib.p015dv.control.IClient
    public void create(final String str, final int i) {
        Dlog.m1383e(f2080a, "Create host " + str + ", port " + i);
        UDPCmdReceiver uDPCmdReceiver = this.f2087h;
        if (uDPCmdReceiver != null) {
            uDPCmdReceiver.stopRunning();
            this.f2087h = null;
        }
        new Thread(new Runnable() { // from class: com.jieli.lib.dv.control.UDPClientImpl.1
            @Override // java.lang.Runnable
            public void run() {
                UDPClientImpl.this.f2085f = str;
                UDPClientImpl.this.f2086g = i;
                if (UDPClientImpl.this.f2084e != null) {
                    UDPClientImpl.this.f2084e.close();
                    UDPClientImpl.this.f2084e = null;
                }
                try {
                    UDPClientImpl.this.f2084e = new DatagramSocket(UDPClientImpl.this.f2086g);
                    UDPClientImpl.this.f2084e.setReuseAddress(true);
                    UDPClientImpl.this.m1317a(0);
                    if (UDPClientImpl.this.f2087h == null || UDPClientImpl.this.f2087h.isInterrupted()) {
                        UDPClientImpl.this.f2087h = new UDPCmdReceiver(UDPClientImpl.this.f2084e, UDPClientImpl.this.f2083d);
                        UDPClientImpl.this.f2087h.start();
                    }
                } catch (SocketException e) {
                    UDPClientImpl.this.m1317a(4);
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override // com.jieli.lib.p015dv.control.IClient
    public boolean isConnected() {
        return getState() == 0;
    }

    @Override // com.jieli.lib.p015dv.control.IClient
    public int getState() {
        return this.f2090k;
    }

    @Override // com.jieli.lib.p015dv.control.IClient
    public void send(CmdInfo cmdInfo, SendResponse sendResponse) {
        this.f2083d.removeMessages(100);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.EXTRA_CMD_INFO, cmdInfo);
        Message messageObtain = Message.obtain();
        messageObtain.setData(bundle);
        messageObtain.obj = sendResponse;
        messageObtain.what = 100;
        this.f2083d.sendMessage(messageObtain);
    }

    @Override // com.jieli.lib.p015dv.control.IClient
    public void close() {
        Handler handler = this.f2083d;
        if (handler != null) {
            handler.removeMessages(102);
            this.f2083d.sendEmptyMessage(102);
        }
    }

    @Override // com.jieli.lib.p015dv.control.IClient
    public void registerConnectStateListener(OnConnectStateListener onConnectStateListener) {
        if (onConnectStateListener == null || this.f2089j.contains(onConnectStateListener)) {
            return;
        }
        this.f2089j.add(onConnectStateListener);
    }

    @Override // com.jieli.lib.p015dv.control.IClient
    public void unregisterConnectStateListener(OnConnectStateListener onConnectStateListener) {
        CopyOnWriteArrayList<OnConnectStateListener> copyOnWriteArrayList = this.f2089j;
        if (copyOnWriteArrayList == null || onConnectStateListener == null) {
            return;
        }
        copyOnWriteArrayList.remove(onConnectStateListener);
    }

    @Override // com.jieli.lib.p015dv.control.IClient
    public void registerNotifyListener(OnNotifyListener onNotifyListener) {
        CopyOnWriteArrayList<OnNotifyListener> copyOnWriteArrayList = this.f2088i;
        if (copyOnWriteArrayList == null || onNotifyListener == null || copyOnWriteArrayList.contains(onNotifyListener)) {
            return;
        }
        this.f2088i.add(onNotifyListener);
    }

    @Override // com.jieli.lib.p015dv.control.IClient
    public void unregisterNotifyListener(OnNotifyListener onNotifyListener) {
        CopyOnWriteArrayList<OnNotifyListener> copyOnWriteArrayList;
        if (onNotifyListener == null || (copyOnWriteArrayList = this.f2088i) == null) {
            return;
        }
        copyOnWriteArrayList.remove(onNotifyListener);
    }

    @Override // com.jieli.lib.p015dv.control.IClient
    public void release() {
        Handler handler = this.f2083d;
        if (handler != null) {
            handler.removeMessages(103);
            this.f2083d.sendEmptyMessage(103);
        }
    }

    @Override // com.jieli.lib.p015dv.control.IClient
    public String getAddress() {
        return this.f2085f;
    }

    /* JADX INFO: renamed from: a */
    private synchronized void m1318a(Message message) {
        CmdInfo cmdInfo = (CmdInfo) message.getData().getParcelable(Constants.EXTRA_CMD_INFO);
        if (cmdInfo == null) {
            throw new NullPointerException("cmdInfo is null");
        }
        SendResponse sendResponse = (SendResponse) message.obj;
        byte[] bArr = ClientImpl.getPackage(cmdInfo);
        if (bArr == null) {
            throw new NullPointerException("Data is null");
        }
        if (this.f2084e == null) {
            m1319a(sendResponse, -1);
        }
        InetAddress byName = null;
        try {
            byName = InetAddress.getByName(this.f2085f);
        } catch (UnknownHostException e) {
            m1319a(sendResponse, -1);
            e.printStackTrace();
        }
        if (byName != null && this.f2086g > 0) {
            DatagramPacket datagramPacket = new DatagramPacket(bArr, bArr.length, byName, this.f2086g);
            try {
                if (this.f2084e != null) {
                    this.f2084e.send(datagramPacket);
                    m1319a(sendResponse, 1);
                } else {
                    m1319a(sendResponse, -1);
                }
            } catch (IOException e2) {
                m1319a(sendResponse, -1);
                e2.printStackTrace();
            }
        } else {
            if (byName == null) {
                throw new IllegalArgumentException("No device IP is specified!");
            }
            if (this.f2086g < 0) {
                throw new IllegalArgumentException("No device's port is specified:" + this.f2086g);
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private void m1319a(final SendResponse sendResponse, final int i) {
        ClientContext.post(new Runnable() { // from class: com.jieli.lib.dv.control.UDPClientImpl.2
            @Override // java.lang.Runnable
            public void run() {
                SendResponse sendResponse2 = sendResponse;
                if (sendResponse2 != null) {
                    sendResponse2.onResponse(Integer.valueOf(i));
                }
            }
        });
    }

    /* JADX INFO: renamed from: a */
    private void m1316a() {
        DatagramSocket datagramSocket = this.f2084e;
        if (datagramSocket != null) {
            datagramSocket.close();
            this.f2084e = null;
        }
        m1317a(1);
        UDPCmdReceiver uDPCmdReceiver = this.f2087h;
        if (uDPCmdReceiver != null) {
            uDPCmdReceiver.stopRunning();
            this.f2087h = null;
        }
    }

    /* JADX INFO: renamed from: b */
    private void m1322b() {
        m1316a();
        CopyOnWriteArrayList<OnNotifyListener> copyOnWriteArrayList = this.f2088i;
        if (copyOnWriteArrayList != null) {
            copyOnWriteArrayList.clear();
            this.f2088i = null;
        }
        CopyOnWriteArrayList<OnConnectStateListener> copyOnWriteArrayList2 = this.f2089j;
        if (copyOnWriteArrayList2 != null) {
            copyOnWriteArrayList2.clear();
            this.f2089j = null;
        }
        if (this.f2082c != null) {
            if (Build.VERSION.SDK_INT >= 18) {
                this.f2082c.quitSafely();
            } else {
                this.f2082c.quit();
            }
            this.f2082c = null;
        }
        Handler handler = this.f2083d;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        this.f2083d = null;
    }

    /* JADX INFO: renamed from: a */
    private void m1320a(final NotifyInfo notifyInfo) {
        for (final OnNotifyListener onNotifyListener : this.f2088i) {
            ClientContext.post(new Runnable() { // from class: com.jieli.lib.dv.control.UDPClientImpl.3
                @Override // java.lang.Runnable
                public void run() {
                    onNotifyListener.onNotify(notifyInfo);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public void m1317a(final int i) {
        this.f2090k = i;
        CopyOnWriteArrayList<OnConnectStateListener> copyOnWriteArrayList = this.f2089j;
        if (copyOnWriteArrayList == null) {
            Dlog.m1386w(f2080a, "OnConnectStateListener is null");
            return;
        }
        for (final OnConnectStateListener onConnectStateListener : copyOnWriteArrayList) {
            ClientContext.post(new Runnable() { // from class: com.jieli.lib.dv.control.UDPClientImpl.4
                @Override // java.lang.Runnable
                public void run() {
                    onConnectStateListener.onStateChanged(Integer.valueOf(i));
                }
            });
        }
    }
}
