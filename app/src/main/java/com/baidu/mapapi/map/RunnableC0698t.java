package com.baidu.mapapi.map;

import android.util.Log;

/* JADX INFO: renamed from: com.baidu.mapapi.map.t */
/* JADX INFO: loaded from: classes.dex */
class RunnableC0698t implements Runnable {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ int f693a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ int f694b;

    /* JADX INFO: renamed from: c */
    final /* synthetic */ int f695c;

    /* JADX INFO: renamed from: d */
    final /* synthetic */ String f696d;

    /* JADX INFO: renamed from: e */
    final /* synthetic */ TileOverlay f697e;

    RunnableC0698t(TileOverlay tileOverlay, int i, int i2, int i3, String str) {
        this.f697e = tileOverlay;
        this.f693a = i;
        this.f694b = i2;
        this.f695c = i3;
        this.f696d = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        String str;
        String str2;
        Tile tile = ((FileTileProvider) this.f697e.f607g).getTile(this.f693a, this.f694b, this.f695c);
        if (tile == null) {
            str = TileOverlay.f601b;
            str2 = "FileTile pic is null";
        } else {
            if (tile.width == 256 && tile.height == 256) {
                this.f697e.m372a(this.f693a + "_" + this.f694b + "_" + this.f695c, tile);
                this.f697e.f606e.remove(this.f696d);
            }
            str = TileOverlay.f601b;
            str2 = "FileTile pic must be 256 * 256";
        }
        Log.e(str, str2);
        this.f697e.f606e.remove(this.f696d);
    }
}
