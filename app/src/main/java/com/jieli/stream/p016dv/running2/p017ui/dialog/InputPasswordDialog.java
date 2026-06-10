package com.jieli.stream.p016dv.running2.p017ui.dialog;

import android.R;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import com.jieli.stream.p016dv.running2.C1438R;
import com.jieli.stream.p016dv.running2.p017ui.base.BaseDialogFragment;
import com.jieli.stream.p016dv.running2.util.IConstant;
import com.jieli.stream.p016dv.running2.util.ToastUtil;

/* JADX INFO: loaded from: classes.dex */
public class InputPasswordDialog extends BaseDialogFragment implements IConstant {
    private TextView mCancel;
    private TextView mConfirm;
    private EditText mContent;
    private OnInputCompletionListener mOnInputCompletionListener;
    private String mTextTitle;
    private TextView mTitle;
    private final int MAX_WIFI_PWD_LENGTH = 63;
    private final int MIN_WIFI_PWD_LENGTH = 8;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: com.jieli.stream.dv.running2.ui.dialog.InputPasswordDialog.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (InputPasswordDialog.this.mConfirm == view) {
                InputPasswordDialog.this.commitPassword();
            } else if (InputPasswordDialog.this.mCancel == view) {
                InputPasswordDialog.this.dismiss();
            }
        }
    };

    public interface OnInputCompletionListener {
        void onCompletion(String str, String str2);
    }

    public static InputPasswordDialog newInstance(String str) {
        InputPasswordDialog inputPasswordDialog = new InputPasswordDialog();
        Bundle bundle = new Bundle();
        bundle.putString("title", str);
        inputPasswordDialog.setArguments(bundle);
        return inputPasswordDialog;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(1, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        this.mTextTitle = getArguments().getString("title", null);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(C1438R.layout.input_password_dialog, viewGroup, false);
        this.mContent = (EditText) viewInflate.findViewById(C1438R.id.et_password);
        this.mConfirm = (TextView) viewInflate.findViewById(C1438R.id.tv_confirm);
        this.mCancel = (TextView) viewInflate.findViewById(C1438R.id.tv_cancel);
        this.mTitle = (TextView) viewInflate.findViewById(C1438R.id.tv_title);
        this.mConfirm.setOnClickListener(this.mOnClickListener);
        this.mCancel.setOnClickListener(this.mOnClickListener);
        this.mTitle.setText(this.mTextTitle);
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getDialog() == null || getDialog().getWindow() == null) {
            return;
        }
        WindowManager.LayoutParams attributes = getDialog().getWindow().getAttributes();
        attributes.width = 100;
        attributes.height = 50;
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        if (getResources().getConfiguration().orientation == 2) {
            attributes.width = (displayMetrics.heightPixels * 4) / 5;
            attributes.height = (displayMetrics.heightPixels * 3) / 5;
        } else if (getResources().getConfiguration().orientation == 1) {
            attributes.width = (displayMetrics.widthPixels * 4) / 5;
            attributes.height = (displayMetrics.widthPixels * 3) / 5;
        }
        attributes.gravity = 17;
        getDialog().getWindow().setAttributes(attributes);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void commitPassword() {
        String strTrim = this.mContent.getText().toString().trim();
        if (TextUtils.isEmpty(strTrim)) {
            ToastUtil.showToastLong(getString(C1438R.string.name_empty_error));
            strTrim = "";
        }
        if (strTrim.length() < 8 || strTrim.length() > 63) {
            ToastUtil.showToastLong(getString(C1438R.string.input_pwd_error));
            return;
        }
        dismiss();
        OnInputCompletionListener onInputCompletionListener = this.mOnInputCompletionListener;
        if (onInputCompletionListener != null) {
            onInputCompletionListener.onCompletion(this.mTextTitle, strTrim);
        }
    }

    public void setOnInputCompletionListener(OnInputCompletionListener onInputCompletionListener) {
        this.mOnInputCompletionListener = onInputCompletionListener;
    }
}
