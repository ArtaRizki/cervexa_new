package com.jieli.stream.p016dv.running2.p017ui.dialog;

import android.R;
import android.os.Bundle;
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

/* JADX INFO: loaded from: classes.dex */
public class PasswordConfirmDialog extends BaseDialogFragment implements IConstant {
    private TextView mCancel;
    private TextView mConfirm;
    private EditText mContent;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: com.jieli.stream.dv.running2.ui.dialog.PasswordConfirmDialog.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (PasswordConfirmDialog.this.mConfirm == view) {
                PasswordConfirmDialog.this.commitPassword();
            } else if (PasswordConfirmDialog.this.mCancel == view) {
                PasswordConfirmDialog.this.dismiss();
            }
        }
    };
    private OnInputCompletionListener mOnInputCompletionListener;
    private String mTextTitle;
    private TextView mTitle;

    public interface OnInputCompletionListener {
        void onCompletion(String str, String str2);
    }

    public static PasswordConfirmDialog newInstance(String str) {
        PasswordConfirmDialog passwordConfirmDialog = new PasswordConfirmDialog();
        Bundle bundle = new Bundle();
        bundle.putString("title", str);
        passwordConfirmDialog.setArguments(bundle);
        return passwordConfirmDialog;
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
