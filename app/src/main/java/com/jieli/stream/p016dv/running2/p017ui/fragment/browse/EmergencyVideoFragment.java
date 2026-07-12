package com.jieli.stream.p016dv.running2.p017ui.fragment.browse;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.jieli.stream.p016dv.running2.bean.FileInfo;
import com.jieli.stream.p016dv.running2.bean.ItemBean;
import com.jieli.stream.p016dv.running2.bean.MediaTaskInfo;
import com.jieli.stream.p016dv.running2.p017ui.activity.GenericActivity;
import com.jieli.stream.p016dv.running2.p017ui.adapter.TimeLineAdapter;
import com.jieli.stream.p016dv.running2.p017ui.base.BaseFragment;
import com.jieli.stream.p016dv.running2.p017ui.dialog.NotifyDialog;
import com.jieli.stream.p016dv.running2.p017ui.widget.pullrefreshview.layout.BaseFooterView;
import com.jieli.stream.p016dv.running2.p017ui.widget.pullrefreshview.view.ExpandFooterView;
import com.jieli.stream.p016dv.running2.task.MediaTask;
import com.jieli.stream.p016dv.running2.util.AppUtils;
import com.jieli.stream.p016dv.running2.util.Dbug;
import com.jieli.stream.p016dv.running2.util.IActions;
import com.jieli.stream.p016dv.running2.util.IConstant;
import com.jieli.stream.p016dv.running2.util.ThumbLoader;
import com.jieli.stream.p016dv.running2.util.ToastUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class EmergencyVideoFragment extends BaseFragment implements BaseFooterView.OnLoadListener, TimeLineAdapter.OnSubViewItemClickListener {
    private static final int MSG_LOAD_DATA = 257;
    private List<FileInfo> allDataList;
    private BrowseFileFragment browseFileFragment;
    private List<FileInfo> dataList;
    private MediaTask emergencyVideoTask;
    private LinearLayout emptyView;
    private ExpandFooterView footerView;
    private boolean isLoading;
    private boolean isOpenTask;
    private boolean isSelectAll;
    private TimeLineAdapter mAdapter;
    private Handler mHandler = new Handler(new Handler.Callback() { // from class: com.jieli.stream.dv.running2.ui.fragment.browse.EmergencyVideoFragment.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (EmergencyVideoFragment.this.getActivity() != null && message != null) {
                int i = message.what;
                if (i == 84) {
                    int i2 = message.arg1;
                    if (i2 == 0) {
                        EmergencyVideoFragment.this.handlerTaskList(IConstant.OP_DELETE_FILES, false);
                    } else if (i2 == 1) {
                        EmergencyVideoFragment.this.handlerTaskList(IConstant.OP_DELETE_FILES, true);
                    }
                } else if (i == 257) {
                    if (EmergencyVideoFragment.this.dataList != null) {
                        EmergencyVideoFragment emergencyVideoFragment = EmergencyVideoFragment.this;
                        emergencyVideoFragment.loadMoreData(emergencyVideoFragment.dataList.size());
                    }
                    EmergencyVideoFragment.this.onStopLoad();
                }
            }
            return false;
        }
    });
    private ListView mListView;
    private int mOp;
    private EmergencyVideoBroadCastReceiver mReceiver;
    private NotifyDialog notifyDialog;
    private int retryNum;
    private List<FileInfo> selectedList;

    private class EmergencyVideoBroadCastReceiver extends BroadcastReceiver {
        private EmergencyVideoBroadCastReceiver() {
        }

        /* JADX WARN: Removed duplicated region for block: B:28:0x0071  */
        @Override // android.content.BroadcastReceiver
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onReceive(android.content.Context r6, android.content.Intent r7) {
            /*
                Method dump skipped, instruction units count: 828
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTextUI() {
        LinearLayout linearLayout = this.emptyView;
        if (linearLayout != null) {
            ((TextView) linearLayout.findViewById(com.weioa.KmedHealthIndonesia.R.id.text_empty_tips)).setText(com.weioa.KmedHealthIndonesia.R.string.no_data_tip);
        }
    }

    public void setParentFragment(BrowseFileFragment browseFileFragment) {
        this.browseFileFragment = browseFileFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(com.weioa.KmedHealthIndonesia.R.layout.fragment_emergency_video, viewGroup, false);
        this.mListView = (ListView) viewInflate.findViewById(com.weioa.KmedHealthIndonesia.R.id.emergency_video_view);
        this.emptyView = (LinearLayout) viewInflate.findViewById(com.weioa.KmedHealthIndonesia.R.id.view_empty);
        ExpandFooterView expandFooterView = (ExpandFooterView) viewInflate.findViewById(com.weioa.KmedHealthIndonesia.R.id.emergency_video_footer);
        this.footerView = expandFooterView;
        expandFooterView.setOnLoadListener(this);
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getActivity() != null) {
            this.selectedList = new ArrayList();
            initListView();
            registerBroadCast();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        BrowseFileFragment browseFileFragment = this.browseFileFragment;
        if (browseFileFragment != null && this.emergencyVideoTask == null) {
            MediaTask mediaTask = browseFileFragment.getMediaTask();
            this.emergencyVideoTask = mediaTask;
            if (mediaTask != null && (this.browseFileFragment.currentFragment() instanceof EmergencyVideoFragment)) {
                this.emergencyVideoTask.setUIHandler(this.mHandler);
            }
        }
        updateTextUI();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.isLoading = false;
        this.isOpenTask = false;
        TimeLineAdapter timeLineAdapter = this.mAdapter;
        if (timeLineAdapter != null) {
            timeLineAdapter.cancelTasks();
        }
        MediaTask mediaTask = this.emergencyVideoTask;
        if (mediaTask != null) {
            mediaTask.setUIHandler(null);
            this.emergencyVideoTask = null;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        this.browseFileFragment = null;
        super.onDetach();
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        NotifyDialog notifyDialog = this.notifyDialog;
        if (notifyDialog != null) {
            if (notifyDialog.isShowing()) {
                this.notifyDialog.dismiss();
            }
            this.notifyDialog = null;
        }
        unRegisterBroadCast();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 4134) {
            sendStateChange(1, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onStopLoad() {
        ExpandFooterView expandFooterView = this.footerView;
        if (expandFooterView != null) {
            expandFooterView.stopLoad();
        }
        this.isLoading = false;
    }

    @Override // com.jieli.stream.dv.running2.ui.widget.pullrefreshview.layout.BaseFooterView.OnLoadListener
    public void onLoad(BaseFooterView baseFooterView) {
        Handler handler = this.mHandler;
        if (handler == null || this.isLoading) {
            return;
        }
        this.isLoading = true;
        handler.sendEmptyMessageDelayed(257, 1500L);
    }

    private void initListView() {
        new Thread(new Runnable() { // from class: com.jieli.stream.dv.running2.ui.fragment.browse.EmergencyVideoFragment.2
            @Override // java.lang.Runnable
            public void run() {
                EmergencyVideoFragment.this.allDataList = AppUtils.queryAllLocalFileList(AppUtils.splicingFilePath(EmergencyVideoFragment.this.mApplication.getAppName(), EmergencyVideoFragment.this.mApplication.getUUID(), null, null), IConstant.DIR_RECORD);
                if (EmergencyVideoFragment.this.allDataList != null) {
                    EmergencyVideoFragment emergencyVideoFragment = EmergencyVideoFragment.this;
                    emergencyVideoFragment.allDataList = AppUtils.selectTypeList(emergencyVideoFragment.allDataList, 2);
                    Dbug.m1389i(EmergencyVideoFragment.this.TAG, "allDataList size = " + EmergencyVideoFragment.this.allDataList.size());
                    if (EmergencyVideoFragment.this.getActivity() != null) {
                        EmergencyVideoFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.jieli.stream.dv.running2.ui.fragment.browse.EmergencyVideoFragment.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                if (EmergencyVideoFragment.this.mAdapter != null) {
                                    EmergencyVideoFragment.this.mAdapter.clear();
                                }
                                EmergencyVideoFragment.this.loadMoreData(0);
                            }
                        });
                        return;
                    }
                    return;
                }
                Dbug.m1388e(EmergencyVideoFragment.this.TAG, "allDataList is null");
            }
        }).start();
    }

    private void registerBroadCast() {
        if (getActivity() != null) {
            if (this.mReceiver == null) {
                this.mReceiver = new EmergencyVideoBroadCastReceiver();
            }
            IntentFilter intentFilter = new IntentFilter(IActions.ACTION_BROWSE_FILE_OPERATION);
            intentFilter.addAction(IActions.ACTION_LANGUAAGE_CHANGE);
            if(android.os.Build.VERSION.SDK_INT>=33){getActivity().getApplicationContext().registerReceiver(this.mReceiver, intentFilter, 4);}else{getActivity().getApplicationContext().registerReceiver(this.mReceiver, intentFilter);}
        }
    }

    private void unRegisterBroadCast() {
        if (getActivity() == null || this.mReceiver == null) {
            return;
        }
        getActivity().getApplicationContext().unregisterReceiver(this.mReceiver);
        this.mReceiver = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadMoreData(int i) {
        List<FileInfo> list = this.allDataList;
        if (list != null) {
            int size = list.size();
            int i2 = size - i;
            this.emptyView.setVisibility(8);
            if (i2 <= 0) {
                if (i2 == 0) {
                    if (size > 0) {
                        ToastUtil.showToastShort(getString(com.weioa.KmedHealthIndonesia.R.string.no_more_data));
                        return;
                    } else {
                        this.emptyView.setVisibility(0);
                        return;
                    }
                }
                return;
            }
            if (i2 > 18) {
                this.dataList = this.allDataList.subList(0, i + 18);
            } else {
                this.dataList = this.allDataList;
            }
            List<ItemBean> listConvertDataList = AppUtils.convertDataList(this.dataList);
            if (listConvertDataList != null) {
                if (this.mAdapter == null) {
                    TimeLineAdapter timeLineAdapter = new TimeLineAdapter(getActivity().getApplicationContext());
                    this.mAdapter = timeLineAdapter;
                    timeLineAdapter.setOnSubViewItemClickListener(this);
                }
                this.mAdapter.clear();
                this.mListView.setAdapter((ListAdapter) this.mAdapter);
                this.mAdapter.setDataList(listConvertDataList);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMsg(int i) {
        if (getActivity() != null) {
            Intent intent = new Intent(IActions.ACTION_SELECT_FILES);
            intent.putExtra(IConstant.KEY_SELECT_FILES_NUM, i);
            getActivity().sendBroadcast(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendStateChange(int i, boolean z) {
        if (getActivity() != null) {
            Intent intent = new Intent(IActions.ACTION_SELECT_STATE_CHANGE);
            intent.putExtra(IConstant.KEY_STATE_TYPE, i);
            intent.putExtra(IConstant.KEY_SELECT_STATE, z);
            getActivity().sendBroadcast(intent);
        }
    }

    @Override // com.jieli.stream.dv.running2.ui.adapter.TimeLineAdapter.OnSubViewItemClickListener
    public void onSubItemClick(int i, int i2, FileInfo fileInfo) {
        TimeLineAdapter timeLineAdapter;
        if (fileInfo == null || (timeLineAdapter = this.mAdapter) == null || this.allDataList == null) {
            return;
        }
        if (timeLineAdapter.isEditMode()) {
            fileInfo.setSelected(!fileInfo.isSelected());
            if (fileInfo.isSelected()) {
                if (!this.selectedList.contains(fileInfo)) {
                    this.selectedList.add(fileInfo);
                }
                if (!this.isSelectAll && this.selectedList.size() == this.allDataList.size()) {
                    this.isSelectAll = true;
                    sendStateChange(2, true);
                }
            } else {
                this.selectedList.remove(fileInfo);
                if (this.isSelectAll) {
                    this.isSelectAll = false;
                    sendStateChange(2, false);
                }
            }
            sendMsg(this.selectedList.size());
            this.mAdapter.notifyDataSetChanged();
            return;
        }
        String path = fileInfo.getPath();
        if (AppUtils.checkFileExist(path)) {
            Dbug.m1389i(this.TAG, "play video url : " + path);
            Intent intent = new Intent(getActivity(), (Class<?>) GenericActivity.class);
            intent.putExtra(IConstant.KEY_FRAGMENT_TAG, 8);
            Bundle bundle = new Bundle();
            bundle.putString(IConstant.KEY_PATH_LIST, path);
            intent.putExtra(IConstant.KEY_DATA, bundle);
            startActivity(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlerTaskList(int i, boolean z) {
        FileInfo fileInfoRemove;
        Dbug.m1389i(this.TAG, "- handlerTaskList - isOpenTask : " + this.isOpenTask);
        List<FileInfo> list = this.selectedList;
        if (list == null || !this.isOpenTask) {
            return;
        }
        this.mOp = i;
        int size = list.size();
        if (z) {
            if (size > 0 && (fileInfoRemove = this.selectedList.remove(0)) != null && this.mOp == 164) {
                updateDeleteUI(fileInfoRemove);
            }
            this.retryNum = 0;
        } else {
            int i2 = this.retryNum + 1;
            this.retryNum = i2;
            if (i2 > 2) {
                this.retryNum = 0;
                if (size > 0) {
                    this.selectedList.remove(0);
                }
            }
        }
        int size2 = this.selectedList.size();
        if (size2 > 0) {
            Dbug.m1391w(this.TAG, "handler task size = " + size2);
            sendMsg(this.selectedList.size());
            FileInfo fileInfo = this.selectedList.get(0);
            if (fileInfo == null || this.emergencyVideoTask == null) {
                return;
            }
            MediaTaskInfo mediaTaskInfo = new MediaTaskInfo();
            mediaTaskInfo.setInfo(fileInfo);
            mediaTaskInfo.setOp(i);
            this.emergencyVideoTask.tryToStartTask(mediaTaskInfo);
            return;
        }
        sendMsg(this.selectedList.size());
        sendStateChange(1, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showNotifyDialog() {
        if (this.notifyDialog == null) {
            this.notifyDialog = NotifyDialog.newInstance(com.weioa.KmedHealthIndonesia.R.string.dialog_warning, com.weioa.KmedHealthIndonesia.R.string.delete_emergency_video_tip, com.weioa.KmedHealthIndonesia.R.string.dialog_cancel, com.weioa.KmedHealthIndonesia.R.string.dialog_confirm, new NotifyDialog.OnNegativeClickListener() { // from class: com.jieli.stream.dv.running2.ui.fragment.browse.EmergencyVideoFragment.3
                @Override // com.jieli.stream.dv.running2.ui.dialog.NotifyDialog.OnNegativeClickListener
                public void onClick() {
                    if (EmergencyVideoFragment.this.notifyDialog != null) {
                        EmergencyVideoFragment.this.notifyDialog.dismiss();
                        EmergencyVideoFragment.this.notifyDialog = null;
                    }
                }
            }, new NotifyDialog.OnPositiveClickListener() { // from class: com.jieli.stream.dv.running2.ui.fragment.browse.EmergencyVideoFragment.4
                @Override // com.jieli.stream.dv.running2.ui.dialog.NotifyDialog.OnPositiveClickListener
                public void onClick() {
                    EmergencyVideoFragment.this.isOpenTask = true;
                    EmergencyVideoFragment.this.handlerTaskList(IConstant.OP_DELETE_FILES, false);
                    if (EmergencyVideoFragment.this.notifyDialog != null) {
                        EmergencyVideoFragment.this.notifyDialog.dismiss();
                        EmergencyVideoFragment.this.notifyDialog = null;
                    }
                    if (EmergencyVideoFragment.this.browseFileFragment != null) {
                        EmergencyVideoFragment.this.browseFileFragment.showWaitingDialog();
                    }
                }
            });
        }
        if (this.notifyDialog.isShowing()) {
            return;
        }
        this.notifyDialog.show(getFragmentManager(), "notify_dialog");
    }

    private void updateDeleteUI(FileInfo fileInfo) {
        if (fileInfo != null) {
            ThumbLoader.getInstance().removeBitmap(fileInfo.getPath());
            List<FileInfo> list = this.allDataList;
            if (list != null) {
                list.remove(fileInfo);
            }
        }
    }
}


