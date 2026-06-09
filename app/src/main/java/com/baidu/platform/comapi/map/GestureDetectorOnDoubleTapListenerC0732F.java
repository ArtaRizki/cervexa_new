package com.baidu.platform.comapi.map;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.TextureView;
import com.baidu.mapapi.common.EnvironmentUtilities;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.C0754m;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaCodecInfo;

/* JADX INFO: renamed from: com.baidu.platform.comapi.map.F */
/* JADX INFO: loaded from: classes.dex */
public class GestureDetectorOnDoubleTapListenerC0732F extends TextureView implements GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener, TextureView.SurfaceTextureListener, C0754m.a {

    /* JADX INFO: renamed from: a */
    public static int f873a;

    /* JADX INFO: renamed from: b */
    public static int f874b;

    /* JADX INFO: renamed from: c */
    private GestureDetector f875c;

    /* JADX INFO: renamed from: d */
    private Handler f876d;

    /* JADX INFO: renamed from: e */
    private C0754m f877e;

    /* JADX INFO: renamed from: f */
    private C0746e f878f;

    public GestureDetectorOnDoubleTapListenerC0732F(Context context, C0729C c0729c, String str) throws Throwable {
        super(context);
        this.f877e = null;
        m580a(context, c0729c, str);
    }

    /* JADX INFO: renamed from: a */
    private void m580a(Context context, C0729C c0729c, String str) throws Throwable {
        setSurfaceTextureListener(this);
        if (context == null) {
            throw new RuntimeException("when you create an mapview, the context can not be null");
        }
        this.f875c = new GestureDetector(context, this);
        EnvironmentUtilities.initAppDirectory(context);
        if (this.f878f == null) {
            this.f878f = new C0746e(context, str);
        }
        this.f878f.m634a();
        this.f878f.m656b();
        this.f878f.m640a(c0729c);
        m582f();
        this.f878f.m638a(this.f876d);
        this.f878f.m671e();
    }

    /* JADX INFO: renamed from: f */
    private void m582f() {
        this.f876d = new HandlerC0733G(this);
    }

    @Override // com.baidu.platform.comapi.map.C0754m.a
    /* JADX INFO: renamed from: a */
    public int mo583a() {
        C0746e c0746e = this.f878f;
        if (c0746e == null) {
            return 0;
        }
        return MapRenderer.nativeRender(c0746e.f954h);
    }

    /* JADX INFO: renamed from: a */
    public void m584a(String str, Rect rect) {
        C0754m c0754m;
        C0746e c0746e = this.f878f;
        if (c0746e == null || c0746e.f953g == null) {
            return;
        }
        if (rect != null) {
            int i = rect.left;
            int i2 = f874b < rect.bottom ? 0 : f874b - rect.bottom;
            int iWidth = rect.width();
            int iHeight = rect.height();
            if (i < 0 || i2 < 0 || iWidth <= 0 || iHeight <= 0) {
                return;
            }
            if (iWidth > f873a) {
                iWidth = Math.abs(rect.width()) - (rect.right - f873a);
            }
            if (iHeight > f874b) {
                iHeight = Math.abs(rect.height()) - (rect.bottom - f874b);
            }
            if (i > SysOSUtil.getScreenSizeX() || i2 > SysOSUtil.getScreenSizeY()) {
                this.f878f.f953g.m832a(str, (Bundle) null);
                C0754m c0754m2 = this.f877e;
                if (c0754m2 != null) {
                    c0754m2.m726a();
                    return;
                }
                return;
            }
            f873a = iWidth;
            f874b = iHeight;
            Bundle bundle = new Bundle();
            bundle.putInt("x", i);
            bundle.putInt("y", i2);
            bundle.putInt("width", iWidth);
            bundle.putInt("height", iHeight);
            this.f878f.f953g.m832a(str, bundle);
            c0754m = this.f877e;
            if (c0754m == null) {
                return;
            }
        } else {
            this.f878f.f953g.m832a(str, (Bundle) null);
            c0754m = this.f877e;
            if (c0754m == null) {
                return;
            }
        }
        c0754m.m726a();
    }

    /* JADX INFO: renamed from: b */
    public C0746e m585b() {
        return this.f878f;
    }

    /* JADX INFO: renamed from: c */
    public void m586c() {
        C0746e c0746e = this.f878f;
        if (c0746e == null || c0746e.f953g == null) {
            return;
        }
        Iterator<InterfaceC0753l> it = this.f878f.f952f.iterator();
        while (it.hasNext()) {
            it.next().mo424d();
        }
        this.f878f.f953g.m869i();
        this.f878f.f953g.m863f();
        this.f878f.f953g.m876p();
        C0754m c0754m = this.f877e;
        if (c0754m != null) {
            c0754m.m726a();
        }
    }

    /* JADX INFO: renamed from: d */
    public void m587d() {
        C0746e c0746e = this.f878f;
        if (c0746e == null || c0746e.f953g == null) {
            return;
        }
        this.f878f.f953g.m860e();
        synchronized (this.f878f) {
            this.f878f.f953g.m860e();
            if (this.f877e != null) {
                this.f877e.m727b();
            }
        }
    }

    /* JADX INFO: renamed from: e */
    public void m588e() {
        synchronized (this.f878f) {
            Iterator<InterfaceC0753l> it = this.f878f.f952f.iterator();
            while (it.hasNext()) {
                it.next().mo428f();
            }
            if (this.f878f != null) {
                this.f878f.m658b(this.f876d);
                this.f878f.m629M();
                this.f878f = null;
            }
            this.f876d.removeCallbacksAndMessages(null);
        }
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTap(MotionEvent motionEvent) {
        C0746e c0746e = this.f878f;
        if (c0746e == null || c0746e.f953g == null || !this.f878f.f955i) {
            return true;
        }
        GeoPoint geoPointM655b = this.f878f.m655b((int) motionEvent.getX(), (int) motionEvent.getY());
        if (geoPointM655b != null) {
            Iterator<InterfaceC0753l> it = this.f878f.f952f.iterator();
            while (it.hasNext()) {
                it.next().mo418b(geoPointM655b);
            }
            if (this.f878f.f951e) {
                C0731E c0731eM620D = this.f878f.m620D();
                c0731eM620D.f845a += 1.0f;
                c0731eM620D.f848d = geoPointM655b.getLongitudeE6();
                c0731eM620D.f849e = geoPointM655b.getLatitudeE6();
                this.f878f.m642a(c0731eM620D, IjkMediaCodecInfo.RANK_SECURE);
                C0746e.f913k = System.currentTimeMillis();
                return true;
            }
        }
        return false;
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        C0746e c0746e = this.f878f;
        if (c0746e == null || c0746e.f953g == null || !this.f878f.f955i) {
            return true;
        }
        if (!this.f878f.f950d) {
            return false;
        }
        float fSqrt = (float) Math.sqrt((f * f) + (f2 * f2));
        if (fSqrt <= 500.0f) {
            return false;
        }
        this.f878f.m705z();
        this.f878f.m631a(34, (int) (fSqrt * 0.6f), ((int) motionEvent2.getX()) | (((int) motionEvent2.getY()) << 16));
        this.f878f.m628L();
        return true;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent motionEvent) {
        C0746e c0746e = this.f878f;
        if (c0746e == null || c0746e.f953g == null || !this.f878f.f955i) {
            return;
        }
        String strM828a = this.f878f.f953g.m828a(-1, (int) motionEvent.getX(), (int) motionEvent.getY(), this.f878f.f956j);
        if (strM828a == null || strM828a.equals("")) {
            Iterator<InterfaceC0753l> it = this.f878f.f952f.iterator();
            while (it.hasNext()) {
                it.next().mo422c(this.f878f.m655b((int) motionEvent.getX(), (int) motionEvent.getY()));
            }
        } else {
            for (InterfaceC0753l interfaceC0753l : this.f878f.f952f) {
                if (interfaceC0753l.mo420b(strM828a)) {
                    this.f878f.f959n = true;
                } else {
                    interfaceC0753l.mo422c(this.f878f.m655b((int) motionEvent.getX(), (int) motionEvent.getY()));
                }
            }
        }
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onShowPress(MotionEvent motionEvent) {
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        JSONObject jSONObject;
        C0746e c0746e = this.f878f;
        if (c0746e != null && c0746e.f953g != null && this.f878f.f955i) {
            String strM828a = this.f878f.f953g.m828a(-1, (int) motionEvent.getX(), (int) motionEvent.getY(), this.f878f.f956j);
            JSONObject jSONObject2 = null;
            if (strM828a == null || strM828a.equals("")) {
                Iterator<InterfaceC0753l> it = this.f878f.f952f.iterator();
                while (it.hasNext()) {
                    it.next().mo412a(this.f878f.m655b((int) motionEvent.getX(), (int) motionEvent.getY()));
                }
            } else {
                try {
                    jSONObject = new JSONObject(strM828a);
                } catch (JSONException e) {
                    e = e;
                }
                try {
                    jSONObject.put("px", (int) motionEvent.getX());
                    jSONObject.put("py", (int) motionEvent.getY());
                } catch (JSONException e2) {
                    e = e2;
                    jSONObject2 = jSONObject;
                    e.printStackTrace();
                    jSONObject = jSONObject2;
                }
                for (InterfaceC0753l interfaceC0753l : this.f878f.f952f) {
                    if (jSONObject != null) {
                        interfaceC0753l.mo414a(jSONObject.toString());
                    }
                }
            }
        }
        return true;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        if (this.f878f == null) {
            return;
        }
        C0754m c0754m = new C0754m(surfaceTexture, this, new AtomicBoolean(true), this);
        this.f877e = c0754m;
        c0754m.start();
        f873a = i;
        f874b = i2;
        C0731E c0731eM620D = this.f878f.m620D();
        if (c0731eM620D == null) {
            return;
        }
        if (c0731eM620D.f850f == 0 || c0731eM620D.f850f == -1 || c0731eM620D.f850f == (c0731eM620D.f854j.left - c0731eM620D.f854j.right) / 2) {
            c0731eM620D.f850f = -1;
        }
        if (c0731eM620D.f851g == 0 || c0731eM620D.f851g == -1 || c0731eM620D.f851g == (c0731eM620D.f854j.bottom - c0731eM620D.f854j.top) / 2) {
            c0731eM620D.f851g = -1;
        }
        c0731eM620D.f854j.left = 0;
        c0731eM620D.f854j.top = 0;
        c0731eM620D.f854j.bottom = i2;
        c0731eM620D.f854j.right = i;
        this.f878f.m641a(c0731eM620D);
        this.f878f.m636a(f873a, f874b);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        C0754m c0754m = this.f877e;
        if (c0754m == null) {
            return true;
        }
        c0754m.m728c();
        this.f877e = null;
        return true;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        C0746e c0746e = this.f878f;
        if (c0746e == null) {
            return;
        }
        f873a = i;
        f874b = i2;
        c0746e.m636a(i, i2);
        MapRenderer.nativeResize(this.f878f.f954h, i, i2);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        C0746e c0746e = this.f878f;
        if (c0746e == null || c0746e.f953g == null) {
            return true;
        }
        super.onTouchEvent(motionEvent);
        Iterator<InterfaceC0753l> it = this.f878f.f952f.iterator();
        while (it.hasNext()) {
            it.next().mo411a(motionEvent);
        }
        if (this.f875c.onTouchEvent(motionEvent)) {
            return true;
        }
        return this.f878f.m653a(motionEvent);
    }
}
