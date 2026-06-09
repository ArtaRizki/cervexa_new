package com.baidu.mapapi.map;

import android.util.Log;
import com.baidu.mapapi.common.Logger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: loaded from: classes.dex */
public final class TileOverlay {

    /* JADX INFO: renamed from: b */
    private static final String f601b = TileOverlay.class.getSimpleName();

    /* JADX INFO: renamed from: f */
    private static int f602f = 0;

    /* JADX INFO: renamed from: a */
    BaiduMap f603a;

    /* JADX INFO: renamed from: g */
    private TileProvider f607g;

    /* JADX INFO: renamed from: d */
    private HashMap<String, Tile> f605d = new HashMap<>();

    /* JADX INFO: renamed from: e */
    private HashSet<String> f606e = new HashSet<>();

    /* JADX INFO: renamed from: c */
    private ExecutorService f604c = Executors.newFixedThreadPool(1);

    public TileOverlay(BaiduMap baiduMap, TileProvider tileProvider) {
        this.f603a = baiduMap;
        this.f607g = tileProvider;
    }

    /* JADX INFO: renamed from: a */
    private synchronized Tile m369a(String str) {
        if (!this.f605d.containsKey(str)) {
            return null;
        }
        Tile tile = this.f605d.get(str);
        this.f605d.remove(str);
        return tile;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public synchronized void m372a(String str, Tile tile) {
        this.f605d.put(str, tile);
    }

    /* JADX INFO: renamed from: b */
    private synchronized boolean m374b(String str) {
        return this.f606e.contains(str);
    }

    /* JADX INFO: renamed from: c */
    private synchronized void m376c(String str) {
        this.f606e.add(str);
    }

    /* JADX INFO: renamed from: a */
    Tile m377a(int i, int i2, int i3) {
        String str;
        String str2;
        String str3 = i + "_" + i2 + "_" + i3;
        Tile tileM369a = m369a(str3);
        if (tileM369a != null) {
            return tileM369a;
        }
        BaiduMap baiduMap = this.f603a;
        if (baiduMap != null && f602f == 0) {
            MapStatus mapStatus = baiduMap.getMapStatus();
            f602f = (((mapStatus.f388a.f854j.right - mapStatus.f388a.f854j.left) / 256) + 2) * (((mapStatus.f388a.f854j.bottom - mapStatus.f388a.f854j.top) / 256) + 2);
        }
        if (this.f605d.size() > f602f) {
            m378a();
        }
        if (m374b(str3) || this.f604c.isShutdown()) {
            return null;
        }
        try {
            m376c(str3);
            this.f604c.execute(new RunnableC0698t(this, i, i2, i3, str3));
            return null;
        } catch (RejectedExecutionException unused) {
            str = f601b;
            str2 = "ThreadPool excepiton";
            Log.e(str, str2);
            return null;
        } catch (Exception unused2) {
            str = f601b;
            str2 = "fileDir is not legal";
            Log.e(str, str2);
            return null;
        }
    }

    /* JADX INFO: renamed from: a */
    synchronized void m378a() {
        Logger.logE(f601b, "clearTaskSet");
        this.f606e.clear();
        this.f605d.clear();
    }

    /* JADX INFO: renamed from: b */
    void m379b() {
        this.f604c.shutdownNow();
    }

    public boolean clearTileCache() {
        return this.f603a.m276b();
    }

    public void removeTileOverlay() {
        BaiduMap baiduMap = this.f603a;
        if (baiduMap == null) {
            return;
        }
        baiduMap.m275a(this);
    }
}
