package com.jieli.lib.p015dv.control.mssdp;

import android.text.TextUtils;
import com.jieli.lib.p015dv.control.utils.Dlog;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class Discovery {
    public static final String BROADCAST_IP = "255.255.255.255";
    public static final String BROADCAST_REPLY = "MSSDP_NOTIFY";
    public static final String DISCOVERY_MSG = "MSSDP_SEARCH ";
    public static final int DISCOVERY_PORT = 3889;

    /* JADX INFO: renamed from: a */
    private static final String f2137a = Discovery.class.getSimpleName();

    /* JADX INFO: renamed from: b */
    private static Discovery f2138b;

    /* JADX INFO: renamed from: c */
    private DatagramSocket f2139c;

    /* JADX INFO: renamed from: d */
    private InetAddress f2140d;

    /* JADX INFO: renamed from: e */
    private Set<OnDiscoveryListener> f2141e;

    /* JADX INFO: renamed from: f */
    private C1417a f2142f;

    /* JADX INFO: renamed from: j */
    private boolean f2146j;

    /* JADX INFO: renamed from: g */
    private int f2143g = DISCOVERY_PORT;

    /* JADX INFO: renamed from: h */
    private int f2144h = 50;

    /* JADX INFO: renamed from: i */
    private int f2145i = 3;

    /* JADX INFO: renamed from: l */
    private String f2148l = DISCOVERY_MSG;

    /* JADX INFO: renamed from: m */
    private String f2149m = BROADCAST_REPLY;

    /* JADX INFO: renamed from: k */
    private String f2147k = m1357d();

    public interface OnDiscoveryListener {
        void onDiscovery(String str, String str2);

        void onError(int i, String str);
    }

    public static Discovery newInstance() {
        if (f2138b == null) {
            synchronized (Discovery.class) {
                if (f2138b == null) {
                    f2138b = new Discovery();
                }
            }
        }
        return f2138b;
    }

    private Discovery() {
    }

    public void setBroadCastFlag(String str) {
        if (TextUtils.isEmpty(str) || str.equals(this.f2149m)) {
            return;
        }
        this.f2149m = str;
    }

    public void setInterval(int i) {
        if (i > 0) {
            this.f2144h = i;
        }
    }

    public int getInterval() {
        return this.f2144h;
    }

    public void setRepeatNumber(int i) {
        if (i > 0) {
            this.f2145i = i;
        }
    }

    public int getRepeatNumber() {
        return this.f2145i;
    }

    public void setFilter(boolean z) {
        this.f2146j = z;
    }

    public boolean isFilter() {
        return this.f2146j;
    }

    public boolean registerOnDiscoveryListener(OnDiscoveryListener onDiscoveryListener) {
        if (this.f2141e == null) {
            this.f2141e = new HashSet();
        }
        return this.f2141e.add(onDiscoveryListener);
    }

    public boolean unregisterOnDiscoveryListener(OnDiscoveryListener onDiscoveryListener) {
        Set<OnDiscoveryListener> set = this.f2141e;
        return set != null && set.remove(onDiscoveryListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public void m1352a(String str, String str2) {
        Set<OnDiscoveryListener> set = this.f2141e;
        if (set != null) {
            for (OnDiscoveryListener onDiscoveryListener : set) {
                if (onDiscoveryListener != null) {
                    onDiscoveryListener.onDiscovery(str, str2);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public void m1349a(int i, String str) {
        Set<OnDiscoveryListener> set = this.f2141e;
        if (set != null) {
            for (OnDiscoveryListener onDiscoveryListener : set) {
                if (onDiscoveryListener != null) {
                    onDiscoveryListener.onError(i, str);
                }
            }
        }
    }

    /* JADX INFO: renamed from: b */
    private void m1354b() {
        try {
            DatagramSocket datagramSocket = new DatagramSocket(this.f2143g);
            this.f2139c = datagramSocket;
            datagramSocket.setBroadcast(true);
            this.f2140d = InetAddress.getByName(this.f2147k);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: renamed from: c */
    private InetAddress m1356c() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = networkInterfaceNextElement.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddressNextElement = inetAddresses.nextElement();
                    if (!inetAddressNextElement.isLoopbackAddress() && (networkInterfaceNextElement.getDisplayName().contains("wlan0") || networkInterfaceNextElement.getDisplayName().contains("eth0") || networkInterfaceNextElement.getDisplayName().contains("ap0"))) {
                        Dlog.m1384i(f2137a, "Current IP Address:" + inetAddressNextElement);
                        return inetAddressNextElement;
                    }
                }
            }
            return null;
        } catch (SocketException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: renamed from: d */
    private String m1357d() {
        InetAddress broadcast;
        InetAddress inetAddressM1356c = m1356c();
        if (inetAddressM1356c == null) {
            return null;
        }
        try {
            for (InterfaceAddress interfaceAddress : NetworkInterface.getByInetAddress(inetAddressM1356c).getInterfaceAddresses()) {
                if (interfaceAddress != null && (broadcast = interfaceAddress.getBroadcast()) != null) {
                    String hostAddress = broadcast.getHostAddress();
                    Dlog.m1384i(f2137a, "myAddress=" + hostAddress);
                    return hostAddress;
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }

    public synchronized void doDiscovery() {
        doDiscovery(DISCOVERY_PORT, this.f2147k, DISCOVERY_MSG);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x002d A[Catch: all -> 0x006d, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0007, B:7:0x0009, B:9:0x000f, B:11:0x0017, B:12:0x0019, B:14:0x001f, B:16:0x0027, B:17:0x0029, B:19:0x002d, B:20:0x0030, B:22:0x0034, B:24:0x0038, B:26:0x0040, B:27:0x004c, B:30:0x0055, B:32:0x0059), top: B:39:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0054  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void doDiscovery(int r2, java.lang.String r3, java.lang.String r4) {
        /*
            r1 = this;
            monitor-enter(r1)
            if (r2 < 0) goto L9
            int r0 = r1.f2143g     // Catch: java.lang.Throwable -> L6d
            if (r0 == r2) goto L9
            r1.f2143g = r2     // Catch: java.lang.Throwable -> L6d
        L9:
            boolean r2 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Throwable -> L6d
            if (r2 != 0) goto L19
            java.lang.String r2 = r1.f2147k     // Catch: java.lang.Throwable -> L6d
            boolean r2 = r3.equals(r2)     // Catch: java.lang.Throwable -> L6d
            if (r2 != 0) goto L19
            r1.f2147k = r3     // Catch: java.lang.Throwable -> L6d
        L19:
            boolean r2 = android.text.TextUtils.isEmpty(r4)     // Catch: java.lang.Throwable -> L6d
            if (r2 != 0) goto L29
            java.lang.String r2 = r1.f2148l     // Catch: java.lang.Throwable -> L6d
            boolean r2 = r4.equals(r2)     // Catch: java.lang.Throwable -> L6d
            if (r2 != 0) goto L29
            r1.f2148l = r4     // Catch: java.lang.Throwable -> L6d
        L29:
            java.net.DatagramSocket r2 = r1.f2139c     // Catch: java.lang.Throwable -> L6d
            if (r2 != 0) goto L30
            r1.m1354b()     // Catch: java.lang.Throwable -> L6d
        L30:
            java.net.DatagramSocket r2 = r1.f2139c     // Catch: java.lang.Throwable -> L6d
            if (r2 == 0) goto L4c
            com.jieli.lib.dv.control.mssdp.Discovery$a r2 = r1.f2142f     // Catch: java.lang.Throwable -> L6d
            if (r2 == 0) goto L40
            com.jieli.lib.dv.control.mssdp.Discovery$a r2 = r1.f2142f     // Catch: java.lang.Throwable -> L6d
            boolean r2 = com.jieli.lib.p015dv.control.mssdp.Discovery.C1417a.m1359a(r2)     // Catch: java.lang.Throwable -> L6d
            if (r2 != 0) goto L4c
        L40:
            com.jieli.lib.dv.control.mssdp.Discovery$a r2 = new com.jieli.lib.dv.control.mssdp.Discovery$a     // Catch: java.lang.Throwable -> L6d
            java.net.DatagramSocket r3 = r1.f2139c     // Catch: java.lang.Throwable -> L6d
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L6d
            r1.f2142f = r2     // Catch: java.lang.Throwable -> L6d
            r2.start()     // Catch: java.lang.Throwable -> L6d
        L4c:
            java.lang.String r2 = r1.f2148l     // Catch: java.lang.Throwable -> L6d
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Throwable -> L6d
            if (r2 != 0) goto L6b
            r2 = 0
        L55:
            int r3 = r1.f2145i     // Catch: java.lang.Throwable -> L6d
            if (r2 >= r3) goto L6b
            java.lang.String r3 = r1.f2148l     // Catch: java.lang.Throwable -> L6d
            byte[] r3 = r3.getBytes()     // Catch: java.lang.Throwable -> L6d
            r1.m1353a(r3)     // Catch: java.lang.Throwable -> L6d
            int r3 = r1.f2144h     // Catch: java.lang.Throwable -> L6d
            long r3 = (long) r3     // Catch: java.lang.Throwable -> L6d
            android.os.SystemClock.sleep(r3)     // Catch: java.lang.Throwable -> L6d
            int r2 = r2 + 1
            goto L55
        L6b:
            monitor-exit(r1)
            return
        L6d:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jieli.lib.p015dv.control.mssdp.Discovery.doDiscovery(int, java.lang.String, java.lang.String):void");
    }

    /* JADX INFO: renamed from: a */
    private synchronized void m1353a(byte[] bArr) {
        if (bArr != null) {
            try {
                DatagramPacket datagramPacket = new DatagramPacket(bArr, bArr.length, this.f2140d, this.f2143g);
                if (this.f2139c != null) {
                    this.f2139c.send(datagramPacket);
                }
            } catch (IOException e) {
                e.printStackTrace();
                m1349a(e.hashCode(), e.getMessage());
            }
        }
    }

    public void release() {
        f2138b = null;
        DatagramSocket datagramSocket = this.f2139c;
        if (datagramSocket != null) {
            datagramSocket.close();
            this.f2139c = null;
        }
        C1417a c1417a = this.f2142f;
        if (c1417a != null) {
            c1417a.m1360b();
            this.f2142f = null;
        }
        this.f2140d = null;
        Set<OnDiscoveryListener> set = this.f2141e;
        if (set != null) {
            set.clear();
            this.f2141e = null;
        }
    }

    /* JADX INFO: renamed from: com.jieli.lib.dv.control.mssdp.Discovery$a */
    private class C1417a extends Thread {

        /* JADX INFO: renamed from: b */
        private boolean f2151b;

        /* JADX INFO: renamed from: c */
        private DatagramSocket f2152c;

        /* JADX INFO: renamed from: d */
        private Set<String> f2153d;

        /* JADX INFO: renamed from: e */
        private String f2154e;

        C1417a(DatagramSocket datagramSocket) {
            this.f2152c = datagramSocket;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: a */
        public boolean m1358a() {
            return this.f2151b;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: b */
        public void m1360b() {
            this.f2151b = false;
            interrupt();
        }

        @Override // java.lang.Thread
        public synchronized void start() {
            this.f2151b = true;
            this.f2153d = new HashSet();
            super.start();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            Dlog.m1384i(Discovery.f2137a, "ReceiverThread start...");
            while (this.f2151b) {
                try {
                    DatagramPacket datagramPacket = new DatagramPacket(new byte[20480], 20480);
                    if (this.f2152c != null) {
                        this.f2152c.receive(datagramPacket);
                    }
                    if (datagramPacket.getLength() > 0) {
                        String strTrim = new String(datagramPacket.getData()).trim();
                        if (!TextUtils.isEmpty(strTrim) && strTrim.startsWith(Discovery.this.f2149m) && datagramPacket.getAddress() != null) {
                            String hostAddress = datagramPacket.getAddress().getHostAddress();
                            String[] strArrSplit = strTrim.split(" ", 2);
                            String str = strArrSplit.length > 1 ? strArrSplit[1] : null;
                            if (!Discovery.this.f2146j) {
                                Discovery.this.m1352a(hostAddress, str);
                            } else if (this.f2153d.add(hostAddress)) {
                                this.f2154e = str;
                                Discovery.this.m1352a(hostAddress, str);
                            } else if (TextUtils.isEmpty(str) || str.equals(this.f2154e)) {
                                Dlog.m1386w(Discovery.f2137a, "Maybe data is repeat");
                            } else {
                                this.f2154e = str;
                                Discovery.this.m1352a(hostAddress, str);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    this.f2151b = false;
                    Discovery.this.m1349a(e.hashCode(), e.getMessage());
                }
            }
            this.f2153d.clear();
            this.f2151b = false;
            Dlog.m1384i(Discovery.f2137a, "ReceiverThread stop...");
            Discovery.this.f2142f = null;
        }
    }
}
