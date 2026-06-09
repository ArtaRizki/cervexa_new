package com.jieli.stream.p016dv.running2.p017ui.fragment;

import android.R;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.p2p.WifiP2pDevice;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.jieli.lib.p015dv.control.mssdp.Discovery;
import com.jieli.stream.p016dv.running2.C1438R;
import com.jieli.stream.p016dv.running2.bean.DeviceBean;
import com.jieli.stream.p016dv.running2.p017ui.MainApplication;
import com.jieli.stream.p016dv.running2.p017ui.activity.MainActivity;
import com.jieli.stream.p016dv.running2.p017ui.base.BaseFragment;
import com.jieli.stream.p016dv.running2.p017ui.dialog.SelectWifiDialog;
import com.jieli.stream.p016dv.running2.util.ClientManager;
import com.jieli.stream.p016dv.running2.util.Dbug;
import com.jieli.stream.p016dv.running2.util.ToastUtil;
import com.jieli.stream.p016dv.running2.util.WifiHelper;
import com.jieli.stream.p016dv.running2.util.WifiP2pHelper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class StaDeviceListFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private static final int MSG_ADD_NEW_DEVICE = 1112;
    private static final int MSG_SEARCH_DEVICE_LIST = 1111;
    private static String tag = StaDeviceListFragment.class.getSimpleName();
    private ListView deviceListView;
    private DeviceListAdapter mAdapter;
    private SearchStaDevice mSearchStaDevice;
    private SelectWifiDialog mSelectWifiDialog;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TextView tvPullToRefresh;
    private boolean isRefreshing = false;
    private List<String> mStationList = new ArrayList();
    private final Handler mHandler = new Handler(new Handler.Callback() { // from class: com.jieli.stream.dv.running2.ui.fragment.StaDeviceListFragment.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message == null || StaDeviceListFragment.this.isDetached()) {
                return false;
            }
            int i = message.what;
            if (i == StaDeviceListFragment.MSG_SEARCH_DEVICE_LIST) {
                StaDeviceListFragment.this.refreshList();
                return false;
            }
            if (i != StaDeviceListFragment.MSG_ADD_NEW_DEVICE) {
                return false;
            }
            DeviceBean deviceBean = (DeviceBean) message.obj;
            Dbug.m1388e(StaDeviceListFragment.tag, "MSG_ADD_NEW_DEVICE=" + deviceBean);
            if (deviceBean == null) {
                return false;
            }
            String wifiSSID = deviceBean.getWifiSSID();
            if (StaDeviceListFragment.this.mAdapter == null || StaDeviceListFragment.this.mStationList.contains(wifiSSID)) {
                return false;
            }
            StaDeviceListFragment.this.mStationList.add(wifiSSID);
            StaDeviceListFragment.this.mAdapter.add(deviceBean);
            StaDeviceListFragment.this.mAdapter.notifyDataSetChanged();
            return false;
        }
    });
    private final SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() { // from class: com.jieli.stream.dv.running2.ui.fragment.StaDeviceListFragment.2
        @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
        public void onRefresh() {
            if (StaDeviceListFragment.this.tvPullToRefresh.getVisibility() == 0) {
                StaDeviceListFragment.this.tvPullToRefresh.setVisibility(8);
            }
            if (StaDeviceListFragment.this.isRefreshing) {
                return;
            }
            StaDeviceListFragment.this.isRefreshing = true;
            WifiP2pHelper.getInstance(StaDeviceListFragment.this.getContext()).requestPeerList();
            StaDeviceListFragment.this.mHandler.sendEmptyMessage(StaDeviceListFragment.MSG_SEARCH_DEVICE_LIST);
            StaDeviceListFragment.this.mHandler.postDelayed(new Runnable() { // from class: com.jieli.stream.dv.running2.ui.fragment.StaDeviceListFragment.2.1
                @Override // java.lang.Runnable
                public void run() {
                    StaDeviceListFragment.this.isRefreshing = false;
                    StaDeviceListFragment.this.mSwipeRefreshLayout.setRefreshing(false);
                }
            }, 1000L);
        }
    };

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(C1438R.layout.sta_device_list_fragment, viewGroup, false);
        ImageView imageView = (ImageView) viewInflate.findViewById(C1438R.id.device_list_switch_search_mode);
        this.deviceListView = (ListView) viewInflate.findViewById(R.id.list);
        this.mSwipeRefreshLayout = (SwipeRefreshLayout) viewInflate.findViewById(C1438R.id.swipe_refresh);
        this.tvPullToRefresh = (TextView) viewInflate.findViewById(C1438R.id.pull_to_refresh);
        imageView.setOnClickListener(this);
        this.deviceListView.setOnItemClickListener(this);
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getActivity() == null) {
            return;
        }
        this.mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.black), getResources().getColor(R.color.darker_gray), getResources().getColor(R.color.black), getResources().getColor(R.color.background_light));
        this.mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(-1);
        this.mSwipeRefreshLayout.setSize(1);
        this.mSwipeRefreshLayout.setOnRefreshListener(this.onRefreshListener);
        if (this.mAdapter == null) {
            this.mAdapter = new DeviceListAdapter(getActivity(), C1438R.layout.item_device_list);
        }
        this.deviceListView.setAdapter((ListAdapter) this.mAdapter);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        Dbug.m1389i(tag, "onResume: current mode=" + this.mApplication.getSearchMode());
        if (WifiHelper.getInstance(getContext()).isWifiClosed()) {
            ToastUtil.showToastShort(getString(C1438R.string.tip_open_wifi));
            this.mAdapter.clear();
            this.mStationList.clear();
            this.mAdapter.notifyDataSetChanged();
        }
        List<String> list = this.mStationList;
        if (list == null || list.size() <= 0 || this.tvPullToRefresh.getVisibility() != 0) {
            return;
        }
        this.tvPullToRefresh.setVisibility(8);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        WifiP2pHelper.getInstance(this.mApplication).stopDiscoverPeers(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshList() {
        SearchStaDevice searchStaDevice = this.mSearchStaDevice;
        if (searchStaDevice != null && searchStaDevice.isSearching) {
            Dbug.m1391w(this.TAG, "SearchStaDevice is running");
            return;
        }
        if (this.mSearchStaDevice != null) {
            stopStaSearchThread();
        }
        this.mAdapter.clear();
        this.mStationList.clear();
        SearchStaDevice searchStaDevice2 = new SearchStaDevice(this.mHandler);
        this.mSearchStaDevice = searchStaDevice2;
        searchStaDevice2.start();
    }

    private void stopStaSearchThread() {
        SearchStaDevice searchStaDevice = this.mSearchStaDevice;
        if (searchStaDevice != null) {
            searchStaDevice.stopSearch();
            this.mSearchStaDevice = null;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null || getActivity() == null || view.getId() != C1438R.id.device_list_switch_search_mode || getActivity() == null) {
            return;
        }
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        DeviceListAdapter deviceListAdapter;
        if (getActivity() != null && (deviceListAdapter = this.mAdapter) != null) {
            DeviceBean item = deviceListAdapter.getItem(i);
            if (item != null) {
                Dbug.m1389i(tag, "isConnected=" + ClientManager.getClient().isConnected() + ", wifiIP=" + item.getWifiIP());
                stopStaSearchThread();
                String wifiIP = item.getWifiIP();
                if (!TextUtils.isEmpty(wifiIP) && !wifiIP.equals(ClientManager.getClient().getAddress())) {
                    if (ClientManager.getClient().isConnected()) {
                        ClientManager.getClient().close();
                    }
                    ((MainActivity) getActivity()).connectDevice(wifiIP);
                } else {
                    enterLiveVideo();
                }
                this.mApplication.setSearchMode(1);
                return;
            }
            Dbug.m1391w(tag, "Device bean is null");
            return;
        }
        Dbug.m1391w(tag, "mAdapter is null");
    }

    private void enterLiveVideo() {
        Dbug.m1391w(tag, "It has connected.");
        Fragment videoFragment = (BaseFragment) getActivity().getSupportFragmentManager().findFragmentByTag(VideoFragment.class.getSimpleName());
        if (videoFragment == null) {
            videoFragment = new VideoFragment();
        }
        ((MainActivity) getActivity()).changeFragment(C1438R.id.container, videoFragment, videoFragment.getClass().getSimpleName());
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        stopStaSearchThread();
        dismissSelectWifiDialog();
        super.onDestroyView();
    }

    private static class DeviceListAdapter extends ArrayAdapter<DeviceBean> {
        private WeakReference<Context> mContextWeakRef;
        private WifiHelper mWifiHelper;
        private int resourceId;

        DeviceListAdapter(Context context, int i) {
            super(context, i);
            this.resourceId = i;
            this.mContextWeakRef = new WeakReference<>(context);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(this.resourceId, viewGroup, false);
                viewHolder = new ViewHolder(view);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            DeviceBean item = getItem(i);
            if (item != null) {
                String wifiSSID = item.getWifiSSID();
                if (TextUtils.isEmpty(wifiSSID)) {
                    WeakReference<Context> weakReference = this.mContextWeakRef;
                    if (weakReference != null && weakReference.get() != null) {
                        viewHolder.devNameTv.setText(this.mContextWeakRef.get().getString(C1438R.string.unknown_device_name));
                    }
                } else {
                    viewHolder.devNameTv.setText(wifiSSID);
                }
                if (checkDeviceConnected(item.getMode(), item.getWifiSSID())) {
                    viewHolder.devStateIcon.setImageResource(C1438R.mipmap.ic_device_selected);
                } else {
                    viewHolder.devStateIcon.setImageResource(C1438R.mipmap.ic_device_unselected);
                }
            }
            return view;
        }

        private class ViewHolder {
            private TextView devNameTv;
            private ImageView devStateIcon;

            ViewHolder(View view) {
                this.devStateIcon = (ImageView) view.findViewById(C1438R.id.device_state_icon);
                this.devNameTv = (TextView) view.findViewById(C1438R.id.device_name_text);
                view.setTag(this);
            }
        }

        private boolean checkDeviceConnected(int i, String str) {
            WifiInfo wifiConnectionInfo;
            if (i == 1) {
                if (ClientManager.getClient().isConnected() && str.equals(ClientManager.getClient().getConnectedIP())) {
                    return true;
                }
            } else if (!TextUtils.isEmpty(str)) {
                if (this.mWifiHelper == null) {
                    this.mWifiHelper = WifiHelper.getInstance(MainApplication.getApplication());
                }
                WifiP2pDevice connectWifiDevice = WifiP2pHelper.getInstance(getContext()).getConnectWifiDevice();
                String ssid = connectWifiDevice != null ? connectWifiDevice.deviceName : null;
                if (TextUtils.isEmpty(ssid) && (wifiConnectionInfo = this.mWifiHelper.getWifiConnectionInfo()) != null) {
                    ssid = WifiHelper.formatSSID(wifiConnectionInfo.getSSID());
                }
                if (str.equals(ssid) && ClientManager.getClient().isConnected()) {
                    return true;
                }
            }
            return false;
        }
    }

    private static class SearchStaDevice extends Thread {
        private boolean isSearching;
        private Discovery mDiscovery;
        private WeakReference<Handler> weakReference;
        private int timeCount = 0;
        private Discovery.OnDiscoveryListener mOnDiscoveryListener = new Discovery.OnDiscoveryListener() { // from class: com.jieli.stream.dv.running2.ui.fragment.StaDeviceListFragment.SearchStaDevice.1
            @Override // com.jieli.lib.dv.control.mssdp.Discovery.OnDiscoveryListener
            public void onDiscovery(String str, String str2) {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                DeviceBean deviceBean = new DeviceBean();
                deviceBean.setWifiIP(str);
                deviceBean.setMode(1);
                deviceBean.setWifiSSID(str);
                Handler handler = (Handler) SearchStaDevice.this.weakReference.get();
                if (handler != null) {
                    handler.sendMessage(handler.obtainMessage(StaDeviceListFragment.MSG_ADD_NEW_DEVICE, deviceBean));
                }
            }

            @Override // com.jieli.lib.dv.control.mssdp.Discovery.OnDiscoveryListener
            public void onError(int i, String str) {
                Dbug.m1388e(StaDeviceListFragment.tag, "code : " + i + " , msg : " + str);
                SearchStaDevice.this.isSearching = false;
            }
        };

        SearchStaDevice(Handler handler) {
            Discovery discoveryNewInstance = Discovery.newInstance();
            this.mDiscovery = discoveryNewInstance;
            discoveryNewInstance.registerOnDiscoveryListener(this.mOnDiscoveryListener);
            this.weakReference = new WeakReference<>(handler);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void stopSearch() {
            this.isSearching = false;
            Discovery discovery = this.mDiscovery;
            if (discovery != null) {
                discovery.unregisterOnDiscoveryListener(this.mOnDiscoveryListener);
                this.mDiscovery.release();
                this.mDiscovery = null;
            }
        }

        @Override // java.lang.Thread
        public synchronized void start() {
            super.start();
            this.isSearching = true;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            Dbug.m1389i(StaDeviceListFragment.tag, "SearchStaDevice thread start running...");
            while (this.isSearching) {
                Discovery discovery = this.mDiscovery;
                if (discovery != null) {
                    discovery.setFilter(true);
                    this.mDiscovery.doDiscovery();
                    for (int i = 0; i < 10 && this.isSearching; i++) {
                        SystemClock.sleep(300L);
                    }
                    if (!this.isSearching) {
                        break;
                    }
                    int i2 = this.timeCount + 3;
                    this.timeCount = i2;
                    if (i2 >= 30) {
                        break;
                    }
                }
            }
            Dbug.m1389i(StaDeviceListFragment.tag, "SearchStaDevice thread stop running..." + this.isSearching);
            this.isSearching = false;
            this.timeCount = 0;
        }
    }

    private void dismissSelectWifiDialog() {
        SelectWifiDialog selectWifiDialog = this.mSelectWifiDialog;
        if (selectWifiDialog != null) {
            if (selectWifiDialog.isShowing() && !isDetached()) {
                this.mSelectWifiDialog.dismiss();
            }
            this.mSelectWifiDialog = null;
        }
    }
}
