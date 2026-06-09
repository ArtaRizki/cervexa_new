package com.serenegiant.utils;

import android.opengl.GLES20;
import com.serenegiant.glutils.EGLBase;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class OpenGLInfo {
    private static final int EGL_CLIENT_APIS = 12429;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v2 */
    public static JSONObject get() throws Throwable {
        JSONObject jSONObject;
        EGLBase eGLBaseCreateFrom;
        EGLBase.IEglSurface iEglSurfaceCreateOffscreen;
        EGLBase eGLBase;
        JSONObject jSONObject2;
        String str;
        String str2;
        JSONObject jSONObject3;
        String str3;
        String str4;
        String str5;
        JSONObject jSONObject4 = "GL_RENDERER";
        JSONObject jSONObject5 = new JSONObject();
        try {
            eGLBaseCreateFrom = EGLBase.createFrom(3, null, false, 0, false);
            iEglSurfaceCreateOffscreen = eGLBaseCreateFrom.createOffscreen(1, 1);
            iEglSurfaceCreateOffscreen.makeCurrent();
            try {
                try {
                    IntBuffer intBufferAllocate = IntBuffer.allocate(2);
                    jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put("GL_VENDOR", GLES20.glGetString(7936));
                    } catch (Exception e) {
                        jSONObject2.put("GL_VENDOR", e.getMessage());
                    }
                    try {
                        jSONObject2.put("GL_VERSION", GLES20.glGetString(7938));
                    } catch (Exception e2) {
                        jSONObject2.put("GL_VERSION", e2.getMessage());
                    }
                    try {
                        jSONObject2.put("GL_RENDERER", GLES20.glGetString(7937));
                    } catch (Exception e3) {
                        jSONObject2.put("GL_RENDERER", e3.getMessage());
                    }
                    try {
                        GLES20.glGetIntegerv(34921, intBufferAllocate);
                        jSONObject2.put("GL_MAX_VERTEX_ATTRIBS", intBufferAllocate.get(0));
                    } catch (Exception e4) {
                        jSONObject2.put("GL_MAX_VERTEX_ATTRIBS", e4.getMessage());
                    }
                    try {
                        GLES20.glGetIntegerv(36347, intBufferAllocate);
                        jSONObject2.put("GL_MAX_VERTEX_UNIFORM_VECTORS", intBufferAllocate.get(0));
                    } catch (Exception e5) {
                        jSONObject2.put("GL_MAX_VERTEX_UNIFORM_VECTORS", e5.getMessage());
                    }
                    try {
                        GLES20.glGetIntegerv(36348, intBufferAllocate);
                        jSONObject2.put("GL_MAX_VARYING_VECTORS", intBufferAllocate.get(0));
                    } catch (Exception e6) {
                        jSONObject2.put("GL_MAX_VARYING_VECTORS", e6.getMessage());
                    }
                    try {
                        GLES20.glGetIntegerv(35661, intBufferAllocate);
                        jSONObject2.put("GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS", intBufferAllocate.get(0));
                    } catch (Exception e7) {
                        jSONObject2.put("GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS", e7.getMessage());
                    }
                    try {
                        GLES20.glGetIntegerv(35660, intBufferAllocate);
                        jSONObject2.put("GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS", intBufferAllocate.get(0));
                    } catch (Exception e8) {
                        jSONObject2.put("GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS", e8.getMessage());
                    }
                    try {
                        GLES20.glGetIntegerv(34930, intBufferAllocate);
                        jSONObject2.put("GL_MAX_TEXTURE_IMAGE_UNITS", intBufferAllocate.get(0));
                    } catch (Exception e9) {
                        jSONObject2.put("GL_MAX_TEXTURE_IMAGE_UNITS", e9.getMessage());
                    }
                    try {
                        GLES20.glGetIntegerv(36349, intBufferAllocate);
                        jSONObject2.put("GL_MAX_FRAGMENT_UNIFORM_VECTORS", intBufferAllocate.get(0));
                    } catch (Exception e10) {
                        jSONObject2.put("GL_MAX_FRAGMENT_UNIFORM_VECTORS", e10.getMessage());
                    }
                    try {
                        GLES20.glGetIntegerv(34076, intBufferAllocate);
                        jSONObject2.put("GL_MAX_CUBE_MAP_TEXTURE_SIZE", intBufferAllocate.get(0));
                    } catch (Exception e11) {
                        jSONObject2.put("GL_MAX_CUBE_MAP_TEXTURE_SIZE", e11.getMessage());
                    }
                    try {
                        GLES20.glGetIntegerv(34024, intBufferAllocate);
                        jSONObject2.put("GL_MAX_RENDERBUFFER_SIZE", intBufferAllocate.get(0));
                    } catch (Exception e12) {
                        jSONObject2.put("GL_MAX_RENDERBUFFER_SIZE", e12.getMessage());
                    }
                    try {
                        GLES20.glGetIntegerv(3379, intBufferAllocate);
                        jSONObject2.put("GL_MAX_TEXTURE_SIZE", intBufferAllocate.get(0));
                    } catch (Exception e13) {
                        jSONObject2.put("GL_MAX_TEXTURE_SIZE", e13.getMessage());
                    }
                    try {
                        GLES20.glGetIntegerv(3386, intBufferAllocate);
                        str = "GL_MAX_VIEWPORT_DIMS";
                        try {
                            jSONObject2.put(str, String.format(Locale.US, "%d x %d", Integer.valueOf(intBufferAllocate.get(0)), Integer.valueOf(intBufferAllocate.get(1))));
                        } catch (Exception e14) {
                            e = e14;
                            jSONObject2.put(str, e.getMessage());
                        }
                    } catch (Exception e15) {
                        e = e15;
                        str = "GL_MAX_VIEWPORT_DIMS";
                    }
                    try {
                        str2 = "GL_EXTENSIONS";
                        try {
                            jSONObject2.put(str2, formatExtensions(GLES20.glGetString(7939)));
                        } catch (Exception e16) {
                            e = e16;
                            jSONObject2.put(str2, e.getMessage());
                        }
                    } catch (Exception e17) {
                        e = e17;
                        str2 = "GL_EXTENSIONS";
                    }
                    jSONObject3 = jSONObject5;
                } catch (Throwable th) {
                    th = th;
                    eGLBase = eGLBaseCreateFrom;
                    iEglSurfaceCreateOffscreen.release();
                    eGLBase.release();
                    throw th;
                }
            } catch (Exception e18) {
                e = e18;
                jSONObject4.put("EXCEPTION", e.getMessage());
                jSONObject = jSONObject4;
            }
        } catch (Exception e19) {
            e = e19;
            jSONObject4 = jSONObject5;
        }
        try {
            jSONObject3.put("GL_INFO", jSONObject2);
            JSONObject jSONObject6 = new JSONObject();
            eGLBase = eGLBaseCreateFrom;
            try {
                try {
                    str3 = "EGL_VENDOR";
                    try {
                        jSONObject6.put(str3, eGLBase.queryString(12371));
                    } catch (Exception e20) {
                        e = e20;
                        jSONObject2.put(str3, e.getMessage());
                    }
                } catch (Throwable th2) {
                    th = th2;
                    iEglSurfaceCreateOffscreen.release();
                    eGLBase.release();
                    throw th;
                }
            } catch (Exception e21) {
                e = e21;
                str3 = "EGL_VENDOR";
            }
            try {
                str4 = "EGL_VERSION";
                try {
                    jSONObject6.put(str4, eGLBase.queryString(12372));
                } catch (Exception e22) {
                    e = e22;
                    jSONObject2.put(str4, e.getMessage());
                }
            } catch (Exception e23) {
                e = e23;
                str4 = "EGL_VERSION";
            }
            try {
                str5 = "EGL_CLIENT_APIS";
                try {
                    jSONObject6.put(str5, eGLBase.queryString(EGL_CLIENT_APIS));
                } catch (Exception e24) {
                    e = e24;
                    jSONObject2.put(str5, e.getMessage());
                }
            } catch (Exception e25) {
                e = e25;
                str5 = "EGL_CLIENT_APIS";
            }
            try {
                jSONObject6.put("EGL_EXTENSIONS:", formatExtensions(eGLBase.queryString(12373)));
            } catch (Exception e26) {
                jSONObject2.put("EGL_EXTENSIONS", e26.getMessage());
            }
            jSONObject3.put("EGL_INFO", jSONObject6);
            iEglSurfaceCreateOffscreen.release();
            eGLBase.release();
            jSONObject = jSONObject3;
            return jSONObject;
        } catch (Throwable th3) {
            th = th3;
            eGLBase = eGLBaseCreateFrom;
            iEglSurfaceCreateOffscreen.release();
            eGLBase.release();
            throw th;
        }
    }

    private static final JSONObject formatExtensions(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        String[] strArrSplit = str.split(" ");
        Arrays.sort(strArrSplit);
        for (int i = 0; i < strArrSplit.length; i++) {
            jSONObject.put(Integer.toString(i), strArrSplit[i]);
        }
        return jSONObject;
    }
}
