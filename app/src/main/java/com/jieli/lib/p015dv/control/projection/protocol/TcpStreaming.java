package com.jieli.lib.p015dv.control.projection.protocol;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.jieli.lib.p015dv.control.projection.OnSendStateListener;
import com.jieli.lib.p015dv.control.projection.beans.StreamData;
import com.jieli.lib.p015dv.control.projection.tools.Utils;
import com.jieli.lib.p015dv.control.utils.Dlog;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Calendar;

/* JADX INFO: loaded from: classes.dex */
public final class TcpStreaming extends BaseStreaming implements Handler.Callback {

    /* JADX INFO: renamed from: b */
    private Socket f2179b;

    /* JADX INFO: renamed from: d */
    private OnSendStateListener f2181d;

    /* JADX INFO: renamed from: g */
    private HandlerThread f2184g;

    /* JADX INFO: renamed from: h */
    private Handler f2185h;

    /* JADX INFO: renamed from: a */
    private String f2178a = getClass().getSimpleName();

    /* JADX INFO: renamed from: c */
    private volatile OutputStream f2180c = null;

    /* JADX INFO: renamed from: e */
    private int f2182e = 0;

    /* JADX INFO: renamed from: f */
    private int f2183f = 0;

    /* JADX INFO: renamed from: i */
    private final int f2186i = 100;

    public TcpStreaming() {
        HandlerThread handlerThread = new HandlerThread(this.f2178a);
        this.f2184g = handlerThread;
        handlerThread.start();
        this.f2185h = new Handler(this.f2184g.getLooper(), this);
    }

    @Override // com.jieli.lib.p015dv.control.projection.IPushStream
    public void create(String str) {
        create(str, 2230);
    }

    @Override // com.jieli.lib.p015dv.control.projection.IPushStream
    public void create(final String str, final int i) {
        new Thread(new Runnable() { // from class: com.jieli.lib.dv.control.projection.protocol.TcpStreaming.1
            @Override // java.lang.Runnable
            public void run() {
                TcpStreaming.this.m1373a(str, i);
            }
        }).start();
    }

    @Override // com.jieli.lib.p015dv.control.projection.IPushStream
    public void close() {
        synchronized (this) {
            if (this.f2179b != null) {
                if (this.f2180c != null) {
                    try {
                        this.f2180c.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    try {
                        this.f2179b.close();
                        if (this.f2180c != null) {
                            try {
                                this.f2180c.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        this.f2180c = null;
                    } catch (IOException e3) {
                        e3.printStackTrace();
                        if (this.f2180c != null) {
                            try {
                                this.f2180c.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        this.f2180c = null;
                    }
                    this.f2179b = null;
                } finally {
                }
            }
            if (this.f2184g != null) {
                if (Build.VERSION.SDK_INT >= 18) {
                    this.f2184g.quitSafely();
                } else {
                    this.f2184g.quit();
                }
                this.f2184g = null;
            }
            if (this.f2185h != null) {
                this.f2185h.removeCallbacksAndMessages(null);
            }
            this.f2185h = null;
        }
    }

    @Override // com.jieli.lib.p015dv.control.projection.IPushStream
    public boolean send(int i, byte[] bArr) {
        Handler handler = this.f2185h;
        if (handler != null) {
            handler.removeMessages(100);
        }
        Bundle bundle = new Bundle();
        Message messageObtain = Message.obtain();
        messageObtain.setData(bundle);
        messageObtain.arg1 = i;
        messageObtain.obj = bArr;
        messageObtain.what = 100;
        Handler handler2 = this.f2185h;
        if (handler2 == null) {
            return true;
        }
        handler2.sendMessage(messageObtain);
        return true;
    }

    @Override // com.jieli.lib.p015dv.control.projection.IPushStream
    public void setOnSendStateListener(OnSendStateListener onSendStateListener) {
        this.f2181d = onSendStateListener;
    }

    /* JADX INFO: renamed from: a */
    private void m1374a(byte[] bArr) {
        if (this.f2180c != null && bArr != null) {
            try {
                this.f2180c.write(bArr, 0, bArr.length);
            } catch (IOException e) {
                OnSendStateListener onSendStateListener = this.f2181d;
                if (onSendStateListener != null) {
                    onSendStateListener.onState(-1, e.getMessage());
                }
                e.printStackTrace();
            }
            OnSendStateListener onSendStateListener2 = this.f2181d;
            if (onSendStateListener2 != null) {
                onSendStateListener2.onState(1, "Successful");
                return;
            }
            return;
        }
        Dlog.m1386w(this.f2178a, "fail to send data");
        OnSendStateListener onSendStateListener3 = this.f2181d;
        if (onSendStateListener3 != null) {
            onSendStateListener3.onState(-1, "Socket maybe has been closed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public void m1373a(String str, int i) {
        try {
            this.f2179b = new Socket(str, i);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Socket socket = this.f2179b;
        if (socket != null) {
            try {
                this.f2180c = socket.getOutputStream();
                return;
            } catch (IOException e2) {
                e2.printStackTrace();
                return;
            }
        }
        OnSendStateListener onSendStateListener = this.f2181d;
        if (onSendStateListener != null) {
            onSendStateListener.onState(-2, "Create socket failure");
        }
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        int i;
        if (message.what == 100) {
            int i2 = message.arg1;
            byte[] bArr = (byte[]) message.obj;
            if (i2 == 2 || i2 == 3) {
                i = this.f2183f + 1;
                this.f2183f = i;
            } else if (i2 == 1) {
                i = this.f2182e + 1;
                this.f2182e = i;
            } else {
                i = 0;
            }
            StreamData streamData = new StreamData();
            streamData.setType(i2);
            streamData.setSave(0);
            streamData.setSeq(i);
            streamData.setFrameSize(bArr.length);
            streamData.setOffset(0);
            streamData.setPayload(bArr);
            streamData.setPayloadLen(bArr.length);
            streamData.setDateFlag((int) Calendar.getInstance().getTimeInMillis());
            byte[] bArrMergeDataPacket = Utils.mergeDataPacket(streamData);
            synchronized (this) {
                m1374a(bArrMergeDataPacket);
            }
        }
        return false;
    }
}
