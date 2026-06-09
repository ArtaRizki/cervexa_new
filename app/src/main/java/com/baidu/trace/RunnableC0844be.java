package com.baidu.trace;

import android.content.Context;
import android.os.Handler;
import com.baidu.trace.api.track.OnTrackListener;
import com.baidu.trace.api.track.QueryCacheTrackRequest;
import com.baidu.trace.api.track.QueryCacheTrackResponse;
import com.baidu.trace.model.StatusCodes;
import java.util.ArrayList;

/* JADX INFO: renamed from: com.baidu.trace.be */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0844be implements Runnable {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ QueryCacheTrackResponse f1701a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ OnTrackListener f1702b;

    /* JADX INFO: renamed from: c */
    private /* synthetic */ Context f1703c;

    /* JADX INFO: renamed from: d */
    private /* synthetic */ QueryCacheTrackRequest f1704d;

    /* JADX INFO: renamed from: e */
    private /* synthetic */ Handler f1705e;

    RunnableC0844be(Context context, QueryCacheTrackRequest queryCacheTrackRequest, QueryCacheTrackResponse queryCacheTrackResponse, Handler handler, OnTrackListener onTrackListener) {
        this.f1703c = context;
        this.f1704d = queryCacheTrackRequest;
        this.f1701a = queryCacheTrackResponse;
        this.f1705e = handler;
        this.f1702b = onTrackListener;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ArrayList arrayList = new ArrayList();
        if (!C0814am.m1076a(this.f1703c, this.f1704d.getEntityName(), arrayList)) {
            this.f1701a.setStatus(StatusCodes.REQUEST_FAILED);
            this.f1701a.setMessage(StatusCodes.MSG_REQUEST_FAILED);
        }
        this.f1701a.setResult(arrayList);
        this.f1705e.post(new RunnableC0845bf(this));
    }
}
