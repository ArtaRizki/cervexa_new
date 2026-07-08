package com.jieli.stream.p016dv.running2.p017ui.fragment.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.jieli.lib.p015dv.control.connect.response.SendResponse;
import com.jieli.lib.p015dv.control.receiver.listener.OnNotifyListener;
import com.jieli.stream.p016dv.running2.p017ui.base.BaseFragment;
import com.jieli.stream.p016dv.running2.p017ui.dialog.NotifyDialog;
import com.jieli.stream.p016dv.running2.util.ClientManager;
import com.jieli.stream.p016dv.running2.util.Dbug;
import com.jieli.stream.p016dv.running2.util.ToastUtil;
import java.util.ArrayList;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class DeviceStorageManageFragment extends BaseFragment {
    private ImageButton delPhotoIbtn;
    private ImageButton delVideoIbtn;
    private Button formatBtn;
    private NotifyDialog formatDialog;
    private PieChart mChart;
    private NotifyDialog mNotifyDialog;
    private TextView tfCapTextView;
    private String tag = getClass().getSimpleName();
    private final OnNotifyListener onNotifyListener = new OnNotifyListener() { // from class: com.jieli.stream.dv.running2.ui.fragment.settings.DeviceStorageManageFragment.5
        /* JADX WARN: Removed duplicated region for block: B:30:0x0072  */
        @Override // com.jieli.lib.p015dv.control.receiver.listener.NotifyResponse
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onNotify(com.jieli.lib.p015dv.control.json.bean.NotifyInfo r10) {
            /*
                Method dump skipped, instruction units count: 275
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jieli.stream.p016dv.running2.p017ui.fragment.settings.DeviceStorageManageFragment.C17055.onNotify(com.jieli.lib.dv.control.json.bean.NotifyInfo):void");
        }
    };

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(com.weioa.KmedHealthIndonesia.R.layout.fragment_device_storage_manage, viewGroup, false);
        this.delPhotoIbtn = (ImageButton) viewInflate.findViewById(com.weioa.KmedHealthIndonesia.R.id.photo_del_ibtn);
        this.delVideoIbtn = (ImageButton) viewInflate.findViewById(com.weioa.KmedHealthIndonesia.R.id.video_del_ibtn);
        this.formatBtn = (Button) viewInflate.findViewById(com.weioa.KmedHealthIndonesia.R.id.device_storage_format_btn);
        this.tfCapTextView = (TextView) viewInflate.findViewById(com.weioa.KmedHealthIndonesia.R.id.tf_cap_tv);
        this.mChart = (PieChart) viewInflate.findViewById(com.weioa.KmedHealthIndonesia.R.id.pie_chart);
        int leftStorage = this.mApplication.getDeviceSettingInfo().getLeftStorage();
        int totalStorage = this.mApplication.getDeviceSettingInfo().getTotalStorage();
        if (totalStorage > 1024) {
            this.tfCapTextView.setText(String.format(Locale.getDefault(), "%.2f", Float.valueOf(totalStorage / 1024.0f)) + "GB");
        } else {
            this.tfCapTextView.setText(totalStorage + "MB");
        }
        initChart();
        setData(leftStorage, totalStorage - leftStorage);
        this.formatBtn.setOnClickListener(new View.OnClickListener() { // from class: com.jieli.stream.dv.running2.ui.fragment.settings.DeviceStorageManageFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (1 == DeviceStorageManageFragment.this.mApplication.getDeviceSettingInfo().getRecordState()) {
                    DeviceStorageManageFragment.this.showStopRecordingDialog();
                } else {
                    DeviceStorageManageFragment.this.showFormatDevice();
                }
            }
        });
        this.delPhotoIbtn.setOnClickListener(new View.OnClickListener() { // from class: com.jieli.stream.dv.running2.ui.fragment.settings.DeviceStorageManageFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ToastUtil.showToastShort(DeviceStorageManageFragment.this.getString(com.weioa.KmedHealthIndonesia.R.string.save_success));
            }
        });
        this.delVideoIbtn.setOnClickListener(new View.OnClickListener() { // from class: com.jieli.stream.dv.running2.ui.fragment.settings.DeviceStorageManageFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ToastUtil.showToastShort(DeviceStorageManageFragment.this.getString(com.weioa.KmedHealthIndonesia.R.string.save_success));
            }
        });
        return viewInflate;
    }

    private void initChart() {
        this.mChart.getDescription().setEnabled(false);
        this.mChart.setExtraOffsets(0.0f, 10.0f, 0.0f, 0.0f);
        this.mChart.setDragDecelerationFrictionCoef(0.95f);
        this.mChart.setDrawHoleEnabled(false);
        this.mChart.setRotationAngle(-90.0f);
        this.mChart.setRotationEnabled(false);
        this.mChart.animateY(1400, Easing.EaseInOutQuad);
        this.mChart.getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setData(float f, float f2) {
        this.mChart.clear();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new PieEntry(f, getString(com.weioa.KmedHealthIndonesia.R.string.remaining_storage)));
        arrayList.add(new PieEntry(f2, getString(com.weioa.KmedHealthIndonesia.R.string.used_storage)));
        PieDataSet pieDataSet = new PieDataSet(arrayList, "");
        pieDataSet.setSelectionShift(0.0f);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(Integer.valueOf(getResources().getColor(com.weioa.KmedHealthIndonesia.R.color.bg_pie_chart_rest)));
        arrayList2.add(Integer.valueOf(getResources().getColor(com.weioa.KmedHealthIndonesia.R.color.bg_pie_chart_used)));
        pieDataSet.setColors(arrayList2);
        PieData pieData = new PieData(pieDataSet);
        pieData.setValueFormatter(new ValueFormatter() { // from class: com.jieli.stream.dv.running2.ui.fragment.settings.DeviceStorageManageFragment.4
            @Override // com.github.mikephil.charting.formatter.ValueFormatter
            public String getFormattedValue(float f3) {
                if (f3 > 1024.0f) {
                    return String.format(Locale.getDefault(), "%.2f", Float.valueOf(f3 / 1024.0f)) + "GB";
                }
                if (f3 < 0.01f) {
                    return "";
                }
                return ((int) f3) + "MB";
            }
        });
        pieData.setValueTextSize(10.0f);
        pieData.setValueTextColor(getResources().getColor(com.weioa.KmedHealthIndonesia.R.color.text_white));
        this.mChart.setEntryLabelTextSize(0.0f);
        this.mChart.setData(pieData);
        this.mChart.highlightValues(null);
        this.mChart.invalidate();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        ClientManager.getClient().registerNotifyListener(this.onNotifyListener);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        NotifyDialog notifyDialog = this.mNotifyDialog;
        if (notifyDialog != null && notifyDialog.isShowing()) {
            this.mNotifyDialog.dismiss();
        }
        this.mNotifyDialog = null;
        ClientManager.getClient().unregisterNotifyListener(this.onNotifyListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showFormatDevice() {
        if (this.formatDialog == null) {
            this.formatDialog = NotifyDialog.newInstance(com.weioa.KmedHealthIndonesia.R.string.dialog_tips, com.weioa.KmedHealthIndonesia.R.string.format, com.weioa.KmedHealthIndonesia.R.string.dialog_cancel, com.weioa.KmedHealthIndonesia.R.string.dialog_confirm, new NotifyDialog.OnNegativeClickListener() { // from class: com.jieli.stream.dv.running2.ui.fragment.settings.DeviceStorageManageFragment.6
                @Override // com.jieli.stream.dv.running2.ui.dialog.NotifyDialog.OnNegativeClickListener
                public void onClick() {
                    DeviceStorageManageFragment.this.formatDialog.dismiss();
                }
            }, new NotifyDialog.OnPositiveClickListener() { // from class: com.jieli.stream.dv.running2.ui.fragment.settings.DeviceStorageManageFragment.7
                @Override // com.jieli.stream.dv.running2.ui.dialog.NotifyDialog.OnPositiveClickListener
                public void onClick() {
                    ClientManager.getClient().tryToFormatTFCard(new SendResponse() { // from class: com.jieli.stream.dv.running2.ui.fragment.settings.DeviceStorageManageFragment.7.1
                        @Override // com.jieli.lib.p015dv.control.connect.response.Response
                        public void onResponse(Integer num) {
                            if (num.intValue() != 1) {
                                ToastUtil.showToastShort(DeviceStorageManageFragment.this.getString(com.weioa.KmedHealthIndonesia.R.string.format_failed));
                            }
                        }
                    });
                    DeviceStorageManageFragment.this.formatDialog.dismiss();
                }
            });
        }
        if (this.formatDialog.isShowing()) {
            return;
        }
        this.formatDialog.show(getFragmentManager(), "formatDialog");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showStopRecordingDialog() {
        if (this.mNotifyDialog == null) {
            NotifyDialog notifyDialogNewInstance = NotifyDialog.newInstance(com.weioa.KmedHealthIndonesia.R.string.dialog_tips, com.weioa.KmedHealthIndonesia.R.string.stop_recording_tips, com.weioa.KmedHealthIndonesia.R.string.dialog_cancel, com.weioa.KmedHealthIndonesia.R.string.dialog_confirm, new NotifyDialog.OnNegativeClickListener() { // from class: com.jieli.stream.dv.running2.ui.fragment.settings.DeviceStorageManageFragment.8
                @Override // com.jieli.stream.dv.running2.ui.dialog.NotifyDialog.OnNegativeClickListener
                public void onClick() {
                    DeviceStorageManageFragment.this.mNotifyDialog.dismiss();
                }
            }, new NotifyDialog.OnPositiveClickListener() { // from class: com.jieli.stream.dv.running2.ui.fragment.settings.DeviceStorageManageFragment.9
                @Override // com.jieli.stream.dv.running2.ui.dialog.NotifyDialog.OnPositiveClickListener
                public void onClick() {
                    ClientManager.getClient().tryToRecordVideo(false, new SendResponse() { // from class: com.jieli.stream.dv.running2.ui.fragment.settings.DeviceStorageManageFragment.9.1
                        @Override // com.jieli.lib.p015dv.control.connect.response.Response
                        public void onResponse(Integer num) {
                            if (num.intValue() != 1) {
                                DeviceStorageManageFragment.this.mNotifyDialog.dismiss();
                                ToastUtil.showToastShort(DeviceStorageManageFragment.this.getString(com.weioa.KmedHealthIndonesia.R.string.operation_failed_and_try_again));
                                Dbug.m1388e(DeviceStorageManageFragment.this.tag, "Send failed");
                            }
                        }
                    });
                }
            });
            this.mNotifyDialog = notifyDialogNewInstance;
            notifyDialogNewInstance.setCancelable(false);
        }
        if (this.mNotifyDialog.isShowing()) {
            return;
        }
        this.mNotifyDialog.show(getActivity().getSupportFragmentManager(), "notify_dialog");
    }
}
