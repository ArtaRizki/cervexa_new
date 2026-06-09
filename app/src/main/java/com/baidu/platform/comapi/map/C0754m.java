package com.baidu.platform.comapi.map;

import android.graphics.SurfaceTexture;
import android.opengl.GLUtils;
import com.serenegiant.glutils.EGLBase;
import java.lang.Thread;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: renamed from: com.baidu.platform.comapi.map.m */
/* JADX INFO: loaded from: classes.dex */
public class C0754m extends Thread {

    /* JADX INFO: renamed from: a */
    private AtomicBoolean f1000a;

    /* JADX INFO: renamed from: b */
    private SurfaceTexture f1001b;

    /* JADX INFO: renamed from: c */
    private a f1002c;

    /* JADX INFO: renamed from: d */
    private EGL10 f1003d;

    /* JADX INFO: renamed from: h */
    private GL10 f1007h;

    /* JADX INFO: renamed from: k */
    private final GestureDetectorOnDoubleTapListenerC0732F f1010k;

    /* JADX INFO: renamed from: e */
    private EGLDisplay f1004e = EGL10.EGL_NO_DISPLAY;

    /* JADX INFO: renamed from: f */
    private EGLContext f1005f = EGL10.EGL_NO_CONTEXT;

    /* JADX INFO: renamed from: g */
    private EGLSurface f1006g = EGL10.EGL_NO_SURFACE;

    /* JADX INFO: renamed from: i */
    private int f1008i = 1;

    /* JADX INFO: renamed from: j */
    private boolean f1009j = false;

    /* JADX INFO: renamed from: com.baidu.platform.comapi.map.m$a */
    public interface a {
        /* JADX INFO: renamed from: a */
        int mo583a();
    }

    public C0754m(SurfaceTexture surfaceTexture, a aVar, AtomicBoolean atomicBoolean, GestureDetectorOnDoubleTapListenerC0732F gestureDetectorOnDoubleTapListenerC0732F) {
        this.f1001b = surfaceTexture;
        this.f1002c = aVar;
        this.f1000a = atomicBoolean;
        this.f1010k = gestureDetectorOnDoubleTapListenerC0732F;
    }

    /* JADX INFO: renamed from: a */
    private boolean m722a(int i, int i2, int i3, int i4, int i5, int i6) {
        EGL10 egl10 = (EGL10) EGLContext.getEGL();
        this.f1003d = egl10;
        EGLDisplay eGLDisplayEglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        this.f1004e = eGLDisplayEglGetDisplay;
        if (eGLDisplayEglGetDisplay == EGL10.EGL_NO_DISPLAY) {
            throw new RuntimeException("eglGetdisplay failed : " + GLUtils.getEGLErrorString(this.f1003d.eglGetError()));
        }
        if (!this.f1003d.eglInitialize(this.f1004e, new int[2])) {
            throw new RuntimeException("eglInitialize failed : " + GLUtils.getEGLErrorString(this.f1003d.eglGetError()));
        }
        EGLConfig[] eGLConfigArr = new EGLConfig[100];
        int[] iArr = new int[1];
        if (!this.f1003d.eglChooseConfig(this.f1004e, new int[]{12324, i, 12323, i2, 12322, i3, 12321, i4, 12325, i5, 12326, i6, 12344}, eGLConfigArr, 100, iArr) || iArr[0] <= 0) {
            return false;
        }
        this.f1005f = this.f1003d.eglCreateContext(this.f1004e, eGLConfigArr[0], EGL10.EGL_NO_CONTEXT, new int[]{EGLBase.EGL_CONTEXT_CLIENT_VERSION, 2, 12344});
        EGLSurface eGLSurfaceEglCreateWindowSurface = this.f1003d.eglCreateWindowSurface(this.f1004e, eGLConfigArr[0], this.f1001b, null);
        this.f1006g = eGLSurfaceEglCreateWindowSurface;
        if (eGLSurfaceEglCreateWindowSurface == EGL10.EGL_NO_SURFACE || this.f1005f == EGL10.EGL_NO_CONTEXT) {
            if (this.f1003d.eglGetError() == 12299) {
                throw new RuntimeException("eglCreateWindowSurface returned  EGL_BAD_NATIVE_WINDOW. ");
            }
            GLUtils.getEGLErrorString(this.f1003d.eglGetError());
        }
        EGL10 egl102 = this.f1003d;
        EGLDisplay eGLDisplay = this.f1004e;
        EGLSurface eGLSurface = this.f1006g;
        if (egl102.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, this.f1005f)) {
            this.f1007h = (GL10) this.f1005f.getGL();
            return true;
        }
        GLUtils.getEGLErrorString(this.f1003d.eglGetError());
        throw new RuntimeException("eglMakeCurrent failed : " + GLUtils.getEGLErrorString(this.f1003d.eglGetError()));
    }

    /* JADX INFO: renamed from: b */
    private static boolean m723b(int i, int i2, int i3, int i4, int i5, int i6) {
        EGL10 egl10 = (EGL10) EGLContext.getEGL();
        EGLDisplay eGLDisplayEglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        egl10.eglInitialize(eGLDisplayEglGetDisplay, new int[2]);
        int[] iArr = new int[1];
        return egl10.eglChooseConfig(eGLDisplayEglGetDisplay, new int[]{12324, i, 12323, i2, 12322, i3, 12321, i4, 12325, i5, 12326, i6, 12344}, new EGLConfig[100], 100, iArr) && iArr[0] > 0;
    }

    /* JADX INFO: renamed from: d */
    private void m724d() {
        try {
            if (m723b(5, 6, 5, 0, 24, 0)) {
                m722a(5, 6, 5, 0, 24, 0);
            } else {
                m722a(8, 8, 8, 0, 16, 0);
            }
        } catch (IllegalArgumentException unused) {
            m722a(8, 8, 8, 0, 16, 0);
        }
        MapRenderer.nativeInit(this.f1010k.m585b().f954h);
        MapRenderer.nativeResize(this.f1010k.m585b().f954h, GestureDetectorOnDoubleTapListenerC0732F.f873a, GestureDetectorOnDoubleTapListenerC0732F.f874b);
    }

    /* JADX INFO: renamed from: e */
    private void m725e() {
        this.f1003d.eglDestroyContext(this.f1004e, this.f1005f);
        this.f1003d.eglDestroySurface(this.f1004e, this.f1006g);
        this.f1003d.eglTerminate(this.f1004e);
        this.f1005f = EGL10.EGL_NO_CONTEXT;
        this.f1006g = EGL10.EGL_NO_SURFACE;
    }

    /* JADX INFO: renamed from: a */
    public void m726a() {
        this.f1008i = 1;
        synchronized (this) {
            if (getState() == Thread.State.WAITING) {
                notify();
            }
        }
    }

    /* JADX INFO: renamed from: b */
    public void m727b() {
        this.f1008i = 0;
    }

    /* JADX INFO: renamed from: c */
    public void m728c() {
        this.f1009j = true;
        synchronized (this) {
            if (getState() == Thread.State.WAITING) {
                notify();
            }
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        m724d();
        while (this.f1002c != null) {
            if (this.f1008i != 1) {
                try {
                    synchronized (this) {
                        wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                if (this.f1010k.m585b() == null) {
                    break;
                }
                synchronized (this.f1010k.m585b()) {
                    this.f1008i = this.f1002c.mo583a();
                    for (InterfaceC0753l interfaceC0753l : this.f1010k.m585b().f952f) {
                        C0731E c0731eM624H = this.f1010k.m585b().m624H();
                        if (this.f1007h == null) {
                            return;
                        } else {
                            interfaceC0753l.mo415a(this.f1007h, c0731eM624H);
                        }
                    }
                    this.f1003d.eglSwapBuffers(this.f1004e, this.f1006g);
                }
            }
            if (this.f1009j) {
                break;
            }
        }
        m725e();
    }
}
