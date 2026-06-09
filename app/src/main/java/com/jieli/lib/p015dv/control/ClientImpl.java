package com.jieli.lib.p015dv.control;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.jieli.lib.p015dv.control.connect.listener.OnConnectStateListener;
import com.jieli.lib.p015dv.control.connect.response.SendResponse;
import com.jieli.lib.p015dv.control.json.bean.CmdInfo;
import com.jieli.lib.p015dv.control.json.bean.NotifyInfo;
import com.jieli.lib.p015dv.control.json.bean.SettingCmd;
import com.jieli.lib.p015dv.control.receiver.listener.OnNotifyListener;
import com.jieli.lib.p015dv.control.receiver.task.CommandReceiver;
import com.jieli.lib.p015dv.control.utils.ClientContext;
import com.jieli.lib.p015dv.control.utils.Constants;
import com.jieli.lib.p015dv.control.utils.Dlog;
import com.jieli.lib.p015dv.control.utils.Topic;
import com.jieli.lib.p015dv.control.utils.proxy.ProxyInterceptor;
import com.jieli.lib.p015dv.control.utils.proxy.ProxyUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class ClientImpl implements Handler.Callback, IClient, ProxyInterceptor {
    public static final int MSG_RECEIVED_CTP_DATA = 261;
    public static final int MSG_SOCKET_STATE = 257;

    /* JADX INFO: renamed from: a */
    static String f2053a = ClientImpl.class.getSimpleName();

    /* JADX INFO: renamed from: b */
    private static IClient f2054b = null;

    /* JADX INFO: renamed from: c */
    private Socket f2055c;

    /* JADX INFO: renamed from: g */
    private HandlerThread f2059g;

    /* JADX INFO: renamed from: h */
    private Handler f2060h;

    /* JADX INFO: renamed from: k */
    private int f2063k;

    /* JADX INFO: renamed from: l */
    private String f2064l;

    /* JADX INFO: renamed from: d */
    private volatile OutputStream f2056d = null;

    /* JADX INFO: renamed from: i */
    private CommandReceiver f2061i = null;

    /* JADX INFO: renamed from: j */
    private int f2062j = -1;

    /* JADX INFO: renamed from: e */
    private HashSet<OnConnectStateListener> f2057e = new HashSet<>();

    /* JADX INFO: renamed from: f */
    private CopyOnWriteArrayList<OnNotifyListener> f2058f = new CopyOnWriteArrayList<>();

    @Override // com.jieli.lib.p015dv.control.utils.proxy.ProxyInterceptor
    public boolean onIntercept(Object obj, Method method, Object[] objArr) {
        return false;
    }

    private ClientImpl(Context context) {
        HandlerThread handlerThread = new HandlerThread(f2053a);
        this.f2059g = handlerThread;
        handlerThread.start();
        this.f2060h = new Handler(this.f2059g.getLooper(), this);
    }

    public static IClient getInstance(Context context) {
        if (f2054b == null) {
            synchronized (ClientImpl.class) {
                if (f2054b == null) {
                    ClientImpl clientImpl = new ClientImpl(context);
                    f2054b = (IClient) ProxyUtils.getProxy(clientImpl, IClient.class, clientImpl);
                }
            }
        }
        return f2054b;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 257) {
            m1307b(message.arg1);
            return false;
        }
        if (i == 263) {
            m1308c();
            return false;
        }
        if (i == 260) {
            m1299a(message);
            return false;
        }
        if (i != 261) {
            return false;
        }
        m1302a((NotifyInfo) message.obj);
        return false;
    }

    @Override // com.jieli.lib.p015dv.control.IClient
    public void create(String str, int i) {
        m1303a(str);
        m1304a(str, i);
    }

    @Override // com.jieli.lib.p015dv.control.IClient
    public boolean isConnected() {
        return getState() == 0;
    }

    @Override // com.jieli.lib.p015dv.control.IClient
    public int getState() {
        return this.f2062j;
    }

    /* JADX INFO: renamed from: a */
    private void m1298a(int i) {
        this.f2062j = i;
    }

    @Override // com.jieli.lib.p015dv.control.IClient
    public void send(CmdInfo cmdInfo, SendResponse sendResponse) {
        if (getState() != 0) {
            if (sendResponse != null) {
                sendResponse.onResponse(-1);
            }
            Dlog.m1386w(f2053a, "Not connected:" + getState());
            return;
        }
        Handler handler = this.f2060h;
        if (handler != null) {
            handler.removeMessages(260);
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.EXTRA_CMD_INFO, cmdInfo);
        Message messageObtain = Message.obtain();
        messageObtain.setData(bundle);
        messageObtain.obj = sendResponse;
        messageObtain.what = 260;
        Handler handler2 = this.f2060h;
        if (handler2 != null) {
            handler2.sendMessage(messageObtain);
        }
    }

    @Override // com.jieli.lib.p015dv.control.IClient
    public void close() {
        m1297a();
    }

    @Override // com.jieli.lib.p015dv.control.IClient
    public void registerConnectStateListener(OnConnectStateListener onConnectStateListener) {
        HashSet<OnConnectStateListener> hashSet = this.f2057e;
        if (hashSet == null || onConnectStateListener == null || hashSet.contains(onConnectStateListener)) {
            return;
        }
        this.f2057e.add(onConnectStateListener);
    }

    @Override // com.jieli.lib.p015dv.control.IClient
    public void unregisterConnectStateListener(OnConnectStateListener onConnectStateListener) {
        HashSet<OnConnectStateListener> hashSet = this.f2057e;
        if (hashSet == null || onConnectStateListener == null) {
            return;
        }
        hashSet.remove(onConnectStateListener);
    }

    @Override // com.jieli.lib.p015dv.control.IClient
    public void registerNotifyListener(OnNotifyListener onNotifyListener) {
        CopyOnWriteArrayList<OnNotifyListener> copyOnWriteArrayList = this.f2058f;
        if (copyOnWriteArrayList == null || onNotifyListener == null || copyOnWriteArrayList.contains(onNotifyListener)) {
            return;
        }
        this.f2058f.add(onNotifyListener);
    }

    @Override // com.jieli.lib.p015dv.control.IClient
    public void unregisterNotifyListener(OnNotifyListener onNotifyListener) {
        CopyOnWriteArrayList<OnNotifyListener> copyOnWriteArrayList = this.f2058f;
        if (copyOnWriteArrayList == null || onNotifyListener == null) {
            return;
        }
        copyOnWriteArrayList.remove(onNotifyListener);
    }

    @Override // com.jieli.lib.p015dv.control.IClient
    public void release() {
        m1306b();
    }

    @Override // com.jieli.lib.p015dv.control.IClient
    public String getAddress() {
        return this.f2064l;
    }

    /* JADX INFO: renamed from: a */
    private void m1303a(String str) {
        this.f2064l = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b */
    public void m1307b(final int i) {
        m1298a(i);
        if (i != 0 && i != 2) {
            m1303a((String) null);
        }
        HashSet<OnConnectStateListener> hashSet = this.f2057e;
        if (hashSet == null) {
            Dlog.m1386w(f2053a, "OnConnectStateListener is null");
            return;
        }
        for (final OnConnectStateListener onConnectStateListener : hashSet) {
            ClientContext.post(new Runnable() { // from class: com.jieli.lib.dv.control.ClientImpl.1
                @Override // java.lang.Runnable
                public void run() {
                    onConnectStateListener.onStateChanged(Integer.valueOf(i));
                }
            });
        }
    }

    /* JADX INFO: renamed from: a */
    private synchronized void m1302a(final NotifyInfo notifyInfo) {
        if (this.f2058f == null) {
            return;
        }
        for (final OnNotifyListener onNotifyListener : (CopyOnWriteArrayList) this.f2058f.clone()) {
            ClientContext.post(new Runnable() { // from class: com.jieli.lib.dv.control.ClientImpl.2
                @Override // java.lang.Runnable
                public void run() {
                    onNotifyListener.onNotify(notifyInfo);
                }
            });
        }
    }

    /* JADX INFO: renamed from: a */
    private void m1304a(final String str, final int i) {
        if (getState() == 2) {
            Dlog.m1386w(f2053a, "It is connecting ...");
            return;
        }
        CommandReceiver commandReceiver = this.f2061i;
        if (commandReceiver != null) {
            commandReceiver.stopRunning();
            this.f2061i = null;
        }
        new Thread(new Runnable() { // from class: com.jieli.lib.dv.control.ClientImpl.3
            @Override // java.lang.Runnable
            public void run() {
                ClientImpl.this.m1307b(2);
                int i2 = 0;
                while (true) {
                    try {
                        ClientImpl.this.f2055c = new Socket(str, i);
                    } catch (IOException unused) {
                        i2++;
                        ClientImpl.this.f2055c = null;
                        if (i2 >= 5) {
                            ClientImpl.this.m1307b(3);
                            Dlog.m1386w(ClientImpl.f2053a, "ERROR_CONNECTION_EXCEPTION： tryToConnect " + i2);
                            return;
                        }
                    }
                    if (ClientImpl.this.f2055c != null) {
                        try {
                            ClientImpl.this.f2056d = ClientImpl.this.f2055c.getOutputStream();
                        } catch (IOException unused2) {
                            Dlog.m1386w(ClientImpl.f2053a, "getOutputStream exception： 4");
                            ClientImpl.this.m1307b(4);
                        }
                        if (ClientImpl.this.f2056d != null) {
                            ClientImpl.this.m1307b(0);
                            ClientImpl.this.m1310d();
                            return;
                        } else {
                            Dlog.m1386w(ClientImpl.f2053a, "ERROR_CONNECTION_EXCEPTION： mOutputStream is null");
                            ClientImpl.this.m1307b(4);
                            return;
                        }
                    }
                    SystemClock.sleep(1000L);
                }
            }
        }).start();
    }

    /* JADX INFO: renamed from: a */
    private void m1297a() {
        Socket socket = this.f2055c;
        if (socket != null) {
            try {
                try {
                    socket.close();
                    m1307b(1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } finally {
                this.f2056d = null;
                this.f2055c = null;
                m1298a(-1);
            }
        }
        CommandReceiver commandReceiver = this.f2061i;
        if (commandReceiver != null) {
            commandReceiver.stopRunning();
            this.f2061i = null;
        }
    }

    /* JADX INFO: renamed from: b */
    private void m1306b() {
        m1297a();
        HashSet<OnConnectStateListener> hashSet = this.f2057e;
        if (hashSet != null) {
            hashSet.clear();
        }
        CopyOnWriteArrayList<OnNotifyListener> copyOnWriteArrayList = this.f2058f;
        if (copyOnWriteArrayList != null) {
            copyOnWriteArrayList.clear();
            this.f2058f = null;
        }
        if (this.f2059g != null) {
            if (Build.VERSION.SDK_INT >= 18) {
                this.f2059g.quitSafely();
            } else {
                this.f2059g.quit();
            }
            this.f2059g = null;
        }
        Handler handler = this.f2060h;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        this.f2060h = null;
        f2054b = null;
    }

    /* JADX INFO: renamed from: a */
    private synchronized void m1299a(Message message) {
        SendResponse sendResponse = (SendResponse) message.obj;
        CmdInfo cmdInfo = (CmdInfo) message.getData().getParcelable(Constants.EXTRA_CMD_INFO);
        if (cmdInfo == null) {
            throw new NullPointerException("cmdInfo is null");
        }
        byte[] bArr = getPackage(cmdInfo);
        if (bArr == null) {
            throw new NullPointerException("Data is null");
        }
        try {
            if (this.f2056d != null) {
                this.f2056d.write(bArr, 0, bArr.length);
                m1301a(sendResponse, 1);
                this.f2063k = 0;
            } else {
                Dlog.m1386w(f2053a, "OutputStream is null");
            }
        } catch (IOException unused) {
            m1301a(sendResponse, -1);
            int i = this.f2063k + 1;
            this.f2063k = i;
            if (i >= 3) {
                this.f2063k = 0;
                Dlog.m1383e(f2053a, "Send Error :retryNum=" + this.f2063k);
                m1307b(4);
            } else {
                m1299a(message);
            }
        }
    }

    /* JADX INFO: renamed from: c */
    private void m1308c() {
        if (this.f2056d != null) {
            SettingCmd settingCmd = new SettingCmd();
            settingCmd.setTopic(Topic.KEEP_ALIVE);
            byte[] bArr = getPackage(settingCmd);
            try {
                if (this.f2056d != null) {
                    this.f2056d.write(bArr, 0, bArr.length);
                }
                m1298a(0);
                this.f2063k = 0;
            } catch (IOException unused) {
                int i = this.f2063k + 1;
                this.f2063k = i;
                if (i >= 3) {
                    Dlog.m1383e(f2053a, "checkSocketAlive error : retryNum=" + this.f2063k);
                    m1298a(4);
                    this.f2063k = 0;
                    return;
                }
                m1308c();
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private void m1301a(final SendResponse sendResponse, final int i) {
        ClientContext.post(new Runnable() { // from class: com.jieli.lib.dv.control.ClientImpl.4
            @Override // java.lang.Runnable
            public void run() {
                SendResponse sendResponse2 = sendResponse;
                if (sendResponse2 != null) {
                    sendResponse2.onResponse(Integer.valueOf(i));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: d */
    public void m1310d() {
        CommandReceiver commandReceiver = this.f2061i;
        if (commandReceiver == null || commandReceiver.isInterrupted()) {
            CommandReceiver commandReceiver2 = new CommandReceiver(this.f2055c, this.f2060h);
            this.f2061i = commandReceiver2;
            commandReceiver2.start();
        }
    }

    public static byte[] getPackage(CmdInfo cmdInfo) {
        byte[] bytes;
        int length;
        byte[] bytes2 = Constants.CTP_SIGNATURE.getBytes();
        byte[] bytes3 = cmdInfo.getTopic().getBytes();
        short length2 = (short) bytes3.length;
        int i = 6 + length2 + 4;
        if (TextUtils.isEmpty(cmdInfo.getOperation())) {
            bytes = null;
            length = 0;
        } else {
            bytes = m1294a(cmdInfo).getBytes();
            length = bytes.length;
            i += length;
        }
        byte[] bArr = new byte[i];
        System.arraycopy(bytes2, 0, bArr, 0, bytes2.length);
        int length3 = bytes2.length + 0;
        ByteBuffer byteBufferOrder = ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN);
        byteBufferOrder.putShort(length2);
        System.arraycopy(byteBufferOrder.array(), 0, bArr, length3, 2);
        byteBufferOrder.clear();
        int i2 = length3 + 2;
        System.arraycopy(bytes3, 0, bArr, i2, length2);
        int i3 = i2 + length2;
        ByteBuffer byteBufferOrder2 = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN);
        byteBufferOrder2.putInt(length);
        System.arraycopy(byteBufferOrder2.array(), 0, bArr, i3, 4);
        byteBufferOrder2.clear();
        int i4 = i3 + 4;
        if (bytes != null) {
            System.arraycopy(bytes, 0, bArr, i4, length);
        }
        return bArr;
    }

    /* JADX INFO: renamed from: a */
    private static String m1294a(CmdInfo cmdInfo) {
        if (cmdInfo == null) {
            throw new NullPointerException("cmd info is null");
        }
        String str = (cmdInfo.getErrorType() != -100 ? "{\"errno\":" + cmdInfo.getErrorType() + "," : "{") + "\"op\":\"" + cmdInfo.getOperation() + "\"";
        ArrayMap<String, String> params = cmdInfo.getParams();
        if (params != null) {
            String str2 = ((str + ",") + "\"param\":") + "{";
            int i = 0;
            for (Map.Entry<String, String> entry : params.entrySet()) {
                str2 = str2 + "\"" + entry.getKey() + "\":\"" + entry.getValue() + "\"";
                if (i == params.size() - 1) {
                    break;
                }
                str2 = str2 + ",";
                i++;
            }
            str = str2 + "}";
        }
        return str + "}";
    }
}
