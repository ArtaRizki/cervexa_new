package com.jieli.stream.p016dv.running2.p017ui.dialog;

import android.R;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import com.jieli.stream.p016dv.running2.C1438R;
import com.jieli.stream.p016dv.running2.p017ui.base.BaseDialogFragment;
import com.jieli.stream.p016dv.running2.util.ToastUtil;
import org.apache.commons.net.ftp.FTPReply;

/* JADX INFO: loaded from: classes.dex */
public class InputContentDialog extends BaseDialogFragment implements View.OnClickListener {
    private EditText mEditText;
    private OnContentListener onContentListener;

    public interface OnContentListener {
        void onInput(String str);
    }

    public void setOnContentListener(OnContentListener onContentListener) {
        this.onContentListener = onContentListener;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(1, R.style.Theme.Translucent.NoTitleBar.Fullscreen);
        setCancelable(false);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(C1438R.layout.dialog_input_content, viewGroup, false);
        this.mEditText = (EditText) viewInflate.findViewById(C1438R.id.dialog_edit_text);
        Button button = (Button) viewInflate.findViewById(C1438R.id.dialog_input_cancel_btn);
        Button button2 = (Button) viewInflate.findViewById(C1438R.id.dialog_input_confirm_btn);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getActivity() == null || getActivity().getWindow() == null || getDialog().getWindow() == null) {
            return;
        }
        WindowManager.LayoutParams attributes = getDialog().getWindow().getAttributes();
        attributes.width = FTPReply.FILE_STATUS_OK;
        attributes.height = 50;
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        if (getResources().getConfiguration().orientation == 2) {
            attributes.width = (displayMetrics.heightPixels * 4) / 6;
            attributes.height = (displayMetrics.heightPixels * 2) / 5;
        } else if (getResources().getConfiguration().orientation == 1) {
            attributes.width = (displayMetrics.widthPixels * 4) / 6;
            attributes.height = (displayMetrics.widthPixels * 2) / 5;
        }
        attributes.gravity = 17;
        getDialog().getWindow().setAttributes(attributes);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        EditText editText;
        if (getActivity() == null || getActivity().getWindow() == null || getDialog().getWindow() == null) {
            return;
        }
        int id = view.getId();
        if (id == C1438R.id.dialog_input_cancel_btn) {
            dismiss();
            return;
        }
        if (id != C1438R.id.dialog_input_confirm_btn || (editText = this.mEditText) == null) {
            return;
        }
        String strTrim = editText.getText().toString().trim();
        if (!TextUtils.isEmpty(strTrim)) {
            OnContentListener onContentListener = this.onContentListener;
            if (onContentListener != null) {
                onContentListener.onInput(strTrim);
            }
            dismiss();
            return;
        }
        ToastUtil.showToastShort(getString(C1438R.string.input_content_empty));
    }
}
