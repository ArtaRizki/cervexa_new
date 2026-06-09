package com.jieli.lib.p015dv.control.utils.proxy;

import android.text.TextUtils;
import com.jieli.lib.p015dv.control.utils.Dlog;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class ProxyBulk {
    public Object[] args;
    public Method method;
    public Object object;
    String tag = getClass().getSimpleName();

    public ProxyBulk(Object obj, Method method, Object[] objArr) {
        this.object = obj;
        this.method = method;
        this.args = objArr;
    }

    public Object safeInvoke() {
        try {
            return this.method.invoke(this.object, this.args);
        } catch (Throwable th) {
            if (!TextUtils.isEmpty(th.getMessage())) {
                Dlog.m1383e(this.tag, th.getMessage());
            }
            return null;
        }
    }

    public static Object safeInvoke(Object obj) {
        return ((ProxyBulk) obj).safeInvoke();
    }
}
