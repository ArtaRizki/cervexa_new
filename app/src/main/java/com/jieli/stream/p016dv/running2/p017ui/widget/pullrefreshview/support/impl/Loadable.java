package com.jieli.stream.p016dv.running2.p017ui.widget.pullrefreshview.support.impl;

import com.jieli.stream.p016dv.running2.p017ui.widget.pullrefreshview.layout.PullRefreshLayout;

/* JADX INFO: loaded from: classes.dex */
public interface Loadable {
    boolean onScroll(float f);

    void onScrollChange(int i);

    boolean onStartFling(float f);

    void setPullRefreshLayout(PullRefreshLayout pullRefreshLayout);

    void startLoad();

    void stopLoad();
}
