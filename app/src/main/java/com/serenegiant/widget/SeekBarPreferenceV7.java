package com.serenegiant.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.serenegiant.common.C1831R;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class SeekBarPreferenceV7 extends Preference {
    private static int sDefaultValue = 1;
    private final int mDefaultValue;
    private final String mFmtStr;
    private final int mLabelTvId;
    private final int mMaxValue;
    private final int mMinValue;
    private final SeekBar.OnSeekBarChangeListener mOnSeekBarChangeListener;
    private final float mScaleValue;
    private final int mSeekbarId;
    private final int mSeekbarLayoutId;
    private TextView mTextView;
    private int preferenceValue;

    public SeekBarPreferenceV7(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SeekBarPreferenceV7(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mOnSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() { // from class: com.serenegiant.widget.SeekBarPreferenceV7.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                if (SeekBarPreferenceV7.this.callChangeListener(Integer.valueOf(progress))) {
                    SeekBarPreferenceV7 seekBarPreferenceV7 = SeekBarPreferenceV7.this;
                    seekBarPreferenceV7.preferenceValue = progress + seekBarPreferenceV7.mMinValue;
                    SeekBarPreferenceV7 seekBarPreferenceV72 = SeekBarPreferenceV7.this;
                    seekBarPreferenceV72.persistInt(seekBarPreferenceV72.preferenceValue);
                    SeekBarPreferenceV7 seekBarPreferenceV73 = SeekBarPreferenceV7.this;
                    seekBarPreferenceV73.setValueLabel(seekBarPreferenceV73.preferenceValue, false);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i2, boolean z) {
                SeekBarPreferenceV7 seekBarPreferenceV7 = SeekBarPreferenceV7.this;
                seekBarPreferenceV7.setValueLabel(i2 + seekBarPreferenceV7.mMinValue, z);
            }
        };
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C1831R.styleable.SeekBarPreference, i, 0);
        this.mSeekbarLayoutId = typedArrayObtainStyledAttributes.getResourceId(C1831R.styleable.SeekBarPreference_seekbar_layout, C1831R.layout.seekbar_preference);
        this.mSeekbarId = typedArrayObtainStyledAttributes.getResourceId(C1831R.styleable.SeekBarPreference_seekbar_id, C1831R.id.seekbar);
        this.mLabelTvId = typedArrayObtainStyledAttributes.getResourceId(C1831R.styleable.SeekBarPreference_seekbar_label_id, C1831R.id.seekbar_value_label);
        this.mMinValue = typedArrayObtainStyledAttributes.getInt(C1831R.styleable.SeekBarPreference_min_value, 0);
        this.mMaxValue = typedArrayObtainStyledAttributes.getInt(C1831R.styleable.SeekBarPreference_max_value, 100);
        this.mDefaultValue = typedArrayObtainStyledAttributes.getInt(C1831R.styleable.SeekBarPreference_default_value, this.mMinValue);
        this.mScaleValue = typedArrayObtainStyledAttributes.getFloat(C1831R.styleable.SeekBarPreference_scale_value, 1.0f);
        String string = typedArrayObtainStyledAttributes.getString(C1831R.styleable.SeekBarPreference_value_format);
        try {
            String.format(string, Float.valueOf(1.0f));
        } catch (Exception unused) {
            string = "%f";
        }
        this.mFmtStr = TextUtils.isEmpty(string) ? "%f" : string;
        typedArrayObtainStyledAttributes.recycle();
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        ViewGroup viewGroup;
        View viewInflate;
        super.onBindViewHolder(preferenceViewHolder);
        if (this.mSeekbarLayoutId == 0 || this.mSeekbarId == 0) {
            return;
        }
        RelativeLayout relativeLayout = null;
        if (preferenceViewHolder.itemView instanceof ViewGroup) {
            viewGroup = (ViewGroup) preferenceViewHolder.itemView;
            int childCount = viewGroup.getChildCount() - 1;
            while (true) {
                if (childCount < 0) {
                    break;
                }
                View childAt = viewGroup.getChildAt(childCount);
                if (childAt instanceof RelativeLayout) {
                    relativeLayout = (RelativeLayout) childAt;
                    break;
                }
                childCount--;
            }
        } else {
            viewGroup = null;
        }
        if (relativeLayout == null || (viewInflate = LayoutInflater.from(getContext()).inflate(this.mSeekbarLayoutId, viewGroup, false)) == null) {
            return;
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(3, R.id.summary);
        relativeLayout.addView(viewInflate, layoutParams);
        SeekBar seekBar = (SeekBar) viewInflate.findViewById(this.mSeekbarId);
        if (seekBar != null) {
            seekBar.setMax(this.mMaxValue - this.mMinValue);
            int i = this.preferenceValue - this.mMinValue;
            seekBar.setProgress(i);
            seekBar.setSecondaryProgress(i);
            seekBar.setOnSeekBarChangeListener(this.mOnSeekBarChangeListener);
            seekBar.setEnabled(isEnabled());
        }
        TextView textView = (TextView) viewInflate.findViewById(C1831R.id.seekbar_value_label);
        this.mTextView = textView;
        if (textView != null) {
            setValueLabel(this.preferenceValue, false);
            this.mTextView.setEnabled(isEnabled());
        }
    }

    protected Object onGetDefaultValue(TypedArray typedArray, int i) {
        return Integer.valueOf(typedArray.getInt(i, this.mDefaultValue));
    }

    protected void onSetInitialValue(boolean z, Object obj) {
        try {
            this.preferenceValue = ((Integer) obj).intValue();
        } catch (Exception unused) {
            this.preferenceValue = this.mDefaultValue;
        }
        if (z) {
            this.preferenceValue = getPersistedInt(this.preferenceValue);
        }
        persistInt(this.preferenceValue);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setValueLabel(int i, boolean z) {
        TextView textView = this.mTextView;
        if (textView != null) {
            textView.setText(formatValueLabel(i, z));
        }
    }

    protected String formatValueLabel(int i, boolean z) {
        try {
            return String.format(this.mFmtStr, Float.valueOf(i * this.mScaleValue));
        } catch (Exception unused) {
            return String.format(Locale.US, "%f", Float.valueOf(i * this.mScaleValue));
        }
    }
}
