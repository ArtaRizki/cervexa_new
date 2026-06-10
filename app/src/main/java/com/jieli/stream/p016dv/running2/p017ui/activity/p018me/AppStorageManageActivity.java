package com.jieli.stream.p016dv.running2.p017ui.activity.p018me;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
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
import com.jieli.stream.p016dv.running2.C1438R;
import com.jieli.stream.p016dv.running2.bean.SettingItem;
import com.jieli.stream.p016dv.running2.p017ui.adapter.SettingAdapter;
import com.jieli.stream.p016dv.running2.p017ui.base.BaseActivity;
import com.jieli.stream.p016dv.running2.p017ui.dialog.BrowseFileDialog;
import com.jieli.stream.p016dv.running2.p017ui.dialog.NotifyDialog;
import com.jieli.stream.p016dv.running2.util.AppUtils;
import com.jieli.stream.p016dv.running2.util.Dbug;
import com.jieli.stream.p016dv.running2.util.IConstant;
import com.jieli.stream.p016dv.running2.util.PreferencesHelper;
import com.jieli.stream.p016dv.running2.util.ThumbLoader;
import com.jieli.stream.p016dv.running2.util.ToastUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AppStorageManageActivity extends BaseActivity implements BrowseFileDialog.OnSelectResultListener, AdapterView.OnItemClickListener {
    private BrowseFileDialog browseFileDialog;
    private SettingItem cacheSizeItem;
    private NotifyDialog cleanCacheDialog;
    private SettingAdapter mAdapter;
    private PieChart mChart;
    private ListView settingListView;
    private SettingItem storagePathItem;
    private String tag = getClass().getSimpleName();

    public static void start(Context context) {
        context.startActivity(new Intent(context, (Class<?>) AppStorageManageActivity.class));
    }

    @Override // com.jieli.stream.p016dv.running2.p017ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1438R.layout.activity_app_storage_manage);
        this.mChart = (PieChart) findViewById(C1438R.id.app_pie_chart);
        ((TextView) findViewById(C1438R.id.app_storage_tv)).setText(AppUtils.getExternalMemorySize(getApplicationContext()));
        ListView listView = (ListView) findViewById(C1438R.id.app_storage_view);
        this.settingListView = listView;
        listView.setOnItemClickListener(this);
        initListView();
        initChart();
        setData(AppUtils.getAvailableExternalMemorySize(), AppUtils.getExternalMemorySize() - AppUtils.getAvailableExternalMemorySize());
    }

    @Override // com.jieli.stream.p016dv.running2.p017ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        dismissClearCacheDialog();
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

    private void setData(float f, float f2) {
        ArrayList arrayList = new ArrayList();
        PieEntry pieEntry = new PieEntry(f, getString(C1438R.string.remaining_storage));
        PieEntry pieEntry2 = new PieEntry(f2, getString(C1438R.string.used_storage));
        arrayList.add(pieEntry);
        arrayList.add(pieEntry2);
        PieDataSet pieDataSet = new PieDataSet(arrayList, "");
        pieDataSet.setSelectionShift(0.0f);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(Integer.valueOf(getResources().getColor(C1438R.color.bg_pie_chart_rest)));
        arrayList2.add(Integer.valueOf(getResources().getColor(C1438R.color.bg_pie_chart_used)));
        pieDataSet.setColors(arrayList2);
        PieData pieData = new PieData(pieDataSet);
        pieData.setValueFormatter(new ValueFormatter() { // from class: com.jieli.stream.dv.running2.ui.activity.me.AppStorageManageActivity.1
            @Override // com.github.mikephil.charting.formatter.ValueFormatter
            public String getFormattedValue(float f3) {
                return Formatter.formatFileSize(AppStorageManageActivity.this.getApplicationContext(), (long) f3);
            }
        });
        pieData.setValueTextSize(10.0f);
        pieData.setValueTextColor(getResources().getColor(C1438R.color.text_white));
        this.mChart.setEntryLabelTextSize(0.0f);
        this.mChart.setData(pieData);
        this.mChart.highlightValues(null);
        this.mChart.invalidate();
    }

    public void returnBtnClick(View view) {
        onBackPressed();
    }

    @Override // com.jieli.stream.dv.running2.ui.dialog.BrowseFileDialog.OnSelectResultListener
    public void onResult(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String strSubstring = str.substring(ROOT_PATH.length());
        if (strSubstring.startsWith(File.separator)) {
            strSubstring = strSubstring.substring(strSubstring.indexOf(File.separator) + 1);
        }
        Dbug.m1389i(this.tag, " ============= newPathName : " + strSubstring + "================");
        if (strSubstring.equals(this.mApplication.getAppName())) {
            return;
        }
        PreferencesHelper.putStringValue(getApplicationContext(), IConstant.KEY_ROOT_PATH_NAME, strSubstring);
        this.mApplication.setAppName(strSubstring);
        SettingItem settingItem = this.storagePathItem;
        if (settingItem != null) {
            settingItem.setValue(ROOT_PATH + File.separator + this.mApplication.getAppName());
            this.mAdapter.notifyDataSetChanged();
        }
        ToastUtil.showToastLong(getString(C1438R.string.modify_storage_url_success));
    }

    private void initListView() {
        String cache;
        String[] stringArray = getResources().getStringArray(C1438R.array.storage_operation);
        ArrayList arrayList = new ArrayList();
        for (String str : stringArray) {
            SettingItem settingItem = new SettingItem();
            settingItem.setName(str);
            if (str.equals(getString(C1438R.string.storage_size))) {
                cache = AppUtils.getFormatSize(2.097152E8d);
                settingItem.setType(2);
            } else if (str.equals(getString(C1438R.string.storage_path))) {
                cache = ROOT_PATH + File.separator + this.mApplication.getAppName();
                this.storagePathItem = settingItem;
            } else if (str.equals(getString(C1438R.string.clean_cache))) {
                cache = getCache();
                this.cacheSizeItem = settingItem;
            } else {
                cache = "";
            }
            settingItem.setValue(cache);
            arrayList.add(settingItem);
        }
        SettingAdapter settingAdapter = new SettingAdapter(this, arrayList);
        this.mAdapter = settingAdapter;
        this.settingListView.setAdapter((ListAdapter) settingAdapter);
        this.mAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getCache() {
        List<String> listQueryThumbDirPath = AppUtils.queryThumbDirPath(AppUtils.splicingFilePath(this.mApplication.getAppName(), null, null, null));
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
        return AppUtils.getFormatSize(folderSize);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCache() {
        List<String> listQueryThumbDirPath = AppUtils.queryThumbDirPath(AppUtils.splicingFilePath(this.mApplication.getAppName(), null, null, null));
        if (listQueryThumbDirPath == null || listQueryThumbDirPath.size() <= 0) {
            return;
        }
        Iterator<String> it = listQueryThumbDirPath.iterator();
        while (it.hasNext()) {
            File file = new File(it.next());
            if (file.exists()) {
                AppUtils.deleteFile(file);
            }
        }
        ThumbLoader.getInstance().clearCache();
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        SettingItem settingItem;
        SettingAdapter settingAdapter = this.mAdapter;
        if (settingAdapter == null || (settingItem = (SettingItem) settingAdapter.getItem(i)) == null) {
            return;
        }
        String name = settingItem.getName();
        if (name.equals(getString(C1438R.string.storage_size))) {
            return;
        }
        if (name.equals(getString(C1438R.string.storage_path))) {
            BrowseFileDialog browseFileDialog = new BrowseFileDialog();
            this.browseFileDialog = browseFileDialog;
            browseFileDialog.setOnSelectResultListener(this);
            this.browseFileDialog.show(getSupportFragmentManager(), "browse_file_dialog");
            return;
        }
        if (name.equals(getString(C1438R.string.clean_cache))) {
            showClearCacheDialog();
        }
    }

    private void showClearCacheDialog() {
        if (this.cleanCacheDialog == null) {
            this.cleanCacheDialog = NotifyDialog.newInstance(C1438R.string.dialog_tips, C1438R.string.clean_cache_content, C1438R.string.dialog_cancel, C1438R.string.dialog_confirm, new NotifyDialog.OnNegativeClickListener() { // from class: com.jieli.stream.dv.running2.ui.activity.me.AppStorageManageActivity.2
                @Override // com.jieli.stream.dv.running2.ui.dialog.NotifyDialog.OnNegativeClickListener
                public void onClick() {
                    AppStorageManageActivity.this.cleanCacheDialog.dismiss();
                }
            }, new NotifyDialog.OnPositiveClickListener() { // from class: com.jieli.stream.dv.running2.ui.activity.me.AppStorageManageActivity.3
                @Override // com.jieli.stream.dv.running2.ui.dialog.NotifyDialog.OnPositiveClickListener
                public void onClick() {
                    AppStorageManageActivity.this.cleanCacheDialog.dismiss();
                    AppStorageManageActivity.this.clearCache();
                    AppStorageManageActivity.this.cacheSizeItem.setValue(AppStorageManageActivity.this.getCache());
                    AppStorageManageActivity.this.mAdapter.notifyDataSetChanged();
                }
            });
        }
        if (this.cleanCacheDialog.isShowing()) {
            return;
        }
        this.cleanCacheDialog.show(getSupportFragmentManager(), "clean_cache");
    }

    private void dismissClearCacheDialog() {
        NotifyDialog notifyDialog = this.cleanCacheDialog;
        if (notifyDialog != null) {
            if (notifyDialog.isShowing()) {
                this.cleanCacheDialog.dismiss();
            }
            this.cleanCacheDialog = null;
        }
    }
}
