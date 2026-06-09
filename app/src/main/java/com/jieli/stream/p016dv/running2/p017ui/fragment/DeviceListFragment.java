package com.jieli.stream.p016dv.running2.p017ui.fragment;

import android.content.Intent;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.jieli.stream.p016dv.running2.C1438R;
import com.jieli.stream.p016dv.running2.p017ui.activity.MainActivity;
import com.jieli.stream.p016dv.running2.p017ui.base.BaseFragment;
import com.jieli.stream.p016dv.running2.util.ClientManager;
import com.jieli.stream.p016dv.running2.util.Dbug;
import com.jieli.stream.p016dv.running2.util.IConstant;
import com.jieli.stream.p016dv.running2.util.IWifiDirectListener;
import com.jieli.stream.p016dv.running2.util.PreferencesHelper;
import com.jieli.stream.p016dv.running2.util.ToastUtil;
import com.jieli.stream.p016dv.running2.util.WifiHelper;
import com.jieli.stream.p016dv.running2.util.WifiP2pHelper;
import java.net.InetAddress;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DeviceListFragment extends BaseFragment implements View.OnClickListener {
    private static String tag = DeviceListFragment.class.getSimpleName();
    private IWifiDirectListener mIWifiDirectListener = new IWifiDirectListener() { // from class: com.jieli.stream.dv.running2.ui.fragment.DeviceListFragment.2
        @Override // com.jieli.stream.p016dv.running2.util.IWifiDirectListener
        public void onCallP2pDeviceChanged(WifiP2pDevice wifiP2pDevice) {
        }

        @Override // com.jieli.stream.p016dv.running2.util.IWifiDirectListener
        public void onCallP2pStateChanged(int i) {
            Dbug.m1389i(DeviceListFragment.tag, "-onCallP2pStateChanged- state : " + i);
        }

        @Override // com.jieli.stream.p016dv.running2.util.IWifiDirectListener
        public void onCallP2pPeersChanged(List<WifiP2pDevice> list) {
            if (list == null || DeviceListFragment.this.mApplication.getSearchMode() != 0) {
                return;
            }
            Dbug.m1389i(DeviceListFragment.tag, "-onCallP2pPeersChanged- size : " + list.size());
            if (list.size() > 0) {
                WifiP2pDevice wifiP2pDevice = null;
                for (WifiP2pDevice wifiP2pDevice2 : list) {
                    Dbug.m1389i(DeviceListFragment.tag, "-onCallP2pPeersChanged- device : " + wifiP2pDevice2 + ", isGroupOwner : " + wifiP2pDevice2.isGroupOwner());
                    if ((!wifiP2pDevice2.isGroupOwner() && wifiP2pDevice2.status != 3) || wifiP2pDevice2.isGroupOwner() || wifiP2pDevice2.status == 0) {
                        wifiP2pDevice = wifiP2pDevice2;
                        break;
                    }
                }
                if (wifiP2pDevice != null) {
                    String str = wifiP2pDevice.deviceName;
                    if (TextUtils.isEmpty(str) || !str.startsWith(IConstant.WIFI_PREFIX)) {
                        return;
                    }
                    WifiP2pHelper.getInstance(DeviceListFragment.this.getContext()).setConnectWifiDevice(wifiP2pDevice);
                    if (ClientManager.getClient().isConnected()) {
                        DeviceListFragment.this.enterLiveVideo();
                        return;
                    }
                    PreferencesHelper.putStringValue(DeviceListFragment.this.mApplication, IConstant.CURRENT_WIFI_SSID, str);
                    PreferencesHelper.putIntValue(DeviceListFragment.this.mApplication, IConstant.RECONNECT_TYPE, 1);
                    if (!DeviceListFragment.this.mApplication.isWifiDirectGO()) {
                        Dbug.m1388e(DeviceListFragment.tag, "It isn't WiFi direct go");
                    } else {
                        WifiP2pHelper.getInstance(DeviceListFragment.this.getContext()).requestConnectionInfo();
                    }
                }
            }
        }

        @Override // com.jieli.stream.p016dv.running2.util.IWifiDirectListener
        public void onCallP2pConnectionChanged(WifiP2pInfo wifiP2pInfo) {
            InetAddress inetAddress;
            if (wifiP2pInfo != null) {
                Dbug.m1389i(DeviceListFragment.tag, "-onCallP2pConnectionChanged- : " + wifiP2pInfo);
                if (DeviceListFragment.this.mApplication.getSearchMode() == 0 && DeviceListFragment.this.mApplication.isWifiDirectGO() && wifiP2pInfo.groupFormed && (inetAddress = wifiP2pInfo.groupOwnerAddress) != null) {
                    if (ClientManager.getClient().isConnected()) {
                        DeviceListFragment.this.enterLiveVideo();
                    } else {
                        ((MainActivity) DeviceListFragment.this.getActivity()).connectDevice(inetAddress.getHostAddress());
                    }
                }
            }
        }
    };

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(C1438R.layout.fragment_device_list, viewGroup, false);
        ((ImageView) viewInflate.findViewById(C1438R.id.device_list_switch_search_mode)).setOnClickListener(this);
        ((TextView) viewInflate.findViewById(C1438R.id.go_to_wifi)).setOnClickListener(this);
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        Bundle arguments;
        super.onActivityCreated(bundle);
        if (getActivity() == null || (arguments = getArguments()) == null) {
            return;
        }
        int i = arguments.getInt(IConstant.KEY_SEARCH_MODE, 0);
        this.mApplication.setSearchMode(i);
        Dbug.m1388e(tag, "onActivityCreated: mode=" + i);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        Dbug.m1388e(tag, "Current mode=" + this.mApplication.getSearchMode());
        if (this.mApplication.getSearchMode() == 0) {
            WifiP2pHelper.getInstance(this.mApplication).registerBroadcastReceiver(this.mIWifiDirectListener);
            Dbug.m1389i(tag, "registerBroadcastReceiver");
            WifiP2pHelper.getInstance(this.mApplication).requestPeerList();
            Dbug.m1389i(tag, "requestPeerList");
            WifiP2pHelper.getInstance(this.mApplication).startDiscoverPeers(new WifiP2pManager.ActionListener() { // from class: com.jieli.stream.dv.running2.ui.fragment.DeviceListFragment.1
                @Override // android.net.wifi.p2p.WifiP2pManager.ActionListener
                public void onSuccess() {
                    Dbug.m1389i(DeviceListFragment.tag, "start discovery wifi direct list");
                }

                @Override // android.net.wifi.p2p.WifiP2pManager.ActionListener
                public void onFailure(int i) {
                    Dbug.m1388e(DeviceListFragment.tag, "start discovery error, code : " + i);
                }
            });
        }
        if (WifiHelper.getInstance(getContext()).isWifiClosed()) {
            ToastUtil.showToastShort(getString(C1438R.string.tip_open_wifi));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        WifiP2pHelper.getInstance(this.mApplication).stopDiscoverPeers(null);
        WifiP2pHelper.getInstance(getContext()).unregisterBroadcastReceiver(this.mIWifiDirectListener);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null || getActivity() == null) {
            return;
        }
        int id = view.getId();
        if (id == C1438R.id.device_list_switch_search_mode) {
            toQrCodeFragment();
        } else if (id == C1438R.id.go_to_wifi) {
            enterWiFiSettings();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enterLiveVideo() {
        Fragment videoFragment = (BaseFragment) getActivity().getSupportFragmentManager().findFragmentByTag(VideoFragment.class.getSimpleName());
        if (videoFragment == null) {
            videoFragment = new VideoFragment();
        }
        ((MainActivity) getActivity()).changeFragment(C1438R.id.container, videoFragment, videoFragment.getClass().getSimpleName());
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void toQrCodeFragment() {
        Fragment fragmentFindFragmentById = getActivity().getSupportFragmentManager().findFragmentById(C1438R.id.container);
        if (!(fragmentFindFragmentById instanceof QRCodeFragment)) {
            fragmentFindFragmentById = new QRCodeFragment();
        }
        ((MainActivity) getActivity()).changeFragment(C1438R.id.container, fragmentFindFragmentById, fragmentFindFragmentById.getClass().getSimpleName());
    }

    private void enterWiFiSettings() {
        try {
            Intent intent = new Intent("android.settings.WIFI_SETTINGS");
            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(intent);
            } else {
                ToastUtil.showToastLong(getString(C1438R.string.go_to_wifi_settings));
            }
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtil.showToastLong(getString(C1438R.string.go_to_wifi_settings));
        }
    }
}
