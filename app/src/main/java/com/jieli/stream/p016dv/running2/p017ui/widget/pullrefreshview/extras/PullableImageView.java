package com.jieli.stream.p016dv.running2.p017ui.widget.pullrefreshview.extras;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.jieli.stream.p016dv.running2.p017ui.widget.pullrefreshview.support.impl.Pullable;

/* JADX INFO: loaded from: classes.dex */
public class PullableImageView extends ImageView implements Pullable {
    @Override // com.jieli.stream.p016dv.running2.p017ui.widget.pullrefreshview.support.impl.Pullable
    public boolean isGetBottom() {
        return true;
    }

    @Override // com.jieli.stream.p016dv.running2.p017ui.widget.pullrefreshview.support.impl.Pullable
    public boolean isGetTop() {
        return true;
    }

    public PullableImageView(Context context) {
        super(context);
    }

    public PullableImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PullableImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
