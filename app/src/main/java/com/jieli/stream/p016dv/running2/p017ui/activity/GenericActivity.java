package com.jieli.stream.p016dv.running2.p017ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.jieli.lib.p015dv.control.connect.listener.OnConnectStateListener;
import com.jieli.stream.p016dv.running2.C1438R;
import com.jieli.stream.p016dv.running2.p017ui.MainApplication;
import com.jieli.stream.p016dv.running2.p017ui.base.BaseActivity;
import com.jieli.stream.p016dv.running2.p017ui.base.BaseFragment;
import com.jieli.stream.p016dv.running2.p017ui.fragment.AboutFragment;
import com.jieli.stream.p016dv.running2.p017ui.fragment.AddDeviceFragment;
import com.jieli.stream.p016dv.running2.p017ui.fragment.UpgradeFragment;
import com.jieli.stream.p016dv.running2.p017ui.fragment.browse.DevPhotoFragment;
import com.jieli.stream.p016dv.running2.p017ui.fragment.browse.PhotoViewFragment;
import com.jieli.stream.p016dv.running2.p017ui.fragment.browse.VideoPlayerFragment;
import com.jieli.stream.p016dv.running2.util.ClientManager;
import com.jieli.stream.p016dv.running2.util.Dbug;
import com.jieli.stream.p016dv.running2.util.IActions;
import com.jieli.stream.p016dv.running2.util.IConstant;
import com.jieli.stream.p016dv.running2.util.PreferencesHelper;

/* JADX INFO: loaded from: classes.dex */
public class GenericActivity extends BaseActivity {
    String tag = getClass().getSimpleName();
    private OnConnectStateListener connectStateListener = new OnConnectStateListener() { // from class: com.jieli.stream.dv.running2.ui.activity.GenericActivity.1
        @Override // com.jieli.lib.p015dv.control.connect.listener.ConnectStateListener
        public void onStateChanged(Integer num) {
            int iIntValue = num.intValue();
            if (iIntValue == -1 || iIntValue == 1 || iIntValue == 3 || iIntValue == 4) {
                Dbug.m1388e(GenericActivity.this.tag, "state=" + num);
                BaseFragment baseFragment = (BaseFragment) GenericActivity.this.getSupportFragmentManager().findFragmentById(C1438R.id.generic_fragment_layout);
                if (baseFragment instanceof DevPhotoFragment) {
                    ((DevPhotoFragment) baseFragment).dismissWaitingDialog();
                }
                if (baseFragment instanceof AboutFragment) {
                    ((AboutFragment) baseFragment).dismissWaitingDialog();
                }
                GenericActivity.this.finish();
            }
        }
    };

    @Override // com.jieli.stream.p016dv.running2.p017ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1438R.layout.activity_generic);
        ClientManager.getClient().registerConnectStateListener(this.connectStateListener);
        Intent intent = getIntent();
        if (intent != null) {
            switchFragmentByTag(intent.getIntExtra(IConstant.KEY_FRAGMENT_TAG, 0), intent.getBundleExtra(IConstant.KEY_DATA));
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    @Override // com.jieli.stream.p016dv.running2.p017ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        ClientManager.getClient().unregisterConnectStateListener(this.connectStateListener);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (MainApplication.isFactoryMode) {
            PreferencesHelper.remove(this.mApplication, IConstant.CURRENT_WIFI_SSID);
            sendBroadcast(new Intent(IActions.ACTION_ACCOUT_CHANGE));
        } else {
            setResult(-1);
        }
        finish();
    }

    private void switchFragmentByTag(int i, Bundle bundle) {
        String simpleName;
        BaseFragment baseFragment = null;
        if (i == 1) {
            BaseFragment addDeviceFragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag(AddDeviceFragment.class.getSimpleName());
            if (addDeviceFragment == null) {
                addDeviceFragment = new AddDeviceFragment();
            }
            baseFragment = addDeviceFragment;
            simpleName = AddDeviceFragment.class.getSimpleName();
        } else {
            switch (i) {
                case 5:
                    BaseFragment upgradeFragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag(UpgradeFragment.class.getSimpleName());
                    if (upgradeFragment == null) {
                        upgradeFragment = new UpgradeFragment();
                    }
                    baseFragment = upgradeFragment;
                    simpleName = UpgradeFragment.class.getSimpleName();
                    break;
                case 6:
                    BaseFragment devPhotoFragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag(DevPhotoFragment.class.getSimpleName());
                    if (devPhotoFragment == null) {
                        devPhotoFragment = new DevPhotoFragment();
                    }
                    baseFragment = devPhotoFragment;
                    simpleName = DevPhotoFragment.class.getSimpleName();
                    break;
                case 7:
                    BaseFragment photoViewFragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag(PhotoViewFragment.class.getSimpleName());
                    if (photoViewFragment == null) {
                        photoViewFragment = new PhotoViewFragment();
                    }
                    baseFragment = photoViewFragment;
                    simpleName = PhotoViewFragment.class.getSimpleName();
                    break;
                case 8:
                    BaseFragment videoPlayerFragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag(VideoPlayerFragment.class.getSimpleName());
                    if (videoPlayerFragment == null) {
                        videoPlayerFragment = new VideoPlayerFragment();
                    }
                    baseFragment = videoPlayerFragment;
                    simpleName = VideoPlayerFragment.class.getSimpleName();
                    break;
                case 9:
                    BaseFragment baseFragmentNewInstance = (BaseFragment) getSupportFragmentManager().findFragmentByTag(AboutFragment.class.getSimpleName());
                    if (baseFragmentNewInstance == null) {
                        baseFragmentNewInstance = AboutFragment.newInstance();
                    }
                    baseFragment = baseFragmentNewInstance;
                    simpleName = AboutFragment.class.getSimpleName();
                    break;
                default:
                    simpleName = null;
                    break;
            }
        }
        if (baseFragment != null) {
            if (bundle != null) {
                baseFragment.setBundle(bundle);
            }
            if (!TextUtils.isEmpty(simpleName)) {
                changeFragment(C1438R.id.generic_fragment_layout, baseFragment, simpleName);
            } else {
                changeFragment(C1438R.id.generic_fragment_layout, baseFragment);
            }
        }
    }
}
