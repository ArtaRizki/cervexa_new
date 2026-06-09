package com.jieli.stream.p016dv.running2.p017ui.base;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.jieli.stream.p016dv.running2.p017ui.MainApplication;
import com.jieli.stream.p016dv.running2.util.ActivityManager;
import com.jieli.stream.p016dv.running2.util.IActions;
import com.jieli.stream.p016dv.running2.util.IConstant;
import com.jieli.stream.p016dv.running2.util.WifiHelper;
import com.serenegiant.net.NetworkChangedReceiver;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseActivity extends FragmentActivity implements IConstant, IActions {
    private final String TAG = getClass().getSimpleName();
    public MainApplication mApplication;
    private BaseWifiBroadcastReceiver mReceiver;
    public WifiHelper mWifiHelper;

    private class BaseWifiBroadcastReceiver extends BroadcastReceiver {
        private BaseWifiBroadcastReceiver() {
        }

        /* JADX WARN: Removed duplicated region for block: B:23:0x0044  */
        @Override // android.content.BroadcastReceiver
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onReceive(android.content.Context r8, android.content.Intent r9) {
            /*
                Method dump skipped, instruction units count: 491
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jieli.stream.dv.running2.ui.base.BaseActivity.BaseWifiBroadcastReceiver.onReceive(android.content.Context, android.content.Intent):void");
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        MainApplication application = MainApplication.getApplication();
        this.mApplication = application;
        this.mWifiHelper = WifiHelper.getInstance(application);
        ActivityManager.getInstance().pushActivity(this);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        registerBroadCastReceiver();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        if (this.mReceiver != null) {
            MainApplication.getApplication().unregisterReceiver(this.mReceiver);
            this.mReceiver = null;
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().popActivity(this);
    }

    private void registerBroadCastReceiver() {
        if (this.mReceiver == null) {
            this.mReceiver = new BaseWifiBroadcastReceiver();
        }
        IntentFilter intentFilter = new IntentFilter("android.net.wifi.STATE_CHANGE");
        intentFilter.addAction("android.net.wifi.supplicant.STATE_CHANGE");
        intentFilter.addAction(NetworkChangedReceiver.ACTION_GLOBAL_CONNECTIVITY_CHANGE);
        MainApplication.getApplication().registerReceiver(this.mReceiver, intentFilter);
    }

    public void changeFragment(int i, Fragment fragment, String str) {
        FragmentManager supportFragmentManager;
        FragmentTransaction fragmentTransactionBeginTransaction;
        if (fragment == null || isFinishing() || isDestroyed() || (supportFragmentManager = getSupportFragmentManager()) == null || (fragmentTransactionBeginTransaction = supportFragmentManager.beginTransaction()) == null) {
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            fragmentTransactionBeginTransaction.replace(i, fragment, str);
        } else {
            fragmentTransactionBeginTransaction.replace(i, fragment);
        }
        fragmentTransactionBeginTransaction.addToBackStack(null);
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
    }

    public void changeFragment(int i, Fragment fragment) {
        changeFragment(i, fragment, null);
    }
}
