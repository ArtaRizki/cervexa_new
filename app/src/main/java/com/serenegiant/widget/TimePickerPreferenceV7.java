package com.serenegiant.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TimePicker;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/* JADX INFO: loaded from: classes2.dex */
public class TimePickerPreferenceV7 extends DialogPreferenceV7 {
    private final Calendar calendar;
    private final long mDefaultValue;
    private TimePicker picker;

    public TimePickerPreferenceV7(Context context) {
        this(context, null);
    }

    public TimePickerPreferenceV7(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.dialogPreferenceStyle);
    }

    public TimePickerPreferenceV7(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.picker = null;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.weioa.KmedHealthIndonesia.R.styleable.TimePicker, i, 0);
        this.mDefaultValue = (long) typedArrayObtainStyledAttributes.getFloat(0, -1.0f);
        typedArrayObtainStyledAttributes.recycle();
        setPositiveButtonText(R.string.ok);
        setNegativeButtonText(R.string.cancel);
        this.calendar = new GregorianCalendar();
    }

    @Override // com.serenegiant.widget.DialogPreferenceV7
    protected View onCreateDialogView() {
        TimePicker timePicker = new TimePicker(getContext());
        this.picker = timePicker;
        timePicker.setIs24HourView(true);
        return this.picker;
    }

    @Override // com.serenegiant.widget.DialogPreferenceV7
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);
        this.picker.setCurrentHour(Integer.valueOf(this.calendar.get(11)));
        this.picker.setCurrentMinute(Integer.valueOf(this.calendar.get(12)));
    }

    @Override // com.serenegiant.widget.DialogPreferenceV7
    protected void onDialogClosed(boolean z) {
        super.onDialogClosed(z);
        if (z) {
            this.calendar.set(11, this.picker.getCurrentHour().intValue());
            this.calendar.set(12, this.picker.getCurrentMinute().intValue());
            setSummary(getSummary());
            if (callChangeListener(Long.valueOf(this.calendar.getTimeInMillis()))) {
                persistLong(this.calendar.getTimeInMillis());
                notifyChanged();
            }
        }
    }

    protected Object onGetDefaultValue(TypedArray typedArray, int i) {
        return typedArray.getString(i);
    }

    protected void onSetInitialValue(Object obj) {
        long jCurrentTimeMillis = this.mDefaultValue;
        if (jCurrentTimeMillis <= 0) {
            jCurrentTimeMillis = System.currentTimeMillis();
        }
        if (obj != null) {
            this.calendar.setTimeInMillis(Long.parseLong((String) obj));
        } else {
            this.calendar.setTimeInMillis(jCurrentTimeMillis);
        }
        setSummary(getSummary());
    }

    public CharSequence getSummary() {
        if (this.calendar == null) {
            return null;
        }
        return DateFormat.getTimeFormat(getContext()).format(new Date(this.calendar.getTimeInMillis()));
    }
}
