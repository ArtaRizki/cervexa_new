package com.baidu.platform.comapi.map;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import java.util.Iterator;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: loaded from: classes.dex */
public class MapRenderer implements GLSurfaceView.Renderer {

    /* JADX INFO: renamed from: d */
    private static final String f885d = MapRenderer.class.getSimpleName();

    /* JADX INFO: renamed from: a */
    public int f886a;

    /* JADX INFO: renamed from: b */
    public int f887b;

    /* JADX INFO: renamed from: c */
    public int f888c;

    /* JADX INFO: renamed from: e */
    private long f889e;

    /* JADX INFO: renamed from: f */
    private InterfaceC0740a f890f;

    /* JADX INFO: renamed from: g */
    private final GestureDetectorOnDoubleTapListenerC0751j f891g;

    /* JADX INFO: renamed from: com.baidu.platform.comapi.map.MapRenderer$a */
    public interface InterfaceC0740a {
        /* JADX INFO: renamed from: e */
        void mo598e();
    }

    public MapRenderer(GestureDetectorOnDoubleTapListenerC0751j gestureDetectorOnDoubleTapListenerC0751j, InterfaceC0740a interfaceC0740a) {
        this.f890f = interfaceC0740a;
        this.f891g = gestureDetectorOnDoubleTapListenerC0751j;
    }

    /* JADX INFO: renamed from: a */
    private void m595a(GL10 gl10) {
        GLES20.glClear(16640);
        GLES20.glClearColor(0.85f, 0.8f, 0.8f, 0.0f);
    }

    /* JADX INFO: renamed from: a */
    private boolean m596a() {
        return this.f889e != 0;
    }

    public static native void nativeInit(long j);

    public static native int nativeRender(long j);

    public static native void nativeResize(long j, int i, int i2);

    /* JADX INFO: renamed from: a */
    public void m597a(long j) {
        this.f889e = j;
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl10) {
        if (!m596a()) {
            m595a(gl10);
            return;
        }
        if (this.f888c <= 1) {
            nativeResize(this.f889e, this.f886a, this.f887b);
            this.f888c++;
        }
        this.f890f.mo598e();
        int iNativeRender = nativeRender(this.f889e);
        Iterator<InterfaceC0753l> it = this.f891g.m716a().f952f.iterator();
        while (it.hasNext()) {
            it.next().mo415a(gl10, this.f891g.m716a().m624H());
        }
        GestureDetectorOnDoubleTapListenerC0751j gestureDetectorOnDoubleTapListenerC0751j = this.f891g;
        if (iNativeRender == 1) {
            gestureDetectorOnDoubleTapListenerC0751j.requestRender();
            return;
        }
        if (gestureDetectorOnDoubleTapListenerC0751j.m716a().m664c()) {
            if (gestureDetectorOnDoubleTapListenerC0751j.getRenderMode() != 1) {
                gestureDetectorOnDoubleTapListenerC0751j.setRenderMode(1);
            }
        } else if (gestureDetectorOnDoubleTapListenerC0751j.getRenderMode() != 0) {
            gestureDetectorOnDoubleTapListenerC0751j.setRenderMode(0);
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        long j = this.f889e;
        if (j != 0) {
            nativeResize(j, i, i2);
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        nativeInit(this.f889e);
        if (m596a()) {
            this.f890f.mo598e();
        }
    }
}
