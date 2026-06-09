package com.jieli.stream.p016dv.running2.p017ui.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.jieli.lib.p015dv.control.connect.response.SendResponse;
import com.jieli.lib.p015dv.control.json.bean.NotifyInfo;
import com.jieli.lib.p015dv.control.receiver.listener.OnNotifyListener;
import com.jieli.lib.p015dv.control.utils.Code;
import com.jieli.lib.p015dv.control.utils.Topic;
import com.jieli.lib.p015dv.control.utils.VersionHelper;
import com.jieli.stream.p016dv.running2.C1438R;
import com.jieli.stream.p016dv.running2.bean.SettingItem;
import com.jieli.stream.p016dv.running2.interfaces.OnSelectedListener;
import com.jieli.stream.p016dv.running2.p017ui.MainApplication;
import com.jieli.stream.p016dv.running2.p017ui.activity.GenericActivity;
import com.jieli.stream.p016dv.running2.p017ui.adapter.SettingAdapter;
import com.jieli.stream.p016dv.running2.p017ui.base.BaseFragment;
import com.jieli.stream.p016dv.running2.p017ui.dialog.BrowseFirmwareDialog;
import com.jieli.stream.p016dv.running2.p017ui.dialog.NotifyDialog;
import com.jieli.stream.p016dv.running2.p017ui.dialog.WaitingDialog;
import com.jieli.stream.p016dv.running2.util.AppUtils;
import com.jieli.stream.p016dv.running2.util.ClientManager;
import com.jieli.stream.p016dv.running2.util.Dbug;
import com.jieli.stream.p016dv.running2.util.FTPClientUtil;
import com.jieli.stream.p016dv.running2.util.IConstant;
import com.jieli.stream.p016dv.running2.util.PreferencesHelper;
import com.jieli.stream.p016dv.running2.util.ToastUtil;
import com.serenegiant.net.SocketChannelDataLink;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class AboutFragment extends BaseFragment {
    private static final int LIMIT_TIME = 60000;
    private static final int MSG_UPGRADE_FILE = 4660;
    private static final int MSG_UPLOAD_FAILED = 257;
    private static final int MSG_UPLOAD_FINISH = 256;
    private static final int TIME_INTERVAL = 2000;
    private SettingAdapter mAdapter;
    private long mBackPressedTimes;
    private CheckAppUpgrade mCheckAppUpgrade;
    private ListView mListView;
    private NotifyDialog mUpgradeCompleteDialog;
    private NotifyDialog mUploadDialog;
    private WaitingDialog mWaitingDialog;
    private TextView tvAppVersionName;
    private NotifyDialog upgradeNotifyDialog;
    private String tag = getClass().getSimpleName();
    private int pressCount = 0;
    private Handler mHandler = new Handler(new Handler.Callback() { // from class: com.jieli.stream.dv.running2.ui.fragment.AboutFragment.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) throws Throwable {
            ArrayList<String> stringArrayList;
            if (message == null || AboutFragment.this.getActivity() == null || AboutFragment.this.getActivity().isDestroyed()) {
                return false;
            }
            int i = message.what;
            if (i == 256) {
                if (AboutFragment.this.mUploadDialog == null || !AboutFragment.this.mUploadDialog.isShowing()) {
                    return false;
                }
                AboutFragment.this.mUploadDialog.dismiss();
                return false;
            }
            if (i == 257) {
                if (AboutFragment.this.mUploadDialog != null && AboutFragment.this.mUploadDialog.isShowing()) {
                    AboutFragment.this.mUploadDialog.dismiss();
                }
                ToastUtil.showToastLong(AboutFragment.this.getString(C1438R.string.upload_failed));
                return false;
            }
            if (i != AboutFragment.MSG_UPGRADE_FILE) {
                if (i != 21863) {
                    return false;
                }
                int i2 = message.arg1;
                if (AboutFragment.this.mUploadDialog == null) {
                    return false;
                }
                AboutFragment.this.mUploadDialog.setProgress(i2);
                return false;
            }
            Bundle data = message.getData();
            if (data == null || (stringArrayList = data.getStringArrayList(IConstant.UPDATE_PATH)) == null || stringArrayList.size() <= 0) {
                return false;
            }
            String string = null;
            if (data.getInt(IConstant.UPDATE_TYPE) == 1) {
                if (stringArrayList.size() > 1) {
                    string = AppUtils.readTxtFile(stringArrayList.get(1));
                }
            } else {
                string = AboutFragment.this.getString(C1438R.string.firmware_upgrade_tip);
            }
            if (TextUtils.isEmpty(string)) {
                return false;
            }
            AboutFragment.this.showNotifyDialog(string, data);
            return false;
        }
    });
    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() { // from class: com.jieli.stream.dv.running2.ui.fragment.AboutFragment.3
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            SettingItem settingItem;
            if (AboutFragment.this.mAdapter == null || (settingItem = (SettingItem) AboutFragment.this.mAdapter.getItem(i)) == null) {
                return;
            }
            String name = settingItem.getName();
            if (AboutFragment.this.getString(C1438R.string.check_app_upgrade).equals(name)) {
                if (AboutFragment.this.mCheckAppUpgrade == null) {
                    AboutFragment.this.mCheckAppUpgrade = new CheckAppUpgrade();
                    AboutFragment.this.mCheckAppUpgrade.execute(new Void[0]);
                    return;
                }
                return;
            }
            if (AboutFragment.this.getString(C1438R.string.upload_firmware).equals(name)) {
                if (ClientManager.getClient().isConnected()) {
                    BrowseFirmwareDialog browseFirmwareDialog = new BrowseFirmwareDialog();
                    browseFirmwareDialog.setOnSelectedListener(AboutFragment.this.onSelectedFileListener);
                    browseFirmwareDialog.show(AboutFragment.this.getFragmentManager(), "browse_firmware_file_dialog");
                    return;
                }
                ToastUtil.showToastShort(AboutFragment.this.getString(C1438R.string.please_connect_device_to_use));
            }
        }
    };
    private OnSelectedListener<String> onSelectedFileListener = new C15804();
    private final OnNotifyListener onNotifyListener = new OnNotifyListener() { // from class: com.jieli.stream.dv.running2.ui.fragment.AboutFragment.5
        @Override // com.jieli.lib.p015dv.control.receiver.listener.NotifyResponse
        public void onNotify(NotifyInfo notifyInfo) {
            if (notifyInfo.getErrorType() != 0) {
                Dbug.m1388e(AboutFragment.this.tag, Code.getCodeDescription(notifyInfo.getErrorType()));
                return;
            }
            String topic = notifyInfo.getTopic();
            byte b = -1;
            if (topic.hashCode() == 1950931127 && topic.equals(Topic.DEVICE_UPGRADE_SUCCESS)) {
                b = 0;
            }
            if (b != 0) {
                return;
            }
            AboutFragment.this.showUpgradeCompleteDialog();
        }
    };
    private View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: com.jieli.stream.dv.running2.ui.fragment.AboutFragment.10
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view == AboutFragment.this.tvAppVersionName) {
                if (AboutFragment.this.mBackPressedTimes + 2000 <= System.currentTimeMillis()) {
                    if (AboutFragment.this.pressCount != 0) {
                        AboutFragment.this.pressCount = 0;
                    }
                    AboutFragment.access$1508(AboutFragment.this);
                    ToastUtil.showToastShort(String.format(Locale.getDefault(), AboutFragment.this.getString(C1438R.string.open_debug_tip), Integer.valueOf(3 - AboutFragment.this.pressCount)));
                } else {
                    AboutFragment.access$1508(AboutFragment.this);
                    if (AboutFragment.this.pressCount == 3) {
                        AboutFragment.this.pressCount = 0;
                        PreferencesHelper.putBooleanValue(AboutFragment.this.mApplication, IConstant.DEBUG_SETTINGS, true ^ PreferencesHelper.getSharedPreferences(AboutFragment.this.mApplication).getBoolean(IConstant.DEBUG_SETTINGS, false));
                        ToastUtil.showToastShort(AboutFragment.this.getString(C1438R.string.open_debug_ok));
                    } else {
                        ToastUtil.showToastShort(String.format(Locale.getDefault(), AboutFragment.this.getString(C1438R.string.open_debug_tip), Integer.valueOf(3 - AboutFragment.this.pressCount)));
                    }
                }
                AboutFragment.this.mBackPressedTimes = System.currentTimeMillis();
            }
        }
    };

    static /* synthetic */ int access$1508(AboutFragment aboutFragment) {
        int i = aboutFragment.pressCount;
        aboutFragment.pressCount = i + 1;
        return i;
    }

    public static AboutFragment newInstance() {
        return new AboutFragment();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(C1438R.layout.fragment_about, viewGroup, false);
        this.tvAppVersionName = (TextView) viewInflate.findViewById(C1438R.id.about_app_version);
        this.mListView = (ListView) viewInflate.findViewById(C1438R.id.about_list_view);
        this.tvAppVersionName.setText(getResources().getString(C1438R.string.app_version) + " " + MainApplication.getApplication().getAppVersionName());
        String sdkVersionName = VersionHelper.getSdkVersionName(MainApplication.getApplication());
        if (!TextUtils.isEmpty(sdkVersionName)) {
            ((TextView) viewInflate.findViewById(C1438R.id.about_sdk_version)).setText(getResources().getString(C1438R.string.sdk_version) + " " + sdkVersionName);
        }
        String firmware_version = this.mApplication.getDeviceDesc() != null ? this.mApplication.getDeviceDesc().getFirmware_version() : null;
        if (!TextUtils.isEmpty(firmware_version)) {
            ((TextView) viewInflate.findViewById(C1438R.id.about_fw_version)).setText(getResources().getString(C1438R.string.firmware_version) + " " + firmware_version);
        }
        ((ImageView) viewInflate.findViewById(C1438R.id.about_return)).setOnClickListener(new View.OnClickListener() { // from class: com.jieli.stream.dv.running2.ui.fragment.AboutFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (AboutFragment.this.getActivity() != null) {
                    AboutFragment.this.getActivity().onBackPressed();
                }
            }
        });
        this.mListView.setOnItemClickListener(this.mOnItemClickListener);
        this.tvAppVersionName.setOnClickListener(this.mOnClickListener);
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        initVersion();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        dismissWaitingDialog();
        dismissNotifyDialog();
        CheckAppUpgrade checkAppUpgrade = this.mCheckAppUpgrade;
        if (checkAppUpgrade != null) {
            checkAppUpgrade.cancel(true);
            this.mCheckAppUpgrade = null;
        }
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    private void initVersion() {
        String[] stringArray = getResources().getStringArray(C1438R.array.about_list);
        ArrayList arrayList = new ArrayList();
        for (String str : stringArray) {
            if (!TextUtils.isEmpty(str)) {
                SettingItem settingItem = new SettingItem();
                settingItem.setType(0);
                settingItem.setName(str);
                arrayList.add(settingItem);
            }
        }
        SettingAdapter settingAdapter = new SettingAdapter(getContext(), arrayList);
        this.mAdapter = settingAdapter;
        this.mListView.setAdapter((ListAdapter) settingAdapter);
        this.mAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: renamed from: com.jieli.stream.dv.running2.ui.fragment.AboutFragment$4 */
    class C15804 implements OnSelectedListener<String> {
        C15804() {
        }

        @Override // com.jieli.stream.p016dv.running2.interfaces.OnSelectedListener
        public void onSelected(final String str) {
            Dbug.m1389i(AboutFragment.this.tag, "path=" + str);
            if (TextUtils.isEmpty(str)) {
                return;
            }
            if (AboutFragment.this.mUploadDialog == null) {
                AboutFragment.this.mUploadDialog = NotifyDialog.newInstance(C1438R.string.dialog_tips, 2, C1438R.string.uploading);
            }
            if (AboutFragment.this.mUploadDialog != null && !AboutFragment.this.mUploadDialog.isShowing()) {
                AboutFragment.this.mUploadDialog.show(AboutFragment.this.getFragmentManager(), "Upload_Firmware");
            }
            new Thread(new Runnable() { // from class: com.jieli.stream.dv.running2.ui.fragment.AboutFragment.4.1
                @Override // java.lang.Runnable
                public void run() {
                    File file = new File(str);
                    if (!file.exists()) {
                        AboutFragment.this.mHandler.sendEmptyMessage(257);
                    } else if (FTPClientUtil.getInstance().uploadFile(file.getName(), str, AboutFragment.this.mHandler)) {
                        AboutFragment.this.mHandler.sendEmptyMessage(256);
                        ClientManager.getClient().tryToResetDev(new SendResponse() { // from class: com.jieli.stream.dv.running2.ui.fragment.AboutFragment.4.1.1
                            @Override // com.jieli.lib.p015dv.control.connect.response.Response
                            public void onResponse(Integer num) {
                                if (num.intValue() == 1) {
                                    AboutFragment.this.mHandler.sendEmptyMessage(256);
                                } else {
                                    Dbug.m1388e(AboutFragment.this.TAG, "send reset cmd failed!");
                                }
                            }
                        });
                    } else {
                        AboutFragment.this.mHandler.sendEmptyMessage(257);
                    }
                }
            }).start();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        NotifyDialog notifyDialog = this.mUploadDialog;
        if (notifyDialog != null && notifyDialog.isShowing()) {
            this.mUploadDialog.dismiss();
            this.mUploadDialog = null;
        }
        if (MainApplication.isFactoryMode) {
            ClientManager.getClient().unregisterNotifyListener(this.onNotifyListener);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (MainApplication.isFactoryMode) {
            ClientManager.getClient().registerNotifyListener(this.onNotifyListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showUpgradeCompleteDialog() {
        if (this.mUpgradeCompleteDialog == null) {
            this.mUpgradeCompleteDialog = NotifyDialog.newInstance(C1438R.string.dialog_tips, C1438R.string.upgrade_step_6, C1438R.string.comfirm, new NotifyDialog.OnConfirmClickListener() { // from class: com.jieli.stream.dv.running2.ui.fragment.AboutFragment.6
                @Override // com.jieli.stream.dv.running2.ui.dialog.NotifyDialog.OnConfirmClickListener
                public void onClick() {
                    AboutFragment.this.mUpgradeCompleteDialog.dismiss();
                }
            });
        }
        if (this.mUpgradeCompleteDialog.isShowing()) {
            return;
        }
        this.mUpgradeCompleteDialog.show(getActivity().getSupportFragmentManager(), "mUpgradeCompleteDialog");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showNotifyDialog(String str, Bundle bundle) {
        if (this.upgradeNotifyDialog == null) {
            NotifyDialog notifyDialogNewInstance = NotifyDialog.newInstance(getString(C1438R.string.upgrade_desc), str, C1438R.string.dialog_cancel, C1438R.string.dialog_confirm, new NotifyDialog.OnNegativeClickListener() { // from class: com.jieli.stream.dv.running2.ui.fragment.AboutFragment.7
                @Override // com.jieli.stream.dv.running2.ui.dialog.NotifyDialog.OnNegativeClickListener
                public void onClick() {
                    AboutFragment.this.dismissNotifyDialog();
                }
            }, new NotifyDialog.OnPositiveClickListener() { // from class: com.jieli.stream.dv.running2.ui.fragment.AboutFragment.8
                @Override // com.jieli.stream.dv.running2.ui.dialog.NotifyDialog.OnPositiveClickListener
                public void onClick() {
                    Bundle bundle2 = AboutFragment.this.upgradeNotifyDialog.getBundle();
                    if (bundle2 != null && AboutFragment.this.getActivity() != null) {
                        Intent intent = new Intent(AboutFragment.this.getActivity(), (Class<?>) GenericActivity.class);
                        intent.putExtra(IConstant.KEY_FRAGMENT_TAG, 5);
                        intent.putExtra(IConstant.KEY_DATA, bundle2);
                        AboutFragment.this.startActivity(intent);
                    }
                    AboutFragment.this.dismissNotifyDialog();
                }
            });
            this.upgradeNotifyDialog = notifyDialogNewInstance;
            notifyDialogNewInstance.setContentTextLeft(true);
        }
        if (!TextUtils.isEmpty(str)) {
            this.upgradeNotifyDialog.setContent(str);
        }
        if (bundle != null) {
            this.upgradeNotifyDialog.setBundle(bundle);
        }
        if (this.upgradeNotifyDialog.isShowing()) {
            return;
        }
        this.upgradeNotifyDialog.show(getFragmentManager(), "notify_dialog");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissNotifyDialog() {
        NotifyDialog notifyDialog = this.upgradeNotifyDialog;
        if (notifyDialog != null) {
            if (notifyDialog.isShowing() && !isDetached()) {
                this.upgradeNotifyDialog.dismiss();
            }
            this.upgradeNotifyDialog = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showWaitingDialog() {
        if (this.mWaitingDialog == null) {
            WaitingDialog waitingDialog = new WaitingDialog();
            this.mWaitingDialog = waitingDialog;
            waitingDialog.setCancelable(false);
            this.mWaitingDialog.setNotifyContent(getString(C1438R.string.check_app_upgrade));
            this.mWaitingDialog.setOnWaitingDialog(new WaitingDialog.OnWaitingDialog() { // from class: com.jieli.stream.dv.running2.ui.fragment.AboutFragment.9
                @Override // com.jieli.stream.dv.running2.ui.dialog.WaitingDialog.OnWaitingDialog
                public void onCancelDialog() {
                    AboutFragment.this.dismissWaitingDialog();
                }
            });
        }
        if (this.mWaitingDialog.isShowing()) {
            return;
        }
        this.mWaitingDialog.show(getFragmentManager(), "waiting_dialog");
    }

    public void dismissWaitingDialog() {
        WaitingDialog waitingDialog = this.mWaitingDialog;
        if (waitingDialog != null) {
            if (waitingDialog.isShowing()) {
                this.mWaitingDialog.dismiss();
            }
            this.mWaitingDialog = null;
        }
    }

    private class CheckAppUpgrade extends AsyncTask<Void, Void, ArrayList<String>> {
        private CheckAppUpgrade() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            super.onPreExecute();
            AboutFragment.this.showWaitingDialog();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(ArrayList<String> arrayList) {
            super.onPostExecute(arrayList);
            AboutFragment.this.dismissWaitingDialog();
            AboutFragment.this.mCheckAppUpgrade = null;
            if (arrayList != null && arrayList.size() > 0) {
                if (arrayList.size() > 1) {
                    if (AboutFragment.this.mHandler != null) {
                        Bundle bundle = new Bundle();
                        bundle.putInt(IConstant.UPDATE_TYPE, 1);
                        bundle.putStringArrayList(IConstant.UPDATE_PATH, arrayList);
                        Message messageObtainMessage = AboutFragment.this.mHandler.obtainMessage(AboutFragment.MSG_UPGRADE_FILE);
                        messageObtainMessage.setData(bundle);
                        AboutFragment.this.mHandler.sendMessage(messageObtainMessage);
                        return;
                    }
                    return;
                }
                ToastUtil.showToastShort(arrayList.get(0));
                return;
            }
            ToastUtil.showToastShort(AboutFragment.this.getString(C1438R.string.upgrade_failed_tip));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public ArrayList<String> doInBackground(Void... voidArr) throws Throwable {
            if (AboutFragment.this.getActivity() == null) {
                return null;
            }
            int i = 0;
            while (!AppUtils.checkNetworkIsAvailable()) {
                AboutFragment.this.mApplication.switchWifi();
                SystemClock.sleep(6000L);
                i += SocketChannelDataLink.DEFAULT_SERVER_PORT;
                if (i > 60000) {
                    break;
                }
            }
            if (!AppUtils.checkNetworkIsAvailable()) {
                Dbug.m1391w(AboutFragment.this.tag, "Network is unavailable");
                return null;
            }
            String strCheckUpdateFilePath = AppUtils.checkUpdateFilePath(AboutFragment.this.mApplication, 1);
            if (TextUtils.isEmpty(strCheckUpdateFilePath)) {
                Dbug.m1391w(AboutFragment.this.tag, "upgradePath=" + strCheckUpdateFilePath);
                return null;
            }
            ArrayList<String> arrayList = new ArrayList<>();
            if (strCheckUpdateFilePath.equals(AboutFragment.this.getString(C1438R.string.latest_version))) {
                arrayList.add(strCheckUpdateFilePath);
            } else {
                Dbug.m1391w(AboutFragment.this.TAG, "有APK更新,更新路径：" + strCheckUpdateFilePath);
                arrayList.add(strCheckUpdateFilePath);
                List<String> listDownLoadUpdateFile = FTPClientUtil.getInstance().downLoadUpdateFile(strCheckUpdateFilePath, 1, 1, null);
                if (listDownLoadUpdateFile != null && listDownLoadUpdateFile.size() > 1) {
                    arrayList.add(listDownLoadUpdateFile.get(1));
                }
            }
            return arrayList;
        }
    }
}
