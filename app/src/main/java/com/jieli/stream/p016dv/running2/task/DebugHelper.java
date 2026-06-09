package com.jieli.stream.p016dv.running2.task;

import android.content.Context;
import android.net.TrafficStats;
import android.os.Handler;
import android.os.Looper;
import android.text.format.Formatter;
import com.jieli.lib.p015dv.control.utils.Dlog;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public class DebugHelper {
    private static final String DEBUG_PACKET_FLAG = "MSSDP_NOTIFY ";
    private static final int DEBUG_PORT = 3889;
    private static final String DEBUG_RESULT_FLAG = "UX_REPORT";
    private static final String DEBUG_SEND_FLAG = "UX_DATA";
    private static final String DEBUG_START_FLAG = "UX_SEND_LEN";
    public static final int ERROR_DATA_EXCEPTION = 3;
    public static final int ERROR_NETWORK_EXCEPTION = 2;
    public static final int ERROR_UDP_UNINIT = 1;
    private static int mSeq;
    private String mBroadCastIP;
    private HashSet<IDebugListener> mIDebugListeners;
    private ReceiveThread mReceiveThread;
    private SendThread mSendThread;
    private DatagramSocket mSocket;
    private static final String tag = DebugHelper.class.getSimpleName();
    private static long lastTotalRxBytes = 0;
    private static long lastTimeStamp = 0;
    private int mPort = 3889;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    /* JADX INFO: Access modifiers changed from: private */
    public String code2Msg(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "" : "receive data is error." : "network error." : "udp socket init failed.";
    }

    static /* synthetic */ int access$908() {
        int i = mSeq;
        mSeq = i + 1;
        return i;
    }

    public void startDebug() {
        createUDPClient();
        startRecvThread();
    }

    public void closeDebug() {
        stopSendThread();
        stopRecvThread();
        closeUDPClient();
        HashSet<IDebugListener> hashSet = this.mIDebugListeners;
        if (hashSet != null) {
            hashSet.clear();
            this.mIDebugListeners = null;
        }
    }

    public boolean registerDebugListener(IDebugListener iDebugListener) {
        if (this.mIDebugListeners == null) {
            this.mIDebugListeners = new HashSet<>();
        }
        return iDebugListener != null && this.mIDebugListeners.add(iDebugListener);
    }

    public boolean unregisterDebugListener(IDebugListener iDebugListener) {
        HashSet<IDebugListener> hashSet;
        return (iDebugListener == null || (hashSet = this.mIDebugListeners) == null || !hashSet.remove(iDebugListener)) ? false : true;
    }

    private void createUDPClient() {
        if (this.mSocket == null) {
            try {
                DatagramSocket datagramSocket = new DatagramSocket(this.mPort);
                this.mSocket = datagramSocket;
                datagramSocket.setBroadcast(true);
                this.mSocket.setReuseAddress(true);
            } catch (IOException e) {
                e.printStackTrace();
                this.mSocket = null;
            }
        }
    }

    private void closeUDPClient() {
        DatagramSocket datagramSocket = this.mSocket;
        if (datagramSocket != null) {
            if (!datagramSocket.isClosed()) {
                this.mSocket.close();
            }
            this.mSocket = null;
        }
    }

    private void startRecvThread() {
        ReceiveThread receiveThread = this.mReceiveThread;
        if (receiveThread == null || !receiveThread.isThreadAlive) {
            ReceiveThread receiveThread2 = new ReceiveThread(this.mSocket);
            this.mReceiveThread = receiveThread2;
            receiveThread2.start();
        }
    }

    private void stopRecvThread() {
        ReceiveThread receiveThread = this.mReceiveThread;
        if (receiveThread != null) {
            if (receiveThread.isThreadAlive) {
                this.mReceiveThread.stopThread();
            }
            this.mReceiveThread = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyDebugStart(final String str, final int i, final int i2) {
        this.mBroadCastIP = str;
        startSendThread(str, i, i2);
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.jieli.stream.dv.running2.task.DebugHelper.1
                @Override // java.lang.Runnable
                public void run() {
                    if (DebugHelper.this.mIDebugListeners != null) {
                        Iterator it = ((HashSet) DebugHelper.this.mIDebugListeners.clone()).iterator();
                        while (it.hasNext()) {
                            ((IDebugListener) it.next()).onStartDebug(str, i, i2);
                        }
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyDebugResult(final int i, final int i2) {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.jieli.stream.dv.running2.task.DebugHelper.2
                @Override // java.lang.Runnable
                public void run() {
                    if (DebugHelper.this.mIDebugListeners != null) {
                        Iterator it = ((HashSet) DebugHelper.this.mIDebugListeners.clone()).iterator();
                        while (it.hasNext()) {
                            ((IDebugListener) it.next()).onDebugResult(i, i2);
                        }
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyErrorEvent(final int i, final String str) {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.jieli.stream.dv.running2.task.DebugHelper.3
                @Override // java.lang.Runnable
                public void run() {
                    if (DebugHelper.this.mIDebugListeners != null) {
                        Iterator it = ((HashSet) DebugHelper.this.mIDebugListeners.clone()).iterator();
                        while (it.hasNext()) {
                            ((IDebugListener) it.next()).onError(i, str);
                        }
                    }
                }
            });
        }
    }

    private class ReceiveThread extends Thread {
        private volatile boolean isThreadAlive = false;
        private DatagramSocket mSocket;

        ReceiveThread(DatagramSocket datagramSocket) {
            this.mSocket = datagramSocket;
        }

        @Override // java.lang.Thread
        public synchronized void start() {
            this.isThreadAlive = true;
            super.start();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public synchronized void stopThread() {
            this.isThreadAlive = false;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Dlog.m1384i(DebugHelper.tag, "ReceiveThread running...");
            while (true) {
                if (!this.isThreadAlive) {
                    break;
                }
                if (this.mSocket == null) {
                    DebugHelper debugHelper = DebugHelper.this;
                    debugHelper.notifyErrorEvent(1, debugHelper.code2Msg(1));
                    break;
                }
                try {
                    DatagramPacket datagramPacket = new DatagramPacket(new byte[5120], 5120);
                    this.mSocket.receive(datagramPacket);
                    if (datagramPacket.getAddress() != null) {
                        String hostAddress = datagramPacket.getAddress().getHostAddress();
                        if (datagramPacket.getData() != null && datagramPacket.getData().length > 0) {
                            String strTrim = new String(datagramPacket.getData()).trim();
                            if (strTrim.startsWith(DebugHelper.DEBUG_PACKET_FLAG)) {
                                String strReplace = strTrim.replace(DebugHelper.DEBUG_PACKET_FLAG, "");
                                if (strReplace.indexOf(DebugHelper.DEBUG_START_FLAG) != -1) {
                                    if (strReplace.contains(",")) {
                                        String[] strArrSplit = strReplace.split(",", 2);
                                        int iString2Int = strArrSplit[0].contains(":") ? string2Int(strArrSplit[0].split(":", 2)[1]) : 0;
                                        int iString2Int2 = strArrSplit[1].contains(":") ? string2Int(strArrSplit[1].split(":", 2)[1]) : 0;
                                        if (iString2Int > 0) {
                                            DebugHelper.this.notifyDebugStart(hostAddress, iString2Int, iString2Int2);
                                        } else {
                                            DebugHelper.this.notifyErrorEvent(3, DebugHelper.this.code2Msg(3));
                                        }
                                    }
                                } else if (strReplace.indexOf(DebugHelper.DEBUG_RESULT_FLAG) == -1) {
                                    Dlog.m1383e(DebugHelper.tag, "unknown data : " + strReplace);
                                    DebugHelper.this.notifyErrorEvent(3, strReplace);
                                } else if (strReplace.contains(":")) {
                                    String str = strReplace.split(":", 2)[1];
                                    if (str.contains(",")) {
                                        String[] strArrSplit2 = str.split(",", 2);
                                        DebugHelper.this.notifyDebugResult(strArrSplit2[0].contains(":") ? string2Int(strArrSplit2[0].split(":", 2)[1]) : 0, strArrSplit2[1].contains(":") ? string2Int(strArrSplit2[1].split(":", 2)[1]) : 0);
                                    }
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    DebugHelper.this.notifyErrorEvent(2, e.getMessage());
                }
            }
            this.isThreadAlive = false;
        }

        private int string2Int(String str) {
            if (str == null || str.length() <= 0) {
                return 0;
            }
            try {
                return Integer.valueOf(str).intValue();
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
    }

    private void startSendThread(String str, int i, int i2) {
        SendThread sendThread = this.mSendThread;
        if (sendThread == null || !sendThread.isThreadAlive) {
            SendThread sendThread2 = new SendThread(this.mSocket, str, i, i2);
            this.mSendThread = sendThread2;
            sendThread2.start();
        }
    }

    private void stopSendThread() {
        SendThread sendThread = this.mSendThread;
        if (sendThread != null) {
            if (sendThread.isThreadAlive) {
                this.mSendThread.stopThread();
            }
            this.mSendThread = null;
        }
    }

    private class SendThread extends Thread {
        private int dataLen;
        private int interval;
        private volatile boolean isThreadAlive = false;
        private InetAddress mInetAddress;
        private DatagramSocket mSocket;

        SendThread(DatagramSocket datagramSocket, String str, int i, int i2) {
            this.mSocket = datagramSocket;
            this.dataLen = i;
            this.interval = i2;
            try {
                this.mInetAddress = InetAddress.getByName(str);
            } catch (UnknownHostException e) {
                e.printStackTrace();
                this.mInetAddress = null;
            }
        }

        @Override // java.lang.Thread
        public synchronized void start() {
            this.isThreadAlive = true;
            super.start();
        }

        public synchronized void stopThread() {
            this.isThreadAlive = false;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            DatagramSocket datagramSocket;
            Dlog.m1384i(DebugHelper.tag, "SendThread running...");
            while (this.isThreadAlive) {
                DatagramPacket datagramPacketCreatePacket = createPacket();
                if (datagramPacketCreatePacket != null && (datagramSocket = this.mSocket) != null) {
                    try {
                        datagramSocket.send(datagramPacketCreatePacket);
                        try {
                            Thread.sleep(this.interval);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e2) {
                        e2.printStackTrace();
                        DebugHelper.this.notifyErrorEvent(2, e2.getMessage());
                    }
                }
            }
        }

        private DatagramPacket createPacket() {
            if (this.dataLen <= 0 || this.mInetAddress == null) {
                return null;
            }
            DebugHelper.access$908();
            byte[] bytes = ("MSSDP_NOTIFY UX_DATA:" + DebugHelper.mSeq + " ").getBytes();
            byte[] bArr = new byte[this.dataLen];
            int i = 0;
            while (true) {
                int i2 = this.dataLen;
                if (i < i2) {
                    bArr[i] = (byte) new Random(100L).nextInt();
                    i++;
                } else {
                    int length = bytes.length + i2;
                    byte[] bArr2 = new byte[length];
                    System.arraycopy(bytes, 0, bArr2, 0, bytes.length);
                    System.arraycopy(bArr, 0, bArr2, bytes.length, this.dataLen);
                    return new DatagramPacket(bArr2, length, this.mInetAddress, DebugHelper.this.mPort);
                }
            }
        }
    }

    private static long getTotalRxBytes(Context context) {
        if (TrafficStats.getUidRxBytes(context.getApplicationInfo().uid) == -1) {
            return 0L;
        }
        return Math.abs(TrafficStats.getTotalRxBytes() - TrafficStats.getMobileRxBytes());
    }

    public static String getNetSpeed(Context context) {
        long totalRxBytes = getTotalRxBytes(context);
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = ((totalRxBytes - lastTotalRxBytes) * 1000) / (jCurrentTimeMillis - lastTimeStamp);
        lastTimeStamp = jCurrentTimeMillis;
        lastTotalRxBytes = totalRxBytes;
        return Formatter.formatFileSize(context, j) + "/s";
    }
}
