package com.jieli.stream.p016dv.running2.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class WifiP2pHelper {
    private static WifiP2pHelper instance;
    private WiFiDirectBroadcastReceiver mBroadcastReceiver;
    private WifiP2pManager.Channel mChannel;
    private WifiP2pDevice mConnectWifiDevice;
    private Context mMainContext;
    private WifiP2pManager mP2pManager;
    private Set<IWifiDirectListener> mWifiDirectListeners;
    private MyWifiHandler mWifiHandler;
    private List<WifiP2pDevice> peers = new ArrayList();
    private WifiP2pManager.PeerListListener peerListListener = new WifiP2pManager.PeerListListener() { // from class: com.jieli.stream.dv.running2.util.WifiP2pHelper.7
        @Override // android.net.wifi.p2p.WifiP2pManager.PeerListListener
        public void onPeersAvailable(WifiP2pDeviceList wifiP2pDeviceList) {
            WifiP2pHelper.this.peers.clear();
            if (wifiP2pDeviceList != null) {
                WifiP2pHelper.this.peers.addAll(wifiP2pDeviceList.getDeviceList());
                WifiP2pHelper wifiP2pHelper = WifiP2pHelper.this;
                wifiP2pHelper.notifyP2pPeerListChanged(wifiP2pHelper.peers);
            }
        }
    };
    private WifiP2pManager.ConnectionInfoListener mConnectionInfoListener = new WifiP2pManager.ConnectionInfoListener() { // from class: com.jieli.stream.dv.running2.util.WifiP2pHelper.8
        @Override // android.net.wifi.p2p.WifiP2pManager.ConnectionInfoListener
        public void onConnectionInfoAvailable(WifiP2pInfo wifiP2pInfo) {
            WifiP2pHelper.this.notifyP2pConnectionChanged(wifiP2pInfo);
        }
    };

    private WifiP2pHelper(Context context) {
        if (context == null) {
            throw new NullPointerException("Context can not be empty.");
        }
        this.mMainContext = context;
        WifiP2pManager wifiP2pManager = (WifiP2pManager) context.getSystemService("wifip2p");
        this.mP2pManager = wifiP2pManager;
        Context context2 = this.mMainContext;
        this.mChannel = wifiP2pManager.initialize(context2, context2.getMainLooper(), null);
        this.mWifiHandler = new MyWifiHandler(this.mMainContext.getMainLooper());
    }

    public static WifiP2pHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (WifiP2pHelper.class) {
                if (instance == null) {
                    instance = new WifiP2pHelper(context);
                }
            }
        }
        return instance;
    }

    public void registerBroadcastReceiver(IWifiDirectListener iWifiDirectListener) {
        if (this.mWifiDirectListeners == null) {
            this.mWifiDirectListeners = new HashSet();
        }
        this.mWifiDirectListeners.add(iWifiDirectListener);
        if (this.mBroadcastReceiver != null || this.mMainContext == null) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.p2p.STATE_CHANGED");
        intentFilter.addAction("android.net.wifi.p2p.PEERS_CHANGED");
        intentFilter.addAction("android.net.wifi.p2p.CONNECTION_STATE_CHANGE");
        intentFilter.addAction("android.net.wifi.p2p.THIS_DEVICE_CHANGED");
        WiFiDirectBroadcastReceiver wiFiDirectBroadcastReceiver = new WiFiDirectBroadcastReceiver();
        this.mBroadcastReceiver = wiFiDirectBroadcastReceiver;
        this.mMainContext.registerReceiver(wiFiDirectBroadcastReceiver, intentFilter);
    }

    public void unregisterBroadcastReceiver(IWifiDirectListener iWifiDirectListener) {
        WiFiDirectBroadcastReceiver wiFiDirectBroadcastReceiver;
        Set<IWifiDirectListener> set = this.mWifiDirectListeners;
        if (set != null) {
            set.remove(iWifiDirectListener);
        }
        Context context = this.mMainContext;
        if (context == null || (wiFiDirectBroadcastReceiver = this.mBroadcastReceiver) == null) {
            return;
        }
        context.unregisterReceiver(wiFiDirectBroadcastReceiver);
        this.mBroadcastReceiver = null;
    }

    public void startDiscoverPeers(WifiP2pManager.ActionListener actionListener) {
        WifiP2pManager.Channel channel;
        WifiP2pManager wifiP2pManager = this.mP2pManager;
        if (wifiP2pManager == null || (channel = this.mChannel) == null) {
            return;
        }
        wifiP2pManager.discoverPeers(channel, actionListener);
    }

    public void stopDiscoverPeers(WifiP2pManager.ActionListener actionListener) {
        WifiP2pManager.Channel channel;
        WifiP2pManager wifiP2pManager = this.mP2pManager;
        if (wifiP2pManager == null || (channel = this.mChannel) == null) {
            return;
        }
        wifiP2pManager.stopPeerDiscovery(channel, actionListener);
    }

    public void connectP2pDevice(WifiP2pDevice wifiP2pDevice, WifiP2pManager.ActionListener actionListener) {
        if (this.mP2pManager == null || wifiP2pDevice == null) {
            return;
        }
        WifiP2pConfig wifiP2pConfig = new WifiP2pConfig();
        wifiP2pConfig.deviceAddress = wifiP2pDevice.deviceAddress;
        wifiP2pConfig.wps.setup = 0;
        this.mP2pManager.connect(this.mChannel, wifiP2pConfig, actionListener);
    }

    public void connectP2pDeviceForGroup(final WifiP2pDevice wifiP2pDevice, final WifiP2pManager.ActionListener actionListener) {
        WifiP2pManager wifiP2pManager = this.mP2pManager;
        if (wifiP2pManager == null || wifiP2pDevice == null) {
            return;
        }
        wifiP2pManager.createGroup(this.mChannel, new WifiP2pManager.ActionListener() { // from class: com.jieli.stream.dv.running2.util.WifiP2pHelper.1
            @Override // android.net.wifi.p2p.WifiP2pManager.ActionListener
            public void onSuccess() {
                WifiP2pConfig wifiP2pConfig = new WifiP2pConfig();
                wifiP2pConfig.deviceAddress = wifiP2pDevice.deviceAddress;
                wifiP2pConfig.wps.setup = 0;
                WifiP2pHelper.this.mP2pManager.connect(WifiP2pHelper.this.mChannel, wifiP2pConfig, actionListener);
            }

            @Override // android.net.wifi.p2p.WifiP2pManager.ActionListener
            public void onFailure(int i) {
                WifiP2pManager.ActionListener actionListener2 = actionListener;
                if (actionListener2 != null) {
                    actionListener2.onFailure(i);
                }
            }
        });
    }

    public void disconnectP2pDevice(WifiP2pManager.ActionListener actionListener) {
        WifiP2pManager.Channel channel;
        WifiP2pManager wifiP2pManager = this.mP2pManager;
        if (wifiP2pManager == null || (channel = this.mChannel) == null) {
            return;
        }
        wifiP2pManager.cancelConnect(channel, actionListener);
    }

    public void disconnectP2pForGroup(final WifiP2pManager.ActionListener actionListener) {
        WifiP2pManager.Channel channel;
        WifiP2pManager wifiP2pManager = this.mP2pManager;
        if (wifiP2pManager == null || (channel = this.mChannel) == null) {
            return;
        }
        wifiP2pManager.removeGroup(channel, new WifiP2pManager.ActionListener() { // from class: com.jieli.stream.dv.running2.util.WifiP2pHelper.2
            @Override // android.net.wifi.p2p.WifiP2pManager.ActionListener
            public void onSuccess() {
                WifiP2pHelper.this.disconnectP2pDevice(actionListener);
            }

            @Override // android.net.wifi.p2p.WifiP2pManager.ActionListener
            public void onFailure(int i) {
                WifiP2pManager.ActionListener actionListener2 = actionListener;
                if (actionListener2 != null) {
                    actionListener2.onFailure(i);
                }
            }
        });
    }

    public List<WifiP2pDevice> getPeerList() {
        return this.peers;
    }

    public void requestConnectionInfo() {
        WifiP2pManager.Channel channel;
        WifiP2pManager wifiP2pManager = this.mP2pManager;
        if (wifiP2pManager == null || (channel = this.mChannel) == null) {
            return;
        }
        wifiP2pManager.requestConnectionInfo(channel, this.mConnectionInfoListener);
    }

    public void requestPeerList() {
        WifiP2pManager.Channel channel;
        WifiP2pManager wifiP2pManager = this.mP2pManager;
        if (wifiP2pManager == null || (channel = this.mChannel) == null) {
            return;
        }
        wifiP2pManager.requestPeers(channel, this.peerListListener);
    }

    public void setConnectWifiDevice(WifiP2pDevice wifiP2pDevice) {
        this.mConnectWifiDevice = wifiP2pDevice;
    }

    public WifiP2pDevice getConnectWifiDevice() {
        return this.mConnectWifiDevice;
    }

    public void release() {
        MyWifiHandler myWifiHandler = this.mWifiHandler;
        if (myWifiHandler != null) {
            myWifiHandler.removeCallbacksAndMessages(null);
        }
        stopDiscoverPeers(null);
        Set<IWifiDirectListener> set = this.mWifiDirectListeners;
        if (set != null) {
            set.clear();
        }
        List<WifiP2pDevice> list = this.peers;
        if (list != null) {
            list.clear();
        }
        this.mConnectWifiDevice = null;
        this.mMainContext = null;
        this.mP2pManager = null;
        this.mChannel = null;
    }

    private class WiFiDirectBroadcastReceiver extends BroadcastReceiver {
        private WiFiDirectBroadcastReceiver() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0040  */
        @Override // android.content.BroadcastReceiver
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onReceive(android.content.Context r6, android.content.Intent r7) {
            /*
                r5 = this;
                if (r7 == 0) goto La7
                java.lang.String r6 = r7.getAction()
                boolean r0 = android.text.TextUtils.isEmpty(r6)
                if (r0 != 0) goto La7
                int r0 = r6.hashCode()
                r1 = 3
                r2 = 2
                r3 = -1
                r4 = 1
                switch(r0) {
                    case -1772632330: goto L36;
                    case -1566767901: goto L2c;
                    case -1394739139: goto L22;
                    case 1695662461: goto L18;
                    default: goto L17;
                }
            L17:
                goto L40
            L18:
                java.lang.String r0 = "android.net.wifi.p2p.STATE_CHANGED"
                boolean r6 = r6.equals(r0)
                if (r6 == 0) goto L40
                r6 = 0
                goto L41
            L22:
                java.lang.String r0 = "android.net.wifi.p2p.PEERS_CHANGED"
                boolean r6 = r6.equals(r0)
                if (r6 == 0) goto L40
                r6 = 1
                goto L41
            L2c:
                java.lang.String r0 = "android.net.wifi.p2p.THIS_DEVICE_CHANGED"
                boolean r6 = r6.equals(r0)
                if (r6 == 0) goto L40
                r6 = 3
                goto L41
            L36:
                java.lang.String r0 = "android.net.wifi.p2p.CONNECTION_STATE_CHANGE"
                boolean r6 = r6.equals(r0)
                if (r6 == 0) goto L40
                r6 = 2
                goto L41
            L40:
                r6 = -1
            L41:
                if (r6 == 0) goto L8c
                if (r6 == r4) goto L86
                if (r6 == r2) goto L58
                if (r6 == r1) goto L4a
                goto La7
            L4a:
                java.lang.String r6 = "wifiP2pDevice"
                android.os.Parcelable r6 = r7.getParcelableExtra(r6)
                android.net.wifi.p2p.WifiP2pDevice r6 = (android.net.wifi.p2p.WifiP2pDevice) r6
                com.jieli.stream.dv.running2.util.WifiP2pHelper r7 = com.jieli.stream.p016dv.running2.util.WifiP2pHelper.this
                com.jieli.stream.p016dv.running2.util.WifiP2pHelper.access$700(r7, r6)
                goto La7
            L58:
                com.jieli.stream.dv.running2.util.WifiP2pHelper r6 = com.jieli.stream.p016dv.running2.util.WifiP2pHelper.this
                android.net.wifi.p2p.WifiP2pManager r6 = com.jieli.stream.p016dv.running2.util.WifiP2pHelper.access$300(r6)
                if (r6 == 0) goto La7
                java.lang.String r6 = "networkInfo"
                android.os.Parcelable r6 = r7.getParcelableExtra(r6)
                android.net.NetworkInfo r6 = (android.net.NetworkInfo) r6
                if (r6 == 0) goto La7
                boolean r6 = r6.isConnected()
                if (r6 == 0) goto La7
                com.jieli.stream.dv.running2.util.WifiP2pHelper r6 = com.jieli.stream.p016dv.running2.util.WifiP2pHelper.this
                android.net.wifi.p2p.WifiP2pManager r6 = com.jieli.stream.p016dv.running2.util.WifiP2pHelper.access$300(r6)
                com.jieli.stream.dv.running2.util.WifiP2pHelper r7 = com.jieli.stream.p016dv.running2.util.WifiP2pHelper.this
                android.net.wifi.p2p.WifiP2pManager$Channel r7 = com.jieli.stream.p016dv.running2.util.WifiP2pHelper.access$200(r7)
                com.jieli.stream.dv.running2.util.WifiP2pHelper r0 = com.jieli.stream.p016dv.running2.util.WifiP2pHelper.this
                android.net.wifi.p2p.WifiP2pManager$ConnectionInfoListener r0 = com.jieli.stream.p016dv.running2.util.WifiP2pHelper.access$600(r0)
                r6.requestConnectionInfo(r7, r0)
                goto La7
            L86:
                com.jieli.stream.dv.running2.util.WifiP2pHelper r6 = com.jieli.stream.p016dv.running2.util.WifiP2pHelper.this
                r6.requestPeerList()
                goto La7
            L8c:
                java.lang.String r6 = "wifi_p2p_state"
                int r6 = r7.getIntExtra(r6, r3)
                com.jieli.stream.dv.running2.util.WifiP2pHelper r7 = com.jieli.stream.p016dv.running2.util.WifiP2pHelper.this
                com.jieli.stream.p016dv.running2.util.WifiP2pHelper.access$400(r7, r6)
                if (r6 != r4) goto La7
                com.jieli.stream.dv.running2.util.WifiP2pHelper r6 = com.jieli.stream.p016dv.running2.util.WifiP2pHelper.this
                android.net.wifi.p2p.WifiP2pDevice r6 = com.jieli.stream.p016dv.running2.util.WifiP2pHelper.access$500(r6)
                if (r6 == 0) goto La7
                com.jieli.stream.dv.running2.util.WifiP2pHelper r6 = com.jieli.stream.p016dv.running2.util.WifiP2pHelper.this
                r7 = 0
                com.jieli.stream.p016dv.running2.util.WifiP2pHelper.access$502(r6, r7)
            La7:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jieli.stream.dv.running2.util.WifiP2pHelper.WiFiDirectBroadcastReceiver.onReceive(android.content.Context, android.content.Intent):void");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyP2pStateChanged(final int i) {
        MyWifiHandler myWifiHandler = this.mWifiHandler;
        if (myWifiHandler == null || this.mWifiDirectListeners == null) {
            return;
        }
        myWifiHandler.post(new Runnable() { // from class: com.jieli.stream.dv.running2.util.WifiP2pHelper.3
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = WifiP2pHelper.this.mWifiDirectListeners.iterator();
                while (it.hasNext()) {
                    ((IWifiDirectListener) it.next()).onCallP2pStateChanged(i);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyP2pPeerListChanged(final List<WifiP2pDevice> list) {
        MyWifiHandler myWifiHandler = this.mWifiHandler;
        if (myWifiHandler == null || this.mWifiDirectListeners == null) {
            return;
        }
        myWifiHandler.post(new Runnable() { // from class: com.jieli.stream.dv.running2.util.WifiP2pHelper.4
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = WifiP2pHelper.this.mWifiDirectListeners.iterator();
                while (it.hasNext()) {
                    ((IWifiDirectListener) it.next()).onCallP2pPeersChanged(list);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyP2pConnectionChanged(final WifiP2pInfo wifiP2pInfo) {
        MyWifiHandler myWifiHandler = this.mWifiHandler;
        if (myWifiHandler == null || this.mWifiDirectListeners == null) {
            return;
        }
        myWifiHandler.post(new Runnable() { // from class: com.jieli.stream.dv.running2.util.WifiP2pHelper.5
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = WifiP2pHelper.this.mWifiDirectListeners.iterator();
                while (it.hasNext()) {
                    ((IWifiDirectListener) it.next()).onCallP2pConnectionChanged(wifiP2pInfo);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyP2pDeviceChanged(final WifiP2pDevice wifiP2pDevice) {
        MyWifiHandler myWifiHandler = this.mWifiHandler;
        if (myWifiHandler == null || this.mWifiDirectListeners == null) {
            return;
        }
        myWifiHandler.post(new Runnable() { // from class: com.jieli.stream.dv.running2.util.WifiP2pHelper.6
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = WifiP2pHelper.this.mWifiDirectListeners.iterator();
                while (it.hasNext()) {
                    ((IWifiDirectListener) it.next()).onCallP2pDeviceChanged(wifiP2pDevice);
                }
            }
        });
    }

    private class MyWifiHandler extends Handler {
        private MyWifiHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
        }
    }
}
