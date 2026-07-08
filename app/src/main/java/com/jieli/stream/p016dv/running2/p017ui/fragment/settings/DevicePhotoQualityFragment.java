package com.jieli.stream.p016dv.running2.p017ui.fragment.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.jieli.lib.p015dv.control.connect.response.SendResponse;
import com.jieli.lib.p015dv.control.json.bean.NotifyInfo;
import com.jieli.lib.p015dv.control.receiver.listener.OnNotifyListener;
import com.jieli.lib.p015dv.control.utils.Code;
import com.jieli.lib.p015dv.control.utils.Topic;
import com.jieli.lib.p015dv.control.utils.TopicKey;
import com.jieli.stream.p016dv.running2.p017ui.base.BaseFragment;
import com.jieli.stream.p016dv.running2.util.ClientManager;
import com.jieli.stream.p016dv.running2.util.Dbug;
import com.jieli.stream.p016dv.running2.util.ToastUtil;

/* JADX INFO: loaded from: classes.dex */
public class DevicePhotoQualityFragment extends BaseFragment {
    private TextView photoQulityTextView;
    private RadioGroup radioGroup;
    private TextView timeTextView;
    private String tag = getClass().getSimpleName();
    private final OnNotifyListener onNotifyListener = new OnNotifyListener() { // from class: com.jieli.stream.dv.running2.ui.fragment.settings.DevicePhotoQualityFragment.2
        @Override // com.jieli.lib.p015dv.control.receiver.listener.NotifyResponse
        public void onNotify(NotifyInfo notifyInfo) {
            if (notifyInfo.getErrorType() != 0) {
                Dbug.m1388e(DevicePhotoQualityFragment.this.tag, Code.getCodeDescription(notifyInfo.getErrorType()));
                String topic = notifyInfo.getTopic();
                if (((topic.hashCode() == 1826555826 && topic.equals(Topic.PHOTO_QUALITY)) ? (byte) 0 : (byte) -1) != 0) {
                    return;
                }
                ToastUtil.showToastShort(DevicePhotoQualityFragment.this.getString(com.weioa.KmedHealthIndonesia.R.string.setting_failed));
                return;
            }
            String topic2 = notifyInfo.getTopic();
            if (((topic2.hashCode() == 1826555826 && topic2.equals(Topic.PHOTO_QUALITY)) ? (byte) 0 : (byte) -1) != 0) {
                return;
            }
            ToastUtil.showToastShort(DevicePhotoQualityFragment.this.getString(com.weioa.KmedHealthIndonesia.R.string.setting_successed));
            Dbug.m1388e(DevicePhotoQualityFragment.this.tag, "qua=" + Integer.valueOf(notifyInfo.getParams().get(TopicKey.QUA)));
        }
    };

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(com.weioa.KmedHealthIndonesia.R.layout.fragment_device_setting_photo_qulity, viewGroup, false);
        this.radioGroup = (RadioGroup) viewInflate.findViewById(com.weioa.KmedHealthIndonesia.R.id.photo_qulity_radio_group);
        this.photoQulityTextView = (TextView) viewInflate.findViewById(com.weioa.KmedHealthIndonesia.R.id.photo_qulity_value);
        this.timeTextView = (TextView) viewInflate.findViewById(com.weioa.KmedHealthIndonesia.R.id.recording_time);
        final String[] stringArray = getResources().getStringArray(com.weioa.KmedHealthIndonesia.R.array.photo_qulity);
        final int photoQualityIndex = this.mApplication.getDeviceSettingInfo().getPhotoQualityIndex();
        ((RadioButton) this.radioGroup.getChildAt(photoQualityIndex)).setChecked(true);
        this.photoQulityTextView.setText(String.format(getString(com.weioa.KmedHealthIndonesia.R.string.photo_qulity_value), stringArray[photoQualityIndex], Integer.valueOf((photoQualityIndex + 1) * 240)));
        this.timeTextView.setText(String.format(getString(com.weioa.KmedHealthIndonesia.R.string.recorded_time), "12:12:12"));
        this.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.jieli.stream.dv.running2.ui.fragment.settings.DevicePhotoQualityFragment.1
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int iIndexOfChild = radioGroup.indexOfChild(DevicePhotoQualityFragment.this.radioGroup.findViewById(i));
                DevicePhotoQualityFragment.this.photoQulityTextView.setText(String.format(DevicePhotoQualityFragment.this.getString(com.weioa.KmedHealthIndonesia.R.string.photo_qulity_value), stringArray[iIndexOfChild], Integer.valueOf((iIndexOfChild + 1) * 240)));
                ClientManager.getClient().tryToSetPhotoQuality(iIndexOfChild, new SendResponse() { // from class: com.jieli.stream.dv.running2.ui.fragment.settings.DevicePhotoQualityFragment.1.1
                    @Override // com.jieli.lib.p015dv.control.connect.response.Response
                    public void onResponse(Integer num) {
                        if (num.intValue() != 1) {
                            ToastUtil.showToastShort(DevicePhotoQualityFragment.this.getString(com.weioa.KmedHealthIndonesia.R.string.setting_failed));
                            ((RadioButton) DevicePhotoQualityFragment.this.radioGroup.getChildAt(photoQualityIndex)).setChecked(true);
                        }
                    }
                });
            }
        });
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        ClientManager.getClient().registerNotifyListener(this.onNotifyListener);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        ClientManager.getClient().unregisterNotifyListener(this.onNotifyListener);
    }
}
