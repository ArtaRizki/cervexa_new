package com.serenegiant.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.serenegiant.widget.ColorPickerView;

/* JADX INFO: loaded from: classes.dex */
public class ColorPickerDialogV4 extends DialogFragmentEx {
    private static final boolean DEBUG = false;
    private static final int DEFAULT_COLOR = -1;
    private static final String KEY_COLOR_CURRENT = "current_color";
    private static final String KEY_COLOR_INIT = "initial_color";
    private static final String TAG = "ColorPickerDialog";
    private boolean isCanceled;
    private OnColorChangedListener mListener;
    private int mTitleResId;
    private int mInitialColor = -1;
    private int mCurrentColor = -1;
    private final ColorPickerView.ColorPickerListener mColorPickerListener = new ColorPickerView.ColorPickerListener() { // from class: com.serenegiant.dialog.ColorPickerDialogV4.1
        @Override // com.serenegiant.widget.ColorPickerView.ColorPickerListener
        public void onColorChanged(ColorPickerView colorPickerView, int i) {
            if (ColorPickerDialogV4.this.mCurrentColor != i) {
                ColorPickerDialogV4.this.mCurrentColor = i;
                if (ColorPickerDialogV4.this.mListener != null) {
                    ColorPickerDialogV4.this.mListener.onColorChanged(ColorPickerDialogV4.this, i);
                }
            }
        }
    };
    private final DialogInterface.OnClickListener mOnClickListener = new DialogInterface.OnClickListener() { // from class: com.serenegiant.dialog.ColorPickerDialogV4.2
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            if (i == -2) {
                dialogInterface.cancel();
            } else {
                if (i != -1) {
                    return;
                }
                dialogInterface.dismiss();
            }
        }
    };

    public interface OnColorChangedListener {
        void onCancel(ColorPickerDialogV4 colorPickerDialogV4);

        void onColorChanged(ColorPickerDialogV4 colorPickerDialogV4, int i);

        void onDismiss(ColorPickerDialogV4 colorPickerDialogV4, int i);
    }

    public static ColorPickerDialogV4 show(FragmentActivity fragmentActivity, int i, int i2) {
        ColorPickerDialogV4 colorPickerDialogV4NewInstance = newInstance(i, i2);
        colorPickerDialogV4NewInstance.show(fragmentActivity.getSupportFragmentManager(), TAG);
        return colorPickerDialogV4NewInstance;
    }

    public static ColorPickerDialogV4 show(Fragment fragment, int i, int i2) {
        ColorPickerDialogV4 colorPickerDialogV4NewInstance = newInstance(i, i2);
        colorPickerDialogV4NewInstance.setTargetFragment(fragment, 0);
        colorPickerDialogV4NewInstance.show(fragment.getFragmentManager(), TAG);
        return colorPickerDialogV4NewInstance;
    }

    public static ColorPickerDialogV4 newInstance(int i, int i2) {
        ColorPickerDialogV4 colorPickerDialogV4 = new ColorPickerDialogV4();
        colorPickerDialogV4.setArguments(i, i2);
        return colorPickerDialogV4;
    }

    public void setArguments(int i, int i2) {
        Bundle arguments = getArguments();
        if (arguments == null) {
            arguments = new Bundle();
        }
        arguments.putInt("title", i);
        arguments.putInt(KEY_COLOR_INIT, i2);
        arguments.remove(KEY_COLOR_CURRENT);
        setArguments(arguments);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle bundleRequireArguments = requireArguments();
        this.mTitleResId = bundleRequireArguments.getInt("title");
        int i = bundleRequireArguments.getInt(KEY_COLOR_INIT, -1);
        this.mInitialColor = i;
        this.mCurrentColor = i;
        if (bundle != null) {
            this.mCurrentColor = bundle.getInt(KEY_COLOR_CURRENT, i);
        }
    }

    @Override // com.serenegiant.dialog.DialogFragmentEx, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(KEY_COLOR_CURRENT, this.mCurrentColor);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.isCanceled = false;
        try {
            this.mListener = (OnColorChangedListener) getTargetFragment();
        } catch (ClassCastException | NullPointerException unused) {
        }
        if (this.mListener == null) {
            try {
                this.mListener = (OnColorChangedListener) getParentFragment();
            } catch (ClassCastException | NullPointerException unused2) {
            }
        }
        if (this.mListener == null) {
            try {
                this.mListener = (OnColorChangedListener) context;
            } catch (ClassCastException | NullPointerException unused3) {
            }
        }
        if (this.mListener == null) {
            Log.w(TAG, "must implement OnColorChangedListener");
        }
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(fragmentActivityRequireActivity).inflate(com.weioa.KmedHealthIndonesia.R.layout.color_picker, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        ColorPickerView colorPickerView = new ColorPickerView(fragmentActivityRequireActivity);
        colorPickerView.setColor(this.mCurrentColor);
        colorPickerView.setColorPickerListener(this.mColorPickerListener);
        frameLayout.addView(colorPickerView, layoutParams);
        AlertDialog.Builder negativeButton = new AlertDialog.Builder(fragmentActivityRequireActivity).setPositiveButton(com.weioa.KmedHealthIndonesia.R.string.color_picker_select, this.mOnClickListener).setNegativeButton(com.weioa.KmedHealthIndonesia.R.string.color_picker_cancel, this.mOnClickListener);
        int i = this.mTitleResId;
        if (i == 0) {
            i = com.weioa.KmedHealthIndonesia.R.string.color_picker_default_title;
        }
        AlertDialog alertDialogCreate = negativeButton.setTitle(i).setView(frameLayout).create();
        alertDialogCreate.setCanceledOnTouchOutside(true);
        return alertDialogCreate;
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        this.isCanceled = true;
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        OnColorChangedListener onColorChangedListener = this.mListener;
        if (onColorChangedListener != null) {
            if (this.isCanceled) {
                onColorChangedListener.onCancel(this);
            } else {
                onColorChangedListener.onDismiss(this, this.mCurrentColor);
            }
        }
    }
}
