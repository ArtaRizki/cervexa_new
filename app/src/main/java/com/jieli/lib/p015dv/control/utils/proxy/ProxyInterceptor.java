package com.jieli.lib.p015dv.control.utils.proxy;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public interface ProxyInterceptor {
    boolean onIntercept(Object obj, Method method, Object[] objArr);
}
