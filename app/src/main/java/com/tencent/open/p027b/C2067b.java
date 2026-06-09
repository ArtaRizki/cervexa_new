package com.tencent.open.p027b;

import android.os.Bundle;
import java.io.Serializable;
import java.util.HashMap;

/* JADX INFO: renamed from: com.tencent.open.b.b */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class C2067b implements Serializable {

    /* JADX INFO: renamed from: a */
    public final HashMap<String, String> f3187a = new HashMap<>();

    public C2067b(Bundle bundle) {
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                this.f3187a.put(str, bundle.getString(str));
            }
        }
    }
}
