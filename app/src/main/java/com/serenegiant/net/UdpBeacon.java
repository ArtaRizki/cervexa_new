package com.serenegiant.net;

import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import com.bumptech.glide.load.Key;
import com.serenegiant.utils.HandlerThreadHandler;
import java.io.IOException;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.UByte;

/* JADX INFO: loaded from: classes2.dex */
public class UdpBeacon {
    public static final int BEACON_SIZE = 23;
    private static final int BEACON_UDP_PORT = 9999;
    private static final byte BEACON_VERSION = 1;
    private static final Charset CHARSET = Charset.forName(Key.STRING_CHARSET_NAME);
    private static final long DEFAULT_BEACON_SEND_INTERVALS_MS = 3000;
    private static final int SO_TIMEOUT_MS = 2000;
    private static final String TAG = "UdpBeacon";
    private final byte[] beaconBytes;
    private Handler mAsyncHandler;
    private final long mBeaconIntervalsMs;
    private final Runnable mBeaconTask;
    private Thread mBeaconThread;
    private final CopyOnWriteArraySet<UdpBeaconCallback> mCallbacks;
    private volatile boolean mIsRunning;
    private final long mRcvMinIntervalsMs;
    private boolean mReceiveOnly;
    private volatile boolean mReleased;
    private final Object mSync;
    private final UUID uuid;

    public interface UdpBeaconCallback {
        void onError(Exception exc);

        void onReceiveBeacon(UUID uuid, String str, int i);
    }

    private static class Beacon {
        public static final String BEACON_IDENTITY = "SAKI";
        private final int extraBytes;
        private final ByteBuffer extras;
        private final int listenPort;
        private final UUID uuid;

        public Beacon(ByteBuffer byteBuffer) {
            this.uuid = new UUID(byteBuffer.getLong(), byteBuffer.getLong());
            int i = byteBuffer.getShort();
            this.listenPort = i < 0 ? i & 65535 : i;
            if (byteBuffer.remaining() > 1) {
                int i2 = byteBuffer.get() & UByte.MAX_VALUE;
                this.extraBytes = i2;
                if (i2 > 0) {
                    ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(i2);
                    this.extras = byteBufferAllocateDirect;
                    byteBufferAllocateDirect.put(byteBuffer);
                    this.extras.flip();
                    return;
                }
                this.extras = null;
                return;
            }
            this.extraBytes = 0;
            this.extras = null;
        }

        public Beacon(UUID uuid, int i) {
            this(uuid, i, 0);
        }

        public Beacon(UUID uuid, int i, int i2) {
            this.uuid = uuid;
            this.listenPort = i;
            int i3 = i2 & 255;
            this.extraBytes = i3;
            if (i3 > 0) {
                this.extras = ByteBuffer.allocateDirect(i3);
            } else {
                this.extras = null;
            }
        }

        public byte[] asBytes() {
            int i = this.extraBytes;
            byte[] bArr = new byte[(i > 0 ? i + 1 : 0) + 23];
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
            byteBufferWrap.put(BEACON_IDENTITY.getBytes());
            byteBufferWrap.put(UdpBeacon.BEACON_VERSION);
            byteBufferWrap.putLong(this.uuid.getMostSignificantBits());
            byteBufferWrap.putLong(this.uuid.getLeastSignificantBits());
            byteBufferWrap.putShort((short) this.listenPort);
            int i2 = this.extraBytes;
            if (i2 > 0) {
                byteBufferWrap.put((byte) i2);
                this.extras.clear();
                this.extras.flip();
                byteBufferWrap.put(this.extras);
            }
            byteBufferWrap.flip();
            return bArr;
        }

        public ByteBuffer extra() {
            return this.extras;
        }

        public void extra(byte[] bArr) {
            extra(ByteBuffer.wrap(bArr));
        }

        public void extra(ByteBuffer byteBuffer) {
            int iRemaining = byteBuffer != null ? byteBuffer.remaining() : -1;
            int i = this.extraBytes;
            if (i <= 0 || i > iRemaining) {
                return;
            }
            this.extras.clear();
            this.extras.put(byteBuffer);
            this.extras.flip();
        }

        public String toString() {
            return String.format(Locale.US, "Beacon(%s,port=%d,extra=%d)", this.uuid.toString(), Integer.valueOf(this.listenPort), Integer.valueOf(this.extraBytes));
        }
    }

    public UdpBeacon(UdpBeaconCallback udpBeaconCallback) {
        this(udpBeaconCallback, BEACON_UDP_PORT, DEFAULT_BEACON_SEND_INTERVALS_MS, false, 0L);
    }

    public UdpBeacon(UdpBeaconCallback udpBeaconCallback, long j) {
        this(udpBeaconCallback, BEACON_UDP_PORT, j, false, 0L);
    }

    public UdpBeacon(UdpBeaconCallback udpBeaconCallback, boolean z) {
        this(udpBeaconCallback, BEACON_UDP_PORT, DEFAULT_BEACON_SEND_INTERVALS_MS, false, 0L);
    }

    public UdpBeacon(UdpBeaconCallback udpBeaconCallback, boolean z, long j) {
        this(udpBeaconCallback, BEACON_UDP_PORT, DEFAULT_BEACON_SEND_INTERVALS_MS, false, j);
    }

    public UdpBeacon(UdpBeaconCallback udpBeaconCallback, long j, boolean z) {
        this(udpBeaconCallback, BEACON_UDP_PORT, j, z, 0L);
    }

    public UdpBeacon(UdpBeaconCallback udpBeaconCallback, long j, boolean z, long j2) {
        this(udpBeaconCallback, BEACON_UDP_PORT, j, z, j2);
    }

    public UdpBeacon(UdpBeaconCallback udpBeaconCallback, int i, long j, boolean z, long j2) {
        this.mSync = new Object();
        this.mCallbacks = new CopyOnWriteArraySet<>();
        this.mBeaconTask = new Runnable() { // from class: com.serenegiant.net.UdpBeacon.2
            /* JADX WARN: Can't wrap try/catch for region: R(11:0|2|(2:48|3)|(7:8|(3:59|10|(3:55|15|(2:57|17)(2:62|60))(4:56|14|63|60))(3:53|18|(1:58)(2:61|60))|34|b5|38|4|42)|52|20|46|21|34|b5|(1:(0))) */
            /* JADX WARN: Code restructure failed: missing block: B:23:0x0089, code lost:
            
                r0 = move-exception;
             */
            /* JADX WARN: Code restructure failed: missing block: B:24:0x008a, code lost:
            
                android.util.Log.w(com.serenegiant.net.UdpBeacon.TAG, r0);
             */
            /* JADX WARN: Finally extract failed */
            /* JADX WARN: Removed duplicated region for block: B:49:0x00b6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void run() {
                /*
                    r11 = this;
                    r0 = 256(0x100, float:3.59E-43)
                    java.nio.ByteBuffer.allocateDirect(r0)
                    r1 = 0
                    r2 = 0
                    com.serenegiant.net.UdpSocket r3 = new com.serenegiant.net.UdpSocket     // Catch: java.lang.Exception -> La4
                    r4 = 9999(0x270f, float:1.4012E-41)
                    r3.<init>(r4)     // Catch: java.lang.Exception -> La4
                    r3.setReceiveBufferSize(r0)     // Catch: java.lang.Exception -> La4
                    r0 = 1
                    r3.setReuseAddress(r0)     // Catch: java.lang.Exception -> La4
                    r0 = 2000(0x7d0, float:2.803E-42)
                    r3.setSoTimeout(r0)     // Catch: java.lang.Exception -> La4
                    java.lang.Thread r0 = new java.lang.Thread     // Catch: java.lang.Exception -> La4
                    com.serenegiant.net.UdpBeacon$ReceiverTask r4 = new com.serenegiant.net.UdpBeacon$ReceiverTask     // Catch: java.lang.Exception -> La4
                    com.serenegiant.net.UdpBeacon r5 = com.serenegiant.net.UdpBeacon.this     // Catch: java.lang.Exception -> La4
                    r4.<init>(r3)     // Catch: java.lang.Exception -> La4
                    r0.<init>(r4)     // Catch: java.lang.Exception -> La4
                    r0.start()     // Catch: java.lang.Exception -> La4
                    long r4 = android.os.SystemClock.elapsedRealtime()     // Catch: java.lang.Exception -> La4
                L2d:
                    com.serenegiant.net.UdpBeacon r6 = com.serenegiant.net.UdpBeacon.this     // Catch: java.lang.Throwable -> L90
                    boolean r6 = com.serenegiant.net.UdpBeacon.access$700(r6)     // Catch: java.lang.Throwable -> L90
                    if (r6 == 0) goto L7d
                    com.serenegiant.net.UdpBeacon r6 = com.serenegiant.net.UdpBeacon.this     // Catch: java.lang.Throwable -> L90
                    boolean r6 = com.serenegiant.net.UdpBeacon.access$100(r6)     // Catch: java.lang.Throwable -> L90
                    if (r6 != 0) goto L7d
                    com.serenegiant.net.UdpBeacon r6 = com.serenegiant.net.UdpBeacon.this     // Catch: java.lang.Throwable -> L90
                    boolean r6 = com.serenegiant.net.UdpBeacon.access$800(r6)     // Catch: java.lang.Throwable -> L90
                    if (r6 != 0) goto L73
                    long r6 = android.os.SystemClock.elapsedRealtime()     // Catch: java.lang.Throwable -> L90
                    long r6 = r4 - r6
                    com.serenegiant.net.UdpBeacon r8 = com.serenegiant.net.UdpBeacon.this     // Catch: java.lang.Throwable -> L90
                    boolean r8 = com.serenegiant.net.UdpBeacon.access$800(r8)     // Catch: java.lang.Throwable -> L90
                    if (r8 != 0) goto L6a
                    r8 = 0
                    int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
                    if (r10 > 0) goto L6a
                    long r4 = android.os.SystemClock.elapsedRealtime()     // Catch: java.lang.Throwable -> L90
                    com.serenegiant.net.UdpBeacon r6 = com.serenegiant.net.UdpBeacon.this     // Catch: java.lang.Throwable -> L90
                    long r6 = com.serenegiant.net.UdpBeacon.access$300(r6)     // Catch: java.lang.Throwable -> L90
                    long r4 = r4 + r6
                    com.serenegiant.net.UdpBeacon r6 = com.serenegiant.net.UdpBeacon.this     // Catch: java.lang.Throwable -> L90
                    com.serenegiant.net.UdpBeacon.access$200(r6, r3)     // Catch: java.lang.Throwable -> L90
                    goto L2d
                L6a:
                    com.serenegiant.net.UdpBeacon r8 = com.serenegiant.net.UdpBeacon.this     // Catch: java.lang.Throwable -> L90
                    boolean r6 = com.serenegiant.net.UdpBeacon.access$400(r8, r11, r6)     // Catch: java.lang.Throwable -> L90
                    if (r6 == 0) goto L2d
                    goto L7d
                L73:
                    com.serenegiant.net.UdpBeacon r6 = com.serenegiant.net.UdpBeacon.this     // Catch: java.lang.Throwable -> L90
                    r7 = 2000(0x7d0, double:9.88E-321)
                    boolean r6 = com.serenegiant.net.UdpBeacon.access$400(r6, r11, r7)     // Catch: java.lang.Throwable -> L90
                    if (r6 == 0) goto L2d
                L7d:
                    com.serenegiant.net.UdpBeacon r4 = com.serenegiant.net.UdpBeacon.this     // Catch: java.lang.Exception -> La4
                    com.serenegiant.net.UdpBeacon.access$702(r4, r2)     // Catch: java.lang.Exception -> La4
                    r3.release()     // Catch: java.lang.Exception -> La4
                    r0.interrupt()     // Catch: java.lang.Exception -> L89
                    goto Laa
                L89:
                    r0 = move-exception
                    java.lang.String r3 = "UdpBeacon"
                    android.util.Log.w(r3, r0)     // Catch: java.lang.Exception -> La4
                    goto Laa
                L90:
                    r4 = move-exception
                    com.serenegiant.net.UdpBeacon r5 = com.serenegiant.net.UdpBeacon.this     // Catch: java.lang.Exception -> La4
                    com.serenegiant.net.UdpBeacon.access$702(r5, r2)     // Catch: java.lang.Exception -> La4
                    r3.release()     // Catch: java.lang.Exception -> La4
                    r0.interrupt()     // Catch: java.lang.Exception -> L9d
                    goto La3
                L9d:
                    r0 = move-exception
                    java.lang.String r3 = "UdpBeacon"
                    android.util.Log.w(r3, r0)     // Catch: java.lang.Exception -> La4
                La3:
                    throw r4     // Catch: java.lang.Exception -> La4
                La4:
                    r0 = move-exception
                    com.serenegiant.net.UdpBeacon r3 = com.serenegiant.net.UdpBeacon.this
                    com.serenegiant.net.UdpBeacon.access$500(r3, r0)
                Laa:
                    com.serenegiant.net.UdpBeacon r0 = com.serenegiant.net.UdpBeacon.this
                    com.serenegiant.net.UdpBeacon.access$702(r0, r2)
                    com.serenegiant.net.UdpBeacon r0 = com.serenegiant.net.UdpBeacon.this
                    java.lang.Object r0 = com.serenegiant.net.UdpBeacon.access$900(r0)
                    monitor-enter(r0)
                    com.serenegiant.net.UdpBeacon r2 = com.serenegiant.net.UdpBeacon.this     // Catch: java.lang.Throwable -> Lbd
                    com.serenegiant.net.UdpBeacon.access$1002(r2, r1)     // Catch: java.lang.Throwable -> Lbd
                    monitor-exit(r0)     // Catch: java.lang.Throwable -> Lbd
                    return
                Lbd:
                    r1 = move-exception
                    monitor-exit(r0)     // Catch: java.lang.Throwable -> Lbd
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.serenegiant.net.UdpBeacon.RunnableC18632.run():void");
            }
        };
        if (udpBeaconCallback != null) {
            this.mCallbacks.add(udpBeaconCallback);
        }
        this.mAsyncHandler = HandlerThreadHandler.createHandler("UdpBeaconAsync");
        UUID uuidRandomUUID = UUID.randomUUID();
        this.uuid = uuidRandomUUID;
        this.beaconBytes = new Beacon(uuidRandomUUID, i).asBytes();
        this.mBeaconIntervalsMs = j;
        this.mReceiveOnly = z;
        this.mRcvMinIntervalsMs = j2;
    }

    public void finalize() {
        try {
            release();
        } finally {
            super.finalize();
        }
    }

    public void release() {
        if (this.mReleased) {
            return;
        }
        this.mReleased = true;
        stop();
        this.mCallbacks.clear();
        synchronized (this.mSync) {
            if (this.mAsyncHandler != null) {
                try {
                    this.mAsyncHandler.getLooper().quit();
                } catch (Exception unused) {
                }
                this.mAsyncHandler = null;
            }
        }
    }

    public void addCallback(UdpBeaconCallback udpBeaconCallback) {
        if (udpBeaconCallback != null) {
            this.mCallbacks.add(udpBeaconCallback);
        }
    }

    public void removeCallback(UdpBeaconCallback udpBeaconCallback) {
        this.mCallbacks.remove(udpBeaconCallback);
    }

    public void start() {
        checkReleased();
        synchronized (this.mSync) {
            if (this.mBeaconThread == null) {
                this.mIsRunning = true;
                Thread thread = new Thread(this.mBeaconTask, "UdpBeaconTask");
                this.mBeaconThread = thread;
                thread.start();
            }
        }
    }

    public void stop() {
        Thread thread;
        this.mIsRunning = false;
        synchronized (this.mSync) {
            thread = this.mBeaconThread;
            this.mBeaconThread = null;
            this.mSync.notifyAll();
        }
        if (thread == null || !thread.isAlive()) {
            return;
        }
        thread.interrupt();
        try {
            thread.join();
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }
    }

    public void shot() throws IllegalStateException {
        shot(1);
    }

    public void shot(int i) throws IllegalStateException {
        checkReleased();
        synchronized (this.mSync) {
            new Thread(new BeaconShotTask(i), "UdpOneShotBeaconTask").start();
        }
    }

    public boolean isActive() {
        return this.mIsRunning;
    }

    public void setReceiveOnly(boolean z) throws IllegalStateException {
        checkReleased();
        synchronized (this.mSync) {
            if (this.mIsRunning) {
                throw new IllegalStateException("beacon is already active");
            }
            this.mReceiveOnly = z;
        }
    }

    public boolean isReceiveOnly() {
        return this.mReceiveOnly;
    }

    private void checkReleased() throws IllegalStateException {
        if (this.mReleased) {
            throw new IllegalStateException("already released");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void callOnError(final Exception exc) {
        if (this.mReleased) {
            Log.w(TAG, exc);
            return;
        }
        synchronized (this.mSync) {
            if (this.mAsyncHandler != null) {
                this.mAsyncHandler.post(new Runnable() { // from class: com.serenegiant.net.UdpBeacon.1
                    @Override // java.lang.Runnable
                    public void run() {
                        for (UdpBeaconCallback udpBeaconCallback : UdpBeacon.this.mCallbacks) {
                            try {
                                udpBeaconCallback.onError(exc);
                            } catch (Exception e) {
                                UdpBeacon.this.mCallbacks.remove(udpBeaconCallback);
                                Log.w(UdpBeacon.TAG, e);
                            }
                        }
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean waitWithoutException(Object obj, long j) {
        boolean z;
        synchronized (obj) {
            try {
                obj.wait(j);
                z = false;
            } catch (InterruptedException unused) {
                z = true;
            }
        }
        return z;
    }

    private final class BeaconShotTask implements Runnable {
        private final int shotNums;

        public BeaconShotTask(int i) {
            this.shotNums = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                UdpSocket udpSocket = new UdpSocket(UdpBeacon.BEACON_UDP_PORT);
                udpSocket.setReuseAddress(true);
                udpSocket.setSoTimeout(2000);
                for (int i = 0; i < this.shotNums && !UdpBeacon.this.mReleased; i++) {
                    try {
                        UdpBeacon.this.sendBeacon(udpSocket);
                        if (UdpBeacon.this.waitWithoutException(this, UdpBeacon.this.mBeaconIntervalsMs)) {
                            break;
                        }
                    } catch (Throwable th) {
                        udpSocket.release();
                        throw th;
                    }
                }
                udpSocket.release();
            } catch (SocketException e) {
                UdpBeacon.this.callOnError(e);
            }
        }
    }

    private class ReceiverTask implements Runnable {
        private final UdpSocket mUdpSocket;

        private ReceiverTask(UdpSocket udpSocket) {
            this.mUdpSocket = udpSocket;
        }

        @Override // java.lang.Runnable
        public void run() {
            int iReceive;
            ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(256);
            UdpSocket udpSocket = this.mUdpSocket;
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            while (UdpBeacon.this.mIsRunning && !UdpBeacon.this.mReleased) {
                if (UdpBeacon.this.mRcvMinIntervalsMs > 0) {
                    long jElapsedRealtime2 = jElapsedRealtime - SystemClock.elapsedRealtime();
                    if (jElapsedRealtime2 > 0 && UdpBeacon.this.waitWithoutException(this, jElapsedRealtime2)) {
                        return;
                    } else {
                        jElapsedRealtime = SystemClock.elapsedRealtime() + UdpBeacon.this.mRcvMinIntervalsMs;
                    }
                }
                try {
                    try {
                        byteBufferAllocateDirect.clear();
                        iReceive = udpSocket.receive(byteBufferAllocateDirect);
                    } catch (IOException unused) {
                    } catch (Exception e) {
                        Log.w(UdpBeacon.TAG, e);
                        return;
                    }
                } catch (IllegalStateException | ClosedChannelException unused2) {
                    return;
                }
                if (!UdpBeacon.this.mIsRunning) {
                    return;
                }
                byteBufferAllocateDirect.rewind();
                if (iReceive == 23 && byteBufferAllocateDirect.get() == 83 && byteBufferAllocateDirect.get() == 65 && byteBufferAllocateDirect.get() == 75 && byteBufferAllocateDirect.get() == 73 && byteBufferAllocateDirect.get() == 1) {
                    final Beacon beacon = new Beacon(byteBufferAllocateDirect);
                    if (!UdpBeacon.this.uuid.equals(beacon.uuid)) {
                        final String strRemote = udpSocket.remote();
                        final int iRemotePort = udpSocket.remotePort();
                        synchronized (UdpBeacon.this.mSync) {
                            if (UdpBeacon.this.mAsyncHandler == null) {
                                return;
                            } else {
                                UdpBeacon.this.mAsyncHandler.post(new Runnable() { // from class: com.serenegiant.net.UdpBeacon.ReceiverTask.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        for (UdpBeaconCallback udpBeaconCallback : UdpBeacon.this.mCallbacks) {
                                            try {
                                                udpBeaconCallback.onReceiveBeacon(beacon.uuid, strRemote, iRemotePort);
                                            } catch (Exception e2) {
                                                UdpBeacon.this.mCallbacks.remove(udpBeaconCallback);
                                                Log.w(UdpBeacon.TAG, e2);
                                            }
                                        }
                                    }
                                });
                            }
                            return;
                        }
                    }
                    continue;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendBeacon(UdpSocket udpSocket) {
        try {
            udpSocket.broadcast(this.beaconBytes);
        } catch (IOException e) {
            Log.w(TAG, e);
        }
    }
}
