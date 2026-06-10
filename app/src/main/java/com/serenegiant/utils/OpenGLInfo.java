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

    public static JSONObject get() {
        JSONObject result = new JSONObject();
        EGLBase eglBase = null;
        EGLBase.IEglSurface offscreenSurface = null;
        try {
            eglBase = EGLBase.createFrom(3, null, false, 0, false);
            offscreenSurface = eglBase.createOffscreen(1, 1);
            offscreenSurface.makeCurrent();

            JSONObject glInfo = new JSONObject();
            put(glInfo, "GL_VENDOR", GLES20.glGetString(GLES20.GL_VENDOR));
            put(glInfo, "GL_VERSION", GLES20.glGetString(GLES20.GL_VERSION));
            put(glInfo, "GL_RENDERER", GLES20.glGetString(GLES20.GL_RENDERER));

            IntBuffer intBuffer = IntBuffer.allocate(2);
            putInt(glInfo, "GL_MAX_VERTEX_ATTRIBS", GLES20.GL_MAX_VERTEX_ATTRIBS, intBuffer);
            putInt(glInfo, "GL_MAX_VERTEX_UNIFORM_VECTORS", GLES20.GL_MAX_VERTEX_UNIFORM_VECTORS, intBuffer);
            putInt(glInfo, "GL_MAX_VARYING_VECTORS", GLES20.GL_MAX_VARYING_VECTORS, intBuffer);
            putInt(glInfo, "GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS", GLES20.GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS, intBuffer);
            putInt(glInfo, "GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS", GLES20.GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS, intBuffer);
            putInt(glInfo, "GL_MAX_TEXTURE_IMAGE_UNITS", GLES20.GL_MAX_TEXTURE_IMAGE_UNITS, intBuffer);
            putInt(glInfo, "GL_MAX_FRAGMENT_UNIFORM_VECTORS", GLES20.GL_MAX_FRAGMENT_UNIFORM_VECTORS, intBuffer);
            putInt(glInfo, "GL_MAX_CUBE_MAP_TEXTURE_SIZE", GLES20.GL_MAX_CUBE_MAP_TEXTURE_SIZE, intBuffer);
            putInt(glInfo, "GL_MAX_RENDERBUFFER_SIZE", GLES20.GL_MAX_RENDERBUFFER_SIZE, intBuffer);
            putInt(glInfo, "GL_MAX_TEXTURE_SIZE", GLES20.GL_MAX_TEXTURE_SIZE, intBuffer);

            try {
                GLES20.glGetIntegerv(GLES20.GL_MAX_VIEWPORT_DIMS, intBuffer);
                glInfo.put("GL_MAX_VIEWPORT_DIMS", String.format(Locale.US, "%d x %d", intBuffer.get(0), intBuffer.get(1)));
            } catch (Exception e) {
                put(glInfo, "GL_MAX_VIEWPORT_DIMS", e.getMessage());
            }

            try {
                glInfo.put("GL_EXTENSIONS", formatExtensions(GLES20.glGetString(GLES20.GL_EXTENSIONS)));
            } catch (Exception e) {
                put(glInfo, "GL_EXTENSIONS", e.getMessage());
            }

            result.put("GL_INFO", glInfo);

            JSONObject eglInfo = new JSONObject();
            put(eglInfo, "EGL_VENDOR", eglBase.queryString(12371));
            put(eglInfo, "EGL_VERSION", eglBase.queryString(12372));
            put(eglInfo, "EGL_CLIENT_APIS", eglBase.queryString(EGL_CLIENT_APIS));

            try {
                eglInfo.put("EGL_EXTENSIONS", formatExtensions(eglBase.queryString(12373)));
            } catch (Exception e) {
                put(eglInfo, "EGL_EXTENSIONS", e.getMessage());
            }

            result.put("EGL_INFO", eglInfo);

        } catch (Exception e) {
            try {
                result.put("EXCEPTION", e.getMessage());
            } catch (JSONException ignored) {}
        } finally {
            if (offscreenSurface != null) offscreenSurface.release();
            if (eglBase != null) eglBase.release();
        }
        return result;
    }

    private static void put(JSONObject obj, String key, String value) {
        try {
            obj.put(key, value);
        } catch (JSONException ignored) {}
    }

    private static void putInt(JSONObject obj, String key, int pname, IntBuffer buffer) {
        try {
            GLES20.glGetIntegerv(pname, buffer);
            obj.put(key, buffer.get(0));
        } catch (Exception e) {
            put(obj, key, e.getMessage());
        }
    }

    private static final JSONObject formatExtensions(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (str != null) {
            String[] strArrSplit = str.split(" ");
            Arrays.sort(strArrSplit);
            for (int i = 0; i < strArrSplit.length; i++) {
                jSONObject.put(Integer.toString(i), strArrSplit[i]);
            }
        }
        return jSONObject;
    }
}
