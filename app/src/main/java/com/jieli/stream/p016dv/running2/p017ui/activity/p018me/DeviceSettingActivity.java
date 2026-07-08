package com.jieli.stream.p016dv.running2.p017ui.activity.p018me;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import com.jieli.stream.p016dv.running2.p017ui.base.BaseActivity;
import com.jieli.stream.p016dv.running2.p017ui.fragment.settings.DeviceAdvancedSettingFragment;
import com.jieli.stream.p016dv.running2.p017ui.fragment.settings.DeviceCameraModeFragment;
import com.jieli.stream.p016dv.running2.p017ui.fragment.settings.DeviceNameFragment;
import com.jieli.stream.p016dv.running2.p017ui.fragment.settings.DevicePhotoQualityFragment;
import com.jieli.stream.p016dv.running2.p017ui.fragment.settings.DevicePwdFragment;
import com.jieli.stream.p016dv.running2.p017ui.fragment.settings.DeviceSettingFragment;
import com.jieli.stream.p016dv.running2.p017ui.fragment.settings.DeviceStaModeFragment;
import com.jieli.stream.p016dv.running2.p017ui.fragment.settings.DeviceStorageManageFragment;
import com.jieli.stream.p016dv.running2.p017ui.fragment.settings.DeviceVolumeFragment;
import com.jieli.stream.p016dv.running2.p017ui.fragment.settings.RecordQualityFragment;
import com.jieli.stream.p016dv.running2.util.Dbug;

/* JADX INFO: loaded from: classes.dex */
public class DeviceSettingActivity extends BaseActivity {
    private String tag = getClass().getSimpleName();

    public static void start(Context context) {
        context.startActivity(new Intent(context, (Class<?>) DeviceSettingActivity.class));
    }

    @Override // com.jieli.stream.p016dv.running2.p017ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(com.weioa.KmedHealthIndonesia.R.layout.activity_generic);
        toDeviceSettingFragment();
    }

    public void toDeviceSettingFragment() {
        Fragment fragmentFindFragmentById = getSupportFragmentManager().findFragmentById(com.weioa.KmedHealthIndonesia.R.id.generic_fragment_layout);
        if (!(fragmentFindFragmentById instanceof DeviceSettingFragment) && (fragmentFindFragmentById = getSupportFragmentManager().findFragmentByTag(DeviceSettingFragment.class.getSimpleName())) == null) {
            fragmentFindFragmentById = new DeviceSettingFragment();
        }
        changeFragment(com.weioa.KmedHealthIndonesia.R.id.generic_fragment_layout, fragmentFindFragmentById);
    }

    public void toDeviceNameFragment() {
        Dbug.m1388e(this.tag, "toDeviceNameFragment");
        Fragment fragmentFindFragmentById = getSupportFragmentManager().findFragmentById(com.weioa.KmedHealthIndonesia.R.id.generic_fragment_layout);
        if (!(fragmentFindFragmentById instanceof DeviceNameFragment) && (fragmentFindFragmentById = getSupportFragmentManager().findFragmentByTag(DeviceNameFragment.class.getSimpleName())) == null) {
            fragmentFindFragmentById = new DeviceNameFragment();
        }
        changeFragment(com.weioa.KmedHealthIndonesia.R.id.generic_fragment_layout, fragmentFindFragmentById, fragmentFindFragmentById.getClass().getSimpleName());
    }

    public void toDevicePwdFragment() {
        Fragment fragmentFindFragmentById = getSupportFragmentManager().findFragmentById(com.weioa.KmedHealthIndonesia.R.id.generic_fragment_layout);
        if (!(fragmentFindFragmentById instanceof DevicePwdFragment) && (fragmentFindFragmentById = getSupportFragmentManager().findFragmentByTag(DevicePwdFragment.class.getSimpleName())) == null) {
            fragmentFindFragmentById = new DevicePwdFragment();
        }
        changeFragment(com.weioa.KmedHealthIndonesia.R.id.generic_fragment_layout, fragmentFindFragmentById, fragmentFindFragmentById.getClass().getSimpleName());
    }

    public void toDeviceVolumeFragment() {
        Fragment fragmentFindFragmentById = getSupportFragmentManager().findFragmentById(com.weioa.KmedHealthIndonesia.R.id.generic_fragment_layout);
        if (!(fragmentFindFragmentById instanceof DeviceVolumeFragment) && (fragmentFindFragmentById = getSupportFragmentManager().findFragmentByTag(DeviceVolumeFragment.class.getSimpleName())) == null) {
            fragmentFindFragmentById = new DeviceVolumeFragment();
        }
        changeFragment(com.weioa.KmedHealthIndonesia.R.id.generic_fragment_layout, fragmentFindFragmentById, fragmentFindFragmentById.getClass().getSimpleName());
    }

    public void toDevicePictureQualityFragment() {
        Fragment fragmentFindFragmentById = getSupportFragmentManager().findFragmentById(com.weioa.KmedHealthIndonesia.R.id.generic_fragment_layout);
        if (!(fragmentFindFragmentById instanceof DevicePhotoQualityFragment) && (fragmentFindFragmentById = getSupportFragmentManager().findFragmentByTag(DevicePhotoQualityFragment.class.getSimpleName())) == null) {
            fragmentFindFragmentById = new DevicePhotoQualityFragment();
        }
        changeFragment(com.weioa.KmedHealthIndonesia.R.id.generic_fragment_layout, fragmentFindFragmentById, fragmentFindFragmentById.getClass().getSimpleName());
    }

    public void toDeviceRecordQualityFragment() {
        Fragment fragmentFindFragmentById = getSupportFragmentManager().findFragmentById(com.weioa.KmedHealthIndonesia.R.id.generic_fragment_layout);
        if (!(fragmentFindFragmentById instanceof RecordQualityFragment) && (fragmentFindFragmentById = getSupportFragmentManager().findFragmentByTag(RecordQualityFragment.class.getSimpleName())) == null) {
            fragmentFindFragmentById = new RecordQualityFragment();
        }
        changeFragment(com.weioa.KmedHealthIndonesia.R.id.generic_fragment_layout, fragmentFindFragmentById, fragmentFindFragmentById.getClass().getSimpleName());
    }

    public void toDeviceCameraModeFragment() {
        Fragment fragmentFindFragmentById = getSupportFragmentManager().findFragmentById(com.weioa.KmedHealthIndonesia.R.id.generic_fragment_layout);
        if (!(fragmentFindFragmentById instanceof DeviceCameraModeFragment) && (fragmentFindFragmentById = getSupportFragmentManager().findFragmentByTag(DeviceCameraModeFragment.class.getSimpleName())) == null) {
            fragmentFindFragmentById = new DeviceCameraModeFragment();
        }
        changeFragment(com.weioa.KmedHealthIndonesia.R.id.generic_fragment_layout, fragmentFindFragmentById, fragmentFindFragmentById.getClass().getSimpleName());
    }

    public void toDeviceAdvancedSettingFragment() {
        Fragment fragmentFindFragmentById = getSupportFragmentManager().findFragmentById(com.weioa.KmedHealthIndonesia.R.id.generic_fragment_layout);
        if (!(fragmentFindFragmentById instanceof DeviceAdvancedSettingFragment) && (fragmentFindFragmentById = getSupportFragmentManager().findFragmentByTag(DeviceAdvancedSettingFragment.class.getSimpleName())) == null) {
            fragmentFindFragmentById = new DeviceAdvancedSettingFragment();
        }
        changeFragment(com.weioa.KmedHealthIndonesia.R.id.generic_fragment_layout, fragmentFindFragmentById, fragmentFindFragmentById.getClass().getSimpleName());
    }

    public void toDeviceStorageManageFragment() {
        Fragment fragmentFindFragmentById = getSupportFragmentManager().findFragmentById(com.weioa.KmedHealthIndonesia.R.id.generic_fragment_layout);
        if (!(fragmentFindFragmentById instanceof DeviceStorageManageFragment) && (fragmentFindFragmentById = getSupportFragmentManager().findFragmentByTag(DeviceStorageManageFragment.class.getSimpleName())) == null) {
            fragmentFindFragmentById = new DeviceStorageManageFragment();
        }
        changeFragment(com.weioa.KmedHealthIndonesia.R.id.generic_fragment_layout, fragmentFindFragmentById, fragmentFindFragmentById.getClass().getSimpleName());
    }

    public void toDeviceStaModeFragment() {
        Fragment fragmentFindFragmentById = getSupportFragmentManager().findFragmentById(com.weioa.KmedHealthIndonesia.R.id.generic_fragment_layout);
        if (!(fragmentFindFragmentById instanceof DeviceStaModeFragment) && (fragmentFindFragmentById = getSupportFragmentManager().findFragmentByTag(DeviceStaModeFragment.class.getSimpleName())) == null) {
            fragmentFindFragmentById = new DeviceStaModeFragment();
        }
        changeFragment(com.weioa.KmedHealthIndonesia.R.id.generic_fragment_layout, fragmentFindFragmentById, fragmentFindFragmentById.getClass().getSimpleName());
    }

    public void returnBtnClick(View view) {
        onBackPressed();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }
}
