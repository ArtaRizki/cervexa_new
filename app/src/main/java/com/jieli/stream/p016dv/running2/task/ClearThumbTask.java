package com.jieli.stream.p016dv.running2.task;

import android.text.TextUtils;
import com.jieli.stream.p016dv.running2.bean.FileInfo;
import com.jieli.stream.p016dv.running2.p017ui.MainApplication;
import com.jieli.stream.p016dv.running2.util.AppUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ClearThumbTask extends Thread {
    private String appRootPath = AppUtils.splicingFilePath(MainApplication.getApplication().getAppName(), null, null, null);
    private boolean isExitClear;
    private OnClearThumbTaskListener listener;

    public interface OnClearThumbTaskListener {
        void onFinish();
    }

    public void stopClear() {
        this.isExitClear = true;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        long cache = getCache();
        while (!this.isExitClear && cache >= 209715200 && !TextUtils.isEmpty(this.appRootPath)) {
            clearOldThumbCache();
            cache = getCache();
        }
        OnClearThumbTaskListener onClearThumbTaskListener = this.listener;
        if (onClearThumbTaskListener != null) {
            onClearThumbTaskListener.onFinish();
        }
    }

    public void setOnClearThumbTaskListener(OnClearThumbTaskListener onClearThumbTaskListener) {
        this.listener = onClearThumbTaskListener;
    }

    private long getCache() {
        List<String> listQueryThumbDirPath = AppUtils.queryThumbDirPath(this.appRootPath);
        long folderSize = 0;
        if (listQueryThumbDirPath != null && listQueryThumbDirPath.size() > 0) {
            Iterator<String> it = listQueryThumbDirPath.iterator();
            while (it.hasNext()) {
                File file = new File(it.next());
                if (file.exists()) {
                    try {
                        folderSize += AppUtils.getFolderSize(file);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return folderSize;
    }

    private boolean deleteFile(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        if (file.exists() && file.isFile()) {
            return file.delete();
        }
        return false;
    }

    private void clearOldThumbCache() {
        int size;
        int i;
        List<FileInfo> listQueryThumbInfoList = AppUtils.queryThumbInfoList(this.appRootPath);
        if (listQueryThumbInfoList == null || (size = listQueryThumbInfoList.size()) <= 0 || (i = size / 3) <= 0) {
            return;
        }
        ArrayList<FileInfo> arrayList = new ArrayList();
        arrayList.addAll(listQueryThumbInfoList.subList(size - i, size));
        for (FileInfo fileInfo : arrayList) {
            if (fileInfo != null) {
                deleteFile(fileInfo.getPath());
            }
        }
    }
}
