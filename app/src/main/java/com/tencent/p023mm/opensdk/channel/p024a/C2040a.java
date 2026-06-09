package com.tencent.p023mm.opensdk.channel.p024a;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.p023mm.opensdk.constants.ConstantsAPI;
import com.tencent.p023mm.opensdk.utils.C2052d;
import com.tencent.p023mm.opensdk.utils.Log;

/* JADX INFO: renamed from: com.tencent.mm.opensdk.channel.a.a */
/* JADX INFO: loaded from: classes2.dex */
public final class C2040a {

    /* JADX INFO: renamed from: com.tencent.mm.opensdk.channel.a.a$a */
    public static class a {

        /* JADX INFO: renamed from: a */
        public String f3066a;
        public String action;

        /* JADX INFO: renamed from: b */
        public long f3067b;
        public Bundle bundle;
        public String content;
    }

    /* JADX INFO: renamed from: a */
    public static boolean m2036a(Context context, a aVar) {
        String str;
        if (context == null) {
            str = "send fail, invalid argument";
        } else {
            if (!C2052d.m2059a(aVar.action)) {
                String str2 = null;
                if (!C2052d.m2059a(aVar.f3066a)) {
                    str2 = aVar.f3066a + ".permission.MM_MESSAGE";
                }
                Intent intent = new Intent(aVar.action);
                if (aVar.bundle != null) {
                    intent.putExtras(aVar.bundle);
                }
                String packageName = context.getPackageName();
                intent.putExtra(ConstantsAPI.SDK_VERSION, 620823552);
                intent.putExtra(ConstantsAPI.APP_PACKAGE, packageName);
                intent.putExtra(ConstantsAPI.CONTENT, aVar.content);
                intent.putExtra(ConstantsAPI.APP_SUPORT_CONTENT_TYPE, aVar.f3067b);
                intent.putExtra(ConstantsAPI.CHECK_SUM, C2041b.m2037a(aVar.content, 620823552, packageName));
                context.sendBroadcast(intent, str2);
                Log.m2050d("MicroMsg.SDK.MMessage", "send mm message, intent=" + intent + ", perm=" + str2);
                return true;
            }
            str = "send fail, action is null";
        }
        Log.m2051e("MicroMsg.SDK.MMessage", str);
        return false;
    }
}
