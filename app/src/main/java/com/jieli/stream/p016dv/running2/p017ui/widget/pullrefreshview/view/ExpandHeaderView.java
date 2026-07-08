package com.jieli.stream.p016dv.running2.p017ui.widget.pullrefreshview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jieli.stream.p016dv.running2.p017ui.widget.pullrefreshview.layout.BaseHeaderView;
import com.jieli.stream.p016dv.running2.p017ui.widget.pullrefreshview.layout.PullRefreshLayout;
import com.jieli.stream.p016dv.running2.p017ui.widget.pullrefreshview.utils.AnimUtil;
import android.animation.ObjectAnimator;

import tv.danmaku.ijk.media.player.IjkMediaCodecInfo;

/* JADX INFO: loaded from: classes.dex */
public class ExpandHeaderView extends BaseHeaderView {
    private static final int layoutType = 1;
    private View loadBox;
    private View progress;
    private int state;
    private View stateImg;

    @Override // com.jieli.stream.p016dv.running2.p017ui.widget.pullrefreshview.layout.BaseHeaderView
    public int getLayoutType() {
        return 1;
    }

    public ExpandHeaderView(Context context) {
        this(context, null);
    }

    public ExpandHeaderView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ExpandHeaderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.state = 0;
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(com.weioa.KmedHealthIndonesia.R.layout.view_header_expand, (ViewGroup) this, true);
        this.progress = findViewById(com.weioa.KmedHealthIndonesia.R.id.progress);
        this.stateImg = findViewById(com.weioa.KmedHealthIndonesia.R.id.state);
        this.loadBox = findViewById(com.weioa.KmedHealthIndonesia.R.id.load_box);
        setLayoutParams(new ViewGroup.LayoutParams(-1, IjkMediaCodecInfo.RANK_SECURE));
    }

    @Override // com.jieli.stream.p016dv.running2.p017ui.widget.pullrefreshview.layout.BaseHeaderView, com.jieli.stream.p016dv.running2.p017ui.widget.pullrefreshview.support.impl.Refreshable
    public void setPullRefreshLayout(PullRefreshLayout pullRefreshLayout) {
        super.setPullRefreshLayout(pullRefreshLayout);
        pullRefreshLayout.setMaxDistance(IjkMediaCodecInfo.RANK_SECURE);
    }

    @Override // com.jieli.stream.p016dv.running2.p017ui.widget.pullrefreshview.layout.BaseHeaderView
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

    @Override // com.jieli.stream.p016dv.running2.p017ui.widget.pullrefreshview.layout.BaseHeaderView
    public float getSpanHeight() {
        return this.loadBox.getHeight();
    }

    @Override // com.jieli.stream.p016dv.running2.p017ui.widget.pullrefreshview.layout.BaseHeaderView, com.jieli.stream.p016dv.running2.p017ui.widget.pullrefreshview.support.impl.Refreshable
    public boolean onScroll(float f) {
        boolean zOnScroll = super.onScroll(f);
        if (!isLockState()) {
            this.progress.setRotation(((f * f) * 48.0f) / 31250.0f);
        }
        return zOnScroll;
    }
}
