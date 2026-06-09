package com.baidu.mapapi;

import com.baidu.platform.comjni.tools.C0785a;

/* JADX INFO: loaded from: classes.dex */
public class OpenLogUtil {

    /* JADX INFO: renamed from: a */
    private static ModuleName f185a;

    public static void setModuleLogEnable(ModuleName moduleName, boolean z) {
        f185a = moduleName;
        C0785a.m900a(z, moduleName.ordinal());
    }
}
