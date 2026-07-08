package com.jieli.stream.p016dv.running2.p017ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import com.jieli.stream.p016dv.running2.p017ui.activity.MainActivity;
import com.jieli.stream.p016dv.running2.p017ui.base.BaseFragment;
import com.jieli.stream.p016dv.running2.p017ui.dialog.QRCodeDialog;
import com.jieli.stream.p016dv.running2.util.IConstant;
import com.jieli.stream.p016dv.running2.util.ToastUtil;
import com.jieli.stream.p016dv.running2.util.json.listener.OnCompletedListener;

/* JADX INFO: loaded from: classes.dex */
public class QRCodeFragment extends BaseFragment implements View.OnClickListener {
    private boolean isShowPwd;
    private Button mBackToSearchButton;
    private EditText mEditWifiPwd;
    private EditText mEditWifiSSID;
    private Button mGenerateQRCodeButton;
    private ImageView mPasswordView;
    private Button mReturnButton;
    private CheckBox mSaveInfoCheckbox;
    private String tag = getClass().getSimpleName();

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(com.weioa.KmedHealthIndonesia.R.layout.fragment_sta_qr_code, viewGroup, false);
        this.mEditWifiSSID = (EditText) viewInflate.findViewById(com.weioa.KmedHealthIndonesia.R.id.edit_hot_spot_wifi);
        this.mEditWifiPwd = (EditText) viewInflate.findViewById(com.weioa.KmedHealthIndonesia.R.id.edit_hot_spot_pwd);
        this.mGenerateQRCodeButton = (Button) viewInflate.findViewById(com.weioa.KmedHealthIndonesia.R.id.generate_qr_code_btn);
        this.mPasswordView = (ImageView) viewInflate.findViewById(com.weioa.KmedHealthIndonesia.R.id.show_or_hide_pwd);
        this.mReturnButton = (Button) viewInflate.findViewById(com.weioa.KmedHealthIndonesia.R.id.hot_spot_return_btn);
        this.mSaveInfoCheckbox = (CheckBox) viewInflate.findViewById(com.weioa.KmedHealthIndonesia.R.id.save_sta_msg);
        this.mBackToSearchButton = (Button) viewInflate.findViewById(com.weioa.KmedHealthIndonesia.R.id.back_to_search_btn);
        this.mGenerateQRCodeButton.setOnClickListener(this);
        this.mPasswordView.setOnClickListener(this);
        this.mReturnButton.setOnClickListener(this);
        this.mBackToSearchButton.setOnClickListener(this);
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.mGenerateQRCodeButton) {
            showQRCodeDialog(this.mEditWifiSSID.getText().toString().trim(), this.mEditWifiPwd.getText().toString().trim());
            return;
        }
        if (view == this.mPasswordView) {
            this.isShowPwd = !this.isShowPwd;
            handlerPwdUI();
        } else if (view == this.mReturnButton) {
            toDeviceListFragment(null);
        } else if (view == this.mBackToSearchButton) {
            toStaDeviceListFragment();
        }
    }

    private void handlerPwdUI() {
        if (this.isShowPwd) {
            this.mEditWifiPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            this.mPasswordView.setImageResource(com.weioa.KmedHealthIndonesia.R.drawable.dbg_show_pwd_selector);
        } else {
            this.mEditWifiPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
            this.mEditWifiPwd.requestFocus();
            this.mPasswordView.setImageResource(com.weioa.KmedHealthIndonesia.R.mipmap.ic_hide_pwd);
        }
    }

    private void showQRCodeDialog(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            ToastUtil.showToastShort(getString(com.weioa.KmedHealthIndonesia.R.string.wifi_ssid_empty_tip));
            return;
        }
        if (!TextUtils.isEmpty(str2) && str2.length() < 8) {
            ToastUtil.showToastShort(getString(com.weioa.KmedHealthIndonesia.R.string.pwd_lenth_limits));
            return;
        }
        QRCodeDialog qRCodeDialogNewInstance = QRCodeDialog.newInstance(str, str2, this.mSaveInfoCheckbox.isChecked());
        qRCodeDialogNewInstance.setOnCompletedListener(new OnCompletedListener<Boolean>() { // from class: com.jieli.stream.dv.running2.ui.fragment.QRCodeFragment.1
            @Override // com.jieli.stream.p016dv.running2.util.json.listener.OnCompletedListener
            public void onCompleted(Boolean bool) {
                if (bool.booleanValue()) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(IConstant.KEY_SEARCH_MODE, 1);
                    QRCodeFragment.this.toDeviceListFragment(bundle);
                }
            }
        });
        if (qRCodeDialogNewInstance.isShowing()) {
            return;
        }
        qRCodeDialogNewInstance.show(getActivity().getSupportFragmentManager(), "QRDialog");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toDeviceListFragment(Bundle bundle) {
        Fragment fragmentFindFragmentById = getActivity().getSupportFragmentManager().findFragmentById(com.weioa.KmedHealthIndonesia.R.id.container);
        if (!(fragmentFindFragmentById instanceof DeviceListFragment)) {
            fragmentFindFragmentById = new DeviceListFragment();
        }
        fragmentFindFragmentById.setArguments(bundle);
        ((MainActivity) getActivity()).changeFragment(com.weioa.KmedHealthIndonesia.R.id.container, fragmentFindFragmentById, fragmentFindFragmentById.getClass().getSimpleName());
    }

    private void toStaDeviceListFragment() {
        Fragment staDeviceListFragment = (BaseFragment) getActivity().getSupportFragmentManager().findFragmentByTag(StaDeviceListFragment.class.getSimpleName());
        if (staDeviceListFragment == null) {
            staDeviceListFragment = new StaDeviceListFragment();
        }
        ((MainActivity) getActivity()).changeFragment(com.weioa.KmedHealthIndonesia.R.id.container, staDeviceListFragment, staDeviceListFragment.getClass().getSimpleName());
    }
}
