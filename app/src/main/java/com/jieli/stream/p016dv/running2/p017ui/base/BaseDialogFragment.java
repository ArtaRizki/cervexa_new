package com.jieli.stream.p016dv.running2.p017ui.base;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.jieli.stream.p016dv.running2.C1438R;

/* JADX INFO: loaded from: classes.dex */
public class BaseDialogFragment extends DialogFragment {
    private Bundle bundle;
    private boolean isShown = false;

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.dimAmount = 0.5f;
        attributes.flags |= 2;
        attributes.windowAnimations = C1438R.style.MyDialogTheme;
        window.setAttributes(attributes);
    }

    @Override // androidx.fragment.app.DialogFragment
    public void show(FragmentManager fragmentManager, String str) {
        if (isShowing()) {
            return;
        }
        FragmentTransaction fragmentTransactionBeginTransaction = fragmentManager.beginTransaction();
        fragmentTransactionBeginTransaction.add(this, str);
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
        this.isShown = true;
    }

    @Override // androidx.fragment.app.DialogFragment
    public void dismiss() {
        dismissAllowingStateLoss();
        this.isShown = false;
    }

    public boolean isShowing() {
        return this.isShown;
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        if (getActivity() != null) {
            super.onDismiss(dialogInterface);
        }
        this.isShown = false;
    }

    public Bundle getBundle() {
        return this.bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }
}
