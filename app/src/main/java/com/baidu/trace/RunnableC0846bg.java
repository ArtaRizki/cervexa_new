package com.baidu.trace;

import android.content.Context;
import android.os.Handler;
import com.baidu.trace.api.track.CacheTrackInfo;
import com.baidu.trace.api.track.ClearCacheTrackResponse;
import com.baidu.trace.api.track.OnTrackListener;
import com.baidu.trace.model.StatusCodes;
import java.util.List;

/* JADX INFO: renamed from: com.baidu.trace.bg */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0846bg implements Runnable {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ ClearCacheTrackResponse f1707a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ OnTrackListener f1708b;

    /* JADX INFO: renamed from: c */
    private /* synthetic */ Context f1709c;

    /* JADX INFO: renamed from: d */
    private /* synthetic */ List f1710d;

    /* JADX INFO: renamed from: e */
    private /* synthetic */ List f1711e;

    /* JADX INFO: renamed from: f */
    private /* synthetic */ Handler f1712f;

    RunnableC0846bg(Context context, List list, List list2, ClearCacheTrackResponse clearCacheTrackResponse, Handler handler, OnTrackListener onTrackListener) {
        this.f1709c = context;
        this.f1710d = list;
        this.f1711e = list2;
        this.f1707a = clearCacheTrackResponse;
        this.f1712f = handler;
        this.f1708b = onTrackListener;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (!C0814am.m1077a(this.f1709c, (List<String>) this.f1710d, (List<CacheTrackInfo>) this.f1711e)) {
            this.f1707a.setStatus(StatusCodes.REQUEST_FAILED);
            this.f1707a.setMessage(StatusCodes.MSG_REQUEST_FAILED);
        }
        this.f1712f.post(new RunnableC0847bh(this));
    }
}
