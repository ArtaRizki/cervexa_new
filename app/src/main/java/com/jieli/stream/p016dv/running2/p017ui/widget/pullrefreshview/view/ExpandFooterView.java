package com.jieli.stream.p016dv.running2.p017ui.widget.pullrefreshview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jieli.stream.p016dv.running2.p017ui.widget.pullrefreshview.layout.BaseFooterView;
import com.jieli.stream.p016dv.running2.p017ui.widget.pullrefreshview.layout.PullRefreshLayout;
import com.jieli.stream.p016dv.running2.p017ui.widget.pullrefreshview.utils.AnimUtil;
import android.animation.ObjectAnimator;

import org.apache.commons.net.ftp.FTPReply;

/* JADX INFO: loaded from: classes.dex */
public class ExpandFooterView extends BaseFooterView {
    private int layoutType;
    private View loadBox;
    private View progress;
    private int state;
    private View stateImg;

    public ExpandFooterView(Context context) {
        this(context, null);
    }

    public ExpandFooterView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ExpandFooterView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.state = 0;
        this.layoutType = 1;
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(com.weioa.KmedHealthIndonesia.R.layout.view_footer_expand, (ViewGroup) this, true);
        this.progress = findViewById(com.weioa.KmedHealthIndonesia.R.id.progress);
        this.stateImg = findViewById(com.weioa.KmedHealthIndonesia.R.id.state);
        this.loadBox = findViewById(com.weioa.KmedHealthIndonesia.R.id.load_box);
        setLayoutParams(new ViewGroup.LayoutParams(-1, FTPReply.FILE_ACTION_PENDING));
    }

    @Override // com.jieli.stream.p016dv.running2.p017ui.widget.pullrefreshview.layout.BaseFooterView, com.jieli.stream.p016dv.running2.p017ui.widget.pullrefreshview.support.impl.Loadable
    public void setPullRefreshLayout(PullRefreshLayout pullRefreshLayout) {
        super.setPullRefreshLayout(pullRefreshLayout);
        pullRefreshLayout.setMaxDistance(FTPReply.FILE_ACTION_PENDING);
    }

    @Override // com.jieli.stream.p016dv.running2.p017ui.widget.pullrefreshview.layout.BaseFooterView
    protected void onStateChange(int i) {
        this.state = i;
        this.stateImg.setVisibility(4);
        this.progress.setVisibility(0);
        this.progress.setAlpha(1.0f);
        if (i == 3) {
            View view = this.progress;
            AnimUtil.startRotation(view, 359.99f + view.getRotation(), 500L, 0L, -1);
        } else {
            if (i != 4) {
                return;
            }
            AnimUtil.startShow(this.stateImg, 0.1f, 400L, 200L);
            AnimUtil.startHide(this.progress);
        }
    }

    @Override // com.jieli.stream.p016dv.running2.p017ui.widget.pullrefreshview.layout.BaseFooterView
    public float getSpanHeight() {
        return this.loadBox.getHeight();
    }

    @Override // com.jieli.stream.p016dv.running2.p017ui.widget.pullrefreshview.layout.BaseFooterView
    public int getLayoutType() {
        return this.layoutType;
    }

    @Override // com.jieli.stream.p016dv.running2.p017ui.widget.pullrefreshview.layout.BaseFooterView, com.jieli.stream.p016dv.running2.p017ui.widget.pullrefreshview.support.impl.Loadable
    public boolean onScroll(float f) {
        boolean zOnScroll = super.onScroll(f);
        if (!isLockState()) {
            this.progress.setRotation(((f * f) * 48.0f) / 31250.0f);
        }
        return zOnScroll;
    }
}
