package com.jieli.lib.p015dv.control.projection;

import android.text.TextUtils;
import com.jieli.lib.p015dv.control.projection.beans.StreamData;
import com.jieli.lib.p015dv.control.projection.tools.Utils;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.concurrent.LinkedBlockingDeque;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class UDPSocketManager {

    /* JADX INFO: renamed from: b */
    private static UDPSocketManager f2157b;

    /* JADX INFO: renamed from: c */
    private DatagramSocket f2159c;

    /* JADX INFO: renamed from: d */
    private InetAddress f2160d;

    /* JADX INFO: renamed from: e */
    private String f2161e;

    /* JADX INFO: renamed from: f */
    private C1430a f2162f;

    /* JADX INFO: renamed from: i */
    private int f2165i;

    /* JADX INFO: renamed from: j */
    private OnSocketErrorListener f2166j;

    /* JADX INFO: renamed from: a */
    private String f2158a = "UDPSocketManager";

    /* JADX INFO: renamed from: g */
    private int f2163g = 0;

    /* JADX INFO: renamed from: h */
    private int f2164h = 0;

    public interface OnSocketErrorListener {
        void onError(int i);
    }

    private UDPSocketManager(String str) {
        createUDPSocket(str);
    }

    public static UDPSocketManager getInstance(String str) {
        if (f2157b == null) {
            synchronized (UDPSocketManager.class) {
                if (f2157b == null) {
                    f2157b = new UDPSocketManager(str);
                }
            }
        }
        return f2157b;
    }

    public void createUDPSocket(String str) {
        try {
            this.f2159c = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f2161e = str;
        try {
            this.f2160d = InetAddress.getByName(str);
        } catch (UnknownHostException e2) {
            e2.printStackTrace();
        }
    }

    public boolean isSendThreadRunning() {
        C1430a c1430a = this.f2162f;
        return c1430a != null && c1430a.f2169c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public int m1364a(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return 0;
        }
        try {
            DatagramPacket datagramPacket = new DatagramPacket(bArr, bArr.length, this.f2160d, 2230);
            if (this.f2159c != null) {
                this.f2159c.send(datagramPacket);
                return 1;
            }
            createUDPSocket(this.f2161e);
            if (this.f2159c == null) {
                return 0;
            }
            this.f2159c.send(datagramPacket);
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
            int i = this.f2165i + 1;
            this.f2165i = i;
            if (i >= 3) {
                OnSocketErrorListener onSocketErrorListener = this.f2166j;
                if (onSocketErrorListener == null) {
                    return -1;
                }
                onSocketErrorListener.onError(1);
                return -1;
            }
            m1364a(bArr);
            return 0;
        }
    }

    public void initSendThread() {
        C1430a c1430a = this.f2162f;
        if (c1430a == null) {
            C1430a c1430a2 = new C1430a();
            this.f2162f = c1430a2;
            c1430a2.f2169c = true;
            this.f2162f.start();
            return;
        }
        if (!c1430a.isAlive()) {
            C1430a c1430a3 = new C1430a();
            this.f2162f = c1430a3;
            c1430a3.f2169c = true;
            this.f2162f.start();
            return;
        }
        if (this.f2162f.f2169c) {
            return;
        }
        this.f2162f.f2169c = true;
    }

    public void writeData(int i, byte[] bArr) {
        int i2;
        int i3;
        byte[] bArr2;
        C1430a c1430a = this.f2162f;
        if (c1430a == null || !c1430a.f2169c || bArr == null || bArr.length <= 0) {
            return;
        }
        int length = bArr.length;
        if (length % 1451 == 0) {
            i2 = length / 1451;
        } else {
            i2 = (length / 1451) + 1;
        }
        if (i == 2 || i == 3) {
            i3 = this.f2164h + 1;
            this.f2164h = i3;
        } else if (i == 1) {
            i3 = this.f2163g + 1;
            this.f2163g = i3;
        } else {
            i3 = 0;
        }
        if (i3 > 0) {
            for (int i4 = 0; i4 < i2; i4++) {
                int i5 = i4 * 1451;
                int i6 = length - i5;
                if (i6 >= 1451) {
                    bArr2 = new byte[1451];
                    System.arraycopy(bArr, i5, bArr2, 0, 1451);
                } else {
                    byte[] bArr3 = new byte[i6];
                    System.arraycopy(bArr, i5, bArr3, 0, i6);
                    bArr2 = bArr3;
                }
                StreamData streamData = new StreamData();
                streamData.setType(i);
                streamData.setSave(0);
                streamData.setSeq(i3);
                streamData.setFrameSize(length);
                streamData.setOffset(i5);
                streamData.setPayload(bArr2);
                streamData.setPayloadLen(bArr2.length);
                streamData.setDateFlag((int) Calendar.getInstance().getTimeInMillis());
                this.f2162f.m1368a(streamData);
            }
        }
    }

    /* JADX INFO: renamed from: com.jieli.lib.dv.control.projection.UDPSocketManager$a */
    private class C1430a extends Thread {

        /* JADX INFO: renamed from: b */
        private final LinkedBlockingDeque<StreamData> f2168b;

        /* JADX INFO: renamed from: c */
        private boolean f2169c;

        private C1430a() {
            this.f2168b = new LinkedBlockingDeque<>();
            this.f2169c = false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: a */
        public void m1368a(StreamData streamData) {
            if (this.f2168b.remainingCapacity() <= 0) {
                this.f2168b.poll();
                return;
            }
            try {
                this.f2168b.put(streamData);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override // java.lang.Thread
        public synchronized void start() {
            super.start();
            UDPSocketManager.this.f2165i = 0;
        }

        /* JADX INFO: renamed from: a */
        public void m1371a() {
            UDPSocketManager.this.f2165i = 0;
            UDPSocketManager.this.f2163g = 0;
            UDPSocketManager.this.f2164h = 0;
            this.f2169c = false;
            this.f2168b.clear();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            byte[] bArrMergeDataPacket;
            while (this.f2169c) {
                StreamData streamDataPoll = this.f2168b.poll();
                if (streamDataPoll != null && (bArrMergeDataPacket = Utils.mergeDataPacket(streamDataPoll)) != null && UDPSocketManager.this.m1364a(bArrMergeDataPacket) < 0) {
                    m1371a();
                    return;
                }
            }
        }
    }

    public void stopSendDataThread() {
        C1430a c1430a = this.f2162f;
        if (c1430a != null) {
            if (c1430a.f2169c) {
                this.f2162f.m1371a();
            }
            this.f2162f.interrupt();
            this.f2162f = null;
        }
    }

    public void release() {
        f2157b = null;
        stopSendDataThread();
        DatagramSocket datagramSocket = this.f2159c;
        if (datagramSocket != null) {
            datagramSocket.disconnect();
            this.f2159c = null;
        }
        if (this.f2160d != null) {
            this.f2160d = null;
        }
    }

    public void setOnSocketErrorListener(OnSocketErrorListener onSocketErrorListener) {
        this.f2166j = onSocketErrorListener;
    }
}
