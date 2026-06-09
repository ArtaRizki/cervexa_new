package com.jieli.stream.p016dv.running2.p017ui.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import com.jieli.lib.p015dv.control.connect.listener.OnConnectStateListener;
import com.jieli.lib.p015dv.control.json.bean.NotifyInfo;
import com.jieli.lib.p015dv.control.receiver.listener.OnNotifyListener;
import com.jieli.lib.p015dv.control.utils.Topic;
import com.jieli.lib.p015dv.control.utils.TopicKey;
import com.jieli.stream.p016dv.running2.p017ui.MainApplication;
import com.jieli.stream.p016dv.running2.task.ClearThumbTask;
import com.jieli.stream.p016dv.running2.task.DeviceDescription;
import com.jieli.stream.p016dv.running2.task.HeartbeatTask;
import com.jieli.stream.p016dv.running2.task.SDPServer;
import com.jieli.stream.p016dv.running2.util.AppUtils;
import com.jieli.stream.p016dv.running2.util.ClientManager;
import com.jieli.stream.p016dv.running2.util.Dbug;
import com.jieli.stream.p016dv.running2.util.IActions;
import com.jieli.stream.p016dv.running2.util.IConstant;

/* JADX INFO: loaded from: classes.dex */
public class CommunicationService extends Service implements IConstant, IActions {
    public static final String SNAP_STATUS = "SNAP_STATUS";
    public static final String snap_status_key = "snap_status";
    private static final String tag = CommunicationService.class.getSimpleName();
    private ClearThumbTask clearThumbTask;
    private DeviceDescription loadDeviceDesTxt;
    private MainApplication mApplication;
    private SDPServer mLiveServer;
    private HeartbeatTask mHeartbeatTask = null;
    private final OnNotifyListener onNotifyResponse = new OnNotifyListener() { // from class: com.jieli.stream.dv.running2.ui.service.CommunicationService.1
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Removed duplicated region for block: B:153:0x02a8  */
        @Override // com.jieli.lib.p015dv.control.receiver.listener.NotifyResponse
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onNotify(com.jieli.lib.p015dv.control.json.bean.NotifyInfo r12) {
            /*
                Method dump skipped, instruction units count: 2958
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jieli.stream.p016dv.running2.p017ui.service.CommunicationService.C17211.onNotify(com.jieli.lib.dv.control.json.bean.NotifyInfo):void");
        }
    };
    private OnConnectStateListener connectStateListener = new OnConnectStateListener() { // from class: com.jieli.stream.dv.running2.ui.service.CommunicationService.2
        @Override // com.jieli.lib.p015dv.control.connect.listener.ConnectStateListener
        public void onStateChanged(Integer num) {
            int iIntValue = num.intValue();
            if ((iIntValue == -1 || iIntValue == 1 || iIntValue == 3 || iIntValue == 4) && CommunicationService.this.mHeartbeatTask != null) {
                Dbug.m1388e(CommunicationService.tag, "stop mHeartbeatTask");
                if (CommunicationService.this.mHeartbeatTask.isHeartbeatTaskRunning()) {
                    CommunicationService.this.mHeartbeatTask.stopRunning();
                }
                CommunicationService.this.mHeartbeatTask = null;
            }
        }
    };

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        ClientManager.getClient().registerNotifyListener(this.onNotifyResponse);
        ClientManager.getClient().registerConnectStateListener(this.connectStateListener);
        this.mApplication = MainApplication.getApplication();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent == null) {
            return 1;
        }
        int intExtra = intent.getIntExtra(IConstant.SERVICE_CMD, -1);
        Dbug.m1389i(tag, "onStartCommand==========cmd=" + intExtra);
        if (intExtra == 1) {
            String stringExtra = intent.getStringExtra(IConstant.KEY_CONNECT_IP);
            if (TextUtils.isEmpty(stringExtra)) {
                stringExtra = IConstant.DEFAULT_DEV_IP;
            }
            if (!ClientManager.getClient().isConnected()) {
                ClientManager.getClient().create(stringExtra, IConstant.CTP_TCP_PORT);
            }
        } else if (intExtra == 2) {
            ClientManager.getClient().disconnect();
        }
        return 1;
    }

    @Override // android.app.Service
    public void onDestroy() {
        release();
        super.onDestroy();
    }

    @Override // android.app.Service
    public void onTaskRemoved(Intent intent) {
        release();
        stopSelf();
        super.onTaskRemoved(intent);
    }

    private void release() {
        Dbug.m1388e(tag, "======= (( release )) =====");
        ClientManager.getClient().unregisterNotifyListener(this.onNotifyResponse);
        ClientManager.getClient().unregisterConnectStateListener(this.connectStateListener);
        ClearThumbTask clearThumbTask = this.clearThumbTask;
        if (clearThumbTask != null) {
            clearThumbTask.stopClear();
            this.clearThumbTask = null;
        }
        DeviceDescription deviceDescription = this.loadDeviceDesTxt;
        if (deviceDescription != null) {
            deviceDescription.interrupt();
            this.loadDeviceDesTxt = null;
        }
        HeartbeatTask heartbeatTask = this.mHeartbeatTask;
        if (heartbeatTask != null) {
            heartbeatTask.stopRunning();
            this.mHeartbeatTask = null;
        }
        stopSdpServer();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startSdpServer(NotifyInfo notifyInfo) {
        if (notifyInfo.getParams() == null) {
            Dbug.m1388e(tag, "cachePlaybackVideoParam is null");
            return;
        }
        if (this.mLiveServer == null) {
            int iIntValue = 30;
            int iIntValue2 = IConstant.AUDIO_SAMPLE_RATE_DEFAULT;
            int iIntValue3 = -1;
            String str = notifyInfo.getParams().get("format");
            String str2 = notifyInfo.getParams().get(TopicKey.FRAME_RATE);
            String str3 = notifyInfo.getParams().get(TopicKey.SAMPLE);
            if (!TextUtils.isEmpty(str3) && TextUtils.isDigitsOnly(str3)) {
                iIntValue2 = Integer.valueOf(str3).intValue();
            }
            if (!TextUtils.isEmpty(str2) && TextUtils.isDigitsOnly(str2)) {
                iIntValue = Integer.valueOf(str2).intValue();
            }
            if (!TextUtils.isEmpty(str) && TextUtils.isDigitsOnly(str)) {
                iIntValue3 = Integer.valueOf(str).intValue();
            }
            SDPServer sDPServer = new SDPServer(IConstant.SDP_PORT, iIntValue3);
            this.mLiveServer = sDPServer;
            sDPServer.setFrameRate(iIntValue);
            this.mLiveServer.setSampleRate(iIntValue2);
            Dbug.m1389i(tag, "format " + iIntValue3 + ", frameRate=" + iIntValue + ", sampleRate=" + iIntValue2);
            this.mLiveServer.setRtpVideoPort(IConstant.RTP_VIDEO_PORT1);
            this.mLiveServer.setRtpAudioPort(IConstant.RTP_AUDIO_PORT1);
            this.mLiveServer.start();
        }
    }

    private void stopSdpServer() {
        SDPServer sDPServer = this.mLiveServer;
        if (sDPServer != null) {
            sDPServer.stopRunning();
            this.mLiveServer = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cachePlaybackVideoParam(NotifyInfo notifyInfo) {
        int iIntValue;
        if (notifyInfo.getParams() == null) {
            Dbug.m1388e(tag, "cachePlaybackVideoParam is null");
            return;
        }
        String str = notifyInfo.getParams().get(TopicKey.WIDTH);
        String str2 = notifyInfo.getParams().get(TopicKey.HEIGHT);
        String str3 = notifyInfo.getParams().get("format");
        String str4 = notifyInfo.getParams().get(TopicKey.FRAME_RATE);
        String str5 = notifyInfo.getParams().get(TopicKey.SAMPLE);
        if (!TextUtils.isEmpty(str) && TextUtils.isDigitsOnly(str) && !TextUtils.isEmpty(str2) && TextUtils.isDigitsOnly(str2)) {
            int iAdjustRtsResolution = AppUtils.adjustRtsResolution(Integer.valueOf(str).intValue(), Integer.valueOf(str2).intValue());
            if (Topic.VIDEO_PARAM.equals(notifyInfo.getTopic())) {
                this.mApplication.getDeviceSettingInfo().setFrontRecordLevel(iAdjustRtsResolution);
                this.mApplication.getDeviceSettingInfo().setFrontLevel(iAdjustRtsResolution);
            } else {
                this.mApplication.getDeviceSettingInfo().setRearRecordLevel(iAdjustRtsResolution);
                this.mApplication.getDeviceSettingInfo().setRearLevel(iAdjustRtsResolution);
            }
        }
        if (!TextUtils.isEmpty(str3) && TextUtils.isDigitsOnly(str3)) {
            try {
                iIntValue = Integer.valueOf(str3).intValue();
            } catch (NumberFormatException e) {
                e.printStackTrace();
                iIntValue = 1;
            }
            if (iIntValue == 0) {
                if (Topic.VIDEO_PARAM.equals(notifyInfo.getTopic())) {
                    this.mApplication.getDeviceSettingInfo().setFrontFormat(0);
                } else {
                    this.mApplication.getDeviceSettingInfo().setRearFormat(0);
                }
            } else if (Topic.VIDEO_PARAM.equals(notifyInfo.getTopic())) {
                this.mApplication.getDeviceSettingInfo().setFrontFormat(1);
            } else {
                this.mApplication.getDeviceSettingInfo().setRearFormat(1);
            }
        }
        if (!TextUtils.isEmpty(str4) && TextUtils.isDigitsOnly(str4)) {
            int iIntValue2 = 30;
            try {
                iIntValue2 = Integer.valueOf(str4).intValue();
            } catch (NumberFormatException e2) {
                e2.printStackTrace();
            }
            if (Topic.VIDEO_PARAM.equals(notifyInfo.getTopic())) {
                this.mApplication.getDeviceSettingInfo().setFrontRate(iIntValue2);
            } else {
                this.mApplication.getDeviceSettingInfo().setRearRate(iIntValue2);
            }
        }
        if (TextUtils.isEmpty(str5) || !TextUtils.isDigitsOnly(str5)) {
            return;
        }
        int iIntValue3 = IConstant.AUDIO_SAMPLE_RATE_DEFAULT;
        try {
            iIntValue3 = Integer.valueOf(str5).intValue();
        } catch (NumberFormatException e3) {
            e3.printStackTrace();
        }
        if (Topic.VIDEO_PARAM.equals(notifyInfo.getTopic())) {
            this.mApplication.getDeviceSettingInfo().setFrontSampleRate(iIntValue3);
        } else {
            this.mApplication.getDeviceSettingInfo().setRearSampleRate(iIntValue3);
        }
    }
}
