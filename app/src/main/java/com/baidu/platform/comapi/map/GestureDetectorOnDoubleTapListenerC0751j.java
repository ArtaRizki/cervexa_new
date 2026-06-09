package com.baidu.platform.comapi.map;

import android.content.Context;
import android.graphics.Rect;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import com.baidu.mapapi.common.EnvironmentUtilities;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.MapRenderer;
import java.util.Iterator;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaCodecInfo;

/* JADX INFO: renamed from: com.baidu.platform.comapi.map.j */
/* JADX INFO: loaded from: classes.dex */
public class GestureDetectorOnDoubleTapListenerC0751j extends GLSurfaceView implements GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener, MapRenderer.InterfaceC0740a {

    /* JADX INFO: renamed from: a */
    private static final String f984a = GestureDetectorOnDoubleTapListenerC0751j.class.getSimpleName();

    /* JADX INFO: renamed from: b */
    private Handler f985b;

    /* JADX INFO: renamed from: c */
    private MapRenderer f986c;

    /* JADX INFO: renamed from: d */
    private int f987d;

    /* JADX INFO: renamed from: e */
    private int f988e;

    /* JADX INFO: renamed from: f */
    private GestureDetector f989f;

    /* JADX INFO: renamed from: g */
    private C0746e f990g;

    /* JADX INFO: renamed from: com.baidu.platform.comapi.map.j$a */
    static class a {

        /* JADX INFO: renamed from: a */
        float f991a;

        /* JADX INFO: renamed from: b */
        float f992b;

        /* JADX INFO: renamed from: c */
        float f993c;

        /* JADX INFO: renamed from: d */
        float f994d;

        /* JADX INFO: renamed from: e */
        boolean f995e;

        /* JADX INFO: renamed from: f */
        float f996f;

        /* JADX INFO: renamed from: g */
        float f997g;

        /* JADX INFO: renamed from: h */
        double f998h;

        a() {
        }
    }

    public GestureDetectorOnDoubleTapListenerC0751j(Context context, C0729C c0729c, String str) throws Throwable {
        super(context);
        if (context == null) {
            throw new RuntimeException("when you create an mapview, the context can not be null");
        }
        setEGLContextClientVersion(2);
        this.f989f = new GestureDetector(context, this);
        EnvironmentUtilities.initAppDirectory(context);
        if (this.f990g == null) {
            this.f990g = new C0746e(context, str);
        }
        this.f990g.m634a();
        m714f();
        this.f990g.m656b();
        this.f990g.m640a(c0729c);
        m715g();
        this.f990g.m638a(this.f985b);
        this.f990g.m671e();
        setBackgroundColor(0);
    }

    /* JADX INFO: renamed from: a */
    private static boolean m711a(int i, int i2, int i3, int i4, int i5, int i6) {
        EGL10 egl10 = (EGL10) EGLContext.getEGL();
        EGLDisplay eGLDisplayEglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        egl10.eglInitialize(eGLDisplayEglGetDisplay, new int[2]);
        int[] iArr = new int[1];
        return egl10.eglChooseConfig(eGLDisplayEglGetDisplay, new int[]{12324, i, 12323, i2, 12322, i3, 12321, i4, 12325, i5, 12326, i6, 12344}, new EGLConfig[100], 100, iArr) && iArr[0] > 0;
    }

    /* JADX INFO: renamed from: f */
    private void m714f() {
        try {
            if (m711a(5, 6, 5, 0, 24, 0)) {
                setEGLConfigChooser(5, 6, 5, 0, 24, 0);
            } else {
                setEGLConfigChooser(true);
            }
        } catch (IllegalArgumentException unused) {
            setEGLConfigChooser(true);
        }
        MapRenderer mapRenderer = new MapRenderer(this, this);
        this.f986c = mapRenderer;
        mapRenderer.m597a(this.f990g.f954h);
        setRenderer(this.f986c);
        setRenderMode(1);
    }

    /* JADX INFO: renamed from: g */
    private void m715g() {
        this.f985b = new HandlerC0752k(this);
    }

    /* JADX INFO: renamed from: a */
    public C0746e m716a() {
        return this.f990g;
    }

    /* JADX INFO: renamed from: a */
    public void m717a(int i) {
        int i2;
        if (this.f990g == null) {
            return;
        }
        Message message = new Message();
        message.what = 50;
        message.obj = Long.valueOf(this.f990g.f954h);
        boolean zM693p = this.f990g.m693p();
        if (i != 3) {
            i2 = zM693p ? 1 : 0;
            this.f985b.sendMessage(message);
        }
        message.arg1 = i2;
        this.f985b.sendMessage(message);
    }

    /* JADX INFO: renamed from: a */
    public void m718a(String str, Rect rect) {
        C0746e c0746e = this.f990g;
        if (c0746e == null || c0746e.f953g == null) {
            return;
        }
        if (rect != null) {
            int i = rect.left;
            int i2 = this.f988e < rect.bottom ? 0 : this.f988e - rect.bottom;
            int iWidth = rect.width();
            int iHeight = rect.height();
            if (i < 0 || i2 < 0 || iWidth <= 0 || iHeight <= 0) {
                return;
            }
            if (iWidth > this.f987d) {
                iWidth = Math.abs(rect.width()) - (rect.right - this.f987d);
            }
            if (iHeight > this.f988e) {
                iHeight = Math.abs(rect.height()) - (rect.bottom - this.f988e);
            }
            if (i > SysOSUtil.getScreenSizeX() || i2 > SysOSUtil.getScreenSizeY()) {
                this.f990g.f953g.m832a(str, (Bundle) null);
                requestRender();
                return;
            }
            this.f987d = iWidth;
            this.f988e = iHeight;
            Bundle bundle = new Bundle();
            bundle.putInt("x", i);
            bundle.putInt("y", i2);
            bundle.putInt("width", iWidth);
            bundle.putInt("height", iHeight);
            this.f990g.f953g.m832a(str, bundle);
        } else {
            this.f990g.f953g.m832a(str, (Bundle) null);
        }
        requestRender();
    }

    /* JADX INFO: renamed from: b */
    public void m719b() {
        C0746e c0746e = this.f990g;
        if (c0746e != null) {
            Iterator<InterfaceC0753l> it = c0746e.f952f.iterator();
            while (it.hasNext()) {
                it.next().mo428f();
            }
            this.f990g.m658b(this.f985b);
            this.f990g.m629M();
            this.f990g = null;
        }
    }

    /* JADX INFO: renamed from: c */
    public void m720c() {
        C0746e c0746e = this.f990g;
        if (c0746e == null) {
            return;
        }
        c0746e.m699t();
    }

    /* JADX INFO: renamed from: d */
    public void m721d() {
        C0746e c0746e = this.f990g;
        if (c0746e == null) {
            return;
        }
        c0746e.m700u();
    }

    @Override // com.baidu.platform.comapi.map.MapRenderer.InterfaceC0740a
    /* JADX INFO: renamed from: e */
    public void mo598e() {
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTap(MotionEvent motionEvent) {
        C0746e c0746e = this.f990g;
        if (c0746e == null || c0746e.f953g == null || !this.f990g.f955i) {
            return true;
        }
        GeoPoint geoPointM655b = this.f990g.m655b((int) motionEvent.getX(), (int) motionEvent.getY());
        if (geoPointM655b != null) {
            Iterator<InterfaceC0753l> it = this.f990g.f952f.iterator();
            while (it.hasNext()) {
                it.next().mo418b(geoPointM655b);
            }
            if (this.f990g.f951e) {
                C0731E c0731eM620D = this.f990g.m620D();
                c0731eM620D.f845a += 1.0f;
                c0731eM620D.f848d = geoPointM655b.getLongitudeE6();
                c0731eM620D.f849e = geoPointM655b.getLatitudeE6();
                this.f990g.m642a(c0731eM620D, IjkMediaCodecInfo.RANK_SECURE);
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
        C0746e c0746e = this.f990g;
        if (c0746e == null || c0746e.f953g == null || !this.f990g.f955i) {
            return true;
        }
        if (!this.f990g.f950d) {
            return false;
        }
        float fSqrt = (float) Math.sqrt((f * f) + (f2 * f2));
        if (fSqrt <= 500.0f) {
            return false;
        }
        this.f990g.m705z();
        this.f990g.m631a(34, (int) (fSqrt * 0.6f), ((int) motionEvent2.getX()) | (((int) motionEvent2.getY()) << 16));
        this.f990g.m628L();
        return true;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent motionEvent) {
        C0746e c0746e = this.f990g;
        if (c0746e == null || c0746e.f953g == null || !this.f990g.f955i) {
            return;
        }
        String strM828a = this.f990g.f953g.m828a(-1, (int) motionEvent.getX(), (int) motionEvent.getY(), this.f990g.f956j);
        if (strM828a == null || strM828a.equals("")) {
            Iterator<InterfaceC0753l> it = this.f990g.f952f.iterator();
            while (it.hasNext()) {
                it.next().mo422c(this.f990g.m655b((int) motionEvent.getX(), (int) motionEvent.getY()));
            }
        } else {
            for (InterfaceC0753l interfaceC0753l : this.f990g.f952f) {
                if (interfaceC0753l.mo420b(strM828a)) {
                    this.f990g.f959n = true;
                } else {
                    interfaceC0753l.mo422c(this.f990g.m655b((int) motionEvent.getX(), (int) motionEvent.getY()));
                }
            }
        }
    }

    @Override // android.opengl.GLSurfaceView
    public void onPause() {
        super.onPause();
        C0746e c0746e = this.f990g;
        if (c0746e == null || c0746e.f953g == null) {
            return;
        }
        this.f990g.f953g.m860e();
    }

    @Override // android.opengl.GLSurfaceView
    public void onResume() {
        super.onResume();
        C0746e c0746e = this.f990g;
        if (c0746e == null || c0746e.f953g == null) {
            return;
        }
        Iterator<InterfaceC0753l> it = this.f990g.f952f.iterator();
        while (it.hasNext()) {
            it.next().mo424d();
        }
        this.f990g.f953g.m869i();
        this.f990g.f953g.m863f();
        this.f990g.f953g.m876p();
        setRenderMode(1);
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
        C0746e c0746e = this.f990g;
        if (c0746e != null && c0746e.f953g != null && this.f990g.f955i) {
            String strM828a = this.f990g.f953g.m828a(-1, (int) motionEvent.getX(), (int) motionEvent.getY(), this.f990g.f956j);
            JSONObject jSONObject2 = null;
            if (strM828a == null || strM828a.equals("")) {
                Iterator<InterfaceC0753l> it = this.f990g.f952f.iterator();
                while (it.hasNext()) {
                    it.next().mo412a(this.f990g.m655b((int) motionEvent.getX(), (int) motionEvent.getY()));
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
                for (InterfaceC0753l interfaceC0753l : this.f990g.f952f) {
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

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        C0746e c0746e = this.f990g;
        if (c0746e == null || c0746e.f953g == null) {
            return true;
        }
        super.onTouchEvent(motionEvent);
        Iterator<InterfaceC0753l> it = this.f990g.f952f.iterator();
        while (it.hasNext()) {
            it.next().mo411a(motionEvent);
        }
        if (this.f989f.onTouchEvent(motionEvent)) {
            return true;
        }
        return this.f990g.m653a(motionEvent);
    }

    @Override // android.opengl.GLSurfaceView, android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        super.surfaceChanged(surfaceHolder, i, i2, i3);
        C0746e c0746e = this.f990g;
        if (c0746e == null || c0746e.f953g == null) {
            return;
        }
        this.f986c.f886a = i2;
        this.f986c.f887b = i3;
        this.f987d = i2;
        this.f988e = i3;
        this.f986c.f888c = 0;
        C0731E c0731eM620D = this.f990g.m620D();
        if (c0731eM620D.f850f == 0 || c0731eM620D.f850f == -1 || c0731eM620D.f850f == (c0731eM620D.f854j.left - c0731eM620D.f854j.right) / 2) {
            c0731eM620D.f850f = -1;
        }
        if (c0731eM620D.f851g == 0 || c0731eM620D.f851g == -1 || c0731eM620D.f851g == (c0731eM620D.f854j.bottom - c0731eM620D.f854j.top) / 2) {
            c0731eM620D.f851g = -1;
        }
        c0731eM620D.f854j.left = 0;
        c0731eM620D.f854j.top = 0;
        c0731eM620D.f854j.bottom = i3;
        c0731eM620D.f854j.right = i2;
        this.f990g.m641a(c0731eM620D);
        this.f990g.m636a(this.f987d, this.f988e);
    }

    @Override // android.opengl.GLSurfaceView, android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        super.surfaceCreated(surfaceHolder);
        if (surfaceHolder == null || surfaceHolder.getSurface().isValid()) {
            return;
        }
        surfaceDestroyed(surfaceHolder);
    }
}
