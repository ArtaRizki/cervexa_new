package com.jieli.lib.p015dv.control.intercom;

import android.text.TextUtils;
import com.jieli.lib.p015dv.control.projection.OnSendStateListener;
import com.jieli.lib.p015dv.control.projection.beans.StreamData;
import com.jieli.lib.p015dv.control.projection.tools.Utils;
import com.jieli.lib.p015dv.control.utils.Dlog;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.concurrent.LinkedBlockingDeque;

/* JADX INFO: loaded from: classes.dex */
public class IntercomManager {

    /* JADX INFO: renamed from: b */
    private static IntercomManager f2105b;

    /* JADX INFO: renamed from: a */
    private String f2106a = "IntercomManager";

    /* JADX INFO: renamed from: c */
    private DatagramSocket f2107c;

    /* JADX INFO: renamed from: d */
    private InetAddress f2108d;

    /* JADX INFO: renamed from: e */
    private C1415a f2109e;

    /* JADX INFO: renamed from: f */
    private OnSendStateListener f2110f;

    /* JADX INFO: renamed from: g */
    private int f2111g;

    /* JADX INFO: renamed from: h */
    private int f2112h;

    /* JADX INFO: renamed from: i */
    private long f2113i;

    /* JADX INFO: renamed from: j */
    private int f2114j;

    /* JADX INFO: renamed from: k */
    private long f2115k;

    /* JADX INFO: renamed from: e */
    static /* synthetic */ int m1337e(IntercomManager intercomManager) {
        int i = intercomManager.f2114j;
        intercomManager.f2114j = i + 1;
        return i;
    }

    private IntercomManager(String str) {
        createClient(str);
    }

    public static IntercomManager getInstance(String str) {
        if (f2105b == null) {
            synchronized (IntercomManager.class) {
                if (f2105b == null) {
                    f2105b = new IntercomManager(str);
                }
            }
        }
        return f2105b;
    }

    public void createClient(String str) {
        try {
            this.f2107c = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            this.f2108d = InetAddress.getByName(str);
        } catch (UnknownHostException e2) {
            e2.printStackTrace();
        }
    }

    public boolean isSendThreadRunning() {
        C1415a c1415a = this.f2109e;
        return c1415a != null && c1415a.f2118c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public int m1328a(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return 0;
        }
        try {
            DatagramPacket datagramPacket = new DatagramPacket(bArr, bArr.length, this.f2108d, 2231);
            if (this.f2107c == null) {
                return 0;
            }
            this.f2107c.send(datagramPacket);
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
            int i = this.f2112h + 1;
            this.f2112h = i;
            if (i >= 3) {
                OnSendStateListener onSendStateListener = this.f2110f;
                if (onSendStateListener == null) {
                    return -1;
                }
                onSendStateListener.onState(1, "Sending failed after try times");
                return -1;
            }
            m1328a(bArr);
            return 0;
        }
    }

    public void initSendThread() {
        C1415a c1415a = this.f2109e;
        if (c1415a == null) {
            C1415a c1415a2 = new C1415a();
            this.f2109e = c1415a2;
            c1415a2.f2118c = true;
            this.f2109e.start();
            return;
        }
        if (!c1415a.isAlive()) {
            C1415a c1415a3 = new C1415a();
            this.f2109e = c1415a3;
            c1415a3.f2118c = true;
            this.f2109e.start();
            return;
        }
        if (this.f2109e.f2118c) {
            return;
        }
        this.f2109e.f2118c = true;
    }

    public void send(byte[] bArr) {
        int i;
        byte[] bArr2;
        C1415a c1415a = this.f2109e;
        if (c1415a == null || !c1415a.f2118c || bArr == null || bArr.length <= 0) {
            return;
        }
        int length = bArr.length;
        if (length % 1448 == 0) {
            i = length / 1448;
        } else {
            i = (length / 1448) + 1;
        }
        int i2 = this.f2111g + 1;
        this.f2111g = i2;
        if (i2 > 0) {
            for (int i3 = 0; i3 < i; i3++) {
                int i4 = i3 * 1448;
                int i5 = length - i4;
                if (i5 >= 1448) {
                    bArr2 = new byte[1448];
                    System.arraycopy(bArr, i4, bArr2, 0, 1448);
                } else {
                    byte[] bArr3 = new byte[i5];
                    System.arraycopy(bArr, i4, bArr3, 0, i5);
                    bArr2 = bArr3;
                }
                StreamData streamData = new StreamData();
                streamData.setType(1);
                streamData.setSave(0);
                streamData.setSeq(this.f2111g);
                streamData.setFrameSize(length);
                streamData.setOffset(i4);
                streamData.setPayload(bArr2);
                streamData.setPayloadLen(bArr2.length);
                streamData.setDateFlag((int) Calendar.getInstance().getTimeInMillis());
                this.f2109e.m1339a(streamData);
            }
        }
    }

    /* JADX INFO: renamed from: com.jieli.lib.dv.control.intercom.IntercomManager$a */
    private class C1415a extends Thread {

        /* JADX INFO: renamed from: b */
        private final LinkedBlockingDeque<StreamData> f2117b;

        /* JADX INFO: renamed from: c */
        private boolean f2118c;

        private C1415a() {
            this.f2117b = new LinkedBlockingDeque<>();
            this.f2118c = false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: a */
        public void m1339a(StreamData streamData) {
            if (this.f2117b.remainingCapacity() <= 0) {
                this.f2117b.poll();
                return;
            }
            try {
                this.f2117b.put(streamData);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override // java.lang.Thread
        public synchronized void start() {
            super.start();
            IntercomManager.this.f2112h = 0;
        }

        /* JADX INFO: renamed from: a */
        public void m1343a() {
            IntercomManager.this.f2112h = 0;
            IntercomManager.this.f2111g = 0;
            this.f2118c = false;
            this.f2117b.clear();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            byte[] bArrMergeDataPacket;
            while (this.f2118c) {
                StreamData streamDataPoll = this.f2117b.poll();
                if (streamDataPoll != null && (bArrMergeDataPacket = Utils.mergeDataPacket(streamDataPoll)) != null) {
                    int iM1328a = IntercomManager.this.m1328a(bArrMergeDataPacket);
                    if (iM1328a == 1) {
                        m1342b(streamDataPoll);
                    }
                    if (iM1328a < 0) {
                        m1343a();
                        return;
                    }
                }
            }
        }

        /* JADX INFO: renamed from: b */
        private void m1342b(StreamData streamData) {
            if (streamData == null) {
                return;
            }
            long timeInMillis = Calendar.getInstance().getTimeInMillis();
            if (IntercomManager.this.f2115k == 0) {
                IntercomManager.this.f2115k = timeInMillis;
                return;
            }
            if (timeInMillis - IntercomManager.this.f2115k >= 1000) {
                Dlog.m1386w(IntercomManager.this.f2106a, "frameNum = " + IntercomManager.this.f2114j + ", totalSize = " + IntercomManager.this.f2113i);
                IntercomManager.this.f2115k = 0L;
                IntercomManager.this.f2114j = 0;
                IntercomManager.this.f2113i = 0L;
                return;
            }
            if (streamData.getOffset() == 0) {
                IntercomManager.m1337e(IntercomManager.this);
            }
            IntercomManager.this.f2113i += (long) streamData.getPayloadLen();
        }
    }

    public void stopSendDataThread() {
        C1415a c1415a = this.f2109e;
        if (c1415a != null) {
            if (c1415a.f2118c) {
                this.f2109e.m1343a();
            }
            this.f2109e.interrupt();
            this.f2109e = null;
        }
    }

    public void closeClient() {
        f2105b = null;
        stopSendDataThread();
        DatagramSocket datagramSocket = this.f2107c;
        if (datagramSocket != null) {
            datagramSocket.disconnect();
            this.f2107c = null;
        }
        if (this.f2108d != null) {
            this.f2108d = null;
        }
    }

    public void setOnSendStateListener(OnSendStateListener onSendStateListener) {
        this.f2110f = onSendStateListener;
    }
}
