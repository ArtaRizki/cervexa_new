package org.apache.commons.net.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.net.ssl.SSLSocket;

/* JADX INFO: loaded from: classes2.dex */
public class SSLSocketUtils {
    private SSLSocketUtils() {
    }

    public static boolean enableEndpointNameVerification(SSLSocket sSLSocket) {
        Object objInvoke;
        try {
            Class<?> cls = Class.forName("javax.net.ssl.SSLParameters");
            Method declaredMethod = cls.getDeclaredMethod("setEndpointIdentificationAlgorithm", String.class);
            Method declaredMethod2 = SSLSocket.class.getDeclaredMethod("getSSLParameters", new Class[0]);
            Method declaredMethod3 = SSLSocket.class.getDeclaredMethod("setSSLParameters", cls);
            if (declaredMethod != null && declaredMethod2 != null && declaredMethod3 != null && (objInvoke = declaredMethod2.invoke(sSLSocket, new Object[0])) != null) {
                declaredMethod.invoke(objInvoke, "HTTPS");
                declaredMethod3.invoke(sSLSocket, objInvoke);
                return true;
            }
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException unused) {
        }
        return false;
    }
}
