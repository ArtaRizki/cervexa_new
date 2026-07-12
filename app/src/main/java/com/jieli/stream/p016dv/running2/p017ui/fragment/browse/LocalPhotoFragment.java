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
public class LocalPhotoFragment extends BaseFragment implements BaseFooterView.OnLoadListener, TimeLineAdapter.OnSubViewItemClickListener {
    private static final int MSG_LOAD_DATA = 257;
    private List<FileInfo> allDataList;
    private BrowseFileFragment browseFileFragment;
    private List<FileInfo> dataList;
    private LinearLayout emptyView;
    private ExpandFooterView footerView;
    private boolean isLoading;
    private boolean isOpenTask;
    private boolean isSelectAll;
    private TimeLineAdapter mAdapter;
    private Handler mHandler = new Handler(new Handler.Callback() { // from class: com.jieli.stream.dv.running2.ui.fragment.browse.LocalPhotoFragment.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (LocalPhotoFragment.this.getActivity() != null && message != null) {
                int i = message.what;
                if (i == 84) {
                    int i2 = message.arg1;
                    if (i2 == 0) {
                        LocalPhotoFragment.this.handlerTaskList(IConstant.OP_DELETE_FILES, false);
                    } else if (i2 == 1) {
                        LocalPhotoFragment.this.handlerTaskList(IConstant.OP_DELETE_FILES, true);
                    }
                } else if (i == 257) {
                    if (LocalPhotoFragment.this.dataList != null) {
                        LocalPhotoFragment localPhotoFragment = LocalPhotoFragment.this;
                        localPhotoFragment.loadMoreData(localPhotoFragment.dataList.size());
                    } else {
                        Dbug.m1388e(LocalPhotoFragment.this.TAG, "======no load===list=");
                    }
                    LocalPhotoFragment.this.onStopLoad();
                }
            }
            return false;
        }
    });
    private ListView mListView;
    private NotifyDialog mLoadingDialog;
    private int mOp;
    private LocalPhotoBroadCast mReceiver;
    private MediaTask photoTask;
    private int retryNum;
    private List<FileInfo> selectedList;

    private class LocalPhotoBroadCast extends BroadcastReceiver {
        private LocalPhotoBroadCast() {
        }

        /* JADX WARN: Removed duplicated region for block: B:28:0x0071  */
        @Override // android.content.BroadcastReceiver
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onReceive(android.content.Context r6, android.content.Intent r7) {
            /*
                Method dump skipped, instruction units count: 816
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

    @Override // com.jieli.stream.p016dv.running2.p017ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(com.weioa.KmedHealthIndonesia.R.layout.fragment_local_photo, viewGroup, false);
        this.mListView = (ListView) viewInflate.findViewById(com.weioa.KmedHealthIndonesia.R.id.local_photo_view);
        this.emptyView = (LinearLayout) viewInflate.findViewById(com.weioa.KmedHealthIndonesia.R.id.view_empty);
        ExpandFooterView expandFooterView = (ExpandFooterView) viewInflate.findViewById(com.weioa.KmedHealthIndonesia.R.id.local_photo_footer);
        this.footerView = expandFooterView;
        expandFooterView.setOnLoadListener(this);
        ((LinearLayout) getActivity().findViewById(com.weioa.KmedHealthIndonesia.R.id.pop_bottom_bar_share)).setVisibility(0);
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getActivity() != null) {
            this.selectedList = new ArrayList();
            initListView();
            if (this.mReceiver == null) {
                this.mReceiver = new LocalPhotoBroadCast();
            }
            IntentFilter intentFilter = new IntentFilter(IActions.ACTION_BROWSE_FILE_OPERATION);
            intentFilter.addAction(IActions.ACTION_LANGUAAGE_CHANGE);
            if(android.os.Build.VERSION.SDK_INT>=33){getActivity().getApplicationContext().registerReceiver(this.mReceiver, intentFilter, 4);}else{getActivity().getApplicationContext().registerReceiver(this.mReceiver, intentFilter);}
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        BrowseFileFragment browseFileFragment = this.browseFileFragment;
        if (browseFileFragment != null && this.photoTask == null) {
            MediaTask mediaTask = browseFileFragment.getMediaTask();
            this.photoTask = mediaTask;
            if (mediaTask != null && (this.browseFileFragment.currentFragment() instanceof LocalPhotoFragment)) {
                this.photoTask.setUIHandler(this.mHandler);
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
        MediaTask mediaTask = this.photoTask;
        if (mediaTask != null) {
            mediaTask.setUIHandler(null);
            this.photoTask = null;
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
        dismissWaitingDialog();
        if (getActivity() == null || this.mReceiver == null) {
            return;
        }
        getActivity().getApplicationContext().unregisterReceiver(this.mReceiver);
        this.mReceiver = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 4134) {
            sendStateChange(1, false);
        }
        Dbug.m1388e(this.TAG, "onActivityResult=" + i + ", resultCode=" + i2);
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
        if (this.mHandler == null || this.isLoading) {
            return;
        }
        this.isLoading = true;
        TimeLineAdapter timeLineAdapter = this.mAdapter;
        if (timeLineAdapter != null && timeLineAdapter.getCount() > 0) {
            this.mHandler.removeMessages(257);
            this.mHandler.sendEmptyMessageDelayed(257, 1500L);
        } else {
            ToastUtil.showToastShort(getString(com.weioa.KmedHealthIndonesia.R.string.no_more_data));
            onStopLoad();
        }
    }

    private void initListView() {
        if (getActivity() != null) {
            showWaitingDialog();
            new Thread(new Runnable() { // from class: com.jieli.stream.dv.running2.ui.fragment.browse.LocalPhotoFragment.2
                @Override // java.lang.Runnable
                public void run() {
                    LocalPhotoFragment.this.allDataList = AppUtils.queryAllLocalFileList(AppUtils.splicingFilePath(LocalPhotoFragment.this.mApplication.getAppName(), LocalPhotoFragment.this.mApplication.getUUID(), null, null), IConstant.DIR_DOWNLOAD);
                    if (LocalPhotoFragment.this.allDataList != null) {
                        LocalPhotoFragment localPhotoFragment = LocalPhotoFragment.this;
                        localPhotoFragment.allDataList = AppUtils.selectTypeList(localPhotoFragment.allDataList, 1);
                        Dbug.m1389i(LocalPhotoFragment.this.TAG, "allDataList size = " + LocalPhotoFragment.this.allDataList.size());
                        if (LocalPhotoFragment.this.getActivity() != null) {
                            LocalPhotoFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.jieli.stream.dv.running2.ui.fragment.browse.LocalPhotoFragment.2.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    if (LocalPhotoFragment.this.mAdapter != null) {
                                        LocalPhotoFragment.this.mAdapter.clear();
                                    }
                                    LocalPhotoFragment.this.loadMoreData(0);
                                    LocalPhotoFragment.this.dismissWaitingDialog();
                                }
                            });
                            return;
                        }
                        return;
                    }
                    Dbug.m1388e(LocalPhotoFragment.this.TAG, "allDataList is null");
                }
            }).start();
        }
    }

    private void showWaitingDialog() {
        if (getActivity() != null && !getActivity().isDestroyed()) {
            this.mLoadingDialog = NotifyDialog.newInstance(com.weioa.KmedHealthIndonesia.R.string.dialog_tips, true, com.weioa.KmedHealthIndonesia.R.string.loading, 0, new NotifyDialog.OnConfirmClickListener() { // from class: com.jieli.stream.dv.running2.ui.fragment.browse.LocalPhotoFragment.3
                @Override // com.jieli.stream.dv.running2.ui.dialog.NotifyDialog.OnConfirmClickListener
                public void onClick() {
                    LocalPhotoFragment.this.mLoadingDialog.dismiss();
                }
            });
        }
        NotifyDialog notifyDialog = this.mLoadingDialog;
        if (notifyDialog == null || notifyDialog.isShowing() || getActivity() == null) {
            return;
        }
        this.mLoadingDialog.show(getActivity().getSupportFragmentManager(), "LoadingDialog");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissWaitingDialog() {
        NotifyDialog notifyDialog = this.mLoadingDialog;
        if (notifyDialog != null) {
            if (notifyDialog.isShowing()) {
                this.mLoadingDialog.dismiss();
            }
            this.mLoadingDialog = null;
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
        ArrayList<String> arrayList = new ArrayList<>();
        for (FileInfo fileInfo2 : this.allDataList) {
            if (fileInfo2 != null) {
                String path2 = fileInfo2.getPath();
                if (AppUtils.checkFileExist(path2)) {
                    arrayList.add(path2);
                }
            }
        }
        int iIndexOf = arrayList.indexOf(path);
        if (arrayList.size() > 0) {
            Intent intent = new Intent(getActivity(), (Class<?>) GenericActivity.class);
            intent.putExtra(IConstant.KEY_FRAGMENT_TAG, 7);
            Bundle bundle = new Bundle();
            bundle.putStringArrayList(IConstant.KEY_PATH_LIST, arrayList);
            bundle.putInt(IConstant.KEY_POSITION, iIndexOf);
            intent.putExtra(IConstant.KEY_DATA, bundle);
            startActivity(intent);
        }
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
                    if (size == 0) {
                        this.emptyView.setVisibility(0);
                        return;
                    } else {
                        ToastUtil.showToastShort(getString(com.weioa.KmedHealthIndonesia.R.string.no_more_data));
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
            if (fileInfo == null || this.photoTask == null) {
                return;
            }
            MediaTaskInfo mediaTaskInfo = new MediaTaskInfo();
            mediaTaskInfo.setInfo(fileInfo);
            mediaTaskInfo.setOp(i);
            this.photoTask.tryToStartTask(mediaTaskInfo);
            return;
        }
        sendMsg(this.selectedList.size());
        sendStateChange(1, false);
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


