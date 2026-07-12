package com.jieli.stream.p016dv.running2.task;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.jieli.lib.p015dv.control.connect.response.SendResponse;
import com.jieli.stream.p016dv.running2.bean.FileInfo;
import com.jieli.stream.p016dv.running2.bean.MediaTaskInfo;
import com.jieli.stream.p016dv.running2.p017ui.MainApplication;
import com.jieli.stream.p016dv.running2.util.AppUtils;
import com.jieli.stream.p016dv.running2.util.ClientManager;
import com.jieli.stream.p016dv.running2.util.Dbug;
import com.jieli.stream.p016dv.running2.util.IConstant;
import com.jieli.stream.p016dv.running2.util.ScanFilesHelper;
import com.jieli.stream.p016dv.running2.util.ThumbLoader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/* JADX INFO: loaded from: classes.dex */
public class MediaTask extends HandlerThread implements IConstant, Handler.Callback {
    private static final int MSG_ADD_TASK = 82;
    private static final int MSG_START_TASK = 80;
    private static final int MSG_STOP_TASK = 81;
    private static final String TAG = "MediaTask";
    private boolean isCancelTask;
    private Call mCall;
    private Context mContext;
    private Handler mUIHandler;
    private Handler mWorkHandler;
    private ScanFilesHelper scanFilesHelper;

    public MediaTask(Context context, String str) {
        super(str, -19);
        this.mContext = context;
    }

    @Override // android.os.HandlerThread
    protected void onLooperPrepared() {
        this.mWorkHandler = new Handler(getLooper(), this);
        super.onLooperPrepared();
    }

    public void setUIHandler(Handler handler) {
        this.mUIHandler = handler;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        MediaTaskInfo mediaTaskInfo;
        if (message == null) {
            return false;
        }
        int i = message.what;
        if (i == 80) {
            Bundle data = message.getData();
            if (data == null || (mediaTaskInfo = (MediaTaskInfo) data.getSerializable(IConstant.MEDIA_TASK)) == null) {
                return false;
            }
            handlerTask(mediaTaskInfo);
            return false;
        }
        if (i != 81) {
            return false;
        }
        Call call = this.mCall;
        if (call != null && call.isExecuted()) {
            this.mCall.cancel();
        }
        this.isCancelTask = true;
        Handler handler = this.mWorkHandler;
        if (handler == null) {
            return false;
        }
        handler.removeMessages(80);
        return false;
    }

    private void handlerTask(MediaTaskInfo mediaTaskInfo) {
        if (mediaTaskInfo != null) {
            FileInfo info = mediaTaskInfo.getInfo();
            int op = mediaTaskInfo.getOp();
            if (info != null) {
                if (op == 163) {
                    tryToDownload(info);
                } else {
                    if (op != 164) {
                        return;
                    }
                    tryToDelete(info);
                }
            }
        }
    }

    private void tryToDownload(FileInfo fileInfo) {
        if (fileInfo == null || fileInfo.getSource() == 1) {
            return;
        }
        final String name = fileInfo.getName();
        String url = AppUtils.formatUrl(ClientManager.getClient().getConnectedIP(), 8080, fileInfo.getPath());
        final String str = AppUtils.splicingFilePath(MainApplication.getApplication().getAppName(), MainApplication.getApplication().getUUID(), AppUtils.checkCameraDir(fileInfo), IConstant.DIR_DOWNLOAD) + File.separator + AppUtils.getDownloadFilename(fileInfo);
        if (fileInfo.isVideo()) {
            File file = new File(str);
            if (!file.exists()) {
                this.mCall = new OkHttpClient().newBuilder().writeTimeout(50L, TimeUnit.SECONDS).build().newCall(new Request.Builder().url(url).build());
                final long size = fileInfo.getSize();
                this.mCall.enqueue(new Callback() { // from class: com.jieli.stream.dv.running2.task.MediaTask.1
                    @Override // okhttp3.Callback
                    public void onFailure(Call call, IOException iOException) {
                        Dbug.m1388e(MediaTask.TAG, "onFailure ~~~~~11111111111111111");
                        File file2 = new File(str);
                        if (file2.exists() && file2.delete()) {
                            Dbug.m1391w(MediaTask.TAG, "download file fail, delete file success!");
                        }
                        if (MediaTask.this.mUIHandler != null) {
                            Message messageObtainMessage = MediaTask.this.mUIHandler.obtainMessage();
                            messageObtainMessage.what = 83;
                            messageObtainMessage.arg1 = 0;
                            messageObtainMessage.obj = name;
                            MediaTask.this.mUIHandler.sendMessage(messageObtainMessage);
                        }
                        MediaTask.this.mCall = null;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:102:0x0202 A[Catch: IOException -> 0x01fe, TRY_LEAVE, TryCatch #8 {IOException -> 0x01fe, blocks: (B:98:0x01fa, B:102:0x0202), top: B:138:0x01fa }] */
                    /* JADX WARN: Removed duplicated region for block: B:107:0x0213  */
                    /* JADX WARN: Removed duplicated region for block: B:120:0x027e  */
                    /* JADX WARN: Removed duplicated region for block: B:134:0x012e A[EXC_TOP_SPLITTER, SYNTHETIC] */
                    /* JADX WARN: Removed duplicated region for block: B:138:0x01fa A[EXC_TOP_SPLITTER, SYNTHETIC] */
                    /* JADX WARN: Removed duplicated region for block: B:68:0x0136 A[Catch: IOException -> 0x0132, TRY_LEAVE, TryCatch #4 {IOException -> 0x0132, blocks: (B:64:0x012e, B:68:0x0136), top: B:134:0x012e }] */
                    /* JADX WARN: Removed duplicated region for block: B:73:0x0147  */
                    /* JADX WARN: Removed duplicated region for block: B:86:0x01b2  */
                    /* JADX WARN: Removed duplicated region for block: B:93:0x01d0  */
                    @Override // okhttp3.Callback
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public void onResponse(okhttp3.Call r17, okhttp3.Response r18) {
                        /*
                            Method dump skipped, instruction units count: 706
                            To view this dump change 'Code comments level' option to 'DEBUG'
                        */
                        return;
                    }
                });
                return;
            }
            if (file.length() >= fileInfo.getSize()) {
                Handler handler = this.mUIHandler;
                if (handler != null) {
                    Message messageObtainMessage = handler.obtainMessage();
                    messageObtainMessage.what = 83;
                    messageObtainMessage.arg1 = 2;
                    messageObtainMessage.obj = str;
                    this.mUIHandler.sendMessage(messageObtainMessage);
                    return;
                }
                return;
            }
            if (file.delete()) {
                tryToDownload(fileInfo);
                return;
            }
            Handler handler2 = this.mUIHandler;
            if (handler2 != null) {
                Message messageObtainMessage2 = handler2.obtainMessage();
                messageObtainMessage2.what = 83;
                messageObtainMessage2.arg1 = 0;
                messageObtainMessage2.obj = name;
                this.mUIHandler.sendMessage(messageObtainMessage2);
                return;
            }
            return;
        }
        File file2 = new File(str);
        if (!file2.exists()) {
            ThumbLoader.getInstance().downloadWebImage(this.mContext, url, str, new ThumbLoader.OnDownloadListener() { // from class: com.jieli.stream.dv.running2.task.MediaTask.2
                @Override // com.jieli.stream.dv.running2.util.ThumbLoader.OnDownloadListener
                public void onResult(boolean z, String str2) {
                    if (z) {
                        if (MediaTask.this.isCancelTask) {
                            MediaTask.this.isCancelTask = false;
                            File file3 = new File(str);
                            if (file3.exists() && file3.delete()) {
                                Dbug.m1391w(MediaTask.TAG, "download image failed, delete file success!");
                            }
                        }
                        if (MediaTask.this.mUIHandler != null) {
                            Message messageObtainMessage3 = MediaTask.this.mUIHandler.obtainMessage();
                            messageObtainMessage3.what = 83;
                            messageObtainMessage3.arg1 = 1;
                            messageObtainMessage3.obj = str;
                            MediaTask.this.mUIHandler.sendMessage(messageObtainMessage3);
                            if (MediaTask.this.scanFilesHelper == null) {
                                MediaTask mediaTask = MediaTask.this;
                                mediaTask.scanFilesHelper = new ScanFilesHelper(mediaTask.mContext);
                            }
                            MediaTask.this.scanFilesHelper.scanFiles(str);
                            return;
                        }
                        return;
                    }
                    File file4 = new File(str);
                    if (file4.exists() && file4.delete()) {
                        Dbug.m1391w(MediaTask.TAG, "download image failed, delete file success!");
                    }
                    if (MediaTask.this.mUIHandler != null) {
                        Message messageObtainMessage4 = MediaTask.this.mUIHandler.obtainMessage();
                        messageObtainMessage4.what = 83;
                        messageObtainMessage4.arg1 = 0;
                        messageObtainMessage4.obj = name;
                        MediaTask.this.mUIHandler.sendMessage(messageObtainMessage4);
                    }
                }
            });
            return;
        }
        if (file2.length() >= fileInfo.getSize()) {
            Handler handler3 = this.mUIHandler;
            if (handler3 != null) {
                Message messageObtainMessage3 = handler3.obtainMessage();
                messageObtainMessage3.what = 83;
                messageObtainMessage3.arg1 = 2;
                messageObtainMessage3.obj = str;
                this.mUIHandler.sendMessage(messageObtainMessage3);
                return;
            }
            return;
        }
        if (file2.delete()) {
            tryToDownload(fileInfo);
            return;
        }
        Handler handler4 = this.mUIHandler;
        if (handler4 != null) {
            Message messageObtainMessage4 = handler4.obtainMessage();
            messageObtainMessage4.what = 83;
            messageObtainMessage4.arg1 = 0;
            messageObtainMessage4.obj = name;
            this.mUIHandler.sendMessage(messageObtainMessage4);
        }
    }

    private void tryToDelete(final FileInfo fileInfo) {
        if (fileInfo == null) {
            return;
        }
        if (fileInfo.getSource() == 1) {
            File file = new File(fileInfo.getPath());
            if (file.exists() && file.isFile()) {
                if (file.delete()) {
                    Handler handler = this.mUIHandler;
                    if (handler != null) {
                        Message messageObtainMessage = handler.obtainMessage();
                        messageObtainMessage.what = 84;
                        messageObtainMessage.arg1 = 1;
                        messageObtainMessage.obj = fileInfo.getName();
                        this.mUIHandler.sendMessage(messageObtainMessage);
                        String path = fileInfo.getPath();
                        if (this.scanFilesHelper == null) {
                            this.scanFilesHelper = new ScanFilesHelper(this.mContext);
                        }
                        this.scanFilesHelper.updateToDeleteFile(path);
                        return;
                    }
                    return;
                }
                Handler handler2 = this.mUIHandler;
                if (handler2 != null) {
                    Message messageObtainMessage2 = handler2.obtainMessage();
                    messageObtainMessage2.what = 84;
                    messageObtainMessage2.arg1 = 0;
                    messageObtainMessage2.obj = fileInfo.getName();
                    this.mUIHandler.sendMessage(messageObtainMessage2);
                    return;
                }
                return;
            }
            Handler handler3 = this.mUIHandler;
            if (handler3 != null) {
                Message messageObtainMessage3 = handler3.obtainMessage();
                messageObtainMessage3.what = 84;
                messageObtainMessage3.arg1 = 0;
                messageObtainMessage3.obj = fileInfo.getName();
                this.mUIHandler.sendMessage(messageObtainMessage3);
                return;
            }
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(fileInfo.getPath());
        ClientManager.getClient().tryToDeleteFile(arrayList, new SendResponse() { // from class: com.jieli.stream.dv.running2.task.MediaTask.3
            @Override // com.jieli.lib.p015dv.control.connect.response.Response
            public void onResponse(Integer num) {
                if (num.intValue() == 1) {
                    if (MediaTask.this.mUIHandler != null) {
                        Message messageObtainMessage4 = MediaTask.this.mUIHandler.obtainMessage();
                        messageObtainMessage4.what = 84;
                        messageObtainMessage4.arg1 = 1;
                        messageObtainMessage4.obj = fileInfo.getName();
                        MediaTask.this.mUIHandler.sendMessage(messageObtainMessage4);
                        String path2 = fileInfo.getPath();
                        if (MediaTask.this.scanFilesHelper == null) {
                            MediaTask mediaTask = MediaTask.this;
                            mediaTask.scanFilesHelper = new ScanFilesHelper(mediaTask.mContext);
                        }
                        MediaTask.this.scanFilesHelper.updateToDeleteFile(path2);
                        return;
                    }
                    return;
                }
                if (MediaTask.this.mUIHandler != null) {
                    Message messageObtainMessage5 = MediaTask.this.mUIHandler.obtainMessage();
                    messageObtainMessage5.what = 84;
                    messageObtainMessage5.arg1 = 0;
                    messageObtainMessage5.obj = fileInfo.getName();
                    MediaTask.this.mUIHandler.sendMessage(messageObtainMessage5);
                }
            }
        });
    }

    public void tryToStartTask(MediaTaskInfo mediaTaskInfo) {
        if (this.mWorkHandler != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(IConstant.MEDIA_TASK, mediaTaskInfo);
            Message messageObtainMessage = this.mWorkHandler.obtainMessage();
            messageObtainMessage.what = 80;
            messageObtainMessage.setData(bundle);
            this.mWorkHandler.sendMessage(messageObtainMessage);
        }
    }

    public void tryToStopTask() {
        Handler handler = this.mWorkHandler;
        if (handler != null) {
            handler.sendEmptyMessage(81);
        }
    }

    public void release() {
        this.mContext = null;
        Handler handler = this.mWorkHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        Handler handler2 = this.mUIHandler;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
        }
        System.gc();
    }
}

