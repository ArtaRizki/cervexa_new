package com.jieli.stream.p016dv.running2.p017ui.fragment.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import com.jieli.lib.p015dv.control.json.bean.NotifyInfo;
import com.jieli.lib.p015dv.control.receiver.listener.OnNotifyListener;
import com.jieli.lib.p015dv.control.utils.Code;
import com.jieli.stream.p016dv.running2.C1438R;
import com.jieli.stream.p016dv.running2.p017ui.base.BaseFragment;
import com.jieli.stream.p016dv.running2.util.ClientManager;
import com.jieli.stream.p016dv.running2.util.Dbug;
import com.jieli.stream.p016dv.running2.util.IConstant;
import com.jieli.stream.p016dv.running2.util.PreferencesHelper;
import com.jieli.stream.p016dv.running2.util.ToastUtil;

/* JADX INFO: loaded from: classes.dex */
public class DeviceVolumeFragment extends BaseFragment {
    private SeekBar seekBar;
    private ImageButton volumeDownImageButton;
    private ImageButton volumePlusImageButton;
    private TextView volumeTextView;
    private String tag = getClass().getSimpleName();
    private final OnNotifyListener onNotifyListener = new OnNotifyListener() { // from class: com.jieli.stream.dv.running2.ui.fragment.settings.DeviceVolumeFragment.5
        @Override // com.jieli.lib.p015dv.control.receiver.listener.NotifyResponse
        public void onNotify(NotifyInfo notifyInfo) {
            if (notifyInfo.getErrorType() != 0) {
                Dbug.m1388e(DeviceVolumeFragment.this.tag, Code.getCodeDescription(notifyInfo.getErrorType()));
                ToastUtil.showToastShort(DeviceVolumeFragment.this.getString(C1438R.string.save_fail));
            } else {
                notifyInfo.getTopic().getClass();
            }
        }
    };

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(C1438R.layout.fragment_device_setting_volume, viewGroup, false);
        this.seekBar = (SeekBar) viewInflate.findViewById(C1438R.id.volume_seek_bar);
        this.volumeDownImageButton = (ImageButton) viewInflate.findViewById(C1438R.id.volume_down_img_btn);
        this.volumePlusImageButton = (ImageButton) viewInflate.findViewById(C1438R.id.volume_plus_img_btn);
        this.volumeTextView = (TextView) viewInflate.findViewById(C1438R.id.device_volume_value_tv);
        this.seekBar.setProgress(PreferencesHelper.getSharedPreferences(getContext()).getInt(IConstant.KEY_VOLUME, 0));
        this.volumeTextView.setText(String.format(getResources().getString(C1438R.string.device_volume_value), Integer.valueOf(this.seekBar.getProgress())));
        this.seekBar.setOnTouchListener(new View.OnTouchListener() { // from class: com.jieli.stream.dv.running2.ui.fragment.settings.DeviceVolumeFragment.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        this.volumeDownImageButton.setOnClickListener(new View.OnClickListener() { // from class: com.jieli.stream.dv.running2.ui.fragment.settings.DeviceVolumeFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DeviceVolumeFragment.this.seekBar.setProgress(DeviceVolumeFragment.this.seekBar.getProgress() - 10);
            }
        });
        this.volumePlusImageButton.setOnClickListener(new View.OnClickListener() { // from class: com.jieli.stream.dv.running2.ui.fragment.settings.DeviceVolumeFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DeviceVolumeFragment.this.seekBar.setProgress(DeviceVolumeFragment.this.seekBar.getProgress() + 10);
            }
        });
        this.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.jieli.stream.dv.running2.ui.fragment.settings.DeviceVolumeFragment.4
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                PreferencesHelper.putIntValue(DeviceVolumeFragment.this.getContext(), IConstant.KEY_VOLUME, i);
                DeviceVolumeFragment.this.volumeTextView.setText(String.format(DeviceVolumeFragment.this.getResources().getString(C1438R.string.device_volume_value), Integer.valueOf(seekBar.getProgress())));
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
